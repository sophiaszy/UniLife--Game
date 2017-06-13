package com.company.model;

/**
 * Created by SophiaShen on 2017-06-11.
 */

import java.util.ArrayList;
import java.util.List;

public class UniLifeGame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int CTR_X = WIDTH / 2;
    public static final int CTR_Y = HEIGHT / 2;

    private List<FallingObject> fallingObjects;
    private Student student;
    private boolean gameOver = false;

    public UniLifeGame(){
        student = new Student();
        fallingObjects = new ArrayList<FallingObject>();
    }


}
