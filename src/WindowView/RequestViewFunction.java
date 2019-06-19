package WindowView;

import java.awt.Choice;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Aplication.Ind_num_docDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import CreateWordDocProtocol.GenerateRequestWordDoc;
import CreateWordDocProtocol.Generate_Map_For_Request_Word_Document;
import DBase_Class.Ind_num_doc;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Period;
import DBase_Class.Request;
import DBase_Class.Sample;
import GlobalVariable.GlobalFormatDate;

public class RequestViewFunction {
	private static String FORMAT_DATE = GlobalFormatDate.getFORMAT_DATE();
	private static String FORMAT_DATE_TIME = GlobalFormatDate.getFORMAT_DATE_TIME(); 

	public static void enterRequestCode( JTextField txtField_RequestCode, JLabel lblError, Boolean corectRequestCode ) {
		txtField_RequestCode.setText(checkFormatString(txtField_RequestCode.getText()));
		if (RequestDAO.checkRequestCode(txtField_RequestCode.getText())) {
			txtField_RequestCode.setForeground(Color.red);
			lblError.setText("Заявка с този номер вече съществува");
			corectRequestCode = false;
		} else {

			if (checkMaxVolume(txtField_RequestCode.getText(), 3000, 6000)) {
				txtField_RequestCode.setForeground(Color.red);
				lblError.setText("Некоректен номер");
				corectRequestCode = false;
			} else {
				txtField_RequestCode.setForeground(Color.BLACK);

				txtField_RequestCode.setBorder(new LineBorder(Color.BLACK));
				lblError.setText(" ");
				corectRequestCode = true;
			}
		}
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
	
	public static Boolean checkMaxVolume(String code, int minVolume, int maxVolume) {
		Boolean underMaximum = true;
		try {
			if (Integer.parseInt(code) >= minVolume && Integer.parseInt(code) <= maxVolume)
				underMaximum = false;
		} catch (NumberFormatException e) {

		}
		return underMaximum;
	}

	public static void setDataIn_Choice_Ind_Num_Doc(Choice choice_ind_num_doc, Request tamplateRequest) {
		String[] arr = RequestViewFunction.getStringMassiveI_N_D();
		for (String string : arr) {
			choice_ind_num_doc.add(string);
		}
		if (tamplateRequest != null) {
			if (tamplateRequest.getInd_num_doc()!=null){
			choice_ind_num_doc.select(tamplateRequest.getInd_num_doc().getName());
			}else {
				choice_ind_num_doc.select("");
			}
		}
	}
	
	public static void setDataIn_Choice_Izpitvan_Produkt(Choice choice_izpitvan_produkt, Request tamplateRequest) {
		String[] arr1 = RequestViewAplication.getStringMassiveIzpitvanProdukt();
		for (String string : arr1) {
			choice_izpitvan_produkt.add(string);
		}
		if (tamplateRequest != null) {
			choice_izpitvan_produkt.select(tamplateRequest.getIzpitvan_produkt().getName_zpitvan_produkt());
		}
	}
	
	public static void setDataIn_Choice_Obekt_na_Izpitvane_Request(Choice choice_obekt_na_izpitvane_request, Request tamplateRequest) {
		ArrayList<String> array_O_I_R = RequestViewAplication.getStringMassiveO_I_R();
		for (String string : array_O_I_R) {
			System.out.println(string);
			choice_obekt_na_izpitvane_request.add(string);
		}
		if (tamplateRequest != null) {
			choice_obekt_na_izpitvane_request
					.select(tamplateRequest.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane());
		}
	}
	
	public static void addNewObjectIn_Choice_Obekt_na_Izpitvane_Request(Choice choice_obekt_na_izpitvane_request) {
		ArrayList<String> array_O_I_R = RequestViewAplication.getStringMassiveO_I_R();
		Boolean fl = false;
		 final JFrame f = new JFrame();

		 new AddInChoice(f, array_O_I_R,
		 choice_obekt_na_izpitvane_request.getSelectedItem());

		String str = AddInChoice.getChoice();
		for (String string : array_O_I_R) {
			if (str.equals(string))
				fl = true;
		}
		if (!fl) {
			array_O_I_R.add(str);
			choice_obekt_na_izpitvane_request.add(str);

		}
		choice_obekt_na_izpitvane_request.select(str);
	}
	
	public static void addNewObjectIn_Choice_Obekt_na_Izpitvane_Request(JFrame parent, List<String> incomingValueStringList, List<String> bsic_listObektNaIzpit, String labelString) {
		Boolean fl = false;
		 final JFrame f = new JFrame();

		 new AddInChoice(f, bsic_listObektNaIzpit, incomingValueStringList.get(incomingValueStringList.size()-1));

		String str = AddInChoice.getChoice();
		for (String string : bsic_listObektNaIzpit) {
			if (str.equals(string))
				fl = true;
		}
		if (!fl) {
			bsic_listObektNaIzpit.add( str);
			incomingValueStringList.remove(incomingValueStringList.size()-1);
			incomingValueStringList.add(str);
		}
		new ChoiceFromListWithPlusAndMinus(f, incomingValueStringList, bsic_listObektNaIzpit, "Обект на изпитване");
		
		
	}
	
	public static void setDataIn_Choice_Razmernost(Choice choice_Razmernost, Request tamplateRequest) {
		String[] arr3 = RequestViewAplication.getStringMassiveRazmernost();
		for (String string : arr3) {
			choice_Razmernost.add(string);
		}
		if (tamplateRequest != null) {
			choice_Razmernost.select(tamplateRequest.getRazmernosti().getName_razmernosti());
		}
	}
	
	public static  void setTextIn_Text_Area_List_Izpitvan_Pokazatel(JTextArea txtArea_list_izpitvan_pokazatel, Request tamplateRequest) {
		if (tamplateRequest != null) {
			List<String> list_String_I_P_Tamplate = new ArrayList<String>();
			for (IzpitvanPokazatel izpitPokazatelFormTamplate : get_List_Izpitvan_pokazatel_From_Request(tamplateRequest)) {
				list_String_I_P_Tamplate.add(izpitPokazatelFormTamplate.getPokazatel().getName_pokazatel());
			}
			JFrame f = new JFrame();
			new ChoiceL_I_P(f, list_String_I_P_Tamplate, true);
			String strTamplate = "";
			txtArea_list_izpitvan_pokazatel.setText("");
			for (String string : ChoiceL_I_P.getChoiceL_P()) {
				strTamplate = strTamplate + string + "\n";
			}
			int cout_str = strTamplate.length();
			txtArea_list_izpitvan_pokazatel.setText(strTamplate.substring(0, cout_str - 1));
		}
	}
	
	public static void generateTextIn_Text_Area_List_Izpitvan_Pokazatel(JTextArea txtArea_list_izpitvan_pokazatel, Border border) {
		ArrayList<String> list_I_P = new ArrayList<String>();

		if (!txtArea_list_izpitvan_pokazatel.getText().equals("")) {
			list_I_P = ChoiceL_I_P.getChoiceL_P();
			System.out.println(list_I_P.size());
		}
		final JFrame f = new JFrame();
		new ChoiceL_I_P(f, list_I_P, false);

		String str = "";
		txtArea_list_izpitvan_pokazatel.setText("");
		for (String string : ChoiceL_I_P.getChoiceL_P()) {
			str = str + string + "\n";
		}

		txtArea_list_izpitvan_pokazatel.setBorder(border);
		int cout_str = str.length();
		txtArea_list_izpitvan_pokazatel.setText(str.substring(0, cout_str - 1));
	}
	
	public static String  generateTxtInDescriptGrupSample(Choice choice_Period, String sampleCount) {
		String txtInDescriptGrupSample="";
		String strMesec = "";
		String strChoice_Period = choice_Period.getSelectedItem();
		String prob = "пробa";
		String sbor = "Сборна";
		int count = 1;
		try {
			 count = Integer.parseInt(sampleCount);

		} catch (NumberFormatException e) {
			
		}
		if(count>1) {
			 prob = "проби";
			 sbor = "Сборни";
		}
//		String str_period_sample = DateChoice_period.get_str_period_sample(false);
		String str_period_sample = DateChoice.get_str_period_sample();
		if (strChoice_Period.equals("")) {
			txtInDescriptGrupSample = "";
			
			if(str_period_sample.length()>0) {
				txtInDescriptGrupSample =  sbor+" "+prob;
			}
			txtInDescriptGrupSample = txtInDescriptGrupSample + " "+str_period_sample;
		} else {
			int id_Period = PeriodDAO.getValuePeriodByPeriod(strChoice_Period).getId_period();
			if(id_Period<13){
				strMesec = "м. ";
			}
			if (str_period_sample.equals("")) {
				txtInDescriptGrupSample = "";
				txtInDescriptGrupSample = sbor+" "+prob +" за "+strMesec + strChoice_Period;
			} else {
				txtInDescriptGrupSample = "";
				
				txtInDescriptGrupSample = sbor+" "+prob +" за "+ strMesec+ strChoice_Period + " "+ str_period_sample ;
			}
		}
		return txtInDescriptGrupSample;
	}
	
	public static void setDataIn_Choice_Period(Choice choice_Period) {
		String[] array = RequestViewFunction.getStringMassivePeriod();
		for (String string : array) {
			choice_Period.add(string);
		}
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

	public static String GenerateStringRefDateTimeFromRequest(Request request) {
		List<Sample> sample = SampleDAO.getListSampleFromColumnByVolume("request", request);
		
		String [] masiveReffDateTime = new String[sample.size()];
		String [] masiveSampleCode = new String[sample.size()];
		int k=0;
		for (Sample samp : sample) {
			masiveReffDateTime[k] = samp.getDate_time_reference();
			masiveSampleCode[k] = samp.getSample_code();
			k++;
		}
		
		String date_time_reference = masiveReffDateTime[0];
		if (compaRefDateTime(masiveReffDateTime)) {
			date_time_reference = "";
			for (int i = 0; i < masiveReffDateTime.length; i++) {
				
				date_time_reference = date_time_reference +request.getRecuest_code()+"-"+ masiveSampleCode[i] + " / " + masiveReffDateTime[i]
						+ "   ";
						}
			date_time_reference = date_time_reference.substring(0, date_time_reference.length()-3);
		}

		return date_time_reference;

	}
	
	public static String GenerateStringRefDateTimeFromMasiveSample(String[][] masiveSampleValue) {
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
	
	public static void OpenRequestInWordDokTamplate(String requestString) throws ParseException {
		Request request = RequestDAO.getRequestFromColumnByVolume("recuest_code", requestString);
		// String list_izpitvan_pokazatel = CreateStringListIzpPokaz(request);

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

		String date_time_reference = "";
		if (!request.getRecuest_code().equals("templ")) {
			date_time_reference = GenerateStringRefDateTimeFromMasiveSample(smple_vol);
		}
		List<IzpitvanPokazatel> list_All_I_P = IzpitvanPokazatelDAO.getInListAllValueIzpitvan_pokazatel();
		String list_izpitvan_pokazatel = CreateStringListIzpPokaz(request, list_All_I_P);

		Map<String, String> substitutionData = Generate_Map_For_Request_Word_Document
				.GenerateMapForRequestWordDocument(request, list_izpitvan_pokazatel, smple_vol, date_time_reference);

		TranscluentWindow round = new TranscluentWindow();
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 GenerateRequestWordDoc.generateAndSend_Request_Docx("temp.docx",
		 				"Z-" + request.getRecuest_code() + "_" + request.getDate_request(), substitutionData, round);
				    	     	
		     }
		    });
		    thread.start();
		
	}
	
