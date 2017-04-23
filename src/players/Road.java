package players;

import boardClasses.GridNode;

/**
 * @author Patrick Martin and Joey Seliga
 * The road class is used to connect GridNodes so that the player may place another settlement on the board.
 * @field startNode The gridNode that starts the road
 * @field endNode The gridNode that ends the road
 * @field owner The player that owns the road.
 */
public class Road {
    GridNode startNode;
    GridNode endNode;
    Player owner;

    /**
     * Constructor Assigns the owner to the road
     * @param owner The owner to the road
     */
    public Road(Player owner){
        this.owner = owner;
    }

    public GridNode getStartNode() {
        return startNode;
    }

    public void setStartNode(GridNode startNode) {
        this.startNode = startNode;
    }

    public GridNode getEndNode() {
        return endNode;
    }

    public void setEndNode(GridNode endNode) {
        this.endNode = endNode;
    }


    public Player getOwner(){return owner;}
}
