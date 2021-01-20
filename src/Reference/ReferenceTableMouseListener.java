package Reference;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import DBase_Class.Results;

public class ReferenceTableMouseListener {

	public ReferenceTableMouseListener( JTable table, Object[][] masiveValueDataTable, String separatorInColumnName) {
		

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@SuppressWarnings("unused")
			public void mousePressed(MouseEvent e) {

				if (SwingUtilities.isLeftMouseButton(e)) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int selectedRow = getSelectedModelRow(table);

					if (selectedRow != -1) {
						selectedRow++;
						String nameSelectedColumn = table.getColumnModel().getColumn(table.getSelectedColumn())
								.getHeaderValue().toString();
						
						int selectedColumnIndex = Integer.parseInt(nameSelectedColumn.substring(0, nameSelectedColumn.indexOf(separatorInColumnName)));
						
							if(selectedColumnIndex>0){
								String value = "";
								
						System.out.println(nameSelectedColumn+"  "+selectedRow+" + "+selectedColumnIndex);
						if (masiveValueDataTable[selectedRow][selectedColumnIndex].getClass().getName().equals(Results.class.getName())) {
							Results result = (Results) masiveValueDataTable[selectedRow][selectedColumnIndex];
							value = result.getMda().toString();
							new InfoResultMiniFrame(new JFrame(), "",  result) ;
						} else {
							value = ((String) masiveValueDataTable[selectedRow][selectedColumnIndex]);
						}
						System.out.println(value);
							}
						}
				}
					
			}

		});
	}

	public static int getSelectedModelRow(JTable table) {
		return table.convertRowIndexToModel(table.getSelectedRow());
	}
	
	
	
	public static int getColumnIndex(JTable table, String columnTitle) {
		int columnCount = table.getColumnCount();
		for (int column = 0; column < columnCount; column++) {
			if (table.getColumnName(column).equalsIgnoreCase(columnTitle)) {
				return column;
			}
		}

		return -1;
	}
}