	public static String CreateStringListIzpPokaz(Request request, List<IzpitvanPokazatel> list_All_I_P) {

		List<IzpitvanPokazatel> list_izp_pok = get_List_Izpitvan_pokazatelFromList_I_P(request, list_All_I_P);
		// List<IzpitvanPokazatel> list_izp_pok =
		// get_List_Izpitvan_pokazatel_From_Request(request);
		String list_izpitvan_pokazatel = "";
		for (IzpitvanPokazatel izpitvan_pokazatel : list_izp_pok) {
			list_izpitvan_pokazatel = list_izpitvan_pokazatel + izpitvan_pokazatel.getPokazatel().getName_pokazatel()
					+ "; \n";
		}
		return list_izpitvan_pokazatel;
	}
	
	public static List<IzpitvanPokazatel> get_List_Izpitvan_pokazatelFromList_I_P(Request request,
			List<IzpitvanPokazatel> listTab_I_P) {
		List<IzpitvanPokazatel> list_izp_pok = new ArrayList<IzpitvanPokazatel>();
		for (IzpitvanPokazatel izpitvanPokazatel : listTab_I_P) {
			if (izpitvanPokazatel.getRequest().getRecuest_code().equals(request.getRecuest_code())) {
				list_izp_pok.add(izpitvanPokazatel);

			}
		}
		return list_izp_pok;
	}

