package boardClasses;

import resourceClasses.ResourceType;

import java.util.ArrayList;
import java.awt.*;
import java.util.Random;

/**
 * @author Patrick, Joey, Tyler, Misha
 */

public class Tiles {

    private ArrayList<Tile> gameTiles = new ArrayList<>();
    private BoardGrid grid;

    /**
     * Constructor Creates all game tiles, and assigns them tokens and reference coordinates
     */
    public Tiles(BoardGrid grid){
        createTiles();
        shuffleTiles();
        setTileCoordinates();
        assignTokens(createShuffledCircleTokens()); //assigns tokens created by createShuffledTokens
        this.grid = grid;
        setNodesToTiles();
    }

    /**
     * Get ArrayList of all game tiles
     * @return ArrayList<boardClasses.Tile> All game tiles
     */
    public ArrayList<Tile> getTilesArray(){
        return gameTiles;
    }
    /**
     * Creates new tile objects and adds them to the gameTiles ArrayList
     */
    private void createTiles(){
        initTilesOfResource(ResourceType.DESERT,1); // 1 desert tile
        initTilesOfResource(ResourceType.BRICK, 3); // 3 brick tiles
        initTilesOfResource(ResourceType.ORE, 3); // 3 ore tiles
        initTilesOfResource(ResourceType.LUMBER, 4); // 4 lumber tiles
        initTilesOfResource(ResourceType.WOOL, 4); // 4 wool tiles
        initTilesOfResource(ResourceType.WHEAT, 4); // 4 wheat tiles
    }

    /**
     * Creates a specified quantity of tiles for a specific resource type
     * If the tile is a desert, the robber is placed.
     * @param type ResourceType
     * @param qty int Quantity desired
     */
    private void initTilesOfResource(ResourceType type, int qty){
        for(int i = 0;i<qty; i++){
            Tile currentTile = new Tile(type);
            // If tile is a desert then place the robber
            if(type.equals(ResourceType.DESERT)){
                currentTile.setHasRobber(true);
            }
            gameTiles.add(currentTile);
        }
    }

    /**
     * Shuffles all of the tiles in gameTiles
     */
    private void shuffleTiles(){
        ArrayList<Tile> shuffledTiles = new ArrayList<>();
        int i = 0; // holds index for while loop
        int indexOfShuffledTile = 0;
        Random rdm = new Random();
        // continue adding tiles to shuffled tiles until there are no more tiles left to shuffle.
        while(gameTiles.size()>0){
            //gets random integer between 0 and the size of the arraylist (non-inclusive)
            i = rdm.nextInt(gameTiles.size());
            Tile t = gameTiles.remove(i);
            t.setIndex(indexOfShuffledTile);
            shuffledTiles.add(t);// removes a tile at index i and adds the tile to the shuffledTiles ArrayList
            indexOfShuffledTile++;
        }
        gameTiles = shuffledTiles;
    }

    /**
     * Sets reference coordinate for all tiles
     * First tile is at [3,0]
     * Max boardClasses.Tile Count per row is as follows 3,4,5,4,3
     */
    private void setTileCoordinates(){
        Point currentPoint = new Point(0,0);
        int rowMinX = 3; // leftmost value for current row
        int rowMinTiles = 3; // Minimum number of tiles per row
        int rowMaxTiles = 5; // Maximum number of tiles per row
        int tileIndex = 0; // Index of the current tile
        int y = currentPoint.y;
        // Loop through each row, progressing to the next until the maximum number of tiles has been reached
        // ie) the board starts decreasing in size after the row of 5 is laid out
        for(int tilesPerRow = rowMinTiles; tilesPerRow<=rowMaxTiles; tilesPerRow++){
            currentPoint.setLocation(rowMinX,y);
            int tilesInRowNow = 0; // Holds number of tiles currently in row
            // Loop until enough tiles are in the row
            while(tilesInRowNow<tilesPerRow){
                gameTiles.get(tileIndex).setGridPointReference(new Point(currentPoint)); // set grid reference to current point
                currentPoint.x += 2; // each tile is 2 to the right
                tileIndex += 1;
                tilesInRowNow += 1;
            }

            y += 2; // next row starts 2 below the current row
            rowMinX -= 1; // next row starts 1 before the current row
        }
        rowMinX += 2; // this undoes the increment from the last iteration of the first for loop
                      // and it starts the next loop at the next tile location
        // does the same as above for loop but this time in reverse direction
        // it is rowMaxTiles -1 since the row of 5 was already made in the above for loop
        for(int tilesPerRow = rowMaxTiles-1 ; tilesPerRow>=rowMinTiles; tilesPerRow--){
            currentPoint.setLocation(rowMinX,y);
            int tilesInRowNow = 0; // Holds number of tiles currently in row
            // Loop until enough tiles are in the row
            while(tilesInRowNow<tilesPerRow){
                gameTiles.get(tileIndex).setGridPointReference(new Point(currentPoint));
                currentPoint.x += 2; // each tile is 2 to the right
                tileIndex += 1;
                tilesInRowNow += 1;
            }
            y += 2; // next row starts 2 below the current row
            rowMinX += 1; // next row starts 1 after the current row
        }

    }

    /**
     * Uses boardClasses.CircleToken ArrayList generated by Token Creator and shuffles the the tokens
     * @return shuffledTokens Arraylist<boardClasses.CircleToken>
     */
    private ArrayList<CircleToken> createShuffledCircleTokens(){
        TokenCreator creator = new TokenCreator();
        ArrayList<CircleToken> shuffledTokens = new ArrayList<>();
        ArrayList<CircleToken> tokens = creator.getArrayList(); // creates all tokens
        int i = 0; // holds index for while loop
        Random rdm = new Random();
        // continue adding tokens to shuffled tokens until there are no more tokens left to shuffle.
        while(tokens.size()>0){
            i = rdm.nextInt(tokens.size()); //gets random integer between 0 and the size of the arraylist (non-inclusive)
            shuffledTokens.add(tokens.remove(i));// removes a token at index i and adds the token to the shuffledTokens ArrayList
        }
        return shuffledTokens;
    }

    /**
     * Assigns CircleTokens to All boardClasses.Tiles
     * It assigns a token the desert tile with values of char = ' ' and  number  = ""
     * @param tokens Arraylist<boardClasses.CircleToken> All circle tokens for the game
     */
    private void assignTokens(ArrayList<CircleToken> tokens){
        int i = 0; // Used to hold the index of the circle token being assigned
        for(Tile myTile: gameTiles){


            if((myTile.getResource().equals(ResourceType.DESERT))){
                myTile.setToken(new CircleToken(' ',0)); // sets a token for the desert that will never be used
                // this is purely to avoid a NullPointerException
            } else{ // if myTile is not a Desert tile then assign the current token to the tile
                CircleToken myToken = tokens.remove(0);
                myTile.setToken(myToken);
            }
        }
    }

    public Tile getTile(int x){
        return gameTiles.get(x);
    }

    // Loops through all tiles and assigns the appropriate grid nodes
    private void setNodesToTiles(){
        for(Tile myTile: gameTiles){
            myTile.setNodesToTile(grid);
        }
    }


}