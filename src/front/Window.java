package front;

import back.Tiles;

import javax.swing.*;


public class Window extends JFrame {
    public Window() {
        add(new Surface());
        add()
        setTitle("BoloBall");
        setSize(GameLoop.WIDTH, GameLoop.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
