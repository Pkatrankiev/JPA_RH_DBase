package AddDobivViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import AddResultViewFunction.AddresultViewMetods;
import Aplication.DobivDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.Metody_to_NiclideForDobiveDAO;
import Aplication.Metody_to_PokazatelDAO;
import Aplication.NuclideDAO;
import Aplication.Nuclide_to_PokazatelDAO;
import Aplication.PostDAO;
import Aplication.TSI_DAO;
import Aplication.UsersDAO;
import DBase_Class.Dobiv;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Metody_to_NiclideForDobive;
import DBase_Class.Metody_to_Pokazatel;
import DBase_Class.Nuclide;
import DBase_Class.Nuclide_to_Pokazatel;
import DBase_Class.Users;
import ExcelFilesFunction.ReadExcelFile;
import WindowView.AddDobivView_;
import WindowView.CheckResultClass;
import WindowView.CheckViewValueDialogFrame;
import WindowView.DatePicker;
import WindowView.RequestViewFunction;

public class AddDobivViewMetods {
	
	private static int tbl_Colum = 8;
	private static int nuclide_Colum = 0;
	private static int actv_value_Colum = 1;
	private static int uncrt_Colum = 2;
	private static int TSI_Colum = 3;
	private static int dateHimObr_Colum = 4;
	private static int dateAnaliz_Colum = 5;
	private static int check_Colum = 6;
	private static int dobiv_Id_Colum = 7;
	
	public static int getActv_value_Colum() {
		return actv_value_Colum;
	}

	public static int getUncrt_Colum() {
		return uncrt_Colum;
	}

	public static int getTbl_Colum() {
		return tbl_Colum;
	}
	
	public static void BasicDataInport(Users user) {
		OverallVariablesAddDobiv.setList_Users (UsersDAO.getInListAllValueUsers());
		OverallVariablesAddDobiv.setUser_Redac(user);

		OverallVariablesAddDobiv.setList_UsersNameFamilyOIR( UsersDAO.getListStringAllName_FamilyUsersByPost(PostDAO.getValuePostByName("ОИР")));
		OverallVariablesAddDobiv.setList_UsersNameFamilyORHO ( UsersDAO.getListStringAllName_FamilyUsersByPost(PostDAO.getValuePostByName("ОРХО")));
		OverallVariablesAddDobiv.setListMetody ( MetodyDAO.getInListAllValueMetody());
		List<String> list= new ArrayList<String>();
		for (Dobiv dobiv : DobivDAO.getListAllDobiv()) {
			list.add(dobiv.getCode_Standart());
		}
		OverallVariablesAddDobiv.setListStandartCodeAllDobiv(list);
		OverallVariablesAddDobiv.setMasiveTSI ( TSI_DAO.getMasiveStringAllValueTSI());
	}

	public static List<String> getListSimbolBasikNulideToMetod(Metody metod) {
		List<String> listSimbolBasikNulide = new ArrayList<String>();
		List<Metody_to_NiclideForDobive> listMetody_NuclideForDobive = Metody_to_NiclideForDobiveDAO
				.getListMetody_to_NiclideForDobiveByMetody(metod);
		for (Metody_to_NiclideForDobive nuclide_to_Metod : listMetody_NuclideForDobive) {
			listSimbolBasikNulide.add(nuclide_to_Metod.getNuclide().getSymbol_nuclide());
		}
		return listSimbolBasikNulide;
	}

	public static List<Nuclide> getListNuclideToMetod(Metody metod) {
		List<Nuclide> listnuclide = new ArrayList<Nuclide>();
		List<Metody_to_Pokazatel> listMet_Pokaz = Metody_to_PokazatelDAO.getListMetody_to_PokazatelByMetody(metod);

		for (Metody_to_Pokazatel metody_to_Pokazatel : listMet_Pokaz) {
			List<Nuclide_to_Pokazatel> listPokazatel = Nuclide_to_PokazatelDAO
					.getListNuclide_to_PokazatelByPokazatel(metody_to_Pokazatel.getPokazatel());
			for (Nuclide_to_Pokazatel nuclide_to_Pokazatel : listPokazatel) {
				listnuclide.add(nuclide_to_Pokazatel.getNuclide());
			}

		}

		return listnuclide;

	}
	
