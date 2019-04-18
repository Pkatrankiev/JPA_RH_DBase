package Menu;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import Table.Table_Results_List;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class MenuData_EnableResultsList extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuData_EnableResultsList() {
		super("Редактиране на Резултатите");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TranscluentWindow round = new TranscluentWindow();
		
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	 JFrame f = new JFrame();
		 		new Table_Results_List(f,round,Login.getCurentUser(), null);
		 			    	
		     }
		    });
		    thread.start();
		
	}

}
