package Table_Default_Structors;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Aplication.RequestDAO;
import DBase_Class.Request;
import Table.Add_TableMouseListener;
import Table.OverallVariablesTableRequestList;
import Table.Table_RequestToObektNaIzp;
import Table.Table_Sample_List;
import WindowView.ChoiceFromListWithPlusAndMinus;
import WindowView.ChoiceL_I_P;
import WindowView.DateChoice_period;
import WindowView.DatePicker;
import WindowView.RequestMiniFrame;
import WindowView.TranscluentWindow;

public class DefauiltTableMouseListener {

	static Object[][] dataForTable;
	private static JTable table;
	private static Map<Integer, List<String>> mapListForChangedStrObektNaIzp = new HashMap<Integer, List<String>>();

	public DefauiltTableMouseListener(JTable tableNew) {
		table = tableNew;
		dataForTable = OverallVariablesTableRequestList.getDataTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRow = getSelectedModelRow();

				if (selectedRow != -1) {
					if (OverallVariablesTableRequestList.isEditableTable()) {

						String nameSelectedColumn = table.getColumnModel().getColumn(table.getSelectedColumn())
								.getHeaderValue().toString();
						int selectedColumnIndex = getColumnIndex(table, nameSelectedColumn);

						// int selectedColumnIndex =
						// getIndexColumnByColumnName(nameSelectedColumn);
						String reqCodeStr = getSelectedCode_Request(model, selectedRow);
						Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
						System.out.println("nameSelectedColumn = " + nameSelectedColumn + " selectedColumnIndex = "
								+ selectedColumnIndex + " choiseRequest = " + reqCodeStr);
						selectedColumnIndex = getModdelIndexColumnByColumnName(nameSelectedColumn);
						
						switch (getTipeColumnByNameColumn(nameSelectedColumn)) {
						
						case "code_Request":
							new RequestMiniFrame(new JFrame(), choiseRequest);
							break;

						case "DatePicker":
							DatePicker_Function(model, selectedRow, selectedColumnIndex, false);
							break;

						case "DatePicker_Dual":
							DatePicker_Dual_Function(model, selectedRow, selectedColumnIndex);
							break;

						case "Table_RequestToObektNaIzp":
							Table_RequestToObektNaIzp(model, selectedRow, selectedColumnIndex, choiseRequest);
							break;

						case "Pokazatel":
							EditColumnPokazatel(model, selectedRow);
							break;

						case "count_Simple":
							startViewSampleTableList(reqCodeStr);
							break;

						case "Date-TimePicer":
							DatePicker_Function(model, selectedRow, selectedColumnIndex, true);
							break;

						}
					}
				}
			}

