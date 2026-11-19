package com.mycompany.carracesimulation;

import java.util.Arrays;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class CarRaceController {
     @FXML
    private Label animationSpeedLabel;

    @FXML
    private Slider animationSpeedSlider;

    @FXML
    private Pane car1;

    @FXML
    private Line car1Line1;

    @FXML
    private Line car1Line2;

    @FXML
    private Polygon car1Shape;

    @FXML
    private Pane car2;

    @FXML
    private Line car2Line1;

    @FXML
    private Line car2Line2;

    @FXML
    private Polygon car2Shape;

    @FXML
    private Pane car3;

    @FXML
    private Line car3Line1;

    @FXML
    private Line car3Line2;

    @FXML
    private Polygon car3Shape;

    @FXML
    private Pane car4;

    @FXML
    private Line car4Line1;

    @FXML
    private Line car4Line2;

    @FXML
    private Polygon car4Shape;

    @FXML
    private ImageView carRaceFinishedSymbol;

    @FXML
    private GridPane carRaceLeaderboard;

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
    private Button pauseButton;

    @FXML
    private Label raceFinishedLabel;

    @FXML
    private Label raceResultsLabel;

    @FXML
    private Button resetButton;

    @FXML
    private Label secondPlaceColor;

    @FXML
    private Label secondPlaceNum;

    @FXML
    private Button startButton;

    @FXML
    private Label thirdPlaceColor;

    @FXML
    private Label thirdPlaceNum;

    @FXML
    private ImageView trophyLogo;

    private Car[] cars;
    private double rate = 1.0;
    ParallelTransition carRaceAnimation = new ParallelTransition();
    
    // car shape
    Polygon carBody = new Polygon(
            0, 25,
            5, 15,
            15, 12,
            23, 3,
            40, 3,
            50, 12,
            60, 15,
            65, 23,
            63, 28,
            5, 28,
            0, 25  
    );
    
    // rotate animation of lines
    private RotateTransition[] wheelSpins;
    
    @FXML
    void initialize() {
        cars = new Car[] {
            new Car(Color.BLUE, 62, car1),
            new Car(Color.YELLOW, 83, car2),
            new Car(Color.RED, 14, car3),
            new Car(Color.GREEN, 27, car4),
        };
        
        wheelSpins = new RotateTransition[] {
            createSpin(car1Line1), createSpin(car1Line2),
            createSpin(car2Line1), createSpin(car2Line2),
            createSpin(car3Line1), createSpin(car3Line2),
            createSpin(car4Line1), createSpin(car4Line2)
        };
        
        car1Shape.getPoints().setAll(carBody.getPoints());
        car2Shape.getPoints().setAll(carBody.getPoints());
        car3Shape.getPoints().setAll(carBody.getPoints());
        car4Shape.getPoints().setAll(carBody.getPoints());

        car1Shape.setFill(Color.BLUE);
        car2Shape.setFill(Color.YELLOW);
        car3Shape.setFill(Color.RED);
        car4Shape.setFill(Color.GREEN);

        animationSpeedLabel.textProperty().bind(
            animationSpeedSlider.valueProperty().asString("%.1fx")
        );
        
        animationSpeedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
           rate = newVal.doubleValue();
           carRaceAnimation.setRate(rate);
           for (RotateTransition spin : wheelSpins) {
               spin.setRate(rate);
           }
        });
    }

    @FXML
    void pauseAnim(ActionEvent event) {
        if (carRaceAnimation.getStatus() == Animation.Status.RUNNING) {
            carRaceAnimation.pause();
            
            for (RotateTransition spin : wheelSpins) {
                if (spin.getStatus() == Animation.Status.RUNNING) {
                    spin.pause();
                }
            }
            
            pauseButton.setText("Resume");
        } else {
            carRaceAnimation.play();

            for (RotateTransition spin : wheelSpins) {
                if (spin.getStatus() == Animation.Status.PAUSED) {
                    spin.play();
                }
            }

            pauseButton.setText("Pause");
        }
    }

    @FXML
    void resetAnim(ActionEvent event) {
        startButton.setDisable(false);
        pauseButton.setDisable(true);
        resetButton.setDisable(true);
        
        // stop and clear previous animation
        carRaceAnimation.stop();
        carRaceAnimation.getChildren().clear();
        
        carRaceAnimation.stop();

        carRaceAnimation.getChildren().clear();
        
        // stop wheel spin animation
        for (RotateTransition spin : wheelSpins) {
            spin.stop();
        }

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
        
        trophyLogo.setImage(null);
        carRaceFinishedSymbol.setImage(null);
    }

    @FXML
    void startAnim(ActionEvent event) {
        startButton.setDisable(true);
        pauseButton.setDisable(false);
        resetButton.setDisable(false);
        
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

            // stop the two wheels when this car finishes
            int wheelIndex = i * 2;

            cars[i].getAnimation().setOnFinished(e -> {
                wheelSpins[wheelIndex].stop();
                wheelSpins[wheelIndex + 1].stop();
            });

            carRaceAnimation.getChildren().add(cars[i].getAnimation());
        }
        
        // simulate acceleration and decceleration
        carRaceAnimation.setInterpolator(Interpolator.EASE_BOTH);
        
        carRaceAnimation.setRate(rate);
        carRaceAnimation.play();
        
        for (RotateTransition spin : wheelSpins) {
            spin.play();
        }
        
        carRaceAnimation.setOnFinished(e -> {
            displayResults();
        });
    }
    
    private void displayResults() {
        pauseButton.setDisable(true);
        
        raceFinishedLabel.setText("Race Finished!");
        trophyLogo.setImage(new Image(getClass().getResource(
                "/images/TrophyImage.jpg").toExternalForm()));
        carRaceFinishedSymbol.setImage(new Image(getClass().getResource(
                "/images/RaceFinishedImage.jpg").toExternalForm()));
        
        // make copy of cars array to avoid changing original sorting
        Car[] sortedCars = Arrays.copyOf(cars, cars.length);
        
        // comparator to sort cars based on speed
        Arrays.sort(sortedCars, (car1, car2) ->
            Double.compare(car1.getSpeed(), car2.getSpeed())
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
    
    /**
     * Creates spinning animation of line
     * @param line the line that rotates 360 degrees
     * @return a rotate transition animation
     */
    private RotateTransition createSpin(Line line) {
        RotateTransition spin = new RotateTransition(
            Duration.seconds(1), line);

        spin.setByAngle(360);
        spin.setCycleCount(Animation.INDEFINITE);
        spin.setInterpolator(Interpolator.LINEAR);

        return spin;
    }
}
