package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.FrameChoiceRequestByCode;

public class MenuDoc_CreateRazpredFormu extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;
	static String CreateRazpredFormu_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("CreateRazpredFormu_TitleName");
	
	public MenuDoc_CreateRazpredFormu() {
		super(CreateRazpredFormu_TitleName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			    	JFrame f = new JFrame();
			    	 new FrameChoiceRequestByCode(f, CreateRazpredFormu_TitleName) ;
			
	}

}