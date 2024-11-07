package ffm.freeflowmusic;

import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/* TEST COMMENT */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader;

        loader = new FXMLLoader(getClass().getResource("mainview-ui.fxml"));

        /* This section is meant to read if a settings file was created.
        if (ProjectSettings.settingsExists()){
            loader = new FXMLLoader(getClass().getResource("musicplayer-ui.fxml"));
        }else {
            loader = new FXMLLoader(getClass().getResource("settings-ui.fxml"));//"musicplayer-ui.fxml"));
        }*/

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // Gracefully exit when pressing 'x' in the top-right
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }
}