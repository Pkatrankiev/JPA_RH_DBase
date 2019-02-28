package CreateWordDocProtocol;


import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBException;
import org.apache.log4j.*;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.docx4j.jaxb.Context;
import org.docx4j.model.properties.table.BorderBottom;
import org.docx4j.model.properties.table.BorderLeft;
import org.docx4j.model.properties.table.BorderRight;
import org.docx4j.model.properties.table.BorderTop;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.StyleDefinitionsPart;
import org.docx4j.wml.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

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

public class GenerateDocRazpredFormul {

	private static final String MAIN_DOCUMENT_PATH = "word/document.xml";
	private static final String TEMPLATE_DIRECTORY_ROOT = "TEMPLATES_DIRECTORY/";
	private static final String destinationDir = "DIRECTORY/";

	public static void GenerateProtokolWordDoc( String nameTaplateProtokol, Request recuest,
			Map<String, String> substitutionData) {
		BasicConfigurator.configure();
	
		nameTaplateProtokol = TEMPLATE_DIRECTORY_ROOT + nameTaplateProtokol;
		String[] masive_column_table_result = new String[] { "$$sample_code$$", "$$ob_izp_sam$$", "$$pokazat$$",
				"$$date_execution$$"};
				
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

		P pargraphTemplateProtokol = AplicationDocTemplate.getTemplateParagraph(template, "$$pp$$");
		AplicationDocTemplate.removeTemplateParagraph(template, "№ Р –");
		
         System.out.println("------"+pargraphTemplateProtokol);
		AplicationDocTemplate.replaceParagraph(pargraphTemplateProtokol, substitutionData);

		// izvlichane na tablicite ot documenta
		List<Object> tables = AplicationDocTemplate.getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);

		// find the table
		Tbl tempTable = null;
		Tbl podpisiTable = null;
		Tbl sertifikatTable = null;
		Tbl zabTable = null;
		try {
			sertifikatTable = AplicationDocTemplate.getTemplateTable(tables, "$$sert$$");
			if(recuest.getAccreditation()){
				AplicationDocTemplate.removeTable(template, sertifikatTable);
				}
			tempTable = AplicationDocTemplate.getTemplateTable(tables, masive_column_table_result[0]);
			podpisiTable = AplicationDocTemplate.getTemplateTable(tables, "Прегледали:");
		
		} catch (Docx4JException | JAXBException e3) {
			e3.printStackTrace();
		}
		
		// prochitane na redovete v tablicata
		List<Object> rows = AplicationDocTemplate.getAllElementFromObject(tempTable, Tr.class);

		 
		Tr headerRow_1 = AplicationDocTemplate.getRowEqualsText(rows, "Код на пробата");
		
		Tr templateRow = AplicationDocTemplate.getRowEqualsText(rows, masive_column_table_result[0]);
		
	
		
     
		
		
		
		tempTable.getContent().remove(templateRow);
		
		
		AplicationDocTemplate.removeTable(template, podpisiTable);
		AplicationDocTemplate.removeTable(template, zabTable);
		
		Map<String, String> repl_request_pokazarel = new HashMap<String, String>();
		List<Results> result_list = new ArrayList<>();
		Map<String, String> repl_results = new HashMap<String, String>();

		Boolean tableEqualsRequestPokazatel = false;
		String pokaz = pokazatel_list.get(0).getPokazatel().getName_pokazatel();
		int maxCounRow = countRowInFirstPege(count_Result_In_Protokol);
		int coutRow = 0;
		if (pokazatel_list.size() == 1) {
			
			for (Sample sample : smple_list) {
				result_list = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
				for (Results result : result_list) {
//					if(coutRow<10) {
					if(result.getInProtokol()) {
					repl_results = generateResultsMap(sample, result, masive_column_table_result);
					AplicationDocTemplate.addRowToTable(tempTable, templateRow, repl_results);
					coutRow++;
					}
//					}
				}

			}
			
					
				
				
				  // Copying a existing table 
				ObjectFactory factory = Context.getWmlObjectFactory();
				Tbl new_table =  AplicationDocTemplate.creatTable(template); // Create a new CTTbl for the new table 
				new_table.setTblPr(tempTable.getTblPr()); // Copy the template table's CTTbl 

				AplicationDocTemplate.addRowToTable(new_table, headerRow_1, repl_results);
				
				for (int i = 0; i < 5; i++) {
											
					AplicationDocTemplate.addRowToTable(new_table, templateRow, repl_results);
					
					}		
				

			}
		AplicationDocTemplate.addTable(template, zabTable);
			AplicationDocTemplate.addTable(template, podpisiTable);
			AplicationDocTemplate.replaceTable(zabTable,  AplicationDocTemplate.createEmptiMap("$$%%"));
			

