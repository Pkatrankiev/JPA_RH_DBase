package Table_Default_Structors;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import Table.RequestTableList_OverallVariables;
import Table_Results.ResultsTableList_OverallVariables;


public abstract class My_Default_Table_Model{
	
	static List<TableObject_Class> list_TableObject_Class = new ArrayList<TableObject_Class>();
	static Object[][] masiveDataTable = null;
	static int[] masive_Invizible_Colum = null;
	static List<Integer> listRowForUpdate;
	
	public static DefaultTableModel Default_Data_Table_Model(String tipe_Table) {
		
		switch (tipe_Table) {
		
		case "request":
			list_TableObject_Class = RequestTableList_OverallVariables.getList_TableObject_Class();
			masiveDataTable = RequestTableList_OverallVariables.getDataTable();
			masive_Invizible_Colum = RequestTableList_OverallVariables.getMasive_Invizible_Colum();
			listRowForUpdate = RequestTableList_OverallVariables.getListRowForUpdate();
			break;
		case "Results":
			list_TableObject_Class = ResultsTableList_OverallVariables.getList_TableObject_Class();
			 masiveDataTable = ResultsTableList_OverallVariables.getDataTable();
				masive_Invizible_Colum = ResultsTableList_OverallVariables.getMasive_Invizible_Colum();
				listRowForUpdate = ResultsTableList_OverallVariables.getListRowForUpdate();
			break;

	}
		
		
		
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
				boolean isEditableTable = false;
				switch (tipe_Table) {
				case "request":
					isEditableTable = RequestTableList_OverallVariables.isEditableTable();
					break;
				case "Results":
					isEditableTable = ResultsTableList_OverallVariables.isEditableTable();
					break;
				}
				return isEditableTable;
					 
				
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
	
@SuppressWarnings("static-access")
public static DefaultTableModel Default_Data_Table_Model_Test(ResultsTableList_OverallVariables objectTableList_OverallVariables) {
		
	list_TableObject_Class = objectTableList_OverallVariables.getList_TableObject_Class();
	masiveDataTable = objectTableList_OverallVariables.getDataTable();
	masive_Invizible_Colum = objectTableList_OverallVariables.getMasive_Invizible_Colum();
	listRowForUpdate = objectTableList_OverallVariables.getListRowForUpdate();
	
	
//		switch (tipe_Table) {
//		
//		case "request":
//			list_TableObject_Class = RequestTableList_OverallVariables.getList_TableObject_Class();
//			masiveDataTable = RequestTableList_OverallVariables.getDataTable();
//			masive_Invizible_Colum = RequestTableList_OverallVariables.getMasive_Invizible_Colum();
//			listRowForUpdate = RequestTableList_OverallVariables.getListRowForUpdate();
//			break;
//		case "Results":
//			list_TableObject_Class = ResultsTableList_OverallVariables.getList_TableObject_Class();
//			 masiveDataTable = ResultsTableList_OverallVariables.getDataTable();
//				masive_Invizible_Colum = ResultsTableList_OverallVariables.getMasive_Invizible_Colum();
//				listRowForUpdate = ResultsTableList_OverallVariables.getListRowForUpdate();
//			break;
//
//	}
		
		
		
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
				boolean isEditableTable = false;
				
				isEditableTable = objectTableList_OverallVariables.isEditableTable();
				
//				switch (tipe_Table) {
//				case "request":
//					isEditableTable = RequestTableList_OverallVariables.isEditableTable();
//					break;
//				case "Results":
//					isEditableTable = ResultsTableList_OverallVariables.isEditableTable();
//					break;
//				}
				
				
				return isEditableTable;
					 
				
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
		
		if(masive_Invizible_Colum!=null){
		System.out.println("++++++++++++++++++++++++++++++++++++ "+masive_Invizible_Colum.length);
		for (int i = 0; i < masive_Invizible_Colum.length; i++) {
			System.out.println(masive_Invizible_Colum[i]+" *************************");
		}
		if(masive_Invizible_Colum!=null)
		for (int i = 0; i < masive_Invizible_Colum.length; i++) {
			table.getColumnModel().getColumn(masive_Invizible_Colum[i]).setWidth(0);
			table.getColumnModel().getColumn(masive_Invizible_Colum[i]).setMinWidth(0);
			table.getColumnModel().getColumn(masive_Invizible_Colum[i]).setMaxWidth(0);
			table.getColumnModel().getColumn(masive_Invizible_Colum[i]).setPreferredWidth(0);
		}
	}
	}

//	public static void setInChoiceOIR(Choice choiceOIR) {
//		Users user = ReadExcelFile.getUserFromExcelFile();
//		String str = user.getName_users() + " " + user.getFamily_users();
//		System.out.println(str);
//		choiceOIR.select(str);
//	}

	

}
