package boardClasses;

import players.DirectionDecider;
import players.Road;
import players.RoadDirection;
import players.Settlement;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Patrick Martin
 * GridNode is a point on the grid for the game board
 * This class exists to hold a Settlement object or a Knight object
 * @extends java.awt.Point
 *
 * <p> The GridNode exists because a method to easily get what settlements are attached to a tile was needed.
 * A GridNode will be assigned to each vertex of a tile. The GridNode will allow us to change the Settlement on the
 * board without having to update three tiles (since each point can be on at most 3 tiles)</p><p>The GridNode also
 * functions as a node for the roads which will be a tree structure. Each node can have up to three branches
 * up, left down diagnal, and right down diagnal. This can be seen in the diagram that Trent and Dom drew of
 * board</p>
 *
 *
 */
public class GridNode extends Point{
    private Settlement mySettlement; // The settlement on that point
    // Knight myKnight; // The Knight on that point
    // ToDo Remove comment once Knight class has been created
    private Road northRoad;
    private Road southWestRoad;
    private Road southEastRoad;
    private Road southRoad;
    private Road northWestRoad;
    private Road northEastRoad;

    /**
     * Constructor that sets the location of the GridNode by calling the constructor from the inherited Point class
     * @param x
     * @param y
     */
    public GridNode(int x,int y){
        super(x,y);
    }
    /**
     * Sets a Settlement on the GridNode
     * @param playerSettlement Settlement The settlement that the player wants to place on the point.
     * @return True if there is no settlement already on the point
     */
    public boolean setSettlement(Settlement playerSettlement){
        boolean placementSuccessful;
        // If a settlement is already placed then
        if (mySettlement instanceof Settlement){
            placementSuccessful = false;
        }else {
            mySettlement = playerSettlement;
            placementSuccessful = true;
        }
        return placementSuccessful;
    }

    /**
     * Get the reference for the Settlement that is on the point
     * @return The Settlement on the point
     * @null Returns null if there is no settlement on the point
     */
    public Settlement getSettlement(){
        if(mySettlement instanceof Settlement) {
            return mySettlement;
        }else{
            return null;
        }
    }

    /**
     * Gets the road at the specified direction
     * @param direction
     * @return The road in the specified direction, returns null if no road is assigned
     * @nullable
     */
    public Road getRoadAt(RoadDirection direction){
        Road myRoad=null;
        switch (direction){
            case NORTH:{
                myRoad = northRoad;
                break;
            }
            case SOUTH_EAST:{
                myRoad = southEastRoad;
                break;
            }
            case SOUTH_WEST:{
                myRoad = southWestRoad;
                break;
            }
            case SOUTH:{
                myRoad = southRoad;
                break;
            }
            case NORTH_WEST:{
                myRoad = northWestRoad;
                break;
            }
            case NORTH_EAST:{
                myRoad = northEastRoad;
                break;
            }
        }
        return myRoad;
    }


    /**
     * Sets a Road object in the direction being built
     * @param rd The player's road that is being built
     * @param direction The direction the road is being built
     */
    public void setRoadAt(Road rd, RoadDirection direction){
        switch (direction){
            case NORTH:{
                northRoad=rd;
                break;
            }
            case SOUTH_EAST:{
                southEastRoad=rd;
                break;
            }
            case SOUTH_WEST:{
                southWestRoad=rd;
                break;
            }
            case SOUTH:{
                southRoad=rd;
                break;
            }
            case NORTH_WEST:{
                northWestRoad=rd;
                break;
            }
            case NORTH_EAST:{
                northEastRoad=rd;
                break;
            }

        }

    }

    /**
     * Gets an ArrayList of all roads attached to this GridNode
     * @return ArrayList<Road> All roads attached to the gridNode. If there are no roads attached,
     * this will return an empty ArrayList.
     */
    public ArrayList<Road> getAllRoads(){
        ArrayList roadsAttached = new ArrayList();
        // for each direction in the DirectionOptions Array, if the road at that direction exists then add it to the
        // roadsAttached ArrayList
        for(RoadDirection dir : DirectionDecider.getDirectionOptions(this.getLocation())){
            Road rd = getRoadAt(dir);
            if(rd instanceof Road){
                roadsAttached.add(rd);
            }
        }
        return roadsAttached;
    }



}
