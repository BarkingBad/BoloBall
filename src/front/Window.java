package front;

import javax.swing.*;


public class Window extends JFrame {
    public Window() {
        add(new Surface());

        setTitle("BoloBall");
        setSize(GameLoop.WIDTH, GameLoop.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
