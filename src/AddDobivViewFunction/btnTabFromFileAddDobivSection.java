package AddDobivViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import AddResultViewFunction.AddResultViewMetods;
import DBase_Class.Metody;
import ExcelFilesFunction.Destruct_Result;
import ExcelFilesFunction.ReadExcelFile;
import Table.Add_DefaultTableModel;
import WindowView.AddDobivView;


public class btnTabFromFileAddDobivSection {
	
	public static void btnTabFromFileListener(AddDobivView addDobivView, JPanel basic_panel, JButton btnTabFromFile,  
			Choice choiceMetody, Choice choiceOIR,JTextField txtStandartCode) {
		btnTabFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (OverallVariablesAddDobiv.getFlagIncertedFile()) {
					
					AddResultViewMetods.setWaitCursor(basic_panel);
					if (!choiceMetody.getSelectedItem().trim().isEmpty()) {
						int switCase =selestTypeReadFileByChoiceMetod(basic_panel, OverallVariablesAddDobiv.getSelectedMetod());
						
//						if (MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()).getId_metody() == 9) {
//							 @SuppressWarnings("unused")
//							TranscluentWindow round = new
//							 TranscluentWindow();
//							 final Thread thread = new Thread(new Runnable() {
//							 @Override
//							 public void run() {
//							
////							 readFromGenie2kFile();
////							 ViewTableInPanel(basic_panel, round);
//							 }
//							 });
//							 thread.start();
//						}
						switch (switCase) {	
					case 10:
//						String codeSamampleFromGamaFile = ReadGamaFile.getCod_sample();
//						if (AddresultViewMetods.checkKorektFileName(codeSamampleFromGamaFile, codeSamample)) {
//						
//						Users user = ReadGamaFile.getUserFromFile();
//						String str = user.getName_users() + " " + user.getFamily_users();
//						choiceOIR.select(str);
//						Object[][] ss = AddresultViewMetods.CreatedataTableFromGeany2kFile(choicePokazatel);
//						OverallVariables.setFromDBase(false);
//						AddresultViewMetods.createDataTableAndViewTableInPanel( addResultsViewWithTable,basic_panel, ss);
//						}
						break;

					case 0:
						
						String standartCode = txtStandartCode.getText();
						
						Object[][] ssExcel = AddDobivViewMetods.CreateDataTableDobivFromExcelFile(choiceMetody);
						List<Destruct_Result> destruct_Result_List = OverallVariablesAddDobiv.getDestruct_Result_List();
						if(Add_DefaultTableModel.checkKorektFileNameAndMetod(choiceMetody,standartCode, destruct_Result_List)){
							Add_DefaultTableModel.setInChoiceOIR(choiceOIR);
						AddDobivViewMetods.createDataTableAndViewTableInPanel( addDobivView ,basic_panel, ssExcel);
						}
						break;
					}
					} else {
						JOptionPane.showInputDialog("Само за метод М.ЛИ-РХ-10", JOptionPane.ERROR_MESSAGE);
					}
					AddResultViewMetods.setDefaultCursor(basic_panel);
				} else {
					JOptionPane.showInputDialog("Не сте избрали коректен файл", JOptionPane.ERROR_MESSAGE);
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

}
