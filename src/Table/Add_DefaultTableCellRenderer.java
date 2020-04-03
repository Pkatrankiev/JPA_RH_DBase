package Table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class Add_DefaultTableCellRenderer  {
	

	public static DefaultTableCellRenderer Add_MyDefaultTableCellRenderer(int active_value_column, int mda_column) {

		
		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer(){
			
			private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				String s2 ="0";
				if(mda_column>0){
					s2 = table.getValueAt(row, mda_column).toString();
				}
					
				String s1 = table.getValueAt(row, active_value_column).toString();
							
				if (Double.compare(Double.parseDouble((String) s1), (Double.parseDouble((String) s2))) == 0) {
					// setBackground(Color.BLACK);
					setForeground(Color.LIGHT_GRAY);
				} else {
					// setBackground(table.getBackground());
					setForeground(table.getForeground());
				}
				return this;
			}
			
	};
		return dcr;
	}
		}

