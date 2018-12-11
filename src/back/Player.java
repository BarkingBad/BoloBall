package back;

import front.Panel;

import java.awt.event.KeyEvent;

import static back.Playground.SIZE_X;

public class Player {
    private Colour colour;
    private int score;
    private boolean availableBalls[];
    private boolean stuckBalls[];
    private int column;

    public Player(Colour colour) {
        this.colour = colour;
        this.score = 0;
        this.availableBalls = new boolean[SIZE_X];
        this.stuckBalls = new boolean[SIZE_X];
        this.column = 0;
        for(int i = 0; i < SIZE_X; i++) {
            availableBalls[i] = true;
            stuckBalls[i] = false;
        }
    }

    public Colour getColour() {
        return colour;

    }

    public int getScore() {
        return score;
    }

    public String getScoreToString() {
        return "" + score;
    }

    public boolean[] getAvaiableBalls() {
        return availableBalls;
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

    public void update(Playground playground) {
        this.prepareView(playground);
    }

    public void addScore(int delta) {
        this.score += delta;
    }
    public boolean canDropTheBall() {
        return availableBalls[column] && !stuckBalls[column];
    }

    public void updateStuckBalls(boolean[] table) {
        for(int i = 0; i<SIZE_X; i++) {
            if(availableBalls[i] == true && table[i] == true) {
                stuckBalls[i] = true;
            }
        }
    }


    public void keyPressed(KeyEvent e, Playground p, Panel panel) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            goLeft();
        }

        if (key == KeyEvent.VK_RIGHT) {
            goRight();
        }

        if (key == KeyEvent.VK_DOWN) {
            if(this.isAvailable(column)) {
                this.markBallAsUsed(column);
                p.updateGrid(panel,this, this.column, 1);
            }

        }
    }


    public void prepareView(Playground p) {


        for(int i=0; i<SIZE_X; i++) {
            p.setTileAt(Tiles.UPPER_FIELD, i, 0);
            if(availableBalls[i]) {
                p.setTileAt(this.getColour().getBall(), i, 1);
            }
        }
        p.setTileAt(this.getColour().getSprite(), column, 0);
    }

    public boolean isAvailable(int i) {
        return this.availableBalls[i];
    }

    public void markBallAsUsed(int i) {
        this.availableBalls[i] = false;
    }
}