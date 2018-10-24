package Table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Aplication.DimensionDAO;
import Aplication.GlobalVariable;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_pokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.NuclideDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import Aplication.UsersDAO;
import Aplication.ZabelejkiDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import WindowView.ChoiceL_I_P;
import WindowView.Login;
import WindowView.RequestMiniFrame;
import WindowView.RequestView;
import WindowView.RequestViewAplication;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class TableRequestList {

	private static Request choiseRequest;
	private static String[] values_I_P;
	private static String[] values_O_I_R;
	private static String[] values_Razmernosti;
	private static String[] value_users;
	private static String[] value_Zabelejki;
	private static JFrame frame;
	private static List<String> listStrRequestCode;
	private static Object[][] dataTable;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void TableRequestList(String[] columnNames, Object[][] data, Class[] types,TranscluentWindow round) {
		frame = new JFrame("Списък на Заявките");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dataTable = data;
		listStrRequestCode = new ArrayList<String>();
		values_I_P = Izpitvan_produktDAO.getMasiveStringAllValueIzpitvan_produkt();
		values_O_I_R = Obekt_na_izpitvane_requestDAO.getMasiveStringAllValueObekt_na_izpitvane();
		values_Razmernosti = RazmernostiDAO.getMasiveStringAllValueRazmernosti();
		value_Zabelejki = ZabelejkiDAO.getMasiveStringAllValueZabelejki();
		value_users = UsersDAO.getMasiveStringAllName_FamilyUsers();

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
					new RequestMiniFrame(new JFrame(), choiseRequest);

				}
				if (table.getSelectedColumn() == 4 && Login.getCurentUser() != null
						&& Login.getCurentUser().getIsAdmin()) {
					int rowPokazatel = table.rowAtPoint(e.getPoint());
					EditColumnPokazatel(table, rowPokazatel);

					AddInUpdateList(rowPokazatel);
				}

				if (e.getClickCount() == 4 && table.getSelectedRow() != -1) {

					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					String reqCodeStr = table.getValueAt(table.getSelectedRow(), 0).toString();

					if (reqCodeStr.startsWith("templ")) {
						choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
						new RequestView(Login.getCurentUser(), choiseRequest);
						frame.setVisible(false);

					} else {
						RequestViewAplication.OpenRequestInWordDokTamplate(reqCodeStr);
					}
				}
			}
		});

		TableFilterHeader tfh = new TableFilterHeader(table, AutoChoices.ENABLED);

		JScrollPane scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setSize(1200, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		round.StopWindow();;
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
						if (Login.getCurentUser() != null && Login.getCurentUser().getIsAdmin()) {
							if (Login.getCurentUser().getIsAdmin()) {
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
							if (col == 11) {
								EditColumnUser(value, row);
							}

							AddInUpdateList(row);
						}
					}

				};
				table.setModel(dtm);
				table.setFillsViewportHeight(true);

				// TODO Auto-generated catch block
				setUp_I_P_Column(table, table.getColumnModel().getColumn(2));
				setUp_O_I_R_Column(table, table.getColumnModel().getColumn(3));
				setUp_Razmernosti(table, table.getColumnModel().getColumn(5));
				setUp_Users(table, table.getColumnModel().getColumn(11));
				setUp_Zabelejki(table, table.getColumnModel().getColumn(12));

				JPanel panel_Btn = new JPanel();
				panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
				frame.getContentPane().add(panel_Btn, BorderLayout.SOUTH);
				panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

				JButton btnSave = new JButton("Запис");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						updateData(table, listStrRequestCode);
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
						frame.setVisible(false);
					}
				});
				panel_Btn.add(btnCancel);
			}
		});
	}

	public static void setUp_Razmernosti(JTable table, TableColumn Razmernosti_Column) {
		JComboBox comboBox = new JComboBox(values_Razmernosti);
		Razmernosti_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Razmernosti_Column.setCellRenderer(renderer);
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

	public static void setUp_Users(JTable table, TableColumn users_Column) {
		JComboBox comboBox = new JComboBox(value_users);
		users_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		users_Column.setCellRenderer(renderer);
	}

	public static void setUp_Zabelejki(JTable table, TableColumn zabel_Column) {
		JComboBox comboBox = new JComboBox(value_Zabelejki);
		zabel_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		zabel_Column.setCellRenderer(renderer);
	}

	public static Request getChoiceRequest() {
		return choiseRequest;
	}

	public static void DrawTableWithEnableRequestList(TranscluentWindow round) {
		
		
		
		List<Request> listRequest = RequestDAO.getInListAllValueRequest();
		String[] tableHeader = { "№ на Заявката", "Дата на заявката", "Изпитван продукт", "Обект на изпитване",
				"Показател", "Размерност", "Брой проби", "Описание на пробите", "Референтна дата", "Срок на изпълнение",
				"Време на приемане", "Приел заявката", "Забележка", "Id User" };
		Class[] types = { Integer.class, Calendar.class, String.class, String.class, String.class, String.class,
				Integer.class, String.class, Calendar.class, Calendar.class, Calendar.class, String.class, String.class,
				Integer.class };

		Object[][] tableRequest = new Object[listRequest.size()][14];
		int i = 0;
		List<IzpitvanPokazatel> list_All_I_P = IzpitvanPokazatelDAO.getInListAllValueIzpitvan_pokazatel();
		List<Sample> listSample = SampleDAO.getInListAllValueSample();

		for (Request request : listRequest) {
			try {
				Integer.parseInt(request.getRecuest_code());
				String[][] masiveSample = RequestViewAplication.getMasiveSampleFromListSampleFromRequest(request,
						listSample);
				tableRequest[i][0] = request.getRecuest_code();
				tableRequest[i][1] = formatToTabDate(request.getDate_request(), false);
				// tableRequest[i][1] = request.getDate_request();
				tableRequest[i][2] = request.getIzpitvan_produkt().getName_zpitvan_produkt();
				tableRequest[i][3] = request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
				tableRequest[i][4] = RequestViewAplication.CreateStringListIzpPokaz(request, list_All_I_P);
				tableRequest[i][5] = request.getRazmernosti().getName_razmernosti();
				tableRequest[i][6] = request.getCounts_samples();
				tableRequest[i][7] = request.getDescription_sample_group();
				tableRequest[i][8] = RequestViewAplication.GenerateStringRefDateTime(masiveSample);
				tableRequest[i][9] = formatToTabDate(request.getDate_execution(), false);
				tableRequest[i][10] = formatToTabDate(request.getDate_reception(), false);
				tableRequest[i][11] = request.getUsers().getName_users() + " " + request.getUsers().getFamily_users();
				String zab = "";
				if (request.getZabelejki() != null)
					zab = request.getZabelejki().getName_zabelejki();
				tableRequest[i][12] = zab;
				tableRequest[i][13] = request.getUsers().getId_users();
				i++;
			} catch (NumberFormatException e) {

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		TableRequestList(tableHeader, tableRequest, types, round);
	}

	private static String formatToTabDate(String origin_date, Boolean inTime) throws ParseException {
		String TAB_FORMAT_DATE = GlobalVariable.getTAB_FORMAT_DATE();
		String TAB_FORMAT_DATE_TIME = GlobalVariable.getTAB_FORMAT_DATE_TIME();
		String FORMAT_DATE = GlobalVariable.getFORMAT_DATE();
		String FORMAT_DATE_TIME = GlobalVariable.getFORMAT_DATE_TIME();
		String globalSeparator = GlobalVariable.getSeparator();
		SimpleDateFormat sdf;
		String sss = "";
		SimpleDateFormat table_sdf;
		char separator = '.';
		char separ = globalSeparator.charAt(0);
		if (origin_date.contains("-")) {
			separator = '-';
		}
		if (separator != separ) {
			sss = FORMAT_DATE_TIME;
			FORMAT_DATE_TIME = FORMAT_DATE_TIME.replace(separ, separator);
			FORMAT_DATE = FORMAT_DATE.replace(separ, separator);
		}

		if (inTime) {
			sss = FORMAT_DATE_TIME;
			sdf = new SimpleDateFormat(FORMAT_DATE_TIME);
			table_sdf = new SimpleDateFormat(TAB_FORMAT_DATE_TIME);

		} else {
			sss = FORMAT_DATE;
			sdf = new SimpleDateFormat(FORMAT_DATE);
			table_sdf = new SimpleDateFormat(TAB_FORMAT_DATE);
		}

		Date date = new Date();

		try {
			date = sdf.parse(origin_date);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(frame, "Преформатиране на Датата", "Грешка в данните",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		date = sdf.parse(origin_date);
		return table_sdf.format(date);
	}

	private static void EditColumnUser(Object value, int row) {
		String valueStr = value + "";
		dataTable[row][13] = UsersDAO.getValueUsersByName(valueStr.substring(0, valueStr.indexOf(" "))).getId_users();

	}

	private static void EditColumnPokazatel(JTable table, int row) {
		List<String> list = ReadListPokazatelInCell(table, row);
		JFrame f = new JFrame();
		ChoiceL_I_P choiceLP = new ChoiceL_I_P(f, list, false);
		if (list.size() == choiceLP.getChoiceL_P().size()) {
			table.setValueAt(CreateStringListIzpPokaz(choiceLP), row, 4);
		} else {
			JOptionPane.showMessageDialog(frame, "Некоректен брой Показатели", "Грешка в данните",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static List<String> ReadListPokazatelInCell(JTable table, int row) {
		List<String> list = new ArrayList<String>();
		String strPokazatel = table.getValueAt(row, 4).toString().trim();
		String str = "";
		while (!strPokazatel.isEmpty()) {
			str = strPokazatel.substring(0, strPokazatel.indexOf(";") + 1);
			list.add(str.replaceAll(";", "").trim());
			strPokazatel = strPokazatel.replaceFirst(str, "");
		}
		return list;
	}

	public static String CreateStringListIzpPokaz(ChoiceL_I_P choiceLP) {

		String list_izpitvan_pokazatel = "";
		for (String izpitvan_pokazatel : choiceLP.getChoiceL_P()) {
			list_izpitvan_pokazatel = list_izpitvan_pokazatel + izpitvan_pokazatel + "; ";
		}
		return list_izpitvan_pokazatel;
	}

	private static void AddInUpdateList(int row) {
		if (listStrRequestCode.isEmpty()) {
			listStrRequestCode.add((String) dataTable[row][0]);
		} else {
			if (!listStrRequestCode.equals(dataTable[row][0])) {
				listStrRequestCode.add((String) dataTable[row][0]);
			}
		}
	}

	private static void updateData(JTable table, List<String> listStrRequestCode) {
		List<Request> list_request = RequestDAO.getInListAllValueRequest();
		int countRows = table.getRowCount();

		for (String strRequestCode : listStrRequestCode) {
		
			for (int row = 0; row < countRows; row++) {
				if (strRequestCode.equals(table.getValueAt(row, 0))) {
					Request request = searchRequestFromListRequest(list_request, strRequestCode);
					
//					Request request = RequestDAO.getRequestFromColumnByVolume("recuest_code", strRequestCode);
					updateRequestObject(table, row, request);
					List_izpitvan_pokazatel[][] list_Masive_L_I_P = updateIzpitvanPokazatelObject(table, row, request);
					updateIzpitvanPokazatelInResultObject( list_Masive_L_I_P, request);
				}
			}
		}

	}

	private static Request searchRequestFromListRequest(List<Request> list_request, String strRequestCode) {
		Request request1=null;
		for (Request request : list_request) {
			if(strRequestCode.equals(request.getRecuest_code())){
				return request;
			}
		}
		return request1;
	}

	private static void updateRequestObject(JTable table, int row, Request request) {
		request.setDate_request(table.getValueAt(row, 1) + "");
		request.setIzpitvan_produkt(Izpitvan_produktDAO.getValueIzpitvan_produktByName(table.getValueAt(row, 2) + ""));
		request.setObekt_na_izpitvane_request(
				Obekt_na_izpitvane_requestDAO.getValueObekt_na_izpitvane_requestByName(table.getValueAt(row, 3) + ""));
		request.setRazmernosti(RazmernostiDAO.getValueRazmernostiByName(table.getValueAt(row, 5) + ""));
		request.setCounts_samples((int) table.getValueAt(row, 6));
		request.setDescription_sample_group(table.getValueAt(row, 7) + "");
		request.setDate_execution(table.getValueAt(row, 9) + "");
		request.setDate_reception(table.getValueAt(row, 10) + "");
		request.setUsers(UsersDAO.getValueUsersById((int) table.getValueAt(row, 13)));
		request.setZabelejki(ZabelejkiDAO.getValueZabelejkiByName(table.getValueAt(row, 12) + ""));

		 RequestDAO.updateObjectRequest(request);
	}
	
	private static List_izpitvan_pokazatel[][] updateIzpitvanPokazatelObject(JTable table, int row, Request request) {
		List<IzpitvanPokazatel> listIzpitvanPokazatelBase = IzpitvanPokazatelDAO
				.getValueIzpitvan_pokazatelByRequest(request);
		List_izpitvan_pokazatel[][] list_Masive_L_I_P = new List_izpitvan_pokazatel[listIzpitvanPokazatelBase
				.size()][2];
		int m = 0;
		for (String l_I_P : ReadListPokazatelInCell(table, row)) {
			list_Masive_L_I_P[m][0] = listIzpitvanPokazatelBase.get(m).getPokazatel();
			list_Masive_L_I_P[m][1] = List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(l_I_P);
			listIzpitvanPokazatelBase.get(m).setPokazatel(list_Masive_L_I_P[m][1]);
			IzpitvanPokazatelDAO.updateObjectIzpitvanPokazatel(listIzpitvanPokazatelBase.get(m));
			m++;
		}
		return list_Masive_L_I_P;
	}
	
	
	private static void updateIzpitvanPokazatelInResultObject(List_izpitvan_pokazatel[][] list_Masive_L_I_P, Request request) {
		List<Sample> listSampleDBase = SampleDAO.getListSampleFromColumnByVolume("request", request);
		for (Sample sampleDBase : listSampleDBase) {
			List<Results> listResults = ResultsDAO.getListResultsFromColumnByVolume("sample", sampleDBase);
			for (Results resultsDBase : listResults) {

				for (int i = 0; i < list_Masive_L_I_P.length; i++) {
					if (resultsDBase.getPokazatel().getName_pokazatel().equals(list_Masive_L_I_P[i][0].getName_pokazatel()))
						resultsDBase.setPokazatel(list_Masive_L_I_P[i][1]);
					ResultsDAO.updateResults(resultsDBase);
System.out.println(resultsDBase.getPokazatel()+"  "+list_Masive_L_I_P[i][0]);
				}

			}
		}
	}
}
