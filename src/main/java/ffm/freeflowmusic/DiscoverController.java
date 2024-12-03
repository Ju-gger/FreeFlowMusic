/*Discover Class
* This class represents the discover tab in the application window. It will display all the songs available online to listen too housed in
* a database that is represented below as a string array of arrays songDB.
* allows playing music found online.
*/

package ffm.freeflowmusic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DiscoverController implements Initializable {
    @FXML
    private VBox pageViewBox;
    @FXML
    private HBox pageNumberBox;

    private Button prevButton = null;
    private int curPageNum = 0;
    private int songIndex = 0;
    private SongController[] songViewList = {null,null,null,null};
    private int maxPageCount = 0;
    private Node selectedSongItem = null;
    //represents the song database where we would access copy right free music.
    private String[][] songDB = {
            {"Deeper Meaning", "Liborio Conti", "7:10", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/DeeperMeaning.mp3"},
            {"Beach Serenity", "Liborio Conti", "17:54", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/BeachSerenity.mp3"},
            {"Cinelax", "Liborio Conti", "6:06", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Cinelax.mp3"},
            {"Serenity In The Woods", "Liborio Conti", "10:08", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/SerenityInTheWoods.mp3"},
            {"Tranquil Reflections", "Liborio Conti", "8:19", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/TranquilReflections.mp3"},
            {"Wonder", "Liborio Conti", "10:00", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Wonder.mp3"},
            {"Noisescape", "Liborio Conti", "7:01", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Noisescape.mp3"},
            {"Frozen In Time", "Liborio Conti", "9:32", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Frozen-in-Time.mp3"},
            {"Soft and Fragile", "Liborio Conti", "8:06", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/SoftAndFragile.mp3"},
            {"By The Fire", "Liborio Conti", "6:29", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/ByTheFire.mp3"},
            {"Forestal", "Liborio Conti", "7:25", "https://www.no-copyright-music.com/wp-content/uploads/2021/10/Forestal.mp3"},
            {"Forever", "Liborio Conti", "19:20", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Forever.mp3"},
            {"Calmness", "Liborio Conti", "8:24", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Calmness.mp3"},
            {"The Calling", "Liborio Conti", "8:44", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/TheCalling.mp3"},
            {"Winter", "Liborio Conti", "8:09", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Winter.mp3"},
            {"Beach Ambient", "Liborio Conti", "8:58", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/BeachAmbient.mp3"},
            {"Horizon", "Liborio Conti", "10:42", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Horizon.mp3"},
            {"Ambient Light", "Liborio Conti", "6:09", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/AmbientLight.mp3"},
            {"Relaxing Moment", "Liborio Conti", "11:08", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/RelaxingMoment.mp3"},
            {"I Believe", "Liborio Conti", "5:13", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Emotional-Piano-Free-Royalty-Free-Relaxing-Music-by-Liborio-Conti-01-I-Believe.mp3"},
            {"Tibetan Bowls", "Liborio Conti", "8:24", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/TibetanBowls.mp3"},
            {"Nature's Call", "Liborio Conti", "6:07", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Nature´s-Call.mp3"},
            {"On A Piano Cloud", "Liborio Conti", "20:21", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/OnAPianoCloud.mp3"},
            {"Forgotton Place", "Liborio Conti", "11:46", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/ForgottonPlace.mp3"},
            {"Bright and Clear", "Liborio Conti", "6:04", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Bright-And-Clear-No-Copyright-Music-01-Bright-And-Clear.mp3"},
            {"Nature Ambience", "Liborio Conti", "9:41", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/NatureAmbience.mp3"},
            {"Evolving Pads", "Liborio Conti", "7:15", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Evolving-Pads-Free-Relaxing-Royalty-Free-Music-by-Liborio-Conti-01-Evolving-Pads.mp3"},
            {"Inspiring Ambience", "Liborio Conti", "5:07", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Inspiring-Ambient-No-Copyright-Music-01-Inspiring-Ambient.mp3"},
            {"Just Relax", "Liborio Conti", "5:10", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Just-Relax-No-Copyright-Music-01-Just-Relax.mp3"},
            {"Light and Balanced", "Liborio Conti", "4:19", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Light-And-Balanced-No-Copyright-Music-01-Light-And-Balanced.mp3"},
            {"Peaceful", "Liborio Conti", "6:58", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Peaceful-Sleep-Music-Free-Royalty-Free-Music-by-Liborio-Conti-01-Peaceful-Sleep-Music.mp3"},
            {"Pure Ambient", "Liborio Conti", "4:56", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Pure-Ambient-No-Copyright-Music-01-Pure-Ambient.mp3"},
            {"Reflections", "Liborio Conti", "4:36", "https://www.no-copyright-music.com/wp-content/uploads/2021/09/Reflections-No-Copyright-Music-01-Reflections.mp3"}

    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (int i=0; i<4; i++){
            pageViewBox.getChildren().add(createSongData(songDB[i]));
        }
        Button bttn = (Button) pageNumberBox.getChildren().get(0);
        bttn.setUnderline(true);
        prevButton = bttn;

        maxPageCount = songDB.length/songViewList.length;

        if (songDB.length%songViewList.length != 0){
            maxPageCount++;
        }
    }

    //Called when button clicked to update the page buttons and displayed songs.
    public void onPageChange(ActionEvent event){
        Button bttn = null;
        int prevPage = curPageNum;
        int testIndex = 0;

        if (prevButton != null) {
            prevButton.setUnderline(false);
        }
        Button button = (Button) event.getSource();

        curPageNum = Integer.parseInt(button.getText()) - 1;

        testIndex = curPageNum;

        if (testIndex-2 <= 0) {
            testIndex = 3;
        }else if (testIndex+2 >= maxPageCount) {
            testIndex = maxPageCount - 2;
        } else{
            testIndex = curPageNum+1;
        }

        bttn = (Button) pageNumberBox.getChildren().get(0);
        bttn.setText(String.valueOf(testIndex-2));
        bttn = (Button) pageNumberBox.getChildren().get(1);
        bttn.setText(String.valueOf(testIndex-1));
        bttn = (Button) pageNumberBox.getChildren().get(2);
        bttn.setText(String.valueOf(testIndex));
        bttn = (Button) pageNumberBox.getChildren().get(3);
        bttn.setText(String.valueOf(testIndex+1));
        bttn = (Button) pageNumberBox.getChildren().get(4);
        bttn.setText(String.valueOf(testIndex+2));

        if (curPageNum+2 >= maxPageCount){
            bttn = (Button) pageNumberBox.getChildren().get(5-(maxPageCount-curPageNum));
            bttn.setUnderline(true);
            bttn.requestFocus();
            prevButton = bttn;
        }else if (curPageNum > 1){
            bttn = (Button) pageNumberBox.getChildren().get(2);
            bttn.setUnderline(true);
            bttn.requestFocus();
            prevButton = bttn;
        }
        else {
            bttn = (Button) pageNumberBox.getChildren().get(curPageNum);
            bttn.setUnderline(true);
            bttn.requestFocus();
            prevButton = bttn;
        }

        if(prevPage != curPageNum){
            updatePageView();
        }
    }

    public void changePageRight(){
        if (curPageNum >= maxPageCount-1){
            return;
        }

        if (prevButton != null) {
            prevButton.setUnderline(false);
        }

        Button bttn = null;
        int testIndex = 0;

        curPageNum++;

        testIndex = curPageNum;

        if (testIndex-2 <= 0) {
            testIndex = 3;
        }else if (testIndex+2 >= maxPageCount) {
            testIndex = maxPageCount - 2;
        } else{
            testIndex = curPageNum+1;
        }

        bttn = (Button) pageNumberBox.getChildren().get(0);
        bttn.setText(String.valueOf(testIndex-2));
        bttn = (Button) pageNumberBox.getChildren().get(1);
        bttn.setText(String.valueOf(testIndex-1));
        bttn = (Button) pageNumberBox.getChildren().get(2);
        bttn.setText(String.valueOf(testIndex));
        bttn = (Button) pageNumberBox.getChildren().get(3);
        bttn.setText(String.valueOf(testIndex+1));
        bttn = (Button) pageNumberBox.getChildren().get(4);
        bttn.setText(String.valueOf(testIndex+2));

        if (curPageNum+2 >= maxPageCount){
            bttn = (Button) pageNumberBox.getChildren().get(5-(maxPageCount-curPageNum));
            bttn.setUnderline(true);
            bttn.requestFocus();
            prevButton = bttn;
        }else if (curPageNum > 1){
            bttn = (Button) pageNumberBox.getChildren().get(2);
            bttn.setUnderline(true);
            bttn.requestFocus();
            prevButton = bttn;
        }
        else {
            bttn = (Button) pageNumberBox.getChildren().get(curPageNum);
            bttn.setUnderline(true);
            bttn.requestFocus();
            prevButton = bttn;
        }

        updatePageView();
    }

    public void changePageLeft(){
        if (curPageNum <= 0){
            return;
        }

        if (prevButton != null) {
            prevButton.setUnderline(false);
        }

        Button bttn = null;
        int testIndex = 0;

        curPageNum--;

        testIndex = curPageNum;

        if (testIndex-2 <= 0) {
            testIndex = 3;
        }else if (testIndex+2 >= maxPageCount) {
            testIndex = maxPageCount - 2;
        } else{
            testIndex = curPageNum+1;
        }

        bttn = (Button) pageNumberBox.getChildren().get(0);
        bttn.setText(String.valueOf(testIndex-2));
        bttn = (Button) pageNumberBox.getChildren().get(1);
        bttn.setText(String.valueOf(testIndex-1));
        bttn = (Button) pageNumberBox.getChildren().get(2);
        bttn.setText(String.valueOf(testIndex));
        bttn = (Button) pageNumberBox.getChildren().get(3);
        bttn.setText(String.valueOf(testIndex+1));
        bttn = (Button) pageNumberBox.getChildren().get(4);
        bttn.setText(String.valueOf(testIndex+2));

        if (curPageNum+2 >= maxPageCount){
            bttn = (Button) pageNumberBox.getChildren().get(5-(maxPageCount-curPageNum));
            bttn.setUnderline(true);
            bttn.requestFocus();
            prevButton = bttn;
        }else if (curPageNum > 1){
            bttn = (Button) pageNumberBox.getChildren().get(2);
            bttn.setUnderline(true);
            bttn.requestFocus();
            prevButton = bttn;
        }
        else {
            bttn = (Button) pageNumberBox.getChildren().get(curPageNum);
            bttn.setUnderline(true);
            bttn.requestFocus();
            prevButton = bttn;
        }

        updatePageView();
    }

    private void updatePageView(){
        int curIndexSet = curPageNum*songViewList.length;

        for (int i=0; i<songViewList.length; i++){
            if(curIndexSet+i >= songDB.length){
                songViewList[i].updateVisible(false);
            }else {
                songViewList[i].updateVisible(true);
                songViewList[i].updateView(songDB[curIndexSet+i]);
            }
        }
    }

    private Parent createSongData(String[] songData){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("song-ui.fxml"));
            root = loader.load();
            songViewList[songIndex] = loader.getController();

            //changes song name to the file name need to update it to name of song NOT file
            Label numberLabel = (Label) root.lookup("#numberLabel");
            Label songLabel = (Label) root.lookup("#songLabel");
            Label durLabel = (Label) root.lookup("#durLabel");
            Label albLabel = (Label) root.lookup("#albumLabel");
            Label artLabel = (Label) root.lookup("#artistLabel");
            numberLabel.setText(String.valueOf(" "));
            songLabel.setText(songData[0]);
            durLabel.setText(songData[2]);
            albLabel.setText("Unkown");
            artLabel.setText(songData[1]);

            root.setUserData(songData);

            ImageView image =  (ImageView) root.lookup("#heartImage");
            image.setVisible(false);
            //Button bttn = (Button) root.lookup("#favButton");
            //bttn.setVisible(false);

            songViewList[songIndex].setRoot(root);
            songIndex++;

            Parent finalRoot = root;
            root.setOnMouseClicked(event -> {
                // Remove highlight from the previously selected song
                if (selectedSongItem != null) {
                    selectedSongItem.getStyleClass().remove("selected-song");
                }

                // Highlight the clicked song item
                finalRoot.getStyleClass().add("selected-song");
                selectedSongItem = finalRoot;

                String[] songInfo = (String[]) selectedSongItem.getUserData();
                PlayerController.getInstance().playDiscoverSong(songInfo);//selectSong(songIndex);

            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return root;
    }
}
