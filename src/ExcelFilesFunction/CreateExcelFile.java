package ExcelFilesFunction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import CreateWordDocProtocol.GenerateRequestWordDoc;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Units;

import DefaultTableList.TableList_Functions;
import GlobalVariable.GlobalPathForDocFile;

public class CreateExcelFile {

	public static void toExcel_old(JTable table) {
		TableColumnModel tcm = table.getColumnModel();
		try {
			Writer excel = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("Some2 name.xls"), "cp1251" + "" + ""));

			for (int i = 0; i < table.getColumnCount(); i++) {
				if (tcm.getColumn(i).getWidth() > 0)
					excel.write(TableList_Functions.reformatString(table.getColumnName(i)) + "\t");
			}

			excel.write("\n");

			for (int row = 0; row < table.getRowCount(); row++) {
				for (int j = 0; j < table.getColumnCount(); j++) {
					if (tcm.getColumn(j).getWidth() > 0) {
						String volue = table.getModel()
								.getValueAt(table.convertRowIndexToModel(row), table.convertColumnIndexToModel(j))
								.toString();
						excel.write(volue.replaceAll("\n", "") + "\t");
					}
				}

				excel.write("\n");

			}

			excel.close();

		} catch (FileNotFoundException e) {
			MessageDialog(e.toString(), "файлова грешка");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void toExcel_exper() {
		
		String excelFilePath = "home.xls";
		Object[] masive1 = {"fffffffff2","assdry","dsfsdf","wwwwwwww"};
		
Object[][] masive = {{1,"ass",123.23,true},
					{2,"асве",123.23,false},
					{22,"кл;лук",123.23,true},
					{34,"assdf",123.23,true},
					{88,"дфсeeeeeeвеф",123.23,true},
								};

		
		try {
			Workbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet();
			
			Row row ;
			Cell cell = null;
			
			int rowCount = 0;
			int columnCount=0;
			
			CellStyle cellStyle = workbook.createCellStyle();
			
			DataFormat format = workbook.createDataFormat();
			
			// Create header CellStyle
			  Font headerFont = workbook.createFont();
			  headerFont.setColor(IndexedColors.WHITE.index);
			  CellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
			  // fill foreground color ...
			  headerCellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
			  // and solid fill pattern produces solid grey cell fill
			  headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			  headerCellStyle.setFont(headerFont);
			
			
			
			cellStyle.setDataFormat(format.getFormat("0.000E+00"));
			cellStyle.setAlignment(HorizontalAlignment.CENTER);			
			cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			
			
			Font font= workbook.createFont();
		    font.setFontHeightInPoints((short)10);
		    font.setFontName("Arial");
		    font.setColor(IndexedColors.RED.getIndex());
		
		    font.setBold(true);
		    font.setItalic(false);
		    
		    int widthExcel = 10;
		    int width256 = (int)Math.round((widthExcel*Units.DEFAULT_CHARACTER_WIDTH+5f)/Units.DEFAULT_CHARACTER_WIDTH*256f);
		    System.out.println(width256);
		    sheet.setColumnWidth(0, width256);
		    
		    
		    
		    
		    cellStyle.setFont(font);
		    row = sheet.createRow(0);
		    
		    for (int i = 0; i < masive1.length; i++) {
				
		    	cell = row.createCell(i, CellType.STRING);
		    	 setBordrCell ( cell, workbook);
	        	cell.setCellValue((String) masive1[i]);
	        	 sheet.autoSizeColumn(i);
	        	 sheet.setColumnWidth(i,sheet.getColumnWidth(i)+500);
		    }
		    rowCount = 1;
			for (int rowTable = 1; rowTable < masive.length; rowTable++) {
				row = sheet.createRow(rowCount);
				columnCount = 0;
				for (int j = 0; j < masive[rowTable].length; j++) {
					
					switch ( masive[rowTable][j].getClass().getSimpleName() ){
				        case "Boolean":{
				        	cell = row.createCell(columnCount, CellType.BOOLEAN);
				        	cell.setCellValue((Boolean) masive[rowTable][j]);
				        	break;
				        }
				        case "Integer":{
				        	cell = row.createCell(columnCount, CellType.NUMERIC);
				        	cell.setCellValue((Integer) masive[rowTable][j]);
				            break;
				        }
				        case "String":{
				        	cell = row.createCell(columnCount, CellType.STRING);
				        	cell.setCellValue((String) masive[rowTable][j]);
				            break;
				        }
				        case "Double":{
				        	cell = row.createCell(columnCount, CellType.NUMERIC);
				        	cell.setCellStyle(cellStyle);
				        	cell.setCellValue((Double) masive[rowTable][j]);
				            break;
				        }
					
				}
					columnCount++;
					
			}
				rowCount++;
			}
			
			sheet.createFreezePane(0, 1);
			sheet.setAutoFilter(new CellRangeAddress ( 0, 1, 0, columnCount-1 ));
		
			FileOutputStream outFile = new FileOutputStream(new File(excelFilePath));
			workbook.write(outFile);
			outFile.close();
			
			GenerateRequestWordDoc.openWordDoc(excelFilePath);

		} catch (FileNotFoundException e) {
			MessageDialog(e.toString(), "файлова грешка");
		} catch (IOException e) {
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
	
	
	
	public static  CellStyle insertBorder (CellStyle style){
	    
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

	public static void toExcel_original(JTable table) {

		try {
			
			File file = new File("D:\\Book41.xls");
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
			
			TableColumnModel tcm = table.getColumnModel();
//			@SuppressWarnings("resource")
//			Workbook workbook = new HSSFWorkbook();
//			Sheet sheet = workbook.createSheet("1");
			Row row = sheet.getRow(0);
			Cell cell = null;
			
			int columnCount = 0;
			for (int i = 0; i < table.getColumnCount(); i++) {
				if (tcm.getColumn(i).getWidth() > 0)
					cell = row.getCell(columnCount);
				cell.setCellValue(TableList_Functions.reformatString(table.getColumnName(i)));
				columnCount++;
			}
			for (int i = columnCount; i < row.getLastCellNum(); i++) {
				System.out.println(i);
				cell = row.getCell(i);
				row.removeCell(cell);
			}
			
			
			int rowCount = 1;
			for (int rowTable = 0; rowTable < table.getRowCount(); rowTable++) {
				row = sheet.createRow(rowCount);
				columnCount = 0;
				for (int j = 0; j < table.getColumnCount(); j++) {
					if (tcm.getColumn(j).getWidth() > 0) {
						String volue = table.getModel()
								.getValueAt(table.convertRowIndexToModel(rowTable), table.convertColumnIndexToModel(j))
								.toString();

						cell = row.createCell(columnCount);
						cell.setCellValue(volue);
						columnCount++;
					}
				}
				rowCount++;
			}

			// for (Object[] aBook : bookData) {
			// Row row = sheet.createRow(rowCount);
			// ++rowCount;
			//
			// int columnCount = 0;
			//
			// for (Object field : aBook) {
			//
			// System.out.println(rowCount+" - "+columnCount+" - "+field);
			//// Cell cell = sheet.createRow(rowCount).createCell(columnCount);
			//// cell.setCellValue((String) field);
			// if (field instanceof String) {
			// System.out.println("теьт-"+field);
			// Cell cell = row.createCell(columnCount);
			// cell.setCellValue((String) field);
			// }
			// if (field instanceof Integer) {
			// System.out.println("инт-"+field);
			// Cell cell = row.createCell(columnCount);
			// cell.setCellValue((Integer) field);
			// }
			// ++columnCount;
			//
			// }

			FileOutputStream outFile = new FileOutputStream(new File("D:\\home.xls"));
			workbook.write(outFile);
			outFile.close();

		} catch (FileNotFoundException e) {
			MessageDialog(e.toString(), "файлова грешка");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void toExcel(String tableTypeColumn[], JTable table, String sheetName, String[][] masiveExtendLamels, int[] columnWith) {
		String excelFilePath =GlobalPathForDocFile.get_destinationDir() + "export.xls";
		try {
			TableColumnModel tcm = table.getColumnModel();
			
			Workbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet(sheetName);
			
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
			SimpleDateFormat formatter =new SimpleDateFormat("yyyy.MM.dd"); 
			SimpleDateFormat formatterTime =new SimpleDateFormat("dd.MM.yyyy hh:mm");
			
		    CellStyle cellStyleData = workbook.createCellStyle();
			short df = workbook.createDataFormat().getFormat("dd.MM.yyyy");
			cellStyleData.setDataFormat(df);
			
		 
			CellStyle cellStyleDataTime = workbook.createCellStyle();
			short dtf = workbook.createDataFormat().getFormat("dd.MM.yyyy hh:mm");
			cellStyleDataTime.setDataFormat( dtf);
			
			CreationHelper createHelper = workbook.getCreationHelper();
			cellStyleData.setDataFormat(
			    createHelper.createDataFormat().getFormat("dd.MM.yyyy"));
	
			
//			Create header column
			Row row = sheet.createRow(0);
			
			int excelColumnCount = 0;
			for (int tableColumCount = 0; tableColumCount < table.getColumnCount(); tableColumCount++) {
				if (tcm.getColumn(tableColumCount).getWidth() > 0){
					cell = row.createCell(excelColumnCount, CellType.STRING);
					cell.setCellStyle(cellStyleHeader);
				cell.setCellValue(TableList_Functions.reformatString(table.getColumnName(tableColumCount)));
				 sheet.autoSizeColumn(excelColumnCount);
	        	 sheet.setColumnWidth(excelColumnCount,sheet.getColumnWidth(excelColumnCount)+1000);
	        	 if(columnWith!= null&& excelColumnCount<columnWith.length){
	        		 sheet.setColumnWidth(excelColumnCount,columnWith[excelColumnCount]*37); 
	        	 }
				excelColumnCount++;
				}
			}
						
//			Create column	
			DataFormat format = workbook.createDataFormat();
			CellStyle cellStyleDouble = workbook.createCellStyle();
			cellStyleDouble.setDataFormat(format.getFormat("0.00E+00"));
			int rowCount = 1;
			for (int rowTable = 0; rowTable < table.getRowCount(); rowTable++) {
				row = sheet.createRow(rowCount);
				excelColumnCount = 0;
				for (int j = 0; j < table.getColumnCount(); j++) {
					if (tcm.getColumn(j).getWidth() > 0) {
						Object volue = table.getModel()
								.getValueAt(table.convertRowIndexToModel(rowTable), table.convertColumnIndexToModel(j));
							
                     System.out.println(tableTypeColumn[j]+" - "+volue);

						switch ( tableTypeColumn[j] ){
				        case "Boolean_Check":
				        	cell = row.createCell(excelColumnCount, CellType.BOOLEAN);
				        	cell.setCellValue((Boolean) volue);
				        	break;
				        
				        case "code_Request":
				        case "count_Simple":
				        case "code_Sample":
				        case "Integer":
				        	
				        	cell = row.createCell(excelColumnCount, CellType.NUMERIC);
				        	cell.setCellValue((Integer) Integer.parseInt(volue.toString()));
				            break;
				       
				        case "DatePicker":
				        case "DatePicker_Dual":
				        	
				        	
				        	cell = row.createCell(excelColumnCount);
				        	cell.setCellStyle(cellStyleData);
				        	try {
								cell.setCellValue(formatter.parse(volue.toString()));
							} catch (ParseException e) {
								
								e.printStackTrace();
							}
				        	
				             break;
				            
				        case "Date-TimePicker":
				       		        
				        	cell = row.createCell(excelColumnCount);
				        	cell.setCellStyle(cellStyleDataTime);
				        	try {
								cell.setCellValue(formatterTime.parse(volue.toString()));
							} catch (ParseException e) {
								cell.setCellValue(volue.toString());
							}
				        	
				            break;
				       
				        case "Double":
				        case "Dobiv":
				        	cell = row.createCell(excelColumnCount, CellType.NUMERIC);
				        	cell.setCellStyle(cellStyleDouble);
				        	cell.setCellValue((Double) volue);
				            break;
				        
				        default:
				           	cell = row.createCell(excelColumnCount, CellType.STRING);
				        	cell.setCellValue((String) volue.toString());
				            break;
				        
				     
					
				}
						setBordrCell ( cell,  workbook);
						excelColumnCount++;
					}
				}
				rowCount++;
			}
			
			
			
			
			sheet.createFreezePane(0, 1);
			sheet.setAutoFilter(new CellRangeAddress ( 0, 1, 0, excelColumnCount-1 ));
			

		if(masiveExtendLamels!=null){
			rowCount++;
			for (int rowFromMasive = 0; rowFromMasive < masiveExtendLamels.length; rowFromMasive++) {
				row = sheet.createRow(rowCount);
				excelColumnCount = 0;
				for (int columnFromMasive = 0; columnFromMasive < masiveExtendLamels[0].length; columnFromMasive++) {
					cell = row.createCell(excelColumnCount, CellType.STRING);
				
		        	cell.setCellValue((String) masiveExtendLamels[rowFromMasive][columnFromMasive].toString());
		        	if(rowFromMasive==0 && columnWith!= null ){
		        		cell.setCellStyle(cellStyleHeader);
		        	}else{
		        	setBordrCell ( cell,  workbook);
		        	}
		        	excelColumnCount++;
					}
				rowCount++;
			}
		}	
			
			
			
			FileOutputStream outFile = new FileOutputStream(new File(excelFilePath));
			workbook.write(outFile);
			outFile.close();

			GenerateRequestWordDoc.openWordDoc(excelFilePath);
			
		} catch (FileNotFoundException e) {
			MessageDialog(e.toString(), "файлова грешка");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	

	public static void MessageDialog(String textInFrame, String textFrame) {
		Icon otherIcon = null;
		JFrame jf = new JFrame();
		jf.setAlwaysOnTop(true);

		JOptionPane.showMessageDialog(jf, textInFrame, textFrame, JOptionPane.PLAIN_MESSAGE, otherIcon);

	}

}
