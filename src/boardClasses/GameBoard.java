package boardClasses;

import resourceClasses.ResourceType;

import java.awt.*;
import java.util.ArrayList;

/**
 * GameBoard holds all Tiles, and the GridNodes
 * This will be used to access all aspects of the board
 * @author Patrick Martin & Joey Seliga
 */
public class GameBoard {
    private BoardGrid grid;
    private Tiles gameTiles;
    private int indexOfRobber; // The index of the robber's current position.

    /**
     * Constructor
     * Creates a new GameBoard object that encapsulates the boardGrid and the gameTiles.
     * The constructor also finds the location of the robber and sets indexOfRobber accordingly.
     */
    public GameBoard(){
        grid = new BoardGrid();
        gameTiles = new Tiles(grid);
        indexOfRobber = getIndexOfDesert();

    }
    public BoardGrid getGrid() {
        return grid;
    }

    /**
     * Gets the gridNode object at coordinate x,y
     * @param x x Coordinate of the grid
     * @param y y Coordinate of the grid
     * @return GridNode reference at (x,y)
     */
    public GridNode getGridNode(int x, int y){
        return grid.getNode(x,y);
    }

    /**
     * Gets the gridNode object at Point pt
     * @param pt The Point where the GridNode is located
     * @return The GridNode at the desired point.
     */
    public GridNode getGridNode(Point pt){return grid.getNode(pt.x,pt.y);}

    public Tiles getTiles(){
        return gameTiles;
    }

    /**
     * Finds all tiles who have a token for the dice roll that is passed in.
     * @param diceRoll The Value of the dice roll
     * @return ArrayList<Tile> All Tiles who have a token for the dice roll that is passed in
     */
    public ArrayList<Tile> getTilesWithDiceRoll(int diceRoll){
        //tilesWithDiceRoll will hold all Tiles that have a token with the input diceRoll value
        ArrayList<Tile> tilesWithDiceRoll = new ArrayList<>();
        //Loop through all tiles and add the tiles who have a token that matches the dice roll to tilesWithDiceRoll ArrayList
        for(Tile myTile:gameTiles.getTilesArray()){
            if(myTile.getToken().getNumber() == diceRoll){
                tilesWithDiceRoll.add(myTile);
            }
        }
        return tilesWithDiceRoll;
    }

    /**
     * Gets the index of the desert tile in the gameTiles ArrayList.
     * This is to find the starting position of the robber so that the GameBoard can keep track of it's position
     * for the remainder of the game.
     * @return The index of the desert tile in gameTiles
     */
    private int getIndexOfDesert(){
        int indexOfDesert = 0;
        // Loop through the Tiles Array in gameTiles object until the Tile resource is the desert
        while(!gameTiles.getTile(indexOfDesert).getResource().equals(ResourceType.DESERT)){
            indexOfDesert++;
        }
        return indexOfDesert;
    }

    /**
     * Moves the robber from it's current position to a different location.
     * @param tileIndex The index of the tile that the robber will be moved to.
     */
    public void moveRobber(int tileIndex){
        // Remove robber from the current tile that has the robber
        gameTiles.getTile(indexOfRobber).setHasRobber(false);
        gameTiles.getTile(tileIndex).setHasRobber(true);
        indexOfRobber = tileIndex;
    }

    //ToDo Add method to get an ArrayList af all tiles attached to a specific node
    //ToDo Change GridNode to extend java.awt.Point

}
