package WindowView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import Aplication.UsersDAO;
import DBase_Class.Users;
import WindowViewAplication.AutoSuggestor;

import java.awt.Component;
import java.awt.Container;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JTextField txt_nik_name;
	private JPasswordField passwordField;
	private JLabel lbl_Username;
	private JLabel lbl_Password;
	private boolean succeeded = false;
	private List<Users> users_list = UsersDAO.getInListAllValueUsers();
	public Login(Frame parent) {
		super(parent, "Логване", true);
		int idUser = 0;

		setBounds(100, 100, 254, 145);
		getContentPane().setLayout(new BorderLayout());
		// центрира рамката (центъра на текущия монитор)
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lbl_Username = new JLabel("прякор");
		lbl_Username.setBounds(10, 15, 46, 14);
		contentPanel.add(lbl_Username);
		{
			txt_nik_name = new JTextField();
			txt_nik_name.setHorizontalAlignment(SwingConstants.LEFT);
			txt_nik_name.setToolTipText("nik-name");
			txt_nik_name.setBounds(78, 12, 115, 20);
			contentPanel.add(txt_nik_name);
			txt_nik_name.setColumns(10);
			 ArrayList<String> words = new ArrayList<>();
			 for (Users user : users_list) {
				 words.add(user.getNikName_users());
			}
            
			AutoSuggestor autoSuggestor = new AutoSuggestor(txt_nik_name, this, words, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.99f);
				
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(78, 43, 115, 20);
			contentPanel.add(passwordField);
		}

		lbl_Password = new JLabel("парола");
		lbl_Password.setBounds(10, 46, 46, 14);
		contentPanel.add(lbl_Password);

		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			// buttonPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new
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
	        	passwordField.setText("");
				txt_nik_name.setText("");
				succeeded = false;
	            e.getWindow().dispose();
	           
	        }
	    });
	}
	}

	public boolean isSucceeded() {
		return succeeded;
	}

	public String getUsername() {
		return txt_nik_name.getText().trim();
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
			System.out .println(enter_pass +" - "+ user.getPass_users()+" - "+getUsername()+" - "+user.getNikName_users());
			if (enter_pass.equals(user.getPass_users()) && getUsername().equals(user.getNikName_users()))
				succeeded = true;
			
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
