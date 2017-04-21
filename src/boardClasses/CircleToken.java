package boardClasses;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CircleToken {

	// constructor set to initialize string
	CircleToken(char letter, int number) {
		this.number = number;
		switch (number) {
		case 2:
			dots = 1;
			break;
		case 3:
			dots = 2;
			break;
		case 4:
			dots = 3;
			break;
		case 5:
			dots = 4;
			break;
		case 6:
			dots = 5;
			break;
		case 8:
			dots = 5;
			break;
		case 9:
			dots = 4;
			break;
		case 10:
			dots = 3;
			break;
		case 11:
			dots = 2;
			break;
		case 12:
			dots = 1;
			break;
		}
		this.letter = letter;
	}

	// declares instance of CirclePanel, an extension of the JPanel class
	private CirclePanel circlePanel = new CirclePanel();

	// private Graphics object used for rendering circle
	private Graphics graphics;

	// letter on the circle tile
	private char letter;

	// number to be displayed on token
	private int number;

	// number of dots on chip
	private int dots;

	// returns private variable value
	// written for communication between the tile classes, as it is the only
	// piece of information required
	public int getNumber() {
		return number;
	}

	// returns number of dots on the token
	public int getDotNumber() {
		return dots;
	}

	// returns the letter on the token
	public char getLetterOnToken() {
		return letter;
	}

	// builds token
	public Graphics getGraphics() {
		return this.graphics;
	}

	// an extension of the JPanel class used to render a circle to the screen
	public class CirclePanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.YELLOW);
			// draws oval with dimensions of window and colors it yellow
			g.drawOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
			g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);

			// creates font used to specify number in token
			// if the number is six or eight, it is colored red
			g.setFont(new Font("TimesRoman", Font.BOLD, 100));
			if ((number == 6) ||( number == 8)) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.BLACK);
			}
			// draws the number specified in boardClasses.CircleToken constructor into the
			// token
			g.drawString(String.valueOf(number), 68, 110);
		}
	}

}