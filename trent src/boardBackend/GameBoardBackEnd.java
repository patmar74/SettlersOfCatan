package boardBackend;

import java.awt.Point;
import java.util.ArrayList;

import otherPeoplesFiles.CircleToken;
import otherPeoplesFiles.ResourceType;
import otherPeoplesFiles.TokenCreator;

/* Notes on what to work on in class: Trent 4/11/17
 * 
 * Tyler and I will iron out a couple things with the token circle classes and make
 * sure our files are compatible.
 * 
 * ports still need to be added to the game board and tiles. I will need to discuss 
 * with whoever is working on ports before I do this so I our approaches sync up.
 * This discussion should also have input from the banker since they are dealing
 * with trade. 
 * 
 * I also need to talk to the person that is working on roads so that I can add them 
 * to the game board (I am not sure if I want roads assigned to tiles, I'll see how 
 * they are setting it up and that will give me guidance).
 * 
 * Working with the class on ports and roads are my next planned steps with regards to
 * the game board and tile set ups. There are many ways to code the game and I want to 
 * make sure the method I go with works with there code that others are writing.
 * 
*/

/**
 * The class that setups up the game board as is exists behind the scenes
 * 
 * @author Trent
 *
 */
public class GameBoardBackEnd {

	private Point[][] pointArray;
	private ArrayList<Point> PointsList;
	private ArrayList<Point> ReferencePoints;
	private ArrayList<CircleToken> TokenList;
	private ArrayList<ResourceType> ResourceList;
	private ArrayList<Tile> TileList;

	/**
	 * Calls all the methods needed to create an instance of a game board
	 * object.
	 */
	public GameBoardBackEnd() {
		setPointsList();
		setTokenList();
		setResourceLayout();
		// this final method takes the data set in the previous methods and uses
		// it to create the 19 game tiles
		setBoard();
	}// end constructor

	/**
	 * Sets the reference structures that store the points used on the game
	 * board (all of the points on the corners of the 19 hexagonal tiles used in
	 * the standard set up). One is a ragged two dimensional array of Point
	 * objects where the row number is the y coordinate and the column is the x
	 * coordinate of the Point at pointArray[y][x]. The other is an ArrayList of
	 * Point objects containing all of the Points on the corners of tiles.
	 */
	private void setPointsList() {
		/*
		 * Note: In an effort to match the format used in graphics displays, the
		 * origin (coordinate (0,0)) is located at the upper left hand corner of
		 * the board. The positive direction for x is right and the positive
		 * direction for y is down.
		 */

		pointArray = new Point[12][11];
		PointsList = new ArrayList<>();
		// reference points will be created in rows 0,2,4,6, and 8.
		ReferencePoints = new ArrayList<>();

		// the y value is the row that the points are being created for
		// the top row on the board is 0 and the bottom row is 12
		int y = 0;
		createPointsForRow(3, 7, y, true, 7);

		y = 1;
		createPointsForRow(2, 8, y, false, 8);

		y = 2;
		createPointsForRow(2, 8, y, true, 8);

		y = 3;
		createPointsForRow(1, 9, y, false, 9);

		y = 4;
		createPointsForRow(1, 9, y, true, 9);

		y = 5;
		createPointsForRow(0, 10, y, false, 10);

		y = 6;
		createPointsForRow(0, 10, y, true, 6);

		y = 7;
		createPointsForRow(1, 9, y, false, 9);

		y = 8;
		createPointsForRow(1, 9, y, true, 5);

		y = 9;
		createPointsForRow(2, 8, y, false, 8);

		y = 10;
		createPointsForRow(2, 8, y, false, 8);

		y = 11;
		createPointsForRow(3, 7, y, false, 7);

	}// end setPointsList method

	/**
	 * Handles the population of PointsList, PointArray, and ReferencePoints.
	 * Works one row at a time.
	 * 
	 * @param start
	 *            - the x coordinate the first point is created at
	 * @param end
	 *            - the x coordinate the last point is created at
	 * @param yValue
	 *            - the y coordinate of the points on the row
	 * @param refferencePointRow
	 *            - true if reference points are being created in this row
	 * @param refferencePointCreationStart
	 *            - on rows 6 and 8 reference points need to be created after a
	 *            certain cutoff, this sets that cutoff
	 */
	private void createPointsForRow(int start, int end, int yValue, boolean refferencePointRow,
			int refferencePointCreationStart) {
		// the row points are being created for
		int y = yValue;
		for (int x = start; x <= end; x += 2) {
			pointArray[y][x] = new Point(x, y);
			PointsList.add(pointArray[y][x]);
			// if reference points are created this statement will execute
			if (refferencePointRow) {
				// the lines on the bottom (y>=6) are different then the top so
				// there
				// need to be two cases for handling the creation of reference
				// points
				if (y >= 6) {
					if (x <= refferencePointCreationStart) {
						ReferencePoints.add(new Point((x + 1), y));
					}
				} else {
					ReferencePoints.add(new Point((x - 1), y));
				}
			} // end if statement for creating reference points

		} // end for loop
	}// end createPoints method

