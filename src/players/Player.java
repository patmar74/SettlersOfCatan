package players;

import java.awt.*;
import java.lang.IllegalArgumentException;

import boardClasses.GameBoard;
import boardClasses.GridNode;
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

	private Color playerColor;
	// The players' individual colors are held in an enum in the same project.
	private int points;
	private String name;
	private ArrayList<Settlement> settlements = new ArrayList<>();
	private ArrayList<City> cities = new ArrayList<>();
	//ToDo remove comment once road is available
	private ArrayList<Road> roads;

	private ArrayList<DevCard> devCards = new ArrayList<>();

	// Arraylist of resource cards player has in their hand
	private ArrayList<ResourceType> hand = new ArrayList<>();

    /**
     * Constructor gives a player name, player color, and initializes 4 player settlements, and 15 roads
     * @param name The name of the player
     * @param playerColor The Color to be associated with the Player
     */
	public Player(String name, Color playerColor){
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
	        roads.add(new Road(this));
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
     * @throws IllegalArgumentException if any quantities cannot be fulfilled. ie) Hand has 1 wheat, but 2 wheat are
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


	public ArrayList<Settlement> getSettlements() {
		return settlements;
	}
    public ArrayList<City> getCities() { return cities;}

	public String getPlayerColor() {
		return playerColor.toString();
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

    /**
     * Place one of the players Settlements on the board, if a Settlement is already on the desired GridNode, then the
     * placement fails. If the player does not have any more settlements, the placement fails. If placement succeeds,
     * the player places one of their settlements on the board and gains a point.
     * @param board The GameBoard object for the game
     * @param x X coordinate of the desired GridNode to place the settlement
     * @param y Y coordinate of the desired GridNode to place the settlement
     * @return True if the placement was successful. This is useful to the GUI to show a message saying the placement
     * was not successful.
     */
    public boolean placeSettlement(GameBoard board, int x, int y){
        //ToDo Add test to see if one of the player's roads is attached to the desired point.
        //ToDo Add test to see if point is 2 edges away from any other settlement.
        boolean placementSuccessful = true;
        // If player has a settlement left to place attempt to place it on the board
        if(settlements.size() > 0){
            GridNode targetNode = board.getGridNode(x,y);
            // If there is already a Settlement or City on the node then placement fails
            if(targetNode.getSettlement() instanceof Settlement){
               placementSuccessful = false;
            }else {
                // remove the settlement from player's settlements and place it on the targetNode
                // and increment player points by 1
                Settlement settlementBeingPlaced = settlements.remove(0);
                targetNode.setSettlement(settlementBeingPlaced);
                points++;
            }
        }else{
            placementSuccessful = false;
        }
        return placementSuccessful;
    }

    /**
     * Places a road on the board that is either starting at a player's settlement, or a player's road that is
     * already on the board. The road will serve as branches for the tree that will determine the longest road.
     * @param board The GameBoard object for the game
     * @param start The Point that starts the road, this corresponds with a GridNode
     * @param end The Point that ends the road, this corresponds with a GridNode
     * @return True if the road placement was successful
     */
    public boolean placeRoad(GameBoard board, Point start, Point end){
        boolean placementSuccessful = true;
        // The settlement on the start GridNode, could return null
        Settlement startSettlement = board.getGridNode(start.x,start.y).getSettlement();
        if(startSettlement.getPlayer() == this || )
        return placementSuccessful;
    }

    /**
     * Determines which direction the road will be built based on the Start and End points
     * @param start The Point that starts the road, this corresponds with a GridNode
     * @param end The Point that ends the road, this corresponds with a GridNode
     * @return RoadDirection The direction that the road will be built, returns null if start and end points are not
     * within one edge of each other.
     * @nullable
     */
    private RoadDirection getRoadDirection(Point start, Point end){
        // North if x does not change and y decreases by 1
        if(end.x == start.x && end.y - start.y == -1){
            return RoadDirection.NORTH;
            // South West if x decreases by 1 and y increases by 1
        }else if(end.x - start.x == -1 && end.y - start.y == 1){
            return RoadDirection.SOUTH_WEST;
            //South East if x increases by 1 and y increases by 1
        }else if(end.x - start.x == 1 && end.y - start.y == 1){
            return RoadDirection.SOUTH_EAST;
            // Not a valid road placement, return null
        }else{
            return null;
        }
    }



}
