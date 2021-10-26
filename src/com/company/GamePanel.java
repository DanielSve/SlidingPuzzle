package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    int baseSize;
    int rows;
    int columns;
    int boardSize;
    Color myGreen1 = new Color(212,255,225);
    Color myGreen2 = new Color(123,189,143);
    Color myBlue1 = new Color(212,245,255);
    Color myBlue2 = new Color(115,187,209);

    MyButton[][] buttons;
    ButtonGenerator buttonGenerator;
    JButton clickedButton;
    boolean swappable;
    Color c1;
    Color c2;

    public GamePanel(int baseSize, Color colorChoice1, Color colorChoice2) {
        c1 = colorChoice1;
        c2 = colorChoice2;
        newGame(baseSize, c1,c2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        swappable = false;
        clickedButton = (JButton) e.getSource();
        findClickedAndEmpty();
        addButtonsWithNewLocations();
        revalidate();
        if (isCorrect()) {
            int choice = JOptionPane.showConfirmDialog(null,"Congratulations! New Game?","You won!",JOptionPane.YES_NO_OPTION);
            if (choice == 0) {
                removeAll();
                revalidate();
                newGame(baseSize,c1,c2);
            } else {
                int exit = JOptionPane.showConfirmDialog(null,"Exit Game?","Exit?",JOptionPane.YES_NO_OPTION);
                if (exit == 1) {
                    removeAll();
                    revalidate();
                    newGame(baseSize,c1,c2);
                } else {
                    System.exit(0);
                }
            }
            swappable = false;
        }
    }
    public void newGame(int baseSize, Color c1, Color c2) {
        this.baseSize = baseSize;
        this.rows = baseSize;
        this.columns = baseSize;
        this.boardSize = this.rows * this.columns;
        setLayout(new GridLayout(rows, columns));
        buttonGenerator = new ButtonGenerator(rows,columns, this);
        buttons = buttonGenerator.getButtons();
        changeColor(c1,c2);
        shuffleButtons();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                add(buttons[i][j]);
            }
        }
    }
    public void shuffleButtons() {
        Random rnd = new Random();
        do {
            for (int i = rows - 1; i > 1; i--) {
                for (int j = columns - 1; j > 1; j--) {
                    swapPlace(rnd.nextInt(i), rnd.nextInt(j), rows - 1, rnd.nextInt(j));
                    swapPlace(rows - 1, columns - 1, rnd.nextInt(i), rnd.nextInt(j));
                }
            }
        } while (!isSolvable());
    }
    // This method was inspired by Geeks for Geeks but does not work for 4+ games
    public boolean isSolvable() {
        if (baseSize < 3) {
            swapPlace(0, baseSize - 1, 1, 1);
            return true;
        } else {
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
        if (buttons[rows-1][columns-1].getText().equals("")) {
            counter++;
        }
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
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < columns; j++) {
                buttons[i][j].setBackgroundColor(c1,c2);
                if (buttons[i][j].getText().equals("")) {
                    buttons[i][j].setBackground(Color.black);
                }
            }
        }
    }
}
