package Menu;

import java.awt.event.ActionEvent;

import Table.TableRequestList;
import WindowView.TranscluentWindow;


public class MenuRequense_RequenseList extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuRequense_RequenseList() {
		super("Списък на Заявките ");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TranscluentWindow round = new TranscluentWindow();
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	 TableRequestList.DrawTableWithEnableRequestList(round);

		    	
		     }
		    });
		    thread.start();
	}

	
}
