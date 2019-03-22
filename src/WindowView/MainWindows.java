package WindowView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import java.awt.Frame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import CreateWordDocProtocol.GenerateRequestWordDoc;
import Menu.MenuData_AddResultsFrame;
import Menu.MenuData_EnableInternalAplicant;
import Menu.MenuData_EnableResultsList;
import Menu.MenuData_EnableSampleList;
import Menu.MenuData_EnableRequestList;
import Menu.MenuData_ReadDataFromDocFileSaveInDBase;
import Menu.MenuDoc_CreateProtokol;
import Menu.MenuDoc_CreateRazpredFormu;
import Menu.MenuDoc_CreateRequest;
import Menu.MenuRequense_NewExtraRequense;
import Menu.MenuRequense_NewRequense;
import Menu.MenuRequense_NewRequenseInTamplate;
import Menu.MenuRequense_RequenseList;
import Table.Table_Request_List;
import javafx.beans.binding.When;

public class MainWindows {

	private static String loginStr = "logIn";
	private Login loginDlg;
	private TranscluentWindow round;
	public void WindowNew() {
		final JFrame win = new JFrame();
		
		GetVisibleLAF(win);

		win.setTitle("my RHA");
		win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		win.setSize(900, 600);
		win.setLocationRelativeTo(null);
		win.getContentPane().setLayout(new GridLayout(4, 4));

		win.setJMenuBar(createMenu(win));

		win.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GenerateRequestWordDoc.deleteTempDataDir();
				win.setVisible(false);
				win.dispose();
				System.exit(0);
			}
		});
		win.setVisible(true);

	}

	private void GetVisibleLAF(final JFrame win) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(win);
			this.pack();
		} catch (Exception ex) {
			Logger.getLogger(JFileChooser.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void pack() {
		try {
		} catch (UnsupportedOperationException eu) {
		}
	}

	private JMenuBar createMenu(Frame win) {
		JMenuBar menu = new JMenuBar();
		// menu.setLayout(new BorderLayout());
		menu.add(createRequenseMenu());
		menu.add(createDataMenu());
		menu.add(createWordDocMenu());
		menu.add(createLoginMenu(win), BorderLayout.EAST);

		return menu;
	}

	private JMenu createRequenseMenu() {
		JMenu sequenseMenu = new JMenu("Заявки");
		sequenseMenu.setMnemonic(KeyEvent.VK_Z);
		
		sequenseMenu.add(new MenuRequense_NewRequense());
		sequenseMenu.add(new MenuRequense_NewExtraRequense());
		sequenseMenu.add(new MenuRequense_NewRequenseInTamplate());
		sequenseMenu.add(new MenuData_AddResultsFrame());
		sequenseMenu.addSeparator();
		sequenseMenu.add(new MenuRequense_RequenseList());
		return sequenseMenu;
	}

	
	private JMenu createDataMenu() {
		JMenu dataMenu = new JMenu("Данни");
		dataMenu.setMnemonic(KeyEvent.VK_D);
		dataMenu.add(new MenuData_EnableRequestList());
		dataMenu.add(new MenuData_EnableSampleList());
		dataMenu.add(new MenuData_EnableResultsList());
		dataMenu.add(new MenuData_EnableInternalAplicant());
		dataMenu.add(new MenuData_ReadDataFromDocFileSaveInDBase());

		return dataMenu;
	}
	
	private JMenu createWordDocMenu() {
		JMenu docMenu = new JMenu("Документи");
		docMenu.setMnemonic(KeyEvent.VK_W);
		docMenu.add(new MenuDoc_CreateProtokol());
		docMenu.add(new MenuDoc_CreateRequest());
		docMenu.add(new MenuDoc_CreateRazpredFormu());
		

		return docMenu;
	}

	private JButton createLoginMenu(Frame win) {
		JButton loginMenu = new JButton(loginStr);
		loginMenu.setMnemonic(KeyEvent.VK_L);
		loginMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 round = new TranscluentWindow();
				String textBtnLogin = loginMenu.getText();
				
				if (textBtnLogin.equals("LogOut")) {
					round.StopWindow();
					Login.logOut();
					loginMenu.setText("LogIn");
					win.setTitle("my RHA");
				} else {
					 final Thread thread = new Thread(new Runnable() {
					     @Override
					     public void run() {
					    	 
					    	 StartLoginMenu(win, loginMenu);
				    	
					     }
					    });
					    thread.start();
					
				}
			}

	
		});
		return loginMenu;
	}
	private void StartLoginMenu(Frame win, JButton loginMenu) {
	
			    	  loginDlg = new Login(win, round);
				    	loginDlg.setVisible(true);
		
		 
		if ( loginDlg.isSucceeded()) {
			win.setTitle("my RHA" + " -> Hi " + loginDlg.getUsername() + "!");
			loginMenu.setText("LogOut");

		}
	}

}
 