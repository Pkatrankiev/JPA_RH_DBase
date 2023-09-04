package Menu;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import ManagementBasicClassTable.ManagementMetodClass;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class MenuData_ManagementMetodClass extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuData_ManagementMetodClass() {
		super(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MetodClassManagement_TitleName"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TranscluentWindow round = new TranscluentWindow();
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 JFrame f = new JFrame();
		    	 new ManagementMetodClass(f, round, Login.getCurentUser(), null, null);
				     }
		    });
		    thread.start();
			
		
	}

}