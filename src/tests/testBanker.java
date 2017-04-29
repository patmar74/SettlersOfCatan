package tests;

import com.sun.javaws.exceptions.InvalidArgumentException;
import players.Player;
import players.playerColors;
import resourceClasses.Banker;
import resourceClasses.ResourceType;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by patrick on 4/13/17.
 */
public class testBanker {
    public static void main(String[] args){
        Player player1 = new Player("Player 1", Color.BLUE);
        Player player2 = new Player("Player 2", Color.RED);
        Banker myBank = new Banker();
        showBank(myBank);
        showHand(player1);
        showHand(player2);

        System.out.println("Bank gives player 1 a Brick");
        myBank.giveResource(player1, ResourceType.BRICK,1);
        showBank(myBank);
        showHand(player1);

        System.out.println("Bank gives player 2 a Lumber");
        myBank.giveResource(player2,ResourceType.LUMBER,3);
        showBank(myBank);
        showHand(player2);

        System.out.println("Player 1 trades a brick to player 2 for 3 lumber");

        try {
            myBank.initPlayerTrade(player1, player1.buildOffering(0,0,0,0,1),player2, player2.buildOffering(0,0,0,3,0));
        } catch (IllegalArgumentException e) {
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
        boolean tradeSuccessful = myBank.processTrade(player1,ResourceType.LUMBER,ResourceType.ORE,4,1);
        displayTradeSuccessful(tradeSuccessful);
        showBank(myBank);
        showHand(player1);

        showHand(player2);
        System.out.println("Player 2 gains 1 Brick from the bank and tries to do a 2:1 Brick Marine Trade for a Wheat");
        myBank.giveResource(player2, ResourceType.BRICK,1);
        tradeSuccessful = myBank.processTrade(player2, ResourceType.BRICK, ResourceType.WHEAT, 2,1);
        displayTradeSuccessful(tradeSuccessful);
        showBank(myBank);
        showHand(player2);
        System.out.println();
    }

    /**
     * Outputs hand to console
     * @param player
     */
    public static void showHand(Player player){
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
    public static void showBank(Banker myBanker){
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
