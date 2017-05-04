/**
 * Autogenerated class that displays board. Panel will be extracted from "initialize" class.
 * @author Dominic, @Mihail
 */
package guiClasses.boardGUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import boardClasses.GameBoard;
import boardClasses.GridNode;
import boardClasses.Tile;
import players.City;
import players.Player;
import players.Road;
import players.Settlement;
import players.playerColors;
import resourceClasses.ResourceType;

/*
 * The Robber needs to be painted, and Tokens need to be painted to start
 */
/**
 * This class sets the JPanel the game board is displayed on.
 * 
 * For testing purposes, this class has a JFrame that the panel is displayed on,
 * but later on this class will only have the JPanel, and it will be added to
 * the frame in a GameDisplay GUI that will have all the visible panels used on
 * the display.
 * 
 * @author Trent
 *
 */
public class CatanWindow {

	// scale used to size the image icons
	// this can be changed for different display sizes
	// must be divisible by 4 for the Tile images to display properly
	final int SCALE = 52;
	// the width of the JPanel panel
	final int width = SCALE * 10;
	// the width of the JPanel panel
	final int height = SCALE * 8;
	// adds a cushion around the game board for readability
	final int BUFFER = SCALE / 2;
	private int adjustRowHeight;
	private JPanel panel;
	private JFrame frame;

