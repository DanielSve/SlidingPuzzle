package com.company;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuItems {
    JMenuItem color1, color2, color3;
    JMenuItem newGame1, newGame2, newGame3;
    JMenuItem[] colorMenuItems;
    JMenuItem[] newGameMenuItems;

    public MenuItems(ActionListener a) {
      color1 = new JMenuItem("Yellow");
      color2 = new JMenuItem("Green");
      color3 = new JMenuItem("Blue");
      color1.addActionListener(a);
      color2.addActionListener(a);
      color3.addActionListener(a);

      newGame1 = new JMenuItem("Size 3 x 3");
      newGame2 = new JMenuItem("Size 4 x 4");
      newGame3 = new JMenuItem("Size 5 x 5");
      newGame1.addActionListener(a);
      newGame2.addActionListener(a);
      newGame3.addActionListener(a);

      colorMenuItems = new JMenuItem[] {color1, color2, color3};
      newGameMenuItems = new JMenuItem[] {newGame1, newGame2, newGame3};
    }

    public JMenuItem[] getColorMenuItems() {
        return colorMenuItems;
    }

    public JMenuItem[] getNewGameMenuItems() {
        return newGameMenuItems;
    }
}
