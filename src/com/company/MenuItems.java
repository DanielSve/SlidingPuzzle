package com.company;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuItems {
    JMenuItem color1, color2, color3;
    JMenuItem newGame1, newGame2, newGame3, newGame4, newGame5;
    JMenuItem[] colorMenuItems;
    JMenuItem[] newGameMenuItems;

    public MenuItems(ActionListener a) {
      color1 = new JMenuItem("Yellow");
      color2 = new JMenuItem("Green");
      color3 = new JMenuItem("Blue");
      color1.addActionListener(a);
      color2.addActionListener(a);
      color3.addActionListener(a);

      newGame1 = new JMenuItem("Size 2 x 2");
      newGame2 = new JMenuItem("Size 3 x 3");
      newGame3 = new JMenuItem("Size 4 x 4");
      newGame4 = new JMenuItem("Size 5 x 5");
      newGame5 = new JMenuItem("Size 3 x 8");

      newGame1.addActionListener(a);
      newGame2.addActionListener(a);
      newGame3.addActionListener(a);
      newGame4.addActionListener(a);
      newGame5.addActionListener(a);

      colorMenuItems = new JMenuItem[] {color1, color2, color3};
      newGameMenuItems = new JMenuItem[] {newGame1, newGame2, newGame3, newGame4, newGame5};
    }

    public JMenuItem[] getColorMenuItems() {
        return colorMenuItems;
    }

    public JMenuItem[] getNewGameMenuItems() {
        return newGameMenuItems;
    }
}
