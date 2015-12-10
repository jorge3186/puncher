package com.jordanalphonso.puncher.main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class SettingsFrame {

	//retrieve timezone changes using these
	private String currentTZ = getCurrentZone();
	private int timeZoneIndex = 4;
	private static String xmlFile = "./target/classes/timeZones.xml";
	private int currentID;
	private String currentZone;
	private JComboBox timeZonesBox;
	private JFrame settingsFrame = new JFrame();
	private MainFrame mainFrame;

	public String getCurrentTZ() {
		return currentTZ;
	}

	public void setCurrentTZ(String currentTZ) {
		this.currentTZ = currentTZ;
	}

	public int getTimeZoneIndex() {
		return timeZoneIndex;
	}

	public void setTimeZoneIndex(int timeZoneIndex) {
		this.timeZoneIndex = timeZoneIndex;
	}

	public JComboBox getTimeZonesBox() {
		return timeZonesBox;
	}

	public void setTimeZonesBox(JComboBox timeZonesBox) {
		this.timeZonesBox = timeZonesBox;
	}
	
	public SettingsFrame(){
		currentID = 3;
		currentZone = "GMT-06:00";
	}

	public void createSettingsFrame(){
		
		settingsFrame = new JFrame();

		settingsFrame.setTitle("Settings");
		settingsFrame.setSize(360,130);
		settingsFrame.setResizable(false);
		settingsFrame.setLocationRelativeTo(null);
		
		//content
		JPanel mainPanel = new JPanel();
		settingsFrame.setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		String[] timezones = {
				"Hawaii (UTC-10:00)",
				"Alaska (UTC-09:00)",
				"Pacific Time (UTC-08:00)",
				"Mountain Time (UTC-07:00)",
				"Central Time (UTC-06:00)",
				"Eastern Time (UTC-05:00)",
				"Atlantic Time (UTC-04:00)",
		};
		
		JLabel timeZoneLabel = new JLabel("Timezone:");
		timeZoneLabel.setBounds(20,15,100,25);
		timeZonesBox = new JComboBox(timezones);
		timeZonesBox.setBounds(90,15,250,25);
		timeZonesBox.setSelectedIndex(getCurrentID());
		JButton okButton = new JButton("OK");
		okButton.setBounds(245,50,95,25);
		
		mainPanel.add(timeZoneLabel);
		mainPanel.add(timeZonesBox);
		mainPanel.add(okButton);
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//close window
				timeZoneXMLWrite();
				timeZonesBox.setSelectedIndex(getCurrentID());
				settingsFrame.setVisible(false);
				settingsFrame.dispose();
			}
		});
		
		timeZonesBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent arg0) {
				
				if (timeZonesBox.getSelectedIndex() == 0){
					currentTZ = "GMT-09:00";
					timeZoneIndex = 0;
				}
				else if (timeZonesBox.getSelectedIndex() == 1){
					currentTZ = "GMT-08:00";
					timeZoneIndex = 1;
				}
				else if (timeZonesBox.getSelectedIndex() == 2){
					currentTZ = "GMT-07:00";
					timeZoneIndex = 2;
				}
				else if (timeZonesBox.getSelectedIndex() == 3){
					currentTZ = "GMT-06:00";
					timeZoneIndex = 3;
				}
				else if (timeZonesBox.getSelectedIndex() == 4){
					currentTZ = "GMT-05:00";
					timeZoneIndex = 4;
				}
				else if (timeZonesBox.getSelectedIndex() == 5){
					currentTZ = "GMT-04:00";
					timeZoneIndex = 5;
				}
				else if (timeZonesBox.getSelectedIndex() == 6){
					currentTZ = "GMT-03:00";
					timeZoneIndex = 6;
					}
				
			}
		});


		//set visible
		settingsFrame.setVisible(true);
		settingsFrame.addWindowListener(new WindowAdapter(){
			
			@Override
			public void windowClosing(WindowEvent e){
				
				timeZoneXMLWrite();
				timeZonesBox.setSelectedIndex(getCurrentID());
				settingsFrame.setVisible(false);
				settingsFrame.dispose();
				
			}
		});
		
	}
	
	public void timeZoneXMLWrite(){
		
		Document tzDoc = null;
		Element root = null;
		
		//timezones xml file
		File TZxml = new File(xmlFile);
		
		try {
			//if 
			if (TZxml.exists()){
				
				FileInputStream fis = new FileInputStream(TZxml);
				
				SAXBuilder saxB = new SAXBuilder(); 
				
				try {
					//load existing timezone xml file
					tzDoc = saxB.build(fis);
					root = tzDoc.getRootElement();
					fis.close();
					
					Element currentTZEL = new Element("currentTZ");
					
					if (root.getChildren("currentTZ") != null){
						//delete existing currentTZ
						root.removeChild("currentTZ");
						
						//add current tz element
						currentTZEL.addContent(new Element("name").setText((String) timeZonesBox.getSelectedItem()));
						currentTZEL.getChild("name").setAttribute(new Attribute("tz_id", String.valueOf(timeZoneIndex)));
						currentTZEL.addContent(new Element("zone").setText(currentTZ));
						
						root.addContent(currentTZEL);
						XMLOutputter xmlOut = new XMLOutputter();
						xmlOut.setFormat(Format.getPrettyFormat());
						xmlOut.output(tzDoc, new FileWriter(xmlFile));
						
					}
					else{
						//add current tz element if not found
						currentTZEL.addContent(new Element("name").setText((String) timeZonesBox.getSelectedItem()));
						currentTZEL.getChild("name").setAttribute(new Attribute("tz_id", String.valueOf(timeZoneIndex)));
						currentTZEL.addContent(new Element("zone").setText(currentTZ));
						
						root.addContent(currentTZEL);
						XMLOutputter xmlOut = new XMLOutputter();
						xmlOut.setFormat(Format.getPrettyFormat());
						xmlOut.output(tzDoc, new FileWriter(xmlFile));
						
					}

				} catch (JDOMException e) {
					e.printStackTrace();
				}
				
			}
			
			else{
				
				//create new xmlFile for holding timezones
			}
		}
		catch (IOException IOe) {
			
			System.out.println(IOe.getMessage());
			
		}
		
	}
	
	public String getCurrentZone(){
		
		SAXBuilder saxB = new SAXBuilder();
		
		try{
			
			Document tzDoc = saxB.build(xmlFile);
			Element root = tzDoc.getRootElement();
			
			currentZone = root.getChild("currentTZ").getChild("zone").getText();
			
		}
		catch(Exception IOe) {
			IOe.printStackTrace();
		}
		
		return currentZone;		
		
	}
	
	public int getCurrentID(){
		
		SAXBuilder saxB = new SAXBuilder();

		
		try{
			
			Document tzDoc = saxB.build(xmlFile);
			Element root = tzDoc.getRootElement();
			
			currentID = Integer.parseInt(root.getChild("currentTZ").getChild("name").getAttributeValue("tz_id"));

		}
		catch(Exception IOe) {
			IOe.printStackTrace();
		}
		
		return currentID;
	}
	
	public String getCurrentZoneName(){
		
		SAXBuilder saxB = new SAXBuilder();
		String currentZoneName = null;
		
		try{
			
			Document tzDoc = saxB.build(xmlFile);
			Element root = tzDoc.getRootElement();
			
			currentZoneName = root.getChild("currentTZ").getChild("name").getText();

		}
		catch(Exception IOe) {
			IOe.printStackTrace();
		}
		
		return currentZoneName;
	}


}

