package CreateWordDocProtocol;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBException;
import org.apache.log4j.*;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.*;
import org.docx4j.wml.P;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tr;

import Aplication.IzpitvanPokazatelDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import WindowView.RequestViewFunction;
import WindowViewAplication.DocxMainpulator;

public class StartGenerateDocTemplate {

	private static final String MAIN_DOCUMENT_PATH = "word/document.xml";
	private static final String TEMPLATE_DIRECTORY_ROOT = "TEMPLATES_DIRECTORY/";
	private static final String destinationDir = "DIRECTORY/";

	public static void GenerateProtokolWordDoc(String nameTaplateProtokol, Request recuest,
			Map<String, String> substitutionData) {
		BasicConfigurator.configure();
		int max_tableRow=100;
		nameTaplateProtokol = TEMPLATE_DIRECTORY_ROOT + nameTaplateProtokol;
		String[] masive_column_table_result = new String[] { "$$sample_code$$", "$$sample_metod$$", "$$nuclide$$",
				"$$razmernost$$", "$$value$$", "$$norma$$" };
		ArrayList<Map<String, String>> listValue = new ArrayList<Map<String, String>>();
		Map<String, String> repl = new HashMap<String, String>();
		List<Sample> smple_list = SampleDAO.getListSampleFromColumnByVolume("request", recuest);
		
		int count_Result_In_Protokol = get_count_Result_In_Protokol(smple_list);
		
		
		List<IzpitvanPokazatel> pokazatel_list = IzpitvanPokazatelDAO
				.getListIzpitvan_pokazatelFromColumnByVolume("request", recuest);

		// zarejdame dokumenta
		WordprocessingMLPackage template = null;
		try {
			template = AplicationDocTemplate.getTemplate(nameTaplateProtokol);
		} catch (FileNotFoundException | Docx4JException e) {
			e.printStackTrace();
		}
		// zamestvane na elementite v parvata stranica na documanta
		AplicationDocTemplate.replaceBasicValueInDoc(template, substitutionData);

		P pargraphTemplateProtokol = AplicationDocTemplate.getTemplateParagraph(template, "Протокол от изпитване");
		P pargraphTemplateText = AplicationDocTemplate.getTemplateParagraph(template, "РЕЗУЛТАТИ ОТ ИЗПИТВАНЕТО");
		P pargraphTemplateNewPage = AplicationDocTemplate.getTemplateParagraph(template, "#$%");
		AplicationDocTemplate.removeTemplateParagraph(template, "#$%");
		P pargraphTemplateNewRow = AplicationDocTemplate.getTemplateParagraph(template, "##$$%%");
		AplicationDocTemplate.removeTemplateParagraph(template, "##$$%%");
		
//		ArrayList<P> listParag = new ArrayList<P>();
//		listParag.add(pargraphTemplateProtokol);
//		listParag.add(pargraphTemplateZ);
//		listParag.add(pargraphTemplateT);
		AplicationDocTemplate.replaceParagraph(pargraphTemplateProtokol, substitutionData);

		// izvlichane na tablicite ot documenta
		List<Object> tables = AplicationDocTemplate.getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);

		// find the table
		Tbl tempTable = null;
		
		try {
			tempTable = AplicationDocTemplate.getTemplateTable(tables, masive_column_table_result[0]);
			
			
		} catch (Docx4JException | JAXBException e3) {
			e3.printStackTrace();
		}

		// prochitane na redovete v tablicata
		List<Object> rows = AplicationDocTemplate.getAllElementFromObject(tempTable, Tr.class);

		 TblBorders border = tempTable.getTblPr().getTblBorders();
		Tr headerRow_1 = AplicationDocTemplate.getRowEqualsText(rows, "Код на пробата");
		Tr headerRow_2 = AplicationDocTemplate.getRowEqualsText(rows, "1");
		Tr templateRow_pokazatel = AplicationDocTemplate.getRowEqualsText(rows, "$$request_pokazarel$$");
		Tr templateRow = AplicationDocTemplate.getRowEqualsText(rows, masive_column_table_result[0]);

		tempTable.getContent().remove(templateRow);
		tempTable.getContent().remove(templateRow_pokazatel);
		Tbl basicTable = tempTable;
		AplicationDocTemplate.removeTable(template,(Tbl) tables.get(2));
		AplicationDocTemplate.removeTable(template,(Tbl) tables.get(3));
		
		Map<String, String> repl_request_pokazarel = new HashMap<String, String>();
		List<Results> result_list = new ArrayList<>();
		Map<String, String> repl_results = new HashMap<String, String>();

