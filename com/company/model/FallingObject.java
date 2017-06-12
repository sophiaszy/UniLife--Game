package com.company.model;

/**
 * Created by anita on 6/12/2017.
 */

public abstract class FallingObject {

    protected static final int D_Y = 5; //Todo
    private int x_coord;
    private int y_coord = 0;
    private FallingObjectType type;

    public FallingObject(FallingObjectType type, int x_coord){
        this.type = type;
        this.x_coord = x_coord;
    }

    public void fall() {
        y_coord += D_Y;
    }


    public int getX() {
        return x_coord;
    }


    public int getY() {
        return y_coord;
    }

    public FallingObjectType get_type(){
        return type;
    }
}
