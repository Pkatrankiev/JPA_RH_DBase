package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import DBase_Class.Users;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import OldClases.Table_Request_List;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class MenuRequense_NewRequenseInTamplate extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;
static String titleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("NewRequenseInTamplate_TitleName");
	public MenuRequense_NewRequenseInTamplate() {
		super(titleName);
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
//			    	 f.setName("tamplete");
			    	 new Table_Request_List(f,round,Login.getCurentUser(),"request", titleName,true);

			    	
			     }
			    });
			    thread.start();
			

			 }
	}
}
