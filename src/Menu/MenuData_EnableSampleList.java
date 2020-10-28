package Menu;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;

import DefaultTableList.ViewTableList;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import Table.Table_Sample_List;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class MenuData_EnableSampleList extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;
	static String EnableSampleList_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("EnableSampleList_TitleName");

	public MenuData_EnableSampleList() {
		
		super(EnableSampleList_TitleName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			TranscluentWindow round = new TranscluentWindow();
			
			 final Thread thread = new Thread(new Runnable() {
			     @Override
			     public void run() {
			    	 
			    	 JFrame f = new JFrame();
			    	 new ViewTableList(f, round, Login.getCurentUser(), "Sample", EnableSampleList_TitleName,true, null);

			     }
			    });
			    thread.start();
	}

}
