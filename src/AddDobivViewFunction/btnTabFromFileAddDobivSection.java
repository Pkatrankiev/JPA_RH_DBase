package AddDobivViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import AddResultViewFunction.AddresultViewMetods;
import AddResultViewFunction.OverallVariables;
import Aplication.DobivDAO;
import Aplication.MetodyDAO;
import DBase_Class.Dobiv;
import DBase_Class.Metody;
import DBase_Class.Users;
import ExcelFilesFunction.ReadExcelFile;
import WindowView.ReadGamaFile;
import WindowView.TranscluentWindow;

public class btnTabFromFileAddDobivSection {
	
	public static void btnTabFromFileListener(JPanel basic_panel, JButton btnTabFromFile,  Choice choiceMetody, JTextField txtStandartCode) {
		btnTabFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (OverallVariablesAddDobiv.getFlagIncertedFile()) {
					
					AddresultViewMetods.setWaitCursor(basic_panel);
					if (!choiceMetody.getSelectedItem().trim().isEmpty()) {
						int switCase =selestTypeReadFileByChoiceMetod(basic_panel, OverallVariables.getSelectedMetod());
						
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
						String codeSamampleFromExcelFile = ReadExcelFile.getCod_sample();
						String standartCode = txtStandartCode.getText();
						if (AddresultViewMetods.checkKorektFileName(codeSamampleFromExcelFile, standartCode)) {
						if( AddresultViewMetods.checkForKoretMetod(OverallVariables.getDestruct_Result_List(), choiceMetody)){
							
							
							Object[][] ssExcel = AddDobivViewMetods.CreateDataTableDobivFromExcelFile(choiceMetody);
							
						OverallVariables.setFromDBase(false);
						AddDobivViewMetods.startViewtablePanel( addDobivView, masiveDobivForMetod, panel, round);
						AddresultViewMetods.createDataTableAndViewTableInPanel( addResultsViewWithTable,basic_panel, ssExcel);
						}
						}
						break;
					}
						
						
						
						
					} else {
						JOptionPane.showInputDialog("Ñàìî çà ìåòîä Ì.ËÈ-ĞÕ-10", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showInputDialog("Íå ñòå èçáğàëè êîğåêòåí ôàéë", JOptionPane.ERROR_MESSAGE);
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
