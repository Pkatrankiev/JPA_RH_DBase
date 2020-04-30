package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import DBase_Class.IzpitvanPokazatel;

public class PokazatelSection {
	
	public static void PokazatelSectionListener(Choice choicePokazatel, Choice choiceSmplCode){
		
		choicePokazatel.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				AddResultViewMetods.setListNuclideToMetodAndToPokaz(choicePokazatel);
				OverallVariablesAddResults.setFlagNotReadListMetody (true);
		}
		});
		
	choicePokazatel.addMouseListener(new MouseAdapter() {
		
		
		
		@Override
		public void mouseEntered(MouseEvent e) {
			choicePokazatel.setBackground(Color.WHITE);
			if (OverallVariablesAddResults.getChoiseRequest() != null) {
				
				if (SampleCodeSection.getSampleObjectFromChoiceSampleCode(choiceSmplCode) != null) {
					if (OverallVariablesAddResults.getFlagNotReadListPokazatel()) {
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
			AddResultViewMetods.setListNuclideToMetodAndToPokaz(choicePokazatel);

		}

		public void mousePressed(MouseEvent e) {

		}

	});
	
	}
	
	
	
	
}
