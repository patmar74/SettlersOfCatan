package guiClasses.boardGUI;

/**
 * This class determines screen resolution
 * @author Tyler Wolf
 */

import java.awt.Dimension;
import java.awt.Toolkit;

public class Resolution {

	// captures screen size
	private Dimension resolution;
	// stores width of screen into x
	private int x;
	// stores height of screen into y
	private int y;

	public Resolution() {
		resolution = Toolkit.getDefaultToolkit().getScreenSize();
		x = resolution.width;
		y = resolution.height;
	}// end constructor

	// returns width of screen
	public int getX() {
		return x;
	}

	// returns height of screen
	public int getY() {
		return y;
	}
}
