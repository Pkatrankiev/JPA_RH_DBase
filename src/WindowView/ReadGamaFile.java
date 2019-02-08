package WindowView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import Aplication.DimensionDAO;
import Aplication.NuclideDAO;
import Aplication.RazmernostiDAO;
import Aplication.UsersDAO;
import DBase_Class.Results;
import DBase_Class.Users;

public class ReadGamaFile {

	private static int countLine;
	private static int countMDAMasive;
	private static List<String> listNuclideAkv;
	private static List<String> listNuclideMDA;
	private static List<String[]> listObjectNuclideAkv;
	private static List<String[]> listObjectNuclideMDA;

	private static String cod_sample;
	private static String user_mesure;
	private static String quantity = "";
	private static String dimension="";
	private static String uncertainy;
	private static String T_S_I;
	private static String sysError;
	private static String sigma;
	private static String date_mesur;

	public static int getCountLine() {
		return countLine;
	}

	public static String getCod_sample() {
		return cod_sample;
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

	public static void ReadGamaFile(String FILENAME) {

		String[] stringArray = CreadMasiveFromReadFile(FILENAME);
		creadDataFromTextLine(stringArray);
		listObjectNuclideAkv = new ArrayList<String[]>();
		listObjectNuclideMDA = new ArrayList<String[]>();

	}

	private static String transformNuclideSimbol(String firstSimbol){
		String newSimbol = null;
		String strNumber = firstSimbol.substring(firstSimbol.indexOf("-")+1);
		String strSimbol = firstSimbol.substring(0,firstSimbol.indexOf("-"));
		return transformLastSimbolInSmal(strNumber)+transformLastSimbolInSmal(strSimbol);
		}
	
	private static String transformLastSimbolInSmal(String firstSimbol){
		if(firstSimbol.length()>1){
		char ch = firstSimbol.charAt(firstSimbol.length()-1);
		int  intChar = (int) ch;
		if(intChar>9){
		ch = Character.toLowerCase(ch);
		}
		return firstSimbol.substring(0,firstSimbol.length()-1)+ch;
		}return firstSimbol;
	}
	
	
	public static String[][] getMasivNuclideAktiv() {
		String[][] str = new String[listNuclideAkv.size()][4];
		String strNuclSim;
		String newSim;
		for (int i = 0; i < listNuclideAkv.size(); i++) {
		
			str[i][0] = transformNuclideSimbol(listNuclideAkv.get(i).substring(4, 16).trim());// nuclide simbol

			str[i][1] = listNuclideAkv.get(i).substring(25, 40).trim();// nuclide
																		// active
			str[i][2] = listNuclideAkv.get(i).substring(40, 55).trim();// nuclide
																		// uncertai
			str[i][3] = listNuclideAkv.get(i).substring(70).trim();// nuclide
																	// mda

		}
		return str;

	}

	
	public static String[][] getMasivNuclideMDA() {
		String[][] listActiveNuclide = getMasivNuclideAktiv();
		String[][] str1 = new String[listNuclideMDA.size()][2];
		int k = 0;
		String str;
		Boolean fl = false;
		for (int i = 0; i < listNuclideMDA.size(); i++) {
			fl=false;
			str = listNuclideMDA.get(i).substring(4, 16).trim();
			if (str.length() > 0) {
				str = transformNuclideSimbol(str);
				for (int j = 0; j < listActiveNuclide.length; j++) {
					System.out.println(listActiveNuclide[j][0]+" "+str+"  "+k+"  "+ fl);
					if (listActiveNuclide[j][0].equals(str)){
					fl=true;
					}
				}
				if(!fl){
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
		countMDAMasive = newStr.length;
		return newStr;

	}

	
	public static Results[] getMasivResultsWithAktiv(){
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
				if(quantity.length()>0){
					results.setQuantity(Double.parseDouble(quantity));	
					results.setDimension(DimensionDAO.getValueDimensionByName(dimension));
					results.setRtazmernosti(RazmernostiDAO.getValueRazmernostiByName("Bq/"+dimension));
				}else{
					results.setRtazmernosti(RazmernostiDAO.getValueRazmernostiByName("Bq"));
				}
				results.setSigma(Integer.parseInt(sigma.trim().substring(0, sigma.indexOf("."))));
				listResults.add(results);
			
			} catch (NumberFormatException e) {
				
				
						}
		
		}
	
			Results [] masiveResultsnew = new Results[listResults.size()];
			for (int j = 0; j < masiveResultsnew.length; j++) {
				masiveResultsnew[j]=listResults.get(j);
			}
					
				
		return masiveResultsnew;
		}

	
	public static Results[] getMasivResultsMDA(List<String> listSimbolBasicNuclide) {
		String[][] masiveNuclideMDA = getMasivNuclideMDA();
		Results[] masiveResultsMDA = new Results[masiveNuclideMDA.length];
		for (int i = 0; i < masiveNuclideMDA.length; i++) {
			System.out.println(i+"  "+masiveNuclideMDA[i][0]);	
		}
		for (String results : listSimbolBasicNuclide) {
			System.out.println(results);
		}
		int k=0;
		for (int i = 0; i < masiveResultsMDA.length; i++) {
			for (String str : listSimbolBasicNuclide) {
				
			if(str.equals(masiveNuclideMDA[i][0]))	{
			masiveResultsMDA[k] = new Results();
			masiveResultsMDA[k].setNuclide(NuclideDAO.getValueNuclideBySymbol(masiveNuclideMDA[i][0]));
			masiveResultsMDA[k].setValue_result(0.0);
			masiveResultsMDA[k].setUncertainty(0.0);
			masiveResultsMDA[k].setMda(Double.parseDouble(masiveNuclideMDA[i][1]));
			masiveResultsMDA[k].setUser_measur(getUserFromFile());
			masiveResultsMDA[k].setDate_measur(date_mesur);
			masiveResultsMDA[k].setDate_chim_oper("");
			if (quantity.length() > 0) {
				masiveResultsMDA[k].setQuantity(Double.parseDouble(quantity));
				masiveResultsMDA[k].setDimension(DimensionDAO.getValueDimensionByName(dimension));
				masiveResultsMDA[k].setRtazmernosti(RazmernostiDAO.getValueRazmernostiByName("Bq/" + dimension));
			} else {
				masiveResultsMDA[k].setRtazmernosti(RazmernostiDAO.getValueRazmernostiByName("Bq"));
			}
			masiveResultsMDA[k].setSigma(Integer.parseInt(sigma.substring(0,1)));
			k++;
			}
		}
		}
		Results[] newMasiveResultsMDA = new Results[k];
		for (int i = 0; i < newMasiveResultsMDA.length; i++) {
			newMasiveResultsMDA[i]=masiveResultsMDA[i];
		}
		return newMasiveResultsMDA;
	}

	private static String[] CreadMasiveFromReadFile(String FILENAME) {
		BufferedReader br = null;
		FileReader fr = null;
		String[] stringArray = new String[1000];
		try {
			// br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			String sCurrentLine;
			int i = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				stringArray[i] = sCurrentLine;
				System.out.println(sCurrentLine);
				i++;
			}
			countLine = i;

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
		return stringArray;
	}

	public  static Users getUserFromFile() {
		String str = null;
		Users user = UsersDAO.getValueUsersById(1);
		if (user_mesure.length() > 0) {
			if (user_mesure.contains(".")) {
				str = user_mesure.substring(user_mesure.indexOf(".")+1);
			}
			user = UsersDAO.getValueUsersByFamily(str);
		}

		return user;
	}

	private static void creadDataFromTextLine(String[] stringArray) {
		int flagNuclidy = 0;
		int countLineToNuclide = 0;

		String[][] stringLine = new String[countLine][];
		listNuclideAkv = new ArrayList<String>();
		listNuclideMDA = new ArrayList<String>();

		for (int j = 0; j < countLine; j++) {
			stringLine[j] = StringUtils.split(stringArray[j]);
			if (flagNuclidy == 1) {
				countLineToNuclide++;
				if (countLineToNuclide > 2) {
					if (stringLine[j].length != 0) {
						listNuclideAkv.add(stringArray[j]);
					} else {
						flagNuclidy++;
					}
				}
			} else {
				if (flagNuclidy == 3 || flagNuclidy == 5 || flagNuclidy == 7) {
					countLineToNuclide++;
					if (countLineToNuclide > 2) {
						if (stringLine[j].length != 0) {
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
							if (stringLine[j].length > 4) {
								dimension = stringLine[j][4];
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
						case "Начало":
							date_mesur = stringLine[j][3];
							break;

						}
					}
				}
			}
		}
	}

}