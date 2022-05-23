package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import AddResultViewFunction.AddResultViewMetods;
import Aplication.AplicantDAO;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.MainWindow;
import WindowView.TranscluentWindow;



public class ArhiveDBase extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;
	static String ArhiveDBase_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("ArhiveDBase_TitleName");
	
	
	public ArhiveDBase() {
		super(ArhiveDBase_TitleName);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		TranscluentWindow round = new TranscluentWindow();
		
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	 AplicantDAO.backupDB_From_RemoteServer(round);
		    	
		 			    	
		     }
		    });
		    thread.start();
	
		
		
	}

	
	

}