		try {
			String newNameProtokol = "P_"+recuest.getRecuest_code()+"_"+ RequestViewFunction.DateNaw(false)+".docx";
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
		List<String> listDokladMDA = new ArrayList<String>();
		Map<String, String> substitutionData = new HashMap<String, String>();

		// "$$sample_code$$"
		substitutionData.put(masive_column_table_result[0],
				sample.getRequest().getRecuest_code() + "-" + sample.getSample_code());
		String string_zab = sample.getRequest().getZabelejki().getName_zabelejki();

		// "$$sample_metod$$"
		substitutionData.put(masive_column_table_result[1], result.getMetody().getCode_metody());

		// "$$nuclide$$"
		String pokaz = result.getPokazatel().getName_pokazatel();
		if (pokaz.indexOf("гама") > 0 || pokaz.indexOf("алфа") > 0) {
//			substitutionData.put(masive_column_table_result[2], superscript(result.getNuclide().getSymbol_nuclide()));
			String[] nuclide = getNumberFromNuclide(result.getNuclide().getSymbol_nuclide());
			substitutionData.put("$$num$$",nuclide[0] );
			substitutionData.put("$$cod$$",nuclide[1] );
			
		} else {
			substitutionData.put(masive_column_table_result[2], superscript(pokaz));
		}
		// "$$razmernost$$"
		substitutionData.put(masive_column_table_result[3], result.getRtazmernosti().getName_razmernosti());

		// "$$value$$"
		String str_VAlue = "";
		
		if (result.getValue_result() != 0) {
			str_VAlue = formatter(result.getValue_result()) + " ± "
					+ alignExpon(result.getValue_result(), result.getUncertainty());
			if(string_zab.indexOf("10%")>0){
				str_VAlue=str_VAlue+"*";
			}
			if(string_zab.indexOf("Да се докладва МДА")>0){
				listDokladMDA.add("<" + formatter(result.getMda()));
			}
		} else {
			str_VAlue = "<" + formatter(result.getMda());
		}
		
		
		substitutionData.put(masive_column_table_result[4], str_VAlue);

		// "$$norma$$"
		substitutionData.put(masive_column_table_result[5], "-");

		return substitutionData;
	}

	 private static String[] getNumberFromNuclide(String symbol_nuclide) {
		String[] str = new String[]{"",""};
		
		 for (int i = 0; i < symbol_nuclide.length(); i++) {
			char temp = symbol_nuclide.charAt(i);
		
		     if(Character.isDigit(temp) ||( temp == '/'))
		     {
		        str[0] = str[0]+temp;
		     }else{
		    	 str[1] = str[1]+temp;
		     }
		}
		return str;
	}

	public static String superscript(String str) {
		
	 	    str = str.replaceAll("0", "\u2070");
	 	    str = str.replaceAll("1", "\u00B9");
	 	    str = str.replaceAll("2", "\u00B2");
	 	    str = str.replaceAll("3", "\u00B3");
	 	    str = str.replaceAll("4", "\u2074");
	 	    str = str.replaceAll("5", "\u2075");
	 	    str = str.replaceAll("6", "\u2076");
	 	    str = str.replaceAll("7", "⁷");
	 	    str = str.replaceAll("8", "\u2078");
	 	    str = str.replaceAll("9", "\u2079");  
	 	    str = str.replaceAll("/", "ᐟ");  
	 	    
	 	    return str;
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

