package DataManager;

import Utility.MailHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Vector;

public class UserManager {
    private static Vector<String> friendList;
    private static Vector<String> requestList; // received request to add
    private static Vector<String> sentList; // sent request

    public UserManager() {
        friendList = new Vector<>(2);
    }

//    public Vector<String> addFriend(String email) {
//        sentList.add(email);
//    }

    public static Vector<String> getFriendList() {
        return friendList;
    }

    public static void setFriendList(Vector<String> friendList) {
        UserManager.friendList = friendList;
    }

    public static Vector<String> getRequestList() {
        return requestList;
    }

    public static void setRequestList(Vector<String> requestList) {
        UserManager.requestList = requestList;
    }

    public static Vector<String> getSentList() {
        return sentList;
    }

    public static void setSentList(Vector<String> sentList) {
        UserManager.sentList = sentList;
    }

    public Boolean blockUser() {
        return true;
    }

    public Boolean deleteUser() {
        return true;
    }
}
