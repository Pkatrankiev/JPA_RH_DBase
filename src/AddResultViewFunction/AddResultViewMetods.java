package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Cursor;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import AddDobivViewFunction.AddDobivViewMetods;
import AddDobivViewFunction.OverallVariablesAddDobiv;
import AddDobivViewFunction.btnPaneAddDobivSection;
import Aplication.DimensionDAO;
import Aplication.DobivDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.NuclideDAO;
import Aplication.Nuclide_to_PokazatelDAO;
import Aplication.PostDAO;
import Aplication.RazmernostiDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import Aplication.TSI_DAO;
import Aplication.UsersDAO;
import DBase_Class.Dobiv;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Nuclide;
import DBase_Class.Nuclide_to_Pokazatel;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import ExcelFilesFunction.Destruct_Result;
import ExcelFilesFunction.ReadExcelFile;
import GlobalVariable.ResourceLoader;
import Table.Add_DefaultTableCellRenderer;
import Table.Add_DefaultTableModel;
import Table.Add_TableHeaderMouseListener;
import Table.Add_TableMouseListener;
import WindowView.AddResultsView;
import WindowView.CheckResultClass;
import WindowView.CheckViewValueDialogFrame;
import WindowView.DatePicker;
import WindowView.ReadGamaFile;
import WindowView.RequestViewFunction;
import WindowView.TranscluentWindow;


public class AddResultViewMetods {

	private static String[] masiveNameFortableHeader = { "Нуклид", "В протокол", "Активност", "Неопределеност", "МДА",
			"Размерност", "Сигма", "Количество", "Мярка", "Т С И", "ДатаХимОбр", "ДатаАнализ", "Проверка",
			"Id_Result" };

	private static String[] masiveTipeHeader = { "Choice", "Boolean_Check", "Double", "Double", "Double", "Choice",
			"Double_All", "Double_All", "Choice", "Choice", "DatePicker", "DatePicker", "Check", "Id" };

	@SuppressWarnings("rawtypes")
	private static Class[] masiveClassColumn = { String.class, Boolean.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class, String.class, String.class, String.class,
			String.class, Integer.class };

	private static String[] masiveTipeColumn = { "Choice", "Boolean_Check", "Double", "Double", "Double", "Choice",
			"Double", "Double", "Choice", "Choice", "DatePicker", "DatePicker", "Check", "Id" };

	private static int tbl_Colum = 14;
	private static int nuclide_Colum = 0;
	private static int in_Prot_Colum = 1;
	private static int actv_value_Colum = 2;
	private static int uncrt_Colum = 3;
	private static int mda_Colum = 4;
	private static int razm_Colum = 5;
	private static int sigma_Colum = 6;
	private static int qunt_Colum = 7;
	private static int dimen_Colum = 8;
	private static int TSI_Colum = 9;
	private static int dateHimObr_Colum = 10;
	private static int dateAnaliz_Colum = 11;
	private static int check_Colum = 12;
	private static int rsult_Id_Colum = 13;
	// private static int rsult_Dobiv = 14;

	public static int getActv_value_Colum() {
		return actv_value_Colum;
	}

	public static int getMda_Colum() {
		return mda_Colum;
	}

	public static int getDateHimObr_Colum() {
		return dateHimObr_Colum;
	}

	public static int getDateAnaliz_Colum() {
		return dateAnaliz_Colum;
	}

	public static int getQunt_Colum() {
		return qunt_Colum;
	}

	public static void BasicDataInport(Users user) {
		OverallVariablesAddResults.setList_Users(UsersDAO.getInListAllValueUsers());
		OverallVariablesAddResults.setList_UsersNameFamilyOIR(
				UsersDAO.getListStringAllName_FamilyUsersByPost(PostDAO.getValuePostByName("ОИР")));
		OverallVariablesAddResults.setList_UsersNameFamilyORHO(
				UsersDAO.getListStringAllName_FamilyUsersByPost(PostDAO.getValuePostByName("ОРХО")));
		OverallVariablesAddResults.setListSample(new ArrayList<Sample>());

		OverallVariablesAddResults.setUser_Redac(user);

		OverallVariablesAddResults.setValues_Razmernosti(RazmernostiDAO.getMasiveStringAllValueRazmernosti());
		OverallVariablesAddResults.setValues_Dimension(DimensionDAO.getMasiveStringAllValueDimension());
		OverallVariablesAddResults.setMasiveTSI(TSI_DAO.getMasiveStringAllValueTSI());
	}

	static Results[] creadMasiveFromResultsObjects_ChoiseSample(Sample sample, Choice choicePokazatel) {
		List<Results> ListResultsFromSample = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
		List<Results> choiceResults = new ArrayList<Results>();
		List_izpitvan_pokazatel pokazatel = AddResultViewMetods.getPokazatelObjectFromChoicePokazatel(choicePokazatel);
		for (Results result : ListResultsFromSample) {
			if (result.getPokazatel().getId_pokazatel() == pokazatel.getId_pokazatel() && result.getMetody()
					.getId_metody() == OverallVariablesAddResults.getSelectedMetod().getId_metody()) {
				choiceResults.add(result);
			}
		}
		Results[] masiveResults = new Results[choiceResults.size()];
		int i = 0;
		for (Results results : choiceResults) {
			masiveResults[i] = results;
			i++;
		}
		return masiveResults;
	}

