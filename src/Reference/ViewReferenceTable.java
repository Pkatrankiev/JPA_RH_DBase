package Reference;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import CreateWordDocProtocol.FunctionForGenerateWordDocFile;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Period;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import ExcelFilesFunction.CreateExcelFile;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.TranscluentWindow;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

@SuppressWarnings("serial")
public class ViewReferenceTable extends JDialog {

	public ViewReferenceTable(JFrame parent, TranscluentWindow round, String frame_name, Choice choicePokazatel,
			Choice choiceObectNaIzpit, Choice choiceObectNaProbata, Choice choiceGodina, Choice choiceIzpitProd,
			JRadioButton rdbtnMDA, JRadioButton rdbtnMesechni, JRadioButton rdbtnAbsNeopred) {
		super(parent, frame_name, true);

		JTable table = null;

		String obekt_Na_Izpitvane_Na_Probata = choiceObectNaProbata.getSelectedItem();
		String obektNaIzpitRequest = choiceObectNaIzpit.getSelectedItem();
		String izpitvanProdukt = choiceIzpitProd.getSelectedItem();
		String godinaStryng = choiceGodina.getSelectedItem();
		String pokazatelString = choicePokazatel.getSelectedItem();

		Object[][] masiveValueDataTable = GenerateMasiveDataForTable(pokazatelString, obekt_Na_Izpitvane_Na_Probata,
				godinaStryng, obektNaIzpitRequest, izpitvanProdukt, rdbtnMDA.isSelected(), rdbtnMesechni.isSelected());

		boolean NoReport = false;
		int countRow = masiveValueDataTable.length;
		int widshRow = 20;
		String reportString = "";
		String separatorInColumnName = "_";

		if (countRow < 2) {
			reportString = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_ReportString");
			NoReport = true;
		} else {
			String[][] masiveDataTable = createMasiveStringDataTable(masiveValueDataTable, rdbtnMDA.isSelected(),
					rdbtnAbsNeopred.isSelected());
			String[] masivecolumnNames = createMasiveStringcolumnNames(masiveValueDataTable, separatorInColumnName);
			table = CreateDefaultTable(masiveDataTable, masivecolumnNames, masiveValueDataTable, separatorInColumnName);

		}

		JPanel top_panel = new JPanel();
		top_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		top_panel.setSize(new Dimension(2, 0));
		getContentPane().add(top_panel, BorderLayout.NORTH);
		GridBagLayout gbl_top_panel = new GridBagLayout();
		gbl_top_panel.columnWidths = new int[] { 121, 323, 63, 66, 60, 74, 0 };
		gbl_top_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_top_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_top_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		top_panel.setLayout(gbl_top_panel);

		JLabel lblIzpitProdukt = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_IzpitvanProdukt"));
		GridBagConstraints gbc_lblIzpitProdukt = new GridBagConstraints();
		gbc_lblIzpitProdukt.anchor = GridBagConstraints.EAST;
		gbc_lblIzpitProdukt.insets = new Insets(0, 0, 5, 5);
		gbc_lblIzpitProdukt.gridx = 0;
		gbc_lblIzpitProdukt.gridy = 0;
		top_panel.add(lblIzpitProdukt, gbc_lblIzpitProdukt);

		JLabel valIzpitProdukt = new JLabel(izpitvanProdukt);
		GridBagConstraints gbc_valIzpitProdukt = new GridBagConstraints();
		gbc_valIzpitProdukt.anchor = GridBagConstraints.WEST;
		gbc_valIzpitProdukt.insets = new Insets(0, 0, 5, 5);
		gbc_valIzpitProdukt.gridx = 1;
		gbc_valIzpitProdukt.gridy = 0;
		top_panel.add(valIzpitProdukt, gbc_valIzpitProdukt);

		JLabel lblPokazatel = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_Pokazatel"));
		GridBagConstraints gbc_lblPokazatel = new GridBagConstraints();
		gbc_lblPokazatel.anchor = GridBagConstraints.EAST;
		gbc_lblPokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPokazatel.gridx = 0;
		gbc_lblPokazatel.gridy = 1;
		top_panel.add(lblPokazatel, gbc_lblPokazatel);

		JLabel valPokazatel = new JLabel(pokazatelString);
		GridBagConstraints gbc_valPokazatel = new GridBagConstraints();
		gbc_valPokazatel.anchor = GridBagConstraints.WEST;
		gbc_valPokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_valPokazatel.gridx = 1;
		gbc_valPokazatel.gridy = 1;
		top_panel.add(valPokazatel, gbc_valPokazatel);

		JLabel lblRazmernost = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelTextRazmernost"));
		GridBagConstraints gbc_lblRazmernost = new GridBagConstraints();
		gbc_lblRazmernost.anchor = GridBagConstraints.EAST;
		gbc_lblRazmernost.insets = new Insets(0, 0, 5, 5);
		gbc_lblRazmernost.gridx = 2;
		gbc_lblRazmernost.gridy = 1;
		top_panel.add(lblRazmernost, gbc_lblRazmernost);

		JLabel valRazmernost = new JLabel();
		GridBagConstraints gbc_valRazmernost = new GridBagConstraints();
		gbc_valRazmernost.anchor = GridBagConstraints.WEST;
		gbc_valRazmernost.insets = new Insets(0, 0, 5, 5);
		gbc_valRazmernost.gridx = 3;
		gbc_valRazmernost.gridy = 1;
		top_panel.add(valRazmernost, gbc_valRazmernost);

		JLabel lblObektNaIzpitRequest = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Reference_LabelText_Obekt_Na_Izpitvane"));
		GridBagConstraints gbc_lblObektNaIzpitRequest = new GridBagConstraints();
		gbc_lblObektNaIzpitRequest.anchor = GridBagConstraints.EAST;
		gbc_lblObektNaIzpitRequest.insets = new Insets(0, 0, 5, 5);
		gbc_lblObektNaIzpitRequest.gridx = 0;
		gbc_lblObektNaIzpitRequest.gridy = 2;
		top_panel.add(lblObektNaIzpitRequest, gbc_lblObektNaIzpitRequest);

		JLabel valObektNaIzpitRequest = new JLabel(obektNaIzpitRequest);
		GridBagConstraints gbc_valObektNaIzpitRequest = new GridBagConstraints();
		gbc_valObektNaIzpitRequest.anchor = GridBagConstraints.WEST;
		gbc_valObektNaIzpitRequest.insets = new Insets(0, 0, 5, 5);
		gbc_valObektNaIzpitRequest.gridx = 1;
		gbc_valObektNaIzpitRequest.gridy = 2;
		top_panel.add(valObektNaIzpitRequest, gbc_valObektNaIzpitRequest);

		JLabel lblObektNaProbata = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Reference_LabelText_Obekt_Na_Izpitvane_Na_Probata"));
		GridBagConstraints gbc_lblObektNaProbata = new GridBagConstraints();
		gbc_lblObektNaProbata.anchor = GridBagConstraints.EAST;
		gbc_lblObektNaProbata.insets = new Insets(0, 0, 5, 5);
		gbc_lblObektNaProbata.gridx = 0;
		gbc_lblObektNaProbata.gridy = 3;
		top_panel.add(lblObektNaProbata, gbc_lblObektNaProbata);

		JLabel valObektNaProbata = new JLabel(obekt_Na_Izpitvane_Na_Probata);
		GridBagConstraints gbc_valObektNaProbata = new GridBagConstraints();
		gbc_valObektNaProbata.anchor = GridBagConstraints.WEST;
		gbc_valObektNaProbata.insets = new Insets(0, 0, 5, 5);
		gbc_valObektNaProbata.gridx = 1;
		gbc_valObektNaProbata.gridy = 3;
		top_panel.add(valObektNaProbata, gbc_valObektNaProbata);

		JLabel lblGodina = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_Godina"));
		GridBagConstraints gbc_lblGodina = new GridBagConstraints();
		gbc_lblGodina.anchor = GridBagConstraints.EAST;
		gbc_lblGodina.insets = new Insets(0, 0, 5, 5);
		gbc_lblGodina.gridx = 0;
		gbc_lblGodina.gridy = 4;
		top_panel.add(lblGodina, gbc_lblGodina);

		JLabel valGodina = new JLabel(godinaStryng);
		GridBagConstraints gbc_valGodina = new GridBagConstraints();
		gbc_valGodina.anchor = GridBagConstraints.WEST;
		gbc_valGodina.insets = new Insets(0, 0, 5, 5);
		gbc_valGodina.gridx = 1;
		gbc_valGodina.gridy = 4;
		top_panel.add(valGodina, gbc_valGodina);

		JLabel lblReport = new JLabel(reportString);
		GridBagConstraints gbc_lblReport = new GridBagConstraints();
		gbc_lblReport.anchor = GridBagConstraints.EAST;
		gbc_lblReport.insets = new Insets(0, 0, 0, 5);
		gbc_lblReport.gridx = 1;
		gbc_lblReport.gridy = 5;
		top_panel.add(lblReport, gbc_lblReport);

		if (!NoReport) {
			JScrollPane scrollPane = new JScrollPane(table);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
		}
		setSize(800, 200 + countRow * widshRow);
		setLocationRelativeTo(null);

		// JLabel ll = new JLabel();
		// scrollPane.add(ll);

		round.StopWindow();

		JPanel panel_Btn = new JPanel();
		panel_Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
		getContentPane().add(panel_Btn, BorderLayout.SOUTH);
		panel_Btn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		if (!NoReport) {
			JButton btnExportButton = new JButton("export");
			panel_Btn.add(btnExportButton);
			btnExportListener(frame_name, btnExportButton, table);
			valRazmernost.setText(getRazmernost(masiveValueDataTable));
		}
		JButton btnCancel = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));
		panel_Btn.add(btnCancel);

		btnCancelListener(btnCancel);

		setVisible(true);
	}

	public static Object[][] GenerateMasiveDataForTable(String stringFromChoicePokazatel,
			String obekt_na_izpitvane_sample, String godina, String Obekt_na_izpitvane_request, String izpitvanProdukt,
			boolean rdbtnMDA_true, boolean rdbtnMesechni_true) {

		List<Sample> listSample = generateListSampleFromDBaseByFiltar(obekt_na_izpitvane_sample, godina,
				Obekt_na_izpitvane_request);

		Izpitvan_produkt izp_prod = Izpitvan_produktDAO.getValueIzpitvan_produktByName(izpitvanProdukt);

		List<Request> listRequestByIzpitvanProdukt = RequestDAO.getListRequestFromColumnByVolume("izpitvan_produkt",
				izp_prod);

		List<Sample> listSampleByIzpitvanProdukt = extractSubListSampleByIzpitvanProdukt(listSample,
				listRequestByIzpitvanProdukt);

		List<Period> listPeriod = PeriodDAO.getInListPeriod_TriMesechni();
		if (rdbtnMesechni_true) {
			listPeriod = PeriodDAO.getInListPeriod_Mesechni();
		}

		List<Sample> listSampleByPeriod = extractSubListSampleByPeriod(listSampleByIzpitvanProdukt, listPeriod);

		List<Results> listResultsByPokazatel = generateListResultsFromListSample(stringFromChoicePokazatel,
				listSampleByPeriod, rdbtnMDA_true);

		List<String> nuclide = generateListNuclide(listResultsByPokazatel);

		List<Referens> listReferens = generateListReferensObject(listPeriod, listSampleByPeriod, nuclide);

		Object[][] listMasiveReferens = generateMasiveReferenseObjekt(nuclide, listReferens);

		return listMasiveReferens;
	}

	private static Object[][] generateMasiveReferenseObjekt(List<String> nuclide, List<Referens> listReferens) {
		int rowCount = nuclide.size();
		int columnCount = 0;
		if (rowCount > 0)
			columnCount = listReferens.size() / nuclide.size();
		Object[][] listMasiveReferens = new Object[nuclide.size() + 1][columnCount + 1];
		listMasiveReferens[0][0] = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Reference_Table_Column_Nuclide");
		int column = 1;
		int row = 1;
		int countReferens = 1;
		for (Referens referens : listReferens) {
			if (countReferens <= rowCount) {
				listMasiveReferens[column][row - 1] = referens.getNuclide().toString();
				listMasiveReferens[0][row] = referens.getPeriod().getValue();
				listMasiveReferens[column][row] = "-".toString();
				if (referens.getResult() != null) {
					listMasiveReferens[column][row] = referens.getResult();
				}

			} else {
				listMasiveReferens[0][row] = referens.getPeriod().getValue();
				listMasiveReferens[column][row] = "-";
				if (referens.getResult() != null) {
					listMasiveReferens[column][row] = referens.getResult();
				}
			}
			column++;
			countReferens++;

			if (column == rowCount + 1) {
				row++;
				column = 1;
			}
		}
		return listMasiveReferens;
	}

	private static List<Referens> generateListReferensObject(List<Period> listPeriod, List<Sample> listSampleByPeriod,
			List<String> nuclide) {
		/**
		 * generirane na spisak s obekti za spravka
		 */
		List<Referens> listReferens = new ArrayList<>();
		boolean flPeriod = false, flNuclide = false;

		for (Period period : listPeriod) {
			flPeriod = false;
			for (Iterator<Sample> it = listSampleByPeriod.iterator(); it.hasNext();) {
				Sample sample = it.next();
				if (sample.getPeriod().getId_period() == period.getId_period()) {
					flPeriod = true;
					List<Results> listResul = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);

					for (String basik_nuclide : nuclide) {
						flNuclide = false;
						for (Iterator<Results> itR = listResul.iterator(); itR.hasNext();) {
							Results result = itR.next();
							if (result.getNuclide().getSymbol_nuclide().equals(basik_nuclide)) {
								flNuclide = true;
								Referens referens = new Referens(basik_nuclide, period, result);
								listReferens.add(referens);
								itR.remove();
							}
						}
						if (!flNuclide) {
							Referens referens = new Referens(basik_nuclide, period, null);
							listReferens.add(referens);
						}

					}
					it.remove();
				}

			}

			if (!flPeriod) {

				for (String basik_nuclide : nuclide) {
					Referens referens = new Referens(basik_nuclide, period, null);

					listReferens.add(referens);

				}
			}
		}

		for (Referens referens : listReferens) {

			if (referens.getResult() != null) {
				System.out.println(referens.getNuclide() + "  " + referens.getPeriod().getValue() + "  "
						+ referens.getResult().getMda().toString());
			} else {
				System.out.println(referens.getNuclide() + "  " + referens.getPeriod().getValue() + " - ");

			}
		}

		return listReferens;
	}

	private static List<Sample> extractSubListSampleByIzpitvanProdukt(List<Sample> listSample,
			List<Request> listRequest) {
		List<Sample> listSampleByIzpitvanProdukt = new ArrayList<>();
		for (Request request : listRequest) {
			for (Sample sample : listSample) {

				if (sample.getRequest().getId_recuest() == request.getId_recuest()) {

					listSampleByIzpitvanProdukt.add(sample);
				}

			}
		}
		return listSampleByIzpitvanProdukt;
	}

	private static List<Sample> extractSubListSampleByPeriod(List<Sample> listSample, List<Period> listPeriod) {
		List<Sample> listSampleByPeriod = new ArrayList<>();
		for (Period period : listPeriod) {
			for (Sample sample : listSample) {
				try {
					if (!sample.getPeriod().equals(null)
							&& sample.getPeriod().getId_period() == period.getId_period()) {
						System.out
								.println(sample.getPeriod().getValue() + "   " + sample.getRequest().getRecuest_code());
						listSampleByPeriod.add(sample);
					}
				} catch (NullPointerException e) {

				}
			}
		}
		return listSampleByPeriod;
	}

	private static List<Results> generateListResultsFromListSample(String pokazatekl_ChoiceString,
			List<Sample> listSampleByPeriod, boolean rdbtnMDA_true) {
		List<Results> results = new ArrayList<>();
		for (Sample sample : listSampleByPeriod) {

			results.addAll(ResultsDAO.getListResultsFromColumnByVolume("sample", sample));
		}

		/**
		 * reducirane na spisaka na Rezultatite po filtar pokazatel
		 */
		List_izpitvan_pokazatel pokazatel = List_izpitvan_pokazatelDAO
				.getValueIzpitvan_pokazatelByName(pokazatekl_ChoiceString);
		List<Results> listResultsByPokazatel = new ArrayList<>();
		boolean flValueExists = false;
		for (Results result : results) {

			if (!rdbtnMDA_true) {
				if (result.getUncertainty() != 0.0) {
					flValueExists = true;
				} else {
					flValueExists = false;
				}
			} else {
				flValueExists = true;
			}

			if (flValueExists && (result.getPokazatel().getId_pokazatel() == pokazatel.getId_pokazatel())) {
				listResultsByPokazatel.add(result);
			}

		}

		if (pokazatel.getId_pokazatel() == 2 && listResultsByPokazatel.size() == 0)
			for (int i = 12; i < 15; i++) {
				for (Results result : results) {
					if (result.getPokazatel().getId_pokazatel() == i) {
						listResultsByPokazatel.add(result);
					}
				}
			}
		return listResultsByPokazatel;
	}

	private static List<Sample> generateListSampleFromDBaseByFiltar(String obekt_na_izpitvane_sample, String godina,
			String Obekt_na_izpitvane_request) {
		List<Sample> listSample = new ArrayList<>();
		for (Sample samp : SampleDAO.getListSampleFrom2ColumnByVolume("obekt_na_izpitvane_sample",
				Obekt_na_izpitvane_sampleDAO.getValueObekt_na_izpitvane_sampleByName(obekt_na_izpitvane_sample),
				"godina_period", Integer.parseInt(godina))) {
			if (samp.getRequest_to_obekt_na_izpitvane_request().getObektNaIzp().getName_obekt_na_izpitvane()
					.equals(Obekt_na_izpitvane_request)) {
				listSample.add(samp);
			}
		}
		return listSample;
	}

	private static List<String> generateListNuclide(List<Results> listResultsByPokazatel) {
		Map<Integer, String> mapNuclide = new HashMap<>();
		String codeNuclide;
		for (Results result : listResultsByPokazatel) {
			codeNuclide = result.getNuclide().getSymbol_nuclide();
			mapNuclide.put(Integer.parseInt(codeNuclide.replaceAll("\\D+", "")), codeNuclide);
		}

		/**
		 * Sortirane po masovo chislo i premahvane na povtariastite se nuklidi
		 * ot spisaka na vsichnki simvoli na nuklidi
		 */
		Map<Integer, String> treeMap = new TreeMap<Integer, String>(mapNuclide);
		List<String> nuclide = new ArrayList<>();
		for (String str : treeMap.values()) {
			nuclide.add(str);
		}
		return nuclide;
	}

	private String[] createMasiveStringcolumnNames(Object[][] masiveValueDataTable, String separatorInColumnName) {
		String[] masive = new String[masiveValueDataTable[0].length];
		for (int j = 0; j < masiveValueDataTable[0].length; j++) {
			String str = ((String) masiveValueDataTable[0][j]);
			if (j == 0) {
				masive[j] = str;
			} else {
				masive[j] = j + separatorInColumnName + str;
			}

		}
		return masive;
	}

	private String[][] createMasiveStringDataTable(Object[][] masiveValueDataTable, boolean selectMDA,
			boolean selectAbsNeopred) {
		
		String[][] listMasiveStringReferens = new String[masiveValueDataTable.length
				- 1][masiveValueDataTable[0].length];
		for (int i = 1; i < masiveValueDataTable.length; i++) {
			for (int j = 0; j < masiveValueDataTable[0].length; j++) {
				if (masiveValueDataTable[i][j].getClass().getName().equals(Results.class.getName())) {
					if (selectMDA) {
						listMasiveStringReferens[i - 1][j]  = FunctionForGenerateWordDocFile
								.formatter(((Results) masiveValueDataTable[i][j]).getMda());
					} else {
						listMasiveStringReferens[i - 1][j] = FunctionForGenerateWordDocFile
								.formatter(((Results) masiveValueDataTable[i][j]).getValue_result());
						if (selectAbsNeopred) {
							listMasiveStringReferens[i - 1][j] = listMasiveStringReferens[i - 1][j] + " ± "
									+ FunctionForGenerateWordDocFile.alignExpon(
											((Results) masiveValueDataTable[i][j]).getValue_result(),
											((Results) masiveValueDataTable[i][j]).getUncertainty());
						} else {
							listMasiveStringReferens[i - 1][j] = listMasiveStringReferens[i - 1][j] + " "
									+ percentFormat(((Results) masiveValueDataTable[i][j]).getValue_result(),
											((Results) masiveValueDataTable[i][j]).getUncertainty()).toString();

						}
					}
				} else {
					listMasiveStringReferens[i - 1][j] = ((String) masiveValueDataTable[i][j]);
				}
			}
		}
		return listMasiveStringReferens;
	}

	private String getRazmernost(Object[][] masiveValueDataTable) {
		String razm = "";
		for (int j = 0; j < masiveValueDataTable[0].length; j++) {

			if (masiveValueDataTable[1][j].getClass().getName().equals(Results.class.getName())) {
				razm = ((Results) masiveValueDataTable[1][j]).getRtazmernosti().getName_razmernosti().toString();

			}
		}
		return razm;
	}

	public static String percentFormat(double value, double uncer) {
		if (value == 0) {
			return "";
		}
		double dd = (uncer / value) * 100;
		DecimalFormat formatter = new DecimalFormat("00.00");
		String fnumber = formatter.format(dd);

		fnumber = fnumber.replace(",", ".");
		fnumber = fnumber + "%";
		return fnumber;
	}

	private void btnCancelListener(JButton btnCancel) {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
	}

	private void btnExportListener(String frame_name, JButton btnExportButton, JTable table) {
		btnExportButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						CreateExcelFile.toExcel(createMasiveTableTypeColumn(table), table, frame_name);
					}
				});
				thread.start();

			}

			private String[] createMasiveTableTypeColumn(JTable table) {
				
				String tableTypeColumn[] = new String[table.getColumnCount()];
				for (int i = 0; i < table.getColumnCount(); i++) {

					tableTypeColumn[i] = "String";

				}

				return tableTypeColumn;
			}
		});
	}

	public static JTable CreateDefaultTable(String[][] masiveDataTable, String[] columnNames,
			Object[][] masiveValueDataTable, String separatorInColumnName) {
		JTable table = new JTable();

		new TableFilterHeader(table, AutoChoices.ENABLED);
		new ReferenceTableMouseListener(table, masiveValueDataTable, separatorInColumnName);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(masiveDataTable, columnNames);
				table.setModel(dtm);
				table.setFillsViewportHeight(true);
			}

		});
		return table;
	}

}
