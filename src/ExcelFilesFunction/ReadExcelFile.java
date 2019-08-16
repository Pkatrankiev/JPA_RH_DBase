package ExcelFilesFunction;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by anirudh on 20/10/14.
 */
public class ReadExcelFile {

//	private static final String FILE_PATH = "d:/123456.xls";

//	public static void main(String args[]) {
//
//		List<Destruct_Result> destruct_Result_List = getDestruct_Result_ListFromExcelFile();
//
//		for (Destruct_Result destruct_Result : destruct_Result_List) {
//			System.out.println(getStringDestruct_Result(destruct_Result));
//		}
//
//	}

	
	public static String getStringDestruct_Result(Destruct_Result destruct_Result) {

		return destruct_Result.getResult() + " ; " + destruct_Result.getMda() + " ;  " + destruct_Result.getMetod()
				+ "  ; " + destruct_Result.getNuclide() + " ;  " + destruct_Result.getTsi() + " ;  "
				+ destruct_Result.getUncert();
	}

	
	public static List<Destruct_Result> getDestruct_Result_ListFromExcelFile(String FILE_PATH) {
		
		DataFormatter formatter = new DataFormatter();
		List<Destruct_Result> destruct_Result_List = new ArrayList<Destruct_Result>();
		FileInputStream fis = null;
		String metod = "", nuclide = "", result = "", uncert = "", mda = "", tsi = "";
		String param = "", valume = "";
		try {
			fis = new FileInputStream(FILE_PATH);

			// Using XSSF for xlsx format, for xls use HSSF
			@SuppressWarnings("resource")
			Workbook workbook = new HSSFWorkbook(fis);

			int numberOfSheets = workbook.getNumberOfSheets();

			// looping over each workbook sheet
			for (int i = 0; i < numberOfSheets; i++) {
				Boolean endNuclideRsult = false;
				Sheet sheet = workbook.getSheetAt(i);
				System.out.println(sheet.getLastRowNum());

				Iterator<Row> rowIterator = sheet.iterator();

				// iterating over each row
				while (rowIterator.hasNext()) {

					Row row = (Row) rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();

					// Iterating over each cell (column wise) in a particular
					// row.
					while (cellIterator.hasNext()) {
						
						Cell cell = (Cell) cellIterator.next();
						if (formatter.formatCellValue(cell).equals("#$")) {
							
							cell = (Cell) cellIterator.next();
							param = formatter.formatCellValue(cell);
							
							cell = (Cell) cellIterator.next();
							valume = formatter.formatCellValue(cell);
							
							switch (param) {
							case "Метод":
								metod = valume;
								break;
							case "Нуклид":
								nuclide = valume;
								break;
							case "Резултат":
								result = String.valueOf(cell.getNumericCellValue());
								break;
							case "Неопределеност":
								uncert = String.valueOf(cell.getNumericCellValue());
								break;
							case "МДА":
								mda = String.valueOf(cell.getNumericCellValue());
								break;
							case "ТСИ":
								tsi = valume;
								break;
							case "end":
								endNuclideRsult = true;
								break;

							}
						
						if (endNuclideRsult) {
							destruct_Result_List.add(new Destruct_Result(metod, nuclide, result, uncert, mda, tsi));
							endNuclideRsult = false;
						}
					}
					}

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destruct_Result_List;
	}

}

