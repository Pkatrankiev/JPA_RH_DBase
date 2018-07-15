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
			if (sample.getPeriod() == null) {
				smple_vol[i][4] = "";
			} else {
				smple_vol[i][4] = sample.getPeriod().getValue();
			}
			smple_vol[i][5] = sample.getGodina_period()+"";
			i++;
			listValue.add(repl);
		}
		
		
		for (int r = 0; r < 20; r++) {
			repl.put("$$sample_code$$", "function1"+r);
			repl.put("$$sample_metod$$", "desc1");
			repl.put("SJ_PERIOD", "period1");
			repl.put("SJ_PERIOD3", "gamma");
			listValue.add(repl);
		}

		String[] colummVariable = new String[] { "$$sample_code$$", "$$sample_metod$$", "SJ_PERIOD" };

		WordprocessingMLPackage template = null;
		try {
			template = AplicationDocTemplate.getTemplate(tempDoc);
		} catch (FileNotFoundException | Docx4JException e) {
			e.printStackTrace();
		}

		AplicationDocTemplate.replacePlaceholder(template, substitutionData);
		
		P pargraphTemplateD = AplicationDocTemplate.getTemplateParagraph(template, "�������� �� ���������");
		P pargraphTemplateZ = AplicationDocTemplate.getTemplateParagraph(template, "��������� �� �����������");
		P pargraphTemplateT = AplicationDocTemplate.getTemplateParagraph(template, "#$%");
		AplicationDocTemplate.removeTemplateParagraph(template, "#$%");
		ArrayList<P> listParag = new ArrayList<P>();
		listParag.add(pargraphTemplateD);
		listParag.add(pargraphTemplateZ);
		listParag.add(pargraphTemplateT);
		AplicationDocTemplate.replaceParagraph(pargraphTemplateD, substitutionData);
		
		Tbl tempTable = AplicationDocTemplate.MapTable(template, listValue, colummVariable, listParag);
		
		
		
		java.util.List<Tr> listRow =null;
		try {
			listRow = AplicationDocTemplate.getListRowFromTamplate(tempTable);
		} catch (Docx4JException | JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		P p = AplicationDocTemplate.getTemplateParagraph(template, "##$$%%");
		
		try {
//			GenerateWordDocTemplate.insertParag(template, "##$$%%", pargraphTemplateT );
			AplicationDocTemplate.insertTable(template, "##$$%%", tempTable );
			AplicationDocTemplate.insertParagraph(template, "##$$%%", pargraphTemplateZ );
			AplicationDocTemplate.insertParagraph(template, "##$$%%", pargraphTemplateD );
		
			AplicationDocTemplate.insertParagraph(template, "##$$%%", pargraphTemplateT );
		
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
//		try {
//			
//			GenerateWordDocTemplate.replaceInNewTable(template, tempTable, listRow, listValue	);
//		} catch (Docx4JException | JAXBException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		try {
			AplicationDocTemplate.writeDocxToStream(template, "temp12.docx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
