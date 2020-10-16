package Menu;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;

import DefaultTableList.ViewTableList;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class MenuData_EnableResultsList extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;
	static String EnableResultList_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("EnableResultsList_TitleName");

	public MenuData_EnableResultsList() {
		super(EnableResultList_TitleName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		TranscluentWindow round = new TranscluentWindow();
		
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	 JFrame f = new JFrame();
//		 		new Table_Results_List_Test(f,round,Login.getCurentUser(), null);
		 		
//		 		new Table_Results_List(f,round,Login.getCurentUser(), null);
		 		new ViewTableList(f, round, Login.getCurentUser(), "Results", EnableResultList_TitleName,true, null);

		 			    	
		     }
		    });
		    thread.start();
		
	}

}
