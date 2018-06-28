package table;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.log4j.BasicConfigurator;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tr;

public class StartGenerateDocTemplate {

	public static void main(String[] args) {
		BasicConfigurator.configure();

		ArrayList<Map<String, String>> listValue = new ArrayList<Map<String, String>>();
		Map<String, String> repl1 = new HashMap<String, String>();
		for (int i = 0; i < 40; i++) {
			repl1.put("SJ_FUNCTION", "function1");
			repl1.put("SJ_DESC", "desc1");
			repl1.put("SJ_PERIOD", "period1");
			repl1.put("SJ_PERIOD3", "gamma");
			listValue.add(repl1);
		}

		String[] colummVariable = new String[] { "SJ_FUNCTION", "SJ_DESC", "SJ_PERIOD" };

		WordprocessingMLPackage template = null;
		try {
			template = GenerateWordDocTemplate.getTemplate("123.docx");
		} catch (FileNotFoundException | Docx4JException e) {
			e.printStackTrace();
		}

		GenerateWordDocTemplate.replacePlaceholder(template, "33333333333", "$$request_date$$");

		Tbl tempTable = GenerateWordDocTemplate.MapTable(template, listValue, colummVariable);
		
		java.util.List<Tr> listRow =null;
		try {
			listRow = GenerateWordDocTemplate.getTemplataRow(colummVariable, tempTable);
		} catch (Docx4JException | JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		GenerateWordDocTemplate.addParagraph(template, GenerateWordDocTemplate.getTemplateParagraph(template));
		
		
		try {
			GenerateWordDocTemplate.replaceInNewTable(template, tempTable, listRow, listValue	);
		} catch (Docx4JException | JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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

}
