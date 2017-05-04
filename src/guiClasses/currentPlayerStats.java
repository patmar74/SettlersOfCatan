package guiClasses;

import javax.swing.JPanel;

import players.City;
import players.Player;
import players.Road;
import players.Settlement;
import resourceClasses.ResourceType;

import javax.swing.JTextField;
import java.util.ArrayList;

public class currentPlayerStats extends JPanel {

	/**
	 * Create the panel.
	 */
	Player currentPlayer;
	ArrayList<Settlement> settlements;
	ArrayList<Road> roads;
	ArrayList<City> cities;
	
	private JTextField PlayerName;
	private JTextField txtSettlements;
	private JTextField Settlements;
	private JTextField txtVictoryPoints;
	private JTextField VictoryPoints;
	private JTextField txtCitys;
	private JTextField Cities;
	private JTextField txtWood;
	private JTextField txtWool;
	private JTextField txtLumber;
	private JTextField txtBrick;
	private JTextField txtOre;
	private JTextField Wheat;
	private JTextField Wool;
	private JTextField Lumber;
	private JTextField Roads_TXT;
	private JTextField Roads;
	private JTextField Brick;
	private JTextField Ore;
	public currentPlayerStats(Player playerAtHand) 
	{
		setLayout(null);
		
		PlayerName = new JTextField();
		PlayerName.setBounds(10, 11, 107, 38);
		add(PlayerName);
		PlayerName.setColumns(10);
		
		JPanel PlayerColor = new JPanel();
		PlayerColor.setBounds(0, 60, 372, 240);
		add(PlayerColor);
		PlayerColor.setLayout(null);
		
		txtSettlements = new JTextField();
		txtSettlements.setText("Settlements");
		txtSettlements.setBounds(10, 11, 100, 39);
		PlayerColor.add(txtSettlements);
		txtSettlements.setColumns(10);
		
		Settlements = new JTextField();
		Settlements.setBounds(120, 20, 47, 20);
		PlayerColor.add(Settlements);
		Settlements.setColumns(10);
		
		txtCitys = new JTextField();
		txtCitys.setText("Citys");
		txtCitys.setBounds(176, 11, 86, 39);
		PlayerColor.add(txtCitys);
		txtCitys.setColumns(10);
		
		Cities = new JTextField();
		Cities.setBounds(276, 20, 56, 20);
		PlayerColor.add(Cities);
		Cities.setColumns(10);
		
		txtWood = new JTextField();
		txtWood.setText("WHEAT:");
		txtWood.setBounds(10, 71, 66, 20);
		PlayerColor.add(txtWood);
		txtWood.setColumns(10);
		
		txtWool = new JTextField();
		txtWool.setText("WOOL:");
		txtWool.setBounds(10, 102, 66, 20);
		PlayerColor.add(txtWool);
		txtWool.setColumns(10);
		
		txtLumber = new JTextField();
		txtLumber.setText("LUMBER:");
		txtLumber.setBounds(10, 133, 66, 20);
		PlayerColor.add(txtLumber);
		txtLumber.setColumns(10);
		
		txtBrick = new JTextField();
		txtBrick.setText("BRICK:");
		txtBrick.setBounds(10, 161, 66, 20);
		PlayerColor.add(txtBrick);
		txtBrick.setColumns(10);
		
		txtOre = new JTextField();
		txtOre.setText("ORE:");
		txtOre.setBounds(10, 192, 66, 20);
		PlayerColor.add(txtOre);
		txtOre.setColumns(10);
		
		Wheat = new JTextField();
		Wheat.setBounds(86, 71, 28, 20);
		PlayerColor.add(Wheat);
		Wheat.setColumns(10);
		
		Wool = new JTextField();
		Wool.setBounds(86, 102, 28, 20);
		PlayerColor.add(Wool);
		Wool.setColumns(10);
		
		Lumber = new JTextField();
		Lumber.setBounds(86, 133, 28, 20);
		PlayerColor.add(Lumber);
		Lumber.setColumns(10);
		
		Roads_TXT = new JTextField();
		Roads_TXT.setText("Roads");
		Roads_TXT.setBounds(176, 74, 86, 30);
		PlayerColor.add(Roads_TXT);
		Roads_TXT.setColumns(10);
		
		Roads = new JTextField();
		Roads.setBounds(280, 78, 61, 20);
		PlayerColor.add(Roads);
		Roads.setColumns(10);
		
		Brick = new JTextField();
		Brick.setBounds(86, 161, 28, 20);
		PlayerColor.add(Brick);
		Brick.setColumns(10);
		
		Ore = new JTextField();
		Ore.setBounds(86, 192, 28, 20);
		PlayerColor.add(Ore);
		Ore.setColumns(10);
		
		txtVictoryPoints = new JTextField();
		txtVictoryPoints.setText("Victory Points");
		txtVictoryPoints.setBounds(149, 20, 86, 20);
		add(txtVictoryPoints);
		txtVictoryPoints.setColumns(10);
		
		VictoryPoints = new JTextField();
		VictoryPoints.setBounds(245, 20, 33, 20);
		add(VictoryPoints);
		VictoryPoints.setColumns(10);
		currentPlayer = playerAtHand;
		PlayerName.setText(currentPlayer.getName());
		PlayerColor.setBackground(currentPlayer.getPlayerColor());
	}
	// method to be invoked each time the panel is redisplayed upon that players turn
	//displays all current statistics.
	public void updatePlayer()
	{
		//sets current arrays held by player used to count number of each type player can use
		settlements = currentPlayer.getSettlements();
		roads = currentPlayer.getRoads();
		cities = currentPlayer.getCities();
		
		Wool.setText(""+currentPlayer.countResources(ResourceType.WOOL));
		Lumber.setText(""+currentPlayer.countResources(ResourceType.LUMBER));
		Brick.setText(""+currentPlayer.countResources(ResourceType.BRICK));
		Ore.setText(""+currentPlayer.countResources(ResourceType.ORE));
		Wheat.setText(""+currentPlayer.countResources(ResourceType.WHEAT));
		VictoryPoints.setText("" + currentPlayer.getPoints());
		Settlements.setText("" + (settlements.size()));
		Cities.setText("" + cities.size());
		Roads.setText("" + roads.size());
		
	}
}
