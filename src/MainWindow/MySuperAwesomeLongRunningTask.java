package MainWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

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
import Table.Table_RequestToObektNaIzp;
import WindowView.DatePicker;

public class MySuperAwesomeLongRunningTask {
	
	 	private static Progressable progressable;
	 	
		private static String dir_Protocols = GlobalPathForDocFile.get_destinationDir_Protocols();
		private static String[] listMonitoringGroup = { "Газообразни изхвърляния", "Течни изхвърляния", "Въздух", "Вода" };

   
	public void performTask(Progressable progressableVew) {
        this.progressable = progressableVew;
    }
	
	protected MySuperAwesomeLongRunningTask(){
    	createListLeftPanelStartWindowClass();
    }
	public static  List<List<LeftPanelStartWindowClass>> createListLeftPanelStartWindowClass() {
		List<List<LeftPanelStartWindowClass>> groupList = Lists.newArrayList();
		
		System.out.println("//////////////////////////");
		FindFile ff = new FindFile();
		String month = getPreviousMesec(1);
		String monitGroup = "";
			
		int ProgressBarSize = 0;
		 progressable.setProgress(ProgressBarSize);
		for (int i = 0; i < listMonitoringGroup.length; i++) {
			monitGroup = listMonitoringGroup[i];

			if (monitGroup.equals("Вода")) {
				month = getPreviousMesec(2);

			}
			List<Request> techniIzh = cerateList(month, monitGroup);
			double stepForProgressBar = 25 / techniIzh.size();

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
				 progressable.setProgress(ProgressBarSize);
			}
			groupList.add(list);
		}
		 progressable.setProgress(ProgressBarSize);
		return groupList;
	}
	
	private static List<Request> cerateList(String monthPeriod, String zpitvan_produkt) {
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
			if (period != null) {
				String str = period.getValue();
				if (str.equals(monthPeriod)) {
					list.add(request);
				}
			}
		}
		System.out.println(max + " / " + min);

		return list;
	}

	private JPanel createRowLeftPanel(JPanel under_panel_Left, Request request) {
		JPanel panel = new JPanel();
		// under_panel_Left.add(panel);
		// panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 1));
		// panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.GRAY));
		// panel.setMaximumSize(new Dimension(440, 16));
		// panel.setAutoscrolls(true);
		//
		//
		// JLabel lblLabel_Code = new JLabel(request.getRecuest_code());
		// lblLabel_Code.setPreferredSize(new Dimension(30, 14));
		// panel.add(lblLabel_Code);
		//
		// JLabel lblLabel_Obect = new
		// JLabel(Table_RequestToObektNaIzp.createStringListObektNaIzp(
		// Table_RequestToObektNaIzp.getListStringOfRequest_To_ObektNaIzpitvaneRequest(
		// request), false));
		// lblLabel_Obect.setPreferredSize(new Dimension(200, 14));
		// panel.add(lblLabel_Obect);
		//
		//
		// JLabel lblLabel_Sample = new JLabel(createStringPokazatel(request));
		// lblLabel_Sample.setPreferredSize(new Dimension(40, 14));
		// panel.add(lblLabel_Sample);
		//
		//
		// FindFile ff = new FindFile();
		// JLabel lblLabel_Protokol = new
		// JLabel(ff.findFile(request.getRecuest_code(), new
		// File(dir_Protocols)));
		// lblLabel_Protokol.setPreferredSize(new Dimension(140, 14));
		// panel.add(lblLabel_Protokol);
		//
		//
		//
		// panel.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseEntered(MouseEvent e) {
		// }
		//
		// public void mousePressed(MouseEvent e) {
		// }
		// });
		//
		return panel;
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

	



	
}
