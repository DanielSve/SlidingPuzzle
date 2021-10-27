package com.company;

import javax.swing.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    int baseSize;
    int rows;
    int columns;
    int boardSize;
    int iClicked = 0;
    int jClicked = 0;
    int iEmpty = 0;
    int jEmpty = 0;

    Temporal now;
    MyButton[][] buttons;
    ButtonGenerator buttonGenerator;
    JButton clickedButton;
    Color c1;
    Color c2;

    public GamePanel(int baseSize, Color c1, Color c2) {
        this.c1 = c1;
        this.c2 = c2;
        newGame(baseSize, c1, c2);
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
            try {
                gameWon();
            } catch (DatatypeConfigurationException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void newGame(int baseSize, Color c1, Color c2) {
        this.baseSize = baseSize;
        this.rows = baseSize;
        this.columns = baseSize;
        this.boardSize = this.rows * this.columns;
        setLayout(new GridLayout(rows, columns));
        buttonGenerator = new ButtonGenerator(rows, columns, this);
        buttons = buttonGenerator.getButtons();
        changeColor(c1, c2);
        shuffleButtons();
        addButtonsWithNewLocations();
        now = LocalTime.now();
    }

    public void shuffleButtons() {
        Random random = new Random();
        for (int loop = 0; loop < 1000; loop++) {
            int iRandom = random.nextInt(baseSize);
            int jRandom = random.nextInt(baseSize);
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
        if (buttons[rows - 1][columns - 1].getText().equals("")) {
            counter++;
        }
        return counter == boardSize;
    }

    public void gameWon() throws DatatypeConfigurationException {

        Duration elapsedTime = Duration.between(now, LocalTime.now());
        javax.xml.datatype.Duration elapsed = DatatypeFactory.newInstance().newDuration(String.valueOf(elapsedTime));

        int choice = JOptionPane.showConfirmDialog(null, "Your time was: "+elapsed.getMinutes()+"."+elapsed.getSeconds()+" minutes!\nNew Game?", "Congratulations, You won!", JOptionPane.YES_NO_OPTION);
        if (choice == 0) {
            removeAll();
            revalidate();
            newGame(baseSize, c1, c2);
        } else {
            int exit = JOptionPane.showConfirmDialog(null, "Exit Game?", "Exit?", JOptionPane.YES_NO_OPTION);
            if (exit == 1) {
                removeAll();
                revalidate();
                newGame(baseSize, c1, c2);
            } else {
                System.exit(0);
            }
        }
    }
}
