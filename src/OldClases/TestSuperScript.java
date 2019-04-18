package OldClases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Body;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.CTVerticalAlignRun;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STVerticalAlignRun;
import org.docx4j.wml.Text;

import Aplication.NuclideDAO;
import CreateWordDocProtocol.AplicationDocTemplate;
import CreateWordDocProtocol.FunctionForGenerateWordDocFile;
import CreateWordDocProtocol.GenerateRequestWordDoc;
import DBase_Class.Nuclide;
import WindowView.RequestViewFunction;

public class TestSuperScript {

	public static void GenerateProtokolWordDoc(String nameTaplateProtokol) {
		nameTaplateProtokol = FunctionForGenerateWordDocFile.get_TEMPLATE_DIRECTORY_ROOT() + nameTaplateProtokol;
		// zarejdame dokumenta
		WordprocessingMLPackage template = null;
		try {
			template = AplicationDocTemplate.getTemplate(nameTaplateProtokol);
		} catch (FileNotFoundException | Docx4JException e) {
			e.printStackTrace();
		}
		String textString = "Текст1 текст2 60Co текст3 137Cs текст4";
		List<Nuclide> list_Nuclide = NuclideDAO.getInListAllValueNuclide();
		GenerateMapForRequestWordDocument();

		AplicationDocTemplate.getTemplateParagraph(template, "Sgfsg");

		P pargraphTemplateSuperScript = AplicationDocTemplate.getTemplateParagraph(template, "$$3$$");
		AplicationDocTemplate.getTemplateParagraph(template, "$$6$$");
		AplicationDocTemplate.removeTemplateParagraph(template, "$$6$$");
		P pargraphTemplateText = AplicationDocTemplate.getTemplateParagraph(template, "$$8$$");
//		pargraphTemplateText.set
//		AplicationDocTemplate.removeTemplateParagraph(template, "$$8$$");
//		AplicationDocTemplate.replaceBasicValueInDoc(template, substitutionData);

		getText(pargraphTemplateSuperScript, "$$4$$");

		org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();

		// ************************************************************************
		org.docx4j.wml.RPr rpr=null;
		org.docx4j.wml.BooleanDefaultTrue b = new org.docx4j.wml.BooleanDefaultTrue();

		String[][] masiveText = createMasiveText(textString, list_Nuclide);
		
		for (int i = 0; i < masiveText.length; i++) {
			
		if(masiveText[i][1].equals("n")) {
		 rpr = newRunSuperscript(pargraphTemplateText, factory, b, masiveText[i][0]);
		}else {
		 rpr = newRunNormalscript(pargraphTemplateText, factory, b, masiveText[i][0]);
	}
		}
		org.docx4j.wml.PPr ppr = factory.createPPr();
		pargraphTemplateText.setPPr(ppr);
		org.docx4j.wml.ParaRPr paraRpr = factory.createParaRPr();
		ppr.setRPr(paraRpr);

		
		rpr.setB(b);
		template.getMainDocumentPart().addObject(pargraphTemplateText);

		// *******************************************************************

		try {
			String newNameProtokol = "Test_" + RequestViewFunction.DateNaw(false) + ".docx";
			AplicationDocTemplate.writeDocxToStream(template,
					FunctionForGenerateWordDocFile.get_destinationDir() + newNameProtokol);

			GenerateRequestWordDoc.openWordDoc(FunctionForGenerateWordDocFile.get_destinationDir() + newNameProtokol);

		} catch (IOException e) {

			e.printStackTrace();
		} catch (Docx4JException e) {

			e.printStackTrace();
		}
		
	}

	private static String[][] createMasiveText(String textString, List<Nuclide> list_Nuclide) {
		List<String> arrayText = new ArrayList<String>();
		List<String> arrayNum = new ArrayList<String>();
		String text = "";
		String[] ssss = textString.split(" ");
		for (int i = 0; i < ssss.length; i++) {
			if (notNuclide(ssss[i], list_Nuclide)) {
				text = text + ssss[i] + " ";
			} else {
				arrayText.add(text);
				arrayNum.add("t");
				String[] nuclide = new String[] { "", "" };
				nuclide = FunctionForGenerateWordDocFile.getNumberFromNuclide(text);
				arrayText.add(nuclide[0]);
				arrayNum.add("n");

				arrayText.add(nuclide[1] + " ");
				arrayNum.add("t");

			}

		}

		String[][] masiveText = new String[arrayText.size()][2];
		for (int i = 0; i < arrayText.size(); i++) {
			masiveText[i][0] = arrayText.get(i);
			masiveText[i][1] = arrayNum.get(i);
		}

		return masiveText;
	}

	private static boolean notNuclide(String string, List<Nuclide> list_Nuclide) {

		for (Nuclide nuclide : list_Nuclide) {
			if (string.equals(nuclide.getSymbol_nuclide())) {
				return false;
			}
		}
		return true;
	}

	private static org.docx4j.wml.RPr newRunSuperscript(P pargraphTemplateText, org.docx4j.wml.ObjectFactory factory,
			org.docx4j.wml.BooleanDefaultTrue b, String str) {

		org.docx4j.wml.Text t = factory.createText();
		t.setValue(str);
		org.docx4j.wml.R run = factory.createR();
		run.getContent().add(t);

		pargraphTemplateText.getContent().add(run);

		org.docx4j.wml.RPr rpr = factory.createRPr();

		CTVerticalAlignRun vAlign = Context.getWmlObjectFactory().createCTVerticalAlignRun();
		vAlign.setVal(STVerticalAlignRun.SUPERSCRIPT);

		rpr.setVertAlign(vAlign);

		run.setRPr(rpr);
		return rpr;
	}

