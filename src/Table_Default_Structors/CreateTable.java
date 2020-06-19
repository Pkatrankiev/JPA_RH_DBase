package Table_Default_Structors;



import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import Table.RequestTableList_OverallVariables;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class CreateTable {
	private static List< TableObject_Class> list_TableObject_Class;
	private static String clickToChoice = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("clickToChoice");
	
	public static JTable CreateDefaultTable(String tipe_Table) {
		
switch (tipe_Table) {
		
		case "request":
			list_TableObject_Class = RequestTableList_OverallVariables
			.getList_TableObject_Class();
			break;
		
		}

		JTable table = new JTable();
		
		new DefauiltTableMouseListener(table);
		new TableFilterHeader(table, AutoChoices.ENABLED);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = My_Default_Table_Model.Default_Data_Table_Model();
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				setValueInChoiceColumn(table, list_TableObject_Class);
								
				My_Default_Table_Model.setInvisibleColumn(table) ;
			
			}

		});
		return table;
	}
	
	private static void setValueInChoiceColumn(JTable table, List< TableObject_Class> list_TableObject_Class) {
		
		
		for (TableObject_Class tableObject_Class : list_TableObject_Class) {
			
			if(tableObject_Class.getTipeColumn().equals("Choice")){
						setUp_ValueInComboBox(table.getColumnModel().getColumn(tableObject_Class.getNumberColum()),
						tableObject_Class.getMasiveValueForChoice());
			}
		}
	}	

	public static void setUp_ValueInComboBox(TableColumn Column, String[] valueForComboBox) {
		JComboBox<?> comboBox = new JComboBox<Object>(valueForComboBox);
		comboBox.setEnabled(true);
		Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText(clickToChoice);
		Column.setCellRenderer(renderer);
	}

}
