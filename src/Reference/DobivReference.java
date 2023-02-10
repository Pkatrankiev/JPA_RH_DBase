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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.Icon;
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
import javax.swing.table.TableColumnModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import Aplication.DobivDAO;
import Aplication.MetodyDAO;

import Aplication.ResultsDAO;
import CreateWordDocProtocol.GenerateRequestWordDoc;
import DBase_Class.Dobiv;
import DBase_Class.Metody;
import DBase_Class.Results;
import DefaultTableList.TableList_Functions;
import GlobalVariable.GlobalPathForDocFile;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import GlobalVariable.ResourceLoader;
import WindowView.TranscluentWindow;

public class DobivReference extends JDialog {

	private static final long serialVersionUID = 7534173139838953837L;

	private JTextField txtFieldGodina;
	static String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	final String[] strPeriod = MetodyDAO.getMasiveStringAllValueMetody();
	static String metod;
	private static Object[][] valueDataTable;
	private static String[] nameColumn;
	static JPanel top_panel;
	JPanel tablePanel = new JPanel();

	int sizeV = 160;
	int sizeH = 450;
	int newRowWith = 15;
	int countRow = 0;
	int headerWith = 80;
	int tableLeth = 600;
	static int listNuclideCount;

	public DobivReference(JFrame parent, String frame_name) {
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

		JLabel lblMetod = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("LabelText_Metod"));
		GridBagConstraints gbc_lblMetod = new GridBagConstraints();
		gbc_lblMetod.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMetod.insets = new Insets(0, 0, 5, 5);
		gbc_lblMetod.gridx = 2;
		gbc_lblMetod.gridy = 0;
		top_panel.add(lblMetod, gbc_lblMetod);

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

