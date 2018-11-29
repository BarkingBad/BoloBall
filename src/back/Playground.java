package back;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;



public class Playground {
    public static final int SIZE_Y = 19;
    public static final int SIZE_X = 29;
    public static final int FIELD = SIZE_X * SIZE_Y;
    private Tiles [][] grid = new Tiles [SIZE_X][SIZE_Y];
    private boolean stuckLanes[] = new boolean[SIZE_X];
    private List<Pair<Tiles, Integer>> amountOfTiles = new LinkedList<>();
    private List<Point> teleports = new LinkedList<>();
    private Random random = new Random();



    public Playground() {

        associateCounts();
        fillGrid();
        for(int i = 0; i<SIZE_X; i++) {
            stuckLanes[i] = false;
        }

    }

    private void associateCounts() {
        amountOfTiles.add(new Pair<>(Tiles.ARROW_LEFT, 39));
        amountOfTiles.add(new Pair<>(Tiles.ARROW_RIGHT, 38));
        amountOfTiles.add(new Pair<>(Tiles.BLOCK, 20));
        amountOfTiles.add(new Pair<>(Tiles.POINTS, 8));
        amountOfTiles.add(new Pair<>(Tiles.TELEPORT, 5));

    }

    private void fillGrid() {
        for(int x = 0; x < SIZE_X; x++) {
            grid[x][0] = Tiles.UPPER_FIELD;
            grid[x][SIZE_Y-1] = Tiles.EMPTY;
        }
        for(int x = 0; x < SIZE_X; x++) {
            for(int y = 1; y <= 2; y++) {
                grid[x][y] = Tiles.EMPTY;
            }
        }

        for(int x = 0; x < SIZE_X; x++) {
            for(int y = 3; y < SIZE_Y-1; y++) {
                grid[x][y] = associateTile();
                if(grid[x][y] == Tiles.TELEPORT)
                    teleports.add(new Point(x, y));
            }
        }
    }

    private Tiles associateTile() {
        double r = random.nextDouble();
        double sum = 0;
        for (Pair<Tiles, Integer> item : amountOfTiles) {

            sum = sum + (double) item.getValue() / FIELD;
            if (sum >= r) {
                return item.getKey();
            }
        }
        return Tiles.EMPTY;
    }

    public void updateGrid(Player player, int column, int row) {
        switch (grid[column][row+1]) {
            case EMPTY:
                freeFall(player , column, row);
                break;
            case BALL_RED:
                stuck(player , column, row);
                break;
            case BALL_BLUE:
                stuck(player , column, row);
                break;
            case ARROW_LEFT:
                turnBall(player , column, row);
                break;
            case ARROW_RIGHT:
                turnBall(player , column, row);
                break;
            case BLOCK:
                stuck(player , column, row);
                break;
            case TELEPORT:
                teleport(player , column, row);
                break;
            case POINTS:
                addScore(player , column, row);
                break;
        }
    }

    private void addScore(Player player, int column, int row) {
        player.addScore(10);
        freeFall(player, column, row);
    }

    private void teleport(Player player, int column, int row) {
        List<Point> tmp = teleports.subList(0, teleports.size());
        tmp.remove(new Point(column, row));
        int r = random.nextInt(tmp.size());
        grid[column][row] = Tiles.TELEPORT;
        grid[tmp.get(r).getX()][tmp.get(r).getY()] = player.getColour().getTile();
        updateGrid(player, tmp.get(r).getX(), tmp.get(r).getY() );
    }

    private void turnBall(Player player, int column, int row) {

    }

    private void stuck(Player player, int column, int row) {
        grid[column][row] = player.getColour().getTile();
        if(row == 2) {
            stuckLanes[column] = true;
        }
    }

    private void freeFall(Player player, int column, int row) {
        grid[column][row] = Tiles.EMPTY;
        grid[column][row+1] = player.getColour().getTile();
        updateGrid(player, column, row+1);
    }

    public boolean[] getStuckLanes() {
        return stuckLanes;
    }
    
}
