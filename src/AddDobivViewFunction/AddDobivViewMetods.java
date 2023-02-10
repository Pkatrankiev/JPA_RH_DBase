package AddDobivViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
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

import AddResultViewFunction.AddResultViewMetods;
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
import GlobalVariable.ResourceLoader;
import Reference.MounthlyReferenceForCNRDWater;
import Table.Add_DefaultTableCellRenderer;
import Table.Add_DefaultTableModel;
import Table.Add_TableHeaderMouseListener;
import Table.Add_TableMouseListener;
import WindowView.AddDobivView;
import WindowView.CheckResultClass;
import WindowView.CheckViewValueDialogFrame;
import WindowView.DatePicker;
import WindowView.RequestViewFunction;
import WindowView.TranscluentWindow;

public class AddDobivViewMetods {
	
	private static String[] masiveTipeHeader = { "Choice", "Double", "Double",  "Choice",
			 "DatePicker", "DatePicker", "Check", "Id" };
	
	private static String[] tableHeader = { "Нуклид", "Добив", "Неопределеност", "Т С И", "ДатаХимОбр", "ДатаАнализ", "Проверка",
	"Id_Result" };
	
	private static String[] masiveTipeColumn = { "Choice", "Double", "Double",  "Choice", "DatePicker", "DatePicker", "Check", "Id" };
	
