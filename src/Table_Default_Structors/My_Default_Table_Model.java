package Table_Default_Structors;

import java.awt.Choice;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import AddResultViewFunction.AddResultViewMetods;
import Aplication.UsersDAO;
import DBase_Class.Users;
import ExcelFilesFunction.Destruct_Result;
import ExcelFilesFunction.ReadExcelFile;
import Table.Add_DefaultTableModel;
import Table.OverallVariablesTableRequestList;
import WindowView.DatePicker;

public abstract class My_Default_Table_Model{
	
		
	public static DefaultTableModel Default_Data_Table_Model() {

		List<TableObject_Class> list_TableObject_Class = OverallVariablesTableRequestList.getList_TableObject_Class();
		Object[][] masiveDataTable = OverallVariablesTableRequestList.getDataTable();
		
		
		Object columnNames[] = getColumnHeaderName(list_TableObject_Class);
	
		DefaultTableModel model = new DefaultTableModel(masiveDataTable, columnNames) {

			private static final long serialVersionUID = 1L;

			Class<?>[] classTypes = getColumnTypesClass(list_TableObject_Class);

			@SuppressWarnings({})
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return this.classTypes[columnIndex];
			}

			public Object getValueAt(int row, int col) {
				return masiveDataTable[row][col];
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				if (OverallVariablesTableRequestList.isEditableTable()) {
					return true;
				} else {
					return false;
				}
			}

			public void setValueAt(Object value, int row, int col) {

				if (!masiveDataTable[row][col].equals(value)) {
					
						masiveDataTable[row][col] = value;
						fireTableCellUpdated(row, col);
						AddInUpdateList(row);
					
				}

			}
	
		};
		return model;
	}

		
	
	private static void AddInUpdateList(int row) {
		List<Integer> listRowForUpdate = OverallVariablesTableRequestList.getListRowForUpdate();
		if (listRowForUpdate.isEmpty()) {
			listRowForUpdate.add(row);
		} else {
			if (!listRowForUpdate.equals(row)) {
				listRowForUpdate.add(row);
			}
		}
	}

	protected static String[] getTypesColumn(List<TableObject_Class> list_TableObject_Class) {

		String[] list = new String[list_TableObject_Class.size()];
		int i = 0;
		for (TableObject_Class tableObject_Class : list_TableObject_Class) {
			list[i] = tableObject_Class.getTipeColumn();
			i++;
		}
		return list;
	}

	private static Class<?>[] getColumnTypesClass(List<TableObject_Class> list_TableObject_Class) {
		Class<?>[] list = new Class<?>[list_TableObject_Class.size()];
		int i = 0;
		for (TableObject_Class tableObject_Class : list_TableObject_Class) {
			list[i] = tableObject_Class.getClassColumn();
			i++;
		}
		return list;
	}

	private static Object[] getColumnHeaderName(List<TableObject_Class> list_TableObject_Class) {
		String[] list = new String[list_TableObject_Class.size()];
		int i = 0;
		for (TableObject_Class tableObject_Class : list_TableObject_Class) {
			list[i] = tableObject_Class.getColumName_Header();
			i++;
		}
		return list;

	}

	public static void setInvisibleColumn(JTable table) {
		int[] masive_Invizible_Colum = OverallVariablesTableRequestList.getMasive_Invizible_Colum();
		if(masive_Invizible_Colum!=null)
		for (int i = 0; i < masive_Invizible_Colum.length; i++) {
			table.getColumnModel().getColumn(masive_Invizible_Colum[i]).setWidth(0);
			table.getColumnModel().getColumn(masive_Invizible_Colum[i]).setMinWidth(0);
			table.getColumnModel().getColumn(masive_Invizible_Colum[i]).setMaxWidth(0);
			table.getColumnModel().getColumn(masive_Invizible_Colum[i]).setPreferredWidth(0);
		}
	}

	public static void setInChoiceOIR(Choice choiceOIR) {
		Users user = ReadExcelFile.getUserFromExcelFile();
		String str = user.getName_users() + " " + user.getFamily_users();
		System.out.println(str);
		choiceOIR.select(str);
	}

	

}
