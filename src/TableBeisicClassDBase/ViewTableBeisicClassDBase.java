package TableBeisicClassDBase;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Aplication.Ind_num_docDAO;
import DBase_Class.Ind_num_doc;
import DBase_Class.Users;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class ViewTableBeisicClassDBase extends JDialog {

	private static final long serialVersionUID = 1L;
	private static List<Integer> listWhithChangeRow;
	private static Object[][] dataTable;
	@SuppressWarnings("unused")
	private static ArrayList<String>[] words;

	@SuppressWarnings("rawtypes")
	public ViewTableBeisicClassDBase(JFrame parent, TranscluentWindow round, Users user, String titleTable,
			String[] columnNames, Class[] types, Object[][] data_Table, int[] columnSize, String key, Point point) {
		super(parent, "", true);

		int withPluss = 100;
		int rowTable = data_Table.length;

		int with = (rowTable * 25) + withPluss;

		setTitle(titleTable);
		words = createChekWords(data_Table);
		dataTable = data_Table;
		listWhithChangeRow = new ArrayList<Integer>();
		final JTable table = new JTable();// new DefaultTableModel(rowData,
											// columnNames));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				if (e.getClickCount() == 2 && getSelectedModelRow(table) != -1) {
					model.getValueAt(getSelectedModelRow(table), 0).toString();
				}
			}
		});

		new TableFilterHeader(table, AutoChoices.ENABLED);

		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

