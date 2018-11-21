package Table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Aplication.DimensionDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.NuclideDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import WindowView.ExtraRequestView;
import WindowView.Login;
import WindowView.RequestMiniFrame;
import WindowView.RequestView;
import WindowView.RequestViewAplication;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class Table_Results_List extends JDialog {

	private static final long serialVersionUID = 1L;
	private static String[] values_Period;
	private static String[] values_Metody;
	private static String[] values_Izpit_Pokazatel;
	private static String[] values_Nuclide;
	private static String[] values_Razmernosti;
	private static String[] values_Dimension;
	private static List<Integer> listWhithChangeRow;
	private static Object[][] dataTable;

	private static int tbl_Colum = 15;
	private static int rqst_code_Colum = 0;
	private static int smpl_code_Colum = 1;
	private static int obk_Izp_Colum = 2;
	private static int mtd_Izp_Colum = 3;
	private static int izp_Pok_Colum = 4;
	private static int nuclide_Colum = 5;
	private static int actv_value_Colum = 6;
	private static int uncrt_Colum = 7;
	private static int sigma_Colum = 8;
	private static int mda_Colum = 9;
	private static int razm_Colum = 10;
	private static int qunt_Colum = 11;
	private static int dimen_Colum = 12;
	private static int in_Prot_Colum = 13;
	private static int rsult_Id_Colum = 14;

	public Table_Results_List(JFrame parent, TranscluentWindow round, Users user, Request request) {
		super(parent, "", true);

		String[] columnNames = getTabHeader();
		@SuppressWarnings("rawtypes")
		Class[] types = getTypes();
		Object[][] data = getDataTable(request);

		setTitle("Списък на Резултатите");
		values_Metody = MetodyDAO.getMasiveStringAllValueMetody();
		values_Izpit_Pokazatel = List_izpitvan_pokazatelDAO.getMasiveStringAllValueList_Izpitvan_Pokazatel();
		values_Nuclide = NuclideDAO.getMasiveStringAllValueNuclide();
		values_Razmernosti = RazmernostiDAO.getMasiveStringAllValueRazmernosti();
		values_Dimension = DimensionDAO.getMasiveStringAllValueDimension();
		dataTable = data;
		listWhithChangeRow = new ArrayList<Integer>() ;
		final JTable table = new JTable();// new DefaultTableModel(rowData,
											// columnNames));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {
				if (table.getSelectedColumn() == rqst_code_Colum ) {
					int row = table.rowAtPoint(e.getPoint());
					int col = table.columnAtPoint(e.getPoint());
					String reqCodeStr = table.getValueAt(table.getSelectedRow(), rqst_code_Colum ).toString();
					Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
					new RequestMiniFrame(new JFrame(), choiseRequest);

				}
				
				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
					String reqCodeStr = table.getValueAt(table.getSelectedRow(), rqst_code_Colum ).toString();
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
								if (column == izp_Pok_Colum||column == rqst_code_Colum){
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

				setUp_Metody(table, table.getColumnModel().getColumn(mtd_Izp_Colum));
				setUp_Nuclide(table, table.getColumnModel().getColumn(nuclide_Colum));
				setUp_Razmernosti(table, table.getColumnModel().getColumn(razm_Colum));
				setUp_Dimension(table, table.getColumnModel().getColumn(dimen_Colum));

				table.getColumnModel().getColumn(rsult_Id_Colum).setWidth(0);
				table.getColumnModel().getColumn(rsult_Id_Colum).setMinWidth(0);
				table.getColumnModel().getColumn(rsult_Id_Colum).setMaxWidth(0);
				table.getColumnModel().getColumn(rsult_Id_Colum).setPreferredWidth(0);
				
				 initColumnSizes(table);

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

	private Object[][] getDataTable(Request BasicRequest) {
		List<Request> listAllRequest = new ArrayList<Request>();
		if(BasicRequest!=null){
			listAllRequest.add(BasicRequest);
		}else{
		listAllRequest = RequestDAO.getInListAllValueRequest();
		}
		List<Sample> listAllSample = SampleDAO.getInListAllValueSample();
		List<Results> listAllResults = ResultsDAO.getInListAllValueResults();
		Object[][] tableResult = new Object[listAllResults.size()][tbl_Colum];
		int i = 0;
		
		for (Request request : listAllRequest) {

			for (Sample sample : listAllSample) {
				if (sample.getRequest().getId_recuest() == request.getId_recuest()) {

					for (Results results : listAllResults) {
						try {
						if (results.getSample().getId_sample() == sample.getId_sample()) {

							
								int request_code = Integer.parseInt(results.getSample().getRequest().getRecuest_code());
								tableResult[i][rqst_code_Colum] = request_code;
								tableResult[i][smpl_code_Colum] = sample.getSample_code();
								tableResult[i][obk_Izp_Colum] = sample.getObekt_na_izpitvane()
										.getName_obekt_na_izpitvane();
								tableResult[i][mtd_Izp_Colum] = results.getMetody().getCode_metody();
								tableResult[i][izp_Pok_Colum] = results.getPokazatel().getName_pokazatel();
								tableResult[i][nuclide_Colum] = results.getNuclide().getSymbol_nuclide();
								// tableSample[i][actv_value_Colum ] =
								// BigDecimal.valueOf(results.getValue_result()).setScale(2,
								// RoundingMode.HALF_UP);
								tableResult[i][actv_value_Colum] = results.getValue_result();
								tableResult[i][uncrt_Colum] = results.getUncertainty();
								tableResult[i][sigma_Colum] = results.getSigma();
								tableResult[i][mda_Colum] = results.getMda();
								tableResult[i][razm_Colum] = results.getRtazmernosti().getName_razmernosti();
								tableResult[i][qunt_Colum] = results.getQuantity();
								tableResult[i][dimen_Colum] = "";
								if (results.getDimension() != null) {
									tableResult[i][dimen_Colum] = results.getDimension().getName_dimension();
								}
								tableResult[i][in_Prot_Colum] = results.getInProtokol();
								tableResult[i][rsult_Id_Colum] = results.getId_results();

								i++;
							
						}
						} catch (NullPointerException e) {
							JOptionPane.showInputDialog("Грешни данни за резултат:"+results.getId_results(), JOptionPane.ERROR_MESSAGE);
						} catch (NumberFormatException e) {
							JOptionPane.showInputDialog("Грешни данни за резултат:", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		}
		
		Object[][] tableSampleNew = new Object[i][tbl_Colum];
		for (int j = 0; j < tableSampleNew.length; j++) {
			for (int k = 0; k < tbl_Colum; k++) {
				tableSampleNew[j][k] = tableResult[j][k];
			}
		}

		return tableSampleNew;
	}

	private Object[][] getDataTableDAO() {
		List<Request> listAllRequest = RequestDAO.getInListAllValueRequest();
		List<Results> listAllResults = ResultsDAO.getInListAllValueResults();

		Object[][] tableSample = new Object[listAllResults.size()][tbl_Colum];
		int i = 0;
		for (Request request : listAllRequest) {

			List<Sample> listSample = SampleDAO.getListSampleFromColumnByVolume("request", request);

			for (Sample sample : listSample) {

				List<Results> listResults = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
				for (Results results : listResults) {

					try {
						int request_code = Integer.parseInt(results.getSample().getRequest().getRecuest_code());
						tableSample[i][rqst_code_Colum] = request_code;
						tableSample[i][smpl_code_Colum] = sample.getSample_code();
						tableSample[i][obk_Izp_Colum] = sample.getObekt_na_izpitvane().getName_obekt_na_izpitvane();
						tableSample[i][mtd_Izp_Colum] = results.getMetody().getCode_metody();
						tableSample[i][izp_Pok_Colum] = results.getPokazatel().getName_pokazatel();
						tableSample[i][nuclide_Colum] = results.getNuclide().getSymbol_nuclide();
						// tableSample[i][actv_value_Colum ] =
						// BigDecimal.valueOf(results.getValue_result()).setScale(2,
						// RoundingMode.HALF_UP);
						tableSample[i][actv_value_Colum] = results.getValue_result();
						tableSample[i][uncrt_Colum] = results.getUncertainty();
						tableSample[i][sigma_Colum] = results.getSigma();
						tableSample[i][mda_Colum] = results.getMda();
						tableSample[i][razm_Colum] = results.getRtazmernosti().getName_razmernosti();
						tableSample[i][qunt_Colum] = results.getQuantity();
						tableSample[i][dimen_Colum] = "";
						if (results.getDimension() != null) {
							tableSample[i][dimen_Colum] = results.getDimension().getName_dimension();
						}
						tableSample[i][in_Prot_Colum] = results.getInProtokol();
						tableSample[i][rsult_Id_Colum] = results.getId_results();

						i++;
					} catch (NullPointerException e) {
						JOptionPane.showInputDialog("Грешни данни за резултат:", JOptionPane.ERROR_MESSAGE);
					} catch (NumberFormatException e) {
						JOptionPane.showInputDialog("Грешни данни за резултат:", JOptionPane.ERROR_MESSAGE);
					}

				}

			}
		}

		Object[][] tableSampleNew = new Object[i][tbl_Colum];
		for (int j = 0; j < tableSampleNew.length; j++) {
			for (int k = 0; k < tbl_Colum; k++) {
				tableSampleNew[j][k] = tableSample[j][k];
			}
		}

		return tableSampleNew;
	}

	@SuppressWarnings("rawtypes")
	private Class[] getTypes() {

		Class[] types = { Integer.class, String.class, String.class, String.class, String.class, String.class,
				Double.class, Double.class, Integer.class, Double.class, String.class, Double.class, String.class,
				Boolean.class, Integer.class };

		return types;
	}
	private Object[] getlong() {

		Object[] types = { new Integer(2000), "123456", "123456", "123456", "123456", "123456",
				 new Double(000000),  new Double(000000), new Integer(2000), new Double(000000), "123456",  new Double(000000), "123456",
				 Boolean.TRUE, new Integer(2000) };

		return types;
	}
	
	
	private String[] getTabHeader() {
		String[] tableHeader = { "№ на Заявката", "Код на пробата", "Обект на пробата", "Метод на изпитване",
				"Изпитван показател", "Нуклид", "Активност", "Неопределеност", "Сигма", "МДА", "Размерност",
				"Количество", "Мярка", "В протокол", "Id" };
		return tableHeader;
	}

    private void initColumnSizes(JTable table) {
	   	
    	DefaultTableModel model =(DefaultTableModel) table.getModel();
        TableColumn column = null;
      
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
       Object[] longValues = getlong();
       
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < tbl_Colum; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, longValues[i],
                                 false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;

           

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
//            column.sizeWidthToFit(); //or simple
        }
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

	private static void updateData(JTable table, TranscluentWindow round) {
			
		for (int changeRoe : listWhithChangeRow) {
			Results result = ResultsDAO.getValueResultsById((int) dataTable[changeRoe][rsult_Id_Colum]);
			udateResultObject(table, changeRoe, result);
				}
				round.StopWindow();
	}

	private static void udateResultObject(JTable table, int row, Results result) {
		result.setMetody((Metody) MetodyDAO.getValueList_MetodyByName(table.getValueAt(row, mtd_Izp_Colum) + ""));
		result.setNuclide(NuclideDAO.getValueNuclideBySymbol((String) table.getValueAt(row, nuclide_Colum)));
		result.setValue_result((Double) table.getValueAt(row, actv_value_Colum));
		result.setUncertainty((Double) table.getValueAt(row, uncrt_Colum));
		result.setSigma((Integer) table.getValueAt(row, sigma_Colum));
		result.setMda((Double) table.getValueAt(row, mda_Colum));
		result.setRtazmernosti(RazmernostiDAO.getValueRazmernostiByName((String) table.getValueAt(row, razm_Colum)));
		result.setQuantity((Double) table.getValueAt(row, qunt_Colum));

		if ((table.getValueAt(row, dimen_Colum).equals(""))) {
			result.setDimension(null);
		} else {
			result.setDimension(DimensionDAO.getValueDimensionByName((String) table.getValueAt(row, dimen_Colum)));
		}
		result.setInProtokol((Boolean) table.getValueAt(row, in_Prot_Colum));
		ResultsDAO.updateResults(result);
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
	}

	
}
