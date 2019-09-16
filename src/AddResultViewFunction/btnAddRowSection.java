package AddResultViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import WindowView.AddResultsViewWithTable;

public class btnAddRowSection {
	
	public static void btmAddRowListener(AddResultsViewWithTable addResultsViewWithTable, JButton btnAddRow, Choice choicePokazatel) {
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddresultViewMwetods.AddNewRowIn_dataTable(choicePokazatel);
				Boolean isNewRow = true;
				AddResultsViewWithTable.ViewTableInPanel( addResultsViewWithTable,isNewRow);
			}

		});
	}
	

	
	

	
}
