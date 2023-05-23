package CreateWordDocProtocol;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Aplication.NaredbiDAO;
import Aplication.NormiDAO;
import Aplication.ResultsDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Metody;
import DBase_Class.Naredbi;
import DBase_Class.Normi;
import DBase_Class.Nuclide;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.RequestViewFunction;

public class FunctionForGenerateWordDocFile {
	private static List<Results> listDokladMDA = new ArrayList<Results>();
	private static Naredbi naredba = null;

	public static int countRowInFirstPege(int count_Result_In_Protokol) {
		int max_tableRow = 100;
		if (count_Result_In_Protokol > 7 && count_Result_In_Protokol <= 10) {
			max_tableRow = count_Result_In_Protokol - 2;
		} else {
			if (count_Result_In_Protokol > 10) {
				max_tableRow = 9;
			}
		}
		return max_tableRow;
	}

	public static int get_count_Result_In_Protokol(List<Sample> smple_list) {
		int count_Result_In_Protokol = 0;
		for (Sample sample : smple_list) {
			for (Results result : ResultsDAO.getListResultsFromColumnByVolume("sample", sample)) {
				if (result.getInProtokol()) {
					count_Result_In_Protokol++;
				}
			}

		}
		return count_Result_In_Protokol;
	}

	static Map<String, String> generateResultsMap(Sample sample, Results result, String[] masive_column_table_result) {
		List<Naredbi> list_Naredbi = NaredbiDAO.getInListAllValueNaredbi();
		Map<String, String> substitutionData = new HashMap<String, String>();

		// "$$sample_code$$"
		substitutionData.put(masive_column_table_result[0],
				sample.getRequest().getRecuest_code() + "-" + sample.getSample_code());

		// "$$sample_metod$$"
		String code_samp_metody = result.getMetody().getCode_metody();
		if (code_samp_metody.indexOf("/") > 0) {
			code_samp_metody.substring(0, code_samp_metody.indexOf("/"));
		}
		substitutionData.put(masive_column_table_result[1], code_samp_metody);
		// substitutionData.put(masive_column_table_result[1],
		// code_samp_metody.substring(0, code_samp_metody.indexOf("/"));

		// "$$nuclide$$"
		String pokaz = result.getPokazatel().getName_pokazatel();
		if (pokaz.indexOf("гама") >= 0 || pokaz.indexOf("алфа") >= 0 || pokaz.indexOf("фракция") >= 0) {
			String[] nuclide = new String[] { "", "" };
			nuclide = getNumberFromNuclide(result.getNuclide().getSymbol_nuclide());
			substitutionData.put("$$s_txt$$", "");
			substitutionData.put("$$n_nucl$$", nuclide[0]);
			substitutionData.put("$$c_nucl$$", nuclide[1]);

		} else {

			String[] nuclide = new String[] { "", "" };
			nuclide = getNumberFromNuclide(pokaz.replaceAll("Съдържание на", "").trim());
			substitutionData.put("$$s_txt$$", "Съдържание на");
			substitutionData.put("$$n_nucl$$", nuclide[0]);
			substitutionData.put("$$c_nucl$$", nuclide[1]);

		}
		// "$$razmernost$$"
		String razmernost = result.getRazmernosti().getName_razmernosti();
		razmernost = razmernost.replaceAll("2", "²").replaceAll("3", "³");
		substitutionData.put(masive_column_table_result[3], razmernost);

		String string_zab = sample.getRequest().getZabelejki().getName_zabelejki();

		String string_doplIzis = getStringDopIzisk(sample);

		String string_doplDogov = getStringDopDogov(sample);

		// "$$value$$"
		String str_VAlue = createValueForMap(result, string_zab, string_doplIzis);
		substitutionData.put(masive_column_table_result[4], str_VAlue);

		// "$$norma$$"
		naredba = getNaredba(list_Naredbi, string_zab, string_doplDogov);

		String norm = createNormaforMap(result, naredba);
		substitutionData.put(masive_column_table_result[5], norm);

		return substitutionData;
	}

