package boardClasses;

import players.Player;
import players.Settlement;
import resourceClasses.ResourceType;

import java.awt.*;
import java.util.ArrayList;

/**
 * @Author Patrick, Joey, Tyler, Misha
 * 
 */

public class Tile {


	// defines boardClasses.CircleToken to be placed on hex
	private CircleToken token;

    // defines the type of resource a hex yields
	private ResourceType resource;
    // Array of points on specified tile which will be assigned based on the reference point
	private ArrayList<GridNode> tilePoints = new ArrayList<>();
    //The reference point will represent the topmost point of the hexagon which will be used to assign points
	private Point gridPointReference;
	// Flag for if the robber is on the tile.
	private boolean hasRobber;
	private int index;

    /**
     * Constructor, creates a tile of ResourceType and sets hasRobber to false
     * @param resource The resource that the tile being created will have.
     */
    public Tile(ResourceType resource){
        this.resource = resource;
        hasRobber = false;
    }
    public CircleToken getToken()
	{
		return token;
	}
    public void setToken(CircleToken token)
	{
		this.token = token;
	}

	public ResourceType getResource()
	{
		return resource;
	}

	public ArrayList<GridNode> getTilePoints()
	{
		return tilePoints;
	}

	public Point getGridPointReference()
	{
		return gridPointReference;
	}

	public void setGridPointReference(Point gridPointReference)
	{
		this.gridPointReference = gridPointReference;
	}

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Sets the appropriate GridNodes to the right position on the hex tile.
     * @param grid BoardGrid
     */
    public void setNodesToTile(BoardGrid grid){
		int xValue = (int)gridPointReference.getX();
		int yValue = (int)gridPointReference.getY();
		tilePoints.add(grid.getNode(xValue,yValue));
		tilePoints.add(grid.getNode(xValue+1,yValue+1));
		tilePoints.add(grid.getNode(xValue+1,yValue+2));
		tilePoints.add(grid.getNode(xValue,yValue+3));
		tilePoints.add(grid.getNode(xValue-1,yValue+2));
		tilePoints.add(grid.getNode(xValue-1,yValue+1));
	}

    /**
     * Sets the flag for if the robber is on the tile
     * @param hasRobber True if the robber is being put on the tile.
     */
	public void setHasRobber(boolean hasRobber){
        this.hasRobber = hasRobber;
    }

    /**
     * Gets the flag if the tile has the robber on it.
     * @return True if the robber is on the tile.
     */
    public boolean getHasRobber(){
	    return hasRobber;
    }

	@Override
	public String toString() {
		return "Tile:"+ getToken().getLetterOnToken() + "; Roll: " + getToken().getNumber() + "; Resource: " + resource;
	}

	public ArrayList<Player> getOtherPlayersWithSettlements(Player currentPlayer){
        ArrayList<Player> otherPlayersWithSettlements = new ArrayList<>();
        for(GridNode node:tilePoints){
            Player otherPlayer=null;
            if(node.getSettlement() instanceof Settlement){
                otherPlayer = node.getSettlement().getPlayer();
            }else if(node.getCity() instanceof Settlement){
                otherPlayer = node.getCity().getPlayer();
            }
            if(otherPlayer instanceof Player){
                if(otherPlayer != currentPlayer) {
                    otherPlayersWithSettlements.add(otherPlayer);
                }
            }
        }
        return otherPlayersWithSettlements;
    }
}
