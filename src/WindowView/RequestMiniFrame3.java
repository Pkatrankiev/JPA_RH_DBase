package WindowView;

import javax.swing.JPanel;

import DBase_Class.Request;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class RequestMiniFrame3 extends JPanel {
	
	public RequestMiniFrame3(Request request) {
		
		String codeRequest = request.getRecuest_code();
		String dateRequest = request.getDate_request();
		
		String i_P_Request = request.getIzpitvan_produkt().getName_zpitvan_produkt();
		String obekt_I_Request = request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
		String countSample = request.getCounts_samples()+"";
		String applicant = "";
		if (request.getXtra_module()==null) {
			applicant = "ДП \"РАО\"";	
		}else{
			if (request.getXtra_module().getInternal_applicant()!=null) {
				applicant = request.getXtra_module().getInternal_applicant().getInternal_applicant_organization();
		}else{
			applicant = request.getXtra_module().getExternal_applicant().getExternal_applicant_name();
		}
		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 57, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel requestCode_label = new JLabel("Код на заявката");
		GridBagConstraints gbc_requestCode_label = new GridBagConstraints();
		gbc_requestCode_label.insets = new Insets(0, 0, 5, 5);
		gbc_requestCode_label.gridx = 0;
		gbc_requestCode_label.gridy = 0;
		add(requestCode_label, gbc_requestCode_label);
		
		JLabel requestCode_value = new JLabel(codeRequest);
		GridBagConstraints gbc_requestCode_value = new GridBagConstraints();
		gbc_requestCode_value.insets = new Insets(0, 0, 5, 5);
		gbc_requestCode_value.gridx = 1;
		gbc_requestCode_value.gridy = 0;
		add(requestCode_value, gbc_requestCode_value);
		
		JLabel label = new JLabel("/");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 0;
		add(label, gbc_label);
		
		JLabel requestDate_value = new JLabel(dateRequest);
		GridBagConstraints gbc_requestDate_value = new GridBagConstraints();
		gbc_requestDate_value.insets = new Insets(0, 0, 5, 5);
		gbc_requestDate_value.gridx = 3;
		gbc_requestDate_value.gridy = 0;
		add(requestDate_value, gbc_requestDate_value);
		
		
//		if(request.getInd_num_doc()!=null){
		
		String IDNumberRequest = request.getInd_num_doc().getName();
		JLabel IdNumber_label = new JLabel("Ид. номер");
		GridBagConstraints gbc_IdNumber_label = new GridBagConstraints();
		gbc_IdNumber_label.insets = new Insets(0, 0, 5, 5);
		gbc_IdNumber_label.gridx = 0;
		gbc_IdNumber_label.gridy = 1;
		add(IdNumber_label, gbc_IdNumber_label);
		
		JLabel IdNumber_Value = new JLabel(IDNumberRequest);
		GridBagConstraints gbc_IdNumber_Value = new GridBagConstraints();
		gbc_IdNumber_Value.insets = new Insets(0, 0, 5, 5);
		gbc_IdNumber_Value.gridx = 1;
		gbc_IdNumber_Value.gridy = 1;
		add(IdNumber_Value, gbc_IdNumber_Value);
//		}
		
		
		JLabel applicant_label = new JLabel("Заявител");
		GridBagConstraints gbc_applicant_label = new GridBagConstraints();
		gbc_applicant_label.insets = new Insets(0, 0, 5, 5);
		gbc_applicant_label.gridx = 0;
		gbc_applicant_label.gridy = 2;
		add(applicant_label, gbc_applicant_label);
		
		JLabel applicant_value = new JLabel(applicant);
		GridBagConstraints gbc_applicant_value = new GridBagConstraints();
		gbc_applicant_value.insets = new Insets(0, 0, 5, 5);
		gbc_applicant_value.gridx = 1;
		gbc_applicant_value.gridy = 2;
		add(applicant_value, gbc_applicant_value);
		
		JLabel I_P_R_label = new JLabel("Изпитван продукт");
		GridBagConstraints gbc_I_P_R_label = new GridBagConstraints();
		gbc_I_P_R_label.insets = new Insets(0, 0, 5, 5);
		gbc_I_P_R_label.gridx = 0;
		gbc_I_P_R_label.gridy = 3;
		add(I_P_R_label, gbc_I_P_R_label);
		
		JLabel I_P_R_Value = new JLabel(i_P_Request);
		GridBagConstraints gbc_I_P_R_Value = new GridBagConstraints();
		gbc_I_P_R_Value.insets = new Insets(0, 0, 5, 5);
		gbc_I_P_R_Value.gridx = 1;
		gbc_I_P_R_Value.gridy = 3;
		add(I_P_R_Value, gbc_I_P_R_Value);
		
		JLabel O_I_R_label = new JLabel("Обект на изпитване");
		GridBagConstraints gbc_O_I_R_label = new GridBagConstraints();
		gbc_O_I_R_label.insets = new Insets(0, 0, 5, 5);
		gbc_O_I_R_label.gridx = 0;
		gbc_O_I_R_label.gridy = 4;
		add(O_I_R_label, gbc_O_I_R_label);
		
		JLabel O_I_R_value = new JLabel(obekt_I_Request);
		GridBagConstraints gbc_O_I_R_value = new GridBagConstraints();
		gbc_O_I_R_value.insets = new Insets(0, 0, 5, 5);
		gbc_O_I_R_value.gridx = 1;
		gbc_O_I_R_value.gridy = 4;
		add(O_I_R_value, gbc_O_I_R_value);
		
		JLabel countSample_label = new JLabel("Брой проби");
		GridBagConstraints gbc_countSample_label = new GridBagConstraints();
		gbc_countSample_label.insets = new Insets(0, 0, 0, 5);
		gbc_countSample_label.gridx = 0;
		gbc_countSample_label.gridy = 5;
		add(countSample_label, gbc_countSample_label);
		
		JLabel countSample_value = new JLabel(countSample);
		GridBagConstraints gbc_countSample_value = new GridBagConstraints();
		gbc_countSample_value.insets = new Insets(0, 0, 0, 5);
		gbc_countSample_value.gridx = 1;
		gbc_countSample_value.gridy = 5;
		add(countSample_value, gbc_countSample_value);
		setVisible(true);
	}

	
}
