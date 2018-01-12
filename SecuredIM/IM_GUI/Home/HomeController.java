package IM_GUI.Home;

import DataManager.GroupManager;
import IM_GUI.Abstract.Controller;
import IM_GUI.Chatting.P2PchatController;
import IM_GUI.ListView.FriendListViewController;
import IM_GUI.ListView.GroupListViewController;
import Utility.DeMultiplexer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeController extends Controller{
    private int sessionNum;
    // listviews
    @FXML private ListView<String> friendList;
    @FXML private ListView<String> groupList;
    @FXML private ListView<String> requestList;
    @FXML private ListView<String> sentList;
    @FXML private ListView<String> blockedList;

    // menu items
    @FXML private MenuItem addFriend;
    @FXML private MenuItem addGroup;

    // view controllers
    private FriendListViewController friendLVC;
    private GroupListViewController groupLVC;

    // for adding new friend
    @FXML private TextField email;
    @FXML private TextField notes;

    private GroupManager groupManager;
    private ConcurrentHashMap<String, Controller> controllerMap;
    private ConcurrentHashMap<Integer, Controller> groupControllerMap;

    private Thread deMultiplexer;
    private ExecutorService executor;

    // getters & setters
    public ConcurrentHashMap<String, Controller> getControllerMap() {
        return controllerMap;
    }

    public void updateControllerMap(String email, Controller controller) {
        this.controllerMap.put(email, controller);
    }

    @FXML
    public void initialize() {
        friendLVC = new FriendListViewController();
        groupLVC = new GroupListViewController();

        groupManager = new GroupManager();
        friendList.setItems(friendLVC.getFriendList());
        requestList.setItems(friendLVC.getRequestList());
        sentList.setItems(friendLVC.getSentList());
        blockedList.setItems(friendLVC.getBlockedList());

        this.sessionNum = 0;

        // prepare to receive message
        controllerMap = new ConcurrentHashMap<>(3);
        controllerMap.put("-1", this);

        groupControllerMap = new ConcurrentHashMap<>(0);
        groupControllerMap.put(-1, this);

        // callable to receive
        this.deMultiplexer = new Thread(new DeMultiplexer(groupControllerMap, controllerMap));
        // asynchronous threading
        this.executor = Executors.newSingleThreadExecutor();
        executor.submit(deMultiplexer);
    }

    private int getSessionNum() {return this.sessionNum;}
    private void updateSessionNum() {this.sessionNum++;}

    @FXML
    private void addFriend(ActionEvent actionEvent) throws IOException {
       newWindow("AddUser.fxml", this);
    }

    @FXML
    private void sentFriendRequest(ActionEvent actionEvent) {
        if(friendLVC.addFriend(email.getText())) {
            sentList.setItems(friendLVC.getSentList());
            email.getScene().getWindow().hide(); // hide addFriend window
        }
    }

    @FXML
    private void newGroup(ActionEvent actionEvent) throws IOException {
        newWindow("NewGroup.fxml", groupLVC);

    }

    @FXML
    private void onClickFriendList(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getButton() == MouseButton.PRIMARY){
            if(mouseEvent.getClickCount() == 2) {
                updateSessionNum();

                P2PchatController pchatController = new P2PchatController(mouseEvent, getSessionNum());
                newWindow("../Chatting/P2Pchat.fxml", pchatController);
                // add this controller to container
                updateControllerMap(pchatController.getEmail(), pchatController);
            }

        } else if(mouseEvent.getClickCount() == 2) {
            // block friend
            int index = getIndex(mouseEvent);
            if(friendLVC.blockUser(index)) {
                System.out.println("Blocked: " + index);
                friendList.setItems(friendLVC.getFriendList());
                blockedList.setItems(friendLVC.getBlockedList());
            }
            // else error
        } else if(mouseEvent.getClickCount() == 3) {
            int index = getIndex(mouseEvent);
            if(friendLVC.deleteFriend(index)) {
                System.out.println("Deleted: " + index);
                friendList.setItems(friendLVC.getFriendList());
            }
        }
    }

    @FXML
    public void onClickRequestList(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseButton.PRIMARY) {
            if(mouseEvent.getClickCount() == 2) {
                int index = getIndex(mouseEvent);
                if(friendLVC.acceptRequest(index)) {
                    System.out.println("Accepted request: " + index);
                    friendList.setItems(friendLVC.getFriendList());
                    requestList.setItems(friendLVC.getRequestList());
                }
            }
        } else if(mouseEvent.getClickCount() == 2) {
            int index = getIndex(mouseEvent);
            if(friendLVC.deleteRequest(index)) {
                System.out.println("Delete request: " + index);
                requestList.setItems(friendLVC.getRequestList());
            }
        }
    }

    // add new friend request to list
    public Boolean pushNewRequest(String email) {
        System.out.println("Received requests: " +email);
        if(friendLVC.addRequest(email)) {
            Platform.runLater(() -> requestList.setItems(friendLVC.getRequestList()));
            return true;
        } else {
            return false;
        }
    }

    public Boolean pushNewConfirm(String email) {
        if(friendLVC.sentAccepted(email)) {
            Platform.runLater(() -> sentList.setItems(friendLVC.getSentList()));
            Platform.runLater(() -> friendList.setItems(friendLVC.getFriendList()));
            return true;
        } else {
            return false;
        }
    }

    private int getIndex(MouseEvent mouseEvent) {
        ListView listView = (ListView) mouseEvent.getSource();
        String email = (String) listView.getSelectionModel().getSelectedItem();
        return listView.getSelectionModel().getSelectedIndex();
    }

    private void newWindow(String file, Controller controller) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
        loader.setController(controller);
        Parent mainFrame = loader.load();
        Scene scene = new Scene(mainFrame);
        Stage newStage = new Stage();

        // set default exit behavior
        newStage.setOnHidden(e -> {
            controller.shutdown();
        });

        // show the window
        newStage.setScene(scene);
        newStage.show();
    }

    @Override
    public void shutdown() {
        executor.shutdown();
//        Platform.exit();
//        System.exit(0);
    }
}
