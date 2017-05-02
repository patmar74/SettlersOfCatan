package tests;

import boardClasses.GameBoard;

import boardClasses.GridNode;
import boardClasses.Tile;
import players.City;
import players.Player;

import players.Settlement;
import resourceClasses.Banker;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by patrick on 4/20/17.
 */
public class testGameBoard {
    public static void main(String[] args){
        GameBoard myBoard = new GameBoard();
        Banker myBanker = new Banker();
        Player player1 = new Player("Player 1", Color.BLUE);
        Player player2 = new Player("Player 2", Color.RED);
        testTiles.showTiles(myBoard);
        System.out.println();
        showPlayerSettlements(player1);
        showPlayerSettlements(player2);
        System.out.println();

        placeAndShowSettlement(myBoard,player1,7,8, true);
        placeAndShowSettlement(myBoard,player2, 2,1, false);
        placeAndShowSettlement(myBoard,player1,2,2,true);

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

        testTiles.showTiles(myBoard);
        ArrayList<Tile> tilesConnected = myBoard.findTiles(player1.getStartingNode(1));
        System.out.println("Resources attached to "  + "(7,8) are: ");
        for(Tile t: tilesConnected){
            System.out.print(t.getResource() + ",");
        }

        tilesConnected = myBoard.findTiles(myBoard.getGridNode(5,0));
        System.out.println("Resources attached to "  + "(5,0) are: ");
        for(Tile t: tilesConnected){
            System.out.print(t.getResource() + ",");
        }


    }
    public static void showPlayerSettlements(Player myPlayer){
        System.out.println(myPlayer.getName() + " has " + myPlayer.getSettlements().size() + " settlements.");
    }

    public static void placeAndShowSettlement(GameBoard board, Player myPlayer, int x, int y, boolean isSettingUp){
        showPlayerSettlements(myPlayer);
        System.out.println(myPlayer.getName() + " attempting to place a settlement at " + x + "," + y);
        boolean successful = myPlayer.placeSettlement(board,x,y,isSettingUp);
        System.out.println("Placement successful = " + successful );
        showPlayerSettlements(myPlayer);
        if(board.getGridNode(x,y) instanceof GridNode){
            System.out.println("(" + x + ","+y + ") has a settlement = " +  (board.getGridNode(x,y).getSettlement() instanceof Settlement));
        }
        System.out.println();
    }

    public static void placeAndShowCity(GameBoard board, Player myPlayer, int x, int y){
        showPlayerSettlements(myPlayer);
        System.out.println(myPlayer.getName() + " attempting to place a city at " + x + "," + y);
        boolean successful = myPlayer.placeCity(board,x,y);
        System.out.println("Placement successful = " + successful );
        showPlayerSettlements(myPlayer);
        System.out.println(myPlayer.getName() + " has " + myPlayer.getCities().size() + " cities");
        System.out.println(myPlayer.getName() + " has " + myPlayer.getPoints() + " points.");
        if(board.getGridNode(x,y) instanceof GridNode){
            System.out.println("(" + x + ","+y + ") has a city = " +  (board.getGridNode(x,y).getCity() instanceof City));
        }
        showPlayerSettlements(myPlayer);
        System.out.println();
    }
}
