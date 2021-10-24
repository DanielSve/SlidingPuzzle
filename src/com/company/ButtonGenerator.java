package com.company;

import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonGenerator {
    MyButton [][] buttonArray;

    public ButtonGenerator(int baseSize, ActionListener a){
        buttonArray = new MyButton[baseSize][baseSize];
        int counter = 1;
        for (int i = 0; i < baseSize ; i++) {
            for (int j = 0; j < baseSize; j++) {
                buttonArray[i][j]=(new MyButton(counter,a));
                counter++;
            }
        }
        buttonArray[baseSize-1][baseSize-1].setText("");
        buttonArray[baseSize-1][baseSize-1].setBackground(Color.black);
    }

    public MyButton[][] getButtons() {
        return buttonArray;
    }
}
