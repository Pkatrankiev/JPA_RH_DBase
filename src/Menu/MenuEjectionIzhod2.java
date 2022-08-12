package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import Reference.MounthlyReferenceForCNRDWater;

public class MenuEjectionIzhod2  extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;
	static String MenuEjectionIzhod2_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MounthlyReferenceForCNRDWaterTable_MenuEjectionIzhod2");
	
	public MenuEjectionIzhod2() {
		super(MenuEjectionIzhod2_TitleName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			    	JFrame f = new JFrame();
			    	new MounthlyReferenceForCNRDWater(f, MenuEjectionIzhod2_TitleName) ;
			    	
			
	}
}
