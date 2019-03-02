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
import WindowViewAplication.DocxMainpulator;

public class GenerateDocRazpredFormul {

	private static final String MAIN_DOCUMENT_PATH = "word/document.xml";
	private static final String TEMPLATE_DIRECTORY_ROOT = "TEMPLATES_DIRECTORY/";
	private static final String destinationDir = "DIRECTORY/";

	public static void GenerateProtokolWordDoc(String nameTaplateProtokol, Request recuest,
			Map<String, String> substitutionData, TranscluentWindow round) {
		BasicConfigurator.configure();

		nameTaplateProtokol = TEMPLATE_DIRECTORY_ROOT + nameTaplateProtokol;
		String[] masive_key_table_row = new String[] { "$$sample_code$$", "$$ob_izp_sam$$", "$$pokazat$$",
				"$$date_exec$$" };

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
		int maxCounRow = countRowInFirstPege(count_Result_In_Protokol);
		int coutRow = 0;

		for (Sample sample : smple_list) {
			result_list = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);

			for (IzpitvanPokazatel pokazatel : pokazatel_list) {
				if (coutRow < 9) {
					repl_results = generateMapRowTable(sample, pokazatel, masive_key_table_row);
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
					repl_results = generateMapRowTable(sample, pokazatel, masive_key_table_row);
					AplicationDocTemplate.addRowToTable(new_table, templateRow, repl_results);

				}
				coutRow++;
			}

		}

		if (coutRow < 12)
			for (int i = coutRow; i < 10; i++) {
				repl_results = generateEmptiMapRowTable(masive_key_table_row);
				AplicationDocTemplate.addRowToTable(tempTable, templateRow, repl_results);

			}
		mergeCellsVertically(tempTable, 0, 2,5);
		AplicationDocTemplate.addTable(template, podpisiTable);

