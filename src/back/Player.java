package back;

import static back.Playground.SIZE_X;

public class Player {
    private Colour colour;
    private int score;
    private boolean avaiableBalls[];
    private int position;

    public Player(Colour colour, int score) {
        this.colour = colour;
        this.score = score;
        this.avaiableBalls = new boolean[SIZE_X];
        this.position = 0;
    }

    public Colour getColour() {
        return colour;

    }

    public int getScore() {
        return score;
    }

    public boolean[] getAvaiableBalls() {
        return avaiableBalls;
    }

    public void goLeft() {
        if(position > 0)
            position = position - 1;
    }

    public void goRight() {
        if(position < SIZE_X - 1)
            position = position + 1;
    }

    public void dropTheBall() {
        if(avaiableBalls[position] && grid[position][1] == Tiles.EMPTY)
            // droping the ball
    }
}
