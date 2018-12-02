package back;


import java.util.LinkedList;

public enum Colour {

    GREEN(0, Tiles.BALL_GREEN, Tiles.SPRITE_GREEN ),
    RED(1, Tiles.BALL_RED, Tiles.SPRITE_RED);

    private int code;
    private Tiles ball;
    private Tiles sprite;

    Colour(int code, Tiles ball, Tiles sprite) {
        this.code = code;
        this.ball = ball;
        this.sprite = sprite;
    }

    public Tiles getBall() {
        return ball;
    }

    public Tiles getSprite() {
        return sprite;
    }
}
