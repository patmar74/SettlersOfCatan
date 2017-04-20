package boardClasses;

import resourceClasses.ResourceType;

import java.awt.*;

import javax.swing.ImageIcon;

/**
 * @Author Patrick, Joey, Tyler, Misha
 * 
 */

public class Tile {

	private boolean hasRobber;
	//hasRobber declares whether or not this.tile has the robber on top of it
	private boolean[] hasSettlement;
	// defines boardClasses.CircleToken to be placed on hex
	private CircleToken token;
	// defines image to be placed on hex
	private ImageIcon image;
	// defines the type of resource a hex yields
	private ResourceType resource;
	// the character on the hex, used for the purpose of setting up the board
	private char Name;
	// Array of points on specified tile which will be assigned based on the reference point
	private GridNode[] tilePoints = new GridNode[6];
	//The reference point will represent the topmost point of the hexagon which will be used to assign points
	private Point gridPointReference;
	private BoardGrid grid;
	
	
	// Setters and getters for all variables
	public boolean isHasRobber() 
	{
		return hasRobber;
	}
	
	public void setHasRobber(boolean hasRobber)
	{
		this.hasRobber = hasRobber;
	}
	public boolean[] getHasSettlement()
	{
		return hasSettlement;
	}
	public void setHasSettlement(boolean[] hasSettlement)
	{
		this.hasSettlement = hasSettlement;
	}
	public CircleToken getToken() 
	{
		return token;
	}
	public void setToken(CircleToken token)
	{
		this.token = token;
	}
	public ImageIcon getImage() 
	{
		return image;
	}
	public void setImage(ImageIcon image) 
	{
		this.image = image;
	}
	public ResourceType getResource() 
	{
		return resource;
	}
	public void setResource(ResourceType resource)
	{
		this.resource = resource;
	}
	public char getName() 
	{
		return Name;
	}
	public void setName(char name)
	{
		Name = name;
	}

	public GridNode[] getTilePoints()
	{
		return tilePoints;
	}

	public void setTilePoints(GridNode[] tilePoints)
	{
		this.tilePoints = tilePoints;
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

    public void setNodesToTile(BoardGrid grid){
		int xValue = (int)gridPointReference.getX();
		int yValue = (int)gridPointReference.getY();
		tilePoints[0] = grid.getNode(xValue,yValue);
		tilePoints[1] = grid.getNode(xValue-1,yValue-1);
		tilePoints[2] = grid.getNode(xValue+1,yValue-1);
		tilePoints[3] = grid.getNode(xValue-1,yValue-2);
		tilePoints[4] = grid.getNode(xValue+1,yValue-2);
		tilePoints[5] = grid.getNode(xValue,yValue-2);
	}
	

}
