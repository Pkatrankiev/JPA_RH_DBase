package ManagementBasicClassTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import GlobalVariable.ResourceLoader;

public class ReadFailHelpWindow {

	public static String  CreadMasiveFromReadFile() {
		BufferedReader br = null;
//		FileReader fr = null;
		String listString = "";
				
		try {
			
			String respath = "TEMPLATES_DIRECTORY\\Global_Variable.txt";
			try {	
			File fileDir = new File(respath);

//			br = new BufferedReader(new InputStreamReader(, "Cp1251"));
			
			
			InputStream in = new FileInputStream(fileDir);
			br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			
			String sCurrentLine;
		
			while ((sCurrentLine = br.readLine()) != null) {
				
				listString += sCurrentLine;
				
				
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
		return listString;
	
	}
	
	
	
	
}
