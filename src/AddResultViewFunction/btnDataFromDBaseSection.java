package AddResultViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Aplication.MetodyDAO;
import DBase_Class.Results;
import WindowView.AddResultsView;

public class btnDataFromDBaseSection {

	public static void btnDataFromDBaseListener(AddResultsView addResultsViewWithTable, JPanel basic_panel, JButton btnDataFromDBase, Choice choiceMetody, Choice choiceDobiv, 
			Choice choiceSmplCode, Choice choicePokazatel, Choice choiceOIR, Choice choiceORHO, JLabel lbl_StoinostiFromDobiv) {
		
		btnDataFromDBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!choiceMetody.getSelectedItem().trim().isEmpty()) {
					OverallVariables.setSelectedMetod ( MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));
					DobivSection.setValueInChoiceDobiv(OverallVariables.getSelectedMetod(), choiceDobiv, lbl_StoinostiFromDobiv, false);

					Results[] masiveResultsForChoiceSample = AddresultViewMetods.creadMasiveFromResultsObjects_ChoiseSample(
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
					OverallVariables.setFromDBase(true);
					AddresultViewMetods.startViewtablePanel(addResultsViewWithTable,basic_panel, masiveResultsForChoiceSample);

				}
			}

		});

	}
	
	
	
	
}
