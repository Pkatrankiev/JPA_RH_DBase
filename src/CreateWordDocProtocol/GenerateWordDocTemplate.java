package CreateWordDocProtocol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.docx4j.TextUtils;
import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Body;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;

public class GenerateWordDocTemplate {
	public static Map<String, String> getMap() {

		Map<String, String> repl1 = new HashMap<String, String>();

		repl1.put("gamma", "Гама радионуклиди");
		return repl1;
	}

	// zarejdame dokumenta
	public static WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {
		WordprocessingMLPackage template = null;
		try {
			template = WordprocessingMLPackage.load(new FileInputStream(new File(name)));
		} catch (FileNotFoundException | Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return template;
	}

	// izvlichame vsichki elementi
	private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
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
		for (Object parag : paragraph) {
			String paragText = parag.toString();
			if (paragText.startsWith(value)) {
				tempParagraph = (P) parag;
				// template.getContentType().
			}
			
		}
		return tempParagraph;

	}
	// iztrivane na paragraph 
	public static P removeTemplateParagraph(WordprocessingMLPackage template, String value) {
		List<Object> paragraph = getAllElementFromObject(template.getMainDocumentPart(), P.class);
		P tempParagraph = null;
		for (Object parag : paragraph) {
			String paragText = parag.toString();
			if (paragText.startsWith(value)) {
				tempParagraph = (P) parag;
				((ContentAccessor) template).getContent().remove(tempParagraph);
			}
			
		}
		return tempParagraph;

	}
	
	
	public static void replaceParagraph(P paragraph, Map<String, String> replacements) {
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
	public static void replacePlaceholder(WordprocessingMLPackage template, Map<String, String> replacements) {
		List<?> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);

		for (Object text : texts) {
			Text textElement = (Text) text;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	static Tbl MapTable(WordprocessingMLPackage template, ArrayList<Map<String, String>> listValue,
			String[] colummVariable, List<P> listParag) {
		Tbl tempTable = null;
		try {
			tempTable = replaceTable(colummVariable, listValue, template, listParag);
		} catch (Docx4JException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempTable;
	}

	public static Tbl replaceTable(String[] placeholders, List<Map<String, String>> textToAdd,
		WordprocessingMLPackage template, List<P> listParag) throws Docx4JException, JAXBException {
		
		
		List<Object> tables = getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);
		System.out.println("ddddddddddddddddddddd");
		// 1. find the table
		Tbl tempTable = getTemplateTable(tables, placeholders[0]);
		List<Object> rows = getAllElementFromObject(tempTable, Tr.class);
		Tbl newTempTable = new Tbl();
		Tr headerRow1 = (Tr) rows.get(0);
		Tr headerRow2 = (Tr) rows.get(1);
		Tr headerRow3 = (Tr) rows.get(2);
		Tr templateRow = (Tr) rows.get(3);
		tempTable.getContent().remove(templateRow);
		Boolean fl = true;
		tempTable.getContent().remove(headerRow3);
		if (fl) {

			addRowToTable(tempTable, headerRow3, getMap());
		}
		int k = 0;
		for (Map<String, String> replacements : textToAdd) {
			if (k == 10) {
				
				 

				System.out.println("tttttttttttttttttttttttttttttttttttttttttttttt");
			}
			if (0 == (k % 5) && k != 0) {
	
				addRowToTable(tempTable, headerRow1, replacements);
				addRowToTable(tempTable, headerRow2, replacements);
			
			}

			addRowToTable(tempTable, templateRow, replacements);

			k++;

		}
		return tempTable;
	}

	// popalwane na nova tablica
	public static void replaceInNewTable(WordprocessingMLPackage template, Tbl tempTable, List<Tr> templateRow,
			List<Map<String, String>> textToAdd) throws Docx4JException, JAXBException {

		tempTable = creatTable(template);
		addRowToTable(tempTable, templateRow.get(0), getMap());

		// first row is header, second row is content

		int k = 0;
		for (Map<String, String> replacements : textToAdd) {

			if (0 == (k % 5) && k != 0) {
				addRowToTable(tempTable, templateRow.get(0), replacements);
				addRowToTable(tempTable, templateRow.get(1), replacements);
			}

			addRowToTable(tempTable, templateRow.get(3), replacements);

			k++;

		}
	}

	@SuppressWarnings("deprecation")
	static	void insertTable(WordprocessingMLPackage pkg, String afterText, Tbl table)
		    throws Exception {
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

	@SuppressWarnings("deprecation")
	static	void insertParagraph(WordprocessingMLPackage pkg, String afterText, P parag)
		    throws Exception {
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
//		             didn't find paragraph to insert after...
		        }
		        }
		     
	
	
	
	
	
	static String getElementText(Object jaxbElem) throws Exception {
		        StringWriter sw = new StringWriter();
		        TextUtils.extractText(jaxbElem, sw);
		        return sw.toString();
		    }
		   @SuppressWarnings("deprecation")
			static	void insertParag(WordprocessingMLPackage pkg, String afterText, P table)
				    throws Exception {
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

//				   static String getElementText2(Object jaxbElem) throws Exception {
//				        StringWriter sw = new StringWriter();
//				        TextUtils.extractText(jaxbElem, sw);
//				        return sw.toString();
//				    }
	
	public static List<Tr> getTemplataRow(String[] placeholders, Tbl tempTable, List<P> listParag)
			throws Docx4JException, JAXBException {

		List<Tr> listTempRow = new ArrayList<Tr>();

		List<Object> rows = getAllElementFromObject(tempTable, Tr.class);
		Tbl newTempTable = new Tbl();
		listTempRow.add((Tr) rows.get(0));
		listTempRow.add((Tr) rows.get(1));
		listTempRow.add((Tr) rows.get(2));
		listTempRow.add((Tr) rows.get(3));
		return listTempRow;
	}

	private static void addRowToTable(Tbl reviewtable, Tr templateRow, Map<String, String> replacements) {
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

	private static Tbl getTemplateTable(List<Object> tables, String templateKey) throws Docx4JException, JAXBException {
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

}
