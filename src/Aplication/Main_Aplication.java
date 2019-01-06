package Aplication;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;

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
import WindowView.MainWindows;
import WindowView.ReadFile;
import WindowView.ReaderWordDoc;



public class Main_Aplication {

	public static void main(String[] args) {
		
		
//		TestIzpitvanPokazatelClass();

//		 SetBasikValueInDataBase();

		 String fileName = "E:\\123\\123.txt";
//		 ReaderWordDoc.readMyDocument(fileName);

//		ChangeObjectsInClass();

//			StartMainWindow();

		
		 ReadFile.ReadFile(fileName);

//		SetDBfromWordDoc.setVolume(FILENAME);
		
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