package guiClasses;
import players.Player;
import resourceClasses.Banker;
import resourceClasses.ResourceType;
import resourceClasses.PurchaseOptions; 
import boardClasses.GameBoard;
import boardClasses.GridNode;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;

public class ConstructionGUI extends JFrame {
	private JFrame frame; 
	private JPanel contentPane;
	//Banker object accepting resource items
	private Banker banker;
	//the player making the trade
	private Player playerConstructing; 
	private GameBoard board; 
	private GridNode startGridNode;
	private GridNode endGridNode;
	private Point x;
	private Point y; 
	private int a;
	private int b;
	boolean isSettingUp;
	
	



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {


			public void run() {
				try {
					
					Player p = new Player("Player", Color.BLUE);
					ConstructionGUI window = new ConstructionGUI(p);
					window.frame.setVisible(true);
				}//end try
				catch (Exception e) {
					e.printStackTrace();
				}//end catch
			
		}//end run()
		});//end eventqueue
	}//end main
			/*
			 * @param the player constructing
			 */
						public ConstructionGUI(Player playerConstructing) {
							// TODO Auto-generated constructor stub
						
					this.playerConstructing = playerConstructing;
					initializeFrame();
					}
			
		
	/**
	 * Create the frame.
	 */	
		
	public void initializeFrame() {	
		
		frame = new JFrame(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Construction");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel(); 
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(84, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 443, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(41, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.add(panel);
JLabel lblClickTheButton = new JLabel("Click the button to purchase a construction item:");
		
		JButton btnRoad = new JButton("Road");
		
		btnRoad.addActionListener(new RoadButtonListener());
		
		JButton btnSettlement = new JButton("Settlement");
		
		btnSettlement.addActionListener(new SettlementButtonListener());
		
		JButton btnCity = new JButton("City");
		
		btnCity.addActionListener(new CityButtonListener());
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(55)
							.addComponent(lblClickTheButton))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(175)
							.addComponent(btnCity))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(153)
							.addComponent(btnSettlement))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(173)
							.addComponent(btnRoad)))
					.addContainerGap(77, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblClickTheButton)
					.addGap(35)
					.addComponent(btnRoad)
					.addGap(30)
					.addComponent(btnSettlement)
					.addGap(36)
					.addComponent(btnCity)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		panel.add(btnRoad);
	
	}

	
private class RoadButtonListener implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		board = new GameBoard();
		banker = new Banker(); 
		x = new Point(); 
		y = new Point(); 
		
		int[] quantity = banker.getQtyNeededToPurchase(PurchaseOptions.ROAD);
		if (banker.checkPurchasePossible(playerConstructing, quantity) == true){
			banker.removeResourcesForPurchase(playerConstructing, quantity);
			playerConstructing.placeRoad(board, x, y);
		}
		else {
			JOptionPane.showMessageDialog(null, "You do not have enough resources to purchase this item.");
		}
		
		
		}
		}//end actionPerformed method


private class SettlementButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
		banker = new Banker();
		board = new GameBoard();
	 	
		int[] quantity = banker.getQtyNeededToPurchase(PurchaseOptions.SETTLEMENT);
		if (banker.checkPurchasePossible(playerConstructing, quantity) == true){
			banker.removeResourcesForPurchase(playerConstructing, quantity);
			playerConstructing.placeSettlement(board, a, b, isSettingUp);
		}
		else {
			JOptionPane.showMessageDialog(null, "You do not have enough resources to purchase this item.");
		}
		}
}

private class CityButtonListener implements ActionListener {
	
	public void actionPerformed(ActionEvent e){
		banker = new Banker();
		board = new GameBoard();
	 	
		int[] quantity = banker.getQtyNeededToPurchase(PurchaseOptions.CITY);
		if (banker.checkPurchasePossible(playerConstructing, quantity) == true){
			banker.removeResourcesForPurchase(playerConstructing, quantity);
			playerConstructing.placeCity(board, a, b);
		}
		else {
			JOptionPane.showMessageDialog(null, "You do not have enough resources to purchase this item.");
		}
		}
	
}


}

			
	
	
	
	
	
	
	
	
	
	
	
	

