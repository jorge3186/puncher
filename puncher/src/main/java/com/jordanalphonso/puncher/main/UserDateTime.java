package com.jordanalphonso.puncher.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JLabel;
import javax.swing.Timer;

public class UserDateTime extends JLabel {
	
	private SimpleDateFormat timeF;
	private SettingsFrame settingsFrame;

	public UserDateTime(String format){
		//time and date format
		timeF = new SimpleDateFormat(format);

		
  	  	//recurring time change
		ActionListener clockUpdater = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  
		    	  //grab timezone changes
		    	  settingsFrame = new SettingsFrame();
	    		  String TZ = settingsFrame.getCurrentTZ();
		    	 
		    	  
		    	  //grab updated time and date
		    	  Date time = new Date(); 
		    	  
		    	  //set Date and Time to current
		    	  timeF.setTimeZone(TimeZone.getTimeZone(TZ));	
		    	  setText(timeF.format(time));
		    	  
		      }
		  };
		Timer clockTimer = new Timer(100, clockUpdater);
		clockTimer.setInitialDelay(100);
		clockTimer.start();

	}
	
}
