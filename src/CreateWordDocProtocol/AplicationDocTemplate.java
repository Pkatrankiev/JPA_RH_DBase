package CreateWordDocProtocol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;


import org.docx4j.TextUtils;
import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Body;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;


public class AplicationDocTemplate {
	

	// zarejdame dokumenta
	public static WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {
		WordprocessingMLPackage template = null;
		try {
			template = WordprocessingMLPackage.load(new FileInputStream(new File(name)));
		} catch (FileNotFoundException | Docx4JException e) {
			JOptionPane.showMessageDialog(null, "Не намирам тамплет-документа", "Грешка в данните",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return template;
	}

	// izvlichame vsichki elementi
	public static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
		List<Object> result = new ArrayList<Object>();
		if (obj instanceof JAXBElement)
			obj = ((JAXBElement<?>) obj).getValue();

		if (obj.getClass().equals(toSearch))
			result.add(obj);
		else if (obj instanceof ContentAccessor) {
			List<?> children = ((ContentAccessor) obj).getContent();
			for (Object child : children) {
				result.addAll(getAllElementFromObject(child, toSearch));
			}

		}
		return result;
	}

	// izvlichane na paragraph za nov red
	public static P getTemplateParagraph(WordprocessingMLPackage template, String value) {
		List<Object> paragraph = getAllElementFromObject(template.getMainDocumentPart(), P.class);
		P tempParagraph = null;
		
		int k =0;
		for (Object parag : paragraph) {
			String paragText = parag.toString();
			
			if (paragText.indexOf(value)>=0) {
				System.out.println(k+"??????????? "+parag.toString());
				tempParagraph = (P) parag;
				return tempParagraph;
			}
k++;
		}
		return tempParagraph;

	}

	// iztrivane na paragraph
	public static P removeTemplateParagraph(WordprocessingMLPackage template, String value) {
		List<Object> paragraph = getAllElementFromObject(template.getMainDocumentPart(), P.class);
		P tempParagraph = null;
		for (Object parag : paragraph) {
			String paragText = parag.toString();
			if (paragText.indexOf(value)>=0) {
				tempParagraph = (P) parag;

				((ContentAccessor) tempParagraph.getParent()).getContent().remove(tempParagraph);
			}

		}
		return tempParagraph;

	}

	// iztrivane na tablica
		public static void removeTable(WordprocessingMLPackage template, Tbl table) {
			Body body = template.getMainDocumentPart().getJaxbElement().getBody();
			body.getContent().remove(table.getParent());
				}

	// popalvane na paragraph
	public static void replaceParagraph(P paragraph, Map<String, String> replacements) {
		List<?> textElements = getAllElementFromObject(paragraph, Text.class);
		for (Object text : textElements) {

			// Text text = (Text) object;
			String replacementValue = replacements.get(((Text) text).getValue());
			System.out.println(((Text) text).getValue() + "  " + replacementValue);
			if (replacementValue != null)
				((Text) text).setValue(replacementValue);

		}
	}
	
	// popalvane na tablica
		public static void replaceTable(Tbl paragraph, Map<String, String> replacements) {
			List<?> textElements = getAllElementFromObject(paragraph, Text.class);
			for (Object text : textElements) {

				// Text text = (Text) object;
				String replacementValue = replacements.get(((Text) text).getValue());
				System.out.println(text.toString() + "  " + replacementValue);
				if (replacementValue != null)
					((Text) text).setValue(replacementValue);

			}
		}

	// zamestvame
	public static void replaceBasicValueInDoc(WordprocessingMLPackage template, Map<String, String> replacements) {
		List<?> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);

		for (Object text : texts) {
//			Text textElement = (Text) text;

			String replacementValue = replacements.get(((Text) text).getValue());
			System.out.println(text.toString() + "  " + replacementValue);
			if (replacementValue != null)
				((Text) text).setValue(replacementValue);
		}
	}

	// zapisvane novia fail
	public static void writeDocxToStream(WordprocessingMLPackage template, String target)
			throws IOException, Docx4JException {
		File f = new File(target);

		try {
			template.save(f);
			
		} catch (Docx4JException e) {
			
			e.printStackTrace();
		}
	}

	public static Map<String, String> createEmptiMap(String str) {
	    Map<String,String> myMap = new HashMap<String,String>();
	    myMap.put(str, "");
	    return myMap;
	}
	
	public static Map<String, String> createReplaceMap(String key, String str) {
	    Map<String,String> myMap = new HashMap<String,String>();
	    myMap.put(key, str);
	    return myMap;
	}
	
	public static Tr getRowEqualsText(List<Object> rows, String templateKey) {
		for (Object row : rows) {
			List<Object> text = getAllElementFromObject(row, Text.class);
			for (Object str : text) {
				Text textElement = (Text) str;
				if (textElement.getValue() != null && textElement.getValue().equals(templateKey))
					return (Tr) row;
			}
		}
		
		return null;
	}

	// popalwane na nova tablica
	public static void replaceInNewTable(WordprocessingMLPackage template, Tbl tempTable, Tr templateRow,
			Map<String, String> textToAdd) throws Docx4JException, JAXBException {

		tempTable = creatTable(template);

		for (int i = 0; i < 5; i++) {
			addRowToTable(tempTable, templateRow, textToAdd);
		}
	}

