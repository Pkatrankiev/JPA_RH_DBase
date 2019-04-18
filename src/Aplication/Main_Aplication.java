package Aplication;


import java.awt.FlowLayout;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.text.ParseException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Results;
import DBase_Class.TSI;
import DBase_Class.Users;
import OldClases.TestSuperScript;
import OldClases.test;
import WindowView.AddResultsViewWithTable;
import WindowView.ChoiceListIzpPokazatel;
import WindowView.MainWindows;
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
		
//		 MesejePanel();

//		updateUserRedac();
		
		StartMainWindow();
		
//		AddResultsViewWithTable();
		
//		try {
//			startcreateProtokolDocx() ;
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		AddResultsViewWithTable.checkValueFrame(NuclideDAO.getValueSNuclideById(47), SampleDAO.getValueSampleById(22), 0.22, 0.005);
	
		
//		ChoiceListIzpPokazatel();
	
	
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
	private static void MesejePanel() {
		String[][] str = new String[4][];
		str[0] = new String[] { "Pie", "2.2", "12" };
		str[1] = new String[] { "Cracker", "4", "15" };
		str[2] = new String[] { "Pop tarts", "1", "4" };
		str[3] = new String[] { "Sun Chips", "5", "2" };
		Object[] options1 = { "Да", "Отказ" };
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(createTextPanel("За изтриване"));
		panel.add(test.creadJPanel(str));
		panel.add(createTextPanel("За запис"));
		panel.add(test.creadJPanel(str));
		int result = JOptionPane.showOptionDialog(null, panel, "Enter a Number", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.ERROR_MESSAGE, null, options1, null);
		System.out.println(result);
	}

	private static JPanel createTextPanel(String str) {
		JPanel panel2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel2.add(new JLabel(str));
		return panel2;
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