package Table;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Aplication.GlobalVariable;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_pokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Request;
import DBase_Class.Sample;
import WindowView.Login;
import WindowView.RequestMiniFrame;
import WindowView.RequestView;
import WindowView.RequestViewAplication;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class TableRequestList {
	
	private static Request choiseRequest;
	private static String[] values_I_P;
	private static String[] values_O_I_R;
	private static JFrame frame;

	public static void TableRequestList(String[] columnNames, Object[][] data, Class[] types) {
		frame = new JFrame();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JTable table = new JTable();// new DefaultTableModel(rowData,
											// columnNames));

		table.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {

					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					String reqCodeStr = table.getValueAt(table.getSelectedRow(), 0).toString();

					if (reqCodeStr.startsWith("templ")) {
						choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
						RequestView reqView = new RequestView(Login.getCurentUser(), choiseRequest);
						frame.setVisible(false);

					} else {
						RequestViewAplication.OpenRequestInWordDokTamplate(reqCodeStr);
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

					private Class[] types2 = types;

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

			}
		});
	}

	public static void TableRequestListEditable(String[] columnNames, Object[][] data, Class[] types) {
		JFrame frame = new JFrame("Списък на Заявките");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		values_I_P = Izpitvan_produktDAO.getMasiveStringAllValueIzpitvan_produkt();
		values_O_I_R = Obekt_na_izpitvane_requestDAO.getMasiveStringAllValueObekt_na_izpitvane();
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

				if (e.getClickCount() == 4 && table.getSelectedRow() != -1) {

					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					System.out.println("selekt row:" + row + " col: " + col);
					String reqCodeStr = table.getValueAt(table.getSelectedRow(), 0).toString();

					if (reqCodeStr.startsWith("templ")) {
						choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
						RequestView reqView = new RequestView(Login.getCurentUser(), choiseRequest);
						frame.setVisible(false);

					} else {
						RequestViewAplication.OpenRequestInWordDokTamplate(reqCodeStr);
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

					private Class[] types2 = types;

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
				setUp_I_P_Column(table, table.getColumnModel().getColumn(2));
				setUp_O_I_R_Column(table, table.getColumnModel().getColumn(3));

			}
		});
	}

	public static void setUp_I_P_Column(JTable table, TableColumn I_P_Column) {

		JComboBox comboBox = new JComboBox(values_I_P);
		I_P_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		I_P_Column.setCellRenderer(renderer);
	}

	public static void setUp_O_I_R_Column(JTable table, TableColumn O_I_R_Column) {
		
		JComboBox comboBox = new JComboBox(values_O_I_R);
		O_I_R_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		O_I_R_Column.setCellRenderer(renderer);
	}

	public static Request getChoiceRequest() {
		return choiseRequest;
	}

	public static void DrawTableWithEnableRequestList() {
		List<Request> listRequest = RequestDAO.getInListAllValueRequest();
		String[] tableHeader = { "№ на Заявката", "Дата на заявката", "Изпитван продукт", "Обект на изпитване",
				"Показател", "Размерност", "Брой проби", "Описание на пробите", "Референтна дата", "Срок на изпълнение",
				"Време на приемане", "Приел заявката", "Забележка" };
		Class[] types = { Integer.class, Calendar.class, String.class, String.class, String.class, String.class,
				Integer.class, String.class, Calendar.class, Calendar.class, Calendar.class, String.class,
				String.class };
		
		
	 
		
		
		Object[][] tableRequest = new Object[listRequest.size()][13];
		int i = 0;
		List<IzpitvanPokazatel> list_All_I_P = IzpitvanPokazatelDAO.getInListAllValueIzpitvan_pokazatel();
		List<Sample> listSample = SampleDAO.getInListAllValueSample();

		for (Request request : listRequest) {
			try {
				Integer.parseInt(request.getRecuest_code());
				String[][] masiveSample = RequestViewAplication.getMasiveSampleFromListSampleFromRequest(request, listSample);
				tableRequest[i][0] = request.getRecuest_code();
				tableRequest[i][1] = formatToTabDate(request.getDate_request(),false);
//				tableRequest[i][1] = request.getDate_request();
				tableRequest[i][2] = request.getIzpitvan_produkt().getName_zpitvan_produkt();
				tableRequest[i][3] = request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
				tableRequest[i][4] = RequestViewAplication.CreateStringListIzpPokaz(request, list_All_I_P);
				tableRequest[i][5] = request.getRazmernosti().getName_razmernosti();
				tableRequest[i][6] = request.getCounts_samples();
				tableRequest[i][7] = request.getDescription_sample_group();
				tableRequest[i][8] = RequestViewAplication.GenerateStringRefDateTime(masiveSample);
				tableRequest[i][9] = formatToTabDate(request.getDate_execution(),false);
				tableRequest[i][10] = formatToTabDate(request.getDate_time_reception(),false);
				tableRequest[i][11] = request.getUsers().getName_users() + " " + request.getUsers().getFamily_users();
				String zab = "";
				if (request.getZabelejki() != null)
					zab = request.getZabelejki().getName_zabelejki();
				tableRequest[i][12] = zab;
				i++;
			} catch (NumberFormatException e) {

			}
				catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		TableRequestListEditable(tableHeader, tableRequest, types);
	}
 
	private static String formatToTabDate(String origin_date,Boolean inTime) throws ParseException {
		String TAB_FORMAT_DATE = GlobalVariable.getTAB_FORMAT_DATE();
		String TAB_FORMAT_DATE_TIME =GlobalVariable.getTAB_FORMAT_DATE_TIME();
		String FORMAT_DATE = GlobalVariable.getFORMAT_DATE();
		String FORMAT_DATE_TIME =GlobalVariable.getFORMAT_DATE_TIME();
		String globalSeparator =GlobalVariable.getSeparator();
		SimpleDateFormat sdf ;
		String sss = "";
		SimpleDateFormat table_sdf ;
		 char separator='.';
		 char separ = globalSeparator.charAt(0);
		  if(origin_date.contains("-")){
			  separator='-'; 
		  }
		  if(separator!=separ){
			  sss = FORMAT_DATE_TIME;
			  FORMAT_DATE_TIME = FORMAT_DATE_TIME.replace(separ, separator);  
			  FORMAT_DATE = FORMAT_DATE.replace(separ, separator);
		 }
		  
		   if(inTime){
			   sss = FORMAT_DATE_TIME;
			   sdf = new SimpleDateFormat(FORMAT_DATE_TIME);
				 table_sdf = new SimpleDateFormat(TAB_FORMAT_DATE_TIME);
				
			}else{
				sss = FORMAT_DATE;
				sdf = new SimpleDateFormat(FORMAT_DATE);
			  table_sdf = new SimpleDateFormat(TAB_FORMAT_DATE);
			}
		 			
			Date date = new Date();
										
			try {
				System.out.println(origin_date+"     "+sss);
				date = sdf.parse(origin_date);
			} catch (ParseException e) {
				 JOptionPane.showMessageDialog(frame, "Преформатиране на Датата",
							"Грешка в данните", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			date = sdf.parse(origin_date);
			System.out.println(origin_date+"     "+table_sdf.format(date));
			return table_sdf.format(date);
	}

	public static void DrawTableWithRequestList() {
		List<Request> listRequest = RequestDAO.getInListAllValueRequest();
		String[] tableHeader = { "№ на Заявката", "Дата на заявката", "Изпитван продукт", "Обект на изпитване",
				"Показател", "Размерност", "Брой проби", "Описание на пробите", "Референтна дата", "Срок на изпълнение",
				"Време на приемане", "Приел заявката", "Забележка" };
		Class[] types = { Integer.class, Calendar.class, String.class, String.class, String.class, String.class,
				Integer.class, String.class, Calendar.class, Calendar.class, Calendar.class, String.class,
				String.class };

		Object[][] tableRequest = new Object[listRequest.size()][13];
		int i = 0;
		List<IzpitvanPokazatel> list_All_I_P = IzpitvanPokazatelDAO.getInListAllValueIzpitvan_pokazatel();
		List<Sample> listSample = SampleDAO.getInListAllValueSample();

		for (Request request : listRequest) {
			try {
				Integer.parseInt(request.getRecuest_code());
				String[][] masiveSample = RequestViewAplication.getMasiveSampleFromListSampleFromRequest(request, listSample);
				tableRequest[i][0] = request.getRecuest_code();
				tableRequest[i][1] = request.getDate_request();
				tableRequest[i][2] = request.getIzpitvan_produkt().getName_zpitvan_produkt();
				tableRequest[i][3] = request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
				tableRequest[i][4] = RequestViewAplication.CreateStringListIzpPokaz(request, list_All_I_P);
				tableRequest[i][5] = request.getRazmernosti().getName_razmernosti();
				tableRequest[i][6] = request.getCounts_samples();
				tableRequest[i][7] = request.getDescription_sample_group();
				tableRequest[i][8] = RequestViewAplication.GenerateStringRefDateTime(masiveSample);
				tableRequest[i][9] = request.getDate_execution();
				tableRequest[i][10] = request.getDate_time_reception();
				tableRequest[i][11] = request.getUsers().getName_users() + " " + request.getUsers().getFamily_users();
				String zab = "";
				if (request.getZabelejki() != null)
					zab = request.getZabelejki().getName_zabelejki();
				tableRequest[i][12] = zab;
				i++;
			} catch (NumberFormatException e) {

			}
		}
		TableRequestList.TableRequestListEditable(tableHeader, tableRequest, types);
	}
	
	
}
