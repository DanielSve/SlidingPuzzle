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
        setLayout(new GridBagLayout());
        menuItems = new MenuItems(this);
        newGameMenu = new JMenu("New Puzzle");
        colorMenu = new JMenu("Color Theme");
        colorMenuBar = new JMenuBar();
        newGameMenuBar = new JMenuBar();

        newGameMenuItems = menuItems.getNewGameMenuItems();
        colorMenuItems = menuItems.getColorMenuItems();
        addItemsToMenu(colorMenu, colorMenuItems);
        addItemsToMenu(newGameMenu, newGameMenuItems);

        newGameMenuBar.add(newGameMenu);
        colorMenuBar.add(colorMenu);

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(6,10,6,2);

        add(newGameMenuBar,gc);
        gc.gridx = 1;
        add(colorMenuBar,gc);
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
