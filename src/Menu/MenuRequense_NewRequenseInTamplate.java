package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import Aplication.UsersDAO;
import DBase_Class.Users;
import Table.TableRequestList;
import Table.TableRequestTamplate;
import WindowView.Login;
import WindowView.RequestView;
import WindowView.RequestViewAplication;
import WindowView.TranscluentWindow;

public class MenuRequense_NewRequenseInTamplate extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuRequense_NewRequenseInTamplate() {
		super("Нова Заявка от шаблон");
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
			    	 
			    	 TableRequestTamplate.DrawTableWithRequestTamplate(round);

			    	
			     }
			    });
			    thread.start();
			

			 }
	}
}
