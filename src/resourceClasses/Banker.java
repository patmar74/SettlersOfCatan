package resourceClasses;

import boardClasses.GameBoard;
import boardClasses.GridNode;
import boardClasses.Tile;
import boardClasses.Tiles;
import developmentCards.DevCard;
import players.Player;
import players.Settlement;

import java.util.ArrayList;
import java.util.Set;


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
	public void giveResource(Player player, ResourceType type, int qty) {
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
	public void returnResource(Player player, ResourceType type, int qty) {
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
	public void initPlayerTrade(Player initPlayer, ArrayList<ResourceType> initOfferArray, Player targetPlayer,
								ArrayList<ResourceType> targetOfferArray) {
		// Loop until initOfferArray is reduced to an empty arrayList
		// remove resource from trading player and add to target player

		while (initOfferArray.size() > 0) {
            // removes the first card from the trading player's offering and stores the value in initOffer
			ResourceType initOffer = initOfferArray.remove(0);
            // remove the resource card from the players hand
			initPlayer.removeResource(initOffer);
            // add it to the target players hand
			targetPlayer.addResource(initOffer);

		}
		while (targetOfferArray.size() > 0) {
			ResourceType targetOffer = targetOfferArray.remove(0);
			// same as above except target and player are reversed
			targetPlayer.removeResource(targetOffer);
			initPlayer.addResource(targetOffer);
		}
	} // end initPlayerTrade

	/**
	 * Processes Trades with known resource inputs and outputs
	 *
	 * @param player
	 *            - Player making the trade
	 * @param offering
	 *            - the resource the player is trading
	 * @param requested
	 *            - the resource the player is receiving
	 * @param numOffering
	 *            - number of resources being traded
	 * @param numRequested
	 *            - number of resources being received
	 * @return true if the trade is completed, false if it is not
	 */
	public boolean processTrade(Player player, ResourceType offering, ResourceType requested, int numOffering,
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
	public boolean checkRemovePossible(Player player, ResourceType myType, int requestedNumber) {
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

    /**
     * Distributes resources to all players with settlements on tiles with the input dice roll value
     * @param board
     * @param diceRoll
     */
    public void distributeResources(GameBoard board, int diceRoll){
        ArrayList<Tile> tilesWithDiceRoll = board.getTilesWithDiceRoll(diceRoll);
        // Loop through all tiles with dice roll to get all settlements on the tile and distribute the tile's resource
        // to the player who owns the settlement.
        for(Tile resourceTile:tilesWithDiceRoll) {
            // If resource tile is not the Desert then continue to check tile, else skip it
            if(!resourceTile.getResource().equals(ResourceType.DESERT)){
                // If resourceTile has does not have the robber then continue onto the tilePoints.
                if (!resourceTile.getHasRobber()) {
                    // Loop through all 6 gridPoints on the tile and check if they have a settlement.
                    // If they do have a settlement then distribute resources to owner of the settlement.
                    for (GridNode tilePoint : resourceTile.getTilePoints()) {
                        Settlement currentSettlement = tilePoint.getSettlement(); // if no settlement at point then returns null
                        // check if currentSettlement references a Settlement object, this also covers City objects since
                        // City class inherits from Settlement class
                        if (currentSettlement instanceof Settlement) {
                            giveResource(currentSettlement.getPlayer(), resourceTile.getResource(), currentSettlement.getMultiplier());
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets an array for the quantity of resource cards needed to purchase the option desired.
     * [WOOL,ORE,LUMBER,BRICK,WHEAT]
     * @param option The option that is desired for purchase.
     * @return int[] Where the order is [WOOL,ORE,LUMBER,BRICK,WHEAT]
     */
    public int[] getQtyNeededToPurchase(PurchaseOptions option){
        int[] qtyNeeded = new int[5];
        int qtyWool = 0;
        int qtyOre = 0;
        int qtyLumber = 0;
        int qtyBrick = 0;
        int qtyWheat = 0;
        switch (option){
            case SETTLEMENT:{
                qtyWool = 1;
                qtyBrick = 1;
                qtyLumber = 1;
                qtyWheat = 1;
                break;
            }
            case CITY:{
                qtyOre = 3;
                qtyWheat = 2;
                break;
            }
            case ROAD:{
                qtyLumber = 1;
                qtyBrick = 1;
                break;
            }
            case DEV_CARD:{
                qtyOre = 1;
                qtyWheat = 1;
                qtyWool = 1;
                break;
            }
        }
        //[WOOL,ORE,LUMBER,BRICK,WHEAT]
        qtyNeeded[0] = qtyWool;
        qtyNeeded[1] = qtyOre;
        qtyNeeded[2] = qtyLumber;
        qtyNeeded[3] = qtyBrick;
        qtyNeeded[4] = qtyWheat;
        return qtyNeeded;
    }

    /**
     * Checks if a purchase is possible for the player
     * @param myPlayer The player doing the purchasing
     * @param qtyRequired [WOOL,ORE,LUMBER,BRICK,WHEAT]
     * @return True if player has enough resources to make the purchase
     */
    public boolean checkPurchasePossible(Player myPlayer, int[] qtyRequired){
        // creates resources array of all ResourceTypes in the order they were declared
        // this includes the Desert so we will not use resources[5]
        ResourceType[] resources = ResourceType.values();
        int i = 0; // index for looping through resources[]
        boolean isPossible = true;
        for(int qty:qtyRequired){
            isPossible = checkRemovePossible(myPlayer,resources[i],qty);
            // if removal is not possible then break out of loop
            if(!isPossible){
                break;
            }
            i++;
        }
        return  isPossible;
    }
    /**
     * Removes resources for purchase from player's hand
     * @param myPlayer The player doing the purchasing
     * @param qtyRequired [WOOL,ORE,LUMBER,BRICK,WHEAT]
     */
    public void removeResourcesForPurchase(Player myPlayer, int[] qtyRequired){
        // creates resources array of all ResourceTypes in the order they were declared
        // this includes the Desert so we will not use resources[5]
        ResourceType[] resources = ResourceType.values();
        int i = 0; // index for looping through resources[]
        for(int qty: qtyRequired){
            returnResource(myPlayer,resources[i],qty);
            i++;
        }

    }

    /**
     * Rolls back a completed purchase.
     * This is due to the player possibly changing their mind after making the purchase.
     * The player will get the same resources that they spent to purchase the item.
     * @param myPlayer The player doing the purchasing
     * @param qtyRequired [WOOL,ORE,LUMBER,BRICK,WHEAT]
     */
    public void rollBackPurchase(Player myPlayer, int[] qtyRequired){
        // creates resources array of all ResourceTypes in the order they were declared
        // this includes the Desert so we will not use resources[5]
        ResourceType[] resources = ResourceType.values();
        int i = 0; // index for looping through resources[]
        for(int qty: qtyRequired){
            giveResource(myPlayer,resources[i],qty);
            i++;
        }
    }
	public void giveDevelopment(Player player, DevCard card)
	{
		player.addDevCard(card);
	}
	public void removeDevelopment(Player player, DevCard card)
	{
		player.removeDevCard(card);
	}

}// end Class
