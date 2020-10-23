package Table_Sample;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Aplication.RequestDAO;
import DBase_Class.Request;
import DefaultTableList.TableList_Functions;
import DefaultTableList.TableList_OverallVariables;
import DefaultTableList.TableObject_Class;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import Table.Table_Sample_List;
import Table_Request.Table_RequestToObektNaIzp;
import WindowView.DateChoice_period;
import WindowView.MiniComboBoxFrame;
import WindowView.RequestMiniFrame;
import WindowView.RequestView;
import WindowView.TranscluentWindow;

public class SampleTableMouseListener {

	static Object[][] dataForTable;
	private static JTable table;
	
	@SuppressWarnings("static-access")
	public SampleTableMouseListener(TableList_OverallVariables objectTableList_OverallVariables, JTable tableNew) {
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
						
					
						int selectedColumnIndex = getModdelIndexColumnByColumnName(objectTableList_OverallVariables,nameSelectedColumn);
						
						switch (TableList_Functions.getTipeColumnByNameColumn(objectTableList_OverallVariables, nameSelectedColumn)) {
						
						case "code_Request":
							new RequestMiniFrame(new JFrame(), choiseRequest);
							break;

						case "Date-TimePicker":
							setDataTime_Referense(model, selectedRow, selectedColumnIndex);
							break;
							
						case "Table_SampleToObektNaIzp":
							setObectNaIzpitvaneRequest( objectTableList_OverallVariables,  choiseRequest,selectedRow);
							break;

						}
					}
				}
			}
			}

			

	

		});
	}

	private static int getSelectedModelRow() {
		return table.convertRowIndexToModel(table.getSelectedRow());
	}

	private String getSelectedCode_Request(TableList_OverallVariables objectTableList_OverallVariables,DefaultTableModel model, int selectedRow) {
		int code_RequestColumnIndex = TableList_Functions.getIndexColumnByKeyMap( objectTableList_OverallVariables,"rqst_code");
		String reqCodeStr = model.getValueAt(selectedRow, code_RequestColumnIndex).toString();
		return reqCodeStr;
	}

	@SuppressWarnings("static-access")
	public static int getModdelIndexColumnByColumnName(TableList_OverallVariables objectTableList_OverallVariables, String columnName) {
		for (TableObject_Class object : objectTableList_OverallVariables.getList_TableObject_Class()) {
			System.out.println(TableList_Functions.reformatString(object.getColumName_Header())+" - "+columnName);
			if (object.getColumName_Header().equalsIgnoreCase(columnName)) {
				return object.getNumberColum();
			}
		}
		return -1;
	}
		
	private void setDataTime_Referense(DefaultTableModel model, int selectedRow, int selectedColumnIndex) {
		final JFrame f = new JFrame();
		Boolean forDateReception = false;
		Boolean withTime = true;
		Boolean fromTable = true;
		String str_date_period_reception = model.getValueAt(selectedRow, selectedColumnIndex).toString();

		DateChoice_period date_time_reference = new DateChoice_period(f, str_date_period_reception,
				withTime, forDateReception,	fromTable);
		model.setValueAt(date_time_reference, selectedRow, selectedColumnIndex);
	}
	
	private void setObectNaIzpitvaneRequest(TableList_OverallVariables objectTableList_OverallVariables, Request choiseRequest,int selectedRow) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String origO_I_Request = model.getValueAt(selectedRow,  TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "obk_Izp")).toString();
		String[] values_O_I_R = Table_RequestToObektNaIzp
				.getMasiveStringOfRequest_To_ObektNaIzpitvaneRequest(choiseRequest);

		JFrame f = new JFrame();
		MiniComboBoxFrame miniBoxFrame = new MiniComboBoxFrame(f, values_O_I_R, origO_I_Request);
		String changed_O_I_R = miniBoxFrame.getStrO_I_Request();
		model.setValueAt(changed_O_I_R, selectedRow, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "obk_Izp"));

	}
	
}
