package ReadAndSaveDocFailInDBase;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import Aplication.DimensionDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.NuclideDAO;
import Aplication.RazmernostiDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import javax.swing.JPanel;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResultsListInTableForReadDoc {

	private static String[] values_Period;
	private static String[] values_Metody;
	private static String[] values_Izpit_Pokazatel;
	private static String[] values_Nuclide;
	private static String[] values_Razmernosti;
	private static String[] values_Dimension;
	private static Request request_basic;

	
	public static void DrawTableWithEnableResultsList(Request request) {
		request_basic = request;
		String[] tableHeader = { "№ на Заявката", "Код на пробата", "Обект на пробата", "Метод на изпитване",
				"Изпитван показател", "Нуклид", "Активност", "Неопределеност", "Сигма", "МДА", "Размерност",
				"Количество", "Мярка", "В протокол", "Id" };
		@SuppressWarnings("rawtypes")
		Class[] types = { Integer.class, String.class, String.class, String.class, String.class, String.class,
				Double.class, Double.class, Integer.class, Double.class, String.class, Double.class, String.class,
				Boolean.class, Integer.class };

		List<Results> listAllResults = ResultsDAO.getInListAllValueResults();
		Object[][] tableSample = new Object[listAllResults.size()][15];
		List<Sample> listSample = SampleDAO.getListSampleFromColumnByVolume("request", request);
		int i = 0;
		for (Sample sample : listSample) {

			List<Results> listResults = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
			for (Results results : listResults) {

				try {
					int request_code = Integer.parseInt(results.getSample().getRequest().getRecuest_code());
					tableSample[i][0] = request_code;
					tableSample[i][1] = sample.getSample_code();
					tableSample[i][2] = sample.getObekt_na_izpitvane().getName_obekt_na_izpitvane();
					tableSample[i][3] = results.getMetody().getCode_metody();
					tableSample[i][4] = results.getPokazatel().getName_pokazatel();
					tableSample[i][5] = results.getNuclide().getSymbol_nuclide();
					// tableSample[i][6] =
					// BigDecimal.valueOf(results.getValue_result()).setScale(2,
					// RoundingMode.HALF_UP);
					tableSample[i][6] = results.getValue_result();
					tableSample[i][7] = results.getUncertainty();
					tableSample[i][8] = results.getSigma();
					tableSample[i][9] = results.getMda();
					tableSample[i][10] = results.getRtazmernosti().getName_razmernosti();
					tableSample[i][11] = results.getQuantity();
					tableSample[i][12] = "";
					if (results.getDimension() != null) {
						tableSample[i][12] = results.getDimension().getName_dimension();
					}
					tableSample[i][13] = results.getInProtokol();
					tableSample[i][14] = results.getId_results();

					i++;
				} catch (NullPointerException e) {
					JOptionPane.showInputDialog("Грешни данни за резултат:", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e) {
					JOptionPane.showInputDialog("Грешни данни за резултат:", JOptionPane.ERROR_MESSAGE);
				}

			}

		}

		Object[][] tableSampleNew = new Object[i][15];
		for (int j = 0; j < tableSampleNew.length; j++) {
			for (int k = 0; k < 15; k++) {
				tableSampleNew[j][k] = tableSample[j][k];
			}
		}

		TableResultsListEditable(tableHeader, tableSampleNew, types);
	}

	public static void TableResultsListEditable(String[] columnNames, Object[][] data, @SuppressWarnings("rawtypes") Class[] types) {
		JFrame frame = new JFrame("Редактиране на Резултатите");

		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// values_O_I_S =
		// Obekt_na_izpitvane_sampleDAO.getMasiveStringAllValueObekt_na_izpitvane_sample();

		values_Metody = MetodyDAO.getMasiveStringAllValueMetody();
		values_Izpit_Pokazatel = List_izpitvan_pokazatelDAO.getMasiveStringAllValueList_Izpitvan_Pokazatel();
		values_Nuclide = NuclideDAO.getMasiveStringAllValueNuclide();
		values_Razmernosti = RazmernostiDAO.getMasiveStringAllValueRazmernosti();
		values_Dimension = DimensionDAO.getMasiveStringAllValueDimension();

		final JTable table = new JTable();// new DefaultTableModel(rowData,
											// columnNames));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (table.getSelectedColumn() == 0) {
								}
				super.mouseReleased(e);
			}

			public void mousePressed(MouseEvent e) {

			}
		});