	static String getStringDopDogov(Sample sample) {
		String string_doplDogov = "";
		if (sample.getRequest().getExtra_module() != null
				&& sample.getRequest().getExtra_module().getAdditional_requirements() != null) {
			string_doplDogov = sample.getRequest().getExtra_module().getAdditional_requirements();
		}
		return string_doplDogov;
	}

	static String getStringDopIzisk(Sample sample) {
		String string_doplIzis = "";
		if (sample.getRequest().getExtra_module() != null
				&& sample.getRequest().getExtra_module().getDoplIzisk() != null) {
			string_doplIzis = sample.getRequest().getExtra_module().getDoplIzisk().getName_dopIzis();
		}
		return string_doplIzis;
	}

	static Boolean isZabContain10pecent(String string_zab) {
		if (string_zab.indexOf("10 %") >= 0)
			return true;
		return false;
	}

	private static String createValueForMap(Results result, String string_zab, String string_doplIzis) {
		String str_VAlue = "";
		String ProtokolResultNeopred = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("ProtokolResultNeopred");

		if (result.getValue_result() != 0) {
			if(ProtokolResultNeopred.equals("1")) {
			str_VAlue = StoinostAndNeopredelenost(result.getValue_result(), result.getUncertainty());
			}else {
			str_VAlue = formatter(result.getValue_result()) + " ± "
					+ alignExpon(result.getValue_result(), result.getUncertainty());
			}
			if (isZabContain10pecent(string_zab)) {
				str_VAlue = str_VAlue + "*";
			}

			if (string_doplIzis.indexOf("Да се докладва МДА") >= 0) {

				listDokladMDA.add(result);
			}
		} else {
			str_VAlue = "< " + formatter(result.getMda());
		}
		return str_VAlue;
	}

	private static String createNormaforMap(Results result, Naredbi naredba) {
		String norm = "-";
		if (naredba != null) {
			List<Normi> listNormi = NormiDAO.getList_NormiByNaredbi(naredba);
			for (Normi normi : listNormi) {
				System.out.println("result.getNuclide   " + result.getNuclide().getSymbol_nuclide() + "nuclide "
						+ normi.getNuclide().getSymbol_nuclide());
				if (result.getNuclide().getSymbol_nuclide().equals(normi.getNuclide().getSymbol_nuclide())) {
					norm = formatter(normi.getValue_norma()) + "*";
					break;
				}
			}
		}
		return norm;
	}

	private static Naredbi getNaredba(List<Naredbi> list_Naredbi, String string_zab, String string_doplDogov) {

		int id_naredba = 0;
		if (string_doplDogov.indexOf("лимитни") >= 0) {
			for (Naredbi naredbi : list_Naredbi) {
				if (string_doplDogov.equals(naredbi.getName_request())) {
					id_naredba = naredbi.getId_naredbi();

				}
			}
		}
		if (string_zab.indexOf("лимитни") >= 0) {
			for (Naredbi naredbi : list_Naredbi) {
				if (string_zab.equals(naredbi.getName_request())) {
					id_naredba = naredbi.getId_naredbi();
					break;
				}
			}
		}
		if (id_naredba > 0) {
			return NaredbiDAO.getValueNaredbiById(id_naredba);
		}
		return null;
	}

	static List<Metody> createCleanFromDuplicateListMetody(Request request) {
		List<IzpitvanPokazatel> listIzpPok = RequestViewFunction.get_List_Izpitvan_pokazatel_From_Request(request);

		List<Metody> listIdMetody = new ArrayList<Metody>();
		for (IzpitvanPokazatel izpitvanPokazayel : listIzpPok) {
			listIdMetody.add(izpitvanPokazayel.getMetody());
		}

		List<Metody> newlistIdMetody = FunctionForGenerateWordDocFile.ClearDuplicateObjectFromListMetody(listIdMetody);
		return newlistIdMetody;
	}

