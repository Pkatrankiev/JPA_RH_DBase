package Aplication;

import javax.swing.JDialog;
import javax.swing.JFrame;

import DBase_Class.Users;
import OldClases.TestClases;
import Table.Table_Results_List;
import WindowView.Login;
import WindowView.MainWindow;
import WindowView.TranscluentWindow;

public class Main_Aplication {

	public static void main(String[] args) {
	
		TestClases.Table_RequestToObektNaIzp();
		
	
		
		
//		StartMainWindow();
	}
	private static void StartMainWindow() {
		TranscluentWindow round = new TranscluentWindow();
		
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	 new MainWindow(round);
		 		
		 			    	
		     }
		    });
		    thread.start();
		

	}
	
	
}



	


