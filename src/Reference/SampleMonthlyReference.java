package Reference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.ResultsDAO;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Results;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import Table_Request.Table_RequestToObektNaIzp;
import WindowView.DateChoice_period;
import WindowView.DatePicker;
import WindowView.RequestViewFunction;
import WindowView.TranscluentWindow;

public class SampleMonthlyReference {
	static String infoString = "";
	public static void SampleMonthly_Reference() {
		Boolean forDateReception = true;
		Boolean withTime = false;
		Boolean fromTable = false;
		String str_date_period_reception = RequestViewFunction.DateNaw(false);
		String title_Name = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("SampleMonthlyReference_TitleName");

		final JFrame f = new JFrame();
		DateChoice_period date_period_reception = new DateChoice_period(f, title_Name, str_date_period_reception,
				withTime, forDateReception, fromTable);
		date_period_reception.setVisible(true);
		Boolean forTable = false;
		String textRefDate = DateChoice_period.get_str_period_sample(forDateReception, forTable);
		if (DateChoice_period.check_correct_DatePeriod()) {

			int indexSim = textRefDate.indexOf("÷");
			String startDate = textRefDate.substring(0, indexSim).trim();
			String endDate = textRefDate.substring(indexSim + 1).trim();
			System.out.println(startDate + "|" + endDate + "  " + startDate.substring(3));

			List<Results> list = ResultsDAO.getListResultsByDataMeasurANDInProtokol(startDate.substring(3));
			List<Results> listResul = new ArrayList<>();
			list = ResultsDAO.getListResults_LargerByIDANDInProtokol(list.get(0).getId_results());

			System.out.println(list.size());
			System.out.println(list.get(0).getId_results() + " - " + list.get(list.size() - 1).getId_results());
			System.out.println(startDate + " - " + endDate);
			for (Results results : list) {
				System.out.println(results.getDate_measur());
				if (DatePicker.isDate2AfterDate1(startDate, results.getDate_measur())
						&& DatePicker.isDate2AfterDate1(results.getDate_measur(), endDate)) {
					listResul.add(results);
				}

			}

			System.out.println(listResul.size());
			System.out.println(
					listResul.get(0).getDate_measur() + " - " + listResul.get(listResul.size() - 1).getDate_measur());
			System.out.println(
					listResul.get(0).getId_results() + " - " + listResul.get(listResul.size() - 1).getId_results());
			
			int countForSampleByPokazatel = 0;
			List<List_izpitvan_pokazatel> listPokazatel = List_izpitvan_pokazatelDAO.getInListAllValuePokazatel();
			List<Results> listResultsByPokazatel = new ArrayList<Results>();
			List<String> listFromListResults = new ArrayList<String>();

			for (List_izpitvan_pokazatel list_izpitvan_pokazatel : listPokazatel) {
				int lastSamleId = 0;
				int lastRequestId = 0;
				for (Iterator<Results> itR = listResul.iterator(); itR.hasNext();) {
					Results result = itR.next();
					if (result.getPokazatel().getId_pokazatel() == list_izpitvan_pokazatel.getId_pokazatel()) {
						if (result.getSample().getId_sample() != lastSamleId) {
							countForSampleByPokazatel++;
							lastSamleId = result.getSample().getId_sample();
						}

						if (result.getRequest().getId_recuest() != lastRequestId) {
							lastRequestId = result.getRequest().getId_recuest();
							listResultsByPokazatel.add(result);
						}

						itR.remove();
					}
				}

				if (countForSampleByPokazatel > 0) {
					listFromListResults.add(
							list_izpitvan_pokazatel.getName_pokazatel() + " - " + countForSampleByPokazatel + " бр.");
					countForSampleByPokazatel = 0;
				}
			}
//			Описание на<br>пробите</html>
			infoString = "<html>";
			for (String str : listFromListResults) {
				infoString = infoString+str+"<br>";
			}
			infoString = infoString+"</html>";
			
			int columnCount = 5;
			String[][] listMasiveReferens = new String[listResultsByPokazatel.size()][columnCount ];
			int index = 0;
			
			for (Results results : listResultsByPokazatel) {
				listMasiveReferens[index][0] = results.getPokazatel().getName_pokazatel();
				listMasiveReferens[index][1] = results.getRequest().getIzpitvan_produkt().getName_zpitvan_produkt();
				listMasiveReferens[index][2] = results.getRequest().getRecuest_code();
				listMasiveReferens[index][3] = Table_RequestToObektNaIzp.createStringListObektNaIzp(Table_RequestToObektNaIzp.
						getListStringOfRequest_To_ObektNaIzpitvaneRequest(results.getRequest()), false) ;
				listMasiveReferens[index][4] = results.getRequest().getCounts_samples()+"";
				index++;

			}
			for (index = 0; index < listMasiveReferens.length; index++) {
						
				System.out.println(listMasiveReferens[index][0] + " - " 
						+ listMasiveReferens[index][1] + " - "
						+ listMasiveReferens[index][2] + "-"
						+ listMasiveReferens[index][3] + "-"
						+ listMasiveReferens[index][4] + " бр.");
			}
			
			System.out.println(infoString);
			
			String[] columnNames = new String[]{
					ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_Pokazatel"),
					ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_IzpitvanProdukt"),
					ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_CodeRequest"),
					ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_Obekt_Na_Izpitvane"),
					ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_CountSample")
			};
					
			TranscluentWindow round = new TranscluentWindow();

			final Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {

					JFrame f = new JFrame();
					String titleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("SampleMonthlyReference_TitleName");
				
					new ViewSampleMonthlyReferenceTable(f, round,titleName, infoString, listMasiveReferens,columnNames);
					
				}
			});
			thread.start();
			
			
			
		}
	}

}
