package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import Reference.DobivReference;

public class MenuDobivReference extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;
	static String MenuReference_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_Dobiv_Menu");
	
	public MenuDobivReference() {
		super(MenuReference_TitleName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
				JFrame f = new JFrame();
			String label =	ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_Dobiv_FrameName");
				new  DobivReference(f,  label);

		
	}
}
