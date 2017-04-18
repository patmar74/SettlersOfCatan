package boardClasses;

/**
 * @author Patrick Martin
 * The BoardGrid class will hold all the GridNodes in a 2D array and be able to return them.
 *
 */
public class BoardGrid {
    GridNode[][] grid = new GridNode[12][11]; // A 11 x 12 grid that a max of 10 in the x and a max of 11 in the y

    /**
     * Constructor assigns GridPoints to all array members
     */
    public BoardGrid(){
        int maxY = 11;// Maximum y point in grid
        int maxX = 10;// Maximum x point in grid
        for(int y = 0;y<=maxY; y++){
            for(int x =0;x<=maxX; x++){
                grid[y][x] = new GridNode();
            }
        }
    }

    public GridNode[][] getGrid(){
        return grid;
    }

}
