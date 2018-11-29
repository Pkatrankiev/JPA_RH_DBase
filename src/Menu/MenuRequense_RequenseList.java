package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import Table.Table_Request_List;
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
		    	 
		    	 JFrame f = new JFrame();
		 		new Table_Request_List(f,round,null,null);

		    	
		     }
		    });
		    thread.start();
	}

	
}
