package com.company.ui;

import com.company.model.*;
import com.sun.deploy.panel.JSmartTextArea;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JButton restart_b = new JButton("restart");
    private JButton quit_b = new JButton("quit");
    private JTextPane intro = new JTextPane();
    private JTextPane name = new JTextPane();

    public ULGamePanel(UniLifeGame controller) {
        this.controller = controller;
        setSize(UniLifeGame.WIDTH, UniLifeGame.HEIGHT);
        setBackground(new Color(162, 210, 223));
        studentImage = convertToBufferedImg(System.getProperty("user.dir") + "\\com\\company\\resources\\student_resized.png");
        coffeeImage  = convertToBufferedImg(System.getProperty("user.dir") + "\\com\\company\\resources\\coffee_resized.png");
        sleepImage = convertToBufferedImg(System.getProperty("user.dir") + "\\com\\company\\resources\\sleep_resized.png");
        vodkaImage = convertToBufferedImg(System.getProperty("user.dir") + "\\com\\company\\resources\\beer_resized.png");
        foodImage = convertToBufferedImg(System.getProperty("user.dir") + "\\com\\company\\resources\\food_resized.png");
        bookImage = convertToBufferedImg(System.getProperty("user.dir") + "\\com\\company\\resources\\books_resized.png");
        bkg = convertToBufferedImg(System.getProperty("user.dir") + "\\com\\company\\resources\\school_resized.png");
        renderGameIntro();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!controller.isGameOver() && controller.hasStarted()) {
            this.remove(intro);
            this.remove(name);
            this.remove(restart_b);
            this.remove(quit_b);
            this.invalidate();
            g.drawImage(bkg, 0, 0, null);
            drawFallingObjects(g);
            drawStudent(g);
        }else if (controller.isGameOver()){
            renderGameOver(g);
        }
    }

    private void drawStudent(Graphics g){
        Student s = controller.getStudent();
        g.drawImage(studentImage,s.getX(),s.getY(),null);
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

    private void renderGameOver(Graphics g){
        g.setFont(new Font("Comic Sans MS", Font.BOLD,30));
        int strWidth = g.getFontMetrics().stringWidth("GAME OVER!!!");
        int strHeight = g.getFontMetrics().getHeight();
        g.drawString("GAME OVER!!!", UniLifeGame.CTR_X-strWidth/2,UniLifeGame.HEIGHT/2-strHeight/2);
        setupButton(restart_b,UniLifeGame.CTR_X-50, UniLifeGame.HEIGHT*2/3, new RestartAction());
        setupButton(quit_b, UniLifeGame.CTR_X-50, UniLifeGame.HEIGHT*2/3+80, new QuitAction());
    }

    private void renderGameIntro(){
        String str =
                "Welcome to UniLife Game!\nAs a university student, your goal is to try to balance your health\nand academics " +
                        "by catching sufficient amounts of Burgers (food),\nSleeping faces (sleep) and Books (academics). " +
                        "The game will be over\nas soon as you run out of any of these items, as indicated by the \nlife bars " +
                        "at the top right corner. Collect some coffee and you will\nmove faster! Grab some beer and " +
                        "you'll be too drunk to move for a few seconds!\n" + "Good luck! You know you will need it!\n" +
                        "You can press q to quit and r to restart the game at any point.\n" +
                        "When you are ready, press the space bar to start the game.";
        //String str = "Welcome to UniLife Game!\n";
        intro.setText(str);
        //intro.setLineWrap(false);
        setLayout(null);
        intro.setFont(new Font("Comic Sans MS", Font.BOLD,20));
        intro.setLocation(60,UniLifeGame.HEIGHT/2-25);
        intro.setBackground(new Color(255, 255, 255));
        intro.setSize(UniLifeGame.WIDTH-120,UniLifeGame.HEIGHT/2+15);
        this.add(intro);


        str = "UniLife \n      Game";
        name.setText(str);
        name.setFont(new Font("Comic Sans MS", Font.BOLD,70));
        name.setLocation(80,60);
        name.setBackground(new Color(162, 210, 223));
        name.setSize(400,300);
        this.add(name);
    }

    private void setupButton(JButton b, int x, int y, ActionListener al){
        b.setLocation(x, y);
        b.setSize(100,25);
        b.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        b.setBackground(new Color(255, 255, 255));
        b.setHorizontalTextPosition(AbstractButton.CENTER);
        b.setVerticalTextPosition(AbstractButton.CENTER);
        b.addActionListener(al);
        b.setFocusable(false);
        this.add(b);
    }

    private class RestartAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.restart();
        }
    }

    private class QuitAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }


}

