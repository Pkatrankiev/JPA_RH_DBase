package CreateWordDocProtocol;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.log4j.BasicConfigurator;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.P;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tr;

import Aplication.RequestDAO;
import Aplication.SampleDAO;
import DBase_Class.Request;
import DBase_Class.Sample;

public class StartGenerateDocTemplate {

	public static void GenerateProtokolWordDoc(String nameTaplateProtokol, String requestCode, Map<String, String> substitutionData) {
		BasicConfigurator.configure();

		ArrayList<Map<String, String>> listValue = new ArrayList<Map<String, String>>();
		Map<String, String> repl = new HashMap<String, String>();
		String tempDoc = nameTaplateProtokol;
	
		Request recuest = RequestDAO.getRequestFromColumnByVolume("recuest_code", requestCode);
		List<Sample> smple_list = SampleDAO.getListSampleFromColumnByVolume("request", recuest);
		String [][] smple_vol = new String [smple_list.size()][6];
		int i = 0;
		for (Sample sample : smple_list) {
			smple_vol[i][0] = sample.getSample_code();
			repl.put("$$sample_code$$", smple_vol[i][0]);
			smple_vol[i][1] = sample.getObekt_na_izpitvane().getName_obekt_na_izpitvane();
			smple_vol[i][2] = sample.getDescription_sample();
			smple_vol[i][3] = sample.getDate_time_reference();
			smple_vol[i][4] = sample.getPeriod().getValue();
			smple_vol[i][5] = sample.getGodina_period()+"";
			i++;
		}
		
//		for (int i = 0; i < 40; i++) {
//			repl1.put("SJ_FUNCTION", "function1");
//			repl1.put("SJ_DESC", "desc1");
//			repl1.put("SJ_PERIOD", "period1");
//			repl1.put("SJ_PERIOD3", "gamma");
//			listValue.add(repl1);
//		}

		String[] colummVariable = new String[] { "$$sample_code$$", "SJ_DESC", "SJ_PERIOD" };

		WordprocessingMLPackage template = null;
		try {
			template = GenerateWordDocTemplate.getTemplate(tempDoc);
		} catch (FileNotFoundException | Docx4JException e) {
			e.printStackTrace();
		}

		GenerateWordDocTemplate.replacePlaceholder(template, substitutionData);
		
		P pargraphTemplateD = GenerateWordDocTemplate.getTemplateParagraph(template, "œÓÚÓÍÓÎ ÓÚ ËÁÔËÚ‚‡ÌÂ π");
		Map<String, String> replickate = new HashMap<String, String>();
		replickate.put("SJ_Date", "15.05.2015");	
		replickate.put("SJ_code", "5555");
		GenerateWordDocTemplate.replaceParagraph(pargraphTemplateD, replickate);
		
		Tbl tempTable = GenerateWordDocTemplate.MapTable(template, listValue, colummVariable);
		
		P pargraphTemplateZ = GenerateWordDocTemplate.getTemplateParagraph(template, "–≈«”À“¿“» Œ“ »«œ»“¬¿Õ≈“Œ");
		
		java.util.List<Tr> listRow =null;
		try {
			listRow = GenerateWordDocTemplate.getTemplataRow(colummVariable, tempTable);
		} catch (Docx4JException | JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		GenerateWordDocTemplate.addParagraph(template, GenerateWordDocTemplate.getTemplateParagraph(template, "#$%"));
		
		GenerateWordDocTemplate.addParagraph(template, pargraphTemplateD);
		GenerateWordDocTemplate.addParagraph(template, pargraphTemplateZ);
		
		
		
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
