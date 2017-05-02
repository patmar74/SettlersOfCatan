package guiClasses;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import players.Player;
import players.playerColors;

public class SetupGame {

	private JLabel playerPromptLabel;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private int playerCount = 1;
	
	SetupGame(){

		// until there the four players are defined, we prompt for players
		while (playerCount < 4){
  		// creates window entitled "Player Prompt"
		JFrame playerPrompt = new JFrame("Player Prompt");
		playerPrompt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playerPrompt.setBounds(0, 0, 500, 500);
		// creates label for prompt
		JLabel playerPromptPanel = new JLabel();
		// creates text field for user to enter name of player
		JTextField playerName = new JTextField(100);
		playerPromptPanel.setText("Enter the name for player number ");
		playerPrompt.add(playerPromptPanel, BorderLayout.NORTH);
		playerPrompt.add(playerName, BorderLayout.SOUTH);
		// creates button that adds player after name is typed
		JButton button = new JButton("Add Player");
		playerPrompt.add(button, BorderLayout.CENTER);
		// overrides actionPerformed() by determining if button is pressed
		button.addActionListener(new ActionListener() {

			   @Override
			   public void actionPerformed(ActionEvent e) {
			       JButton btn =  (JButton) e.getSource();
			   	}
			   });
		// calls setupPlayer() function and increments the amount of players for 
		// the while loop
		setupPlayer(playerName.getText(), playerCount);
		playerCount++;
		System.out.println(playerCount);
		playerPrompt.setVisible(true);
		}
	}
		
	
	// switches player number and adds player to array list
	public void setupPlayer(String name, int playerCount){
			switch (playerCount){
			case 1:
				Player playerOne = new Player(name, Color.red);
				playerOne.setPoints(2);
				playerList.add(playerOne);
				break;
			case 2:
				Player playerTwo = new Player(name, Color.blue);
				playerTwo.setPoints(2);
				playerList.add(playerTwo);
				break;
			case 3:
				Player playerThree = new Player(name, Color.white);
				playerThree.setPoints(2);
				playerList.add(playerThree);
				break;
			case 4:
				Player playerFour = new Player(name, Color.yellow);
				playerFour.setPoints(2);
				playerList.add(playerFour);
				break;
			}
			// exists solely for testing
			if (playerCount == 4){
				displayPlayers();
			}
	}
	
	// function is for testing only and will no longer be relevant upon the game's 
	// creation
	public void displayPlayers(){
		for (int i = 0; i < playerList.size(); i++){
			System.out.println(playerList.get(i).getName());
			System.out.println(playerList.get(i).getPlayerColor());
		}
	}
	
}
