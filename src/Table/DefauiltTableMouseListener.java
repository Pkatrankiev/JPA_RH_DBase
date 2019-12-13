package Table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import WindowView.ChoiceL_I_P;

public class DefauiltTableMouseListener {

	static Object[][] dataTable;
	
	public DefauiltTableMouseListener(JTable table, List<TableObject_Class> list_TableObject_Class) {
		
		dataTable = OverallVariablesTableRequestList.getDataTable();
				
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				
				int selectedRow = getSelectedModelRow(table);
				int col = table.columnAtPoint(e.getPoint());

				if(list_TableObject_Class.get(col).getNumberColum()==col){
					String nameSelectedColumn = list_TableObject_Class.get(col).getColumName_Header();
				switch (list_TableObject_Class.get(col).getTipeColumn()) {

				case "DatePicker":
					if (SwingUtilities.isRightMouseButton(e)) {
						String date_choice = Add_TableMouseListener.getDateFromDatePicker(table, col);
						table.setValueAt(date_choice, selectedRow, col);
					}
					break;

				case "Pokazatel":
											
						EditColumnPokazatel(table, selectedRow,nameSelectedColumn );

						AddInUpdateList(table, selectedRow);
					
					break;
				
				}
				}
			}
		
		});
	}
	
	private static void EditColumnPokazatel(JTable table, int row, String nameSelectedColumn) {
		int columnIndex = Table_Sample_List.getColumnIndex(table, nameSelectedColumn); 
		List<String> list = ReadListPokazatelInCell(table, row, columnIndex);
		JFrame f = new JFrame();
		ChoiceL_I_P choiceLP = new ChoiceL_I_P(f, list, false);
		if (list.size() == ChoiceL_I_P.getChoiceL_P().size()) {
			table.setValueAt(CreateStringListIzpPokaz(choiceLP), row, columnIndex);
		} else {
			JOptionPane.showMessageDialog(null, "Не можете да променяте броя Показатели", "Грешка в данните",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private static List<String> ReadListPokazatelInCell(JTable table, int row, int columnIndex) {
		List<String> list = new ArrayList<String>();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String strPokazatel = model.getValueAt(row, columnIndex).toString().trim();
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
	
	private int getSelectedModelRow(JTable table) {
		return table.convertRowIndexToModel(table.getSelectedRow());
	}
	
	public static String getColumnName(JTable table, int c) {
	    
	    JTableHeader tableHeader = table.getTableHeader();
	    String columnName;
	    if (tableHeader != null) {
	        columnName = tableHeader.getColumnModel().getColumn(c).getHeaderValue().toString();
	    } else {
	        columnName = table.getColumnName(c);
	    }
	    return columnName;
	}
	
	public static int getIndexColumnByColumnName (JTable table, String columnName){
		for (int i = 0; i < table.getColumnCount(); i++){
			if(getColumnName(table,i).equals(columnName)){
				return i;
			}
		}
		
		return -1;
		
	}
	
	private static void AddInUpdateList(JTable table, int row) {
		String rqst_code_ColumName = OverallVariablesTableRequestList.getRqst_code_ColumName();
		 List<Integer>  listRowForUpdate = OverallVariablesTableRequestList.getListRowForUpdate();
		if (listRowForUpdate.isEmpty()) {
			listRowForUpdate.add(row);
		} else {
			if (!listRowForUpdate.equals(dataTable[row][getIndexColumnByColumnName (table, rqst_code_ColumName)])) {
				listRowForUpdate.add(row);
			}
		}
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
}
