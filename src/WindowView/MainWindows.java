package WindowView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenu;
import javax.swing.JTextPane;
import java.awt.Panel;
import java.awt.Font;
import javax.swing.Box;
import java.awt.Label;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import javax.swing.UIManager;
import java.awt.Cursor;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import Aplication.UsersDAO;
import DBase_Class.Users;

import java.awt.SystemColor;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MainWindows {
	private final Action action = new SwingAction();

	/**
	 * @wbp.parser.entryPoint
	 */
	public void Window() {
		final JFrame win = new JFrame("JDialog Demo");
		win.setTitle("my RHA");

		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(900, 600);
		win.setLocationRelativeTo(null);
		// win.setLayout(new FlowLayout(1));
		// win.setLayout(new BorderLayout());
		win.getContentPane().setLayout(new GridLayout(4, 4));

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setLayout(new BorderLayout());
		menuBar_1.setBackground(Color.LIGHT_GRAY);
		win.setJMenuBar(menuBar_1);

		// ***********************************************************
		final JPanel panel = new JPanel();
		panel.setBackground(Color.blue);
		panel.setSize(400, 400);
		FlowLayout layout = new FlowLayout(1, 20, 20);
		panel.setLayout(layout);

		final JLabel label1 = new JLabel();
		final JLabel label2 = new JLabel();
		final JButton bnt = new JButton();

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
		final Login loginDlg = new Login(win);
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
		;

		JMenu mnNewMenu = new JMenu("Меню");
		mnNewMenu.setInheritsPopupMenu(true);
		mnNewMenu.setSelected(true);
		mnNewMenu.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));

		menuBar_1.add(mnNewMenu, BorderLayout.WEST);

		final Panel panel_2 = new Panel();
		mnNewMenu.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 15, 15));

		JLabel lblNewLabel_1 = new JLabel("Отваряне на Заявка ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setIcon(
				new ImageIcon(MainWindows.class.getResource("/javax/swing/plaf/metal/icons/ocean/newFolder.gif")));
		panel_2.add(lblNewLabel_1);
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panel_2.setBackground(Color.WHITE);
			}

			public void mousePressed(MouseEvent e) {
				if(loginDlg.getUsername().equals("")){
					JOptionPane.showMessageDialog(lblNewLabel_1, "Логнете се");
				}else{
				Users user = UsersDAO.getValueUsersByNicName(loginDlg.getUsername());
				RequestView reqView = new RequestView(user);
				reqView.setVisible(true);
				}
			}
		});

		final Panel panel_1 = new Panel();
		mnNewMenu.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 15, 15));

		JLabel lblNewLabel = new JLabel("Генериране на Нова Заявка ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setIcon(new ImageIcon(
				MainWindows.class.getResource("/org/apache/log4j/lf5/viewer/images/channelexplorer_new.gif")));
		panel_1.add(lblNewLabel);
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_1.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panel_1.setBackground(Color.WHITE);
			}
		});

		final JButton btnLogin = new JButton("Login");
		menuBar_1.add(btnLogin, BorderLayout.EAST);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textBtnLogin = btnLogin.getText();
				if (textBtnLogin.equals("LogOut")) {
					btnLogin.setText("LogIn");
					win.setTitle("my RHA");
				} else {
					
					loginDlg.setVisible(true);
					// if logon successfully
					if (loginDlg.isSucceeded()) {
						win.setTitle("my RHA" + " -> Hi " + loginDlg.getUsername() + "!");
						btnLogin.setText("LogOut");
						System.out.println("Hi " + loginDlg.getUsername() + "!");
					} else {
						System.out.println("Hi NOT " + loginDlg.getUsername() + "!");
					}
				}
			}
		});

		win.setVisible(true);

	}

	public boolean Login() {
		boolean corectUser = false;
		return corectUser;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