	private void setBoard() {
		int numberOfTiles = 19;
		ArrayList<Tile> TileList = new ArrayList<>();

		CircleToken token;
		String tileName;
		Point refPoint;
		ResourceType resource;

		String tileLetters = "ABCDEFGHIJKLMNOPQRS";

		for (int i = 0; i < numberOfTiles; i++) {
			tileName = "Tile" + tileLetters.indexOf(i);
			token = TokenList.get(i);
			refPoint = ReferencePoints.get(i);
			resource = ResourceList.get(i);

			TileList.add(new Tile(tileName, refPoint, token, resource));
		} // end for loop
	}// end setBoard method

	/**
	 * Takes the token list (that has a clockwise order) and puts it in an order
	 * that matches the A-S convention
	 * 
	 * This section will be revised and possibly transfered into the
	 * TokenCreator class depending on what Tyler and I decide the best way to
	 * handle this is.
	 */
	private void setTokenList() {
		TokenCreator tokenCreator = new TokenCreator();
		// Calls a different constructor from TokenCreator that has a ENUM
		// input, this constructor will only initialize one token to the
		// TokenArrayList, the dummy robber token
		TokenCreator desertTokenCreator = new TokenCreator(ResourceType.DESERT);
		CircleToken desertToken = desertTokenCreator.getArrayList().get(0);
		// Creates the list of the 18 standard tokens
		ArrayList<CircleToken> List = tokenCreator.getArrayList();
		ArrayList<CircleToken> TokenList = new ArrayList<>();
		// fix numbers to put in spiral order
		TokenList.add(List.get(0)); // A
		TokenList.add(List.get(1)); // B
		TokenList.add(List.get(2)); // C
		TokenList.add(List.get(10)); // D
		TokenList.add(List.get(11)); // E
		TokenList.add(List.get(12)); // F
		TokenList.add(List.get(3)); // G
		TokenList.add(desertToken); // H (DESERT)
		TokenList.add(List.get(16)); // I
		TokenList.add(List.get(17)); // J
		TokenList.add(List.get(13)); // K
		TokenList.add(List.get(4)); // L
		TokenList.add(List.get(9)); // M
		TokenList.add(List.get(15)); // N
		TokenList.add(List.get(14)); // O
		TokenList.add(List.get(5)); // P
		TokenList.add(List.get(8)); // Q
		TokenList.add(List.get(7)); // R
		TokenList.add(List.get(6)); // S

		this.TokenList = TokenList;
	}// end setTokenList method

	/**
	 * Sets the layout of the resource tiles for the game board, for now this is
	 * a fixed method. (it uses the layout from the doc in the discussion
	 * thread)
	 */
	private void setResourceLayout() {
		// this layout is based on the layout from
		// http://www.catan.com/en/download/?SoC_rv_Rules_091907.pdf

		ArrayList<ResourceType> ResourceList = new ArrayList<>();
		ResourceList.add(ResourceType.LUMBER);
		ResourceList.add(ResourceType.WOOL);
		ResourceList.add(ResourceType.WHEAT);
		ResourceList.add(ResourceType.BRICK);
		ResourceList.add(ResourceType.ORE);
		ResourceList.add(ResourceType.BRICK);
		ResourceList.add(ResourceType.WOOL);
		ResourceList.add(ResourceType.DESERT);
		ResourceList.add(ResourceType.LUMBER);
		ResourceList.add(ResourceType.WHEAT);
		ResourceList.add(ResourceType.LUMBER);
		ResourceList.add(ResourceType.WHEAT);
		ResourceList.add(ResourceType.BRICK);
		ResourceList.add(ResourceType.WOOL);
		ResourceList.add(ResourceType.WOOL);
		ResourceList.add(ResourceType.ORE);
		ResourceList.add(ResourceType.ORE);
		ResourceList.add(ResourceType.WHEAT);
		ResourceList.add(ResourceType.WOOL);

		this.ResourceList = ResourceList;

	}// end defaultResourceLayout method

	public ArrayList<Point> getReferencePointList() {
		return ReferencePoints;
	}

	public ArrayList<Tile> getTileList() {
		return TileList;
	}

}// end GameBoard class
