package AddResultViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import WindowView.AddResultsView;

public class btnAddRowSection {
	
	public static void btmAddRowListener(AddResultsView addResultsViewWithTable, JButton btnAddRow, Choice choicePokazatel) {
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddresultViewMetods.AddNewRowIn_dataTable(choicePokazatel);
				Boolean isNewRow = true;
				AddResultsView.ViewTableInPanel( addResultsViewWithTable,isNewRow);
			}

		});
	}
	

	
	

	
}
