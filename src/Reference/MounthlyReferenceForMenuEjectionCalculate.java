package Reference;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import Aplication.EjectionDAO;

import Aplication.KoeficientObjectDAO;
import Aplication.NuclideDAO;
import Aplication.PeriodDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;

import CreateWordDocProtocol.GenerateRequestWordDoc;

import DBase_Class.Ejection;
import DBase_Class.KoeficientObject;
import DBase_Class.Nuclide;
import DBase_Class.Period;
import DBase_Class.Results;
import DBase_Class.Sample;
import GlobalVariable.GlobalPathForDocFile;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import GlobalVariable.ResourceLoader;
import InfoPanelInMainWindow.CreateListLeftPanelStartWindowClass;
import WindowView.TranscluentWindow;
import javax.swing.BoxLayout;
import javax.swing.Icon;

public class MounthlyReferenceForMenuEjectionCalculate extends JDialog {

	private static final long serialVersionUID = 7534173139838953837L;

	private JTextField txtFieldGodina;
	static String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	final String[] strPeriod = generatePeriodMasive();
	static String mesec;
	private static Object[][] valueDataTable;
	private static String[] nameColumn;
	static JPanel top_panel;
	JPanel tablePanel = new JPanel();

	int sizeV = 160;
	int sizeH = 390;
	int newRowWith = 15;
	int countRow = 0;
	int headerWith = 80;
	int tableLeth = 600;
	static int[] columnExcellWith = { 66, 167, 167, 59, 80 };

