package GUI;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import IM_GUI.SecuredIM;
import org.junit.Test;

public class BasicStart {

    @Test
    public void testA() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel(); // Initializes the JavaFx Platform
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            new SecuredIM().start(new Stage()); // Create and
                        } catch (java.lang.Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

}