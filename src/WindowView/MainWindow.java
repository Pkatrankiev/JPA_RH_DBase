package WindowView;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import CreateWordDocProtocol.GenerateRequestWordDoc;
import DBase_Class.TableColumn;
import DBase_Class.Users;
import GlobalVariable.GlobalPathForIcons;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import InfoPanelInMainWindow.CreatRightPanel;
import InfoPanelInMainWindow.CreateMainWindowInfoPanelWithProgrssBar;
import Menu.MenuData_EnableRequestList;
import Menu.MenuData_EnableResultsList;
import Menu.MenuData_EnableSampleList;
import Menu.MenuDoc_CreateProtokol;
import Menu.MenuDoc_CreateRazpredFormu;
import Menu.MenuDoc_CreateRequest;
import Menu.MenuOder;
import Menu.MenuReference;
import Menu.MenuRequense_AddDobiveFrame;
import Menu.MenuRequense_AddResultsFrame;
import Menu.MenuRequense_DeleteRequense;
import Menu.MenuRequense_NewRequense;
import Menu.MenuRequense_NewRequenseInTamplate;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Aplication.TableColumnDAO;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 8880252554988817126L;
	private JPanel contentPane;

	private static String mainWindow_Title = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
			.get("MainWindow_Title");
	private static String loginStr = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
			.get("MainWindow_LogInStr_Btn");
	private static String logOutStr = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
			.get("MainWindow_LogOutStr_Btn");
	private static String version = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MainWindow_Ver");
	private static Login loginDlg;
	

	public MainWindow(TranscluentWindow round) {

		setMinimumSize(new Dimension(900, 600));
		GetVisibleLAF(this);
		setTitle(mainWindow_Title);

		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(GlobalPathForIcons.get_destination_winIcon())));
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

		JLabel lblVer = new JLabel(version + " ");
		panel_1.add(lblVer);

		JProgressBar progressBar = new JProgressBar();
		panel_1.add(progressBar);
		progressBar.setPreferredSize(new Dimension(146, 15));
		progressBar.setValue(0);

		JPanel basicPanel = new JPanel();
		basicPanel.setLayout(new GridLayout(0, 2, 0, 0));
		contentPane.add(basicPanel);

		JPanel panel_Left = new JPanel();
		basicPanel.add(panel_Left);
		panel_Left.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_Left = new JScrollPane();
		panel_Left.add(scrollPane_Left);

		JPanel under_panel_Left = new JPanel();
		scrollPane_Left.setViewportView(under_panel_Left);
		under_panel_Left.setLayout(new BoxLayout(under_panel_Left, BoxLayout.Y_AXIS));

		JPanel panel_row_Left_Label = new JPanel();
		panel_row_Left_Label.setMaximumSize(new Dimension(32767, 20));
		under_panel_Left.add(panel_row_Left_Label);
		panel_row_Left_Label.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		

		JButton btnProgressBar = new JButton(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MainWindow_Btn_Refresh"));
		btnProgressBar.setEnabled(false);
		panel_1.add(btnProgressBar);

	

		
			JPanel panel_Right = new JPanel();
			basicPanel.add(panel_Right);
			panel_Right.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane_Right = new JScrollPane();
			panel_Right.add(scrollPane_Right);

			JPanel under_panel_Right = new JPanel();
			scrollPane_Right.setViewportView(under_panel_Right);
			under_panel_Right.setLayout(new BoxLayout(under_panel_Right, BoxLayout.Y_AXIS));


			
			


			btnProgressBar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (progressBar.getValue() == 100) {
						progressBar.setValue(0);
						under_panel_Left.removeAll();
						under_panel_Left.revalidate();
						under_panel_Left.repaint();
						
						under_panel_Right.removeAll();
						under_panel_Right.revalidate();
						under_panel_Right.repaint();
						
						
						
						btnProgressBar.setEnabled(false);
						new CreateMainWindowInfoPanelWithProgrssBar(progressBar, under_panel_Left,
								under_panel_Right, btnProgressBar).execute();
						
					}

				}
			});

		
		 setJMenuBar(createMenu(this));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GenerateRequestWordDoc.deleteTempDataDir();
				setVisible(false);
				if(CreatRightPanel.getKorektYearInTxtField()){
				List<TableColumn> list_TableColumn = TableColumnDAO.getListTableColumnByTipe_Table("MainWindow_text");
				list_TableColumn.get(0).setName_Column(CreatRightPanel.getYearInTxtField());
				TableColumnDAO.updateObjectTableColumn(list_TableColumn.get(0));
				}
				 
				dispose();
				System.exit(0);
			}
		});

		round.StopWindow();
		setVisible(true);

		new CreateMainWindowInfoPanelWithProgrssBar(progressBar, under_panel_Left,
				under_panel_Right, btnProgressBar)
				.execute();
		basicPanel .revalidate();
		basicPanel .repaint();
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
		menu.add(createReferenceMenu());
		menu.add(createOderMenu());
		menu.add(createLoginMenu(win), BorderLayout.EAST);

		return menu;
	}
	
	private JMenu createReferenceMenu() {
		JMenu oderMenu = new JMenu(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MainWindow_BtnMenu_Reference"));
		oderMenu.setMnemonic(KeyEvent.VK_S);

		oderMenu.add(new MenuReference());
		return oderMenu;
	}

	private JMenu createOderMenu() {
		JMenu oderMenu = new JMenu(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MainWindow_BtnMenu_Oder"));
		oderMenu.setMnemonic(KeyEvent.VK_D);

		oderMenu.add(new MenuOder());
		return oderMenu;
	}
	
	private JMenu createRequenseMenu() {
		JMenu requenseMenu = new JMenu(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MainWindow_BtnMenu_Request"));
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
		JMenu dataMenu = new JMenu(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MainWindow_BtnMenu_Data"));
		dataMenu.setMnemonic(KeyEvent.VK_D);
		dataMenu.add(new MenuData_EnableRequestList());
		dataMenu.add(new MenuData_EnableSampleList());
		dataMenu.add(new MenuData_EnableResultsList());
		// dataMenu.add(new MenuData_EnableInternalAplicant());
		// dataMenu.add(new MenuData_ReadDataFromDocFileSaveInDBase());

		return dataMenu;
	}

	private JMenu createWordDocMenu() {
		JMenu docMenu = new JMenu(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MainWindow_BtnMenu_Documents"));
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

				if (textBtnLogin.equals(logOutStr)) {
					round.StopWindow();
					Login.logOut();

					loginMenu.setText(loginStr);
					win.setTitle(mainWindow_Title);

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
			@SuppressWarnings("static-access")
			Users user = loginDlg.getCurentUser();

			win.setTitle(mainWindow_Title + " "
					+ ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MainWindow_Title_work") + " "
					+ user.getName_users() + " " + user.getFamily_users());
			loginMenu.setText(logOutStr);

		}
	}

}
