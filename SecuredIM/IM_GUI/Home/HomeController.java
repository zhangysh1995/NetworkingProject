package IM_GUI.Home;

import DataManager.GroupManager;
import DataManager.UserManager;
import IM_GUI.ListView.FriendListViewController;
import IM_GUI.ListView.GroupListViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    // listviews
    @FXML private ListView<String> friendList;
    @FXML private ListView<String> groupList;
    @FXML private ListView<String> blockedList;

    // menu items
    @FXML private MenuItem addFriend;
    @FXML private MenuItem addGroup;

    // view controllers
    private FriendListViewController friendLVC = new FriendListViewController();
    private GroupListViewController groupLVC = new GroupListViewController();
    // TODO: BlockedViewController

    // delegate to managers
    private UserManager userManager;
    private GroupManager groupManager;

    @FXML
    public void initialize() {
        userManager = new UserManager();
        groupManager = new GroupManager();
        friendList.setItems(friendLVC.getFriendLVC());
    }

    // TODO: use actual data to replace demo
    @FXML
    private void addFriend(ActionEvent actionEvent) throws IOException{
//        addFriend.setText("pressed");
//        userManager.addUser();
        newWindow("AddUser.fxml");

    }

    @FXML
    private void newGroup(ActionEvent actionEvent) throws IOException{
//        addGroup.setText("pressed");
//        groupManager.newGroup();
        newWindow("NewGroup.fxml");

    }

    private void newWindow(String file) throws IOException{
        Parent mainFrame = FXMLLoader.load(getClass().getResource(file));
        Scene scene = new Scene(mainFrame);
        Stage newStage = new Stage();

        newStage.setScene(scene);
        newStage.show();
    }

}
