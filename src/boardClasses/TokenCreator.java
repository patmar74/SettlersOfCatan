package boardClasses;

import java.util.ArrayList;

import javax.swing.JFrame;

public class TokenCreator {
	// created this no arg constructor and made
	public TokenCreator() {
		createTokens();
	}

	// ArrayList to be returned to the tile class
	private ArrayList<CircleToken> tokens = new ArrayList<CircleToken>();

	// game board to be used
	private JFrame jFrame;

	// creates 18 tokens for game board and stores them in an ArrayList
	private void createTokens() {

		CircleToken five = new CircleToken('A', 5);
		CircleToken two = new CircleToken('B', 2);
		CircleToken six = new CircleToken('C', 6);
		CircleToken three = new CircleToken('D', 3);
		CircleToken eight = new CircleToken('E', 8);
		CircleToken ten = new CircleToken('F', 10);
		CircleToken nine = new CircleToken('G', 9);
		CircleToken twelve = new CircleToken('H', 12);
		CircleToken eleven = new CircleToken('I', 11);
		CircleToken four = new CircleToken('J', 4);
		CircleToken secondEight = new CircleToken('K', 8);
		CircleToken secondTen = new CircleToken('L', 10);
		CircleToken secondNine = new CircleToken('M',9);
		CircleToken secondFour = new CircleToken('N', 4);
		CircleToken secondFive = new CircleToken('O', 5);
		CircleToken secondSix = new CircleToken('P', 6);
		CircleToken secondThree = new CircleToken('Q', 3);
		CircleToken secondEleven = new CircleToken('R', 11);
		tokens.add(five);
		tokens.add(two);
		tokens.add(six);
		tokens.add(three);
		tokens.add(eight);
		tokens.add(ten);
		tokens.add(nine);
		tokens.add(twelve);
		tokens.add(eleven);
		tokens.add(four);
		tokens.add(secondEight);
		tokens.add(secondTen);
		tokens.add(secondNine);
		tokens.add(secondFour);
		tokens.add(secondFive);
		tokens.add(secondSix);
		tokens.add(secondThree);
		tokens.add(secondEleven);

	}

	// returns tokens in ArrayList
	public ArrayList<CircleToken> getArrayList() {
		return tokens;
	}

	// returns graphics stored in each circle token to draw to a hex
	public CircleToken draw(char a) {
		CircleToken e = null;
		for (int i = 0; i < tokens.size(); i++) {
			if (a == tokens.get(i).getLetterOnToken()) {
				e = tokens.get(i);
			} else {
				continue;
			}
		}

		return e;
	}

	public void test() {
		createTokens();
		getArrayList();
		System.out.println(tokens.size());
		for (int i = 0; i < tokens.size(); i++) {
			System.out.println(tokens.get(i).getNumber() + "\t" + tokens.get(i).getDotNumber());
		}
	}

}