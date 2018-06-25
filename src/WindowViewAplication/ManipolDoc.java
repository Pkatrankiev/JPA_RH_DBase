package WindowViewAplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class ManipolDoc {
private static WordprocessingMLPackage template = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			template = GenerateWordDoc.getTemplate("E:\\123.docx");
		} catch (FileNotFoundException | Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GenerateWordDoc.replacePlaceholder(template,"33333333333","$$request_date$$");
		
		try {
			GenerateWordDoc.writeDocxToStream(template, "D:\\temp12.docx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void MapTable(){
		Map<String,String> repl1 = new HashMap<String, String>();
		repl1.put("SJ_FUNCTION", "function1");
		repl1.put("SJ_DESC", "desc1");
		repl1.put("SJ_PERIOD", "period1");

		Map<String,String> repl2 = new HashMap<String, String>();
		repl2.put("SJ_FUNCTION", "function2");
		repl2.put("SJ_DESC", "desc2");
		repl2.put("SJ_PERIOD", "period2");

		Map<String,String> repl3 = new HashMap<String, String>();
		repl3.put("SJ_FUNCTION", "function3");
		repl3.put("SJ_DESC", "desc3");
		repl3.put("SJ_PERIOD", "period3");

		try {
			GenerateWordDoc.replaceTable(new String[]{"SJ_FUNCTION","SJ_DESC","SJ_PERIOD"}, Arrays.asList(repl1,repl2,repl3), template);
		} catch (Docx4JException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
