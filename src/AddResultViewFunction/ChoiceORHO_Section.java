package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Aplication.MetodyDAO;
import DBase_Class.Metody;
import WindowView.AddDobivViewWithTable;

public class ChoiceORHO_Section {

	public static void addItemInChoiceORHO(Choice choiceORHO) {
		for (String str : ÎverallVariables.getList_UsersNameFamilyORHO()) {
			choiceORHO.addItem(str);
		}
	}
	
	public static void ChoiceORHOListener(Choice choiceORHO) {
		choiceORHO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				choiceORHO.setBackground(Color.WHITE);

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

			}

		});
	}
	

	
}