	static Map<String, String> generateMapRowTable(Sample sample, IzpitvanPokazatel pokazatel,
			String[] masive_key_table_row, Boolean isDiferent_Ob_Izp_Req) {

		Map<String, String> substitutionData = new HashMap<String, String>();

		// "$$sample_code$$"
		substitutionData.put(masive_key_table_row[0],
				sample.getRequest().getRecuest_code() + "-" + sample.getSample_code());

		// "$$ob_izp_sam$$"
		String str_ob_izp_sam = generate_str_Ob_izp_samp(sample, isDiferent_Ob_Izp_Req);
		substitutionData.put(masive_key_table_row[1], str_ob_izp_sam);

		// "$$pokazat$$"
		String strMinipokazatel = generateSimpliStrOnPokazatel(pokazatel.getPokazatel().getName_pokazatel());
		String[] str = new String[] { "", "" };
		str = getNumberFromNuclide(strMinipokazatel);
		substitutionData.put("$$num$$", str[0]);
		substitutionData.put("$$pokazat$$", str[1]);

		// "$$date_execution$$"
		substitutionData.put(masive_key_table_row[3], pokazatel.getRequest().getDate_execution());

		return substitutionData;
	}

	public static String generate_str_Ob_izp_samp(Sample sample, Boolean isDiferent_Ob_Izp_Req) {
		String obIzpRequest = sample.getRequest_to_obekt_na_izpitvane_request().getObektNaIzp()
				.getName_obekt_na_izpitvane();
		String obIzpSample = sample.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane();
		String str_ob_izp_req_simpl = sample.getRequest_to_obekt_na_izpitvane_request().getObektNaIzp()
				.getSimple_Name();
		String str_ob_izp_sam = "";

		if (isDiferent_Ob_Izp_Req) {
			if (str_ob_izp_req_simpl.equals("")) {
				str_ob_izp_sam = obIzpRequest;
			} else {
				str_ob_izp_sam = str_ob_izp_req_simpl;
			}
		}

		if (!obIzpRequest.equals(obIzpSample)) {

			if (!str_ob_izp_sam.equals("")) {
				str_ob_izp_sam += ", ";
			}
			str_ob_izp_sam += obIzpSample;
		}

		if (str_ob_izp_sam.equals("")) {
			if (str_ob_izp_req_simpl.equals("")) {
				str_ob_izp_sam = obIzpRequest;
			} else {
				str_ob_izp_sam = str_ob_izp_req_simpl;
			}
		}

		return str_ob_izp_sam;
	}

	static Map<String, String> generateEmptiMapRowTable(String[] masive_key_table_row) {

		Map<String, String> substitutionData = new HashMap<String, String>();

		// "$$sample_code$$"
		substitutionData.put(masive_key_table_row[0], "");

		// "$$ob_izp_sam$$"
		substitutionData.put(masive_key_table_row[1], "");

		// "$$pokazat$$"

		substitutionData.put("$$num$$", "");
		substitutionData.put("$$pokazat$$", "");

		// "$$date_execution$$"
		substitutionData.put(masive_key_table_row[3], "");

		return substitutionData;
	}

	private static String generateSimpliStrOnPokazatel(String name_pokazatel) {
		name_pokazatel = name_pokazatel.replaceAll("Съдържание на", "").trim();
		name_pokazatel = name_pokazatel.replaceAll("излъчващи", "").trim();
		return name_pokazatel;
	}

	static Map<String, String> generateMapMDA(Results result) {
		Map<String, String> substitutionData = new HashMap<String, String>();
		substitutionData.put("$$sample_code$$",
				result.getSample().getRequest().getRecuest_code() + "-" + result.getSample().getSample_code());

		String[] nuclide = new String[] { "", "" };
		nuclide = getNumberFromNuclide(result.getNuclide().getSymbol_nuclide());
		substitutionData.put("$$s_txt$$", "");
		substitutionData.put("$$n_nucl$$", nuclide[0]);
		substitutionData.put("$$c_nucl$$", nuclide[1]);

		substitutionData.put("$$MDA$$", " " + formatter(result.getMda()));
		substitutionData.put("$$razmernost$$",
				result.getRazmernosti().getName_razmernosti().replaceAll("2", "²").replaceAll("3", "³"));
		return substitutionData;
	}

	static void clearListDokladMDA() {
		listDokladMDA.clear();
	}

	static List<Results> getListDokladMDA() {
		return listDokladMDA;
	}

	static Naredbi getNaredba() {
		return naredba;
	}

