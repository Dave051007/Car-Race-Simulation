package com.mycompany.carracesimulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CarRaceController {

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
    private Button pauseButton;

    @FXML
    private Pane raceView;

    @FXML
    private Button resetButton;

    @FXML
    private Button startButton;
    
    Car[] cars = {
        new Car(Color.BLUE, 56, car1),
        new Car(Color.YELLOW, 78, car2),
        new Car(Color.RED, 11, car3),
        new Car(Color.GREEN, 98, car4),
    };
    
    @FXML
    void initialize() {
        animationSpeedLabel.textProperty().bind(
            animationSpeedSlider.valueProperty().asString("%.1fx")
        );
    }

    @FXML
    void pauseAnim(ActionEvent event) {

    }

    @FXML
    void resetAnim(ActionEvent event) {

    }

    @FXML
    void startAnim(ActionEvent event) {

    }
}
