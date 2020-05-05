package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DBase_Class.Users;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import OldClases.ExtraRequestView;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class MenuRequense_NewExtraRequense extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuRequense_NewExtraRequense() {
		super(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("NewExtraRequense_TitleName"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Users loginDlg = Login.getCurentUser();
		if (loginDlg == null) {
			JOptionPane.showMessageDialog(null, ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("LogInText"));
		} else {
			TranscluentWindow round = new TranscluentWindow();
			 final Thread thread = new Thread(new Runnable() {
			     @Override
			     public void run() {
			    	 JFrame f = new JFrame();
			    	new ExtraRequestView(f, loginDlg,null,round);
					
			    	     	
			     }
			    });
			    thread.start();
			
			
		}
	}

	
}
