package ExcelFilesFunction;

import org.apache.poi.hssf.OldExcelFormatException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import AddDobivViewFunction.OverallVariablesAddDobiv;
import AddResultViewFunction.OverallVariablesAddResults;
import AddResultViewFunction.SampleCodeSection;
import Aplication.DimensionDAO;
import Aplication.MetodyDAO;
import Aplication.NuclideDAO;
import Aplication.RazmernostiDAO;
import Aplication.TSI_DAO;
import Aplication.UsersDAO;
import DBase_Class.Dobiv;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.Metody;
import DBase_Class.Razmernosti;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.TSI;
import DBase_Class.Users;
import GlobalVariable.GlobalFormatDate;
import WindowView.ReadGamaFile;
import WindowView.RequestViewFunction;

import java.awt.Choice;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

public class ReadExcelFile {

	private static String cod_sample;
	private static String user_Analize = "";
	private static String value_Standatd = "";
	private static String nuclide_StandardStr = "";
	private static String nuclideSimbol_StandardStr = "";

	// private static String nuclide_Standard;
	public static String getCod_sample() {
		return cod_sample;
	}

	public static String getStringDestruct_Result(Destruct_Result destruct_Result) {

		return destruct_Result.getResult() + " ; " + destruct_Result.getMda() + " ;  " + destruct_Result.getMetod()
				+ "  ; " + destruct_Result.getNuclide() + " ;  " + destruct_Result.getTsi() + " ;  "
				+ destruct_Result.getUncert();
	}

