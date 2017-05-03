package tests;

import boardClasses.GameBoard;
import boardClasses.Robber;
import players.Player;
import resourceClasses.Banker;
import resourceClasses.ResourceType;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by patrick on 5/2/17.
 */
public class testRobber {
    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        Player p1 = new Player("Player1", Color.BLUE);
        Player p2 = new Player("Player2", Color.BLACK);
        Player p3 = new Player("Player3", Color.RED);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        Banker myBanker = new Banker();
        testTiles.showTiles(board);
        testGameBoard.placeAndShowSettlement(board,p1,3,0,true);
        testGameBoard.placeAndShowSettlement(board,p2, 2,2,true);
        testGameBoard.placeAndShowSettlement(board,p3, 4,2, true);
        testBanker.showHand(p3);
        myBanker.giveResource(p3,ResourceType.BRICK,9);
        testBanker.showHand(p3);
        System.out.println("Location of Robber is now " + board.getIndexOfRobber());
        Robber.moveRobber(board,p1);
        testBanker.showHand(p1);
        testBanker.showHand(p3);
        System.out.println("Location of Robber is now "+ board.getIndexOfRobber());

        myBanker.giveResource(p2,ResourceType.LUMBER, 7);
        myBanker.giveResource(p1,ResourceType.LUMBER,3);
        testBanker.showHand(p1);
        testBanker.showHand(p2);
        testBanker.showHand(p3);
        Robber.stealHalfCards(players,myBanker);
        testBanker.showHand(p1);
        testBanker.showHand(p2);
        testBanker.showHand(p3);
    }
}