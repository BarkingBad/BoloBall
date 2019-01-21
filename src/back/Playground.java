package back;

import front.GamePanel;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;



public class Playground {
    public static final int SIZE_Y = 19;
    public static final int SIZE_X = 29;
    public static final int FIELD = SIZE_X * SIZE_Y;
    private Tiles [][] grid = new Tiles [SIZE_X][SIZE_Y];
    private Jukebox jukebox = new Jukebox();


    private boolean stuckLanes[] = new boolean[SIZE_X];
    private List<Pair<Tiles, Integer>> amountOfTiles = new LinkedList<>();
    private List<Point> teleports = new LinkedList<>();
    private List<Point> temporaryPoints = new LinkedList<>();
    private Random random = new Random();



    public Playground() {
        jukebox.start();
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

    public void swipeTheBall(GamePanel gamePanel, int column, End end) {
        int direction = gamePanel.getCurrentPlayer().getColour() == Colour.RED ? 1 : -1;

        while(column % (SIZE_X-1) != 0) {
            this.setTileAt(Tiles.EMPTY, column, SIZE_Y-1);
            this.setTileAt(gamePanel.getCurrentPlayer().getColour().getBall(), column+=direction, SIZE_Y-1);
            jukebox.push(Sounds.POINTS_INCREASE);
            gamePanel.sleepAndPaint(100);
        }
        this.setTileAt(Tiles.EMPTY, column, SIZE_Y-1);
        gamePanel.getCurrentPlayer().addScore(60);
        jukebox.push(Sounds.FULL_SCORE);
        gamePanel.getCurrentPlayer().canMove(this);
        if(!end.getState()) {
            gamePanel.changePlayer();
        }
    }

    public void updateGrid(GamePanel gamePanel, int column, int row, End end) {
        gamePanel.sleepAndPaint(500);
        if(row == SIZE_Y - 1) {
            swipeTheBall(gamePanel, column, end);
        } else {
            switch (grid[column][row + 1]) {
                case EMPTY:
                    freeFall(gamePanel, column, row, end);
                    break;
                case BALL_RED:
                    stuck(gamePanel, column, row, end);
                    break;
                case BALL_GREEN:
                    stuck(gamePanel, column, row, end);
                    break;
                case ARROW_LEFT:
                    turnBall(gamePanel, column, row, end);
                    break;
                case ARROW_RIGHT:
                    turnBall(gamePanel, column, row, end);
                    break;
                case BLOCK:
                    stuck(gamePanel, column, row, end);
                    break;
                case TELEPORT:
                    fallInTeleport(gamePanel, column, row, end);
                    break;
                case POINTS:
                    addScore(gamePanel, column, row, end);
                    break;
            }
        }
    }

    private void addScore(GamePanel gamePanel, int column, int row, End end) {
        gamePanel.getCurrentPlayer().addScore(10);
        jukebox.push(Sounds.POINTS_INCREASE);
        freeFall(gamePanel, column, row, end);
    }

    private void fallInTeleport(GamePanel gamePanel, int column, int row, End end) {
        grid[column][row] = Tiles.EMPTY;
        grid[column][row+1] = gamePanel.getCurrentPlayer().getColour().getBall();
        teleport(gamePanel, column, row+1, end);
    }

    private void teleport(GamePanel gamePanel, int column, int row, End end) {
        List<Point> tmp = new LinkedList<>(teleports);
        tmp.remove(new Point(column, row));
        int r = random.nextInt(teleports.size()-1);
        jukebox.push(Sounds.TELEPORT);
        gamePanel.sleepAndPaint(500);
        grid[tmp.get(r).getX()][tmp.get(r).getY()] = gamePanel.getCurrentPlayer().getColour().getBall();
        if( grid[tmp.get(r).getX()][tmp.get(r).getY()+1] == Tiles.BALL_GREEN  ||
            grid[tmp.get(r).getX()][tmp.get(r).getY()+1] == Tiles.BALL_RED  ||
            grid[tmp.get(r).getX()][tmp.get(r).getY()+1] == Tiles.BLOCK ) {
            teleports.remove(new Point(tmp.get(r).getX(), tmp.get(r).getY()));
        }
        tooFewTeleports();
        updateGrid(gamePanel, tmp.get(r).getX(), tmp.get(r).getY(), end);
    }

    private void turnBall(GamePanel gamePanel, int column, int row, End end) {

        if(grid[column][row+1] == Tiles.ARROW_LEFT && column > 0) {
            pushAside(gamePanel, column, row, end, -1);
        } else if(grid[column][row+1] == Tiles.ARROW_RIGHT && column < SIZE_X - 1) {
            pushAside(gamePanel, column, row, end, 1);
        } else {
            stuck(gamePanel, column, row, end);
        }
    }

    private void pushAside(GamePanel gamePanel, int column, int row, End end, int direction) {
        if(!(grid[column + direction][row] == Tiles.EMPTY || grid[column + direction][row] == Tiles.POINTS || grid[column + direction][row] == Tiles.TELEPORT)) {
            stuck(gamePanel, column, row, end);
        }
        else{
            if (grid[column + direction][row] == Tiles.POINTS) {
                gamePanel.getCurrentPlayer().addScore(10);
            }
            grid[column][row + 1] = Tiles.ARROW_DOWN;
            grid[column][row] = Tiles.EMPTY;
            grid[column + direction][row] = gamePanel.getCurrentPlayer().getColour().getBall();
            if (teleports.contains(new Point(column + direction, row))) {
                grid[column][row + 1] = direction == -1 ? Tiles.ARROW_RIGHT : Tiles.ARROW_LEFT;
                teleport(gamePanel, column + direction, row, end);
            }
            else if ((grid[column + direction][row + 1] == Tiles.BALL_GREEN || grid[column + direction][row + 1] == Tiles.BALL_RED) && (column > 1 && column < SIZE_X-1) && grid[column + 2*direction][row] == Tiles.EMPTY) {
                gamePanel.sleepAndPaint(500);
                grid[column][row + 1] = direction == -1 ? Tiles.ARROW_RIGHT : Tiles.ARROW_LEFT;
                grid[column + direction][row] = Tiles.EMPTY;
                grid[column + 2*direction][row] = gamePanel.getCurrentPlayer().getColour().getBall();
                gamePanel.sleepAndPaint(500);

                updateGrid(gamePanel, column + 2*direction, row, end);
            } else {
                gamePanel.sleepAndPaint(500);
                grid[column][row + 1] = direction == -1 ? Tiles.ARROW_RIGHT : Tiles.ARROW_LEFT;
                updateGrid(gamePanel, column + direction, row, end);
            }
        }
    }

    private void stuck(GamePanel gamePanel, int column, int row, End end) {
        grid[column][row] = gamePanel.getCurrentPlayer().getColour().getBall();
        temporaryPoints.add(new Point(column, row));
        teleports.remove(new Point(column, row));
        gamePanel.getCurrentPlayer().addScore((row-1)*2);
        if(row == 2) {
            stuckLanes[column] = true;
        }
        gamePanel.getPlayers()[0].canMove(this);
        gamePanel.getPlayers()[1].canMove(this);
        if(!end.getState()) {
            gamePanel.changePlayer();
        }
    }

    private void freeFall(GamePanel gamePanel, int column, int row, End end) {
        grid[column][row] = Tiles.EMPTY;
        grid[column][row+1] = gamePanel.getCurrentPlayer().getColour().getBall();
        for(Point p : teleports) {
            grid[p.getX()][p.getY()] = Tiles.TELEPORT;
        }
        jukebox.push(Sounds.FALLING);
        updateGrid(gamePanel, column, row+1, end);
    }

    public boolean isLaneStuck(int column) {
        return stuckLanes[column];
    }

    public Tiles[][] getGrid() {
        return grid;
    }

    public Tiles getTileAt(int x, int y) throws ArrayIndexOutOfBoundsException {
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
            teleports.clear();
        }
    }

