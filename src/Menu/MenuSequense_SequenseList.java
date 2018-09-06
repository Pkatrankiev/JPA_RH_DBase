package Menu;

import java.awt.event.ActionEvent;

import Table.TableRequestList;


public class MenuSequense_SequenseList extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuSequense_SequenseList() {
		super("Списък на Заявките ");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TableRequestList.DrawTableWithRequestList();
	}

	
}