//		TableFilterHeader tfh = new TableFilterHeader(table, AutoChoices.ENABLED);

		JScrollPane scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setSize(1200, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(data, columnNames) {

					private static final long serialVersionUID = 1L;
					@SuppressWarnings("rawtypes")
					private Class[] types2 = types;

					@SuppressWarnings({ })
					@Override
					public Class<?> getColumnClass(int columnIndex) {
						return this.types2[columnIndex];
					}

					@Override
					public boolean isCellEditable(int row, int column) {
						if (column > 2) {
							return true;
						} else {
							return false;
						}
					}

					public int getColumnCount() {
						return columnNames.length;
					}

					public int getRowCount() {
						return data.length;
					}

				};

				table.setModel(dtm);
				table.setFillsViewportHeight(true);

				setUp_Metody(table, table.getColumnModel().getColumn(3));
				setUp_Izpit_Pokazatel_Column(table, table.getColumnModel().getColumn(4));
				setUp_Nuclide(table, table.getColumnModel().getColumn(5));
				setUp_Razmernosti(table, table.getColumnModel().getColumn(10));
				setUp_Dimension(table, table.getColumnModel().getColumn(12));

				table.getColumnModel().getColumn(14).setWidth(0);
				table.getColumnModel().getColumn(14).setMinWidth(0);
				table.getColumnModel().getColumn(14).setMaxWidth(0);
				table.getColumnModel().getColumn(14).setPreferredWidth(0);

				JPanel panel_Btn = new JPanel();
				panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
				frame.getContentPane().add(panel_Btn, BorderLayout.SOUTH);
				panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

				JButton btnSave = new JButton("Запис");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						updateData(table);
					}
				});
				btnSave.setHorizontalAlignment(SwingConstants.RIGHT);
				btnSave.setAlignmentX(Component.RIGHT_ALIGNMENT);
				panel_Btn.add(btnSave);

				JButton btnCancel = new JButton("Изход");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frame.setVisible(false);
					}
				});
				panel_Btn.add(btnCancel);

			}
		});
	}

	public static void setUp_Izpit_Pokazatel_Column(JTable table, TableColumn izpit_Pokazatel_Column) {

		JComboBox<?> comboBox = new JComboBox<Object>(values_Izpit_Pokazatel);
		izpit_Pokazatel_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		izpit_Pokazatel_Column.setCellRenderer(renderer);
	}

	public static void setUp_values_Period_Column(JTable table, TableColumn Period_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(values_Period);
		Period_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Period_Column.setCellRenderer(renderer);
	}

	public static void setUp_Metody(JTable table, TableColumn Metody_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(values_Metody);
		Metody_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Metody_Column.setCellRenderer(renderer);
	}

	public static void setUp_Nuclide(JTable table, TableColumn Nuclide_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(values_Nuclide);
		Nuclide_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Nuclide_Column.setCellRenderer(renderer);
	}

	public static void setUp_Razmernosti(JTable table, TableColumn Razmernosti_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(values_Razmernosti);
		Razmernosti_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Razmernosti_Column.setCellRenderer(renderer);
	}

	public static void setUp_Dimension(JTable table, TableColumn Dimension_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(values_Dimension);
		Dimension_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Dimension_Column.setCellRenderer(renderer);
	}

	private static void updateData(JTable table) {
		int numRows = table.getRowCount();
		String[][] masiveIzpitPokazatel = new String[numRows][2];
		List<List_izpitvan_pokazatel> listIzpitPokazatel = new ArrayList<List_izpitvan_pokazatel>();
		for (int i = 0; i < numRows; i++) {
			Results result = ResultsDAO.getValueResultsById((int) table.getValueAt(i, 14));
			result.setMetody((Metody) MetodyDAO.getValueList_MetodyByName(table.getValueAt(i, 3) + ""));
			result.setPokazatel((List_izpitvan_pokazatel) List_izpitvan_pokazatelDAO
					.getValueIzpitvan_pokazatelByName(table.getValueAt(i, 4) + ""));
			result.setNuclide(NuclideDAO.getValueNuclideBySymbol((String) table.getValueAt(i, 5)));
			result.setValue_result((Double) table.getValueAt(i, 6));
			result.setUncertainty((Double) table.getValueAt(i, 7));
			result.setSigma((Integer) table.getValueAt(i, 8));
			result.setMda((Double) table.getValueAt(i, 9));
			result.setRtazmernosti(RazmernostiDAO.getValueRazmernostiByName((String) table.getValueAt(i, 10)));
			result.setQuantity((Double) table.getValueAt(i, 11));

			if ((table.getValueAt(i, 12).equals(""))) {
				result.setDimension(null);
			} else {
				result.setDimension(DimensionDAO.getValueDimensionByName((String) table.getValueAt(i, 12)));
			}
			result.setInProtokol((Boolean) table.getValueAt(i, 13));
			ResultsDAO.updateResults(result);
			listIzpitPokazatel.add(result.getPokazatel());
			masiveIzpitPokazatel[i][0] = result.getPokazatel().getName_pokazatel();
			masiveIzpitPokazatel[i][1] = result.getMetody().getCode_metody();
		}
		updateDataIzpitwanPokazatel(request_basic, masiveIzpitPokazatel);
	}

	private static void updateDataIzpitwanPokazatel(Request request, String[][] masiveIzpitPokazatel) {
		List<String> listPokazatel = new ArrayList<String>();

		for (int i = 0; i < masiveIzpitPokazatel.length; i++) {
			listPokazatel.add(masiveIzpitPokazatel[i][0]);
		}
		Set<String> unique = new HashSet<String>(listPokazatel);

		IzpitvanPokazatelDAO.deleteIzpitvanPokazatelByRequest(request);

		for (int i = 0; i < unique.size(); i++) {
						IzpitvanPokazatelDAO.setValueIzpitvanPokazatel(
					List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(masiveIzpitPokazatel[i][0]), request,
					MetodyDAO.getValueList_MetodyByName(masiveIzpitPokazatel[i][1]));
					}

	}
}