				if (!comboBox.getSelectedItem().toString().equals(metod)) {
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
						metod = (String) comboBox.getSelectedItem();
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

	private void createAndViewTable(JLabel lblError, JButton exportButton) {
		JTable table = null;

		int godina = Integer.parseInt(txtFieldGodina.getText());
		List<Dobiv> listDobiv = getListDobiv(godina);
		List<String> listNuclide = generateListSymbolNuclide(listDobiv);
		Object[][] DataValue = ceateDataValue(listDobiv, listNuclide);

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
			nameColumn = createColumnNameTableValue(listNuclide);
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

	private Object[][] ceateDataValue(List<Dobiv> listDobiv, List<String> listNuclide) {

		List<String> listDateDobiv = generateListDateDobiv(listDobiv);
		int numNuclide = listNuclide.size();
		int numColumn = 8 + numNuclide;

		// *******************************************************************************************************

		// Object[][] DataTableValue = generateDataTableValue(listDobiv,
		// listNuclide, listDateDobiv, numColumn + 1);

		// Object[][] DataTableValue = generateDataTableValueBiSample(listDobiv,
		// listNuclide, listDateDobiv, numColumn + 1);

		Object[][] DataTableValue = generateDataTableValueAllDobive(listDobiv, listNuclide, listDateDobiv,
				numColumn);

		// int countVolume = 0;
		// String stringVolume = "";
		// String newStringVolume = "";
		// String nuclideSimbol = "";
		// int rowDataTab = 0;
		// while (countVolume == 0 && rowDataTab < DataTableValue.length) {
		// int colDataTab = 4;
		// int nuclideIndex = 0;
		// while (countVolume == 0 && colDataTab < numNuclide) {
		// stringVolume = DataTableValue[rowDataTab][colDataTab].toString();
		// countVolume = StringUtils.countMatches(stringVolume, ";");
		// nuclideSimbol = listNuclide.get(nuclideIndex);
		// nuclideIndex++;
		// colDataTab++;
		// }
		// rowDataTab++;
		// }
		//
		// List<NewDataRowTatle> listNewDataRowTatle = new
		// ArrayList<NewDataRowTatle>();
		// if (countVolume > 0) {
		// NewDataRowTatle newDataTableValue = new NewDataRowTatle();
		// stringVolume = DataTableValue[rowDataTab][1].toString();
		// for (int k = 0; k < countVolume; k++) {
		// System.out.println(stringVolume);
		// newStringVolume = stringVolume.substring(0,
		// stringVolume.indexOf(";")).trim();
		// stringVolume = stringVolume.substring(stringVolume.indexOf(";") +
		// 1).trim();
		// System.out.println(newStringVolume + " " + stringVolume);
		// listNewDataRowTatle.add(addNewRow(rowDataTab, newStringVolume,
		// nuclideSimbol, listDobiv));
		// }
		// listNewDataRowTatle.add(addNewRow(rowDataTab, stringVolume,
		// nuclideSimbol, listDobiv));
		// }

		for (int j = 0; j < DataTableValue.length; j++) {
			System.out.println();
			for (int j2 = 0; j2 < numColumn; j2++) {
				System.out.print(DataTableValue[j][j2] + "  ");
			}
			System.out.println();
		}

		return DataTableValue;

	}

	private Object[][] generateDataTableValueAllDobive(List<Dobiv> listDobiv, List<String> listNuclide,
			List<String> listDateDobiv, int numColumn) {

		Object[][] DataTableValue = new Object[listDobiv.size()][numColumn];

		listNuclideCount = listNuclide.size();

		int rowDataTableValue = 0;
		for (String data : listDateDobiv) {

			List<NewDataRowTatle> listNewDobivBiData = generateListDobivBiData(listDobiv, data);
			List<String> listCodeSample = new ArrayList<String>();
			Object[][] DataTableValueBiData = new Object[listNewDobivBiData.size()][numColumn];
			int countDataTableValueBiData = 0;
			for (NewDataRowTatle newDobiv : listNewDobivBiData) {

				@SuppressWarnings("unchecked")
				List<String>[] listMasiveValueDobivBiNuclide = new ArrayList[listNuclideCount];
				for (int z = 0; z < listNuclideCount; z++) {
					listMasiveValueDobivBiNuclide[z] = new ArrayList<String>();
				}

				DataTableValueBiData[countDataTableValueBiData] = addNewDobivToRow(listNuclide, data, newDobiv);
				listCodeSample.add(DataTableValueBiData[countDataTableValueBiData][1].toString().trim());
				countDataTableValueBiData++;

			}
			
			Object[][] masive = generateMasiveData(DataTableValueBiData, numColumn);
			for (int i =0 ; i < masive.length; i++) {
				DataTableValue[rowDataTableValue] = masive[i];
				rowDataTableValue++;
			}
			
			}
		 int k=0;
		 int row = DataTableValue.length;
		for (int i = 0; i < row; i++) {
			if(DataTableValue[i][0]==null){
				k++;
			}
		}
		
		row = row - k;
		Object[][] newMasive = new Object[row][numColumn];
		for (int i = 0; i < row; i++) {
			newMasive[i] = DataTableValue[i];
			}
		
		return newMasive;
}
		
		
	private Object[][] generateMasiveData(Object[][] dataTableValueBiData, int numColumn) {
		
		
		
		List<String> listCodeSample = new ArrayList<String>();
		for (Object[] objects : dataTableValueBiData) {
			listCodeSample.add(objects[1].toString());
		}
		List<String> listRemoteCodeSample = removeDuplicates(listCodeSample);
		Object[][] masive = new Object[listRemoteCodeSample.size()][numColumn];
		int k =0;
		for (String codeSample : listRemoteCodeSample) {
			List<Object[]> listObject = new ArrayList<Object[]>();
			for (Object[] objects : dataTableValueBiData) {
				if(codeSample.equals(objects[1])){
					listObject.add(objects);
				}
			}
			masive[k] = generateMasiveTableValueBiSample(listObject, numColumn);
			k++;
		}
		
		
		return masive;
	}

	private Object[] generateMasiveTableValueBiSample(List<Object[]> listObjectBiSampleCode, int numColumn) {
		
		Object[] masive =	new Object[numColumn];
		for (int i = 0; i < numColumn; i++) {
			List<String> listString = new ArrayList<String>();
		for (Object[] objects : listObjectBiSampleCode) {
			if(objects[i]==null){objects[i]="";}
			System.out.println(i+" - "+objects[i].toString());
				listString.add(objects[i].toString());
			}
			masive[i] = genarateStringFromList(removeDuplicates(listString));
		}
		
		return masive;
	}

	private List<NewDataRowTatle> generateListDobivBiData(List<Dobiv> listDobiv, String data) {
		List<NewDataRowTatle> listNewDobivBiData = new ArrayList<NewDataRowTatle>();
		for (Dobiv dobiv : listDobiv) {
			if (dobiv.getDate_chim_oper().equals(data)) {
				listNewDobivBiData.add(generateNewDobiv(dobiv));
			}
		}
		
		
		return listNewDobivBiData;
	}

	private Object[] addNewDobivToRow(List<String> listNuclide, String data, NewDataRowTatle newDobiv) {

		Object[] DataTableValue = new Object[listNuclide.size() + 8];

		DataTableValue[0] = newDobiv.getCodStandart();
		DataTableValue[1] = newDobiv.getCodSample();
		DataTableValue[2] = newDobiv.getDescriptionSample();
		DataTableValue[3] = newDobiv.getProdukt();

		int k = 4;

		for (String nuclid : listNuclide) {
			System.out.println(nuclid + " -> " + newDobiv.getNuclideSimbol());
			if (nuclid.equals(newDobiv.getNuclideSimbol())) {
				DataTableValue[k] = newDobiv.getValueDobiv();

			}
			k++;
		}

		DataTableValue[k] = newDobiv.gettSI();
		DataTableValue[k + 1] = newDobiv.getHimUser();
		DataTableValue[k + 2] = newDobiv.getMeasurUser();
		DataTableValue[k + 3] = data;

		return DataTableValue;
	}

	
	private NewDataRowTatle generateNewDobiv(Dobiv dobiv) {

		NewDataRowTatle newRowObject = new NewDataRowTatle();

		double value = convertValue(dobiv.getValue_result());
		
		newRowObject.setCodStandart(dobiv.getCode_Standart());
		newRowObject.setCodSample(genarateStringFromList(generateListStringCodeSample(dobiv)).toString());
		newRowObject.setDescriptionSample(dobiv.getDescription());
		newRowObject.setProdukt(dobiv.getIzpitvan_produkt().getName_zpitvan_produkt());
		newRowObject.setNuclideSimbol(dobiv.getNuclide().getSymbol_nuclide());
		newRowObject.setValueDobiv(convertAndNegativToString(value, 4, false));
		newRowObject.settSI(dobiv.getTsi().getName());
		newRowObject.setHimUser(
				dobiv.getUser_chim_oper().getName_users() + " " + dobiv.getUser_chim_oper().getFamily_users());
		newRowObject
				.setMeasurUser(dobiv.getUser_measur().getName_users() + " " + dobiv.getUser_measur().getFamily_users());

		return newRowObject;
	}

	private double convertValue(Double value_result) {
		if(value_result>2){
			value_result = value_result/100;
		}
		return value_result;
	}

		private List<Dobiv> getListDobiv(int godina) {
		Metody met = MetodyDAO.getValueList_MetodyByCode(metod);
		List<Dobiv> listDobiv = generateListDobiv(met, godina);
		return listDobiv;
	}

	private Object genarateStringFromList(List<String> listString) {
		String str = "";
		for (String string : removeDuplicates(listString)) {
			if (!string.trim().isEmpty()) {
				str = str + string + "; ";
			}
		}
		if (str.length() >= 2) {
			str = str.substring(0, (str.length() - 2));
		}
		return str;
	}

	private List<String> generateListStringCodeSample(Dobiv dobiv) {

		List<String> listCodeSample = new ArrayList<String>();
		for (Results result : ResultsDAO.getListResultsFromColumnByVolume("dobiv", dobiv)) {
			listCodeSample.add(result.getRequest().getRecuest_code() + "-" + result.getSample().getSample_code());
		}
		Collections.sort(listCodeSample);
		return removeDuplicates(listCodeSample);
	}

	private List<String> generateListDateDobiv(List<Dobiv> listDobiv) {
		List<String> ListDateDobiv = new ArrayList<String>();
		for (Dobiv dobiv : listDobiv) {
			ListDateDobiv.add(dobiv.getDate_chim_oper());
		}
		return removeDuplicates(ListDateDobiv);

	}

	private List<String> generateListSymbolNuclide(List<Dobiv> listDobiv) {
		List<String> listNuclide = new ArrayList<String>();
		for (Dobiv dobiv : listDobiv) {
			listNuclide.add(dobiv.getNuclide().getSymbol_nuclide());
		}
		return removeDuplicates(listNuclide);

	}

	static List<Dobiv> generateListDobiv(Metody met, int godina) {
		List<Dobiv> listDobiv = new ArrayList<Dobiv>();
		for (Dobiv dobiv : DobivDAO.getListDobivByMetody(met)) {
			int god = Integer.parseInt(dobiv.getDate_chim_oper().substring(6));
			if (godina == god) {
				listDobiv.add(dobiv);
			}
		}
		return listDobiv;
	}

	private String convertAndNegativToString(double aktiv, int crease, boolean exponent) {
		return FormatDoubleNumber.formatDoubleToString(aktiv, crease, exponent);
	}

	private String[] createColumnNameTableValue(List<String> listNuclide) {

		int numNuclide = listNuclide.size();
		int column = 7 + numNuclide;

		String[] columnNames = new String[column];

		columnNames[0] = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Reference_LabelText_CodeStandart");
		columnNames[1] = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Reference_LabelText_SampleCode");
		columnNames[2] = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Reference_LabelText_CommentSample");
		columnNames[3] = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Reference_LabelText_IzpitvanProdukt");

		int k = 4;
		String str = "<html>" + ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("LabelText_Dobiv")
				+ "<br>";
		for (String string : listNuclide) {
			columnNames[k] = str + string + "</html>";
			k++;
		}

		columnNames[k] = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_TSI");
		columnNames[k + 1] = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_ORH");
		columnNames[k + 2] = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_OРМ");

		return columnNames;
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
			String sheetName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_Dobiv_FrameName");
			String[] excellnameColumn = createExcellColumnNameValue(nameColumn);
			Workbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet(sheetName);
			// Cell cell = null;

						
			Cell cell = null;
			
			Font fontHeader= workbook.createFont();
		    fontHeader.setColor(IndexedColors.BLACK.getIndex());
			fontHeader.setBold(true);
			
			
			CellStyle cellStyleHeader = workbook.createCellStyle();
			cellStyleHeader.setFont(fontHeader);
			cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
			cellStyleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
			cellStyleHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
			cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cellStyleHeader.setWrapText(true);
			insertBorder(cellStyleHeader);
			
			CellStyle cellStyleLabel = workbook.createCellStyle();
			cellStyleLabel.setFont(fontHeader);
			cellStyleLabel.setAlignment(HorizontalAlignment.LEFT);	
			cellStyleLabel.setVerticalAlignment(VerticalAlignment.CENTER);
			cellStyleLabel.setFillPattern(FillPatternType.NO_FILL);
			
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HorizontalAlignment.LEFT);	
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellStyle.setFillPattern(FillPatternType.NO_FILL);
			
			
			
			DataFormat formatDouble = workbook.createDataFormat();
			CellStyle cellStyleDouble = workbook.createCellStyle();
			cellStyleDouble.setDataFormat(formatDouble.getFormat("0,0000"));
			
//			Create label text
			
			String label = sheetName+" на метод "+metod+" за "+year+"г.";
			Row row = sheet.createRow(0);
					cell = row.createCell(0, CellType.STRING);
					cell.setCellStyle(cellStyleLabel);
					cell.setCellValue(label);
					sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));		
		
