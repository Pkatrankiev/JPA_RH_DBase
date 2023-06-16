package Aplication;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

 class ChangeColor extends JLabel implements TableCellRenderer {
//	private int columnIndex;
//
//	public ChangeColor(int index) {
//		this.columnIndex = index;
//		setOpaque(true);
//	}
//
//	public Component getTableCellRendererComponent(JTable table, Object value,
//			boolean selected, boolean hasFocus, int row, int column) {
//		Object columnValue = table.getValueAt(row, column);
//		if (value != null)
//			setText(value.toString());
//		setBackground(table.getBackground());
//		setForeground(table.getForeground());
//
//		if (columnValue.equals("A"))
//			setBackground(Color.green);
//	
//		return this;
//	}
	
	private String columnName;

	public ChangeColor(String column) {
		this.columnName = column;
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean selected, boolean hasFocus, int row, int column) {
		Object columnValue = table.getValueAt(row, table.getColumnModel()
				.getColumnIndex(columnName));
		if (value != null)
			setText(value.toString());
		setBackground(table.getBackground());
		setForeground(table.getForeground());

		if (columnValue.equals("A"))
			setBackground(Color.green);
		if (columnValue.equals("B"))
			setBackground(Color.red);
		if (columnValue.equals("C"))
			setBackground(Color.blue);
		if (columnValue.equals("D"))
			setBackground(Color.pink);
		if (columnValue.equals("E"))
			setBackground(Color.orange);
		if (columnValue.equals("F"))
			setBackground(Color.yellow);
		return this;
	}	
	
	
	
}