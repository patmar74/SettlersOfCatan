package players;

/**
 * Represents a settlement for the Settlers of Catan
 * @field owner The player who owns the settlement
 * @field multiplier int The multiplier for the number of resources the player will receive
 */
public class Settlement {

    private Player owner;
    protected int multiplier = 1; // a multiplier of 1 for a settlement, meaning the player gets only 1 resource
    private int[] location = new int[2]; // [x,y]the coordinate location of the settlement, used for converting to a city

    /**
     * Constructor
     * @param owner The Player who is the owner of the settlement
     */
    public Settlement(Player owner){
        this.owner = owner;
    }

    /**
     * Gets the multiplier for the settlement
     * @return The multiplier for the settlement
     */
    public int getMultiplier(){
        return multiplier;
    }

    /**
     * Gets the player associated with the settlement.
     * @return The player associated with the settlement
     */
    public Player getPlayer(){return owner;}

    /**
     * Set the location of the settlement
     * This will be called when the player places the settlement.
     * @param x X coordinate of the settlement
     * @param y Y coordinate of the settlement
     */
    public void setLocation(int x, int y){
        location[0] = x;
        location[1] = y;
    }

    /**
     * Gets the location [x,y] array of the settlement
     * @return
     */
    public int[] getLocation(){
        return location;
    }

}