	static List_izpitvan_pokazatel getPokazatelObjectFromChoicePokazatel(Choice choicePokazatel) {
		return List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(choicePokazatel.getSelectedItem());
	}

	static void startViewtablePanel(AddResultsView addResultsViewWithTable, JPanel basic_panel,
			Results[] masiveResultsForChoiceSample) {
		Object[][] ss = getDataTable(masiveResultsForChoiceSample,
				OverallVariablesAddResults.getListSimbolBasikNulide());
		createDataTableAndViewTableInPanel(addResultsViewWithTable, basic_panel, ss);
	}

	public static Boolean checkKorektFileName(String fileName, String codeSamample) {
		int choice = 0;
		Boolean fl = true;
		if (fileName.indexOf(codeSamample) < 0) {
			fl = false;
			// display the showOptionDialog
			Object[] options = { "Да", "Не" };
			choice = JOptionPane.showOptionDialog(null,
					"Кода  не съвпада с името на файла." + "\nкод от файл: " + fileName + " <--> въведен код: "
							+ codeSamample + " \nЩе продължите ли?",
					"Грешни данни", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (choice == JOptionPane.YES_OPTION) {
				fl = true;
			}
		}

		return fl;
	}

	public static Boolean checkForKoretMetod(List<Destruct_Result> destruct_Result_List, Choice choiceMetody) {
		Boolean fl = false;
		if (!choiceMetody.getSelectedItem().trim().isEmpty()) {
			OverallVariablesAddResults
					.setSelectedMetod(MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));

			for (Object destruct_Result : destruct_Result_List) {
				if (OverallVariablesAddResults.getSelectedMetod().getCode_metody()
						.indexOf(((Destruct_Result) destruct_Result).getMetod().replace("М.ЛИ-РХ-", "")) > 0) {
					fl = true;
				}
			}
			if (!fl) {
				JOptionPane.showMessageDialog(null, "Не съвпада избрания метод и този от файла", "Грешни данни",
						JOptionPane.ERROR_MESSAGE);
			}
			return fl;

		} else {
			JOptionPane.showMessageDialog(null, "Не е избран метод", "Грешни данни", JOptionPane.ERROR_MESSAGE);
		}
		return fl;
	}

	public static List<Nuclide_to_Pokazatel> getListNuklideToPokazatel(Choice choicePokazatel) {
		List<Nuclide_to_Pokazatel> listNucToPok = Nuclide_to_PokazatelDAO.getListNuclide_to_PokazatelByPokazatel(
				AddResultViewMetods.getPokazatelObjectFromChoicePokazatel(choicePokazatel));
		return listNucToPok;
	}

	public static List<String> getListSimbolBasikNulideFNuclideToPokazatel(List<Nuclide_to_Pokazatel> listNucToPok) {
		List<String> listSimbolBasikNulide = new ArrayList<String>();
		for (Nuclide_to_Pokazatel nuclide_to_Pokazatel : listNucToPok) {
			if (nuclide_to_Pokazatel.getNuclide().getFavorite_nuclide()) {
				listSimbolBasikNulide.add(nuclide_to_Pokazatel.getNuclide().getSymbol_nuclide());
			}

		}
		return listSimbolBasikNulide;
	}

	public static void setListNuclideToMetodAndToPokaz(Choice choicePokazatel) {
		OverallVariablesAddResults.setListSimbolBasikNulideToMetod(
				AddDobivViewMetods.getListSimbolBasikNulideToMetod(OverallVariablesAddResults.getSelectedMetod()));

		OverallVariablesAddResults.setListNucToPok(AddResultViewMetods.getListNuklideToPokazatel(choicePokazatel));

		OverallVariablesAddResults.setListSimbolBasikNulide(AddResultViewMetods
				.getListSimbolBasikNulideFNuclideToPokazatel(OverallVariablesAddResults.getListNucToPok()));

		OverallVariablesAddResults.setMasuveSimbolNuclide(
				AddResultViewMetods.getMasiveSimbolNuclideToPokazatel(OverallVariablesAddResults.getListNucToPok()));
	}

	static String[] getMasiveSimbolNuclideToPokazatel(List<Nuclide_to_Pokazatel> listNucToPok) {
		String[] masiveSimbolNuclide = new String[listNucToPok.size()];
		int i = 0;
		for (Nuclide_to_Pokazatel nuclide_to_Pokazatel : listNucToPok) {
			masiveSimbolNuclide[i] = nuclide_to_Pokazatel.getNuclide().getSymbol_nuclide();
			i++;
		}
		return masiveSimbolNuclide;
	}

	static Object[][] CreateMasiveObjectFromGeany2kFile(Choice choicePokazatel) {
		List<Nuclide_to_Pokazatel> listNucToPok = AddResultViewMetods.getListNuklideToPokazatel(choicePokazatel);
		List<String> listSimbolBasicNuclide = AddResultViewMetods
				.getListSimbolBasikNulideFNuclideToPokazatel(listNucToPok);
		OverallVariablesAddResults
				.setMasuveSimbolNuclide(AddResultViewMetods.getMasiveSimbolNuclideToPokazatel(listNucToPok));
		Results[] masiveResultsActivFromFile = ReadGamaFile.getMasivResultsWithAktiv();
		Results[] masiveResultsMDAFromFile = ReadGamaFile.getMasivResultsMDA(listSimbolBasicNuclide);
		System.out.print(masiveResultsActivFromFile.length);
		int countBigMasive = masiveResultsActivFromFile.length + masiveResultsMDAFromFile.length;
		Object[][] tableResult = new Object[countBigMasive][tbl_Colum];

		int k = 0;
		for (int i = 0; i < masiveResultsActivFromFile.length; i++) {
			tableResult[i] = rowWithValueResultsFromFile(masiveResultsActivFromFile[i]);
			k++;
		}
		for (int i = 0; i < masiveResultsMDAFromFile.length; i++) {
			tableResult[k] = rowWithValueResultsFromFile(masiveResultsMDAFromFile[i]);
			k++;

		}

		return tableResult;
	}
	
	
	
