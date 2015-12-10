package com.jordanalphonso.puncher.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminFrame extends JFrame {
	
	private JPanel loginPanel;
	private JPanel adminPanel;
	private JTextField userField;
	private JPasswordField pwField;
	private String pw;
	private String usr;
	private MainFrame mainFrame;
	
	public void windowSetup(){
		
		//setup
				setTitle("Admin Information");
				setSize(350, 300);
				setResizable(false);
				setLocationRelativeTo(null);
				
				//show login screen
				adminLoginContent();
				
				
				//make sure window closes properly
				addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent We){
						setVisible(false);
						dispose();
					}
				});
				
				//show window
				setVisible(true);
				

		
	}
	
	public void adminLoginContent(){
		
		//setup and set content to be login screen
		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(loginPanel);
		
		//add components to login screen
		JLabel userLabel = new JLabel("           User:");
		userLabel.setBounds(20, 30, 130, 25);
		userField = new JTextField(12);
		userField.setBounds(100, 30, 220, 25);
		JLabel pwLabel = new JLabel("Password:");
		pwLabel.setBounds(20, 60, 130, 25);
		pwField = new JPasswordField(10);
		pwField.setBounds(100, 60, 220, 25);
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(235, 95, 85, 25);
		
		loginPanel.add(userLabel);
		loginPanel.add(userField);
		loginPanel.add(pwLabel);
		loginPanel.add(pwField);
		loginPanel.add(loginButton);
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){

				usr = getAdminUser();
				pw = getAdminPW();
				
				adminControlContent();
			}
		});
		
	}

	public void adminControlContent(){
		
		loginPanel.setVisible(false);
		adminPanel = new JPanel();
		adminPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(adminPanel);

	}
	
	public String getAdminUser(){
		//String user = userField.getText();
		
		return null;
		
	}
	
	public String getAdminPW(){
		//if (pw == null){
		//	pw = "";
		//}
		//else {
		//	pw = pwField.getPassword().toString();
		//}
		return null;
	}
}
