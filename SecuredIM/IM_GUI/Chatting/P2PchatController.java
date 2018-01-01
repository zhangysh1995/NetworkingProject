package IM_GUI.Chatting;

import IM_GUI.Abstract.Controller;
import Utility.MailHandler;
import cyy_IM_protocol.CYY_PACKET_generator;
import cyy_IM_protocol.IM_Handler;
import cyy_IM_protocol.IM_capsulation;
import cyy_IM_protocol.Message_cyy;
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

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class P2PchatController extends Controller{
    private MouseEvent mouseEvent;
    private ListView listView;
    private String friend;
    private String email;
    private int index;

    private MailHandler mailHandler;
    private CYY_PACKET_generator cyy_packet_generator;

    private int seqNum;
    private int sessionId;

    private ExecutorService executor;
    private Thread rthread;

    @FXML private Label title;
    @FXML private ScrollPane scrollPane;
    @FXML private TextField userInput;
    @FXML private TextFlow textLog;

    public P2PchatController(MouseEvent mouseEvent, int sessionId) {
        this.mailHandler = new MailHandler();
        this.mouseEvent = mouseEvent;
        this.listView = (ListView) mouseEvent.getSource();
        this.email = (String) listView.getSelectionModel().getSelectedItem();
        this.index = listView.getSelectionModel().getSelectedIndex();

        this.seqNum = 0;
        this.sessionId = sessionId;
        this.cyy_packet_generator = new CYY_PACKET_generator();

        ReceiveMessage(this.mailHandler);
    }

    private int getSessionId() {return this.sessionId;}

    private int getSeqNum() {return this.seqNum;}

    private void updateSeqNum() { this.seqNum++;}

    @FXML
    public void sendMessage(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            String input = ":\n" + userInput.getText() + "\n";

            // prepare to send
            Message_cyy message = cyy_packet_generator.create_messageObj(userInput.getText(),
                    "GnuPG 2.0", getSeqNum(), System.currentTimeMillis(), getSessionId());

            IM_capsulation cap = cyy_packet_generator.capsulate("CYYClient 1.0",
                    IM_Handler.ACTION_individualSending, "IMAP",
                    "zhangyushao@zhangyushao.site", mailHandler.getMail());

            try {
                String content = new String(cyy_packet_generator.packet_generate(cap), "UTF-8");
                System.out.println(content);
//                mailHandler.sendMessage(email, content);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            // local update
            updateSeqNum();
            Text msg = new Text(mailHandler.getMail() + input);
            showNewMessage(msg);
        }
    }

    private void ReceiveMessage(MailHandler mailHandler){
        // callable to receive
        this.rthread = new Thread(new ReceivingWork(mailHandler, new CYY_PACKET_generator()));
        // asynchronous threading
        this.executor = Executors.newSingleThreadExecutor();
        executor.submit(rthread);
    };

    @Override
    public Boolean pushNewMsg(String msg) {
        String text = this.email + ":\n" + msg + "\n";
        Text line = new Text(text);
        showNewMessage(line);
        return true;
    }

    // life-cycle
    @FXML
    public void initialize() {
        title.setText("Chatroom: " + email);
        textLog.setLineSpacing(5);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        userInput.setText("Enter here...press `Enter` to send");
    }

    @Override
    public void shutdown() {
        System.out.println("Exited");
    }

    private void showNewMessage(Text msg) {
        textLog.getChildren().add(msg);
        scrollPane.setVvalue(scrollPane.getHmax());
    }

}
