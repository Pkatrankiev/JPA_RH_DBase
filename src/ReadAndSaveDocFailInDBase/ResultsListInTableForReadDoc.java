package ReadAndSaveDocFailInDBase;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Aplication.IzpitvanPokazatelDAO;
import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class ResultsListInTableForReadDoc {
	private static Request choiseRequest;
	private static String[] values_Period;
	private static String[] values_O_I_S;

	public static void DrawTableWithEnableResultsList(Request request) {

	

		String[] tableHeader = { "№ на Заявката", "Код на пробата", "Обект на пробата", "Метод на изпитване",
				"Изпитван показател", "Нуклид", "Активност", "Неопределеност", "Сигма", "МДА", "Размерност",
				"Количество", "Мярка", "В протокол" };
		Class[] types = { Integer.class, String.class, String.class, String.class, String.class, String.class,
				BigDecimal.class, BigDecimal.class, Integer.class, BigDecimal.class, String.class, String.class, String.class,
				Boolean.class };
		List<Results> listAllResults = ResultsDAO.getInListAllValueResults();
		Object[][] tableSample = new Object[listAllResults.size()][14];
		int i = 0;

		List<IzpitvanPokazatel> listIzpPokaz = IzpitvanPokazatelDAO.getValueIzpitvan_pokazatelByRequest(request);
		List<Sample> listSample = SampleDAO.getListSampleFromColumnByVolume("request", request);
		for (Sample sample : listSample) {
			for (IzpitvanPokazatel izpitvanPokazatel : listIzpPokaz) {
				List<Results> listResults = ResultsDAO.getListResultsFromColumnByVolume("pokazatel", izpitvanPokazatel);
				for (Results results : listResults) {

			
		 try {
		 Integer.parseInt(results.getPokazatel().getRequest().getRecuest_code());
		 tableSample[i][0] = results.getPokazatel().getRequest().getRecuest_code();
		 tableSample[i][1] = sample.getSample_code();
		 tableSample[i][2] = sample.getObekt_na_izpitvane().getName_obekt_na_izpitvane();
		 tableSample[i][3] = izpitvanPokazatel.getMetody().getCode_metody();
		 tableSample[i][4] = izpitvanPokazatel.getPokazatel().getName_pokazatel();
		 tableSample[i][5] = results.getNuclide().getSymbol_nuclide();
		 tableSample[i][6] = results.getValue_result();
		 tableSample[i][7] = results.getUncertainty();
		 tableSample[i][8] = results.getSigma();
		 tableSample[i][9] = results.getMda();
		 tableSample[i][10] = results.getRtazmernosti().getName_razmernosti();
		 tableSample[i][11] = results.getBasic_value();
		 tableSample[i][12] = "";
		 if(results.getDimension()!=null){
		 tableSample[i][12] = results.getDimension().getName_dimension();
		 }
		 tableSample[i][13] = results.getInProtokol();
		 		
		 i++;
		 } catch (NumberFormatException e) {
			 JOptionPane.showInputDialog( "Грешни данни за резултат:",
						JOptionPane.ERROR_MESSAGE);
		 }
		 
		
		
				}
			}
		}
		
		Object[][] tableSampleNew = new Object[i][14];
		for (int j = 0; j < tableSampleNew.length; j++) {
			for (int k = 0; k < 14; k++) {
				tableSampleNew[j][k] = tableSample[j][k];
			}
		}
		
		 TableResultsListEditable(tableHeader, tableSampleNew, types);
	}

	public static void TableResultsListEditable(String[] columnNames, Object[][] data, Class[] types) {
		JFrame frame = new JFrame("Редактиране на Резултатите");

		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		values_O_I_S = Obekt_na_izpitvane_sampleDAO.getMasiveStringAllValueObekt_na_izpitvane_sample();
		values_Period = PeriodDAO.getMasiveStringAllValuePeriod();
		final JTable table = new JTable();// new DefaultTableModel(rowData,
											// columnNames));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (table.getSelectedColumn() == 0) {
					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					System.out.println(row + " " + col);
				}
				super.mouseReleased(e);
			}

			public void mousePressed(MouseEvent e) {
				// if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
				//
				// int row = table.getSelectedRow();
				// int col = table.getSelectedColumn();
				// String reqCodeStr = table.getValueAt(table.getSelectedRow(),
				// 0).toString();
				//
				// if (reqCodeStr.startsWith("templ")) {
				// choiseRequest =
				// RequestDAO.getRequestFromColumnByVolume("recuest_code",
				// reqCodeStr);
				// RequestView reqView = new RequestView(Login.getCurentUser(),
				// choiseRequest);
				// frame.setVisible(false);
				//
				// } else {
				// RequestViewAplication.OpenRequestInWordDokTamplate(reqCodeStr);
				// }
				// }
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
						if (column == 0 || column == 2 || column == 6) {
							return true;
						} else {
							return false;
						}
					}

				};
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				// setUp_O_I_S_Column(table,
				// table.getColumnModel().getColumn(2));
				// setUp_values_Period_Column(table,
				// table.getColumnModel().getColumn(5));

			}
		});
	}

}
