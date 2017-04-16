package boardClasses;

import players.Settlement;

import java.util.Set;

/**
 * @author Patrick Martin
 * GridPoint is a point on the grid for the game board
 * This class exists to hold a Settlement object or a Knight object
 *
 * <p> The GridPoint exists because a method to easily get what settlements are attached to a tile was needed.
 * A GridPoint will be assigned to each vertex of a tile. The GridPoint will allow us to change the Settlement on the
 * board without having to update three tiles (since each point can be on at most 3 tiles)</p>
 *
 *
 */
public class GridPoint {
    Settlement mySettlement; // The settlement on that point
    // Knight myKnight; // The Knight on that point
    // ToDo Remove comment once Knight class has been created

    /**
     * Sets a Settlement on the GridPoint
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
}
