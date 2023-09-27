package HelloWorld;

import java.util.Random;

public class OceanMap {
    private boolean[][] myGrid;
    private int dimensions;
    private Random rand;

    public OceanMap(int dimensions) {
        this.dimensions = dimensions;
        this.myGrid = new boolean[dimensions][dimensions];
        this.rand = new Random();

        // Initialize the grid with empty cells (blue sea)
        for (int x = 0; x < dimensions; x++) {
            for (int y = 0; y < dimensions; y++) {
                myGrid[x][y] = false;
            }
        }

        // Randomly place islands (true) on the grid
        placeIslands(10); // You can change the number of islands
    }

    public boolean[][] getMap() {
        return myGrid;
    }

    public int getDimensions() {
        return dimensions;
    }

    public boolean isIsland(int x, int y) {
        if (x >= 0 && x < dimensions && y >= 0 && y < dimensions) {
            return myGrid[x][y];
        }
        return false;
    }

    private void placeIslands(int numIslands) {
        for (int i = 0; i < numIslands; i++) {
            int x, y;
            do {
                x = rand.nextInt(dimensions);
                y = rand.nextInt(dimensions);
            } while (myGrid[x][y]); // Keep generating until an empty cell is found

            myGrid[x][y] = true;
        }
    }
}
