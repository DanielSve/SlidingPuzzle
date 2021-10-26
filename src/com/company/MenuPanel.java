package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    JMenu colorMenu;
    JMenu newGameMenu;
    JMenuBar colorMenuBar;
    JMenuBar newGameMenuBar;
    MenuItems menuItems = new MenuItems(this);
    JMenuItem[] colorMenuItems;
    JMenuItem[] newGameMenuItems;
    ActionListener newGameMenuListener;
    ActionListener colorMenuListener;

    public MenuPanel(){
        setLayout(new FlowLayout());
        newGameMenu = new JMenu("New Game");
        colorMenu = new JMenu("Change color");
        colorMenuBar = new JMenuBar();
        newGameMenuBar = new JMenuBar();

        newGameMenuItems = menuItems.getNewGameMenuItems();
        colorMenuItems = menuItems.getColorMenuItems();
        System.out.println(colorMenuItems.length);
        addItemsToMenu(colorMenu, colorMenuItems);
        addItemsToMenu(newGameMenu, newGameMenuItems);

        newGameMenuBar.add(newGameMenu);
        colorMenuBar.add(colorMenu);

        add(newGameMenuBar);
        add(colorMenuBar);
    }

    public void setNewGameMenuListener(ActionListener newGameMenuListener) {
        this.newGameMenuListener = newGameMenuListener;
    }

    public void setColorMenuListener(ActionListener colorMenuListener) {
        this.colorMenuListener = colorMenuListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == colorMenu.getItem(0) || e.getSource() == colorMenu.getItem(1) || e.getSource() == colorMenu.getItem(2)){
            colorMenuListener.actionPerformed(e);
        } else if(e.getSource() == newGameMenu.getItem(0) || e.getSource() == newGameMenu.getItem(1) || e.getSource() == newGameMenu.getItem(2)) {
            newGameMenuListener.actionPerformed(e);
        }
    }

    public void addItemsToMenu(JMenu jMenu, JMenuItem [] jMenuItems){
        for (int i = 0; i <jMenuItems.length ; i++) {
            jMenu.add(jMenuItems[i]);
        }
    }
}
