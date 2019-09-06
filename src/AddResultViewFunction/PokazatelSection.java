package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Sample;

public class PokazatelSection {
	
	public static void PokazatelSectionListener(Choice choicePokazatel, Choice choiceSmplCode){
	choicePokazatel.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			choicePokazatel.setBackground(Color.WHITE);
			if (�verallVariables.getChoiseRequest() != null) {
				if (SampleCodeSection.getSampleObjectFromChoiceSampleCode(choiceSmplCode) != null) {
					if (�verallVariables.getFlagNotReadListPokazatel()) {
						choicePokazatel.removeAll();
						for (IzpitvanPokazatel pokazat : �verallVariables.getListPokazatel()) {
							choicePokazatel.add(pokazat.getPokazatel().getName_pokazatel());
							�verallVariables.setFlagNotReadListPokazatel ( false);
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
