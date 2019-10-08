package AddDobivViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import AddResultViewFunction.AddResultViewMetods;
import ExcelFilesFunction.ReadExcelFile;
import WindowView.ReadGamaFile;

public class btnOpenFileAddDobivSection {

	public static void btnOpenFileListener(JButton btnOpenFile, JFileChooser fileChooser, 
			JTextField txtBasicValueResult, JTextField txtStandartCode, Choice choiceMetody) {
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showOpenDialog(null);
				int sizeExcelList = 0, sizeGamaList = 0;
				try {
					String fileName = fileChooser.getSelectedFile().toString();
					String codeDobiv = txtStandartCode.getText();
					
					if (AddResultViewMetods.checkKorektFileName(fileName, codeDobiv)) {
					
					txtBasicValueResult.setText(fileName);
					System.out.println(codeDobiv+"   "+fileName);
					if (!choiceMetody.getSelectedItem().trim().isEmpty()){
					if (choiceMetody.getSelectedItem().indexOf("10")>0){
					
						ReadGamaFile.getReadGamaFile(fileName);
						sizeGamaList = ReadGamaFile.getListNuclideMDA();
					} else {
						Boolean forResults = false;
						OverallVariablesAddDobiv.setDestruct_Result_List (ReadExcelFile.getDestruct_Result_ListFromExcelFile(fileName, forResults));
						sizeExcelList = OverallVariablesAddDobiv.getDestruct_Result_List().size();
					}
					
					if (sizeGamaList > 0 || sizeExcelList > 0) {
						OverallVariablesAddDobiv.setFlagIncertedFile ( true);
					} else {
						OverallVariablesAddDobiv.setFlagIncertedFile ( false);
						JOptionPane.showMessageDialog(null, "Не сте избрали коректен файл!", "Грешни данни",
								JOptionPane.ERROR_MESSAGE);

					}
					}else {
						OverallVariablesAddDobiv.setFlagIncertedFile ( false);
						JOptionPane.showMessageDialog(null, "Не сте избрали метод", "Грешни данни",
								JOptionPane.ERROR_MESSAGE);
				}
					}
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "Не сте избрали файл!", "Грешни данни",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});
	}
	

}
