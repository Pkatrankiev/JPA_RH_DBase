package WindowView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JTextField textField;
	private JPasswordField passwordField;

	public Login() {
		int idUser = 0;
		setAlwaysOnTop(true);
		setTitle("Login");
		setBounds(100, 100, 254, 145);
		getContentPane().setLayout(new BorderLayout());
		// �������� ������� (������� �� ������� �������)
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.LEFT);
			textField.setToolTipText("nik-name");
			textField.setBounds(56, 12, 115, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(56, 43, 115, 20);
			contentPanel.add(passwordField);
		}
		
		
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { okButton, cancelButton }));

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
						textField.setText("");
					}
				});

			}
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			buttonPane.add(okButton);
			buttonPane.add(cancelButton);
		}
	}

	private void okButton() {
		String name = textField.getText();

		char[] pass = passwordField.getPassword();

		String final_pass = "";
		for (char x : pass) {
			final_pass += x;
		}
		String pass12 = "123";
		// String md5_encrypted_pass_userInput =
		// encrypt(final_pass); //kriptirane na string v MD5
		// format

		if (final_pass
				.equals(pass12)) { /*
									 * pass1 = the password from the database
									 */
			// Correct password
			System.out.println("corect pass = " + final_pass);
		}
		System.out.println("name = " + name + "   pass = " + final_pass);
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
