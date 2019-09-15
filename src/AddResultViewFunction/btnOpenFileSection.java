package AddResultViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ExcelFilesFunction.Destruct_Result;
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
					String fileName = fileChooser.getSelectedFile().toString();
					String codeSamample = txtRqstCode.getText() + "-" + choiceSmplCode.getSelectedItem();
					
					if (AddresultViewMwetods.checkKorektFileName(fileName, codeSamample)) {
					
					txtBasicValueResult.setText(fileName);
					System.out.println(codeSamample+"   "+fileName);
					if (!choiceMetody.getSelectedItem().trim().isEmpty()){
					if (choiceMetody.getSelectedItem().indexOf("10")>0){
					
						ReadGamaFile.getReadGamaFile(fileName);
						sizeGamaList = ReadGamaFile.getListNuclideMDA();
					} else {
						�verallVariables.setDestruct_Result_List (ReadExcelFile.getDestruct_Result_ListFromExcelFile(fileName));
						sizeExcelList = �verallVariables.getDestruct_Result_List().size();
					}
					
					if (sizeGamaList > 0 || sizeExcelList > 0) {
						�verallVariables.setFlagIncertedFile ( true);
					} else {
						�verallVariables.setFlagIncertedFile ( false);
						JOptionPane.showMessageDialog(null, "�� ��� ������� �������� ����!", "������ �����",
								JOptionPane.ERROR_MESSAGE);

					}
					}else {
						�verallVariables.setFlagIncertedFile ( false);
						JOptionPane.showMessageDialog(null, "�� ��� ������� �����", "������ �����",
								JOptionPane.ERROR_MESSAGE);
				}
					}
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "�� ��� ������� ����!", "������ �����",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});
	}

	
	
}