package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    GamePanel gamePanel;
    MenuPanel menuPanel;
    Color colorChoice1 = Color.yellow;
    Color colorChoice2 = Color.orange;

    public MyFrame() {
        setLayout(new BorderLayout());
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel(colorChoice1,colorChoice2);

        menuPanel.setNewGameListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(gamePanel);
                gamePanel = new GamePanel(colorChoice1,colorChoice2);
                add(gamePanel);
                revalidate();
            }
        });

        menuPanel.setMenuListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj==menuPanel.color1){
                    System.out.println("Yellow");
                    colorChoice1 = Color.yellow;
                    colorChoice2 = Color.orange;
                    gamePanel.changeColor(colorChoice1, colorChoice2);
                }
                if(obj==menuPanel.color2){
                    System.out.println("Green");
                    colorChoice1 = Color.green;
                    colorChoice2 = Color.white;
                    gamePanel.changeColor(colorChoice1, colorChoice2);
                }
                if(obj==menuPanel.color3){
                    System.out.println("Blue");
                    colorChoice1 = Color.blue;
                    colorChoice2 = Color.cyan;
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
