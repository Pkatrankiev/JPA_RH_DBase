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
import org.docx4j.wml.TcPrInner.HMerge;
import org.docx4j.wml.TcPrInner.VMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import Aplication.IzpitvanPokazatelDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import WindowView.RequestViewFunction;
import WindowView.TranscluentWindow;

public class GenerateDocRazpredFormul {

	public static int maxRowInTableOnePage = 10;
	public static void GenerateRazpFormWordDoc(String nameTaplateProtokol, Request recuest,
			Map<String, String> substitutionData, TranscluentWindow round) {
		BasicConfigurator.configure();

		nameTaplateProtokol = FunctionForGenerateWordDocFile.get_TEMPLATE_DIRECTORY_ROOT() + nameTaplateProtokol;
		String[] masive_key_table_row = new String[] { "$$sample_code$$", "$$ob_izp_sam$$", "$$pokazat$$",
				"$$date_exec$$" };

		List<Sample> smple_list = SampleDAO.getListSampleFromColumnByVolume("request", recuest);

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

		// izvlichane na tablicite ot documenta
		List<Object> tables = AplicationDocTemplate.getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);

		// find the table
		Tbl tempTable = null;
		Tbl podpisiTable = null;
		Tbl new_table = null;

		try {

			tempTable = AplicationDocTemplate.getTemplateTable(tables, masive_key_table_row[0]);
			podpisiTable = AplicationDocTemplate.getTemplateTable(tables, "Прегледали:");

		} catch (Docx4JException | JAXBException e3) {
			e3.printStackTrace();
		}

		// prochitane na redovete v tablicata
		List<Object> rows = AplicationDocTemplate.getAllElementFromObject(tempTable, Tr.class);

		Tr headerRow_1 = AplicationDocTemplate.getRowEqualsText(rows, "Код на пробата");

		Tr templateRow = AplicationDocTemplate.getRowEqualsText(rows, masive_key_table_row[0]);

		tempTable.getContent().remove(templateRow);

		AplicationDocTemplate.removeTable(template, podpisiTable);

		Map<String, String> repl_request_pokazarel = new HashMap<String, String>();
		List<Results> result_list = new ArrayList<>();
		Map<String, String> repl_results = new HashMap<String, String>();

		Boolean newTableCreate = true;
		String pokaz = pokazatel_list.get(0).getPokazatel().getName_pokazatel();
		int maxCounRow = FunctionForGenerateWordDocFile.countRowInFirstPege(count_Result_In_Protokol);
		int coutRow = 0;

		for (Sample sample : smple_list) {
			result_list = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);

			for (IzpitvanPokazatel pokazatel : pokazatel_list) {
				if (coutRow < maxRowInTableOnePage) {
					repl_results = FunctionForGenerateWordDocFile.generateMapRowTable(sample, pokazatel,
							masive_key_table_row);
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
							masive_key_table_row);
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
	
		AplicationDocTemplate.addTable(template, podpisiTable);

		try {
			String newNameProtokol = "P_" + recuest.getRecuest_code() + "_" + RequestViewFunction.DateNaw(false)
					+ ".docx";
			AplicationDocTemplate.writeDocxToStream(template, FunctionForGenerateWordDocFile.get_destinationDir() + newNameProtokol);
			round.StopWindow();
			GenerateRequestWordDoc.openWordDoc(FunctionForGenerateWordDocFile.get_destinationDir() + newNameProtokol);

		} catch (IOException e) {

			e.printStackTrace();
		} catch (Docx4JException e) {

			e.printStackTrace();
		}
	}

}
