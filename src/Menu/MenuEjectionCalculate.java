package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import Reference.MounthlyReferenceForMenuEjectionCalculate;

public class MenuEjectionCalculate extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;
	static String MenuEjectionCalculate_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MenuEjection_Calculate");
	
	public MenuEjectionCalculate() {
		super(MenuEjectionCalculate_TitleName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
			    	JFrame f = new JFrame();
			    	new MounthlyReferenceForMenuEjectionCalculate(f,  MenuEjectionCalculate_TitleName) ;
		
	}
}