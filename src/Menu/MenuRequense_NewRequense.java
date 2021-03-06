package Menu;


import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import DBase_Class.Users;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.Login;
import WindowView.RequestView;
import WindowView.TranscluentWindow;



public class MenuRequense_NewRequense extends AbstractMenuAction{
	
	private static final long serialVersionUID = 1L;

	public MenuRequense_NewRequense() {
		super(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("NewRequense_TitleName"));
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
			    	new RequestView(f, loginDlg,null,round,false);
		    	     	
			     }
			    });
			    thread.start();
			
			
		}
	}

	
}
