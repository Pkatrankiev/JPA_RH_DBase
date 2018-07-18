package WindowViewAplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.text.MaskFormatter;
import Aplication.Ind_num_docDAO;
import Aplication.Izpitvan_pokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import Aplication.ZabelejkiDAO;
import CreateWordDocProtocol.Generate_Map_For_Request_Word_Document;
import CreateWordDocProtocol.StartGenerateDocTemplate;
import DBase_Class.Ind_num_doc;
import DBase_Class.Izpitvan_pokazatel;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Period;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Sample;
import DBase_Class.Zabelejki;
import WindowView.TablePrintDemo;

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

	public static String GenerateStringRefDateTime(String[][] masiveSampleValue) {
		String[] masiveRefDateTime = new String[masiveSampleValue.length];

		for (int i = 0; i < masiveSampleValue.length; i++) {
			masiveRefDateTime[i] = masiveSampleValue[i][3];
		}
		String date_time_reference = masiveRefDateTime[0];
		if (compaRefDateTime(masiveRefDateTime)) {
			date_time_reference = "";
			for (int i = 0; i < masiveRefDateTime.length; i++) {
				date_time_reference = date_time_reference + masiveSampleValue[i][0] + " / " + masiveRefDateTime[i]
						+ ";  ";
			}
		}

		return date_time_reference;

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

	private static Boolean compaRefDateTime(String[] masiveRefDateTime) {
		int count_Sample = masiveRefDateTime.length;
		Boolean comparedFlag = false;
		for (int i = 0; i < count_Sample; i++) {
			String compared = masiveRefDateTime[i];
			for (int j = i; j < count_Sample; j++) {
				if (!compared.equals(masiveRefDateTime[j])) {
					comparedFlag = true;
					return comparedFlag;
				}
			}
		}
		return comparedFlag;
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

	public static String checkFormatString(String code) {

		String newCode = code;

		System.out.println(code);
		for (int i = 0; i < code.length(); i++) {
			try {
				Integer.parseInt(code.substring(i, i + 1));

			} catch (NumberFormatException e) {
				newCode = code.replace(code.substring(i, i + 1), "");
			}
		}

		return newCode;
	}

	public static void OpenRequestInWordDokTamplate(String requestString) {
		Request request = RequestDAO.getRequestFromColumnByVolume("recuest_code", requestString);
		String list_izpitvan_pokazatel = CreateStringListIzpPokaz(request);

		List<Sample> smple_list = SampleDAO.getListSampleFromColumnByVolume("request", request);
		String[][] smple_vol = new String[smple_list.size()][6];
		int i = 0;
		for (Sample sample : smple_list) {
			smple_vol[i][0] = sample.getSample_code();
			smple_vol[i][1] = sample.getObekt_na_izpitvane().getName_obekt_na_izpitvane();
			smple_vol[i][2] = sample.getDescription_sample();
			smple_vol[i][3] = sample.getDate_time_reference();
			if (sample.getPeriod() == null) {
				smple_vol[i][4] = "";
			} else {
				smple_vol[i][4] = sample.getPeriod().getValue();
			}
			smple_vol[i][5] = sample.getGodina_period() + "";
			i++;
		}

		String date_time_reference = RequestViewAplication.GenerateStringRefDateTime(smple_vol);

		Map<String, String> substitutionData = Generate_Map_For_Request_Word_Document
				.GenerateMapForRequestWordDocument(request, list_izpitvan_pokazatel, smple_vol, date_time_reference);

		// DocxMainpulator.generateAndSendDocx("temp.docx",
		// "Z-" + recuest.getRecuest_code() + "_" +
		// recuest.getDate_request(), substitutionData);

		StartGenerateDocTemplate.GenerateProtokolWordDoc("Protokol.docx", requestString, substitutionData);
	}

	private static String CreateStringListIzpPokaz(Request request) {
		List<Izpitvan_pokazatel> list_izp_pok = RequestViewAplication.get_List_Izpitvan_pokazatel_From_Request(request);
		String list_izpitvan_pokazatel = "";
		for (Izpitvan_pokazatel izpitvan_pokazatel : list_izp_pok) {
			if (!list_izpitvan_pokazatel.equals(""))
				list_izpitvan_pokazatel = list_izpitvan_pokazatel + "\n";
			list_izpitvan_pokazatel = list_izpitvan_pokazatel + izpitvan_pokazatel.getPokazatel().getName_pokazatel();
		}
		return list_izpitvan_pokazatel;
	}

	public static void DrawTableWithRequestTamplate() {
		List<Request> listTamplateRequest = RequestDAO.getListRequestFromColumnByVolume("recuest_code", "templ");
		String[] tableHeader = { "Ид.№ на документа", "Изпитван продукт", "Обект на изпитване", "Показател" };
		String[][] tabletamplateRequest = new String[listTamplateRequest.size()][4];
		int i = 0;
		for (Request tamplateRequest : listTamplateRequest) {
			tabletamplateRequest[i][0] = tamplateRequest.getInd_num_doc().getName();
			tabletamplateRequest[i][1] = tamplateRequest.getIzpitvan_produkt().getName_zpitvan_produkt();
			tabletamplateRequest[i][2] = tamplateRequest.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
			tabletamplateRequest[i][3] = tamplateRequest.getRazmernosti().getName_razmernosti();
			i++;
		}
		TablePrintDemo.createAndShowGUI(tableHeader, tabletamplateRequest);
	}

	public static void DrawTableWithRequestList() {
		List<Request> listRequest = RequestDAO.getInListAllValueRequest();
		String[] tableHeader = { "№ на Заявката", "Дата на заявката", "Изпитван продукт", "Обект на изпитване",
				"Показател", "Размерност", "Брой проби", "Описание на пробите", "Референтна дата", "Срок на изпълнение",
				"Време на приемане", "Приел заявката", "Забележка" };

		String[][] tableRequest = new String[listRequest.size()][13];
		int i = 0;
		for (Request request : listRequest) {
			k
			try {
				Integer.parseInt(request.getRecuest_code());
				tableRequest[i][0] = request.getRecuest_code();
				tableRequest[i][1] = request.getDate_request();
				tableRequest[i][2] = request.getIzpitvan_produkt().getName_zpitvan_produkt();
				tableRequest[i][3] = request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
				tableRequest[i][4] = CreateStringListIzpPokaz(request);
				tableRequest[i][5] = request.getRazmernosti().getName_razmernosti();
				tableRequest[i][6] = request.getCounts_samples() + "";
				tableRequest[i][7] = request.getDescription_sample_group();
				String[][] masiveSample = getMasiveSampleFromReques(request);
				tableRequest[i][8] = RequestViewAplication.GenerateStringRefDateTime(masiveSample);
				tableRequest[i][9] = request.getDate_execution();
				tableRequest[i][10] = request.getDate_time_reception();
				tableRequest[i][11] = request.getUsers().getName_users() + " " + request.getUsers().getFamily_users();
				String zab = "";
				if (request.getZabelejki() != null)
					zab = request.getZabelejki().getName_zabelejki();
				tableRequest[i][12] = zab;
				i++;

			} catch (NumberFormatException e) {
				listRequest.remove(request);
			}
		}

		TablePrintDemo.createAndShowGUI(tableHeader, tableRequest);
	}

	public static List<Izpitvan_pokazatel> get_List_Izpitvan_pokazatel_From_Request(Request request) {
		int count_samp = request.getCounts_samples();
		List<Sample> list_sam = SampleDAO.getListSampleFromColumnByVolume("request", request);
		System.out.println(request.getRecuest_code() + "////////////////////////////////// " + list_sam.size());
		Sample samp = list_sam.get(0);
		List<Izpitvan_pokazatel> list_izp_pok = Izpitvan_pokazatelDAO
				.getListIzpitvan_pokazatelFromColumnByVolume("sample", samp);

		Izpitvan_pokazatel[][] masive_izp_pok = new Izpitvan_pokazatel[count_samp][];

		return list_izp_pok;
	}

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
