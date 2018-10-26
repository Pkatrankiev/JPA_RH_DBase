package Menu;


import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import DBase_Class.Users;
import Table.TableRequestList;
import WindowView.Login;
import WindowView.RequestView;
import WindowView.TranscluentWindow;



public class MenuRequense_NewRequense extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuRequense_NewRequense() {
		super("Генериране на Нова Заявка ");
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
			    	 RequestView reqView = new RequestView(loginDlg,null,round);
						reqView.setVisible(true);
			    	     	
			     }
			    });
			    thread.start();
			
			
		}
	}

	
}
