package front;

import back.Playground;
import back.Tiles;

import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame {
    public static final int WIDTH = 990;//984;
    public static final int HEIGHT = 800;

    public Frame() {
        super();
        init();
    }

    private void init() {
        add(new Panel());

        setResizable(false);
        pack();

        setTitle("BoloBall");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Frame frame = new Frame();
            frame.setVisible(true);
        });
    }
}