		try {
			String newNameProtokol = "P_" + recuest.getRecuest_code() + "_" + RequestViewFunction.DateNaw(false)
					+ ".docx";
			AplicationDocTemplate.writeDocxToStream(template, destinationDir + newNameProtokol);
			round.StopWindow();
			DocxMainpulator.openWordDoc(destinationDir + newNameProtokol);

		} catch (IOException e) {

			e.printStackTrace();
		} catch (Docx4JException e) {

			e.printStackTrace();
		}
	}

	public static int countRowInFirstPege(int count_Result_In_Protokol) {
		int max_tableRow = 100;
		if (count_Result_In_Protokol > 7 && count_Result_In_Protokol <= 10) {
			max_tableRow = count_Result_In_Protokol - 2;
		} else {
			if (count_Result_In_Protokol > 10) {
				max_tableRow = 9;
			}
		}
		return max_tableRow;
	}

	public static int get_count_Result_In_Protokol(List<Sample> smple_list) {
		int count_Result_In_Protokol = 0;
		for (Sample sample : smple_list) {
			for (Results result : ResultsDAO.getListResultsFromColumnByVolume("sample", sample)) {
				if (result.getInProtokol()) {
					count_Result_In_Protokol++;
				}
			}

		}
		return count_Result_In_Protokol;
	}

	private static Map<String, String> generateMapRowTable(Sample sample, IzpitvanPokazatel pokazatel,
			String[] masive_key_table_row) {
		List<String> listDokladMDA = new ArrayList<String>();
		Map<String, String> substitutionData = new HashMap<String, String>();

		// "$$sample_code$$"
		substitutionData.put(masive_key_table_row[0],
				sample.getRequest().getRecuest_code() + "-" + sample.getSample_code());

		// "$$ob_izp_sam$$"
		substitutionData.put(masive_key_table_row[1], sample.getObekt_na_izpitvane().getName_obekt_na_izpitvane());

		// "$$pokazat$$"
		String strMinipokazatel = generateSimpliStrOnPokazatel(pokazatel.getPokazatel().getName_pokazatel());
		String[] str = getNumberFromNuclide(strMinipokazatel);
		substitutionData.put("$$num$$", str[0]);
		substitutionData.put("$$pokazat$$", str[1]);

		// "$$date_execution$$"
		substitutionData.put(masive_key_table_row[3], pokazatel.getRequest().getDate_execution());

		return substitutionData;
	}

	private static Map<String, String> generateEmptiMapRowTable(String[] masive_key_table_row) {
		List<String> listDokladMDA = new ArrayList<String>();
		Map<String, String> substitutionData = new HashMap<String, String>();

		// "$$sample_code$$"
		substitutionData.put(masive_key_table_row[0], "");

		// "$$ob_izp_sam$$"
		substitutionData.put(masive_key_table_row[1], "");

		// "$$pokazat$$"

		substitutionData.put("$$num$$", "");
		substitutionData.put("$$pokazat$$", "");

		// "$$date_execution$$"
		substitutionData.put(masive_key_table_row[3], "");

		return substitutionData;
	}

	private static String generateSimpliStrOnPokazatel(String name_pokazatel) {
		name_pokazatel = name_pokazatel.replaceAll("Съдържание на", "").trim();
		name_pokazatel = name_pokazatel.replaceAll("излъчващи", "").trim();
		return name_pokazatel;
	}

	private static String[] getNumberFromNuclide(String symbol_nuclide) {
		String[] str = new String[] { "", "" };

		for (int i = 0; i < symbol_nuclide.length(); i++) {
			char temp = symbol_nuclide.charAt(i);

			if (Character.isDigit(temp) || (temp == '/')) {
				str[0] = str[0] + temp;
			} else {
				str[1] = str[1] + temp;
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

	

	

	public static List<Tc> getTrAllCell(Tr tr) {  
	    List<Object> objList = AplicationDocTemplate.getAllElementFromObject(tr, Tc.class);  
	    List<Tc> tcList = new ArrayList<Tc>();  
	    if (objList == null) {  
	        return tcList;  
	    }  
	    for (Object tcObj : objList) {  
	        if (tcObj instanceof Tc) {  
	            Tc objTc = (Tc) tcObj;  
	            tcList.add(objTc);  
	        }  
	    }  
	    return tcList;  
	}
	
	public void mergeCellsHorizontal(Tbl tbl, int row, int fromCell, int toCell) {  
        if (row < 0 || fromCell < 0 || toCell < 0) {  
            return;  
        }  
        List<Tr> trList = getTblAllTr(tbl);  
        if (row > trList.size()) {  
            return;  
        }  
        Tr tr = trList.get(row);  
        List<Tc> tcList = getTrAllCell(tr);  
        for (int cellIndex = fromCell, len = Math  
                .min(tcList.size() - 1, toCell); cellIndex <= len; cellIndex++) {  
            Tc tc = tcList.get(cellIndex);  
            TcPr tcPr = getTcPr(tc);  
            HMerge hMerge = tcPr.getHMerge();  
            if (hMerge == null) {  
                hMerge = new HMerge();  
                tcPr.setHMerge(hMerge);  
            }  
            if (cellIndex == fromCell) {  
                hMerge.setVal("restart");  
            } else {  
                hMerge.setVal("continue");  
            }  
        }  
    }  
	
	 public static void mergeCellsVertically(Tbl tbl, int col, int fromRow, int toRow) {  
	        if (col < 0 || fromRow < 0 || toRow < 0) {  
	            return;  
	        }  
	        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {  
	            Tc tc = getTc(tbl, rowIndex, col);  
	            if (tc == null) {  
	                break;  
	            }  
	            TcPr tcPr = getTcPr(tc);  
	            VMerge vMerge = tcPr.getVMerge();  
	            if (vMerge == null) {  
	                vMerge = new VMerge();  
	                tcPr.setVMerge(vMerge);  
	            }  
	            if (rowIndex == fromRow) {  
	                vMerge.setVal("restart");  
	            } else {  
	                vMerge.setVal("continue");  
	            }  
	        }  
	    }  
	
	 public static TcPr getTcPr(Tc tc) {  
	        TcPr tcPr = tc.getTcPr();  
	        if (tcPr == null) {  
	            tcPr = new TcPr();  
	            tc.setTcPr(tcPr);  
	        }  
	        return tcPr;  
	    }  
	 
	 public static Tc getTc(Tbl tbl, int row, int cell) {  
	        if (row < 0 || cell < 0) {  
	            return null;  
	        }  
	        List<Tr> trList = getTblAllTr(tbl);  
	        if (row >= trList.size()) {  
	            return null;  
	        }  
	        List<Tc> tcList = getTrAllCell(trList.get(row));  
	        if (cell >= tcList.size()) {  
	            return null;  
	        }  
	        return tcList.get(cell);  
	    }  
	 
	  public static List<Tr> getTblAllTr(Tbl tbl) {  
	        List<Object> objList = AplicationDocTemplate.getAllElementFromObject(tbl, Tr.class);  
	        List<Tr> trList = new ArrayList<Tr>();  
	        if (objList == null) {  
	            return trList;  
	        }  
	        for (Object obj : objList) {  
	            if (obj instanceof Tr) {  
	                Tr tr = (Tr) obj;  
	                trList.add(tr);  
	            }  
	        }  
	        return trList;  
	  
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
