package WindowView;

import java.io.FileInputStream;

import java.util.ArrayList;



import org.apache.poi.hwpf.usermodel.*;

import org.apache.poi.hpsf.DocumentSummaryInformation;

import org.apache.poi.hwpf.HWPFDocument;

import org.apache.poi.hwpf.extractor.WordExtractor;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReaderWordDoc {

	private static String[][][] data;

	public static String [][][] readMyDocument(String fileName) {

		POIFSFileSystem fs = null;

		try {

			fs = new POIFSFileSystem(new FileInputStream(fileName));

			HWPFDocument doc = new HWPFDocument(fs);



			

			/** Read the content **/

			// readParagraphs(doc);



			/** Read the Table **/

			data = readTable(doc);



			/** We will try reading the header for page 1 **/

			// int pageNumber = 1;

			// readHeader(doc, pageNumber);



			/** Let's try reading the footer for page 1 **/

			// readFooter(doc, pageNumber);



			/** Read the document summary **/

			// readDocumentSummary(doc);



		} catch (Exception e) {

			e.printStackTrace();

		}

		return data;

	}

	public static void readParagraphs(HWPFDocument doc) throws Exception {

		WordExtractor we = new WordExtractor(doc);



		/** Get the total number of paragraphs **/

		String[] paragraphs = we.getParagraphText();

//		System.out.println("Total Paragraphs: " + paragraphs.length);



		for (int i = 0; i < paragraphs.length; i++) {



//			System.out.println("Length of paragraph " + (i + 1) + ": " + paragraphs[i].length());

//			System.out.println(paragraphs[i].toString());



		}



	}

	public static void readHeader(HWPFDocument doc, int pageNumber) {

		HeaderStories headerStore = new HeaderStories(doc);

		String header = headerStore.getHeader(pageNumber);

//		System.out.println("Header Is: " + header);



	}

	public static void readFooter(HWPFDocument doc, int pageNumber) {

		HeaderStories headerStore = new HeaderStories(doc);

		String footer = headerStore.getFooter(pageNumber);

//		System.out.println("Footer Is: " + footer);



	}

	public static void readDocumentSummary(HWPFDocument doc) {

		DocumentSummaryInformation summaryInfo = doc.getDocumentSummaryInformation();

		String category = summaryInfo.getCategory();

		String company = summaryInfo.getCompany();

		int lineCount = summaryInfo.getLineCount();

		int sectionCount = summaryInfo.getSectionCount();

		int slideCount = summaryInfo.getSlideCount();



//		System.out.println("---------------------------");
//
//		System.out.println("Category: " + category);
//
//		System.out.println("Company: " + company);
//
//		System.out.println("Line Count: " + lineCount);
//
//		System.out.println("Section Count: " + sectionCount);
//
//		System.out.println("Slide Count: " + slideCount);



	}

	public static String [][][] readTable(HWPFDocument doc) {

		Range range = doc.getRange();

		Boolean endTab = true;



		Boolean isTableHeat = false;



		ArrayList<String> paragraphList = new ArrayList<String>();

		String cellParagraph;

		int totalMaxColl = 0;

		int totalMaxRaw = 0;

		int numtab = 0;

		int numPargraf = 0;

		String[] paragrafList;

		String cels[][][] = new String[5][100][20];

		int maxrow[] = new int[5];

		int maxcol[] = new int[5];



		for (int i = 0; i < range.numParagraphs(); i++)



		{

			Paragraph tablePar = range.getParagraph(i);



			if (tablePar.isInTable()) {

				if (endTab) {

					endTab = false;



					Table table = range.getTable(tablePar);

					numtab++;

					for (int rowIdx = 0; rowIdx < table.numRows(); rowIdx++) {

						TableRow row = table.getRow(rowIdx);



						for (int colIdx = 0; colIdx < row.numCells(); colIdx++) {

							TableCell cell = row.getCell(colIdx);

							String text = "";

							for (int celIdx = 0; celIdx < cell.numParagraphs(); celIdx++) {



								text = text + cell.getParagraph(celIdx).text();



							}

							i = i + cell.numParagraphs();



							cels[numtab][rowIdx][colIdx] = text.replaceAll("", "");

						}

						maxcol[numtab] = row.numCells();

					}

					maxrow[numtab] = table.numRows();



				}

				if (totalMaxColl < maxcol[numtab]) {

					totalMaxColl = maxcol[numtab];

				}

				if (totalMaxRaw < maxrow[numtab]) {

					totalMaxRaw = maxrow[numtab];

				}



			} else {

				endTab = true;

				cellParagraph = tablePar.text().replaceAll("\r", "").replaceAll("\n", "").replaceAll("", "");

				if (!cellParagraph.equals("")) {

					paragraphList.add(cellParagraph);



				}

			}



		}

		if (totalMaxRaw < paragraphList.size()) {

			totalMaxRaw = paragraphList.size();

		}

		int tabHeat = 0;

		if (isTableHeat) {

			tabHeat = 1;

		}

		

		String celsTranfer[][][] = new String[numtab+1][totalMaxRaw][totalMaxColl];

		

		for (int tab1 = 1; tab1 <= numtab; tab1++) {



			String columnNames[] = new String[maxcol[tab1]];

			Object data[][] = new Object[maxrow[tab1] - tabHeat][maxcol[tab1]];

			String cleanString = "";



			for (int row1 = 0; row1 < maxrow[tab1]; row1++) {



				for (int col1 = 0; col1 < maxcol[tab1]; col1++) {



					if (cels[tab1][row1][col1] == null) {

						cleanString = "";

					} else

						cleanString = cels[tab1][row1][col1];
//								.replaceAll("\r", " ").replaceAll("\n", " ");



					if (row1 == 0) {

						columnNames[col1] = cleanString;



						if (!isTableHeat) {

							data[row1][col1] = cleanString;

							celsTranfer[tab1][row1][col1]= cleanString;

						}

					} else

						data[row1 - tabHeat][col1] = cleanString;

					celsTranfer[tab1][row1 - tabHeat][col1]= cleanString;

				}



			}



						
//			System.out.println("tab1 "+tab1);
//			TablePrintDemo.createAndShowGUI(columnNames, data);

		}

		String columnNames[] = new String[1];

		Object data[][] = new Object[paragraphList.size()][1];

		int k = 0;

		columnNames[k] = "";

		for (String dataPragraph : paragraphList) {

			data[k][0] = dataPragraph;

			celsTranfer[0][k][0] =  dataPragraph;

			k++;

		}
//		System.out.println("numtab+1 "+(numtab+1));
//		TablePrintDemo.createAndShowGUI(columnNames, data);

	return celsTranfer;

	}

}
