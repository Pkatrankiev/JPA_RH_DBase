package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import OldClases.Test_ReadGmmaReportFile;


public class MenuOder extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;
	static String MenuOder_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MenuOder_TitleName");
	
	public MenuOder() {
		super(MenuOder_TitleName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			    	JFrame f = new JFrame();
			    	 new Test_ReadGmmaReportFile(f, MenuOder_TitleName) ;
			
	}
}
