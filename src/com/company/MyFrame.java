package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    GamePanel gamePanel;
    MenuPanel menuPanel;
    int baseSize = 4;
    Color colorChoice1 = Color.yellow;
    Color colorChoice2 = Color.orange;

    public MyFrame() {
        setLayout(new BorderLayout());
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel(baseSize,colorChoice1,colorChoice2);

        menuPanel.setNewGameMenuListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                remove(gamePanel);
                if(e.getSource()==menuPanel.newGameMenu.getItem(0)) {
                    gamePanel = new GamePanel(3, colorChoice1, colorChoice2);
                } else if(e.getSource()==menuPanel.newGameMenu.getItem(1)){
                    gamePanel = new GamePanel(4, colorChoice1, colorChoice2);
                } else if(e.getSource()==menuPanel.newGameMenu.getItem(2)) {
                    gamePanel = new GamePanel(5, colorChoice1, colorChoice2);
                }

                add(gamePanel);
                revalidate();
            }
        });

        menuPanel.setColorMenuListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj==menuPanel.colorMenu.getItem(0)){
                    System.out.println("Yellow");
                    colorChoice1 = Color.yellow;
                    colorChoice2 = Color.orange;
                    gamePanel.changeColor(colorChoice1, colorChoice2);
                }
                if(obj==menuPanel.colorMenu.getItem(1)){
                    System.out.println("Green");
                    colorChoice1 = gamePanel.myGreen1;
                    colorChoice2 = gamePanel.myGreen2;
                    gamePanel.changeColor(colorChoice1, colorChoice2);
                }
                if(obj==menuPanel.colorMenu.getItem(2)){
                    System.out.println("Blue");
                    colorChoice1 = gamePanel.myBlue1;
                    colorChoice2 = gamePanel.myBlue2;
                    gamePanel.changeColor(colorChoice1, colorChoice2);
                }
            }
        });

        add(menuPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
}
