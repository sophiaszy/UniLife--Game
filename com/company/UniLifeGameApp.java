package com.company;

import com.company.model.FallingObjectType;
import com.company.model.UniLifeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anita on 6/12/2017.
 */

public class UniLifeGameApp extends JFrame {

    private Timer timer;
    private int SPEED = 200;
    private int PAUSE = 3000;
    private UniLifeGame controller;
    JFrame frame;
    ULGamePanel panel;

    public UniLifeGameApp(){
        setUpFrame();
        setUpPanel();
        //setUpTimer();
        controller = new UniLifeGame();

    }

    private void setUpFrame() {
        setSize(UniLifeGame.WIDTH,UniLifeGame.HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setUpPanel() {
        panel = new ULGamePanel(controller);
        add(panel);
        setVisible(true);
    }

    /*
    private void setUpTimer() {
        timer = new Timer(SPEED, this);
        timer.setInitialDelay(PAUSE);
        timer.start();

    }
    */

    public void actionPerformed( ActionEvent evt) {
        controller.update();
        if (controller.isGameOver())
            timer.stop();
        frame.repaint();
    }


    public static void main(String[] args) {
	/* write your code here */
        //new UniLifeGameApp();
        System.out.println(FallingObjectType.Coffee);
    }

}
