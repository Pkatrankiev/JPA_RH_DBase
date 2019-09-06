package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import Aplication.DobivDAO;
import DBase_Class.Dobiv;
import DBase_Class.Metody;

public class DobivSection {

	
	public static  void choiceDobivListener(Choice choiceDobiv, JLabel lbl_StoinostiFromDobiv) {
		choiceDobiv.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (choiceDobiv.getSelectedItem() == null)
					setValueInChoiceDobiv(ÎverallVariables.getSelectedMetod(), choiceDobiv);
				lbl_StoinostiFromDobiv
						.setText(generate_strStoinostiDobiv_Nuclide(choiceDobiv.getSelectedItem().trim(), choiceDobiv));

			}

		});

		choiceDobiv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceDobiv.setBackground(Color.WHITE);
				if (choiceDobiv.getSelectedItem() != null) {
					setValueInChoiceDobiv(ÎverallVariables.getSelectedMetod(), choiceDobiv);
				lbl_StoinostiFromDobiv
						.setText(generate_strStoinostiDobiv_Nuclide(choiceDobiv.getSelectedItem().trim(), choiceDobiv));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				if (choiceDobiv.getSelectedItem() != null) {
					setValueInChoiceDobiv(ÎverallVariables.getSelectedMetod(), choiceDobiv);
				lbl_StoinostiFromDobiv
						.setText(generate_strStoinostiDobiv_Nuclide(choiceDobiv.getSelectedItem().trim(), choiceDobiv));
				}
			}

		});
	}
	
	static void setValueInChoiceDobiv(Metody selectedMetod, Choice choiceDobiv) {
		choiceDobiv.removeAll();
		choiceDobiv.addItem("");
		ÎverallVariables.setListDobivFromMetod ( DobivDAO.getListDobivByMetody(selectedMetod));
		for (Dobiv str : ÎverallVariables.getListDobivFromMetod()) {
			choiceDobiv.addItem(str.getCode_Standart());
		}
	}
	
	static String generate_strStoinostiDobiv_Nuclide(String strSelectedDobiv, Choice choiceDobiv) {
		String strStoinostiDobiv_Nuclide = "";
		if (!strSelectedDobiv.isEmpty()) {
			for (Dobiv dobiv : DobivDAO.getList_DobivByCode_Standart(choiceDobiv.getSelectedItem())) {
				strStoinostiDobiv_Nuclide += dobiv.getNuclide().getSymbol_nuclide() + " - " + dobiv.getValue_result()
						+ "% , ";
			}
			if (!strStoinostiDobiv_Nuclide.isEmpty())
				strStoinostiDobiv_Nuclide = strStoinostiDobiv_Nuclide.substring(0,
						strStoinostiDobiv_Nuclide.length() - 2);
		}

		return strStoinostiDobiv_Nuclide;
	}

	
}
