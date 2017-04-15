package tests;

import players.Person;
import players.playerColors;
import resourceClasses.Banker;
import resourceClasses.ResourceType;

import java.util.ArrayList;

/**
 * Created by patrick on 4/13/17.
 */
public class testBanker {
    public static void main(String[] args){
        Person player1 = new Person("Player 1",playerColors.BLUE);
        Person player2 = new Person("Player 2", playerColors.RED);
        Banker myBank = new Banker();
        myBank.giveResource(player1, ResourceType.BRICK);
        myBank.giveResource(player2,ResourceType.LUMBER);


    }

    /**
     * Outputs hand to console
     * @param player
     */
    public void showHand(Person player){
        ArrayList<ResourceType> myHand = player.getHand();
        for(ResourceType card: myHand){
            System.out.print(card + " ");
        }
    }

    /**
     * Outputs bank deck number
     * @param myBanker
     */
    public void showBank(Banker myBanker){

    }
}
