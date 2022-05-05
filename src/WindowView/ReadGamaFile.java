package WindowView;

import java.awt.Choice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

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
import DBase_Class.Nuclide;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.TSI;
import DBase_Class.Users;


public class ReadGamaFile {

	private static int countLine;
	private static List<String> listNuclideAkv;
	private static List<String> listNuclideMDA;
	private static String[] stringArray;
	private static String cod_sample;
	private static String user_mesure;
	private static String quantity = "";
	private static String dimension = "";
	private static String uncertainy;
	private static String T_S_I;
	private static String sysError;
	private static String sigma;
	private static String dobivFromFile;
	private static String date_mesur;
	private static Nuclide nuclideTracer;

	public static int getCountLine() {
		return countLine;
	}

	public static String getCod_sample() {
		return cod_sample;
	}

	public static String getDobiv() {
		return dobivFromFile;
	}

	public static Nuclide getNuclideTracer() {
		return nuclideTracer;
	}

	public static String getUser_mesure() {
		return user_mesure;
	}

	public static String getQuantity() {
		return quantity;
	}

	public static String getDimension() {
		return dimension;
	}

	public static String getUncertainy() {
		return uncertainy;
	}

	public static String getT_S_I() {
		return T_S_I;
	}

	public static String getSysError() {
		return sysError;
	}

	public static String getSigma() {
		return sigma;
	}

	public static String getDate_mesur() {
		return date_mesur;
	}

	public static int getListNuclideMDA() {
		return listNuclideMDA.size();
	}

	public static List<String> getListNuclideAkv() {
		return listNuclideAkv;
	}

	public static String[] getStringArray() {
		return stringArray;
	}

