package WindowView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Aplication.UsersDAO;
import DBase_Class.Users;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowViewAplication.AutoSuggestor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private static JTextField txt_nik_name;
	private static JPasswordField passwordField;
	private JLabel lbl_Username;
	private JLabel lbl_Password;
	private static boolean succeeded = false;
	private static Users curentUser = null;
	private List<Users> users_list = null;

	public Login(Frame parent, String frameName, TranscluentWindow round) {
		
		super(parent, frameName, true);
		String pass = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("LoginFrame_pass");
		users_list = UsersDAO.getInListAllValueUsers();
//		pro.StopWindow();
		setBounds(100, 100, 272, 145);
		getContentPane().setLayout(new BorderLayout());
		// центрира рамката (центъра на текущия монитор)
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lbl_Username = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("LoginFrame_UserName"));
		lbl_Username.setBounds(10, 15, 109, 14);
		contentPanel.add(lbl_Username);
		{
			txt_nik_name = new JTextField();
			txt_nik_name.setHorizontalAlignment(SwingConstants.LEFT);
			txt_nik_name.setToolTipText("nik-name");
			txt_nik_name.setBounds(129, 12, 115, 20);
			contentPanel.add(txt_nik_name);
			txt_nik_name.setColumns(10);
			ArrayList<String> words = new ArrayList<>();
			for (Users user : users_list) {
				words.add(user.getNikName_users());
			}

			new AutoSuggestor(txt_nik_name, this, words, Color.WHITE.brighter(),
					Color.BLUE, Color.RED, 0.99f);

			{
				passwordField = new JPasswordField();
				passwordField.setBounds(129, 43, 115, 20);
				contentPanel.add(passwordField);
			}

			lbl_Password = new JLabel(pass);
			lbl_Password.setBounds(10, 46, 109, 14);
			contentPanel.add(lbl_Password);

			{
				JPanel buttonPane = new JPanel();
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				// buttonPane.setFocusTraversalPolicy(new
				// FocusTraversalOnArray(new
				// Component[] { okButton, cancelButton }));

				{
					okButton = new JButton("OK");
					okButton.setActionCommand("OK");
					getRootPane().setDefaultButton(okButton);

					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							okButton();

						}

					});

				}

				{
					cancelButton = new JButton("Cancel");
					cancelButton.setActionCommand("Cancel");

					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							passwordField.setText("");
							txt_nik_name.setText("");
						}
					});

				}
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				buttonPane.add(okButton);
				buttonPane.add(cancelButton);
			}
			this.setDefaultCloseOperation(Login.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent e) {
					logOut();
					e.getWindow().dispose();

				}
			});
		}
		
		round.StopWindow();
		
	}

	public boolean isSucceeded() {
		return succeeded;
	}

	public String getUsername() {
		return txt_nik_name.getText().trim();
	}

	public static  Users getCurentUser() {
		return curentUser;
	}

	private void okButton() {

		char[] pass = passwordField.getPassword();

		String enter_pass = "";
		for (char x : pass) {
			enter_pass += x;
		}

		// String md5_encrypted_pass_userInput =
		// encrypt(final_pass); //kriptirane na string v MD5
		// format
		for (Users user : users_list) {
			if (enter_pass.equals(user.getPass_users()) && getUsername().equals(user.getNikName_users())) {
				succeeded = true;
				curentUser = user;
			}
		}
		if (succeeded) {
			dispose();
		} else {
			JOptionPane.showMessageDialog(Login.this, "Invalid username or password", "Login",
					JOptionPane.ERROR_MESSAGE);
			// reset username and password
			passwordField.setText("");
			txt_nik_name.setText("");

		}
	}

	public static void logOut() {
		passwordField.setText("");
		txt_nik_name.setText("");
		succeeded = false;
		curentUser = null;
	}

	public static final String encrypt(String md5) { // kriptirane na string v
														// MD5 format
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
} 