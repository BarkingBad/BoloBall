package front;

import back.Colour;
import back.Player;
import back.Playground;

import java.awt.*;


public class GameLoop {
    public static final int WIDTH = 984;
    public static final int HEIGHT = 800;

    private Playground playground;
    private Player[] players;

    private GameLoop() {
        init();

    }
    private void init() {
        playground = new Playground();
        players = new Player[]{new Player(Colour.RED), new Player(Colour.GREEN)};


        EventQueue.invokeLater(() -> {
            Frame frame = new Frame(playground);
            frame.setVisible(true);
        });

        loop();
    }

    private void loop() {
//        while(true) {
//
//        }

        players[0].updateStuckBalls(playground.getStuckLanes());
        if(players[0].canDropTheBall()) {
            playground.updateGrid(players[0], players[0].getColumn(), 1);
        }

    }

    public static void main(String[] args) {
        GameLoop game = new GameLoop();
    }

}