package Menu;

import java.awt.event.ActionEvent;

import Table.Table_Sample_List;

public class MenuData_EnableSampleList extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuData_EnableSampleList() {
		super("������ �� �������");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Table_Sample_List.DrawTableWithEnableSampleList(null);
	}

}
