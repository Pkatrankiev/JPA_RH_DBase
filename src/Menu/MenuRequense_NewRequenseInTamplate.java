package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import Aplication.UsersDAO;
import DBase_Class.Users;
import Table.TableRequestTamplate;
import WindowView.Login;
import WindowView.RequestView;
import WindowView.RequestViewAplication;

public class MenuRequense_NewRequenseInTamplate extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuRequense_NewRequenseInTamplate() {
		super("���� ������ �� ������");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Users loginDlg = Login.getCurentUser();
		if (loginDlg == null) {
			JOptionPane.showMessageDialog(null, "������� ��");
		} else {
			TableRequestTamplate.DrawTableWithRequestTamplate();

			 }
	}
}