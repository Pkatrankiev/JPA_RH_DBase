package LeftPanelInMainWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import javax.swing.JProgressBar;

import org.apache.commons.compress.utils.Lists;

import Aplication.IzpitvanPokazatelDAO;
import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Period;
import DBase_Class.Request;
import GlobalVariable.GlobalPathForDocFile;
import OldClases.FindFile;
import OldClases.Table_RequestToObektNaIzp;
import WindowView.DatePicker;

public class CreateListLeftPanelStartWindowClass {
	
	 
	

	private static String dir_Protocols = GlobalPathForDocFile.get_destinationDir_Protocols();
	private static String[] listMonitoringGroup = { "Газообразни изхвърляния", "Течни изхвърляния", "Въздух", "Вода" };

	


protected CreateListLeftPanelStartWindowClass(JProgressBar progressBarView){
	VariableFromLeftPanel.setListLeftPanelStartWindow(createListLeftPanelStartWindowClass(progressBarView));
	System.out.println(VariableFromLeftPanel.getListLeftPanelStartWindow().size()+" +++++++++++++++++++++/");
}

public static  List<List<LeftPanelStartWindowClass>> createListLeftPanelStartWindowClass(JProgressBar progressBarView) {
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
		double stepForProgressBar=25;
		if(techniIzh.size()>0){
		stepForProgressBar = 25 / techniIzh.size();
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
	progressBarView.setValue(ProgressBarSize);
	System.out.println(groupList.size()+" ////////////////////");
	return groupList;
}

private static List<Request> cerateList(String monthPeriod, String zpitvan_produkt) {
	int curentYear = getYar();
	List<Request> list = new ArrayList<Request>();
	List<Request> listRequest = RequestDAO.getListRequestFromProgramm(zpitvan_produkt);

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
			if (str.equals(monthPeriod)&& curentYear==year) {
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
		year -=1;
	} 
	return year;
}

public void performTask() {
	// TODO Auto-generated method stub
	
}






}

