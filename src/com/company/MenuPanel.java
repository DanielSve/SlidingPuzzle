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
    MenuItems menuItems;
    JMenuItem[] colorMenuItems;
    JMenuItem[] newGameMenuItems;
    ActionListener newGameMenuListener;
    ActionListener colorMenuListener;

    public MenuPanel() {
        setLayout(new FlowLayout());
        menuItems = new MenuItems(this);
        newGameMenu = new JMenu("    New Game");
        colorMenu = new JMenu(" Change color");
        colorMenu.setPreferredSize(new Dimension(120,20));
        newGameMenu.setPreferredSize(new Dimension(120,20));
        colorMenuBar = new JMenuBar();
        newGameMenuBar = new JMenuBar();

        newGameMenuItems = menuItems.getNewGameMenuItems();
        colorMenuItems = menuItems.getColorMenuItems();
        addItemsToMenu(colorMenu, colorMenuItems);
        addItemsToMenu(newGameMenu, newGameMenuItems);

        newGameMenuBar.add(newGameMenu);
        colorMenuBar.add(colorMenu);

        add(newGameMenuBar,"left");
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
        if(e.getSource() == colorMenu.getItem(0) || e.getSource() == colorMenu.getItem(1) ||
                e.getSource() == colorMenu.getItem(2)){
            colorMenuListener.actionPerformed(e);

        } else if(e.getSource() == newGameMenu.getItem(0) || e.getSource() == newGameMenu.getItem(1) ||
                e.getSource() == newGameMenu.getItem(2)) {
            newGameMenuListener.actionPerformed(e);
        }
    }

    public void addItemsToMenu(JMenu jMenu, JMenuItem [] jMenuItems){
        for (int i = 0; i <jMenuItems.length ; i++) {
            jMenu.add(jMenuItems[i]);
        }
    }
}
