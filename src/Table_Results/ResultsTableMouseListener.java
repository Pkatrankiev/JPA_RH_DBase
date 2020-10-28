package Table_Results;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Aplication.DobivDAO;
import Aplication.MetodyDAO;
import Aplication.RequestDAO;
import DBase_Class.Dobiv;
import DBase_Class.Metody;
import DBase_Class.Request;
import DBase_Class.Results;
import DefaultTableList.TableList_Functions;
import DefaultTableList.TableList_OverallVariables;
import DefaultTableList.TableObject_Class;
import WindowView.RequestMiniFrame;

public class ResultsTableMouseListener {

	static Object[][] dataForTable;
	private static JTable table;

	@SuppressWarnings("static-access")
	public ResultsTableMouseListener(TableList_OverallVariables objectTableList_OverallVariables, JTable tableNew) {
		table = tableNew;
		dataForTable = objectTableList_OverallVariables.getDataTable();

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

						String reqCodeStr = getSelectedCode_Request(objectTableList_OverallVariables, model,
								selectedRow);
						Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
						if (objectTableList_OverallVariables.isEditableTable()) {

							String nameSelectedColumn = table.getColumnModel().getColumn(table.getSelectedColumn())
									.getHeaderValue().toString();
							int selectedColumnIndex = TableList_Functions.getModdelIndexColumnByColumnName(
									objectTableList_OverallVariables, nameSelectedColumn);
							selectedColumnIndex = TableList_Functions.getModdelIndexColumnByColumnName(
									objectTableList_OverallVariables, nameSelectedColumn);

							switch (getTipeColumnByNameColumn(objectTableList_OverallVariables, nameSelectedColumn)) {

							
							case "code_Request":
								new RequestMiniFrame(new JFrame(), choiseRequest);
								break;
								
							case "Dobiv":
							case "Dobiv_Nuclide":
								Table_DobivInResults(objectTableList_OverallVariables, model, selectedRow);
								break;

							}
						} else {

						}
					}
				}
			}

		});
	}

	public static int getSelectedModelRow(JTable table) {
		return table.convertRowIndexToModel(table.getSelectedRow());
	}

	public static String getSelectedCode_Request(TableList_OverallVariables objectTableList_OverallVariables,
			DefaultTableModel model, int selectedRow) {
		int code_RequestColumnIndex = TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,
				"NumberRequest");
		String reqCodeStr = model.getValueAt(selectedRow, code_RequestColumnIndex).toString();
		return reqCodeStr;
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
	public static String getTipeColumnByNameColumn(TableList_OverallVariables objectTableList_OverallVariables,
			String nameColumn) {
		List<TableObject_Class> map_TableObject_Class = objectTableList_OverallVariables.getList_TableObject_Class();
		for (TableObject_Class tableObject_Class : map_TableObject_Class) {
			if (tableObject_Class.getColumName_Header().equals(nameColumn)) {
				return tableObject_Class.getTipeColumn();
			}
		}
		return null;
	}

	private void Table_DobivInResults(TableList_OverallVariables objectTableList_OverallVariables,
			DefaultTableModel model, int selectedRow) {
		int idDobiv_ColumnIndex = TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,
				"Id_Dobiv");
		int dobivResult_ColumnIndex = TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,
				"result_Dobiv");
		int dobivNuclide_ColumnIndex = TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,
				"Nuclide_Dobiv");
		int metody_ColumnIndex = TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,
				"MetodNaIzpitvane");

		List<Dobiv> listNameDobivs = null;
		
		Dobiv selectDobiv = DobivDAO.getDobivById((int) model.getValueAt(selectedRow, idDobiv_ColumnIndex));
		System.out.println("****************"+model.getValueAt(selectedRow, idDobiv_ColumnIndex).toString());
		if(selectDobiv==null){
			Metody metod = MetodyDAO.getValueList_MetodyByCode(model.getValueAt(selectedRow, metody_ColumnIndex).toString());
			 listNameDobivs = DobivDAO.getListDobivByMetody(metod);
		}else{
		 listNameDobivs = DobivDAO.getListDobivByNuclide(selectDobiv.getNuclide());
		}
		
		DialogView_DobivFromResultTableList dobivFromResultTableList = new DialogView_DobivFromResultTableList(new JFrame(),
				listNameDobivs, selectDobiv);
		
if(dobivFromResultTableList.getSelectDobiv()!=null){
		model.setValueAt(dobivFromResultTableList.getSelectDobiv().getNuclide().getSymbol_nuclide(), selectedRow,
				dobivNuclide_ColumnIndex);
		model.setValueAt(dobivFromResultTableList.getSelectDobiv().getValue_result(), selectedRow,
				dobivResult_ColumnIndex);
		model.setValueAt(dobivFromResultTableList.getSelectDobiv().getId_dobiv(), selectedRow, idDobiv_ColumnIndex);
}else{
	model.setValueAt("", selectedRow, dobivNuclide_ColumnIndex);
	model.setValueAt(0.0, selectedRow, dobivResult_ColumnIndex);
	model.setValueAt(0, selectedRow, idDobiv_ColumnIndex);
}

	}

	public static Boolean isEditableDobivObjectInResults1(Results choiseResults, Dobiv newDobiv) {
		Dobiv originalDobiv = choiseResults.getDobiv();
		if (newDobiv != null && originalDobiv != null) {
			return newDobiv.getId_dobiv() != originalDobiv.getId_dobiv();
		} else {
			if (newDobiv == null && originalDobiv == null) {
				return false;
			}

			return true;
		}
	}
}
