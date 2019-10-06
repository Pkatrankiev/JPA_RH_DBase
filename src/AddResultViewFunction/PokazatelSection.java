package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import DBase_Class.IzpitvanPokazatel;

public class PokazatelSection {
	
	public static void PokazatelSectionListener(Choice choicePokazatel, Choice choiceSmplCode){
	choicePokazatel.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			choicePokazatel.setBackground(Color.WHITE);
			if (OverallVariablesAddResults.getChoiseRequest() != null) {
				
				if (SampleCodeSection.getSampleObjectFromChoiceSampleCode(choiceSmplCode) != null) {
					if (OverallVariablesAddResults.getFlagNotReadListPokazatel()) {
						System.out.println("-----------  ÎverallVariables.getFlagNotReadListPokazatel()");
						choicePokazatel.removeAll();
						for (IzpitvanPokazatel pokazat : OverallVariablesAddResults.getListPokazatel()) {
							choicePokazatel.add(pokazat.getPokazatel().getName_pokazatel());
							OverallVariablesAddResults.setFlagNotReadListPokazatel ( false);
						}
					}
				}
				
			}

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {

		}

	});
	
	}
	
	
	
	
}
