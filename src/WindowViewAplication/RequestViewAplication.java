package WindowViewAplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.RazmernostiDAO;
import Aplication.ZabelejkiDAO;
import DBase_Class.Ind_num_doc;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Period;
import DBase_Class.Razmernosti;
import DBase_Class.Zabelejki;

public class RequestViewAplication {


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

	public static String getIND_DescriptByName(String name){
		String descript = Ind_num_docDAO.getValueIByName(name).getContent();
		return descript;
	}
	
	public static String[] getStringMassiveIzpitvanProdukt() {
		int i = 0;
		List<Izpitvan_produkt> list = Izpitvan_produktDAO.getInListAllValueIzpitvan_produkt();
		String[] arr2 = new String[list.size()];
		for (Izpitvan_produkt e : list) {
			arr2[i] = ((Izpitvan_produkt) e).getName_zpitvan_produkt();
			i++;
		}
		arr2[0] = "";
		return arr2;
	}
	
	public static ArrayList<String> getStringListLIP() {
		String str="";
		List<List_izpitvan_pokazatel> list = List_izpitvan_pokazatelDAO.getInListAllValuePokazatel();
		ArrayList<String> arr2 =new ArrayList<String>();
		for (List_izpitvan_pokazatel e : list) {
			str = e.getName_pokazatel();
			arr2.add(str);
			
		}
		
		return arr2;
	}
	
	public static String[] setMasiveFromList(List<String> list){
		String[] str = new String [list.size()];
		int i=0;
		for (String e : list) {
			str[i] = e;
			i++;
		}
		
		return str;
	}
	
	public static String GenerateStringRefDateTime(String[][] masiveSampleValue) {
		String[] masiveRefDateTime = new String[masiveSampleValue.length];
		
		
		for (int i = 0; i < masiveSampleValue.length; i++) {
			masiveRefDateTime[i] = masiveSampleValue[i][3];
		}
		String date_time_reference = masiveRefDateTime[0];
		if (compaRefDateTime(masiveRefDateTime)) {
			date_time_reference ="";
			for (int i = 0; i < masiveRefDateTime.length; i++) {
				date_time_reference = date_time_reference + masiveSampleValue[i][0] + " / " + masiveRefDateTime[i]
						+ "; ";
			}
		} 
		
		return date_time_reference;
		
	}
	
	private static Boolean compaRefDateTime(String[] masiveRefDateTime) {
		int count_Sample = masiveRefDateTime.length;
		Boolean comparedFlag = true;
		for (int i = 0; i < count_Sample; i++) {
			String compared = masiveRefDateTime[i];
			for (int j = i; j < count_Sample; j++) {
				if (!compared.equals(masiveRefDateTime[i])) {
					comparedFlag = false;
					return comparedFlag;
				}
			}
		}
		return comparedFlag;
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

	public static ArrayList<String> getStringMassiveO_I_R() {
	
		List<Obekt_na_izpitvane_request> list = Obekt_na_izpitvane_requestDAO.getInListAllValueObekt_na_izpitvane();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("");
		for (Obekt_na_izpitvane_request e : list) {
			arr.add(e.getName_obekt_na_izpitvane());
		
		}
		
		return arr;
	}

	public static ArrayList<String> getStringMassiveO_I_S() {
		
		List<Obekt_na_izpitvane_sample> list = Obekt_na_izpitvane_sampleDAO
				.getInListAllValueObekt_na_izpitvane_sample();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("");
		for (Obekt_na_izpitvane_sample e : list) {
			String strr =((Obekt_na_izpitvane_sample) e).getName_obekt_na_izpitvane();
			arr.add(strr);
			
		}
		
		return arr;
	}

	public static String[] getStringMassivePeriod() {
		List<Period> list = PeriodDAO.getInListAllValuePeriod();
		String[] arr = new String[list.size() + 1];
		arr[0] = "";
		int i = 1;
		for (Period e : list) {
			arr[i] = ((Period) e).getValue();
			i++;
		}
		return arr;
	}

	public static ArrayList<String> getStringZabelejki() {
		List<Zabelejki> list = ZabelejkiDAO.getInListAllValueZabelejki();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("");
		int i = 1;
		for (Zabelejki e : list) {
			arr.add(e.getName_zabelejki());
			i++;
		}
		return arr;
	}	
	
	public static String DateNaw(Boolean whiteTime) {
		String dateNaw = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		if (whiteTime)
			sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

		dateNaw = sdf.format(Calendar.getInstance().getTime());

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

	public static String writeSampleDescript( String[][] str1) {

		int[] numPoz = { 8, 20, 50, 17, 10, 6 };
		String someLine = "  Код  "+" Обект на изпитване "+"                Описание на пробата               "+" Референтна дата "+
		"  Периодичност  "+"\n";
		String str_som = "";
		String[] str_desc = new String[10];
		int k = 0;
		for (int i = 0; i < str1.length; i++) {

			for (int j = 0; j <str1[0].length; j++) {
				if (j == 2) {
					k = 0;
					str_desc[k] = str1[i][j];
					String ssss = str_desc[k];
					while (ssss.indexOf("\n") > 0) {
						str_desc[k] = ssss.substring(0, ssss.indexOf("\n"));
						ssss = ssss.substring(ssss.indexOf("\n") + 1);
						k++;
						str_desc[k] = ssss;
					}

					str_som = str_desc[0];
				} else
					str_som = str1[i][j];
				someLine = someLine + padString(str_som, numPoz[j]);

			}

			someLine = someLine + "\n";
			if (k > 0) {
				for (int l = 1; l <= k; l++) {
					someLine = someLine + "                           " + padString(str_desc[l], 50) + "\n";
				}
			}
		}
		int cout_str = someLine.length();
		someLine = someLine.substring(0, cout_str-1);
		return someLine;
	}

	private static String padString(String str, int n) {
		if (str.length() <= n) {
			for (int j = str.length(); j < n; j++) {
				str += " ";
			} // end for
		} else {
			str = str.substring(0, n - 1) + " ";
		}
		return str;
	}

	
	public static String checkFormatString(String code){
		
		String newCode = code;
		int k;
		System.out.println(code);
		for (int i = 0; i < code.length(); i++) {
			try {
			k=Integer.parseInt(code.substring(i, i+1));
			
			}catch (NumberFormatException e) {
				newCode=code.replace(code.substring(i, i+1),"");
			}
		}
	
		return newCode;
	}
	
	public static Boolean checkMaxVolume(String code, int minVolume, int maxVolume){
		Boolean underMaximum =true;
		try {
		if(Integer.parseInt(code)>=minVolume && Integer.parseInt(code)<=maxVolume) underMaximum =false;
		}catch (NumberFormatException e) {
			
		}
		return underMaximum;
	}
}
