package InfoPanelInMainWindow;

import java.util.List;

public class VariableFromStartWindowPanel {
	
private static  List<List<LeftPanelStartWindowClass>> listLeftPanelStartWindow;
private static  List<Integer> listMissingRequestRightPanelStartWindow;
private static  List<Integer> listMissingResultsRightPanelStartWindow;
private static  List<Integer> listMissingProtokolsRightPanelStartWindow;
private static  List<String> listAllProtokols;

	public static void setListLeftPanelStartWindow(List<List<LeftPanelStartWindowClass>> listLeftPanelStartWindow) {
	VariableFromStartWindowPanel.listLeftPanelStartWindow = listLeftPanelStartWindow;
}


	public static List<List<LeftPanelStartWindowClass>> getListLeftPanelStartWindow() {
		return listLeftPanelStartWindow;
	}
	
	public static void setListMissingRequestRightPanelStartWindow(List<Integer> listMissingRequestRightPanelStartWindow) {
		VariableFromStartWindowPanel.listMissingRequestRightPanelStartWindow = listMissingRequestRightPanelStartWindow;
	}
	
		
	public static List<Integer> getListMissingRequestRightPanelStartWindow() {
		return listMissingRequestRightPanelStartWindow;
	}
	
	public static void setListMissingResultsRightPanelStartWindow(List<Integer>listMissingResultsRightPanelStartWindow) {
		VariableFromStartWindowPanel.listMissingResultsRightPanelStartWindow = listMissingResultsRightPanelStartWindow;
	}
	
		
	public static List<Integer> getListMissingResultsRightPanelStartWindo() {
		return listMissingResultsRightPanelStartWindow;
	}


	public static List<Integer> getListMissingProtokolsRightPanelStartWindo() {
		return listMissingProtokolsRightPanelStartWindow;
	}
	
	public static void setListMissingProtokolsRightPanelStartWindo(List<Integer>listMissingProtokolsRightPanelStartWindow) {
		VariableFromStartWindowPanel.listMissingProtokolsRightPanelStartWindow = listMissingProtokolsRightPanelStartWindow;
	}


	public static List<String> getListAllProtokols() {
		return listAllProtokols;
	}


	public static void setListAllProtokols(List<String> listAllProtokols) {
		VariableFromStartWindowPanel.listAllProtokols = listAllProtokols;
	}
}
