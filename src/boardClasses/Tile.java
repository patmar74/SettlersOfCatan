package boardClasses;

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

	public Tile(ResourceType resource){
	    this.resource = resource;
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

}
