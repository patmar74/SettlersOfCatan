package players;

/**
 * Represents a settlement for the Settlers of Catan
 * @field myPlayer The player who owns the settlement
 * @field multiplier int The multiplier for the number of resources the player will receive
 */
public class Settlement {

    private Player myPlayer;
    protected int multiplier = 1; // a multiplier of 1 for a settlement, meaning the player gets only 1 resource

    /**
     * Constructor
     * @param myPlayer The Player who is the owner of the settlement
     */
    public Settlement(Player myPlayer){
        this.myPlayer = myPlayer;
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
    public Player getPlayer(){return myPlayer;}





}
