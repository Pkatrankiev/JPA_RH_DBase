package CreateWordDocProtocol;

import java.util.HashMap;
import java.util.Map;
import DBase_Class.Request;
import WindowView.RequestViewAplication;

public class Generate_Map_For_Request_Word_Document {

	public static Map<String, String> GenerateMapForRequestWordDocument(Request request, String list_izpitvan_pokazatel,
			String[][] sample_description, String date_time_reference) {
		Map<String, String> substitutionData = new HashMap<String, String>();

		substitutionData.put("$$request_code$$", request.getRecuest_code());

		substitutionData.put("$$request_date$$", request.getDate_request());
//		substitutionData.put("$$request_date$$", RequestViewAplication.DateNaw(true));
		String str = "";
		if (request.getInd_num_doc() != null)
			str = request.getInd_num_doc().getName();

		substitutionData.put("$$ind_num_doc$$", str);
		substitutionData.put("$$izpitvan_produkt$$", request.getIzpitvan_produkt().getName_zpitvan_produkt());

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
		substitutionData.put("$$obekt_na_izpitvane_1$$", str1);
		substitutionData.put("$$obekt_na_izpitvane_2$$", str2);

		str1 = "";
		str2 = "";
		String izpit_pokaz = list_izpitvan_pokazatel.replaceAll("\n", " ");
		izpit_pokaz = izpit_pokaz + " / " + request.getRazmernosti().getName_razmernosti();
		str1 = izpit_pokaz;
		max = 50;
		String txt = izpit_pokaz;
		if (txt.length() >= max) {
			int i = max;
			while (i > 0) {
				if (txt.substring(i - 1, i).equals(" ")) {
					str1 = txt.substring(0, i);
					str2 = txt.substring(i, txt.length());
					i = 0;
				}
				i--;
			}
		}
		substitutionData.put("$$pokazatel_razmernost_1$$", str1);
		substitutionData.put("$$pokazatel_razmernost_2$$", str2);

		str2 = "";
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
		descrip_sam_gr_str = count + getWordOFNumber(count) + "проби, " + descrip_sam_gr_str+ " на "+sample_description[0][5]+ "г.  ";
		String samp_str = "";

		for (int i = 0; i < count; i++) {
			samp_str = samp_str + sample_description[i][0] + " / ";
			samp_str = samp_str + sample_description[i][1] + ", ";
			samp_str = samp_str + sample_description[i][2] + ", ";
	
			if (period_fl)
				samp_str = samp_str + "за " + sample_description[i][4] + " на " + sample_description[i][5] + "г.";
		}

		max = 45;
		txt = descrip_sam_gr_str + " " + samp_str;
		if (txt.length() >= max) {
			int i = max;
			while (i > 0) {
				if (txt.substring(i - 1, i).equals(" ")) {
					str1 = txt.substring(0, i);
					str2 = txt.substring(i, txt.length());
					i = 0;
				}
				i--;
			}
		}

		max = 80;
		String counts_sample_str = str1;
		String[] desk_samp_str = new String[3];

		desk_samp_str[0] = str2;
		
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

		substitutionData.put("$$counts_sample$$", counts_sample_str);
		substitutionData.put("$$description_sample_group$$", desk_samp_str[0]);
		substitutionData.put("$$description_sample_1$$", desk_samp_str[1]);
		substitutionData.put("$$description_sample_2$$", desk_samp_str[2]);
		substitutionData.put("$$date_time_reception$$", request.getDate_reception());
		substitutionData.put("$$date_execution$$", request.getDate_execution());
		substitutionData.put("$$date_time_request$$", date_time_reference);
		if(request.getUsers()!=null){
		substitutionData.put("$$user$$",
				request.getUsers().getName_users() + " " + request.getUsers().getFamily_users());
		}
		
		String zabel_str = RequestViewAplication.getStringZabelejkiFormRequest(request);
		str2 = "";

		max = 70;
		str1 = zabel_str;
		txt = zabel_str;
		if (txt.length() >= max) {
			int i = max;
			while (i > 0) {
				if (txt.substring(i - 1, i).equals(" ")) {
					str1 = txt.substring(0, i);
					str2 = txt.substring(i, txt.length());
					i = 0;
				}
				i--;
			}
		}
		substitutionData.put("$$zabelejki_1$$", str1);
		substitutionData.put("$$zabelejki_2$$", str2);
		return substitutionData;

		
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