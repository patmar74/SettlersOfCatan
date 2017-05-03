package boardClasses;

import players.Player;
import resourceClasses.Banker;
import resourceClasses.ResourceType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import static javax.swing.JOptionPane.showInputDialog;

/**
 * Robber class holds static methods that either move the robber on the board, or steal from other people.
 */

// ToDo: Complete robber class
public class Robber {
    public static void moveRobber(GameBoard board, Player playerTakingTurn) {
        int tileIndex = 0;
        Object[] tilesArray = board.getTiles().getTilesArray().toArray();
        Tile nextTile = (Tile)JOptionPane.showInputDialog(null,"Select a tile to move the Robber to:","Move The Robber",JOptionPane.PLAIN_MESSAGE, null ,tilesArray,board.getTiles().getTile(0));
        tileIndex = nextTile.getIndex();
        board.moveRobber(tileIndex); // place on chosen tile

        ArrayList<Player> players = nextTile.getOtherPlayersWithSettlements(playerTakingTurn);

        if (players.size()>0) {
            Object[] playersArray = players.toArray();
            Player targetPlayer = (Player)JOptionPane.showInputDialog(null,"Select a Player to Steal From",
                    "Select a Player",JOptionPane.PLAIN_MESSAGE, null ,playersArray,playersArray[0]);
            stealCard(playerTakingTurn,targetPlayer);
        }
    }

    // ToDo: steal half of cards and return them to the bank
    public static void stealHalfCards(ArrayList<Player> players, Banker banker) {
        Random rdm = new Random();
        for(Player p: players){
            ArrayList<ResourceType> hand = p.getHand();
            // if hand is greater than or equal to seven discard half
            if(hand.size()>=7){
                int halvedSize = hand.size()/2;
                for(int i = 0; i<halvedSize;i++){
                    ResourceType selectedResource = selectResource(hand,p);
                    banker.returnResource(p,selectedResource,1);
                }

            }
        }
    }

    // ToDo: steal a single card from a player and give it to the player that moved the robber
    private static void stealCard(Player initPlayer, Player targetPlayer) {
        Random rdm = new Random();
        ArrayList<ResourceType> targetPlayerHand = targetPlayer.getResources();
        int size = targetPlayerHand.size();
        if(size>0){
            int i = rdm.nextInt(targetPlayerHand.size());
            initPlayer.addResource(targetPlayerHand.remove(i));
        }else{
            JOptionPane.showMessageDialog(null,"You tried to steal from a player with no resources, you get nothing!");
        }

    }

    private static ResourceType selectResource(ArrayList<ResourceType> resources, Player p){
        ResourceType selectedType;
        Object[] resourcesArray = resources.toArray();
        selectedType = (ResourceType)JOptionPane.showInputDialog(null,p.getName() + " Select a Resource Card to Discard",
                "Select a Resource Card",JOptionPane.PLAIN_MESSAGE, null ,resourcesArray,resourcesArray[0]);
        return selectedType;
    }
}

