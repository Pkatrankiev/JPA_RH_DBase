package WindowView;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;

import javax.swing.JComboBox;

import javax.swing.table.AbstractTableModel;

import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.TableCellRenderer;

import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import WindowViewAplication.RequestViewAplication;

import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;

import javax.swing.DefaultCellEditor;

import java.text.MessageFormat;

public class TablePrintDemo extends JPanel implements java.awt.event.ActionListener {
	private boolean DEBUG = true;
	private JTable table;
	private int k = 0;
	 private JTextField filterText;
	 private TableRowSorter<MyTableModel> sorter;

	public static void createAndShowGUI(String[] columnNames, Object[][] data) {

		/** Create and set up the window. **/
		JFrame frame = new JFrame("TablePrintDemo");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		table.setPreferredScrollableViewportSize(new Dimension(900, 470));
		table.setFillsViewportHeight(true);
		sorter = new TableRowSorter<MyTableModel>(MyTable);
//		table.setAutoCreateRowSorter(true);
		table.setRowSorter(sorter);
		
	     JPanel form = new JPanel();
	        form.setLayout(new BoxLayout(form, BoxLayout.X_AXIS));
	        form.setMaximumSize(new Dimension(800, 20));
	        JLabel l1 = new JLabel("Filter Text:", SwingConstants.TRAILING);
	        form.add(l1);
	        filterText = new JTextField();
	        //Whenever filterText changes, invoke newFilter.
	        filterText.getDocument().addDocumentListener(
	                new DocumentListener() {
	                    public void changedUpdate(DocumentEvent e) {
	                        newFilter();
	                    }
	                    public void insertUpdate(DocumentEvent e) {
	                        newFilter();
	                    }
	                    public void removeUpdate(DocumentEvent e) {
	                        newFilter();
	                    }
	                });
	        l1.setLabelFor(filterText);
	        form.add(filterText);
	        add(form);
	        
		/** Create the scroll pane and add the table to it. **/
		JScrollPane scrollPane = new JScrollPane(table);

		/** Set up column sizes. **/
		initColumnSizes(table, columnNames);

		/** Fiddle with the Sport column's cell editors/renderers. **/
		setLabelColumn(table, table.getColumnModel().getColumn(1));

//		initListenerCells(data);
		
		/** Add the scroll pane to this panel. **/
		add(scrollPane);
		
	      //Create a separate form for filterText and statusText
   
    

		/** Add a print button. **/
		// addPrintButton();
	}
private void newFilter() {
    RowFilter<MyTableModel, Object> rf = null;
    //If current expression doesn't parse, don't update.
    try {
        rf = RowFilter.regexFilter(filterText.getText(), 0);
    } catch (java.util.regex.PatternSyntaxException e) {
        return;
    }
    sorter.setRowFilter(rf);
}
	private void addPrintButton() {
		JButton printButton = new JButton("Print");
		printButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent ignore) {
				MessageFormat header = new MessageFormat("Page {0,number,integer}");
				try {
					table.print(JTable.PrintMode.FIT_WIDTH, header, null);
				} catch (java.awt.print.PrinterException e) {
					System.err.format("Cannot print %s%n", e.getMessage());
				}

				// printTableData(table);
			}
		});

		add(printButton);
	}

	private void initListenerCells(Object[][] data) {

		for (int i = 0; i < data.length; i++) {
			k = i;
			((AbstractButton) data[0][k]).addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent ignore) {
					System.out.println("**-----************-***" + data[0][k]);
				}
			});
		}
	}

	private void initColumnSizes(JTable table, String[] columnNames) {
		MyTableModel model = (MyTableModel) table.getModel();
		TableColumn column = null;
		Component comp = null;
		int headerWidth = 0;
		int cellWidth = 0;
		Object[] longValuess = model.columnNames;
		TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
		for (int i = 0; i < longValuess.length; i++) {
			column = table.getColumnModel().getColumn(i);
			comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
			headerWidth = comp.getPreferredSize().width;
			comp = table.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(table,
					longValuess[i], false, false, 0, i);
			cellWidth = comp.getPreferredSize().width;
			if (DEBUG) {
				System.out.println("Initializing width of column " + i + ". " + "headerWidth = " + headerWidth
						+ "; cellWidth = " + cellWidth);
			}
		}
		column.setPreferredWidth(Math.max(headerWidth, cellWidth));

		// column.sizeWidthToFit(); //or simple

	}

	public void setUpSportColumn5(JTable table, TableColumn sportColumn) {
JComboBox comboBox = new JComboBox();
		comboBox.addItem("Snowboarding");
		comboBox.addItem("Rowing");
		comboBox.addItem("Knitting");
		comboBox.addItem("Speed reading");
		comboBox.addItem("Pool");
		comboBox.addItem("None of the above");
		sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for combo box");
		sportColumn.setCellRenderer(renderer);

	}

	public void setLabelColumn(JTable table, TableColumn labelColumn) {
			JLabel labelBox = new JLabel();
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for choice");
//		renderer.addMouseWheelListener(l);
		labelColumn.setCellRenderer(renderer);
		renderer.addMouseWheelListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				renderer.setText("4444");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				renderer.setBackground(Color.WHITE);
			}
			public void mousePressed(MouseEvent e) {
				System.out.print(" //////////////// " + renderer.getName());
					}

		});

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
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}
		public boolean isCellEditable(int row, int col) {
			if (col != 0) {
				return false;
			} else {
				return true;
			}
		}


		public void setValueAt(Object value, int row, int col) {
			if (DEBUG) {
				System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of "
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