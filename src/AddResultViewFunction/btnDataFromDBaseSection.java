package AddResultViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Aplication.MetodyDAO;
import DBase_Class.Results;
import Table.Add_DefaultTableModel;
import WindowView.AddResultsView;

public class btnDataFromDBaseSection {

	public static void btnDataFromDBaseListener(AddResultsView addResultsViewWithTable, JPanel basic_panel, JButton btnDataFromDBase, Choice choiceMetody, Choice choiceDobiv, 
			Choice choiceSmplCode, Choice choicePokazatel, Choice choiceOIR, Choice choiceORHO, JLabel lbl_StoinostiFromDobiv) {
		
		btnDataFromDBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				OverallVariablesAddResults.setValueFromDBase(true);
				
				AddResultViewMetods.setWaitCursor(basic_panel);
				if (!choiceMetody.getSelectedItem().trim().isEmpty()) {
					OverallVariablesAddResults.setSelectedMetod ( MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));
					DobivSection.setValueInChoiceDobiv(OverallVariablesAddResults.getSelectedMetod(), choiceDobiv, lbl_StoinostiFromDobiv, false);

					Results[] masiveResultsForChoiceSample = AddResultViewMetods.creadMasiveFromResultsObjects_ChoiseSample(
							SampleCodeSection.getSampleObjectFromChoiceSampleCode(choiceSmplCode), choicePokazatel);
					if (masiveResultsForChoiceSample.length > 0) {
						if (masiveResultsForChoiceSample[0].getUser_measur() != null) {
							String str = masiveResultsForChoiceSample[0].getUser_measur().getName_users() + " "
									+ masiveResultsForChoiceSample[0].getUser_measur().getFamily_users();
							choiceOIR.select(str);
						}
						if (masiveResultsForChoiceSample[0].getUser_chim_oper() != null) {
							String str = masiveResultsForChoiceSample[0].getUser_chim_oper().getName_users() + " "
									+ masiveResultsForChoiceSample[0].getUser_chim_oper().getFamily_users();
							choiceORHO.select(str);
						}

						if (masiveResultsForChoiceSample[0].getDobiv() != null) {
							String str = masiveResultsForChoiceSample[0].getDobiv().getCode_Standart();
							choiceDobiv.select(str);
							lbl_StoinostiFromDobiv
							.setText(DobivSection.generate_strStoinostiDobiv_Nuclide(choiceDobiv));
						}

					}
//					Add_DefaultTableModel.setInChoiceOIR(choiceOIR);
					Add_DefaultTableModel.setFromDBase(true);
					AddResultViewMetods.startViewtablePanel(addResultsViewWithTable,basic_panel, masiveResultsForChoiceSample);
					AddResultViewMetods.setDefaultCursor(basic_panel);
				}
			}

		});

	}
	
	
	
	
}
