/**
 * An irrelevant window, that holds a panel that we will extract later for the main window.
 * @Dominic, @Jen
 */

package guiClasses;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;

public class PlayerDataGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerDataGUI frame = new PlayerDataGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PlayerDataGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 200, 200);
		contentPane.add(panel);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setLabelFor(panel);
		lblBackground.setBounds(0, 0, 200, 200);
		ImageIcon backgroundIcon = new ImageIcon("Resources/PlayerImages/PlayerBackground.png");
		Image resizedImage = backgroundIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		backgroundIcon = new ImageIcon(resizedImage);
		
		panel.setLayout(null);
		lblBackground.setIcon(backgroundIcon);
		panel.add(lblBackground);
	}
}
