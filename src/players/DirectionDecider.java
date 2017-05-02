package players;

import boardClasses.GameBoard;
import boardClasses.GridNode;

import java.awt.*;

/**
 * Class holds static methods for determining the direction of roads
 * @author Patrick Martin & Joey Seliga
 */
public class DirectionDecider {
    /**
     *
     * Determines which direction the road will be built based on the Start and End points
     * @param start The Point that starts the road, this corresponds with a GridNode
     * @param end The Point that ends the road, this corresponds with a GridNode
     * @return RoadDirection The direction that the road will be built, returns null if start and end points are not
     * within one edge of each other.
     */
    public static RoadDirection getRoadDirection(Point start, Point end){
        RoadDirection dir = null;
        // Change in x
        int dx = end.x - start.x;
        // Change in y
        int dy = end.y - start.y;
        // If x doesn't change, check if North or South
        if(dx == 0){
            // If y increases by 1 then direction is South
            if(dy == 1){
                dir = RoadDirection.SOUTH;
                // if y decreases by 1 then direction is North
            }else if(dy == -1){
                dir = RoadDirection.NORTH;
            }
            // if x decreases then build in Western direction, check if North or South
        }else if(dx == -1){
            // If y increases by 1 then direction is Southern
            if(dy == 1){
                dir = RoadDirection.SOUTH_WEST;
                // If y decreases by 1 then direction is Northern
            }else if (dy == -1){
                dir = RoadDirection.NORTH_WEST;
            }
            // If x increases then build in Eastern direction, check if North or South
        }else if(dx == 1){
            // If y increases by 1 then direction is Southern
            if(dy == 1){
                dir = RoadDirection.SOUTH_EAST;
                // If y decreases by 1 then direction is Northern
            }else if(dy == -1){
                dir = RoadDirection.NORTH_EAST;
            }
        }
        return dir;
    }

    /**
     * Get the reflection (aka opposite) road direction to the input direction.
     * This will be useful for setting the proper GridNode road connection for the end of the road.
     * Ex) If a road is starting in the North direction, the end of the Road has to be assigned in the South direction.
     * @param dirFromStart The direction of the road from the start GridNode
     * @return The direction that is a reflection (aka the opposite) of the start direction
     * <p>Ex) getReflection(RoadDirection.NORTH) returns RoadDirection.SOUTH
     * Ex) getReflection(RoadDirection.SOUTH_WEST) returns RoadDirection.NORTH_EAST
     * Ex) getReflection(RoadDirection.SOUTH_EAST) returns RoadDirection.NORTH_WEST
     * and vice-versa for each case.</p>
     */
    public static RoadDirection getReflection(RoadDirection dirFromStart){
        RoadDirection reflection=null;
        switch (dirFromStart){
            case NORTH:{
                reflection = RoadDirection.SOUTH;
                break;
            }
            case SOUTH_EAST:{
                reflection = RoadDirection.NORTH_WEST;
                break;
            }
            case SOUTH_WEST:{
                reflection = RoadDirection.NORTH_EAST;
                break;
            }
            case SOUTH:{
                reflection = RoadDirection.NORTH;
                break;
            }
            case NORTH_WEST:{
                reflection = RoadDirection.SOUTH_EAST;
                break;
            }
            case NORTH_EAST:{
                reflection = RoadDirection.SOUTH_WEST;
                break;
            }
        }
        return reflection;
    }

    /**
     * Gets the next point in a specific direction
     * @param dir
     * @param currentNode The reference node for finding the next node
     * @return The next Point in the specified direction
     */
    public static Point getNextPoint(RoadDirection dir, GridNode currentNode){
        Point nextPoint = new Point(currentNode.getLocation());
        switch (dir){
            case NORTH:{
                nextPoint.y -= 1;
                break;
            }
            case NORTH_EAST:{
                nextPoint.x += 1;
                nextPoint.y -= 1;
                break;
            }
            case SOUTH_EAST:{
                nextPoint.x += 1;
                nextPoint.y += 1;
                break;
            }
            case SOUTH:{
                nextPoint.y += 1;
                break;
            }
            case SOUTH_WEST:{
                nextPoint.x -= 1;
                nextPoint.y += 1;
                break;
            }
            case NORTH_WEST:{
                nextPoint.x -= 1;
                nextPoint.y -= 1;
                break;
            }
        }
        return nextPoint;
    }

    /**
     * Gets an Array of all options that a road can be built on for the GridNode. The array is ordered staring with the
     * vertical direction and rotating clockwise.
     * @return <p>If y value of the GridNode location is even then return [NORTH,SOUTH_EAST,SOUTH_WEST].
     * If y value is odd then return [SOUTH,NORTH_WEST,NORTH_EAST]</p>
     */
    public static RoadDirection[] getDirectionOptions(Point pt){
        RoadDirection[] directionOptions = new RoadDirection[3];
        if(pt.y % 2 == 0){
            directionOptions[0] = RoadDirection.NORTH;
            directionOptions[1] = RoadDirection.SOUTH_EAST;
            directionOptions[2] = RoadDirection.SOUTH_WEST;
        }else{
            directionOptions[0] = RoadDirection.SOUTH;
            directionOptions[1] = RoadDirection.NORTH_WEST;
            directionOptions[2] = RoadDirection.NORTH_EAST;
        }
        return directionOptions;
    }



}
