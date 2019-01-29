package WindowView;

import java.awt.Choice;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Aplication.Ind_num_docDAO;
import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import DBase_Class.Ind_num_doc;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Period;
import DBase_Class.Request;

public class RequestViewFunction {

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
			for (IzpitvanPokazatel izpitPokazatelFormTamplate : RequestViewAplication
					.get_List_Izpitvan_pokazatel_From_Request(tamplateRequest)) {
				list_String_I_P_Tamplate.add(izpitPokazatelFormTamplate.getPokazatel().getName_pokazatel());
			}
			JFrame f = new JFrame();
			ChoiceL_I_P choiceLP = new ChoiceL_I_P(f, list_String_I_P_Tamplate, true);
			String strTamplate = "";
			txtArea_list_izpitvan_pokazatel.setText("");
			for (String string : choiceLP.getChoiceL_P()) {
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
		ChoiceL_I_P choiceLP = new ChoiceL_I_P(f, list_I_P, false);

		String str = "";
		txtArea_list_izpitvan_pokazatel.setText("");
		for (String string : choiceLP.getChoiceL_P()) {
			str = str + string + "\n";
		}

		txtArea_list_izpitvan_pokazatel.setBorder(border);
		int cout_str = str.length();
		txtArea_list_izpitvan_pokazatel.setText(str.substring(0, cout_str - 1));
	}
	
	public static String  generateTxtInDescriptGrupSample(Choice choice_Period) {
		String txtInDescriptGrupSample="";
		String strMesec = "";
		String strChoice_Period = choice_Period.getSelectedItem();
		
		if (strChoice_Period.equals("")) {
			txtInDescriptGrupSample = "";
			txtInDescriptGrupSample = DateChoice.get_str_period();
		} else {
			int id_Period = PeriodDAO.getValuePeriodByPeriod(strChoice_Period).getId_period();
			if(id_Period<13){
				strMesec = "м.";
			}
			if (DateChoice.get_str_period().equals("")) {
				txtInDescriptGrupSample = "";
				txtInDescriptGrupSample = " за "+strMesec + strChoice_Period;
			} else {
				txtInDescriptGrupSample = "";
				
				txtInDescriptGrupSample = DateChoice.get_str_period() + " за "+strMesec
						+ strChoice_Period;
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
}
