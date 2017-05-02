package tests;

import boardClasses.GameBoard;
import players.Player;

import java.awt.*;

/**
 * Created by patrick on 5/2/17.
 */
public class testLongestRoadImplementation {
    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        Player p1 = new Player("Player1", Color.cyan);
        Player p2 = new Player("Player2", Color.RED);

        testGameBoard.placeAndShowSettlement(board,p1,4,2,true);
        testRoad.placeAndShowRoad(board,p1,new Point(4,2), new Point(5,3));

        testGameBoard.placeAndShowSettlement(board,p2, 6,6,true);
        testRoad.placeAndShowRoad(board,p2,new Point(6,6), new Point(6,5));

        testGameBoard.placeAndShowSettlement(board,p1,8,2, true);
        testRoad.placeAndShowRoad(board,p1,new Point(8,2), new Point(8,1));

        testGameBoard.placeAndShowSettlement(board,p2,2,9, true);
        testRoad.placeAndShowRoad(board,p2,new Point(2,9), new Point(3,8));

        placeAndShowRoad(board,p1,new Point(5,3), new Point(5,4));
        placeAndShowRoad(board,p1,new Point(5,4), new Point(4,5));
        placeAndShowRoad(board,p1,new Point(4,5), new Point(3,4));
        System.out.println();
        System.out.println("Should start calculating road length now on next placement");
        placeAndShowRoad(board,p1,new Point(3,4), new Point(3,3));


        placeAndShowRoad(board,p2,new Point(6,5), new Point(5,4));
        testGameBoard.placeAndShowSettlement(board,p2,5,4,false);
        System.out.println();
        System.out.println("Should recalculate Player1's longest road because Player2's settlement just cut the road");
        System.out.println("Player1 now has a longest road of " + p1.getLongestRoadLength() + " roads");

    }
    private static void placeAndShowRoad(GameBoard board, Player p, Point start, Point end){
        testRoad.placeAndShowRoad(board,p,start,end);
        System.out.println(p.getName() + " has a longest road of " + p.getLongestRoadLength() +" roads\n");
    }
}