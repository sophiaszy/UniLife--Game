package com.company.model;


/**
 * Created by anita on 6/11/2017.
 */
public class Student {

    private int x_coord;
    private int y_coord;
    private boolean isDrunk;
    private int speed;
    //TODO
    private final int NORMAL_SPEED = 25;
    private final int FAST_SPEED = 50;
    private final int STUDENT_Y = UniLifeGame.HEIGHT - 100;

    public Student() {
        y_coord = STUDENT_Y;
        x_coord = UniLifeGame.CTR_X;
        speed = NORMAL_SPEED;
    }

    public void moveLeft() {
        if (isDrunk)
            return;
        if ((x_coord - speed) <= 0)
            x_coord = 0;
        else
            x_coord -= speed;
    }

    public void moveRight() {
        if (isDrunk)
            return;
        if ((x_coord + speed) >= UniLifeGame.HEIGHT)
            x_coord = UniLifeGame.HEIGHT;
        else
            x_coord += speed;
    }

    public void changeDrunkStatus(boolean status) {
        isDrunk = status;
    }

    public void drinkCoffee() {
        speed = FAST_SPEED;
    }

    public void finishCoffee() {
        speed = NORMAL_SPEED;
    }

    public int getX() {
        return x_coord;
    }

    public int getY() {
        return y_coord;
    }



}
