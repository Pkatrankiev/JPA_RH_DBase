package table;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.log4j.BasicConfigurator;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


public class StartReplaceTemplateWord {
	
private static WordprocessingMLPackage template = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		BasicConfigurator.configure();
//		log.info("This is Logger Info");
		
		try {
			template =  GenerateWordDocTemplate.getTemplate("123.docx");
		} catch (FileNotFoundException | Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 GenerateWordDocTemplate.replacePlaceholder(template,"33333333333","$$request_date$$");
		MapTable();
		try {
			 GenerateWordDocTemplate.writeDocxToStream(template, "temp12.docx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void MapTable(){
		ArrayList<Map<String,String>> listValue = new ArrayList<Map<String,String>>();
		Map<String,String> repl1 = new HashMap<String, String>();
		for (int i = 0; i < 40; i++) {
		
		repl1.put("SJ_FUNCTION", "function1");
		repl1.put("SJ_DESC", "desc1");
		repl1.put("SJ_PERIOD", "period1");
		repl1.put("SJ_PERIOD3", "gamma");

		listValue.add(repl1);
		
		}

		try {
			 GenerateWordDocTemplate.replaceTable(new String[]{"SJ_FUNCTION","SJ_DESC","SJ_PERIOD"}, listValue, template);
		} catch (Docx4JException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}

