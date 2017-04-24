package guiClasses;

import gameControllers.Dice;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/* Authors: Jennifer Kearney & Alexis McCaffery
 * Date: 04/06/17
 * Purpose: Make dice for rolls and display on a  gui!
 */

public class DiceGUI extends JFrame {
	private int sumOfRoll;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DiceGUI frame = new DiceGUI();
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
	public DiceGUI() {
		sumOfRoll = 0;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Creates Sum Of Roll label and adds to contentPane
		JLabel lblSumOfRoll = new JLabel("   ");
		lblSumOfRoll.setHorizontalAlignment(SwingConstants.CENTER);
		lblSumOfRoll.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblSumOfRoll.setBounds(70, 198, 145, 42);
		contentPane.add(lblSumOfRoll);

		// creates new dice object for use in gui
		Dice dice = new Dice();

		// creates dice1 image label and adds to contentPane
		JLabel lbldice1 = new JLabel(new ImageIcon("Resources/diceImages/1.png"));
		lbldice1.setSize(70, 69);
		lbldice1.setLocation(70, 69);
		contentPane.add(lbldice1);

		// creates dice2 image label and adds to contentPane
		JLabel lbldice2 = new JLabel(new ImageIcon("Resources/diceImages/2.png"));
		lbldice2.setSize(70, 69);
		lbldice2.setLocation(145, 69);
		contentPane.add(lbldice2);

		// creates roll button
		JButton btnRollDice = new JButton("Roll!!");

		// action listener for button click event
		btnRollDice.addActionListener(new ActionListener() {
			/*
			 * method to handle button click action rolls dice updates labels
			 * with correct dice image and sum amount
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// rolls dice
				dice.rollDice();
				// integer to hold value of dice1 roll
				int dieRolled = dice.die1;
				// updates label with new image from file path chosen in method
				// getDiceImage
				lbldice1.setIcon(new ImageIcon(getDiceImage(dieRolled)));
				// updates integer with value of dice2 roll
				dieRolled = dice.die2;
				// updates label with new image from file path chosen in method
				// getDiceImage
				lbldice2.setIcon(new ImageIcon(getDiceImage(dieRolled)));
				// updates sum with new rolled sum
				lblSumOfRoll.setText(Integer.toString(dice.sumOfRoll));
				// hides button so dice can only be rolled once
				btnRollDice.setVisible(false);

				sumOfRoll = dice.sumOfRoll;
			}// ends actionPerformed method for button
		});
		btnRollDice.setBounds(101, 164, 89, 23);
		contentPane.add(btnRollDice);

	}

	// method to evaluate number roll and select proper dice image path
	private String getDiceImage(int dieRolled) {
		// string var to hold the path, initialized to null
		String returnValue = null;
		// switch to select each roll option
		switch (dieRolled) {
		case 1:
			returnValue = "Resources/diceImages/1.png";
			break;

		case 2:
			returnValue = "Resources/diceImages/2.png";
			break;

		case 3:
			returnValue = "Resources/diceImages/3.png";
			break;

		case 4:
			returnValue = "Resources/diceImages/4.png";
			break;

		case 5:
			returnValue = "Resources/diceImages/5.png";
			break;

		case 6:
			returnValue = "Resources/diceImages/6.png";
			break;

		}// ends dieRolled switch
		return returnValue;

	}// end getDiceImage method

	public int getSumOfRoll() {
		return sumOfRoll;
	}// end getSumOfRoll

}
