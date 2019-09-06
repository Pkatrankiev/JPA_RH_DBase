package AddResultViewFunction;

import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;

import DBase_Class.Sample;

public class SampleCodeSection {
		
	public static void choiceSmplCodeListener(JLabel lbl_OI_Sample, JLabel lblSampleDescript, Choice choiceSmplCode	) {

		// Add item listener
		choiceSmplCode.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				visibleSampleInfoLbl(choiceSmplCode, lbl_OI_Sample, lblSampleDescript, choiceSmplCode);

			}

		});

		choiceSmplCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				visibleSampleInfoLbl(choiceSmplCode, lbl_OI_Sample, lblSampleDescript, choiceSmplCode);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				visibleSampleInfoLbl(choiceSmplCode, lbl_OI_Sample, lblSampleDescript, choiceSmplCode);

			}

			public void mousePressed(MouseEvent e) {

			}

		});
	}

	public static void visibleSampleInfoLbl(Choice choiceSmplCode, JLabel lbl_OI_Sample, JLabel lblSampleDescript,
			Choice choice_SmplCode) {
		if (choice_SmplCode.getSelectedItem() != "") {
			if (getName_IO_Sample(choice_SmplCode.getSelectedItem(), choice_SmplCode).length() > 50) {
				lbl_OI_Sample.setText(getName_IO_Sample(choice_SmplCode.getSelectedItem(), choice_SmplCode)
						.substring(0, 50));
			} else {
				lbl_OI_Sample
						.setText(getName_IO_Sample(choice_SmplCode.getSelectedItem(), choice_SmplCode));
			}
			if (get_Sample_Descrip(choice_SmplCode.getSelectedItem(), choice_SmplCode).length() > 50) {
				lblSampleDescript
						.setText(get_Sample_Descrip(choice_SmplCode.getSelectedItem(), choice_SmplCode)
								.substring(0, 50));
			} else {
				lblSampleDescript
						.setText(get_Sample_Descrip(choice_SmplCode.getSelectedItem(), choice_SmplCode));
			}
			lbl_OI_Sample.setVisible(true);
			lblSampleDescript.setVisible(true);
		}
	}

	public static String get_Sample_Descrip(String name_IO_Sample, Choice choiceSmplCode) {
		{

			String strChoiseSamp = choiceSmplCode.getSelectedItem();
			for (Sample samp : ÎverallVariables.getListSample()) {
				if (samp.getSample_code().equals(strChoiseSamp)) {
					name_IO_Sample = samp.getDescription_sample();

				}
			}
		}
		return name_IO_Sample;
	}

	public static String getName_IO_Sample(String name_IO_Sample, Choice choiceSmplCode) {
		String strChoiseSamp = choiceSmplCode.getSelectedItem();
		for (Sample samp : ÎverallVariables.getListSample()) {
			if (samp.getSample_code().equals(strChoiseSamp)) {
				name_IO_Sample = samp.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane();

			}
		}

		return name_IO_Sample;
	}

	static Sample getSampleObjectFromChoiceSampleCode(Choice choiceSmplCode) {
		Sample smp = null;
		for (Sample samp : ÎverallVariables.getListSample()) {
			if (samp.getSample_code().equals(choiceSmplCode.getSelectedItem())) {
				return samp;
			}
		}
		return smp;
	}
	
	
	
	
}
