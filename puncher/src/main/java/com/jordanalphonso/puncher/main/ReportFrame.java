package com.jordanalphonso.puncher.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ReportFrame extends JFrame {
	
	MainFrame mainFrame;
	
	public void createReportFrame() {
		
		setTitle("TimePuncher Report");
		setSize(700, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		
		String[] columnNames = {"WeekDay", "Date", "Time-In", "Time-Out", "Total Hours" };
		
		//placeholders
		Object[][] data = {{"Friday", "10/9/2015", "8:01", "4:59", new Float(7.98)}};
		
		JTable tablePanel = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(tablePanel);
		tablePanel.setFillsViewportHeight(true);
		setContentPane(scrollPane);
		
		
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent We){
				setVisible(false);
				dispose();
			}
		});
		
		setVisible(true);
	}

}