	static Object[][] CreateMasiveObjectFromGeanyAlphaFile(Choice choicePokazatel) {
		List<Nuclide_to_Pokazatel> listNucToPok = AddResultViewMetods.getListNuklideToPokazatel(choicePokazatel);
		List<String> listSimbolBasicNuclide = AddResultViewMetods
				.getListSimbolBasikNulideFNuclideToPokazatel(listNucToPok);
		OverallVariablesAddResults
				.setMasuveSimbolNuclide(AddResultViewMetods.getMasiveSimbolNuclideToPokazatel(listNucToPok));
		Results[] masiveResultsActivFromFile = ReadGamaFile.getMasivResultsWithAktivGenieAlpha();
		Results[] masiveResultsMDAFromFile = ReadGamaFile.getMasivResultsMDAGenieAlpha(listSimbolBasicNuclide);
		System.out.print(masiveResultsActivFromFile.length);
		int countBigMasive = masiveResultsActivFromFile.length + masiveResultsMDAFromFile.length;
		Object[][] tableResult = new Object[countBigMasive][tbl_Colum];

		int k = 0;
		for (int i = 0; i < masiveResultsActivFromFile.length; i++) {
			tableResult[i] = rowWithValueResultsFromFile(masiveResultsActivFromFile[i]);
			k++;
		}
		for (int i = 0; i < masiveResultsMDAFromFile.length; i++) {
			tableResult[k] = rowWithValueResultsFromFile(masiveResultsMDAFromFile[i]);
			k++;

		}

		return tableResult;
	}

	static Object[][] CreateMasiveObjectFromExcelFile(Choice choicePokazatel) {
		List<Nuclide_to_Pokazatel> listNucToPok = getListNuklideToPokazatel(choicePokazatel);
		List<String> listSimbolBasicNuclide = getListSimbolBasikNulideFNuclideToPokazatel(listNucToPok);
		Results[] masiveResultsFromFile = ReadExcelFile.getMasivResultsFromExcelFile(
				OverallVariablesAddResults.getDestruct_Result_List(), listSimbolBasicNuclide);
		System.out.println(masiveResultsFromFile.length + " ----------------------------------------");
		Object[][] tableResult = new Object[masiveResultsFromFile.length][tbl_Colum];

		for (int i = 0; i < masiveResultsFromFile.length; i++) {
			tableResult[i] = rowWithValueResultsFromFile(masiveResultsFromFile[i]);

		}
		return tableResult;
	}

	public static void AddNewRowIn_dataTable(Choice choicePokazatel) {
		List<Nuclide_to_Pokazatel> listNucToPok = AddResultViewMetods.getListNuklideToPokazatel(choicePokazatel);
		OverallVariablesAddResults.setMasive_NuclideToPokazatel(getListSimbolNuclideToPokazatel(listNucToPok));
		int countDataTable = OverallVariablesAddResults.getDataTable().length;
		Object[][] newTable = new Object[countDataTable + 1][tbl_Colum];
		for (int i = 0; i < countDataTable; i++) {
			newTable[i] = OverallVariablesAddResults.getDataTable()[i];
		}
		newTable[countDataTable] = rowWithoutValueResults(OverallVariablesAddResults.getMasive_NuclideToPokazatel()[0]);
		OverallVariablesAddResults.setDataTable(new Object[newTable.length][tbl_Colum]);
		for (int i = 0; i < newTable.length; i++) {
			OverallVariablesAddResults.getDataTable()[i] = newTable[i];
		}
	}

	private static Object[] rowWithoutValueResults(String BasicNuclide) {
		Object[] rowFromTableResult = new Object[tbl_Colum];
		rowFromTableResult[nuclide_Colum] = BasicNuclide;
		rowFromTableResult[actv_value_Colum] = 0.0;
		rowFromTableResult[uncrt_Colum] = 0.0;
		rowFromTableResult[mda_Colum] = 0.0;
		rowFromTableResult[razm_Colum] = OverallVariablesAddResults.getChoiseRequest().getRazmernosti()
				.getName_razmernosti();
		rowFromTableResult[sigma_Colum] = 2;
		rowFromTableResult[qunt_Colum] = 0.0;
		rowFromTableResult[dimen_Colum] = "";
		rowFromTableResult[dimen_Colum] = OverallVariablesAddResults.getValues_Dimension()[0];
		rowFromTableResult[TSI_Colum] = "";
		rowFromTableResult[dateHimObr_Colum] = "";
		rowFromTableResult[dateAnaliz_Colum] = "";
		rowFromTableResult[in_Prot_Colum] = false;
		rowFromTableResult[check_Colum] = "Провери";
		rowFromTableResult[rsult_Id_Colum] = null;
		return rowFromTableResult;
	}

