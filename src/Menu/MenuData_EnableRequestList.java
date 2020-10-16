package Menu;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;

import DefaultTableList.ViewTableList;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class MenuData_EnableRequestList extends AbstractMenuAction {

	private static final long serialVersionUID = 1L;
	static String EnableRequestList_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("EnableRequestList_TitleName");

	public MenuData_EnableRequestList() {
		
		super(EnableRequestList_TitleName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		 
		TranscluentWindow round = new TranscluentWindow();
	
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	 JFrame f = new JFrame();
//		 		new Table_Request_List(f,round,Login.getCurentUser(),"request", EnableRequestList_TitleName,true);
	    	
		 		new ViewTableList(f, round, Login.getCurentUser(), "request", EnableRequestList_TitleName,true, null);

		 		
		     }
		    });
		    thread.start();
		    
	}

}
