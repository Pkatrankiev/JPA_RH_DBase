package WindowView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import java.awt.Frame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import Menu.MenuNewSequense;
import WindowViewAplication.DocxMainpulator;



public class MainWindowsNew {

	private static String loginStr = "logIn";
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void WindowNew() {
		final JFrame win = new JFrame();
		
		 try {
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		        SwingUtilities.updateComponentTreeUI(win);
		        this.pack();
		    } catch (Exception ex) {
		        Logger.getLogger(JFileChooser.class.getName()).log(Level.SEVERE, null, ex);
		    }
		
		win.setTitle("my RHA");

		win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		win.setSize(900, 600);
		win.setLocationRelativeTo(null);
		win.getContentPane().setLayout(new GridLayout(4, 4));
		win.setJMenuBar(createMenu(win));

		// ***********************************************************
		final JPanel panel = new JPanel();
		panel.setBackground(Color.blue);
		panel.setSize(400, 400);
		FlowLayout layout = new FlowLayout(1, 20, 20);
		panel.setLayout(layout);

		final JLabel label1 = new JLabel();
		final JLabel label2 = new JLabel();
		final JButton bnt = new JButton();
		final Login loginDlg = new Login(win);

		label1.setText("Label11234567890");
		label2.setText("Label2qweryuip[';lkjhgfdszxcvbn");
		label1.setForeground(Color.white);
		panel.add(label1);
		panel.add(bnt);
		panel.add(label2);
		panel.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				panel.setBackground(Color.GREEN);
			}

			public void focusLost(FocusEvent e) {
				panel.setBackground(Color.DARK_GRAY);
			}
		});
		win.getContentPane().add(panel).setLocation(4, 2);
		;

		// ***************************************************

		final JLabel label11 = new JLabel();
		final JLabel label12 = new JLabel();
		final JButton bnt1 = new JButton();
//		final Login loginDlg = new Login(win);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.green);
		panel2.setSize(300, 400);
		label11.setText("Label11234567890");
		label12.setText("Label2qweryuip[';lkjhgfdszxcvbn");
		label11.setForeground(Color.white);
		panel2.add(label11);
		panel2.add(bnt1);
		panel2.add(label12);
		win.getContentPane().add(panel2).setLocation(0, 0);
		
		
		
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//		JMenu mnNewMenu = new JMenu("Меню");
//		mnNewMenu.setInheritsPopupMenu(true);
//		mnNewMenu.setSelected(true);
//		mnNewMenu.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
//		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
//
//		menuBar_1.add(mnNewMenu, BorderLayout.WEST);
//
//		final Panel panel_2 = new Panel();
//		mnNewMenu.add(panel_2);
//		panel_2.setLayout(new GridLayout(0, 1, 15, 15));
//
//
//		Panel panel_3 = new Panel();
//		mnNewMenu.add(panel_3);
//		panel_3.setLayout(new GridLayout(0, 1, 15, 15));
//
//		JLabel label = new JLabel("Генериране на Нова Заявка по шаблон ");
//		label.setHorizontalAlignment(SwingConstants.LEFT);
//		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		label.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//		panel_3.add(label);
//		panel_3.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				panel_3.setBackground(Color.LIGHT_GRAY);
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				panel_3.setBackground(Color.WHITE);
//			}
//
//			public void mousePressed(MouseEvent e) {
//
//				if (loginDlg.getUsername().equals("")) {
//					JOptionPane.showMessageDialog(win, "Логнете се");
//				} else
//				{
//					RequestViewAplication.DrawTableWithRequestTamplate();
//					
////					Users user = UsersDAO.getValueUsersByNicName(loginDlg.getUsername());
////					
////					reqView.setVisible(true);
//				}
//			}
//
//		});
//
//		final Panel panel_1 = new Panel();
//		mnNewMenu.add(panel_1);
//		panel_1.setLayout(new GridLayout(0, 1, 15, 15));
//
//		JLabel lblNewLabel = new JLabel("Отваряне на Заявка ");
//		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblNewLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
//		lblNewLabel.setIcon(new ImageIcon(
//				MainWindows.class.getResource("/org/apache/log4j/lf5/viewer/images/channelexplorer_new.gif")));
//		panel_1.add(lblNewLabel);
//		panel_1.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				panel_1.setBackground(Color.LIGHT_GRAY);
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				panel_1.setBackground(Color.WHITE);
//			}
//
//			public void mousePressed(MouseEvent e) {
//
////				String requestString = "3467";
////				RequestViewAplication.OpenRequestInWordDokTamplate(requestString);
//
//				
//				RequestViewAplication.DrawTableWithRequestList();
//
//			}
//
//		});
		
//		++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


		win.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
            	DocxMainpulator.deleteTempDataDir();
                	win.setVisible(false);
                    win.dispose();
                    System.exit(0);
            }
        });
		win.setVisible(true);

	}

	public boolean Login() {
		boolean corectUser = false;
		return corectUser;
	}
	
	private void pack() {
	    try{
	    }catch(UnsupportedOperationException eu){
	    };
	}
	
	JPanel menuPanel = new JPanel();
	private JMenuBar createMenu(Frame win) {
        JMenuBar menu = new JMenuBar();
//        menu.setLayout(new BorderLayout());
        menu.add(createTableMenu());
        menu.add(createFiltersMenu());
        menu.add(createLoginMenu(win), BorderLayout.EAST);

        return menu;
    }
	 private JMenu createTableMenu() {
	        JMenu tableMenu = new JMenu("Table");
	        tableMenu.setMnemonic(KeyEvent.VK_T);
	        tableMenu.add(new MenuNewSequense());
	        tableMenu.addSeparator();
//	        tableMenu.add(new MenuAutoResize(this));
	        return tableMenu;
	    }
	 private JMenu createFiltersMenu() {
	        JMenu filtereMenu = new JMenu("wwwwww");
	        filtereMenu.setMnemonic(KeyEvent.VK_T);
	        filtereMenu.add(new MenuNewSequense());

	        return filtereMenu;
	    }
	 
	 private JButton createLoginMenu(Frame win) {
	        JButton loginMenu = new JButton(loginStr);
	        loginMenu.setMnemonic(KeyEvent.VK_L);
	        loginMenu.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					
					String textBtnLogin = loginMenu.getText();
					if (textBtnLogin.equals("LogOut")) {
						Login.logOut();
						loginMenu.setText("LogIn");
						win.setTitle("my RHA");
					} else {
						Login loginDlg = new Login(win);
						loginDlg.setVisible(true);
						// if logon successfully
						if (loginDlg.isSucceeded()) {
							win.setTitle("my RHA" + " -> Hi " + loginDlg.getUsername() + "!");
							loginMenu.setText("LogOut");

						} else {
							System.out.println("Hi NOT " + loginDlg.getUsername() + "!");
						}
					}
				}
	        });
	       return loginMenu;
	    }

}
