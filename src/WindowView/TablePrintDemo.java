package WindowView;



import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTable;

import javax.swing.JButton;

import javax.swing.JComboBox;

import javax.swing.table.AbstractTableModel;

import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.TableCellRenderer;

import javax.swing.table.TableColumn;



import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.event.ActionListener;

import java.awt.Component;

import javax.swing.BoxLayout;

import javax.swing.DefaultCellEditor;



import java.text.MessageFormat;



public class TablePrintDemo extends JPanel implements java.awt.event.ActionListener {

	private boolean DEBUG = false;

	private JTable table;



	public static void createAndShowGUI(String[] columnNames, Object[][] data) {

		/** Create and set up the window. **/

		JFrame frame = new JFrame("TablePrintDemo");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		/** Create and set up the content pane. **/

		TablePrintDemo newContentPane = new TablePrintDemo(columnNames, data);

		newContentPane.setOpaque(true); // content panes must be opaque

		frame.setContentPane(newContentPane);



		/** Display the window. **/

		frame.pack();

		frame.setVisible(true);

	}


	public TablePrintDemo(String[] columnNames, Object[][] data) {

		super(new GridLayout(1, 0));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



		MyTableModel MyTable = new MyTableModel(columnNames, data);

		table = new JTable(MyTable);

		table.setPreferredScrollableViewportSize(new Dimension(500, 70));

		table.setFillsViewportHeight(true);



		/** Create the scroll pane and add the table to it. **/

		JScrollPane scrollPane = new JScrollPane(table);



		/** Set up column sizes. **/

		 initColumnSizes(table, columnNames);



		/** Fiddle with the Sport column's cell editors/renderers. **/

		setUpSportColumn(table, table.getColumnModel().getColumn(0));



		/** Add the scroll pane to this panel. **/

		add(scrollPane);



		/** Add a print button. **/

		JButton printButton = new JButton("Print");

		printButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		printButton.addActionListener(new ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent ignore) 
			
			{	
			MessageFormat header = new MessageFormat("Page {0,number,integer}");

			try {

				table.print(JTable.PrintMode.FIT_WIDTH, header, null);

			} catch (java.awt.print.PrinterException e) {

				System.err.format("Cannot print %s%n", e.getMessage());

			}
			}
			
			
			
			
			
//
//				
////				printTableData(table);
//
//
//
//			}

		});

		add(printButton);



	}



	private void initColumnSizes(JTable table, String[] columnNames) {

		

		MyTableModel model =(MyTableModel) table.getModel();

		 TableColumn column = null;

		

		 Component comp = null;

		 int headerWidth = 0;

		 int cellWidth = 0;

		 Object[] longValuess = model.columnNames;

		 TableCellRenderer headerRenderer =

		 table.getTableHeader().getDefaultRenderer();

		

		 for (int i = 0; i < longValuess.length; i++) {

		 column = table.getColumnModel().getColumn(i);

		

		 comp = headerRenderer.getTableCellRendererComponent(

		 null, column.getHeaderValue(),

		 false, false, 0, 0);

		 headerWidth = comp.getPreferredSize().width;

		

		 comp = table.getDefaultRenderer(model.getColumnClass(i)).

		 getTableCellRendererComponent(

		 table, longValuess[i],

		 false, false, 0, i);

		 cellWidth = comp.getPreferredSize().width;

		

		 if (DEBUG) {

		 System.out.println("Initializing width of column "

		 + i + ". "

		 + "headerWidth = " + headerWidth

		 + "; cellWidth = " + cellWidth);

		 }

		 }

		 column.setPreferredWidth(Math.max(headerWidth, cellWidth));

//		 column.sizeWidthToFit(); //or simple

	}



	public void setUpSportColumn(JTable table, TableColumn sportColumn) {



		/** Set up the editor for the sport cells. **/

		JComboBox comboBox = new JComboBox();

		comboBox.addItem("Snowboarding");

		comboBox.addItem("Rowing");

		comboBox.addItem("Knitting");

		comboBox.addItem("Speed reading");

		comboBox.addItem("Pool");

		comboBox.addItem("None of the above");

		sportColumn.setCellEditor(new DefaultCellEditor(comboBox));



		/** Set up tool tips for the sport cells. **/

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

		renderer.setToolTipText("Click for combo box");

		sportColumn.setCellRenderer(renderer);

	}



	private void printTableData(JTable table) {

		int numRows = table.getRowCount();

		int numCols = table.getColumnCount();



		for (int i = 0; i < numRows; i++) {

			System.out.print("    row " + i + ":");

			for (int j = 0; j < numCols; j++) {

				System.out.print("  " + table.getValueAt(i, j));

			}

			System.out.println();

		}

		System.out.println("--------------------------");

	}



	class MyTableModel extends AbstractTableModel {



		private String[] columnNames;

		private Object[][] data;

		

		public MyTableModel(String[] columnNames, Object[][] data) {



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



		/**

		 * JTable uses this method to determine the default renderer/ editor for

		 * each cell. If we didn't implement this method, then the last column

		 * would contain text ("true"/"false"), rather than a check box.

		 **/

		public Class getColumnClass(int c) {

			return getValueAt(0, c).getClass();

		}



		/**

		 * Don't need to implement this method unless your table's editable.

		 **/

		public boolean isCellEditable(int row, int col) {

			/**

			 * Note that the data/cell address is constant, no matter where the

			 * cell appears onscreen.

			 **/

			if (col < 1) {

				return false;

			} else {

				return true;

			}

		}



		/**

		 * Don't need to implement this method unless your table's data can

		 * change.

		 **/

		public void setValueAt(Object value, int row, int col) {

			if (DEBUG) {

				System.out.println("Setting value at " + row + "," + col + " to " + value

						+ " (an instance of " + value.getClass() + ")");

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



			for (int i = 0; i < numRows; i++) {

				System.out.print("    row " + i + ":");

				for (int j = 0; j < numCols; j++) {

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