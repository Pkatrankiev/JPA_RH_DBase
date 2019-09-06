package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import WindowView.AddResultsViewWithTable;
import WindowView.CheckResultClass;
import WindowView.CheckViewValueDialogFrame;
import WindowView.DatePicker;
import WindowView.ReadGamaFile;
import WindowView.RequestViewFunction;


public class AddresultViewMwetods {

	private static String[] tableHeader = { "Íóêëèä", "Â ïðîòîêîë", "Àêòèâíîñò", "Íåîïðåäåëåíîñò", "ÌÄÀ", "Ðàçìåðíîñò",
			"Ñèãìà", "Êîëè÷åñòâî", "Ìÿðêà", "Ò Ñ È", "ÄàòàÕèìÎáð", "ÄàòàÀíàëèç", "Ïðîâåðêà", "Id_Result" };

	@SuppressWarnings("rawtypes")
	private static Class[] types = { String.class, Boolean.class, String.class, String.class, String.class,
			String.class, String.class, String.class, String.class, String.class, String.class, String.class,
			String.class, Integer.class };

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
		ÎverallVariables.setList_Users(UsersDAO.getInListAllValueUsers());
		ÎverallVariables.setList_UsersNameFamilyOIR(
				UsersDAO.getListStringAllName_FamilyUsersByPost(PostDAO.getValuePostByName("ÎÈÐ")));
		ÎverallVariables.setList_UsersNameFamilyORHO(
				UsersDAO.getListStringAllName_FamilyUsersByPost(PostDAO.getValuePostByName("ÎÐÕÎ")));
		ÎverallVariables.setListSample(new ArrayList<Sample>());

		ÎverallVariables.setUser_Redac(user);

