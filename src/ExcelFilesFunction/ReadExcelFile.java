package ExcelFilesFunction;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import Aplication.DimensionDAO;
import Aplication.NuclideDAO;
import Aplication.RazmernostiDAO;
import DBase_Class.Results;
import WindowView.ReadGamaFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Created by anirudh on 20/10/14.
 */
public class ReadExcelFile {
	private static List<Destruct_Result> listNuclideDestruct_Result;
	// private static final String FILE_PATH = "d:/123456.xls";

	// public static void main(String args[]) {
	//
	// List<Destruct_Result> destruct_Result_List =
	// getDestruct_Result_ListFromExcelFile();
	//
	// for (Destruct_Result destruct_Result : destruct_Result_List) {
	// System.out.println(getStringDestruct_Result(destruct_Result));
	// }
	//
	// }

	public static String getStringDestruct_Result(Destruct_Result destruct_Result) {

		return destruct_Result.getResult() + " ; " + destruct_Result.getMda() + " ;  " + destruct_Result.getMetod()
				+ "  ; " + destruct_Result.getNuclide() + " ;  " + destruct_Result.getTsi() + " ;  "
				+ destruct_Result.getUncert();
	}

	public static List<Destruct_Result> getDestruct_Result_ListFromExcelFile(String FILE_PATH) {

		DataFormatter formatter = new DataFormatter();
		List<Destruct_Result> destruct_Result_List = new ArrayList<Destruct_Result>();
		FileInputStream fis = null;
		String cod = "", metod = "", nuclide = "", result = "", uncert = "", mda = "", quantity = "", tsi = "",
				dimencion = "";
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
							case "Код":
								cod = valume;
								break;
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
							case "Количество":
								quantity = String.valueOf(cell.getNumericCellValue());
								break;
							case "ТСИ":
								tsi = valume;
								break;
							case "Размерност":
								dimencion = valume;
								break;
							case "end":
								endNuclideRsult = true;
								break;

							}

							if (endNuclideRsult) {
								destruct_Result_List.add(new Destruct_Result(cod, metod, nuclide, result, uncert, mda,
										tsi, quantity, dimencion));
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
			JOptionPane.showMessageDialog(null, "Не е избран excel файл", "Грешни данни", JOptionPane.ERROR_MESSAGE);
		}
		return destruct_Result_List;
	}

	public static String[][] getMasivNuclideFromExcelFile(List<Destruct_Result> list_destruct_Result) {
		String[][] str = new String[list_destruct_Result.size()][9];
		for (int i = 0; i < list_destruct_Result.size(); i++) {
			str[i][0] = list_destruct_Result.get(i).getCod(); // nuclide Cod
			str[i][1] = list_destruct_Result.get(i).getMetod(); // nuclide Metod
			str[i][2] = list_destruct_Result.get(i).getNuclide();// nuclide
																	// simbol
			str[i][3] = list_destruct_Result.get(i).getResult();// nuclide
																// active
			str[i][4] = list_destruct_Result.get(i).getUncert();// nuclide
																// uncertai
			str[i][5] = list_destruct_Result.get(i).getMda(); // nuclide mda
			str[i][6] = list_destruct_Result.get(i).getQuantity(); // nuclide
																	// kolichestwo
			str[i][7] = list_destruct_Result.get(i).getTsi(); // nuclide TSI
			str[i][8] = list_destruct_Result.get(i).getDimencion(); // nuclide
																	// razmernost

		}
		return str;

	}

	public static Results[] getMasivResultsFromExcelFile(List<Destruct_Result> list_destruct_Result,
			List<String> listSimbolBasicNuclide) {
		String[][] masiveActiveResults = getMasivNuclideFromExcelFile(list_destruct_Result);
		List<Results> listResults = new ArrayList<Results>();
		for (int i = 0; i < masiveActiveResults.length; i++) {
			String nuclideResult = masiveActiveResults[i][2];
			for (String nuclideBasic : listSimbolBasicNuclide) {
				if (nuclideResult.equals(nuclideBasic))
					try {
						Results results = new Results();
						results.setNuclide(NuclideDAO.getValueNuclideBySymbol(nuclideResult));
						results.setValue_result(Double.parseDouble(masiveActiveResults[i][3]));
						results.setUncertainty(Double.parseDouble(masiveActiveResults[i][4]));
						results.setMda(Double.parseDouble(masiveActiveResults[i][5]));
						results.setQuantity(Double.parseDouble(masiveActiveResults[i][6]));
						results.setDate_measur("");
						results.setDate_chim_oper("");
						results.setTsi(ReadGamaFile.getTSIObjectFromFileString(masiveActiveResults[i][7]));
						String dim = masiveActiveResults[i][8];
						results.setRtazmernosti(RazmernostiDAO.getValueRazmernostiByName(dim));
						if (dim.indexOf("/") > 0) {
							results.setDimension(DimensionDAO.getValueDimensionByName(dim.replace("", "Bq/")));
						} else {
							results.setDimension(DimensionDAO.getValueDimensionByName(dim.replace("", "Bq")));
						}
						results.setSigma(2);
						listResults.add(results);

					} catch (NumberFormatException e) {
					}
			}
		}

		Results[] masiveResultsnew = new Results[listResults.size()];
		for (int j = 0; j < masiveResultsnew.length; j++) {
			masiveResultsnew[j] = listResults.get(j);
		}

		return masiveResultsnew;
	}
}
