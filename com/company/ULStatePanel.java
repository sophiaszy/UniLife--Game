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

    UniLifeGame controller;

    private static final int PANEL_X_LOC = UniLifeGame.WIDTH/3*2;
    private static final int PANEL_Y_LOC = 0;
    private static final int PANEL_WIDTH = UniLifeGame.WIDTH-PANEL_X_LOC;
    private static final int PANEL_HEIGHT = PANEL_WIDTH;

    public ULStatePanel(UniLifeGame controller) {
        this.controller = controller;
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
        setBackground(new Color(116, 121, 105));
        setLocation(PANEL_X_LOC, PANEL_Y_LOC);
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
        outlineLifeBar((w-LifeBar.BAR_WIDTH)/2+w*o.getKey().getType() , PANEL_HEIGHT/2, g);

        switch(o.getKey()){
            case Book:
                g.setColor(new Color(154, 255, 143));
                break;
            case Sleep:
                g.setColor(new Color(126, 209, 255));
                break;
            case Food:
                g.setColor(new Color(255, 104, 102));
                break;
        }
        g.fillRect((w-LifeBar.BAR_WIDTH)/2+w*o.getKey().getType() , PANEL_HEIGHT/2+(LifeBar.BAR_HEIGHT-o.getValue().getLevel()), LifeBar.BAR_WIDTH, o.getValue().getLevel());
        g.setColor(savedCol);
    }

    private void outlineLifeBar(int x, int y, Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(x- LifeBar.LINE_THICKNESS,y- LifeBar.LINE_THICKNESS,LifeBar.BAR_WIDTH+2* LifeBar.LINE_THICKNESS, LifeBar.LINE_THICKNESS);
        g.fillRect(x- LifeBar.LINE_THICKNESS, y+LifeBar.BAR_HEIGHT, LifeBar.BAR_WIDTH+ LifeBar.LINE_THICKNESS *2, LifeBar.LINE_THICKNESS);
        g.fillRect(x- LifeBar.LINE_THICKNESS, y- LifeBar.LINE_THICKNESS, LifeBar.LINE_THICKNESS, LifeBar.BAR_HEIGHT+2* LifeBar.LINE_THICKNESS);
        g.fillRect(x+LifeBar.BAR_WIDTH,y- LifeBar.LINE_THICKNESS, LifeBar.LINE_THICKNESS, LifeBar.BAR_HEIGHT+ LifeBar.LINE_THICKNESS *2);
    }

    private void lifeBarCaption(Graphics g){
        g.setColor(Color.BLACK);
        int w = PANEL_WIDTH/3;
        int x = (w-LifeBar.BAR_WIDTH)/2-10;
        g.setFont(new Font(null,Font.BOLD,12));
        g.drawString("Nutrition                 Sleep               Academics",x,PANEL_HEIGHT/2+LifeBar.BAR_HEIGHT+20);
    }

    private void printText(Graphics g){
        int w = PANEL_WIDTH/3;
        g.setFont(new Font(null,Font.BOLD,20));
        g.setColor(Color.black);
        g.drawString(controller.getMessage(),5,100);
    }

}
