package IM_GUI;

import Utility.MailHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @FXML  private TextField userEmail;

    @FXML
    protected void handleLogin(ActionEvent actionEvent) throws Exception {
        Parent mainFrame = FXMLLoader.load(getClass().getResource("Home/Home.fxml"));
        Scene scene = new Scene(mainFrame, 270, 450);
        Stage newStage = new Stage();

        // set user's email address
        MailHandler.setMail(userEmail.getText());
        System.out.println("My email: " + userEmail.getText());

        // hide login window
        Stage lastStage = (Stage) signIn.getScene().getWindow();
        lastStage.hide();

        // show mainframe
        newStage.setScene(scene);
        newStage.show();
    }
}