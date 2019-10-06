package AddDobivViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChoiceORHODobivSection {

	public static void choiceORHOListener(Choice choiceORHO) {
		for (String str : OverallVariablesAddDobiv.getList_UsersNameFamilyORHO()) {
			choiceORHO.addItem(str);
		}
		choiceORHO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceORHO.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				choiceORHO.setBackground(Color.WHITE);
			}

		});

	}
}