//    /**
//     *
//     * Created by patrick on 4/24/17.
//     */
//// steal card and return to player that moved it
//
////
//    public class Robber {
//
//        private Player playerTakingTurn;
//        private ArrayList<Tile> tileList;
//        private Tile destinationTile;
//        private Tile tile;
//        private GameBoard board;
//        private ArrayList<Player> playerList;
//
//
//        public Robber(Player playerTakingTurn, Tile tile, ArrayList<Tile> tileList, ArrayList<Player> playerList, GameBoard board) {
//
//            this.playerTakingTurn = playerTakingTurn;
//            this.tileList = tileList;
//            this.board = board;
//            this.tile = tile;
//            this.playerList = playerList;
//
//            //runRobber()
//            //    moveRobber
//            //        set all panels as as not having robber
//            //        get user selection for destination
//            //        get robber on next panel
//            //    takeCard
//            //        last players w/settlments on next tile
//            //        select one to take from
//            //        take resource(provided they have it)
//            //    removeExcessResources
//            //        loop moving through Player(for loop)
//            //        remove resources(while loop)
//
//
//
//        }
//
//        /**
//         * This method moves the Robber's location on the game board from one place to another.
//         * @return
//         * @param board
//         * @param playerTakingTurn
//         * @param tile
//         */
//        public void moveRobber(GameBoard board,Player playerTakingTurn, Tile tile){
//
//            int tileIndex = 0;
//            board.moveRobber(tileIndex); // place on chosen tile
//        /*
//         *
//         */
//            int tileChosen;
//            // User selection for tile to move robber
//            Scanner scanner = new Scanner(System.in);
//            tileChosen = scanner.nextInt();
//            // Some sort of movement of robber
//            destinationTile = tileList.get(tileChosen);
//            destinationTile.setHasRobber(true);
//
//            /**
//             *
//             * scanner takes destinationTile (0-18),
//             *
//             *
//             */
//
//
//            int test;
//            test = board.getIndexOfRobber();
//            //tile.setHasRobber(true).board.getIndexOfRobber();
//
//            /**
//             *
//             * this portion needs work
//             *
//             */
//
//
//            for (int i = 0; i < tileList.size(); i++) {
//                if (tileList.get(i).getHasRobber() == true) {
//                    tileList.get(i).setHasRobber(false);
//                }
//            }
//
//
//            System.out.println(test);
//
//
//
//        }
//
//        public void stealHalfCards(GameBoard board, Player player, Banker banker){
//
//        }
//
//
//
//        /**
//         * This method is used to transfer one card from a target player to the acting player stealing the card.
//         * @return
//         * @param board
//         * @param initPlayer
//         * @param targetPlayer
//         * @param resource
//         * @param tile
//         * @param settlement
//         */
//        public void stealCard(GameBoard board, Player targetPlayer, ResourceType resource, Tile tile, Settlement settlement){
//            //ResourceType resource = new ResourceType;
//            // Some sort of print out array of hand
//            //System.out.println(Player.getHand());
//            // public boolean checkSettlementNearPoint(GridNode currentNode){
//
//            //}
//
//            ArrayList<GridNode> nodeAdjacentToRobber = destinationTile.getTilePoints();
//            ArrayList<Player> playersWithSettlementsOnRobberTile = new ArrayList<>();
//
//            // loops through the Tile's GridNodes
//            for (int tileNodeIndex = 0; tileNodeIndex < nodeAdjacentToRobber.size(); tileNodeIndex++) {
//                // checks to see if there is a settlement on the GridNode
//                if (nodeAdjacentToRobber.get(tileNodeIndex).getSettlement() instanceof Settlement) {
//                    // sets the settlement on the GridNode
//                    settlement = nodeAdjacentToRobber.get(tileNodeIndex).getSettlement();
//                    // sets the settlements owner
//                    playersWithSettlementsOnRobberTile.add(settlement.getPlayer());
//                /*
//                 * display a list of players with settlements nearby
//                 * player picks a settlement to steal from
//                 * scanner input, display options, some way of picking
//                 * move resources around, takeresource method etc
//                 *
//                 */
//                }
//            }
//
//
//            /**
//             * Need some sort of selection for multiple settlements
//             */
//            Settlement settlementOne = new Settlement(targetPlayer);
//
//            targetPlayer = settlement.getPlayer();
//
//            // String to hold input
//            String resourceInput;
//
//            // New scanner for input
//            System.out.println("Enter a resource to steal");
//            Scanner scanner = new Scanner(System.in);
//
//            // String = input
//            resourceInput = scanner.next();
//
//            resourceInput.toUpperCase();
//
//            // String = the enum value of the ResourceType from ResourceType Class
//            resource = Enum.valueOf(ResourceType.class, resourceInput);
//
//        /*
//         *
//         * scanner select player, resource, else repeat, try/catch
//         */
//
//
//            if (banker.checkRemovePossible(targetPlayer, targetResource, 1)) {
//                // Remove cards from player's hand
//                targetPlayer.removeResource(resource);
//
//                initPlayer.addResource(resource);
//            }
//
//
//
//            /**board.getTiles();
//             *
//             System.out.println(board.getIndexOfRobber());
//             GridNode node = new GridNode(0, 0);
//             // Need some way of converting the Robber's position on the board to a useable grid node
//             node = board.getGridNode(indexOfRobber);
//             // Check settlements nearby position of robber
//             board.checkSettlementNearPoint(node);
//             */
//
//
//
//
//
//
//        /*
//         *
//         * Some sort of settlement selection
//         *
//         */
//
//
//            /**tile.getGridPointReference(board.getIndexOfRobber());
//             board.getGridNode(indexOfRobber);
//             */
//
//        /*for (i = 0; i < Player.getHand().size(); i++) {
//
//        }*/
//            /**int numOfCards = Player.getHand.size();
//
//             numOfCards =
//             */
//            // Some input for choosing card(s) to steal
//
//        }
//
//        public void removeExcessCards(ArrayList<Player> playerList, ResourceType resource) {
//            for (int i = 0; i < playerList.size(); i++) {
//                ArrayList<ResourceType> playerHand = playerList.get(i).getHand();
//
//
//                while (playerHand.size() > 7) {
//
//                    Random rand = new Random();
//                    int n = rand.nextInt(playerHand.size());
//                    ResourceType resourceDiscarded = playerHand.get(n);
//                    playerList.get(i).removeResource(resourceDiscarded);
//
//                }
//            }
//
//
//        }
//
//
//    }
//}
