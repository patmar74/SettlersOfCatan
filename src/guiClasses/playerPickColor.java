package guiClasses;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import players.Player;
public class playerPickColor implements ActionListener {
	Color playerColor;
   JFrame myFrame = null;
   public static void main(String[] a) {
      (new playerPickColor()).test(null);
   }
   public void test(String name) {
      myFrame = new JFrame("showOptionDialog() Test");
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container myPane = myFrame.getContentPane();
      JButton myButton = new JButton("Click Here To Show Selections for " + name);
      myButton.addActionListener(this);
      myPane.add(myButton);
      myFrame.pack();
      myFrame.setVisible(true);
   }
   public void actionPerformed(ActionEvent e) {
      int messageType = JOptionPane.QUESTION_MESSAGE;
      String[] options = {"red", "blue", "yellow", "green"};
      int color = JOptionPane.showOptionDialog(myFrame, 
         "What Color", 
         "Option Dialog Box", 0, messageType, 
         null, options, "red");
      if(color == 0)
      {
    	  playerColor = Color.red;
    	  myFrame.dispose();
      }
      if(color == 1)
      {
    	  playerColor = Color.blue;
    	  myFrame.dispose();
      }
      if(color == 2)
      {
    	  playerColor = Color.yellow;
    	  myFrame.dispose();
      }
      if(color == 3)
      {
;    	  playerColor = Color.green;
		  myFrame.dispose();
      }
      
   }
public Color getPlayerColor() {
	return playerColor;
}
public void setPlayerColor(Color playerColor) {
	this.playerColor = playerColor;
}
   
}