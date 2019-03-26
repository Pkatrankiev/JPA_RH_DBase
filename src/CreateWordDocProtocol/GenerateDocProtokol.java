package CreateWordDocProtocol;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import Aplication.NuclideDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Naredbi;
import DBase_Class.Nuclide;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import WindowView.RequestViewFunction;
import WindowView.TranscluentWindow;

public class GenerateDocProtokol {
	
	private static String  strKeyTemplateProtokol = "Протокол от изпитване";
	private static String  strKeyTemplateText = "РЕЗУЛТАТИ ОТ ИЗПИТВАНЕТО";
	private static String  strKeyTemplateNewPage= "#$%";
	private static String  strKeyTemplateZabel = "$$zab$$";
	
	private static String  strKeyTemplateNewRow = "##$$%%";
	private static String  strKeyTemplateMDA = "$$MDA$$";
	private static String  strKeyUser = "$$sert$$";
	private static String  strKeyPodpisiTable =  "Извършили изпитването:";
	private static String  strKeyzabTable = "$$%%";
	private static String  strKeyHeaderRow_1 = "Код на пробата";
	private static String  strKeyHeaderRow_2 = "1";
	private static String  strKeyRow_pokazatel = "$$request_pokazarel$$";
	
	public static void GenerateProtokolWordDoc(String nameTaplateProtokol, Request recuest,
			Map<String, String> substitutionData, TranscluentWindow round) {
		BasicConfigurator.configure();

//		List<Nuclide> list_Nuclide = NuclideDAO.getInListAllValueNuclide();
		nameTaplateProtokol = FunctionForGenerateWordDocFile.get_TEMPLATE_DIRECTORY_ROOT() + nameTaplateProtokol;
		String[] masive_column_table_result = new String[] { "$$sample_code$$", "$$sample_metod$$", "$$cod$$",
				"$$razmernost$$", "$$value$$", "$$norma$$" };

		List<Sample> smple_list = SampleDAO.getListSampleFromColumnByVolume("request", recuest);
		List<Sample> new_smple_list;
//		int count_Result_In_Protokol = FunctionForGenerateWordDocFile.get_count_Result_In_Protokol(smple_list);

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

		P pargraphTemplateProtokol = AplicationDocTemplate.getTemplateParagraph(template, strKeyTemplateProtokol);
		P pargraphTemplateText = AplicationDocTemplate.getTemplateParagraph(template, strKeyTemplateText );
		
		P pargraphTemplateNewPage = AplicationDocTemplate.getTemplateParagraph(template, strKeyTemplateNewPage);
		AplicationDocTemplate.removeTemplateParagraph(template, strKeyTemplateNewPage);
		
		P pargraphTemplateZabel = AplicationDocTemplate.getTemplateParagraph(template, strKeyTemplateZabel);
		AplicationDocTemplate.removeTemplateParagraph(template, strKeyTemplateZabel);
		
		P pargraphTemplateNewRow = AplicationDocTemplate.getTemplateParagraph(template, strKeyTemplateNewRow);
		AplicationDocTemplate.removeTemplateParagraph(template, strKeyTemplateNewRow);
		
		P pargraphTemplateMDA = AplicationDocTemplate.getTemplateParagraph(template, strKeyTemplateMDA);
		AplicationDocTemplate.removeTemplateParagraph(template, strKeyTemplateMDA);
		
		AplicationDocTemplate.replaceParagraph(pargraphTemplateProtokol, substitutionData);

		// izvlichane na tablicite ot documenta
		List<Object> tables = AplicationDocTemplate.getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);

		// find the table
		Tbl tempTable = null;
		Tbl podpisiTable = null;
		Tbl sertifikatTable = null;
		Tbl zabTable = null;
		try {
			sertifikatTable = AplicationDocTemplate.getTemplateTable(tables, strKeyUser);
			if (recuest.getAccreditation()) {
				AplicationDocTemplate.removeTable(template, sertifikatTable);
			}
			tempTable = AplicationDocTemplate.getTemplateTable(tables, masive_column_table_result[0]);
			podpisiTable = AplicationDocTemplate.getTemplateTable(tables,strKeyPodpisiTable);
			zabTable = AplicationDocTemplate.getTemplateTable(tables, strKeyzabTable);

		} catch (Docx4JException | JAXBException e3) {
			e3.printStackTrace();
		}

		// prochitane na redovete v tablicata
		List<Object> rows = AplicationDocTemplate.getAllElementFromObject(tempTable, Tr.class);

		Tr headerRow_1 = AplicationDocTemplate.getRowEqualsText(rows, strKeyHeaderRow_1);
		Tr headerRow_2 = AplicationDocTemplate.getRowEqualsText(rows, strKeyHeaderRow_2);
		Tr templateRow_pokazatel = AplicationDocTemplate.getRowEqualsText(rows, strKeyRow_pokazatel);
		Tr templateRow = AplicationDocTemplate.getRowEqualsText(rows, masive_column_table_result[0]);

