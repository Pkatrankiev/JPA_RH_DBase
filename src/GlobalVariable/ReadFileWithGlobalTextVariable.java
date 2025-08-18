package GlobalVariable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import java.nio.charset.*;

public class ReadFileWithGlobalTextVariable  {

	private static 	Map<String, String> globalTextVariableMap;
	private static 	Map<String, String> globalIntVariableMap;
	private static 	Map<String, String> globalDBasePersisLokalMap;
	private static 	Map<String, String> globalDBasePersisRemoteMap;
	
	
	

	public static void  CreadMasiveFromReadFile() {
		BufferedReader br = null;
//		FileReader fr = null;
		List<String> listString = new ArrayList<String>();
		globalTextVariableMap = new HashMap<String, String>();
		globalIntVariableMap = new HashMap<String, String>();
		globalDBasePersisLokalMap = new HashMap<String, String>();
		globalDBasePersisRemoteMap = new HashMap<String, String>();
		
		try {
			
			String respath = "TEMPLATES_DIRECTORY\\Global_Variable.txt";
			try {	
			File fileDir = new File(respath);

//			br = new BufferedReader(new InputStreamReader(, "Cp1251"));
			
			
			InputStream in = new FileInputStream(fileDir);
			
			 Charset charset = detectCharset(fileDir);
			 br = new BufferedReader(new InputStreamReader(in, charset));
			 
//			br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			
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
			} catch (FileNotFoundException e) {
				ResourceLoader.appendToFile(e);
				JOptionPane.showMessageDialog(null, "Не намирам: TEMPLATES_DIRECTORY \\ Global_Variable.txt", "Грешни данни",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	} catch (IOException  e) {
		ResourceLoader.appendToFile(e);
		e.printStackTrace();
	} finally {
		try {
			if (br != null)
				br.close();
			
		} catch (IOException ex) {
			ResourceLoader.appendToFile(ex);
			ex.printStackTrace();
		}
	}
	
	}
	
	private static Charset detectCharset(File file) throws IOException {
        // Първо пробваме UTF-8
        if (isDecodable(file, StandardCharsets.UTF_8)) {
            return StandardCharsets.UTF_8;
        }
        // После Windows-1251
        Charset win1251 = Charset.forName("windows-1251");
        if (isDecodable(file, win1251)) {
            return win1251;
        }
        // Ако не пасва - по подразбиране UTF-8
        return StandardCharsets.UTF_8;
    }

	private static boolean isDecodable(File file, Charset charset) throws IOException {
        CharsetDecoder decoder = charset.newDecoder()
                .onMalformedInput(CodingErrorAction.REPORT)
                .onUnmappableCharacter(CodingErrorAction.REPORT);

        try (InputStream in = new FileInputStream(file);
             Reader reader = new InputStreamReader(in, decoder)) {
            char[] buffer = new char[4096];
            while (reader.read(buffer) != -1) {
                // Ако има грешка, ще влезе в catch
            }
            return true;
        } catch (CharacterCodingException e) {
            return false;
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
