package players;

import java.lang.IllegalArgumentException;

import com.sun.javaws.exceptions.InvalidArgumentException;
import developmentCards.DevCard;
import resourceClasses.ResourceType;

import java.util.ArrayList;

/**
 * Player class represents a player in the game
 * @author Kellyn, Joey, Tyler, & Patrick
 * @throws IllegalArgumentException If a player wants to offer more resource cards than he has in his hand
 * ie) Player has 1 wheat, but wants to offer 2 wheat
 */
public class Player {

	private playerColors playerColor;
	// The players' individual colors are held in an enum in the same project.
	private int points;
	private String name;
	private ArrayList<Settlement> settlements = new ArrayList<>();
	private ArrayList<City> cities = new ArrayList<>();
	//ToDo remove comment once road is available
	//private ArrayList<road> roads;
	private int playerTurn;
	private ArrayList<DevCard> devCards = new ArrayList<>();

	// Arraylist of resource cards player has in their hand
	private ArrayList<ResourceType> hand = new ArrayList<>();

    /**
     * Constructor gives a player name, player color, and initializes 4 player settlements, and 15 roads
     * @param name
     * @param playerColor
     */
	public Player(String name, playerColors playerColor){
	    this.name = name;
	    this.playerColor = playerColor;
	    // add 5 new settlements to the settlements ArrayList
	    for(int i = 0;i<5; i++){
	        settlements.add(new Settlement(this));
        }
        // add 4 new cities to the cities ArrayList
        for(int i = 0; i<4; i++){
	        cities.add(new City(this));
        }

        // add 15 roads to the roads ArrayList
        for(int i = 0; i<15;i++){
	        //ToDo remove comment once Road class is made and added to project
	        //roads.add(new Road(playerColor));
        }
    }
	/**
	 * Add resource to hand
	 */
	public void addResource(ResourceType resource){
	    hand.add(resource);
    }

    public void removeResource(ResourceType resource){
	    hand.remove(resource);
    }

    /**
     * Gets a deep copy of the player's hand.
     * This makes sure that the hand is not changed due incidentally
     * @return ArrayList<ResourceType> A Deep copy of the players hand
     */
    public ArrayList<ResourceType> getHand(){
        ArrayList<ResourceType> copy = new ArrayList<>();
        for(ResourceType resource: hand){
            copy.add(resource);
        }
        return copy;
    }

    /**
     * Makes a "hand" of all the resources that the player wants to offer as a trade
     * @param qtyWheat Quantity of wheat being offered
     * @param qtyWool Quantity of wool being offered
     * @param qtyOre Quantity of ore being offered
     * @param qtyLumber Quantity of lumber being offered
     * @param qtyBrick Quantity of brick being offered
     * @return ArrayList<ResourceType> All resources the player wants to offer as a trade
     * @throws IllegalArgumentException if any quantities cannot be fullfilled. ie) Hand has 1 wheat, but 2 wheat are
     * being offered.
     */
    public ArrayList<ResourceType> buildOffering(int qtyWheat,int qtyWool,int qtyOre,int qtyLumber, int qtyBrick) throws InvalidArgumentException{
        ArrayList<ResourceType> copiedHand = getHand();
        ArrayList<ResourceType> offering = new ArrayList<>();
        // If enough wheat is available then add it to the offering
        if(checkOfferValid(qtyWheat,ResourceType.WHEAT,copiedHand)){
            for(int i=0;i<qtyWheat;i++){
                offering.add(ResourceType.WHEAT);
            }
        }else{
            throw new IllegalArgumentException("Player does not have enough resources.");
        }
        // If enough wool is available then add it to the offering
        if(checkOfferValid(qtyWool,ResourceType.WOOL,copiedHand)){
            for(int i=0;i<qtyWool;i++){
                offering.add(ResourceType.WOOL);
            }
        }else{
            throw new IllegalArgumentException("Player does not have enough resources.");
        }
        // If enough Ore is available then add it to the offering
        if(checkOfferValid(qtyOre,ResourceType.ORE,copiedHand)){
            for(int i=0;i<qtyOre;i++){
                offering.add(ResourceType.ORE);
            }
        }else{
            throw new IllegalArgumentException("Player does not have enough resources.");
        }
        // If enough lumber is available then add it to the offering
        if(checkOfferValid(qtyLumber,ResourceType.LUMBER,copiedHand)){
            for(int i=0;i<qtyLumber;i++){
                offering.add(ResourceType.LUMBER);
            }
        }else{
            throw new IllegalArgumentException("Player does not have enough resources.");
        }
        // If enough brick is available then add it to the offering
        if(checkOfferValid(qtyBrick,ResourceType.BRICK,copiedHand)){
            for(int i=0;i<qtyBrick;i++){
                offering.add(ResourceType.BRICK);
            }
        }else{
            throw new IllegalArgumentException("Player does not have enough resources.");
        }
        return offering;
    }

    /**
     * Checks if enough resources are available to add the resource to the offering
     * This is useful to check if the buildOffering should throw an exception
     * @param qty int Quantity of resource wanted
     * @param resource ResourceType Resource method
     * @param copy ArrayList<ResourceType> A deep copy of the player's hand
     * @return True if player's hand has the resources to fulfill offer
     */
    private boolean checkOfferValid(int qty, ResourceType resource, ArrayList<ResourceType> copy){
        int qtyAvailable=0;
        // Loop through copy of hand and increment qtyAvailable if resource matched the current resource type
        for(ResourceType card: copy){
            if(card.equals(resource)){
                qtyAvailable++;
            }
        }
        return qtyAvailable>=qty;
    }

    /**
     * Get the number of a specific resource in the player's hand.
     * This is useful to the GUI so that the values can be updated.
     * @param resource ResourceType The resource type that is being totaled
     * @return int The number of resource cards in the player's hand that are of selected type.
     */
    public int getNumberInHand(ResourceType resource){
        int numberInHand=0;
        for(ResourceType card:hand){
            if(card.equals(resource)){
                numberInHand++;
            }
        }
        return numberInHand;
    }


	public ArrayList<Settlement> getPlayerSettlements() {
		return settlements;
	}
    public ArrayList<City> getPlayerCities() { return cities;}
//ToDo add get road method
//	public ArrayList<road> getPlayerRoad() {
//		return playerRoad;
//	}
// ToDo add set road method
//	public void setPlayerRoad(ArrayList<road> playerRoad) {
//		this.playerRoad = playerRoad;
//	}

	public String getPlayerColors() {
		return playerColor.toString();
	}

	// Takes in an enum of type players.playerColors, which holds
    // all of the colors used by the board game for the individual
    // players.

	public void setPlayerColors(playerColors playerColor) {
		this.playerColor = playerColor;
	}


    // Return's the player's current points.
	public int getPoints() {
		return points;
	}


    // Used by the actual game class to add/subtract points
    // from player, such as in events of [ADD EVENT HERE]***.
	public void setPoints(int points) {
		this.points = points;
	}


    // Returns the player's name.
	public String getName() {
		return name;
	}

    // Used to set the player's names to be displayed for events that
    // occur within the game.

	public void setName(String name) {
		this.name = name;
	}




}
