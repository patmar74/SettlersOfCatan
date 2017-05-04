package guiClasses;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import players.Player;

public class setupGameOfficial
{
	public static void main(String[] args)
	{
		int numPlayers;
		Player player1= new Player();
		Player player2 = new Player();
		Color player1Color;
		Color player2Color;
		
		//creating buttons to have each player select a color
		/*
		JButton[] option = new JButton[4];
		for(int i = 0; i < 4; i++)
		{
			option[i] = new JButton();
		}
		option[0].setText("blue");
		option[1].setText("red");
		option[2].setText("yellow");
		option[3].setText("green");
		option[0].setEnabled(true);
		option[1].setEnabled(true);
		option[2].setEnabled(true);
		option[3].setEnabled(true);
		*/
		// array of buttons organized in format to put into joptionpane
		/*
		Object[] options = {option[0], option[1], option[2], option[3]};
		
		int playerSelection = JOptionPane.showOptionDialog(null, "Player Color Selection", "Colors", 0, 
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		System.out.print(playerSelection);
		*/
		
		numPlayers=Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of Players"));
		// assignment of player names
		if (numPlayers == 2)
		{
			player1.setName(JOptionPane.showInputDialog(null,"Enter player 1 name"));
			player2.setName(JOptionPane.showInputDialog(null,"Enter player 2 name"));
			playerPickColor player1ColorSelectionFrame = new playerPickColor();
			player1ColorSelectionFrame.test(player1.getName());
			player1.setPlayerColor(player1ColorSelectionFrame.getPlayerColor());
			playerPickColor player2ColorSelectionFrame = new playerPickColor();
			player2ColorSelectionFrame.test(player2.getName());
			player2.setPlayerColor(player2ColorSelectionFrame.getPlayerColor());
		}
		if (numPlayers == 3)
		{
			Player player3 = new Player();
			player1.setName(JOptionPane.showInputDialog(null,"Enter player 1 name"));
			player2.setName(JOptionPane.showInputDialog(null,"Enter player 2 name"));
			player3.setName(JOptionPane.showInputDialog(null,"Enter player 3 name"));
			playerPickColor player1ColorSelectionFrame = new playerPickColor();
			player1ColorSelectionFrame.test(player1.getName());
			player1.setPlayerColor(player1ColorSelectionFrame.getPlayerColor());
			playerPickColor player2ColorSelectionFrame = new playerPickColor();
			player2ColorSelectionFrame.test(player2.getName());
			player2.setPlayerColor(player2ColorSelectionFrame.getPlayerColor());
			playerPickColor player3ColorSelectionFrame = new playerPickColor();
			player3ColorSelectionFrame.test(player3.getName());
			player3.setPlayerColor(player3ColorSelectionFrame.getPlayerColor());
		}
		if (numPlayers == 4)
		{
			Player player3 = new Player();
			Player player4 = new Player();
			player1.setName(JOptionPane.showInputDialog(null,"Enter player 1 name"));
			player2.setName(JOptionPane.showInputDialog(null,"Enter player 2 name"));
			player3.setName(JOptionPane.showInputDialog(null,"Enter player 3 name"));
			player4.setName(JOptionPane.showInputDialog(null,"Enter player 4 name"));
			playerPickColor player1ColorSelectionFrame = new playerPickColor();
			player1ColorSelectionFrame.test(player1.getName());
			player1.setPlayerColor(player1ColorSelectionFrame.getPlayerColor());
			playerPickColor player2ColorSelectionFrame = new playerPickColor();
			player2ColorSelectionFrame.test(player2.getName());
			player2.setPlayerColor(player2ColorSelectionFrame.getPlayerColor());
			playerPickColor player3ColorSelectionFrame = new playerPickColor();
			player3ColorSelectionFrame.test(player3.getName());
			player3.setPlayerColor(player3ColorSelectionFrame.getPlayerColor());
			playerPickColor player4ColorSelectionFrame = new playerPickColor();
			player4ColorSelectionFrame.test(player4.getName());
			player4.setPlayerColor(player4ColorSelectionFrame.getPlayerColor());
		}
		// if input fails will prompt again with same results
		while(numPlayers > '4' || numPlayers<'2' && numPlayers != 2 && numPlayers!=3 && numPlayers!=4 )
		{
			JOptionPane.showMessageDialog(null, "Invalid Input Try Again");
			numPlayers=Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of Players"));
			
			if (numPlayers == 2)
			{
				player1.setName(JOptionPane.showInputDialog(null,"Enter player 1 name"));
				player2.setName(JOptionPane.showInputDialog(null,"Enter player 2 name"));
				playerPickColor player1ColorSelectionFrame = new playerPickColor();
				player1ColorSelectionFrame.test(player1.getName());
				player1.setPlayerColor(player1ColorSelectionFrame.getPlayerColor());
				playerPickColor player2ColorSelectionFrame = new playerPickColor();
				player2ColorSelectionFrame.test(player2.getName());
				player2.setPlayerColor(player2ColorSelectionFrame.getPlayerColor());
			}
			if (numPlayers == 3)
			{
				Player player3 = new Player();
				player1.setName(JOptionPane.showInputDialog(null,"Enter player 1 name"));
				player2.setName(JOptionPane.showInputDialog(null,"Enter player 2 name"));
				player3.setName(JOptionPane.showInputDialog(null,"Enter player 3 name"));
				playerPickColor player1ColorSelectionFrame = new playerPickColor();
				player1ColorSelectionFrame.test(player1.getName());
				player1.setPlayerColor(player1ColorSelectionFrame.getPlayerColor());
				playerPickColor player2ColorSelectionFrame = new playerPickColor();
				player2ColorSelectionFrame.test(player2.getName());
				player2.setPlayerColor(player2ColorSelectionFrame.getPlayerColor());
				playerPickColor player3ColorSelectionFrame = new playerPickColor();
				player3ColorSelectionFrame.test(player3.getName());
				player3.setPlayerColor(player3ColorSelectionFrame.getPlayerColor());
			}
			if (numPlayers == 4)
			{
				Player player3 = new Player();
				Player player4 = new Player();
				player1.setName(JOptionPane.showInputDialog(null,"Enter player 1 name"));
				player2.setName(JOptionPane.showInputDialog(null,"Enter player 2 name"));
				player3.setName(JOptionPane.showInputDialog(null,"Enter player 3 name"));
				player4.setName(JOptionPane.showInputDialog(null,"Enter player 4 name"));
				playerPickColor player1ColorSelectionFrame = new playerPickColor();
				player1ColorSelectionFrame.test(player1.getName());
				player1.setPlayerColor(player1ColorSelectionFrame.getPlayerColor());
				playerPickColor player2ColorSelectionFrame = new playerPickColor();
				player2ColorSelectionFrame.test(player2.getName());
				player2.setPlayerColor(player2ColorSelectionFrame.getPlayerColor());
				playerPickColor player3ColorSelectionFrame = new playerPickColor();
				player3ColorSelectionFrame.test(player3.getName());
				player3.setPlayerColor(player3ColorSelectionFrame.getPlayerColor());
				playerPickColor player4ColorSelectionFrame = new playerPickColor();
				player4ColorSelectionFrame.test(player4.getName());
				player4.setPlayerColor(player4ColorSelectionFrame.getPlayerColor());
			}
		}
	}
}

