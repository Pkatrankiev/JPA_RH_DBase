package Table;


import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import AddResultViewFunction.OverallVariablesAddResults;

public class CreateTable {

	public static JTable CreateDefaultTable(JTableHeader header,  Boolean isEditable,
			 Object[][] dataTable) {


		JTable table = new JTable();
		
		List<TableObject_Class> list_TableObject_Class = CreateColumnTapeForReuqestTable.CreateListColumnTapeForReuqestTable();	
	

		new DefauiltTableMouseListener(table, list_TableObject_Class);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = Add_DefaultTableModel.Default_Data_TableModel(
						dataTable, isEditable);

				table.setModel(dtm);

//				setUp_Nuclide(table.getColumnModel().getColumn(nuclide_Colum), isNewRow);
//
//				setUp_ValueInComboBox(table.getColumnModel().getColumn(razm_Colum),
//						OverallVariablesAddResults.getValues_Razmernosti());
//				setUp_ValueInComboBox(table.getColumnModel().getColumn(dimen_Colum),
//						OverallVariablesAddResults.getValues_Dimension());
//				setUp_ValueInComboBox(table.getColumnModel().getColumn(TSI_Colum),
//						OverallVariablesAddResults.getMasiveTSI());
//
//				Add_DefaultTableModel.setInvisibleColumn(table, rsult_Id_Colum);

				if (OverallVariablesAddResults.getDataTable().length > 0) {
					table.setRowSelectionInterval(0, 0);
					table.requestFocus();
				}

			}

		});
		return table;
	}
	
	public static void setUp_ValueInComboBox(TableColumn Column, String[] valueForComboBox) {
		JComboBox<?> comboBox = new JComboBox<Object>(valueForComboBox);
		Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Column.setCellRenderer(renderer);
	}

}
