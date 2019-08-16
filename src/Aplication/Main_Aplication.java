package Aplication;

import javax.swing.JDialog;
import javax.swing.JFrame;

import DBase_Class.Users;
import OldClases.TestClases;
import OldClases.test;
import Table.Table_Results_List;
import WindowView.Login;
import WindowView.MainWindow;

import WindowView.TranscluentWindow;

public class Main_Aplication {

	public static void main(String[] args) {
	
//		TestClases.Table_RequestToObektNaIzp();
		
//	 test.convertDocx2pdf("l:/ЛИ-РХ/Протоколи/3793_08.02.2019.docx");
		
//		TestClases.testSetText_Ob_na_Izp_Request();
//		TestClases.testNewRequestVew();
//		TestClases.startCreateProtokolDocx();
//		TestClases.testTableSampleList();
		
		TestClases.AddResultsViewWithTable() ;
		
//		TestClases.start_Table_Request_List();
		
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



	


