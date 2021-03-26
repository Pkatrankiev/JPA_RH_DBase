package Menu;

import java.awt.event.ActionEvent;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import Reference.SampleMonthlyReference;

public class MenuSampleMonthlyReference extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;
	static String  MenuSampleMonthlyReference_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MenuSampleMonthlyReference_TitleName");
	
	public MenuSampleMonthlyReference() {
		super(MenuSampleMonthlyReference_TitleName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SampleMonthlyReference.SampleMonthly_Reference();
	}
	

}
