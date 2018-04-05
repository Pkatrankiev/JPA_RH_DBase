package WindowView;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import WindowViewAplication.RequestViewAplication;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.awt.event.ActionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class SampleAddView extends JDialog {
	private static final String Sys = null;
	private final JScrollPane scrollPane;
	private boolean DEBUG = true;
	private JTable table;
	private String [] str=new String[10];

	/**
	 * Create the dialog.
	 */
	public SampleAddView(Frame parent, int countSample) {
		super(parent, "Информация за пробите", true);
		setBounds(100, 100, 850, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				// table_1 = new JTable();

			}
		}
		String[] columnSize = { "012345", "0123456789", "01234567890123456789012345678901234567890123456789", "0123456789", "0123456789",
		"012345" };
		
		String[] columnNames = { "Код", "Обект на изпитване", "Описание", "Референтна дата", "Период",
		"Година" };
		Object[][] data = setMasiveData(countSample);
		
		table = new JTable(new MyTableModel(columnNames, data));

		// Set up column sizes.
		initColumnSizes(table, columnSize);

		table.setPreferredScrollableViewportSize(new Dimension(850, 70));
		
		
		
		
		
		
 
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		
		// Fiddle with the Sport column's cell editors/renderers.
		setUp_O_I_S_Column(table, table.getColumnModel().getColumn(1), RequestViewAplication.getStringMassiveO_I_S());
		setUp_Period_Column(table, table.getColumnModel().getColumn(4), RequestViewAplication.getStringMassivePeriod());
		setUp_SampleDescription(table, table.getColumnModel().getColumn(2)," ");

		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						for (String  ss : str) {
							System.out.println(ss);	
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private Object[][] setMasiveData(int countSample) {
		Object[][] kkk = new Object[countSample][6];
		
		for (int i = 0; i < countSample; i++) {
			kkk[i][0] ="1234-"+i;
			kkk[i][1] =" ";
			kkk[i][2] ="1";
			kkk[i][3] ="02.04.2018 10:25";
			kkk[i][4] =" ";
			kkk[i][5] = "2018";
					}
		
		return kkk;
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

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		/*
		 * Don't need to implement this method unless your table's editable.
		 */
		public boolean isCellEditable(int row, int col) {
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			if (col < 1) {
				return false;
			} else {
				return true;
			}
		}

		/*
		 * Don't need to implement this method unless your table's data can
		 * change.
		 */
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
	
	

	private void initColumnSizes(JTable table, String[] columnNames) {
		
		 table.getColumnModel().getColumn(0).setPreferredWidth(100);
		 table.getColumnModel().getColumn(1).setPreferredWidth(300);
		 table.getColumnModel().getColumn(2).setPreferredWidth(500);
		 table.getColumnModel().getColumn(3).setPreferredWidth(200);
		 table.getColumnModel().getColumn(4).setPreferredWidth(200);
		 table.getColumnModel().getColumn(5).setPreferredWidth(100);
		 table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
           
		
		
		
		
//		MyTableModel model = (MyTableModel) table.getModel();
//		TableColumn column = null;
//		
//		int f=0;
//		
//		Component comp = null;
//		int headerWidth = 0;
//		int cellWidth = 0;
//		Object[] longValues = model.columnNames;
//		TableCellRenderer headerRenderer = 
//				table.getTableHeader().getDefaultRenderer();
//
//		for (int i = 0; i < longValues.length; i++) {
//			column = table.getColumnModel().getColumn(i);
//
//			comp = headerRenderer.getTableCellRendererComponent(
//					null, column.getHeaderValue(), false, false, 0, 0);
//			headerWidth = comp.getPreferredSize().width;
//
//			comp = table.getDefaultRenderer(model.getColumnClass(i)).
//					getTableCellRendererComponent(table, longValues[i],
//					false, false, 0, i);
//			cellWidth = comp.getPreferredSize().width;
//
//			if (DEBUG) {
//				str[f]="Initializing width of column " + i + ". " + "headerWidth = " + headerWidth
//						+ "; cellWidth = " + cellWidth;
//				f++;
//				System.out.println("Initializing width of column " + i + ". " + "headerWidth = " + headerWidth
//						+ "; cellWidth = " + cellWidth);
//			}
//
//			column.setPreferredWidth(Math.max(headerWidth, cellWidth));
//		}
	}

	public void setUp_O_I_S_Column(JTable table, TableColumn obektIzpitvaneColumn, String[] comboBoxItem) {
		// Set up the editor for the sport cells.
		JComboBox comboBox = new JComboBox();
		for (String string : comboBoxItem) {
			comboBox.addItem(string);
		}

		obektIzpitvaneColumn.setCellEditor(new DefaultCellEditor(comboBox));

		// Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Изберете обекта на изпитване");
		obektIzpitvaneColumn.setCellRenderer(renderer);
	}
	public void setUp_Period_Column(JTable table, TableColumn periodColumn, String[] comboBoxItem) {
		// Set up the editor for the sport cells.
		JComboBox comboBox = new JComboBox();
		for (String string : comboBoxItem) {
			comboBox.addItem(string);
		}

		periodColumn.setCellEditor(new DefaultCellEditor(comboBox));

		// Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Изберете периодичността");
		periodColumn.setCellRenderer(renderer);
	}
	
	public void setUp_SampleDescription(JTable table, TableColumn sampleDescription, String txtAreaItem) {
		// Set up the editor for the sport cells.
		
		sampleDescription.setCellRenderer(new TextAreaCellRenderer()); 
		sampleDescription.setCellEditor(new TextAreaCellEditor(){
		
		
		    });
	
	}
	
}




