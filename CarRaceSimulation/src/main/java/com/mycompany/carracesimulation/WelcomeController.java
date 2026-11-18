package com.mycompany.carracesimulation;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WelcomeController {
    private String[] images = {
        "/images/image1.png",
        "/images/image2.jpg",
        "/images/image3.jpg",
        "/images/image4.jpg",
        "/images/image5.jpg"
    };

    @FXML
    private ImageView car1;

    @FXML
    private ImageView car2;

    @FXML
    private ImageView car3;

    @FXML
    private ImageView car4;

    @FXML
    private Button exitButton;

    @FXML
    private Button startButton;
    
    @FXML
    void initialize() {
        car1.setImage(new Image(getClass().getResource(images[0]).toExternalForm()));
        car2.setImage(new Image(getClass().getResource(images[1]).toExternalForm()));
        car3.setImage(new Image(getClass().getResource(images[2]).toExternalForm()));
        car4.setImage(new Image(getClass().getResource(images[3]).toExternalForm()));
        
        car1.setVisible(false);
        car2.setVisible(false);
        car3.setVisible(false);
        car4.setVisible(false);
        
        
        SequentialTransition slideShow = new SequentialTransition();
        
        ImageView[] cars = {car1, car2, car3,car4};
        
        for (ImageView car : cars) { 
            car.setOpacity(0);
            car.setVisible(true);
            
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), car);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), car);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            
            SequentialTransition slideTransition = new SequentialTransition(
                    fadeIn, fadeOut
            );
            
            slideShow.getChildren().add(slideTransition);
        }
        
        slideShow.setCycleCount(SequentialTransition.INDEFINITE);
        slideShow.play();
    }

    @FXML
    void exitRace(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void startRace(ActionEvent event) throws IOException {
        App.setRoot("carRace");
    }

}
