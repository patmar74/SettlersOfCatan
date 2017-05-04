package guiClasses;
import players.Player;

import resourceClasses.Banker;
import resourceClasses.ResourceType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * This GUI handles all port trades and banker trades and displays a summary of
 * the result. The display images are functional, but not jazzy. If there is
 * more time at the end we can dress that up a bit.
 *
 * @author Trent
 *
 */
public class BankerAndHarborTradeGUI {
	// the frame the gui is on
	private JFrame frame;
	private JTextField textField_NumberOfResourcesTraded;
	// set as an object array so it can work with the combo box constructor. The
	// JComboBox<ResourceType>() constructor is not recognized by windows
	// builder. the list of resources the player can chose from
	private Object[] dropDownList;
	// the set resource the player is trading in (for port trades)
	private ResourceType resourceTraded;
	// the exchange rate of the trade, how many the player needs to give up for
	// every once resource received
	private int tradeRatio;
	// string representation of the trade ratio integer
	private String tradeRatioString;
	// if the traded resource is not set, this will be the list of options of
	// resources to be traded
	private JComboBox<Object> resourceRecievedOptionsComboBox;
	// this will be the list of options of resources to be received
	private JComboBox<Object> resourceTradedComboBox;
	// the panel that holds the action button in the beginning and the ending
	// summary label in the end
	private JPanel bottomPanel;
	// Banker object handling the trade
	private Banker banker;
	// panel with the components needed to set up the trade
	private JPanel setTradepanel;
	// Player making the trade
	private Player playerTrading;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					// creates a dummy person for trading purposes (the version
					// of the Player class I was working with didn't have a
					// constructor so I made one that sets all resources as
					// being 8)
					Player p = new Player("Player", Color.BLUE);
					// the trade ratio in this test case is 4 with no resource
					// restriction.
					// it is a banker trade
					BankerAndHarborTradeGUI window = new BankerAndHarborTradeGUI(p, 4);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates an instance of the trade hub for non specific exchanges, where
	 * any resource type can be traded in.
	 *
	 * @param playerTrading
	 *            - the player making the trade
	 * @param tradeRatio
	 *            - (also know as the exchange rate) it is the number of cards
	 *            you need to trade in to get one back. for trades with the
	 *            banker it is 4, for ports it can be 3 or 2.
	 */
	public BankerAndHarborTradeGUI(Player playerTrading, int tradeRatio) {
		// the desert resource type is used to represent a lack of a resource
		// traded restriction. the desert type is used instead of setting the
		// resource traded as null
		this.resourceTraded = ResourceType.DESERT;
		commonConstructorCalls(playerTrading, tradeRatio);
	}// end constructor

	/**
	 * Constructor used for trades where a specific resource is being traded in.
	 * This will happen for some port exchanges.
	 *
	 * @param playerTrading
	 *            - the player making the trade
	 * @param resourceTraded
	 *            - the resource specified by the port of exchange
	 * @param tradeRatio
	 *            - exchange rate on the trade
	 */
	public BankerAndHarborTradeGUI(Player playerTrading, int tradeRatio, ResourceType resourceTraded) {
		// the resource traded is fixed and cannot be changed in the GUI
		this.resourceTraded = resourceTraded;
		commonConstructorCalls(playerTrading, tradeRatio);
	}// end constructor

	/**
	 * Handles all of the methods that are called in both constructors.
	 *
	 * @param playerTrading
	 *            - same as the value passed into the constructor
	 * @param tradeRatio
	 *            - same as the value passed into the constructor
	 */
	private void commonConstructorCalls(Player playerTrading, int tradeRatio) {
		// sets the person making the trade
		this.playerTrading = playerTrading;
		// sets the trade ratio
		this.tradeRatio = tradeRatio;
		// sets a string representation of the trade ratio, used for calling
		// files and displaying labels
		this.tradeRatioString = Integer.toString(tradeRatio);
		// sets the dropDownList used for selecting resources to receive
		setDropDownList();
		// sets up the frame for the GUI
		initializeFrame();
		// creates an instance of the Banker object
		banker = new Banker();
	} // end commonConstructorCalls

