package Table;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Request;
import DBase_Class.Sample;
import WindowView.Login;
import WindowView.RequestMiniFrame;
import WindowView.RequestMiniFrame4;
import WindowView.RequestMiniFrame3;
import WindowView.RequestView;
import WindowView.RequestViewAplication;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class Table_Sample_List {

	private static String[] values_Period;
	private static String[] values_O_I_S;

	public static void DrawTableWithEnableSampleList(Request templateRequest) {
		List<Sample> listSample = new ArrayList<Sample>();
		if(templateRequest==null) {
			listSample = SampleDAO.getInListAllValueSample();
		}else {
			listSample =SampleDAO.getListSampleFromColumnByVolume("request", templateRequest);
			
		}
		
		 
		String[] tableHeader = { "№ на Заявката", "Код на пробата", "Обект на изпитване", "Обект на пробата",
				"Описание на групата проби","Описание на пробата", "Референтна дата", "Приод", "Година" };
		Class[] types = { Integer.class, String.class, String.class, String.class, String.class, String.class, Calendar.class,
				String.class, String.class };

		Object[][] tableSample = new Object[listSample.size()][9];
		int i = 0;

		for (Sample sample : listSample) {

			try {
				Integer.parseInt(sample.getRequest().getRecuest_code());
				tableSample[i][0] = sample.getRequest().getRecuest_code();
				tableSample[i][1] = sample.getSample_code();
				tableSample[i][2] = sample.getRequest().getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
				tableSample[i][3] = sample.getObekt_na_izpitvane().getName_obekt_na_izpitvane();
				tableSample[i][4] = sample.getRequest().getDescription_sample_group();			
				tableSample[i][5] = sample.getDescription_sample();
				tableSample[i][6] = sample.getDate_time_reference();
				if (sample.getPeriod() == null) {
					tableSample[i][7] = "";
				} else {
					tableSample[i][7] = sample.getPeriod().getValue();
				}
				tableSample[i][8] = sample.getGodina_period();

				i++;
			} catch (NumberFormatException e) {

			}
		}

		TableSampleListEditable(tableHeader, tableSample, types);
	}

	public static void TableSampleListEditable(String[] columnNames, Object[][] data, Class[] types) {
		JFrame frame = new JFrame("Списък на Пробите");

		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		values_O_I_S = Obekt_na_izpitvane_sampleDAO.getMasiveStringAllValueObekt_na_izpitvane_sample();
		values_Period = PeriodDAO.getMasiveStringAllValuePeriod();
		final JTable table = new JTable();// new DefaultTableModel(rowData,
											// columnNames));

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				if (table.getSelectedColumn() == 0) {
					int row = table.rowAtPoint(e.getPoint());
					int col = table.columnAtPoint(e.getPoint());
					String reqCodeStr = table.getValueAt(table.getSelectedRow(), 0).toString();
					Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
					System.out.println(row + " " + choiseRequest.getRecuest_code());
					RequestMiniFrame frame = new RequestMiniFrame(new JFrame(), choiseRequest);

				}
				 if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
				
				 int row = table.getSelectedRow();
				 int col = table.getSelectedColumn();
				 String reqCodeStr = table.getValueAt(table.getSelectedRow(),
				 0).toString();
				
				 if (reqCodeStr.startsWith("templ")) {
//				 choiseRequest =
//				 RequestDAO.getRequestFromColumnByVolume("recuest_code",
//				 reqCodeStr);
//				 RequestView reqView = new RequestView(Login.getCurentUser(),
//				 choiseRequest);
//				 frame.setVisible(false);
				
				 } else {
					Request choiseRequest =
							 RequestDAO.getRequestFromColumnByVolume("recuest_code",
							 reqCodeStr);
					TranscluentWindow round = new TranscluentWindow();
					
					 final Thread thread = new Thread(new Runnable() {
					     @Override
					     public void run() {
					    	 
					    	 JFrame f = new JFrame();
						 		new Table_Results_List(f,round,Login.getCurentUser(), choiseRequest);
					 			    	
					     }
					    });
					    thread.start();
					
				 }
				 }
			}
		});

		TableFilterHeader tfh = new TableFilterHeader(table, AutoChoices.ENABLED);

		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(1200, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					@SuppressWarnings("rawtypes")
					private Class[] types2 = types;

					@SuppressWarnings({ "unchecked", "rawtypes" })
					@Override
					public Class getColumnClass(int columnIndex) {
						return this.types2[columnIndex];
					}

					@Override
					public boolean isCellEditable(int row, int column) {
						if (Login.getCurentUser() != null) {
							if (Login.getCurentUser().getIsAdmin()) {
								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					}

				};
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				setUp_O_I_S_Column(table, table.getColumnModel().getColumn(2));
				setUp_values_Period_Column(table, table.getColumnModel().getColumn(6));

			}
		});
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

}
