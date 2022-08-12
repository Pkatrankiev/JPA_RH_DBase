package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import Reference.MounthlyReferenceEjectionRHtoORDK;


public class ManuMounthlyReferenceEjectionRHtoORDK extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;
	static String ManuMounthlyReferenceEjectionRHtoORDK_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MounthlyReferenceEjectionRHtoORDK_TitleName");
	
	public ManuMounthlyReferenceEjectionRHtoORDK() {
		super(ManuMounthlyReferenceEjectionRHtoORDK_TitleName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
			    	JFrame f = new JFrame();
			    	new MounthlyReferenceEjectionRHtoORDK(f,  ManuMounthlyReferenceEjectionRHtoORDK_TitleName) ;
		
	}
}