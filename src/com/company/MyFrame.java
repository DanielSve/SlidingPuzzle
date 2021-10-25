package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
        GamePanel gamePanel;
        MenuPanel menuPanel;

    public MyFrame() {
        setLayout(new BorderLayout());
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();

        menuPanel.setNewGameListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(gamePanel);
                gamePanel = new GamePanel();
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
                    gamePanel.changeColor(Color.yellow, Color.orange);
                }
                if(obj==menuPanel.color2){
                    System.out.println("Green");
                    gamePanel.changeColor(Color.green, Color.white);
                }
                if(obj==menuPanel.color3){
                    System.out.println("Blue");
                    gamePanel.changeColor(Color.blue, Color.cyan);
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
