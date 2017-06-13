package com.company.model;

/**
 * Created by anita on 6/12/2017.
 */

public abstract class FallingObject {

    private static final int D_Y = 5; //Todo
    private int x_coord;
    private int y_coord = 0;
    private NecessityType type;

    public FallingObject(NecessityType type, int x_coord){
        this.type = type;
        this.x_coord = x_coord;
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

    public NecessityType getType(){
        return type;
    }
}
