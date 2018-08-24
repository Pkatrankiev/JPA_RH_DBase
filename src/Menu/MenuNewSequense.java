package Menu;


import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import DBase_Class.Users;
import WindowView.Login;
import WindowView.RequestView;



public class MenuNewSequense extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuNewSequense() {
		super("Генериране на Нова Заявка ");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Users loginDlg = Login.getCurentUser();
		if (loginDlg == null) {
			JOptionPane.showMessageDialog(null, "Логнете се");
		} else {
			
			RequestView reqView = new RequestView(loginDlg,null);
			reqView.setVisible(true);
		}
	}

	
}
