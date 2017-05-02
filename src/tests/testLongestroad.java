package tests;

import boardClasses.GameBoard;
import players.Player;

import java.awt.*;

/**
 * Created by patrick on 5/1/17.
 */
public class testLongestroad {
    public static void main(String[] args){
        GameBoard board = new GameBoard();
        Player p1 = new Player("Player1", Color.BLACK);
        Player p2 = new Player("Player2", Color.BLUE);
        int length;
        System.out.println("Player1 sets settlement at (4,2) and (4,5)");
        System.out.println(p1.placeSettlement(board,4,2,true));
        System.out.println(p1.placeSettlement(board,4,5, true));
        System.out.println( "Road placed: " + p1.placeRoad(board,new Point(4,2),new Point(3,3)));
        System.out.println("Player 1 places road from (4,2) to (3,3)");
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result Should be 1\n");

        System.out.println("Player 1 places road from (4,2) to (4,1)");
        System.out.println(p1.placeRoad(board, new Point(4,2),new Point(4,1)));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result Should be 2\n");

        System.out.println("Player1 places road from (4,2) to (5,3)");
        System.out.println(p1.placeRoad(board, new Point(4,2),new Point(5,3)));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result Should still be 2\n");

        System.out.println("Player1 places road from (3,3) to (2,2)");
        System.out.println(p1.placeRoad(board, new Point(3,3),new Point(2,2)));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result Should still be 3\n");

        System.out.println("Player1 places road from (4,1) to (5,0)");
        System.out.println(p1.placeRoad(board, new Point(4,1),new Point(5,0)));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result Should be 4\n");

//        System.out.println("Player1 places road from (5,0) to (6,1)");
//        System.out.println(p1.placeRoad(board, new Point(5,0),new Point(6,1)));
//        board.findAndSetPlayerRoadLength(p1);
//        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
//        System.out.println("Result Should still be 5\n");

        System.out.println("Player2 places a settlement at (5,0) cutting off the end of player1's road");
        System.out.println(p2.placeSettlement(board,5,0,true));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result Should be 4\n");

        System.out.println("Player1 places road from (6,1) to (7,0)");
        System.out.println(p1.placeRoad(board, new Point(6,1),new Point(7,0)));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result Should still be 4 because Player 2's settlement has cut the road already\n");

        System.out.println("Player1 places road from (5,3) to (5,4)");
        System.out.println(p1.placeRoad(board, new Point(5,3),new Point(5,4)));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result Should still be 4\n");

        System.out.println("Player1 places road from (5,4) to (4,5)");
        System.out.println(p1.placeRoad(board, new Point(5,4),new Point(4,5)));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result Should be 5\n");

        System.out.println("Player1 places road from (4,5) to (3,4)");
        System.out.println(p1.placeRoad(board, new Point(4,5),new Point(3,4)));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result Should be 6\n");

        System.out.println("Player1 places road from (5,4) to (6,5)");
        System.out.println(p1.placeRoad(board, new Point(5,4),new Point(6,5)));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result Should still be 6\n");

        System.out.println("Player1 places road from (6,5) to (7,4)");
        System.out.println(p1.placeRoad(board, new Point(6,5),new Point(7,4)));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result should still be 6\n");

        System.out.println("Player1 places road from (7,4) to (8,5)");
        System.out.println(p1.placeRoad(board, new Point(7,4),new Point(8,5)));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result should be 7\n");

        System.out.println("Player1 places road from (7,4) to (7,3)");
        System.out.println(p1.placeRoad(board, new Point(7,4),new Point(7,3)));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result should still be 7\n");

        testRoad.placeAndShowRoad(board,p1,new Point(7,3), new Point(8,2));
        board.findAndSetPlayerRoadLength(p1);
        System.out.println("Player 1 road length = " + p1.getLongestRoadLength());
        System.out.println("Result should be 8\n");


        System.out.println("Player2 places settlement at (5,7) and puts a road down from (5,7) to (4,6) and (5,0) to (6,1)");
        testGameBoard.placeAndShowSettlement(board,p2,5,7,true);
        placeAndShowRoadAndLength(board,p2,new Point(5,7), new Point(4,6));
        placeAndShowRoadAndLength(board,p2,new Point(5,0), new Point(6,1));
        System.out.println("Result should be 1\n\n");

        System.out.println("Player2 places roads at (6,1)to(7,0), (7,0)to(8,1),(8,1)to(8,2), (8,2),(9,3) , (9,3)to(9,4)" +
                "(9,4)to(8,5), (8,5)to(8,6), (8,6)to(7,7), (7,7)to(6,6)");
        placeAndShowRoadAndLength(board,p2,new Point(6,1), new Point(7,0));
        placeAndShowRoadAndLength(board,p2,new Point(7,0), new Point(8,1));
        placeAndShowRoadAndLength(board,p2,new Point(8,1), new Point(8,2));
        placeAndShowRoadAndLength(board,p2,new Point(8,2), new Point(9,3));
        placeAndShowRoadAndLength(board,p2,new Point(9,3), new Point(9,4));
        placeAndShowRoadAndLength(board,p2,new Point(9,4), new Point(8,5));
        placeAndShowRoadAndLength(board,p2,new Point(8,5), new Point(8,6));
//        placeAndShowRoadAndLength(board,p2,new Point(8,6), new Point(7,7));
//        placeAndShowRoadAndLength(board,p2,new Point(7,7), new Point(6,6));
//        System.out.println("Result should be 10");
//
//        System.out.println("Player 2 places roads from (5,7)to(6,6) and (6,6)to(6,5");
//        placeAndShowRoadAndLength(board,p2,new Point(5,7), new Point(6,6));
//        placeAndShowRoadAndLength(board,p2,new Point(6,6), new Point(6,5));
//        System.out.println("Result should be 12");

        System.out.println("Player2 places roads at (8,6)to(9,7)to(10,6)to(10,5)to(9,4) creating a loop for one branch");
        placeAndShowRoadAndLength(board,p2,new Point(8,6), new Point(9,7));
        placeAndShowRoadAndLength(board,p2,new Point(9,7), new Point(10,6));
        placeAndShowRoadAndLength(board,p2,new Point(10,6), new Point(10,5));
        placeAndShowRoadAndLength(board,p2,new Point(10,5), new Point(9,4));
        System.out.println("Result should be 12");
    }

    private static void placeAndShowRoadAndLength(GameBoard board, Player p, Point start, Point end){
        testRoad.placeAndShowRoad(board,p,start,end);
        board.findAndSetPlayerRoadLength(p);
        System.out.println(p.getName() + " road length = " + p.getLongestRoadLength());
    }
}
