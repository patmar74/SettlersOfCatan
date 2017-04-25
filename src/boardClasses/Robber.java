package boardClasses;

import players.Player;
import resourceClasses.Banker;

/**
 * Created by patrick on 4/24/17.
 */

// ToDo: Complete robber class
public class Robber {
    public void moveRobber(GameBoard board,Player playerTakingTurn){
        // ToDo ask for user input
        int tileIndex = 0;
        board.moveRobber(tileIndex); // place on chosen tile
    }
    // ToDo: steal half of cards and return them to the bank
    public void stealHalfCards(Player player, Banker banker){

    }

    // ToDo: steal a single card from a player and give it to the player that moved the robber
    public void stealCard(Player initPlayer,Player targetPlayer){

    }


}
