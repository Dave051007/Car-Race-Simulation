package com.mycompany.carracesimulation;

import java.util.Arrays;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CarRaceController {
    @FXML
    private ImageView carRaceFinishedSymbol;

    @FXML
    private Label congratulationLabel;

    @FXML
    private Label firstPlaceColor;

    @FXML
    private Label firstPlaceNum;

    @FXML
    private Label fourthPlaceColor;

    @FXML
    private Label fourthPlaceNum;

    @FXML
    private Label raceFinishedLabel;
    
    @FXML
    private Label raceResultsLabel;

    @FXML
    private Label secondPlaceColor;

    @FXML
    private Label secondPlaceNum;

    @FXML
    private Label thirdPlaceColor;

    @FXML
    private Label thirdPlaceNum;

    @FXML
    private ImageView trophyLogo;
    
    @FXML
    private GridPane carRaceLeaderboard;
    
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

        carRaceAnimation.getChildren().clear();

        for (Car car : cars) {
            car.getPane().setTranslateX(0);
        }

        raceFinishedLabel.setText("...");
        congratulationLabel.setText("...");
        raceResultsLabel.setText("...");
        
        firstPlaceNum.setText("...");
        secondPlaceNum.setText("...");
        thirdPlaceNum.setText("...");
        fourthPlaceNum.setText("...");
        
        firstPlaceColor.setText("...");
        secondPlaceColor.setText("...");
        thirdPlaceColor.setText("...");
        fourthPlaceColor.setText("...");
    }

    @FXML
    void startAnim(ActionEvent event) {
        // stop and clear previous animation
        carRaceAnimation.stop();
        carRaceAnimation.getChildren().clear();
        
        for (Car car : cars) {
            car.getPane().setTranslateX(0);
        }
        
        int X = 630;
        
        for (int i = 0; i < cars.length; i++) {
            cars[i].generateSpeed(); 
            
            // generate unique speed for each car
            boolean duplicate;
            do {
                duplicate = false;
 
                for (int j = 0; j < i; j++) {
                    if (cars[i].getSpeed() == cars[j].getSpeed()) {
                        cars[i].setSpeed(cars[i].getSpeed() + 5);
                        duplicate = true;
                        break;
                    }
                }

            } while (duplicate);
            
            double speed = cars[i].getSpeed();
            cars[i].createAnimation(speed, X);
            carRaceAnimation.getChildren().add(cars[i].getAnimation());
        }
        
        carRaceAnimation.setRate(rate);
        carRaceAnimation.play();
        
        carRaceAnimation.setOnFinished(e -> {
            displayResults();
        });
    }
    
    private void displayResults() {
        raceFinishedLabel.setText("Race Finished!");
        
        // make copy of cars array to avoid changing original sorting
        Car[] sortedCars = Arrays.copyOf(cars, cars.length);
        
        // comparator to sort cars based on speed
        Arrays.sort(sortedCars, (car1, car2) ->
            Double.compare(car2.getSpeed(), car1.getSpeed())
        );
        
        double firstPlaceNumber = sortedCars[0].getNum();
        double secondPlaceNumber = sortedCars[1].getNum();
        double thirdPlaceNumber = sortedCars[2].getNum();
        double fourthPlaceNumber = sortedCars[3].getNum();
        
        String firstPlaceCol = getColorName(sortedCars[0].getColor());
        String secondPlaceCol = getColorName(sortedCars[1].getColor());
        String thirdPlaceCol = getColorName(sortedCars[2].getColor());
        String fourthPlaceCol = getColorName(sortedCars[3].getColor());
      
        congratulationLabel.setText("Congratulations! Car #" + firstPlaceNumber + 
                " (" + firstPlaceCol + ") " + "is the winner!");
        
        raceResultsLabel.setText("Final Results");
        
        firstPlaceNum.setText("Car #" + firstPlaceNumber);
        secondPlaceNum.setText("Car #" + secondPlaceNumber);
        thirdPlaceNum.setText("Car #" + thirdPlaceNumber);
        fourthPlaceNum.setText("Car #" + fourthPlaceNumber);
        
        firstPlaceColor.setText("(" + firstPlaceCol + ")");
        secondPlaceColor.setText("(" + secondPlaceCol + ")");
        thirdPlaceColor.setText("(" + thirdPlaceCol + ")");
        fourthPlaceColor.setText("(" + fourthPlaceCol + ")");
    }
    
    private String getColorName(Color color) {
        if (color.equals(Color.BLUE)) {
            return "BLUE";
        }
        else if (color.equals(Color.YELLOW)) {
            return "YELLOW";
        }
        else if (color.equals(Color.RED)) {
            return "RED";
        }
        else if (color.equals(Color.GREEN)) {
            return "GREEN";
        }

        return "UNKNOWN";
    }
}
