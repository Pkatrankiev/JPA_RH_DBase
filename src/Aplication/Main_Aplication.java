package Aplication;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JFrame;
import javax.xml.ws.*;

import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Nuclide;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Results;
import WindowView.Login;
import WindowView.MainWindows;
//import WindowView.ReadFile;
import WindowView.ReaderWordDoc;
import WindowView.SetDBfromWordDoc;
import WindowView.TablePrintDemo;
import DBase_Class.Dimension;
import DBase_Class.Izpitvan_pokazatel;

public class Main_Aplication {

	public static void main(String[] args) {

//		 SetBasikValueInDataBase();
		

//		ChangeObjectsInClassIzpitvan_pokazatel();

		
		
		// ReaderWordDoc.readMyDocument(fileName);

		
		 
		 
		 StartMainWindow();
		 
		 
		
//		String FILENAME = "c:\\GENIE2K\\REPFILES\\3532-2.RPT";
//		
//		ReadFile.ReadFile(FILENAME);


	}

	private static void ChangeObjectsInClassIzpitvan_pokazatel() {
		Razmernosti nuclide_old = RazmernostiDAO.getValueRazmernostiById(7);
		Razmernosti nuclide_new = RazmernostiDAO.getValueRazmernostiById(5);
		List<Results> results_list = ResultsDAO.getListResultsFromColumnByVolume("rtazmernosti", nuclide_old);
		for (Results results : results_list) {
			ResultsDAO.setVolumeInColumInResultsById(results.getId_results(), nuclide_new, "rtazmernosti");
					}
	}

	private static void ChangeObjectsInClass() {
		Izpitvan_produkt izpitvan_produkt_old = Izpitvan_produktDAO.getValueIzpitvan_produktById(9);
		Izpitvan_produkt izpitvan_produkt_new = Izpitvan_produktDAO.getValueIzpitvan_produktById(4);
		List<Request> list_request = RequestDAO.getListRequestFromColumnByVolume("izpitvan_produkt",
				izpitvan_produkt_old);
		for (Request request : list_request) {
			RequestDAO.setIzpitvan_produktInRequestById(request.getId_recuest(), izpitvan_produkt_new);
		}
	}

	private static void StartMainWindow() {
		MainWindows win = new MainWindows();
		win.Window();
	}

	private static void SetBasikValueInDataBase() {
//		External_applicantDAO.setBasikValueExternal_applicant();
//		Izpitvan_produktDAO.setBasikValueIzpitvan_produkt();
//		Ind_num_docDAO.setBasikValueInd_num_doc();
//		MetodyDAO.setBasikValueMetody();
//		List_izpitvan_pokazatelDAO.setBasikValuePokazatel();
//		NuclideDAO.setBasicValueNuclide();
//		Obekt_na_izpitvane_requestDAO.setBasicValueObekt_na_izpitvane();
//		Obekt_na_izpitvane_sampleDAO.setBasicValueObekt_na_izpitvane_sample();
//		Izpitvan_pokazatelDAO.setBasikValueIzpitvan_pokazatel();
//		RazmernostiDAO.setBasicValueRazmernosti();
//		DimensionDAO.setBasicValueDimension();
//		PostDAO.setBasikValuePost();
//		UsersDAO.setBasicValueUsers();
//		ZabelejkiDAO.setBasicValueZabelejki();
//		Internal_applicantDAO.setBasikValueInternal_applicant();
//		PeriodDAO.setBasicValuePeriod();
		// RequestDAO.setBasicValueRequest();
		// SampleDAO.setBasicValueSample();
		//
		// ResultsDAO.setBasicValueResults();
		Extra_moduleDAO.setBasicValueRequest();
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