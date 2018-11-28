package back;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;



public class Playground {
    public static final int SIZE_Y = 19;
    public static final int SIZE_X = 29;
    public static final int FIELD = SIZE_X * SIZE_Y;
    public static final Tiles [][] grid = new Tiles [SIZE_X][SIZE_Y];
    private List<Pair<Tiles, Integer>> amountOfTiles = new LinkedList<>();
    private Random random = new Random();



    public Playground() {

        associateCounts();
        fillGrid();
        System.out.println(Arrays.deepToString(grid));
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
}
