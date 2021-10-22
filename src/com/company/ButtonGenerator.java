package com.company;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;


public class ButtonGenerator {
        MyButton [] buttonArray;
        ArrayList<MyButton> buttonList;

    public ButtonGenerator(ActionListener a){
        buttonArray = new MyButton[16];

        for (int i = 0; i < buttonArray.length ; i++) {
            buttonArray[i]=(new MyButton("" +(i+1),a));
        }
        buttonArray[15].setText("");
        buttonArray[15].setBackground(Color.black);
        buttonList = new ArrayList(Arrays.asList(buttonArray));
    }

    public ArrayList<MyButton> getButtonList() {
        return buttonList;
    }

}
