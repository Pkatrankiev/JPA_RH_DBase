package AddDobivViewFunction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import WindowView.TranscluentWindow;

public class addNewRowInTableAddDobiv {
	
	public static void btmAddRowInTableAddDobivListener(JPanel basic_panel, JButton btnAddRow) {
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TranscluentWindow round = new TranscluentWindow();
				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {

						AddNewRowIn_dataTable();
						Boolean isNewRow = true;
//						ViewTableInPanel(basic_panel, round, isNewRow);
					}
				});
				thread.start();

			}

		});
	}

	private static void AddNewRowIn_dataTable() {
		OverallVariablesAddDobiv.setListNuclideToMetod( AddDobivViewMetods.getListNuclideToMetod(OverallVariablesAddDobiv.getSelectedMetod()));
		OverallVariablesAddDobiv.setMasive_NuclideToMetod ( AddDobivViewMetods.createMasiveStringSimbolNuklide(OverallVariablesAddDobiv.getListNuclideToMetod()));

		int countDataTable = OverallVariablesAddDobiv.getDataTable().length;
		Object[][] newTable = new Object[countDataTable + 1][AddDobivViewMetods.getTbl_Colum()];
		for (int i = 0; i < countDataTable; i++) {
			newTable[i] = OverallVariablesAddDobiv.getDataTable()[i];
		}
		
		
		newTable[countDataTable] = AddDobivViewMetods.rowWithoutValueDobivs(OverallVariablesAddDobiv.getMasive_NuclideToMetod()[0]);
		OverallVariablesAddDobiv.setDataTable(new Object[newTable.length][AddDobivViewMetods.getTbl_Colum()]);
		for (int i = 0; i < newTable.length; i++) {
			OverallVariablesAddDobiv.getDataTable()[i] = newTable[i];
		}
	}
}
