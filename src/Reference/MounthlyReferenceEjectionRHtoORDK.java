package Reference;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
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

import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import CreateWordDocProtocol.GenerateRequestWordDoc;
import DBase_Class.Period;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import GlobalVariable.GlobalPathForDocFile;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import GlobalVariable.ResourceLoader;
import WindowView.RequestViewFunction;
import WindowView.TranscluentWindow;

public class MounthlyReferenceEjectionRHtoORDK extends JDialog {

	private static final long serialVersionUID = 7534173139838953837L;
	
	private final JPanel contentPanel = new JPanel();
	static String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	private JTextField textField_BT_C;
	private JTextField textField_BT_H;
	static boolean errorFl = true;
	public MounthlyReferenceEjectionRHtoORDK(JFrame parent, String nameFrame) {
		super(parent, nameFrame, true);
		setResizable(false);
		String[] strMounth = getStringMounth();
	
		setSize(520, 280);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 90, 162, 158, 0 };
		gbl_contentPanel.rowHeights = new int[] { 20, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		String str_BasicLabel = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceEjectionRHtoORDK_BasicLabel");
		JLabel lblHeaderText = new JLabel("<html><div style='text-align: center;'>" + str_BasicLabel + "</div></html>");

		lblHeaderText.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblHeaderText.setHorizontalAlignment(SwingConstants.CENTER);
		
		GridBagConstraints gbc_lblHeaderText = new GridBagConstraints();
		gbc_lblHeaderText.gridwidth = 2;
		gbc_lblHeaderText.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeaderText.gridx = 0;
		gbc_lblHeaderText.gridy = 0;
		contentPanel.add(lblHeaderText, gbc_lblHeaderText);
				
			JLabel lblPeriod = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWater_MesecLabel"));
			GridBagConstraints gbc_lblPeriod = new GridBagConstraints();
			gbc_lblPeriod.insets = new Insets(0, 0, 5, 5);
			gbc_lblPeriod.gridx = 0;
			gbc_lblPeriod.gridy = 1;
			contentPanel.add(lblPeriod, gbc_lblPeriod);
		
			JComboBox<?> comboBox = new JComboBox<Object>(strMounth);
			comboBox.setMaximumRowCount(20);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.anchor = GridBagConstraints.NORTHWEST;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 1;
			contentPanel.add(comboBox, gbc_comboBox);
			
			JLabel lblError = new JLabel();
			GridBagConstraints gbc_lblError = new GridBagConstraints();
			gbc_lblError.insets = new Insets(0, 0, 0, 5);
			gbc_lblError.anchor = GridBagConstraints.WEST;
			gbc_lblError.gridwidth = 3;
			gbc_lblError.gridx = 0;
			gbc_lblError.gridy = 4;
			contentPanel.add(lblError, gbc_lblError);
			// lblErrorGodina.setVisible(false);
		
			JButton okButton = new JButton("OK");
			
			
			
			
		JLabel lblBT_C = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceEjectionRHtoORDK_Request_C"));
		GridBagConstraints gbc_lblBT_C = new GridBagConstraints();
		gbc_lblBT_C.fill = GridBagConstraints.VERTICAL;
		gbc_lblBT_C.anchor = GridBagConstraints.EAST;
		gbc_lblBT_C.insets = new Insets(0, 0, 5, 5);
		gbc_lblBT_C.gridx = 0;
		gbc_lblBT_C.gridy = 2;
		contentPanel.add(lblBT_C, gbc_lblBT_C);
		
		textField_BT_C = new JTextField();
		GridBagConstraints gbc_textField_BT_C = new GridBagConstraints();
		gbc_textField_BT_C.fill = GridBagConstraints.VERTICAL;
		gbc_textField_BT_C.anchor = GridBagConstraints.WEST;
		gbc_textField_BT_C.insets = new Insets(0, 0, 5, 5);
		gbc_textField_BT_C.gridx = 1;
		gbc_textField_BT_C.gridy = 2;
		contentPanel.add(textField_BT_C, gbc_textField_BT_C);
		textField_BT_C.setColumns(6);
		textField_BT_C.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {
				enterRequestCode(textField_BT_C, lblError, okButton);

			}

			@Override
			public void keyPressed(KeyEvent event) {

			}
		});
		
		
		JLabel lblBT_H = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceEjectionRHtoORDK_Request_H"));
		GridBagConstraints gbc_lblBT_H = new GridBagConstraints();
		gbc_lblBT_H.fill = GridBagConstraints.VERTICAL;
		gbc_lblBT_H.anchor = GridBagConstraints.EAST;
		gbc_lblBT_H.insets = new Insets(0, 0, 5, 5);
		gbc_lblBT_H.gridx = 0;
		gbc_lblBT_H.gridy = 3;
		contentPanel.add(lblBT_H, gbc_lblBT_H);
		
		textField_BT_H = new JTextField();
		GridBagConstraints gbc_textField_BT_H = new GridBagConstraints();
		gbc_textField_BT_H.fill = GridBagConstraints.VERTICAL;
		gbc_textField_BT_H.anchor = GridBagConstraints.WEST;
		gbc_textField_BT_H.insets = new Insets(0, 0, 5, 5);
		gbc_textField_BT_H.gridx = 1;
		gbc_textField_BT_H.gridy = 3;
		contentPanel.add(textField_BT_H, gbc_textField_BT_H);
		textField_BT_H.setColumns(6);
		textField_BT_H.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {
				enterRequestCode(textField_BT_H, lblError, okButton);

			}

			@Override
			public void keyPressed(KeyEvent event) {

			}
		});

	

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createVolumeAndViewTable(comboBox.getSelectedItem().toString(),  
						textField_BT_C.getText(), textField_BT_H.getText(), lblError);
			}

		});
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}

		});
		
		
		
		
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
			
	public static void enterRequestCode(JTextField txtField_RequestCode, JLabel lblError, JButton okButton) {
		txtField_RequestCode.setText(RequestViewFunction.checkFormatString(txtField_RequestCode.getText()));
		if (!RequestDAO.checkRequestCode(txtField_RequestCode.getText())) {
			txtField_RequestCode.setForeground(Color.red);
			lblError.setText("Заявка с този номер не съществува");
			errorFl = false;
			okButton.setEnabled(false);
		} else {

			if (RequestViewFunction.checkMaxVolume(txtField_RequestCode.getText(), 3000, 6000)) {
				txtField_RequestCode.setForeground(Color.red);
				lblError.setText("Некоректен номер");
				errorFl = false;
				okButton.setEnabled(false);
			} else {
				txtField_RequestCode.setForeground(Color.BLACK);
				txtField_RequestCode.setBorder(new LineBorder(Color.BLACK));
				lblError.setText("");
				errorFl = true;
				okButton.setEnabled(true);
			}
		}
	}
	
	private void createVolumeAndViewTable(String mesec, String requestC, String requestH, JLabel lblError) {
		TranscluentWindow round = new TranscluentWindow();
		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				
		if(checkRequestCodes(mesec, requestC, requestH, lblError, round)){
			String[] listHTO = {"HT+HTO","HTO"};
			String[] listCO = {"CO₂+CO+CxHy","CO₂"};
			Object[][] DataTableValue_H = generateDataTable(requestH, listHTO);
			Object[][] DataTableValue_C = generateDataTable(requestC, listCO);
			
			toExcel( DataTableValue_H,  DataTableValue_C,  mesec, round); 
			
			}
				}
			
				});
		thread.start();
		
	}
	
	private Object[][] generateDataTable(String request, String[] listObektSample) {
		Object[][] DataTableValue = new Object[4][5];
		String[] listBT = {"Вентилационна тръба-1","Вентилационна тръба-2"};
		Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", request);
		List<Sample> list_Sample = SampleDAO.getListSampleFromColumnByVolume("request", choiseRequest);
		int indexTable = 0;
		String obektRequest = "";
		String obektSample = "";
		for (int i = 0; i <2; i++) {
			for (int j = 0; j <2; j++) {
		for (Sample sample : list_Sample) {
			List<Results> listResultBiSample = ResultsDAO.getListResultsFromCurentSampleInProtokol(sample);
			for (Results result : listResultBiSample) {
				obektRequest = sample.getRequest_to_obekt_na_izpitvane_request().getObektNaIzp().getName_obekt_na_izpitvane();
				obektSample = sample.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane();
				System.out.println(obektRequest+" <-> "+(listBT[i])+" ------------ "+ obektSample+" <-> "+(listObektSample[j]));
			if(obektRequest.equals(listBT[i]) && obektSample.equals(listObektSample[j])){
				System.out.println(obektRequest.equals(listBT[i])+"  "+ obektSample.equals(listObektSample[j]));
				
					DataTableValue[indexTable][0] = sample.getRequest_to_obekt_na_izpitvane_request().getObektNaIzp().getSimple_Name();
					DataTableValue[indexTable][1] = obektSample;
					DataTableValue[indexTable][2] = result.getValue_result();
					DataTableValue[indexTable][3] = result.getUncertainty();
					DataTableValue[indexTable][4] = result.getMda();
					indexTable++;
				}
			}
			}
		}
		}
	
		return DataTableValue;
	}

	private boolean checkRequestCodes(String mesec, String requestC, String requestH, JLabel lblError, TranscluentWindow round) {
		String errorLabel ="";
		boolean fl = true;
		
		if(!requestC.isEmpty() && !requestH.isEmpty()){
			
		
		if(!checkCorectRequest_C(requestC, mesec)){
			errorLabel ="<html>"+ ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("RequestView_CodeForRequest")+
					" "+ requestC +" не е за изхвърляния на 14С от ВТ в м."+ mesec+"</html>";
					
		}
		if(!checkCorectRequest_H(requestH, mesec)){
			String str = "<html>"+ ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("RequestView_CodeForRequest")+
					" "+ requestH +" не е за изхвърляния на 3H от ВТ в м."+ mesec+"</html>";
		
		if(errorLabel.isEmpty()){
			errorLabel = str;
			}else{
				errorLabel = errorLabel.replace("</html>", "")	+ str.replace("<html>", "<br>");
			}
		}
		}else{
			errorLabel = "Несте въвели код на заявката";
		
		}
		if(!errorLabel.isEmpty()){
			lblError.setText(errorLabel);
			round.StopWindow();
			fl=false;
		}
		return fl;
	}

	public static void toExcel(Object[][] DataTableValue_H, Object[][] DataTableValue_C, String mesec, TranscluentWindow round) {
		for (Object[] objects : DataTableValue_C) {
			for (Object object : objects) {
				System.out.println(object);
			}
		}
		
		int[] columnExcellWith = { 110, 90, 90, 60, 110, 130, 90 };
		String[] label_H = {"A (3H), Bq/ml","U (3H), Bq/ml","MDA(3H), Bq/ml"};
		String[] label_C = {"A (14C), Bq/ml","U (14C), Bq/ml","MDA(14C), Bq/ml"};
		
		try {
			String name = "таблица РХ за ОРДК-";
			String excelFilePath = GlobalPathForDocFile.get_destinationDir() +name+ mesec+".xls";
			String sheetName = mesec;
//			String[] excellnameColumn = createExcellColumnNameValue(nameColumn);
			Workbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet(sheetName);
		
			short blue = IndexedColors.LIGHT_TURQUOISE1.getIndex();
			short textBlue = IndexedColors.BLUE.getIndex();
			
			short ping = IndexedColors.ROSE.getIndex();
			short textRed = IndexedColors.RED.getIndex();
			
			short orang = IndexedColors.TAN.getIndex();
			
			for (int i = 0; i < 20; i++) {
			sheet.createRow(i);
//			row.setHeightInPoints(22);	
			}
			
			for (int i = 0; i < columnExcellWith.length; i++) {
				sheet.setColumnWidth(i, columnExcellWith[i] * 37);
			}

			// Create header column
			
			String strMesec = "м. " + sheetName;
			sheet.getRow(1).setHeightInPoints(30);
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 4));
			createCell(1, 2, strMesec, cellStyleBoldWitholtBorder(workbook, true, 20), sheet);
			
			
			createCell(3, 3, DataTableValue_H[0][0].toString(), cellStyleBoldWitholtBorderText(workbook, true, 14, textRed), sheet);
			int excellRow = 5;	
					
					
			
										
					createCell(excellRow, 1, DataTableValue_H[0][1].toString(), cellStyleStringBoldWithBorder(workbook, true, 10, ping), sheet);
					createCell(excellRow, 2, DataTableValue_H[1][1].toString(), cellStyleStringBoldWithBorder(workbook, true, 10, ping), sheet);
					
					createCell(excellRow, 5, DataTableValue_C[0][1].toString(), cellStyleStringBoldWithBorder(workbook, true, 10, ping), sheet);
					createCell(excellRow, 6, DataTableValue_C[1][1].toString(), cellStyleStringBoldWithBorder(workbook, true, 10, ping), sheet);
					
					excellRow++;
					
				for (int i = 0; i < 3; i++) {
					createCell(excellRow, 0, label_H[i], cellStyleStringBoldWithBorder(workbook, true, 10, orang), sheet);
					createNumberCell(excellRow, 1, DataTableValue_H[0][i+2].toString(), cellStyleNumberBoldWithBorder(workbook, true, 10, orang), sheet);
					createNumberCell(excellRow, 2, DataTableValue_H[1][i+2].toString(), cellStyleNumberBoldWithBorder(workbook, true, 10, orang), sheet);
					
					createCell(excellRow, 4, label_C[i],  cellStyleStringBoldWithBorder(workbook, true, 10, orang), sheet);
					createNumberCell(excellRow, 5, DataTableValue_C[0][i+2].toString(),  cellStyleNumberBoldWithBorder(workbook, true, 10, orang), sheet);
					createNumberCell(excellRow, 6, DataTableValue_C[1][i+2].toString(),  cellStyleNumberBoldWithBorder(workbook, true, 10, orang), sheet);
					excellRow++;
				}
				
				
				
				createCell(11, 3, DataTableValue_H[2][0].toString(), cellStyleBoldWitholtBorderText(workbook, true, 14, textBlue), sheet);
				excellRow = 13;
				createCell(excellRow, 1, DataTableValue_H[2][1].toString(), cellStyleNumberBoldWithBorder(workbook, true, 10, blue), sheet);
				createCell(excellRow, 2, DataTableValue_H[3][1].toString(), cellStyleNumberBoldWithBorder(workbook, true, 10, blue), sheet);
		
				createCell(excellRow, 5, DataTableValue_C[2][1].toString(), cellStyleNumberBoldWithBorder(workbook, true, 10, blue), sheet);
				createCell(excellRow, 6, DataTableValue_C[3][1].toString(), cellStyleNumberBoldWithBorder(workbook, true, 10, blue), sheet);
				
				excellRow++;
				for (int i = 0; i < 3; i++) {
					createCell(excellRow, 0, label_H[i], cellStyleNumberBoldWithBorder(workbook, true, 10,  orang), sheet);
					createNumberCell(excellRow, 1, DataTableValue_H[2][i+2].toString(), cellStyleNumberBoldWithBorder(workbook, true, 10,  orang), sheet);
					createNumberCell(excellRow, 2, DataTableValue_H[3][i+2].toString(), cellStyleNumberBoldWithBorder(workbook, true, 10,  orang), sheet);
					
					createCell(excellRow, 4, label_C[i], cellStyleNumberBoldWithBorder(workbook, true, 10,  orang), sheet);
					createNumberCell(excellRow, 5, DataTableValue_C[2][i+2].toString(), cellStyleNumberBoldWithBorder(workbook, true, 10,  orang), sheet);
					createNumberCell(excellRow, 6, DataTableValue_C[3][i+2].toString(), cellStyleNumberBoldWithBorder(workbook, true, 10,  orang), sheet);
					excellRow++;
				}
	