	@SuppressWarnings("rawtypes")
	private static Class[] masiveClassColumn = { String.class, String.class, String.class, String.class, String.class, String.class,
			String.class, Integer.class };

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
			 ResourceLoader.appendToFile(e);
			JOptionPane.showInputDialog("Грешни данни за резултат:" , JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			 ResourceLoader.appendToFile(e);
			JOptionPane.showInputDialog("Грешни данни за резултат:", JOptionPane.ERROR_MESSAGE);
		}
		return rowFromTableDobiv;
	}

	static Object[] rowWithValueDobivsFromFile(Dobiv dobiv) {
		Object[] rowFromTableDobiv = new Object[tbl_Colum];
		rowFromTableDobiv[nuclide_Colum] = dobiv.getNuclide().getSymbol_nuclide();
		rowFromTableDobiv[actv_value_Colum] = dobiv.getValue_result();
		rowFromTableDobiv[uncrt_Colum] = dobiv.getUncertainty();
			rowFromTableDobiv[TSI_Colum] = dobiv.getTsi().getName();
		rowFromTableDobiv[dateHimObr_Colum] = dobiv.getDate_chim_oper();
		rowFromTableDobiv[dateAnaliz_Colum] = dobiv.getDate_measur();
		rowFromTableDobiv[check_Colum] = "Провери";
		rowFromTableDobiv[dobiv_Id_Colum] = null;
		return rowFromTableDobiv;
	}
	
	public static JTable CreateTableDobivs(Boolean isNewRow,JButton btnAddRow) {

				
		if (OverallVariablesAddDobiv.getListSimbolBasikNulide().size() < OverallVariablesAddDobiv.getListNuclideToMetod().size()) {
			btnAddRow.setVisible(true);
		} else {
			btnAddRow.setVisible(false);
		}

		JTable table = new JTable();
		Add_TableHeaderMouseListener.Add_TableHeaderMouseListener_( table,  masiveTipeHeader, OverallVariablesAddDobiv.getDataTable().length);

		new Add_TableMouseListener( table,  masiveTipeColumn,  actv_value_Colum,  0, 
				 nuclide_Colum, null);
		
		table.setDefaultRenderer(Object.class, Add_DefaultTableCellRenderer.Add_MyDefaultTableCellRenderer(AddDobivViewMetods.getActv_value_Colum(), 0));

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = Add_DefaultTableModel.Add_DefaultTableModel_dd(OverallVariablesAddDobiv.getDataTable(), tableHeader, masiveClassColumn, masiveTipeColumn, check_Colum);
				table.setModel(dtm);

				setUp_Nuclide(table.getColumnModel().getColumn(nuclide_Colum), isNewRow);
				setUp_TSI(table.getColumnModel().getColumn(TSI_Colum));
				
				Add_DefaultTableModel.setInvisibleColumn(table, dobiv_Id_Colum);
								

				table.setRowSelectionInterval(0, 0);
				table.requestFocus();
			}

		});
		return table;
	}

	public static String[] getTabHeader() {
		return tableHeader;
	}

	@SuppressWarnings("rawtypes")
	public static Class[] getMasiveClassColumn() {
		return masiveClassColumn;
	}

	public static void checkValueFrame(Nuclide nuclide, Metody curentMetod, Double actv_value) {
		TranscluentWindow round = new TranscluentWindow();
		
		 final Thread thread = new Thread(new Runnable() {
		 @Override
		 public void run() {
		
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
				new CheckViewValueDialogFrame(f, listCheckResultObject, actv_value, null,  round);
				 }
		 });
		 thread.start();
		
		
		
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

	
	public static List<Dobiv> creadListDobivObjectForSave(AddDobivView addDobivView, Choice choiceOIR, Choice choiceORHO, JTextField txtBasicValueResult,
			 Choice choiceIzpitProd, JTextField txtStandartCode, Choice choiceMetody, JTextField textFieldDobivDescrip, JLabel lblNameMetod) {
		
		List<Dobiv> listDobivsFromTable = new ArrayList<Dobiv>();
		
			for (int i = 0; i < OverallVariablesAddDobiv.getDataTable().length; i++) {
				String s2 = OverallVariablesAddDobiv.getDataTable()[i][actv_value_Colum].toString();
			
				if (((Double.parseDouble((String) s2)) != 0)) {
					listDobivsFromTable.add(creadDobivObject(i, choiceOIR,  choiceORHO,  txtBasicValueResult,
							  choiceIzpitProd,  txtStandartCode,  choiceMetody,  textFieldDobivDescrip));
				} 
				}
			return listDobivsFromTable;
			}
	
	public static List<Dobiv> creadListDobivObjectForDelete(){
		List<Dobiv> listDobivsForDelete = new ArrayList<Dobiv>();
		for (int i = 0; i < OverallVariablesAddDobiv.getDataTable().length; i++) {
				String s2 = OverallVariablesAddDobiv.getDataTable()[i][actv_value_Colum].toString();
			if (((Double.parseDouble((String) s2)) == 0)) {
				if (OverallVariablesAddDobiv.getDataTable()[i][dobiv_Id_Colum] != null) {
					listDobivsForDelete.add(DobivDAO
							.getDobivById((int) OverallVariablesAddDobiv.getDataTable()[i][dobiv_Id_Colum]));
					
				}
				}
			}
		return listDobivsForDelete;
			}
	
	

	public static Dobiv creadDobivObject(int i, Choice choiceOIR, Choice choiceORHO, JTextField txtBasicValueResult,
			 Choice choiceIzpitProd, JTextField txtStandartCode, Choice choiceMetody, JTextField textFieldDobivDescrip) {
		Dobiv dobiv;
		if (OverallVariablesAddDobiv.getDataTable()[i][dobiv_Id_Colum] == null) {
			dobiv = creadDobivsObject(i, new Dobiv(), choiceOIR, choiceORHO,  txtBasicValueResult,
					 choiceIzpitProd,  txtStandartCode,  choiceMetody, textFieldDobivDescrip);
					
		} else {
			dobiv =creadDobivsObject(i, DobivDAO.getDobivById((int) OverallVariablesAddDobiv.getDataTable()[i][dobiv_Id_Colum]), 
					choiceOIR, choiceORHO,  txtBasicValueResult,
					 choiceIzpitProd,  txtStandartCode,  choiceMetody, textFieldDobivDescrip);
					
		}
		dobiv.setDate_redac("");
		return dobiv;
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

	public static Boolean checkDobiv( AddDobivView addDobivView, JTextField txtStandartCode,	 JLabel lblNameMetod, Choice choiceIzpitProd,
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

	
	static void startViewtablePanel(AddDobivView addDobivView, Dobiv[] masiveDobivForMetod, JPanel basic_panel) {
		Object[][] ss = getDataTable(masiveDobivForMetod, OverallVariablesAddDobiv.getListSimbolBasikNulide());
		createDataTableAndViewTableInPanel(addDobivView, basic_panel, ss);
	}
	
	static void createDataTableAndViewTableInPanel( AddDobivView addDobivView, JPanel basic_panel, 
			Object[][] ss) {
		Boolean isNewRow = false;
		OverallVariablesAddDobiv.setDataTable ( new Object[ss.length][tbl_Colum]);
		OverallVariablesAddDobiv.setDataTable ( ss);
		isNewRow = false;
		AddDobivView.ViewTableInPanel(addDobivView, isNewRow);
		
	}
	
	static Object[][] CreateDataTableDobivFromExcelFile(Choice choiceMetody) {
		
		List<List_izpitvan_pokazatel> listIzpPokazatel = getListList_izpitvan_pokazatelToMetod(choiceMetody);
		List<Nuclide_to_Pokazatel> listNucToPok = getListNuklideToPokazatel(listIzpPokazatel);
		List<String> listSimbolBasicNuclide =  AddResultViewMetods.getListSimbolBasikNulideFNuclideToPokazatel(listNucToPok);
		
		List<String> new_listSimbolBasicNuclide = MounthlyReferenceForCNRDWater.removeDuplicates(listSimbolBasicNuclide);
		
		System.out.println(listIzpPokazatel.size());
		System.out.println(listNucToPok.size());
		System.out.println(new_listSimbolBasicNuclide.size());
		
		// masuveSimbolNuclide =
		// getMasiveSimbolNuclideToPokazatel(listNucToPok);
		Dobiv[] masiveResultsFromFile = ReadExcelFile
				.getMasivDobivsFromExcelFile(OverallVariablesAddDobiv.getDestruct_Result_List(), new_listSimbolBasicNuclide);
		Object[][] tableResult = new Object[masiveResultsFromFile.length][tbl_Colum];

		for (int i = 0; i < masiveResultsFromFile.length; i++) {
			tableResult[i] = rowWithValueDobivsFromFile(masiveResultsFromFile[i]);

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
