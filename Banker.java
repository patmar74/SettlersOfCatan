package edits419;

import java.util.ArrayList;

import otherPeoplesFiles.Person;
import otherPeoplesFiles.ResourceBank;
import otherPeoplesFiles.ResourceType;

/**
 * resourceClasses.Banker maintains all resources and facilitates trade
 * 
 * @author Patrick Martin & Dom
 *
 */
public class Banker {
	ResourceBank myBank;

	/**
	 * Constructor
	 */
	public Banker() {
		myBank = new ResourceBank();
	}

	/**
	 * Give resource to player
	 * 
	 * @param player
	 * @param type
	 */
	public void giveResource(Person player, ResourceType type, int qty) {
		for (int i = 0; i < qty; i++) {
			myBank.drawResource(type);
			player.addResource(type);
		}
	}

	/**
	 * Return the resource from the player to the bank
	 * 
	 * @param player
	 * @param type
	 */
	public void returnResource(Person player, ResourceType type, int qty) {
		for (int i = 0; i < qty; i++) {
			player.removeResource(type);
			myBank.returnToBank(type);
		}
	}

	/**
	 * Facilitates trade between players
	 * 
	 * @param initPlayer
	 *            Player initializing trade (their turn)
	 * @param initOfferArray
	 *            ArrayList<ResourceType> All the resources that the player is
	 *            offering
	 * @parm targetPlayer Player that is being traded with (not their turn)
	 * @param targetOfferArray
	 *            ArrayList<ResourceType> All the resources that the target
	 *            player is offering
	 */
	public void initPlayerTrade(Person initPlayer, ArrayList<ResourceType> initOfferArray, Person targetPlayer,
			ArrayList<ResourceType> targetOfferArray) {
		// Loop until initOfferArray is reduced to an empty arrayList
		// remove resource from trading player and add to target player

		while (initOfferArray.size() > 0) {
			ResourceType initOffer = initOfferArray.remove(0);// removes the
																// first card
																// from the
																// trading
																// player's
																// offering and
																// stores the
																// value in
																// initOffer
			initPlayer.removeResource(initOffer);// remove the resource card
													// from the players hand
			targetPlayer.addResource(initOffer); // add it to the target players
													// hand
		}
		while (targetOfferArray.size() > 0) {
			ResourceType targetOffer = targetOfferArray.remove(0); // same as
																	// above
																	// except
																	// target
																	// and
																	// player
																	// are
																	// reversed
			targetPlayer.removeResource(targetOffer);
			initPlayer.addResource(targetOffer);
		}
	} // end initPlayerTrade

	/**
	 * Processes Trades with known resource inputs and outputs
	 * 
	 * @param player
	 *            - Person making the trade
	 * @param offering
	 *            - the resource the player is trading
	 * @param requested
	 *            - the resource the player is receiving
	 * @param numOffering
	 *            - number of resources being traded
	 * @param numRequested
	 *            - number of resources bing received
	 * @return true if the trade is completed, false if it is not
	 */
	public boolean processTrade(Person player, ResourceType offering, ResourceType requested, int numOffering,
			int numRequested) {
		boolean tradeSucessfull = true;
		// checks to see if the player has enough resources to make the trade
		if (checkRemovePossible(player, offering, numOffering)) {

			// removes the resources the player is offering
			for (int i = 0; i < numOffering; i++) {
				player.removeResource(offering);
			}
			// adds the resources the player is requesting
			for (int i = 0; i < numRequested; i++) {
				player.addResource(requested);
			}

		} else {
			tradeSucessfull = false;
		}

		return tradeSucessfull;
	}// end processTrade method

	/**
	 * Check if removal of resource cards are possible
	 * 
	 * @param player
	 * @param myType
	 * @return boolean true if removal is possible
	 */
	public boolean checkRemovePossible(Person player, ResourceType myType, int requestedNumber) {
		ArrayList<ResourceType> hand = player.getHand(); // gets the arraylist
															// of the player's
															// resource cards
		int quantityAvailable = 0;
		// loop through entire hand
		for (ResourceType type : hand) {
			if (type.equals(myType)) {
				quantityAvailable += 1;// increase count of resource type total
			}
		}
		// if quantity of available resources is adequate for the trade then
		// return true
		if (quantityAvailable >= requestedNumber) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Gets Resource Bank Object. This is not for production code. Only used to
	 * test the Banker class.
	 * 
	 * @return
	 */
	public ResourceBank getResourceBank() {
		return myBank;
	}

}// end Class
