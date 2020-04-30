package Aplication;

import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;

import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import ExcelFilesFunction.ReadExcelFile;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import OldClases.MainWindow_Old;
import OldClases.TestClases;
import Table.Table_Results_List;
import WindowView.Login;
import WindowView.MainWindow;
import WindowView.MesejePanelInAddResultsView;
import WindowView.TranscluentWindow;

public class Main_Aplication {

	public static void main(String[] args) {
		
		
		ReadFileWithGlobalTextVariable.CreadMasiveFromReadFile();
	
//		TestClases.Table_RequestToObektNaIzp();
		
//	 test.convertDocx2pdf("l:/ЛИ-РХ/Протоколи/3793_08.02.2019.docx");
		
//		TestClases.testSetText_Ob_na_Izp_Request();
		
//		TestClases.testNewRequestVew();
		
//		TestClases.startCreateProtokolDocx();
		
//		TestClases.testTableSampleList();
		
//		ReadFileWithGlobalTextVariable.CreadMasiveFromReadFile();
		
//		TestClases.AddResultsViewWithTable(3) ;
		
		
//		TestClases.Table_Results_List_Test(3) ;
		
//		TestClases.createProtocolWordDoc("Изтриване на Заявка");
		
//		TestClases.createRazprFormWordDoc();
		
//		System.out.println(ReadExcelFile.NumberFormatWithRounding("1.09531E-11"));
		
//		TestClases.ChangeStringToNumber();
		
		
//		ReadExcelFile.ReformatDoubleTo4decimalExponet("0.0000969769462295195");
//		TestClases.NumberFormatWithRounding("0.00135256");
		
//		TestClases.start_Table_Request_List_Test(4);
		
		
//		TestClases.StartMainWindow_Test();
		
		Map<String, String> globalIntVariableMap = ReadFileWithGlobalTextVariable.getGlobalIntVariableMap();
		for (String entry : globalIntVariableMap.values()) {
			System.out.println(Integer.parseInt(entry));	
		}
		
		StartMainWindow();
		
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



	


