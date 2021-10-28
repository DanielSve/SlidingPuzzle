package com.company;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    GamePanel gamePanel;
    MenuPanel menuPanel;
    Colors colors = new Colors();

    public MainFrame() {
        setTitle("Sliding Puzzle");
        setLayout(new BorderLayout());
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel(4,4,colors.currentColor1,colors.currentColor2);

        menuPanel.setNewGameMenuListener(e -> {

            remove(gamePanel);
            if(e.getSource() == menuPanel.newGameMenu.getItem(0)) {
                gamePanel = new GamePanel(2,2, colors.currentColor1, colors.currentColor2);
            } else if(e.getSource() == menuPanel.newGameMenu.getItem(1)){
                gamePanel = new GamePanel(3,3, colors.currentColor1, colors.currentColor2);
            } else if(e.getSource() == menuPanel.newGameMenu.getItem(2)) {
                gamePanel = new GamePanel(4,4, colors.currentColor1, colors.currentColor2);
            } else if(e.getSource() == menuPanel.newGameMenu.getItem(3)) {
                gamePanel = new GamePanel(5,5, colors.currentColor1, colors.currentColor2);
            } else if(e.getSource() == menuPanel.newGameMenu.getItem(4)) {
                gamePanel = new GamePanel(5,8, colors.currentColor1, colors.currentColor2);
            }

            add(gamePanel);
            revalidate();
        });

        menuPanel.setColorMenuListener(e -> {
            Object obj = e.getSource();
            if(obj == menuPanel.colorMenu.getItem(0)){
                colors.currentColor1 = Color.yellow;
                colors.currentColor2 = Color.orange;
                gamePanel.changeColor(colors.currentColor1, colors.currentColor2);
            }
            if(obj == menuPanel.colorMenu.getItem(1)){
                colors.currentColor1 = colors.myGreen1;
                colors.currentColor2 = colors.myGreen2;
                gamePanel.changeColor(colors.currentColor1, colors.currentColor2);
            }
            if(obj == menuPanel.colorMenu.getItem(2)){
                colors.currentColor1 = colors.myBlue1;
                colors.currentColor2 = colors.myBlue2;
                gamePanel.changeColor(colors.currentColor1, colors.currentColor2);
            }
        });

        add(menuPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
}
