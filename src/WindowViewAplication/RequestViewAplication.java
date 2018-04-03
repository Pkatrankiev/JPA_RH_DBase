package WindowViewAplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

import Aplication.Ind_num_docDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.PeriodDAO;
import Aplication.RazmernostiDAO;
import DBase_Class.Ind_num_doc;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Period;
import DBase_Class.Razmernosti;

public class RequestViewAplication {

	public static String[] getStringMassiveLIP() {
		int i = 0;
		List<List_izpitvan_pokazatel> list = List_izpitvan_pokazatelDAO.getInListAllValuePokazatel();
		String[] arr = new String[list.size()];
		for (List_izpitvan_pokazatel e : list) {
			arr[i] = ((List_izpitvan_pokazatel) e).getName_pokazatel();
			i++;
		}
		return arr;
	}
			
	public static String[] getStringMassiveI_N_D() {
		int i = 0;
		List<Ind_num_doc> list = Ind_num_docDAO.getInListAllValueInd_num_doc();
		String[] arr1 = new String[list.size()];
		for (Ind_num_doc e : list) {
			arr1[i] = ((Ind_num_doc) e).getName();
			i++;
		}
		arr1[0] = "";
		return arr1;
	}
	
	public static String[] getStringMassiveIzpitvanProdukt() {
		int i = 0;
		List<Izpitvan_produkt> list = Izpitvan_produktDAO.getInListAllValueIzpitvan_produkt();
		String[] arr2 = new String[list.size()];
		for (Izpitvan_produkt e : list) {
			arr2[i] = ((Izpitvan_produkt) e).getName_zpitvan_produkt();
			i++;
		}
		return arr2;
	}
	
	public static String[] getStringMassiveRazmernost() {
		int i = 0;
		List<Razmernosti> list = RazmernostiDAO.getInListAllValueRazmernosti();
		String[] arr = new String[list.size()];
		for (Razmernosti e : list) {
			arr[i] = ((Razmernosti) e).getName_razmernosti();
			i++;
		}
		return arr;
	}
	
	public static String[] getStringMassiveO_I_R() {
		int i = 0;
		 List<Obekt_na_izpitvane_request> list = Obekt_na_izpitvane_requestDAO.getInListAllValueObekt_na_izpitvane();
		String[] arr = new String[list.size()];
		for (Obekt_na_izpitvane_request e : list) {
			arr[i] = ((Obekt_na_izpitvane_request) e).getName_obekt_na_izpitvane();
			i++;
		}
		return arr;
	}
	
	public static String[] getStringMassivePeriod() {
		List<Period> list = PeriodDAO.getInListAllValuePeriod();
		String[] arr = new String[list.size()+1];
		arr[0] = "";
		int i = 1;
		for (Period e : list) {
			arr[i] = ((Period) e).getValue();
			i++;
		}
		return arr;
	}

	public static String DateNaw(Boolean whiteTime){
		String dateNaw = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if(whiteTime) sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		
		dateNaw  = sdf.format(Calendar.getInstance().getTime());
		
		return dateNaw;
	}


	public static MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }


	
	
}
