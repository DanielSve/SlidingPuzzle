package com.company;

import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonGenerator {
    MyButton [][] buttonArray;

    public ButtonGenerator(int rows, int columns, ActionListener a) {
        buttonArray = new MyButton[rows][columns];
        int counter = 1;
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < columns; j++) {
                buttonArray[i][j]=(new MyButton(counter,a,Color.yellow,Color.orange));
                counter++;
            }
        }
        buttonArray[rows-1][columns-1].setText("");
        buttonArray[rows-1][columns-1].setNr(0);
        buttonArray[rows-1][columns-1].setBackground(Color.black);
    }

    public MyButton[][] getButtons() {
        return buttonArray;
    }

}
