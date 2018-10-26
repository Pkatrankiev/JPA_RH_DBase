package Menu;

import java.awt.event.ActionEvent;
import Table.TableResultsList;

public class MenuData_EnableResultsList extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuData_EnableResultsList() {
		super("Редактиране на Резултатите");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TableResultsList.TableResultsList();
	}

}
