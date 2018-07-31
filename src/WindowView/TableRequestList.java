package WindowView;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import java.awt.*;


public class TableRequestList {

	public static void TableRequestList(String[] columnNames, Object[][] data){
	       JFrame frame = new JFrame();
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        final JTable table = new JTable();//new DefaultTableModel(rowData, columnNames));
	        
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

	
	
}
