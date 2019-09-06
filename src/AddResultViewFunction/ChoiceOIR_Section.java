package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChoiceOIR_Section {

	
	public static void setItemInChoiceOIR(Choice choiceOIR) {
		for (String str : ÎverallVariables.getList_UsersNameFamilyOIR()) {
			choiceOIR.addItem(str);
		}
	}
	
	public static void choiceOIRListener(Choice choiceOIR) {
		choiceOIR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				choiceOIR.setBackground(Color.WHITE);

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

			}

		});
	}	
	
	
	
}
