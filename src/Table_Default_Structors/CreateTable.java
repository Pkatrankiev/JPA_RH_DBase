package Table_Default_Structors;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import AddResultViewFunction.OverallVariablesAddResults;
import DBase_Class.Users;
import Table.CreateColumnTapeForReuqestTable;
import Table.OverallVariablesTableRequestList;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class CreateTable {
	public static JTable CreateDefaultTable() {

		JTable table = new JTable();
		
		new DefauiltTableMouseListener(table);
		new TableFilterHeader(table, AutoChoices.ENABLED);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = My_Default_Table_Model.Default_Data_Table_Model();
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				setValueInChoiceColumn(table);
				
				
				My_Default_Table_Model.setInvisibleColumn(table) ;
							
	

			}

		});
		return table;
	}
	
	private static void setValueInChoiceColumn(JTable table) {
		List< TableObject_Class> list_TableObject_Class = OverallVariablesTableRequestList
				.getList_TableObject_Class();
		
		for (TableObject_Class tableObject_Class : list_TableObject_Class) {
			
			if(tableObject_Class.getTipeColumn().equals("Choice")){
				System.out.println(tableObject_Class.getNumberColum());
				System.out.println(tableObject_Class.getMasiveValueForChoice().length);
				setUp_ValueInComboBox(table.getColumnModel().getColumn(tableObject_Class.getNumberColum()),
						tableObject_Class.getMasiveValueForChoice());
			}
		}
	}	
//	private static Object[] getColumnHeaderName(List<TableObject_Class> list_TableObject_Class) {
//		String[] list = new String[list_TableObject_Class.size()];
//		int i = 0;
//		for (TableObject_Class tableObject_Class : list_TableObject_Class) {
//			list[i] = tableObject_Class.getColumName_Header();
//			i++;
//		}
//		return list;
//
//	}
	public static void setUp_ValueInComboBox(TableColumn Column, String[] valueForComboBox) {
		JComboBox<?> comboBox = new JComboBox<Object>(valueForComboBox);
		comboBox.setEnabled(true);
		Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Column.setCellRenderer(renderer);
	}

}
