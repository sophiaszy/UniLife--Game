package com.company.model;

/**
 * Created by SophiaShen on 2017-06-11.
 */

import java.awt.event.KeyEvent;
import java.util.*;

public class UniLifeGame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int CTR_X = WIDTH / 2;

    private static final int BORDER = 25; //TODO

    private int sobernessCount;
    private int coffeeRushCount;
    private int barCount = 0;
    private static final int MAX_COUNT = 25;
    private String message = "";
    private Random rand = new Random();
    private List<FallingObject> fallingObjects;
    private Student student;
    private boolean gameOver = false;
    private boolean gameStarted = false;
    private Map<FallingObjectType, LifeBar> barMap;

    public int score;

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
    public boolean hasStarted(){return gameStarted;}
    public void startGame(){gameStarted = true;}

    public void update() {
        //TODO
        if(!gameOver && gameStarted) {
            addFallingObjects();
            collectObjects();
            moveFallingObjects();
            dropAllBarLevels();
            updateStudentCoffeeStatus();
            updateStudentSoberness();
            checkIsGameOver();
            updateMessage();
        }
    }


    private void addFallingObjects() {
        int rand_xpos = rand.nextInt(WIDTH - 2*BORDER) + BORDER;

        int random1 = rand.nextInt(20);
        if(random1 <= 2){
            fallingObjects.add(new FallingObject( FallingObjectType.values()[random1], rand_xpos));
        }

        int random2 = rand.nextInt(80);
        if (random2 == FallingObjectType.Coffee.getType() ||
                random2 == FallingObjectType.Beer.getType()) {
            int rand_xpos2 = rand.nextInt(WIDTH - 2*BORDER) + BORDER;
            fallingObjects.add(new FallingObject(FallingObjectType.values()[random2], rand_xpos2));
        }
    }

    private void moveFallingObjects() {
        List<FallingObject> toBeRemoved = new ArrayList<FallingObject>();
        for (FallingObject obj: fallingObjects) {
            obj.fall();
            if (obj.getY() == HEIGHT || obj.isCollected())
                toBeRemoved.add(obj);
        }
        //delete everything from the to-be-removed list
        for (FallingObject obj: toBeRemoved)
            fallingObjects.remove(obj);
    }

    private void collectObjects() {
        for (FallingObject obj: fallingObjects) {
            if (obj.isCollected())
                return;
            if (obj.getX() < (student.getX() + Student.STUDENT_WIDTH) &&
                    obj.getX()+35 > (student.getX()) &&
                    obj.getY() < (student.getY()+ Student.STUDENT_HEIGHT) &&
                    obj.getY()+35 > student.getY()) {
                if (obj.getType() == FallingObjectType.Coffee) {
                    student.drinkCoffee();
                    coffeeRushCount = MAX_COUNT;
                } else if (obj.getType() == FallingObjectType.Beer) {
                    student.changeDrunkStatus(true);
                    sobernessCount = MAX_COUNT;
                } else
                    barMap.get(obj.getType()).increaseLevel();
                    score +=1;
                obj.setCollected();
            }
        }
    }

    private void dropAllBarLevels() {
        if (barCount == 0) {
            for (LifeBar bar : barMap.values()) {
                bar.dropLevel();
            }
        }
        barCount = (barCount+1)%3;
    }

    private void checkIsGameOver() {
        for (LifeBar bar: barMap.values()) {
            if (bar.getLevel() == 0) {
                gameOver = true;
                return;
            }
        }
    }

    private void updateMessage(){
        if(coffeeRushCount!=0){
            message = "Coffee Rush: Super Speed!";
        }else if (sobernessCount !=0){
            message = "Too drunk to move!";
        }else if(gameOver){
            message = "Your final score: " + score;
        }else{
            message = "";
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
        if (sobernessCount == 0) {
            student.changeDrunkStatus(false);
            return;
        }
        sobernessCount--;
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

    public String getMessage() { return message;}


    public void keyPressed(int e){
        if (e == KeyEvent.VK_LEFT)
            student.moveLeft();
        else if (e == KeyEvent.VK_RIGHT)
            student.moveRight();
        else if(e == KeyEvent.VK_Q){
            System.exit(0);
        }else if(e == KeyEvent.VK_R){
            restart();
        }else if(e == KeyEvent.VK_SPACE){
            startGame();
        }
    }

    public void restart(){
        fallingObjects.clear();
        barMap.clear();
        barMap.put(FallingObjectType.Food, new LifeBar());
        barMap.put(FallingObjectType.Book, new LifeBar());
        barMap.put(FallingObjectType.Sleep, new LifeBar());
        message= "";
        rand = new Random();
        student.center();
        sobernessCount = 0;
        coffeeRushCount = 0;
        barCount = 0 ;
        score = 0;
        gameOver = false;
    }

}
