package WindowView;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import java.awt.*;

public class TableRequestList {

	public static void TableRequestList(){
	       JFrame frame = new JFrame();
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        final JTable table = new JTable();//new DefaultTableModel(rowData, columnNames));

	        new TableFilterHeader(table, AutoChoices.ENABLED);

	        JScrollPane scrollPane = new JScrollPane(table);
	        frame.add(scrollPane, BorderLayout.CENTER);
	        frame.setSize(300, 150);
	        frame.setVisible(true);

	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                Object rowData[][] = {{"Str1", true, new Double(1)},
	                        {"Str2", true, new Double(2)},
	                        {"Str3", false, new Double(3)},
	                        {"Str4", true, new Double(4)},
	                        {"Str5", false, new Double(5)}};
	                Object columnNames[] = {"String", "BOO", "Double"};

	                DefaultTableModel dtm = new DefaultTableModel(rowData, columnNames);

	                table.setModel(dtm);

	            }
	        });
	}

	
	
}
