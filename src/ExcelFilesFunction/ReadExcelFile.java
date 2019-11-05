package ExcelFilesFunction;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import AddDobivViewFunction.OverallVariablesAddDobiv;
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
import DBase_Class.Results;
import DBase_Class.Sample;
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
	private static String user_Analize="";
	private static String value_Standatd;
	private static String nuclide_StandardStr;
	private static String nuclide_Standard;
	public static String getCod_sample() {
		return cod_sample;
	}

	public static String getStringDestruct_Result(Destruct_Result destruct_Result) {

		return destruct_Result.getResult() + " ; " + destruct_Result.getMda() + " ;  " + destruct_Result.getMetod()
				+ "  ; " + destruct_Result.getNuclide() + " ;  " + destruct_Result.getTsi() + " ;  "
				+ destruct_Result.getUncert();
	}

	public static List<Destruct_Result> getDestruct_Result_ListFromOrtecExcelFile(String FILE_PATH, Boolean forResults) {
		DataFormatter formatter = new DataFormatter();
		List<Destruct_Result> destruct_Result_List = new ArrayList<Destruct_Result>();
		FileInputStream fis = null;
		String metod = "", nuclide = "", result = "", uncert = "", mda = "", quantity = "", tsi = "",
				dimencion = "";
		String date_Analize= ""  ;
	
		
		try {
			fis = new FileInputStream(FILE_PATH);

			// Using XSSF for xlsx format, for xls use HSSF
			@SuppressWarnings("resource")
			Workbook workbook = new HSSFWorkbook(fis);

			 Sheet sheet = workbook.getSheetAt(0);
			 
			 cod_sample = sheet.getRow(5).getCell(0).getStringCellValue().replaceFirst("Sample:", "");
			 metod = "М.ЛИ-РХ-01";
			 quantity = sheet.getRow(5).getCell(6).getStringCellValue().replaceFirst("Sample Volume :", "").replace(",", ".");
			 tsi = "T04";
			 dimencion = sheet.getRow(68).getCell(10).getStringCellValue().replace("^", "");
			 date_Analize = sheet.getRow(3).getCell(9).getStringCellValue().replace("/", ".");
			 nuclide_StandardStr = sheet.getRow(17).getCell(6).getStringCellValue().replace("Tracer Nuclide:", "");
			 int indexNuclideStandart = nuclide_StandardStr.indexOf("-");
			 nuclide_Standard = nuclide_StandardStr.substring(indexNuclideStandart+1)+nuclide_StandardStr.substring(0,indexNuclideStandart);
			 nuclide_StandardStr =  nuclide_StandardStr.substring(0, nuclide_StandardStr.indexOf("-"));
			
			 value_Standatd = sheet.getRow(19).getCell(6).getStringCellValue().replace("Tracer Recovery:", "").replace(",", ".").replace("%", "");
			 user_Analize ="";
						
			 for (int i = 70; i <= sheet.getLastRowNum(); i+=2) {
				if (!formatter.formatCellValue(sheet.getRow(i).getCell(0)).isEmpty()) {
					nuclide = sheet.getRow(i).getCell(0).getStringCellValue();
					int index = nuclide.indexOf("-");
					nuclide = nuclide.substring(index+1)+nuclide.substring(0,index);
					
					 result = sheet.getRow(i).getCell(10).getStringCellValue(); 
					 double dub_result = Double.valueOf(result);
					 uncert =  sheet.getRow(i).getCell(11).getStringCellValue(); 
					 mda = sheet.getRow(i).getCell(13).getStringCellValue(); 
					 double dub_MDA = Double.valueOf(mda);	
					 if(forResults){
							if(dub_MDA > dub_result) {
								result = "0.0";
								uncert = "0.0";
						}
				 }
				 }
				System.out.println(cod_sample+" - "+ metod+" - "+  nuclide+" - "+  result+" - "+ uncert+" - "+  mda+" - "+ 
						tsi+" - "+  quantity+" - "+  dimencion+" - "+  date_Analize+" - "+  user_Analize);
				 destruct_Result_List.add(new Destruct_Result(cod_sample, metod, nuclide, result, "", uncert, mda,
							tsi, quantity, dimencion, date_Analize, user_Analize)); 
			 	
			 }						
 
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Не е избран excel файл", "Грешни данни", JOptionPane.ERROR_MESSAGE);
		}
		
		return destruct_Result_List;
	}
	
	public static Dobiv  getDobivFromOrtecExcelFile(List<Destruct_Result> destruct_Result_List, Choice choiceSmplCode, String selectMetodStr){
		Destruct_Result destruct_Result = destruct_Result_List.get(0);
		Dobiv dobiv = new Dobiv();
		Sample sample = SampleCodeSection.getSampleObjectFromChoiceSampleCode( choiceSmplCode);
		Izpitvan_produkt izpitProd = sample.getRequest().getIzpitvan_produkt();
		dobiv.setCode_Standart(nuclide_StandardStr+cod_sample);
		dobiv.setMetody((Metody) MetodyDAO.getValueList_MetodyByCode(selectMetodStr));
		dobiv.setIzpitvan_produkt(izpitProd);
		dobiv.setDescription(sample.getRequest_to_obekt_na_izpitvane_request().getObektNaIzp().getSimple_Name()+", "+sample.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane());
		dobiv.setNuclide(NuclideDAO.getValueNuclideBySymbol(nuclide_StandardStr));
		dobiv.setValue_result(Double.parseDouble(value_Standatd));
		dobiv.setUncertainty(0.0);
		dobiv.setTsi(TSI_DAO.getValueTSIByName(destruct_Result.getTsi()));
		dobiv.setDate_measur(destruct_Result.getDate_Analize());
		dobiv.setDate_redac(RequestViewFunction.DateNaw(false));
		dobiv.setUser_redac(OverallVariablesAddDobiv.getUser_Redac());


		return dobiv;
	}
	
	
	
	public static List<Destruct_Result> getDestruct_Result_ListFromExcelFile(String FILE_PATH, Boolean forResults) {

		DataFormatter formatter = new DataFormatter();
		List<Destruct_Result> destruct_Result_List = new ArrayList<Destruct_Result>();
		FileInputStream fis = null;
		String metod = "", nuclide = "", result = "", uncert = "", mda = "", quantity = "", tsi = "",
				dimencion = "";
		String param = "", valume = "", date_Analize= "" , dobiv = "" ;
		
		
		SimpleDateFormat sdf = new SimpleDateFormat(GlobalFormatDate.getFORMAT_DATE());
		Date dateNull = null;
		try {
			dateNull = sdf.parse("01.01.1910");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
							case "Добив":
								dobiv = String.valueOf(cell.getNumericCellValue());
								dobiv = NumberFormatWithRounding(quantity);
								break;
							case "ТСИ":
								tsi = valume;
								break;
							case "Размерност":
								dimencion = cell.getStringCellValue();
								break;
							case "Дата на анализа":
								if(cell.getDateCellValue().after(dateNull)){
								date_Analize = sdf.format(cell.getDateCellValue());
								}
								break;
							case "Извършил анализа":
								try{
								user_Analize = cell.getStringCellValue();
							} catch (IllegalStateException e) {
								user_Analize ="";
							}
								break;
							case "end":
								endNuclideRsult = true;
								break;

							}

							if (endNuclideRsult) {
								double dub_MDA = Double.valueOf(mda);
								double dub_result = Double.valueOf(result);
								if(forResults){
								if(dub_MDA > dub_result) {
									result = "0.0";
									uncert = "0.0";
								}
								}
								destruct_Result_List.add(new Destruct_Result(cod_sample, metod, nuclide, result, dobiv, uncert, mda,
										tsi, quantity, dimencion, date_Analize, user_Analize));
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
				System.out.println(nuclideResult+" -+ "+nuclideBasic);		
				if (nuclideBasic.contains( nuclideResult)) {
					try {
						System.out.println(nuclideResult+" - "+nuclideBasic);
						Results results = new Results();
						results.setNuclide(NuclideDAO.getValueNuclideBySymbol(nuclideResult));
						results.setValue_result(Double.parseDouble(masiveActiveResults[i][3]));
						results.setUncertainty(Double.parseDouble(masiveActiveResults[i][4]));
						results.setMda(Double.parseDouble(masiveActiveResults[i][5]));
						results.setQuantity(Double.parseDouble(masiveActiveResults[i][6]));
						results.setDate_measur(masiveActiveResults[i][9]);
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
		System.out.println(listResults.size()+" +-+ ");
			Results[] masiveResultsnew = new Results[listResults.size()];
		for (int j = 0; j < listResults.size(); j++) {
			masiveResultsnew[j] = listResults.get(j);
		}
		System.out.println(listResults.size()+" - "+masiveResultsnew.length);
		return masiveResultsnew;
	}
	
	public static String NumberFormatWithRounding(String num) {
		if( Double.parseDouble(num)==0){
			return num;
		}
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

	public  static Users getUserFromExcelFile() {
		System.out.println("--------------------------------- ////////////// "+user_Analize);
		String str = user_Analize;
		Users user = UsersDAO.getValueUsersById(10);
		
		if (user_Analize.length() > 0) {
			if (user_Analize.contains(".")) {
				str = user_Analize.substring(user_Analize.indexOf(".")+1);
			}
			user = UsersDAO.getValueUsersByFamily(str);
		}

		return user;
	}

}
