package AddResultViewFunction;

import java.awt.Choice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;


public class LabelStoinostiFromDobivSection {

	public static void LabelStoinFromDobivListener(JLabel lbl_StoinostiFromDobiv, Choice choiceDobiv) {
		lbl_StoinostiFromDobiv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				String stoinostDobiv = DobivSection.generate_strStoinostiDobiv_Nuclide(choiceDobiv);
				lbl_StoinostiFromDobiv.setText(stoinostDobiv);
			}

			
			@Override
			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {

			}

		});
	}
	
}
