/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.carracesimulation;

import java.util.Random;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author Admin
 */
public class Car {
    private Color color;
    private int num;
    private Pane pane;
    private double speed;
    private TranslateTransition animation;

    public Car(Color color, int num, Pane pane, double speed, TranslateTransition animation) {
        this.color = color;
        this.num = num;
        this.pane = pane;
        this.speed = speed;
        this.animation = animation;
    }
    
    /**
     * Generates a random speed for the car
     */
    public void generateSpeed() {
        Random rand = new Random();
        double randomNum = rand.nextDouble(5, 11);
        speed = randomNum;
    }

    public Color getColor() {
        return color;
    }

    public int getNum() {
        return num;
    }

    public Pane getPane() {
        return pane;
    }

    public double getSpeed() {
        return speed;
    }

    public TranslateTransition getAnimation() {
        return animation;
    }
    
    
}
