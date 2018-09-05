package Menu;

import java.awt.event.ActionEvent;

import Table.TableRequestList;


public class MenuRequense_RequenseList extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuRequense_RequenseList() {
		super("Списък на Заявките ");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TableRequestList.DrawTableWithRequestList();
	}

	
}
