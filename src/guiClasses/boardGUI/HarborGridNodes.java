package guiClasses.boardGUI;

import java.util.ArrayList;

import boardClasses.GameBoard;
import boardClasses.GridNode;
import boardClasses.Harbor;
import boardClasses.Tile;

/**
 * 
 * @author Trent
 *
 */
public class HarborGridNodes {

	// an array containing all the Tiles on the GameBoard
	private ArrayList<Tile> tilesList;
	// the number of Tiles on the GameBoard
	private int numberOfTiles;
	// the list of the GrideNodes with Harbors
	private ArrayList<GridNode> harborGridNodes;

	/**
	 * Uses a gameBoard object to create an ArrayList of the GridNodes that have
	 * Harbors
	 * 
	 * @param gameBoard-
	 *            the game board setup that the class uses to get the locations
	 *            of the Harbors
	 */
	public HarborGridNodes(GameBoard gameBoard) {
		tilesList = gameBoard.getTiles().getTilesArray();
		numberOfTiles = tilesList.size();
		setHarborGridNodes();
	}// end constructor

	/**
	 * Sets the actual List
	 */
	private void setHarborGridNodes() {
		// initializes the array list
		ArrayList<GridNode> harborGridNodes = new ArrayList<>();
		// creates and arrayList that will hold the GridNodes located on a
		// specific Tile
		ArrayList<GridNode> tilePoints;

		// Loops through all of the Tiles on the GameBoard
		for (int indexOfTile = 0; indexOfTile < numberOfTiles; indexOfTile++) {
			// sets the list of the GridNodes on the Tile
			tilePoints = tilesList.get(indexOfTile).getTilePoints();
			// Loops through all of the GridNodes on the Tile
			for (int indexOfTilePoint = 0; indexOfTilePoint < tilePoints.size(); indexOfTilePoint++) {
				// Sets the current GridNode
				GridNode currentNode = tilePoints.get(indexOfTilePoint);
				// If the GridNode has a Harbor, and has not already on the
				// list, it is added to the list
				if (currentNode.getHarbor() instanceof Harbor) {
					// since GridNodes can be located on more than one Tile,
					// this check in necessary to avoid duplicates on the list
					if (harborGridNodes.indexOf(currentNode) == -1) {
						harborGridNodes.add(currentNode);
					}
				}
			}

		}

		this.harborGridNodes = harborGridNodes;
	}// end setHarborGridNodes method

	/**
	 * Returns the list of the GridNodes with Harbors
	 * 
	 * @return - ArrayList<GridNode> harborGridNodes
	 */
	public ArrayList<GridNode> getGridNodes() {
		return harborGridNodes;
	}// end getGridNodes method

}// end HarborGridNodes class
