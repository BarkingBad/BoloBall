package front;

import back.Playground;
import back.Tiles;

import javax.swing.*;


public class Frame extends JFrame {
    public Frame(Playground p) {
        super();
        add(new Panel(p));
        pack();
        setTitle("BoloBall");
        setSize(GameLoop.WIDTH, GameLoop.HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
