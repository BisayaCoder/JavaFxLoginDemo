/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaming_hub;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.util.*;
import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Administrator
 */
public class Gaming_Hub extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login_Panel.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        scene.setFill(Color.FIREBRICK);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Icons/frame_title_logo.png")));
        stage.show();
        //System.exit(0);
        //voice();
        /*javafx.geometry.Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 1.4);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 1.5);
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(javafx.util.Duration.seconds(9), (ActionEvent event) -> {
            //System.out.println("this is called every 5 seconds on UI thread");
            stage.close();
            LaunchReg();
        }));
        fiveSecondsWonder.setDelay(javafx.util.Duration.ZERO);
        fiveSecondsWonder.play();*/

 /*PauseTransition delay = new PauseTransition(javafx.util.Duration.seconds(9));
        delay.setOnFinished(event -> stage.close());
        delay.play();
        LaunchReg();*/
 /*new Timer().schedule(
                new TimerTask() {

            @Override
            public void run() {
                 //stage.close();
                System.out.println("otnot");
            }
        }, 0, 3000);
           
            //System.exit(0);
        
    }
        
    /**
     * @param args the command line arguments
         */
    }

    public static void main(String[] args) {
        launch(args);
    }

    void LaunchReg() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gaming_hub/Login_Panel.fxml"));
            Parent r1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Registration");
            stage.setScene(new Scene(r1));
            stage.initStyle(StageStyle.TRANSPARENT);
            //scene.setFill(Color.TRANSPARENT);
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Icons/frame_title_logo.png")));
            //stage.setTitle("Gaming HUB - Registration");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*void voice() {
        try {
            URL url;
            url = Gaming_Hub.class.getResource("stt-config.xml");
            System.out.println("Loading...");
            ConfigurationManager cm = new ConfigurationManager(url);
            Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
            Microphone microphone = (Microphone) cm.lookup("microphone");
            recognizer.allocate();
            if (microphone.startRecording()) {
                System.out.println("Say Anything");
                System.out.println("Start speaking. Press Ctrl-C to quit.\n");
                Result result = recognizer.recognize();
                if (result != null) {
                    String resultText = result.getBestFinalResultNoFiller();
                    System.out.println("You said: " + resultText + "\n");
                    if (resultText.equals("bye")) {
                        System.exit(1);
                    }
                } else {
                    System.out.println("I can't hear what you said.\n");
                }

            } else {
                System.out.println("Cannot start microphone.");
                recognizer.deallocate();
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Problem when loading HelloWorld: " + e);
            e.printStackTrace();
        } catch (PropertyException e) {
            System.err.println("Problem configuring HelloWorld: " + e);
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.err.println("Problem creating HelloWorld: " + e);
            e.printStackTrace();
        }
    }*/
}