	public MounthlyReferenceForMenuEjectionCalculate(JFrame parent, String frame_name) {
		super(parent, frame_name, true);

		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));
		getContentPane().add(tablePanel, BorderLayout.CENTER);
		setSize(sizeH, sizeV);
		setLocationRelativeTo(null);

		top_panel = new JPanel();
		top_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		top_panel.setSize(new Dimension(2, 0));
		getContentPane().add(top_panel, BorderLayout.NORTH);
		GridBagLayout gbl_top_panel = new GridBagLayout();
		LineBorder.createBlackLineBorder();

		gbl_top_panel.columnWidths = new int[] { 24, 91, 64, 99, 80, 40, 10 };
		gbl_top_panel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_top_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_top_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		top_panel.setLayout(gbl_top_panel);

		JLabel lblGodina = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_GodinaLabel"));
		GridBagConstraints gbc_lblGodina = new GridBagConstraints();
		gbc_lblGodina.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGodina.insets = new Insets(0, 0, 5, 5);
		gbc_lblGodina.gridx = 1;
		gbc_lblGodina.gridy = 0;
		top_panel.add(lblGodina, gbc_lblGodina);

		JLabel lblMesec = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_MesecLabel"));
		GridBagConstraints gbc_lblMesec = new GridBagConstraints();
		gbc_lblMesec.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMesec.insets = new Insets(0, 0, 5, 5);
		gbc_lblMesec.gridx = 2;
		gbc_lblMesec.gridy = 0;
		top_panel.add(lblMesec, gbc_lblMesec);

		txtFieldGodina = new JTextField(year);
		txtFieldGodina.setPreferredSize(new Dimension(16, 20));
		txtFieldGodina.setMinimumSize(new Dimension(16, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.anchor = GridBagConstraints.SOUTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		top_panel.add(txtFieldGodina, gbc_textField);
		txtFieldGodina.setColumns(5);
		txtFieldGodina.setText(year);

		JComboBox<?> comboBox = new JComboBox<Object>(strPeriod);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.anchor = GridBagConstraints.SOUTH;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		top_panel.add(comboBox, gbc_comboBox);

		JLabel lblError = new JLabel();
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.anchor = GridBagConstraints.WEST;
		gbc_lblError.gridwidth = 4;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 2;
		top_panel.add(lblError, gbc_lblError);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.GRAY);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 5;
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 3;
		top_panel.add(separator, gbc_separator);

		JButton btnReference = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceEjectionVolums_Btn_Reference"));
		GridBagConstraints gbc_btnReference = new GridBagConstraints();
		gbc_btnReference.gridwidth = 2;
		gbc_btnReference.anchor = GridBagConstraints.SOUTHWEST;
		gbc_btnReference.insets = new Insets(0, 0, 5, 5);
		gbc_btnReference.gridx = 3;
		gbc_btnReference.gridy = 1;
		top_panel.add(btnReference, gbc_btnReference);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton exportButton = new JButton("export");
		exportButton.setEnabled(false);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!comboBox.getSelectedItem().toString().equals(mesec)) {
					exportButton.setEnabled(false);
				} else {
					exportButton.setEnabled(true);
				}
			}

		});

		buttonPane.add(exportButton);
		getRootPane().setDefaultButton(exportButton);
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {

						toExcel();
					}

				});
				thread.start();
			}

		});

		JButton cancelButton = new JButton(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}

		});

		txtFieldGodina.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				MounthlyReferenceForCNRDWater.enterGodina(txtFieldGodina, lblError, btnReference, 2022);

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		btnReference.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TranscluentWindow round = new TranscluentWindow();

				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						mesec = (String) comboBox.getSelectedItem();
						createAndViewTable(lblError, exportButton);

						round.StopWindow();
					}
				});
				thread.start();
			}
		});

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private String[] generatePeriodMasive() {

		List<Period> listMounth = PeriodDAO.getInListPeriod_Mesechni();
		List<Period> listTrim = PeriodDAO.getInListPeriod_TriMesechni();
		for (Period period : listTrim) {
			listMounth.add(period);
		}

		String[] strMounth = new String[listMounth.size()];
		int i = 0;
		for (Period period : listMounth) {
			strMounth[i] = period.getValue();
			i++;
		}
		return strMounth;

	}

	private void createAndViewTable(JLabel lblError, JButton exportButton) {
		JTable table = null;

		int godina = Integer.parseInt(txtFieldGodina.getText());
		Object[][] DataValue = ceateDataValue(godina, mesec);

		valueDataTable = DataValue;
		int countRow = valueDataTable.length;

		tablePanel.removeAll();
		lblError.setText("");
		int header = 0;
		int newTableLeth = 0;
		if (countRow < 2) {
			lblError.setText(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_ReportString"));
			exportButton.setEnabled(false);
		} else {
			nameColumn = createColumnNameTableValue();
			table = CreateDefaultTable(valueDataTable, nameColumn);
			exportButton.setEnabled(true);
			tablePanel.add(new JScrollPane(table));
			header = headerWith;
			newTableLeth = tableLeth;
		}
		setSize(sizeH + newTableLeth, sizeV + header + countRow * newRowWith);
		setLocationRelativeTo(null);
		repaint();
		revalidate();
	}

	private Object[][] ceateDataValue(int godina, String period) {
		String AllResultInInjectionReference = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("AllResultInInjectionReference");
		Period per = PeriodDAO.getValuePeriodByPeriod(period);
		List<Ejection> listEject = generateListEjection(per, godina);

		List<Sample> listNewSample = new ArrayList<>();
		List<Results> listNewResults = new ArrayList<>();
		List<Sample> listSample = SampleDAO.getListSampleByMounthlyReferenceForCNRDWater_Table(per, godina);
		List<Nuclide> listNuclideEjection = NuclideDAO.getListNuclideEjection();

		for (Ejection ejection : listEject) {
			for (Sample sample : listSample) {
				if (sample.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane()
						.equals(ejection.getObect().getName_obekt_na_izpitvane())) {
					listNewSample.add(sample);
				}
			}
		}

		for (Sample sample : listNewSample) {
			System.out.println(sample.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane());
			// List<Results> listResults =
			// ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
			List<Results> listResults = ResultsDAO.getListResultsFromCurentSampleInProtokol(sample);
			
				for (Results results : listResults) {
					if (AllResultInInjectionReference.equals("1")) {
						listNewResults.add(results);
					} else {
					for (Nuclide nuclide : listNuclideEjection) {
					
						if (results.getNuclide().getSymbol_nuclide().equals(nuclide.getSymbol_nuclide())) {
							listNewResults.add(results);
						}
					}
				}
			}
		}

		// *******************************************************************************************************

		Object[][] DataTableValue = new Object[listNewResults.size()][9];
		List<KoeficientObject> listKoeficientObject = KoeficientObjectDAO.getInListAllValueKoeficientObject();
		int i = 0;
		int key = 0;
		Nuclide nuclid;

		double koef;
		double obem = 1;
		double aktiv;
		double izvhAktiv = 0;
		double uncert = 0;
		double mda = 0;
		String obektOld = "";
		String obektNew = "";
		for (Results result : listNewResults) {

			obektNew = result.getSample().getRequest_to_obekt_na_izpitvane_request().getObektNaIzp()
					.getName_obekt_na_izpitvane();
			koef = getKoeficient(listKoeficientObject, obektNew);
			obem = getObem(obektNew, listEject);
			nuclid = result.getNuclide();
			aktiv = result.getValue_result();
			izvhAktiv = aktiv * obem * koef;
			key = nuclid.getEjection_key();
			uncert = 0;
			mda = 0;
			if (key > 1 || aktiv > 0) {
				if (aktiv > 0) {
					uncert = (result.getUncertainty() / result.getSigma()) * 100 / aktiv;
				}
				if (key > 1) {
					mda = result.getMda();
				}

				if (obektNew.equals(obektOld)) {
					DataTableValue[i][0] = "";
					DataTableValue[i][1] = "";
					DataTableValue[i][2] = "";
					DataTableValue[i][3] = "";
				} else {

					DataTableValue[i][0] = obektNew;
					DataTableValue[i][1] = CreateListLeftPanelStartWindowClass
							.getLabelProtokol(result.getRequest().getRecuest_code());
					DataTableValue[i][2] = FormatDoubleNumber.formatDoubleToString(obem, 2, true);
					DataTableValue[i][3] = FormatDoubleNumber.formatDoubleToString(koef, 2, false);
					obektOld = obektNew;
				}

				DataTableValue[i][4] = nuclid.getSymbol_nuclide();
				DataTableValue[i][5] = convertToString(aktiv, 2, true);
				DataTableValue[i][6] = convertToString(izvhAktiv, 2, true);
				DataTableValue[i][7] = convertToString(uncert, 0, false);
				if (uncert != 0) {
					DataTableValue[i][7] = "�" + DataTableValue[i][7] + "%";
				}
				DataTableValue[i][8] = convertToString(mda, 2, true);

				i++;

			}
		}
		for (int j = 0; j < DataTableValue.length; j++) {
			System.out.println();
			for (int j2 = 0; j2 < 9; j2++) {
				System.out.print(DataTableValue[j][j2] + "  ");
			}
			System.out.println();
		}

		return DataTableValue;

	}

	static List<Ejection> generateListEjection(Period per, int godina) {
		Ejection sampleEject = new Ejection();
		List<Ejection> listEjection = new ArrayList<Ejection>();
		List<Period> listIDPeriodBiTrim = new ArrayList<Period>();
		List<String> listStringObectEjection = new ArrayList<String>();
		int idPeriod = per.getId_period();
		if (idPeriod > 20 && idPeriod < 40) {
			switch (idPeriod) {
			case 31:
				listIDPeriodBiTrim.add(PeriodDAO.getPeriodById(1));
				listIDPeriodBiTrim.add(PeriodDAO.getPeriodById(2));
				listIDPeriodBiTrim.add(PeriodDAO.getPeriodById(3));
				break;
			case 32:
				listIDPeriodBiTrim.add(PeriodDAO.getPeriodById(4));
				listIDPeriodBiTrim.add(PeriodDAO.getPeriodById(5));
				listIDPeriodBiTrim.add(PeriodDAO.getPeriodById(6));
				break;
			case 33:
				listIDPeriodBiTrim.add(PeriodDAO.getPeriodById(7));
				listIDPeriodBiTrim.add(PeriodDAO.getPeriodById(8));
				listIDPeriodBiTrim.add(PeriodDAO.getPeriodById(9));
				break;
			case 34:
				listIDPeriodBiTrim.add(PeriodDAO.getPeriodById(10));
				listIDPeriodBiTrim.add(PeriodDAO.getPeriodById(11));
				listIDPeriodBiTrim.add(PeriodDAO.getPeriodById(12));
				break;
			}
			List<Ejection> listNewEjection = new ArrayList<Ejection>();
			for (Period period : listIDPeriodBiTrim) {
				for (Ejection eject : EjectionDAO.getListEjectionFromMesecANDGodina(period, godina)) {
					listNewEjection.add(eject);
				}
			}

			for (Ejection eject : listNewEjection) {
				listStringObectEjection.add(eject.getObect().getName_obekt_na_izpitvane());
			}

			listStringObectEjection = MounthlyReferenceForCNRDWater.removeDuplicates(listStringObectEjection);
			for (String string : listStringObectEjection) {
				double obem = 0.0;
				for (Ejection eject : listNewEjection) {
					if (string.equals(eject.getObect().getName_obekt_na_izpitvane())) {
						obem = obem + eject.getVolum();
						sampleEject.setProdukt(eject.getProdukt());
						sampleEject.setObect(eject.getObect());
						sampleEject.setMesec(per);
						sampleEject.setGodina(godina);
						sampleEject.setVolum(obem);
					}
				}
				listEjection.add(sampleEject);
			}

		} else {
			listEjection = EjectionDAO.getListEjectionFromMesecANDGodina(per, godina);
		}
		return listEjection;
	}

	private Double getKoeficient(List<KoeficientObject> listKoeficientObject, String obektNew) {
		Double kk = 1.0;
		for (KoeficientObject koeficientObject : listKoeficientObject) {
			if (koeficientObject.getObect().getName_obekt_na_izpitvane().equals(obektNew)) {
				kk = koeficientObject.getKoeficient();
			}
		}

		return kk;
	}

	private Double getObem(String obektNew, List<Ejection> listEject) {
		for (Ejection ejection : listEject) {
			if (ejection.getObect().getName_obekt_na_izpitvane().equals(obektNew)) {
				return ejection.getVolum();
			}
		}
		return 1.0;
	}

	private String convertToString(double aktiv, int crease, boolean exponent) {
		String str = "";
		if (aktiv > 0) {
			str = FormatDoubleNumber.formatDoubleToString(aktiv, crease, exponent);
		}
		return str;
	}

	private String[] createColumnNameTableValue() {
		String[] columnNames = new String[] {
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_ObektSample"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_ProtokolN"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_Obem"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWater_Koeficient"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_Nuclide"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_SpActivnost"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_IzhvActivnost"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_KombiniranaNeopred"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_MDA") };
		return columnNames;
	}

	private static String[] createExcellColumnNameValue(String[] columnNameDataValue) {

		for (int i = 0; i < columnNameDataValue.length; i++) {
			columnNameDataValue[i] = columnNameDataValue[i].replace("<html>", "").replace("</html>", "")
					.replace("<br>", " ").replace("<center>", "").replace("</center>", "");
		}

		return columnNameDataValue;
	}

	public static JTable CreateDefaultTable(Object[][] masiveDataTable, String[] columnNames) {
		JTable table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {

				// viewLblVolume ( ceateDataValue(masiveDataTable,
				// listSimbolNuclide));

			}
		});

		table.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// viewLblVolume ( ceateDataValue(masiveDataTable,
				// listSimbolNuclide));
			}
		});

		// new TableFilterHeader(table, AutoChoices.ENABLED);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(masiveDataTable, columnNames) {

					private static final long serialVersionUID = 1L;

					public Object getValueAt(int row, int col) {
						return masiveDataTable[row][col];
					}

					public boolean isCellEditable(int row, int column) {
						if (column == 2) {
							return true;
						}
						return false;
					}

					public void setValueAt(Object value, int row, int col) {
						try {
							// @SuppressWarnings("unused")
							// Double k = Double.valueOf((String) value);
							// if (!masiveDataTable[row][col].equals(value) &&
							// !masiveDataTable[row][col].equals("")) {
							// masiveDataTable[row][col] = value;
							// recalculateRow(row, table, masiveDataTable);
							//
							// viewLblVolume ( ceateDataValue(masiveDataTable,
							// listSimbolNuclide));
							//
							//
							// }
						} catch (Exception e) {
							ResourceLoader.appendToFile(e);
						}
					}

				};

				table.setModel(dtm);
				table.setFillsViewportHeight(true);
				table.getTableHeader()
						.setPreferredSize(new Dimension(table.getColumnModel().getTotalColumnWidth(), 52));
				// table.getTableHeader (). GetDefaultRenderer ().
				// SetHorizontalAlignment (SwingConstants.CENTER);

				((JLabel) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

				initColumnSizes(table);
			}

		});
		return table;
	}

	private static void initColumnSizes(JTable table) {

		// DefaultTableModel model =(DefaultTableModel) table.getModel();
		TableColumn column = null;

		Component comp = null;
		int headerWidth = 0;
		int cellWidth = 0;
		// Object[] longValues = getlong();

		TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();

		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);

			comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
			headerWidth = comp.getPreferredSize().width;

			// comp = table.getDefaultRenderer(model.getColumnClass(i)).
			// getTableCellRendererComponent(
			// table, longValues[i],
			// false, false, 0, i);
			// cellWidth = comp.getPreferredSize().width;

			column.setPreferredWidth(Math.max(headerWidth, cellWidth));
			// column.sizeWidthToFit(); //or simple
		}
	}

	public static void toExcel() {

		try {
			String excelFilePath = GlobalPathForDocFile.get_destinationDir() + "export.xls";
			String sheetName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MenuEjection_TitleName");
			String[] excellnameColumn = createExcellColumnNameValue(nameColumn);
			Workbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet(sheetName);
			Object[][] dataTable = valueDataTable;
			// Cell cell = null;

			for (int i = 0; i < columnExcellWith.length; i++) {
				sheet.setColumnWidth(i, columnExcellWith[i] * 37);
			}

			// Create header column
			Row row = sheet.createRow(0);

			String strMesec = "�����: " + mesec;
			int excellRow = 0;

			for (int i = 0; i < dataTable.length; i++) {

				if (dataTable[i][0] != "") {
					excellRow = excellRow + 3;

					row = sheet.createRow(excellRow);
					row.setHeightInPoints(22);
					sheet.addMergedRegion(new CellRangeAddress(excellRow, excellRow, 1, 2));
					sheet.addMergedRegion(new CellRangeAddress(excellRow, excellRow, 3, 4));
					createCell(row, 1, dataTable[i][0].toString(), cellStyleBoldWithBorder(workbook, true, 14));
					createCell(row, 3, excellnameColumn[1], cellStyleBoldWithBorder(workbook, true, 12));

					excellRow++;
					row = sheet.createRow(excellRow);
					sheet.addMergedRegion(new CellRangeAddress(excellRow, excellRow, 3, 4));
					createCell(row, 1, excellnameColumn[2], cellStyleBoldWithBorder(workbook, false, 12));
					createCell(row, 2, dataTable[i][2].toString(), cellStyleBoldWithBorder(workbook, true, 12));
					createCell(row, 3, dataTable[i][1].toString(), cellStyleBoldWithBorder(workbook, false, 12));

					excellRow++;
					row = sheet.createRow(excellRow);
					createCell(row, 0, excellnameColumn[4], cellStyleBoldWithBorder(workbook, false, 12));
					createCell(row, 1, excellnameColumn[5], cellStyleBoldWithBorder(workbook, false, 12));
					String text = excellnameColumn[6] + "� " + excellnameColumn[3] + " " + dataTable[i][3];
					createCell(row, 2, text, cellStyleBoldWithBorder(workbook, false, 12));
					createCell(row, 3, excellnameColumn[7], cellStyleBoldWithBorder(workbook, false, 8));
					createCell(row, 4, excellnameColumn[8], cellStyleBoldWithBorder(workbook, false, 12));
				}
				excellRow++;
				int k = 0;
				row = sheet.createRow(excellRow);
				boolean fl = false;
				for (int j = 4; j < 9; j++) {
					if (j > 5)
						fl = true;
					createCell(row, k, dataTable[i][j].toString(), cellStyleBoldWithBorder(workbook, fl, 12));
					k++;
				}

			}

			setBordersToMergedCells(workbook, sheet);

			row = sheet.createRow(0);
			row.setHeightInPoints(22);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
			createCell(row, 0, strMesec, cellStyleBoldWitholtBorder(workbook, true, 14));

			excellRow = excellRow + 3;
			row = sheet.createRow(excellRow);
			createCell(row, 1, "��������:", cellStyleBoldWitholtBorder(workbook, false, 12));
			createCell(row, 3, "��������:", cellStyleBoldWitholtBorder(workbook, false, 12));
			sheet.addMergedRegion(new CellRangeAddress(excellRow, excellRow, 3, 4));

			FileOutputStream outFile = new FileOutputStream(new File(excelFilePath));
			workbook.write(outFile);
			outFile.close();

			GenerateRequestWordDoc.openWordDoc(excelFilePath);

		} catch (FileNotFoundException e) {
			ResourceLoader.appendToFile(e);
			MessageDialog(e.toString(), "������� ������");
		} catch (IOException e) {
			ResourceLoader.appendToFile(e);
			e.printStackTrace();
		}
	}

	private static CellStyle cellStyleBoldWithBorder(Workbook workbook, boolean fl, int size) {
		CellStyle cellStyleHeader;
		Font fontHeader = workbook.createFont();
		fontHeader.setFontName("Times New Roman");
		fontHeader.setFontHeightInPoints((short) size);
		fontHeader.setColor(IndexedColors.BLACK.getIndex());
		fontHeader.setBold(fl);
		cellStyleHeader = workbook.createCellStyle();
		cellStyleHeader.setFont(fontHeader);
		cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
		cellStyleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
		// cellStyleHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		// cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyleHeader.setWrapText(true);
		setBordrCell(cellStyleHeader);

		return cellStyleHeader;
	}

	private static CellStyle cellStyleBoldWitholtBorder(Workbook workbook, boolean fl, int size) {
		CellStyle cellStyleHeader;
		Font fontHeader = workbook.createFont();
		fontHeader.setFontName("Times New Roman");
		fontHeader.setFontHeightInPoints((short) 12);
		fontHeader.setColor(IndexedColors.BLACK.getIndex());
		fontHeader.setBold(fl);
		cellStyleHeader = workbook.createCellStyle();
		cellStyleHeader.setFont(fontHeader);
		cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
		cellStyleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
		// cellStyleHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		// cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyleHeader.setWrapText(true);

		return cellStyleHeader;
	}

	private static void createCell(Row row, int col, String str, CellStyle cellStyleHeader) {
		Cell cell = null;
		cell = row.createCell(col, CellType.STRING);
		cell.setCellStyle(cellStyleHeader);
		cell.setCellValue(str);

	}

	// private static void createNumberCell(Workbook workbook,Row row, int col,
	// String str, CellStyle cellStyleHeader) {
	//
	// Cell cell = null;
	// cell = row.createCell(col, CellType.NUMERIC);
	// DataFormat format = workbook.createDataFormat();
	// cellStyleHeader.setDataFormat(format.getFormat("0.00E00"));
	// cell.setCellStyle(cellStyleHeader);
	//
	// cell.setCellValue(FormatDoubleNumber.formatStringToDouble(str, 2));
	//
	// }

	public static void MessageDialog(String textInFrame, String textFrame) {
		Icon otherIcon = null;
		JFrame jf = new JFrame();
		jf.setAlwaysOnTop(true);

		JOptionPane.showMessageDialog(jf, textInFrame, textFrame, JOptionPane.PLAIN_MESSAGE, otherIcon);

	}

	public static CellStyle insertBorder(CellStyle style) {

		style.setBorderBottom(BorderStyle.DOUBLE);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());

		return style;

	}

	public static CellStyle setBordrCell(CellStyle style) {

		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());

		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());

		return style;

	}

	private static void setBordersToMergedCells(Workbook workBook, Sheet sheet) {
		List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
		for (CellRangeAddress rangeAddress : mergedRegions) {
			RegionUtil.setBorderTop(BorderStyle.THIN, rangeAddress, sheet);
			RegionUtil.setBorderLeft(BorderStyle.THIN, rangeAddress, sheet);
			RegionUtil.setBorderRight(BorderStyle.THIN, rangeAddress, sheet);
			RegionUtil.setBorderBottom(BorderStyle.THIN, rangeAddress, sheet);
		}
	}
}