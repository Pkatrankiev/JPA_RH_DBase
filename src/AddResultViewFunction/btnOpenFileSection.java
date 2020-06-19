package AddResultViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import AddDobivViewFunction.OverallVariablesAddDobiv;
import DBase_Class.Dobiv;
import ExcelFilesFunction.Destruct_Result;
import ExcelFilesFunction.ReadExcelFile;

import WindowView.ReadGamaFile;

public class btnOpenFileSection {

	public static void btnOpenFileListener(JButton btnOpenFile, JFileChooser fileChooser, JTextField txtRqstCode,
			Choice choiceSmplCode, JTextField txtBasicValueResult, Choice choiceMetody, Choice choicePokazatel) {
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showOpenDialog(null);

				// destruct_Result_List = new ArrayList<Destruct_Result>();
				int sizeExcelList = 0, sizeGamaList = 0;
				try {
					String pathfileName = fileChooser.getSelectedFile().toString();
					String stringfileName = fileChooser.getSelectedFile().getName();
					String codeSamample = txtRqstCode.getText() + "-" + choiceSmplCode.getSelectedItem();
					System.out.println("******************************************" + pathfileName);
					if (AddResultViewMetods.checkKorektFileName(stringfileName, codeSamample)) {

						txtBasicValueResult.setText(pathfileName);

						if (!choiceMetody.getSelectedItem().trim().isEmpty()) {
							String selectMetodStr = choiceMetody.getSelectedItem();
							int switCase = AddResultViewMetods.selestTypeReadFileByChoiceMetod(OverallVariablesAddResults.getSelectedMetod(), choicePokazatel.getSelectedItem().toString());
							
							if (switCase== 1) {
								Boolean forResults = true;
								if(pathfileName.equals("MDA")){
									List<Destruct_Result> destruct_Result_List = ReadExcelFile
											.getDestruct_Result_ListMDAAlfaExcelFile(pathfileName, forResults);
									OverallVariablesAddResults.setDestruct_Result_List(destruct_Result_List);
								}else{
									List<Destruct_Result> destruct_Result_List = ReadExcelFile
											.getDestruct_Result_ListFromOrtecExcelFile(pathfileName, forResults);
									OverallVariablesAddResults.setDestruct_Result_List(destruct_Result_List);
									Dobiv dobiv = ReadExcelFile.getDobivFromOrtecExcelFile(destruct_Result_List,
											choiceSmplCode, selectMetodStr);
									List<Dobiv> listChoisedDobiv = new ArrayList<Dobiv>();
									listChoisedDobiv.add(dobiv);
									OverallVariablesAddDobiv.setListChoisedDobiv(listChoisedDobiv);
									
								}
								sizeExcelList = OverallVariablesAddResults.getDestruct_Result_List().size();
								System.out.println("-----------------------------------" + sizeExcelList);
							}

							if (switCase == 10) {

								ReadGamaFile.getReadGamaFile(pathfileName);
								sizeGamaList = ReadGamaFile.getListNuclideMDA();
							}
							if (switCase == 16 || switCase == 3) {
								Boolean forResults = true;
								OverallVariablesAddResults.setDestruct_Result_List(
										ReadExcelFile.getDestruct_Result_ListFromExcelFile(pathfileName, forResults));
								sizeExcelList = OverallVariablesAddResults.getDestruct_Result_List().size();
							}

							if (sizeGamaList > 0 || sizeExcelList > 0) {
								OverallVariablesAddResults.setFlagIncertedFile(true);
							} else {
								OverallVariablesAddResults.setFlagIncertedFile(false);
								JOptionPane.showMessageDialog(null, "Не сте избрали коректен файл!\n"+"error 75", "Грешни данни",
										JOptionPane.ERROR_MESSAGE);

							}
						} else {
							OverallVariablesAddResults.setFlagIncertedFile(false);
							JOptionPane.showMessageDialog(null, "Не сте избрали метод!\n"+"error 81", "Грешни данни",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "Не сте избрали файл!\n"+"error 86", "Грешни данни",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});
	}

}