			private void startViewSampleTableList(String reqCodeStr) {
				Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
				new JFrame();
				TranscluentWindow round = new TranscluentWindow();

				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {

						JFrame f = new JFrame();
						new Table_Sample_List(f, round, choiseRequest);
					}
				});
				thread.start();

			}

		});
	}

	private String getSelectedCode_Request(DefaultTableModel model, int selectedRow) {
		int code_RequestColumnIndex = getIndexColumnByKeyMap("rqst_code");
		String reqCodeStr = model.getValueAt(selectedRow, code_RequestColumnIndex).toString();
		return reqCodeStr;
	}

	public static int getIndexColumnByKeyMap(String keyMap) {
		Map<String, TableObject_Class> map_TableObject_Class = OverallVariablesTableRequestList
				.getMap_TableObject_Class();
		return map_TableObject_Class.get(keyMap).getNumberColum();
	}

	private String getTipeColumnByNameColumn(String nameColumn) {
		List<TableObject_Class> map_TableObject_Class = OverallVariablesTableRequestList.getList_TableObject_Class();
		for (TableObject_Class tableObject_Class : map_TableObject_Class) {
			if (tableObject_Class.getColumName_Header().equals(nameColumn)) {
				return tableObject_Class.getTipeColumn();
			}
		}
		return null;
	}

	public static String getNameColumnByKeyMap(String keyMap) {
		Map<String, TableObject_Class> map_TableObject_Class = OverallVariablesTableRequestList
				.getMap_TableObject_Class();
		return map_TableObject_Class.get(keyMap).getColumName_Header();
	}

	private void Table_RequestToObektNaIzp(DefaultTableModel model, int selectedRow, int columnIndex,
			Request choiseRequest) {
		if (Table_RequestToObektNaIzp.EditRequestObektIzpit(table, selectedRow, choiseRequest,
				mapListForChangedStrObektNaIzp, OverallVariablesTableRequestList.getValues_O_I_R())) {
			List<String> listFromChoiceObektNaIzp = ChoiceFromListWithPlusAndMinus.getMasiveStringFromChoice();
			model.setValueAt(Table_RequestToObektNaIzp.createStringListObektNaIzp(listFromChoiceObektNaIzp, false),
					selectedRow, columnIndex);
			mapListForChangedStrObektNaIzp.put(selectedRow, listFromChoiceObektNaIzp);
			OverallVariablesTableRequestList.setMapListForChangedStrObektNaIzp(mapListForChangedStrObektNaIzp);

		}
	}

	private void DatePicker_Function(DefaultTableModel model, int selectedRow, int columnIndex, Boolean withTime) {

		String strDate = model.getValueAt(selectedRow, columnIndex).toString();
		final JFrame f = new JFrame();
		DatePicker dPicer = new DatePicker(f, withTime, strDate);
		String str = dPicer.setPickedDate(withTime);
		strDate = DatePicker.formatToTabDate(str, withTime);
		model.setValueAt(strDate, selectedRow, columnIndex);
	}

	private void DatePicker_Dual_Function(DefaultTableModel model, int selectedRow, int columnIndex) {
		String str_date_period_reception = model.getValueAt(selectedRow, columnIndex).toString();
		Boolean forDateReception = true;
		Boolean forTable = true;
		Boolean withTime = false;
		Boolean fromTable = true;
		final JFrame frame = new JFrame();
		DateChoice_period date_period_reception = new DateChoice_period(frame, str_date_period_reception, withTime,
				forDateReception, fromTable);
		date_period_reception.setVisible(true);
		model.setValueAt(DateChoice_period.get_str_period_sample(forDateReception, forTable), selectedRow, columnIndex);
	}

	private static void EditColumnPokazatel(DefaultTableModel model, int selectedRow) {
		int columnIndex = getIndexColumnByKeyMap("izp_Pok");
		String strPokazatel = model.getValueAt(selectedRow, columnIndex).toString().trim();
		System.out.println(columnIndex + " ++++++++++++++ " + strPokazatel);
		List<String> list = ReadListPokazatelInCell(strPokazatel);
		System.out.println(list.size());
		JFrame f = new JFrame();
		ChoiceL_I_P choiceLP = new ChoiceL_I_P(f, list, false);
		if (list.size() == ChoiceL_I_P.getChoiceL_P().size()) {
			System.out.println(CreateStringListIzpPokaz(choiceLP));
			model.setValueAt(CreateStringListIzpPokaz(choiceLP), selectedRow, columnIndex);
		} else {
			JOptionPane.showMessageDialog(null, "Не можете да променяте броя Показатели", "Грешка в данните",
					JOptionPane.ERROR_MESSAGE);

		}
	}

	private static List<String> ReadListPokazatelInCell(String strPokazatel) {
		List<String> list = new ArrayList<String>();
		System.out.println(strPokazatel + " -----------------------");
		String str = "";
		while (!strPokazatel.isEmpty()) {
			System.out.println(strPokazatel + " //////");
			str = strPokazatel.substring(0, strPokazatel.indexOf(";") + 1);
			list.add(str.replaceAll(";", "").trim());
			strPokazatel = strPokazatel.replaceFirst(str, "");
		}
		return list;
	}

	// private static List<String> ReadListPokazatelInCell(JTable table, int
	// row) {
	// List<String> list = new ArrayList<String>();
	// DefaultTableModel model = (DefaultTableModel) table.getModel();
	// String strPokazatel = model.getValueAt(row,
	// izp_Pok_Colum).toString().trim();
	// String str = "";
	// while (!strPokazatel.isEmpty()) {
	// str = strPokazatel.substring(0, strPokazatel.indexOf(";") + 1);
	// list.add(str.replaceAll(";", "").trim());
	// strPokazatel = strPokazatel.replaceFirst(str, "");
	// }
	// return list;
	// }

	@SuppressWarnings("static-access")
	public static String CreateStringListIzpPokaz(ChoiceL_I_P choiceLP) {
		String list_izpitvan_pokazatel = "";
		for (String izpitvan_pokazatel : choiceLP.getChoiceL_P()) {
			list_izpitvan_pokazatel = list_izpitvan_pokazatel + izpitvan_pokazatel + "; ";
		}
		return list_izpitvan_pokazatel;
	}

	private static int getSelectedModelRow() {
		return table.convertRowIndexToModel(table.getSelectedRow());
	}

	public static int getModdelIndexColumnByColumnName(String columnName) {
		for (TableObject_Class object : OverallVariablesTableRequestList.getList_TableObject_Class()) {
			if (object.getColumName_Header().equalsIgnoreCase(columnName)) {
				return object.getNumberColum();
			}
		}
		return -1;
	}

	public static int getIndexColumnByColumnName2(String columnName) {
		int columnCount = table.getColumnCount();

		for (int column = 0; column < columnCount; column++) {
			if (table.getColumnName(column).equalsIgnoreCase(columnName)) {
				return column;
			}
		}

		return -1;
	}

	// private static void AddInUpdateList(int row) {
	// Map<String, TableObject_Class> map_TableObject_Class =
	// OverallVariablesTableRequestList
	// .getMap_TableObject_Class();
	// String rqst_code_ColumName =
	// map_TableObject_Class.get("rqst_code").getColumName_Header();
	// List<Integer> listRowForUpdate =
	// OverallVariablesTableRequestList.getListRowForUpdate();
	// if (listRowForUpdate.isEmpty()) {
	// listRowForUpdate.add(row);
	// } else {
	// if
	// (!listRowForUpdate.equals(dataForTable[row][getIndexColumnByColumnName(rqst_code_ColumName)]))
	// {
	// listRowForUpdate.add(row);
	// }
	// }
	// }

	// public static String getKeyMapByHeaderNameColumn(String headerNameColumn,
	// Map<String, TableObject_Class> map_TableObject_Class){
	// for(String tabObject : map_TableObject_Class.keySet()) {
	// if(
	// map_TableObject_Class.get(tabObject).getColumName_Header().equals(headerNameColumn)){
	// return tabObject;
	// }
	// }
	// return "";
	//
	// }

	public static int getColumnIndex(JTable table, String columnTitle) {
		int columnCount = table.getColumnCount();
		for (int column = 0; column < columnCount; column++) {
			if (table.getColumnName(column).equalsIgnoreCase(columnTitle)) {
				return column;
			}
		}

		return -1;
	}

	// public static String getColumnName(JTable table, int c) {
	//
	// JTableHeader tableHeader = table.getTableHeader();
	// String columnName;
	// if (tableHeader != null) {
	// columnName =
	// tableHeader.getColumnModel().getColumn(c).getHeaderValue().toString();
	// } else {
	// columnName = table.getColumnName(c);
	// }
	// return columnName;
	// }

	// private int getColumnIndexByMapKey(String mapKey) {
	// return ((TableObject_Class)
	// map_TableObject_Class.get(mapKey)).getNumberColum();
	// }

}