	private static org.docx4j.wml.RPr newRunNormalscript(P pargraphTemplateText, org.docx4j.wml.ObjectFactory factory,
			org.docx4j.wml.BooleanDefaultTrue b, String str) {
		org.docx4j.wml.Text t = factory.createText();
		t.setValue(str);
		org.docx4j.wml.R run = factory.createR();
		run.getContent().add(t);

		pargraphTemplateText.getContent().add(run);

		org.docx4j.wml.RPr rpr = factory.createRPr();

		b.setVal(false);

		run.setRPr(rpr);
		return rpr;
	}

	static Map<String, String> generateNuclideMap(Nuclide nucl) {

		Map<String, String> substitutionData = new HashMap<String, String>();

		String[] nuclide = new String[] { "", "" };

		nuclide = FunctionForGenerateWordDocFile.getNumberFromNuclide(nucl.getSymbol_nuclide());
		substitutionData.put("$$6$$", nuclide[0]);
		substitutionData.put("$$7$$", nuclide[1]);
		return substitutionData;

	}

//	public org.docx4j.wml.P createStyledParagraphOfText(String styleId, String text) {
//		
//		org.docx4j.wml.P p = createParagraphOfText(text);
//						
//		StyleDefinitionsPart styleDefinitionsPart = this.getStyleDefinitionsPart();
//
//		if (getPropertyResolver().activateStyle(styleId)) {
//			// Style is available 
//			org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();			
//			org.docx4j.wml.PPr  pPr = factory.createPPr();
//			p.setPPr(pPr);
//			org.docx4j.wml.PPrBase.PStyle pStyle = factory.createPPrBasePStyle();
//			pPr.setPStyle(pStyle);
//			pStyle.setVal(styleId);
//		} 		
//		
//		return p;
//
//	}

	static Map<String, String> generateTextMap(String key, String text) {

		Map<String, String> substitutionData = new HashMap<String, String>();

		substitutionData.put(key, text);
		return substitutionData;
	}

	public static Text getText(P pargraphTemplateSuperScript, String templateKey) {

		List<Object> txtWithSuperScript = AplicationDocTemplate.getAllElementFromObject(pargraphTemplateSuperScript,
				Text.class);
		for (Object txt : txtWithSuperScript) {
			Text textElement = (Text) txt;
			if (textElement.getValue() != null && textElement.getValue().equals(templateKey))
				return (Text) txt;

		}

		return null;
	}

	public static void addParagraph(WordprocessingMLPackage wordMLPackage, P paragraph) {

		wordMLPackage.getMainDocumentPart().addObject(paragraph);

	}

	// vmakvane na tablica sled opredelen text
	@SuppressWarnings("deprecation")
	static void insertText(WordprocessingMLPackage pkg, String afterText, Text text) throws Exception {
		Body b = pkg.getMainDocumentPart().getJaxbElement().getBody();
		int addPoint = -1, count = 0;
		for (Object o : b.getEGBlockLevelElts()) {
			System.out.println(AplicationDocTemplate.getElementText(o));
			if (o instanceof Text && AplicationDocTemplate.getElementText(o).startsWith(afterText)) {
				addPoint = count + 1;
				break;
			}
			count++;
		}
		if (addPoint != -1)
			b.getEGBlockLevelElts().add(addPoint, text);
		else {
			// didn't find paragraph to insert after...
		}
	}

//		public String getText(WordprocessingMLPackage pkg) {
//		    if (pkg == null) {
//		        throw new NullPointerException();
//		    }
//
//		    MainDocumentPart documentPart = pkg.getMainDocumentPart();
//		    org.docx4j.wml.Document wmlDocumentEl = (org.docx4j.wml.Document) documentPart
//		        .getJaxbElement();
//		    Body body = wmlDocumentEl.getBody();
//
//		    return getTextOfObject(body);
//		}

	public static Map<String, String> GenerateMapForRequestWordDocument() {

		Map<String, String> substitutionData = new HashMap<String, String>();

		substitutionData.put("$$1$$", "Test1");
		substitutionData.put("$$2$$", "Test2");
		substitutionData.put("$$3$$", "Test3");
		substitutionData.put("$$4$$", "Test4");
		substitutionData.put("$$5$$", "Test5");
		substitutionData.put("$$6$$", "Test6");
		substitutionData.put("$$8$$", "Текст1 текст2 60Co текст3 137Cs текст4");

		return substitutionData;
	}

	public static void WordDocument(WordprocessingMLPackage pkg) {
		org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
		org.docx4j.wml.P p = factory.createP();
		// Create object for first run
		R r = factory.createR();
		p.getContent().add(r);

		// Create object for rPr
		RPr rpr = factory.createRPr();
		r.setRPr(rpr);
		// Create object for b
		BooleanDefaultTrue booleandefaulttrue = factory.createBooleanDefaultTrue();
		rpr.setB(booleandefaulttrue);

		// Create object for t (wrapped in JAXBElement)
		Text text = factory.createText();
		JAXBElement<org.docx4j.wml.Text> textWrapped = factory.createRT(text);
		r.getContent().add(textWrapped);
		text.setValue("Name:");

		// Create object for second run
		R r2 = factory.createR();
		p.getContent().add(r2);

		// Create object for rPr
		RPr rpr2 = factory.createRPr();
		r2.setRPr(rpr2);

		// Create object for t (wrapped in JAXBElement)
		Text text2 = factory.createText();
		JAXBElement<org.docx4j.wml.Text> textWrapped2 = factory.createRT(text2);
		r2.getContent().add(textWrapped2);
		text2.setValue(" something");
		text2.setSpace("preserve");
	}

}
