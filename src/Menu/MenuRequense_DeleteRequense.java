package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.FrameChoiceRequestByCode;

public class MenuRequense_DeleteRequense extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;
	static String DeleteRequense_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("DeleteRequense_TitleName");
	
	public MenuRequense_DeleteRequense() {
		super(DeleteRequense_TitleName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			    	JFrame f = new JFrame();
			    	 new FrameChoiceRequestByCode(f, DeleteRequense_TitleName, null) ;
			
	}

}