		tempTable.getContent().remove(templateRow);
		tempTable.getContent().remove(templateRow_pokazatel);

		AplicationDocTemplate.removeTable(template, podpisiTable);
		AplicationDocTemplate.removeTable(template, zabTable);

		Map<String, String> repl_request_pokazarel = new HashMap<String, String>();
		List<Results> result_list = new ArrayList<>();
		Map<String, String> repl_results = new HashMap<String, String>();
//		int[] numberMergeCells = new int[smple_list.size() + 1];
		
		int coutRow = 2;
		List<int[]> list = createMsasiveRowTable(smple_list, pokazatel_list,coutRow);

		
		String pokaz = pokazatel_list.get(0).getPokazatel().getName_pokazatel();
		Boolean sendOnePokazatel = false;
		if (pokazatel_list.size() == 1) {
			Boolean fl = CreateListForMultiTable.addRowPokazatelIfGamaOrAlpha(tempTable, templateRow_pokazatel,
					repl_request_pokazarel, pokaz);
			if (fl) {
				sendOnePokazatel = true;
				coutRow++;
			}
		}
		System.out.println("smple_list.size()= " + smple_list.size());
		FunctionForGenerateWordDocFile.clearListDokladMDA();
		int idexSample = -1;
		for (Sample sample : smple_list) {
			result_list = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
			
			idexSample++;
			
		
			
			
				for (Results result : result_list) {
				
				
					int[] masive = list.get(0);
					if (coutRow < masive[masive.length - 1]) {
						if (result.getInProtokol()) {
							repl_results = FunctionForGenerateWordDocFile.generateResultsMap(sample, result,
									masive_column_table_result);
							AplicationDocTemplate.addRowToTable(tempTable, templateRow, repl_results);
							coutRow++;
						}
					} else {
						mergeCelsInTAble(tempTable, list.get(0),recuest);
						
//						 create new table
						Tbl new_table = newTable(substitutionData, template, pargraphTemplateProtokol,
								pargraphTemplateText, pargraphTemplateNewPage, pargraphTemplateNewRow, tempTable,
								headerRow_1, headerRow_2, repl_results);
						tempTable = new_table;
						coutRow = 1;
						if(result_list.size()==1){
							coutRow = 2;
						}
						
						new_smple_list = createListForLastSample(idexSample+1, smple_list);
						for (int i = 0; i < new_smple_list.size(); i++) {
							System.out.println(new_smple_list.get(i).getSample_code());
						}
						list = createMsasiveRowTable(new_smple_list, pokazatel_list, coutRow );
						for (int i = 0; i < list.size(); i++) {
							for (int j = 0; j < list.get(i).length; j++) {
								System.out.println(list.get(i).length+" "+list.get(i)[j]);
							}
						}
						if (result.getInProtokol()) {
						repl_results = FunctionForGenerateWordDocFile.generateResultsMap(sample, result,
								masive_column_table_result);
						AplicationDocTemplate.addRowToTable(tempTable, templateRow, repl_results);
						coutRow++;
						}
					}
				}
			}

		

		mergeCelsInTAble(tempTable, list.get(0),recuest);
		
		if(!FunctionForGenerateWordDocFile.getStringDopIzisk(smple_list.get(0)).equals("")){
			for (Results result : FunctionForGenerateWordDocFile.getListDokladMDA()) {
				AplicationDocTemplate.addparagToDoc(template, pargraphTemplateMDA, FunctionForGenerateWordDocFile.generateMapMDA(result));
			}
		}
		String string_zab = smple_list.get(0).getRequest().getZabelejki().getName_zabelejki();
		if( FunctionForGenerateWordDocFile.isZabContain10pecent (string_zab)){
			AplicationDocTemplate.addparagToDoc(template, pargraphTemplateZabel, 
					AplicationDocTemplate.createReplaceMap(strKeyTemplateZabel,  "* "+string_zab));
		}
		

		Naredbi naredba = FunctionForGenerateWordDocFile.getNaredba();
		if(naredba!=null){
			AplicationDocTemplate.addparagToDoc(template, pargraphTemplateZabel, 
					AplicationDocTemplate.createReplaceMap(strKeyTemplateZabel, "* "+naredba.getName_protokol())); 
	}
		AplicationDocTemplate.addParagraph(template, pargraphTemplateNewRow);
		AplicationDocTemplate.addTable(template, zabTable);
		AplicationDocTemplate.addParagraph(template, pargraphTemplateNewRow);
		AplicationDocTemplate.addTable(template, podpisiTable);
		AplicationDocTemplate.replaceTable(zabTable, AplicationDocTemplate.createEmptiMap(strKeyzabTable));

