package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    int baseSize = 4;
    int rows = baseSize;
    int columns = baseSize;
    int boardSize = rows * columns;

    MyButton[][] buttons;
    ButtonGenerator buttonGenerator;
    JButton clickedButton;
    boolean swappable;

    public GamePanel() {
        setLayout(new GridLayout(rows, columns));
        buttonGenerator = new ButtonGenerator(baseSize, this);
        buttons = buttonGenerator.getButtons();
        shuffleButtons();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                add(buttons[i][j]);
            }
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        swappable = false;
        clickedButton = (JButton) e.getSource();
        findClickedAndEmpty();
        addButtonsWithNewLocations();
        revalidate();
        if (isCorrect()) {
            System.out.println("TRUE");
        }
    }
    public void shuffleButtons() {
        Random rnd = new Random();
        do {
            for (int i = baseSize - 1; i > 1; i--) {
                for (int j = baseSize - 1; j > 1; j--) {
                    swapPlace(rnd.nextInt(j), rnd.nextInt(i), baseSize - 1, rnd.nextInt(i));
                    swapPlace(baseSize - 1, baseSize - 1, rnd.nextInt(j), rnd.nextInt(i));
                }
            }
        } while (!isSolvable());
    }
    // This method was inspired by Geeks for Geeks but does not work for 4+ games
    public boolean isSolvable() {
        int invCount = 0;
        for (int i = 0; i < rows - 1; i++) {
            for (int j = i + 1; j < columns; j++) {
                if (buttons[j][i].getNr() > 0 && buttons[j][i].getNr() > buttons[i][j].getNr()) {
                    invCount++;
                }
            }
        }
        return (invCount % 2 == 0);
    }
    public boolean isCorrect() {
        int counter = 0;
        int compare = 1;
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < columns; j++) {
                if (buttons[i][j].getText().equals(Integer.toString(compare))){
                    counter++;
                    compare++;
                }
            }
        }
        if(buttons[rows-1][columns-1].getText().equals("")){
            counter++;
        }
        System.out.println(counter);
        return counter == boardSize;
    }
    public void checkSwappable(int i, int j, int x, int y) {
        if (x == i && y == j - 1 || x == i && y == j + 1 || x == i - 1 && y == j || x == i + 1 && y == j) {
            swappable = true;
        } else {
            swappable = false;
        }
    }
    public void findClickedAndEmpty() {
        int rowA = 0; int colA = 0; int rowB = 0; int colB = 0;
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < columns; j++) {
                if (clickedButton == buttons[i][j]) {
                    rowA = i;
                    colA = j;
                    break;
                }
            }
        }
        for (int x = 0; x <rows ; x++) {
            for (int y = 0; y <columns ; y++) {
                if (buttons[x][y].getText().equals("")){
                    rowB = x;
                    colB = y;
                    break;
                }
            }
        }
        checkSwappable(rowA,colA,rowB,colB);
        if (swappable) {
            swapPlace(rowA,colA,rowB,colB);
        }
    }
    public void swapPlace(int i, int j, int x, int y) {
        MyButton temp = buttons[i][j];
        buttons[i][j] = buttons[x][y];
        buttons[x][y] = temp;
        revalidate();
    }
    public void addButtonsWithNewLocations(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                add(buttons[i][j]);
            }
        }
    }
    public void changeColor(Color c1, Color c2){
        for (int i = 0; i < buttons.length ; i++) {
            for (int j = 0; j <buttons.length; j++) {
                buttons[i][j].setBackgroundColor(c1,c2);
                if (buttons[i][j].getText().equals("")) {
                    buttons[i][j].setBackground(Color.black);
                }
            }
        }

    }
}