	// vmakvane na tablica sled opredelen text
	@SuppressWarnings("deprecation")
	static void insertTable(WordprocessingMLPackage pkg, String afterText, Tbl table) throws Exception {
		Body b = pkg.getMainDocumentPart().getJaxbElement().getBody();
		int addPoint = -1, count = 0;
		for (Object o : b.getEGBlockLevelElts()) {
			if (o instanceof P && getElementText(o).startsWith(afterText)) {
				addPoint = count + 1;
				break;
			}
			count++;
		}
		if (addPoint != -1)
			b.getEGBlockLevelElts().add(addPoint, table);
		else {
			// didn't find paragraph to insert after...
		}
	}

	// vmakvane na paragraph sled opredelen text
	@SuppressWarnings("deprecation")
	static void insertParagraph(WordprocessingMLPackage pkg, String afterText, P parag) throws Exception {
		Body b = pkg.getMainDocumentPart().getJaxbElement().getBody();
		int addPoint = -1, count = 0;
		for (Object o : b.getEGBlockLevelElts()) {
			if (o instanceof P && getElementText(o).startsWith(afterText)) {
				addPoint = count + 1;
				break;
			}
			count++;
		}
		if (addPoint != -1)
			b.getEGBlockLevelElts().add(addPoint, parag);
		else {
			// didn't find paragraph to insert after...
		}
	}
	
	// vmakvane na Text sled opredelen text
	@SuppressWarnings("deprecation")
	static void insertTextInParagraph(P parag, String afterText, Text text) throws Exception {
		List<Object> listText = getAllElementFromObject(parag, Text.class);
		int addPoint = -1, count = 0;
		for (Object o : listText) {
			if (o instanceof Text && getElementText(o).startsWith(afterText)) {
				addPoint = count + 1;
				break;
			}
			count++;
		}
		if (addPoint != -1)
			parag.getParagraphContent().add(addPoint, text);
		else {
			// didn't find paragraph to insert after...
		}
	}

	public static String getElementText(Object jaxbElem) throws Exception {
		StringWriter sw = new StringWriter();
		TextUtils.extractText(jaxbElem, sw);
		return sw.toString();
	}

	// izvlichane na osnovnite redove ot tablicate
//	@SuppressWarnings("deprecation")
	public static List<Tr> getListRowFromTamplate(Tbl tempTable) throws Docx4JException, JAXBException {

		List<Tr> listTempRow = new ArrayList<Tr>();

		List<Object> rows = getAllElementFromObject(tempTable, Tr.class);
		for (int i = 0; i < rows.size(); i++) {
			listTempRow.add((Tr) rows.get(i));
		}
		return listTempRow;
	}

	// dobavqne na nov red kym tablicata
	public static void addRowToTable(Tbl reviewtable, Tr templateRow, Map<String, String> replacements) {
		Tr workingRow = (Tr) XmlUtils.deepCopy(templateRow);
		List<?> textElements = getAllElementFromObject(workingRow, Text.class);
		for (Object object : textElements) {

			Text text = (Text) object;
			String replacementValue = (String) replacements.get(text.getValue());
			if (replacementValue != null)
				text.setValue(replacementValue);
		}

		reviewtable.getContent().add(workingRow);
	}
	
	// dobavqne na nov paragraf v dokumenta
		public static void addparagToDoc(WordprocessingMLPackage template, P parag, Map<String, String> replacements) {
			P workingPara = (P) XmlUtils.deepCopy(parag);
			List<?> textElements = getAllElementFromObject(workingPara, Text.class);
			for (Object object : textElements) {

				Text text = (Text) object;
				String replacementValue = (String) replacements.get(text.getValue());
				if (replacementValue != null)
					text.setValue(replacementValue);
			}

			template.getMainDocumentPart().addObject(workingPara);
		}

	public static Tbl getTemplateTable(List<Object> tables, String templateKey) throws Docx4JException, JAXBException {
		for (Iterator<Object> iterator = tables.iterator(); iterator.hasNext();) {
			Object tbl = iterator.next();
			List<?> textElements = getAllElementFromObject(tbl, Text.class);
			for (Object text : textElements) {
				Text textElement = (Text) text;
				if (textElement.getValue() != null && textElement.getValue().equals(templateKey))
					return (Tbl) tbl;
			}
		}
		return null;
	}

	// sazdavane na nova tablica
	public static Tbl creatTable(WordprocessingMLPackage wordMLPackage) {

		// try {
		// wordMLPackage = WordprocessingMLPackage.createPackage();
		// } catch (InvalidFormatException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		ObjectFactory factory = Context.getWmlObjectFactory();

		Tbl table = factory.createTbl();
		// addBorders(table);

		// Tr tr = factory.createTr();

		// table.getContent().add(tr);

		wordMLPackage.getMainDocumentPart().addObject(table);
		// wordMLPackage.save(new java.io.File("src/main/HelloWord133.docx") );
		return table;
	}

	public static void addParagraph(WordprocessingMLPackage wordMLPackage, P paragraph) {

		wordMLPackage.getMainDocumentPart().addObject(paragraph);

	}
	
	public BufferedReader encoding(InputStream inputStream) throws UnsupportedEncodingException {
	return new BufferedReader(new InputStreamReader(inputStream, "Windows-1251"));
	}
	
	 public static String convertStreamToString(String strutf) throws UnsupportedEncodingException {
		  			String c_str ="";
							byte [] b_strutf = strutf.getBytes( "UTF8");
				c_str = new String(b_strutf,"cp1251");
			System.out.println(strutf+" -  "+c_str);
		    return c_str;
		  }
	
		

	
	public static void addTable(WordprocessingMLPackage wordMLPackage, Tbl table) {

		wordMLPackage.getMainDocumentPart().addObject(table);

	}
}
