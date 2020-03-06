package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import WindowView.FrameChoiceRequestByCode;

public class MenuDoc_CreateRequest extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;

	public MenuDoc_CreateRequest() {
		super("Генериране на Заявка");
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			    	JFrame f = new JFrame();
			    	 new FrameChoiceRequestByCode(f, "Генериране на Заявка") ;
			
	}

}