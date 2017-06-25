package com.company.model;

/**
 * Created by SophiaShen on 2017-06-11.
 */
public class LifeBar {
    private static final int DROP_SPEED = 1; //Todo
    private static final int INC_SPEED = 10; //Todo
    private static final int MAX_LEVEL = 500; //Todo
    private int level= MAX_LEVEL / 2; //Todo


    public LifeBar(){}

    public void dropLevel(){
        level -= DROP_SPEED;
    }

    public void increaseLevel(){
        if(level+INC_SPEED>=MAX_LEVEL)
            level = MAX_LEVEL;
        else
            level += INC_SPEED;
    }

    public int getLevel(){
        return level;
    }
}
