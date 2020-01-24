package Table;

import java.awt.Choice;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import AddResultViewFunction.AddResultViewMetods;
import DBase_Class.Users;
import ExcelFilesFunction.Destruct_Result;
import ExcelFilesFunction.ReadExcelFile;
import WindowView.DatePicker;

public class Add_DefaultTableModel  {
	 
	public static Boolean fromDBase = false;

	public static DefaultTableModel Add_DefaultTableModel_dd (Object masiveDataTable[][], Object columnNames[],
  Class<?>[] types, String[] tipeColumn,	int check_Colum) {
		
		DefaultTableModel model = new DefaultTableModel(masiveDataTable, columnNames) {
			
			private static final long serialVersionUID = 1L;
		
			@SuppressWarnings("rawtypes")
			private Class[] classTypes = types;
								

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
					if (col < check_Colum) {
						return true;
					} else {
						return false;
					}
				}

				public void setValueAt(Object value, int row, int col) {

					if (!masiveDataTable[row][col].equals(value)) {
						
						switch (tipeColumn[col]) {
						
						case "DatePicker":
							if (!DatePicker.incorrectDate((String) value, false)) {
								masiveDataTable[row][col] = value;
								fireTableCellUpdated(row, col);
							}
							break;
							
						case "Double":
							try {
								Double.parseDouble((String) value);
								masiveDataTable[row][col] = value;
								fireTableCellUpdated(row, col);
							} catch (NumberFormatException e) {
							}
							break;
							
						case  "Choice":
							masiveDataTable[row][col] = value;
							fireTableCellUpdated(row, col);
							break;
							
						case  "Boolean_Check":
							masiveDataTable[row][col] = value;
							fireTableCellUpdated(row, col);
							break;
						}
						

					}

				}

				public int getColumnCount() {
					return columnNames.length;
				}

				public int getRowCount() {
					return masiveDataTable.length;
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
	 
	public static Boolean getFromDBase() {
		return fromDBase;
	}

	public static void setFromDBase(Boolean fromDBase) {
		Add_DefaultTableModel.fromDBase = fromDBase;
	}
	
	public static void setInChoiceOIR(Choice choiceOIR) {
		Users user = ReadExcelFile.getUserFromExcelFile();
		String str = user.getName_users() + " " + user.getFamily_users();
		System.out.println(str);
		choiceOIR.select(str);
	}
	
	public static Boolean checkKorektFileNameAndMetod(Choice choiceMetody, String codeSamample,List<Destruct_Result> destruct_Result_List) {
		Boolean fl = false;
			String codeSamampleFromExcelFile = ReadExcelFile.getCod_sample();
			
			if (AddResultViewMetods.checkKorektFileName(codeSamampleFromExcelFile, codeSamample)) {
			if( AddResultViewMetods.checkForKoretMetod(destruct_Result_List, choiceMetody)){
				fl = true;
				setFromDBase(false);
			}
			}
			return fl;
		}
	
	}
		
	
	

	


