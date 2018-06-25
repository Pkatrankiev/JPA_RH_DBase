package WindowViewAplication;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class ManipolDoc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordprocessingMLPackage template = null;
		try {
			template = GenerateWordDoc.getTemplate("C:\\temp.docx");
		} catch (FileNotFoundException | Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GenerateWordDoc.replacePlaceholder(template,"33333333333","$$request_date$$");
		GenerateWordDoc.replacePlaceholder(template,"444444444","$$ind_num_doc$$");
		try {
			GenerateWordDoc.writeDocxToStream(template, "D:\\temp12.docx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
