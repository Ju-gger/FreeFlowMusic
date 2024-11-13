/*
* Class is controller for the music player that shows UI to user to control aspects of a selected MP3 file.
* Will play a song when needed/selected by user or a full playlist/album when selected.
* Class changes
*   -added a play selected song that grabs song from song list in SongViewController
*   -temp removed initialize method until better use is found for it
*   -changed songs to songQueue that when full the music player can cycle through
* To Do:
*   -check for a queue to cycle through
*   -make another array to allow for queue repeat
*   -update all functions to use the songQueue more effectively
*   -change play/shuffle to remove selected song so it will not be selected again (low priority)
*   -update array to be a stack instead java.util.Stack Stack<File> songQueue*/

package ffm.freeflowmusic;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.media.*;
import javafx.scene.control.*;

import static javafx.util.Duration.seconds;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@SuppressWarnings("FieldCanBeLocal")
public class PlayerController implements Initializable { //removed for now to test code

    /* XML References */
    @FXML
    private Button playButton, rewindButton, forwardButton, prevButton, nextButton;
    @FXML
    private CheckBox loopCheckbox;
    @FXML
    private CheckBox shuffleCheckbox;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ProgressBar songDurationBar;
    @FXML
    private Label songLabel;
    @FXML
    private Label artistLabel;

    /* Global Variables */
    private Media media;
    private MediaPlayer mediaPlayer;
    private File directory;
    private File[] files;   // File of songs
    private static ArrayList<File> songQueue = new ArrayList<File>(); // For mp3 files songs
    private int songNumber = 0; // index out of array of songs
    private Timer timer; // track our progressBar
    private TimerTask task;
    private boolean isRunning;
    private boolean isPaused = true;

    /* removed to test code
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        songs = new ArrayList<File>();

        directory = new File("src/main/resources/ffm/freeflowmusic/music");

        files = directory.listFiles(); // will get all the files in our directory ("music")

        if (files != null) {
            for (File file : files) {
                songs.add(file);
                System.out.println(file);
            }
        }

        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        songLabel.setText(songs.get(songNumber).getName());

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });

        songDurationBar.setStyle("-fx-accent: #000000;"); // Change color of progress bar
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });
    }

    /*plays a singular song selected from music list*/
    public static void selectSong(int songNum){
        File song = SongViewController.getSong(songNum);
        songQueue = new ArrayList<File>();
        songQueue.add(song);
    }

    private void getFullSongList(){
        songQueue = SongViewController.getSongList();
    }

    /*updates queue to a selected playlist*/
    public void setQueue(ArrayList<File> userList){
        songQueue = userList;
    }

    public void PlayPause(){
        if(songQueue.isEmpty()){
            getFullSongList();
        }

        if(mediaPlayer == null){
            media = new Media(songQueue.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songQueue.get(songNumber).getName());
        }

        if(isPaused){ //  If currently paused
            isPaused = false;
            beginTimer();
            mediaPlayer.setVolume(volumeSlider.getValue() * 0.01); // reset volume slider
            mediaPlayer.play();
        }
        else{ // If currently playing
            mediaPlayer.pause();
            cancelTimer();
            isPaused = true;
        }
    }

    // Rewinds the song by 10 seconds
    public void Rewind() {
        if (mediaPlayer != null) {
            double currentTime = mediaPlayer.getCurrentTime().toSeconds();
            double newTime = Math.max(currentTime - 10, 0); // Ensures it doesn't go below 0
            mediaPlayer.seek(seconds(newTime));
        }
    }

    // Forwards the song by 10 seconds
    public void Forward() {
        if (mediaPlayer != null) {
            double currentTime = mediaPlayer.getCurrentTime().toSeconds();
            double endTime = mediaPlayer.getTotalDuration().toSeconds();
            double newTime = Math.min(currentTime + 10, endTime); // Ensures it doesn't exceed the song's length
            mediaPlayer.seek(seconds(newTime));
        }
    }

    // replays the same song when finished
    public void Loop() {
        if (loopCheckbox.isSelected()) {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop indefinitely
        } else {
            mediaPlayer.setCycleCount(1); // Play only once
        }
    }


    // The next song could be any random song in the array except for the current one
    //update to use a queue structure that pops index and moves it to spare array
    public void Shuffle() {
        if (songQueue.size() > 1) { // Only shuffle if there is more than one song
            int randomIndex;
            do {
                randomIndex = new Random().nextInt(songQueue.size());
            } while (randomIndex == songNumber); // Ensure it’s not the current song

            songNumber = randomIndex;
        }
    }

    // Previous and Next Song Helper
    private void playSelectedSong() {
        mediaPlayer.stop();

        if (isRunning) {
            cancelTimer();
        }

        media = new Media(songQueue.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        Platform.runLater(()->songLabel.setText(songQueue.get(songNumber).getName()));
        //songLabel.setText(songQueue.get(songNumber).getName());

        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01); // Set initial volume
        beginTimer();
        mediaPlayer.play();

        //songQueue.remove(songNumber);
    }


    public void PreviousSong() {
        if (mediaPlayer.getCurrentTime().toSeconds() >= 5) {
            songDurationBar.setProgress(0);
            mediaPlayer.seek(seconds(0));
        } else { // go to the previous song
            songNumber = (songNumber > 0) ? songNumber - 1 : songQueue.size() - 1; // if the songNumber is greater than 0 SongNumber-= 1 else go to last song
            playSelectedSong();
        }
    }

    public void NextSong() {
        songNumber = (songNumber < songQueue.size() - 1) ? songNumber + 1 : 0; // if the songNumber is less than 0 SongNumber += 1 else go to first song
        playSelectedSong();
    }


    //updated if condition to play next song in queue when currSong is finished
    public void beginTimer(){
        timer = new Timer();

        task = new TimerTask() {
            @Override
            public void run() {
                isRunning = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = mediaPlayer.getTotalDuration().toSeconds();

                songDurationBar.setProgress(current/end);

                if ( current/end  == 1){
                    if ((songQueue.size() == 1)) {
                        cancelTimer();
                        return;
                    }
                    NextSong();
                }
            };
        };

        timer.scheduleAtFixedRate(task,0,1000);

    }


    public void cancelTimer(){
        isRunning = false;
        timer.cancel();
    }


}