package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import Aplication.MetodyDAO;
import Aplication.Metody_to_PokazatelDAO;
import DBase_Class.Metody;
import DBase_Class.Metody_to_Pokazatel;
import WindowView.AddDobivViewWithTable;

public class MetodSection {

	public static void choiceMetodyListener(Choice choiceMetody, JLabel lblNameMetod, Choice choicePokazatel, Choice choiceDobiv, JLabel lbl_StoinostiFromDobiv) {
		choiceMetody.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				setVisiblelblNameMetody(lblNameMetod, choiceMetody);
				String strChoisedmetod = choiceMetody.getSelectedItem();
				OverallVariables.setSelectedMetod (MetodyDAO.getValueList_MetodyByCode(strChoisedmetod));
				Boolean clearItems = true; 
				DobivSection.setValueInChoiceDobiv(OverallVariables.getSelectedMetod(), choiceDobiv, lbl_StoinostiFromDobiv, clearItems);
				OverallVariables.setListSimbolBasikNulideToMetod ( AddDobivViewWithTable.getListSimbolBasikNulideToMetod(OverallVariables.getSelectedMetod()));
			}
		});

		choiceMetody.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				choiceMetody.setBackground(Color.WHITE);
				if (!choicePokazatel.getSelectedItem().trim().isEmpty()) {
					if (OverallVariables.getFlagNotReadListMetody()) {
						choiceMetody.removeAll();
						if (getListMetodyFormMetody_To_Pokaztel(choicePokazatel).isEmpty()) {
							choiceMetody.add("");
						}
						for (Metody metod : getListMetodyFormMetody_To_Pokaztel(choicePokazatel)) {
							choiceMetody.add(metod.getCode_metody());
							OverallVariables.setFlagNotReadListMetody (false);
						}

						setVisiblelblNameMetody(lblNameMetod, choiceMetody);
					}
					DobivSection.setValueInChoiceDobiv(MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()), choiceDobiv, lbl_StoinostiFromDobiv, false);
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {

				if (!choiceMetody.getSelectedItem().trim().isEmpty()) {
					String strChoisedmetod = choiceMetody.getSelectedItem();
					OverallVariables.setSelectedMetod ( MetodyDAO.getValueList_MetodyByCode(strChoisedmetod));
					setVisiblelblNameMetody(lblNameMetod, choiceMetody);
					OverallVariables.setListSimbolBasikNulideToMetod ( AddDobivViewWithTable.getListSimbolBasikNulideToMetod(OverallVariables.getSelectedMetod()));
					OverallVariables.setListNucToPok ( AddresultViewMwetods.getListNuklideToPokazatel(choicePokazatel));
					OverallVariables.setListSimbolBasikNulide ( AddresultViewMwetods.getListSimbolBasikNulideFNuclideToPokazatel(OverallVariables.getListNucToPok()));
					OverallVariables.setMasuveSimbolNuclide(  AddresultViewMwetods.getMasiveSimbolNuclideToPokazatel(OverallVariables.getListNucToPok()));

					// setValueInChoiceDobiv(selectedMetod);
				}

			}

			public void mousePressed(MouseEvent e) {

			}

		});
	}
	
	
	
	private static void setVisiblelblNameMetody(JLabel lblNameMetod, Choice choiceMetody ) {

		if (choiceMetody.getSelectedItem() != "") {
			Metody selectedMetod = MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem());
			lblNameMetod.setText(selectedMetod.getName_metody());
			lblNameMetod.setVisible(true);
		}
	}

	private static List<Metody> getListMetodyFormMetody_To_Pokaztel(Choice choicePokazatel) {
		List<Metody_to_Pokazatel> list = Metody_to_PokazatelDAO
				.getListMetody_to_PokazatelByPokazatel(AddresultViewMwetods.getPokazatelObjectFromChoicePokazatel(choicePokazatel));
		List<Metody> listMetody = new ArrayList<Metody>();
		for (Metody_to_Pokazatel metody_to_Pokazatel : list) {
			listMetody.add(metody_to_Pokazatel.getMetody());
		}
		return listMetody;
	}

	
	

	


	
	
}
