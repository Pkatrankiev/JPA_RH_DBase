package Table_Results;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Aplication.RequestDAO;
import DBase_Class.Request;
import Table_Default_Structors.TableList_Functions;
import Table_Default_Structors.TableObject_Class;
import WindowView.RequestMiniFrame;


public class DefauiltResultsTableMouseListener {

	static Object[][] dataForTable;
	private static JTable table;
	
	@SuppressWarnings("static-access")
	public DefauiltResultsTableMouseListener(ResultsTableList_OverallVariables objectTableList_OverallVariables, JTable tableNew) {
		table = tableNew;
		dataForTable = objectTableList_OverallVariables.getDataTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			
			@SuppressWarnings("unused")
			public void mousePressed(MouseEvent e) {

				if (SwingUtilities.isLeftMouseButton(e)){
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRow = getSelectedModelRow(table);

				if (selectedRow != -1) {
					
					String reqCodeStr = getSelectedCode_Request(objectTableList_OverallVariables, model, selectedRow);
					Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
					if (objectTableList_OverallVariables.isEditableTable()) {

						String nameSelectedColumn = table.getColumnModel().getColumn(table.getSelectedColumn())
								.getHeaderValue().toString();					
						int selectedColumnIndex = TableList_Functions.getModdelIndexColumnByColumnName(objectTableList_OverallVariables,nameSelectedColumn);
						selectedColumnIndex = TableList_Functions.getModdelIndexColumnByColumnName(objectTableList_OverallVariables,nameSelectedColumn);
						
						switch (getTipeColumnByNameColumn(objectTableList_OverallVariables,nameSelectedColumn)) {
						
						case "code_Request":
							new RequestMiniFrame(new JFrame(), choiseRequest);
							break;



						}
					}else {
						
					}
				}
			}
			}
			
		});
	}
		
	public static int getSelectedModelRow(JTable table) {
			return table.convertRowIndexToModel(table.getSelectedRow());
		}

		public static String getSelectedCode_Request(ResultsTableList_OverallVariables objectTableList_OverallVariables,DefaultTableModel model, int selectedRow) {
			int code_RequestColumnIndex = getIndexColumnByKeyMap(objectTableList_OverallVariables, "NumberRequest");
			String reqCodeStr = model.getValueAt(selectedRow, code_RequestColumnIndex).toString();
			return reqCodeStr;
		}

		
		@SuppressWarnings("static-access")
		public static int getIndexColumnByKeyMap(ResultsTableList_OverallVariables objectTableList_OverallVariables, String keyMap) {
			Map<String, TableObject_Class> map_TableObject_Class = objectTableList_OverallVariables
					.getMap_TableObject_Class();
			return map_TableObject_Class.get(keyMap).getNumberColum();
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
	

		@SuppressWarnings("static-access")
		public static String getTipeColumnByNameColumn(ResultsTableList_OverallVariables objectTableList_OverallVariables, String nameColumn) {
			List<TableObject_Class> map_TableObject_Class = objectTableList_OverallVariables.getList_TableObject_Class();
			for (TableObject_Class tableObject_Class : map_TableObject_Class) {
				if (tableObject_Class.getColumName_Header().equals(nameColumn)) {
					return tableObject_Class.getTipeColumn();
				}
			}
			return null;
		}

		

}
