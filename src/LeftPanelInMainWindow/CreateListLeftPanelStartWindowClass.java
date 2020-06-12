package LeftPanelInMainWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JProgressBar;

import org.apache.commons.compress.utils.Lists;

import Aplication.IzpitvanPokazatelDAO;
import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Period;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import GlobalVariable.GlobalPathForDocFile;
import OldClases.FindFile;
import OldClases.Table_RequestToObektNaIzp;
import WindowView.DatePicker;

public class CreateListLeftPanelStartWindowClass {

	private static String dir_Protocols = GlobalPathForDocFile.get_destinationDir_Protocols();
	private static String[] listMonitoringGroup = { "Газообразни изхвърляния", "Течни изхвърляния", "Въздух", "Вода" };

	protected CreateListLeftPanelStartWindowClass(JProgressBar progressBarView, int startCheckYear) {
		VariableFromStartWindowPanel
				.setListLeftPanelStartWindow(createListLeftPanelStartWindowClass(progressBarView, startCheckYear));

		System.out
				.println(VariableFromStartWindowPanel.getListLeftPanelStartWindow().size() + " +++++++++++++++++++++/");
	}

	public static List<List<LeftPanelStartWindowClass>> createListLeftPanelStartWindowClass(
			JProgressBar progressBarView, int startCheckYear) {
		List<List<LeftPanelStartWindowClass>> groupList = Lists.newArrayList();

		FindFile ff = new FindFile();
		String month = getPreviousMesec(1);

		String monitGroup = "";

		int ProgressBarSize = 0;
		progressBarView.setValue(ProgressBarSize);
		for (int i = 0; i < listMonitoringGroup.length; i++) {
			monitGroup = listMonitoringGroup[i];

			if (monitGroup.equals("Вода")) {
				month = getPreviousMesec(2);

			}
			List<Request> techniIzh = cerateList(month, monitGroup);

			double stepForProgressBar = 25;

			if (techniIzh.size() > 0) {
				stepForProgressBar = stepForProgressBar / techniIzh.size();
			}
			List<LeftPanelStartWindowClass> list = new ArrayList<>();
			if (list.isEmpty()) {
				LeftPanelStartWindowClass object = new LeftPanelStartWindowClass();
				object.setMonitoringGroup(monitGroup);
				object.setLblLabel_Code("");
				object.setLblLabel_Obect("");
				object.setLblLabel_Sample("");
				object.setLblLabel_Protokol("");
				list.add(object);
			}
			for (Request request : techniIzh) {
				LeftPanelStartWindowClass object = new LeftPanelStartWindowClass();
				object.setMonitoringGroup(monitGroup);
				object.setLblLabel_Code(request.getRecuest_code());
				object.setLblLabel_Obect(Table_RequestToObektNaIzp.createStringListObektNaIzp(
						Table_RequestToObektNaIzp.getListStringOfRequest_To_ObektNaIzpitvaneRequest(request), false));
				object.setLblLabel_Sample(createStringPokazatel(request));
				object.setLblLabel_Protokol(ff.findFile(request.getRecuest_code(), new File(dir_Protocols)));
				System.out.println(object.getLblLabel_Code() + " / " + object.getLblLabel_Obect() + " / "
						+ object.getLblLabel_Protokol() + " / " + object.getLblLabel_Sample() + " / "
						+ object.getMonitoringGroup() + " / ");
				list.add(object);
				ProgressBarSize += stepForProgressBar;
				System.out.println(ProgressBarSize + " - " + stepForProgressBar + " * " + techniIzh.size());
				progressBarView.setValue(ProgressBarSize);
			}
			groupList.add(list);
		}

		creadDataForRigthPanel(startCheckYear);

		progressBarView.setValue(ProgressBarSize);
		System.out.println(groupList.size() + " ////////////////////");

		return groupList;
	}

	public static void creadDataForRigthPanel(int startCheckYear) {
		List<Request> listCeckRequest = createListCheckRequest(startCheckYear);

		VariableFromStartWindowPanel
				.setListMissingRequestRightPanelStartWindow(createListMissingRequest(listCeckRequest));
		VariableFromStartWindowPanel
				.setListMissingResultsRightPanelStartWindow(createListMissingResults(listCeckRequest));
		VariableFromStartWindowPanel
				.setListMissingProtokolsRightPanelStartWindo(createListMissingProtokols(listCeckRequest));
	}

	public static List<Integer> createListMissingRequest(List<Request> listCeckRequest) {

		List<Integer> listMissingRequest = new ArrayList<Integer>();

		int startCodeRequest = Integer.parseInt(listCeckRequest.get(0).getRecuest_code());
		int endCodeRequest = RequestDAO.getMaxRequestCode();
		int indexRequest = 0;
		for (int index = startCodeRequest; index < endCodeRequest; index++) {

			if (Integer.parseInt(listCeckRequest.get(indexRequest).getRecuest_code()) != index) {
				listMissingRequest.add(index);
				indexRequest--;
			}
			indexRequest++;
		}
		for (Integer integer : listMissingRequest) {
			System.out.println(integer);
		}
		return listMissingRequest;
	}

