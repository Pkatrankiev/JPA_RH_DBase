package WindowView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.awt.Dimension;
import java.awt.Component;
import javax.swing.BoxLayout;
import java.text.MessageFormat;

public class TablePrintDemo extends JPanel 
                            implements java.awt.event.ActionListener {
    private boolean DEBUG = false;
    private JTable table;
	
	
    
//    private String[] columnNames = {"First Name",
//            "Last Name",
//            "Sport",
//            "# of Years",
//            "Vegetarian"};
//    
//    private Object[][] data = {
//    	    {"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
//    	    {"John", "Doe",  "Rowing", new Integer(3), new Boolean(true)},
//    	    {"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
//    	    {"Jane", "White","Speed reading", new Integer(20), new Boolean(true)},
//    	    {"Joe", "Brown","Pool", new Integer(10), new Boolean(false)}
//            };
    
    public static void createAndShowGUI(String[] columnNames, Object[][] data) {
        //Create and set up the window.
        JFrame frame = new JFrame("TablePrintDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TablePrintDemo newContentPane = new TablePrintDemo(columnNames, data);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    


    public TablePrintDemo(String[] columnNames, Object[][] data) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        MyTableModel MyTable = new MyTableModel(columnNames, data);
        table = new JTable(MyTable);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);

//        //Add a print button.
//        JButton printButton = new JButton("Print");
//        printButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        printButton.addActionListener(this);
//        add(printButton);

    }

    
//	public TablePrintDemo(Object object, Object object2) {
//		// TODO Auto-generated constructor stub
//	}

	class MyTableModel extends AbstractTableModel {
    	
 	
    	private String[] columnNames;
		private Object[][] data;
		
		
		

		public MyTableModel(String[] columnNames, Object[][] data) {
			// TODO Auto-generated constructor stub
    		super();
    		this.columnNames = columnNames;
    		this.data = data;
		
    	}
    	

        public int getColumnCount() {
            return columnNames.length;
        }


		public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
    
   
    
    public void actionPerformed(java.awt.event.ActionEvent ignore) {
        MessageFormat header = new MessageFormat("Page {0,number,integer}");
        try {
            table.print(JTable.PrintMode.FIT_WIDTH, header, null);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Cannot print %s%n", e.getMessage());
        }
    }

	
   
}

