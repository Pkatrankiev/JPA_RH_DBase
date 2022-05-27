package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;


import GlobalVariable.ReadFileWithGlobalTextVariable;
import Reference.MounthlyReferenceForCNRDWater;
import Reference.MounthlyReferenceForMenuEjectionVolums;

public class MenuEjectionVolums extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;
	static String MenuEjectionVolums_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MenuEjectionVolums");
	
	public MenuEjectionVolums() {
		super(MenuEjectionVolums_TitleName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			    	JFrame f = new JFrame();
			    	new MounthlyReferenceForMenuEjectionVolums(f, MenuEjectionVolums_TitleName) ;
			    	
			
	}
}
