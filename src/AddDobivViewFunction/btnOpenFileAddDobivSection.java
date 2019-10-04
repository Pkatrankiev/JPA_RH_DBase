package AddDobivViewFunction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import ExcelFilesFunction.Destruct_Result;
import ExcelFilesFunction.ReadExcelFile;
import WindowView.ReadGamaFile;

public class btnOpenFileAddDobivSection {

	public static void btnOpenFileListener(JButton btnOpenFile, JTextField txtBasicValueResult) {
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				f.showOpenDialog(null);
				List<Destruct_Result> destruct_Result_List = new ArrayList<Destruct_Result>();
				try {
					txtBasicValueResult.setText((f.getSelectedFile()).toString());
					if(OverallVariablesAddDobiv.getSelectedMetod().getCode_metody().equals("10")){
					ReadGamaFile.getReadGamaFile(f.getSelectedFile().toString());
					}else{
						destruct_Result_List = ReadExcelFile.getDestruct_Result_ListFromExcelFile(f.getSelectedFile().toString());
					}
					if (ReadGamaFile.getListNuclideMDA() > 0 || destruct_Result_List.size() > 0) {
						OverallVariablesAddDobiv.setFlagIncertedFile( true);
					} else {
						OverallVariablesAddDobiv.setFlagIncertedFile( false);
					}
						
					
					
				} catch (NullPointerException e2) {

				}

			}
		});
	}

}
