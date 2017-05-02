package boardClasses;

/**
 * @author Patrick Martin
 * The BoardGrid class will hold all the GridNodes in a 2D array and be able to return them.
 *
 */
public class BoardGrid {
    private int maxX;
    private int maxY;
    private GridNode[][] grid = new GridNode[12][11]; // A 11 x 12 grid that a max of 10 in the x and a max of 11 in the y

    /**
     * Constructor assigns GridPoints to all array members
     */
    public BoardGrid(){
        maxY = 11;// Maximum y point in grid
        maxX = 10;// Maximum x point in grid
        for(int y = 0;y<=maxY; y++){
            for(int x =0;x<=maxX; x++){
                grid[y][x] = new GridNode(x,y);
            }
        }
    }

    public GridNode[][] getGrid(){
        return grid;
    }

    /**
     * Get grid node at the x,y position
     * @param x
     * @param y
     * @return The GridNode if x,y is within the bounds of the BoardGrid.
     * null if x,y is out of the bounds of the BoardGrid
     *
     */
    public GridNode getNode(int x, int y){
        if(y<0 || y > maxY){
            return null;
        }else if(x<0 || x > maxX ){
            return null;
        }
        return grid[y][x];
    }

}
