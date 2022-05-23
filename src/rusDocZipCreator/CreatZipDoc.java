package rusDocZipCreator;

import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import GlobalVariable.ResourceLoader;


public class CreatZipDoc {
	 
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
       try{
    	 
			String fileName = "myWord.doc";

			XWPFDocument doc = new XWPFDocument();
	        
			
			XWPFParagraph para1 = doc.createParagraph();
			para1.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun run = para1.createRun();
			run.setBold(true);
			run.setFontSize(30);
			run.setText("ThaiCreate.Com");

			
			XWPFTable tab = doc.createTable();
			XWPFTableRow row = tab.getRow(0);
			
			row.getCell(0).setText("Column 1");
			row.addNewTableCell().setText("Column 2");
			row.addNewTableCell().setText("Column 3");
			row.addNewTableCell().setText("Column 4");
			row.addNewTableCell().setText("Column 5");
		   
			row = tab.createRow();
			row.getCell(0).setText("Data 1");
			row.getCell(1).setText("Data 2");
			row.getCell(2).setText("Data 3");
			row.getCell(3).setText("Data 4");
			row.getCell(4).setText("Data 5");
			
			row = tab.createRow();
			row.getCell(0).setText("Data 6");
			row.getCell(1).setText("Data 7");
			row.getCell(2).setText("Data 8");
			row.getCell(3).setText("Data 9");
			row.getCell(4).setText("Data 10");
			
			doc.createParagraph().createRun().addBreak();
			
			XWPFParagraph para2 = doc.createParagraph();
			XWPFRun run2 = para2.createRun();
			run2.setText("By...mr.win");
			run2.setTextPosition(100);

			doc.write(new FileOutputStream(fileName));
			
			System.out.println("Word document created.");
	
		}
		catch (Exception e) {
			ResourceLoader.appendToFile(e);
			e.printStackTrace();
		}
		
	}
	
}