package tests;

import boardClasses.GameBoard;

import players.Player;

import resourceClasses.Banker;

import java.awt.*;


/**
 * Created by patrick on 4/20/17.
 */
public class testGameBoard {
    public static void main(String[] args){
        GameBoard myBoard = new GameBoard();
        Banker myBanker = new Banker();
        Player player1 = new Player("Player 1", Color.BLUE);
        Player player2 = new Player("Player 2", Color.RED);
        testTiles.showTiles(myBoard.getTiles(),myBoard.getGrid());
        System.out.println();
        showPlayerSettlements(player1);
        showPlayerSettlements(player2);
        System.out.println();

        testPlacement(myBoard,player1,3,0);
        testPlacement(myBoard,player2, 2,1);
        testPlacement(myBoard,player1,2,1);

        int diceRoll =  myBoard.getTiles().getTile(0).getToken().getNumber();
        System.out.println("Tile 0 is a: "+myBoard.getTiles().getTile(0).getResource().toString() + " with dice roll: " + diceRoll);
        myBanker.distributeResources(myBoard,diceRoll);
        testBanker.showHand(player1);
        testBanker.showHand(player2);
        testBanker.showBank(myBanker);
        System.out.println();
        System.out.println("Robber moved to Tile 0");
        myBoard.moveRobber(0);
        myBanker.distributeResources(myBoard,diceRoll);
        testBanker.showHand(player1);
        testBanker.showHand(player2);
        testBanker.showBank(myBanker);


    }
    private static void showPlayerSettlements(Player myPlayer){
        System.out.println(myPlayer.getName() + " has " + myPlayer.getPlayerSettlements().size() + " settlements.");
    }

    private static void testPlacement(GameBoard board, Player myPlayer, int x, int y){
        showPlayerSettlements(myPlayer);
        System.out.println(myPlayer.getName() + " attempting to place a settlement at " + x + "," + y);
        boolean successful = myPlayer.placeSettlement(board,x,y);
        System.out.println("Placement successful = " + successful );
        showPlayerSettlements(myPlayer);
        System.out.println();
    }
}
