package com.mycompany.carracesimulation;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CarRaceController {
    @FXML
    private ImageView carRaceFinishedSymbol;

    @FXML
    private Label congratulationLabel;

    @FXML
    private Pane firstPLaceCar;

    @FXML
    private Label firstPlaceColor;

    @FXML
    private Label firstPlaceNum;

    @FXML
    private Pane fourthPlaceCar;

    @FXML
    private Label fourthPlaceColor;

    @FXML
    private Label fourthPlaceNum;

    @FXML
    private Button pauseButton;

    @FXML
    private Label raceFinishedLabel;
    
    @FXML
    private Pane secondPlaceCar;

    @FXML
    private Label secondPlaceColor;

    @FXML
    private Label secondPlaceNum;
    
     @FXML
    private Pane thirdPlaceCar;

    @FXML
    private Label thirdPlaceColor;

    @FXML
    private Label thirdPlaceNum;

    @FXML
    private ImageView trophyLogo;
    
    @FXML
    private Label animationSpeedLabel;

    @FXML
    private Slider animationSpeedSlider;

    @FXML
    private Pane car1;

    @FXML
    private Pane car2;

    @FXML
    private Pane car3;

    @FXML
    private Pane car4;
    
    @FXML
    private Pane raceView;

    @FXML
    private Button resetButton;

    @FXML
    private Button startButton;
    
    
    private Car[] cars;
    private double rate = 1.0;
    ParallelTransition carRaceAnimation = new ParallelTransition();
    
    @FXML
    void initialize() {
        cars = new Car[] {
            new Car(Color.BLUE, 56, car1),
            new Car(Color.YELLOW, 78, car2),
            new Car(Color.RED, 11, car3),
            new Car(Color.GREEN, 98, car4),
        };
        
        animationSpeedLabel.textProperty().bind(
            animationSpeedSlider.valueProperty().asString("%.1fx")
        );
        
        animationSpeedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
           rate = newVal.doubleValue();
           carRaceAnimation.setRate(rate);
        });
    }

    @FXML
    void pauseAnim(ActionEvent event) {
        carRaceAnimation.pause();
    }

    @FXML
    void resetAnim(ActionEvent event) {
        carRaceAnimation.stop();
        
        for (Car car : cars) {
            car.getPane().setTranslateX(0);
        }
    }

    @FXML
    void startAnim(ActionEvent event) {
        int X = 630;
        
        for (Car car : cars) {
            car.generateSpeed();
            
            double speed = car.getSpeed();
            car.createAnimation(speed, X);
            
            carRaceAnimation.getChildren().add(car.getAnimation());
        }
        
        // add 2 if speed is the same
        for (int i = 0; i < cars.length; i++) {
            for (int j = 0; j < i; j++) {
                if (cars[i].getSpeed() == cars[j].getSpeed()) {
                    cars[i].setSpeed(cars[i].getSpeed() + 2);
                }
            }
        }
        
        carRaceAnimation.setRate(rate);
        carRaceAnimation.play();
        carRaceAnimation.setOnFinished(e -> {
            displayResults();
        });
    }
    
    private void displayResults() {
    }
}
