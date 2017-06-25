package com.company;

import com.company.model.UniLifeGame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by SophiaShen on 2017-06-25.
 */
public class ULStatePanel extends JPanel {
    //TOP RIGHT CORNER

    UniLifeGame controller;

    public ULStatePanel(UniLifeGame controller) {
        this.controller = controller;
        setSize(100, 100);
        setBackground(new Color(200, 255, 255));
        setLocation(UniLifeGame.WIDTH/3*2, 0);
    }




}
