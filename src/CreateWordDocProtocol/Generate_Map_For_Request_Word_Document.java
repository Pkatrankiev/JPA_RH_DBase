package CreateWordDocProtocol;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.Metody;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import GlobalVariable.GlobalPathForDocFile;
import OldClases.Table_RequestToObektNaIzp;
import WindowView.DatePicker;
import WindowView.RequestViewAplication;
import WindowView.RequestViewFunction;


public class Generate_Map_For_Request_Word_Document {
	
	private static String key_request_code = "$$request_code$$";
	private static String key_ind_num_doc = "$$ind_num_doc$$";
	private static String key_request_date  = "$$request_date$$";
	private static String key_protokol_date  = "$$protokol_date$$";
	private static String key_izpitvan_produkt = "$$izpitvan_produkt$$";
	private static String key_obekt_na_izpitvane_1 = "$$obekt_na_izpitvane_1$$";
	private static String key_obekt_na_izpitvane_protokol = "$$obekt_na_izpitvane_protokol$$";
	private static String key_zaqvitel = "$$zaqvitel$$";
	private static String key_metody = "$$metody$$";
	private static String key_pokazatel_razmernost_1 = "$$pokazatel_razmernost_1$$";
	private static String key_counts_sample = "$$counts_sample$$";
	private static String key_description_sample_group = "$$description_sample_group$$";
	private static String key_description_sample_group_protokol = "$$description_sample_group_protokol$$";
	private static String key_description_sample_protokol = "$$description_sample_protokol$$";
	private static String key_date_time_reception = "$$date_time_reception$$";
	private static String key_date_execution = "$$date_execution$$";
	private static String key_date_time_request = "$$date_time_request$$";
	private static String key_request_zab = "$$req_zab$$";
	private static String strKeyDopalneniaZabel = "$$dopalnenia$$";
	private static String key_date_measur = "$$date_measur$$";
	private static String key_user = "$$user$$";
//	private static String 
//	private static String 
	

	public static Map<String, String> GenerateMapForRequestWordDocument(Request request, String list_izpitvan_pokazatel,
			String[][] sample_description, String date_time_reference) {
		List<String> date = new ArrayList<String>();

		List<Sample> list_Sample = SampleDAO.getListSampleFromColumnByVolume("request", request);
		for (Sample sample : list_Sample) {
			List<Results> list_Results = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
			for (Results result : list_Results) {
				if (result.getDate_chim_oper().length() > 0)
						date.add(DatePicker.formatToTabDate(result.getDate_chim_oper(), false));
					if (result.getDate_measur().length() > 0)
						date.add(DatePicker.formatToTabDate(result.getDate_measur(), false));
				

			}
		}
		String maxDate = "", minDate = "";
		if (date.size() > 0) {
			maxDate = Collections.max(date);
			minDate = Collections.min(date);
			
				maxDate = DatePicker.reformatFromTabDate(maxDate, false);
				minDate = DatePicker.reformatFromTabDate(minDate, false);

			
		}

		Map<String, String> substitutionData = new HashMap<String, String>();

		substitutionData.put(key_request_code, request.getRecuest_code());
		substitutionData.put(key_protokol_date, RequestViewFunction.DateNaw(false));
		substitutionData.put(key_request_date, request.getDate_request());

		String str = "";
		if (request.getInd_num_doc() != null)
			str = request.getInd_num_doc().getName();

		substitutionData.put(key_ind_num_doc, str);

		substitutionData.put(key_izpitvan_produkt, request.getIzpitvan_produkt().getName_zpitvan_produkt());

		String obekt_na_izpitvane = generate_obekt_na_izpitvane_Masive_String(request);

		substitutionData.put(key_obekt_na_izpitvane_1, obekt_na_izpitvane);

		substitutionData.put(key_obekt_na_izpitvane_protokol, obekt_na_izpitvane);

		if (request.getExtra_module() != null) {
			if (request.getExtra_module().getInternal_applicant() != null) {
				substitutionData.put(key_zaqvitel, "ДП “РАО”" + ", "
						+ request.getExtra_module().getInternal_applicant().getInternal_applicant_organization());
			} else {
				if (request.getExtra_module().getExternal_applicant() != null){
				substitutionData.put(key_zaqvitel,
						request.getExtra_module().getExternal_applicant().getExternal_applicant_name());
				}else{
					substitutionData.put(key_zaqvitel, "Държавно предприятие “Радиоактивни отпадъци”");
				}
			}
		} else {
			substitutionData.put(key_zaqvitel, "Държавно предприятие “Радиоактивни отпадъци”");
		}

		String str_metody = generate_Metody_String(request);
		substitutionData.put(key_metody, str_metody);

		String pokazatel_razmernost = generate_pokazatel_razmernost_Masive_String(request, list_izpitvan_pokazatel);

		substitutionData.put(key_pokazatel_razmernost_1, pokazatel_razmernost);

		// Описание на пробите за изпитване
		String[] count_description_sample_group = generate_count_description_sample_group_Masive_String(request,
				sample_description);

		substitutionData.put(key_counts_sample, count_description_sample_group[0]);
		substitutionData.put(key_description_sample_group, count_description_sample_group[1]);

		String[] description_sample_group = generate_description_sample_group_protokol(request, sample_description);

		substitutionData.put(key_description_sample_group_protokol, description_sample_group[0]);
		substitutionData.put(key_description_sample_protokol, description_sample_group[1]);

		substitutionData.put(key_date_time_reception, request.getDate_reception());
		substitutionData.put(key_date_execution, request.getDate_execution());
		try {
			substitutionData.put(key_date_time_request, DatePicker.formatToProtokolDate(date_time_reference));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}

		String request_zabelejki = RequestViewAplication.getStringZabelejkiForRequest(request);
		substitutionData.put(key_request_zab, request_zabelejki);
				
		if (request_zabelejki.equals("")) {
			request_zabelejki = GlobalPathForDocFile.getTextDopalneniqIzklucheniq();
		}else{
			request_zabelejki =request.getZabelejki().getProtokol_name();
			if (request_zabelejki.equals("")) {
				request_zabelejki = GlobalPathForDocFile.getTextDopalneniqIzklucheniq();
			}
		}
		substitutionData.put(strKeyDopalneniaZabel, request_zabelejki);
		
	
		if(minDate.equals(maxDate)){
			substitutionData.put(key_date_measur, minDate);
		}else{			
		substitutionData.put(key_date_measur, minDate + " ÷ " + maxDate);
		}

		if (request.getUsers() != null) {
			substitutionData.put(key_user,
					request.getUsers().getName_users() + " " + request.getUsers().getFamily_users());
		}
		
		return substitutionData;

	}
	

