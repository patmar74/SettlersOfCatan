package developmentCards;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by patrick on 4/26/17.
 */
public class DevCards {
    //Array of DevCards that will represent the Deck
    ArrayList<DevCard> deck  = new ArrayList<>();
    public DevCards()
    {
       setupDeck();
       shuffleDeck();
    }

    private void setupDeck()
    {
        //Current position in the array of Deck that will be assigned
        //Each position of the Deck is assigned here
        for(int knight = 0; knight < 14; knight++)
        {
            deck.add(new DevCard(DevCardType.KNIGHT));
        }
        for(int victory = 0; victory < 5; victory++)
        {
            deck.add(new DevCard(DevCardType.VICTORY_POINT));
        }
        for(int road = 0; road < 2; road++)
        {
            deck.add(new DevCard(DevCardType.ROAD_BUILDING));
        }
        for(int monopoly = 0; monopoly < 2; monopoly++)
        {
            deck.add(new DevCard(DevCardType.MONOPOLY));
        }
        for(int yearOfPleanty = 0; yearOfPleanty < 2; yearOfPleanty++)
        {
            deck.add(new DevCard(DevCardType.YEAR_OF_PLENTY));

        }
    }

    private void shuffleDeck(){
        ArrayList<DevCard> shuffled = new ArrayList<>();
        Random rand = new Random();
        int i = 0;
        while(deck.size()>0){
            i = rand.nextInt(deck.size());
            shuffled.add(deck.remove(i));
        }
        deck = shuffled; // set reference to the shuffled deck

    }

    /**
     * Remove top card from the deck and return it
     * @return The top development card on the development card deck.
     */
    public DevCard removeCardFromDeck(){
        return deck.remove(0);
    }

}
