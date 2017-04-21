package boardClasses;

/**
 * GameBoard holds all Tiles, and the GridNodes
 * This will be used to access all aspects of the board
 * @author Patrick Martin & Joey Seliga
 */
public class GameBoard {
    private BoardGrid grid;
    private Tiles gameTiles;

    public GameBoard(){
        grid = new BoardGrid();
        gameTiles = new Tiles(grid);
    }
    public BoardGrid getGrid() {
        return grid;
    }

    public Tiles getTiles(){
        return gameTiles;
    }

}
