package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class MyPanel extends JPanel implements ActionListener {

        ArrayList<MyButton> buttons;
        ButtonGenerator buttonGenerator;
        int clicked = 0;
        int empty = 0;
        JButton clickedButton;
        boolean swappable;

    public MyPanel(){
        setLayout(new GridLayout(4,4));
        buttonGenerator = new ButtonGenerator(this);
        buttons = buttonGenerator.getButtonList();
        Collections.shuffle(buttons);

        for (int i = 0; i < buttons.size(); i++) {
            add(buttons.get(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clicked = 0;
        swappable = false;
        clickedButton = (JButton) e.getSource();
        findEmpty();
        swapIfPossible();
        addButtonsWithNewLocations();
        revalidate();
        if(isCorrect()){
            System.out.println("TRUE");
        }
    }

    public boolean isCorrect (){
        int counter = 0;
        for (int i = 0; i <buttons.size() ; i++) {
            if (buttons.get(i).getText().equals(Integer.toString(i+1))){
                counter++;
            }
        }
        if(buttons.get(15).getText().equals("")){
            counter++;
        }
        System.out.println(counter);
        return counter == 16;
    }

    public void checkSwappable() {

        if (clicked == 1 || clicked == 2 || clicked == 5 || clicked == 6 || clicked == 9 || clicked == 10 || clicked == 13
                || clicked == 14) {
            if (empty == clicked - 1 || empty == clicked + 1 || empty == clicked - 4 || empty == clicked + 4) {
                swappable = true;
            }
        } else if (clicked == 0 || clicked == 4 || clicked == 8 || clicked == 12) {
            if (empty == clicked + 1 || empty == clicked - 4 || empty == clicked + 4) {
                swappable = true;
            }
        } else if (clicked == 3 || clicked == 7 || clicked == 11 || clicked == 15) {
            if (empty == clicked - 1 || empty == clicked - 4 || empty == clicked + 4) {
                swappable = true;
            }
        } else {
            swappable = false;
        }
    }

    public void findEmpty(){
        for (int j = 0; j <buttons.size() ; j++) {
            if(buttons.get(j).getText().equals("")){
                empty = j;
                System.out.println( "EMPTY" + j);
            }
        }
    }
    public void swapIfPossible(){
        for (int i = 0; i < buttons.size() ; i++) {
            if (clickedButton == buttons.get(i)) {
                clicked = i;
                checkSwappable();
                if (swappable) {
                    Collections.swap(buttons, i, empty);
                }
            }
        }
    }

    public void addButtonsWithNewLocations(){
        for (int i = 0; i < buttons.size(); i++) {
            add(buttons.get(i));
        }
    }
}
