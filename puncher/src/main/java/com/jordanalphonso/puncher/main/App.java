package com.jordanalphonso.puncher.main;

import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		
		try{
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					//open app
					MainFrame mainFrame = new MainFrame();
					mainFrame.createMainFrame();
				}
			});
		}
		catch (Exception e){
			e.printStackTrace();
		}

	}

}
