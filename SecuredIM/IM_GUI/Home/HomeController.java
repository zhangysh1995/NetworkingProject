package IM_GUI.Home;

import IM_GUI.ListView.FriendListViewController;
import IM_GUI.ListView.GroupListViewController;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

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


    @FXML
    public void initialize() {

        friendList.setItems(friendLVC.getFriendLVC());

    }



}
