package TableBeisicClassDBase;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Aplication.DimensionDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.NuclideDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Users;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.RequestMiniFrame;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class ViewTableBeisicClassDBase extends JDialog {

	private static List<Integer> listWhithChangeRow;
	private static Object[][] dataTable;
	
	public ViewTableBeisicClassDBase(JFrame parent, TranscluentWindow round, Users user,
			String[] columnNames, Class[] types, Object[][] data_Table) {
		super(parent, "", true);

		

		setTitle(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("DBSTCN_Ind_num_doc_TableTitle"));
		
		dataTable = data_Table;
		listWhithChangeRow = new ArrayList<Integer>() ;
		final JTable table = new JTable();// new DefaultTableModel(rowData,
											// columnNames));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {
				DefaultTableModel model =(DefaultTableModel) table.getModel();
			
				
				if (e.getClickCount() == 2 && getSelectedModelRow(table) != -1) {
					model.getValueAt(getSelectedModelRow(table), 0 ).toString();
							}
							}
		});

		new TableFilterHeader(table, AutoChoices.ENABLED);

		
		
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setSize(1200, 800);
		setLocationRelativeTo(null);
		round.StopWindow();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(dataTable, columnNames) {

					private static final long serialVersionUID = 1L;
					@SuppressWarnings("rawtypes")
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
								if (column == 0){
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

							
				

				JPanel panel_Btn = new JPanel();
				panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
				getContentPane().add(panel_Btn, BorderLayout.SOUTH);
				panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

				JButton btnSave = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("saveBtn_Text"));
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						TranscluentWindow round = new TranscluentWindow();
						final Thread thread = new Thread(new Runnable() {
							@Override
							public void run() {
								updateData(table, round);
							}
						});
						thread.start();

					}
				});
				btnSave.setHorizontalAlignment(SwingConstants.RIGHT);
				btnSave.setAlignmentX(Component.RIGHT_ALIGNMENT);
				if (user != null && user.getIsAdmin()) {
					panel_Btn.add(btnSave);
				}

				JButton btnCancel = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				panel_Btn.add(btnCancel);

			}
		});
		
		setVisible(true);
	}
	private static void AddInUpdateList(int row) {
		if (listWhithChangeRow.isEmpty()) {
			listWhithChangeRow.add(row);
		} else {
			
		Boolean fl=false;
			for (int i = 0; i < listWhithChangeRow.size(); i++) {
			if (listWhithChangeRow.get(i) == row) {
				fl=true;
			}
			}
			if(!fl){
				listWhithChangeRow.add(row);
			}
		}
		System.out.println(row);
	}

	private int getSelectedModelRow(JTable table) {
		return  table.convertRowIndexToModel(table.getSelectedRow());
		}
	private static void updateData(JTable table, TranscluentWindow round) {
		
		for (int changeRow : listWhithChangeRow) {
			Results result = ResultsDAO.getValueResultsById((int) dataTable[changeRow][0]);
//			udateResultObject(table, changeRow, result);
				}
				round.StopWindow();
	}
	

	
}
