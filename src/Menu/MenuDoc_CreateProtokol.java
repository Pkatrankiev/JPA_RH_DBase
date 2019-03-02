package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import Table.Table_Sample_List;
import WindowView.ExtraRequestView;
import WindowView.FrameChoiceGenerateWordDoc;
import WindowView.TranscluentWindow;

public class MenuDoc_CreateProtokol extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;

	public MenuDoc_CreateProtokol() {
		super("Генериране на Протокол");
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
			    	JFrame f = new JFrame();
			    	 new FrameChoiceGenerateWordDoc(f, "Генериране на Протокол") ;
			
	}

}
