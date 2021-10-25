package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    JButton newGameButton;
    JMenu menu;
    JMenuBar menuBar;
    JMenuItem color1, color2, color3;
    ActionListener newGameListener;
    ActionListener menuListener;
    public MenuPanel(){
        setLayout(new FlowLayout());
        newGameButton = new JButton("New Game");
        menu = new JMenu("Change color");
        menuBar = new JMenuBar();
        color1 = new JMenuItem("Yellow");
        color2 = new JMenuItem("Green");
        color3 = new JMenuItem("Blue");

        menu.add(color1);
        menu.add(color2);
        menu.add(color3);
        menuBar.add(menu);

        color1.addActionListener(this);
        color2.addActionListener(this);
        color3.addActionListener(this);
        newGameButton.addActionListener(this);

        add(newGameButton);
        add(menuBar);
    }

    public void setNewGameListener(ActionListener newGameListener) {
        this.newGameListener = newGameListener;
    }

    public void setMenuListener(ActionListener menuListener) {
        this.menuListener = menuListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == color1 || e.getSource() == color2 || e.getSource() == color3){
            menuListener.actionPerformed(e);
        } else if(e.getSource()==newGameButton) {
            newGameListener.actionPerformed(e);
        }
    }
}
