package ExcelFilesFunction;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import CreateWordDocProtocol.GenerateRequestWordDoc;
import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Period;
import DBase_Class.Results;
import DBase_Class.Sample;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import GlobalVariable.ResourceLoader;

public class generateInformationToBAK45 {

	public generateInformationToBAK45(String file_name, String mount, int godina, Object[][] DataValue,
			double obemBAK) {
		FileOutputStream fileOut = null;
		List<Results> listResultsBAK = getListResult_BAKToMesetcGodina(mount, godina);
		String nameSheet = "";
		int numMouth = PeriodDAO.getValuePeriodByPeriod(mount).getId_period();
		if(numMouth<10){
		nameSheet = "0"+ numMouth;
		}else{
		nameSheet = numMouth+"";
		}
		String errorOfData = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("errorOfData");
		String notSelectCorectExcelFile = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_NotSelectCorectExcelFile");
		String excelFileIsOpen = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_excelFileIsOpen");
		
		double MDACNRD = 0.0;
		double MDABAK = 0.0;

		for (int i = 0; i < DataValue.length; i++) {
			System.out.print(i);
			for (int j = 0; j < DataValue[0].length; j++) {
				System.out.print(" - " + DataValue[i][j]);
			}
			System.out.println();
		}

		// String nameSheet = "07";
		Sheet sheet = null;
		Workbook workbook = null;
		if (listResultsBAK.size() > 0) {

			List<String> listMissingNuclideBAK = new ArrayList<String>();
			List<String> listMissingNuclideCNRD = new ArrayList<String>();
		
			try {
				System.out.println(file_name);
				
				FileInputStream fis = new FileInputStream(file_name);
				workbook = new HSSFWorkbook(fis);
				sheet = workbook.getSheet(nameSheet);
				double maxObAktBAK = 0.0;
				
				String checkKorektExcelFile = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWater_CheckKorektExcelFile");

				Cell cell = null;
				cell = sheet.getRow(5).getCell(1);
				if(cell.getStringCellValue().equals(checkKorektExcelFile)){
												
				String excellNuclideCode = "";

				double dd = 0.0;

				cell = sheet.getRow(12).getCell(7);
				cell.setCellValue(obemBAK);

				dd = Double.parseDouble(DataValue[1][5].toString());
				cell = sheet.getRow(13).getCell(7);
				cell.setCellValue(dd);

				for (int i = 16; i < 38; i++) {
					cell = sheet.getRow(i).getCell(1);
					excellNuclideCode = reformatSimbolNuclide(cell);

					for (Results result : listResultsBAK) {

						if (excellNuclideCode.equals(result.getNuclide().getSymbol_nuclide())) {

							if (result.getValue_result() != 0.0) {
								dd = result.getValue_result();
								maxObAktBAK = maxObAktBAK + dd;
								cell = sheet.getRow(i).getCell(3);
								cell.setCellValue(dd * obemBAK);

								dd = (result.getUncertainty()) / (dd * 2);
								cell = sheet.getRow(i).getCell(4);
								cell.setCellValue(dd);
								listMissingNuclideBAK.add(result.getNuclide().getSymbol_nuclide());
							}
							MDABAK = result.getMda();
						}

					}

					for (int j = 0; j < DataValue.length; j++) {

						if (excellNuclideCode.equals(DataValue[j][0].toString())) {
							if (!DataValue[j][1].toString().isEmpty()) {
								dd = Double.parseDouble(DataValue[j][1].toString());
								cell = sheet.getRow(i).getCell(5);
								cell.setCellValue(dd);

								dd = Double.parseDouble(DataValue[j][2].toString());
								cell = sheet.getRow(i).getCell(6);
								cell.setCellValue(dd / 100);
								listMissingNuclideCNRD.add(DataValue[j][0].toString());
							}
							MDACNRD = Double.parseDouble(DataValue[j][3].toString());
						}

					}

					cell = sheet.getRow(i).getCell(1);
					if (cell.getStringCellValue().toString().indexOf("(K) *") > 0) {

						cell = sheet.getRow(i).getCell(2);
						cell.setCellValue(MDACNRD > MDABAK ? MDACNRD : MDABAK);
					}
				}

				dd = Double.parseDouble(DataValue[1][7].toString());
				if (dd > 0.0) {
					cell = sheet.getRow(32).getCell(9);
					cell.setCellValue(dd);
				}

				if (maxObAktBAK > 0.0) {
					cell = sheet.getRow(33).getCell(9);
					cell.setCellValue(maxObAktBAK / 1000);
				}


				try {
					fileOut = new FileOutputStream(file_name);
					workbook.write(fileOut);
					fileOut.close();
					GenerateRequestWordDoc.openWordDoc(file_name);
				} catch (IOException e) {
					ResourceLoader.appendToFile(e);
					JOptionPane.showMessageDialog(null, excelFileIsOpen, errorOfData, JOptionPane.ERROR_MESSAGE);
					
				
				}
				
				

			}else{
				
				JOptionPane.showMessageDialog(null, notSelectCorectExcelFile, errorOfData, JOptionPane.ERROR_MESSAGE);	
			}
				
		
			} catch (IOException e) {
				ResourceLoader.appendToFile(e);
				e.printStackTrace();
			}

		} else {
			String noResults = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWater_OpenExcelFileNotBAKResults") +" "+ mount + " " + godina;
			JOptionPane.showMessageDialog(null, noResults, errorOfData, JOptionPane.ERROR_MESSAGE);
		}

		// out = new FileOutputStream("./new.xls");
		// wb.write(out);
		// out.close();

	}

	private String reformatSimbolNuclide(Cell cell) {
		String strCell;
		String excellNuclideCode;
		int k;
		strCell = cell.getStringCellValue().replace("(K) *", "").trim();
		k = strCell.indexOf("-");
		excellNuclideCode = strCell.substring(k + 1) + strCell.substring(0, k);
		return excellNuclideCode;
	}

	private List<Results> getListResult_BAKToMesetcGodina(String mount, int godina) {
		int idObekt_na_izpitvane_sample_BAK4i5 = 5;
		List<Results> listResults = new ArrayList<>();
		Period period = PeriodDAO.getValuePeriodByPeriod(mount);
		Obekt_na_izpitvane_sample object = Obekt_na_izpitvane_sampleDAO
				.getValueObekt_na_izpitvane_sampleById(idObekt_na_izpitvane_sample_BAK4i5);
		Sample samp = SampleDAO.getSampleByObject_Mesetc_Godina(object, period, godina);
		if(samp!=null){
			listResults = ResultsDAO.getListResultsFromCurentSampleInProtokol(samp);	
		}
		return listResults;
	}



}