		Boolean tableEqualsRequestPokazatel = false;
		String pokaz = pokazatel_list.get(0).getPokazatel().getName_pokazatel();
		int maxCounRow = countRowInFirstPege(count_Result_In_Protokol);
		int coutRow = 0;
		if (pokazatel_list.size() == 1) {
			if (pokaz.indexOf("гама") > 0 || pokaz.indexOf("алфа") > 0) {
				repl_request_pokazarel.put("$$request_pokazarel$$", pokaz);
				AplicationDocTemplate.addRowToTable(tempTable, templateRow_pokazatel, repl_request_pokazarel);
				coutRow++;
			}
			for (Sample sample : smple_list) {
				result_list = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
				for (Results result : result_list) {
					if(result.getInProtokol()) {
					repl_results = generateResultsMap(sample, result, masive_column_table_result);
					AplicationDocTemplate.addRowToTable(tempTable, templateRow, repl_results);
					coutRow++;
					}
					if(coutRow>7) {
						coutRow=0;
						
						AplicationDocTemplate.addParagraph(template,pargraphTemplateNewPage);
						AplicationDocTemplate.addParagraph(template, pargraphTemplateProtokol);
						AplicationDocTemplate.replaceParagraph(pargraphTemplateProtokol, substitutionData);
						
						tempTable = AplicationDocTemplate.creatTable(template);
						tempTable.getTblPr().setTblBorders(border);
						AplicationDocTemplate.addRowToTable(tempTable, headerRow_1, repl_results);
						
//						AplicationDocTemplate.addParagraph(template, pargraphTemplateText);
//						ObjectFactory factory = Context.getWmlObjectFactory();
//						
//						P br = factory.createP(); // this Br element is used break the current and go for next line
//						AplicationDocTemplate.addParagraph(template, br);
//						tempTable = basicTable;
//						template.getMainDocumentPart().addObject(tempTable);
//						count_Result_In_Protokol = count_Result_In_Protokol-coutRow;
//						maxCounRow = countRowInFirstPege(count_Result_In_Protokol);
					}
				}

			}
		}

//		try {
//			AplicationDocTemplate.getListRowFromTamplate(tempTable);
//		} catch (Docx4JException | JAXBException e1) {
//
//			e1.printStackTrace();
//		}
		
//		P p = AplicationDocTemplate.getTemplateParagraph(template, "##$$%%");

//		try {
////			GenerateWordDocTemplate.insertParag(template, "##$$%%", pargraphTemplateT );
//			AplicationDocTemplate.insertTable(template, "##$$%%", tempTable);
//			AplicationDocTemplate.insertParagraph(template, "##$$%%", pargraphTemplateZ);
//			AplicationDocTemplate.insertParagraph(template, "##$$%%", pargraphTemplateD);
//			AplicationDocTemplate.insertParagraph(template, "##$$%%", pargraphTemplateT);
//
//		} catch (Exception e2) {
//
//			e2.printStackTrace();
//		}

//		try {
//			
//			GenerateWordDocTemplate.replaceInNewTable(template, tempTable, listRow, listValue	);
//		} catch (Docx4JException | JAXBException e1) {
//		 
//			e1.printStackTrace();
//		}

		try {
			String newNameProtokol = recuest.getRecuest_code()+"_"+ RequestViewFunction.DateNaw(false)+".docx";
			AplicationDocTemplate.writeDocxToStream(template, destinationDir+newNameProtokol);
			
			DocxMainpulator.openWordDoc(destinationDir + newNameProtokol);
			
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Docx4JException e) {

			e.printStackTrace();
		}
	}

	public static int countRowInFirstPege(int count_Result_In_Protokol) {
		int max_tableRow=100;
		if(count_Result_In_Protokol>7 && count_Result_In_Protokol<=10) {
			max_tableRow = count_Result_In_Protokol-2;
		}else {
			if(count_Result_In_Protokol>10) {
				max_tableRow=9;
			}
		}
		return max_tableRow;
	}

	public static int get_count_Result_In_Protokol(List<Sample> smple_list) {
		int count_Result_In_Protokol = 0;
		for (Sample sample : smple_list) {
			for (Results result : ResultsDAO.getListResultsFromColumnByVolume("sample", sample)) {
				if(result.getInProtokol()) {
					count_Result_In_Protokol++;
				}
			}
			
			}
		return count_Result_In_Protokol;
	}

	private static Map<String, String> generateResultsMap(Sample sample, Results result,
			String[] masive_column_table_result) {

		Map<String, String> substitutionData = new HashMap<String, String>();

		// "$$sample_code$$"
		substitutionData.put(masive_column_table_result[0],
				sample.getRequest().getRecuest_code() + "-" + sample.getSample_code());

		// "$$sample_metod$$"
		substitutionData.put(masive_column_table_result[1], result.getMetody().getCode_metody());

		// "$$nuclide$$"
		String pokaz = result.getPokazatel().getName_pokazatel();
		if (pokaz.indexOf("гама") > 0 || pokaz.indexOf("алфа") > 0) {
			substitutionData.put(masive_column_table_result[2], result.getNuclide().getSymbol_nuclide());
		} else {
			substitutionData.put(masive_column_table_result[2], pokaz);
		}
		// "$$razmernost$$"
		substitutionData.put(masive_column_table_result[3], result.getRtazmernosti().getName_razmernosti());

		// "$$value$$"
		String str_VAlue = "";
		if (result.getValue_result() != 0) {
			str_VAlue = formatter(result.getValue_result()) + " ± "
					+ alignExpon(result.getValue_result(), result.getUncertainty());
		} else {
			str_VAlue = "<" + formatter(result.getMda());
		}
		substitutionData.put(masive_column_table_result[4], str_VAlue);

		// "$$norma$$"
		substitutionData.put(masive_column_table_result[5], "-");

		return substitutionData;
	}

	public static String alignExpon(double basic, double foll) {
		NumberFormat frm = new DecimalFormat("0.00E00");
		NumberFormat frm_foll = new DecimalFormat("0.00");
		String str_bas = frm.format(basic);
		double expon = Double.valueOf("1.0" + str_bas.substring(str_bas.indexOf("E")));
		foll = foll / expon;
		String str_foll = frm_foll.format(foll) + str_bas.substring(str_bas.indexOf("E"));
		if (!str_foll.contains("E-")) { // don't blast a negative sign
			str_foll = str_foll.replace("E", "E+");
		}
		str_foll = str_foll.replace(",", ".");
		return str_foll;
	}

	private static String formatter(double number) {
		DecimalFormat formatter = new DecimalFormat("0.00E00");
		String fnumber = formatter.format(number);
		if (!fnumber.contains("E-")) { // don't blast a negative sign
			fnumber = fnumber.replace("E", "E+");
		}
		fnumber = fnumber.replace(",", ".");
		return fnumber;
	}
}