//		setLocationRelativeTo(null);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(dataTable, columnNames) {

					private static final long serialVersionUID = 1L;
					private Class[] types2 = types;

					@SuppressWarnings({})
					@Override
					public Class<?> getColumnClass(int columnIndex) {
						return this.types2[columnIndex];
					}

					public Object getValueAt(int row, int col) {
						return dataTable[row][col];
					}

					@Override
					public boolean isCellEditable(int row, int column) {
						if (user != null && user.getIsAdmin()) {
							if (user.getIsAdmin()) {
								if (column == 0) {
									return false;
								}
								return true;

							} else {
								return false;
							}
						} else {
							return false;
						}
					}

					public void setValueAt(Object value, int row, int col) {

						if (!dataTable[row][col].equals(value)) {
							dataTable[row][col] = value;
							fireTableCellUpdated(row, col);
							AddInUpdateList(row);
						}
					}

					public int getColumnCount() {
						return columnNames.length;
					}

					public int getRowCount() {
						return dataTable.length;
					}

				};

				table.setModel(dtm);
				table.setFillsViewportHeight(true);

				initColumnSizes(table, columnSize);

				JPanel panel_Btn = new JPanel();
				panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
				getContentPane().add(panel_Btn, BorderLayout.SOUTH);
				panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

				JButton btnAdd = new JButton(
						ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("DBSTCN_btnADD_Text"));

				JButton btnDelete = new JButton(
						ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("DBSTCN_btnDELETE_Text"));

				JButton btnSave = new JButton(
						ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("saveBtn_Text"));

				JButton btnCancel = new JButton(
						ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));

				btnAddListener( user, titleTable, columnNames, types, columnSize, key, rowTable, btnAdd);
				btnDeleteListener(table, user, key, columnNames, types, columnSize, key, btnDelete);
				btnSaveListener(parent, key, table, btnSave);
				btnCancelListener(btnCancel);

				if (user != null && user.getIsAdmin()) {
					if (key.indexOf("+add") > 0) {
						panel_Btn.add(btnSave);
					} else {
						panel_Btn.add(btnDelete);
						panel_Btn.add(btnAdd);
						panel_Btn.add(btnSave);
					}
				}
				panel_Btn.add(btnCancel);

			}

		});
		setSize(columnSize[columnSize.length - 1], with);
		if(point==null){
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		}else{
		setLocation(point);
		}
		
		round.StopWindow();
		setVisible(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ArrayList<String>[] createChekWords(Object[][] data_Table) {
		int col = data_Table[0].length;
		int row = data_Table.length;
		ArrayList<String> list[] = new ArrayList[col];
		for (int i = 0; i < col; i++) {
			list[i] = new ArrayList();
			for (int j = 0; j < row; j++) {
				list[i].add(data_Table[j][i].toString());
			}
		}
		

		return list;
	}

	private static void AddInUpdateList(int row) {
		if (listWhithChangeRow.isEmpty()) {
			listWhithChangeRow.add(row);
		} else {

			Boolean fl = false;
			for (int i = 0; i < listWhithChangeRow.size(); i++) {
				if (listWhithChangeRow.get(i) == row) {
					fl = true;
				}
			}
			if (!fl) {
				listWhithChangeRow.add(row);
			}
		}
		System.out.println(row);
	}

	private int getSelectedModelRow(JTable table) {
		return table.convertRowIndexToModel(table.getSelectedRow());
	}

	private void btnAddListener(Users user, String titleTable, String[] columnNames,
			@SuppressWarnings("rawtypes") Class[] types, int[] columnSize, String key, int rowTable, JButton btnAdd) {
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewAddNewObject newObject = new ViewAddNewObject(new JFrame(), titleTable, dataTable, columnNames, types, key, rowTable);
				if(!newObject.gettCancellPresset()){
				
					Point point = getLocationOnScreen();	
				setVisible(false);
				refreshTable(user, titleTable, columnNames, types, columnSize, key,  point);
				}
			}

			

		});
	}

	private void    refreshTable( Users user, String titleTable, String[] columnNames, @SuppressWarnings("rawtypes") Class[] types, int[] columnSize, String key, Point point) {
		
		TranscluentWindow round = new TranscluentWindow();
		
		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Object[][] data_Table = null;
				JFrame f = new JFrame();
				switch (key) {
				case "Ind_num_doc":
					data_Table = Ind_num_docDAO.getAll_Ind_num_doc_ForTable();
					break;

				default:
					break;
				}
				
				new ViewTableBeisicClassDBase(f, round, user, titleTable, columnNames, types, data_Table,	columnSize, key, point);
				
			}
		});
		thread.start();
			
	
		
	}
	
	
	
	private void btnSaveListener(JFrame f, String key, final JTable table, JButton btnSave) {
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(key);
				updateData(table, key);
			}

		});
	}

	private void btnDeleteListener(final JTable table, Users user, String titleTable, String[] columnNames,
			@SuppressWarnings("rawtypes") Class[] types, int[] columnSize, String key, JButton btnDelete) {
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if (row < 0) {
					infoDialog();
				} else if (DeleteDialog(model.getValueAt(row, 2).toString()) == 0) {
					Ind_num_doc object = Ind_num_docDAO.getValueInd_num_docById((int) model.getValueAt(row, 0));
					Ind_num_docDAO.DeleteInd_num_doc(object);
					Point point = getLocationOnScreen();	
					setVisible(false);
					refreshTable(user, titleTable, columnNames, types, columnSize, key,  point);
				}

			}

		});
	}

	private void btnCancelListener(JButton btnCancel) {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
	}

	private static void updateData(JTable table, String key) {

		for (int changeRow : listWhithChangeRow) {

			switch (key) {
			case "Ind_num_doc":
				Ind_num_doc objectChange = Ind_num_docDAO.getValueInd_num_docById((int) dataTable[changeRow][0]);
				udate_Ind_num_doc_Object(table, changeRow, objectChange);
				break;

			default:
				break;
			}

		}

	}


	
	
	private void initColumnSizes(JTable table, int[] columnSize) {
		int cols = table.getColumnCount();
		TableColumn column = null;

		for (int i = 0; i < cols; i++) {

			column = table.getColumnModel().getColumn(i);

			column.setPreferredWidth(columnSize[i]);
			// column.sizeWidthToFit(); //or simple
		}
	}

	private static void udate_Ind_num_doc_Object(JTable table, int row, Ind_num_doc object) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		object.setContent(model.getValueAt(row, 1).toString());
		object.setName(model.getValueAt(row, 2).toString());

		Ind_num_docDAO.updateInd_num_doc(object);
	}

	public static void infoDialog() {
		JFrame jf = new JFrame();
		jf.setAlwaysOnTop(true);

		JOptionPane.showMessageDialog(jf,
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("DBSTCN_InfoDialog_Text"), "",
				JOptionPane.PLAIN_MESSAGE);
	}

	public static int DeleteDialog(String textObjekt) {
		String[] options = { ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Yes_Btn_Text"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("No_Btn_Text") };

		int x = JOptionPane.showOptionDialog(null,
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("DBSTCN_Delete_Text") + textObjekt, "",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		System.out.println(x);
		return x;
	}
}
