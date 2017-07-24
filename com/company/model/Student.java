package com.company.model;


/**
 * Created by anita on 6/11/2017.
 */
public class Student {

    private final int NORMAL_SPEED = 10; //TODO
    private final int FAST_SPEED = 20; //TODO
    private int x_coord = UniLifeGame.CTR_X;
    private int y_coord = UniLifeGame.HEIGHT - STUDENT_HEIGHT; //TODO
    private boolean isDrunk = false;
    private int speed = NORMAL_SPEED;
    public static final int STUDENT_WIDTH = 86;
    public static final int STUDENT_HEIGHT = 131;


    public Student() {}

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
        if ((x_coord + speed) >= UniLifeGame.WIDTH - STUDENT_WIDTH)
            x_coord = UniLifeGame.WIDTH- STUDENT_WIDTH;
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

    public void center(){
        x_coord = UniLifeGame.CTR_X;
    }

}
