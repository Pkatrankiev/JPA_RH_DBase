package Menu;


import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import DBase_Class.Users;
import WindowView.Login;
import WindowView.RequestView;



public class MenuSequense_NewSequense extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuSequense_NewSequense() {
		super("���������� �� ���� ������ ");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Users loginDlg = Login.getCurentUser();
		if (loginDlg == null) {
			JOptionPane.showMessageDialog(null, "������� ��");
		} else {
			
			RequestView reqView = new RequestView(loginDlg,null);
			reqView.setVisible(true);
		}
	}

	
}