	int columnCount = nameColumn.length;
	int rowCount = valueDataTable.length;
	int excelColumnCount = 0;
			
//			Create header column
			row = sheet.createRow(2);
			for (int i = 0; i < columnCount+1; i++) {
				
					cell = row.createCell(i, CellType.STRING);
					cell.setCellStyle(cellStyleHeader);
					 if(i<columnCount){
				cell.setCellValue(excellnameColumn[i]);
					 }else{
						 cell.setCellValue("Дата");
					 }
				 sheet.autoSizeColumn(i);
				
	        	 sheet.setColumnWidth(i,sheet.getColumnWidth(i)+1000);
	        		
			}
						
//			Create column	
			Object volue ;
				double doble;	
			int rowIndex = 3;
			for (int rowTable = 0; rowTable < rowCount; rowTable++) {
				row = sheet.createRow(rowIndex);
				excelColumnCount = 0;
				for (int j = 0; j < columnCount+1; j++) {
						 volue = valueDataTable[rowTable][j];
						 try {
						 doble = FormatDoubleNumber.formatStringToDouble(volue.toString(), 4);
						 cell = row.createCell(excelColumnCount, CellType.NUMERIC);
				        	cell.setCellStyle(cellStyleDouble);
				        	cell.setCellValue(doble);
						 } catch (NumberFormatException e) {
							 cell = row.createCell(excelColumnCount);
					        	cell.setCellStyle(cellStyle);
					        	cell.setCellValue(volue.toString());
							}
										     
						setBordrCell ( cell,  workbook);
						excelColumnCount++;
					
				}
				rowIndex++;
			}
			
			
			
			
			sheet.createFreezePane(0, 3);
			sheet.setAutoFilter(new CellRangeAddress ( 2, 2, 0, excelColumnCount-1 ));
			
