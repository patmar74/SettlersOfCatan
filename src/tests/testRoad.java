package tests;

import boardClasses.GameBoard;
import players.DirectionDecider;
import players.Player;
import players.Road;
import players.RoadDirection;

import java.awt.*;

/**
 * Created by patrick on 4/13/17.
 */
public class testRoad {
    public static void main(String[] args){
        Player player1 = new Player("Player1", Color.BLACK);
        Player player2 = new Player("Player2", Color.BLUE);
        GameBoard board = new GameBoard();
        showRoads(player1);
        showRoads(player2);

        System.out.println("Test 1");
        System.out.println("no settlement placed yet, should not place");
        placeAndShowRoad(board,player1,new Point(3,0),new Point(2,1));

        System.out.println("Test 2");
        testGameBoard.placeAndShowSettlement(board,player1,3,0, true);
        System.out.println( "settlement placed, valid start point, end point out of bounds");
        placeAndShowRoad(board,player1, new Point(3,0), new Point (3,-1));

        System.out.println("Test 3");
        System.out.println("settlement placed but invalid end point, should not place");
        placeAndShowRoad(board,player1,new Point(3,0),new Point(2,5));

        System.out.println("Test 4");
        System.out.println("settlement placed, valid end point, should be placed");
        placeAndShowRoad(board,player1, new Point(3,0), new Point(2,1));


        System.out.println("Test 5");
        System.out.println("road placed, valid end point, should be placed");
        placeAndShowRoad(board, player1, new Point(2,1), new Point (2,2));

        System.out.println("Test 6");
        System.out.println("not player's settlement, should not be placed");
        placeAndShowRoad(board, player2, new Point(3,0),new Point(4,1));

        System.out.println("Test 7");
        System.out.println("road already exits in destination, should not be placed");
        placeAndShowRoad(board,player1, new Point(3,0), new Point(2,1));

        System.out.println("Test 8");
        System.out.println("previous road is not players, end point is valid and not already used, should not be placed");
        placeAndShowRoad(board, player2, new Point (2,2), new Point(1,3));

        System.out.println("Test 9");
        System.out.println("start point valid, end point not valid because on a tile, should not be placed");
        placeAndShowRoad(board,player1,new Point (2,2), new Point (3,1));


    }

    public static void showRoads(Player owner){
        System.out.println(owner.getName() + " has " + owner.getRoads().size() + " roads.");
    }

    public static void placeAndShowRoad(GameBoard board,Player p,Point start, Point end){
        System.out.println(p.getName() + " attempts to place road at {(" + start.x + "," + start.y + "), (" + end.x + ","+ end.y + ")}" );
        boolean roadPlaced = p.placeRoad(board,start,end);
        System.out.println("Road placed = " + roadPlaced);
        RoadDirection buildDirection = DirectionDecider.getRoadDirection(start,end);

        if (roadPlaced) {
            Road road = board.getGridNode(start).getRoadAt(buildDirection);
            Point roadStart = road.getStartNode().getLocation();
            Point roadEnd = road.getEndNode().getLocation();
            System.out.print("Road owned by: " + road.getOwner().getName());
            System.out.print(" starts at (" + roadStart.x + "," + roadStart.y + ")");
            System.out.print(" ends at (" + roadEnd.x + "," + roadEnd.y + ")");
        }
        showRoads(p);
        System.out.println();
    }


}
