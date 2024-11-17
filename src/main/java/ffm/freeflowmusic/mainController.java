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
    private AnchorPane musicView, albumView, discView, listView, settingsView, musicViewSmall, musicViewLarge;

    //This var is meant to represent the main music player used throughout the application.
    private PlayerController mediaPlayer = null;

    //method loads all views that are needed to allow a user to use the application.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Parent root = null;

        //We use the below structure to load the view so we can grab its controller to assign our music player.
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("smallplayer-ui.fxml"));
            root = loader.load();
            mediaPlayer = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        musicViewSmall.getChildren().add(root);

        try {
            root = FXMLLoader.load(getClass().getResource("songview-ui.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        musicView.getChildren().add(root);

        /*
        //remove comments once fxml files are made

        try {
            root = FXMLLoader.load(getClass().getResource("album-ui.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        albumView.getChildren().add(root);
        *
        *
        *
        try {
            root = FXMLLoader.load(getClass().getResource("playlist-ui.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        listView.getChildren().add(root);
        */

        //method uses loader to first pass our media player loaded earlier to our DiscoverController/discover view for it to use.
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("discoverview-ui.fxml"));
            loader.setControllerFactory(mainController -> new DiscoverController(mediaPlayer));
            root = loader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        discView.getChildren().add(root);

        try {
            root = FXMLLoader.load(getClass().getResource("settings-ui.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        settingsView.getChildren().add(root);
    }
}