			FileOutputStream outFile = new FileOutputStream(new File(excelFilePath));
			workbook.write(outFile);
			outFile.close();

			GenerateRequestWordDoc.openWordDoc(excelFilePath);
			
		} catch (FileNotFoundException e) {
			ResourceLoader.appendToFile(e);
			MessageDialog(e.toString(), "файлова грешка");
		} catch (IOException e) {
			ResourceLoader.appendToFile(e);
			e.printStackTrace();
		}
	}

	
	public static Cell setBordrCell (Cell cell, Workbook workbook){
	    CellStyle style = workbook.createCellStyle();  
        style.setBorderBottom(BorderStyle.THIN);  
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());  
        
        style.setBorderRight(BorderStyle.THIN);  
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());  
       
        style.setBorderTop(BorderStyle.THIN);  
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());  
        
        style.setBorderLeft(BorderStyle.THIN);  
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        
        cell.setCellStyle(style); 
		
		
		return cell;
		
	}	
	
	private static String[] createExcellColumnNameValue(String[] columnNameDataValue) {

		for (int i = 0; i < columnNameDataValue.length; i++) {
			columnNameDataValue[i] = columnNameDataValue[i].replace("<html>", "").replace("</html>", "")
					.replace("<br>", " ").replace("<center>", "").replace("</center>", "");
		}

		return columnNameDataValue;
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

	public static List<String> removeDuplicates(List<String> listAllNuclide) {

		Set<String> set = new LinkedHashSet<>();
		// Add the elements to set
		set.addAll(listAllNuclide);
		// Clear the list
		listAllNuclide.clear();
		// add the elements of set
		// with no duplicates to the list
		listAllNuclide.addAll(set);
		// return the list
		return listAllNuclide;
	}

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

class NewDataRowTatle {
	private String codStandart;
	private String codSample;
	private String descriptionSample;
	private String produkt;
	private String nuclideSimbol;
	private String valueDobiv;
	private String tSI;
	private String HimUser;
	private String MeasurUser;
	private int numRow;

	public String getCodStandart() {
		return codStandart;
	}

	public void setCodStandart(String codStandart) {
		this.codStandart = codStandart;
	}

	public String getCodSample() {
		return codSample;
	}

	public void setCodSample(String codSample) {
		this.codSample = codSample;
	}

	public String getDescriptionSample() {
		return descriptionSample;
	}

	public void setDescriptionSample(String descriptionSample) {
		this.descriptionSample = descriptionSample;
	}

	public String getProdukt() {
		return produkt;
	}

	public void setProdukt(String produkt) {
		this.produkt = produkt;
	}

	public String getNuclideSimbol() {
		return nuclideSimbol;
	}

	public void setNuclideSimbol(String nuclideSimbol) {
		this.nuclideSimbol = nuclideSimbol;
	}

	public String getValueDobiv() {
		return valueDobiv;
	}

	public void setValueDobiv(String valueDobiv) {
		this.valueDobiv = valueDobiv;
	}

	public String gettSI() {
		return tSI;
	}

	public void settSI(String tSI) {
		this.tSI = tSI;
	}

	public String getHimUser() {
		return HimUser;
	}

	public void setHimUser(String himUser) {
		HimUser = himUser;
	}

	public String getMeasurUser() {
		return MeasurUser;
	}

	public void setMeasurUser(String measurUser) {
		MeasurUser = measurUser;
	}

	public int getNumRow() {
		return numRow;
	}

	public void setNumRow(int numRow) {
		this.numRow = numRow;
	}
}