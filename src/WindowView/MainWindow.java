package WindowView;


import java.awt.BorderLayout;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


import CreateWordDocProtocol.GenerateRequestWordDoc;
import GlobalVariable.GlobalPathForIcons;
import LeftPanelInMainWindow.StartCreateListForRowInLeftPanelWithProgrssBar;
import Menu.MenuData_EnableInternalAplicant;
import Menu.MenuData_EnableRequestList;
import Menu.MenuData_EnableResultsList;
import Menu.MenuData_EnableSampleList;
import Menu.MenuData_ReadDataFromDocFileSaveInDBase;
import Menu.MenuDoc_CreateProtokol;
import Menu.MenuDoc_CreateRazpredFormu;
import Menu.MenuDoc_CreateRequest;
import Menu.MenuOder;
import Menu.MenuRequense_AddDobiveFrame;
import Menu.MenuRequense_AddResultsFrame;
import Menu.MenuRequense_DeleteRequense;
import Menu.MenuRequense_NewRequense;
import Menu.MenuRequense_NewRequenseInTamplate;
import Menu.MenuRequense_RequenseList;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.MatteBorder;
import javax.swing.JProgressBar;



public class MainWindow extends JFrame {
	
	
	private static final long serialVersionUID = 8880252554988817126L;
		private JPanel contentPane;
	private static String loginStr = "logIn";
	private static Login loginDlg;
	
	
	public MainWindow(TranscluentWindow round) {
		
		
		
		setMinimumSize(new Dimension(900, 600));
		GetVisibleLAF(this);
		setTitle("my RHA_Test");

		setIconImage(Toolkit.getDefaultToolkit().getImage(GlobalPathForIcons.get_destination_winIcon()));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(269, 149);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JProgressBar progressBar = new JProgressBar();
		panel_1.add(progressBar);
		progressBar.setPreferredSize(new Dimension(146, 15));
		progressBar.setValue(0);
		
			

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		contentPane.add(panel);
		
		JPanel panel_Left = new JPanel();
		panel.add(panel_Left);
		panel_Left.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_Left = new JScrollPane();
		panel_Left.add(scrollPane_Left);
		
		JPanel under_panel_Left = new JPanel();
		scrollPane_Left.setViewportView(under_panel_Left);
		under_panel_Left.setLayout(new BoxLayout(under_panel_Left, BoxLayout.Y_AXIS));
		
//		JPanel panel_row_Left_Label = new JPanel();
//		panel_row_Left_Label.setMaximumSize(new Dimension(32767, 20));
//		under_panel_Left.add(panel_row_Left_Label);
//		panel_row_Left_Label.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//		
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		under_panel_Left.add(lblNewLabel);
			
		
		JButton btnProgressBar = new JButton("Рефреш");
		btnProgressBar.setEnabled(false);
		panel_1.add(btnProgressBar);
		
		btnProgressBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(progressBar.getValue()==100){
					progressBar.setValue(0);
					under_panel_Left.removeAll();
					under_panel_Left.revalidate();
					under_panel_Left.repaint();
					btnProgressBar.setEnabled(false);
					new StartCreateListForRowInLeftPanelWithProgrssBar(progressBar,under_panel_Left, lblNewLabel, btnProgressBar).execute();
				}
				
			}
		});
		
		
		
	{
		JPanel panel_Right = new JPanel();
		panel.add(panel_Right);
		panel_Right.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_Right = new JScrollPane();
		panel_Right.add(scrollPane_Right);

		JPanel under_panel_Right = new JPanel();
		scrollPane_Right.setViewportView(under_panel_Right);
		under_panel_Right.setLayout(new BoxLayout(under_panel_Right, BoxLayout.Y_AXIS));

		JPanel panel_row_Right = new JPanel();
		
		panel_row_Right.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel_row_Right.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.GRAY));
		panel_row_Right.setMaximumSize(new Dimension(440, 15));
		
		
		panel_row_Right.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
		under_panel_Right.add(panel_row_Right);

		panel_row_Right.setLayout(new BoxLayout(panel_row_Right, BoxLayout.X_AXIS));

		JLabel lblLabel_Code = new JLabel("code");
		lblLabel_Code.setAlignmentY(Component.TOP_ALIGNMENT);
		lblLabel_Code.setMaximumSize(new Dimension(60, 14));

		panel_row_Right.add(lblLabel_Code);

		panel_row_Right.add(Box.createHorizontalStrut(5));

		JLabel lblLabel_Obect = new JLabel("obect");
		lblLabel_Obect.setAlignmentY(Component.TOP_ALIGNMENT);
		lblLabel_Obect.setMaximumSize(new Dimension(100, 14));
		panel_row_Right.add(lblLabel_Obect);
		
		

	}
		setJMenuBar(createMenu(this));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GenerateRequestWordDoc.deleteTempDataDir();
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		

		round.StopWindow();
		setVisible(true);
		
		new StartCreateListForRowInLeftPanelWithProgrssBar(progressBar,under_panel_Left, lblNewLabel, btnProgressBar).execute();
		

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

	private JMenuBar createMenu(Frame win) {
		JMenuBar menu = new JMenuBar();
		// menu.setLayout(new BorderLayout());
		menu.add(createRequenseMenu());
		menu.add(createDataMenu());
		menu.add(createWordDocMenu());
		menu.add(createOderMenu());
		menu.add(createLoginMenu(win), BorderLayout.EAST);

		return menu;
	}

	private JMenu createOderMenu() {
		JMenu oderMenu = new JMenu("Други");
		oderMenu.setMnemonic(KeyEvent.VK_D);

		oderMenu.add(new MenuOder());
		return oderMenu;
	}





	private JMenu createRequenseMenu() {
		JMenu requenseMenu = new JMenu("Заявки");
		requenseMenu.setMnemonic(KeyEvent.VK_Z);

		requenseMenu.add(new MenuRequense_NewRequense());
		// sequenseMenu.add(new MenuRequense_NewExtraRequense());
		requenseMenu.add(new MenuRequense_NewRequenseInTamplate());
		requenseMenu.addSeparator();
		requenseMenu.add(new MenuRequense_AddDobiveFrame());
		requenseMenu.add(new MenuRequense_AddResultsFrame());
		requenseMenu.addSeparator();
		requenseMenu.add(new MenuRequense_DeleteRequense());
		return requenseMenu;
	}

	private JMenu createDataMenu() {
		JMenu dataMenu = new JMenu("Данни");
		dataMenu.setMnemonic(KeyEvent.VK_D);
		dataMenu.add(new MenuData_EnableRequestList());
		dataMenu.add(new MenuData_EnableSampleList());
		dataMenu.add(new MenuData_EnableResultsList());
//		dataMenu.add(new MenuData_EnableInternalAplicant());
//		dataMenu.add(new MenuData_ReadDataFromDocFileSaveInDBase());


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
				TranscluentWindow round = new TranscluentWindow();
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

	public static void StartLoginMenu(Frame win, JButton loginMenu) {
		TranscluentWindow round = new TranscluentWindow();
		loginDlg = new Login(win, round);
		loginDlg.setVisible(true);

		if (loginDlg.isSucceeded()) {
			win.setTitle("my RHA" + " -> Hi " + loginDlg.getUsername() + "!");
			loginMenu.setText("LogOut");

		}
	}

	
 
}
