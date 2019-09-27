package AddResultViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Aplication.MetodyDAO;
import DBase_Class.Metody;
import DBase_Class.Users;
import ExcelFilesFunction.ReadExcelFile;
import WindowView.AddResultsView;
import WindowView.ReadGamaFile;

public class btnTabFromFileSection {

	public static void btnTabFromFileListener(AddResultsView addResultsViewWithTable,JPanel basic_panel, JButton btnTabFromFile, Choice choiceMetody, JTextField txtRqstCode,
			Choice choiceSmplCode, Choice choiceOIR, Choice choicePokazatel) {
		
		btnTabFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (OverallVariables.getFlagIncertedFile()) {
										
					AddresultViewMetods.setWaitCursor(basic_panel);
						if (!choiceMetody.getSelectedItem().trim().isEmpty()) {
							OverallVariables.setSelectedMetod ( MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));
							String codeSamample = txtRqstCode.getText() + "-" + choiceSmplCode.getSelectedItem();
							int switCase =selestTypeReadFileByChoiceMetod(basic_panel, OverallVariables.getSelectedMetod());
							System.out.println(switCase+ " switCase------------------------------------------------");
							switch (switCase) {
							case 10:
								String codeSamampleFromGamaFile = ReadGamaFile.getCod_sample();
								if (AddresultViewMetods.checkKorektFileName(codeSamampleFromGamaFile, codeSamample)) {
								checkFor10SysError();
								Users user = ReadGamaFile.getUserFromFile();
								String str = user.getName_users() + " " + user.getFamily_users();
								choiceOIR.select(str);
								Object[][] ss = AddresultViewMetods.CreatedataTableFromGeany2kFile(choicePokazatel);
								OverallVariables.setFromDBase(false);
								AddresultViewMetods.createDataTableAndViewTableInPanel( addResultsViewWithTable,basic_panel, ss);
								}
								break;

							case 0:
								String codeSamampleFromExcelFile = ReadExcelFile.getCod_sample();
								if (AddresultViewMetods.checkKorektFileName(codeSamampleFromExcelFile, codeSamample)) {
								if( AddresultViewMetods.checkForKoretMetod(OverallVariables.getDestruct_Result_List(), choiceMetody)){
								Object[][] ssExcel = AddresultViewMetods.CreatedataTableFromExcelFile( choicePokazatel);
								OverallVariables.setFromDBase(false);
								AddresultViewMetods.createDataTableAndViewTableInPanel( addResultsViewWithTable,basic_panel, ssExcel);
								}
								}
								break;
							}
						} else {
							JOptionPane.showMessageDialog(null, "�� � ������ �����", "������ �����",
									JOptionPane.ERROR_MESSAGE);
						}
					
						AddresultViewMetods.setDefaultCursor(basic_panel);
				} else {
					JOptionPane.showMessageDialog(null, "�� ��� ������� �������� ����!", "������ �����",
							JOptionPane.ERROR_MESSAGE);

				}
			}

		});

	}

	private static int selestTypeReadFileByChoiceMetod(JPanel basic_panel, Metody selectedMetod) {
		if (selectedMetod.getCode_metody().indexOf("10") > 1) {
			return 10;
		}
		return 0;
	}
	
	private static void checkFor10SysError() {
		Double sysError = Double.parseDouble((String) ReadGamaFile.getSysError());
		if (OverallVariables.getChoiseRequest().getZabelejki().getName_zabelejki().toString().indexOf("10%") > 0
				&& Double.compare(sysError, 10.00) != 0) {
			JOptionPane.showMessageDialog(null, "�� � �������� 10% ������������ \n������ ��� ����������������",
					"������ �����", JOptionPane.ERROR_MESSAGE);
		}
	}

	

	
	
}
