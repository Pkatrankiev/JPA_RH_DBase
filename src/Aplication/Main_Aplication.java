package Aplication;



import GlobalVariable.ReadFileWithGlobalTextVariable;
import OldClases.TestClases;
import Reference.SampleMonthlyReference;
import WindowView.MainWindow;
import WindowView.TranscluentWindow;

public class Main_Aplication {
	
	public static void main(String[] args) {
	
		ReadFileWithGlobalTextVariable.CreadMasiveFromReadFile();
		
		
	
//		TestClases.Table_RequestToObektNaIzp();
		
//	 test.convertDocx2pdf("l:/ЛИ-РХ/Протоколи/3793_08.02.2019.docx");
		
//		TestClases.testSetText_Ob_na_Izp_Request();
		
		
		
		
//		TestClases.testNewRequestVew();
		
//		TestClases.testSetNewSimbolInDescriptionSampleGrupe_Request();
		
		
		
//		TestClases.startCreateProtokolDocx();
		
//		TestClases.testTableSampleList();
		
//		ReadFileWithGlobalTextVariable.CreadMasiveFromReadFile();
		
		
		
//		TestClases.AddResultsViewWithTable(3) ;
		
		
		
//		TestClases.AddResultsViewWithTable(3) ;
		
		
		
//		TestClases.createProtocolWordDoc("Генериране на Протокол");
		
//		Генериране на Заявка
		
		
//		TestClases.createRazprFormWordDoc();
		
//		System.out.println(ReadExcelFile.NumberFormatWithRounding("1.09531E-11"));
		
//		TestClases.ChangeStringToNumber();
		
		
//		ReadExcelFile.ReformatDoubleTo4decimalExponet("0.8775");
		
//		TestClases.NumberFormatWithRounding("0.00135256");
		
//		request  Results
		
//		TestClases.tbBackup("rhdbase","someuser","123","D:/000/123.sql");
		
		
		
		
//		TestClases.backupDB_From_RemoteServer();
		
		
		
		
//		try {
//			TestClases.getData();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		TestClases.Table_Results_List_Test(2, "request");
		
		
//		TestClases.setDataTime_Referense("28.10.2020 12:55");
				
		
//			CreateExcelFile.toExcel_exper();
		
		
//		TestClases.Change_In_Dobiv_Nuclide ();
		
//		TestClases.DialogView_DobivFromResultTableList_test (430);
		
//		TestClases.TestRequestMiniFrame("4460");
		
//		CreateListLeftPanelStartWindowClass.createListMissingRequest(2020);
		
//		TestClases.StartMainWindow_Test();
		
//		TestClases. CompareDoubleUnits(0.33,0.55);
		
//		TestClases. creatInDBaseRequestInResultsClass();
		
//		CreateListLeftPanelStartWindowClass.createListMissingResults(null);
 		
//		CreateListLeftPanelStartWindowClass.createListMissingProtokols(null);
		
//		TestClases.testReferenceView();
		
		
		
//		TestClases.testNewResultFormat(4600,40);
		
//		FunctionForGenerateWordDocFile.StoinostAndNeopredelenost(19.0464	,3.94921);
		
		
		
		
//		TestClases.testReferenceSample();
//		
//		TestClases.changeStringDateMeasurInResults("8.4.2019", "08.04.2019");
//		TestClases.TestIterator();
		
//		SampleMonthlyReference.SampleMonthly_Reference();
		
//		AplicantDAO.exctractDBase();
		
//		TestClases.MounthlyReferenceForCNRDWater_test();
		
//		TestClases.Table_BeisicClasses();
		
//		TestClases.backupDB_From_RemoteServer();
		
//		ReadTextFile.CreadMasiveFromReadFile("d:\\Q056001N.003");
//		ReadTextFile.convertTextFromFilenameToTextInNewFilename("d:\\Q056001N.003", "Spectrum1");
//		CreateListLeftPanelStartWindowClass.getLabelProtokol("4784");
		
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



	


