package WindowView;

import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Aplication.Ind_num_docDAO;
import Aplication.RequestDAO;
import DBase_Class.Ind_num_doc;

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
