package DefaultTableList;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public abstract class My_Default_Table_Model{
	
	static List<TableObject_Class> list_TableObject_Class = new ArrayList<TableObject_Class>();
	static Object[][] masiveDataTable = null;
	static int[] masive_Invizible_Colum = null;
	static List<Integer> list_FreeEditColumn;
	static List<Integer> listRowForUpdate;
	
@SuppressWarnings("static-access")
public static DefaultTableModel Default_Data_Table_Model(TableList_OverallVariables objectTableList_OverallVariables) {
		
	list_TableObject_Class = objectTableList_OverallVariables.getList_TableObject_Class();
	masiveDataTable = objectTableList_OverallVariables.getDataTable();
	masive_Invizible_Colum = objectTableList_OverallVariables.getMasive_Invizible_Colum();
	listRowForUpdate = objectTableList_OverallVariables.getListRowForUpdate();
	
		list_FreeEditColumn = getTypesFreeEditColumn(list_TableObject_Class);
		
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
				
//				isEditableTable = objectTableList_OverallVariables.isEditableTable();
				isEditableTable = objectTableList_OverallVariables.isSelectedCheckBox() && editableColumn(col)||objectTableList_OverallVariables.isEditableTable();
				System.out.println("col = "+col);
				System.out.println(objectTableList_OverallVariables.isSelectedCheckBox() && editableColumn(col));
				return isEditableTable;
					 
				
			}

			private boolean editableColumn(int col) {
				boolean isEditableColumn = false;
				for (int numColumn : list_FreeEditColumn) {
				if(col==numColumn){
					isEditableColumn = true;
				}
				}
				return isEditableColumn;
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

	protected static List<Integer> getTypesFreeEditColumn(List<TableObject_Class> list_TableObject_Class) {

		List<Integer> list = new ArrayList<Integer>();
		int i = 0;
		for (TableObject_Class tableObject_Class : list_TableObject_Class) {
			if(tableObject_Class.getTipeColumn().equals("Text") ){
				list.add(i);
			}
			i++;
		}
		for (int l : list) {
			System.out.println(l);
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
			
		if(masive_Invizible_Colum!=null)
		for (int i = 0; i < masive_Invizible_Colum.length; i++) {
			table.getColumnModel().getColumn(masive_Invizible_Colum[i]).setWidth(0);
			table.getColumnModel().getColumn(masive_Invizible_Colum[i]).setMinWidth(0);
			table.getColumnModel().getColumn(masive_Invizible_Colum[i]).setMaxWidth(0);
			table.getColumnModel().getColumn(masive_Invizible_Colum[i]).setPreferredWidth(0);
		}
	
	}


}
