package Table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import DBase_Class.Request;
import DBase_Class.Request_To_ObektNaIzpitvaneRequest;
import DBase_Class.Sample;
import WindowView.DatePicker;
import WindowView.Login;
import WindowView.MiniComboBoxFrame;
import WindowView.RequestMiniFrame;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class Table_Sample_List extends JDialog {

	private static final long serialVersionUID = 1L;
	private static String[] values_Period;
	private static String[] values_O_I_S;
	private static Object[][] dataTable;
	private static List<Integer> listChangedSampleId;

	private static String[] tableHeader = { "№ на Заявката", "Код на пробата", "Обект на изпитване",
			"Описание на групата проби", "Обект на пробата", "Описание на пробата", "Референтна дата", "Приод",
			"Година", "Id" };

	private static int tbl_Colum = 10;
	private static int rqst_code_Colum = 0;
	private static int smpl_code_Colum = 1;
	private static int O_I_R_Colum = 2;
	private static int smpl_group_descrp_Colum = 3;
	private static int O_I_S_Colum = 4;
	private static int smpl_descrip_Colum = 5;
	private static int date_time_ref_Colum = 6;
	private static int period_Colum = 7;
	private static int yar_Colum = 8;
	private static int smpl_Id_Colum = 9;

	private static String name_rqst_Colum = tableHeader[0];
	private static String name_smpl_Colum = tableHeader[1];
	private static String name_O_I_R_Colum = tableHeader[2];
	private static String name_smpl_group_descrp_Colum = tableHeader[3];
	private static String name_O_I_S_Colum = tableHeader[4];
//	private static String name_smpl_descrip_Colum = tableHeader[5];
//	private static String name_date_time_ref_Colum = tableHeader[6];
//	private static String name_period_Colum = tableHeader[7];
//	private static String name_yar_Colum = tableHeader[8];
//	private static String name_smpl_Id_Colum = tableHeader[9];

	public Table_Sample_List(JFrame parent, TranscluentWindow round, Request templateRequest) {
		super(parent, "Списък на Пробите", true);

		String[] columnNames = getTabHeader();
		@SuppressWarnings("rawtypes")
		Class[] types = getTypes();
		Object[][] data = getDataTable(templateRequest);

		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dataTable = data;
		listChangedSampleId = new ArrayList<Integer>();
		values_O_I_S = Obekt_na_izpitvane_sampleDAO.getMasiveStringAllValueObekt_na_izpitvane_sample();
		values_Period = PeriodDAO.getMasiveStringAllValuePeriod();
		final JTable table = new JTable();// new DefaultTableModel(rowData,
											// columnNames));

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				String nameSelectedColumn = table.getColumnModel().getColumn(table.getSelectedColumn()).getHeaderValue()
						.toString();
				
				int selectedRow = getSelectedModelRow(table);
				String reqCodeStr = model.getValueAt(selectedRow, rqst_code_Colum).toString();

				if (nameSelectedColumn.equals(name_rqst_Colum)) {

					Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
					new RequestMiniFrame(new JFrame(), choiseRequest);
				}

				if (nameSelectedColumn.equals(name_O_I_R_Colum)) {
					String origO_I_Request = model.getValueAt(selectedRow, O_I_R_Colum).toString();
					Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
					String[] values_O_I_R = Table_RequestToObektNaIzp
							.getMasiveStringOfRequest_To_ObektNaIzpitvaneRequest(choiseRequest);

					JFrame f = new JFrame();
					MiniComboBoxFrame miniBoxFrame = new MiniComboBoxFrame(f, values_O_I_R, origO_I_Request);
					String changed_O_I_R = miniBoxFrame.getStrO_I_Request();
					model.setValueAt(changed_O_I_R, selectedRow, O_I_R_Colum);
					if (!changed_O_I_R.equals(origO_I_Request)) {
						AddInUpdateList(selectedRow);
					}
				}

				if (e.getClickCount() == 2 && getSelectedModelRow(table) != -1) {

					if (nameSelectedColumn.equals(name_smpl_Colum)) {
						Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
						TranscluentWindow round = new TranscluentWindow();

						final Thread thread = new Thread(new Runnable() {
							@Override
							public void run() {
								JFrame f = new JFrame();
								new Table_Results_List(f, round, Login.getCurentUser(), choiseRequest);
							}
						});
						thread.start();
					}

					if (nameSelectedColumn.equals(name_smpl_group_descrp_Colum)) {
						startEditChoiceRequest(reqCodeStr);
					}

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
				DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {

					private static final long serialVersionUID = 1L;
					@SuppressWarnings("rawtypes")
					private Class[] types2 = types;

					@SuppressWarnings({ "unchecked", "rawtypes" })
					@Override
					public Class getColumnClass(int columnIndex) {
						return this.types2[columnIndex];
					}

					public Object getValueAt(int row, int col) {
						return dataTable[row][col];
					}

					@Override
					public boolean isCellEditable(int row, int column) {
						if (Login.getCurentUser() != null) {
							if (Login.getCurentUser().getIsAdmin()) {
								if (column <= 3) {
									return false;
								} else {
									return true;
								}
							} else {
								return false;
							}
						} else {
							return false;
						}

					}

					public void setValueAt(Object value, int row, int col) {

						if (!dataTable[row][col].equals(value)) {
							if (col == date_time_ref_Colum) {
								String str = (String) value;
								if (!DatePicker.incorrectDate(str, true)) {
									dataTable[row][col] = value;
								}
							} else {
								dataTable[row][col] = value;
							}
							fireTableCellUpdated(row, col);
							AddInUpdateList(row);
						}
					}

				};
				table.setModel(dtm);
				table.setFillsViewportHeight(true);

				table.getColumnModel().getColumn(smpl_Id_Colum).setWidth(0);
				table.getColumnModel().getColumn(smpl_Id_Colum).setMinWidth(0);
				table.getColumnModel().getColumn(smpl_Id_Colum).setMaxWidth(0);
				table.getColumnModel().getColumn(smpl_Id_Colum).setPreferredWidth(0);

				setUp_O_I_S_Column(table, table.getColumnModel().getColumn(O_I_S_Colum));
				setUp_values_Period_Column(table, table.getColumnModel().getColumn(period_Colum));

				JPanel panel_Btn = new JPanel();
				panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
				getContentPane().add(panel_Btn, BorderLayout.SOUTH);
				panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

				JButton btnNewButton = new JButton("export");

				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						TranscluentWindow round = new TranscluentWindow();

						final Thread thread = new Thread(new Runnable() {
							@Override
							public void run() {

								DefaultTableModel model = (DefaultTableModel) table.getModel();
								List<Integer> listRow = new ArrayList<Integer>();
								for (int i = 0; i < model.getRowCount(); i++) {
									listRow.add(i);
								}

								updateChangedSampleObject(table, listRow, round);
						
							}
						});
						thread.start();

					}
				});
				panel_Btn.add(btnNewButton);

				JButton btnSave = new JButton("Запис");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						TranscluentWindow round = new TranscluentWindow();

						final Thread thread = new Thread(new Runnable() {
							@Override
							public void run() {
								System.out.println(listChangedSampleId);
								updateChangedSampleObject(table, listChangedSampleId, round);

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
						setVisible(false);
					}
				});
				panel_Btn.add(btnCancel);

			}
		});
		setVisible(true);
	}

	public static void setUp_O_I_S_Column(JTable table, TableColumn O_I_S_Column) {

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBox = new JComboBox(values_O_I_S);
		O_I_S_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		O_I_S_Column.setCellRenderer(renderer);
	}

	public static void setUp_values_Period_Column(JTable table, TableColumn Period_Column) {

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBox = new JComboBox(values_Period);
		Period_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Period_Column.setCellRenderer(renderer);
	}

	private static void AddInUpdateList(int row) {

		if (listChangedSampleId.isEmpty()) {
			listChangedSampleId.add(row);
		} else {
			if (!listChangedSampleId.contains(row)) {
				listChangedSampleId.add(row);
			}
		}
		System.out.println(row);
	}

	private static Object[][] getDataTable(Request templateRequest) {
				
		List<Sample> listSample = new ArrayList<Sample>();
		if (templateRequest == null) {
			listSample = SampleDAO.getInListAllValueSample();
		} else {
			listSample = SampleDAO.getListSampleFromColumnByVolume("request", templateRequest);

		}

		Object[][] tableSample = new Object[listSample.size()][tbl_Colum];
		int i = 0;

		for (Sample sample : listSample) {

			try {
				Integer.parseInt(sample.getRequest().getRecuest_code());
				tableSample[i][rqst_code_Colum] = sample.getRequest().getRecuest_code();
				tableSample[i][smpl_code_Colum] = sample.getSample_code();
				if (sample.getRequest_to_obekt_na_izpitvane_request() != null) {
					tableSample[i][O_I_R_Colum] = sample.getRequest_to_obekt_na_izpitvane_request().getObektNaIzp()
							.getName_obekt_na_izpitvane();
				} else {
					tableSample[i][O_I_R_Colum] = "";
					// tableSample[i][O_I_R_Colum] =
					// sample.getRequest().getText_obekt_na_izpitvane_request();
				}
				tableSample[i][O_I_S_Colum] = sample.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane();
				tableSample[i][smpl_group_descrp_Colum] = sample.getRequest().getDescription_sample_group();
				tableSample[i][smpl_descrip_Colum] = sample.getDescription_sample();
				tableSample[i][date_time_ref_Colum] = sample.getDate_time_reference();
				if (sample.getPeriod() == null) {
					tableSample[i][period_Colum] = "";
				} else {
					tableSample[i][period_Colum] = sample.getPeriod().getValue();
				}
				tableSample[i][yar_Colum] = sample.getGodina_period();
				tableSample[i][smpl_Id_Colum] = sample.getId_sample();

				i++;
			} catch (NumberFormatException e) {

			}
		}
		return tableSample;
	}

	private static void updateChangedSampleObject(JTable table, List<Integer> listRowChangedSample,
			TranscluentWindow round) {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
//		updateIndexColumn(table);
		for (Integer rowChangedSample : listRowChangedSample) {
			Sample sample = SampleDAO.getValueSampleById((int) model.getValueAt(rowChangedSample, smpl_Id_Colum));

			sample.setDate_time_reference(model.getValueAt(rowChangedSample, date_time_ref_Colum).toString());
			sample.setDescription_sample(model.getValueAt(rowChangedSample, smpl_descrip_Colum).toString());
			sample.setGodina_period((int) model.getValueAt(rowChangedSample, yar_Colum));
			sample.setSample_code(model.getValueAt(rowChangedSample, smpl_code_Colum).toString());
			Request_To_ObektNaIzpitvaneRequest requestTo_O_I_R = Table_RequestToObektNaIzp
					.getRequest_To_ObektNaIzpitvaneRequest(sample.getRequest(),
							model.getValueAt(rowChangedSample, O_I_R_Colum).toString());
			sample.setRequest_to_obekt_na_izpitvane_request(requestTo_O_I_R);
			System.out.println(name_O_I_S_Colum+" -/- "+O_I_S_Colum+"  *******  "+model.getValueAt(rowChangedSample, O_I_S_Colum).toString());
			sample.setObekt_na_izpitvane_sample(Obekt_na_izpitvane_sampleDAO.getValueObekt_na_izpitvane_sampleByName(
					model.getValueAt(rowChangedSample, O_I_S_Colum).toString()));
			String strPeriod = model.getValueAt(rowChangedSample, period_Colum).toString();
			if (strPeriod.equals("")) {
				sample.setPeriod(null);
			} else {
				sample.setPeriod(PeriodDAO.getValuePeriodByPeriod(strPeriod));
			}
			sample.setRequest(RequestDAO.getRequestFromColumnByVolume("recuest_code",
					model.getValueAt(rowChangedSample, rqst_code_Colum).toString()));

			SampleDAO.updateSample(sample);
		}
		round.StopWindow();
	}

	private static String[] getTabHeader() {
		return tableHeader;
	}

	@SuppressWarnings("rawtypes")
	private static Class[] getTypes() {
		Class[] types = { Integer.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, Integer.class, Integer.class };
		return types;
	}

	private void startEditChoiceRequest(String reqCodeStr) {
		Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);

		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				JFrame f = new JFrame();
				new Table_Request_List(f, round, Login.getCurentUser(), choiseRequest);

			}
		});
		thread.start();

	}

	private int getSelectedModelRow(JTable table) {
		return table.convertRowIndexToModel(table.getSelectedRow());
	}

	public static int getColumnIndex(JTable table, String columnTitle) {
	    int columnCount = table.getColumnCount();

	    for (int column = 0; column < columnCount; column++) {
	        if (table.getColumnName(column).equalsIgnoreCase(columnTitle)) {
	            return column;
	        }
	    }

	    return -1;
	}


}
