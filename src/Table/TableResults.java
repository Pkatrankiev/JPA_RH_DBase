package Table;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import DBase_Class.Request;
import DBase_Class.Sample;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class TableResults {
	private static Request choiseRequest;
	private static String[] values_Period;
	private static String[] values_O_I_S;

	public static void DrawTableWithEnableSampleList() {
		List<Request> listRequest = RequestDAO.getInListAllValueRequest();
		List<Sample> listSample = SampleDAO.getInListAllValueSample();
		String[] tableHeader = { "№ на Заявката", "Код на пробата", "Обект на изпитване", "Обект на пробата", "Описание на пробата",
				"Референтна дата", "Приод", "Година" };
		Class[] types = { Integer.class, String.class, String.class, String.class, String.class, Calendar.class, String.class,
				String.class };

		Object[][] tableSample = new Object[listSample.size()][8];
		int i = 0;

		for (Sample sample : listSample) {

			try {
				Integer.parseInt(sample.getRequest().getRecuest_code());
				tableSample[i][0] = sample.getRequest().getRecuest_code();
				tableSample[i][1] = sample.getSample_code();
				tableSample[i][2] = sample.getRequest().getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
				tableSample[i][3] = sample.getObekt_na_izpitvane().getName_obekt_na_izpitvane();
				tableSample[i][4] = sample.getDescription_sample();
				tableSample[i][5] = sample.getDate_time_reference();
				if (sample.getPeriod() == null) {
					tableSample[i][6] ="";
				}else{
				tableSample[i][6] = sample.getPeriod().getValue();
				}
				tableSample[i][7] = sample.getGodina_period();

				i++;
			} catch (NumberFormatException e) {

			}
		}

		TableSampleListEditable(tableHeader, tableSample, types);
	}

	public static void TableSampleListEditable(String[] columnNames, Object[][] data, Class[] types) {
		JFrame frame = new JFrame();
		
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		values_O_I_S = Obekt_na_izpitvane_sampleDAO.getMasiveStringAllValueObekt_na_izpitvane_sample();
		values_Period = PeriodDAO.getMasiveStringAllValuePeriod();
		final JTable table = new JTable();// new DefaultTableModel(rowData,
											// columnNames));

		table.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
//				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
//
//					int row = table.getSelectedRow();
//					int col = table.getSelectedColumn();
//					String reqCodeStr = table.getValueAt(table.getSelectedRow(), 0).toString();
//
//					if (reqCodeStr.startsWith("templ")) {
//						choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
//						RequestView reqView = new RequestView(Login.getCurentUser(), choiseRequest);
//						frame.setVisible(false);
//
//					} else {
//						RequestViewAplication.OpenRequestInWordDokTamplate(reqCodeStr);
//					}
//				}
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
						if (column == 2 || column == 5) {
							return true;
						} else {
							return false;
						}
					}

				};
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
//				setUp_O_I_S_Column(table, table.getColumnModel().getColumn(2));
//				setUp_values_Period_Column(table, table.getColumnModel().getColumn(5));

			}
		});
	}

	
}
