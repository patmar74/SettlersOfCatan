package boardBackend;

import java.awt.Point;

import javax.swing.ImageIcon;

import otherPeoplesFiles.CircleToken;
import otherPeoplesFiles.ResourceType;

/**
 * 
 * @author Trent
 *
 */
public class Tile {

	// name is from a-s with the tile being set up as in the diagram
	private String name;
	// the upper left had corner of the rectangle with the same width and height
	// as the hexagon it contains.
	private Point locationReferencePoint;
	private Point[] locaionPointList;
	private boolean[] hasSettlement;
	private CircleToken token; // token holding holding the roll value
	private ResourceType resource; // enum value of the resource of the tile
	private ImageIcon image; // the folder containing these images still needs
								// to be populated
	private boolean hasRobber;

	/**
	 * Constructor that creates a tile object. It is called by the GameBoard
	 * constructor.
	 * 
	 * 
	 * @param name
	 *            - the name of the tile
	 * @param referencePoint
	 *            - the upper left corner of rectangle the hexagon sits on
	 * @param token
	 *            - the token that sits on the tile
	 * @param resource
	 *            - the type of resource the tile distributes
	 */
	public Tile(String name, Point referencePoint, CircleToken token, ResourceType resource) {
		this.name = name;
		this.locationReferencePoint = referencePoint;
		this.token = token;
		this.resource = resource;

		setHasRobber();
		setPointList();
		initializeHasSettlement();
		setImageIcon();
	}// end constructor

	public boolean getHasRobber() {
		return hasRobber;
	}// end getHasRobber method

	/**
	 * Sets the initial boolean value for weather or not the robber is on the
	 * tile to start
	 */
	private void setHasRobber() {
		// if the tile is the desert it will have the robber
		if (resource.equals(ResourceType.DESERT)) {
			hasRobber = true;
			// if the tile is a resource tile it will not have the robber
		} else {
			hasRobber = false;
		}
	}// end setHasRobber method

	/**
	 * Removes the robber from the tile
	 */
	public void removeRobber() {
		hasRobber = false;
	}// end removeRobber method

	/**
	 * places the robber on the tile
	 */
	public void placeRobber() {
		hasRobber = true;
	}// end placeRobber method

	/**
	 * The reference point is used to set the an array of the Points of corners
	 * of the tile. A point's index number in the array matches its position
	 * number on the tile. Position 0 is the top point and the position numbers
	 * increase as you proceed clockwise around the tile, the highest position
	 * being 5.
	 */
	private void setPointList() {
		Point[] pointList = new Point[6];
		// the x and y are the reference point coordinates
		// the return is a double but the value of the double will be an integer
		int x = (int) locationReferencePoint.getX();
		int y = (int) locationReferencePoint.getY();

		// this general form sets the position Point locations based off their
		// relative position with respect to the reference point
		Point position0 = new Point((x + 1), y);
		pointList[0] = position0;
		Point position1 = new Point((x + 2), (y + 1));
		pointList[1] = position1;
		Point position2 = new Point((x + 2), (y + 2));
		pointList[2] = position2;
		Point position3 = new Point((x + 1), (y + 3));
		pointList[3] = position3;
		Point position4 = new Point(x, (y + 2));
		pointList[4] = position4;
		Point position5 = new Point(x, (y + 1));
		pointList[5] = position5;

		this.locaionPointList = pointList;
	}// end setPointList method

	/**
	 * initializes all of the settlements as being false(no settlements have
	 * been built when the tile is created)
	 */
	private void initializeHasSettlement() {
		boolean[] hasSettlement = new boolean[6];
		for (int i = 0; i < 6; i++) {
			hasSettlement[i] = false;
		}
	}// end initializeHasSettlement method

	/**
	 * Checks to see if a Point is on the tile
	 * 
	 * @param p
	 *            - Point to be compared
	 * @return - the position number of the point on the tile or -1 if the point
	 *         is not on the tile
	 */
	public int isPointOnTile(Point p) {
		for (int i = 0; i < 6; i++) {
			if (p.equals(locaionPointList[i])) {
				return i;
			}
		}
		return -1;
	}// end isPointOnTile method

	public void buildSettlement(Point p) {
		int index = isPointOnTile(p);
		if (index != -1 && hasSettlement[index] == false) {
			hasSettlement[index] = true;
			// add some code that adds the settlement to the person object
		}
	}// end buildSettlement method

	public Point getReferencePoint() {
		return locationReferencePoint;
	}// end getReferencePoint method

	public Point[] getPointList() {
		return locaionPointList;
	}// end getPointList method

	public CircleToken getToken() {
		return token;
	}// end getToken method

	public ResourceType getResourceType() {
		return resource;
	}// end getResourceType method

	public String getTileName() {
		return name;
	}// end getTileName method

	/**
	 * Gives the tile an ImageIcon based off of it's resource enum value. The
	 * image files are saved under the TileImages folder in the build path under
	 * the name of the enum they correspond to.
	 */
	private void setImageIcon() {
		String imageFileName = "TileImages/" + resource + ".jpg";
		image = new ImageIcon(imageFileName);
	}// end setImage method

	public ImageIcon getImageIcon() {
		return image;
	}// end getImageIcon method

	/**
	 * 
	 * @return - the integer value of the token that will be compared with the
	 *         value of the dice roll to see if the tile is selected to
	 *         distribute resources
	 */
	public int getTokenValue() {
		return Integer.parseInt(token.getNumber());
	}// end getTokenValue method

}// end Tile class