	/**
	 * Sets the resource options for the resources received combo box. If there
	 * is no restriction on the resource being traded, this list will also be
	 * used for the resources traded combo box (in this case all resources
	 * except the desert will be options for resources traded and received)
	 */
	private void setDropDownList() {
		ArrayList<ResourceType> dropDown = new ArrayList<>();
		ResourceType[] array = ResourceType.values();
		// adds all of the enum values of ResourceType to the arrayList
		for (int i = 0; i < array.length; i++) {
			dropDown.add(array[i]);
		}
		// removes the desert resource type since it cannot be traded
		dropDown.remove(ResourceType.DESERT);
		// if the resource to be traded in is set (not desert) , it removes it
		// from the options of resources you can receive. Since you would not
		// want there to be an option to trade 3 WOOL for 1 WOOL
		//
		// (recall that the resource traded being desert means the resource type
		// is not set)
		if (!resourceTraded.equals(ResourceType.DESERT)) {
			dropDown.remove(resourceTraded);
		}
		// creates an array of Objects that can be passed into the combo box
		// constructor
		dropDownList = dropDown.toArray();
	}// end setDropDownList method

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeFrame() {

		frame = new JFrame();
		frame.setBounds(100, 100, 600, 350);
		// the title will display the exchange rate of the trade
		frame.setTitle("Exhcange Rate: " + tradeRatioString + " to 1");
		// hide on close is used so the game will not exit when the trade window
		// is closed
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		// this button is clicked when the trader wants to submit the trade
		JButton btnMakeTrade = new JButton("Make Trade");
		// this is the only action listener in this class
		btnMakeTrade.addActionListener((new MakeTradeListener()));
		bottomPanel.add(btnMakeTrade);

		setTradepanel = new JPanel();
		frame.getContentPane().add(setTradepanel, BorderLayout.CENTER);

		JLabel lblResourceTraded = new JLabel("Resource Traded");
		setTradepanel.add(lblResourceTraded);

		// the text field where the player enters the number of resources they
		// want to trade
		textField_NumberOfResourcesTraded = new JTextField();
		textField_NumberOfResourcesTraded.setColumns(3);
		setTradepanel.add(textField_NumberOfResourcesTraded);

		// sets the resource traded options
		if (resourceTraded.equals(ResourceType.DESERT)) {
			// if the resource traded is not restricted, the dropDownList is
			// used to create a combo box
			resourceTradedComboBox = new JComboBox<Object>(dropDownList);
			setTradepanel.add(resourceTradedComboBox);
		} else {
			// if the resource traded is restricted, a label displaying the
			// fixed
			// resource traded is added
			JLabel lblSetResourceTraded = new JLabel(resourceTraded.toString());
			setTradepanel.add(lblSetResourceTraded);
		}

		JLabel lblResourceRecieved = new JLabel("Resource Recieved");
		setTradepanel.add(lblResourceRecieved);

		// used the drowDownList to set the resources recieved combo box
		resourceRecievedOptionsComboBox = new JComboBox<Object>(dropDownList);
		setTradepanel.add(resourceRecievedOptionsComboBox);

	}// end initialize method

	/**
	 * The private inner class that handles the action listener on the MakeTrade
	 * button
	 *
	 * @author Trent
	 *
	 */
	private class MakeTradeListener implements ActionListener {
		// int quantities of resources traded and received by the player
		private int numberOfResourcesRecieved;
		private int numberOfResourcesTraded;
		// String versions of the resources traded and received
		// I decided to used a string variable instead of a ResourceType enum
		// value since the outer class already has and ResourceType variable
		// called resourceTraded so things would get messy if I used enum values
		private String resourceTradedString;
		private String resourceRecievedString;

		// the labels used to display the images on the imagePanel
		private JLabel imageOfResourceRecieved;
		private JLabel imageOfResourceTraded;
		private JLabel arrow;

		@Override
		public void actionPerformed(ActionEvent e) {
			// determines the type of resource that are being traded and
			// received
			setResourcesTradedAndRecieved();
			// if there is an error in setting the number of resources traded
			// and received, the rest of the methods will not be performed
			if (setNumberOfResources()) {
				// if the trade goes through, display the results
				if (processTrade()) {
					setUpImagePanel();
					displayCardImages();
					displayTradeSummary();
					// these actions fall more under the responsibility of the
					// banker
				}
			}

		}// end actionPerformed method

		/**
		 * Sets the resourceTradedSting and resourceRecievedString values for
		 * the trade
		 */
		private void setResourcesTradedAndRecieved() {
			// if the resource traded was selected in the combo box, that
			// value is used to set the resource traded
			if (resourceTraded.equals(ResourceType.DESERT)) {
				resourceTradedString = resourceTradedComboBox.getSelectedItem().toString();
			} else {
				// if the resourced traded was fixed, the info passed into
				// the constructor is used to set the resource traded
				resourceTradedString = resourceTraded.toString();
			}
			// sets the resource received from the combo box
			resourceRecievedString = resourceRecievedOptionsComboBox.getSelectedItem().toString();
		}// end setResourcesTradedAndRecieved method

