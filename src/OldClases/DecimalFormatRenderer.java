package OldClases;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class DecimalFormatRenderer {
	public static DefaultTableCellRenderer Decimal_FormatRenderer(int mda_column) {

		
		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer(){
    private static final long serialVersionUID = 1L;
    private final DecimalFormat formatter = new DecimalFormat( "0.000E00" );

    @SuppressWarnings("unused")
	public void DecimalFormatRenderer(){
      
//        formatter.setMinimumFractionDigits(4);
    }

    public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected,
    		boolean hasFocus, int row, int column) {
                    // First format the cell value as required
    	super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    	
    	if(column==3){
    		 System.out.println(value+"*****************************************************");
    		setForeground(Color.LIGHT_GRAY);
    	if(value instanceof Double){
//    		double dd = (double) value;
    		 value = formatter.format((Number)value);
    		

//    		if( Double.compare(dd , 0.009) < 0){
//            value = formatter.format((Number)value);
//    		}
        }
       }else{
    	   setForeground(table.getForeground());
       }
        return super.getTableCellRendererComponent(
            table, value, isSelected, hasFocus, row, column );
    }
    };
	
	return dcr;
	}
}