		try {
			String newNameProtokol = recuest.getRecuest_code() + "_" + RequestViewFunction.DateNaw(false) + ".docx";
			AplicationDocTemplate.writeDocxToStream(template,
					FunctionForGenerateWordDocFile.get_destinationDir() + newNameProtokol);
			round.StopWindow();
			GenerateRequestWordDoc.openWordDoc(FunctionForGenerateWordDocFile.get_destinationDir() + newNameProtokol);

		} catch (IOException e) {

			e.printStackTrace();
		} catch (Docx4JException e) {

			e.printStackTrace();
		}
		int k = 1;
		for (int[] masive : list) {
			System.out.println("masive= " + k++);
			for (int i = 0; i < masive.length; i++) {
				System.out.println("i= " + i + "; " + masive[i]);
			}
		}
	}

	private static List<Sample> createListForLastSample(int idexSample,  List<Sample> listSample) {
		 List<Sample> list = new  ArrayList<Sample>();
		 for (int i = idexSample; i < listSample.size(); i++) {
			 list.add(listSample.get(i));
		}
		 
		return list;
	}

	private static List<int[]> createMsasiveRowTable(List<Sample> smple_list, List<IzpitvanPokazatel> pokazatel_list, int coutRow) {
		int[] masive = CreateListForMultiTable.CreateMasiveForMultiTable(smple_list, pokazatel_list, coutRow);
		System.out.println("masive.size= " + masive.length + "maxRow= " + masive[masive.length - 1]);
		List<int[]> list = CreateListForMultiTable.addRowPokazatelIfGamaOrAlpha(masive);
		return list;
	}

	private static void mergeCelsInTAble(Tbl tempTable, int[] numberMergeCells,Request recuest) {
		for (int i = 0; i < numberMergeCells.length - 1; i++) {
			MergeCellsAplication.mergeCellsVertically(tempTable, 0, numberMergeCells[i], numberMergeCells[i + 1]);
			MergeCellsAplication.mergeCellsVertically(tempTable, 1, numberMergeCells[i], numberMergeCells[i + 1]);
			MergeCellsAplication.mergeCellsVertically(tempTable, 3, numberMergeCells[i], numberMergeCells[i + 1]);
		}
		int max =0;
		for (int i = 1; i < numberMergeCells.length; i++) {
			if(max<=numberMergeCells[i]-numberMergeCells[i-1]){
				max=numberMergeCells[i]-numberMergeCells[i-1];
			}
		}
		System.out.println("max= "+max);
		if(FunctionForGenerateWordDocFile.createCleanFromDuplicateListMetody(recuest).size()==1 && max<3){
			MergeCellsAplication.mergeCellsVertically(tempTable, 1, numberMergeCells[0], numberMergeCells[numberMergeCells.length-1]);
			MergeCellsAplication.mergeCellsVertically(tempTable, 3, numberMergeCells[0], numberMergeCells[numberMergeCells.length-1]);
		}
	}
	
	

	private static Tbl newTable(Map<String, String> substitutionData, WordprocessingMLPackage template,
			P pargraphTemplateProtokol, P pargraphTemplateText, P pargraphTemplateNewPage, P pargraphTemplateNewRow,
			Tbl tempTable, Tr headerRow_1, Tr headerRow_2, Map<String, String> repl_results) {
		AplicationDocTemplate.addParagraph(template, pargraphTemplateNewPage);
		AplicationDocTemplate.replaceParagraph(pargraphTemplateNewPage, AplicationDocTemplate.createEmptiMap("#$%"));

		AplicationDocTemplate.addParagraph(template, pargraphTemplateProtokol);
		AplicationDocTemplate.replaceParagraph(pargraphTemplateProtokol, substitutionData);

		AplicationDocTemplate.addParagraph(template, pargraphTemplateNewRow);
		AplicationDocTemplate.replaceParagraph(pargraphTemplateNewRow, AplicationDocTemplate.createEmptiMap("##$$%%"));

		AplicationDocTemplate.addParagraph(template, pargraphTemplateText);

		AplicationDocTemplate.addParagraph(template, pargraphTemplateNewRow);
		AplicationDocTemplate.replaceParagraph(pargraphTemplateNewRow, AplicationDocTemplate.createEmptiMap("##$$%%"));

		// Copying a existing table
//		ObjectFactory factory = Context.getWmlObjectFactory();
		Tbl new_table = AplicationDocTemplate.creatTable(template); // Create a new CTTbl for the new table
		new_table.setTblPr(tempTable.getTblPr()); // Copy the template table's CTTbl

		AplicationDocTemplate.addRowToTable(new_table, headerRow_1, repl_results);
		AplicationDocTemplate.addRowToTable(new_table, headerRow_2, repl_results);
		return new_table;
	}

}
