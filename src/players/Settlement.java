package players;

/**
 * Represents a settlement for the Settlers of Katan
 * @field location array of [x,y], set to [-1,-1] for settlements that have not been placed yet
 * @field isCity Boolean flag true if settlement is a city
 * @field color playerColors enum for the color of the settlement piece
 */
public class Settlement {
    private int[] location = new int[2];
    private boolean isCity;
    private playerColors color;

    /**
     * Constructor
     * @param color
     */
    public Settlement(playerColors color){
        this.color = color;
        isCity = false;
        location[0] = -1;
        location[1] = -1;
    }

    /**
     * Getter for settlement color
     * @return playerColors color of settlement
     */
    public playerColors getColor(){
        return color;
    }
    /**
     * Change settlement to city by toggling the isCity flag
     */
    public void convertToCity(){
        isCity = true;
    }

    /**
     * Get location of settlement
     * @return int[] [x,y]
     */
    public int[] getLocation(){
        return location;
    }

    /**
     * Set location of settlement
     * @param x
     * @param y
     */
    public void setLocation(int x, int y){
        location[0] = x;
        location[1] = y;
    }
}
