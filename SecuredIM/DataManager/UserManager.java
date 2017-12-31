package DataManager;

import Utility.MailHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

<<<<<<< HEAD
import java.util.Arrays;
import java.util.Vector;

public class UserManager {
    private static Vector<String> friendList
            = new Vector<>(Arrays.asList("Julia", "Ian", "Sue"));
    private static Vector<String> requestList
            = new Vector<>(Arrays.asList("test@163.com", "test@126.com"));
    private static Vector<String> sentList
            = new Vector<>(2);

    public UserManager() {

=======
import java.util.Vector;

public class UserManager {
    private static Vector<String> friendList;
    private static Vector<String> requestList; // received request to add
    private static Vector<String> sentList; // sent request

    public UserManager() {
        friendList = new Vector<>(2);
>>>>>>> 55a7ad08d2ae2ea55c4b22bae9d91539e3a7284c
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

<<<<<<< HEAD
    public static Boolean addSend(String email) {
        sentList.add(email);
        return true;
    }

    public static Boolean blockUser() {
        return true;
    }

    public static Boolean deleteUser() {
=======
    public Boolean blockUser() {
        return true;
    }

    public Boolean deleteUser() {
>>>>>>> 55a7ad08d2ae2ea55c4b22bae9d91539e3a7284c
        return true;
    }
}
