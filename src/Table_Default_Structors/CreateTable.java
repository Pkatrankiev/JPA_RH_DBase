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
import Table.RequestTableMouseListener;
import Table_Results.DefauiltResultsTableMouseListener;
import Table_Results.ResultsTableList_Functions;
import Table_Results.ResultsTableList_OverallVariables;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class CreateTable {
	private static List<TableObject_Class> list_TableObject_Class;
	private static String clickToChoice = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
			.get("clickToChoice");

	public static JTable CreateDefaultTable(ResultsTableList_OverallVariables objectTableList_OverallVariables, String tipe_Table) {
		JTable table = new JTable();
	
		
		switch (tipe_Table) {

		case "request":
			list_TableObject_Class = RequestTableList_OverallVariables.getList_TableObject_Class();
			new DefauiltRequestTableMouseListener(table);
			break;

		case "Results":
			list_TableObject_Class = ResultsTableList_OverallVariables.getList_TableObject_Class();
			new DefauiltResultsTableMouseListener(objectTableList_OverallVariables, table);
			break;

		}

		new TableFilterHeader(table, AutoChoices.ENABLED);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = My_Default_Table_Model.Default_Data_Table_Model(tipe_Table);
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				setValueInChoiceColumn(table, list_TableObject_Class);

				System.out.println("------------------------------My_Default_Table_Model  Masive_Invizible_Colum "
						+ ResultsTableList_OverallVariables.getMasive_Invizible_Colum().length);

				My_Default_Table_Model.setInvisibleColumn(table);

			}

		});
		return table;
	}

	@SuppressWarnings("static-access")
	public static JTable CreateDefaultTable_Test(ResultsTableList_OverallVariables objectTableList_OverallVariables) {
		JTable table = new JTable();
		
		list_TableObject_Class = objectTableList_OverallVariables.getList_TableObject_Class();
		
		
		switch (objectTableList_OverallVariables.getTipe_Table()) {

		case "request":
			new RequestTableMouseListener(objectTableList_OverallVariables, table);
			break;

		case "Results":
			
			new DefauiltResultsTableMouseListener(objectTableList_OverallVariables,table);
			break;

		}

		new TableFilterHeader(table, AutoChoices.ENABLED);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = My_Default_Table_Model.Default_Data_Table_Model_Test( objectTableList_OverallVariables);
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				setValueInChoiceColumn(table, list_TableObject_Class);

				System.out.println("------------------------------My_Default_Table_Model  Masive_Invizible_Colum "
						+ ResultsTableList_OverallVariables.getMasive_Invizible_Colum().length);

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