	public static String[][] generateMasiveSampleDescriptionFromRequest(Request request) {
		int countSample = request.getCounts_samples();
		List<Sample> sample = SampleDAO.getListSampleFromColumnByVolume("request", request);
		String[][] volSampleView = new String[countSample][6];
for (int i = 0; i < countSample; i++) {
	volSampleView[i][0] = request.getRecuest_code()+"-"+ sample.get(i).getSample_code();
	volSampleView[i][1] = sample.get(i).getObekt_na_izpitvane().getName_obekt_na_izpitvane();
	volSampleView[i][2] = sample.get(i).getDescription_sample();
	volSampleView[i][3] = sample.get(i).getDate_time_reference();
	String str = "";
	if (sample.get(i).getPeriod()!=null) {
		str = sample.get(i).getPeriod().getValue();
	}
	volSampleView[i][4] = str;
	volSampleView[i][5] = sample.get(i).getGodina_period()+"";

}
return volSampleView;
}
	
	private static Boolean compaRefDateTime(String[] masiveRefDateTime) {
		
		int count_Sample = masiveRefDateTime.length;
		Boolean comparedFlag = false;
		for (int i = 0; i < count_Sample; i++) {
			String compared = masiveRefDateTime[i];
			for (int j = i; j < count_Sample; j++) {
				System.out.println(j+"  "+masiveRefDateTime[j]);
				if (!compared.equals(masiveRefDateTime[j])) {
					comparedFlag = true;
					return comparedFlag;
				}
			}
		}
		return comparedFlag;
	}
	
