package Table;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import AddResultViewFunction.OverallVariablesAddResults;
import WindowView.DatePicker;

public class Add_DefaultTableModel  {
	 


	public static DefaultTableModel Add_DefaultTableModel_dd (Object rowData[][], Object columnNames[], @SuppressWarnings("rawtypes") Class[] types, String[] tipeColumn,int check_Colum) {
		
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames) {
			
			private static final long serialVersionUID = 1L;
		
			@SuppressWarnings("rawtypes")
			private Class[] classTypes = types;
								

				@SuppressWarnings({})
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return this.classTypes[columnIndex];
				}

				
				public Object getValueAt(int row, int col) {
					return OverallVariablesAddResults.getDataTable()[row][col];
				}

				@Override
				public boolean isCellEditable(int row, int col) {
					if (col < check_Colum) {
						return true;
					} else {
						return false;
					}
				}

				public void setValueAt(Object value, int row, int col) {

					if (!OverallVariablesAddResults.getDataTable()[row][col].equals(value)) {
						
						switch (tipeColumn[col]) {
						
						case "DatePicker":
							if (!DatePicker.incorrectDate((String) value, false)) {
								OverallVariablesAddResults.getDataTable()[row][col] = value;
								fireTableCellUpdated(row, col);
							}
							break;
							
						case "Double":
							try {
								Double.parseDouble((String) value);
								OverallVariablesAddResults.getDataTable()[row][col] = value;
								fireTableCellUpdated(row, col);
							} catch (NumberFormatException e) {
							}
							break;
							
						case  "Choice":
							OverallVariablesAddResults.getDataTable()[row][col] = value;
							fireTableCellUpdated(row, col);
							break;
							
						case  "Boolean_Check":
							OverallVariablesAddResults.getDataTable()[row][col] = value;
							fireTableCellUpdated(row, col);
							break;
						}
						

					}

				}

				public int getColumnCount() {
					return columnNames.length;
				}

				public int getRowCount() {

					return OverallVariablesAddResults.getDataTable().length;
				}
		 
		 };
		return model;
	}
        
	public static void setInvisibleColumn (JTable table, int rsult_Id_Colum){
		table.getColumnModel().getColumn(rsult_Id_Colum).setWidth(0);
		table.getColumnModel().getColumn(rsult_Id_Colum).setMinWidth(0);
		table.getColumnModel().getColumn(rsult_Id_Colum).setMaxWidth(0);
		table.getColumnModel().getColumn(rsult_Id_Colum).setPreferredWidth(0);
	}
	 
		
	}
		
	
	

	


