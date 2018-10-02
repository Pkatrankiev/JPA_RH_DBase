
package Menu;

import java.awt.event.ActionEvent;

import ReadAndSaveDocFailInDBase.ChoiceDocFileFrame;
import Table.TableResultsList;
import WindowView.RequestView;

public class MenuData_ReadDataFromDocFileSaveInDBase extends AbstractMenuAction {

	private static final long serialVersionUID = 1L;

	public MenuData_ReadDataFromDocFileSaveInDBase() {
		super("Четене от Word и запис");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ChoiceDocFileFrame reqView = new ChoiceDocFileFrame();
		reqView.setVisible(true);
	}

}
