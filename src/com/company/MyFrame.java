package com.company;

import javax.swing.*;

public class MyFrame extends JFrame {
        MyPanel myPanel;

    public MyFrame(){
        myPanel = new MyPanel();
        add(myPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
}