	private static String[] generate_count_description_sample_group_Masive_String(Request request,
			String[][] sample_description) {

		String[] count_description_sample_group = new String[2];

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
		descrip_sam_gr_str = count + getWordOFNumber(count) + descrip_sam_gr_str;

		String samp_str = "";
		for (int i = 0; i < count; i++) {
			samp_str = samp_str + sample_description[i][0] + " / ";
			samp_str = samp_str + sample_description[i][1];
			if (sample_description[i][2].length() > 0) {
				samp_str = samp_str + ", " + sample_description[i][2] + "; ";
			} else {
				samp_str = samp_str + "; ";
			}
		
			if (period_fl)
				samp_str = samp_str + "за " + sample_description[i][4];
		}

		count_description_sample_group[0] = descrip_sam_gr_str;
		count_description_sample_group[1] = samp_str.substring(0, samp_str.length()-2);

		return count_description_sample_group;
	}

	private static String[] generate_description_sample_group_protokol(Request request, String[][] sample_description) {
		
		String[] count_description_sample_group = new String[2];

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

		String samp_str = "";

		for (int i = 0; i < count; i++) {
			samp_str = samp_str + sample_description[i][0] + " / ";
			samp_str = samp_str + sample_description[i][1];
			if (sample_description[i][2].length() > 0) {
				samp_str = samp_str + ", " + sample_description[i][2] + "; ";
			} else {
				samp_str = samp_str + "; ";
			}
			if (period_fl)
				samp_str = samp_str + "за " + sample_description[i][4];
		}

		count_description_sample_group[0] = descrip_sam_gr_str;
		count_description_sample_group[1] = samp_str.substring(0, samp_str.length()-2);

		return count_description_sample_group;
	}

	private static String generate_pokazatel_razmernost_Masive_String(Request request, String list_izpitvan_pokazatel) {

		String izpit_pokaz = list_izpitvan_pokazatel.replaceAll("\n", " ");
		izpit_pokaz = izpit_pokaz + " / " + RequestViewFunction.ConvertNumberInStrToSuperscript(request.getRazmernosti().getName_razmernosti());

		return izpit_pokaz;
	}

	private static String generate_obekt_na_izpitvane_Masive_String(Request request) {
		
		return Table_RequestToObektNaIzp.createStringListObektNaIzp(
				Table_RequestToObektNaIzp.getListStringOfRequest_To_ObektNaIzpitvaneRequest(request), false);
//		return request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();

	}

	private static String generate_Metody_String(Request request) {
		String metody = "";
			for (Metody metod :  FunctionForGenerateWordDocFile.createCleanFromDuplicateListMetody(request)) {
			if (metod != null) {
				metody = metody + metod.getName_metody() + ", "
						+ metod.getCode_metody() + "\n";
			}
		}
		return metody;
	}

	public static String getWordOFNumber(int num) {
		String str = "";
		String str2 = "";
		Boolean fl = false;
		if (num > 12&& num < 20) {
			num = num - 10;
			str2 = "надесет";
		}
		if (num >= 20) {
			str2 = "двадесет";
			num = num - 20;
			if(num>0){
				str2 = str2 + " и ";
				}
			fl=true;
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
		if(fl){
			return " (" + str2 + str + ") брой ";	
		}
		if (num == 1) {
			return " (" + str + ") брой ";
		} else{
			return " (" + str + str2 + ") броя ";
		}
		
	}

}