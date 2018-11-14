package Table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Aplication.Internal_applicantDAO;
import DBase_Class.Internal_applicant;
import DBase_Class.Users;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class TableInternalApplicantList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static String[] values_address;
	private static String[] values_organiz;
	private static String[] values_teleph;

	private static List<Integer> listStrUpdateIntAplic;
	private static Object[][] dataTable;
	private static Internal_applicant int_Aplic = null;

	private static int tbl_Colum = 4;
	private static int aplic_organiz_Colum = 0;
	private static int aplic_address_Colum = 1;
	private static int aplic_teleph_Colum = 2;
	private static int aplic_Id_Colum = 3;

	public TableInternalApplicantList(JDialog parent, TranscluentWindow round, Users user) {

		super(parent, "");
		// setModal(true);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		pack();

		setTitle("������ �� �������� �������");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		// ------------------------------------------------

		dataTable = getDataTable();
		listStrUpdateIntAplic = new ArrayList<Integer>();

		final JTable table = new JTable();// new DefaultTableModel(rowData,
											// columnNames));

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {

					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					int id_Aplic = (int) table.getValueAt(table.getSelectedRow(), aplic_Id_Colum);
					int_Aplic = Internal_applicantDAO.getValueInternal_applicantById(id_Aplic);

				}
			}
		});

		TableFilterHeader tfh = new TableFilterHeader(table, AutoChoices.ENABLED);

		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setSize(1200, 800);
		setLocationRelativeTo(null);

		String[] columnNames = getTabHeader();
		Class[] types = getTypes();
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
						if (user != null && user.getIsAdmin()) {
							if (user.getIsAdmin()) {
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

				};
				table.setModel(dtm);
				table.setFillsViewportHeight(true);

				JPanel panel_Btn = new JPanel();
				panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
				getContentPane().add(panel_Btn, BorderLayout.SOUTH);
				panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				JButton btnSave = new JButton("�����");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						TranscluentWindow round = new TranscluentWindow();
						final Thread thread = new Thread(new Runnable() {
							@Override
							public void run() {

								updateData(table, listStrUpdateIntAplic, round);
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
				JButton btnCancel = new JButton("�����");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				panel_Btn.add(btnCancel);
			}
		});

		// -------------------------------------------------

		setVisible(true);
		repaint();
	}

	private Object[][] getDataTable() {
		List<Internal_applicant> listAllIntApplic = Internal_applicantDAO.getInListAllInternal_applicant();
		String[] tableHeader = { "�����������", "�����", "�������", "Id" };

		Object[][] tableSample = new Object[listAllIntApplic.size()][tbl_Colum];
		int i = 0;
		for (Internal_applicant intApplic : listAllIntApplic) {
			try {
				tableSample[i][aplic_organiz_Colum] = intApplic.getInternal_applicant_organization();
				tableSample[i][aplic_address_Colum] = intApplic.getInternal_applicant_address();
				tableSample[i][aplic_teleph_Colum] = intApplic.getInternal_applicant_telephone();
				tableSample[i][aplic_Id_Colum] = intApplic.getId_internal_applicant();
				i++;
			} catch (NumberFormatException e) {
				JOptionPane.showInputDialog("������ ����� �� ��������:", JOptionPane.ERROR_MESSAGE);
			}
		}
		return tableSample;
	}

	private Class[] getTypes() {
		@SuppressWarnings("rawtypes")
		Class[] types = { String.class, String.class, String.class, Integer.class };
		return types;
	}

	private String[] getTabHeader() {
		String[] tableHeader = { "�����������", "�����", "�������", "Id" };
		return tableHeader;
	}

	public Internal_applicant getInternal_applicant() {
		return int_Aplic;

	}

	private static void updateData(JTable table, List<Integer> listStrIntAplic, TranscluentWindow round) {
		List<Internal_applicant> list_Internal_applicant = Internal_applicantDAO.getInListAllInternal_applicant();
		int countRows = table.getRowCount();

		for (int strIntAplic : listStrIntAplic) {

			for (int row = 0; row < countRows; row++) {
				if (strIntAplic == (Integer) table.getValueAt(row, aplic_Id_Colum)) {
					Internal_applicant intAplic = Internal_applicantDAO.getValueInternal_applicantById(strIntAplic);

					updateRequestObject(table, row, intAplic);

				}
			}
		}
		round.StopWindow();
	}

	private static void updateRequestObject(JTable table, int row, Internal_applicant intAplic) {

		intAplic.setInternal_applicant_address((table.getValueAt(row, aplic_address_Colum) + ""));
		intAplic.setInternal_applicant_organization((table.getValueAt(row, aplic_organiz_Colum) + ""));
		intAplic.setInternal_applicant_telephone((table.getValueAt(row, aplic_teleph_Colum) + ""));

		Internal_applicantDAO.updateObjectInternal_applicant(intAplic);
	}

	private static void AddInUpdateList(int row) {

		if (listStrUpdateIntAplic.isEmpty()) {
			listStrUpdateIntAplic.add((Integer) dataTable[row][aplic_Id_Colum]);
		} else {
			if (!listStrUpdateIntAplic.equals(dataTable[row][aplic_Id_Colum])) {
				listStrUpdateIntAplic.add((Integer) dataTable[row][aplic_Id_Colum]);
			}
		}
	}

}
