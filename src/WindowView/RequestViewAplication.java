package WindowView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.swing.text.MaskFormatter;
import Aplication.GlobalVariable;
import Aplication.Ind_num_docDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.OtclonenieDAO;
import Aplication.PeriodDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import Aplication.ZabelejkiDAO;
import CreateWordDocProtocol.Generate_Map_For_Request_Word_Document;
import DBase_Class.Ind_num_doc;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Otclonenie;
import DBase_Class.Period;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Sample;
import DBase_Class.Zabelejki;
import WindowViewAplication.DocxMainpulator;

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

	public static String getIND_DescriptByName(String name) {
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
		String str = "";
		List<List_izpitvan_pokazatel> list = List_izpitvan_pokazatelDAO.getInListAllValuePokazatel();
		ArrayList<String> arr2 = new ArrayList<String>();
		for (List_izpitvan_pokazatel e : list) {
			str = e.getName_pokazatel();
			arr2.add(str);

		}

		return arr2;
	}

	public static String[] setMasiveFromList(List<String> list) {
		String[] str = new String[list.size()];
		int i = 0;
		for (String e : list) {
			str[i] = e;
			i++;
		}

		return str;
	}

	

	public static String[][] getMasiveSampleFromReques(Request request) {
		int countSample = request.getCounts_samples();
		String[][] volSampleView = new String[countSample][6];
		List<Sample> listSample = SampleDAO.getListSampleFromColumnByVolume("request", request);
		int i = 0;
		for (Sample sample : listSample) {
			volSampleView[i][0] = sample.getSample_code();
			volSampleView[i][1] = sample.getObekt_na_izpitvane().getName_obekt_na_izpitvane();
			volSampleView[i][2] = sample.getDescription_sample();
			volSampleView[i][3] = sample.getDate_time_reference();
			String period = "";
			if (sample.getPeriod() != null)
				period = sample.getPeriod().getValue();
			volSampleView[i][4] = period;

			volSampleView[i][5] = sample.getGodina_period() + "";
			i++;
		}
		return volSampleView;
	}



	public static String get_String_Ind_Num_Doc_From_Request(Request request) {
		String str_I_N_D = "";
		if (request.getInd_num_doc() != null) {
			str_I_N_D = request.getInd_num_doc().getName();
		}
		return str_I_N_D;
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

	public static ArrayList<String> getStringOtclon() {
		List<Otclonenie> list = OtclonenieDAO.getInListAllValueOtclon();
		ArrayList<String> masive_Otclon = new ArrayList<String>();
		for (Otclonenie e : list) {
			masive_Otclon.add(e.getName_otclon());
		}
		return masive_Otclon;
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
			String strr = ((Obekt_na_izpitvane_sample) e).getName_obekt_na_izpitvane();
			arr.add(strr);

		}

		return arr;
	}

	public static String getStringZabelejkiFormRequest(Request request) {
		String zabel_str = "";
		if (request.getZabelejki() != null) {
			zabel_str = request.getZabelejki().getName_zabelejki();
		}

		return zabel_str;
	}

	public static ArrayList<String> getStringZabelejki() {
		List<Zabelejki> list = ZabelejkiDAO.getInListAllValueZabelejki();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("");

		for (Zabelejki e : list) {
			arr.add(e.getName_zabelejki());

		}
		return arr;
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

	// Sample
	public static String writeSampleDescript(String[][] str1) {

		int[] numPoz = { 8, 20, 50, 17, 10, 6 };
		String someLine = "  Код  " + " Обект на изпитване " + "                Описание на пробата               "
				+ " Референтна дата " + "  Периодичност  " + "\n";
		String str_som = "";
		String[] str_desc = new String[10];
		int k = 0;
		for (int i = 0; i < str1.length; i++) {

			for (int j = 0; j < str1[0].length; j++) {
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
		someLine = someLine.substring(0, cout_str - 1);
		return someLine;
	}

	public static List<Sample> get_List_SamplelFromList_Sample(Request request, List<Sample> listAllSample) {
		List<Sample> list_Sample = new ArrayList<Sample>();
		for (Sample sample : listAllSample) {
			if (sample.getRequest().getRecuest_code().equals(request.getRecuest_code())) {
				list_Sample.add(sample);
			}
		}
		return list_Sample;
	}

	public static String[][] getMasiveSampleFromListSampleFromRequest(Request request, List<Sample> listAllSample) {
		int countSample = request.getCounts_samples();
		String[][] volSampleView = new String[countSample][6];
		int i = 0;
		List<Sample> list_Sample_From_Request = get_List_SamplelFromList_Sample(request, listAllSample);
		for (Sample sample : list_Sample_From_Request) {
			volSampleView[i][0] = sample.getSample_code();
			volSampleView[i][1] = sample.getObekt_na_izpitvane().getName_obekt_na_izpitvane();
			volSampleView[i][2] = sample.getDescription_sample();
			volSampleView[i][3] = sample.getDate_time_reference();
			String period = "";
			if (sample.getPeriod() != null)
				period = sample.getPeriod().getValue();
			volSampleView[i][4] = period;

			volSampleView[i][5] = sample.getGodina_period() + "";
			i++;
		}
		return volSampleView;
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



	// List_Izpitvan_Pokazatel
	
	


		
	public static Boolean checkMaxVolume(String code, int minVolume, int maxVolume) {
		Boolean underMaximum = true;
		try {
			if (Integer.parseInt(code) >= minVolume && Integer.parseInt(code) <= maxVolume)
				underMaximum = false;
		} catch (NumberFormatException e) {

		}
		return underMaximum;
	}
}
