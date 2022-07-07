package Menu;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.FrameChoiceRequestByCode;

public class MenuDoc_CreateProtokol extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;
	static String CreateProtokol_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("CreateProtokol_TitleName");
	
	public MenuDoc_CreateProtokol() {
		super(CreateProtokol_TitleName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			    	JFrame f = new JFrame();
			    	 new FrameChoiceRequestByCode(f, CreateProtokol_TitleName, null) ;
			
	}

}