	@SuppressWarnings("unused")
	private static String[] setSuperScribeNuclideInText(String text, List<Nuclide> list_Nuclide) {
		String[] str = new String[] { text, "", "", "" };
		String[] masiveStr = text.split(" ");
		Boolean nucl_available = false;

		for (int i = 0; i < masiveStr.length; i++) {
			for (Nuclide nuclide : list_Nuclide) {
				if (nuclide.getSymbol_nuclide().equals(masiveStr[i])) {
					nucl_available = true;
					for (int j = 0; j < i; j++) {
						str[0] = str[0] + masiveStr[j];
					}
					for (int j = i + 1; j < masiveStr.length; j++) {
						str[3] = str[3] + masiveStr[j];
					}

				}
			}
		}
		return str;

	}

	public static String[] getNumberFromNuclide(String symbol_nuclide) {
		String[] str = new String[] { "", "" };

		for (int i = 0; i < symbol_nuclide.length(); i++) {
			char temp = symbol_nuclide.charAt(i);

			if (Character.isDigit(temp) || (temp == '/')) {
				str[0] = str[0] + temp;
			} else {
				str[1] = str[1] + temp;
			}
		}
		return str;
	}

	public static String StoinostAndNeopredelenost(double stoinost, double neopr) {

//		neopr = 0.76699;
//		stoinost = 6.73841;
		String stoinostStr = "";
//		NumberFormat frm = new DecimalFormat("0.00E00");
		DecimalFormat frm_St1 = new DecimalFormat("0.0E00");
		DecimalFormat frm_St2 = new DecimalFormat("0.00E00");
		DecimalFormat frm_St3 = new DecimalFormat("0.000E00");
		DecimalFormat frm_St4 = new DecimalFormat("0.0000E00");

		// namirame expon na neopred
		
//		String exponStoinostStr = "";
		int exponNeopr = getExpon(neopr);
		// dokarvame neopred do wida 00.0
		int IntValue = calculationValueInX_XXFormat(neopr, exponNeopr);
		// otnovo namirame expon na neopred
		int exponNeoprA = getExpon(IntValue);
		// preizchislqvame nanovo expon za da poluchim neopred vuv vida 00
		int newEsponNeop = exponNeopr + (exponNeoprA - 1);
		int newNeopr = calculationValueInX_XXFormat(neopr, newEsponNeop);
		
	System.out.println(exponNeopr + " * " + IntValue + " - " + exponNeoprA + " + " + newEsponNeop+" . "+newNeopr);

		//**********************************************************************
		// namirame expon na stoinost
		int exponStoinost = getExpon(stoinost);
		stoinostStr = formatter(stoinost);
//		String exponStoinostStr1 = stoinostStr.substring(stoinostStr.indexOf("E"));
		
		System.out.println(newEsponNeop+"  "+exponStoinost);
		int sub = exponStoinost - newEsponNeop;
		int k = (int) Math.pow(10, sub+1);
//		String stoinostStr1 = formatter(Math.round(stoinost*k)/k);
		
		double a = stoinost/( Math.pow(10, exponStoinost));
		System.out.println(stoinost+"  "+exponStoinost + "  " + sub + "  " +k+"  "+ a +"  "+stoinostStr);
		switch (sub) {
		case 0:
			stoinostStr = generateStringStoinostAndNeopred(frm_St1, newNeopr,stoinost);
			break;
		case 1:
			stoinostStr = generateStringStoinostAndNeopred(frm_St2, newNeopr, stoinost);
			break;
		case 2:
			stoinostStr = generateStringStoinostAndNeopred(frm_St3, newNeopr, stoinost);
			break;
			
		case 3:
			stoinostStr = generateStringStoinostAndNeopred(frm_St4, newNeopr, stoinost);
			break;

		default:
			stoinostStr = formatter(stoinost) + " ± "
					+ alignExpon(stoinost, neopr);	
			
			break;
		}
	

		
		System.out.println(stoinostStr+" * "+sub);
		
		return stoinostStr;

	}
	
