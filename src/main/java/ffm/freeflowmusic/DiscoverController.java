/*Discover Class
* This class represents the discover tab in the application window. It will display all the songs available online to listen too housed in
* a database that is represented below as a string array of arrays songDB.
* allows playing music found online.
*/

package ffm.freeflowmusic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DiscoverController implements Initializable {
    @FXML
    private VBox pageViewBox;
    @FXML
    private HBox pageNumberBox;

    private PlayerController mediaPlayer;
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

    //Class constructor will initialize the media player for class to access and call methods.
    public DiscoverController(PlayerController mediaPlayer){
        this.mediaPlayer = mediaPlayer;
    }

    //function to test if the music player can be called to play a song.
    public void testFunc(){
        mediaPlayer.setSong(songDB[0]);
    }

    //add songs from the songDB array
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
