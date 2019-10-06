package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChoiceORHO_Section {

	public static void addItemInChoiceORHO(Choice choiceORHO) {
		for (String str : OverallVariablesAddResults.getList_UsersNameFamilyORHO()) {
			choiceORHO.addItem(str);
		}
	}
	
	public static void ChoiceORHOListener(Choice choiceORHO) {
		addItemInChoiceORHO(choiceORHO);
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
