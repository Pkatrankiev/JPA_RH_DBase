package Aplication;

import java.math.BigInteger;
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
		

	
//		SetBasikValueInDataBase();
	
		String fileName = "c:\\Users\\ALPHA\\Desktop\\3344.doc";
		SetDBfromWordDoc.setVolume(fileName);
		
//		ReaderWordDoc.readMyDocument(fileName);

//		StartMainWindow();
		
	
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
}
	
	

