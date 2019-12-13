package Table;

import java.awt.Choice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import AddDobivViewFunction.AddDobivViewMetods;
import AddDobivViewFunction.OverallVariablesAddDobiv;
import AddResultViewFunction.AddResultViewMetods;
import AddResultViewFunction.SampleCodeSection;
import Aplication.NuclideDAO;
import DBase_Class.Nuclide;
import DBase_Class.Sample;
import WindowView.DatePicker;

public class Add_TableMouseListener {

	public Add_TableMouseListener(JTable table, String[] tipeColumn, int actv_value_Colum, int mda_Colum, 
			int nuclide_Colum, Choice choiceSmplCode) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());

				switch (tipeColumn[col]) {

				case "DatePicker":
					if (SwingUtilities.isRightMouseButton(e)) {
						String date_choice = getDateFromDatePicker(table, col);
						table.setValueAt(date_choice, row, col);
					}
					break;

				case "Check":
					checkResults(table, actv_value_Colum, mda_Colum, nuclide_Colum, choiceSmplCode);
					break;
				}

			}

			private void checkResults(JTable table, int actv_value_Colum, int mda_Colum, int nuclide_Colum,
					Choice choiceSmplCode) {
				
				double actv_value = Double
						.parseDouble((table.getValueAt(table.getSelectedRow(), actv_value_Colum)).toString());
				Nuclide nuclide = NuclideDAO.getValueNuclideBySymbol(
						table.getValueAt(table.getSelectedRow(), nuclide_Colum).toString());
				if(choiceSmplCode!=null){
				double mda = Double.parseDouble((table.getValueAt(table.getSelectedRow(), mda_Colum)).toString());
				Sample samp = SampleCodeSection.getSampleObjectFromChoiceSampleCode(choiceSmplCode);
				AddResultViewMetods.checkResultsValueFrame(nuclide, samp, actv_value, mda);
				}else{
					AddDobivViewMetods.checkValueFrame(nuclide, OverallVariablesAddDobiv.getSelectedMetod(), actv_value);
				}
			}

		});
	}
	

	
	static String getDateFromDatePicker(JTable table, int col) {
		 String date = table.getValueAt(table.getSelectedRow(), col).toString();
		final JFrame f = new JFrame();
		DatePicker dPicer = new DatePicker(f, false, date);
		String date_choice = dPicer.setPickedDate(false);
		return date_choice;
	}

	
	
}
