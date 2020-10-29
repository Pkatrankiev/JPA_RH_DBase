package DefaultTableList;

import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import Table_Request.RequestTableMouseListener;
import Table_Results.ResultsTableMouseListener;
import Table_Sample.SampleTableMouseListener;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class CreateTable {
	private static List<TableObject_Class> list_TableObject_Class;
	private static String clickToChoice = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
			.get("clickToChoice");

	public static JTable CreateDefaultTable_Old(ViewTableList viewTableList,TableList_OverallVariables objectTableList_OverallVariables, String tipe_Table) {
		JTable table = new JTable();
		list_TableObject_Class = TableList_OverallVariables.getList_TableObject_Class();
		
		switch (tipe_Table) {

		case "request":
			new RequestTableMouseListener( viewTableList, objectTableList_OverallVariables,table);
			break;
		case "Sample":
			new SampleTableMouseListener(objectTableList_OverallVariables,table);
			break;
			
			
		case "Results":
			new ResultsTableMouseListener(objectTableList_OverallVariables, table);
			break;

		}

		new TableFilterHeader(table, AutoChoices.ENABLED);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = My_Default_Table_Model.Default_Data_Table_Model(objectTableList_OverallVariables);
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				setValueInChoiceColumn(table, list_TableObject_Class);

				System.out.println("------------------------------My_Default_Table_Model  Masive_Invizible_Colum "
						+ TableList_OverallVariables.getMasive_Invizible_Colum().length);

				My_Default_Table_Model.setInvisibleColumn(table);

			}

		});
		return table;
	}

	@SuppressWarnings("static-access")
	public static JTable CreateDefaultTable(ViewTableList viewTableList, TableList_OverallVariables objectTableList_OverallVariables) {
		JTable table = new JTable();
		
		list_TableObject_Class = objectTableList_OverallVariables.getList_TableObject_Class();
	
		System.out.println("****************************************** " + list_TableObject_Class.size());
		
		
		switch (objectTableList_OverallVariables.getTipe_Table()) {

		case "request":
			new RequestTableMouseListener( viewTableList, objectTableList_OverallVariables, table);
			break;
			
		case "Sample":
			new SampleTableMouseListener(objectTableList_OverallVariables,table);
			break;
			
		case "Results":
			
			new ResultsTableMouseListener(objectTableList_OverallVariables,table);
			break;

		}

		new TableFilterHeader(table, AutoChoices.ENABLED);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = My_Default_Table_Model.Default_Data_Table_Model( objectTableList_OverallVariables);
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				setValueInChoiceColumn(table, list_TableObject_Class);
			
				My_Default_Table_Model.setInvisibleColumn(table);

			}

		});
		return table;
	}
	
	private static void setValueInChoiceColumn(JTable table, List<TableObject_Class> list_TableObject_Class) {

		for (TableObject_Class tableObject_Class : list_TableObject_Class) {

			if (tableObject_Class.getTipeColumn().equals("Choice")) {
				setUp_ValueInComboBox(table.getColumnModel().getColumn(tableObject_Class.getNumberColum()),
						tableObject_Class.getMasiveValueForChoice());
			}
		}
	}

	public static void setUp_ValueInComboBox(TableColumn Column, String[] valueForComboBox) {
		System.out.println("****************************************** " + valueForComboBox[0]);
		JComboBox<?> comboBox = new JComboBox<Object>(valueForComboBox);
		comboBox.setEnabled(true);
		Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText(clickToChoice);
		Column.setCellRenderer(renderer);
	}

}