	public static String[] createMasiveStringSimbolNuklide(List<Nuclide> list) {
		String[] masiv = new String[list.size()];
		int i = 0;
		for (Nuclide nuclide : list) {
			masiv[i] = nuclide.getSymbol_nuclide();
			i++;
		}
		return masiv;
	}
	
	public static Dobiv[] creadMasiveFromDobivsObjects_StandartCode(Metody metod, List<Dobiv> ListDobivsFromStandart_code) {

		Dobiv[] masiveDobiv = new Dobiv[ListDobivsFromStandart_code.size()];
		int i = 0;
		for (Dobiv dobiv : ListDobivsFromStandart_code) {
			if (dobiv.getMetody().getCode_metody().equals(metod.getCode_metody())) {
				masiveDobiv[i] = dobiv;
			}
			i++;
		}
		return masiveDobiv;
	}
	
	public static Object[][] getDataTable(Dobiv[] masiveDobivForMetod, List<String> listSimbolBasikNulide) {

		int countBigMasive = masiveDobivForMetod.length + listSimbolBasikNulide.size();
		Object[][] bigMasiveDobiv = new Object[countBigMasive][tbl_Colum];

		for (int i = 0; i < masiveDobivForMetod.length; i++) {
			Dobiv dobiv = masiveDobivForMetod[i];
			bigMasiveDobiv[i] = rowWithValueDobivs(dobiv);

			Iterator<String> itr = listSimbolBasikNulide.iterator();
			while (itr.hasNext()) {
				String basikNulide = itr.next();
				if (basikNulide.equals(dobiv.getNuclide().getSymbol_nuclide())) {
					itr.remove();
				}
			}
		}

		int k = masiveDobivForMetod.length;
		for (String basikNulide : listSimbolBasikNulide) {
			bigMasiveDobiv[k] = rowWithoutValueDobivs(basikNulide);
			k++;
		}
		int countMasiveTable = masiveDobivForMetod.length + listSimbolBasikNulide.size();
		Object[][] tableDobiv = new Object[countMasiveTable][tbl_Colum];
		for (int i = 0; i < tableDobiv.length; i++) {
			tableDobiv[i] = bigMasiveDobiv[i];
		}

		return tableDobiv;
	}

	public static Object[] rowWithoutValueDobivs(String BasicNuclide) {
		Object[] rowFromTableResult = new Object[tbl_Colum];
		rowFromTableResult[nuclide_Colum] = BasicNuclide;
		rowFromTableResult[actv_value_Colum] = 0.0;
		rowFromTableResult[uncrt_Colum] = 0.0;
		rowFromTableResult[TSI_Colum] = "";
		rowFromTableResult[dateHimObr_Colum] = "";
		rowFromTableResult[dateAnaliz_Colum] = "";
		rowFromTableResult[check_Colum] = "Провери";
		rowFromTableResult[dobiv_Id_Colum] = null;
		return rowFromTableResult;
	}

