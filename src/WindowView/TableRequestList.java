package WindowView;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import WindowView.TablePrintDemo.MyTableModel;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class TableRequestList {

	public static void TableRequestList(String[] columnNames, Object[][] data){
	       JFrame frame = new JFrame();
//	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        final JTable table = new JTable();//new DefaultTableModel(rowData, columnNames));
	        table.setEnabled(false);
//	        MyTableModel model = (MyTableModel) table.getModel();
	        table.addMouseListener(new MouseAdapter() {
//	        	@Override
//				public void mouseEntered(MouseEvent e) {
//					panel_3.setBackground(Color.LIGHT_GRAY);
//				}
//
//				@Override
//				public void mouseExited(MouseEvent e) {
//					model.setRowColour(1, Color.YELLOW);
//					panel_3.setBackground(Color.WHITE);
//				}
	        	public void mousePressed(MouseEvent e) {
	        		if (e.getClickCount() == 2&& table.getSelectedRow() != -1) {
	        	          
	        	int row = table.getSelectedRow();
	        	int col = table.getSelectedColumn();
	        	System.out.println("selekt row:"+row+" col: "+col);
	        	System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
	        		}
	        	}
	        	});
	        
	        TableFilterHeader tfh = new TableFilterHeader(table, AutoChoices.ENABLED);
	      	      
	        JScrollPane scrollPane = new JScrollPane(table);
	        frame.add(scrollPane, BorderLayout.CENTER);
	        frame.setSize(300, 150);
	        frame.setVisible(true);

	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	             DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
	                table.setModel(dtm);

	            }
	        });
	}

//	public void setRowColour(int row, Color c) {
//		List<Color> rowColours = Arrays.asList(
//		        Color.RED,
//		        Color.GREEN,
//		        Color.CYAN
//		    );
//        rowColours.set(row, c);
//        fireTableRowsUpdated(row, row);
//    }
	  
}
