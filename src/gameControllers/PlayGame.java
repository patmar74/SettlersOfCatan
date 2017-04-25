package gameControllers;

import java.awt.*;
import java.util.ArrayList;

import boardClasses.GameBoard;
import boardClasses.Robber;
import guiClasses.DiceGUI;
import players.Player;
import resourceClasses.Banker;

/**
 * Each time someone wants to play Settlers of Catan, they will create an
 * instance of this class.
 * 
 * @author Jen, Trent, Tyler
 *
 */
public class PlayGame {
	// I am using an Array List under the assumption that the number of players
	// can change, if we choose to go to a set number of players, this will
	// become an Array with a set length equal to that set number of players
	private ArrayList<Player> playerList;
	private GameBoard board;
	private Banker banker;

	/**
	 * Sets up an instance of the Settlers Of Catan board game
	 */
	public PlayGame() {
		board = new GameBoard(); // sets up the gameboard
		setPlayers();
		twoRoundsOfGameSetUp();
		// the method we largely focused on
		gameLoop();
	}// end constructor

	/**
	 * Sets all of the
	 */
	private void setUpGameBoard() {
		/*
		 * A ton of stuff will be here
		 * 
		 * sets up the features of the game boards and the player lists
		 */

	}// end setUpGameBoard method

	/**
	 * Sets the Player list
	 */
	private void setPlayers() {
		/*
		 * Sets the names of the player and their player colors, also sets the
		 * player order. The player going first
		 */
	}// end setPlayers method

	/**
	 * Players place their first 8 settlements and roads
	 */
	private void twoRoundsOfGameSetUp() {
		// first round of game set up
		for (int playerIndex = 0; playerIndex < playerList.size(); playerIndex++) {
            // ToDo:request position
			playerList.get(playerIndex).placeSettlement(board,3,0);
			// ToDo: request position
			playerList.get(playerIndex).placeRoad(board,new Point(3,0),new Point(2,1));
		}
		// second round of game set up, occurs in reverse order
		for (int playerIndex = playerList.size(); playerIndex > 0; playerIndex--) {
		    // ToDo: request position
            playerList.get(playerIndex).placeSettlement(board,3,0);
            // ToDo: request position
            playerList.get(playerIndex).placeRoad(board,new Point(3,0),new Point(2,1));		}

	}// end twoRoundsOfGameSetUp method


	/**
	 * Loops the game through turns until a player collects 10 points
	 */
	private void gameLoop() {

		// keeps track of whose turn it is.
		int indexOfPlayerTakingTurn = 0;

		// loop runs while nobody has won the game
		while (!playerHasWon()) {
			// player who's turn it is takes their turn
			gameTurn(playerList.get(indexOfPlayerTakingTurn));

			// moves the index of the player taking the turn up one to the next
			// player
			indexOfPlayerTakingTurn++;
			// if the index is equal to the size, the last player in the list
			// has taken their turn, so the counter returns to the start of the
			// list, index 0
			if (indexOfPlayerTakingTurn == playerList.size()) {
				indexOfPlayerTakingTurn = 0;
			}
		} // end while loop

	}// end gameLoop method

	/**
	 * Checks to see if any player has won the game
	 * 
	 * @return - true if a player has accumulated 10 or more points (he/she won
	 *         the game), false if all players have less than ten points
	 */
	private boolean playerHasWon() {
		// loops through all of the players to see if one of them has 10 or more
		// points
		for (int playerIndex = 0; playerIndex < playerList.size(); playerIndex++) {
			if (playerList.get(playerIndex).getPoints() >= 10) {
				return true;
			}
		}
		return false;

	}// end checkIfVictory method

	/**
	 * Runs through the three phases of a turn in settlers of Catan, rolling the
	 * dice and distributing resources, making trades, and building or buying
	 * cards
	 * 
	 * @param playerTakingTurn
	 *            - the player taking his or her turn
	 */
	private void gameTurn(Player playerTakingTurn) {
		rollDice(playerTakingTurn);
		tradePhase(playerTakingTurn);
		constructionPhase(playerTakingTurn);
	}// end constructor

	/**
	 * Rolls the dice and then distributes resources or completes the robber
	 * actions if a 7 is rolled
	 * 
	 * @param playerTakingTurn
	 *            - player who rolls the dice and, if a 7 is rolled, moves the
	 *            robber
	 */
	private void rollDice(Player playerTakingTurn) {
		// calls the GUI that is used to roll the dice
		DiceGUI dice = new DiceGUI();
		dice.setVisible(true);

		// holding pattern that waits for a value to be assigned by the
		// action listener, the code will stick in this loop until the dice are
		// rolled (at that point the sum of the roll will no longer be zero)
		while (dice.getSumOfRoll() == 0) {
		}
		// sets the value of the dice roll
		int sumOfRoll = dice.getSumOfRoll();

		if (sumOfRoll == 7) {
			// code that executes if the robber is rolled
			Robber r = new Robber();
			r.stealHalfCards(playerTakingTurn,banker);
			r.moveRobber(board,playerTakingTurn);
		} else {
			// happens for any other roll other than 7
			distributeResources(sumOfRoll);
		}
	}// end rollDice method

	/**
	 * Distributes resources to the players. Players with settlements on
	 * vertices of Tiles who's token value matches the sum of the dice roll
	 * receive resources. They receive on resource per settlement, and two
	 * resources per city.
	 */
	private void distributeResources(int sumOfRoll) {
		banker.distributeResources(board,sumOfRoll);
	}// end distributedResources method

	/**
	 * Players make Trades Between Players, at Harbors, or with the Banker
	 * 
	 * @param playerTakingTurn
	 *            - the player making the trades
	 */
	private void tradePhase(Player playerTakingTurn) {

	}// end tradePhase methods

	/**
	 * build roads, build settlements, upgrade settlements to cities, or buy or
	 * play development cards (knights blocks roads?)
	 * 
	 * @param playerTakingTurn
	 *            - the player building or purchasing cards
	 */
	private void constructionPhase(Player playerTakingTurn) {

	}// end constructionPhase method

}// end PlayGame class
