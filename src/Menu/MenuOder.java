package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import OldClases.Test_ReadGmmaReportFile;


public class MenuOder extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;

	public MenuOder() {
		super("Гама Кал. Екстракт");
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			    	JFrame f = new JFrame();
			    	 new Test_ReadGmmaReportFile(f, "Гама Кал. Екстракт") ;
			
	}
}
