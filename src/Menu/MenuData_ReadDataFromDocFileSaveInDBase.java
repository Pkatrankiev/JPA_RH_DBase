
package Menu;

import java.awt.event.ActionEvent;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import ReadAndSaveDocFailInDBase.ChoiceDocFileFrame;


public class MenuData_ReadDataFromDocFileSaveInDBase extends AbstractMenuAction {

	private static final long serialVersionUID = 1L;
	
	public MenuData_ReadDataFromDocFileSaveInDBase() {
		super(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("ReadDataFromDocFileSaveInDBase_TitleName"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ChoiceDocFileFrame reqView = new ChoiceDocFileFrame();
		reqView.setVisible(true);
	}

}
