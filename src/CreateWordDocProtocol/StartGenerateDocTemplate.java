package CreateWordDocProtocol;


import java.awt.Cursor;
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
			listValue.add(repl);
		}
		
		for (int r = 0; r < 40; r++) {
			repl.put("$$sample_code$$", "function1");
			repl.put("SJ_DESC", "desc1");
			repl.put("SJ_PERIOD", "period1");
			repl.put("SJ_PERIOD3", "gamma");
			listValue.add(repl);
		}

		String[] colummVariable = new String[] { "$$sample_code$$", "$$sample_metod$$", "SJ_PERIOD" };

		WordprocessingMLPackage template = null;
		try {
			template = GenerateWordDocTemplate.getTemplate(tempDoc);
		} catch (FileNotFoundException | Docx4JException e) {
			e.printStackTrace();
		}

		GenerateWordDocTemplate.replacePlaceholder(template, substitutionData);
		
		P pargraphTemplateD = GenerateWordDocTemplate.getTemplateParagraph(template, "Ïðîòîêîë îò èçïèòâàíå");
		P pargraphTemplateZ = GenerateWordDocTemplate.getTemplateParagraph(template, "ÐÅÇÓËÒÀÒÈ ÎÒ ÈÇÏÈÒÂÀÍÅÒÎ");
		P pargraphTemplateT = GenerateWordDocTemplate.getTemplateParagraph(template, "#$%");
		ArrayList<P> listParag = new ArrayList<P>();
		listParag.add(pargraphTemplateD);
		listParag.add(pargraphTemplateZ);
		listParag.add(pargraphTemplateT);
		GenerateWordDocTemplate.replaceParagraph(pargraphTemplateD, substitutionData);
		
		Tbl tempTable = GenerateWordDocTemplate.MapTable(template, listValue, colummVariable, listParag);
		
		
		
		java.util.List<Tr> listRow =null;
		try {
			listRow = GenerateWordDocTemplate.getTemplataRow(colummVariable, tempTable, listParag);
		} catch (Docx4JException | JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		P p = GenerateWordDocTemplate.getTemplateParagraph(template, "##$$%%");
		
		try {
//			GenerateWordDocTemplate.insertParag(template, "##$$%%", pargraphTemplateT );
			GenerateWordDocTemplate.insertTable(template, "##$$%%", tempTable );
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
