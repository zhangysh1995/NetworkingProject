package IM_GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class SecuredIM extends Application {
    private static final Logger LOGGER =
            Logger.getLogger( SecuredIM.class.getName() );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        // initialize welcome frame
        Parent root = FXMLLoader.load(getClass().getResource("login/Login.fxml"));
        Scene scene = new Scene(root, 400, 300);

        // show frame
        primaryStage.setTitle("Secured IM");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML private Button signIn;
    @FXML private Button test;
    @FXML private TextField userEmail;
    @FXML private PasswordField emailPass;
    @FXML private PasswordField gpgPass;
    @FXML private TextField smtp;
    @FXML private TextField imap;


    @FXML
    protected void testConnect(ActionEvent actionEvent) {
//        if(! MailHandler.connectSMTP(userEmail.getText(),
//                emailPass.getText(), smtp.getText())) showWarning("Connection SMTP failed.");

//        if(! MailHandler.connectIMAP(userEmail.getText(),
//                emailPass.getText(), imap.getText())) showWarning("Connection IMAP failed.");
    }

    private void showWarning(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR, text);
        alert.showAndWait();
    }

    @FXML
    protected void handleLogin(ActionEvent actionEvent) throws Exception {
        Parent mainFrame = FXMLLoader.load(getClass().getResource("Home/Home.fxml"));
        Scene scene = new Scene(mainFrame, 270, 450);
        Stage newStage = new Stage();

        // hide login window
        Stage lastStage = (Stage) signIn.getScene().getWindow();
        lastStage.hide();

        // show mainframe
        newStage.setScene(scene);
        newStage.show();

        // set user's email address
//        MailHandler.setMail("zhangyushao@zhangyushao.site");
//        MailHandler.setPassword(emailPass.getText());
//        MailHandler.setMySmtpServer(smtp.getText());
//        MailHandler.setMyImapServer(imap.getText());
    }
}