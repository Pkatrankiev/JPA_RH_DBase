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
		

//		SampleDAO.getInListAllValueSample();
		
//		SetBasikValueInDataBase();
	
		String fileName = "c:\\Users\\Acer\\Desktop\\3344.doc";
//		ReaderWordDoc.readMyDocument(fileName);
		SetDBfromWordDoc.setVolume(fileName);
//		StartMainWindow();
		
//		System.out.println(Izpitvan_produktDAO.getValueIzpitvan_produktByName("Течни изхвърляния8").getName_zpitvan_produkt());
		
	}

	private static void StartMainWindow() {
		MainWindows win = new MainWindows();
		win.Window();
	}

	private static void SetBasikValueInDataBase() {
		External_applicantDAO.setBasikValueExternal_applicant();
		Ind_num_docDAO.setBasikValueInd_num_doc();
		Izpitvan_pokazatelDAO.setBasikValuePokazatel();
		Izpitvan_produktDAO.setBasikValueIzpitvan_produkt();
		List_MetodyDAO.setBasikValueMetody();
		NuclideDAO.setBasicValueNuclide();
		Obekt_na_izpitvaneDAO.setBasicValueObekt_na_izpitvane();
		RazmernostiDAO.setBasicValueRazmernosti();
		PostDAO.setBasikValuePost();
		UsersDAO.setBasicValueUsers();
		ZabelejkiDAO.setBasicValueZabelejki();
		Internal_applicantDAO.setBasikValueInternal_applicant();
		RequestDAO.setBasicValueRequest();
		SampleDAO.setBasicValueSample();
		MetodyDAO.setBasicValueMetody();
		ResultsDAO.setBasicValueResults();
	}
}
	
	

