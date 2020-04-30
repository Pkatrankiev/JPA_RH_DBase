package GlobalVariable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFileWithGlobalTextVariable {

	private static 	Map<String, String> globalTextVariableMap;
	private static 	Map<String, String> globalIntVariableMap;
	private static 	Map<String, String> globalDBasePersisLokalMap;
	private static 	Map<String, String> globalDBasePersisRemoteMap;
	
	
	

	public static void  CreadMasiveFromReadFile() {
		BufferedReader br = null;
		FileReader fr = null;
		List<String> listString = new ArrayList<String>();
		globalTextVariableMap = new HashMap<String, String>();
		globalIntVariableMap = new HashMap<String, String>();
		globalDBasePersisLokalMap = new HashMap<String, String>();
		globalDBasePersisRemoteMap = new HashMap<String, String>();
		
		try {

			File fileDir = new File("ICONS/Global Variable.txt");

	        br = new BufferedReader(
	           new InputStreamReader(new FileInputStream(fileDir),"Cp1251"));
			String sCurrentLine;
			String flagTypeValue = "";
			int index;
			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.contains("#")){
					flagTypeValue = sCurrentLine;
				}
				listString.add(sCurrentLine);
				
				index = sCurrentLine.indexOf("=");
				System.out.println(sCurrentLine+"   "+index +"    "+flagTypeValue);
				if(index >0){
						
					switch (flagTypeValue) {
					
					case "#Sring":
						
						globalTextVariableMap.put(sCurrentLine.substring(0, index).trim(), sCurrentLine.substring(index+1).trim());
						break;
					case "#int":
						
						globalIntVariableMap.put(sCurrentLine.substring(0, index).trim(), sCurrentLine.substring(index+1).trim());
						break;
					case "#DBasePersisLokal":
							
						globalDBasePersisLokalMap.put(sCurrentLine.substring(0, index).trim(), sCurrentLine.substring(index+1).trim());
						break;
						
					case "#DBasePersisRemote":
						
						globalDBasePersisRemoteMap.put(sCurrentLine.substring(0, index).trim(), sCurrentLine.substring(index+1).trim());
						break;
					}
				
				
				System.out.println(sCurrentLine);
				System.out.println(sCurrentLine.substring(0, index)+"  -  "+sCurrentLine.substring(index+1).trim());
				}
			}
			
			System.out.println("++++++++++++++++++++++");	
			for (String entry : globalDBasePersisRemoteMap.values()) {
				System.out.println(entry);	
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
	
	}
	
	public static Map<String, String> getGlobalIntVariableMap() {
		return globalIntVariableMap;
	}

	public static void setGlobalIntVariableMap(Map<String, String> globalIntVariableMap) {
		ReadFileWithGlobalTextVariable.globalIntVariableMap = globalIntVariableMap;
	}

	public static Map<String, String> getGlobalTextVariableMap() {
		return globalTextVariableMap;
	}

	public static void setGlobalTextVariableMap(Map<String, String> globalTextVariableMap) {
		ReadFileWithGlobalTextVariable.globalTextVariableMap = globalTextVariableMap;
	}
	public static Map<String, String> getGlobalDBasePersisLokalMap() {
		return globalDBasePersisLokalMap;
	}

	public static void setGlobalDBasePersisLokalMap(Map<String, String> globalDBasePersisLokalMap) {
		ReadFileWithGlobalTextVariable.globalDBasePersisLokalMap = globalDBasePersisLokalMap;
	}

	public static Map<String, String> getGlobalDBasePersisRemoteMap() {
		return globalDBasePersisRemoteMap;
	}

	public static void setGlobalDBasePersisRemoteMap(Map<String, String> globalDBasePersisRemotMap) {
		ReadFileWithGlobalTextVariable.globalDBasePersisRemoteMap = globalDBasePersisRemotMap;
	}	
	
}
