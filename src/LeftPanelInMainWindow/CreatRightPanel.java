package LeftPanelInMainWindow;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Aplication.RequestDAO;

public class CreatRightPanel {

	public static void creatRightPanel(JPanel under_column_Panel_Right) {
		
		List<Integer> listMissingRequest = VariableFromStartWindowPanel.getListMissingRequestRightPanelStartWindow();

		Component horizontalStrut_1 = Box.createHorizontalStrut(15);
		under_column_Panel_Right.add(horizontalStrut_1);
	
		JPanel panel_missing_request = new JPanel();
		under_column_Panel_Right.add(panel_missing_request);
		panel_missing_request.setMaximumSize(new Dimension(60, 32767));
		panel_missing_request.setLayout(new BoxLayout(panel_missing_request, BoxLayout.Y_AXIS));


		Component horizontalStrut = Box.createHorizontalStrut(15);
		under_column_Panel_Right.add(horizontalStrut);

		JPanel panel_missing_results = new JPanel();
		under_column_Panel_Right.add(panel_missing_results);
		panel_missing_results.setMaximumSize(new Dimension(100, 32767));
		panel_missing_results.setLayout(new BoxLayout(panel_missing_results, BoxLayout.Y_AXIS));

		Component horizontalStrut_2 = Box.createHorizontalStrut(15);
		under_column_Panel_Right.add(horizontalStrut_2);

		JPanel panel_missing_protokol = new JPanel();
		under_column_Panel_Right.add(panel_missing_protokol);
		panel_missing_protokol.setMaximumSize(new Dimension(100, 32767));
		panel_missing_protokol.setLayout(new BoxLayout(panel_missing_protokol, BoxLayout.Y_AXIS));
		
		JLabel lblLabel_Code = new JLabel("Пропуснати");
		panel_missing_request.add(lblLabel_Code);
		lblLabel_Code.setAlignmentY(Component.TOP_ALIGNMENT);
		lblLabel_Code.setMaximumSize(new Dimension(80, 14));

		JLabel lblNewLabel_1 = new JLabel("заявки");
		panel_missing_request.add(lblNewLabel_1);
		
		setsLabel(listMissingRequest, panel_missing_request);
		
		
		panel_missing_request.revalidate();
		panel_missing_request.repaint();
		
		listMissingRequest = VariableFromStartWindowPanel.getListMissingResultsRightPanelStartWindo();
		JLabel lblResults = new JLabel("Заявки без");
		panel_missing_results.add(lblResults);
		lblResults.setAlignmentY(Component.TOP_ALIGNMENT);
		lblResults.setMaximumSize(new Dimension(60, 14));

		lblResults = new JLabel("резултати");
		panel_missing_results.add(lblResults);
		
		setsLabel(listMissingRequest, panel_missing_results);
		
		panel_missing_results.revalidate();
		panel_missing_results.repaint();
		
		listMissingRequest = VariableFromStartWindowPanel.getListMissingProtokolsRightPanelStartWindo();
		
		JLabel lblProtokol = new JLabel("Заявки без");
		panel_missing_protokol.add(lblProtokol);
		lblProtokol.setAlignmentY(Component.TOP_ALIGNMENT);
		lblProtokol.setMaximumSize(new Dimension(60, 14));

		lblProtokol = new JLabel("протокол");
		panel_missing_protokol.add(lblProtokol);
		
		setsLabel(listMissingRequest, panel_missing_protokol);
		
		panel_missing_protokol.revalidate();
		panel_missing_protokol.repaint();
		
		
		
	}

	private static void setsLabel(List<Integer> listMissingRequest, JPanel panel_missing_results) {
		JLabel lblResults;
		for (Integer integer : listMissingRequest) {
			System.out.println(integer);
			lblResults = new JLabel(integer.toString());
			panel_missing_results.add(lblResults);
		}
	}
	
	public static Boolean checkEnterKorektYearForTextField(JTextField txtField_RequestCode) {
	 int curentYear = Calendar.getInstance().get(Calendar.YEAR);
	 int yearFromTxtField = Integer.parseInt(txtField_RequestCode.getText());
		Boolean corectRequestCode = true;
			if (yearFromTxtField>curentYear||yearFromTxtField<2017) {
				txtField_RequestCode.setForeground(Color.red);
				
				corectRequestCode = false;
			} else {
				txtField_RequestCode.setForeground(Color.BLACK);

//				txtField_RequestCode.setBorder(new LineBorder(Color.BLACK));
				
				corectRequestCode = true;
			}
			return corectRequestCode;
		}
	
}
