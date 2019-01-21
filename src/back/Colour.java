package back;


public enum Colour {

    GREEN(0, Tiles.BALL_GREEN, Tiles.SPRITE_GREEN, "<html><center><font face=\"Consolas\" color=#00ff00 size=\"20\" >Green player has run <br> out of balls!</font></center></html>"),
    RED(1, Tiles.BALL_RED, Tiles.SPRITE_RED, "<html><center><font face=\"Consolas\" color=#ff0000 size=\"20\">Red player has run <br> out of balls!</font></center></html>");

    private int code;
    private Tiles ball;
    private Tiles sprite;
    Colour(int code, Tiles ball, Tiles sprite, String text) {
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
