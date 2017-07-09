package com.company.ui;

import com.company.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.List;
import java.util.Map;

/**
 * Created by SophiaShen on 2017-06-17.
 */
public class ULGamePanel extends JPanel {


    UniLifeGame controller;
    private BufferedImage studentImage;
    private BufferedImage coffeeImage;
    private BufferedImage sleepImage;
    private BufferedImage vodkaImage;
    private BufferedImage foodImage;
    private BufferedImage bookImage;
    private BufferedImage bkg;

    public ULGamePanel(UniLifeGame controller) {
        this.controller = controller;
        setSize(UniLifeGame.WIDTH, UniLifeGame.HEIGHT);
        setBackground(new Color(255, 255, 255));
        studentImage = convertToBufferedImg("C:/Users/anita/Documents/UniLife--Game/com/company/resources/student_resized.png");
        coffeeImage  = convertToBufferedImg("C:/Users/anita/Documents/UniLife--Game/com/company/resources/coffee_resized.png");
        sleepImage = convertToBufferedImg("C:/Users/anita/Documents/UniLife--Game/com/company/resources/sleep_resized.png");
        vodkaImage = convertToBufferedImg("C:/Users/anita/Documents/UniLife--Game/com/company/resources/beer_resized.png");
        foodImage = convertToBufferedImg("C:/Users/anita/Documents/UniLife--Game/com/company/resources/food_resized.png");
        bookImage = convertToBufferedImg("C:/Users/anita/Documents/UniLife--Game/com/company/resources/books_resized.png");
        bkg = convertToBufferedImg("C:/Users/anita/Documents/UniLife--Game/com/company/resources/school_resized.png");

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bkg,0,0,null);
        drawFallingObjects(g);
        drawStudent(g);
    }

    private void drawStudent(Graphics g){
        Student s = controller.getStudent();
        g.drawImage(studentImage,s.getX(),UniLifeGame.HEIGHT-131,null);
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
                g.drawImage(coffeeImage,o.getX(),o.getY(),null);
                break;
            case Book:
                g.drawImage(bookImage,o.getX(),o.getY(),null);
                break;
            case Sleep:
                g.drawImage(sleepImage,o.getX(),o.getY(),null);
                break;
            case Vodka:
                g.drawImage(vodkaImage,o.getX(),o.getY(),null);
                break;
            case Food:
                g.drawImage(foodImage,o.getX(),o.getY(),null);
                break;
        }
    }

    private BufferedImage convertToBufferedImg(String path){
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(new File(path));
        }catch(IOException e){
            System.out.println(e);
        }
        return temp;
    }


}