	private static String[] getListSimbolNuclideToPokazatel(List<Nuclide_to_Pokazatel> listNucToPok) {
		String[] listSimbolBasikNulide = new String[listNucToPok.size()];
		int i = 0;
		for (Nuclide_to_Pokazatel nuclide_to_Pokazatel : listNucToPok) {
			listSimbolBasikNulide[i] = nuclide_to_Pokazatel.getNuclide().getSymbol_nuclide();
			i++;
		}
		return listSimbolBasikNulide;
	}

	static Object[] rowWithValueResultsFromFile(Results result) {
		Object[] rowFromTableResult = new Object[tbl_Colum];
		
		rowFromTableResult[nuclide_Colum] = result.getNuclide().getSymbol_nuclide();
		rowFromTableResult[actv_value_Colum] = result.getValue_result();
		rowFromTableResult[uncrt_Colum] = result.getUncertainty();
		rowFromTableResult[mda_Colum] = result.getMda();
		if(result.getValue_result()<result.getMda()){
			rowFromTableResult[actv_value_Colum] = 0;
			rowFromTableResult[uncrt_Colum] = 0;
		}
		rowFromTableResult[razm_Colum] = result.getRazmernosti().getName_razmernosti();
		rowFromTableResult[sigma_Colum] = result.getSigma();
		rowFromTableResult[qunt_Colum] = result.getQuantity();
		rowFromTableResult[dimen_Colum] = result.getDimension().getName_dimension();
		rowFromTableResult[TSI_Colum] = result.getTsi().getName();
		rowFromTableResult[dateHimObr_Colum] = result.getDate_chim_oper();
		rowFromTableResult[dateAnaliz_Colum] = result.getDate_measur();
		rowFromTableResult[in_Prot_Colum] = false;
		rowFromTableResult[check_Colum] = "Провери";
		rowFromTableResult[rsult_Id_Colum] = null;
		return rowFromTableResult;
	}

	private static Object[] rowWithValueResults(Results results) {
		Object[] rowFromTableResult = new Object[tbl_Colum];
		try {
			rowFromTableResult[nuclide_Colum] = results.getNuclide().getSymbol_nuclide();
			rowFromTableResult[actv_value_Colum] = results.getValue_result();
			rowFromTableResult[uncrt_Colum] = results.getUncertainty();
			rowFromTableResult[mda_Colum] = results.getMda();
			rowFromTableResult[razm_Colum] = results.getRazmernosti().getName_razmernosti();
			rowFromTableResult[sigma_Colum] = results.getSigma();
			rowFromTableResult[qunt_Colum] = results.getQuantity();
			rowFromTableResult[dimen_Colum] = "";
			if (results.getDimension() != null) {
				rowFromTableResult[dimen_Colum] = results.getDimension().getName_dimension();
			}
			rowFromTableResult[TSI_Colum] = results.getTsi().getName();
			rowFromTableResult[dateHimObr_Colum] = results.getDate_chim_oper();
			rowFromTableResult[dateAnaliz_Colum] = results.getDate_measur();
			rowFromTableResult[in_Prot_Colum] = results.getInProtokol();
			rowFromTableResult[check_Colum] = "Провери";
			rowFromTableResult[rsult_Id_Colum] = results.getId_results();
		} catch (NullPointerException | NumberFormatException e) {
			 ResourceLoader.appendToFile(e);
			JOptionPane.showInputDialog("Грешни данни за резултат:" + results.getId_results(),
					JOptionPane.ERROR_MESSAGE);
		} 

		return rowFromTableResult;

	}

	static void createDataTableAndViewTableInPanel(AddResultsView addResultsViewWithTable, JPanel basic_panel,
			Object[][] ss) {
		Boolean isNewRow = false;
		OverallVariablesAddResults.setDataTable(new Object[ss.length][tbl_Colum]);
		OverallVariablesAddResults.setDataTable(ss);
		isNewRow = false;
		AddResultsView.ViewTableInPanel(addResultsViewWithTable, isNewRow);

	}

