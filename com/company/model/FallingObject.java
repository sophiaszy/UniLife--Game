package com.company.model;

/**
 * Created by anita on 6/12/2017.
 */

public class FallingObject {

    private static final int D_Y = 15; //Todo
    private int x_coord;
    private int y_coord = 0;
    private FallingObjectType type;
    private boolean hasBeenCollected;

    public FallingObject(FallingObjectType type, int x_coord){
        this.type = type;
        this.x_coord = x_coord;
        hasBeenCollected = false;
    }

    public void setCollected() {
        hasBeenCollected = true;
    }

    public boolean isCollected() {
        return hasBeenCollected;
    }

    public void fall() {
        if(y_coord+D_Y>=UniLifeGame.HEIGHT)
            y_coord = UniLifeGame.HEIGHT;
        else
            y_coord += D_Y;
    }


    public int getX() {
        return x_coord;
    }


    public int getY() {
        return y_coord;
    }

    public FallingObjectType getType(){
        return type;
    }
}