	public static void getReadGamaFile(String FILENAME) {

		stringArray = CreadMasiveFromReadFile(FILENAME);

		date_mesur = DatePicker.reformarDateMeasur(StringUtils.split(stringArray[0])[0]);
		int flagNuclidy = 0;
		int countLineToNuclide = 0;
		Boolean fl_error_MDA = false;
		Nuclide nucl = null;
		String[][] stringLine = new String[countLine][];
		listNuclideAkv = new ArrayList<String>();
		listNuclideMDA = new ArrayList<String>();

		for (int j = 0; j < countLine; j++) {
			stringLine[j] = StringUtils.split(stringArray[j]);
			nucl = getNuclideTracerFromGenieAlphaFile(stringLine[j]);
			if(nucl!=null){
			nuclideTracer = nucl;
			}
			if (flagNuclidy == 1) {
				countLineToNuclide++;
				if (countLineToNuclide > 2) {
					if (stringLine[j].length != 0) {
						if (stringArray[j].length() > 70 && stringArray[j].substring(25, 40).trim().indexOf("*") < 0) {
						
							if(!(transformNuclideSimbol(StringUtils.split(stringArray[j])[0])).equals(nuclideTracer.getSymbol_nuclide())){
								System.out.println(transformNuclideSimbol(StringUtils.split(stringArray[j])[0])+"   -  "+(nuclideTracer.getSymbol_nuclide()));
							listNuclideAkv.add(stringArray[j]);
							System.out.println("IN");
							}
						}
					} else {
						flagNuclidy++;
					}
				}
			} else {
				if (flagNuclidy == 3 || flagNuclidy == 5 || flagNuclidy == 7) {
					countLineToNuclide++;
					if (countLineToNuclide > 2) {
						if (stringLine[j].length != 0) {
							if (stringArray[j].substring(0, 10).indexOf("@") > 0) {
								fl_error_MDA = true;
							}
							listNuclideMDA.add(stringArray[j]);
						} else {
							flagNuclidy++;
						}
					}
				} else {
					if (stringLine[j].length > 0) {
						switch (stringLine[j][0]) {
						case "Описание":
							cod_sample = stringLine[j][3];
							break;
						case "Извършил":
							user_mesure = stringLine[j][3];
							break;
						case "Количество":
							quantity = stringLine[j][3];
							dimension = "";
							if (stringLine[j].length > 4) {
								dimension = stringLine[j][4];
								// dimension = dimension.replaceAll("2", "²");
								// dimension = dimension.replaceAll("3","³");
							}
							break;
						case "Неопределеност":
							uncertainy = stringLine[j][1];
							break;
						case "Kалибровка":
							T_S_I = stringLine[j][3];
							break;
						case "Нуклид": {
							flagNuclidy++;
							countLineToNuclide = 0;
						}
							break;
						case "Систематична":
							sysError = stringLine[j][2];
							break;
						case "Неопределеността":
							sigma = stringLine[j][4];
							break;
						case "Chem.":
							dobivFromFile = stringLine[j][3];
							break;
						// case "Дата":
						// date_mesur = stringLine[j][1];
						// break;

						}
					}
				}
			}
		}

		if (fl_error_MDA) {
			JOptionPane.showMessageDialog(null, " Няма корекция за разпадане.\n Проверете референтната дата",
					"Грешни данни", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private static Nuclide getNuclideTracerFromGenieAlphaFile(String[] strings) {
		Nuclide nuclideTracer = null;
		String str = "";
		if (strings.length > 2) {
			if (strings[0].contains("-")) {
				str = transformNuclideSimbol(strings[0]);
				if (NuclideDAO.getValueNuclideBySymbol(str) != null) {
					if (strings[1].equals("T")) {
						nuclideTracer = NuclideDAO.getValueNuclideBySymbol(str);
					}
				}
			}
		}
		return nuclideTracer;
	}

	private static String transformNuclideSimbol(String firstSimbol) {
		String strNumber = firstSimbol.substring(firstSimbol.indexOf("-") + 1);
		String strSimbol = firstSimbol.substring(0, firstSimbol.indexOf("-"));
		return transformLastSimbolInSmal(strNumber) + transformLastSimbolInSmal(strSimbol);
	}

	private static String transformLastSimbolInSmal(String firstSimbol) {
		if (firstSimbol.length() > 1) {
			char ch = firstSimbol.charAt(firstSimbol.length() - 1);
			int intChar = (int) ch;
			if (intChar > 9) {
				ch = Character.toLowerCase(ch);
			}
			return firstSimbol.substring(0, firstSimbol.length() - 1) + ch;
		}
		return firstSimbol;
	}

	public static String[][] getMasivNuclideAktivGenieAlpha() {
		String[][] str = new String[listNuclideAkv.size()][4];
		for (int i = 0; i < listNuclideAkv.size(); i++) {
			System.out.println(listNuclideAkv.get(i));
			System.out.println(listNuclideAkv.get(i).substring(0, 4).trim());
			if (listNuclideAkv.get(i).substring(0, 4).trim().length() == 0) {

				str[i][0] = transformNuclideSimbol(listNuclideAkv.get(i).substring(2, 12).trim());// nuclide
																									// simbol

				str[i][1] = listNuclideAkv.get(i).substring(13, 27).trim();// nuclide
																			// active
				str[i][2] = listNuclideAkv.get(i).substring(27, 40).trim();// nuclide
																			// uncertai
				str[i][3] = listNuclideAkv.get(i).substring(50, 65).trim();// nuclide
			} // mda
			for (int j = 0; j < 4; j++) {
				System.out.print(str[i][j] + " ");
			}
			System.out.println();
		}
		return str;

	}

	public static String[][] getMasivNuclideAktiv() {
		String[][] str = new String[listNuclideAkv.size()][4];
		for (int i = 0; i < listNuclideAkv.size(); i++) {

			if (listNuclideAkv.get(i).substring(0, 5).trim().length() == 0) {

				str[i][0] = transformNuclideSimbol(listNuclideAkv.get(i).substring(4, 16).trim());// nuclide
																									// simbol

				str[i][1] = listNuclideAkv.get(i).substring(25, 41).trim();// nuclide
																			// active
				str[i][2] = listNuclideAkv.get(i).substring(41, 55).trim();// nuclide
																			// uncertai
				str[i][3] = listNuclideAkv.get(i).substring(70).trim();// nuclide
			} // mda

		}
		return str;

	}

	public static List<String> getListStringMDA() {

		return listNuclideMDA;

	}

	public static String[][] getMasivNuclideMDAGenieAlpha() {
		String[][] listActiveNuclide = getMasivNuclideAktivGenieAlpha();
		String[][] str1 = new String[listNuclideMDA.size()][2];
		int k = 0;
		String str;
		Boolean fl = false;
		for (int i = 0; i < listNuclideMDA.size(); i++) {
			fl = false;
			str = listNuclideMDA.get(i).substring(6, 16).trim();
			if (str.length() > 0) {
				str = transformNuclideSimbol(str);
				System.out.println(str+"  - "+(nuclideTracer.getSymbol_nuclide()));
				if(str.equals(nuclideTracer.getSymbol_nuclide())){
					fl = true;
				}
				for (int j = 0; j < listActiveNuclide.length; j++) {
					if (listActiveNuclide[j][0].equals(str)) {
						fl = true;
					}
				
			}
				if (!fl) {
					str1[k][0] = str;
					str1[k][1] = listNuclideMDA.get(i).substring(40, 55).trim();
					k++;
				}

			}
		}
		String[][] newStr = new String[k][2];
		for (int i = 0; i < newStr.length; i++) {
			newStr[i][0] = str1[i][0];
			newStr[i][1] = str1[i][1];
		}
		return newStr;

	}

	public static String[][] getMasivNuclideMDA() {
		String[][] listActiveNuclide = getMasivNuclideAktiv();
		String[][] str1 = new String[listNuclideMDA.size()][2];
		int k = 0;
		String str;
		Boolean fl = false;
		for (int i = 0; i < listNuclideMDA.size(); i++) {
			fl = false;
			str = listNuclideMDA.get(i).substring(6, 16).trim();
			if (str.length() > 0) {
				str = transformNuclideSimbol(str);
				for (int j = 0; j < listActiveNuclide.length; j++) {
					if (listActiveNuclide[j][0].equals(str)) {
						fl = true;
					}
				}
				if (!fl) {
					str1[k][0] = str;
					str1[k][1] = listNuclideMDA.get(i).substring(50, 65).trim();
					k++;
				}

			}
		}
		String[][] newStr = new String[k][2];
		for (int i = 0; i < newStr.length; i++) {
			newStr[i][0] = str1[i][0];
			newStr[i][1] = str1[i][1];
		}
		return newStr;

	}

	public static Results[] getMasivResultsWithAktivGenieAlpha() {
		String[][] masiveActiveResults = getMasivNuclideAktivGenieAlpha();
		List<Results> listResults = new ArrayList<Results>();

		for (int i = 0; i < masiveActiveResults.length; i++) {
			try {
				Double.parseDouble(masiveActiveResults[i][1]);

				Results results = new Results();
				results.setNuclide(NuclideDAO.getValueNuclideBySymbol(masiveActiveResults[i][0]));
				results.setValue_result(Double.parseDouble(masiveActiveResults[i][1]));
				results.setUncertainty(Double.parseDouble(masiveActiveResults[i][2]));
				results.setMda(Double.parseDouble(masiveActiveResults[i][3]));
				results.setUser_measur(getUserFromFile());
				results.setDate_measur(date_mesur);
				results.setDate_chim_oper("");
				results.setTsi(getTSIObjectFromFileString("T20A"));
				results.setQuantity(Double.parseDouble(quantity));
				results.setDimension(DimensionDAO.getValueDimensionByName(dimension));
				if (dimension.length() > 0) {
					results.setRazmernosti(RazmernostiDAO.getValueRazmernostiByName("Bq/" + dimension));
				} else {
					results.setRazmernosti(RazmernostiDAO.getValueRazmernostiByName("Bq"));
				}
				results.setSigma(Integer.parseInt(sigma.trim().substring(0, sigma.indexOf("."))));
				listResults.add(results);

			} catch (NumberFormatException e) {

			}

		}

		Results[] masiveResultsnew = new Results[listResults.size()];
		for (int j = 0; j < masiveResultsnew.length; j++) {
			masiveResultsnew[j] = listResults.get(j);
		}

		return masiveResultsnew;
	}

	public static Results[] getMasivResultsWithAktiv() {
		String[][] masiveActiveResults = getMasivNuclideAktiv();
		List<Results> listResults = new ArrayList<Results>();

		for (int i = 0; i < masiveActiveResults.length; i++) {
			try {
				Double.parseDouble(masiveActiveResults[i][1]);

				Results results = new Results();
				results.setNuclide(NuclideDAO.getValueNuclideBySymbol(masiveActiveResults[i][0]));
				results.setValue_result(Double.parseDouble(masiveActiveResults[i][1]));
				results.setUncertainty(Double.parseDouble(masiveActiveResults[i][2]));
				results.setMda(Double.parseDouble(masiveActiveResults[i][3]));
				results.setUser_measur(getUserFromFile());
				results.setDate_measur(date_mesur);
				results.setDate_chim_oper("");
				results.setTsi(getTSIObjectFromFileString(T_S_I));
				results.setQuantity(Double.parseDouble(quantity));
				results.setDimension(DimensionDAO.getValueDimensionByName(dimension));
				if (dimension.length() > 0) {
					results.setRazmernosti(RazmernostiDAO.getValueRazmernostiByName("Bq/" + dimension));
				} else {
					results.setRazmernosti(RazmernostiDAO.getValueRazmernostiByName("Bq"));
				}
				results.setSigma(Integer.parseInt(sigma.trim().substring(0, sigma.indexOf("."))));
				listResults.add(results);

			} catch (NumberFormatException e) {

			}

		}

		Results[] masiveResultsnew = new Results[listResults.size()];
		for (int j = 0; j < masiveResultsnew.length; j++) {
			masiveResultsnew[j] = listResults.get(j);
		}

		return masiveResultsnew;
	}

	public static Results[] getMasivResultsMDAGenieAlpha(List<String> listSimbolBasicNuclide) {
		String[][] masiveNuclideMDA = getMasivNuclideMDAGenieAlpha();
		Results[] masiveResultsMDA = new Results[masiveNuclideMDA.length];
		for (int i = 0; i < masiveNuclideMDA.length; i++) {
			System.out.println(i + "  " + masiveNuclideMDA[i][0]);
		}
		for (String results : listSimbolBasicNuclide) {
			System.out.println(results);
		}
		int k = 0;
		for (int i = 0; i < masiveResultsMDA.length; i++) {
			for (String str : listSimbolBasicNuclide) {

				if (str.equals(masiveNuclideMDA[i][0])) {
					masiveResultsMDA[k] = new Results();
					masiveResultsMDA[k].setNuclide(NuclideDAO.getValueNuclideBySymbol(masiveNuclideMDA[i][0]));
					masiveResultsMDA[k].setValue_result(0.0);
					masiveResultsMDA[k].setUncertainty(0.0);
					masiveResultsMDA[k].setMda(Double.parseDouble(masiveNuclideMDA[i][1]));
					masiveResultsMDA[k].setUser_measur(getUserFromFile());
					masiveResultsMDA[k].setDate_measur(date_mesur);
					masiveResultsMDA[k].setDate_chim_oper("");
					masiveResultsMDA[k].setTsi(getTSIObjectFromFileString(T_S_I));
					masiveResultsMDA[k].setQuantity(Double.parseDouble(quantity));
					masiveResultsMDA[k].setDimension(DimensionDAO.getValueDimensionByName(dimension));
					if (dimension.length() > 0) {

						masiveResultsMDA[k].setRazmernosti(RazmernostiDAO.getValueRazmernostiByName("Bq/" + dimension));

					} else {
						masiveResultsMDA[k].setRazmernosti(RazmernostiDAO.getValueRazmernostiByName("Bq"));
					}
					masiveResultsMDA[k].setSigma(Integer.parseInt(sigma.substring(0, 1)));
					k++;
				}
			}
		}
		Results[] newMasiveResultsMDA = new Results[k];
		for (int i = 0; i < newMasiveResultsMDA.length; i++) {
			newMasiveResultsMDA[i] = masiveResultsMDA[i];
		}
		return newMasiveResultsMDA;
	}

	public static Results[] getMasivResultsMDA(List<String> listSimbolBasicNuclide) {
		String[][] masiveNuclideMDA = getMasivNuclideMDA();
		Results[] masiveResultsMDA = new Results[masiveNuclideMDA.length];
		for (int i = 0; i < masiveNuclideMDA.length; i++) {
			System.out.println(i + "  " + masiveNuclideMDA[i][0]);
		}
		for (String results : listSimbolBasicNuclide) {
			System.out.println(results);
		}
		int k = 0;
		for (int i = 0; i < masiveResultsMDA.length; i++) {
			for (String str : listSimbolBasicNuclide) {

				if (str.equals(masiveNuclideMDA[i][0])) {
					masiveResultsMDA[k] = new Results();
					masiveResultsMDA[k].setNuclide(NuclideDAO.getValueNuclideBySymbol(masiveNuclideMDA[i][0]));
					masiveResultsMDA[k].setValue_result(0.0);
					masiveResultsMDA[k].setUncertainty(0.0);
					masiveResultsMDA[k].setMda(Double.parseDouble(masiveNuclideMDA[i][1]));
					masiveResultsMDA[k].setUser_measur(getUserFromFile());
					masiveResultsMDA[k].setDate_measur(date_mesur);
					masiveResultsMDA[k].setDate_chim_oper("");
					masiveResultsMDA[k].setTsi(getTSIObjectFromFileString(T_S_I));
					masiveResultsMDA[k].setQuantity(Double.parseDouble(quantity));
					masiveResultsMDA[k].setDimension(DimensionDAO.getValueDimensionByName(dimension));
					if (dimension.length() > 0) {

						masiveResultsMDA[k].setRazmernosti(RazmernostiDAO.getValueRazmernostiByName("Bq/" + dimension));

					} else {
						masiveResultsMDA[k].setRazmernosti(RazmernostiDAO.getValueRazmernostiByName("Bq"));
					}
					masiveResultsMDA[k].setSigma(Integer.parseInt(sigma.substring(0, 1)));
					k++;
				}
			}
		}
		Results[] newMasiveResultsMDA = new Results[k];
		for (int i = 0; i < newMasiveResultsMDA.length; i++) {
			newMasiveResultsMDA[i] = masiveResultsMDA[i];
		}
		return newMasiveResultsMDA;
	}

	public static TSI getTSIObjectFromFileString(String t_s_i) {
		System.out.println("--------------------- " + t_s_i);
		return TSI_DAO.getValueTSIByNumberFromName(t_s_i.substring(1, 3).trim());

	}

	private static String[] CreadMasiveFromReadFile(String FILENAME) {
		BufferedReader br = null;
		FileReader fr = null;
		List<String> listString = new ArrayList<String>();
		String[] newStringArray = null;
		try {
			// br = new BufferedReader(new FileReader(FILENAME));
			// fr = new FileReader(FILENAME);
			// br = new BufferedReader(fr);
			File fileDir = new File(FILENAME);

			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "Cp1251"));
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				listString.add(sCurrentLine);
				System.out.println(sCurrentLine);

			}

			countLine = listString.size();
			newStringArray = new String[countLine];
			for (int i = 0; i < newStringArray.length; i++) {
				newStringArray[i] = listString.get(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return newStringArray;
	}

	public static Users getUserFromFile() {
		String str = null;
		Users user = UsersDAO.getValueUsersById(10);
		if (user_mesure != null && user_mesure.length() > 0) {
			if (user_mesure.contains(".")) {
				str = user_mesure.substring(user_mesure.indexOf(".") + 1).trim();
			}
			user = UsersDAO.getValueUsersByFamily(str);
		}

		return user;
	}

	public static Dobiv getDobivFromGenieAlphaFile(Choice choiceSmplCode, Choice choiceMetody) {

		Dobiv dobiv = new Dobiv();
		Sample sample = SampleCodeSection.getSampleObjectFromChoiceSampleCode(choiceSmplCode);
		Izpitvan_produkt izpitProd = sample.getRequest().getIzpitvan_produkt();
		dobiv.setCode_Standart(nuclideTracer.getSymbol_nuclide() +" "+ cod_sample);
		dobiv.setMetody((Metody) MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));
		dobiv.setIzpitvan_produkt(izpitProd);
		dobiv.setDescription(sample.getRequest_to_obekt_na_izpitvane_request().getObektNaIzp().getSimple_Name() + ", "
				+ sample.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane());
		dobiv.setNuclide(nuclideTracer);
		dobiv.setValue_result(Double.parseDouble(dobivFromFile));
		dobiv.setUncertainty(0.0);
		dobiv.setTsi(TSI_DAO.getValueTSIByName(T_S_I));
		dobiv.setDate_measur(date_mesur);
		dobiv.setDate_redac(RequestViewFunction.DateNaw(false));
		dobiv.setUser_redac(OverallVariablesAddDobiv.getUser_Redac());

		return dobiv;
	}

}