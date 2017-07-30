package com.company.model;

/**
 * Created by anita on 6/12/2017.
 */
public enum FallingObjectType {
    Food(0), Sleep(1), Book(2), Coffee(3), Beer(4);

    private int type;

    FallingObjectType(int i){ type = i;}
    public int getType(){ return type;}

}
