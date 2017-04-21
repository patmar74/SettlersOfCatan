///**
// * Week 4 Special Knight Card & GUI
// * Authors: Alexis, Michael, Kellyn
// * April 13th, 2017
// *
// * *included is a modified Person, Settlement.java
// */
//package knightCommon;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import resourceClasses.ResourceType;
//
//public class Knight {
//
//		/**
//		 * Requires 1 knight development, 1 resource ore card, 1 resource lumber card
//		 *
//		 * Check for player possession of all cards required
//		 *
//		 * Player can choose to play their knight card on their turn
//		 *
//		 * Takes action after turn, can be interrupted by another player that builds through it,
//		 * card is then discarded.
//		 */
//
//		//Boolean checks for ownership of Knight, Ore, and Lumber cards; acts as a check for cards in player's possession
//
//		boolean isPlayed = false;
//		boolean specialKnightPlay = false;
//		boolean fortressIsActivated = false;
//
//
//		// Does player want to play knight card? some sort of check would go here
//		if (isPlayed == true) {
//
//			if (playerOne.PlayerKnightCards.size() > 0) {
//				// Can play standard knight card
//				/**
//				 * The player has some sort of chance to play their Knight card before the complex if check
//				 *
//				 *
//				 */
//
//				if (specialKnightPlay == true && playerOne.PlayerLumberCards.size() > 0 && playerOne.PlayerOreCards.size() > 0 && playerOne.PlayerKnightCards.size() > 0) {
//
//					System.out.println("Would you like to:\n\t1. Play card normally\n\t2. Play special move");
//					System.out.println("Please input 1 or 2");
//					Scanner scanner = new Scanner(System.in);
//
//					try {
//						scanner.nextInt();
//					} catch (NumberFormatException e) {
//						System.out.println("Please only input '1' or '2'.");
//					}
//
//					if (scanner.next() == "1") {
//						robber.isMoved();
//					}
//
//
//					if (scanner.next() == "2") {
//
//						specialKnightPlay = true;
//					}
//						/**
//						 * Special card play 'if' block
//						 * Uses lumber, ore, and knight card
//						 */
//
//					if (specialKnightPlay == true) {
//						//public int subtractResourceType(ArrayList<ResourceType> Cards, int amount) {
//
//
//						playerOne.subtractResourceType(PlayerLumberCards, 1);
//						playerOne.subtractResourceType(PlayerOreCards, 1);
//						// Technically a development card
//						playerOne.subtractResourceType(PlayerKnightCards, 1);
//
//						// Some sort of location check
//						// settlement.getLocation();
//
//						/**
//						 * Some sort of turn function needed for waiting
//						 * for activation of special card play
//						 */
//
//						//Fortress is placed at settlement location
//
//						if (playerOne.PlayerWheatCards.size() > 0) {
//
//						playerOne.subtractResourceType(PlayerWheatCards, 1);
//
//
//						fortress.isActivated = true;
//
//						// Settlements can't get resources around this panel at location of fortress
//
//						// Exception for player who activated the card
//
//
//						}
//
//						} else {
//							System.out.println("Your fortress has failed as you didn't provide wheat in time.");
//						}
//
//
//					// Case statement instead? this portion has become confusing
//					/*if (playerOne.PlayerKnightCards.size() > 0) {
//
//						 * Standard knight play
//						 * for Robber movement
//
//						// Robber is moved anywhere the player chooses
//						// Standard play
//						robber.isMoved();
//					}*/
//
//
//				}
//
//
//			}
//
//		}
//
//
//
//		//if (knightCard == true && oreCard == true && lumberCard == true) {
//			// is eligible to make this turn
//
//			else {
//			System.out.println("You cannot do that because you lack one of the devolopment or resource cards!");
//		}
//
//
//	}
//
//}
