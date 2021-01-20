package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import GlobalVariable.ReadFileWithGlobalTextVariable;
import Reference.PeriodicReference;
import WindowView.TranscluentWindow;

public class MenuReference extends AbstractMenuAction{
	
	
	private static final long serialVersionUID = 1L;
	static String MenuReference_TitleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MenuPeriodicReference_TitleName");
	
	public MenuReference() {
		super(MenuReference_TitleName);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {

				JFrame f = new JFrame();
				new PeriodicReference(f, round);

			}
		});
		thread.start();
			
	}
}
