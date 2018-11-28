package back;

public enum Tiles {
    EMPTY(0),
    UPPER_FIELD(1),
    BALL(2),
    BALL_STUCK(3),
    ARROW_LEFT(4),
    ARROW_RIGHT(5),
    BLOCK(6),
    TELEPORT(7),
    POINTS(8);

    private int code;

    Tiles(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
