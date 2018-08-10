package WindowView;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Aplication.RequestDAO;
import DBase_Class.Request;
import WindowView.TablePrintDemo.MyTableModel;
import WindowViewAplication.RequestViewAplication;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;


public class TableRequestList {
	private static Request choiseRequest ;

	public static void TableRequestList(String[] columnNames, Object[][] data, Class[] types){
	       JFrame frame = new JFrame();
//	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        final JTable table = new JTable();//new DefaultTableModel(rowData, columnNames));

	        table.addMouseListener(new MouseAdapter() {

	        	public void mousePressed(MouseEvent e) {
	        		if (e.getClickCount() == 2&& table.getSelectedRow() != -1) {
	        	          
	        	int row = table.getSelectedRow();
	        	int col = table.getSelectedColumn();
	        	System.out.println("selekt row:"+row+" col: "+col);
	        	String reqCodeStr = table.getValueAt(table.getSelectedRow(), 0).toString();
	        	
	        	if(reqCodeStr.startsWith("templ")){
	        		choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
	        		RequestView reqView = new RequestView(Login.getCurentUser(), choiseRequest);
	        		 frame.setVisible(false);
					
	        	}else{
	        	RequestViewAplication.OpenRequestInWordDokTamplate(reqCodeStr);
	        	}
	        		}
	        	}
	        	});
	        
	        TableFilterHeader tfh = new TableFilterHeader(table, AutoChoices.ENABLED);
	      	    
	     
	        
	        JScrollPane scrollPane = new JScrollPane(table);
	        frame.add(scrollPane, BorderLayout.CENTER);
	        frame.setSize(1200, 800);
	        frame.setVisible(true);

	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	             DefaultTableModel dtm = new DefaultTableModel(data, columnNames){
	            	
	            	    private  Class[] types2 = types;


						@Override
	            	    public Class getColumnClass(int columnIndex) {
	            	        return this.types2[columnIndex];
	            	    } 
	            	 
	            	 @Override
	            	    public boolean isCellEditable(int row, int column) {
	            	        return false;
	            	    }
	            	    
	            	};
	                table.setModel(dtm);

	            }
	        });
	}

	public static Request getChoiceRequest() {
		return choiseRequest;
}
	 
	  
}
