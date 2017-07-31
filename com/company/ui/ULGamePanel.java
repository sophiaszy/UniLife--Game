package com.company.ui;

import com.company.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by SophiaShen on 2017-06-17.
 */
public class ULGamePanel extends JPanel {


    UniLifeGame controller;
    private BufferedImage studentImage;
    private BufferedImage coffeeImage;
    private BufferedImage sleepImage;
    private BufferedImage beerImage;
    private BufferedImage foodImage;
    private BufferedImage bookImage;
    private BufferedImage bkg;
    private BufferedImage end_bkg;
 
    private JButton restart_b = new JButton("restart");
    private JButton quit_b = new JButton("quit");
    private JTextPane intro = new JTextPane();
    private JTextPane name = new JTextPane();

    public ULGamePanel(UniLifeGame controller) {
        this.controller = controller;
        setSize(UniLifeGame.WIDTH, UniLifeGame.HEIGHT);
        setBackground(new Color(162, 210, 223));
        studentImage = convertToBufferedImg(this.getClass().getClassLoader().getResourceAsStream("student_resized.png"));
        coffeeImage  = convertToBufferedImg(this.getClass().getClassLoader().getResourceAsStream("coffee_resized.png"));
        beerImage = convertToBufferedImg(this.getClass().getClassLoader().getResourceAsStream("beer_resized.png"));
        bkg = convertToBufferedImg(this.getClass().getClassLoader().getResourceAsStream("school_resized.png"));
        end_bkg = convertToBufferedImg(this.getClass().getClassLoader().getResourceAsStream("gameover_resized.png"));
        foodImage = convertToBufferedImg(this.getClass().getClassLoader().getResourceAsStream("food_resized.png"));
        bookImage = convertToBufferedImg(this.getClass().getClassLoader().getResourceAsStream("books_resized.png"));
        sleepImage = convertToBufferedImg(this.getClass().getClassLoader().getResourceAsStream("sleep_resized.png"));
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
            case Beer:
                g.drawImage(beerImage,o.getX(),o.getY(),null);
                break;
            case Food:
                g.drawImage(foodImage,o.getX(),o.getY(),null);
                break;
        }
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

    private void renderGameOver(Graphics g){
        g.setFont(new Font("Comic Sans MS", Font.BOLD,30));
        g.drawImage(end_bkg, 0, 0, null);
        setupButton(restart_b,UniLifeGame.CTR_X-70, UniLifeGame.HEIGHT*2/3+30, new RestartAction());
        setupButton(quit_b, UniLifeGame.CTR_X-70, UniLifeGame.HEIGHT*2/3+80, new QuitAction());
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
        intro.setEditable(false);
        intro.setFocusable(false);
        this.add(intro);


        str = "UniLife \n      Game";
        name.setText(str);
        name.setFont(new Font("Comic Sans MS", Font.BOLD,70));
        name.setLocation(80,60);
        name.setBackground(new Color(162, 210, 223));
        name.setSize(400,300);
        name.setEditable(false);
        name.setFocusable(false);
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

