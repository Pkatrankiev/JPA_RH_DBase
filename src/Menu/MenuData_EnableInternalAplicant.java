package Menu;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;

import Table.Table_InternalApplicant_List;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class MenuData_EnableInternalAplicant extends AbstractMenuAction{

		
		private static final long serialVersionUID = 1L;

		public MenuData_EnableInternalAplicant() {
			super("Редактиране на Вътрешни Клиенти");
			// TODO Auto-generated constructor stub
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			TranscluentWindow round = new TranscluentWindow();
			 final Thread thread = new Thread(new Runnable() {
			     @Override
			     public void run() {
			    	 JDialog dialog = new JDialog();
			    	 new Table_InternalApplicant_List(dialog, round, Login.getCurentUser());
 				     }
			    });
			    thread.start();
				
			
		}

	}
