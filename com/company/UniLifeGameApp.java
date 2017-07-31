package com.company;

import com.company.model.UniLifeGame;
import com.company.ui.ULGamePanel;
import com.company.ui.ULStatePanel;

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
    private int PAUSE = 300;
    private UniLifeGame controller;
    private JLayeredPane lpane;
    ULGamePanel gPanel;
    ULStatePanel sPanel;

    public UniLifeGameApp(){
        super("UniLife Game");
        controller = new UniLifeGame();
        setUpFrame();
        lpane = new JLayeredPane();
        add(lpane);
        setUpGPanel();
        setUpSPanel();
        setUpTimer();
        addKeyListener(new HandleKeys());

    }

    private void setUpFrame() {
        setSize(UniLifeGame.WIDTH,UniLifeGame.HEIGHT);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void setUpGPanel() {
        gPanel = new ULGamePanel(controller);
        lpane.add(gPanel, 1); //priority
        setVisible(true);
    }

    private void setUpSPanel() {
        sPanel = new ULStatePanel(controller);
        lpane.add(sPanel, 0);
        setVisible(true);
    }


    private void setUpTimer() {
        timer = new Timer(SPEED, this);
        timer.setInitialDelay(PAUSE);
        timer.start();

    }


    public void actionPerformed( ActionEvent evt) {
        controller.update();
        //if (controller.isGameOver())
            //timer.stop();
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
