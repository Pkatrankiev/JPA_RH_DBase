package LeftPanelInMainWindow;

import java.util.List;

public class VariableFromLeftPanel {
	
private static  List<List<LeftPanelStartWindowClass>> listLeftPanelStartWindow;
	

	public static void setListLeftPanelStartWindow(List<List<LeftPanelStartWindowClass>> listLeftPanelStartWindow) {
	VariableFromLeftPanel.listLeftPanelStartWindow = listLeftPanelStartWindow;
}


	public static List<List<LeftPanelStartWindowClass>> getListLeftPanelStartWindow() {
		return listLeftPanelStartWindow;
	}
}
