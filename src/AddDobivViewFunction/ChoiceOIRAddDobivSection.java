package AddDobivViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChoiceOIRAddDobivSection {

	
	public static void choiceOIRListener(Choice choiceOIR) {
		for (String str : OverallVariablesAddDobiv.getList_UsersNameFamilyOIR()) {
			choiceOIR.addItem(str);
		}
		choiceOIR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceOIR.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				choiceOIR.setBackground(Color.WHITE);
			}

		});
	}
}