	public static List<IzpitvanPokazatel> get_List_Izpitvan_pokazatel_From_Request(Request request) {
		List<IzpitvanPokazatel> list_izp_pok = IzpitvanPokazatelDAO
				.getListIzpitvan_pokazatelFromColumnByVolume("request", request);

		return list_izp_pok;
	}
	
	public static String generateStringListIzpitvanPokazatelFromrequest (Request request){
	String strTamplate = "";
	
	for (IzpitvanPokazatel izpitvanPokazayel : get_List_Izpitvan_pokazatel_From_Request(request)) {
		strTamplate = strTamplate + izpitvanPokazayel.getPokazatel().getName_pokazatel() + "\n";
	}
	int cout_str = strTamplate.length();
	
	return strTamplate.substring(0, cout_str - 1);
	}
	
	public static String DateNaw(Boolean whiteTime) {
		String dateNaw = null;
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
		if (whiteTime)
			sdf = new SimpleDateFormat(FORMAT_DATE_TIME);

		dateNaw = sdf.format(Calendar.getInstance().getTime());

		return dateNaw;
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

	public static Boolean incorrectReception_Date_Period(JTextField txtFld_date_reception) {
		Boolean incorrectReception_date_period =false;
		String startData ="";
				String endData = "";		
		String  reception_date_period = txtFld_date_reception.getText();
		if(reception_date_period.length()>10){
		
			if(reception_date_period.indexOf("÷")>0){
				 startData = reception_date_period.substring(0,reception_date_period.indexOf("÷")).trim();
				endData = reception_date_period.substring(reception_date_period.indexOf("÷")+1,reception_date_period.length()).trim();
				if (DatePicker.incorrectDate(startData, false)||DatePicker.incorrectDate(endData, false)) {
					incorrectReception_date_period	= true;
				}
			}else{
				incorrectReception_date_period	= true;	
			}
		}else{
			 startData = reception_date_period;
			 if (DatePicker.incorrectDate(startData, false)) {
					incorrectReception_date_period	= true;
				}
		}
		return incorrectReception_date_period;
	}

}
