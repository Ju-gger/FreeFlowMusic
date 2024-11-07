package ffm.freeflowmusic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SettingsController {

    //xml references
    @FXML
    private TextField dirText;

    //private variables
    private Stage stage;
    private Parent parent;
    private Scene scene;


    //will pull up a directory window to choose song locations
    public void changeDirectory(ActionEvent event) throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); //gets current scene
        File selectedDirectory = directoryChooser.showDialog(stage); //initiates dir viewer

        dirText.setText(selectedDirectory.getAbsolutePath());

    }
}
