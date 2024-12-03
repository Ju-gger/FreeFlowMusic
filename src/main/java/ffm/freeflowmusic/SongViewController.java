/*
* class is meant to show the list of songs similar to a traditional music player.
* The class will have the songs in a scrollable panel and display songs added from top to bottom
* class functions will deal with the list of songs.
* to do:
*   -add songs to view
*   -have search function to look up song
*   -have songs update when added
*   -filter songs by condition
*   -pick songs to play
*   -tag songs to a list
* */

package ffm.freeflowmusic;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SongViewController implements Initializable {
    @FXML
    private VBox songView;
    @FXML
    private TextField findTextfield;
    @FXML
    private VBox searchSongView;
    private Node selectedSongItem = null;  // Track the currently selected song

    private SongController songController;

    /* Private Variables */
    private static ArrayList<File> songs; // For mp3 files songs
    private int songNumber = 1; // index out of array of songs
    private PlayerController playerControllerInstance;

    /*initializes the scene to add all the songs found in a given directory
    * to do:
    *   -update directory to what the user gives
    *   -create better array to contain songs (hashmap maybe)
    *   -access media file data rather than a files'*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        songs = new ArrayList<File>();

        //directory = new File("src/main/resources/ffm/freeflowmusic/music");
        File directory = new File("Songs");

        // File of songs
        File[] files = directory.listFiles(); // will get all the files in our directory ("music")

        if (files != null) {
            for (File file : files) {
                songs.add(file);
                songView.getChildren().add(createSongData(file, songNumber, false));
                songNumber++;
            }
        }

    }

    //remove static key from this method can pass this class to the song view to do so
    public static File getSong(int songNum){
        return songs.get(songNum % songs.size());
    }

    //method called by songs in list need to pass this class to the created Song Controllers to remove static keyword.
    public static ArrayList<File> getSongList() {
        return songs;
    }

    /*Make it so that it creates a new Vbox and hides the original then displays new delete when text is updated
    * and make original box visible
    * */
    public void onSongLookUp(KeyEvent e){

        //when text field is empty show original song list and empty the copy list
        if(findTextfield.getText().isEmpty()){
            songView.setVisible(true);
            searchSongView.getChildren().clear();
            searchSongView.setVisible(false);
            return;
        }

        //on enter populate copy list then display it until text field is empty or updated
        if(e.getCode() == KeyCode.ENTER){
            searchSongView.getChildren().clear();
            int count = 1;
            songView.setVisible(false);
            for(File file : songs){
                if(file.getName().toUpperCase().contains(findTextfield.getText().toUpperCase())){
                    searchSongView.getChildren().add(createSongData(file, count, true));
                }
                count++;
            }

            searchSongView.setVisible(true);
        }

    }

    /*function to create a song fxml root that can then be updated to include
    * the song information and passed to the main initialize method
    * bool set visibility of the fav tag so that it is not shown when performing search functions
    * to do:
    *   -update song info to match song data: artist name, album name, song name, duration, tags*/
    private Parent createSongData(File file, int songNum, boolean visFlag){
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("song-ui.fxml"));
            root = loader.load();

            //changes song name to the file name need to update it to name of song NOT file
            Label numberLabel = (Label) root.lookup("#numberLabel");
            Label songLabel = (Label) root.lookup("#songLabel");
            numberLabel.setText(String.valueOf(songNum));
            songLabel.setText(file.getName());
            root.setUserData(songNum - 1);

            if(visFlag){
                ImageView image =  (ImageView) root.lookup("#heartImage");
                image.setVisible(false);
                //Button bttn = (Button) root.lookup("#favButton");
                //bttn.setVisible(false);
            }


/*          UNCOMMENT WHEN ABLE TO CALL SONG SELECTED PROPERLY */

            Parent finalRoot = root;
            root.setOnMouseClicked(event -> {
                // Remove highlight from the previously selected song
                if (selectedSongItem != null) {
                    selectedSongItem.getStyleClass().remove("selected-song");
                }

                // Highlight the clicked song item
                finalRoot.getStyleClass().add("selected-song");
                selectedSongItem = finalRoot;

                // Call the songSelected method manually
                int songIndex = (int) selectedSongItem.getUserData();// Integer.parseInt(numberLabel.getText()) - 1;
                System.out.println("Selected song index: " + songIndex);

                PlayerController.getInstance().selectSong(songIndex);

            });


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return root;
    }
}
