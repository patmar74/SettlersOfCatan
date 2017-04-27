package tests;

import boardClasses.BoardGrid;
import boardClasses.GameBoard;
import players.DirectionDecider;
import players.Player;
import players.RoadDirection;

import java.awt.*;

/**
 * Created by patrick on 4/24/17.
 */
public class testPlaceSettlement {
    public static void main(String[] args){
        GameBoard board = new GameBoard();
        Player player1 = new Player("Player1", Color.BLUE);
        Player player2 = new Player("Player2", Color.RED);
        System.out.println("Test 1");
        System.out.println("Trying to place settlement without a road attached and isSetting up is false, should fail");
        testGameBoard.placeAndShowSettlement(board,player1,3,0,false);

        System.out.println("Test 2");
        System.out.println("Trying to place settlement without a road attached and isSettingUp is true, should place");
        testGameBoard.placeAndShowSettlement(board,player1,3,0,true);

        System.out.println("Test 3");
        System.out.println("Trying to place settlement on out of bounds grid node, should fail");
        testGameBoard.placeAndShowSettlement(board,player1,3,-1,true);

        System.out.println("Test 4");
        System.out.println("Trying to place settlement 1 point away from another settlement with isSettingUp is true, should fail");
        testGameBoard.placeAndShowSettlement(board,player1,2,1,true);

        System.out.println("Test 5");
        System.out.println("Player1 Places 2 roads attached to existing settlement and player 2 places settlement at that point, isSettingUp is false should fail");
        testRoad.placeAndShowRoad(board,player1,new Point(3,0),new Point(2,1));
        testRoad.placeAndShowRoad(board,player1,new Point(2,1),new Point(2,2));
        testGameBoard.placeAndShowSettlement(board,player2,2,2,false);

        System.out.println("Test 6");
        System.out.println("Player1 now attempts to place settlement at end of his road, should place");
        testGameBoard.placeAndShowSettlement(board,player1,2,2, false);

        System.out.println("City Test 1");
        System.out.println("Player1 attempts to place a city on a spot that does not have a settlement, should fail");
        testGameBoard.placeAndShowCity(board,player1,3,3);

        System.out.println("City Test 2");
        System.out.println("Player2 attempts to place a city on player 2's settlement, should fail");
        testGameBoard.placeAndShowCity(board,player2,3,0);

        System.out.println("City Test 3");
        System.out.println("Player1 attempts to place a city on his settlement, should place");
        testGameBoard.placeAndShowCity(board,player1,3,0);

        System.out.println("City Test 4");
        System.out.println("Player1 attempts to place a city on a spot that already has a city, should fail.");
        testGameBoard.placeAndShowCity(board,player1,3,0);

        System.out.println("City Test 5");
        System.out.println("Player1 attempts to upgrade one of his settlements, should place");
        testGameBoard.placeAndShowCity(board,player1,2,2);

    }


}
