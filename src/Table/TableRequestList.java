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
import Aplication.Ind_num_docDAO;
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
import WindowView.ExtraRequestView;
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
	private static String[] values_Id_Num_Doc;
	private static String[] values_Razmernosti;
	private static String[] value_users;
	private static String[] value_Zabelejki;
	private static JFrame frame;
	private static List<String> listStrRequestCode;
	private static Object[][] dataTable;

	private static int tbl_Colum = 15;
	private static int rqst_code_Colum = 0;
	private static int id_ND_Colum = 1;
	private static int rqst_Date_Colum = 2;
	private static int izp_Prod_Colum = 3;
	private static int obk_Izp_Colum = 4;
	private static int izp_Pok_Colum = 5;
	private static int razmer_Colum = 6;
	private static int cunt_Smpl_Colum = 7;
	private static int dscr_Smpl_Colum = 8;
	private static int ref_Date_Colum = 9;
	private static int exec_Date_Colum = 10;
	private static int rcpt_Date_Colum = 11;
	private static int user_Colum = 12;
	private static int zab_Colum = 13;
	private static int user_Id_Colum = 14;
	
	
	
	public static void TableRequestList(String[] columnNames, Object[][] data, Class[] types,TranscluentWindow round) {
		frame = new JFrame("Списък на Заявките");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dataTable = data;
		listStrRequestCode = new ArrayList<String>();
		values_Id_Num_Doc = Ind_num_docDAO.getMasiveStringAllValueValueInd_num_doc();
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
				if (table.getSelectedColumn() == rqst_code_Colum ) {
					int row = table.rowAtPoint(e.getPoint());
					int col = table.columnAtPoint(e.getPoint());
					String reqCodeStr = table.getValueAt(table.getSelectedRow(), rqst_code_Colum ).toString();
					Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
					System.out.println(row + " " + choiseRequest.getRecuest_code());
					new RequestMiniFrame(new JFrame(), choiseRequest);

				}
				if (table.getSelectedColumn() == izp_Pok_Colum  && Login.getCurentUser() != null
						&& Login.getCurentUser().getIsAdmin()) {
					int rowPokazatel = table.rowAtPoint(e.getPoint());
					EditColumnPokazatel(table, rowPokazatel);

					AddInUpdateList(rowPokazatel);
				}

				if (e.getClickCount() == 4 && table.getSelectedRow() != -1) {

					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					String reqCodeStr = table.getValueAt(table.getSelectedRow(), rqst_code_Colum ).toString();

					if (reqCodeStr.startsWith("templ")) {
						choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
						if(choiseRequest.getXtra_module()!=null){
							new ExtraRequestView(Login.getCurentUser(), choiseRequest, round);
						}else{
						new RequestView(Login.getCurentUser(), choiseRequest, round);
						}
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
		
		round.StopWindow();
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
							if (col == user_Colum ) {
								EditColumnUser(value, row);
							}

							AddInUpdateList(row);
						}
					}

				};
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				setUp_Id_Num_Doc(table, table.getColumnModel().getColumn(id_ND_Colum ));
				setUp_I_P_Column(table, table.getColumnModel().getColumn(izp_Prod_Colum ));
				setUp_O_I_R_Column(table, table.getColumnModel().getColumn(obk_Izp_Colum ));
				setUp_Razmernosti(table, table.getColumnModel().getColumn(razmer_Colum ));
				setUp_Users(table, table.getColumnModel().getColumn(user_Colum ));
				setUp_Zabelejki(table, table.getColumnModel().getColumn(zab_Colum ));

				JPanel panel_Btn = new JPanel();
				panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
				frame.getContentPane().add(panel_Btn, BorderLayout.SOUTH);
				panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

				JButton btnSave = new JButton("Запис");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						TranscluentWindow round = new TranscluentWindow();
						
						 final Thread thread = new Thread(new Runnable() {
						     @Override
						     public void run() {
						    	 
						    	 updateData(table, listStrRequestCode, round);
					    	
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
						frame.setVisible(false);
					}
				});
				panel_Btn.add(btnCancel);
			}
		});
		frame.setVisible(true);
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
	
	public static void setUp_Id_Num_Doc(JTable table, TableColumn id_Num_Doc_Column) {
		JComboBox comboBox = new JComboBox(values_Id_Num_Doc);
		id_Num_Doc_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		id_Num_Doc_Column.setCellRenderer(renderer);
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
		String[] tableHeader = { "№ на Заявката", "Ид.№ на документа", "Дата на заявката", "Изпитван продукт", "Обект на изпитване",
				"Показател", "Размерност", "Брой проби", "Описание на пробите", "Референтна дата", "Срок на изпълнение",
				"Време на приемане", "Приел заявката", "Забележка", "Id User" };
		Class[] types = { Integer.class, String.class,String.class, String.class, String.class, String.class, String.class,
				Integer.class, String.class, String.class, String.class, String.class, String.class, String.class,
				Integer.class };

		Object[][] tableRequest = new Object[listRequest.size()][tbl_Colum ];
		int i = 0;
		List<IzpitvanPokazatel> list_All_I_P = IzpitvanPokazatelDAO.getInListAllValueIzpitvan_pokazatel();
		List<Sample> listSample = SampleDAO.getInListAllValueSample();

		for (Request request : listRequest) {
			try {
				Integer.parseInt(request.getRecuest_code());
				String[][] masiveSample = RequestViewAplication.getMasiveSampleFromListSampleFromRequest(request,
						listSample);
				tableRequest[i][rqst_code_Colum ] = request.getRecuest_code();
				tableRequest[i][id_ND_Colum ] = request.getInd_num_doc().getName();
				tableRequest[i][rqst_Date_Colum ] = formatToTabDate(request.getDate_request(), false);
				// tableRequest[i][rqst_Date_Colum ] = request.getDate_request();
				tableRequest[i][izp_Prod_Colum ] = request.getIzpitvan_produkt().getName_zpitvan_produkt();
				tableRequest[i][obk_Izp_Colum ] = request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
				tableRequest[i][izp_Pok_Colum ] = RequestViewAplication.CreateStringListIzpPokaz(request, list_All_I_P);
				tableRequest[i][razmer_Colum ] = request.getRazmernosti().getName_razmernosti();
				tableRequest[i][cunt_Smpl_Colum ] = request.getCounts_samples();
				tableRequest[i][dscr_Smpl_Colum ] = request.getDescription_sample_group();
				tableRequest[i][ref_Date_Colum ] = RequestViewAplication.GenerateStringRefDateTime(masiveSample);
				tableRequest[i][exec_Date_Colum ] = formatToTabDate(request.getDate_execution(), false);
				tableRequest[i][rcpt_Date_Colum ] = formatToTabDate(request.getDate_reception(), false);
				tableRequest[i][user_Colum  ] = request.getUsers().getName_users() + " " + request.getUsers().getFamily_users();
				String zab = "";
				if (request.getZabelejki() != null)
					zab = request.getZabelejki().getName_zabelejki();
				tableRequest[i][zab_Colum ] = zab;
				tableRequest[i][user_Id_Colum ] = request.getUsers().getId_users();
				i++;
			} catch (NumberFormatException e) {

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		TableRequestList(tableHeader, tableRequest, types, round);
	}

	private static String reformatFromTabDate(String origin_date, Boolean inTime) throws ParseException {
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
			sss = TAB_FORMAT_DATE_TIME;
			sdf = new SimpleDateFormat(TAB_FORMAT_DATE_TIME);
			table_sdf = new SimpleDateFormat(FORMAT_DATE_TIME);

		} else {
			sss = TAB_FORMAT_DATE;
			sdf = new SimpleDateFormat(TAB_FORMAT_DATE);
			table_sdf = new SimpleDateFormat(FORMAT_DATE);
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
		
		if (!origin_date.substring(0, 3).contains(".")) {
			return origin_date;
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
		dataTable[row][user_Id_Colum ] = UsersDAO.getValueUsersByName(valueStr.substring(0, valueStr.indexOf(" "))).getId_users();

	}

	private static void EditColumnPokazatel(JTable table, int row) {
		List<String> list = ReadListPokazatelInCell(table, row);
		JFrame f = new JFrame();
		ChoiceL_I_P choiceLP = new ChoiceL_I_P(f, list, false);
		if (list.size() == choiceLP.getChoiceL_P().size()) {
			table.setValueAt(CreateStringListIzpPokaz(choiceLP), row, izp_Pok_Colum );
		} else {
			JOptionPane.showMessageDialog(frame, "Некоректен брой Показатели", "Грешка в данните",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static List<String> ReadListPokazatelInCell(JTable table, int row) {
		List<String> list = new ArrayList<String>();
		String strPokazatel = table.getValueAt(row, izp_Pok_Colum ).toString().trim();
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
			listStrRequestCode.add((String) dataTable[row][rqst_code_Colum ]);
		} else {
			if (!listStrRequestCode.equals(dataTable[row][rqst_code_Colum ])) {
				listStrRequestCode.add((String) dataTable[row][rqst_code_Colum ]);
			}
		}
	}

	private static void updateData(JTable table, List<String> listStrRequestCode, TranscluentWindow round) {
		List<Request> list_request = RequestDAO.getInListAllValueRequest();
		int countRows = table.getRowCount();

		for (String strRequestCode : listStrRequestCode) {
		
			for (int row = 0; row < countRows; row++) {
				if (strRequestCode.equals(table.getValueAt(row, rqst_code_Colum ))) {
					Request request = searchRequestFromListRequest(list_request, strRequestCode);
					
//					Request request = RequestDAO.getRequestFromColumnByVolume("recuest_code", strRequestCode);
					updateRequestObject(table, row, request);
					List_izpitvan_pokazatel[][] list_Masive_L_I_P = updateIzpitvanPokazatelObject(table, row, request);
					updateIzpitvanPokazatelInResultObject( list_Masive_L_I_P, request);
				}
			}
		}
		round.StopWindow();
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
		try {
			request.setDate_request(reformatFromTabDate(table.getValueAt(row, rqst_Date_Colum ) + "",false));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(frame, "Преформатиране на Датата", "Грешка в данните",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		request.setInd_num_doc(Ind_num_docDAO.getValueIByName(table.getValueAt(row, id_ND_Colum ) + ""));
		request.setIzpitvan_produkt(Izpitvan_produktDAO.getValueIzpitvan_produktByName(table.getValueAt(row, izp_Prod_Colum ) + ""));
		request.setObekt_na_izpitvane_request(
				Obekt_na_izpitvane_requestDAO.getValueObekt_na_izpitvane_requestByName(table.getValueAt(row, obk_Izp_Colum ) + ""));
		request.setRazmernosti(RazmernostiDAO.getValueRazmernostiByName(table.getValueAt(row, razmer_Colum ) + ""));
		request.setCounts_samples((int) table.getValueAt(row, cunt_Smpl_Colum ));
		request.setDescription_sample_group(table.getValueAt(row, dscr_Smpl_Colum ) + "");
		try{
		request.setDate_execution(reformatFromTabDate(table.getValueAt(row, exec_Date_Colum ) + "",false));
	} catch (ParseException e) {
		JOptionPane.showMessageDialog(frame, "Преформатиране на Датата", "Грешка в данните",
				JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	};
	try{
		request.setDate_reception(reformatFromTabDate(table.getValueAt(row, rcpt_Date_Colum ) + "",false));
	} catch (ParseException e) {
		JOptionPane.showMessageDialog(frame, "Преформатиране на Датата", "Грешка в данните",
				JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
		request.setUsers(UsersDAO.getValueUsersById((int) table.getValueAt(row, user_Id_Colum )));
		request.setZabelejki(ZabelejkiDAO.getValueZabelejkiByName(table.getValueAt(row, zab_Colum ) + ""));

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
				}

			}
		}
	}
}
