package IM_GUI.Chatting;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class P2PchatController {
    private MouseEvent mouseEvent;
    private ListView listView;
    private String friend;
    private String email;
    private int index;

    @FXML private Label title;
    @FXML private TextField userInput;

    public P2PchatController(MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
        this.listView = (ListView) mouseEvent.getSource();
        email = (String) listView.getSelectionModel().getSelectedItem();
        index = listView.getSelectionModel().getSelectedIndex();
    }

    @FXML
    public void initialize() {
        title.setText("Chatroom: " + email);
        userInput.setText("Enter here...press `Enter` to send");
    }
}
