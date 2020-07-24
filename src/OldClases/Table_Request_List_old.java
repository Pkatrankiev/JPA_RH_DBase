package OldClases;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import Aplication.UsersDAO;
import Aplication.ZabelejkiDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import Table.Table_Sample_List;
import WindowView.ChoiceFromListWithPlusAndMinus;
import WindowView.ChoiceL_I_P;
import WindowView.DateChoice_period;
import WindowView.DatePicker;
import WindowView.RequestMiniFrame;
import WindowView.RequestViewAplication;
import WindowView.RequestViewFunction;
import WindowView.RequestView;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class Table_Request_List_old extends JDialog {

	private static final long serialVersionUID = 1L;
	private static Request choiseRequest;
	private static String[] values_I_P;
	private static List<String> values_O_I_R;
	private static String[] values_Id_Num_Doc;
	private static String[] values_Razmernosti;
	private static String[] value_users;
	private static String[] value_Zabelejki;
	private static Map<Integer, List<String>> mapListForChangedStrObektNaIzp = new HashMap<Integer, List<String>>();

	// private static JFrame frame;
	private static List<Integer> listRowForUpdate;
	private static Object[][] dataTable;

	private static String[] tableHeader = { "№ на Заявката", "Ид.№ на документа", "Дата на заявката",
			"Изпитван продукт", "Обект на изпитване", "Показател", "Размерност", "Брой проби", "Описание на пробите",
			"Референтна дата", "Срок на изпълнение", "Време на приемане", "Приел заявката", "Забележка", "Id User" };

	private static String name_rqst_code_Colum = tableHeader[0];
//	private static String name_id_ND_Colum = tableHeader[1];
	private static String name_rqst_Date_Colum = tableHeader[2];
//	private static String name_izp_Prod_Colum = tableHeader[3];
	private static String name_obk_Izp_Colum = tableHeader[4];
	private static String name_izp_Pok_Colum = tableHeader[5];
//	private static String name_razmer_Colum = tableHeader[6];
	private static String name_cunt_Smpl_Colum = tableHeader[7];
//	private static String name_dscr_Smpl_Colum = tableHeader[8];
	private static String name_ref_Date_Colum = tableHeader[9];
	private static String name_exec_Date_Colum = tableHeader[10];
	private static String name_rcpt_Date_Colum = tableHeader[11];
//	private static String name_user_Colum = tableHeader[12];
//	private static String name_zab_Colum = tableHeader[13];
//	private static String name_user_Id_Colum = tableHeader[14];

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

	public Table_Request_List_old(JFrame parent, TranscluentWindow round, Users user, Request tamplateRequest) {
		super(parent, "Списък на Заявките", true);

		String[] columnNames = getTabHeader();
		@SuppressWarnings("rawtypes")
		Class[] types = getTypes();
		Object[][] data = getDataTable(tamplateRequest);
		dataTable = data;

		listRowForUpdate = new ArrayList<Integer>();
		values_Id_Num_Doc = Ind_num_docDAO.getMasiveStringAllValueValueInd_num_doc();
		values_I_P = Izpitvan_produktDAO.getMasiveStringAllValueIzpitvan_produkt();
		values_O_I_R = Obekt_na_izpitvane_requestDAO.getListStringAllValueObekt_na_izpitvane();
		values_Razmernosti = RazmernostiDAO.getMasiveStringAllValueRazmernosti();
		value_Zabelejki = ZabelejkiDAO.getMasiveStringAllValueZabelejki();
		value_users = UsersDAO.getMasiveStringAllName_FamilyUsers();

		final JTable table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				// Users loginUser = Login.getCurentUser();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String nameSelectedColumn = table.getColumnModel().getColumn(table.getSelectedColumn()).getHeaderValue()
						.toString();
				int selectedRow = getSelectedModelRow(table);
				String reqCodeStr = model.getValueAt(selectedRow, rqst_code_Colum).toString();
//				int selectedRow = table.rowAtPoint(e.getPoint());
//				table.columnAtPoint(e.getPoint());
				// DefaultTableModel model =(DefaultTableModel)
				// table.getModel();
				System.out.println("-----  "+nameSelectedColumn+"   code Request= "+reqCodeStr);
				
				if (getSelectedModelRow(table) != -1) {

					if (user != null && user.getIsAdmin()) {
						
						if (nameSelectedColumn.equals(name_rqst_code_Colum)) {
						
							
							Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
							new RequestMiniFrame(new JFrame(), choiseRequest);

						}
						
						if (nameSelectedColumn.equals(name_exec_Date_Colum) || nameSelectedColumn.equals(name_rqst_Date_Colum)) {
							int columnIndex = Table_Sample_List.getColumnIndex(table, nameSelectedColumn); 
							String strDate = model.getValueAt(selectedRow, columnIndex).toString();
							
							final JFrame f = new JFrame();
							DatePicker dPicer = new DatePicker(f, false, strDate);
							String str = dPicer.setPickedDate(false);
							strDate = DatePicker.formatToTabDate(str, false);
							model.setValueAt(strDate, selectedRow, columnIndex);
														
						} 
						
						
						if (nameSelectedColumn.equals(name_rcpt_Date_Colum)){
							int columnIndex = Table_Sample_List.getColumnIndex(table, nameSelectedColumn); 
							String str_date_period_reception = model.getValueAt(selectedRow, columnIndex).toString();
						Boolean forDateReception = true;
						Boolean forTable = true;
						Boolean withTime = false;
						Boolean fromTable = true;					
						final JFrame f = new JFrame();
						DateChoice_period date_period_reception = new DateChoice_period(f, str_date_period_reception,
								withTime, forDateReception, fromTable);
						date_period_reception.setVisible(true);
						model.setValueAt(DateChoice_period.get_str_period_sample(forDateReception, forTable),selectedRow, columnIndex);
						}
						
						
						
						if (nameSelectedColumn.equals(name_izp_Pok_Colum)) {
							
							EditColumnPokazatel(table, selectedRow,nameSelectedColumn );

							AddInUpdateList(selectedRow);
						}

						if (nameSelectedColumn.equals(name_obk_Izp_Colum)) {
							int columnIndex = Table_Sample_List.getColumnIndex(table, nameSelectedColumn); 
							
							Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
							if (Table_RequestToObektNaIzp.EditRequestObektIzpit(table, selectedRow, choiseRequest,
									mapListForChangedStrObektNaIzp, values_O_I_R)) {
								List<String> listFromChoiceObektNaIzp = ChoiceFromListWithPlusAndMinus
										.getMasiveStringFromChoice();
								model.setValueAt(Table_RequestToObektNaIzp.createStringListObektNaIzp(
										listFromChoiceObektNaIzp, false), selectedRow, columnIndex);
								mapListForChangedStrObektNaIzp.put(selectedRow, listFromChoiceObektNaIzp);
								AddInUpdateList(selectedRow);
							}
						}

						if (e.getClickCount() == 2) {

							if (nameSelectedColumn.equals(name_cunt_Smpl_Colum)
									|| nameSelectedColumn.equals(name_ref_Date_Colum)) {
								
								Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code",
										reqCodeStr);
								new JFrame();
								TranscluentWindow round = new TranscluentWindow();

								final Thread thread = new Thread(new Runnable() {
									@Override
									public void run() {

										JFrame f = new JFrame();
										new Table_Sample_List(f, round, choiseRequest);
									}
								});
								thread.start();
							}
						
						}

					}else {
						if (e.getClickCount() == 2) {
						
						if (parent.getName().equals("tamplete")) {
							choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
							JFrame f = new JFrame();

							new RequestView(f, user, choiseRequest, round,false);
						}
						}}

					System.out.println(getSelectedModelRow(table) + "   " + table.getSelectedRow());
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

					public Class<?> getColumnClass(int columnIndex) {
						return this.types2[columnIndex];
					}

					public Object getValueAt(int row, int col) {
						return dataTable[row][col];
					}

					@Override
					public boolean isCellEditable(int row, int column) {
						if (user != null && user.getIsAdmin()) {
							return true;
						} else {
							return false;
						}
						
					}

					public void setValueAt(Object value, int row, int col) {

						if (!dataTable[row][col].equals(value)) {					
								dataTable[row][col] = value;
				
							fireTableCellUpdated(row, col);
							if (col == user_Colum) {
								EditColumnUser(value, row);
							}

							AddInUpdateList(row);
						}
					}

				};
				
				
				
				
				table.setModel(dtm);
				table.setFillsViewportHeight(true);

				table.getColumnModel().getColumn(user_Id_Colum).setWidth(0);
				table.getColumnModel().getColumn(user_Id_Colum).setMinWidth(0);
				table.getColumnModel().getColumn(user_Id_Colum).setMaxWidth(0);
				table.getColumnModel().getColumn(user_Id_Colum).setPreferredWidth(0);

				setUp_Id_Num_Doc(table, table.getColumnModel().getColumn(id_ND_Colum));
				setUp_I_P_Column(table, table.getColumnModel().getColumn(izp_Prod_Colum));
				// setUp_O_I_R_Column(table,
				// table.getColumnModel().getColumn(obk_Izp_Colum));
				setUp_Razmernosti(table, table.getColumnModel().getColumn(razmer_Colum));
				setUp_Users(table, table.getColumnModel().getColumn(user_Colum));
				setUp_Zabelejki(table, table.getColumnModel().getColumn(zab_Colum));

				// table.getColumnModel().getColumn(user_Id_Colum).se

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
								DefaultTableModel model = (DefaultTableModel) table.getModel();
								for (int rowForUpdate : listRowForUpdate) {
									System.out.println(rowForUpdate+"  --  "+model.getValueAt(rowForUpdate, rqst_code_Colum));
								}
								updateData(table, listRowForUpdate, round);

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

	public static void setUp_Razmernosti(JTable table, TableColumn Razmernosti_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(values_Razmernosti);
		Razmernosti_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Razmernosti_Column.setCellRenderer(renderer);
	}

	public static void setUp_I_P_Column(JTable table, TableColumn I_P_Column) {

		JComboBox<?> comboBox = new JComboBox<Object>(values_I_P);
		I_P_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		I_P_Column.setCellRenderer(renderer);
	}
	
	public static void setUp_Users(JTable table, TableColumn users_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(value_users);
		users_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		users_Column.setCellRenderer(renderer);
	}

	public static void setUp_Id_Num_Doc(JTable table, TableColumn id_Num_Doc_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(values_Id_Num_Doc);
		id_Num_Doc_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		id_Num_Doc_Column.setCellRenderer(renderer);
	}

	public static void setUp_Zabelejki(JTable table, TableColumn zabel_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(value_Zabelejki);
		zabel_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		zabel_Column.setCellRenderer(renderer);
	}

	public static Request getChoiceRequest() {
		return choiseRequest;
	}

	private Object[][] getDataTable(Request tamplateRequest) {
		List<Request> listRequest = new ArrayList<Request>();
		if (tamplateRequest == null) {
			listRequest = RequestDAO.getInListAllValueRequest();
		} else {
			listRequest.add(tamplateRequest);
		}

		Object[][] tableRequest = new Object[listRequest.size()][tbl_Colum];
		int i = 0;
		List<IzpitvanPokazatel> list_All_I_P = IzpitvanPokazatelDAO.getInListAllValueIzpitvan_pokazatel();
		List<Sample> listSample = SampleDAO.getInListAllValueSample();

		for (Request request : listRequest) {
			
				Integer.parseInt(request.getRecuest_code());
				String[][] masiveSample = RequestViewAplication.getMasiveSampleFromListSampleFromRequest(request,
						listSample);
				tableRequest[i][rqst_code_Colum] = request.getRecuest_code();

				if (request.getInd_num_doc() != null) {
					tableRequest[i][id_ND_Colum] = request.getInd_num_doc().getName();
				} else {
					if (request.getExtra_module() != null) {
						if (request.getExtra_module().getInternal_applicant() != null) {
							tableRequest[i][id_ND_Colum] = request.getExtra_module().getInternal_applicant()
									.getInternal_applicant_organization();
						} else {
							if (request.getExtra_module().getExternal_applicant() != null) {
								tableRequest[i][id_ND_Colum] = request.getExtra_module().getExternal_applicant()
										.getExternal_applicant_name();
							}
						}
					}
				}
				tableRequest[i][rqst_Date_Colum] = DatePicker.formatToTabDate(request.getDate_request(), false);
				// tableRequest[i][rqst_Date_Colum ] =
				// request.getDate_request();
				tableRequest[i][izp_Prod_Colum] = request.getIzpitvan_produkt().getName_zpitvan_produkt();
				// tableRequest[i][obk_Izp_Colum] =
				// request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();

				tableRequest[i][obk_Izp_Colum] = request.getText_obekt_na_izpitvane_request();

				tableRequest[i][izp_Pok_Colum] = RequestViewFunction.CreateStringListIzpPokaz(request, list_All_I_P);
				tableRequest[i][razmer_Colum] = request.getRazmernosti().getName_razmernosti();
				tableRequest[i][cunt_Smpl_Colum] = request.getCounts_samples();
				tableRequest[i][dscr_Smpl_Colum] = request.getDescription_sample_group();
				tableRequest[i][ref_Date_Colum] = RequestViewFunction
						.GenerateStringRefDateTimeFromMasiveSample(masiveSample);
				tableRequest[i][exec_Date_Colum] = DatePicker.formatToTabDate(request.getDate_execution(), false);
				
				String[] strPeriodDate = DateChoice_period.getMasiveDateFromPeriodString(request.getDate_reception());
				
				tableRequest[i][rcpt_Date_Colum] = DateChoice_period.generateStringPeriodDate(true, true, strPeriodDate[0],
						strPeriodDate[1]);;
				tableRequest[i][user_Colum] = request.getUsers().getName_users() + " "
						+ request.getUsers().getFamily_users();
				String zab = "";
				if (request.getZabelejki() != null)
					zab = request.getZabelejki().getName_zabelejki();
				tableRequest[i][zab_Colum] = zab;
				tableRequest[i][user_Id_Colum] = request.getUsers().getId_users();
				i++;
			
		}

		return tableRequest;
	}

	@SuppressWarnings("rawtypes")
	private Class[] getTypes() {
		Class[] types = { Integer.class, String.class, String.class, String.class, String.class, String.class,
				String.class, Integer.class, String.class, String.class, String.class, String.class, String.class,
				String.class, Integer.class };
		return types;
	}

	private String[] getTabHeader() {

		return tableHeader;
	}

	private int getSelectedModelRow(JTable table) {
		return table.convertRowIndexToModel(table.getSelectedRow());
	}

	private static void EditColumnUser(Object value, int row) {
		String valueStr = value + "";
		dataTable[row][user_Id_Colum] = UsersDAO.getValueUsersByName(valueStr.substring(0, valueStr.indexOf(" ")))
				.getId_users();

	}

	private static void EditColumnPokazatel(JTable table, int row, String nameSelectedColumn) {
		int columnIndex = Table_Sample_List.getColumnIndex(table, nameSelectedColumn); 
		List<String> list = ReadListPokazatelInCell(table, row);
		JFrame f = new JFrame();
		ChoiceL_I_P choiceLP = new ChoiceL_I_P(f, list, false, ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().
				get("DefauiltTableMouseListener_EditColumnPokazatel"),null);
		if (list.size() == ChoiceL_I_P.getChoiceL_P().size()) {
			table.setValueAt(CreateStringListIzpPokaz(choiceLP), row, columnIndex);
		} else {
			JOptionPane.showMessageDialog(null, "Не можете да променяте броя Показатели", "Грешка в данните",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static List<String> ReadListPokazatelInCell(JTable table, int row) {
		List<String> list = new ArrayList<String>();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String strPokazatel = model.getValueAt(row, izp_Pok_Colum).toString().trim();
		String str = "";
		while (!strPokazatel.isEmpty()) {
			str = strPokazatel.substring(0, strPokazatel.indexOf(";") + 1);
			list.add(str.replaceAll(";", "").trim());
			strPokazatel = strPokazatel.replaceFirst(str, "");
		}
		return list;
	}

	@SuppressWarnings("static-access")
	public static String CreateStringListIzpPokaz(ChoiceL_I_P choiceLP) {

		String list_izpitvan_pokazatel = "";
		for (String izpitvan_pokazatel : choiceLP.getChoiceL_P()) {
			list_izpitvan_pokazatel = list_izpitvan_pokazatel + izpitvan_pokazatel + "; ";
		}
		return list_izpitvan_pokazatel;
	}

	private static void AddInUpdateList(int row) {
		if (listRowForUpdate.isEmpty()) {
			listRowForUpdate.add(row);
		} else {
			if (!listRowForUpdate.equals(dataTable[row][rqst_code_Colum])) {
				listRowForUpdate.add(row);
			}
		}
	}

	private static void updateData(JTable table, List<Integer> listStrRequestCodeForUpdate, TranscluentWindow round) {
		// List<Request> listAllValueRequest =
		// RequestDAO.getInListAllValueRequest();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		// int countRows = model.getRowCount();

		for (int rowForUpdate : listStrRequestCodeForUpdate) {
			Request request = RequestDAO.getRequestFromColumnByVolume("recuest_code",
					model.getValueAt(rowForUpdate, rqst_code_Colum));

			List_izpitvan_pokazatel[][] list_Masive_L_I_P = updateIzpitvanPokazatelObject(table, rowForUpdate, request);
			updateIzpitvanPokazatelInResultObject(list_Masive_L_I_P, request);
			if (mapListForChangedStrObektNaIzp != null && !mapListForChangedStrObektNaIzp.isEmpty()) {
				List<Obekt_na_izpitvane_request> listObektIzpit_request = Table_RequestToObektNaIzp
						.creadListStrFromMap(mapListForChangedStrObektNaIzp, rowForUpdate);
				Table_RequestToObektNaIzp.updateRequestToObIzpObject(request, listObektIzpit_request);
				List<String> listStringObektIzpit_request = Table_RequestToObektNaIzp
						.creatListStringfromListObekt_na_izpitvane_request(listObektIzpit_request);
				request.setText_obekt_na_izpitvane_request(
						Table_RequestToObektNaIzp.createStringListObektNaIzp(listStringObektIzpit_request, false));
			}
			updateRequestObject(table, rowForUpdate, request);

		}
		round.StopWindow();
	}

	private static void updateRequestObject(JTable table, int row, Request request) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String str_rqst_Date = model.getValueAt(row, rqst_Date_Colum).toString();
		str_rqst_Date = reformatDate(str_rqst_Date);
		
		request.setDate_request(str_rqst_Date);
		request.setInd_num_doc(Ind_num_docDAO.getValueIByName(model.getValueAt(row, id_ND_Colum).toString()));
		request.setIzpitvan_produkt(
				Izpitvan_produktDAO.getValueIzpitvan_produktByName(model.getValueAt(row, izp_Prod_Colum).toString()));

		request.setRazmernosti(
				RazmernostiDAO.getValueRazmernostiByName(model.getValueAt(row, razmer_Colum).toString()));
		request.setCounts_samples((int) model.getValueAt(row, cunt_Smpl_Colum));
		request.setDescription_sample_group(model.getValueAt(row, dscr_Smpl_Colum).toString());
		
		String str_exec_Date = model.getValueAt(row, exec_Date_Colum).toString();
		str_exec_Date = reformatDate(str_exec_Date);
		request.setDate_execution(str_exec_Date);

		String str_recept_Date = model.getValueAt(row, rcpt_Date_Colum).toString();
		str_recept_Date = DateChoice_period.reformatPeriodDateFromTable(str_recept_Date);
		request.setDate_reception(str_recept_Date);
		
		request.setUsers(UsersDAO.getValueUsersById((int) model.getValueAt(row, user_Id_Colum)));
		request.setZabelejki(ZabelejkiDAO.getValueZabelejkiByName(model.getValueAt(row, zab_Colum).toString()));

		RequestDAO.updateObjectRequest(request);
	}

	private static String reformatDate(String strDate) {
		strDate = DatePicker.reformatFromTabDate(strDate, false);
		return strDate;
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

	private static void updateIzpitvanPokazatelInResultObject(List_izpitvan_pokazatel[][] list_Masive_L_I_P,
			Request request) {
		List<Sample> listSampleDBase = SampleDAO.getListSampleFromColumnByVolume("request", request);
		for (Sample sampleDBase : listSampleDBase) {
			List<Results> listResults = ResultsDAO.getListResultsFromColumnByVolume("sample", sampleDBase);
			for (Results resultsDBase : listResults) {

				for (int i = 0; i < list_Masive_L_I_P.length; i++) {
					if (resultsDBase.getPokazatel().getName_pokazatel()
							.equals(list_Masive_L_I_P[i][0].getName_pokazatel()))
						resultsDBase.setPokazatel(list_Masive_L_I_P[i][1]);
					ResultsDAO.updateResults(resultsDBase);
				}

			}
		}
	}
}
