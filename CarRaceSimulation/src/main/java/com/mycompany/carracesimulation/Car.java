/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.carracesimulation;

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
    
    
}
