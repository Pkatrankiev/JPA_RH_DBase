package Menu;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import Table.TableRequestList;
import WindowView.ThreadProcesingRoundGif;
import WindowView.TranscluentWindow;

public class MenuData_EnableSequenseList extends AbstractMenuAction {

	private static final long serialVersionUID = 1L;

	public MenuData_EnableSequenseList() {
		super("Редактиране на Заявките");
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