	/**
	 * Displays the game board
	 * 
	 * @param gameBoard
	 *            - the game board background setup that the panel is creating a
	 *            visual representation for
	 */
	public CatanWindow(GameBoard gameBoard) {
		initialize(gameBoard);
	}// end constructor

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize(GameBoard gameBoard) {
		frame = new JFrame();

		frame.setResizable(true);
		frame.setBounds(100, 100, 700, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		// draws a boarder around the panel so I can see where it lies on the
		// frame
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		// sets the dimensions of the panel, the buffer will be on the top,
		// bottom, left and right, so 2 times the size of the BUFFER is added to
		// each dimension
		panel.setBounds(0, 0, width + BUFFER * 2, height + BUFFER * 2);
		// adds the panel to the JFrame
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// sets the list of tiles on the game board
		ArrayList<Tile> tileList = gameBoard.getTiles().getTilesArray();
		// sets the list of the tile's reference points, these points are used
		// to orient the images for the tiles
		RefferencePoints reffPoints = new RefferencePoints(gameBoard);
		ArrayList<Point> referencePoints = reffPoints.getRefferencePointsList();

		// loops through all of the tiles and displays them on the panel
		for (int index = 0; index < tileList.size(); index++) {
			// sets the resource type of the tile
			ResourceType tileResource = tileList.get(index).getResource();

			// sets the image icon for the tile based on its resource type
			ImageIcon icon = new ImageIcon("Resources/TileImages/" + tileResource.toString() + ".png");
			// transform the image icon into an image so it can be scaled
			Image image = icon.getImage();
			// scales the image two two times the size of the final int SCALE
			// this is size of the JLable the image will sit on
			Image scaledImage = image.getScaledInstance(SCALE * 2, SCALE * 2, java.awt.Image.SCALE_SMOOTH);
			// transforms the image back into a IconImage so it can be added to
			// a JLabel
			icon = new ImageIcon(scaledImage);

			// creates a label with the image icon in the center
			JLabel lblTileImage = new JLabel("", icon, JLabel.CENTER);
			// sets the x value of the tile's reference point
			int x = (int) referencePoints.get(index).getX();
			// sets the y value of the tile's reference point
			int y = (int) referencePoints.get(index).getY();
			// The images line up next to each other left to right, but they
			// overlap and stack on top of each other from top to bottom.
			// each row starts 3/4 of the way down the height of the previous
			// one. This shift is accounted four by moving the y value of each
			// row up 1/4 of the tile height (SCALE).
			adjustRowHeight = SCALE / 4 * y;
			// sets the location and dimension of the label holding the Tile's
			// image icon
			lblTileImage.setBounds(x * SCALE + BUFFER, y * SCALE - adjustRowHeight + BUFFER, SCALE * 2, SCALE * 2);
			// adds the label to the panel
			panel.add(lblTileImage);
		}
		// adds the labels showing where the harbors are
		addHarborImages(gameBoard);

		// once all tiles have been visually represented, the frame is set to
		// visible
		frame.setVisible(true);
	}// end initialize method

	/**
	 * Displays text labels for all of the Harbors. The labels are displayed on
	 * top of the GridNode where the harbor is located and they contain the
	 * ResourceType and trade ratio of the harbor
	 * 
	 * @param gameBoard
	 *            - the game board background setup that the panel is creating a
	 *            visual representation for
	 */
	private void addHarborImages(GameBoard gameBoard) {
		// creates a list of the grideNodes with harbors from the gameBoard
		HarborGridNodes harborNodes = new HarborGridNodes(gameBoard);
		ArrayList<GridNode> harborNodeList = harborNodes.getGridNodes();

		String harborText;
		// loops through the list of GridNodes with harbors
		for (int indexOfNode = 0; indexOfNode < harborNodeList.size(); indexOfNode++) {
			// sets the Point location of the GrideNode
			Point harborLocation = harborNodeList.get(indexOfNode).getLocation();
			// sets the resource that can be traded at the harbor
			ResourceType harborResource = harborNodeList.get(indexOfNode).getHarbor().getTradeType();
			// if the harbor has no set resource, it is 3:1
			if (harborResource.equals(ResourceType.DESERT)) {
				harborText = "? 3:1";
			}
			// if the harbor has a set trade resource, it is 2:1
			else {
				harborText = harborResource.toString().substring(0, 2) + " 2:1";

			}
			// sets the x and y coordinates of the harborLocation
			int x = (int) harborLocation.getX();
			int y = (int) harborLocation.getY();
			// adjusts the row height to accommodate the fact that the Tiles are
			// layered on top of one another
			adjustRowHeight = SCALE / 4 * y;
			if (y % 2 == 1) {
				adjustRowHeight += SCALE / 4;
			}
			// creates a new label holding the text description of the harbor
			JLabel harborLabel = new JLabel(harborText);
			// sets the background and foreground colors
			harborLabel.setForeground(Color.white);
			harborLabel.setBackground(Color.BLACK);
			// makes the label opaque so the background color can be changed
			harborLabel.setOpaque(true);
			// set the font type, style, and size to be used
			harborLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 10));
			// sets the size as the minimum needed to display the text
			harborLabel.setSize(harborLabel.getPreferredSize());
			// centers the label on the harborLocation Point
			int widthAdjustment;
			int heightAdjustment;
			// top harbors to above their location Point
			if (y < 6) {
				heightAdjustment = (int) (harborLabel.getPreferredSize().getHeight());
			}
			// leaves the harbors bellow the point
			else {
				heightAdjustment = 0;
			}
			// moves the harbor labels to the right of their location point
			if (x < 5) {
				widthAdjustment = (int) (harborLabel.getPreferredSize().getWidth());
			}
			// leaves the harbors on the left of the location Point
			else {
				widthAdjustment = 0;
			}

			// sets the display location of the label
			harborLabel.setLocation(x * SCALE + BUFFER - widthAdjustment,
					y * SCALE - adjustRowHeight + BUFFER - heightAdjustment);
			// adds the label to the panel on the top layer
			panel.add(harborLabel, 0);
		}

	}// end addHarborImages method

	/**
	 * Adds a visual representation of a settlement or city to the GameBoard.
	 * This method is used for adding new settlements, and upgrading settlements
	 * to cities
	 * 
	 * @param buildingBeingAdded
	 *            - the Settlement of City object being added to the visual game
	 *            board
	 */
	public void addSettlementOrCityToMap(Settlement buildingBeingAdded) {

		Color playerColor = getPlayerColor(buildingBeingAdded.getPlayer());

		// sets the location of the building being added to the map
		int[] buildingLocation = buildingBeingAdded.getLocation();
		// sets the x and y coordinates of the harborLocation
		int x = buildingLocation[0];
		int y = buildingLocation[1];

		// sets the character symbol used to represent the settlement or city
		String buildingText;
		if (buildingBeingAdded instanceof Settlement) {
			if (buildingBeingAdded instanceof City) {
				buildingText = "C";
			} else {
				buildingText = "S";
			}
		} else {
			buildingText = "";
		}

		// adjusts the row height to accommodate the fact that the Tiles are
		// layered on top of one another
		adjustRowHeight = SCALE / 4 * y;
		if (y % 2 == 1) {
			adjustRowHeight += SCALE / 4;
		}
		JLabel buildingLabel = new JLabel(buildingText);
		// sets the text color as white and the background color as the player
		// color
		buildingLabel.setForeground(Color.white);
		buildingLabel.setBackground(playerColor);
		// makes the label opaque so the background color can be changed
		buildingLabel.setOpaque(true);
		// set the font type, style, and size to be used
		buildingLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 8));
		// sets the size as the minimum needed to display the text
		buildingLabel.setSize(buildingLabel.getPreferredSize());
		// centers the label over the point
		int widthAdjustment = (int) (buildingLabel.getPreferredSize().getHeight()) / 2;
		int heightAdjustment = (int) (buildingLabel.getPreferredSize().getHeight()) / 2;

		// sets the display location of the label
		buildingLabel.setLocation(x * SCALE + BUFFER - widthAdjustment,
				y * SCALE - adjustRowHeight + BUFFER - heightAdjustment);
		// adds the label to the panel on the top layer
		panel.add(buildingLabel, 0);

	}// end addSettlementOrCityToMap method

	/**
	 * Draws a Road onto the game board
	 * 
	 * @param road
	 *            - the road to be added to the gameBoard display
	 */
	public void addRoadToMap(Road road) {
		// sets the player color
		Color playerColor = getPlayerColor(road.getOwner());
		// sets the road start and end Points
		Point startPoint = road.getStartNode().getLocation();
		Point endPoint = road.getEndNode().getLocation();

		// creates panel with the line graphic of the road
		LineGraphic roadLine = new LineGraphic(startPoint, endPoint, playerColor, SCALE, BUFFER);
		// makes that panel see through so only the road will show up when it is
		// added to the frame
		roadLine.setOpaque(false);
		// sets the bounds to be the same as those of the panel it sits on
		roadLine.setBounds(panel.getBounds());
		// adds the road to the top layer
		frame.getContentPane().add(roadLine, 0);
		roadLine.setLayout(null);

	}// end addRoadToMap method

	/**
	 * 
	 * @param player
	 *            - player the color is being found for
	 * @return - A Color object holding matching the player's color
	 */
	private Color getPlayerColor(Player player) {
		playerColors playerColor = playerColors.valueOf(player.getPlayerColor());

		switch (playerColor) {
		case RED:
			return Color.RED;
		case BLUE:
			return Color.BLUE;
		case WHITE:
			return Color.WHITE;
		case YELLOW:
			return Color.YELLOW;
		// This statement should never be reached
		default:
			return Color.WHITE;
		}

	}// end getPlayerColor method

	/**
	 * Inner Main used for testing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// creates a game board object to pass into the constructor
		GameBoard gb = new GameBoard();

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					CatanWindow window = new CatanWindow(gb);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}// end CatanWindow class