	public static Object[] rowWithValueDobivs(Dobiv dobiv) {
		Object[] rowFromTableDobiv = new Object[tbl_Colum];
		try {
			rowFromTableDobiv[nuclide_Colum] = dobiv.getNuclide().getSymbol_nuclide();
			rowFromTableDobiv[actv_value_Colum] = dobiv.getValue_result();
			rowFromTableDobiv[uncrt_Colum] = dobiv.getUncertainty();
			rowFromTableDobiv[TSI_Colum] = dobiv.getTsi().getName();
			rowFromTableDobiv[dateHimObr_Colum] = dobiv.getDate_chim_oper();
			rowFromTableDobiv[dateAnaliz_Colum] = dobiv.getDate_measur();
			rowFromTableDobiv[check_Colum] = "Провери";
			rowFromTableDobiv[dobiv_Id_Colum] = dobiv.getId_dobiv();
		} catch (NullPointerException e) {
			JOptionPane.showInputDialog("Грешни данни за резултат:" , JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showInputDialog("Грешни данни за резултат:", JOptionPane.ERROR_MESSAGE);
		}
		return rowFromTableDobiv;
	}

	public static JTable CreateTableDobivs(Boolean isNewRow) {

		String[] columnNames = getTabHeader();
		@SuppressWarnings("rawtypes")
		Class[] types = getTypes();

		JTable table = new JTable();// new DefaultTableModel(rowData,
									// columnNames));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				if (SwingUtilities.isRightMouseButton(e)) {
					String date = "";
					if (col == dateAnaliz_Colum) {
						date = table.getValueAt(table.getSelectedRow(), dateAnaliz_Colum).toString();
					}
					if (col == dateHimObr_Colum) {
						date = table.getValueAt(table.getSelectedRow(), dateHimObr_Colum).toString();
					}
					final JFrame f = new JFrame();
					DatePicker dPicer = new DatePicker(f, false, date);
					table.setValueAt(dPicer.setPickedDate(false), row, col);

				}
				if (table.getSelectedColumn() == check_Colum) {
					double actv_value = Double
							.parseDouble((table.getValueAt(table.getSelectedRow(), actv_value_Colum)).toString());
					Nuclide nuclide = NuclideDAO.getValueNuclideBySymbol(
							table.getValueAt(table.getSelectedRow(), nuclide_Colum).toString());
					checkValueFrame(nuclide, OverallVariablesAddDobiv.getSelectedMetod(), actv_value);
				}
			}

		});

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(OverallVariablesAddDobiv.getDataTable(), columnNames) {

					private static final long serialVersionUID = 1L;
					@SuppressWarnings("rawtypes")
					private Class[] classTypes = types;

					@SuppressWarnings({})
					@Override
					public Class<?> getColumnClass(int columnIndex) {
						return this.classTypes[columnIndex];
					}

					public Object getValueAt(int row, int col) {
						return OverallVariablesAddDobiv.getDataTable()[row][col];
					}

					@Override
					public boolean isCellEditable(int row, int column) {
						if (column < check_Colum) {
							return true;
						} else {
							return false;
						}
					}

					public void setValueAt(Object value, int row, int col) {
						if (!OverallVariablesAddDobiv.getDataTable()[row][col].equals(value)) {
							if (col == dateHimObr_Colum || col == dateAnaliz_Colum) {
								if (!DatePicker.incorrectDate((String) value, false)) {
									OverallVariablesAddDobiv.getDataTable()[row][col] = value;
									fireTableCellUpdated(row, col);
								}
							}

							if (col == actv_value_Colum || col == uncrt_Colum) {
								try {
									Double.parseDouble((String) value);
									OverallVariablesAddDobiv.getDataTable()[row][col] = value;
									fireTableCellUpdated(row, col);
								} catch (NumberFormatException e) {
								}
							}

							if (col == nuclide_Colum || col == TSI_Colum) {
								OverallVariablesAddDobiv.getDataTable()[row][col] = value;
								fireTableCellUpdated(row, col);
							}
						}

					}

					public int getColumnCount() {
						return columnNames.length;
					}

					public int getRowCount() {

						return OverallVariablesAddDobiv.getDataTable().length;
					}

				};

				table.setModel(dtm);

				setUp_Nuclide(table.getColumnModel().getColumn(nuclide_Colum), isNewRow);
				setUp_TSI(table.getColumnModel().getColumn(TSI_Colum));

				table.getColumnModel().getColumn(dobiv_Id_Colum).setWidth(0);
				table.getColumnModel().getColumn(dobiv_Id_Colum).setMinWidth(0);
				table.getColumnModel().getColumn(dobiv_Id_Colum).setMaxWidth(0);
				table.getColumnModel().getColumn(dobiv_Id_Colum).setPreferredWidth(0);

			}

		});
		return table;
	}

	public static String[] getTabHeader() {
		String[] tableHeader = { "Нуклид", "Добив", "Неопределеност", "Т С И", "ДатаХимОбр", "ДатаАнализ", "Проверка",
				"Id_Result" };
		return tableHeader;
	}

	@SuppressWarnings("rawtypes")
	public static Class[] getTypes() {

		Class[] types = { String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, Integer.class };

		return types;
	}

	public static void checkValueFrame(Nuclide nuclide, Metody curentMetod, Double actv_value) {
		List<Metody> listAllMetody = MetodyDAO.getInListAllValueMetody();
		List<CheckResultClass> listCheckResultObject = new ArrayList<CheckResultClass>();

		for (Metody metod : listAllMetody) {

			if (metod.getCode_metody().equals(curentMetod.getCode_metody())) {
				for (Dobiv dobiv : DobivDAO.getListDobivByMetody(metod)) {

					if (dobiv.getNuclide().getSymbol_nuclide().equals(nuclide.getSymbol_nuclide())) {
						int value = dobiv.getId_dobiv();
						CheckResultClass checkResultObject = new CheckResultClass(dobiv.getValue_result(), null, value);
						listCheckResultObject.add(checkResultObject);
					}
				}
			}

		}
		Collections.sort(listCheckResultObject, CheckResultClass.StuNameComparator);

		JFrame f = new JFrame();
		new CheckViewValueDialogFrame(f, listCheckResultObject, actv_value, null);
	}

	public static void setUp_Nuclide(TableColumn Nuclide_Column, Boolean isNewRow) {
		JComboBox<?> comboBox = new JComboBox<Object>(OverallVariablesAddDobiv.getMasuveSimbolBasikNuclide());
		if (isNewRow) {
			comboBox = new JComboBox<Object>(OverallVariablesAddDobiv.getMasive_NuclideToMetod());
		}
		Nuclide_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Nuclide_Column.setCellRenderer(renderer);
	}

	public static void setUp_TSI(TableColumn tSI_column) {
		JComboBox<?> comboBox = new JComboBox<Object>(OverallVariablesAddDobiv.getMasiveTSI());
		tSI_column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		tSI_column.setCellRenderer(renderer);

	}

	public static String strCurrentDataInDataTable(Object[][] dataTable) {
		String errDuplic ="";
		String errTSI ="";
		String errDateHim ="";
		String errDateAnaliz ="";
		List<String> listCodeNuclide = new ArrayList<String>();
		if (dataTable != null) {
			for (int i = 0; i < dataTable.length; i++) {
				String s1 = dataTable[i][uncrt_Colum].toString().toString();
				String s2 = dataTable[i][actv_value_Colum].toString();
				if ((Double.parseDouble((String) s1) + (Double.parseDouble((String) s2)) != 0)) {
					listCodeNuclide.add(dataTable[i][nuclide_Colum].toString());
					
					if (dataTable[i][TSI_Colum].toString().trim().isEmpty()) {
						errTSI = "Т С И " + "\n";
					}
					
					if (DatePicker.incorrectDate(dataTable[i][dateHimObr_Colum].toString().trim(), false)) {
						errDateHim =  "дата на хим. обраб." + "\n";
					}
					
					if (DatePicker.incorrectDate(dataTable[i][dateAnaliz_Colum].toString().trim(), false)) {
						errDateAnaliz = "дата на анализ" + "\n";
					}

				}
			}

			List<String> deDupStringList = new ArrayList<>(new HashSet<>(listCodeNuclide));
			
			if (deDupStringList.size() != listCodeNuclide.size()) {
				errDuplic = "повтарящи се нуклиди" + "\n";
			}
		} else {
			errDuplic =  "невъведени данни"+ "\n";
		}
		return (errTSI + errDateHim  + errDateAnaliz + errDuplic);
	}

	public static List<Dobiv> creadListFromDobivObjectForSave(AddDobivView_ addDobivView, Choice choiceOIR, Choice choiceORHO, JTextField txtBasicValueResult,
			 Choice choiceIzpitProd, JTextField txtStandartCode, Choice choiceMetody, JTextField textFieldDobivDescrip, JLabel lblNameMetod) {
		Boolean fl;
		List<Dobiv> listDobivsForSave = new ArrayList<Dobiv>();
		List<Dobiv> listDobivsForDelete = new ArrayList<Dobiv>();
		List<Dobiv> listDobivsFromTable = new ArrayList<Dobiv>();
		if (checkDobiv( addDobivView,  txtStandartCode,	  lblNameMetod,  choiceIzpitProd,
				 choiceOIR,  choiceORHO,  choiceMetody)) {
			for (int i = 0; i < OverallVariablesAddDobiv.getDataTable().length; i++) {
				String s1 = OverallVariablesAddDobiv.getDataTable()[i][uncrt_Colum].toString().toString();
				String s2 = OverallVariablesAddDobiv.getDataTable()[i][actv_value_Colum].toString();
				if ((Double.parseDouble((String) s1) != 0 || (Double.parseDouble((String) s2)) != 0)) {
					listDobivsFromTable.add(creadDobivObject(i, choiceOIR,  choiceORHO,  txtBasicValueResult,
							  choiceIzpitProd,  txtStandartCode,  choiceMetody,  textFieldDobivDescrip));
				} else {
					if (OverallVariablesAddDobiv.getDataTable()[i][dobiv_Id_Colum] != null) {
						listDobivsForDelete.add(DobivDAO.getDobivById((int) OverallVariablesAddDobiv.getDataTable()[i][dobiv_Id_Colum]));
					}
				}
			}
		}

		List<Dobiv> ListDobivsFromDBase = DobivDAO.getList_DobivByCode_Standart(txtStandartCode.getText());
		Iterator<Dobiv> itr = null;
		for (Dobiv dobiv : listDobivsFromTable) {

			itr = ListDobivsFromDBase.iterator();
			fl = false;
			while (itr.hasNext()) {
				String codeNulide = itr.next().getNuclide().getSymbol_nuclide();
				if (codeNulide.equals(dobiv.getNuclide().getSymbol_nuclide())) {
					itr.remove();
					listDobivsForSave.add(dobiv);
					fl = true;
				}
			}
			if (!fl) {
				listDobivsForSave.add(dobiv);
			}
		}

		for (Dobiv dobiv : ListDobivsFromDBase) {

			listDobivsForSave.add(dobiv);
		}

		for (Dobiv dobiv : listDobivsForDelete) {

			DobivDAO.deleteDobivById(dobiv.getId_dobiv());
		}
		;

		return listDobivsForSave;
	}

	public static Dobiv creadDobivObject(int i, Choice choiceOIR, Choice choiceORHO, JTextField txtBasicValueResult,
			 Choice choiceIzpitProd, JTextField txtStandartCode, Choice choiceMetody, JTextField textFieldDobivDescrip) {
		Dobiv dobiv;
		if (OverallVariablesAddDobiv.getDataTable()[i][dobiv_Id_Colum] == null) {
			dobiv = new Dobiv();
		} else {
			dobiv = DobivDAO.getDobivById((int) OverallVariablesAddDobiv.getDataTable()[i][dobiv_Id_Colum]);
		}
		return creadDobivsObject(i, dobiv, choiceOIR, choiceORHO,  txtBasicValueResult,
				 choiceIzpitProd,  txtStandartCode,  choiceMetody, textFieldDobivDescrip);
	}

	public static Dobiv creadDobivsObject(int i, Dobiv dobiv, Choice choiceOIR, Choice choiceORHO, JTextField txtBasicValueResult,
			 Choice choiceIzpitProd, JTextField txtStandartCode, Choice choiceMetody, JTextField textFieldDobivDescrip) {
		Object[][] dataTable = OverallVariablesAddDobiv.getDataTable();
		dobiv.setCode_Standart(txtStandartCode.getText());
		dobiv.setMetody((Metody) MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));
		dobiv.setIzpitvan_produkt(
				Izpitvan_produktDAO.getValueIzpitvan_produktByName(choiceIzpitProd.getSelectedItem()));
		dobiv.setDescription(textFieldDobivDescrip.getText());
		dobiv.setNuclide(NuclideDAO.getValueNuclideBySymbol(dataTable[i][nuclide_Colum].toString()));
		dobiv.setValue_result(Double.parseDouble(dataTable[i][actv_value_Colum].toString()));
		dobiv.setUncertainty(Double.parseDouble(dataTable[i][uncrt_Colum].toString()));
		dobiv.setTsi(TSI_DAO.getValueTSIByName(dataTable[i][TSI_Colum].toString()));
		dobiv.setDate_chim_oper(dataTable[i][dateHimObr_Colum].toString());
		dobiv.setDate_measur(dataTable[i][dateAnaliz_Colum].toString());
		dobiv.setDate_redac(RequestViewFunction.DateNaw(false));
		String choiceUser = choiceORHO.getSelectedItem();
		for (Users user : OverallVariablesAddDobiv.getList_Users()) {
			if (choiceUser.substring(0, choiceUser.indexOf(" ")).equals(user.getName_users())
					&& choiceUser.substring(choiceUser.indexOf(" ") + 1).equals(user.getFamily_users())) {
				dobiv.setUser_chim_oper(user);
			}
		}
		choiceUser = choiceOIR.getSelectedItem();
		for (Users user : OverallVariablesAddDobiv.getList_Users()) {
			if (choiceUser.substring(0, choiceUser.indexOf(" ")).equals(user.getName_users())
					&& choiceUser.substring(choiceUser.indexOf(" ") + 1).equals(user.getFamily_users())) {
				dobiv.setUser_measur(user);
			}
		}
		dobiv.setUser_redac(OverallVariablesAddDobiv.getUser_Redac());
		dobiv.setBasic_value(txtBasicValueResult.getText());

		return dobiv;
	}

	private static Boolean checkDobiv( AddDobivView_ addDobivView, JTextField txtStandartCode,	 JLabel lblNameMetod, Choice choiceIzpitProd,
	Choice choiceOIR, Choice choiceORHO, Choice choiceMetody) {
		Boolean saveCheck = true;
		String str_Error = "";

		if (txtStandartCode.getText().trim().isEmpty()) {
			txtStandartCode.setBackground(Color.RED);
		str_Error = str_Error + "код на стандарта" + "\n";
		 saveCheck = false;
		 }

		if (choiceIzpitProd.getSelectedItem().trim().isEmpty()) {
			choiceIzpitProd.setBackground(Color.RED);
			str_Error = str_Error + "изпитван продукт" + "\n";
			saveCheck = false;
		}

		if (choiceMetody.getSelectedItem().trim().isEmpty()) {
			choiceMetody.setBackground(Color.RED);
			str_Error = str_Error + "метод" + "\n";
			saveCheck = false;
		}

		if (choiceOIR.getSelectedItem().trim().isEmpty()) {
			choiceOIR.setBackground(Color.RED);
			str_Error = str_Error + "извършил анализа" + "\n";
			saveCheck = false;
		}

		if (choiceORHO.getSelectedItem().trim().isEmpty()) {
			choiceORHO.setBackground(Color.RED);
			str_Error = str_Error + "изв. хим. обработка" + "\n";
			saveCheck = false;
		}

		if (!strCurrentDataInDataTable(OverallVariablesAddDobiv.getDataTable()).trim().isEmpty()) {
			str_Error = str_Error + strCurrentDataInDataTable(OverallVariablesAddDobiv.getDataTable());
			saveCheck = false;
		}

		if (!saveCheck) {
			JOptionPane.showMessageDialog(addDobivView, str_Error, "Грешни данни за следните полета:",
					JOptionPane.ERROR_MESSAGE);
		}

		return saveCheck;
	}

	
	static void startViewtablePanel(AddDobivView_ addDobivView, Dobiv[] masiveDobivForMetod, JPanel basic_panel) {
		Object[][] ss = getDataTable(masiveDobivForMetod, OverallVariablesAddDobiv.getListSimbolBasikNulide());
		createDataTableAndViewTableInPanel(addDobivView, basic_panel, ss);
	}
	
	static void createDataTableAndViewTableInPanel( AddDobivView_ addDobivView, JPanel basic_panel, 
			Object[][] ss) {
		Boolean isNewRow = false;
		OverallVariablesAddDobiv.setDataTable ( new Object[ss.length][AddDobivViewMetods.getTbl_Colum()]);
		OverallVariablesAddDobiv.setDataTable ( ss);
		isNewRow = false;
		AddDobivView_.ViewTableInPanel(addDobivView, basic_panel,  isNewRow);
		
	}
	
	static Object[][] CreateDataTableDobivFromExcelFile(Choice choiceMetody) {
		
		List<List_izpitvan_pokazatel> listIzpPokazatel = getListList_izpitvan_pokazatelToMetod(choiceMetody);
		List<Nuclide_to_Pokazatel> listNucToPok = getListNuklideToPokazatel(listIzpPokazatel);
		List<String> listSimbolBasicNuclide =  AddresultViewMetods.getListSimbolBasikNulideFNuclideToPokazatel(listNucToPok);
		// masuveSimbolNuclide =
		// getMasiveSimbolNuclideToPokazatel(listNucToPok);
		Dobiv[] masiveResultsFromFile = ReadExcelFile
				.getMasivDobivsFromExcelFile(OverallVariablesAddDobiv.getDestruct_Result_List(), listSimbolBasicNuclide);
		System.out.println(masiveResultsFromFile.length + " ----------------------------------------");
		Object[][] tableResult = new Object[masiveResultsFromFile.length][tbl_Colum];

		for (int i = 0; i < masiveResultsFromFile.length; i++) {
			tableResult[i] = rowWithValueDobivs(masiveResultsFromFile[i]);

		}
		return tableResult;
	}
	
	public static List<Nuclide_to_Pokazatel> getListNuklideToPokazatel(List<List_izpitvan_pokazatel> listIzpPokazatel) {
		List<Nuclide_to_Pokazatel> listNucToPok = new ArrayList<Nuclide_to_Pokazatel>();
		for (List_izpitvan_pokazatel izpitvan_pokazatel : listIzpPokazatel) {
		List<Nuclide_to_Pokazatel> listNucToPokToPokazatel = Nuclide_to_PokazatelDAO.getListNuclide_to_PokazatelByPokazatel(
				List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(izpitvan_pokazatel.getName_pokazatel()));
		for (Nuclide_to_Pokazatel nuclide_to_Pokazatel : listNucToPokToPokazatel) {
			listNucToPok.add(nuclide_to_Pokazatel);
		}
		}
		return listNucToPok;
	}

	public static List<List_izpitvan_pokazatel> getListList_izpitvan_pokazatelToMetod(Choice choiceMetody) {
		List<Metody_to_Pokazatel> listMetodyToPok = Metody_to_PokazatelDAO.getListMetody_to_PokazatelByMetody(OverallVariablesAddDobiv.getSelectedMetod());
		List<List_izpitvan_pokazatel> list = new ArrayList<List_izpitvan_pokazatel>	()	;
		for (Metody_to_Pokazatel metodyToPok : listMetodyToPok) {
			list.add(metodyToPok.getPokazatel());
		}
		return list;
	}

}
