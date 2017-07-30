package com.company.ui;

import com.company.model.FallingObjectType;
import com.company.model.LifeBar;
import com.company.model.UniLifeGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    private BufferedImage foodImage;
    private BufferedImage bookImage;
    private BufferedImage sleepImage;
    private BufferedImage logoImage;


    public ULStatePanel(UniLifeGame controller) {
        this.controller = controller;
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
        setBackground(new Color(162, 210, 223));
        setLocation(PANEL_X_LOC, PANEL_Y_LOC);

        foodImage = convertToBufferedImg(this.getClass().getClassLoader().getResourceAsStream("food_resized.png"));
        bookImage = convertToBufferedImg(this.getClass().getClassLoader().getResourceAsStream("books_resized.png"));
        sleepImage = convertToBufferedImg(this.getClass().getClassLoader().getResourceAsStream("sleep_resized.png"));
        logoImage = convertToBufferedImg(this.getClass().getClassLoader().getResourceAsStream("logo_resized.png"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(controller.hasStarted()) {
            drawBorder(g);
            drawLifeBars(g);
            printText(g);
            lifeBarCaption(g);
        }else{
            g.drawImage(logoImage,0,0,null);
        }
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
                g.drawImage(bookImage,(w-LifeBar.BAR_WIDTH)/2+w*o.getKey().getType(),PANEL_HEIGHT/2 - 50,null);
                g.setColor(new Color(154, 255, 143));
                break;
            case Sleep:
                g.setColor(new Color(85, 178, 255));
                g.drawImage(sleepImage,(w-LifeBar.BAR_WIDTH)/2+w*o.getKey().getType(),PANEL_HEIGHT/2 - 50,null);
                break;
            case Food:
                g.setColor(new Color(255, 104, 102));
                g.drawImage(foodImage,(w-LifeBar.BAR_WIDTH)/2+w*o.getKey().getType(),PANEL_HEIGHT/2 - 50,null);
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

    private void drawBorder(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,5,PANEL_HEIGHT);
        g.fillRect(0,0,PANEL_WIDTH,5);
        g.fillRect(0,PANEL_HEIGHT-5,PANEL_WIDTH,5);
        g.fillRect(PANEL_WIDTH-5,0,5,PANEL_HEIGHT);
    }

    private void lifeBarCaption(Graphics g){
        g.setColor(Color.black);
        int w = PANEL_WIDTH/3;
        int x = (w-LifeBar.BAR_WIDTH)/2-10;
        g.setFont(new Font("Comic Sans MS",Font.BOLD,14));
        g.drawString("Nutrition      Sleep      Academics",x,PANEL_HEIGHT/2+LifeBar.BAR_HEIGHT+20);
    }

    private void printText(Graphics g){
        g.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        g.setColor(Color.black);
        int w = g.getFontMetrics().stringWidth(controller.getMessage())/2;
        g.drawString(controller.getMessage(),PANEL_WIDTH/2-w ,50);
    }

    private BufferedImage convertToBufferedImg(InputStream path){
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(path);
        }catch(IOException e){
            System.out.println(e);
        }
        return temp;
    }
}
