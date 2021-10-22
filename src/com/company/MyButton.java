package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyButton extends JButton {

    public MyButton(String nr, ActionListener a){
        setBackground(Color.yellow);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setOpaque(true);
        setPreferredSize(new Dimension(100,100));
        setHorizontalAlignment(SwingConstants.CENTER);
        this.setText(nr);
        this.addActionListener(a);
    }
}
