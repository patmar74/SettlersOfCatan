package boardClasses;

import players.*;
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

    public int getIndexOfRobber() {
        return indexOfRobber;
    }

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
        assignHarbors();
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


    /**
     * Checks if a settlement is within 2 edges to a specified point.
     * This will be used to check if a settlement placement point is valid.
     * @param currentNode The possible settlement node that is being checked for valid placement.
     * @return True if a settlement has been found within 2 edges (ie 1 adjacent point)
     */
    public boolean checkSettlementNearPoint(GridNode currentNode){
        boolean settlementFound = false; // True if the settlement has been found
        int i = 0;
        // Gets the possible branches for the point
        RoadDirection[]  branches = DirectionDecider.getDirectionOptions(currentNode.getLocation());
        // Loop through all branches or until a settlement is found
        while(i < branches.length && !settlementFound){
            // sets nextPoint for the branch
            Point nextPoint = DirectionDecider.getNextPoint(branches[i],currentNode);
            GridNode nextNode = getGridNode(nextPoint);
            // check existance of gridNode prior to calling a GridNode method.
            if(nextNode instanceof GridNode){
                    // Sets settlement found if nextNode has a Settlement
                    settlementFound = nextNode.getSettlement() instanceof Settlement;
            }
            i++;
        }
        return settlementFound;
    }

    /**
     * Creates and Assigns all harbors to the appropriate gridNodes
     */
    private void assignHarbors(){
        Harbor currentHarbor = new Harbor(ResourceType.DESERT);
        assignHarbor(currentHarbor, 3,0);
        assignHarbor(currentHarbor, 2,1);
        currentHarbor = new Harbor(ResourceType.WHEAT);
        assignHarbor(currentHarbor,5,0);
        assignHarbor(currentHarbor,6,1);
        currentHarbor = new Harbor(ResourceType.ORE);
        assignHarbor(currentHarbor,8,2);
        assignHarbor(currentHarbor,9,3);
        currentHarbor = new Harbor(ResourceType.DESERT);
        assignHarbor(currentHarbor,10,5);
        assignHarbor(currentHarbor,10,6);
        currentHarbor = new Harbor(ResourceType.WOOL);
        assignHarbor(currentHarbor,9,8);
        assignHarbor(currentHarbor,8,9);
        currentHarbor = new Harbor(ResourceType.DESERT);
        assignHarbor(currentHarbor,6,10);
        assignHarbor(currentHarbor,5,11);
        currentHarbor = new Harbor(ResourceType.DESERT);
        assignHarbor(currentHarbor,3,11);
        assignHarbor(currentHarbor,2,10);
        currentHarbor = new Harbor(ResourceType.BRICK);
        assignHarbor(currentHarbor,1,8);
        assignHarbor(currentHarbor,1,7);
        currentHarbor = new Harbor(ResourceType.LUMBER);
        assignHarbor(currentHarbor,1,4);
        assignHarbor(currentHarbor,1,3);
    }

    /**
     * Assigns a harbor to a gridNode at a specific point.
     * This does not need to check for null GridNode references because it will be hardcoded.
     * @param myHarbor
     * @param x
     * @param y
     */
    private void assignHarbor(Harbor myHarbor, int x, int y){
        getGridNode(x,y).setHarbor(myHarbor);
    }

    /**
     * Calculates the length of the player's longest continuous road.
     * @param myPlayer The player being checked
     */
    public void findAndSetPlayerRoadLength(Player myPlayer){
        int roadLength=0;
        GridNode firstNode = myPlayer.getStartingNode(1);
        ArrayList<Road> longestRoadFromPoint1 = new ArrayList<>();
        longestRoadFromPoint1 = getLongestRoadAtStartingPoint(myPlayer,firstNode,longestRoadFromPoint1);

        ArrayList<Road> longestRoadFromPoint2 = new ArrayList<>();
        GridNode secondNode = myPlayer.getStartingNode(2);
        longestRoadFromPoint2 = getLongestRoadAtStartingPoint(myPlayer,secondNode,longestRoadFromPoint1);

        if(longestRoadFromPoint1.size()>= longestRoadFromPoint2.size()){
            roadLength = longestRoadFromPoint1.size();
        }else{
            roadLength = longestRoadFromPoint2.size();
        }
        myPlayer.setLongestRoadLength(roadLength);
    }

    /**
     * Checks if any of an ArrayList of items is in Another ArrayList
     * @param items The items being checked for
     * @param list The list being checked to contain items.
     * @return True if any items are in the list
     */
    private boolean hasItemsInList(ArrayList<Road> items, ArrayList<Road> list){
        int i = 0;
        boolean exists = false;

        while(!exists && i<items.size()){
            exists = list.contains(items.get(i));
            i++;
        }
        return exists;
    }

    /**
     * Gets the ArrayList of roads that make up the longest road from the firstNode.
     * @param myPlayer
     * @param firstNode
     * @param longestRoadFromOtherPoint The ArrayList of roads that are the Longest road from the other point.
     *                                  This is to check if there are any intersecting paths between the two points
     * @return The ArrayList of roads that make up the longest road form the startingNode selected
     */
    private ArrayList<Road> getLongestRoadAtStartingPoint(Player myPlayer, GridNode firstNode, ArrayList<Road> longestRoadFromOtherPoint){

        ArrayList<Road> allRoadsChecked = new ArrayList<>();
        ArrayList<Road> firstPathOptions = firstNode.getNextPlayerRoads(myPlayer,allRoadsChecked,new ArrayList<Road>());
        if (!hasItemsInList(firstPathOptions,longestRoadFromOtherPoint)) {
            ArrayList<Road> path1 = new ArrayList<>();
            ArrayList<Road> path2 = new ArrayList<>();
            ArrayList<Road> path3 = new ArrayList<>();
            ArrayList<Integer> pathSizes= new ArrayList<>();
            pathSizes.add(0);
            pathSizes.add(0);
            pathSizes.add(0);
            Road firstRoad;
            if(firstPathOptions.size()>=1){
                firstRoad=firstPathOptions.get(0);
                path1.add(firstRoad);
                path1 = getBranch(myPlayer,path1,firstNode,allRoadsChecked);
                addToAllRoadsChecked(allRoadsChecked,path1);
                pathSizes.set(0,path1.size());
            }
            if(firstPathOptions.size()>=2) {
                firstRoad = firstPathOptions.get(1);
                path2.add(firstRoad);
                path2 = getBranch(myPlayer, path2, firstNode,allRoadsChecked);
                addToAllRoadsChecked(allRoadsChecked,path2);
                pathSizes.set(1,path2.size());
            }
            if(firstPathOptions.size()==3){
                firstRoad = firstPathOptions.get(2);
                path3.add(firstRoad);
                path3 = getBranch(myPlayer,path3, firstNode, allRoadsChecked);
                pathSizes.set(2,path3.size());
            }
            ArrayList<ArrayList<Road>> allBranches = new ArrayList<>();
            allBranches.add(path1);
            allBranches.add(path2);
            allBranches.add(path3);
            int[] indexesOfHighestTwo = getTopTwoIndex(pathSizes);
            ArrayList<Road> longestRoadAtStartingPoint = new ArrayList<>();
            addToAllRoadsChecked(longestRoadAtStartingPoint, allBranches.get(indexesOfHighestTwo[0]));
            addToAllRoadsChecked(longestRoadAtStartingPoint, allBranches.get(indexesOfHighestTwo[1]));
            return longestRoadAtStartingPoint;
        }else{
            return new ArrayList<>();
        }

    }

    /**
     * Adds all items in this Path to allRoadsChecked
     * @param allRoadsChecked
     * @param thisPath
     */
    private void addToAllRoadsChecked(ArrayList<Road> allRoadsChecked, ArrayList<Road> thisPath){
        for(Road rd:thisPath){
            allRoadsChecked.add(rd);
        }

    }

    private int[] getTopTwoIndex(ArrayList<Integer> pathSizes){
        int[] indexes = new int[2];
        ArrayList<Integer> array = new ArrayList<>(pathSizes);
        int indexOfHighest=0;
        int indexOfNextHighest=0;
        int maximum=0;
        for(int i = 0; i<array.size();i++){
            if(array.get(i) >= maximum){
                maximum = array.get(0);
                indexOfHighest = i;
            }
        }
        // set value of highest index to -1 to ensure it does not get the second highest as well
        array.set(indexOfHighest,-1);
        maximum = 0;

        for(int i = 0; i<array.size();i++){
            if(array.get(i) >= maximum){
                maximum = array.get(i);
                indexOfNextHighest = i;
            }
        }
        indexes[0] = indexOfHighest;
        indexes[1] = indexOfNextHighest;
        return indexes;

    }

    /**
     * Recursively called function that evaluates all branches and determines which is the longest, returns the longest branch
     * @param myPlayer The player being checked
     * @param roadsTraversed The roads in the current major branch from the root node that have been checked already
     * @param currentNode The GridNode being checked for roads
     * @param allRoadsChecked All roads that are from a finalized major branch. This is so that no roads are counted twice
     * @return The ArrayList of Roads that make up the branch so far
     */
    private ArrayList<Road> getBranch(Player myPlayer,ArrayList<Road> roadsTraversed, GridNode currentNode, ArrayList<Road> allRoadsChecked){

        int lastIndex = roadsTraversed.size()-1;
        // Get the last road that was traversed.
        Road lastRoad  = roadsTraversed.get(lastIndex);
        GridNode currentNodeCopy = new GridNode(currentNode);
        // Get the next node to check
        GridNode nextNodeToCheck;
        // it is possible that the roads from the starting points might intersect with each other, therefore the
        // startNode for a road from one starting Point could be the endNode for a road from the other starting point
        if(currentNodeCopy.getLocation().equals(lastRoad.getStartNode().getLocation())){
            nextNodeToCheck = lastRoad.getEndNode();
        }else{
            nextNodeToCheck = lastRoad.getStartNode();
        }

        if(nextNodeToCheck.getSettlement() instanceof Settlement){
            // If there is a settlement at the next point and it is not the Player's then the branch has ended.
            if(nextNodeToCheck.getSettlement().getPlayer() != myPlayer){
                return roadsTraversed;
            }
            // same check except for cities
        }else if(nextNodeToCheck.getCity() instanceof City){
            if(nextNodeToCheck.getCity().getPlayer() != myPlayer){
                return roadsTraversed;
            }
        }
        ArrayList<Road> nextRoadOptions = nextNodeToCheck.getNextPlayerRoads(myPlayer,allRoadsChecked ,roadsTraversed);
        Road nextRoad;
        // If there are no more road options then the branch has ended
        if(nextRoadOptions.size() == 0){
            return roadsTraversed;
        }else{
            // Each gridNode can have at most 2 other branches
            ArrayList<Road> pathOption1 = new ArrayList<>(roadsTraversed);
            ArrayList<Road> pathOption2 = new ArrayList<>(roadsTraversed);
            if(nextRoadOptions.size() >= 1){
                nextRoad = nextRoadOptions.get(0);
                pathOption1.add(nextRoad);
                pathOption1 = getBranch(myPlayer,pathOption1,nextNodeToCheck,allRoadsChecked);
            }
            if(nextRoadOptions.size() == 2){
                nextRoad = nextRoadOptions.get(1);
                pathOption2.add(nextRoad);
                pathOption2 = getBranch(myPlayer,pathOption2,nextNodeToCheck,allRoadsChecked);
            }

            if(pathOption1.size()>pathOption2.size()){
                roadsTraversed = pathOption1;
            }else{
                roadsTraversed = pathOption2;
            }
        }
        return roadsTraversed;
    }

    /**
     * Finds all Tiles that are connected to a specific GridNode; This is useful for the very beginning of the game when
     * the player chooses which settlement to get the resources of.
     * @param searchNode
     * @return An ArrayList of Tile objects that connect with the GridNode
     */
    public ArrayList<Tile> findTiles(GridNode searchNode)
    {
        //ArrayList that will be returned containing all tiles that this grid node is attached to
        ArrayList<Tile> connectedTiles = new ArrayList<>();
        // Variables to contain the tiles being compared to search value
        Tile currentTile;
        // current node on tile being inspected on current tile
        GridNode currentNode;
        // all node points for current tile
        ArrayList<GridNode> currentNodePoints;
        //X and y values to represent the current node to be checked with search x and y
        int xCurrentValue;
        int yCurrentValue;
        // variables that will be used to identify the node given in the formal Parameter.
        int xSearchValue = (int)searchNode.getX();
        int ySearchValue = (int)searchNode.getY();
        // for loop to search through all tiles
        for(int i = 0; i<gameTiles.getTilesArray().size(); i++)
        {
            // current tile assigned based on loop and array
            currentTile = gameTiles.getTile(i);
            //All node points for current tile are assigned here
            currentNodePoints = currentTile.getTilePoints();

            // for loop to search through all grid points on specific tile in the array
            for(int j = 0; j< 6; j++)
            {
                // new node assigned
                currentNode = currentNodePoints.get(j);
                //Current x and y values to be checked with search node x and y values are assigned upon
                // a new node being checked.
                xCurrentValue = (int)currentNode.getX();
                yCurrentValue = (int)currentNode.getY();
                // if the x and y point of a grid node on a tile match with the settlemetns point, where the tile and
                // settlement intersect, then the tile is valid to give a resource to that player
                // and will be added to the array list containing all the tiles
                if(xCurrentValue == xSearchValue && yCurrentValue == ySearchValue)
                {
                    connectedTiles.add(currentTile);
                }
            }
        }
        // array of all connected tiles returned
        return connectedTiles;
    }

}
