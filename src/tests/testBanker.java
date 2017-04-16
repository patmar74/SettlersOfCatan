package tests;

import com.sun.javaws.exceptions.InvalidArgumentException;
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
        showBank(myBank);
        showHand(player1);
        showHand(player2);

        System.out.println("Bank gives player 1 a Brick");
        myBank.giveResource(player1, ResourceType.BRICK,1);
        showBank(myBank);
        showHand(player1);

        System.out.println("Bank gives player 2 a Lumber");
        myBank.giveResource(player2,ResourceType.LUMBER,1);
        showBank(myBank);
        showHand(player2);

        System.out.println("Player 1 trades a brick to player 2 for lumber");

        try {
            myBank.initPlayerTrade(player1, player1.buildOffering(0,0,0,0,1),player2, player2.buildOffering(0,0,0,1,0));
        } catch (InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
        showBank(myBank);
        showHand(player1);
        showHand(player2);
        System.out.println("Bank gives player1 3 lumber");
        myBank.giveResource(player1,ResourceType.LUMBER,3);
        showBank(myBank);
        showHand(player1);

        System.out.println("Player 1 does universal trade of 4 lumber for 1 Ore");
        boolean tradeSuccessful = myBank.universalTrade(player1,ResourceType.LUMBER,ResourceType.ORE);
        displayTradeSuccessful(tradeSuccessful);
        showBank(myBank);
        showHand(player1);

        showHand(player2);
        System.out.println("Player 2 gains 1 Brick from the bank and tries to do a 2:1 Brick Marine Trade for a Wheat");
        myBank.giveResource(player2, ResourceType.BRICK,1);
        tradeSuccessful = myBank.marineTrade(player2, ResourceType.BRICK, ResourceType.WHEAT, 2);
        displayTradeSuccessful(tradeSuccessful);
        showBank(myBank);
        showHand(player2);

    }

    /**
     * Outputs hand to console
     * @param player
     */
    private static void showHand(Person player){
        System.out.print(player.getName() + ":");
        ArrayList<ResourceType> myHand = player.getHand();
        for(ResourceType card: myHand){
            System.out.print(card + " ");
        }
        System.out.print("\n");
    }

    /**
     * Outputs bank deck numbers
     * @param myBanker
     */
    private static void showBank(Banker myBanker){
        myBanker.getResourceBank().getAllBankStats();
    }

    /**
     * Outputs whether or not the trade was successful to console
     * @param tradeSuccessful
     */
    private static void displayTradeSuccessful(boolean tradeSuccessful){
        if(tradeSuccessful){
            System.out.println("The trade was successful");
        }else{
            System.out.println("The trade was not successful");
        }

    }
}
