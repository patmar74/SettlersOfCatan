package tests;

import boardClasses.GameBoard;
import boardClasses.GridNode;
import boardClasses.Tile;
import players.Settlement;
import players.playerColors;

import java.util.ArrayList;

/**
 * Created by patrick on 4/20/17.
 */
public class testGameBoard {
    public static void main(String[] args){
        GameBoard myBoard = new GameBoard();
        myBoard.getGrid().getNode(3,0).setSettlement(new Settlement(playerColors.BLUE));
        System.out.println(myBoard.getTiles().getTile(0).getTilePoints().get(0).getSettlement().getColor());





    }
}
