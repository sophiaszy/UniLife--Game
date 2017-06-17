package com.company;

import com.company.model.UniLifeGame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by SophiaShen on 2017-06-17.
 */
public class ULGamePanel extends JPanel {
    //TODO

    UniLifeGame controller;

    public ULGamePanel(UniLifeGame controller) {
        this.controller = controller;
        setSize(UniLifeGame.WIDTH, UniLifeGame.HEIGHT);
        setBackground(new Color(120, 150, 255));

    }
}

