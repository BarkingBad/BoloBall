package back;

import static back.Playground.SIZE_X;

public class Player {
    private Colour colour;
    private int score;
    private boolean avilableBalls[];
    private boolean stuckBalls[];
    private int column;

    public Player(Colour colour) {
        this.colour = colour;
        this.score = 0;
        this.avilableBalls = new boolean[SIZE_X];
        this.stuckBalls = new boolean[SIZE_X];
        this.column = 0;
        for(int i = 0; i < SIZE_X; i++) {
            avilableBalls[i] = true;
            stuckBalls[i] = false;
        }
    }

    public Colour getColour() {
        return colour;

    }

    public int getScore() {
        return score;
    }

    public boolean[] getAvaiableBalls() {
        return avilableBalls;
    }

    public int getColumn() {
        return column;
    }

    public void goLeft() {
        if(column > 0)
            column = column - 1;
    }

    public void goRight() {
        if(column < SIZE_X - 1)
            column = column + 1;
    }

    public void addScore(int delta) {
        this.score += delta;
    }
    public boolean canDropTheBall() {
        return avilableBalls[column] && !stuckBalls[column];
    }

    public void updateStuckBalls(boolean[] table) {
        for(int i = 0; i<SIZE_X; i++) {
            if(avilableBalls[i] == true && table[i] == true) {
                stuckBalls[i] = true;
            }
        }
    }
}