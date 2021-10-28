package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    int rows;
    int columns;
    int boardSize;
    int iClicked = 0;
    int jClicked = 0;
    int iEmpty = 0;
    int jEmpty = 0;

    GameTimer timer = new GameTimer();
    MyButton[][] buttons;
    ButtonGenerator buttonGenerator;
    JButton clickedButton;
    Color c1;
    Color c2;

    public GamePanel(int rows, int columns, Color c1, Color c2) {
        setBackground(Color.black);
        this.c1 = c1;
        this.c2 = c2;
        newGame(rows,columns, c1, c2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clickedButton = (JButton) e.getSource();
        findClicked();
        findEmpty();
        swapIfPossible(iClicked, jClicked, iEmpty, jEmpty);
        addButtonsWithNewLocations();
        revalidate();
        if (isCorrect()) {
            gameWon();
        }
    }

    public void newGame(int rows, int columns, Color c1, Color c2) {
        this.rows = rows;
        this.columns = columns;
        this.boardSize = this.rows * this.columns;
        setLayout(new GridLayout(rows, columns));
        buttonGenerator = new ButtonGenerator(rows, columns, this);
        buttons = buttonGenerator.getButtons();
        changeColor(c1, c2);
        shuffleButtons();
        addButtonsWithNewLocations();
        timer.startTimer();
    }

    public void shuffleButtons() {
        Random random = new Random();
        for (int loop = 0; loop < 1000; loop++) {
            int iRandom = random.nextInt(rows);
            int jRandom = random.nextInt(columns);
            findEmpty();
            swapIfPossible(iRandom, jRandom, iEmpty, jEmpty);
        }
    }

    public void findClicked() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (clickedButton == buttons[i][j]) {
                    iClicked = i;
                    jClicked = j;
                    break;
                }
            }
        }
    }

    public void findEmpty() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (buttons[i][j].getText().equals("")) {
                    iEmpty = i;
                    jEmpty = j;
                    break;
                }
            }
        }
    }

    public void swapIfPossible(int iClicked, int jClicked, int iEmpty, int jEmpty) {
        if (iEmpty == iClicked && jEmpty == jClicked - 1 || iEmpty == iClicked && jEmpty == jClicked + 1 ||
            iEmpty == iClicked - 1 && jEmpty == jClicked || iEmpty == iClicked + 1 && jEmpty == jClicked) {
            MyButton tempSwap = buttons[iClicked][jClicked];
            buttons[iClicked][jClicked] = buttons[iEmpty][jEmpty];
            buttons[iEmpty][jEmpty] = tempSwap;
            revalidate();
        }
    }

    public void addButtonsWithNewLocations() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                add(buttons[i][j]);
            }
        }
    }

    public void changeColor(Color c1, Color c2) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                buttons[i][j].setBackgroundColor(c1, c2);
                if (buttons[i][j].getText().equals("")) {
                    buttons[i][j].setBackground(Color.black);
                }
            }
        }
    }

    public boolean isCorrect() {
        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (buttons[i][j].getNr() == counter + 1) {
                    counter++;
                }
            }
        }
        return counter == boardSize;
    }

    public void gameWon() {
        String winMessage = timer.stopTimer();
        int choice = JOptionPane.showConfirmDialog(null, winMessage+"\nNew Game?", "Congratulations, You won!", JOptionPane.YES_NO_OPTION);
        if (choice == 0) {
            removeAll();
            revalidate();
            newGame(rows, columns, c1, c2);
        } else {
            int exit = JOptionPane.showConfirmDialog(null, "Exit Game?", "Exit?", JOptionPane.YES_NO_OPTION);
            if (exit == 1) {
                removeAll();
                revalidate();
                newGame(rows, columns, c1, c2);
            } else {
                System.exit(0);
            }
        }
    }
}
