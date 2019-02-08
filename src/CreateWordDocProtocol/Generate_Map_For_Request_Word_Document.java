package CreateWordDocProtocol;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import Aplication.TSI_DAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import WindowView.DatePicker;
import WindowView.RequestViewAplication;
import WindowView.RequestViewFunction;

public class Generate_Map_For_Request_Word_Document {

	public static Map<String, String> GenerateMapForRequestWordDocument(Request request, String list_izpitvan_pokazatel,
			String[][] sample_description, String date_time_reference) {
		List<String> date = new ArrayList<String>();
		
		List<Sample> list_Sample = SampleDAO.getListSampleFromColumnByVolume("request", request);
		for (Sample sample : list_Sample) {
		List<Results> list_Results = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
		for (Results result : list_Results) {
			try {
				if(result.getDate_chim_oper().length()>0)
				date.add(DatePicker.formatToTabDate(result.getDate_chim_oper(),false));
				if(result.getDate_measur().length()>0)
				date.add(DatePicker.formatToTabDate(result.getDate_measur(), false));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		}
		String maxDate = Collections.max(date);
		String minDate = Collections.min(date);
		try {
			maxDate = DatePicker.reformatFromTabDate(maxDate,false);
			minDate = DatePicker.reformatFromTabDate(minDate,false);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		Map<String, String> substitutionData = new HashMap<String, String>();

		substitutionData.put("$$request_code$$", request.getRecuest_code());

		substitutionData.put("$$request_date$$", RequestViewFunction.DateNaw(false));

		String str = "";
		if (request.getInd_num_doc() != null)
			str = request.getInd_num_doc().getName();

		substitutionData.put("$$ind_num_doc$$", str);

		substitutionData.put("$$izpitvan_produkt$$", request.getIzpitvan_produkt().getName_zpitvan_produkt());

		String[] obekt_na_izpitvane = generate_obekt_na_izpitvane_Masive_String(request);

		substitutionData.put("$$obekt_na_izpitvane_1$$", obekt_na_izpitvane[0]);
		substitutionData.put("$$obekt_na_izpitvane_2$$", obekt_na_izpitvane[1]);

		substitutionData.put("$$obekt_na_izpitvane_protokol$$", obekt_na_izpitvane[0] + " " + obekt_na_izpitvane[1]);

		if (request.getExtra_module() != null) {
			if (request.getExtra_module().getInternal_applicant() != null) {
				substitutionData.put("$$zaqvitel$$", "ДП “РАО”"+", "
						+ request.getExtra_module().getInternal_applicant().getInternal_applicant_organization());
			} else {
				substitutionData.put("$$zaqvitel$$",
						request.getExtra_module().getExternal_applicant().getExternal_applicant_name());
			}
		} else {
			substitutionData.put("$$zaqvitel$$", "Държавно предприятие “Радиоактивни отпадъци”");
		}

		String metody = generate_Metody_String(request);
		substitutionData.put("$$metody$$", metody);

		String[] pokazatel_razmernost = generate_pokazatel_razmernost_Masive_String(request, list_izpitvan_pokazatel);

		substitutionData.put("$$pokazatel_razmernost_1$$", pokazatel_razmernost[0]);
		substitutionData.put("$$pokazatel_razmernost_2$$", pokazatel_razmernost[1]);

		String[] count_description_sample_group = generate_count_description_sample_group_Masive_String(request,
				sample_description);

		substitutionData.put("$$counts_sample$$", count_description_sample_group[0]);
		substitutionData.put("$$description_sample_group$$", count_description_sample_group[1]);
		substitutionData.put("$$description_sample_1$$", count_description_sample_group[2]);
		substitutionData.put("$$description_sample_2$$", count_description_sample_group[3]);

		String[] description_sample_group = generate_description_sample_group_protokol(request, sample_description);

		substitutionData.put("$$description_sample_group_protokol$$", description_sample_group[0]);
		substitutionData.put("$$description_sample_protokol$$", description_sample_group[1]);

		substitutionData.put("$$date_time_reception$$", request.getDate_reception());
		substitutionData.put("$$date_execution$$", request.getDate_execution());
		substitutionData.put("$$date_time_request$$", date_time_reference);

		String[] zabelejki = generate_zabelejki_Masive_String(request);

		substitutionData.put("$$zabelejki_1$$", zabelejki[0]);
		substitutionData.put("$$zabelejki_2$$", zabelejki[1]);
		
		substitutionData.put ("$$date_measur$$", minDate + " - " +  maxDate);

		if (request.getUsers() != null) {
			substitutionData.put("$$user$$",
					request.getUsers().getName_users() + " " + request.getUsers().getFamily_users());
		}
		String strrr = TSI_DAO.getValueTSIById(1).getName();
		System.out.println(strrr);
		return substitutionData;

	}
		
	private static String[] generate_zabelejki_Masive_String(Request request) {
		String txt;
		String[] zabelejki = new String[2];
		String str_1 = "";
		String str_2 = "";

		String zabel_str = RequestViewAplication.getStringZabelejkiFormRequest(request);

		int max = 70;
		str_1 = zabel_str;
		txt = zabel_str;
		if (txt.length() >= max) {
			int i = max;
			while (i > 0) {
				if (txt.substring(i - 1, i).equals(" ")) {
					str_1 = txt.substring(0, i);
					str_2 = txt.substring(i, txt.length());
					i = 0;
				}
				i--;
			}
		}

		zabelejki[0] = str_1;
		zabelejki[1] = str_2;
		return zabelejki;
	}

	private static String[] generate_count_description_sample_group_Masive_String(Request request,
			String[][] sample_description) {
		String txt;
		String[] count_description_sample_group = new String[4];
		String str_1 = "";
		String str_2 = "";
		int max;
		int count = request.getCounts_samples();
		String descrip_sam_gr_str = request.getDescription_sample_group().replaceAll("\n", " ");

		Boolean period_fl = false;
		for (int k = 0; k < count; k++) {

			String check_period_str = sample_description[k][4];
			for (int i = 0; i < count; i++) {
				if (!sample_description[i][4].equals(check_period_str))
					period_fl = true;
			}
		}
		descrip_sam_gr_str = count + getWordOFNumber(count) + descrip_sam_gr_str + " на " + sample_description[0][5]
				+ "г.  ";
		String samp_str = "";

		for (int i = 0; i < count; i++) {
			samp_str = samp_str + sample_description[i][0] + " / ";
			samp_str = samp_str + sample_description[i][1] + ", ";
			samp_str = samp_str + sample_description[i][2] + ", ";

			if (period_fl)
				samp_str = samp_str + "за " + sample_description[i][4] + " на " + sample_description[i][5] + "г.";
		}

		max = 45;
		txt = descrip_sam_gr_str + "\n " + samp_str;

		if (txt.length() >= max) {
			int i = max;
			while (i > 0) {
				if (txt.substring(i - 1, i).equals(" ")) {
					str_1 = txt.substring(0, i);
					str_2 = txt.substring(i, txt.length());
					i = 0;
				}
				i--;
			}
		}

		max = 80;
		String counts_sample_str = str_1;
		
		String[] desk_samp_str = new String[3];

		desk_samp_str[0] = str_2;
		
		desk_samp_str[1] = "";
		desk_samp_str[2] = "";
		for (int k = 0; k < 3; k++) {
			if (desk_samp_str[k].length() >= max) {
				int i = max;
				while (i > 0) {

					if (desk_samp_str[k].substring(i - 1, i).equals(" ")) {
						String ss = desk_samp_str[k];
						desk_samp_str[k] = ss.substring(0, i);
						if (k < 2)
							desk_samp_str[k + 1] = ss.substring(i, ss.length());
						i = 0;
					}
					i--;
				}
			}
		}

		count_description_sample_group[0] = counts_sample_str;
		count_description_sample_group[1] = desk_samp_str[0];
		count_description_sample_group[2] = desk_samp_str[1];
		count_description_sample_group[3] = desk_samp_str[2];
		return count_description_sample_group;
	}


	private static String[] generate_description_sample_group_protokol(Request request, String[][] sample_description) {
		String txt;
		String[] count_description_sample_group = new String[2];
		String str_request_code = request.getRecuest_code();
		String str_2 = "";
		int max;
		int count = request.getCounts_samples();
		String descrip_sam_gr_str = request.getDescription_sample_group().replaceAll("\n", " ");

		Boolean period_fl = false;
		for (int k = 0; k < count; k++) {
			String check_period_str = sample_description[k][4];
			for (int i = 0; i < count; i++) {
				if (!sample_description[i][4].equals(check_period_str))
					period_fl = true;
			}
		}
		if (period_fl || !sample_description[0][4].equals("")) {
			descrip_sam_gr_str = descrip_sam_gr_str + " на " + sample_description[0][5] + "г.  ";
		}
		String samp_str = "";

		for (int i = 0; i < count; i++) {
			samp_str = samp_str + str_request_code + "-" + sample_description[i][0] + " / ";
			samp_str = samp_str + sample_description[i][1] ;
			if (sample_description[i][2].length()>0) {
				samp_str = samp_str + ", "+ sample_description[i][2] + "; ";
			}else {
				samp_str = samp_str + "; ";
			}
			if (period_fl)
				samp_str = samp_str + "за " + sample_description[i][4] + " на " + sample_description[i][5] + "г.";
		}

//		max = 45;
//		txt = descrip_sam_gr_str + "\n " + samp_str;
//		if (txt.length() >= max) {
//			int i = max;
//			while (i > 0) {
//				if (txt.substring(i - 1, i).equals(" ")) {
//					str_1 = txt.substring(0, i);
//					str_2 = txt.substring(i, txt.length());
//					i = 0;
//				}
//				i--;
//			}
//		}
//
//		max = 80;
//		String counts_sample_str = str_1;
//		
//		String[] desk_samp_str = new String[3];
//
//		desk_samp_str[0] = str_2;
//		
//		desk_samp_str[1] = "";
//		desk_samp_str[2] = "";
//		for (int k = 0; k < 3; k++) {
//			if (desk_samp_str[k].length() >= max) {
//				int i = max;
//				while (i > 0) {
//
//					if (desk_samp_str[k].substring(i - 1, i).equals(" ")) {
//						String ss = desk_samp_str[k];
//						desk_samp_str[k] = ss.substring(0, i);
//						if (k < 2)
//							desk_samp_str[k + 1] = ss.substring(i, ss.length());
//						i = 0;
//					}
//					i--;
//				}
//			}
//		}

		count_description_sample_group[0] = descrip_sam_gr_str;
		count_description_sample_group[1] = samp_str;

		return count_description_sample_group;
	}

	
	private static String[] generate_pokazatel_razmernost_Masive_String(Request request,
			String list_izpitvan_pokazatel) {
		String[] pokazatel_razmernost = new String[2];
		String str_1 = "";
		String str_2 = "";
		String izpit_pokaz = list_izpitvan_pokazatel.replaceAll("\n", " ");
		izpit_pokaz = izpit_pokaz + " / " + request.getRazmernosti().getName_razmernosti();
		str_1 = izpit_pokaz;
		int max_1 = 50;
		String txt = izpit_pokaz;
		if (txt.length() >= max_1) {
			int i = max_1;
			while (i > 0) {
				if (txt.substring(i - 1, i).equals(" ")) {
					str_1 = txt.substring(0, i);
					str_2 = txt.substring(i, txt.length());
					i = 0;
				}
				i--;
			}
		}
		pokazatel_razmernost[0] = str_1;
		pokazatel_razmernost[1] = str_2;
		return pokazatel_razmernost;
	}

	private static String[] generate_obekt_na_izpitvane_Masive_String(Request request) {
		String[] obekt_na_izpitvane = new String[2];
		String strObect = request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
		String str1 = strObect, str2 = "";
		System.out.println("strObect " + strObect.length());
		int max = 50;
		if (strObect.length() >= max) {
			int i = max;
			while (i > 0) {
				if (strObect.substring(i - 1, i).equals(" ")) {
					str1 = strObect.substring(0, i);
					str2 = strObect.substring(i, strObect.length());
					i = 0;
				}
				i--;
			}
		}
		obekt_na_izpitvane[0] = str1;
		obekt_na_izpitvane[1] = str2;
		return obekt_na_izpitvane;
	}

	private static String generate_Metody_String(Request request) {
		String metody = "";
		for (IzpitvanPokazatel izpitvanPokazayel : RequestViewFunction
				.get_List_Izpitvan_pokazatel_From_Request(request)) {
			metody = metody + izpitvanPokazayel.getMetody().getName_metody() + "\n";
		}

		return metody;
	}

	public static String getWordOFNumber(int num) {
		String str = "";
		String str2 = "";
		if (num > 12) {
			num = num - 10;
			str2 = "надесет";
		}

		switch (num) {
		case 1:
			str = "един";
			break;

		case 2:
			str = "двa";
			break;

		case 3:
			str = "три";
			break;

		case 4:
			str = "четири";
			break;

		case 5:
			str = "пет";
			break;

		case 6:
			str = "шест";
			break;

		case 7:
			str = "седем";
			break;

		case 8:
			str = "осем";
			break;

		case 9:
			str = "девет";
			break;

		case 10:
			str = "десет";
			break;

		case 11:
			str = "единадесет";
			break;

		case 12:
			str = "дванадесет";
			break;
		}
		if (num == 1) {
			return "(" + str + ") брой ";
		} else
			return "(" + str + str2 + ") броя ";
	}

}