//			setBordersToMergedCells(workbook, sheet);

			FileOutputStream outFile = new FileOutputStream(new File(excelFilePath));
			workbook.write(outFile);
			outFile.close();
			round.StopWindow();
			GenerateRequestWordDoc.openWordDoc(excelFilePath);

		} catch (FileNotFoundException e) {
			ResourceLoader.appendToFile(e);
			MessageDialog(e.toString(), "файлова грешка");
			round.StopWindow();
		} catch (IOException e) {
			ResourceLoader.appendToFile(e);
			e.printStackTrace();
		}
	}
	
	private static CellStyle cellStyleNumberBoldWithBorder(Workbook workbook, boolean fl, int size,  short indexColor ) {
		
		
		CellStyle cellStyleHeader;
		Font fontHeader = workbook.createFont();
		DataFormat format = workbook.createDataFormat();
		fontHeader.setFontName("Times New Roman");
		fontHeader.setFontHeightInPoints((short) size);
		fontHeader.setColor(IndexedColors.BLACK.getIndex());
		fontHeader.setBold(fl);
		cellStyleHeader = workbook.createCellStyle();
		cellStyleHeader.setDataFormat(format.getFormat("0.00E+00"));
		cellStyleHeader.setFont(fontHeader);
		cellStyleHeader.setAlignment(HorizontalAlignment.CENTER);
		cellStyleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
//		  HSSFPalette palette = ((HSSFWorkbook) workbook).getCustomPalette();
//		  palette.setColorAtIndex(HSSFColor.HSSFColorPredefined.LIME.getIndex(), rgb[0], rgb[1], rgb[2]);
//		 cellStyleHeader.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIME.getIndex());
//				
		cellStyleHeader.setFillForegroundColor(indexColor);
		cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyleHeader.setWrapText(true);
		setBordrCell(cellStyleHeader);

		return cellStyleHeader;
	}
	
	private static CellStyle cellStyleStringBoldWithBorder(Workbook workbook, boolean fl, int size,  short indexColor  ) {
		
		
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
//		  HSSFPalette palette = ((HSSFWorkbook) workbook).getCustomPalette();
//		  palette.setColorAtIndex(HSSFColor.HSSFColorPredefined.LIME.getIndex(), rgb[0], rgb[1], rgb[2]);
//		 cellStyleHeader.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIME.getIndex());
				
		cellStyleHeader.setFillForegroundColor(indexColor);
		cellStyleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyleHeader.setWrapText(true);
		setBordrCell(cellStyleHeader);

		return cellStyleHeader;
	}
		
	private static void createCell(int counRow, int col, String str, CellStyle cellStyleHeader, Sheet sheet) {
		Cell cell = null;
		Row row = sheet.getRow(counRow);
		cell = row.createCell(col, CellType.STRING);
		cell.setCellStyle(cellStyleHeader);
		cell.setCellValue(str);

	}
	
	private static void createNumberCell(int counRow, int col, String str, CellStyle cellStyleHeader, Sheet sheet) {
		Cell cell = null;
		Row row = sheet.getRow(counRow);
		cell = row.createCell(col, CellType.NUMERIC);
		cell.setCellStyle(cellStyleHeader);
		cell.setCellValue( Double.parseDouble(str));

	}

	private static CellStyle cellStyleBoldWitholtBorder(Workbook workbook, boolean fl, int size) {
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

		return cellStyleHeader;
	}
		
	private static CellStyle cellStyleBoldWitholtBorderText(Workbook workbook, boolean fl, int size, short indexColor ) {
		CellStyle cellStyleHeader;
		Font fontHeader = workbook.createFont();
		fontHeader.setFontName("Times New Roman");
		fontHeader.setFontHeightInPoints((short) size);
		fontHeader.setColor(indexColor);
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
	
	private boolean checkCorectRequest_C(String requestC, String mesec) {
		boolean fl=true;
		Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", requestC);
		List<Sample> list_Sample = SampleDAO.getListSampleFromColumnByVolume("request", choiseRequest);
		for (Sample sample : list_Sample) {
			List<Results> listResultBiSample = ResultsDAO.getListResultsFromCurentSampleInProtokol(sample);
			for (Results result : listResultBiSample) {
					if (result.getMetody().getCode().contains("М.ЛИ-РХ-15") &&
						sample.getPeriod().getValue().equals(mesec) &&
						sample.getRequest_to_obekt_na_izpitvane_request().getObektNaIzp().getName_obekt_na_izpitvane().contains("Вентилационна тръба")){
					
				}else{
					fl = false;
				}
		}
		}
		return fl;
	}
	
	private boolean checkCorectRequest_H(String requestH, String mesec) {
		boolean fl=true;
		Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", requestH);
		List<Sample> list_Sample = SampleDAO.getListSampleFromColumnByVolume("request", choiseRequest);
		for (Sample sample : list_Sample) {
			List<Results> listResultBiSample = ResultsDAO.getListResultsFromCurentSampleInProtokol(sample);
			for (Results result : listResultBiSample) {
				if (result.getMetody().getCode().contains("М.ЛИ-РХ-03") &&
						sample.getPeriod().getValue().equals(mesec) &&
						sample.getRequest_to_obekt_na_izpitvane_request().getObektNaIzp().getName_obekt_na_izpitvane().contains("Вентилационна тръба")){
					
				}else{
					fl = false;
				}
		}
		}
		return fl;
	}

	public static String[] getStringMounth() {
		List<Period> listMounth = PeriodDAO.getInListPeriod_Mesechni();
		String[] strMounth = new String[listMounth.size()];
		int i = 0;
		for (Period period : listMounth) {
			strMounth[i] = period.getValue();
			i++;
		}
		return strMounth;
	}

	public static void MessageDialog(String textInFrame, String textFrame) {
		Icon otherIcon = null;
		JFrame jf = new JFrame();
		jf.setAlwaysOnTop(true);

		JOptionPane.showMessageDialog(jf, textInFrame, textFrame, JOptionPane.PLAIN_MESSAGE, otherIcon);

	}

}

	