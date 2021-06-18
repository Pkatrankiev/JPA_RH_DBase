package ExcelFilesFunction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.OldExcelFormatException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Period;
import DBase_Class.Results;
import DBase_Class.Sample;
import GlobalVariable.ReadFileWithGlobalTextVariable;

public class generateInformationToBAK45 {

	public generateInformationToBAK45(String file_name, String mount, int godina, Object[][] DataValue) {

		List<Results> listResultsBAK = getListResult_BAKToMesetcGodina(mount, godina);
		String nameSheet = "0"+PeriodDAO. getValuePeriodByPeriod(mount).getId_period();
//		String nameSheet = "07";
		Sheet sheet = null;
		Workbook workbook = null;
		if (listResultsBAK.size() > 0) {

			try {
				System.out.println(file_name);
				FileInputStream fis = new FileInputStream(file_name);
				workbook = new HSSFWorkbook(fis);
				sheet = workbook.getSheet(nameSheet);

				// Make changes to the sheet
				Cell cell;
				String strCell = "";
				String excellNuclideCode = "";
				int k = 0;

				for (int i = 16; i < 38; i++) {
					cell = sheet.getRow(i).getCell(1);
					strCell = cell.getStringCellValue().replace("(K) *", "").trim();
					k = strCell.indexOf("-");
					System.out.println(i + " - " + strCell + "  " + k);
					excellNuclideCode = strCell.substring(k + 1)
							+ strCell.substring(0, k);
					System.out.println(i + " - " + strCell + "  -  " + excellNuclideCode);
				}

				// All done
				FileOutputStream fileOut = new FileOutputStream(file_name);
				workbook.write(fileOut);
				fileOut.close();

			} catch (OldExcelFormatException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Excel файлът трябва да е версия 97/2000/XP/2003", "Грешни данни",
						JOptionPane.ERROR_MESSAGE);

			} catch (StringIndexOutOfBoundsException e) {
				String NotCorectNuclideList = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWater_OpenExcelFileNotCorectNuclideList") + nameSheet;
				JOptionPane.showMessageDialog(null, NotCorectNuclideList, "Грешни данни", JOptionPane.ERROR_MESSAGE);

			} catch (FileNotFoundException e) {
				String fileNotFound = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWater_OpenExcelFileNotFound") + file_name;
				JOptionPane.showMessageDialog(null, fileNotFound, "Грешни данни", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();
			}

		} else {
			String noResults = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWater_OpenExcelFileNotBAKResults") + mount + " " + godina;
			JOptionPane.showMessageDialog(null, noResults, "Грешни данни", JOptionPane.ERROR_MESSAGE);
		}

		// out = new FileOutputStream("./new.xls");
		// wb.write(out);
		// out.close();

	}

	private List<Results> getListResult_BAKToMesetcGodina(String mount, int godina) {
		Period period = PeriodDAO.getValuePeriodByPeriod(mount);
		Obekt_na_izpitvane_sample object = Obekt_na_izpitvane_sampleDAO
				.getValueObekt_na_izpitvane_sampleOrSaveByName("Бак 4 и 5");
		Sample samp = SampleDAO.getSampleByObject_Mesetc_Godina(object, period, godina);
		List<Results> listResults = ResultsDAO.getListResultsFromCurentSampleInProtokol(samp);
		return listResults;
	}

	// for (int i = 1; i <30; i++) {
	// for (int j = 1; j < 80; j++) {
	// if (sheet.getRow(i) != null){
	// cell = sheet.getRow(i).getCell(j); // For example
	// if (cell != null){
	// try{
	// System.out.println(i+"/"+j+" - "+cell.getStringCellValue());
	// } catch (IllegalStateException e) {
	// System.out.println(i+"/"+j+" - "+cell.getNumericCellValue());
	// }
	// }
	// }
	// }
	//
	// }
	//
	// cell = sheet.getRow(20).getCell(3);
	// cell.setCellValue(8885.55);

}
