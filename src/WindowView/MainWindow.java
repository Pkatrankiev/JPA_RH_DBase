package WindowView;

import java.awt.BorderLayout;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
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
import DBase_Class.TableColumn;
import DBase_Class.Users;
import GlobalVariable.GlobalPathForIcons;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import LeftPanelInMainWindow.CreatRightPanel;
import LeftPanelInMainWindow.IntegerDocumentFilter;
import LeftPanelInMainWindow.StartCreateListForRowInLeftPanelAndColumnInRightPanelWithProgrssBar;
import Menu.MenuData_EnableRequestList;
import Menu.MenuData_EnableResultsList;
import Menu.MenuData_EnableSampleList;
import Menu.MenuDoc_CreateProtokol;
import Menu.MenuDoc_CreateRazpredFormu;
import Menu.MenuDoc_CreateRequest;
import Menu.MenuOder;
import Menu.MenuRequense_AddDobiveFrame;
import Menu.MenuRequense_AddResultsFrame;
import Menu.MenuRequense_DeleteRequense;
import Menu.MenuRequense_NewRequense;
import Menu.MenuRequense_NewRequenseInTamplate;
import Table_Default_Structors.TableObject_Class;

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

import Aplication.RequestDAO;
import Aplication.TableColumnDAO;

import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.Insets;
import java.awt.ComponentOrientation;

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
	private JTextField textField_Panel_Right;
	private Boolean korektYearInTxtField=true;

	public MainWindow(TranscluentWindow round) {

		setMinimumSize(new Dimension(900, 600));
		GetVisibleLAF(this);
		setTitle(mainWindow_Title);

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

			JPanel text_Panel_Right = new JPanel();
			FlowLayout fl_text_Panel_Right = (FlowLayout) text_Panel_Right.getLayout();
			fl_text_Panel_Right.setAlignment(FlowLayout.RIGHT);
			text_Panel_Right.setMaximumSize(new Dimension(32767, 20));
			under_panel_Right.add(text_Panel_Right);
			
			JLabel lbl_text_Last_Request = new JLabel("Последната заявка: "+RequestDAO.getMaxRequestCode());
			text_Panel_Right.add(lbl_text_Last_Request);


			JLabel lbl_text_Panel_Right = new JLabel("Пропуснати записи в базата от началото на ");
			text_Panel_Right.add(lbl_text_Panel_Right);

			textField_Panel_Right = new JTextField();
			textField_Panel_Right.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			textField_Panel_Right.setMargin(new Insets(2, 0, 2, 0));
			textField_Panel_Right.setBorder(null);
//			textField_Panel_Right.setPreferredSize(new Dimension(2, 16));
			textField_Panel_Right.setMaximumSize(new Dimension(6, 50));
			text_Panel_Right.add(textField_Panel_Right);
			textField_Panel_Right.setColumns(3);
			textField_Panel_Right.setText(getTextStartYear());
			textField_Panel_Right.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent event) {

				}

				@Override
				public void keyReleased(KeyEvent event) {
					korektYearInTxtField = CreatRightPanel.checkEnterKorektYearForTextField(textField_Panel_Right);

				}

				@Override
				public void keyPressed(KeyEvent event) {

				}
			});
			IntegerDocumentFilter.addTo(textField_Panel_Right);

			JLabel lbl2_text_Panel_Right = new JLabel("г. за:");
			text_Panel_Right.add(lbl2_text_Panel_Right);

			JPanel column_Panel_Right = new JPanel();
			under_panel_Right.add(column_Panel_Right);
			column_Panel_Right.setLayout(new BorderLayout(0, 0));

			JPanel under_column_Panel_Right = new JPanel();
			column_Panel_Right.add(under_column_Panel_Right);
			under_column_Panel_Right.setLayout(new BoxLayout(under_column_Panel_Right, BoxLayout.X_AXIS));
			
			


			btnProgressBar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (progressBar.getValue() == 100) {
						progressBar.setValue(0);
						under_panel_Left.removeAll();
						under_panel_Left.revalidate();
						under_panel_Left.repaint();
						
						under_column_Panel_Right.removeAll();
						under_column_Panel_Right.revalidate();
						under_column_Panel_Right.repaint();
						
						
						
						btnProgressBar.setEnabled(false);
						new StartCreateListForRowInLeftPanelAndColumnInRightPanelWithProgrssBar(progressBar, under_panel_Left,
								under_column_Panel_Right, btnProgressBar, textField_Panel_Right).execute();
						
					}

				}
			});

		
		 setJMenuBar(createMenu(this));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GenerateRequestWordDoc.deleteTempDataDir();
				setVisible(false);
				if(korektYearInTxtField){
				List<TableColumn> list_TableColumn = TableColumnDAO.getListTableColumnByTipe_Table("MainWindow_text");
				list_TableColumn.get(0).setName_Column(textField_Panel_Right.getText());
				TableColumnDAO.updateObjectTableColumn(list_TableColumn.get(0));
				}
				dispose();
				System.exit(0);
			}
		});

		round.StopWindow();
		setVisible(true);

		new StartCreateListForRowInLeftPanelAndColumnInRightPanelWithProgrssBar(progressBar, under_panel_Left,
				under_column_Panel_Right, btnProgressBar, textField_Panel_Right)
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
		menu.add(createOderMenu());
		menu.add(createLoginMenu(win), BorderLayout.EAST);

		return menu;
	}
	
	private String getTextStartYear(){
		String startYear = "2020";
	List<TableColumn> list_TableColumn = TableColumnDAO.getListTableColumnByTipe_Table("MainWindow_text");
	startYear = list_TableColumn.get(0).getName_Column();
	
	return startYear;
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
