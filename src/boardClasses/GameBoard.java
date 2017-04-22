package boardClasses;

import java.util.ArrayList;

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
}
