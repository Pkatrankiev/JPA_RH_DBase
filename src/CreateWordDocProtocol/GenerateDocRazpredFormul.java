package CreateWordDocProtocol;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.*;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.*;

import Aplication.IzpitvanPokazatelDAO;
import Aplication.Request_To_ObektNaIzpitvaneRequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Request;
import DBase_Class.Request_To_ObektNaIzpitvaneRequest;
import DBase_Class.Sample;
import GlobalVariable.GlobalPathForDocFile;
import WindowView.RequestViewFunction;
import WindowView.TranscluentWindow;


public class GenerateDocRazpredFormul {

	public static int maxRowInTableOnePage = 10;
	private static String strKeyTemplateNewRow = "##$$%%";
	
	
	public static void GenerateRazpFormWordDoc(String nameTaplateProtokol, Request recuest,
			Map<String, String> substitutionData, TranscluentWindow round) {
		BasicConfigurator.configure();

		nameTaplateProtokol = GlobalPathForDocFile.get_TEMPLATE_DIRECTORY_ROOT() + nameTaplateProtokol;
		String[] masive_key_table_row = new String[] { "$$sample_code$$", "$$ob_izp_sam$$", "$$pokazat$$",
				"$$date_exec$$" };

		List<Sample> smple_list = SampleDAO.getListSampleFromColumnByVolume("request", recuest);
		Boolean isDiferent_Ob_Izp_Req = isDiferent_Ob_Izp_Req(recuest);
		int count_Result_In_Protokol = FunctionForGenerateWordDocFile.get_count_Result_In_Protokol(smple_list);

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
		AplicationDocTemplate.removeTemplateParagraph(template, "$$pp$$");
		P pargraphTemplateNewPage = AplicationDocTemplate.getTemplateParagraph(template, "#$%");
		AplicationDocTemplate.removeTemplateParagraph(template, "#$%");
		P pargraphTemplateNewRow = AplicationDocTemplate.getTemplateParagraph(template, strKeyTemplateNewRow);
		AplicationDocTemplate.removeTemplateParagraph(template, strKeyTemplateNewRow);

		// izvlichane na tablicite ot documenta
		List<Object> tables = AplicationDocTemplate.getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);

		// find the table
		Tbl tempTable = null;
		Tbl podpisiTable = null;
		Tbl new_table = null;

		try {

			tempTable = AplicationDocTemplate.getTemplateTable(tables, masive_key_table_row[0]);
			podpisiTable = AplicationDocTemplate.getTemplateTable(tables, "Прегледали:");

		} catch (Docx4JException | javax.xml.bind.JAXBException e3) {
			e3.printStackTrace();
		}

		// prochitane na redovete v tablicata
		List<Object> rows = AplicationDocTemplate.getAllElementFromObject(tempTable, Tr.class);

		Tr headerRow_1 = AplicationDocTemplate.getRowEqualsText(rows, "Код на пробата");

		Tr templateRow = AplicationDocTemplate.getRowEqualsText(rows, masive_key_table_row[0]);

		tempTable.getContent().remove(templateRow);

		AplicationDocTemplate.removeTable(template, podpisiTable);

		new HashMap<String, String>();
		new ArrayList<>();
		Map<String, String> repl_results = new HashMap<String, String>();

		Boolean newTableCreate = true;
		pokazatel_list.get(0).getPokazatel().getName_pokazatel();
		FunctionForGenerateWordDocFile.countRowInFirstPege(count_Result_In_Protokol);
		int coutRow = 0;

		
		for (Sample sample : smple_list) {
			ResultsDAO.getListResultsFromColumnByVolume("sample", sample);

			for (IzpitvanPokazatel pokazatel : pokazatel_list) {
				if (coutRow < maxRowInTableOnePage) {
					repl_results = FunctionForGenerateWordDocFile.generateMapRowTable(sample, pokazatel,
							masive_key_table_row, isDiferent_Ob_Izp_Req);
					AplicationDocTemplate.addRowToTable(tempTable, templateRow, repl_results);

				} else {
					if (newTableCreate) {
						// Copying a existing table
						AplicationDocTemplate.addParagraph(template, pargraphTemplateNewPage);
						AplicationDocTemplate.replaceParagraph(pargraphTemplateNewPage,
								AplicationDocTemplate.createEmptiMap("#$%"));

						AplicationDocTemplate.addParagraph(template, pargraphTemplateProtokol);

						AplicationDocTemplate.replaceParagraph(pargraphTemplateProtokol,
								AplicationDocTemplate.createEmptiMap("$$pp$$"));
						AplicationDocTemplate.replaceParagraph(pargraphTemplateProtokol, substitutionData);

						new_table = AplicationDocTemplate.creatTable(template); // Create a new CTTbl for the new table
						new_table.setTblPr(tempTable.getTblPr()); // Copy the template table's CTTbl
						AplicationDocTemplate.addRowToTable(new_table, headerRow_1, repl_results);
						newTableCreate = false;
					}
					repl_results = FunctionForGenerateWordDocFile.generateMapRowTable(sample, pokazatel,
							masive_key_table_row, isDiferent_Ob_Izp_Req);
					AplicationDocTemplate.addRowToTable(new_table, templateRow, repl_results);

				}
				coutRow++;
			}

		}

		if (coutRow < 12)
			for (int i = coutRow; i < 10; i++) {
				repl_results = FunctionForGenerateWordDocFile.generateEmptiMapRowTable(masive_key_table_row);
				AplicationDocTemplate.addRowToTable(tempTable, templateRow, repl_results);

			}
		
		AplicationDocTemplate.addParagraph(template, pargraphTemplateNewRow);
		AplicationDocTemplate.replaceParagraph(pargraphTemplateNewRow, AplicationDocTemplate.createEmptiMap("##$$%%"));
		AplicationDocTemplate.addTable(template, podpisiTable);

		try {
			String newNameProtokol = "P_" + recuest.getRecuest_code() + "_" + RequestViewFunction.DateNaw(false)
					+ ".docx";
			AplicationDocTemplate.writeDocxToStream(template, GlobalPathForDocFile.get_destinationDir() + newNameProtokol);
			round.StopWindow();
			GenerateRequestWordDoc.openWordDoc(GlobalPathForDocFile.get_destinationDir() + newNameProtokol);

		} catch (IOException e) {

			e.printStackTrace();
		} catch (Docx4JException e) {

			e.printStackTrace();
		}
	}


	public static Boolean isDiferent_Ob_Izp_Req(Request  request) {
		List<Request_To_ObektNaIzpitvaneRequest> list_request_To_ObektNaIzpitvaneRequest = Request_To_ObektNaIzpitvaneRequestDAO.
				getRequest_To_ObektNaIzpitvaneRequestByRequest( request);
		if(list_request_To_ObektNaIzpitvaneRequest.size()>1){
				return true;
		}
		return false;
	}

}
