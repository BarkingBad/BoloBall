package front;

import back.Colour;
import back.Player;
import back.Playground;

import java.awt.*;


public class GameLoop {
    private long window;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Playground playground;
    private Player[] players;

    public void run() {


    }

    private void init() {
        playground = new Playground();
        players = new Player[]{new Player(Colour.RED), new Player(Colour.BLUE)};



    }

    private void loop() {

        players[0].updateStuckBalls(playground.getStuckLanes());
        if(players[0].canDropTheBall()) {
            playground.updateGrid(players[0], players[0].getColumn(), 1);
        }

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Window window = new Window();
                window.setVisible(true);
            }
        });
    }

}