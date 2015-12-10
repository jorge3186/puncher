package com.jordanalphonso.puncher.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame {
	private String OS = System.getProperty("os.name").toLowerCase();
	
	private SettingsFrame settingsFrame;
	private AboutMessage aboutMessage;
	private AdminFrame admin;
	private ReportFrame reportFrame;
	
	public void createMainFrame() {
		
		//setup
		setTitle("TimePuncher");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		// layout
		JPanel mainPanel = new JPanel(new BorderLayout());
		add(mainPanel);
		
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3, 15, 10, 15);
		
		//all components	
		
			//menu bar components
			JMenuBar menuBar = new JMenuBar();
			menuBar.setBackground(null);
			JMenu fileMenu = new JMenu("File");
			JMenu helpMenu = new JMenu("Help");
			menuBar.add(fileMenu);
			menuBar.add(helpMenu);		
				//menu items	
					//file menu
					JMenuItem settings = new JMenuItem("Settings");
					JMenuItem adminLogin = new JMenuItem("Admin Login                   ");
					JMenuItem report = new JMenuItem("Report");
					JMenuItem exit = new JMenuItem("Exit                                      ");
					fileMenu.add(settings);
					fileMenu.add(adminLogin);
					fileMenu.add(report);
					fileMenu.addSeparator();
					fileMenu.add(exit);
					//settingsFrame popup
					settings.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							try{								
								settingsFrame = new SettingsFrame();
								settingsFrame.createSettingsFrame();
							}
							catch (Exception ev){
								ev.printStackTrace();
							}
						}
					});
					exit.setAccelerator(KeyStroke.getKeyStroke("control Q"));
					exit.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
								System.exit(0);
						}
					});
					//admin page
					adminLogin.setAccelerator(KeyStroke.getKeyStroke("control L"));
					//help menu
					JMenuItem about = new JMenuItem("About TimePuncher        ");
					helpMenu.add(about);
					about.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							aboutMessage = new AboutMessage();
							aboutMessage.createAboutMessage();
						}
					});
					adminLogin.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent ADe){
							try {
									admin = new AdminFrame();
									admin.windowSetup();

							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
					});
					//report menu
					report.setAccelerator(KeyStroke.getKeyStroke("control R"));
					report.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent Re){
								reportFrame = new ReportFrame();
								reportFrame.createReportFrame();
							}
						});
					
			
			//main panel components
					
				//date and time
				UserDateTime timePanel = new UserDateTime("hh:mm a");
				timePanel.setMinimumSize(new Dimension(360,100));
				timePanel.setMaximumSize(new Dimension(360,100));
				timePanel.setPreferredSize(new Dimension(360,100));
				timePanel.setFont(new Font("San-Serif", Font.BOLD, 76));
				UserDateTime datePanel = new UserDateTime("E M/d/y");
				datePanel.setFont(new Font("San-Serif", Font.BOLD, 20));
				datePanel.setForeground(Color.DARK_GRAY);
				
				JPanel dateTimePanel = new JPanel(new BorderLayout());
				dateTimePanel.add(timePanel, BorderLayout.NORTH);
				dateTimePanel.add(datePanel, BorderLayout.LINE_END);
				
				//combo boxes
				String[] depts = { "Select Department", "" };
				String[] users = { "Select Employee", "" };
				JComboBox deptBox = new JComboBox(depts);
				deptBox.setBorder(BorderFactory.createTitledBorder("Department:"));
				deptBox.setPreferredSize(new Dimension(300, 30));
				JComboBox userBox = new JComboBox(users);
				userBox.setBorder(BorderFactory.createTitledBorder("Employee:"));
				userBox.setPreferredSize(new Dimension(300, 30));
				
				//punch IN-OUT button
				PunchButton punchButton = new PunchButton();
				
				//notes and sheets
				JTextArea notesBox = new JTextArea();
				notesBox.setBorder(BorderFactory.createTitledBorder("Notes:"));
				notesBox.setLineWrap(true);
				notesBox.setWrapStyleWord(true);
				notesBox.setBackground(null);
				
				JTextArea logsBox = new JTextArea();
				logsBox.setBorder(BorderFactory.createTitledBorder("Logs:"));
				logsBox.setEditable(false);
				logsBox.setLineWrap(true);
				logsBox.setWrapStyleWord(true);
				logsBox.setBackground(null);

		
		//// GUI ///////////////////////////////////////////////////		
		setJMenuBar(menuBar);		

			// mainPanel - GridBag 
				//time clock
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridy = 0;
				c.gridx = 0;
				c.gridwidth = 2;
				c.weighty = 20;
				c.weightx = 1;
				mainPanel.add(dateTimePanel, c);
				
				//department combo box
				c.anchor = GridBagConstraints.LINE_START;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 2;
				c.gridwidth = 2;
				c.ipady = 15;
				mainPanel.add(deptBox, c);
				
				//user combo box
				c.gridx = 0;
				c.gridy = 3;
				c.gridwidth = 2;
				mainPanel.add(userBox, c);
				
				//punch in-out button
				c.fill = GridBagConstraints.BOTH;
				c.gridx = 0;
				c.gridy = 4;
				c.gridheight = 2;
				c.ipady = 20;
				mainPanel.add(punchButton, c);
				
				//notes box
				c.fill = GridBagConstraints.BOTH;
				c.gridx = 2;
				c.gridy = 0;
				c.gridwidth = 1;
				c.ipady = 0;
				c.gridheight = 4;
				c.weightx = 50;
				mainPanel.add(notesBox, c);

				//logs box
				c.gridx = 2;
				c.gridy = 4;
				c.gridwidth = 1;
				c.ipady = 0;
				c.gridheight = 1;
				mainPanel.add(logsBox, c);
				
	
		//show window
		setVisible(true);
	}

}
