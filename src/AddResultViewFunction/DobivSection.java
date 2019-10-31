package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import AddDobivViewFunction.OverallVariablesAddDobiv;
import Aplication.DobivDAO;
import DBase_Class.Dobiv;
import DBase_Class.Metody;

public class DobivSection {

	
	public static  void choiceDobivListener(Choice choiceDobiv, JLabel lbl_StoinostiFromDobiv) {
		choiceDobiv.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (choiceDobiv.getSelectedItem() == null)
					setValueInChoiceDobiv(OverallVariablesAddResults.getSelectedMetod(), choiceDobiv,lbl_StoinostiFromDobiv, false);
				lbl_StoinostiFromDobiv
						.setText(generate_strStoinostiDobiv_Nuclide(choiceDobiv));

			}

		});

		choiceDobiv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceDobiv.setBackground(Color.WHITE);
				if (choiceDobiv.getSelectedItem().trim().isEmpty()) {
					setValueInChoiceDobiv(OverallVariablesAddResults.getSelectedMetod(), choiceDobiv, lbl_StoinostiFromDobiv, false);
				lbl_StoinostiFromDobiv
						.setText(generate_strStoinostiDobiv_Nuclide(choiceDobiv));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				if (choiceDobiv.getSelectedItem().trim().isEmpty()) {
					setValueInChoiceDobiv(OverallVariablesAddResults.getSelectedMetod(), choiceDobiv,lbl_StoinostiFromDobiv, false);
				lbl_StoinostiFromDobiv
						.setText(generate_strStoinostiDobiv_Nuclide(choiceDobiv));
				}  
			}

		});
	}
	
	static void setValueInChoiceDobiv(Metody selectedMetod, Choice choiceDobiv, JLabel lbl_StoinostiFromDobiv, Boolean clearItems) {
		if (clearItems || choiceDobiv.getSelectedItem().trim().isEmpty()) {
			
		choiceDobiv.removeAll();
		choiceDobiv.addItem("");
		
		OverallVariablesAddResults.setListDobivFromMetod ( DobivDAO.getListDobivByMetody(selectedMetod));
		for (Dobiv str : OverallVariablesAddResults.getListDobivFromMetod()) {
			choiceDobiv.addItem(str.getCode_Standart());
		}
		lbl_StoinostiFromDobiv
		.setText(generate_strStoinostiDobiv_Nuclide(choiceDobiv));	
		}
	}
	
	static void setValueInChoiceDobivFromORTECFile(Metody selectedMetod, Choice choiceDobiv, JLabel lbl_StoinostiFromDobiv) {
					
		choiceDobiv.removeAll();
//		choiceDobiv.addItem("");
		String selectItemStr = OverallVariablesAddDobiv.getListChoisedDobiv().get(0).getCode_Standart();
		choiceDobiv.addItem(selectItemStr);
		OverallVariablesAddResults.setListDobivFromMetod ( DobivDAO.getListDobivByMetody(selectedMetod));
		for (Dobiv str : OverallVariablesAddResults.getListDobivFromMetod()) {
			choiceDobiv.addItem(str.getCode_Standart());
		}
		choiceDobiv.select(selectItemStr);
		lbl_StoinostiFromDobiv.setText(generate_strStoinostiDobiv_Nuclide(choiceDobiv));	

	}
	
	static String generate_strStoinostiDobiv_Nuclide(Choice choiceDobiv) {
		String strStoinostiDobiv_Nuclide = "";
		if (!choiceDobiv.getSelectedItem().trim().isEmpty()) {
			for (Dobiv dobiv : DobivDAO.getList_DobivByCode_Standart(choiceDobiv.getSelectedItem())) {
				strStoinostiDobiv_Nuclide += dobiv.getNuclide().getSymbol_nuclide() + " " + dobiv.getValue_result()
						+ "% , ";
			}
			if(strStoinostiDobiv_Nuclide.trim().isEmpty()){
				 Dobiv dobiv = OverallVariablesAddDobiv.getListChoisedDobiv().get(0);
				 strStoinostiDobiv_Nuclide += dobiv.getNuclide().getSymbol_nuclide() + " " + dobiv.getValue_result()
					+ "% , ";
			}
			
			if (!strStoinostiDobiv_Nuclide.isEmpty())
				strStoinostiDobiv_Nuclide = strStoinostiDobiv_Nuclide.substring(0,
						strStoinostiDobiv_Nuclide.length() - 2);
		}
		return strStoinostiDobiv_Nuclide;
	}
	

}
