package Table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Aplication.Ind_num_docDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import Aplication.UsersDAO;
import Aplication.ZabelejkiDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Request;
import DBase_Class.Sample;
import WindowView.DatePicker;
import WindowView.Login;
import WindowView.RequestMiniFrame;
import WindowView.RequestMiniFrame4;
import WindowView.RequestMiniFrame3;
import WindowView.RequestView;
import WindowView.RequestViewAplication;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class Table_Sample_List extends JDialog {

	private static String[] values_Period;
	private static String[] values_O_I_S;
	private static Object[][] dataTable;
	private static List<Integer> listChangedSampleId;

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
				if (table.getSelectedColumn() == rqst_code_Colum) {
					int row = table.rowAtPoint(e.getPoint());
					int col = table.columnAtPoint(e.getPoint());
					String reqCodeStr = table.getValueAt(table.getSelectedRow(), rqst_code_Colum).toString();
					Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
					System.out.println(row + " " + choiseRequest.getRecuest_code());
					RequestMiniFrame frame = new RequestMiniFrame(new JFrame(), choiseRequest);

				}
				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {

					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					String reqCodeStr = table.getValueAt(table.getSelectedRow(), rqst_code_Colum).toString();
					if (reqCodeStr.startsWith("templ")) {
						// choiseRequest =
						// RequestDAO.getRequestFromColumnByVolume("recuest_code",
						// reqCodeStr);
						// RequestView reqView = new
						// RequestView(Login.getCurentUser(),
						// choiseRequest);
						// frame.setVisible(false);

					}

					if (col == smpl_code_Colum) {
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
					
					
					if (col == O_I_R_Colum || col == smpl_group_descrp_Colum) {
						startEditChoiceRequest(reqCodeStr);
					}
				}
			}

			
		});

		TableFilterHeader tfh = new TableFilterHeader(table, AutoChoices.ENABLED);

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

				JButton btnSave = new JButton("Запис");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						TranscluentWindow round = new TranscluentWindow();

						final Thread thread = new Thread(new Runnable() {
							@Override
							public void run() {

								updateData(table, listChangedSampleId, round);

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

	@SuppressWarnings("rawtypes")
	private static void AddInUpdateList(int row) {
		if (listChangedSampleId.isEmpty()) {
			listChangedSampleId.add((Integer) dataTable[row][smpl_Id_Colum]);
		} else {
			if (!listChangedSampleId.equals(dataTable[row][smpl_Id_Colum])) {
				listChangedSampleId.add((Integer) dataTable[row][smpl_Id_Colum]);
			}
		}
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
				tableSample[i][O_I_R_Colum] = sample.getRequest().getObekt_na_izpitvane_request()
						.getName_obekt_na_izpitvane();
				tableSample[i][O_I_S_Colum] = sample.getObekt_na_izpitvane().getName_obekt_na_izpitvane();
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

	private static void updateData(JTable table, List<Integer> listChangedSampleId, TranscluentWindow round) {
		int countRows = table.getRowCount();
		for (Integer sampleId : listChangedSampleId) {

			for (int row = 0; row < countRows; row++) {
				if (sampleId == table.getValueAt(row, smpl_Id_Colum)) {
					Sample sample = SampleDAO.getValueSampleById(sampleId);

					updateSampleObject(table, row, sample);

				}
			}
		}
		round.StopWindow();
	}

	private static void updateSampleObject(JTable table, int row, Sample sample) {
		sample.setDate_time_reference(table.getValueAt(row, date_time_ref_Colum) + "");
		sample.setDescription_sample(table.getValueAt(row, smpl_descrip_Colum) + "");
		sample.setGodina_period((int) table.getValueAt(row, yar_Colum));
		sample.setSample_code(table.getValueAt(row, smpl_code_Colum) + "");
		sample.setObekt_na_izpitvane(Obekt_na_izpitvane_sampleDAO
				.getValueObekt_na_izpitvane_sampleByName(table.getValueAt(row, O_I_S_Colum) + ""));
		String strPeriod = table.getValueAt(row, period_Colum) + "";
		if (strPeriod.equals("")) {
			sample.setPeriod(null);
		} else {
			sample.setPeriod(PeriodDAO.getValuePeriodByPeriod(strPeriod));
		}
		sample.setRequest(
				RequestDAO.getRequestFromColumnByVolume("recuest_code", table.getValueAt(row, rqst_code_Colum) + ""));

		SampleDAO.updateSample(sample);
	}

	private static String[] getTabHeader() {
		String[] tableHeader = { "№ на Заявката", "Код на пробата", "Обект на изпитване", "Описание на групата проби",
				"Обект на пробата", "Описание на пробата", "Референтна дата", "Приод", "Година", "Id" };
		return tableHeader;
	}

	private static Class[] getTypes() {
		Class[] types = { Integer.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, Integer.class };
		return types;
	}
	
	private void startEditChoiceRequest(String reqCodeStr) {
		Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
		
		TranscluentWindow round = new TranscluentWindow();
		
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	 JFrame f = new JFrame();
		 		new Table_Request_List(f,round,Login.getCurentUser(),choiseRequest);
	    	
		     }
		    });
		    thread.start();
		    
		
	}

}
