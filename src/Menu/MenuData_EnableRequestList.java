package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import Table.Table_Request_List;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class MenuData_EnableRequestList extends AbstractMenuAction {

	private static final long serialVersionUID = 1L;

	public MenuData_EnableRequestList() {
		super("Списък на Заявките");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		 
		TranscluentWindow round = new TranscluentWindow();
	
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	 JFrame f = new JFrame();
		 		new Table_Request_List(f,round,Login.getCurentUser(),null);
	    	
		     }
		    });
		    thread.start();
		    
	}

}
