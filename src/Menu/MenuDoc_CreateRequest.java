package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.FrameChoiceRequestByCode;

public class MenuDoc_CreateRequest extends AbstractMenuAction{
		
	private static final long serialVersionUID = 1L;
	static String CreateRequest_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("CreateRequest_TitleName");
	
	public MenuDoc_CreateRequest() {
		super(CreateRequest_TitleName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			    	JFrame f = new JFrame();
			    	 new FrameChoiceRequestByCode(f, CreateRequest_TitleName) ;
			
	}

}