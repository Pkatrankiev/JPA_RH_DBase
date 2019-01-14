package Aplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Metody_to_Pokazatel;
import DBase_Class.Nuclide;
import DBase_Class.Nuclide_to_Pokazatel;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import OldClases.test;
import WindowView.MainWindows;
import WindowView.ReadGamaFile;
import WindowView.ReaderWordDoc;



public class Main_Aplication {

	public static void main(String[] args) {
		
		
//		TestIzpitvanPokazatelClass();

//		 SetBasikValueInDataBase();

//		 String fileName = "E:\\123\\123.txt";
//		 ReadGamaFile.ReadGamaFile(fileName);
		 
//		 ReaderWordDoc.readMyDocument(fileName);

//		ChangeObjectsInClass();
		
//		MesejePanel();
		
		

//		String str1= "123";
//		String str2= "123";
//		int int1 = 6;
//		String ss = "";
//		
//		for (int i = 0; i < 5; i++) {
//			 int1++;
//			 System.out.format("|%-10s|%-10d|%-10s|\n", str1, int1, str2);
////			ss = ss+str3;
//		}
//		int n = JOptionPane.showConfirmDialog(
//			    null,
//			    ss,
//			    "An Inane Question",
//			    JOptionPane.ERROR_MESSAGE);
		
//	        if (result == JOptionPane.PLAIN_MESSAGE){

//	        	System.out.println(n);;
//	        		        }

		
					StartMainWindow();

		
		 

//		SetDBfromWordDoc.setVolume(FILENAME);
		
	}
	private static void MesejePanel() {
		String[][] str = new String[4][];
		str[0] = new String[] { "Pie", "2.2", "12" };
		str[1] = new String[] { "Cracker", "4", "15" };
		str[2] = new String[] { "Pop tarts", "1", "4" };
		str[3] = new String[] { "Sun Chips", "5", "2" };
		Object[] options1 = { "Да",  "Отказ" };
		  JPanel panel = new JPanel();
		  panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		  panel.add(createTextPanel("За изтриване"));
		  panel.add(test.creadJPanel(str));
		  panel.add(createTextPanel("За запис"));
		  panel.add(test.creadJPanel(str));
		 int result = JOptionPane.showOptionDialog(null,panel, "Enter a Number",
	                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE,
	                null, options1, null);
		 System.out.println(result);
	}
	
	private static JPanel createTextPanel(String str) {
		JPanel panel2 = new JPanel(); 
		  FlowLayout flowLayout = (FlowLayout) panel2.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel2.add(new JLabel(str));
		return panel2;
	}
	
	private static void TestIzpitvanPokazatelClass() {
		List<IzpitvanPokazatel> list =  IzpitvanPokazatelDAO.getInListAllValueIzpitvan_pokazatel();
		for (IzpitvanPokazatel izpitvanPokazatel : list) {
			System.out.println(izpitvanPokazatel.getMetody().getName_metody()+" / "+izpitvanPokazatel.getPokazatel().getName_pokazatel());
		}
	}


	private static void ChangeObjectsInClass() {
		List_izpitvan_pokazatel pokaz = List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelById(8);
		
		List<Results> listResult = ResultsDAO.getListResultsFromColumnByVolume("pokazatel", pokaz);
		
		for (Results object : listResult) {
			Request request = object.getSample().getRequest();
			List<IzpitvanPokazatel> izpitPok = IzpitvanPokazatelDAO.getValueIzpitvan_pokazatelByRequest(request);
			System.out.println("***********************************************");
			System.out.println(izpitPok.size());
			for (IzpitvanPokazatel izpitvanPokazatel : izpitPok) {
				System.out.println("///////////////////////////////////////////////");
				System.out.println(izpitvanPokazatel.getPokazatel().getId_pokazatel());
				if(izpitvanPokazatel.getPokazatel().getId_pokazatel()==pokaz.getId_pokazatel()){
					System.out.println("-------------------------------------------------");
					System.out.println(pokaz.getId_pokazatel());
					Metody metody_new = izpitvanPokazatel.getMetody();
					Results res = ResultsDAO.getValueResultsById(object.getId_results());
					System.out.println(metody_new.getId_metody()+"   "+res.getId_results());
					res.setMetody(metody_new);
					ResultsDAO.updateResults(res);
				}
			}
			
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(listResult.size());
	}

	private static void StartMainWindow() {
				
		MainWindows win = new MainWindows();
		win.WindowNew();
	}

	public static String alignExpon(double basic, double foll) {
		NumberFormat frm = new DecimalFormat("0.00E00");
		NumberFormat frm_foll = new DecimalFormat("0.00");
		String str_bas = frm.format(basic);
		double expon = Double.valueOf("1.0" + str_bas.substring(str_bas.indexOf("E")));
		foll = foll / expon;
		String str_foll = frm_foll.format(foll) + str_bas.substring(str_bas.indexOf("E"));
		if (!str_foll.contains("E-")) { // don't blast a negative sign
			str_foll = str_foll.replace("E", "E+");
		}
		str_foll = str_foll.replace(",", ".");
		return str_foll;
	}

	private static String formatter(double number) {
		DecimalFormat formatter = new DecimalFormat("0.00E00");
		String fnumber = formatter.format(number);
		if (!fnumber.contains("E-")) { // don't blast a negative sign
			fnumber = fnumber.replace("E", "E+");
		}
		fnumber = fnumber.replace(",", ".");
		return fnumber;
	}
}