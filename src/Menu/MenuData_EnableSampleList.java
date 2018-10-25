package Menu;

import java.awt.event.ActionEvent;

import Table.TableSampleList;

public class MenuData_EnableSampleList extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuData_EnableSampleList() {
		super("Списък на Пробите");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TableSampleList.DrawTableWithEnableSampleList();
	}

}
