package developmentCards;

import boardClasses.GameBoard;
import players.Player;
import resourceClasses.Banker;
import resourceClasses.ResourceType;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a development card
 * @author Patrick Martin & Alexis 
 *
 */
public class DevCard {
	Player myPlayer;     // Player that currently holds the development card
	boolean wasDrawnThisTurn; // Flag if the card was drawn this turn
	DevCardType type;

	/**
	 * Constructor
	 * @param type DevCardType
	 */
	public DevCard(DevCardType type){
		this.type = type;
		wasDrawnThisTurn = false;
	}

	public void setPlayer(Player myPlayer){
	    this.myPlayer = myPlayer;
    }

	/**
	 * Change the Flag for if the DevCard was drawn this turn to true
     * This will be called at the start of the player's turn
	 */
	public void setDrawnThisTurn(){
		wasDrawnThisTurn = false;
	}

    /**
     * Overloaded method Does action for the Road Building card
     * Place 2 roads on the board, this will need to have a prompt for the user to select the points.
     */
    public void doAction(GameBoard board, Point road1Start, Point road1End, Point road2Start, Point road2End){
	   myPlayer.placeRoad(board,road1Start,road2End);
	   myPlayer.placeRoad(board,road2Start,road2End);
    }

    /**
     * Overloaded method Does action for the Knight card
     * Moves the robber to a new tile, and adds the Knight card to the Player's knights ArrayList
     * @param
     */
	public void doAction(GameBoard myBoard, int newLocation){
	    myBoard.moveRobber(newLocation);
        myPlayer.addKnight(this);
   }

    /**
     * Overloaded do action method for the Year of Plenty Dev card
     * Player will select two resources and gain them from the banker
     * @param player
     * @param firstRequest First resource type that the player wants to gain
     * @param secondRequest Second resource type that the player wants to gain,
     *                      this can be the same as the firstRequest
     */
    public void doAction(Player player, ResourceType firstRequest, ResourceType secondRequest, Banker myBanker){
        myBanker.giveResource(player,firstRequest,1);
        myBanker.giveResource(player, secondRequest,1);
    }

    /**
     * Overloaded method for doing the action of the Monopoly card
     * Gets all resource cards of a requested type from all other players
     * and gives them to the player that used the dev card.
     */
    public void doAction(Player player, ArrayList<Player> players, ResourceType resourceRequested){
        int totalCardsGained = 0; // useful only for Monopoly card;
        for (Player myPlayer:players){
            if (myPlayer != player) { // if myPlayer has same address as player then skip it
                int cardsLost = player.countResources(resourceRequested);
                totalCardsGained += cardsLost;
                for(int i=0;i<cardsLost; i++){
                    myPlayer.removeResource(resourceRequested);
                }
            }
        }
        // add all stolen resource cards to the player's hand
        for(int i=0;i<totalCardsGained;i++){
            player.addResource(resourceRequested);
        }
    }



	
}
