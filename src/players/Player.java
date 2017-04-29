package players;

import java.awt.*;
import java.lang.IllegalArgumentException;

import boardClasses.GameBoard;
import boardClasses.GridNode;
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
	private ArrayList<Road> roads = new ArrayList<>();

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
     * Get the ArrayList of Roads
     * @return The ArrayList of the Player's roads
     */
    public ArrayList<Road> getRoads(){
	    return roads;
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
    public ArrayList<ResourceType> buildOffering(int qtyWheat,int qtyWool,int qtyOre,int qtyLumber, int qtyBrick) throws IllegalArgumentException{
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
     * @param isSettingUp Boolean if the game is being setup still. This is important because the first two
     *           settlements placed DO NOT have to be attached to roads.
     * @return True if the placement was successful. This is useful to the GUI to show a message saying the placement
     * was not successful.
     */
    public boolean placeSettlement(GameBoard board, int x, int y, boolean isSettingUp){
        boolean placementSuccessful = true;
        GridNode targetNode = board.getGridNode(x,y);
        // If player has a settlement left to place attempt to place it on the board
        if(settlements.size() > 0){

            // If targetNode exists
            if (targetNode instanceof GridNode) {
                // If there is already a Settlement or City on the node then placement fails
                if(targetNode.getSettlement() instanceof Settlement){
                   placementSuccessful = false;
                   // If Another settlement is too close to the point, placement fails
                }else if(board.checkSettlementNearPoint(targetNode)){
                    placementSuccessful = false;
                    // If settlement placement not during setup, check if player's road is attached.
                }else if(!isSettingUp){
                    // If a player does not have a road attached to the target node, placement fails
                    if(!checkPlayerRoadConnected(targetNode)){
                        placementSuccessful = false;
                    }
                }
            } else { // targetNode does not exist, placement fails.
                placementSuccessful = false;
            }
        }else{ // player does not have enough settlements
            placementSuccessful = false;
        }

        if(placementSuccessful){
            // Place the settlement
            // remove the settlement from player's settlements and place it on the targetNode
            // and increment player points by 1
            Settlement settlementBeingPlaced = settlements.remove(0);
            targetNode.setSettlement(settlementBeingPlaced);
            points++;
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
        GridNode startGridNode = board.getGridNode(start);
        GridNode endGridNode = board.getGridNode(end);
        boolean placementSuccessful = checkRoadPlacementPossible(board,startGridNode,endGridNode);

        RoadDirection dirFromStart = DirectionDecider.getRoadDirection(start,end);
        // If placement is allowed then place the road
        if (placementSuccessful){
            // Remove a road from the player's roads
            Road rd = roads.remove(0);
            // Set road on GridNode on the Road branch, dirFromStart
            startGridNode.setRoadAt(rd,dirFromStart);
            // Set start GridNode to the Road object
            rd.setStartNode(startGridNode);

            endGridNode.setRoadAt(rd,DirectionDecider.getReflection(dirFromStart));
            rd.setEndNode(endGridNode);
            // checks for longest road and modifies the array lengths accordingly
            //ToDo check how this actually works and uncomment
            //checkLongestRoad(board,start,end);

        }
        return placementSuccessful;
    }

    private boolean checkRoadPlacementPossible(GameBoard board, GridNode startGridNode, GridNode endGridNode){
        boolean placementSuccessful = false;

        RoadDirection dirFromStart=null;

        if (startGridNode instanceof  GridNode && endGridNode instanceof GridNode) {
            // The settlement on the start GridNode, could return null
            Settlement startSettlement = startGridNode.getSettlement();
            // Get direction that the rode is to be built.
            dirFromStart = DirectionDecider.getRoadDirection(startGridNode.getLocation(),endGridNode.getLocation());
            // Check if the road direction is an option for the startGridNode
            if (checkDirectionForNode(startGridNode,dirFromStart)) {
                // Check if startSettlement is an instance of Settlement or a City, ie) Not null
                if(startSettlement instanceof Settlement){
                    //If start settlement is referenced by the player (owned by them)
                    if(startSettlement.getPlayer() == this){
                        placementSuccessful = true;
                        // Cannot place road if settlement is not the player's
                    }else{
                        placementSuccessful = false;
                    }
                    // The player does not have a settlement, check if they have a connecting road at start
                    // If there is already a road in the dirFromStart the road is being built, then the placement fails
                }else if(startGridNode.getRoadAt(dirFromStart) instanceof Road){
                    placementSuccessful = false;
                    // Check other branches for one of the player's roads
                } else if(checkOtherBranchesForRoad(startGridNode,dirFromStart)){
                    placementSuccessful = true;
                }
                // Not a valid end point for a road, cannot place road
            }else{
                placementSuccessful = false;
            }
            // One or more of the GridNodes returned null, placement fails.
        } else {
            placementSuccessful = false;
        }
        return placementSuccessful;
    }

    /**
     * Checks if the RoadDirection is an option for the GridNode
     * @param node GridNode being checked
     * @param attemptedDirection RoadDirection being checked
     * @return True if attemptedDirection is an option for the node
     */
    private boolean checkDirectionForNode(GridNode node, RoadDirection attemptedDirection){
        boolean directionInOptions = false;
        int i = 0;
        RoadDirection[] options = DirectionDecider.getDirectionOptions(node.getLocation());
        while(i < options.length && !directionInOptions){
            if(options[i].equals(attemptedDirection)){
                directionInOptions = true;
            }
            i++;
        }
        return directionInOptions;
    }
    /**
     * Check the road branches at the start gridNode for another road owned by the Player
     * @param start The GridNode where the road will start
     * @param directionBuilt The direction the road will be built
     * @return True if the GridNode has another road owned by the player attached to it.
     */
    private boolean checkOtherBranchesForRoad(GridNode start, RoadDirection directionBuilt){
        boolean playerRoadConnected = false;
        RoadDirection[] directions = DirectionDecider.getDirectionOptions(start.getLocation()); // Gets array of directions for that gridNode
        int i = 0;
                //Loop through until all directions are checked, but stop if a road is connected
        while(i< directions.length && !playerRoadConnected){
            RoadDirection dir = directions[i];
            // skip iteration if in same direction as to be built
            if(dir.equals(directionBuilt)){
                i++;
            }else{
                Road roadAtDir = start.getRoadAt(dir);
                i++;
                // If road at the current direction then check owner
                if(roadAtDir instanceof Road){
                    if(roadAtDir.getOwner() == this){
                        playerRoadConnected = true;
                    }
                }
            }
        }
        return playerRoadConnected;
    }

    /**
     * Check if a player's road is connected to a GridNode
     * @param target The GridNode being checked
     * @return True if a player's road is connected to the GridNode
     */
    // Check is a player's road is connected to
    private boolean checkPlayerRoadConnected(GridNode target){
        boolean roadConnected = false;
        for(Road rd: target.getAllRoads()){
            if(rd.getOwner() == this){
                roadConnected = true;
                break;
            }
        }
        return roadConnected;
    }

    /**
     * Places a city at a specific point and returns the settlement on the point to the player.
     * This also increases the player's points by one because it is replacing a settlement(1pt) with a city(2pts.)
     * The following are the conditions for successful placement:
     * The player still has cities, The GridNode at (x,y) exists, There is a settlement at the GridNode
     * The settlement at the GridNode is owned by the Player, The Settlement is not already a city
     * @param board The GameBoard object for the game
     * @param x The desired x position
     * @param y The desired y position
     * @return True if city placement was successful.
     */
    public boolean placeCity(GameBoard board,int x, int y) {
        boolean placementSuccessful = false;
        GridNode targetNode = board.getGridNode(x, y);
        if (cities.size() > 0) {
            // Check that the GridNode exists
            if (targetNode instanceof GridNode) {
                // Check that there is a settlement at the GridNode
                if (targetNode.getSettlement() instanceof Settlement) {
                    // Check that the settlement is owned by the player
                    if(targetNode.getSettlement().getPlayer() == this){
                        // Check that the settlement is not already a city
                        if(!(targetNode.getCity() instanceof City)){
                            placementSuccessful = true;
                        }
                    }
                }
            }
        }
        // if all conditions are satisfied, then the city is placed
        if (placementSuccessful) {
            // add Settlement on GridNode back to player's settlements ArrayList
            settlements.add(targetNode.getSettlement());
            // remove reference from GridNode
            targetNode.setSettlement(null);
            // remove a City and place it on the GridNode
            targetNode.setCity(cities.remove(0));
            points++;
        }
        return placementSuccessful;
    }

    public void addDevCard(DevCard card)
    {
        devCards.add(card);
    }
    public void removeDevCard(DevCard card)
    {
        devCards.remove(card);
    }

    //ToDo Fix longest road method and uncomment
//    // check specific for the longest road
//    private boolean checkRoadConnected(GridNode target, ArrayList<Road> roads)
//    {
//        //Target points to check if it is next to a road
//        int targetX = (int)target.getX();
//        int targetY = (int)target.getY();
//        // for loop cycles through all the points in the array that it is checking
//        // there can only be 2 arrays so we run this method twice for both as seen
//        // in the check
//        for(int i = 0; i< (roads).size(); i ++)
//        {
//            //road is set to current road and gridnodes are taken out to take position
//            //values
//            Road currentRoad = roads.get(i);
//            GridNode startTestNode = currentRoad.getStartNode();
//            GridNode endTestNode = currentRoad.getEndNode();
//            int startX = (int)startTestNode.getX();
//            int startY = (int)startTestNode.getY();
//            int endX = (int)endTestNode.getX();
//            int endY = (int)endTestNode.getY();
//            // if statement that checks if either node is next to another thus
//            //indicating that the roads are connected and we can return true
//            if((targetX + 1) == startX && (targetY + 1) == startY)
//            {
//                return true;
//            }
//            if((targetX - 1) == startX && (targetY + 1) == startY)
//            {
//                return true;
//            }
//            if((targetX + 1) == startX && (targetY - 1) == startY)
//            {
//                return true;
//            }
//            if((targetX - 1) == startX && (targetY - 1) == startY)
//            {
//                return true;
//            }
//            //end nodes
//            if((targetX + 1) == endX && (targetY + 1) == endY)
//            {
//                return true;
//            }
//            if((targetX - 1) == startX && (targetY + 1) == endY)
//            {
//                return true;
//            }
//            if((targetX + 1) == endX && (targetY - 1) == endY)
//            {
//                return true;
//            }
//            if((targetX - 1) == endX && (targetY - 1) == endY)
//            {
//                return true;
//            }
//        }
//        // if loop fails and no road is near the method returns
//        //false the roads are not near in the array
//        return false;
//    }
//    // invocation of the check if the roads are connected in the arrays to see
//    //whom has the longest road
//    public void checkLongestRoad(GameBoard board,Point start, Point end)
//    {
//        // start node of the new road that is created and end node used to compare
//        //to each value in each array as specified in the method above.
//        GridNode startNode = board.getGridNode(start);
//        GridNode endNode = board.getGridNode(end);
//        if(checkRoadConnected(startNode,roads1) == true || checkRoadConnected(endNode,roads1) == true)
//        {
//            // if method returns true and they are connected the length of the road is
//            //increased by 1
//            roads1Length++;
//        }
//        if(checkRoadConnected(startNode,roads2) == true || checkRoadConnected(endNode,roads2) == true)
//        {
//            roads2Length++;
//            // if method returns true and they are connected the length of the road is
//            //increased by 1
//        }
//        // for loop to compare each position in the arrays to one another to see if
//        // the roads are connected is an extension of joint **erase maybe?
//        for(int i = 0; i<roads1.size(); i++)
//        {
//            // get current road in the array
//            Road currentRoad = roads1.get(i);
//            GridNode newStartNode = currentRoad.getStartNode();
//            GridNode newEndNode = currentRoad.getEndNode();
//
//            if(checkRoadConnected(newStartNode, roads2) == true || checkRoadConnected(newEndNode, roads2) == true)
//            {
//                // returns combined length if joint
//                roadsJointLength = roads1Length + roads2Length;
//            }
//        }
//    }
//    //Checks if roads are joint
//    public boolean isJoint()
//    {
//        // same as above for loop but returns true/false if joint used in play class
//        // for flow
//        for(int i = 0; i<roads1.size(); i++)
//        {
//            Road currentRoad = roads1.get(i);
//            GridNode newStartNode = currentRoad.getStartNode();
//            GridNode newEndNode = currentRoad.getEndNode();
//
//            if(checkRoadConnected(newStartNode, roads2) == true || checkRoadConnected(newEndNode, roads2) == true)
//            {
//                roadsJointLength = roads1Length + roads2Length;
//                return true;
//            }
//        }
//        // returns false if not joint
//        return false;
//    }
//

}
