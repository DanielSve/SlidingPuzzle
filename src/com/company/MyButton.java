package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyButton extends JButton {

    int nr;

    public MyButton(int nr, ActionListener a, Color c1, Color c2) {
        this.nr = nr;
        setBackgroundColor(c1,c2);
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

    public void setNr(int number) {
        this.nr = number;
    }

    public void setBackgroundColor(Color c1, Color c2){
        if (nr % 2 == 0) {
            setBackground(c1);
        } else {
            setBackground(c2);
        }
    }
}