	public static JTable CreateTableResults(Boolean isNewRow, JButton btnAddRow, JTableHeader header,
			Choice choiceSmplCode) {

		if (1 < OverallVariablesAddResults.getListNucToPok().size()) {
			btnAddRow.setVisible(true);
		} else {
			btnAddRow.setVisible(false);
		}

		JTable table = new JTable();

		Add_TableHeaderMouseListener.Add_TableHeaderMouseListener_(table, masiveTipeHeader,
				OverallVariablesAddResults.getDataTable().length);

		new Add_TableMouseListener(table, masiveTipeColumn, actv_value_Colum, mda_Colum, nuclide_Colum, choiceSmplCode);

		table.setDefaultRenderer(Object.class, Add_DefaultTableCellRenderer.Add_MyDefaultTableCellRenderer(
				AddResultViewMetods.getActv_value_Colum(), AddResultViewMetods.getMda_Colum()));

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				DefaultTableModel dtm = Add_DefaultTableModel.Add_DefaultTableModel_dd(
						OverallVariablesAddResults.getDataTable(), masiveNameFortableHeader, masiveClassColumn,
						masiveTipeColumn, check_Colum);

				table.setModel(dtm);

				setUp_Nuclide(table.getColumnModel().getColumn(nuclide_Colum), isNewRow);

				setUp_ValueInComboBox(table.getColumnModel().getColumn(razm_Colum),
						OverallVariablesAddResults.getValues_Razmernosti());
				setUp_ValueInComboBox(table.getColumnModel().getColumn(dimen_Colum),
						OverallVariablesAddResults.getValues_Dimension());
				setUp_ValueInComboBox(table.getColumnModel().getColumn(TSI_Colum),
						OverallVariablesAddResults.getMasiveTSI());

				Add_DefaultTableModel.setInvisibleColumn(table, rsult_Id_Colum);

				if (OverallVariablesAddResults.getDataTable().length > 0) {
					table.setRowSelectionInterval(0, 0);
					table.requestFocus();
				}

			}

		});
		return table;
	}

	public static void checkResultsValueFrame(Nuclide nuclide, Sample samp, Double actv_value, Double mda) {
		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				List<Sample> listAllSamp = SampleDAO.getInListAllValueSample();
				List<CheckResultClass> listCheckResultObject = new ArrayList<CheckResultClass>();

				for (Sample sample : listAllSamp) {
					if (sample.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane()
							.equals(samp.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane())
							&& sample.getDescription_sample().equals(samp.getDescription_sample())) {
						for (Results result : ResultsDAO.getListResultsFromColumnByVolume("sample", sample)) {
							if (result.getNuclide().getSymbol_nuclide().equals(nuclide.getSymbol_nuclide())) {
								int value = Integer.parseInt(sample.getRequest().getRecuest_code());
								CheckResultClass checkResultObject = new CheckResultClass(result.getValue_result(),
										result.getMda(), value);
								listCheckResultObject.add(checkResultObject);
							}
						}
					}

				}
				Collections.sort(listCheckResultObject, CheckResultClass.StuNameComparator);

				JFrame f = new JFrame();
				new CheckViewValueDialogFrame(f, listCheckResultObject, actv_value, mda, round);
			}
		});
		thread.start();
	}

	public static void setUp_Nuclide(TableColumn nuclide_Column, Boolean isNewRow) {
		JComboBox<?> comboBox = new JComboBox<Object>(OverallVariablesAddResults.getMasuveSimbolNuclide());
		if (isNewRow) {
			comboBox = new JComboBox<Object>(OverallVariablesAddResults.getMasive_NuclideToPokazatel());
		}
		nuclide_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		nuclide_Column.setCellRenderer(renderer);
	}

	public static void setUp_ValueInComboBox(TableColumn Column, String[] valueForComboBox) {
		JComboBox<?> comboBox = new JComboBox<Object>(valueForComboBox);
		Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Column.setCellRenderer(renderer);
	}

	static Object[][] getDataTable(Results[] masiveResultsForChoiceSample, List<String> listSimbolBasikNulide) {

		int countBigMasive = masiveResultsForChoiceSample.length + listSimbolBasikNulide.size();
		Object[][] bigMasiveResult = new Object[countBigMasive][tbl_Colum];

		for (int i = 0; i < masiveResultsForChoiceSample.length; i++) {
			Results results = masiveResultsForChoiceSample[i];
			bigMasiveResult[i] = rowWithValueResults(results);

			Iterator<String> itr = listSimbolBasikNulide.iterator();
			while (itr.hasNext()) {
				String basikNulide = itr.next();
				if (basikNulide.equals(results.getNuclide().getSymbol_nuclide())) {
					itr.remove();
				}
			}
		}

		int k = masiveResultsForChoiceSample.length;
		for (String basikNulide : listSimbolBasikNulide) {
			bigMasiveResult[k] = rowWithoutValueResults(basikNulide);
			k++;
		}
		int countMasiveTable = masiveResultsForChoiceSample.length + listSimbolBasikNulide.size();
		Object[][] tableResult = new Object[countMasiveTable][tbl_Colum];
		for (int i = 0; i < tableResult.length; i++) {
			tableResult[i] = bigMasiveResult[i];
		}

		return tableResult;
	}
	static int getMaxUncertaintyFromCode_metody(Metody metod){
		int uncertainty;
		String str = metod.getCode_metody();
		int k = str.indexOf("М.ЛИ-РХ-");
		int key = Integer.parseInt(str.substring(k+8,k+10));
		
		switch (key) {
		case 1:
		case 7:
		case 16:
			uncertainty = 74;
			break;
		case 3:
		case 14:
		case 15:
			uncertainty = 72;
			break;
		case 12:
			uncertainty = 76;
			break;
		case 10:
			uncertainty = 52;
			break;
		default:
			uncertainty = 0;
			break;
		} 
		
	return uncertainty;
	}
	
	static String strCurrentDataInDataTable(Object[][] dataTable, Choice choiceDobiv, Choice choicePokazatel) {
		int maxUncertainty = getMaxUncertaintyFromCode_metody(OverallVariablesAddResults.getSelectedMetod());
		String errDuplic = "";
		String errTSI = "";
		String uncrtError = "";
		String errDateAnaliz = "";
		String errRazm = "";
		String errQunt = "";
		String errDim = "";
		String errDobiv = "";
		String inProtokol = "резултати в протокол" + "\n";
		List<String> listCodeNuclide = new ArrayList<String>();
		if (dataTable != null) {
			for (int i = 0; i < dataTable.length; i++) {
				Double mda = Double.parseDouble((String) dataTable[i][mda_Colum].toString());
				Double actv = Double.parseDouble((String) dataTable[i][actv_value_Colum].toString());
				Double uncrt = Double.parseDouble((String) dataTable[i][uncrt_Colum].toString());
				if (mda + actv != 0) {
					listCodeNuclide.add(dataTable[i][nuclide_Colum].toString());
					if (dataTable[i][razm_Colum].toString().trim().isEmpty()) {
						errRazm = "размерност " + "\n";
					}
					String razm = dataTable[i][razm_Colum].toString();
					String qunt = dataTable[i][qunt_Colum].toString();
					if (!razm.trim().isEmpty() && !razm.replace("Bq", "").isEmpty()) {
						if (qunt.trim().isEmpty() || Double.parseDouble(qunt) <= 0) {
							errQunt = "количество " + "\n";
						}

						if (dataTable[i][dimen_Colum].toString().trim().isEmpty()) {
							errDim = "мярка " + "\n";
						}

					}

					if (dataTable[i][TSI_Colum].toString().trim().isEmpty()) {
						errTSI = "Т С И " + "\n";
					}

					if (DatePicker.incorrectDate(dataTable[i][dateAnaliz_Colum].toString().trim(), false)) {
						errDateAnaliz = "дата на анализ" + "\n";
					}

				}

				if (dataTable[i][in_Prot_Colum].toString() == "true") {
					String nuclede_uncrtError = "";
					if (actv != 0) {
						if (uncrt == 0) {
							nuclede_uncrtError = "нулева неопределеност";
						} else if ((uncrt / actv) * 100 > maxUncertainty) {
							nuclede_uncrtError = "неопределеност >"+maxUncertainty+"%";
						}
					}
					if (!nuclede_uncrtError.equals("")) {

						uncrtError += dataTable[i][nuclide_Colum].toString() + " е  с " + nuclede_uncrtError + "\n";
					}
					inProtokol = "";
				}
				int switCase = AddResultViewMetods.selestTypeReadFileByChoiceMetod(
						OverallVariablesAddResults.getSelectedMetod(), choicePokazatel.getSelectedItem().toString());
				if (switCase != 3 && switCase != 10) {
					Double dobivValueFromDestruct_Result = 0.0;
					
					try {
						List<Destruct_Result> destruct_Result_List = OverallVariablesAddResults
								.getDestruct_Result_List();
						if (destruct_Result_List != null) {
							
							
							for (Destruct_Result destruct_Result : destruct_Result_List) {

								if (dataTable[i][nuclide_Colum].toString().equals(destruct_Result.getNuclide())|| switCase == 1) {
									System.out.println("---------------------" + destruct_Result.getDobiv());
									dobivValueFromDestruct_Result = Double.parseDouble((String) destruct_Result.getDobiv());
								}
							}
						}
						if (!choiceDobiv.getSelectedItem().toString().isEmpty()) {
							if (!OverallVariablesAddResults.getValueFromDBase()) {
								List<Dobiv> listDobiv = DobivDAO
										.getList_DobivByCode_Standart(choiceDobiv.getSelectedItem().toString());
								System.out.println(listDobiv.size());
								if (listDobiv.size() > 0) {
									for (Dobiv dobiv : listDobiv) {
										System.out.println("1- "+CompareDoubleUnits(dobiv.getValue_result(), dobivValueFromDestruct_Result));
										System.out.println("2- "+dobiv.getNuclide().getSymbol_nuclide()+" "+(dataTable[i][nuclide_Colum].toString()));	
										System.out.println("3- "+dobiv.getNuclide().getSymbol_nuclide().replaceAll("[0-9]", "")+
												" "+dataTable[i][nuclide_Colum].toString().replaceAll("[0-9]", ""));
										if(dobiv.getNuclide().getSymbol_nuclide().replaceAll("[0-9]", "").replaceAll("/", "").equals(dataTable[i][nuclide_Colum].toString().replaceAll("Cm", "Am").replaceAll("[0-9]", "").replaceAll("/", ""))){
											
											System.out.println(dobiv.getNuclide().getSymbol_nuclide()+" - "+(dataTable[i][nuclide_Colum].toString()));	
										if (!CompareDoubleUnits(dobiv.getValue_result(), dobivValueFromDestruct_Result)) {
											errDobiv = "несъвпадащи стойности за добиви" + "\n ";
										}
										}else{
											errDobiv = "несъвпадащи нуклиди за добиви" + "\n ";
										}
										
									}
								}
							}
						}

					} catch (NumberFormatException e) {
						ResourceLoader.appendToFile(e);
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Липсва добив във файла", "Грешни данни",
								JOptionPane.ERROR_MESSAGE);
					}

				}

			}

			List<String> deDupStringList = new ArrayList<>(new HashSet<>(listCodeNuclide));

			if (deDupStringList.size() != listCodeNuclide.size()) {
				errDuplic = "повтарящи се нуклиди" + "\n";
			}
		} else {
			errDuplic = "невъведени данни" + "\n";
		}

		if (!uncrtError.equals("")) {
			JOptionPane.showMessageDialog(null, uncrtError, "Грешни данни за следните полета:",
					JOptionPane.ERROR_MESSAGE);
		}
		
		return (errTSI + errDateAnaliz + errDuplic + errRazm + errQunt + errDim + inProtokol + errDobiv);
	}

	public static Double reformatDoubleValue(Double value) {
		DecimalFormat df4 = new DecimalFormat("#.######");
		df4.setRoundingMode(RoundingMode.HALF_UP);
		String stt = df4.format(value).replaceAll(",", ".");

		return Double.parseDouble(stt);

	}

	public static Boolean CompareDoubleUnits(Double dobivValue1, Double dobivValue2) {
		System.out.println(dobivValue1 + " " + dobivValue2+"  //////////////////////////");
		int intdobivValue1 = (int) (AddResultViewMetods.reformatDoubleValue(dobivValue1) * 100000);
		int intdobivValue2 = (int) (AddResultViewMetods.reformatDoubleValue(dobivValue2) * 100000);
		int dobiveValuePlus = (int) ((AddResultViewMetods.reformatDoubleValue(dobivValue1 + 0.00005)) * 100000);
		int dobiveValueMinus = (int) ((AddResultViewMetods.reformatDoubleValue(dobivValue1 - 0.00005)) * 100000);

		System.out.println(intdobivValue1 + " " + intdobivValue2 + " " + dobiveValuePlus + " " + dobiveValueMinus);
	
		return (dobiveValuePlus >= intdobivValue2 && dobiveValueMinus <= intdobivValue2);

	}

	public static List<Results> creadResultListForSave(Sample sample, JTextField txtBasicValueResult,
			Choice choiceMetody, Choice choicePokazatel, Choice choiceORHO, Choice choiceOIR, Choice choiceDobiv) {
		List<Results> listResultsForSave = new ArrayList<Results>();
		for (int i = 0; i < OverallVariablesAddResults.getDataTable().length; i++) {
			String s1 = OverallVariablesAddResults.getDataTable()[i][mda_Colum].toString();
			String s2 = OverallVariablesAddResults.getDataTable()[i][actv_value_Colum].toString();
			if ((Double.parseDouble((String) s1) + (Double.parseDouble((String) s2)) > 0)) {
				listResultsForSave.add(creadResultObject(sample, i, txtBasicValueResult, choiceMetody, choicePokazatel,
						choiceORHO, choiceOIR, choiceDobiv));

			}
		}

		return listResultsForSave;
	}

	public static List<Results> creadResultListForDelete(Sample sample) {
		List<Results> listResultsForDelete = new ArrayList<Results>();
		for (int i = 0; i < OverallVariablesAddResults.getDataTable().length; i++) {
			String s1 = OverallVariablesAddResults.getDataTable()[i][mda_Colum].toString();
			String s2 = OverallVariablesAddResults.getDataTable()[i][actv_value_Colum].toString();
			if ((Double.parseDouble((String) s1) + (Double.parseDouble((String) s2)) == 0)) {
				if (OverallVariablesAddResults.getDataTable()[i][rsult_Id_Colum] != null) {
					listResultsForDelete.add(ResultsDAO
							.getValueResultsById((int) OverallVariablesAddResults.getDataTable()[i][rsult_Id_Colum]));
				}
			}
		}

		return listResultsForDelete;
	}

	private static Results creadResultObject(Sample sample, int i, JTextField txtBasicValueResult, Choice choiceMetody,
			Choice choicePokazatel, Choice choiceORHO, Choice choiceOIR, Choice choiceDobiv) {
		Results result;
		if (OverallVariablesAddResults.getDataTable()[i][rsult_Id_Colum] == null) {
			result = creadResultsObject(i, new Results(), sample, txtBasicValueResult, choiceMetody, choicePokazatel,
					choiceORHO, choiceOIR, choiceDobiv);
		} else {
			result = creadResultsObject(i,
					ResultsDAO.getValueResultsById((int) OverallVariablesAddResults.getDataTable()[i][rsult_Id_Colum]),
					sample, txtBasicValueResult, choiceMetody, choicePokazatel, choiceORHO, choiceOIR, choiceDobiv);
		}
		return result;
	}

	private static Results creadResultsObject(int i, Results result, Sample sample, JTextField txtBasicValueResult,
			Choice choiceMetody, Choice choicePokazatel, Choice choiceORHO, Choice choiceOIR, Choice choiceDobiv) {
		Users user_ORHO = null, user_OIR = null;
		result.setBasic_value(txtBasicValueResult.getText());
		if (OverallVariablesAddResults.getDataTable()[i][dateHimObr_Colum] == null) {
			result.setDate_chim_oper("");
		} else {
			result.setDate_chim_oper(OverallVariablesAddResults.getDataTable()[i][dateHimObr_Colum].toString());
		}
		result.setDate_measur(OverallVariablesAddResults.getDataTable()[i][dateAnaliz_Colum].toString());
		result.setDate_redac(RequestViewFunction.DateNaw(false));
		result.setInProtokol((Boolean) OverallVariablesAddResults.getDataTable()[i][in_Prot_Colum]);
		result.setMda(Double.parseDouble(OverallVariablesAddResults.getDataTable()[i][mda_Colum].toString()));
		result.setQuantity(Double.parseDouble(OverallVariablesAddResults.getDataTable()[i][qunt_Colum].toString()));
		result.setSigma(Integer.parseUnsignedInt(OverallVariablesAddResults.getDataTable()[i][sigma_Colum].toString()));
		result.setUncertainty(Double.parseDouble(OverallVariablesAddResults.getDataTable()[i][uncrt_Colum].toString()));
		result.setValue_result(
				Double.parseDouble(OverallVariablesAddResults.getDataTable()[i][actv_value_Colum].toString()));
		result.setTsi(TSI_DAO.getValueTSIByName(OverallVariablesAddResults.getDataTable()[i][TSI_Colum].toString()));
		if ((OverallVariablesAddResults.getDataTable()[i][dimen_Colum].equals(""))) {
			result.setDimension(null);
		} else {
			result.setDimension(DimensionDAO
					.getValueDimensionByName(OverallVariablesAddResults.getDataTable()[i][dimen_Colum].toString()));
		}
		result.setMetody((Metody) MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));
		Nuclide nuclide = NuclideDAO
				.getValueNuclideBySymbol(OverallVariablesAddResults.getDataTable()[i][nuclide_Colum].toString());
		result.setNuclide(nuclide);
		result.setPokazatel(
				List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(choicePokazatel.getSelectedItem()));
		result.setRazmernosti(RazmernostiDAO
				.getValueRazmernostiByName(OverallVariablesAddResults.getDataTable()[i][razm_Colum].toString()));
		result.setSample(sample);
		result.setRequest(sample.getRequest());
		String choiceUser_ORHO = choiceORHO.getSelectedItem();
		for (Users user : OverallVariablesAddResults.getList_Users()) {
			if (choiceUser_ORHO.substring(0, choiceUser_ORHO.indexOf(" ")).equals(user.getName_users())
					&& choiceUser_ORHO.substring(choiceUser_ORHO.indexOf(" ") + 1).equals(user.getFamily_users())) {
				result.setUser_chim_oper(user);
				user_ORHO = user;
			}
		}
		String choiceUser_OIR = choiceOIR.getSelectedItem();
		for (Users user : OverallVariablesAddResults.getList_Users()) {
			if (choiceUser_OIR.substring(0, choiceUser_OIR.indexOf(" ")).equals(user.getName_users())
					&& choiceUser_OIR.substring(choiceUser_OIR.indexOf(" ") + 1).equals(user.getFamily_users())) {
				result.setUser_measur(user);
				user_OIR = user;
			}
		}
		result.setUser_redac(OverallVariablesAddResults.getUser_Redac());
		result.setZabelejki(null);

		if (!choiceDobiv.getSelectedItem().toString().isEmpty()) {
			List<Dobiv> listDobiv = DobivDAO.getList_DobivByCode_Standart(choiceDobiv.getSelectedItem().toString());

			if (listDobiv.size() > 0) {
				// for (Dobiv dobiv : listDobiv) {
				// if
				// (dobiv.getNuclide().getSymbol_nuclide().replaceAll("[0123456789]","").equals(nuclide.getSymbol_nuclide().replaceAll("[0123456789]","")))
				// {
				result.setDobiv(listDobiv.get(0));
				// }
				// }
			} else {
				Dobiv dobiv = OverallVariablesAddDobiv.getListChoisedDobiv().get(0);
				dobiv.setDate_chim_oper(OverallVariablesAddResults.getDataTable()[i][dateHimObr_Colum].toString());
				dobiv.setUser_chim_oper(user_ORHO);
				dobiv.setUser_measur(user_OIR);
				dobiv.setBasic_value(txtBasicValueResult.getText());

				List<String> list = new ArrayList<String>();
				for (Dobiv dobivL : DobivDAO.getListAllDobiv()) {
					list.add(dobivL.getCode_Standart());
				}
				OverallVariablesAddDobiv.setListStandartCodeAllDobiv(list);
				int idDobivWithExixtStandartCodeInBase = btnPaneAddDobivSection
						.ExixtStandartCodeInBase(OverallVariablesAddDobiv.getListStandartCodeAllDobiv(), dobiv);
				if (idDobivWithExixtStandartCodeInBase != 0) {
					dobiv.setId_dobiv(idDobivWithExixtStandartCodeInBase);
					DobivDAO.updateDobiv(dobiv);
				} else {
					DobivDAO.setValueDobiv(dobiv);
				}
				
				result.setDobiv(dobiv);
			}
		}

		return result;
	}

	public static void setWaitCursor(JPanel frame) {
		if (frame != null) {
			RootPaneContainer root = (RootPaneContainer) frame.getRootPane().getTopLevelAncestor();
			root.getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			root.getGlassPane().setVisible(true);
		}
	}

	public static void setDefaultCursor(JPanel frame) {
		if (frame != null) {
			RootPaneContainer root = (RootPaneContainer) frame.getRootPane().getTopLevelAncestor();
			root.getGlassPane().setCursor(Cursor.getDefaultCursor());
			root.getGlassPane().setVisible(false);
		}
	}

	public static int selestTypeReadFileByChoiceMetod(Metody selectedMetod, String pokazatel) {
		if (selectedMetod.getCode_metody().indexOf("-10") > 1) {
			return 10;
		}
		if (selectedMetod.getCode_metody().indexOf("-01") > 1) {
			if (pokazatel.indexOf("90Sr") > 0) {

				return 16;
			}
			return 1;
		}
		if (selectedMetod.getCode_metody().indexOf("-16") > 1 || selectedMetod.getCode_metody().indexOf("-15") > 1
				|| selectedMetod.getCode_metody().indexOf("-07") > 1
				|| selectedMetod.getCode_metody().indexOf("-14") > 1) {
			return 16;
		}
		if (selectedMetod.getCode_metody().indexOf("-03") > 1) {
			return 3;
		}
		return 0;
	}

}
