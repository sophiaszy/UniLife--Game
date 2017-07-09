package com.company;

import com.company.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Created by SophiaShen on 2017-06-17.
 */
public class ULGamePanel extends JPanel {


    UniLifeGame controller;

    public ULGamePanel(UniLifeGame controller) {
        this.controller = controller;
        setSize(UniLifeGame.WIDTH, UniLifeGame.HEIGHT);
        setBackground(new Color(120, 150, 255));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFallingObjects(g);
        drawStudent(g);
    }

    private void drawStudent(Graphics g){
        Student s = controller.getStudent();
        Color savedCol = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.fillRect(s.getX(), UniLifeGame.HEIGHT - 100, 50, 100);
        g.setColor(savedCol);
    }

    private void drawFallingObjects(Graphics g){
        List<FallingObject> objs = controller.getFallingObjects();
        for(FallingObject o : objs){
            drawFallingObject(o, g);
        }
    }

    private void drawFallingObject(FallingObject o, Graphics g){
        Color savedCol = g.getColor();
        switch(o.getType()){
            case Coffee:
                g.setColor(new Color(195, 98, 33));
                break;
            case Book:
                g.setColor(new Color(154, 255, 143));
                break;
            case Sleep:
                g.setColor(new Color(126, 209, 255));
                break;
            case Vodka:
                g.setColor(new Color(250, 221, 255));
                break;
            case Food:
                g.setColor(new Color(255, 104, 102));
                break;
        }
        g.fillRect(o.getX(), o.getY(), 20, 20);
        g.setColor(savedCol);
    }


}

