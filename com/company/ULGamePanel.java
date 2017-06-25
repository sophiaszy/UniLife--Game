package com.company;

import com.company.model.Student;
import com.company.model.UniLifeGame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by SophiaShen on 2017-06-17.
 */
public class ULGamePanel extends JPanel {
    //TODO

    UniLifeGame controller;

    public ULGamePanel(UniLifeGame controller) {
        this.controller = controller;
        setSize(UniLifeGame.WIDTH, UniLifeGame.HEIGHT);
        setBackground(new Color(120, 150, 255));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStudent(g);
    }

    private void drawStudent(Graphics g){
        Student s = controller.getStudent();
        g.setColor(new Color(0, 0, 0));
        g.fillRect(s.getX() - 50 , UniLifeGame.HEIGHT - 50, s.getX(), UniLifeGame.HEIGHT);
    }
}

