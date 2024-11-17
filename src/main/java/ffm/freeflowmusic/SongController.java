/*
* class for the song list in main view controller for each individual song
* to do:
*   -on fav add song to favorite playlist
*   -add music select method to send music to play to playerController class*/

package ffm.freeflowmusic;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SongController {
    @FXML
    private ImageView favImage;
    @FXML
    private Label numberLabel;

    /*method to add a song to fav playlist not implemented*/
    public void onFavSelected(){
        favImage.setVisible(!favImage.isVisible());
    }


    //need to add a playerController variable so we can call a PlayerController method without making the method static.
    //Can make this song directly call media player or can call a method in SongViewController to do so.
    public void songSelected(){
        PlayerController.selectSong(Integer.parseInt(numberLabel.getText()) - 1);
    }
}
