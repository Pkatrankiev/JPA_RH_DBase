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
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import Aplication.Internal_applicantDAO;
import Aplication.IzpitvanPokazatelDAO;
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
import CreateWordDocProtocol.Generate_Map_For_Request_Word_Document;
import CreateWordDocProtocol.StartGenerateDocTemplate;
import DBase_Class.Internal_applicant;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import WindowView.ChoiceL_I_P;
import WindowView.DatePicker;
import WindowView.ExtraRequestView;
import WindowView.Login;
import WindowView.RequestMiniFrame;
import WindowView.RequestView;
import WindowView.RequestViewAplication;
import WindowView.RequestViewFunction;
import WindowView.TranscluentWindow;
import WindowViewAplication.DocxMainpulator;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

public class Table_Request_List extends JDialog {

	private static Request choiseRequest;
	private static String[] values_I_P;
	private static String[] values_O_I_R;
	private static String[] values_Id_Num_Doc;
	private static String[] values_Razmernosti;
	private static String[] value_users;
	private static String[] value_Zabelejki;
	// private static JFrame frame;
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

	public Table_Request_List(JFrame parent, TranscluentWindow round, Users user4, Request tamplateRequest) {
		super(parent, "������ �� ��������", true);

		String[] columnNames = getTabHeader();
		@SuppressWarnings("rawtypes")
		Class[] types = getTypes();
		Object[][] data = getDataTable(tamplateRequest);
		int counRow = data.length;

		dataTable = data;
		listStrRequestCode = new ArrayList<String>();
		values_Id_Num_Doc = Ind_num_docDAO.getMasiveStringAllValueValueInd_num_doc();
		values_I_P = Izpitvan_produktDAO.getMasiveStringAllValueIzpitvan_produkt();
		values_O_I_R = Obekt_na_izpitvane_requestDAO.getMasiveStringAllValueObekt_na_izpitvane();
		values_Razmernosti = RazmernostiDAO.getMasiveStringAllValueRazmernosti();
		value_Zabelejki = ZabelejkiDAO.getMasiveStringAllValueZabelejki();
		value_users = UsersDAO.getMasiveStringAllName_FamilyUsers();

		final JTable table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				Users loginUser = Login.getCurentUser();
				if (table.getSelectedColumn() == rqst_code_Colum && table.getSelectedRow() != -1) {
					int row = table.rowAtPoint(e.getPoint());
					int col = table.columnAtPoint(e.getPoint());
					String reqCodeStr = table.getValueAt(table.getSelectedRow(), rqst_code_Colum).toString();
					Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
					new RequestMiniFrame(new JFrame(), choiseRequest);

				}
				if (table.getSelectedColumn() == izp_Pok_Colum && loginUser != null && loginUser.getIsAdmin()
						&& table.getSelectedRow() != -1) {
					int rowPokazatel = table.rowAtPoint(e.getPoint());
					EditColumnPokazatel(table, rowPokazatel);

					AddInUpdateList(rowPokazatel);
				}

				if (e.getClickCount() == 2 && loginUser != null && table.getSelectedRow() != -1) {
					if (loginUser.getIsAdmin()) {
						if (table.getSelectedColumn() == cunt_Smpl_Colum
								|| table.getSelectedColumn() == ref_Date_Colum) {
							String reqCodeStr = table.getValueAt(table.getSelectedRow(), rqst_code_Colum).toString();
							Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
							JFrame f = new JFrame();
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
					} else {

						String reqCodeStr = table.getValueAt(table.getSelectedRow(), rqst_code_Colum).toString();
						if (parent.getName().equals("tamplete")) {
							choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
							JFrame f = new JFrame();
							if (choiseRequest.getExtra_module() != null) {
								new ExtraRequestView(f, loginUser, choiseRequest, round);
							} else {
								new RequestView(f, loginUser, choiseRequest, round);
							}

						} else {
							choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", reqCodeStr);
							String date_time_reference = RequestViewFunction
									.GenerateStringRefDateTimeFromRequest(choiseRequest);

							Map<String, String> substitutionData = Generate_Map_For_Request_Word_Document
									.GenerateMapForRequestWordDocument(choiseRequest,
											RequestViewFunction
													.generateStringListIzpitvanPokazatelFromrequest(choiseRequest),
											RequestViewFunction.generateMasiveSampleDescriptionFromRequest(
													choiseRequest),
											date_time_reference);

							StartGenerateDocTemplate.GenerateProtokolWordDoc("Protokol.docx", choiseRequest,
									substitutionData);

							// DocxMainpulator.generateAndSend_Request_Docx("temp.docx",
							// "Z-" + choiseRequest.getRecuest_code() + "_" +
							// choiseRequest.getDate_request(),
							// substitutionData);
						}
					}

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
								if (column == ref_Date_Colum) {
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
							if (col == exec_Date_Colum) {
								String str = (String) value;
								if (!DatePicker.incorrectDate(str, true)) {
									dataTable[row][col] = value;
								}
							} else {
								dataTable[row][col] = value;
							}
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
				setUp_O_I_R_Column(table, table.getColumnModel().getColumn(obk_Izp_Colum));
				setUp_Razmernosti(table, table.getColumnModel().getColumn(razmer_Colum));
				setUp_Users(table, table.getColumnModel().getColumn(user_Colum));
				setUp_Zabelejki(table, table.getColumnModel().getColumn(zab_Colum));

				// table.getColumnModel().getColumn(user_Id_Colum).se

				JPanel panel_Btn = new JPanel();
				panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
				getContentPane().add(panel_Btn, BorderLayout.SOUTH);
				panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

				JButton btnSave = new JButton("�����");
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
				JButton btnCancel = new JButton("�����");
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
		JComboBox comboBox = new JComboBox(values_Razmernosti);
		Razmernosti_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("������� �� �����");
		Razmernosti_Column.setCellRenderer(renderer);
	}

	public static void setUp_I_P_Column(JTable table, TableColumn I_P_Column) {

		JComboBox comboBox = new JComboBox(values_I_P);
		I_P_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("������� �� �����");
		I_P_Column.setCellRenderer(renderer);
	}

	public static void setUp_O_I_R_Column(JTable table, TableColumn O_I_R_Column) {

		JComboBox comboBox = new JComboBox(values_O_I_R);
		O_I_R_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("������� �� �����");
		O_I_R_Column.setCellRenderer(renderer);
	}

	public static void setUp_Users(JTable table, TableColumn users_Column) {
		JComboBox comboBox = new JComboBox(value_users);
		users_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("������� �� �����");
		users_Column.setCellRenderer(renderer);
	}

	public static void setUp_Id_Num_Doc(JTable table, TableColumn id_Num_Doc_Column) {
		JComboBox comboBox = new JComboBox(values_Id_Num_Doc);
		id_Num_Doc_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("������� �� �����");
		id_Num_Doc_Column.setCellRenderer(renderer);
	}

	public static void setUp_Zabelejki(JTable table, TableColumn zabel_Column) {
		JComboBox comboBox = new JComboBox(value_Zabelejki);
		zabel_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("������� �� �����");
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
			try {
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
				tableRequest[i][obk_Izp_Colum] = request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
				tableRequest[i][izp_Pok_Colum] = RequestViewFunction.CreateStringListIzpPokaz(request, list_All_I_P);
				tableRequest[i][razmer_Colum] = request.getRazmernosti().getName_razmernosti();
				tableRequest[i][cunt_Smpl_Colum] = request.getCounts_samples();
				tableRequest[i][dscr_Smpl_Colum] = request.getDescription_sample_group();
				tableRequest[i][ref_Date_Colum] = RequestViewFunction
						.GenerateStringRefDateTimeFromMasiveSample(masiveSample);
				tableRequest[i][exec_Date_Colum] = DatePicker.formatToTabDate(request.getDate_execution(), false);
				tableRequest[i][rcpt_Date_Colum] = DatePicker.formatToTabDate(request.getDate_reception(), false);
				tableRequest[i][user_Colum] = request.getUsers().getName_users() + " "
						+ request.getUsers().getFamily_users();
				String zab = "";
				if (request.getZabelejki() != null)
					zab = request.getZabelejki().getName_zabelejki();
				tableRequest[i][zab_Colum] = zab;
				tableRequest[i][user_Id_Colum] = request.getUsers().getId_users();
				i++;
			} catch (NumberFormatException e) {

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		String[] tableHeader = { "� �� ��������", "��.� �� ���������", "���� �� ��������", "�������� �������",
				"����� �� ���������", "���������", "����������", "���� �����", "�������� �� �������", "���������� ����",
				"���� �� ����������", "����� �� ��������", "����� ��������", "���������", "Id User" };
		return tableHeader;
	}

	private static void EditColumnUser(Object value, int row) {
		String valueStr = value + "";
		dataTable[row][user_Id_Colum] = UsersDAO.getValueUsersByName(valueStr.substring(0, valueStr.indexOf(" ")))
				.getId_users();

	}

	private static void EditColumnPokazatel(JTable table, int row) {
		List<String> list = ReadListPokazatelInCell(table, row);
		JFrame f = new JFrame();
		ChoiceL_I_P choiceLP = new ChoiceL_I_P(f, list, false);
		if (list.size() == ChoiceL_I_P.getChoiceL_P().size()) {
			table.setValueAt(CreateStringListIzpPokaz(choiceLP), row, izp_Pok_Colum);
		} else {
			JOptionPane.showMessageDialog(null, "���������� ���� ����������", "������ � �������",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static List<String> ReadListPokazatelInCell(JTable table, int row) {
		List<String> list = new ArrayList<String>();
		String strPokazatel = table.getValueAt(row, izp_Pok_Colum).toString().trim();
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
			listStrRequestCode.add((String) dataTable[row][rqst_code_Colum]);
		} else {
			if (!listStrRequestCode.equals(dataTable[row][rqst_code_Colum])) {
				listStrRequestCode.add((String) dataTable[row][rqst_code_Colum]);
			}
		}
	}

	private static void updateData(JTable table, List<String> listStrRequestCode, TranscluentWindow round) {
		List<Request> list_request = RequestDAO.getInListAllValueRequest();
		int countRows = table.getRowCount();

		for (String strRequestCode : listStrRequestCode) {

			for (int row = 0; row < countRows; row++) {
				if (strRequestCode.equals(table.getValueAt(row, rqst_code_Colum))) {
					Request request = searchRequestFromListRequest(list_request, strRequestCode);

					// Request request =
					// RequestDAO.getRequestFromColumnByVolume("recuest_code",
					// strRequestCode);
					updateRequestObject(table, row, request);
					List_izpitvan_pokazatel[][] list_Masive_L_I_P = updateIzpitvanPokazatelObject(table, row, request);
					updateIzpitvanPokazatelInResultObject(list_Masive_L_I_P, request);
				}
			}
		}
		round.StopWindow();
	}

	private static Request searchRequestFromListRequest(List<Request> list_request, String strRequestCode) {
		Request request1 = null;
		for (Request request : list_request) {
			if (strRequestCode.equals(request.getRecuest_code())) {
				return request;
			}
		}
		return request1;
	}

	private static void updateRequestObject(JTable table, int row, Request request) {
		try {
			request.setDate_request(DatePicker.reformatFromTabDate(table.getValueAt(row, rqst_Date_Colum) + "", false));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "�������������� �� ������", "������ � �������",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		request.setInd_num_doc(Ind_num_docDAO.getValueIByName(table.getValueAt(row, id_ND_Colum) + ""));
		request.setIzpitvan_produkt(
				Izpitvan_produktDAO.getValueIzpitvan_produktByName(table.getValueAt(row, izp_Prod_Colum) + ""));
		request.setObekt_na_izpitvane_request(Obekt_na_izpitvane_requestDAO
				.getValueObekt_na_izpitvane_requestByName(table.getValueAt(row, obk_Izp_Colum) + ""));
		request.setRazmernosti(RazmernostiDAO.getValueRazmernostiByName(table.getValueAt(row, razmer_Colum) + ""));
		request.setCounts_samples((int) table.getValueAt(row, cunt_Smpl_Colum));
		request.setDescription_sample_group(table.getValueAt(row, dscr_Smpl_Colum) + "");
		try {
			request.setDate_execution(
					DatePicker.reformatFromTabDate(table.getValueAt(row, exec_Date_Colum) + "", false));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "�������������� �� ������", "������ � �������",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		;
		try {
			request.setDate_reception(
					DatePicker.reformatFromTabDate(table.getValueAt(row, rcpt_Date_Colum) + "", false));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "�������������� �� ������", "������ � �������",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		request.setUsers(UsersDAO.getValueUsersById((int) table.getValueAt(row, user_Id_Colum)));
		request.setZabelejki(ZabelejkiDAO.getValueZabelejkiByName(table.getValueAt(row, zab_Colum) + ""));

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
