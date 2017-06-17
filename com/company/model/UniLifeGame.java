package com.company.model;

/**
 * Created by SophiaShen on 2017-06-11.
 */

import java.util.*;

public class UniLifeGame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int CTR_X = WIDTH / 2;
    public static final int CTR_Y = HEIGHT / 2;

    private Random rand = new Random();
    private List<FallingObject> fallingObjects;
    private Student student;
    private boolean gameOver = false;
    private Map<FallingObjectType, LifeBar> barMap;

    public UniLifeGame(){
        student = new Student();
        fallingObjects = new ArrayList<FallingObject>();
        barMap = new HashMap<FallingObjectType, LifeBar>();
        // initializes bars
        barMap.put(FallingObjectType.Food, new LifeBar());
        barMap.put(FallingObjectType.Book, new LifeBar());
        barMap.put(FallingObjectType.Sleep, new LifeBar());
    }


    public boolean isGameOver() {
        return gameOver;
    }

    public void update() {
        //TODO
    }

    public void addFallingObjects() {
        int rand_xpos = rand.nextInt(WIDTH);
        fallingObjects.add(new FallingObject( FallingObjectType.values()[rand.nextInt(3)], rand_xpos));
        int random2 = rand.nextInt(10);
        switch (random2) {
           case FallingObjectType.Coffee.getType():


        }
        if (random2 == 0) {
            int rand_xpos2 = rand.nextInt(WIDTH);
            fallingObjects.add(new FallingObject(FallingObjectType.Coffee))
 
        }

    }







}
