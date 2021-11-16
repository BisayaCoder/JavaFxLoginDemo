/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaming_hub;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class SplashController implements Initializable {

    @FXML
    private MediaView mview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Media media = new Media("file:///C:/Users/Administrator/Desktop/aw.mp4");
        MediaPlayer player = new MediaPlayer(media);
        player.setVolume(30);
        player.play();
        mview.setMediaPlayer(player);
        mview.isSmooth();
    }

}
