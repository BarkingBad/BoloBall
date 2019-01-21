package back;

import front.GamePanel;

import static back.Playground.SIZE_X;

public class Player {
    private Colour colour;
    private int score;
    private boolean availableBalls[];
    public boolean canContinue = true;
    private int column;

    public Player(Colour colour) {
        this.colour = colour;
        this.score = 0;
        this.availableBalls = new boolean[SIZE_X];
        this.column = 0;
        for(int i = 0; i <  /*3*/ SIZE_X; i++) {
            availableBalls[i] = true;
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

    public void update(Playground playground, GamePanel gamePanel) {
        this.prepareView(playground);
    }

    public void addScore(int delta) {
        this.score += delta;
    }

    public boolean canDropTheBall(Playground playground, int column) {
        return isAvailable(column) && !playground.isLaneStuck(column);
    }

    public void keyPressed(int i, Playground playground, GamePanel gamePanel) {
        if (i == -1) {
            goLeft();
        }

        if (i == 1) {
            goRight();
        }

        if (i == 0) {
            if(this.canDropTheBall(playground, column)) {
                this.markBallAsUsed(column);
                playground.updateGrid(gamePanel, this.column, 1, End.NO);
            }

        }
    }


    public void prepareView(Playground p) {

        for(int i=0; i<SIZE_X; i++) {
            p.setTileAt(Tiles.UPPER_FIELD, i, 0);
            if(availableBalls[i]) {
                p.setTileAt(this.getColour().getBall(), i, 1);
            }
            else {
                p.setTileAt(Tiles.EMPTY, i, 1);
            }
        }
        p.setTileAt(this.getColour().getSprite(), column, 0);
    }

    private boolean isAvailable(int i) {
        return this.availableBalls[i];
    }

    private void markBallAsUsed(int i) {
        this.availableBalls[i] = false;
    }

    public void canMove(Playground p) {
        canContinue = recursiveBallCheck(p, SIZE_X - 1);
    }

    private boolean recursiveBallCheck(Playground p, int n) {
        if(n == 0) {
            return canDropTheBall(p, n);
        }
        else {
            return recursiveBallCheck(p, n-1) || canDropTheBall(p, n);
        }
    }
}