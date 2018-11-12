package Table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Aplication.Ind_num_docDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.UsersDAO;
import Aplication.ZabelejkiDAO;
import DBase_Class.Request;
import WindowView.ExtraRequestView;
import WindowView.Login;
import WindowView.RequestMiniFrame;
import WindowView.RequestView;
import WindowView.RequestViewAplication;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class TableInternalAplicantList {

	private static String[] values_address;
	private static String[] values_organiz;
	private static String[] values_teleph;
	private static JFrame frame;
	private static Object[][] dataTable;
	
	private static int tbl_Colum = 3;
	private static int aplic_address_Colum = 0;
	private static int aplic_organiz_Colum = 1;
	private static int aplic_teleph_Colum = 2;
	
	public static void TableRequestList(String[] columnNames, Object[][] data, Class[] types,TranscluentWindow round) {
		frame = new JFrame("Списък на Заявките");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dataTable = data;

		final JTable table = new JTable();// new DefaultTableModel(rowData,
											// columnNames));

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
			
				if (table.getSelectedColumn() == izp_Pok_Colum  && Login.getCurentUser() != null
						&& Login.getCurentUser().getIsAdmin()) {
					int rowPokazatel = table.rowAtPoint(e.getPoint());
					EditColumnPokazatel(table, rowPokazatel);

					AddInUpdateList(rowPokazatel);
				}

				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {

					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					String reqCodeStr = table.getValueAt(table.getSelectedRow(), rqst_code_Colum ).toString();

					if (reqCodeStr.startsWith("templ")) {
						choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
						if(choiseRequest.getExtra_module()!=null){
							new ExtraRequestView(Login.getCurentUser(), choiseRequest, round);
						}else{
						new RequestView(Login.getCurentUser(), choiseRequest, round);
						}
						frame.setVisible(false);

					} else {
						RequestViewAplication.OpenRequestInWordDokTamplate(reqCodeStr);
					}
				}
			}
		});

		TableFilterHeader tfh = new TableFilterHeader(table, AutoChoices.ENABLED);

		JScrollPane scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setSize(1200, 800);
		frame.setLocationRelativeTo(null);
		
		round.StopWindow();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(dataTable, columnNames) {

					private Class[] types2 = types;

					@Override
					public Class getColumnClass(int columnIndex) {
						return this.types2[columnIndex];
					}

					public Object getValueAt(int row, int col) {
						return dataTable[row][col];
					}

					@Override
					public boolean isCellEditable(int row, int column) {
						if (Login.getCurentUser() != null && Login.getCurentUser().getIsAdmin()) {
							if (Login.getCurentUser().getIsAdmin()) {
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
							if (col == user_Colum ) {
								EditColumnUser(value, row);
							}

							AddInUpdateList(row);
						}
					}

				};
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				setUp_Id_Num_Doc(table, table.getColumnModel().getColumn(id_ND_Colum ));
				setUp_I_P_Column(table, table.getColumnModel().getColumn(izp_Prod_Colum ));
				setUp_O_I_R_Column(table, table.getColumnModel().getColumn(obk_Izp_Colum ));
				setUp_Razmernosti(table, table.getColumnModel().getColumn(razmer_Colum ));
				setUp_Users(table, table.getColumnModel().getColumn(user_Colum ));
				setUp_Zabelejki(table, table.getColumnModel().getColumn(zab_Colum ));

				JPanel panel_Btn = new JPanel();
				panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
				frame.getContentPane().add(panel_Btn, BorderLayout.SOUTH);
				panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

				JButton btnSave = new JButton("Запис");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						TranscluentWindow round = new TranscluentWindow();
						
						 final Thread thread = new Thread(new Runnable() {
						     @Override
						     public void run() {
						    	 
						    	 updateData(table, listStrRequestCode, round);
					    	
						     }
						    });
						    thread.start();
						
						
						
					}
				});
				btnSave.setHorizontalAlignment(SwingConstants.RIGHT);
				btnSave.setAlignmentX(Component.RIGHT_ALIGNMENT);
				if (Login.getCurentUser() != null && Login.getCurentUser().getIsAdmin()) {
					panel_Btn.add(btnSave);
				}
				JButton btnCancel = new JButton("Изход");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frame.setVisible(false);
					}
				});
				panel_Btn.add(btnCancel);
			}
		});
		frame.setVisible(true);
	}
	
	
	
}
