package com.company;

import com.company.model.FallingObjectType;
import com.company.model.LifeBar;
import com.company.model.UniLifeGame;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by SophiaShen on 2017-06-25.
 */
public class ULStatePanel extends JPanel {
    //TOP RIGHT CORNER

    UniLifeGame controller;
    private static final int PANEL_LOC = UniLifeGame.WIDTH/3*2;
    private static final int PANEL_WIDTH = UniLifeGame.WIDTH-PANEL_LOC;
    private static final int PANEL_HEIGHT = PANEL_WIDTH;
    private static final int BAR_WIDTH = 30;
    private static final int BAR_HEIGHT = LifeBar.MAX_LEVEL;
    private static final int LINE_THICKNESS = 3;

    public ULStatePanel(UniLifeGame controller) {
        this.controller = controller;
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
        setBackground(new Color(116, 121, 105));
        setLocation(PANEL_LOC, 0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLifeBars(g);
        printText(g);
        lifeBarCaption(g);
    }

    private void drawLifeBars(Graphics g){
        Map<FallingObjectType, LifeBar> objs = controller.getBars();
        for(Map.Entry<FallingObjectType, LifeBar> o : objs.entrySet()){
            drawLifeBar(o, g);
        }
    }

    private void drawLifeBar(Map.Entry<FallingObjectType, LifeBar> o, Graphics g){
        Color savedCol = g.getColor();
        g.setColor(Color.BLACK);
        int w = PANEL_WIDTH/3;
        outlineLifeBar((w-BAR_WIDTH)/2+w*o.getKey().getType() , PANEL_HEIGHT/2, g);

        switch(o.getKey()){
            case Book:
                g.setColor(Color.blue);
                break;
            case Sleep:
                g.setColor(Color.magenta);
                break;
            case Food:
                g.setColor(Color.GREEN);
                break;
        }
        g.fillRect((w-BAR_WIDTH)/2+w*o.getKey().getType() , PANEL_HEIGHT/2+(BAR_HEIGHT-o.getValue().getLevel()), BAR_WIDTH, o.getValue().getLevel());
        g.setColor(savedCol);
    }

    private void outlineLifeBar(int x, int y, Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(x- LINE_THICKNESS,y- LINE_THICKNESS,BAR_WIDTH+2* LINE_THICKNESS, LINE_THICKNESS);
        g.fillRect(x- LINE_THICKNESS, y+BAR_HEIGHT, BAR_WIDTH+ LINE_THICKNESS *2, LINE_THICKNESS);
        g.fillRect(x- LINE_THICKNESS, y- LINE_THICKNESS, LINE_THICKNESS, BAR_HEIGHT+2* LINE_THICKNESS);
        g.fillRect(x+BAR_WIDTH,y- LINE_THICKNESS, LINE_THICKNESS, BAR_HEIGHT+ LINE_THICKNESS *2);
    }

    private void lifeBarCaption(Graphics g){
        g.setColor(Color.BLACK);
        int w = PANEL_WIDTH/3;
        int x = (w-BAR_WIDTH)/2-10;
        g.setFont(new Font(null,Font.BOLD,12));
        g.drawString("Nutrition                 Sleep               Academics",x,PANEL_HEIGHT/2+BAR_HEIGHT+20);
    }

    private void printText(Graphics g){
        int w = PANEL_WIDTH/3;
        g.setFont(new Font(null,Font.BOLD,20));
        g.setColor(Color.black);
        g.drawString(controller.getMessage(),5,100);
    }

}
