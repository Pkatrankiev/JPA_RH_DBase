package OldClases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadTextFile {

	private static int countLine;
	
	
	public static String[] CreadMasiveFromReadFile(String FILENAME) {
		ArrayList<String> listStr = new ArrayList<>();
		BufferedReader br = null;
		FileReader fr = null;
		List<String> listString = new ArrayList<String>();
		String[] newStringArray = null ;
		try {
			// br = new BufferedReader(new FileReader(FILENAME));
//			fr = new FileReader(FILENAME);
//			br = new BufferedReader(fr);
			File fileDir = new File(FILENAME);

	        br = new BufferedReader(
	           new InputStreamReader(new FileInputStream(fileDir),"Cp1251"));
			String sCurrentLine;
			
			while ((sCurrentLine = br.readLine()) != null) {
				listString.add(sCurrentLine);
				System.out.println(sCurrentLine);
				
			}
						
			countLine = listString.size();
			newStringArray = new String[countLine];
			for (int i = 0; i < newStringArray.length; i++) {
				newStringArray[i]=listString.get(i);
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

	public static  void WriteStringToFile (String fileName, String textToFile){
	    try {
	    	File myObj = new File(fileName);
	        if (myObj.createNewFile()) {
	          System.out.println("File created: " + myObj.getName());
	        } else {
	          System.out.println("File already exists.");
	        }
	        FileWriter myWriter = new FileWriter(fileName);
	        myWriter.write(textToFile);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file.");
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
	
	public static String CreadStringToFileFromMasive (String[] masiveTextToFile){
		String textToFile = "";
		for (String string : masiveTextToFile) {
			textToFile += string+"\n";
		}
		
		return textToFile;
		
	}
	
	
	public static  void convertTextFromFilenameToTextInNewFilename(String fileName, String newFileName){
		String textToFile = "";
		String[] masive =  CreadMasiveFromReadFile(fileName);
	
		for (String string : masive) {
			for (String str : string.trim().split(" ")){
				textToFile += str+"\n";
		
			}
		}
		
		
			
			System.out.println(textToFile);
		
	
		WriteStringToFile (newFileName, textToFile);
	}
}
