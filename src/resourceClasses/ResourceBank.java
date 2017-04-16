package resourceClasses;

import java.util.ArrayList;

/**
 * Resource Deck represents the full deck of resources
 * @field  ArrayList of resource card objects, 19 of each kind
 * @author Patrick Martin & Alexis McCaffery
 *
 */
public class ResourceBank {
	private ArrayList<ResourceType> woolBank = new ArrayList<>();
	private ArrayList<ResourceType> oreBank = new ArrayList<>();
	private ArrayList<ResourceType> lumberBank = new ArrayList<>();
	private ArrayList<ResourceType> brickBank = new ArrayList<>();
	private ArrayList<ResourceType> wheatBank = new ArrayList<>();
	
	/**
	 * Constructor, creates a Resource Bank of all the resource cards
	 *  
	 */
	public ResourceBank(){
		woolBank = createResourceDeck(ResourceType.WOOL);// create a deck of 19 Sheep
		oreBank = createResourceDeck(ResourceType.ORE);// create a deck of 19 ORE
		lumberBank = createResourceDeck(ResourceType.LUMBER);// create a deck of 19 Lumber
		brickBank = createResourceDeck(ResourceType.BRICK);	// create a deck of 19 Brick
		wheatBank = createResourceDeck(ResourceType.WHEAT);	// create a deck of 19 Wheat
	}
	
	/**
	 * Create an ArrayList for a deck of resource cards used for the resource bank
	 * @param type
	 * @return ArrayList<resourceClasses.ResourceType> Deck of 19 of the same resource
	 */
	private ArrayList<ResourceType> createResourceDeck(ResourceType type){
		ArrayList<ResourceType> myResourceDeck = new ArrayList<>();
		
		// Adds 19 new resource cards with same type;
		for(int i=0; i<19; i++){
			myResourceDeck.add(type);
		}
		return myResourceDeck;
	}
	/**
	 * Used by the drawSource method to reduce redundancy
	 * @param bank respective bank arraylist
	 * @return the card drawn, or null if no card is available to be drawn
	 */
	private ResourceType drawFromBank(ArrayList<ResourceType> bank){
		if (!bank.isEmpty()) {
			return bank.remove(0); // respective bank is not empty
		} else{
			System.out.println("There are no more cards in this stack; you cannot draw your card.");
			return null; //bank is empty
		}
	}
	
	/**
	 * Draw a card from the appropriate resource deck 
	 * @param type
	 * @return myCard A resource card of the input Type
	 */
	public ResourceType drawResource(ResourceType type){
		// Switch checks if the resource bank is empty and assigns myCard the resourceClasses.ResourceType stored at the 0 index
		// The card is then removed from the bank
		switch(type){
			case WOOL:
				return drawFromBank(woolBank);
			case ORE:
				return drawFromBank(oreBank);
			case LUMBER:
				return drawFromBank(lumberBank);
			case BRICK:
				return drawFromBank(brickBank);
			case WHEAT:
				return drawFromBank(wheatBank);

		}
		return null; //program flow should never reach this point
	}
	/**
	 * Returns the card to the proper bank
	 * @param type Resource card type
	 */
	public void returnToBank(ResourceType type) {
        switch (type) {
            case WOOL:
                woolBank.add(type);
                break;
            case ORE:
                oreBank.add(type);
                break;
            case LUMBER:
                lumberBank.add(type);
                break;
            case BRICK:
                brickBank.add(type);
                break;
            case WHEAT:
                wheatBank.add(type);
                break;
        }

    }
    /**
     * Outputs to the console the number of resources in each deck
     */
    public void getAllBankStats(){
        System.out.print("Wool:" + woolBank.size() + ", ");
        System.out.print("Ore:" + oreBank.size() + ", ");
        System.out.print("Wheat:" + wheatBank.size() + ", ");
        System.out.print("Lumber:" + lumberBank.size() + ", ");
        System.out.print("Brick:" + brickBank.size() + "\n");
    }

}
