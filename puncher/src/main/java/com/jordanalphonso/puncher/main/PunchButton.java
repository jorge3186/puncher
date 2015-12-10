package com.jordanalphonso.puncher.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PunchButton extends JButton {
	
	private static String greenButton = "./imgs/punchIn.png";
	private static String redButton = "./imgs/punchOut.png";
	private ImageIcon greenImage;
	private ImageIcon redImage;
	
	public PunchButton(){
		
		//if statement to switch button images
		greenImage = new ImageIcon(greenButton);
		redImage = new ImageIcon(redButton);
		
		setIcon(greenImage);
		setFocusable(false);
		

		setAlignmentX(0);
		
		addActionListener(new ActionListener(){
			public void actionPerformed( ActionEvent e){
				if (getIcon() == greenImage){
					setIcon(redImage);
				}
				else{
					setIcon(greenImage);
				}
			}
		});	
		
		
	}

}
