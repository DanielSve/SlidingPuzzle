package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyButton extends JButton {

    int nr;

    public MyButton(int nr, ActionListener a){
        this.nr = nr;
        if (nr % 2 == 0) {
            setBackground(Color.yellow);
        } else {
            setBackground(Color.orange);
        }
        setBorder(BorderFactory.createLineBorder(Color.black));
        setOpaque(true);
        setPreferredSize(new Dimension(100,100));
        setHorizontalAlignment(SwingConstants.CENTER);
        this.setText(""+nr);
        this.addActionListener(a);
    }

    public int getNr() {
        return nr;
    }
}
