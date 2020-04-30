package Menu;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.JFrame;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import Table.Table_Sample_List;
import WindowView.TranscluentWindow;

public class MenuData_EnableSampleList extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;
	
	public MenuData_EnableSampleList() {
		super(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("EnableSampleList_TitleName"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			TranscluentWindow round = new TranscluentWindow();
			
			 final Thread thread = new Thread(new Runnable() {
			     @Override
			     public void run() {
			    	 
			    	 JFrame f = new JFrame();
			    		new Table_Sample_List(f,round, null);
			     }
			    });
			    thread.start();
	}

}
