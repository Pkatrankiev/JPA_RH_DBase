package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DBase_Class.Users;
import WindowView.AddDobivViewWithTable;
import WindowView.AddResultsViewWithTable;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class MenuRequense_AddDobiveFrame extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuRequense_AddDobiveFrame() {
		super("��������� �� �����");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Users loginDlg = Login.getCurentUser();
		if (loginDlg == null) {
			JOptionPane.showMessageDialog(null, "������� ��");
		} else {
		TranscluentWindow round = new TranscluentWindow();
		
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	 JFrame f = new JFrame();
		 		new AddDobivViewWithTable(f,round, loginDlg);
		 			    	
		     }
		    });
		    thread.start();
		}
	}

}