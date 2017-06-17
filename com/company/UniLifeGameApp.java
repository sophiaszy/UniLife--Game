package com.company;

import com.company.model.UniLifeGame;

import javax.swing.*;

/**
 * Created by anita on 6/12/2017.
 */

public class UniLifeGameApp {

    public UniLifeGameApp(){
        JFrame frame = new JFrame("UniLife Game");
        frame.setSize(UniLifeGame.WIDTH,UniLifeGame.HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
	/* write your code here */
        new UniLifeGameApp();
    }

}
