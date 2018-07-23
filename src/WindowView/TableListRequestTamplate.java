package WindowView;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

import javax.swing.JComboBox;

import javax.swing.table.AbstractTableModel;

import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.TableCellRenderer;

import javax.swing.table.TableColumn;

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

public class TableListRequestTamplate extends JPanel implements java.awt.event.ActionListener {
	private boolean DEBUG = true;
	private JPanel panel_Main;
	private int k = 0;

	public static void createTable(String[] columnNames, Object[][] data) {

		/** Create and set up the window. **/
		JFrame frame = new JFrame("TablePrintDemo");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/** Create and set up the content pane. **/
		TableListRequestTamplate newContentPane = new TableListRequestTamplate(columnNames, data);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		/** Display the window. **/
		frame.pack();
		frame.setVisible(true);

	}

	public TableListRequestTamplate(String[] columnNames, Object[][] data) {
		super(new GridLayout(1, 0));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		int[] columnSizes = initColumnSizes(columnNames);
		panel_Main = new JPanel();
		panel_Main.setSize(new Dimension(900, 170));
		panel_Main.setLayout(new BoxLayout(panel_Main, BoxLayout.Y_AXIS));
		/** Create the scroll pane and add the table to it. **/
		JScrollPane scrollPane = new JScrollPane(panel_Main);
		JPanel[] panel_Label = new JPanel[data.length+1];
		JLabel[][] lbl_collum = new JLabel[data.length+1][columnNames.length];
		
		panel_Label[0] = new JPanel();
		panel_Main.add(panel_Label[0]);
		for (int collum_index = 0; collum_index < columnNames.length; collum_index++) {
			
			lbl_collum[0][collum_index] = new JLabel(columnNames[collum_index].toString());
			lbl_collum[0][collum_index].setPreferredSize(new Dimension(columnSizes[collum_index]*10+10, 20));
			lbl_collum[0][collum_index].setHorizontalAlignment(JLabel.CENTER);
			lbl_collum[0][collum_index].setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label[0].add(lbl_collum[0][collum_index]);
		}
		for (int row_index = 1; row_index <= data.length; row_index++) {
			panel_Label[row_index] = new JPanel();
			panel_Main.add(panel_Label[row_index]);
			
			for (int collum_index = 0; collum_index < columnNames.length; collum_index++) {
	
			lbl_collum[row_index][collum_index] = new JLabel(data[row_index-1][collum_index].toString());
			lbl_collum[row_index][collum_index].setPreferredSize(new Dimension(columnSizes[collum_index]*10+10, 20));
			lbl_collum[row_index][collum_index].setHorizontalAlignment(JLabel.CENTER);
			lbl_collum[row_index][collum_index].setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label[row_index].add(lbl_collum[row_index][collum_index]);
		}
		}
	

		// initListenerCells(data);

		/** Add the scroll pane to this panel. **/
		add(scrollPane);

		/** Add a print button. **/
		// addPrintButton();
	}

	private int[] initColumnSizes(String[] columnNames) {
		int[] columWidth = new int[columnNames.length];
		for (int i = 0; i < columnNames.length; i++) {
			columWidth[i] = columnNames[i].length();
			System.out.println(columWidth[i]);
		}
		return columWidth;
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

	public void setLabelColumn(JTable table, TableColumn labelColumn) {

		/** Set up the editor for the sport cells. **/

		JLabel labelBox = new JLabel();

		/** Set up tool tips for the sport cells. **/

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

		renderer.setToolTipText("Click for choice");
		// renderer.addMouseWheelListener(l);
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

		/**
		 * 
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 * 
		 **/

		public Class getColumnClass(int c) {

			return getValueAt(0, c).getClass();

		}

		/**
		 * 
		 * Don't need to implement this method unless your table's editable.
		 * 
		 **/

		public boolean isCellEditable(int row, int col) {

			/**
			 * 
			 * Note that the data/cell address is constant, no matter where the
			 * cell appears onscreen.
			 * 
			 **/

			if (col != 0) {

				return false;

			} else {

				return true;

			}

		}

		/**
		 * 
		 * Don't need to implement this method unless your table's data can
		 * 
		 * change.
		 * 
		 **/

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}