package AddResultViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import ExcelFilesFunction.ReadExcelFile;
import WindowView.ReadGamaFile;

public class btnOpenFileSection {

	public static void btnOpenFileListener(JButton btnOpenFile, JFileChooser fileChooser,JTextField txtRqstCode,
			Choice choiceSmplCode, JTextField txtBasicValueResult, Choice choiceMetody) {
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showOpenDialog(null);

//				destruct_Result_List = new ArrayList<Destruct_Result>();
				int sizeExcelList = 0, sizeGamaList = 0;
				try {
					String pathfileName = fileChooser.getSelectedFile().toString();
					String stringfileName = fileChooser.getSelectedFile().getName();
					String codeSamample = txtRqstCode.getText() + "-" + choiceSmplCode.getSelectedItem();
					
					if (AddResultViewMetods.checkKorektFileName(stringfileName, codeSamample)) {
					
					txtBasicValueResult.setText(pathfileName);
					
					if (!choiceMetody.getSelectedItem().trim().isEmpty()){
					if (choiceMetody.getSelectedItem().indexOf("10")>0){
					
						ReadGamaFile.getReadGamaFile(pathfileName);
						sizeGamaList = ReadGamaFile.getListNuclideMDA();
					} else {
						Boolean forResults = true;
						OverallVariablesAddResults.setDestruct_Result_List (ReadExcelFile.getDestruct_Result_ListFromExcelFile(pathfileName, forResults));
						sizeExcelList = OverallVariablesAddResults.getDestruct_Result_List().size();
					}
					
					if (sizeGamaList > 0 || sizeExcelList > 0) {
						OverallVariablesAddResults.setFlagIncertedFile ( true);
					} else {
						OverallVariablesAddResults.setFlagIncertedFile ( false);
						JOptionPane.showMessageDialog(null, "Не сте избрали коректен файл!", "Грешни данни",
								JOptionPane.ERROR_MESSAGE);

					}
					}else {
						OverallVariablesAddResults.setFlagIncertedFile ( false);
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
