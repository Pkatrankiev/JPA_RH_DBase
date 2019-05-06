package Aplication;


import java.awt.FlowLayout;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Results;
import DBase_Class.TSI;
import DBase_Class.Users;
import OldClases.AddResultsViewWithTable_Test;
import OldClases.Foo;
import OldClases.TestSuperScript;
import OldClases.test;
import OldClases.testDialogLayeredPane;
import WindowView.AddResultsViewWithTable;
import WindowView.ChoiceListIzpPokazatel;
import WindowView.MainWindows;
import WindowView.MesejePanel;
import WindowView.TranscluentWindow;

public class Main_Aplication {

	public static void main(String[] args) {

		// TestIzpitvanPokazatelClass();

		// SetBasikValueInDataBase();

		// String fileName = "E:\\123\\123.txt";
		// ReadGamaFile.ReadGamaFile(fileName);

		// ReaderWordDoc.readMyDocument(fileName);

//		 ChangeObjectsInClass();
		
		
//		testScript () ;
		
	

//		updateUserRedac();
		
//		StartMainWindow();
		
//		test2();
//		new testDialogLayeredPane();
		
		AddResultsViewWithTable();
		
//		try {
//			startcreateProtokolDocx() ;
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		AddResultsViewWithTable.checkValueFrame(NuclideDAO.getValueSNuclideById(47), SampleDAO.getValueSampleById(22), 0.22, 0.005);
	
		
//		ChoiceListIzpPokazatel();
	
	
	}
	private static void test() {
		TranscluentWindow round = new TranscluentWindow();	
		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int m=0;
				for (int i = 0; i < 900000000; i++) {
					for (int k = 0;k  < 900000000; k++) {
						m++;
					}
				}
			}
		});
		thread.start();
		round.StopWindow();
//System.out.println(value);
	}
	public static void test2() {
		TranscluentWindow round = new TranscluentWindow();	
		Callable<Integer> worker = new Callable<Integer>() {
		    @Override
		    public Integer call() throws Exception {
		    	int m = metodTest();


		      return m;
		    }

		
		  };

		  Future<Integer> result = Executors.newSingleThreadExecutor().submit(worker);
		  try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  round.StopWindow();
	}
	
	private static int metodTest() throws InterruptedException {
		int m=0;

			for (int k = 0;k  < 900; k++) {
				Thread.sleep(10);
				m++;
			}
		return m;
	}
	
	@SuppressWarnings("unused")
	private static void ChoiceListIzpPokazatel() {
		JFrame f = new JFrame();
		 new ChoiceListIzpPokazatel(f, null) ;
	}
	
	
	@SuppressWarnings("unused")
	private static void updateUserRedac() {
		Users user = UsersDAO.getValueUsersById(3);
		List<Results> list = ResultsDAO.getInListAllValueResults();
		for (Results dobiv : list) {
			dobiv.setUser_redac(user);
			ResultsDAO.updateResults(dobiv);
		}
	}
	 
	@SuppressWarnings("unused")
	private static void AddResultsViewWithTable() {
		TranscluentWindow round = new TranscluentWindow();
		
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	 JFrame f = new JFrame();
//		 		new AddDobivViewWithTable(f,round, UsersDAO.getValueUsersById(3));
		 		new AddResultsViewWithTable(f,round, UsersDAO.getValueUsersById(3)); 
//		 		new AddResultsViewWithTable_Test(f,round, UsersDAO.getValueUsersById(3)); 
		     }
		    });
		    thread.start();
			
	}

	@SuppressWarnings("unused")
	private static void startcreateProtokolDocx() throws ParseException {

//		String codeRequest = "3833";
//		
//		Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", codeRequest);
//		String date_time_reference = RequestViewFunction.GenerateStringRefDateTimeFromRequest(choiseRequest);
//
//		Map<String, String> substitutionData = Generate_Map_For_Request_Word_Document.GenerateMapForRequestWordDocument(
//				choiseRequest, RequestViewFunction.generateStringListIzpitvanPokazatelFromrequest(choiseRequest),
//				RequestViewFunction.generateMasiveSampleDescriptionFromRequest(choiseRequest), date_time_reference);
		TestSuperScript.GenerateProtokolWordDoc("test.docx");
//		StartGenerateDocTemplate.GenerateProtokolWordDoc("Protokol.docx", choiseRequest, substitutionData);
	}

	@SuppressWarnings("unused")
	private static void testScript () {
		  String as ="asd4 5/78Ajj";	      
	     
	      JOptionPane.showMessageDialog(null, "Грешни:" + superscript1(as)+" "+ superscript2(as)+" "+ superscript3(as)+" ");
	}
	
	 public static String superscript1(String str) {
		 
		 AttributedString as = new AttributedString(str);
	     
	        as.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 1, 5);
		 
