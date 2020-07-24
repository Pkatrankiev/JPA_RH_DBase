package Table;

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

import Aplication.RequestDAO;
import DBase_Class.Request;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import OldClases.Table_RequestToObektNaIzp;
import Table_Default_Structors.TableList_Functions;
import Table_Default_Structors.TableObject_Class;

import Table_Results.ResultsTableList_OverallVariables;
import WindowView.ChoiceFromListWithPlusAndMinus;
import WindowView.ChoiceL_I_P;
import WindowView.DateChoice_period;
import WindowView.DatePicker;
import WindowView.RequestMiniFrame;
import WindowView.RequestView;
import WindowView.TranscluentWindow;

public class RequestTableMouseListener {

		static Object[][] dataForTable;
		private static JTable table;
		private static Map<Integer, List<String>> mapListForChangedStrObektNaIzp = new HashMap<Integer, List<String>>();

		@SuppressWarnings("static-access")
		public RequestTableMouseListener(ResultsTableList_OverallVariables objectTableList_OverallVariables, JTable tableNew) {
			table = tableNew;
			dataForTable = objectTableList_OverallVariables.getDataTable();

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {

					if (SwingUtilities.isLeftMouseButton(e)){
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int selectedRow = getSelectedModelRow();

					if (selectedRow != -1) {
						
						String reqCodeStr = getSelectedCode_Request(objectTableList_OverallVariables, model, selectedRow);
						Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
						if (objectTableList_OverallVariables.isEditableTable()) {

							String nameSelectedColumn = table.getColumnModel().getColumn(table.getSelectedColumn())
									.getHeaderValue().toString();
//							int selectedColumnIndex = getColumnIndex(table, nameSelectedColumn);
						
							int selectedColumnIndex = getModdelIndexColumnByColumnName(objectTableList_OverallVariables,nameSelectedColumn);
							
							switch (getTipeColumnByNameColumn(objectTableList_OverallVariables, nameSelectedColumn)) {
							
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
								Table_RequestToObektNaIzp( objectTableList_OverallVariables, model, selectedRow, selectedColumnIndex, choiseRequest);
								break;

							case "Pokazatel":
								EditColumnPokazatel(objectTableList_OverallVariables, model, selectedRow);
								break;

							case "count_Simple":
							case "Date-TimePicker":
								startViewSampleTableList(reqCodeStr);
								break;

//							case "Date-TimePicker":
//								DatePicker_Function(model, selectedRow, selectedColumnIndex, true);
//								break;

							}
						}else {
							if (e.getClickCount() == 2) {
														
							if (objectTableList_OverallVariables.getFrame_name().
									equals(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("NewRequenseInTamplate_TitleName"))) {
								JFrame f = new JFrame();
								TranscluentWindow round = new TranscluentWindow();
								new RequestView(f, objectTableList_OverallVariables.getUser(), choiseRequest, round,false);
							}
							}}
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

//		public static String getNameColumnByKeyMap(String keyMap) {
//			Map<String, TableObject_Class> map_TableObject_Class = objectTableList_OverallVariables
//					.getMap_TableObject_Class();
//			return map_TableObject_Class.get(keyMap).getColumName_Header();
//		}

		@SuppressWarnings("static-access")
		private void Table_RequestToObektNaIzp(ResultsTableList_OverallVariables objectTableList_OverallVariables,DefaultTableModel model, int selectedRow, int columnIndex,
				Request choiseRequest) {
			if (Table_RequestToObektNaIzp.EditRequestObektIzpit(table, selectedRow, choiseRequest,
					mapListForChangedStrObektNaIzp, objectTableList_OverallVariables.getValues_O_I_R())) {
				List<String> listFromChoiceObektNaIzp = ChoiceFromListWithPlusAndMinus.getMasiveStringFromChoice();
				System.out.println(selectedRow+"  "+selectedRow+"  "+Table_RequestToObektNaIzp.createStringListObektNaIzp(listFromChoiceObektNaIzp, false));
				model.setValueAt(Table_RequestToObektNaIzp.createStringListObektNaIzp(listFromChoiceObektNaIzp, false),
						selectedRow, columnIndex);
				mapListForChangedStrObektNaIzp.put(selectedRow, listFromChoiceObektNaIzp);
				objectTableList_OverallVariables.setMapListForChangedStrObektNaIzp(mapListForChangedStrObektNaIzp);

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

		private static void EditColumnPokazatel(ResultsTableList_OverallVariables objectTableList_OverallVariables, DefaultTableModel model, int selectedRow) {
			int columnIndex = getIndexColumnByKeyMap(objectTableList_OverallVariables, "izp_Pok");
			String strPokazatel = model.getValueAt(selectedRow, columnIndex).toString().trim();
			List<String> list = ReadListPokazatelInCell(strPokazatel);
			System.out.println(list.size());
			JFrame f = new JFrame();
			ChoiceL_I_P choiceLP = new ChoiceL_I_P(f, list, false,
					ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("DefauiltTableMouseListener_EditColumnPokazatel"),null);
			if (list.size() == ChoiceL_I_P.getChoiceL_P().size()) {
				System.out.println(CreateStringListIzpPokaz(choiceLP));
				model.setValueAt(CreateStringListIzpPokaz(choiceLP), selectedRow, columnIndex);
			} else {
				JOptionPane.showMessageDialog(null,
						ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("noChengeNumberOfPokazateli"), 
						ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("errorOfData"),
						JOptionPane.ERROR_MESSAGE);

			}
		}

		private static List<String> ReadListPokazatelInCell(String strPokazatel) {
			List<String> list = new ArrayList<String>();
			System.out.println(strPokazatel + " -----------------------");
			String str = "";
			while (!strPokazatel.isEmpty()) {
				str = strPokazatel.substring(0, strPokazatel.indexOf(";") + 1);
				list.add(str.replaceAll(";", "").trim());
				strPokazatel = strPokazatel.replaceFirst(str, "");
			}
			return list;
		}
		
		@SuppressWarnings("static-access")
		public static String CreateStringListIzpPokaz(ChoiceL_I_P choiceLP) {
			String list_izpitvan_pokazatel = "";
			for (String izpitvan_pokazatel : choiceLP.getChoiceL_P()) {
				list_izpitvan_pokazatel = list_izpitvan_pokazatel + izpitvan_pokazatel + "; ";
			}
			return list_izpitvan_pokazatel;
		}

		@SuppressWarnings("static-access")
		public static int getModdelIndexColumnByColumnName(ResultsTableList_OverallVariables objectTableList_OverallVariables, String columnName) {
			for (TableObject_Class object : objectTableList_OverallVariables.getList_TableObject_Class()) {
				System.out.println(TableList_Functions.reformatString(object.getColumName_Header())+" - "+columnName);
				if (object.getColumName_Header().equalsIgnoreCase(columnName)) {
					return object.getNumberColum();
				}
			}
			return -1;
		}
		
		private static int getSelectedModelRow() {
			return table.convertRowIndexToModel(table.getSelectedRow());
		}

		private String getSelectedCode_Request(ResultsTableList_OverallVariables objectTableList_OverallVariables,DefaultTableModel model, int selectedRow) {
			int code_RequestColumnIndex = getIndexColumnByKeyMap( objectTableList_OverallVariables,"rqst_code");
			String reqCodeStr = model.getValueAt(selectedRow, code_RequestColumnIndex).toString();
			return reqCodeStr;
		}
		@SuppressWarnings("static-access")
		public static int getIndexColumnByKeyMap(ResultsTableList_OverallVariables objectTableList_OverallVariables,String keyMap) {
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
		private String getTipeColumnByNameColumn(ResultsTableList_OverallVariables objectTableList_OverallVariables, String nameColumn) {
			List<TableObject_Class> map_TableObject_Class = objectTableList_OverallVariables.getList_TableObject_Class();
			for (TableObject_Class tableObject_Class : map_TableObject_Class) {
				if (tableObject_Class.getColumName_Header().equals(nameColumn)) {
					return tableObject_Class.getTipeColumn();
				}
			}
			return null;
		}	


}
