package ffm.freeflowmusic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    @FXML
    private AnchorPane musicView, albumView, discView, listView, settingsView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("musicplayer-ui.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        musicView.getChildren().add(root); //adds fxml to the tabs of the main scene

        /*//remove comments once fxml files are made
        * try {
            root = FXMLLoader.load(getClass().getResource("album-ui.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        albumView.getChildren().add(root);
        *
        * try {
            root = FXMLLoader.load(getClass().getResource("discover-ui.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        discView.getChildren().add(root);
        *
        * try {
            root = FXMLLoader.load(getClass().getResource("playlist-ui.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        listView.getChildren().add(root);*/

        try {
            root = FXMLLoader.load(getClass().getResource("settings-ui.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        settingsView.getChildren().add(root);
    }
}