//		 Font font = new  Font("Tahoma", Font.PLAIN, 11);
//				 Map<TextAttribute,?> attributes = font.getAttributes();
//				 Map<TextAttribute,Object> newAttributes = new HashMap<TextAttribute,Object>(attributes);
//				 newAttributes.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);
//				 font = font.deriveFont(newAttributes);
//		 
// 	    str = str.replaceAll("0", "⁰");
// 	    str = str.replaceAll("1", "¹");
// 	    str = str.replaceAll("2", "²");
// 	    str = str.replaceAll("3", "³");
// 	    str = str.replaceAll("4", "⁴");
// 	    str = str.replaceAll("5", "⁵");
// 	    str = str.replaceAll("6", "⁶");
// 	    str = str.replaceAll("7", "⁷");
// 	    str = str.replaceAll("8", "⁸");
// 	    str = str.replaceAll("9", "⁹");  
// 	   str = str.replaceAll("/", "⸍");  
 	    return str;
 	}
	 public static String superscript2(String str) {
	 	    str = str.replaceAll("0", "⁰");
	 	    str = str.replaceAll("1", "¹");
	 	    str = str.replaceAll("2", "²");
	 	    str = str.replaceAll("3", "³");
	 	    str = str.replaceAll("4", "⁴");
	 	    str = str.replaceAll("5", "⁵");
	 	    str = str.replaceAll("6", "⁶");
	 	    str = str.replaceAll("7", "⁷");
	 	    str = str.replaceAll("8", "⁸");
	 	    str = str.replaceAll("9", "⁹");  
	 	   str = str.replaceAll("/", "⸍");  
	 	    return str;
	 	}
	 public static String superscript3(String str) {
	 	    str = str.replaceAll("0", "⁰");
	 	    str = str.replaceAll("1", "¹");
	 	    str = str.replaceAll("2", "²");
	 	    str = str.replaceAll("3", "³");
	 	    str = str.replaceAll("4", "⁴");
	 	    str = str.replaceAll("5", "⁵");
	 	    str = str.replaceAll("6", "⁶");
	 	    str = str.replaceAll("7", "⁷");
	 	    str = str.replaceAll("8", "⁸");
	 	    str = str.replaceAll("9", "⁹");  
	 	   str = str.replaceAll("/", "⸍");  
	 	    return str;
	 	}


	

	@SuppressWarnings("unused")
	private static void TestIzpitvanPokazatelClass() {
		List<IzpitvanPokazatel> list = IzpitvanPokazatelDAO.getInListAllValueIzpitvan_pokazatel();
		for (IzpitvanPokazatel izpitvanPokazatel : list) {
			System.out.println(izpitvanPokazatel.getMetody().getName_metody() + " / "
					+ izpitvanPokazatel.getPokazatel().getName_pokazatel());
		}
	}

	@SuppressWarnings("unused")
	private static void ChangeObjectsInClass() {
		
		List<Results> listResult = ResultsDAO.getInListAllValueResults();
		TSI obTSI = TSI_DAO.getValueTSIById(9);

		for (Results object : listResult) {
			object.setTsi(obTSI);
			ResultsDAO.updateResults(object);
		}
	
	}

	private static void StartMainWindow() {

		MainWindows win = new MainWindows();
		win.WindowNew();
	}
	
}



	


