package IM_GUI.Chatting;

import Utility.MailHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class P2PchatController {
    private MouseEvent mouseEvent;
    private ListView listView;
    private String friend;
    private String email;
    private int index;

    private MailHandler mailHandler;

    @FXML private Label title;
    @FXML private ScrollPane scrollPane;
    @FXML private TextField userInput;
    @FXML private TextFlow textLog;

    public P2PchatController(MouseEvent mouseEvent) {
        mailHandler = new MailHandler();
        this.mouseEvent = mouseEvent;
        this.listView = (ListView) mouseEvent.getSource();
        email = (String) listView.getSelectionModel().getSelectedItem();
        index = listView.getSelectionModel().getSelectedIndex();
    }

    @FXML
    public void sendMessage(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            String input = ":\n" + userInput.getText() + "\n";
            Text msg = new Text(MailHandler.getMail() + input);
//            mailHandler.sendMessage(email, userInput.getText());
            showNewMessage(msg);
        }
    }

    @FXML
    public void ReceiveMessage(){};

    @FXML
    public void initialize() {
        title.setText("Chatroom: " + email);
        textLog.setLineSpacing(5);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        userInput.setText("Enter here...press `Enter` to send");
    }

    private void showNewMessage(Text msg) {
        textLog.getChildren().add(msg);
        scrollPane.setVvalue(scrollPane.getHmax());
    }
}
