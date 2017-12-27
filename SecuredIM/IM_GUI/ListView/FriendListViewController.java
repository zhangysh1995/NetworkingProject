package IM_GUI.ListView;

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
public class FriendListViewController {
    // add new friend
    @FXML private TextField email;
    @FXML private TextField notes;

    private MailHandler mailHandler;

    private ObservableList<String> names;

    public FriendListViewController() {
        mailHandler = new MailHandler();
    }

    public ObservableList<String> getFriendLVC() {
        if(names == null)
            names = FXCollections.observableArrayList(
                    "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");

        return names;
    }

    @FXML private void addFriend() {
        if(mailHandler.sendRequestMail(email.getText())) {
            email.getScene().getWindow().hide(); // hide addFriend window
        } else System.err.println("Error when sending message");
    }
}