	public static List<Destruct_Result> getDestruct_Result_ListFromOrtecExcelFile(String FILE_PATH,
			Boolean forResults) {
		DataFormatter formatter = new DataFormatter();
		List<Destruct_Result> destruct_Result_List = new ArrayList<Destruct_Result>();
		FileInputStream fis = null;
		String metod = "", nuclide = "", result = "", uncert = "", mda = "", quantity = "", tsi = "", dimencion = "";
		String date_Analize = "";
	
		try {
			fis = new FileInputStream(FILE_PATH);

			// Using XSSF for xlsx format, for xls use HSSF


			try {	
			@SuppressWarnings("resource")
			Workbook workbook = new HSSFWorkbook(fis);
			
			Sheet sheet = workbook.getSheetAt(0);
			user_Analize = "";
			metod = "М.ЛИ-РХ-01";
			tsi = "T04";
			String textNulCell = "";
			int rowAcrivitiNuclide = 0;

			Cell cell;
			for (int row = 0; row <= sheet.getLastRowNum(); row += 1) {
				System.out.println(row);
				if (sheet.getRow(row) != null){
				cell = sheet.getRow(row).getCell(0);

				if (CellNOEmpty(cell)) {

					textNulCell = cell.getStringCellValue();
					System.out.println(row + "  -  " + textNulCell);

					if (!textNulCell.isEmpty() && (textNulCell.equals("Analyst: ORTEC")||textNulCell.equals("AlphaVision v5.3"))) {
						for (int col = 1; col <= 20; col += 1) {
							cell = sheet.getRow(row).getCell(col);
							if (CellNOEmpty(cell) && (cell.getStringCellValue().indexOf("ã.") > 0 || cell.getStringCellValue().indexOf("г.")>0)) {
								System.out.println("  - ***************"+cell.getStringCellValue());
								date_Analize = cell.getStringCellValue().replace("/", ".").replace("ã.", "").replace("г.", ""); 
							}
						}
					}
					if (!textNulCell.isEmpty() && textNulCell.startsWith("Sample:")) {
						cod_sample = textNulCell.replaceFirst("Sample:", "");
						for (int col = 1; col <= 20; col += 1) {
							cell = sheet.getRow(row).getCell(col);
							if (CellNOEmpty(cell) && (cell.getStringCellValue().startsWith("Sample Volume :")||
									cell.getStringCellValue().startsWith("Sample Weight :"))) {
								quantity = sheet.getRow(row).getCell(col).getStringCellValue()
										.replaceFirst("Sample Volume :", "").replaceFirst("Sample Weight :", "").replace(",", ".");
								System.out.println("  - "+quantity);
							}
						}
					}
					if (!textNulCell.isEmpty() && textNulCell.contains("Tracer Name:")) {
						
						for (int col = 1; col <= 20; col += 1) {
							cell = sheet.getRow(row).getCell(col);
							if (CellNOEmpty(cell) && cell.getStringCellValue().startsWith("Tracer Nuclide:")) {
								nuclide_StandardStr = sheet.getRow(row).getCell(col).getStringCellValue()
										.replace("Tracer Nuclide:", "").trim();
								nuclideSimbol_StandardStr = reformatNuclideSimbol(nuclide_StandardStr);
								nuclide_StandardStr = nuclide_StandardStr.substring(0,
										nuclide_StandardStr.indexOf("-"));
								
							}
						}
					}
					if (!textNulCell.isEmpty() && textNulCell.contains("Tracer Activity:")) {
						for (int col = 1; col <= 20; col += 1) {
							cell = sheet.getRow(row).getCell(col);
							if (CellNOEmpty(cell) && cell.getStringCellValue().startsWith("Tracer Recovery:")) {
								value_Standatd = sheet.getRow(row).getCell(col).getStringCellValue()
										.replace("Tracer Recovery:", "").replace(",", ".").replace("%", "");
								value_Standatd = value_Standatd.substring(0, 7);
								
							}
						}
					}

					if (!textNulCell.isEmpty() && textNulCell.contains("Nuclide")) {
						dimencion = getStringInCell(sheet.getRow(row).getCell(10)).replace("^", "");
						rowAcrivitiNuclide = row;
						row = sheet.getLastRowNum();
					}

				}

			}
			}
			int row = rowAcrivitiNuclide;
			int col = 3;
			while (sheet.getRow(row).getCell(col) != null && sheet.getRow(row).getCell(col) .getCellType() != CellType.BLANK) {
				col++;
					}
			col--;
			
			for (int i = rowAcrivitiNuclide + 2; i <= sheet.getLastRowNum(); i += 1) {
				
				Row row2 = sheet.getRow(i);

				if (row2 != null ) {
					if(!(formatter.formatCellValue(sheet.getRow(i).getCell(0))).isEmpty()){
					nuclide = getStringInCell(sheet.getRow(i).getCell(0));
					nuclide = reformatNuclideSimbol(nuclide);

					result = getStringInCell(sheet.getRow(i).getCell(col-3));
					double dub_result = Double.valueOf(result);
					uncert = getStringInCell(sheet.getRow(i).getCell(col-2));
					mda = getStringInCell(sheet.getRow(i).getCell(col));
					double dub_MDA = Double.valueOf(mda);
					if (forResults) {
						if (dub_MDA > dub_result) {
							result = "0.0";
							uncert = "0.0";
						}
					
					
				}
				
				
				if(!nuclideSimbol_StandardStr.equals(nuclide))
				destruct_Result_List.add(new Destruct_Result(cod_sample, metod, nuclide, result, value_Standatd, uncert, mda, tsi,
						quantity, dimencion, date_Analize, user_Analize));
				}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (OldExcelFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Excel файлът трябва да е версия 97/2000/XP/2003", "Грешни данни", JOptionPane.ERROR_MESSAGE);
		}
		
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Не е избран excel файл", "Грешни данни", JOptionPane.ERROR_MESSAGE);
		}

		return destruct_Result_List;
	}
	
	
	private static String getStringInCell(Cell cell){
		
		if(cell.getCellType()==CellType.NUMERIC){
			return String.valueOf(cell.getNumericCellValue());
		}
		
		return cell.getStringCellValue();
		
	}

	private static String reformatNuclideSimbol(String nuclide) {
		int index = nuclide.indexOf("-");
		nuclide = nuclide.substring(index + 1) + nuclide.substring(0, index);
		return nuclide;
	}

	public static boolean CellNOEmpty(Cell cell) {
		return cell != null && cell.getCellType() != CellType.BLANK;
	}

	public static Dobiv getDobivFromOrtecExcelFile(List<Destruct_Result> destruct_Result_List, Choice choiceSmplCode,
			String selectMetodStr) {
		Destruct_Result destruct_Result = destruct_Result_List.get(0);
		Dobiv dobiv = new Dobiv();
		Sample sample = SampleCodeSection.getSampleObjectFromChoiceSampleCode(choiceSmplCode);
		Izpitvan_produkt izpitProd = sample.getRequest().getIzpitvan_produkt();
		dobiv.setCode_Standart(nuclideSimbol_StandardStr +" "+ cod_sample);
		dobiv.setMetody((Metody) MetodyDAO.getValueList_MetodyByCode(selectMetodStr));
		dobiv.setIzpitvan_produkt(izpitProd);
		dobiv.setDescription(sample.getRequest_to_obekt_na_izpitvane_request().getObektNaIzp().getSimple_Name() + ", "
				+ sample.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane());
		dobiv.setNuclide(NuclideDAO.getValueNuclideBySymbol(nuclideSimbol_StandardStr));
		dobiv.setValue_result(Double.parseDouble(value_Standatd));
		dobiv.setUncertainty(0.0);
		dobiv.setTsi(TSI_DAO.getValueTSIByName(destruct_Result.getTsi()));
		dobiv.setDate_measur(destruct_Result.getDate_Analize());
		dobiv.setDate_redac(RequestViewFunction.DateNaw(false));
		dobiv.setUser_redac(OverallVariablesAddDobiv.getUser_Redac());

		return dobiv;
	}

	public static List<Destruct_Result> getDestruct_Result_ListMDAAlfaExcelFile(String FILE_PATH, Boolean forResults) {
		DataFormatter formatter = new DataFormatter();
		List<Destruct_Result> destruct_Result_List = new ArrayList<Destruct_Result>();
		FileInputStream fis = null;
		String metod = "", nuclide = "", result = "", uncert = "", mda = "", quantity = "", tsi = "", dimencion = "";
		String date_Analize = "", cellValue = "";

		try {
			fis = new FileInputStream(FILE_PATH);

			// Using XSSF for xlsx format, for xls use HSSF
			@SuppressWarnings("resource")
			Workbook workbook = new HSSFWorkbook(fis);

			Sheet sheet = workbook.getSheetAt(0);

			metod = "М.ЛИ-РХ-01";

			tsi = "T04";

			date_Analize = "01.01.2020";

			user_Analize = "";

			for (int i = 1; i <= sheet.getLastRowNum(); i += 2) {
				for (int colum = 1; i <= 30; i += 1) {
					if (!formatter.formatCellValue(sheet.getRow(i).getCell(colum)).isEmpty()) {

						cellValue = sheet.getRow(i).getCell(colum).getStringCellValue();
						if (cellValue.equals("Код на пробата")) {
							cod_sample = sheet.getRow(i).getCell(colum + 1).getStringCellValue();
						}
						if (cellValue.equals("Количество")) {
							quantity = sheet.getRow(i).getCell(colum + 1).getStringCellValue();
						}
						if (colum > 22) {

							if (cellValue.equals("Активност на алфа-изотоп")) {
								dimencion = sheet.getRow(i).getCell(colum + 1).getStringCellValue();
							}
							if (cellValue.equals("-")) {

								nuclide = sheet.getRow(i).getCell(colum).getStringCellValue();
								nuclide = reformatNuclideSimbol(nuclide);

								result = sheet.getRow(i + 1).getCell(colum).getStringCellValue();
								double dub_result = Double.valueOf(result);
								uncert = sheet.getRow(i + 2).getCell(colum).getStringCellValue();
								mda = sheet.getRow(i + 3).getCell(colum).getStringCellValue().replace("<", "");
								double dub_MDA = Double.valueOf(mda);
								if (forResults) {
									if (dub_MDA > dub_result) {
										result = "0.0";
										uncert = "0.0";
									}
								}
							}

						}
					}
					System.out.println(cod_sample + " - " + metod + " - " + nuclide + " - " + result + " - " + uncert
							+ " - " + mda + " - " + tsi + " - " + quantity + " - " + dimencion + " - " + date_Analize
							+ " - " + user_Analize);
					destruct_Result_List.add(new Destruct_Result(cod_sample, metod, nuclide, result, "", uncert, mda,
							tsi, quantity, dimencion, date_Analize, user_Analize));

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

	public static List<Destruct_Result> getDestruct_Result_ListFromExcelFile(String FILE_PATH, Boolean forResults) {

		DataFormatter formatter = new DataFormatter();
		List<Destruct_Result> destruct_Result_List = new ArrayList<Destruct_Result>();
		FileInputStream fis = null;
		String metod = "", nuclide = "", result = "", uncert = "", mda = "", quantity = "", tsi = "", dimencion = "";
		String param = "", date_Analize = "", dobiv = "";
		String errString ="";
		SimpleDateFormat sdf = new SimpleDateFormat(GlobalFormatDate.getFORMAT_DATE());
		List<String> listSimbolBasikNuclideToMetod = new ArrayList<String>();
		Date dateNull = null;
		try {
			dateNull = sdf.parse("01.01.1910");
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		try {
			fis = new FileInputStream(FILE_PATH);

			// Using XSSF for xlsx format, for xls use HSSF
			@SuppressWarnings("resource")
			Workbook workbook = new HSSFWorkbook(fis);

			int numberOfSheets = workbook.getNumberOfSheets();

			// looping over each workbook sheet
			String errKod = "", errMetod = "", errNuclide = "", errRezultat = "", errNeopred = "", errMDA = "",
					errKolichestvo = "", errDobiv = "", errTSI = "", errRazmer = "", errData = "",
					errIzvurshiAnaliza = "";
			String dobiv1 = "";
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
							// valume = formatter.formatCellValue(cell);

							switch (param) {
							case "Код":
								try {
									cod_sample = cell.getStringCellValue();
								} catch (IllegalStateException e) {
									errKod = "Код -> не е коректен текст\n";
									cod_sample = "_";
								}
								break;
							case "Метод":
								try {
									metod = cell.getStringCellValue();
								} catch (IllegalStateException e) {
									errMetod = "Метод -> не е коректен текст\n";
									metod = "";
								}

								break;
							case "Нуклид":
								try {
									nuclide = cell.getStringCellValue();
									errNuclide = "Нуклид " + nuclide + " не е в избрания метод\n";
									if (forResults) {
										listSimbolBasikNuclideToMetod =  OverallVariablesAddResults
												.getListSimbolBasikNulideToMetod();
									}else {
										listSimbolBasikNuclideToMetod =  OverallVariablesAddDobiv.getListSimbolBasikNulide ();	
									}
									for (String basicNuklide : listSimbolBasikNuclideToMetod) {
										System.out.println(basicNuklide+" ****************************************");
										if (basicNuklide.equals(nuclide)) {
											errNuclide = "";
										}
									}
								} catch (IllegalStateException e) {
									errNuclide = "Нуклид -> не е коректен текст\n";
									nuclide = " ";
								}

								break;
							case "Резултат":
								try {
									result = String.valueOf(cell.getNumericCellValue());
									result = ReformatDoubleTo4decimalExponet(result);
								} catch (IllegalStateException e) {
									errRezultat = "Резултат -> не е число\n";
									result = "0.0";
								}
								break;
							case "Неопределеност":
								try {
									uncert = String.valueOf(cell.getNumericCellValue());
									uncert = ReformatDoubleTo4decimalExponet(uncert);
								} catch (IllegalStateException e) {
									errNeopred = "Неопределеност -> не е число\n";
									uncert = "0.0";
								}
								break;
							case "МДА":
								try {
									mda = String.valueOf(cell.getNumericCellValue());
									mda = ReformatDoubleTo4decimalExponet(mda);
								} catch (IllegalStateException e) {
									errMDA = "МДА -> не е число\n";
									mda = "0.0";
								}
								break;
							case "Количество":
								try {
									quantity = String.valueOf(cell.getNumericCellValue());
									quantity = ReformatDoubleTo4decimalExponet(quantity);
								} catch (IllegalStateException e) {
									errKolichestvo = "Количество -> не е число\n";
									quantity = "0.0";
								}

								quantity = ReformatDoubleTo4decimalExponet(quantity);
								break;
							case "Добив":
								try {
									 dobiv1 = String.valueOf(cell.getNumericCellValue());
									dobiv = ReformatDoubleTo4decimalExponet(dobiv1);
								} catch (IllegalStateException e) {
									errDobiv = "Добив -> не е число\n";
									dobiv = "0.0";
								}
								break;
							case "ТСИ":
								try {
									tsi = cell.getStringCellValue();
									errTSI = "ТСИ " + tsi + " не е в списъка\n";
									String str = tsi.replaceAll("[TtAaТтАа/]", "");
									for (TSI basicTSI : TSI_DAO.getListAllValueTSI()) {

										if (basicTSI.getName().contains(str)) {
											errTSI = "";
										}
									}
									if (!errTSI.isEmpty()) {
										tsi = "    ";
									}
								} catch (IllegalStateException e) {
									errTSI = "ТСИ -> не е коректен текст\n";
									tsi = "    ";
								}
								break;
							case "Размерност":
								try {
									dimencion = cell.getStringCellValue();
									errRazmer = "Размерност " + dimencion + " не е в списъка\n";
									for (Razmernosti basicRazmernosti : RazmernostiDAO.getInListAllValueRazmernosti()) {

										if (basicRazmernosti.getName_razmernosti().contains(dimencion)) {
											errRazmer = "";
										}
									}
								} catch (IllegalStateException e) {
									errRazmer = "Размерност -> не е коректен текст\n";
									dimencion = " ";
								}
								break;
							case "Дата на анализа":
								try {

									if (cell.getDateCellValue().after(dateNull)) {

										date_Analize = sdf.format(cell.getDateCellValue());
									}
								} catch (IllegalStateException e) {
									errData = "Дата на анализа -> не е коректна дата\n";
								}
								break;
							case "Извършил анализа":
								try {
									user_Analize = cell.getStringCellValue();
									errIzvurshiAnaliza = "Извършил анализа: " + user_Analize + " не е в списъка\n";
									for (String basicUser : UsersDAO.getMasiveStringAllName_FamilyUsers()) {
										basicUser = basicUser.substring(basicUser.indexOf(" ") + 1);
										System.out.println(basicUser);
										if (user_Analize.contains(basicUser)) {
											errIzvurshiAnaliza = "";
										}
									}
								} catch (IllegalStateException e) {
									errIzvurshiAnaliza = "Извършил анализа -> не е коректен текст\n";
									user_Analize = "";
								}
								break;
							case "end":
								endNuclideRsult = true;
								break;

							}

							if (endNuclideRsult) {
								double dub_MDA = Double.valueOf(mda);
								double dub_result = Double.valueOf(result);
								if (forResults) {
									if (dub_MDA > dub_result) {
										result = "0.0";
										uncert = "0.0";
									}
								}
								destruct_Result_List.add(new Destruct_Result(cod_sample, metod, nuclide, result, dobiv,
										uncert, mda, tsi, quantity, dimencion, date_Analize, user_Analize));
								System.out.println(cod_sample+" "+metod+" "+ nuclide+" "+ result+" "+ dobiv+" "+
										uncert+" "+mda+" "+tsi+" "+ quantity+" "+ dimencion+" "+ date_Analize+" "+ user_Analize+"  "+ dobiv1);
								endNuclideRsult = false;
							}
						}
					}

				}
			}

			 errString = errKod + errMetod + errNuclide + errRezultat + errNeopred + errMDA + errKolichestvo
					+ errDobiv + errTSI + errRazmer + errData + errIzvurshiAnaliza;

			if (!errString.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Във Файла има грашки за:\n" + errString, "Грешни данни",
						JOptionPane.ERROR_MESSAGE);

			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, errString, "Грешни данни", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Не е избран excel файл", "Грешни данни", JOptionPane.ERROR_MESSAGE);
		}

		return destruct_Result_List;
	}

	public static String[][] getMasivNuclideFromExcelFile(List<Destruct_Result> list_destruct_Result) {
		String[][] str = new String[list_destruct_Result.size()][12];
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
			str[i][9] = list_destruct_Result.get(i).getDate_Analize();
			str[i][10] = list_destruct_Result.get(i).getUser_Analize();
			str[i][11] = list_destruct_Result.get(i).getDobiv();

		}
		return str;

	}

	public static Dobiv[] getMasivDobivsFromExcelFile(List<Destruct_Result> list_destruct_Result,
			List<String> listSimbolBasicNuclide) {
		String[][] masiveActiveResults = getMasivNuclideFromExcelFile(list_destruct_Result);
		List<Dobiv> listDobivs = new ArrayList<Dobiv>();
		for (int i = 0; i < masiveActiveResults.length; i++) {
			String nuclideResult = masiveActiveResults[i][2].trim();
			for (String nuclideBasic : listSimbolBasicNuclide) {
				if (nuclideResult.equals(nuclideBasic)) {
					try {
						Dobiv dobiv = new Dobiv();
						dobiv.setNuclide(NuclideDAO.getValueNuclideBySymbol(nuclideResult));
						dobiv.setValue_result(Double.parseDouble(masiveActiveResults[i][3]));
						dobiv.setUncertainty(Double.parseDouble(masiveActiveResults[i][4]));
						dobiv.setDate_measur(masiveActiveResults[i][9]);
						dobiv.setDate_chim_oper("");
						dobiv.setTsi(ReadGamaFile.getTSIObjectFromFileString(masiveActiveResults[i][7]));

						listDobivs.add(dobiv);

					} catch (NumberFormatException e) {
					}
				}
			}
		}
		Dobiv[] masiveDobivsnew = new Dobiv[listDobivs.size()];
		for (int j = 0; j < listDobivs.size(); j++) {
			masiveDobivsnew[j] = listDobivs.get(j);
		}
		return masiveDobivsnew;
	}

	public static Results[] getMasivResultsFromExcelFile(List<Destruct_Result> list_destruct_Result,
			List<String> listSimbolBasicNuclide) {
		String[][] masiveActiveResults = getMasivNuclideFromExcelFile(list_destruct_Result);
		System.out.println(masiveActiveResults.length + " //////////////////////////////////");
		List<Results> listResults = new ArrayList<Results>();
		for (int i = 0; i < masiveActiveResults.length; i++) {
			String nuclideResult = masiveActiveResults[i][2].trim();

			for (String nuclideBasic : listSimbolBasicNuclide) {
				System.out.println(nuclideResult + " -+ " + nuclideBasic);
				if (nuclideBasic.contains(nuclideResult.replaceAll("\\D+",""))
						&& nuclideBasic.contains(nuclideResult.replaceAll("[^a-zA-Z ]", ""))) {
					if(!nuclideBasic.equals(nuclideResult)){
						nuclideResult = nuclideBasic;
					}
					System.out.println(nuclideResult + " - " + nuclideBasic);
					Results results = new Results();
					String qantity ="";
					try {
						Double.parseDouble( masiveActiveResults[i][6]);
						qantity = masiveActiveResults[i][6];
						
					} catch (NumberFormatException e) {
						qantity = masiveActiveResults[i][6].replaceAll("[^0-9.,\\s]", "").trim();
						 qantity = qantity.replaceAll(" ","");
//						JOptionPane.showMessageDialog(null, "NumberFormatException "+nuclideResult,"text frame", JOptionPane.PLAIN_MESSAGE);	
					}
					System.out.println(qantity+"********************"+qantity.indexOf(" "));
						results.setNuclide(NuclideDAO.getValueNuclideBySymbol(nuclideResult));
						results.setValue_result(Double.parseDouble(masiveActiveResults[i][3]));
						results.setUncertainty(Double.parseDouble(masiveActiveResults[i][4]));
						results.setMda(Double.parseDouble(masiveActiveResults[i][5]));
						results.setQuantity(Double.parseDouble(qantity));
						results.setDate_measur(masiveActiveResults[i][9]);
						results.setDate_chim_oper("");
						results.setTsi(ReadGamaFile.getTSIObjectFromFileString(masiveActiveResults[i][7]));
						String dim = masiveActiveResults[i][8];
						results.setRazmernosti(RazmernostiDAO.getValueRazmernostiByName(dim));
						if (dim.indexOf("/") > 0) {
							results.setDimension(DimensionDAO.getValueDimensionByName(dim.replace("Bq/", "")));
						} else {
							results.setDimension(DimensionDAO.getValueDimensionByName(dim.replace("Bq", "")));
						}
						results.setSigma(2);

						listResults.add(results);

					
				}
			}
		}
		System.out.println(listResults.size() + " +-+ ");
		Results[] masiveResultsnew = new Results[listResults.size()];
		for (int j = 0; j < listResults.size(); j++) {
			masiveResultsnew[j] = listResults.get(j);
			System.out.println(masiveResultsnew[j].getNuclide().getSymbol_nuclide());
		}

		return masiveResultsnew;
	}

	public static String ReformatDoubleTo4decimalExponet(String formatNum) {
		String stt = "";
		System.out.println("1 "+formatNum);
		double dob2 = Double.parseDouble(formatNum);
		DecimalFormat df = new DecimalFormat("0.00000E00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		stt = df.format(dob2).replaceAll(",", ".");
		System.out.println("2 "+stt);
		String expon = stt.substring(stt.indexOf("E") + 1);
		int kk = Integer.parseInt(expon);
		if (kk >= -4 && kk < 0) {

			stt = NumberToMAXDigitAftrerZerro(formatNum);
			System.out.println("3 "+stt);
		}
		System.out.println("4 "+stt);
		if (kk >= 0 && kk <= 4) {
			DecimalFormat df4 = new DecimalFormat("#.#####");
			df4.setRoundingMode(RoundingMode.HALF_UP);
			stt = df4.format(dob2).replaceAll(",", ".");
		}
		System.out.println("5 "+stt);
		return stt;
	}

	public static String NumberToMAXDigitAftrerZerro(String num) {
		int MAXDigit = 5;
		double dd = Double.parseDouble(num);
		if (Double.compare(dd, 0.0) != 0) {

			String head = num.substring(0, num.indexOf("."));
			if (Integer.parseInt(head) == 0) {
				head = "0.";
				String body = num.substring(num.indexOf(".") + 1);

				while (body.substring(0, 1).equals("0")) {
					head = head + "0";
					body = body.substring(1);
				}

				if (body.length() >= MAXDigit) {
					String olt;
					int bodyInt = 0;
					double dob2 = 0;
					body = body.substring(0, MAXDigit);
					bodyInt = Integer.parseInt(body);
					olt = "0." + body.substring(MAXDigit - 1);
					dob2 = Double.parseDouble(olt);

					if (dob2 + 0.5 >= 1) {
						bodyInt++;
					}
					num = head + bodyInt;
				}
			}
		}
		return num;
	}

	public static Users getUserFromExcelFile() {
		System.out.println("--------------------------------- ////////////// " + user_Analize);
		String str = user_Analize;
		Users user = UsersDAO.getValueUsersById(10);

		if (user_Analize.length() > 0) {
			if (user_Analize.contains(".")) {
				str = user_Analize.substring(user_Analize.indexOf(".") + 1);
			}
			user = UsersDAO.getValueUsersByFamily(str);
		}

		return user;
	}

}
