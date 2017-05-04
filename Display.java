package guiClasses.boardGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boardClasses.GameBoard;
import boardClasses.GridNode;
import players.City;
import players.Player;
import players.Road;
import players.Settlement;

/**
 * Rough Draft of the GameDisplay For now it just has the one panel
 * 
 * @author Trent
 *
 */
public class Display {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Display window = new Display();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Display() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// sets the screen height and width
		Resolution frameSize = new Resolution();
		frame = new JFrame();
		frame.setBounds(100, 100, frameSize.getX(), frameSize.getY());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// sets it to be maximized to start
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		GameBoard gb = new GameBoard();
		CatanWindow cw = new CatanWindow(gb);

		// for testing purposes
		Player p1 = new Player("Joe", Color.ORANGE);
		Player p2 = new Player("Bob", Color.PINK);
		Settlement s1 = new Settlement(p1);
		s1.setLocation(6, 6);
		Settlement s2 = new City(p2);
		s2.setLocation(2, 6);
		Road r = new Road(p1);
		r.setStartNode(new GridNode(7, 4));
		r.setEndNode(new GridNode(8, 5));

		// adds the stuff to the display
		cw.addSettlementOrCityToMap(s2);
		cw.addSettlementOrCityToMap(s1);
		cw.addRoadToMap(r, frame);

		frame.getContentPane().add(cw.getJPanel(), BorderLayout.CENTER);

		JPanel player1 = new JPanel();
		// frame.getContentPane().add(player1, BorderLayout.EAST);

		JPanel player2 = new JPanel();
		// frame.getContentPane().add(player2, BorderLayout.EAST);

		JPanel player3 = new JPanel();
		// frame.getContentPane().add(player3, BorderLayout.WEST);

		JPanel player4 = new JPanel();
		// frame.getContentPane().add(player4, BorderLayout.WEST);

	}

}