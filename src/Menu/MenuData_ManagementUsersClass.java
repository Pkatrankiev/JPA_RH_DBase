package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import ManagementBasicClassTable.ManagementUsersClass;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class MenuData_ManagementUsersClass extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuData_ManagementUsersClass() {
		super(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Users_UsersManagement"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TranscluentWindow round = new TranscluentWindow();
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 JFrame f = new JFrame();
		    	 new ManagementUsersClass(f, round, Login.getCurentUser(), null, null);
				     }
		    });
		    thread.start();
			
		
	}

}