/*
* class for the song list in main view controller for each individual song
* to do:
*   -on fav add song to favorite playlist
*   -add music select method to send music to play to playerController class*/

package ffm.freeflowmusic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SongController {
    @FXML
    private AnchorPane basePanel;
    @FXML
    private ImageView favImage;
    @FXML
    private Label numberLabel, songLabel, artistLabel, albumLabel, durLabel;
    private Parent root = null;

    public void setRoot(Parent root){
        this.root = root;
    }

    /*method to add a song to fav playlist not implemented*/
    public void onFavSelected(){
        favImage.setVisible(!favImage.isVisible());
    }

    //need to add a playerController variable so we can call a PlayerController method without making the method static.
    //Can make this song directly call media player or can call a method in SongViewController to do so.
    public void songSelected(){
        favImage.setVisible(!favImage.isVisible());
    }

    public void updateView(String[] strings) {
        songLabel.setText(strings[0]);
        artistLabel.setText(strings[1]);
        durLabel.setText(strings[2]);
        albumLabel.setText("Unknown");
        root.setUserData(strings);
    }

    public void updateVisible(boolean value) {
        basePanel.setVisible(value);
    }
}
