package guiClasses;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import players.Player;
import resourceClasses.Banker;;

public class PersonTradeGUI extends JFrame {

	private JPanel contentPane;
	// the panel that holds the action button in the beginning and the ending
	// summary label in the end
	private JPanel bottomPanel;
	// Banker object handling the trade
	private Banker banker;
	// panel with the components needed to set up the trade
	private JPanel setTradepanel;
	// Players making the trade
	private Player playerOne;
	private Player playerTwo;
	private JButton playerOneConfirm;
	private JButton playerTwoConfirm;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PersonTradeGUI frame = new PersonTradeGUI();
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
	public PersonTradeGUI() {
		setTitle("Player Trade");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 16, 564, 284);
		contentPane.add(panel);
		/// PLAYER ONE CONFIRM BUTTON
		playerOneConfirm = new JButton("CONFIRM");
		playerOneConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				playerOneConfirm.setEnabled(false);
				playerOneConfirm.setText("CONFIRMED");
			}
		});
		///
		/// PLAYER TWO CONFIRM BUTTON
		playerTwoConfirm = new JButton("CONFIRM");
		playerTwoConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				playerTwoConfirm.setEnabled(false);
				playerTwoConfirm.setText("CONFIRMED");
			}
		});
		///
		SpringLayout sl_panel = new SpringLayout();
		sl_panel.putConstraint(SpringLayout.NORTH, playerTwoConfirm, 0, SpringLayout.NORTH, playerOneConfirm);
		sl_panel.putConstraint(SpringLayout.EAST, playerTwoConfirm, -134, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.WEST, playerOneConfirm, 72, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, playerOneConfirm, -27, SpringLayout.SOUTH, panel);

		JComboBox<Player> comboBoxPLAYER1NAME = new JComboBox<>();
		sl_panel.putConstraint(SpringLayout.WEST, comboBoxPLAYER1NAME, 61, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, comboBoxPLAYER1NAME, 13, SpringLayout.EAST, playerOneConfirm);

		JComboBox<Player> comboBoxPLAYER2NAME = new JComboBox<>();
		sl_panel.putConstraint(SpringLayout.NORTH, comboBoxPLAYER1NAME, 0, SpringLayout.NORTH, comboBoxPLAYER2NAME);
		sl_panel.putConstraint(SpringLayout.NORTH, comboBoxPLAYER2NAME, 126, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, comboBoxPLAYER2NAME, 337, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, comboBoxPLAYER2NAME, -124, SpringLayout.EAST, panel);
		for (int i = 0; i < 4; i++) {
			comboBoxPLAYER1NAME.addItem(/// PLAYERLIST GOES HERE!!!
		}

		panel.setLayout(sl_panel);
		panel.add(playerOneConfirm);
		panel.add(playerTwoConfirm);
		panel.add(comboBoxPLAYER2NAME);
		panel.add(comboBoxPLAYER1NAME);

	}
}
