package tests;

import boardClasses.GameBoard;
import players.Player;
import players.playerColors;
import resourceClasses.Banker;


/**
 * Created by patrick on 4/20/17.
 */
public class testGameBoard {
    public static void main(String[] args){
        GameBoard myBoard = new GameBoard();
        Banker myBanker = new Banker();
        Player player1 = new Player("Me", playerColors.BLUE);
        Player player2 = new Player("You", playerColors.RED);
        myBoard.getGrid().getNode(3,0).setSettlement(player1.getPlayerSettlements().get(0));
        myBoard.getGrid().getNode(2,2).setSettlement(player2.getPlayerSettlements().get(0));
        int diceRoll =  myBoard.getTiles().getTile(0).getToken().getNumber();
        System.out.println("Tile 0 is a: "+myBoard.getTiles().getTile(0).getResource() + "with dice roll: " + diceRoll);
        myBanker.distributeResources(myBoard,diceRoll);
        testBanker.showHand(player1);
        testBanker.showHand(player2);
        testBanker.showBank(myBanker);






    }
}
