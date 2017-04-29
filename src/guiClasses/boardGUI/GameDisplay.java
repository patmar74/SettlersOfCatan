package guiClasses.boardGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class GameDisplay {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GameDisplay window = new GameDisplay();
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
	public GameDisplay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// sets the screen height and width
		Resolution frameSize = new Resolution();
		System.out.println(Integer.toString(frameSize.getX()) + " " + Integer.toString(frameSize.getY()));
		frame = new JFrame();
		frame.setBounds(100, 100, frameSize.getX(), frameSize.getY());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// sets it to be maximized to start
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
	}

}
