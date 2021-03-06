package Menu;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DBase_Class.Users;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.AddResultsView;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class MenuRequense_AddResultsFrame extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;
	
	
	public MenuRequense_AddResultsFrame() {
		super(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("AddResultsFrame_TitleName"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Users loginDlg = Login.getCurentUser();
		if (loginDlg == null) {
			JOptionPane.showMessageDialog(null, ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("logInMesege"));
		} else {
		TranscluentWindow round = new TranscluentWindow();
		
		 final Thread thread = new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	 JFrame f = new JFrame();
		 		new AddResultsView(f,round, loginDlg);
		 			    	
		     }
		    });
		    thread.start();
		}
	}

}