		ÎverallVariables.setValues_Razmernosti(RazmernostiDAO.getMasiveStringAllValueRazmernosti());
		ÎverallVariables.setValues_Dimension(DimensionDAO.getMasiveStringAllValueDimension());
		ÎverallVariables.setMasiveTSI(TSI_DAO.getMasiveStringAllValueTSI());
	}

	static Results[] creadMasiveFromResultsObjects_ChoiseSample(Sample sample, Choice choicePokazatel) {
		List<Results> ListResultsFromSample = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
		List<Results> choiceResults = new ArrayList<Results>();
		List_izpitvan_pokazatel pokazatel = AddresultViewMwetods.getPokazatelObjectFromChoicePokazatel(choicePokazatel);
		for (Results result : ListResultsFromSample) {
			if (result.getPokazatel().getId_pokazatel() == pokazatel.getId_pokazatel()
					&& result.getMetody().getId_metody() == ÎverallVariables.getSelectedMetod().getId_metody()) {
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

	static void startViewtablePanel(JPanel basic_panel, JPanel panel, Results[] masiveResultsForChoiceSample) {
		Object[][] ss = getDataTable(masiveResultsForChoiceSample, ÎverallVariables.getListSimbolBasikNulide());
		createDataTableAndViewTableInPanel(basic_panel, ss);
	}

	static Boolean checkKorektFileName(String fileName, String codeSamample) {
		int choice = 0;
		Boolean fl = true;
		if (fileName.indexOf(codeSamample) < 0) {
			fl = false;
			// display the showOptionDialog
			Object[] options = { "Äà", "Íå" };
			choice = JOptionPane.showOptionDialog(null, "Êîäà  íå ñúâïàäà \nñ èìåòî íà ôàéëà. \nÙå ïðîäúëæèòå ëè?",
					"Ãðåøíè äàííè", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (choice == JOptionPane.YES_OPTION) {
				fl = true;
			}
		}

		return fl;
	}

	static Boolean checkForKoretMetod(List<Destruct_Result> destruct_Result_List, Choice choiceMetody) {
		Boolean fl = false;
		if (!choiceMetody.getSelectedItem().trim().isEmpty()) {
			ÎverallVariables.setSelectedMetod(MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));

			for (Destruct_Result destruct_Result : destruct_Result_List) {
				if (ÎverallVariables.getSelectedMetod().getCode_metody()
						.indexOf(destruct_Result.getMetod().replace("Ì.ËÈ-ÐÕ-", "")) > 0) {
					fl = true;
				}
			}
			if (!fl) {
				JOptionPane.showMessageDialog(null, "Íå ñúâïàäà èçáðàíèÿ ìåòîä è òîçè îò ôàéëà", "Ãðåøíè äàííè",
						JOptionPane.ERROR_MESSAGE);
			}
			return fl;

		} else {
			JOptionPane.showMessageDialog(null, "Íå å èçáðàí ìåòîä", "Ãðåøíè äàííè", JOptionPane.ERROR_MESSAGE);
		}
		return fl;
	}

	static List<Nuclide_to_Pokazatel> getListNuklideToPokazatel(Choice choicePokazatel) {
		List<Nuclide_to_Pokazatel> listNucToPok = Nuclide_to_PokazatelDAO.getListNuclide_to_PokazatelByPokazatel(
				AddresultViewMwetods.getPokazatelObjectFromChoicePokazatel(choicePokazatel));
		return listNucToPok;
	}

	static List<String> getListSimbolBasikNulideFNuclideToPokazatel(List<Nuclide_to_Pokazatel> listNucToPok) {
		List<String> listSimbolBasikNulide = new ArrayList<String>();
		for (Nuclide_to_Pokazatel nuclide_to_Pokazatel : listNucToPok) {
			if (nuclide_to_Pokazatel.getNuclide().getFavorite_nuclide()) {
				listSimbolBasikNulide.add(nuclide_to_Pokazatel.getNuclide().getSymbol_nuclide());
			}

		}
		return listSimbolBasikNulide;
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

	static Object[][] CreatedataTableFromGeany2kFile(Choice choicePokazatel) {
		List<Nuclide_to_Pokazatel> listNucToPok = AddresultViewMwetods.getListNuklideToPokazatel(choicePokazatel);
		List<String> listSimbolBasicNuclide = AddresultViewMwetods
				.getListSimbolBasikNulideFNuclideToPokazatel(listNucToPok);
		ÎverallVariables.setMasuveSimbolNuclide(AddresultViewMwetods.getMasiveSimbolNuclideToPokazatel(listNucToPok));
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

	static Object[][] CreatedataTableFromExcelFile(Choice choicePokazatel) {
		List<Nuclide_to_Pokazatel> listNucToPok = getListNuklideToPokazatel(choicePokazatel);
		List<String> listSimbolBasicNuclide = getListSimbolBasikNulideFNuclideToPokazatel(listNucToPok);
		// masuveSimbolNuclide =
		// getMasiveSimbolNuclideToPokazatel(listNucToPok);
		Results[] masiveResultsFromFile = ReadExcelFile
				.getMasivResultsFromExcelFile(ÎverallVariables.getDestruct_Result_List(), listSimbolBasicNuclide);
		System.out.println(masiveResultsFromFile.length + " ----------------------------------------");
		Object[][] tableResult = new Object[masiveResultsFromFile.length][tbl_Colum];

		for (int i = 0; i < masiveResultsFromFile.length; i++) {
			tableResult[i] = rowWithValueResultsFromFile(masiveResultsFromFile[i]);

		}
		return tableResult;
	}

	static void AddNewRowIn_dataTable(Choice choicePokazatel) {
		List<Nuclide_to_Pokazatel> listNucToPok = AddresultViewMwetods.getListNuklideToPokazatel(choicePokazatel);
		ÎverallVariables.setMasive_NuclideToPokazatel(getListSimbolNuclideToPokazatel(listNucToPok));
		int countDataTable = ÎverallVariables.getDataTable().length;
		Object[][] newTable = new Object[countDataTable + 1][tbl_Colum];
		for (int i = 0; i < countDataTable; i++) {
			newTable[i] = ÎverallVariables.getDataTable()[i];
		}
		newTable[countDataTable] = rowWithoutValueResults(ÎverallVariables.getMasive_NuclideToPokazatel()[0]);
		ÎverallVariables.setDataTable(new Object[newTable.length][tbl_Colum]);
		for (int i = 0; i < newTable.length; i++) {
			ÎverallVariables.getDataTable()[i] = newTable[i];
		}
	}

	private static Object[] rowWithoutValueResults(String BasicNuclide) {
		Object[] rowFromTableResult = new Object[tbl_Colum];
		rowFromTableResult[nuclide_Colum] = BasicNuclide;
		rowFromTableResult[actv_value_Colum] = 0.0;
		rowFromTableResult[uncrt_Colum] = 0.0;
		rowFromTableResult[mda_Colum] = 0.0;
		rowFromTableResult[razm_Colum] = ÎverallVariables.getChoiseRequest().getRazmernosti().getName_razmernosti();
		rowFromTableResult[sigma_Colum] = 2;
		rowFromTableResult[qunt_Colum] = 0.0;
		rowFromTableResult[dimen_Colum] = "";
		rowFromTableResult[dimen_Colum] = ÎverallVariables.getValues_Dimension()[0];
		rowFromTableResult[TSI_Colum] = "";
		rowFromTableResult[dateHimObr_Colum] = "";
		rowFromTableResult[dateAnaliz_Colum] = "";
		rowFromTableResult[in_Prot_Colum] = false;
		rowFromTableResult[check_Colum] = "Ïðîâåðè";
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
		rowFromTableResult[razm_Colum] = result.getRtazmernosti().getName_razmernosti();
		rowFromTableResult[sigma_Colum] = result.getSigma();
		rowFromTableResult[qunt_Colum] = result.getQuantity();
		rowFromTableResult[dimen_Colum] = result.getDimension().getName_dimension();
		rowFromTableResult[TSI_Colum] = result.getTsi().getName();
		rowFromTableResult[dateHimObr_Colum] = result.getDate_chim_oper();
		rowFromTableResult[dateAnaliz_Colum] = result.getDate_measur();
		rowFromTableResult[in_Prot_Colum] = false;
		rowFromTableResult[check_Colum] = "Ïðîâåðè";
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
			rowFromTableResult[razm_Colum] = results.getRtazmernosti().getName_razmernosti();
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
			rowFromTableResult[check_Colum] = "Ïðîâåðè";
			rowFromTableResult[rsult_Id_Colum] = results.getId_results();
		} catch (NullPointerException e) {
			JOptionPane.showInputDialog("Ãðåøíè äàííè çà ðåçóëòàò:" + results.getId_results(),
					JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showInputDialog("Ãðåøíè äàííè çà ðåçóëòàò:", JOptionPane.ERROR_MESSAGE);
		}
		return rowFromTableResult;

	}

	static void createDataTableAndViewTableInPanel(JPanel basic_panel, Object[][] ss) {
		Boolean isNewRow = false;
		ÎverallVariables.setDataTable(new Object[ss.length][tbl_Colum]);
		ÎverallVariables.setDataTable(ss);
		isNewRow = false;
		AddResultsViewWithTable.ViewTableInPanel(basic_panel, isNewRow);

	}

	
	public static void TableRendererMetod(Boolean isNewRow, JTable tabResults,int countRowTabResults,JButton btnAddRow, JTableHeader header, 
			Choice choiceSmplCode) {
		tabResults = CreateTableResults(isNewRow,  btnAddRow,  header, 
				 choiceSmplCode);
		countRowTabResults = ÎverallVariables.getDataTable().length;
		tabResults.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 7888392104063188806L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				String s1 = table.getValueAt(row, actv_value_Colum).toString();
				String s2 = table.getValueAt(row, mda_Colum).toString();

				if ((Double.parseDouble((String) s1) + (Double.parseDouble((String) s2))) == 0) {
					// setBackground(Color.BLACK);
					setForeground(Color.LIGHT_GRAY);
				} else {
					// setBackground(table.getBackground());
					setForeground(table.getForeground());
				}
				return this;
			}
		});
	}

	public static JTable CreateTableResults(Boolean isNewRow, JButton btnAddRow, JTableHeader header, 
			Choice choiceSmplCode) {

		if (1 < ÎverallVariables.getListNucToPok().size()) {
			btnAddRow.setVisible(true);
		} else {
			btnAddRow.setVisible(false);
		}

		String[] columnNames = getTabHeader();
		@SuppressWarnings("rawtypes")
		Class[] types = getTypes();

		JTable table = new JTable();// new DefaultTableModel(rowData,
									// columnNames));

		header = table.getTableHeader();
		header.setReorderingAllowed(false);
		header.addMouseListener(new TableHeaderMouseListener(table));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());

				if (SwingUtilities.isRightMouseButton(e)) {
					if (col == dateAnaliz_Colum || col == dateHimObr_Colum) {
						String date_choice = getDateFromDatePicker(table, col);
						table.setValueAt(date_choice, row, col);
					}
				}

				if (col == in_Prot_Colum) {
					table.changeSelection(row, 0, false, false);
					DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
					cellRenderer.getBorder();
				}

				if (table.getSelectedColumn() == check_Colum) {
					double actv_value = Double
							.parseDouble((table.getValueAt(table.getSelectedRow(), actv_value_Colum)).toString());
					double mda = Double.parseDouble((table.getValueAt(table.getSelectedRow(), mda_Colum)).toString());
					Nuclide nuclide = NuclideDAO.getValueNuclideBySymbol(
							table.getValueAt(table.getSelectedRow(), nuclide_Colum).toString());
					Sample samp = SampleCodeSection.getSampleObjectFromChoiceSampleCode(choiceSmplCode);
					checkValueFrame(nuclide, samp, actv_value, mda);
				}

			}

		});

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(ÎverallVariables.getDataTable(), columnNames) {

					private static final long serialVersionUID = 1L;
					@SuppressWarnings("rawtypes")
					private Class[] classTypes = types;

					@SuppressWarnings({})
					@Override
					public Class<?> getColumnClass(int columnIndex) {
						return this.classTypes[columnIndex];
					}

					public Object getValueAt(int row, int col) {
						return ÎverallVariables.getDataTable()[row][col];
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

						if (!ÎverallVariables.getDataTable()[row][col].equals(value)) {
							if (col == dateHimObr_Colum || col == dateAnaliz_Colum) {
								if (!DatePicker.incorrectDate((String) value, false)) {
									ÎverallVariables.getDataTable()[row][col] = value;
									fireTableCellUpdated(row, col);
								}
							}

							if (col == actv_value_Colum || col == uncrt_Colum || col == mda_Colum || col == sigma_Colum
									|| col == qunt_Colum) {
								try {
									Double.parseDouble((String) value);
									ÎverallVariables.getDataTable()[row][col] = value;
									fireTableCellUpdated(row, col);
								} catch (NumberFormatException e) {
								}
							}

							if (col == nuclide_Colum || col == razm_Colum || col == dimen_Colum || col == in_Prot_Colum
									|| col == TSI_Colum) {
								ÎverallVariables.getDataTable()[row][col] = value;
								fireTableCellUpdated(row, col);
							}
						}

					}

					public int getColumnCount() {
						return columnNames.length;
					}

					public int getRowCount() {

						return ÎverallVariables.getDataTable().length;
					}

				};

				table.setModel(dtm);

				setUp_Nuclide(table.getColumnModel().getColumn(nuclide_Colum), isNewRow);

				setUp_Razmernosti(table.getColumnModel().getColumn(razm_Colum));
				setUp_Dimension(table.getColumnModel().getColumn(dimen_Colum));
				setUp_TSI(table.getColumnModel().getColumn(TSI_Colum));

				table.getColumnModel().getColumn(rsult_Id_Colum).setWidth(0);
				table.getColumnModel().getColumn(rsult_Id_Colum).setMinWidth(0);
				table.getColumnModel().getColumn(rsult_Id_Colum).setMaxWidth(0);
				table.getColumnModel().getColumn(rsult_Id_Colum).setPreferredWidth(0);

			}

		});
		return table;
	}

	public static void checkValueFrame(Nuclide nuclide, Sample samp, Double actv_value, Double mda) {
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

		// TranscluentWindow round = new TranscluentWindow();
		JFrame f = new JFrame();
		new CheckViewValueDialogFrame(f, listCheckResultObject, actv_value, mda);
		// final Thread thread = new Thread(new Runnable() {
		// @Override
		// public void run() {
		//
		//
		//
		//
		// }
		// });
		// thread.start();
	}

	private static String getDateFromDatePicker(JTable table, int col) {
		String date = "";
		if (col == dateAnaliz_Colum) {
			date = table.getValueAt(table.getSelectedRow(), dateAnaliz_Colum).toString();
		}
		if (col == dateHimObr_Colum) {
			date = table.getValueAt(table.getSelectedRow(), dateHimObr_Colum).toString();
		}
		final JFrame f = new JFrame();
		DatePicker dPicer = new DatePicker(f, false, date);
		String date_choice = dPicer.setPickedDate(false);
		return date_choice;
	}

	public static void setUp_Nuclide(TableColumn nuclide_Column, Boolean isNewRow) {
		JComboBox<?> comboBox = new JComboBox<Object>(ÎverallVariables.getMasuveSimbolNuclide());
		if (isNewRow) {
			comboBox = new JComboBox<Object>(ÎverallVariables.getMasive_NuclideToPokazatel());
		}
		nuclide_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Íàòèñíè çà èçáîð");
		nuclide_Column.setCellRenderer(renderer);
	}

	public static void setUp_Razmernosti(TableColumn Razmernosti_Column) {
		JComboBox<?> comboBox1 = new JComboBox<Object>(ÎverallVariables.getValues_Razmernosti());
		Razmernosti_Column.setCellEditor(new DefaultCellEditor(comboBox1));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Íàòèñíè çà èçáîð");
		Razmernosti_Column.setCellRenderer(renderer);
	}

	public static void setUp_Dimension(TableColumn Dimension_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(ÎverallVariables.getValues_Dimension());
		Dimension_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Íàòèñíè çà èçáîð");
		Dimension_Column.setCellRenderer(renderer);
	}

	private static void setUp_TSI(TableColumn tSI_column) {
		JComboBox<?> comboBox = new JComboBox<Object>(ÎverallVariables.getMasiveTSI());
		tSI_column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Íàòèñíè çà èçáîð");
		tSI_column.setCellRenderer(renderer);

	}

	private static String[] getTabHeader() {
		return tableHeader;
	}

	@SuppressWarnings("rawtypes")
	private static Class[] getTypes() {
		return types;
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

	static String strCurrentDataInDataTable(Object[][] dataTable) {
		String errDuplic = "";
		String errTSI = "";
		String uncrtError = "";
		String errDateAnaliz = "";
		String errRazm = "";
		String errQunt = "";
		String errDim = "";
		String inProtokol = "ðåçóëòàòè â ïðîòîêîë" + "\n";
		List<String> listCodeNuclide = new ArrayList<String>();
		if (dataTable != null) {
			for (int i = 0; i < dataTable.length; i++) {
				Double mda = Double.parseDouble((String) dataTable[i][mda_Colum].toString());
				Double actv = Double.parseDouble((String) dataTable[i][actv_value_Colum].toString());
				Double uncrt = Double.parseDouble((String) dataTable[i][uncrt_Colum].toString());
				if (mda + actv != 0) {
					listCodeNuclide.add(dataTable[i][nuclide_Colum].toString());
					if (dataTable[i][razm_Colum].toString().trim().isEmpty()) {
						errRazm = "ðàçìåðíîñò " + "\n";
					}
					String razm = dataTable[i][razm_Colum].toString();
					String qunt = dataTable[i][qunt_Colum].toString();
					if (!razm.trim().isEmpty() && !razm.replace("Bq", "").isEmpty()) {
						if (qunt.trim().isEmpty() || Double.parseDouble(qunt) <= 0) {
							errQunt = "êîëè÷åñòâî " + "\n";
						}

						if (dataTable[i][dimen_Colum].toString().trim().isEmpty()) {
							errDim = "ìÿðêà " + "\n";
						}

					}

					if (dataTable[i][TSI_Colum].toString().trim().isEmpty()) {
						errTSI = "Ò Ñ È " + "\n";
					}

					if (DatePicker.incorrectDate(dataTable[i][dateAnaliz_Colum].toString().trim(), false)) {
						errDateAnaliz = "äàòà íà àíàëèç" + "\n";
					}

				}

				if (dataTable[i][in_Prot_Colum].toString() == "true") {
					String nuclede_uncrtError = "";
					if (actv != 0) {
						if (uncrt == 0) {
							nuclede_uncrtError = "íóëåâà íåîïðåäåëåíîñò";
						} else if ((uncrt / actv) * 100 > 52) {
							nuclede_uncrtError = "íåîïðåäåëåíîñò >52%";
						}
					}
					if (!nuclede_uncrtError.equals("")) {

						uncrtError += dataTable[i][nuclide_Colum].toString() + " å  ñ " + nuclede_uncrtError + "\n";
					}
					inProtokol = "";
				}

			}

			List<String> deDupStringList = new ArrayList<>(new HashSet<>(listCodeNuclide));

			if (deDupStringList.size() != listCodeNuclide.size()) {
				errDuplic = "ïîâòàðÿùè ñå íóêëèäè" + "\n";
			}
		} else {
			errDuplic = "íåâúâåäåíè äàííè" + "\n";
		}

		if (!uncrtError.equals("")) {
			JOptionPane.showMessageDialog(null, uncrtError, "Ãðåøíè äàííè çà ñëåäíèòå ïîëåòà:",
					JOptionPane.ERROR_MESSAGE);
		}
		return (errTSI + errDateAnaliz + errDuplic + errRazm + errQunt + errDim + inProtokol);
	}

	
	
	
	
	public static List<Results> creadResultListForSave(Sample sample, JTextField txtBasicValueResult,
			Choice choiceMetody, Choice choicePokazatel, Choice choiceORHO, Choice choiceOIR, Choice choiceDobiv) {
		List<Results> listResultsForSave = new ArrayList<Results>();
		for (int i = 0; i < ÎverallVariables.getDataTable().length; i++) {
			String s1 = ÎverallVariables.getDataTable()[i][mda_Colum].toString();
			String s2 = ÎverallVariables.getDataTable()[i][actv_value_Colum].toString();
			if ((Double.parseDouble((String) s1) + (Double.parseDouble((String) s2)) > 0)) {
				listResultsForSave.add(creadResultObject(sample, i,  txtBasicValueResult,
						  choiceMetody,   choicePokazatel,   choiceORHO,  choiceOIR,   choiceDobiv));

			}
		}

		return listResultsForSave;
	}

	public static List<Results> creadResultListForDelete(Sample sample) {
		List<Results> listResultsForDelete = new ArrayList<Results>();
		for (int i = 0; i < ÎverallVariables.getDataTable().length; i++) {
			String s1 = ÎverallVariables.getDataTable()[i][mda_Colum].toString();
			String s2 = ÎverallVariables.getDataTable()[i][actv_value_Colum].toString();
			if ((Double.parseDouble((String) s1) + (Double.parseDouble((String) s2)) == 0)) {
				if (ÎverallVariables.getDataTable()[i][rsult_Id_Colum] != null) {
					listResultsForDelete.add(ResultsDAO.getValueResultsById((int) ÎverallVariables.getDataTable()[i][rsult_Id_Colum]));
				}
			}
		}

		return listResultsForDelete;
	}
	
	private static Results creadResultObject(Sample sample, int i, JTextField txtBasicValueResult,
			Choice choiceMetody, Choice choicePokazatel, Choice choiceORHO, Choice choiceOIR, Choice choiceDobiv) {
		Results result;
		if (ÎverallVariables.getDataTable()[i][rsult_Id_Colum] == null) {
			result = creadResultsObject(i, new Results(), sample,  txtBasicValueResult,
					choiceMetody,  choicePokazatel, choiceORHO,  choiceOIR, choiceDobiv);
		} else {
			result = creadResultsObject(i, ResultsDAO.getValueResultsById((int) ÎverallVariables.getDataTable()[i][rsult_Id_Colum]), sample, txtBasicValueResult,
					choiceMetody,choicePokazatel,  choiceORHO,  choiceOIR, choiceDobiv);
		}
		return result;
	}

	private static Results creadResultsObject(int i, Results result, Sample sample, JTextField txtBasicValueResult,
			Choice choiceMetody, Choice choicePokazatel, Choice choiceORHO, Choice choiceOIR, Choice choiceDobiv) {

		result.setBasic_value(txtBasicValueResult.getText());
		if (ÎverallVariables.getDataTable()[i][dateHimObr_Colum] == null) {
			result.setDate_chim_oper("");
		} else {
			result.setDate_chim_oper(ÎverallVariables.getDataTable()[i][dateHimObr_Colum].toString());
		}
		result.setDate_measur(ÎverallVariables.getDataTable()[i][dateAnaliz_Colum].toString());
		result.setDate_redac(RequestViewFunction.DateNaw(false));
		result.setInProtokol((Boolean) ÎverallVariables.getDataTable()[i][in_Prot_Colum]);
		result.setMda(Double.parseDouble(ÎverallVariables.getDataTable()[i][mda_Colum].toString()));
		result.setQuantity(Double.parseDouble(ÎverallVariables.getDataTable()[i][qunt_Colum].toString()));
		result.setSigma(Integer.parseUnsignedInt(ÎverallVariables.getDataTable()[i][sigma_Colum].toString()));
		result.setUncertainty(Double.parseDouble(ÎverallVariables.getDataTable()[i][uncrt_Colum].toString()));
		result.setValue_result(Double.parseDouble(ÎverallVariables.getDataTable()[i][actv_value_Colum].toString()));
		result.setTsi(TSI_DAO.getValueTSIByName(ÎverallVariables.getDataTable()[i][TSI_Colum].toString()));
		if ((ÎverallVariables.getDataTable()[i][dimen_Colum].equals(""))) {
			result.setDimension(null);
		} else {
			result.setDimension(DimensionDAO.getValueDimensionByName(ÎverallVariables.getDataTable()[i][dimen_Colum].toString()));
		}
		result.setMetody((Metody) MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));
		Nuclide nuclide = NuclideDAO.getValueNuclideBySymbol(ÎverallVariables.getDataTable()[i][nuclide_Colum].toString());
		result.setNuclide(nuclide);
		result.setPokazatel(
				List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(choicePokazatel.getSelectedItem()));
		result.setRtazmernosti(RazmernostiDAO.getValueRazmernostiByName(ÎverallVariables.getDataTable()[i][razm_Colum].toString()));
		result.setSample(sample);
		String choiceUser = choiceORHO.getSelectedItem();
		for (Users user : ÎverallVariables.getList_Users()) {
			if (choiceUser.substring(0, choiceUser.indexOf(" ")).equals(user.getName_users())
					&& choiceUser.substring(choiceUser.indexOf(" ") + 1).equals(user.getFamily_users())) {
				result.setUser_chim_oper(user);
			}
		}
		choiceUser = choiceOIR.getSelectedItem();
		for (Users user : ÎverallVariables.getList_Users()) {
			if (choiceUser.substring(0, choiceUser.indexOf(" ")).equals(user.getName_users())
					&& choiceUser.substring(choiceUser.indexOf(" ") + 1).equals(user.getFamily_users())) {
				result.setUser_measur(user);
			}
		}
		result.setUser_redac(ÎverallVariables.getUser_Redac());
		result.setZabelejki(null);

		if (!choiceDobiv.getSelectedItem().toString().isEmpty()) {
			for (Dobiv dobiv : DobivDAO.getList_DobivByCode_Standart(choiceDobiv.getSelectedItem().toString())) {
				if (dobiv.getNuclide().getSymbol_nuclide().equals(nuclide.getSymbol_nuclide())) {
					result.setDobiv(dobiv);
				}
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

	
	}
