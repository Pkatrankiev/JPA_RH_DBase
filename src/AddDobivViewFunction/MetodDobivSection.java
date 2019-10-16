package AddDobivViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;

import Aplication.MetodyDAO;
import DBase_Class.Metody;

public class MetodDobivSection {

	
	public static void choiceMetodyListener(Choice choiceMetody, JLabel lblNameMetod) {
		
		for (Metody metod : OverallVariablesAddDobiv.getListMetody()) {
			choiceMetody.add(metod.getCode_metody());
		}
		
		createAllListsForNuclide(choiceMetody);
		
		choiceMetody.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				setTextInMetodLabel( choiceMetody, lblNameMetod);
				createAllListsForNuclide(choiceMetody);
			}

		});

		choiceMetody.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceMetody.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setTextInMetodLabel( choiceMetody, lblNameMetod);
				OverallVariablesAddDobiv.setSelectedMetod ( MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));

				// masive_NuclideToPokazatel =
				// createMasiveStringSimbolNuklide(listNuclideToMetod);
			}

			public void mousePressed(MouseEvent e) {
				choiceMetody.setBackground(Color.WHITE);
			}

		});
	}

	private static void setTextInMetodLabel(Choice choiceMetody, JLabel lblNameMetod) {
		if (choiceMetody.getSelectedItem() != null) {
			OverallVariablesAddDobiv.setSelectedMetod ( MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));
			lblNameMetod.setText(OverallVariablesAddDobiv.getSelectedMetod().getName_metody());
		}
	}


	public static void createAllListsForNuclide(Choice choiceMetody ) {
		OverallVariablesAddDobiv.setSelectedMetod ( MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));
		OverallVariablesAddDobiv.setListSimbolBasikNulide ( AddDobivViewMetods.getListSimbolBasikNulideToMetod(OverallVariablesAddDobiv.getSelectedMetod()));
		OverallVariablesAddDobiv.setMasuveSimbolBasikNuclide( creatMasiveSimbolNuclideToMrtod(OverallVariablesAddDobiv.getListSimbolBasikNulide()));
		OverallVariablesAddDobiv.setListNuclideToMetod ( AddDobivViewMetods.getListNuclideToMetod(OverallVariablesAddDobiv.getSelectedMetod()));
		OverallVariablesAddDobiv.setMasive_NuclideToMetod (AddDobivViewMetods.createMasiveStringSimbolNuklide(OverallVariablesAddDobiv.getListNuclideToMetod()));
	}	
	
	private static String[] creatMasiveSimbolNuclideToMrtod(List<String> listSimbolBasikNulide) {
		String[] masiveSimbolNuclide = new String[listSimbolBasikNulide.size()];
		int i = 0;
		for (String nuclide_to_Pokazatel : listSimbolBasikNulide) {
			masiveSimbolNuclide[i] = nuclide_to_Pokazatel;
			i++;
		}
		return masiveSimbolNuclide;
	}

	
	
	
}
