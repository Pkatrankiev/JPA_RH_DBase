package Aplication;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JFrame;
import javax.xml.ws.*;

import DBase_Class.Izpitvan_produkt;
import DBase_Class.Nuclide;

import WindowView.Login;
import WindowView.MainWindows;
import WindowView.ReaderWordDoc;
import WindowView.SetDBfromWordDoc;
import WindowView.TablePrintDemo;
import DBase_Class.Izpitvan_pokazatel;

public class Main_Aplication {

	public static void main(String[] args) {

		// SetBasikValueInDataBase();

//		String fileName = "c:\\Users\\Acer\\Desktop\\3344.doc";
		 String fileName = "c:\\Users\\ALPHA\\Desktop\\3281.doc";
		 SetDBfromWordDoc.setVolume(fileName);

//		 ReaderWordDoc.readMyDocument(fileName);

		// StartMainWindow();

		
//		double st1 = 29478;
//		double st2 = 5902;
//		System.out.println(formatter(st1));
//		// st2 = Double.valueOf("9.18E+5");
//		System.out.println(alignExpon(st1, st2));

	}

	private static void StartMainWindow() {
		MainWindows win = new MainWindows();
		win.Window();
	}

	private static void SetBasikValueInDataBase() {
		External_applicantDAO.setBasikValueExternal_applicant();
		Izpitvan_produktDAO.setBasikValueIzpitvan_produkt();
		Ind_num_docDAO.setBasikValueInd_num_doc();
		MetodyDAO.setBasikValueMetody();
		List_izpitvan_pokazatelDAO.setBasikValuePokazatel();
		NuclideDAO.setBasicValueNuclide();
		Obekt_na_izpitvaneDAO.setBasicValueObekt_na_izpitvane();
		RazmernostiDAO.setBasicValueRazmernosti();
		PostDAO.setBasikValuePost();
		UsersDAO.setBasicValueUsers();
		ZabelejkiDAO.setBasicValueZabelejki();
		Internal_applicantDAO.setBasikValueInternal_applicant();
		RequestDAO.setBasicValueRequest();
		SampleDAO.setBasicValueSample();
		Izpitvan_pokazatelDAO.setBasikValueIzpitvan_pokazatel();
		ResultsDAO.setBasicValueResults();
	}

	public static String alignExpon(double basic, double foll) {
		NumberFormat frm = new DecimalFormat("0.00E00");
		NumberFormat frm_foll = new DecimalFormat("0.00");
		String str_bas = frm.format(basic);
		double expon = Double.valueOf("1.0" + str_bas.substring(str_bas.indexOf("E")));
		foll = foll / expon;
		String str_foll = frm_foll.format(foll) + str_bas.substring(str_bas.indexOf("E"));
		if (!str_foll.contains("E-")) { //don't blast a negative sign
			str_foll = str_foll.replace("E", "E+");
	    }
		str_foll = str_foll.replace(",", ".");
		return str_foll;
	}
	
	private static String formatter(double number){
	    DecimalFormat formatter = new DecimalFormat("0.00E00");
	    String fnumber = formatter.format(number);
	    if (!fnumber.contains("E-")) { //don't blast a negative sign
	        fnumber = fnumber.replace("E", "E+");
	    }
	    fnumber = fnumber.replace(",", ".");
	    return fnumber;
	}
}
