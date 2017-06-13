package com.company.model;

/**
 * Created by anita on 6/12/2017.
 */
public enum NecessityType {
    Food(1), Sleep(2), Book(3), Coffee(4), Vodka(5);

    private int type;

    NecessityType(int i){ type = i;}
    public int getType(){ return type;}
}
