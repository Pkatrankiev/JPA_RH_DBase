package WindowView;

import java.io.FileInputStream;
import org.apache.poi.hwpf.model.*;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.HeaderStories;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReaderWordDoc {

	public static void readMyDocument(String fileName) {
		POIFSFileSystem fs = null;
		try {
			fs = new POIFSFileSystem(new FileInputStream(fileName));
			HWPFDocument doc = new HWPFDocument(fs);

			/** Read the content **/
			// readParagraphs(doc);
			readTable(doc);

			int pageNumber = 1;

			/** We will try reading the header for page 1 **/
			readHeader(doc, pageNumber);

			/** Let's try reading the footer for page 1 **/
			readFooter(doc, pageNumber);

			/** Read the document summary **/
			readDocumentSummary(doc);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void readParagraphs(HWPFDocument doc) throws Exception {
		WordExtractor we = new WordExtractor(doc);

		/** Get the total number of paragraphs **/
		String[] paragraphs = we.getParagraphText();
		System.out.println("Total Paragraphs: " + paragraphs.length);

		for (int i = 0; i < paragraphs.length; i++) {

			System.out.println("Length of paragraph " + (i + 1) + ": " + paragraphs[i].length());
			System.out.println(paragraphs[i].toString());

		}

	}

	public static void readHeader(HWPFDocument doc, int pageNumber) {
		HeaderStories headerStore = new HeaderStories(doc);
		String header = headerStore.getHeader(pageNumber);
		System.out.println("Header Is: " + header);

	}

	public static void readFooter(HWPFDocument doc, int pageNumber) {
		HeaderStories headerStore = new HeaderStories(doc);
		String footer = headerStore.getFooter(pageNumber);
		System.out.println("Footer Is: " + footer);

	}

	public static void readDocumentSummary(HWPFDocument doc) {
		DocumentSummaryInformation summaryInfo = doc.getDocumentSummaryInformation();
		String category = summaryInfo.getCategory();
		String company = summaryInfo.getCompany();
		int lineCount = summaryInfo.getLineCount();
		int sectionCount = summaryInfo.getSectionCount();
		int slideCount = summaryInfo.getSlideCount();

		System.out.println("---------------------------");
		System.out.println("Category: " + category);
		System.out.println("Company: " + company);
		System.out.println("Line Count: " + lineCount);
		System.out.println("Section Count: " + sectionCount);
		System.out.println("Slide Count: " + slideCount);

	}

	public static void readTable(HWPFDocument doc) {
		Range range = doc.getRange();
		int tab = 0;
		int row = 0;
		int col = 0;
		String cels[][][] = new String[5][100][20];
		int maxrow[] = new int[5];
		int maxcol[] = new int[5];

		for (int i = 0; i < range.numParagraphs(); i++) {
			Paragraph par = range.getParagraph(i);
			// System.out.println("paragraph " + (i + 1));
			// System.out.println("is in table: " + par.isInTable());
			// System.out.println("is table row end: " + par.isTableRowEnd());
			//
			// System.out.println("tab " + (tab + 1) + " / " + "red " + (row +
			// 1) + " / " + "kolona " + (col + 1));
			// System.out.println(par.text());
			cels[tab][row][col] = par.text();
			// System.out.println("maxrow[" + tab + "] =" + maxrow[tab] + " / "
			// + "maxcol[" + tab + "] =" + maxcol[tab]);
			if (par.isInTable()) {
				col++;
				maxcol[tab] = col;
			}
			if (par.isTableRowEnd()) {
				row++;
				col = 0;
				maxrow[tab] = row;
				// System.out.println();
			}
			if (!par.isInTable() & !par.isTableRowEnd() & row + col > 0) {
				tab++;
				row = 0;
				col = 0;
				// System.out.println("***************");

			}
		}
		String format_row = "%20s";
		for (int tab1 = 0; tab1 < tab; tab1++) {
			// System.out
			// .println("maxrow[" + tab1 + "] =" + maxrow[tab1] + " / " +
			// "maxcol[" + tab1 + "] =" + maxcol[tab1]);

		}

		 for (int tab1 = 0; tab1 < tab; tab1++) {
		 if (cels[tab1][0][0] != null) {
		// System.out.println("tab " + (tab1 + 1));
		// System.out.println();
		 }
		
		String columnNames[] = new String[maxcol[tab1]];
		Object data[][] = new Object[maxrow[tab1] - 1][maxcol[tab1]];
		String cleanString = "";

		for (int row1 = 0; row1 < maxrow[tab1]; row1++) {
			// System.out.println();

			for (int col1 = 0; col1 < maxcol[tab1]; col1++) {
				// System.out.println(cels[tab1][row1][col1]);

				// System.out.format("|%20s", cels[tab1][row1][col1]);
				if (cels[tab1][row1][col1] == null) {
					cleanString = "";
				} else
					cleanString = cels[tab1][row1][col1].replaceAll("\r", "").replaceAll("\n", "");
				if (row1 == 0) {
					columnNames[col1] = cleanString;
				} else
					data[row1 - 1][col1] = cleanString;
			}

		}
//		for (int col1 = 0; col1 < maxcol[tab1]; col1++) {
//			System.out.print(columnNames[col1] + "\", \"");
//		}
			for (String objects : columnNames) {
				System.out.print(objects + "\", \"");
			}
			
			
		
		for (int row1 = 0; row1 < maxrow[tab1] - 1; row1++) {
			System.out.println();
			for (int col1 = 0; col1 < maxcol[tab1]; col1++) {
				System.out.print(data[row1][col1] + "\", \"");
			}
		}
		TablePrintDemo.createAndShowGUI(columnNames, data);
	}

	// String[] columnNames = {"1", "2", "3", "4", "5", "6", ""};
	// Object[][] data = {
	// {"3344-1", "", "БНС-1 , СК-1 ", "Bq/g", "3.05E+04 ± 0.34E+04", "-",
	// ""},
	// {"", "", "Съдържание на 63Ni", "", "2.26E+04 ± 0.27E+04", "-",
	// ""},
	// {"", "", "Съдържание на 90Sr", "", "", "","-"},
	// {"", "", "Съдържание на 241Pu", "", "3.93E+03 ± 0.52E+03", "-",
	// ""},
	// {"3344-2", "", "Съдържание на алфа-излъчващи радионуклиди:", "", "#",
	// "#", "#"},
	// {"", "M.ЛИ-РХ-08 Редакция 02/2014", "241Am", "Bq/g", "5.52E+01 ±
	// 0.52E+01", "-", ""}
	//
	// };

	 }

}
