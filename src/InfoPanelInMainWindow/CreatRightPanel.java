package InfoPanelInMainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Calendar;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import Aplication.RequestDAO;
import Aplication.TableColumnDAO;
import DBase_Class.Request;
import DBase_Class.TableColumn;
import WindowView.DatePicker;
import WindowView.RequestMiniFrame;

public class CreatRightPanel {
	
	private static JTextField textField_Panel_Right;
	private static Boolean korektYearInTxtField=true;
	
	public static void creatRightPanel(JPanel under_panel_Right) {

		List<Integer> listMissingRequest = VariableFromStartWindowPanel.getListMissingRequestRightPanelStartWindow();
		
		
		JPanel text_Panel_Right = new JPanel();
		FlowLayout fl_text_Panel_Right = (FlowLayout) text_Panel_Right.getLayout();
		fl_text_Panel_Right.setAlignment(FlowLayout.RIGHT);
		text_Panel_Right.setMaximumSize(new Dimension(32767, 20));
		under_panel_Right.add(text_Panel_Right);
		
		String curentData = DatePicker.getCurentDate(true);
		
		curentData = "Към "+curentData+" Последната заявка е ";
		JLabel lbl_text_Last_Request = new JLabel(curentData+RequestDAO.getMaxRequestCode()+". ");
		
		
		text_Panel_Right.add(lbl_text_Last_Request);


		JLabel lbl_text_Panel_Right = new JLabel("Пропуснати записи от ");
		text_Panel_Right.add(lbl_text_Panel_Right);

		textField_Panel_Right = new JTextField();
		textField_Panel_Right.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textField_Panel_Right.setMargin(new Insets(2, 0, 2, 0));
		textField_Panel_Right.setBorder(null);
//		textField_Panel_Right.setPreferredSize(new Dimension(2, 16));
		textField_Panel_Right.setMaximumSize(new Dimension(6, 50));
		text_Panel_Right.add(textField_Panel_Right);
		textField_Panel_Right.setColumns(3);
		textField_Panel_Right.setText(getTextStartYear());
		
		textField_Panel_Right.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {
				setKorektYearInTxtField(CreatRightPanel.checkEnterKorektYearForTextField(textField_Panel_Right));

			}

			@Override
			public void keyPressed(KeyEvent event) {

			}
		});
		IntegerDocumentFilter.addTo(textField_Panel_Right);

		JLabel lbl2_text_Panel_Right = new JLabel("г.:");
		text_Panel_Right.add(lbl2_text_Panel_Right);

		JPanel column_Panel_Right = new JPanel();
		under_panel_Right.add(column_Panel_Right);
		column_Panel_Right.setLayout(new BorderLayout(0, 0));

		JPanel under_column_Panel_Right = new JPanel();
		column_Panel_Right.add(under_column_Panel_Right);
		under_column_Panel_Right.setLayout(new BoxLayout(under_column_Panel_Right, BoxLayout.X_AXIS));
		
		
		
		
		

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

		for (Integer integer : listMissingRequest) {
			System.out.println(integer);
			JLabel lblResults = new JLabel(integer.toString());
			panel_missing_results.add(lblResults);
			lblResults.setToolTipText("info");
			lblResults.addMouseListener(new MouseAdapter() {

				public void mouseReleased(MouseEvent e) {
					
				}

				public void mousePressed(MouseEvent e) {
					Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", integer.toString());
					if (choiseRequest != null)
						new RequestMiniFrame(new JFrame(), choiseRequest);
				}

				public void mouseEntered(MouseEvent e) {
					lblResults.setForeground(Color.RED);
				}

				public void mouseExited(MouseEvent e) {
					lblResults.setForeground(Color.BLACK);
				}

			});
		}
	}

	static String getTextStartYear(){
		String startYear = "2020";
	List<TableColumn> list_TableColumn = TableColumnDAO.getListTableColumnByTipe_Table("MainWindow_text");
	startYear = list_TableColumn.get(0).getName_Column();
	
	return startYear;
	}
	
	
	public static Boolean checkEnterKorektYearForTextField(JTextField txtField_RequestCode) {
		int curentYear = Calendar.getInstance().get(Calendar.YEAR);
		int yearFromTxtField = Integer.parseInt(txtField_RequestCode.getText());
		Boolean corectRequestCode = true;
		if (yearFromTxtField > curentYear || yearFromTxtField < 2017) {
			txtField_RequestCode.setForeground(Color.red);

			corectRequestCode = false;
		} else {
			txtField_RequestCode.setForeground(Color.BLACK);
			corectRequestCode = true;
		}
		return corectRequestCode;
	}

	public static Boolean getKorektYearInTxtField() {
		return korektYearInTxtField;
	}

	public static String getYearInTxtField() {
		return textField_Panel_Right.getText();
	}
	
	public static void setKorektYearInTxtField(Boolean korektYearInTxtField) {
		CreatRightPanel.korektYearInTxtField = korektYearInTxtField;
	}

}
