package guiClasses;

import developmentCards.DevCardType;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Trent Koberna & Donique Hicks
 *
 */
public class DevelopmentCardGUI {

	private JFrame frame;
	private ImageIcon imageIcon;
	private String cardType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DevelopmentCardGUI window = new DevelopmentCardGUI(DevCardType.ROAD_BUILDING);
					DevelopmentCardGUI window2 = new DevelopmentCardGUI(DevCardType.KNIGHT);
					DevelopmentCardGUI window3 = new DevelopmentCardGUI(DevCardType.MONOPOLY);
					window.frame.setVisible(true);
					window2.frame.setVisible(true);
					window3.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DevelopmentCardGUI(DevCardType card) {
		this.cardType = card.toString();
		display();
	}// end constructor

	/**
	 * Initialize the contents of the frame.
	 */
	private void display() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Developement Card");
		frame.setBounds(100, 100, 185, 305);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblImageLabel = new JLabel("");
		setImageIcon();
		// add the image to the label
		lblImageLabel.setIcon(imageIcon);
		// add the label to the panel
		panel.add(lblImageLabel);
	}// end display method

	/**
	 * ImageIcon based off of the DevCard enum value passed in. The image files
	 * are saved under the DevCardImages folder in the build path under the name
	 * of the enum they correspond to.
	 */
	private void setImageIcon() {
		String imageFileName = "Resources/DevCardImages/" + cardType + ".jpg";
		imageIcon = new ImageIcon(imageFileName);
	}// end setImage method

}// end DevelopmentCardGUI class
