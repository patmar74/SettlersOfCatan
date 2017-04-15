package developmentCards;

import players.Person;
import resourceClasses.Banker;
import resourceClasses.ResourceType;

import java.util.ArrayList;

/**
 * Represents a development card
 * @author Patrick Martin & Alexis 
 *
 */
public class DevCard {
	// Player myPlayer     Player that currently holds the development card
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

	/**
	 * Change the state of drawnThisTurn flag
	 */
	public void toggleDrawnThisTurn(){
		if(wasDrawnThisTurn){
			wasDrawnThisTurn = false;
		}else{
			wasDrawnThisTurn = true;
		}

	}
// ToDo place roads action
    /**
     * Place 2 roads on the board
     */
//	public doAction(GameBoard myBoard, location1, location2){
//	    myBoard.buildRoad(location1);
//      myBoard.buildRoad(location2);
//  }

    /**
     *
     * Overloaded method Does action for the Knight card
     * Moves the robber to a new tile
     * @param myRobber
     */
    //ToDo move robber action
//	public doAction(GameBoard myBoard, newLocation){
//	    // myBoard.moveRobber(newLocation);
//   }

    /**
     * Overloaded do action method for the Year of Plenty Dev card
     * Player will select two resources and gain them from the banker
     * @param player
     * @param firstRequest
     * @param secondRequest
     */
    public void doAction(Person player, ResourceType firstRequest, ResourceType secondRequest, Banker myBanker){
        myBanker.giveResource(player,firstRequest);
        myBanker.giveResource(player, secondRequest);
    }

    /**
     * Overloaded method for doing the action of the Monopoly card
     * Gets all resource cards of a requested type from all other players
     * and gives them to the player that used the dev card.
     */
    public void doAction(Person player, ArrayList<Person> players, ResourceType resourceRequested){
        int totalCardsGained = 0; // useful only for Monopoly card;
        for (Person myPlayer:players){
            if (myPlayer != player) { // if myPlayer has same address as player then skip it
                int cardsLost = countResources(myPlayer,resourceRequested);
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

    /**
     * Return number of cards in a player's hand that match the resource type
     * @param player
     * @param type
     * @return
     */
    private int countResources(Person player, ResourceType type){
	    int total = 0;
	    ArrayList<ResourceType> hand = player.getHand();
	    for (ResourceType card: hand){
	        if (card.equals(type)){
	            total++;
            }
        }
        return total;
    }

	
}
