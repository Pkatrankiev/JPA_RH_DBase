package WindowView;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class MainWindows {

	public void Window(){
		 final JFrame win = new JFrame("JDialog Demo");
	       win.setTitle("my RHA");
			
			win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JMenuBar menuBar_1 = new JMenuBar();
			win.setJMenuBar(menuBar_1);
			
			final JButton btnLogin = new JButton("Login");
			menuBar_1.add(btnLogin);
			btnLogin.addActionListener(
		            new ActionListener(){
		                public void actionPerformed(ActionEvent e) {
		                	String textBtnLogin = btnLogin.getText();
		                	if (textBtnLogin.equals("LogOut") ) {
		                		btnLogin.setText("LogIn");
		                		 win.setTitle("my RHA");
							}
		                	else{
		                    Login loginDlg = new Login(win);
		                    loginDlg.setVisible(true);
		                    // if logon successfully
		                    if(loginDlg.isSucceeded()){
		                    	 win.setTitle("my RHA"+" -> Hi " + loginDlg.getUsername() + "!");
		                    	 btnLogin.setText("LogOut");
		                        System.out.println("Hi " + loginDlg.getUsername() + "!");
		                    }else{
		                    	 System.out.println("Hi NOT " + loginDlg.getUsername() + "!");
		                    }
		                }
		                }
		            });
			win.setSize(900, 600);
			win.setLocationRelativeTo(null);
			win.setLayout(new FlowLayout());
			win.setVisible(true);
			
		}




		
		
		public boolean Login (){
			
			
			
			boolean corectUser = false;
			
			return corectUser;
		} 

		
		
	}


