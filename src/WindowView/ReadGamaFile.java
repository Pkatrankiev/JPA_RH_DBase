package WindowView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ReadGamaFile {

	private static int countLine;
	private static List<String> listNuclideAkv;
	private static List<String> listNuclideMDA ;
	private static List<String[]> listObjectNuclideAkv ;
	private static List<String[]> listObjectNuclideMDA ;
	
	private static String cod_sample;
	private static String user_mesure;
	private static String quantity;
	private static String dimension;
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
	public static void  ReadGamaFile( String FILENAME ) {
	
		String[] stringArray = CreadMasiveFromReadFile(FILENAME);
		creadDataFromTextLine(stringArray);
		listObjectNuclideAkv = new ArrayList<String[]>();
		listObjectNuclideMDA = new ArrayList<String[]>();
	
	}
	public static Object[][] getMasivNuclideAktiv(){
		String[][] str = new String[listNuclideAkv.size()][4];
		for (int i = 0; i < listNuclideAkv.size(); i++) {
			str[i][0] = listNuclideAkv.get(i).substring(4,16).trim();
			str[i][1] = listNuclideAkv.get(i).substring(25,40).trim();
			str[i][2] = listNuclideAkv.get(i).substring(40,55).trim();
			str[i][3] = listNuclideAkv.get(i).substring(70).trim();
			
		}
		return str;
		
	}
	public static Object[][] getMasivNuclideMDA(){
		String[][] str1 = new String[listNuclideMDA.size()][2];
		int k=0;
		for (int i = 0; i < listNuclideMDA.size(); i++) {
			if(listNuclideMDA.get(i).substring(4,16).trim().length()>0){
			str1[k][0] = listNuclideMDA.get(i).substring(4,16).trim();
				str1[k][1] = listNuclideMDA.get(i).substring(50,65).trim();
				k++;	
			}
		}
		String[][] newStr = new String[k][2];
		for (int i = 0; i < newStr.length; i++) {
			newStr[i][0]=str1[i][0];
			newStr[i][1]=str1[i][1];
		}	
		return newStr;
		
	}
	
 	
	private static String [] CreadMasiveFromReadFile(String FILENAME) {
	BufferedReader br = null;
	FileReader fr = null;
	String [] stringArray = new String[1000];
	try {
		//br = new BufferedReader(new FileReader(FILENAME));
		fr = new FileReader(FILENAME);
		br = new BufferedReader(fr);
		String sCurrentLine;
		int i=0;
		while ((sCurrentLine = br.readLine()) != null) {
			stringArray[i]=sCurrentLine;
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


 	private static void creadDataFromTextLine(String[] stringArray) {
	int flagNuclidy = 0;
	int countLineToNuclide = 0;
		
	String [][] stringLine = new String[countLine][];
	 listNuclideAkv = new ArrayList<String>();
	 listNuclideMDA = new ArrayList<String>();
	
				
	for (int j = 0; j < countLine; j++) {
		stringLine[j] = StringUtils.split(stringArray[j]);
			if(flagNuclidy==1){
			countLineToNuclide++;
			if(countLineToNuclide>2){
				if(stringLine[j].length!=0){
				listNuclideAkv.add(stringArray[j]);
				}else{
					flagNuclidy++;	
				}
			}
		}else{
			if(flagNuclidy==3){
				countLineToNuclide++;
				if(countLineToNuclide>2){
					if(stringLine[j].length!=0){
						listNuclideMDA.add(stringArray[j]);
					}else{
						flagNuclidy++;	
					}
				}
			}else{
	if(stringLine[j].length>0){
	switch (stringLine[j][0]) {
	case "Описание":
		cod_sample = stringLine[j][3];
		break;
	case "Извършил":
		user_mesure = stringLine[j][3];
		break;
	case "Количество":
		quantity = stringLine[j][3];
		if(stringLine[j].length>4){
			 dimension = stringLine[j][4];
		}
		break;
	case "Неопределеност":
		 uncertainy = stringLine[j][1];
		break;
	case "Kалибровка":
		 T_S_I = stringLine[j][3];
		break;
	case "Нуклид":
	{flagNuclidy++;
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