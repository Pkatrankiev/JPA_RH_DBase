package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import WindowView.FrameChoiceRequestByCode;

public class MenuRequense_DeleteRequense extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;

	public MenuRequense_DeleteRequense() {
		super("Изтриване на Заявка");
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			    	JFrame f = new JFrame();
			    	 new FrameChoiceRequestByCode(f, "Изтриване на Заявка") ;
			
	}

}
