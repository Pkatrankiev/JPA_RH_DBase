package Menu;

import java.awt.event.ActionEvent;

import Table.TableRequestList;

public class MenuData_EnableSequenseList extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuData_EnableSequenseList() {
		super("Редактиране на Заявките");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TableRequestList.DrawTableWithEnableRequestList();
	}

}
