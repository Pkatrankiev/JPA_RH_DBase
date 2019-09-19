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
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;


public class ReadExcelFile {

	private static String cod_sample;
	
	public static String getCod_sample() {
		return cod_sample;
	}

	public static String getStringDestruct_Result(Destruct_Result destruct_Result) {

		return destruct_Result.getResult() + " ; " + destruct_Result.getMda() + " ;  " + destruct_Result.getMetod()
				+ "  ; " + destruct_Result.getNuclide() + " ;  " + destruct_Result.getTsi() + " ;  "
				+ destruct_Result.getUncert();
	}

	public static List<Destruct_Result> getDestruct_Result_ListFromExcelFile(String FILE_PATH) {

		DataFormatter formatter = new DataFormatter();
		List<Destruct_Result> destruct_Result_List = new ArrayList<Destruct_Result>();
		FileInputStream fis = null;
		String metod = "", nuclide = "", result = "", uncert = "", mda = "", quantity = "", tsi = "",
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
								cod_sample = cell.getStringCellValue();
								break;
							case "Метод":
								metod = valume;
								break;
							case "Нуклид":
								nuclide = valume;
								break;
							case "Резултат":
								result = String.valueOf(cell.getNumericCellValue());
								result = NumberFormatWithRounding(result);
								break;
							case "Неопределеност":
								uncert = String.valueOf(cell.getNumericCellValue());
								uncert = NumberFormatWithRounding(uncert);
								break;
							case "МДА":
								mda = String.valueOf(cell.getNumericCellValue());
								mda = NumberFormatWithRounding(mda);
								break;
							case "Количество":
								quantity = String.valueOf(cell.getNumericCellValue());
								quantity = NumberFormatWithRounding(quantity);
								break;
							case "ТСИ":
								tsi = valume;
								break;
							case "Размерност":
								dimencion = cell.getStringCellValue();
								break;
							case "end":
								endNuclideRsult = true;
								break;

							}

							if (endNuclideRsult) {
								double dub_MDA = Double.valueOf(mda);
								double dub_result = Double.valueOf(result);
								if(dub_MDA > dub_result) {
									result = "0.0";
									uncert = "0.0";
								}
								destruct_Result_List.add(new Destruct_Result(cod_sample, metod, nuclide, result, uncert, mda,
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
			String nuclideResult = masiveActiveResults[i][2].trim();
			for (String nuclideBasic : listSimbolBasicNuclide) {
				if (nuclideResult.equals(nuclideBasic)) {
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
							results.setDimension(DimensionDAO.getValueDimensionByName(dim.replace("Bq/","")));
						} else {
							results.setDimension(DimensionDAO.getValueDimensionByName(dim.replace("Bq","")));
						}
						results.setSigma(2);
						
						listResults.add(results);

					} catch (NumberFormatException e) {
					}
			}
			}
		}
			Results[] masiveResultsnew = new Results[listResults.size()];
		for (int j = 0; j < listResults.size(); j++) {
			masiveResultsnew[j] = listResults.get(j);
		}
		return masiveResultsnew;
	}
	
	public static String NumberFormatWithRounding(String num) {
		String formatNum;
		String head = num.substring(0, num.indexOf("."));
		if( Integer.parseInt(head)==0){
		String body = num.substring(num.indexOf(".")+1);
		while (body.substring(0,1).equals("0")) {
			body = body.substring(1);
		}
		if(body.length()>5){
			body = body.substring(0,5);
		}
		num = num.substring(0,num.indexOf(body)+body.length());
		formatNum = num;
		}else{
		Double boubVal = Double.parseDouble(num);
		 DecimalFormat df = new DecimalFormat("#.####");
		    df.setRoundingMode(RoundingMode.HALF_UP);
		    formatNum =df.format(boubVal);
		
		}
		return formatNum.replaceAll(",",".");
	}
}