	public static String transformEXPto10(String val){
		String exponStoinostStr = val.substring(val.indexOf("E")+1);
		int exp = Integer.valueOf(exponStoinostStr);
		String stoinostStr = val.substring(0,val.indexOf("E"));
		
		return exp==0?stoinostStr:stoinostStr+"10 "+exp;
		
	}

	private static String generateStringStoinostAndNeopred(DecimalFormat frm_St, int newNeopr, double a) {
		String stoinostStr;
		String exponStoinostStr;
		stoinostStr = formatter(a, frm_St);
		System.out.println("*************"+stoinostStr);
		exponStoinostStr = stoinostStr.substring(stoinostStr.indexOf("E"));
		stoinostStr = stoinostStr.substring(0,stoinostStr.indexOf("E"));
		
		stoinostStr = addNullThenStoinosIs99999(frm_St, a, stoinostStr);
		
		stoinostStr = stoinostStr+" ("+newNeopr+") "+exponStoinostStr;
		return stoinostStr;
	}

	private static String addNullThenStoinosIs99999(DecimalFormat frm_St, double a, String stoinostStr) {
		String stoinos = formatter(a, frm_St);
		int sttr = Integer.parseInt(stoinos.substring(stoinos.indexOf("E")+1, stoinos.length()));
		System.out.println(sttr+"  "+getExpon(a));
		if(sttr!=getExpon(a)) {
			stoinostStr = stoinostStr+"0";
		}
		return stoinostStr;
	}

	private static int calculationValueInX_XXFormat(double value, int exponNeopr) {
		double a = (value * Math.pow(10, exponNeopr * (-1)));
		// zakruglqvame nagore neopred za sluchai da ne stane 000.0
		int IntValue = (int) Math.round(a * 10);
	return IntValue;
	}

	private static int getExpon(double neopr) {
		NumberFormat frm = new DecimalFormat("0.00E00");
		String str = frm.format(neopr);
		int exponNeopr = Integer.valueOf(str.substring(str.indexOf("E") + 1));
		return exponNeopr;
	}

	public static String alignExpon(double basic, double foll) {
		NumberFormat frm = new DecimalFormat("0.00E00");
		NumberFormat frm_foll = new DecimalFormat("0.00");
		NumberFormat frm_follA = new DecimalFormat("0.000");
		String str_bas = frm.format(basic);
		double expon = Double.valueOf("1.0" + str_bas.substring(str_bas.indexOf("E")));
		foll = foll / expon;
		String str_foll = frm_foll.format(foll);

		// zastoto metoda ne zakarglqva ot 0.045 na 0.05
		String str_follA = frm_follA.format(foll);
		if (str_follA.substring(4).equals("5")) {
			str_follA = str_follA.substring(0, 4);
			str_follA = str_follA + "6";
			str_follA = str_follA.replace(",", ".");
			double d = Double.parseDouble(str_follA);
			str_foll = frm_foll.format(d);
		}

		str_foll = str_foll + str_bas.substring(str_bas.indexOf("E"));
		if (!str_foll.contains("E-")) { // don't blast a negative sign
			str_foll = str_foll.replace("E", "E+");
		}
		str_foll = str_foll.replace(",", ".");
		return str_foll;
	}
	
	
	public static String formatter(double number, DecimalFormat formatter) {
		
		String fnumber = formatter.format(number);
		if (!fnumber.contains("E-")) { // don't blast a negative sign
			fnumber = fnumber.replace("E", "E+");
		}
		fnumber = fnumber.replace(",", ".").replace("E", "E");
		return fnumber;
	}

	public static String formatter(double number) {
		DecimalFormat formatter = new DecimalFormat("0.00E00");
		String fnumber = formatter.format(number);
		if (!fnumber.contains("E-")) { // don't blast a negative sign
			fnumber = fnumber.replace("E", "E+");
		}
		fnumber = fnumber.replace(",", ".").replace("E", "E");
		return fnumber;
	}

	static List<Metody> ClearDuplicateObjectFromListMetody(List<Metody> listIdMetody) {
		Set<Metody> newListIdMetody = new LinkedHashSet<>(listIdMetody);
		listIdMetody.clear();
		listIdMetody.addAll(newListIdMetody);
		return listIdMetody;
	}

}
