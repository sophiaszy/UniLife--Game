package com.company.model;

/**
 * Created by SophiaShen on 2017-06-11.
 */

import com.sun.javafx.UnmodifiableArrayList;

import java.awt.event.KeyEvent;
import java.util.*;

public class UniLifeGame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int CTR_X = WIDTH / 2;

    private static final int BORDER = 25; //TODO
    private final int VICINITY = 10; //TODO

    private int e;
    private int coffeeRushCount;
    private static final int MAX_COUNT = 25;

    private Random rand = new Random();
    private List<FallingObject> fallingObjects;
    private Student student;
    private boolean gameOver = false;
    private Map<FallingObjectType, LifeBar> barMap;

    public UniLifeGame(){
        student = new Student();
        fallingObjects = new LinkedList<FallingObject>();
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
        addFallingObjects();
        collectObjects();
        moveFallingObjects();
        dropAllBarLevels();
        updateStudentCoffeeStatus();
        updateStudentSoberness();
        checkIsGameOver();
        //student.moveLeft();
    }


    private void addFallingObjects() {
        int rand_xpos = rand.nextInt(WIDTH - 2*BORDER) + BORDER;

        int random1 = rand.nextInt(50);
        if(random1 <= 2){
            fallingObjects.add(new FallingObject( FallingObjectType.values()[random1], rand_xpos));
        }

        int random2 = rand.nextInt(100);
        if (random2 == FallingObjectType.Coffee.getType() ||
                random2 == FallingObjectType.Vodka.getType()) {
            int rand_xpos2 = rand.nextInt(WIDTH - 2*BORDER) + BORDER;
            fallingObjects.add(new FallingObject(FallingObjectType.values()[random2], rand_xpos2));
        }
    }

    private void moveFallingObjects() {
        List<FallingObject> toBeRemoved = new ArrayList<FallingObject>();
        for (FallingObject obj: fallingObjects) {
            obj.fall();
            if (obj.getY() == HEIGHT)
                toBeRemoved.add(obj);
        }
        //delete everything from the to-be-removed list
        for (FallingObject obj: toBeRemoved)
            fallingObjects.remove(obj);
    }

    private void collectObjects() {
        for (FallingObject obj: fallingObjects) {
            if (obj.getX() < (student.getX() + VICINITY) &&
                    obj.getX() > (student.getX() - VICINITY))
                if (obj.getType() == FallingObjectType.Coffee) {
                    student.drinkCoffee();
                    coffeeRushCount = MAX_COUNT;
                }
                else if (obj.getType() == FallingObjectType.Vodka) {
                    student.changeDrunkStatus(true);
                    e = MAX_COUNT;
                }
                else
                    barMap.get(obj.getType()).increaseLevel();
        }
    }

    private void dropAllBarLevels() {
        for (LifeBar bar: barMap.values()) {
            bar.dropLevel();
        }
    }

    private void checkIsGameOver() {
        for (LifeBar bar: barMap.values()) {
            if (bar.getLevel() == 0) {
                gameOver = true;
                return;
            }
        }
    }

    private void updateStudentCoffeeStatus() {
        if (coffeeRushCount == 0) {
            student.finishCoffee();
            return;
        }
        coffeeRushCount --;
    }

    private void updateStudentSoberness() {
        if (e == 0) {
            student.changeDrunkStatus(false);
            return;
        }
        e --;
    }

    public Student getStudent(){
        return student;
    }

    public List<FallingObject> getFallingObjects(){
        return Collections.unmodifiableList(fallingObjects);
    }

    public Map<FallingObjectType, LifeBar> getBars(){
        return Collections.unmodifiableMap(barMap);
    }


    public void keyPressed(int e){
        if (e == KeyEvent.VK_LEFT)
            student.moveLeft();
        else if (e == KeyEvent.VK_RIGHT)
            student.moveRight();
    }

}
