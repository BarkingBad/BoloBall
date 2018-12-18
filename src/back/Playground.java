package back;

import front.Frame;
import front.Panel;

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

        tooFewTeleports();
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

    private void sleepAndPaint(Panel panel) {
        panel.paintImmediately(0,0, Frame.WIDTH, Frame.HEIGHT);
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e){

        }
    }

    public void swipeTheBall(Panel panel, Player player, int column) {
        stuck(panel,player,column,SIZE_Y-1);
    }

    public void updateGrid(Panel panel, Player player, int column, int row) {
        sleepAndPaint(panel);
        if(row == SIZE_Y - 1) {
            swipeTheBall(panel, player, column);
        } else {
            switch (grid[column][row + 1]) {
                case EMPTY:
                    freeFall(panel, player, column, row);
                    break;
                case BALL_RED:
                    stuck(panel, player, column, row);
                    break;
                case BALL_GREEN:
                    stuck(panel, player, column, row);
                    break;
                case ARROW_LEFT:
                    turnBall(panel, player, column, row);
                    break;
                case ARROW_RIGHT:
                    turnBall(panel, player, column, row);
                    break;
                case BLOCK:
                    stuck(panel, player, column, row);
                    break;
                case TELEPORT:
                    teleport(panel, player, column, row);
                    break;
                case POINTS:
                    addScore(panel, player, column, row);
                    break;
            }
        }
    }

    private void addScore(Panel panel, Player player, int column, int row) {
        player.addScore(10);
        freeFall(panel, player, column, row);
    }

    private void teleport(Panel panel, Player player, int column, int row) {
        List<Point> tmp = teleports.subList(0, teleports.size());
        tmp.remove(new Point(column, row)); //TODO: bład z losowaniem samego siebie
        int r = random.nextInt(tmp.size());
        grid[column][row] = Tiles.EMPTY;
        grid[column][row+1] = player.getColour().getBall();
        sleepAndPaint(panel);
        grid[column][row+1] = Tiles.TELEPORT;
        grid[tmp.get(r).getX()][tmp.get(r).getY()] = player.getColour().getBall();
        updateGrid(panel, player, tmp.get(r).getX(), tmp.get(r).getY() );
        tooFewTeleports();
    }

    private void turnBall(Panel panel, Player player, int column, int row) {

        if(grid[column][row+1] == Tiles.ARROW_LEFT && column > 0 && (grid[column-1][row] == Tiles.EMPTY || grid[column-1][row] == Tiles.POINTS)) {
            if(grid[column-1][row] == Tiles.POINTS) {
                player.addScore(10);
            }
            grid[column][row+1] = Tiles.ARROW_DOWN;
            grid[column][row] = Tiles.EMPTY;
            grid[column-1][row] = player.getColour().getBall();
            sleepAndPaint(panel);
            grid[column][row+1] = Tiles.ARROW_RIGHT;
            if((grid[column-1][row+1] == Tiles.BALL_GREEN || grid[column-1][row+1] == Tiles.BALL_RED) && column > 1) {
                grid[column - 1][row] = Tiles.EMPTY;
                grid[column - 2][row] = player.getColour().getBall();
                sleepAndPaint(panel);

                updateGrid(panel, player, column-2, row);
            }

            updateGrid(panel, player, column-1, row);

        }

        else if(grid[column][row+1] == Tiles.ARROW_RIGHT && column < SIZE_X - 1 && (grid[column+1][row] == Tiles.EMPTY || grid[column+1][row] == Tiles.POINTS)) {
            if(grid[column+1][row] == Tiles.POINTS) {
                player.addScore(10);
            }
            grid[column][row+1] = Tiles.ARROW_DOWN;
            grid[column][row] = Tiles.EMPTY;
            grid[column+1][row] = player.getColour().getBall();
            sleepAndPaint(panel);
            grid[column][row+1] = Tiles.ARROW_LEFT;

            if((grid[column+1][row+1] == Tiles.BALL_GREEN || grid[column+1][row+1] == Tiles.BALL_RED) && column < SIZE_X - 2) {
                grid[column + 1][row] = Tiles.EMPTY;
                grid[column + 2][row] = player.getColour().getBall();
                sleepAndPaint(panel);

                updateGrid(panel, player, column+2, row);
            }
            updateGrid(panel, player, column+1, row);

        }
    }

    private void stuck(Panel panel, Player player, int column, int row) {
        grid[column][row] = player.getColour().getBall();
        if(row == 2) {
            stuckLanes[column] = true;
        }
    }

    private void freeFall(Panel panel, Player player, int column, int row) {
        grid[column][row] = Tiles.EMPTY;
        grid[column][row+1] = player.getColour().getBall();
        updateGrid(panel, player, column, row+1);

    }

    public boolean[] getStuckLanes() {
        return stuckLanes;
    }

    public Tiles[][] getGrid() {
        return grid;
    }

    public Tiles getBallAt(int x, int y) throws ArrayIndexOutOfBoundsException {
        if(x < 0 || x >= SIZE_X || y < 0 || y >= SIZE_Y)
            throw new ArrayIndexOutOfBoundsException();
        return grid[x][y];
    }

    public void setTileAt(Tiles t, int x, int y) throws ArrayIndexOutOfBoundsException {
        if(x < 0 || x >= SIZE_X || y < 0 || y >= SIZE_Y)
            throw new ArrayIndexOutOfBoundsException();
        grid[x][y] = t;
    }

    public void tooFewTeleports() {
        if(teleports.size() < 2) {
            for(Point p : teleports) {
                grid[p.getX()][p.getY()] = Tiles.EMPTY;
            }
        }
    }
}
