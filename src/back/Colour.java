package back;

public enum Colour {
    BLUE(0, Tiles.BALL_BLUE),
    RED(1, Tiles.BALL_RED);

    private int code;
    private Tiles tile;

    Colour(int code, Tiles tile) {
        this.code = code;
        this.tile = tile;
    }

    public Tiles getTile() {
        return tile;
    }
}