	public static List<Integer> createListMissingResults(List<Request> listCeckRequest) {
		List<Request> listCeckRequest1 = createListCheckRequest(2020);

		List<Integer> listMissingResults = new ArrayList<Integer>();
		List<Sample> listSampleResults = new ArrayList<Sample>();
		int sampleCount = 0;
		for (Request request : listCeckRequest1) {

			sampleCount = request.getCounts_samples();
			List<Results> listResultsByRequest = ResultsDAO.getListResultsFromColumnByVolume("request", request);
			System.out.println("listResultsByRequest.size " + listResultsByRequest.size());
			for (Results results : listResultsByRequest) {
				listSampleResults.add(results.getSample());
			}
			System.out.println("listSampleResults.size " + listSampleResults.size());
			Set<Sample> mySet = new HashSet<Sample>();
			mySet.addAll(listSampleResults);
			System.out.println("mySet.size " + mySet.size());
			if (sampleCount != mySet.size()) {
				listMissingResults.add(Integer.parseInt(request.getRecuest_code()));
			}
			System.out.println("listMissingResults.size " + listMissingResults.size());
			listSampleResults.clear();
		}
		for (int sample : listMissingResults) {
			System.out.println(sample);
		}
		return listMissingResults;
	}

	public static List<Integer> createListMissingProtokols(List<Request> listCeckRequest) {
		String dir_Protocols = GlobalPathForDocFile.get_destinationDir_Protocols();
		FindFile ff = new FindFile();
		List<Integer> listMissingProtokol = new ArrayList<Integer>();
		for (Request request : listCeckRequest) {
			System.out.println("request.getRecuest_code() " + request.getRecuest_code());
			
			if (ff.findFile(request.getRecuest_code(), new File(dir_Protocols)).isEmpty()) {
				listMissingProtokol.add(Integer.parseInt(request.getRecuest_code()));

			}
		}

		return listMissingProtokol;
	}

	private static List<Request> createListCheckRequest(int startCheckYear) {
		List<Request> listCeckRequest = new ArrayList<Request>();
		for (Request request : RequestDAO.getInListAllValueRequest()) {
			if (DatePicker.checkStringDateAfterCurentYear(startCheckYear, request.getDate_request())) {
				if (Integer.parseInt(request.getRecuest_code()) > 3100) {
					listCeckRequest.add(request);
				}
			}
		}
		return listCeckRequest;
	}

	private static List<Request> cerateList(String monthPeriod, String izpitvan_produkt) {
		int curentYear = getYar();
		List<Request> list = new ArrayList<Request>();
		List<Request> listRequest = RequestDAO.getListRequestFromMonitoringProgramm(izpitvan_produkt);

		int countNeseseryRequest = 15;

		int max = listRequest.size();
		int min = 0;
		System.out.println(max + " / " + listRequest.get(0).getRecuest_code() + " - "
				+ listRequest.get(max - 1).getRecuest_code());
		if (max > countNeseseryRequest) {
			min = max - countNeseseryRequest;
		}
		for (Request request : listRequest.subList(min, max)) {
			Period period = SampleDAO.getListSampleFromColumnByVolume("request", request).get(0).getPeriod();
			int year = SampleDAO.getListSampleFromColumnByVolume("request", request).get(0).getGodina_period();
			if (period != null) {
				String str = period.getValue();
				if (str.equals(monthPeriod) && curentYear == year) {
					list.add(request);
				}
			}
		}
		System.out.println(max + " / " + min);

		return list;
	}

	private static String createStringPokazatel(Request request) {
		String str = "";
		List<IzpitvanPokazatel> list = IzpitvanPokazatelDAO.getValueIzpitvan_pokazatelByRequest(request);
		if (list.size() > 1) {
			str = "<html>";
			for (IzpitvanPokazatel izpPok : list) {
				str = str + izpPok.getPokazatel().getName_pokazatel().replaceFirst("Съдържание на ", "") + "<br>";
			}

			str = str.substring(0, str.length() - 4);
			str = str + "</html>";
		} else {
			str = list.get(0).getPokazatel().getName_pokazatel().replaceFirst("Съдържание на ", "");
		}
		return str;
	}

	static String getPreviousMesec(int koregMount) {
		int month = DatePicker.getActualyMonth() + koregMount;
		if (month == 1) {
			month = 12;
		} else {
			month--;
		}
		return PeriodDAO.getPeriodById(month).getValue();
	}

	static int getYar() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = DatePicker.getActualyMonth() + 1;
		if (month == 1) {
			year -= 1;
		}
		return year;
	}

	public void performTask() {

	}

}
