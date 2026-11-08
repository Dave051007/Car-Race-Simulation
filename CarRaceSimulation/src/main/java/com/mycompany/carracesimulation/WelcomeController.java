package com.mycompany.carracesimulation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

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
    private ImageView welcomeMessage;
    
    @FXML
    void initialize() {
        car1.setImage(new Image(getClass().getResource(images[0]).toExternalForm()));
        car2.setImage(new Image(getClass().getResource(images[1]).toExternalForm()));
        car3.setImage(new Image(getClass().getResource(images[2]).toExternalForm()));
        car4.setImage(new Image(getClass().getResource(images[3]).toExternalForm()));
        
        welcomeMessage.setImage(new Image(getClass().getResource(images[4]).toExternalForm()));
        
        car1.setVisible(false);
        car2.setVisible(false);
        car3.setVisible(false);
        car4.setVisible(false);
    }

    @FXML
    void startSlideShow(MouseEvent event) { 
        welcomeMessage.setVisible(false);
        
        
    }

}
