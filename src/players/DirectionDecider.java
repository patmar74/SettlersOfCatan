package players;

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
                reflection = RoadDirection.NORTH;
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
}
