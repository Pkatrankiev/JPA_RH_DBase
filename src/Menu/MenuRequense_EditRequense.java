package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DBase_Class.Users;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.FrameChoiceRequestByCode;
import WindowView.Login;

public class MenuRequense_EditRequense extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;
	static String titleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("EditRequense_TitleName");
	
	public MenuRequense_EditRequense() {
		super(titleName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Users loginDlg = Login.getCurentUser();
		if (loginDlg == null) {
			JOptionPane.showMessageDialog(null, ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("LogInText"));
		} else {
		JFrame f = new JFrame();
		 new FrameChoiceRequestByCode(f, titleName, loginDlg) ;
		 }
			
	}

}