		/**
		 * Sets the quantities of resources to be traded and received and makes
		 * sure the trade is valid.
		 *
		 * @return - true if no errors are encountered in the method, false if
		 *         there is an error in the information entered into the GUI
		 */
		private boolean setNumberOfResources() {
			try {

				// takes in the text field input and uses it to set the number
				// of resources traded
				numberOfResourcesTraded = Integer.parseInt(textField_NumberOfResourcesTraded.getText());

				// the number of resources traded must be divisible by the
				// exchange rate in order to make a valid trade
				if (numberOfResourcesTraded % tradeRatio != 0) {
					// if there is a remainder, the trade cannot happen
					JOptionPane.showMessageDialog(null, "The trade does not match the exchange rate.",
							"Invalid Exchange", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				// if the resources traded number is valid, the resources
				// received is found by dividing by the exchange rate
				numberOfResourcesRecieved = numberOfResourcesTraded / tradeRatio;

				// checks to see if the payer has enough cards to trade, if they
				// do not, the message is displayed
				if (!banker.checkRemovePossible(playerTrading, ResourceType.valueOf(resourceTradedString),
						numberOfResourcesTraded)) {
					JOptionPane.showMessageDialog(null, "You do not have enough resources to complete the trade.",
							"Insufficiant Funds", JOptionPane.ERROR_MESSAGE);
					return false;
				}

			} catch (NumberFormatException e) {
				// catches the error of the player entering non integers for the
				// number of resources to be traded
				JOptionPane.showMessageDialog(null, "Invaild Entry", "Number Format Exception",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			// if the method makes is this far, the info entered into the GUI is
			// valid
			return true;

		}// end setNumberOfResources method

		/**
		 * Removes the center panel that entered the trade information and
		 * replaces it with a panel that will display the images that denote the
		 * trade
		 */
		private void setUpImagePanel() {
			// removes the trade panel from the frame
			frame.remove(setTradepanel);
			// creates a new panel and adds it to the frame
			JPanel imagePanel = new JPanel();
			frame.getContentPane().add(imagePanel, BorderLayout.CENTER);

			// creates three labels that will later display images
			imageOfResourceTraded = new JLabel();
			imageOfResourceRecieved = new JLabel();
			arrow = new JLabel();

			// adds the labels to the panel in order from left to right
			imagePanel.add(imageOfResourceTraded);
			imagePanel.add(arrow);
			imagePanel.add(imageOfResourceRecieved);

		}// end setUpImagePanel method

		/**
		 * Displays an image depiction of the trade on the newly created
		 * imagePanel. All image files used are in the Resources folder
		 */
		private void displayCardImages() {

			// displays the image of the resource cards traded stacked to match
			// the trade ratio
			String imageFileNameTraded = "Resources/ResourceCardImages/" + resourceTradedString + tradeRatioString
					+ ".jpg";
			ImageIcon tradedImageIcon = new ImageIcon(imageFileNameTraded);
			imageOfResourceTraded.setIcon(tradedImageIcon);

			// Displays an arrow to signify the conversion or trade taking place
			String imageFileNameArrow = "Resources/" + "arrow" + ".png";
			ImageIcon arrowImageIcon = new ImageIcon(imageFileNameArrow);
			arrow.setIcon(arrowImageIcon);

			// displays an image of the resource card received
			String imageFileNameRecieved = "Resources/ResourceCardImages/" + resourceRecievedString + ".jpg";
			ImageIcon recievedImageIcon = new ImageIcon(imageFileNameRecieved);
			imageOfResourceRecieved.setIcon(recievedImageIcon);
		}// end displayCardImages method

		/**
		 * Removes the bottomPanel (that held the MakeTrade button) from the
		 * frame, and replaces it with a label that summarizes the details of
		 * the trade
		 */
		private void displayTradeSummary() {
			// removes the bottom panel
			frame.remove(bottomPanel);
			// adds a new summaryPanel
			JPanel summaryPanel = new JPanel();
			frame.getContentPane().add(summaryPanel, BorderLayout.SOUTH);

			// creates a string that summarizes the trade details
			String TradeSummary = Integer.toString(numberOfResourcesTraded) + " " + resourceTradedString
					+ " were traded for " + Integer.toString(numberOfResourcesRecieved) + " " + resourceRecievedString;
			// creates a label that has the text to the TradeSummary string
			JLabel lblTradeSummary = new JLabel(TradeSummary);
			// makes the font big so it is prominent and easy to read
			lblTradeSummary.setFont(new Font("Tahoma", Font.PLAIN, 20));
			// adds the label to the panel
			summaryPanel.add(lblTradeSummary);

		}// end displayTradeSummary method

		/**
		 * Process the trade and updates the Player's hand to reflect the
		 * outcome of the trade
		 *
		 * @return true if the trade is processed, false if it is not
		 */
		private boolean processTrade() {
			ResourceType resourceTraded = ResourceType.valueOf(resourceTradedString);
			ResourceType resourceRecieved = ResourceType.valueOf(resourceRecievedString);

			return banker.processTrade(playerTrading, resourceTraded, resourceRecieved, numberOfResourcesTraded,
					numberOfResourcesRecieved);
		}// end processTrade method

	}// end MakeTradeListener inner class

}// end TradeHub class
