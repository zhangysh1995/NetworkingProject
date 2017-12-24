package IM_GUI.Home;

import DataManager.GroupManager;
import DataManager.UserManager;
import IM_GUI.ListView.FriendListViewController;
import IM_GUI.ListView.GroupListViewController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

import javafx.event.ActionEvent;
import org.omg.PortableInterceptor.ACTIVE;

import javax.swing.*;

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
    private void addFriend(ActionEvent actionEvent) {
        addFriend.setText("pressed");
        userManager.addUser();
    }

    @FXML
    private void newGroup(ActionEvent actionEvemt) {
        addGroup.setText("pressed");
        groupManager.newGroup();

    }

}
