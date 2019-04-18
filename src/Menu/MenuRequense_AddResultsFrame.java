package Menu;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import DBase_Class.Users;
import WindowView.AddResultsViewWithTable;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class MenuRequense_AddResultsFrame extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuRequense_AddResultsFrame() {
		super("Въвеждане на Резултати");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Users loginDlg = Login.getCurentUser();
		if (loginDlg == null) {
			JOptionPane.showMessageDialog(null, "Логнете се");
		} else {
		TranscluentWindow round = new TranscluentWindow();
		
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	 JFrame f = new JFrame();
		 		new AddResultsViewWithTable(f,round, loginDlg);
		 			    	
		     }
		    });
		    thread.start();
		}
	}

}