    public List<Point> getTemporaryPoints() {
        return temporaryPoints;
    }

    public void finishTheGame(GamePanel gamePanel) {
        for(int j = 2; j<SIZE_Y-1; j++) {
            for(int i = 0; i<SIZE_X; i++) {
                if(grid[i][j] == Tiles.BLOCK) {
                    grid[i][j] = Tiles.EMPTY;
                    gamePanel.sleepAndPaint(50);
                }
            }
        }
        for(int j = SIZE_Y - 2; j>1; j--) {
            for(int i = 0; i<SIZE_X; i++) {
                if(getTileAt(i, j) == Tiles.BALL_GREEN) {
                    gamePanel.getPlayers()[0].addScore( (-2)*(j-1) );
                    gamePanel.setCurrentTurn(0);
                    temporaryPoints.remove(new Point(i, j));
                    updateGrid(gamePanel, i, j, End.YES);
                }
                if(getTileAt(i, j) == Tiles.BALL_RED) {
                    gamePanel.getPlayers()[1].addScore( (-2)*(j-1) );
                    gamePanel.setCurrentTurn(1);
                    temporaryPoints.remove(new Point(i, j));
                    updateGrid(gamePanel, i, j, End.YES);
                }
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gamePanel.getFrame().dashboard(gamePanel.getPlayers()[0].getScore(), gamePanel.getPlayers()[1].getScore());
    }
}
