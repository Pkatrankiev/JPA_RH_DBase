package OldClases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Convert;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class TestCon {

	public static void main(String[] args) throws Exception {
        String inputFile="D:/test.docx";
        String outputFile="D:/TEST.pdf";
        if (args != null && args.length == 2) {
          inputFile=args[0];
          outputFile=args[1];
        }
        
        ConvertToPDF(inputFile,outputFile);
        
//        System.out.println("inputFile:" + inputFile + ",outputFile:"+ outputFile);
//        FileInputStream in=new FileInputStream(inputFile);
//        XWPFDocument document=new XWPFDocument(in);
//        File outFile=new File(outputFile);
//        OutputStream out=new FileOutputStream(outFile);
//        PdfOptions options=null;
//        PdfConverter.getInstance().convert(document,out,options);
      }
	
	public static void ConvertToPDF(String docPath, String pdfPath) {
	    try {
	    	
	    	ClassLoader classloader =
	    			   org.apache.poi.poifs.filesystem.POIFSFileSystem.class.getClassLoader();
	    			URL resPath = classloader.getResource(
	    			         "org/apache/poi/poifs/filesystem/POIFSFileSystem.class");
	    			String path = resPath.getPath();
	    			System.out.println("The actual POI Path is " + path);
	    	
	    	
	    			
	        InputStream doc = new FileInputStream(new File(docPath));
	        XWPFDocument document = new XWPFDocument(doc);
	        PdfOptions options = PdfOptions.create();
	        OutputStream out = new FileOutputStream(new File(pdfPath));
	        PdfConverter.getInstance().convert(document, out, options);
	    } catch (FileNotFoundException ex) {
	        Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
	        Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
}
