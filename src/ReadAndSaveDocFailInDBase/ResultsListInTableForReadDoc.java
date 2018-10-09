package ReadAndSaveDocFailInDBase;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

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
import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.NuclideDAO;
import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
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
	private static String[] values_O_I_S;
	private static String[] values_Metody;
	private static String[] values_Izpit_Pokazatel;
	private static String[] values_Nuclide;
	private static String[] values_Razmernosti;
	private static String[] values_Dimension;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void DrawTableWithEnableResultsList(Request request) {

		String[] tableHeader = { "№ на Заявката", "Код на пробата", "Обект на пробата", "Метод на изпитване",
				"Изпитван показател", "Нуклид", "Активност", "Неопределеност", "Сигма", "МДА", "Размерност",
				"Количество", "Мярка", "В протокол" };
		Class[] types = { Integer.class, String.class, String.class, String.class, String.class, String.class,
				BigDecimal.class, BigDecimal.class, Integer.class, BigDecimal.class, String.class, String.class, String.class,
				Boolean.class };
		
		List<Results> listAllResults = ResultsDAO.getInListAllValueResults();
		Object[][] tableSample = new Object[listAllResults.size()][14];
		

		List<Sample> listSample = SampleDAO.getListSampleFromColumnByVolume("request", request);
		int i = 0;
		for (Sample sample : listSample) {
			
				List<Results> listResults = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
				for (Results results : listResults) {

			
		 try {
		 Integer.parseInt(results.getPokazatel().getRequest().getRecuest_code());
		 tableSample[i][0] = results.getPokazatel().getRequest().getRecuest_code();
		 tableSample[i][1] = sample.getSample_code();
		 tableSample[i][2] = sample.getObekt_na_izpitvane().getName_obekt_na_izpitvane();
		 tableSample[i][3] = results.getPokazatel().getMetody().getCode_metody();
		 tableSample[i][4] = results.getPokazatel().getPokazatel().getName_pokazatel();
		 tableSample[i][5] = results.getNuclide().getSymbol_nuclide();
		 tableSample[i][6] = BigDecimal.valueOf(results.getValue_result()).setScale(2, RoundingMode.HALF_UP);
//		 tableSample[i][6] = results.getValue_result();
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
		 } catch (NullPointerException e) {
			 JOptionPane.showInputDialog( "Грешни данни за резултат:",
						JOptionPane.ERROR_MESSAGE);
		 }
				 catch (NumberFormatException e) {
					 JOptionPane.showInputDialog( "Грешни данни за резултат:",
								JOptionPane.ERROR_MESSAGE);
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
//		values_O_I_S = Obekt_na_izpitvane_sampleDAO.getMasiveStringAllValueObekt_na_izpitvane_sample();
				
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
					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
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
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
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
						if (column > 2) {
							return true;
						} else {
							return false;
						}
					}

				};
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				
//				setUp_O_I_S_Column(table, table.getColumnModel().getColumn(2));
				
				setUp_Metody(table, table.getColumnModel().getColumn(3));	
				setUp_Izpit_Pokazatel_Column(table, table.getColumnModel().getColumn(4));
				setUp_Nuclide(table, table.getColumnModel().getColumn(5));
				setUp_Razmernosti(table, table.getColumnModel().getColumn(10));
				setUp_Dimension(table, table.getColumnModel().getColumn(12));
				
				
				JPanel panel_Btn = new JPanel();
				panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
				frame.getContentPane().add(panel_Btn, BorderLayout.SOUTH);
				panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				
				JButton btnSave = new JButton("Запис");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						
					}
				});
				btnSave.setHorizontalAlignment(SwingConstants.RIGHT);
				btnSave.setAlignmentX(Component.RIGHT_ALIGNMENT);
				panel_Btn.add(btnSave);
				
				JButton btnCancel = new JButton("Изход");
				panel_Btn.add(btnCancel);
				

			}
		});
	}
	

	public static void setUp_Izpit_Pokazatel_Column(JTable table, TableColumn izpit_Pokazatel_Column) {

		JComboBox comboBox = new JComboBox(values_Izpit_Pokazatel);
		izpit_Pokazatel_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		izpit_Pokazatel_Column.setCellRenderer(renderer);
	}
	public static void setUp_values_Period_Column(JTable table, TableColumn Period_Column) {
		JComboBox comboBox = new JComboBox(values_Period);
		Period_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Period_Column.setCellRenderer(renderer);
	}
	public static void setUp_Metody(JTable table, TableColumn Metody_Column) {
		JComboBox comboBox = new JComboBox(values_Metody);
		Metody_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Metody_Column.setCellRenderer(renderer);
	}
	public static void setUp_Nuclide(JTable table, TableColumn Nuclide_Column) {
		JComboBox comboBox = new JComboBox(values_Nuclide);
		Nuclide_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Nuclide_Column.setCellRenderer(renderer);
	}
	public static void setUp_Razmernosti(JTable table, TableColumn Razmernosti_Column) {
		JComboBox comboBox = new JComboBox(values_Razmernosti);
		Razmernosti_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Razmernosti_Column.setCellRenderer(renderer);
	}
	public static void setUp_Dimension(JTable table, TableColumn Dimension_Column) {
		JComboBox comboBox = new JComboBox(values_Dimension);
		Dimension_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Dimension_Column.setCellRenderer(renderer);
	}


}
