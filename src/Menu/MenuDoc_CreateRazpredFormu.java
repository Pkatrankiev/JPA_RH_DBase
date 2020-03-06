package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import WindowView.FrameChoiceRequestByCode;

public class MenuDoc_CreateRazpredFormu extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;

	public MenuDoc_CreateRazpredFormu() {
		super("Генериране на Разпределителен формуляр");
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			    	JFrame f = new JFrame();
			    	 new FrameChoiceRequestByCode(f, "Генериране на Разпределителен формуляр") ;
			
	}

}