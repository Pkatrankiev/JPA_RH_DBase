package AddResultViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import AddDobivViewFunction.OverallVariablesAddDobiv;
import Aplication.MetodyDAO;
import DBase_Class.Metody;
import DBase_Class.Users;
import ExcelFilesFunction.Destruct_Result;
import ExcelFilesFunction.ReadExcelFile;
import Table.Add_DefaultTableModel;
import WindowView.AddResultsView;
import WindowView.ReadGamaFile;

public class btnTabFromFileSection {

	public static void btnTabFromFileListener(AddResultsView addResultsViewWithTable,JPanel basic_panel, JButton btnTabFromFile, Choice choiceMetody, JTextField txtRqstCode,
			Choice choiceSmplCode, Choice choiceOIR, Choice choicePokazatel) {
		
		btnTabFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (OverallVariablesAddResults.getFlagIncertedFile()) {
										
					AddResultViewMetods.setWaitCursor(basic_panel);
						if (!choiceMetody.getSelectedItem().trim().isEmpty()) {
							OverallVariablesAddResults.setSelectedMetod ( MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));
							String codeSamample = txtRqstCode.getText() + "-" + choiceSmplCode.getSelectedItem();
							int switCase =selestTypeReadFileByChoiceMetod(basic_panel, OverallVariablesAddResults.getSelectedMetod());
							System.out.println(switCase+ " switCase------------------------------------------------");
							switch (switCase) {
							case 10:
								String codeSamampleFromGamaFile = ReadGamaFile.getCod_sample();
								if (AddResultViewMetods.checkKorektFileName(codeSamampleFromGamaFile, codeSamample)) {
								checkFor10SysError();
								Users user = ReadGamaFile.getUserFromFile();
								String str = user.getName_users() + " " + user.getFamily_users();
								choiceOIR.select(str);
								Object[][] ss = AddResultViewMetods.CreateMasiveObjectFromGeany2kFile(choicePokazatel);
								Add_DefaultTableModel.setFromDBase(false);
								AddResultViewMetods.createDataTableAndViewTableInPanel( addResultsViewWithTable,basic_panel, ss);
								}
								break;

							case 0:
								Object[][] ssExcel = AddResultViewMetods.CreateMasiveObjectFromExcelFile( choicePokazatel);
								List<Destruct_Result> destruct_Result_List = OverallVariablesAddResults.getDestruct_Result_List();
								
								if(Add_DefaultTableModel.checkKorektFileNameAndMetod(choiceMetody,codeSamample, destruct_Result_List)){
									Add_DefaultTableModel.setInChoiceOIR(choiceOIR);
									AddResultViewMetods.createDataTableAndViewTableInPanel( addResultsViewWithTable,basic_panel, ssExcel);
								}
								break;
							}
						} else {
							JOptionPane.showMessageDialog(null, "Не е избран метод", "Грешни данни",
									JOptionPane.ERROR_MESSAGE);
						}
					
						AddResultViewMetods.setDefaultCursor(basic_panel);
				} else {
					JOptionPane.showMessageDialog(null, "Не сте избрали коректен файл!", "Грешни данни",
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
		if (OverallVariablesAddResults.getChoiseRequest().getZabelejki().getName_zabelejki().toString().indexOf("10%") > 0
				&& Double.compare(sysError, 10.00) != 0) {
			JOptionPane.showMessageDialog(null, "Не е добавена 10% систематична \nгрешка към неопределеността",
					"Грешни данни", JOptionPane.ERROR_MESSAGE);
		}
	}

	

	
	
}
