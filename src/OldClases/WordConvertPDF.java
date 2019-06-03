package OldClases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import org.apache.poi.xwpf.usermodel.XWPFDocument;



public class WordConvertPDF {
    public static void main(String[] args) {
        WordConvertPDF cwoWord = new WordConvertPDF();
//        cwoWord.ConvertToPDF("D:/test.docx", "D:/Test.pdf");
    }

//    public void ConvertToPDF(String docPath, String pdfPath) {
//        try {
//            InputStream doc = new FileInputStream(new File(docPath));
//            XWPFDocument document = new XWPFDocument(doc);
//            PdfOptions options = PdfOptions.create();
//            OutputStream out = new FileOutputStream(new File(pdfPath));
//            PdfConverter.getInstance().convert(document, out, options);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
}
