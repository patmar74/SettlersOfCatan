package resourceClasses;

import players.Person;
import java.util.ArrayList;

/**
 * resourceClasses.Banker maintains all resources and facilitates trade
 * @author Patrick Martin & Dom
 *
 */
public class Banker {
	ResourceBank myBank;

	/**
	 * Constructor
	 */
	public Banker(){
		myBank = new ResourceBank();
	}

    /**
     * Give resource to player
     * @param player
     * @param type
     */
	public void giveResource(Person player, ResourceType type){
	    myBank.drawResource(type);
	    player.addResource(type);
    }

    /**
     * Return the resource from the player to the bank
     * @param player
     * @param type
     */
    public void returnResource(Person player, ResourceType type){
	    player.removeResource(type);
	    myBank.returnToBank(type);
    }
	/**
	 * Facilitates trade between players
	 * @param initPlayer Player initializing trade (their turn)
	 * @param initOfferArray ArrayList<ResourceType> All the resources that the player is offering
	 * @parm targetPlayer Player that is being traded with (not their turn)
	 * @param targetOfferArray ArrayList<ResourceType> All the resources that the target player is offering
	 */
	public void initPlayerTrade(Person initPlayer, ArrayList<ResourceType> initOfferArray, Person targetPlayer, ArrayList<ResourceType> targetOfferArray){
		// Loop until initOfferArray is reduced to an empty arrayList
		// remove resource from trading player and add to target player
		
		while (initOfferArray.size() > 0){
			ResourceType initOffer = initOfferArray.remove(0);// removes the first card from the trading player's offering and stores the value in initOffer
			initPlayer.removeResource(initOffer);// remove the resource card from the players hand
			targetPlayer.addResource(initOffer); // add it to the target players hand
		}
		while (targetOfferArray.size() > 0){
			ResourceType targetOffer = targetOfferArray.remove(0); // same as above except target and player are reversed
			targetPlayer.removeResource(targetOffer);
			initPlayer.addResource(targetOffer);
		}
	} // end initPlayerTrade
	
	/**
	 * Facilitates marine trade
	 * @param player
	 * @param offering resourceClasses.ResourceType
	 * @param requestedType
	 * @return tradeSuccessful
	 */
	public boolean marineTrade(Person player, ResourceType offering, ResourceType requestedType, int quantityNeeded){
		boolean tradeSuccessful = true; 
		// if player has the necessary resources then check that 
		if (checkRemovePossible(player, offering, quantityNeeded)){
			ResourceType myType = myBank.drawResource(requestedType);// returns null if not enough resources in the bank
			// if bank does not have enough resources for the trade then the trade fails
			if (myType != null){
				player.addResource(myType);// draws the requested resource from the bank and adds it to the players hand
				for (int i = 0; i < quantityNeeded; i++) {
					player.removeResource(offering);
				} 
			}else{
				tradeSuccessful = false; // bank does not have the resources so trade fails
			}
		}else{
			tradeSuccessful = false; // player does not have the resources so trade fails
		}
		return tradeSuccessful;
	}
	
	/**
	 * Universal trade which is 4 of a specific type for any one resource from the bank
	 * @param player
	 * @param offering
	 * @param requestedType
	 * @return
	 */
	public boolean universalTrade(Person player, ResourceType offering, ResourceType requestedType){
		boolean tradeSuccessful = true; 
		// if player has the necessary resources then check that 
		if (checkRemovePossible(player, offering, 4)){
			ResourceType myType = myBank.drawResource(requestedType);
			// if bank does not have enough resources for the trade then the trade fails
			if (myType != null){
				player.addResource(myType);// draws the requested resource from the bank and adds it to the players hand
				for (int i = 0; i < 4; i++) {
					player.removeResource(offering);
				} 
			}else{
				tradeSuccessful = false; // bank does not have the resources so trade fails
			}
		}else{
			tradeSuccessful = false; // player does not have the resources so trade fails
		}
		return tradeSuccessful;
	}
	
	
/**
 * Check if removal of resource cards are possible
 * @param player
 * @param myType
 * @return boolean true if removal is possible
 */
	private boolean checkRemovePossible(Person player,ResourceType myType, int requestedNumber){
		ArrayList<ResourceType> hand = player.getHand(); // gets the arraylist of the player's resource cards
		int quantityAvailable = 0;
		// loop through entire hand
		for (ResourceType type: hand){
			if (type.equals(myType)){
				quantityAvailable += 1;// increase count of resource type total
			}
		}
		// if quantity of available resources is adequate for the trade then return true
		if(quantityAvailable >= requestedNumber ){
			return true;
		} else{
			return false;
		}
		
	}

}//end Class
