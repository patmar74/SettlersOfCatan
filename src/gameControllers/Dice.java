package gameControllers;

import java.util.Random;

/* Authors: Jennifer Kearney & Alexis McCaffery
 * Date: 04/06/17
 * Purpose: Make dice for rolls
 */

public class Dice {

	// creates variable to hold int for first die
	public int die1;
	// creates variable to hold int for second die
	public int die2;
	// creates variable to hold int for sum of the magic dice
	public int sumOfRoll;

	public int getSumOfRoll() {
		return sumOfRoll;
	}

	// creates new random object to randomize in its magical ways
	Random rand = new Random();

	/*
	 * "rolls" each die and sets int value to each die variable lowest roll is
	 * 1, highest roll is 6
	 */
	public void rollDice() {
		die1 = rand.nextInt(6) + 1;
		die2 = rand.nextInt(6) + 1;
		sumOfRoll = die1 + die2;
	}

}
