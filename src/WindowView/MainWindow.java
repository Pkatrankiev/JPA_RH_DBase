package WindowView;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Aplication.IzpitvanPokazatelDAO;
import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import CreateWordDocProtocol.GenerateRequestWordDoc;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Period;
import DBase_Class.Request;
import GlobalVariable.GlobalPathForDocFile;
import GlobalVariable.GlobalPathForIcons;
import Menu.MenuData_EnableInternalAplicant;
import Menu.MenuData_EnableRequestList;
import Menu.MenuData_EnableResultsList;
import Menu.MenuData_EnableSampleList;
import Menu.MenuData_ReadDataFromDocFileSaveInDBase;
import Menu.MenuDoc_CreateProtokol;
import Menu.MenuDoc_CreateRazpredFormu;
import Menu.MenuDoc_CreateRequest;
import Menu.MenuRequense_AddDobiveFrame;
import Menu.MenuRequense_AddResultsFrame;
import Menu.MenuRequense_NewRequense;
import Menu.MenuRequense_NewRequenseInTamplate;
import Menu.MenuRequense_RequenseList;
import OldClases.FindFile;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.ComponentOrientation;
import javax.swing.border.MatteBorder;



public class MainWindow extends JFrame {
	
	private String dir_Protocols = GlobalPathForDocFile.get_destinationDir_Protocols();
	private JPanel contentPane;
	private static String loginStr = "logIn";
	private Login loginDlg;
	private TranscluentWindow round;
	private String[] listMonitoringGroup = { "Газообразни изхвърляния", "Течни изхвърляния", "Въздух", "Вода"};
	
	public MainWindow(TranscluentWindow round) {
		setMinimumSize(new Dimension(900, 600));
		GetVisibleLAF(this);
		setTitle("my RHA");

		setIconImage(Toolkit.getDefaultToolkit().getImage(GlobalPathForIcons.get_destination_winIcon()));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(269, 149);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		{
			JPanel panel_Left = new JPanel();
			contentPane.add(panel_Left);
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

			String month = getPreviousMesec(1);
			JLabel lblNewLabel = new JLabel("Проби от програма периодичен мониторинг за м." + month);
			lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel_row_Left_Label.add(lblNewLabel);

			String inLabel = "";
			for (String string : listMonitoringGroup) {
				if(string.equals("Вода")){
					month = getPreviousMesec(2);
					inLabel = " за м."+month;
				}
				JPanel panel = new JPanel();
				panel.setMaximumSize(new Dimension(32767, 20));
				under_panel_Left.add(panel);
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				JLabel lbl_Grup_ = new JLabel(string+inLabel);
					panel.add(lbl_Grup_);

				List<Request> techniIzh = cerateList(month, string);
				for (Request request : techniIzh) {
					createRowLeftPanel(under_panel_Left, request);

				}
			}
		}

		{
			JPanel panel_Right = new JPanel();
			contentPane.add(panel_Right);
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

	}

	private JPanel createRowLeftPanel(JPanel under_panel_Left, Request request) {
		JPanel panel = new JPanel();
		under_panel_Left.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 1));
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.GRAY));
		panel.setMaximumSize(new Dimension(440, 16));
		panel.setAutoscrolls(true);
		

		JLabel lblLabel_Code = new JLabel(request.getRecuest_code());
		lblLabel_Code.setPreferredSize(new Dimension(30, 14));
		panel.add(lblLabel_Code);

		JLabel lblLabel_Obect = new JLabel(request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane());
		lblLabel_Obect.setPreferredSize(new Dimension(200, 14));
		panel.add(lblLabel_Obect);


		JLabel lblLabel_Sample = new JLabel(createStringPokazatel(request));
		lblLabel_Sample.setPreferredSize(new Dimension(40, 14));
		panel.add(lblLabel_Sample);
		

		FindFile ff = new FindFile();
		JLabel lblLabel_Protokol = new JLabel(ff.findFile(request.getRecuest_code(), new File(dir_Protocols)));
		lblLabel_Protokol.setPreferredSize(new Dimension(140, 14));
		panel.add(lblLabel_Protokol);

		

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}
		});

		return panel;
	}
	



	private String createStringPokazatel(Request request) {
		String str = "";
		List<IzpitvanPokazatel> list = IzpitvanPokazatelDAO.getValueIzpitvan_pokazatelByRequest(request);
		if (list.size() > 1) {
			str = "<html>";
			for (IzpitvanPokazatel izpPok : list) {
				str = str + izpPok.getPokazatel().getName_pokazatel().replaceFirst("Съдържание на ", "") + "<br>";
			}

			str = str.substring(0, str.length() - 4);
			str = str + "</html>";
		} else {
			str = list.get(0).getPokazatel().getName_pokazatel().replaceFirst("Съдържание на ", "");
		}
		return str;
	}

	private List<Request> cerateList(String monthPeriod, String zpitvan_produkt) {
		List<Request> list = new ArrayList<Request>();
		for (Request request : RequestDAO.getListRequestFromProgramm(zpitvan_produkt)) {
			Period period = SampleDAO.getListSampleFromColumnByVolume("request", request).get(0).getPeriod();
			if (period != null) {
				String str = period.getValue();
				if (str.equals(monthPeriod)) {
					list.add(request);
				}
			}
		}
		return list;
	}

	private String getPreviousMesec(int koregMount) {
		int month = DatePicker.getActualyMonth() + koregMount;
		if (month == 1) {
			month = 12;
		} else {
			month--;
		}
		return PeriodDAO.getPeriodById(month).getValue();
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

	// /**
	// * @wbp.parser.entryPoint
	// */
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
		// sequenseMenu.add(new MenuRequense_NewExtraRequense());
		sequenseMenu.add(new MenuRequense_NewRequenseInTamplate());
		sequenseMenu.add(new MenuRequense_AddDobiveFrame());
		sequenseMenu.add(new MenuRequense_AddResultsFrame());
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

		if (loginDlg.isSucceeded()) {
			win.setTitle("my RHA" + " -> Hi " + loginDlg.getUsername() + "!");
			loginMenu.setText("LogOut");

		}
	}

	
}
