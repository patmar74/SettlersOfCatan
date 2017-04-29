package guiClasses.boardGUI;

import boardClasses.GameBoard;
import boardClasses.Tile;

import java.awt.Point;
import java.util.ArrayList;


/**
 * 
 * @author Trent
 *
 */
public class RefferencePoints {
	private ArrayList<Tile> tilesList;
	private int numberOfTiles;
	private ArrayList<Point> refferencePointsList;

	/**
	 * Returns an array list of reference points in the same indexed order as
	 * the tile list
	 * 
	 * @param gameBoard
	 *            - the game board that holds the tiles this class is creating a
	 *            list of reference points for
	 */
	public RefferencePoints(GameBoard gameBoard) {
		tilesList = gameBoard.getTiles().getTilesArray();
		numberOfTiles = tilesList.size();
		setRefferencePoints();
	}// end constructor

	/**
	 * sets the reference points based off of the location of the top point on
	 * the tiles
	 */
	private void setRefferencePoints() {
		// initializes an array list
		ArrayList<Point> refferencePointsList = new ArrayList<>();

		Point gridRefferencePoint;
		Point refferencePoint;
		// loops through the tiles
		for (int indexOfTile = 0; indexOfTile < numberOfTiles; indexOfTile++) {
			// gets the Point at the top of the hexagon
			gridRefferencePoint = tilesList.get(indexOfTile).getGridPointReference();
			// the reference point is one spot over to the left from the top
			// point
			refferencePoint = new Point(gridRefferencePoint.x - 1, gridRefferencePoint.y);
			// adds the reference point to the array list
			refferencePointsList.add(refferencePoint);
		}
		this.refferencePointsList = refferencePointsList;
	}// end setRefferencePoints method

	/**
	 * 
	 * @return - an ArrayList<Point> that contains all of the reference points
	 *         for the tiles
	 */
	public ArrayList<Point> getRefferencePointsList() {
		return refferencePointsList;
	}// end getRefferencePointsList method

}// end RefferencePoints
