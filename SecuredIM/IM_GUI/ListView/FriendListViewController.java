package IM_GUI.ListView;

import IM_GUI.Abstract.Controller;
import DataManager.User;
import DataManager.UserManager;
import Utility.MailHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by KellyZhang on 2017/12/22.
 */
public class FriendListViewController extends Controller{
    // add new friend
    @FXML private TextField email;
    @FXML private TextField notes;

    private MailHandler mailHandler;

    public FriendListViewController() {
        mailHandler = new MailHandler();
    }

    public ObservableList<String> getFriendList() {
        return FXCollections.observableArrayList(UserManager.getFriendList());
    }

    public ObservableList<String> getSentList() {
        return FXCollections.observableArrayList(UserManager.getSentList());
    }

//    public ObservableList<String> updateSentList() {
//        return FXCollections.observableArrayList(UserManager.u)
//    }
//
//    public ObservableList<String> getFriendLVC() {
//
//    }

    @FXML private void addFriend() {
        if(mailHandler.sendRequestMail(email.getText())) {
            email.getScene().getWindow().hide(); // hide addFriend window
            if (UserManager.addSend(email.getText()))
                System.err.println("Error: save friend request to list");
        } else System.err.println("Error: send friend request");
    }
}
