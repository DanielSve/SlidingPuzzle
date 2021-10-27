package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    GamePanel gamePanel;
    MenuPanel menuPanel;
    Colors colors = new Colors();

    public MyFrame() {
        setTitle("Sliding Puzzle");
        setLayout(new BorderLayout());
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel(4,colors.currentColor1,colors.currentColor2);

        menuPanel.setNewGameMenuListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                remove(gamePanel);
                if(e.getSource() == menuPanel.newGameMenu.getItem(0)) {
                    gamePanel = new GamePanel(3, colors.currentColor1, colors.currentColor2);
                } else if(e.getSource() == menuPanel.newGameMenu.getItem(1)){
                    gamePanel = new GamePanel(4, colors.currentColor1, colors.currentColor2);
                } else if(e.getSource() == menuPanel.newGameMenu.getItem(2)) {
                    gamePanel = new GamePanel(5, colors.currentColor1, colors.currentColor2);
                }

                add(gamePanel);
                revalidate();
            }
        });

        menuPanel.setColorMenuListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == menuPanel.colorMenu.getItem(0)){
                    System.out.println("Yellow");
                    colors.currentColor1 = Color.yellow;
                    colors.currentColor2 = Color.orange;
                    gamePanel.changeColor(colors.currentColor1, colors.currentColor2);
                }
                if(obj == menuPanel.colorMenu.getItem(1)){
                    System.out.println("Green");
                    colors.currentColor1 = colors.myGreen1;
                    colors.currentColor2 = colors.myGreen2;
                    gamePanel.changeColor(colors.currentColor1, colors.currentColor2);
                }
                if(obj == menuPanel.colorMenu.getItem(2)){
                    System.out.println("Blue");
                    colors.currentColor1 = colors.myBlue1;
                    colors.currentColor2 = colors.myBlue2;
                    gamePanel.changeColor(colors.currentColor1, colors.currentColor2);
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
