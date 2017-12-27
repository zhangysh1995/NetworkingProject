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

    public Boolean setUserList(Vector<String> list) {
            friendList = list;
            return true;
    }

    public Vector<String> getUserList() {
        return friendList;
    }

    // add new friend
    @FXML private TextField email;
    @FXML private TextField notes;

    private MailHandler mailHandler = new MailHandler();

    @FXML
    public Vector<String> addFriend(ActionEvent actionEvent) {
        //
        if(mailHandler.sendRequestMail(email.getText())) {
            email.getScene().getWindow().hide(); // hide addFriend window
        }
        return friendList;
    }

    public Boolean blockUser() {
        return true;
    }

    public Boolean deleteUser() {
        return true;
    }
}
