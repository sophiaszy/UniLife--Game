package com.company;

import com.company.model.FallingObjectType;
import com.company.model.UniLifeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by anita on 6/12/2017.
 */

public class UniLifeGameApp extends JFrame implements ActionListener {

    private Timer timer;
    private int SPEED = 200;
    private int PAUSE = 3000;
    private UniLifeGame controller;
    ULGamePanel panel;

    public UniLifeGameApp(){
        super("UniLife Game");
        controller = new UniLifeGame();
        setUpFrame();
        setUpPanel();
        setUpTimer();


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


    private void setUpTimer() {
        timer = new Timer(SPEED, this);
        timer.setInitialDelay(PAUSE);
        timer.start();

    }


    public void actionPerformed( ActionEvent evt) {
        controller.update();
        if (controller.isGameOver())
            timer.stop();
        repaint();
    }

    private class HandleKeys extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            controller.keyPressed(e.getKeyCode());
        }
    }


    public static void main(String[] args) {
	/* write your code here */
        new UniLifeGameApp();
    }

}
