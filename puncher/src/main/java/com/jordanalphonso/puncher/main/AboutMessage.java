package com.jordanalphonso.puncher.main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AboutMessage extends JFrame {
	
	public void createAboutMessage() {
		
		setSize(150,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// option pane
		JOptionPane.showMessageDialog(this, "TimePuncher \u00a9\nJordan Alphonso \u00ae 2015 ", "About TimePuncher", 1);
		
		
	}

}
