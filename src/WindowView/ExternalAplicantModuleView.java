package WindowView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DBase_Class.External_applicant;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ExternalAplicantModuleView extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private String fondHeatText = "Tahoma";
	private Font font = new Font(fondHeatText, Font.PLAIN, 11);
	private JTextField txtField_Tel;
	private JTextArea txtArea_Organiztion;
	private JTextArea txtArea_Adress;
	private JTextArea txtArea_Dogovor;
	private External_applicant external_Aplic;
	private String strOrganiz = "";
	private String strAdress = "";
	private String strDogovor = "";
	private String strTel = "";
	
	public ExternalAplicantModuleView(JFrame parent, External_applicant tamplateExternalAplic,
			TranscluentWindow round) {
		super(parent, "", true);
		setRootPaneCheckingEnabled(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 77, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		if (tamplateExternalAplic != null) {
			strOrganiz = tamplateExternalAplic.getExternal_applicant_name();
			strAdress = tamplateExternalAplic.getExternal_applicant_address();
			strDogovor = tamplateExternalAplic.getExternal_applicant_contract_number();
			strTel = tamplateExternalAplic.getExternal_applicant_telephone();
			external_Aplic = tamplateExternalAplic;
			tamplateExternalAplic.getId_external_applicant();
		}

		{
			JLabel lblNewLabel = new JLabel("Заявител:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.gridwidth = 2;
			gbc_lblNewLabel.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lbl_Organiztion = new JLabel("Организация / Име:");
			GridBagConstraints gbc_lbl_Organiztion = new GridBagConstraints();
			gbc_lbl_Organiztion.gridwidth = 2;
			gbc_lbl_Organiztion.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lbl_Organiztion.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_Organiztion.gridx = 0;
			gbc_lbl_Organiztion.gridy = 1;
			contentPanel.add(lbl_Organiztion, gbc_lbl_Organiztion);
		}
		{
			txtArea_Organiztion = new JTextArea(strOrganiz);
			txtArea_Organiztion.setBorder(new LineBorder(Color.LIGHT_GRAY));
			txtArea_Organiztion.setFont(font);
			GridBagConstraints gbc_txtArea_Organiztion = new GridBagConstraints();
			gbc_txtArea_Organiztion.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtArea_Organiztion.anchor = GridBagConstraints.NORTH;
			gbc_txtArea_Organiztion.gridwidth = 3;
			gbc_txtArea_Organiztion.insets = new Insets(0, 0, 5, 0);
			gbc_txtArea_Organiztion.gridx = 0;
			gbc_txtArea_Organiztion.gridy = 2;
			contentPanel.add(txtArea_Organiztion, gbc_txtArea_Organiztion);
		}
		{
			JLabel lbl_Adress = new JLabel("Адрес:");
			GridBagConstraints gbc_lbl_Adress = new GridBagConstraints();
			gbc_lbl_Adress.gridwidth = 2;
			gbc_lbl_Adress.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lbl_Adress.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_Adress.gridx = 0;
			gbc_lbl_Adress.gridy = 3;
			contentPanel.add(lbl_Adress, gbc_lbl_Adress);
		}
		{
			txtArea_Adress = new JTextArea(strAdress);
			txtArea_Adress.setBorder(new LineBorder(Color.LIGHT_GRAY));
			txtArea_Adress.setFont(font);
			GridBagConstraints gbc_txtArea_Adress = new GridBagConstraints();
			gbc_txtArea_Adress.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtArea_Adress.anchor = GridBagConstraints.NORTH;
			gbc_txtArea_Adress.gridwidth = 3;
			gbc_txtArea_Adress.insets = new Insets(0, 0, 5, 0);
			gbc_txtArea_Adress.gridx = 0;
			gbc_txtArea_Adress.gridy = 4;
			contentPanel.add(txtArea_Adress, gbc_txtArea_Adress);
		}
		{
			JLabel lbl_Tel = new JLabel("Tel.:");
			GridBagConstraints gbc_lbl_Tel = new GridBagConstraints();
			gbc_lbl_Tel.anchor = GridBagConstraints.WEST;
			gbc_lbl_Tel.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_Tel.gridx = 0;
			gbc_lbl_Tel.gridy = 5;
			contentPanel.add(lbl_Tel, gbc_lbl_Tel);
		}
		{
			txtField_Tel = new JTextField(strTel);
			txtField_Tel.setBorder(new LineBorder(Color.LIGHT_GRAY));
			txtField_Tel.setFont(font);
			GridBagConstraints gbc_txtField_Tel = new GridBagConstraints();
			gbc_txtField_Tel.insets = new Insets(0, 0, 5, 0);
			gbc_txtField_Tel.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtField_Tel.gridx = 1;
			gbc_txtField_Tel.gridy = 5;
			contentPanel.add(txtField_Tel, gbc_txtField_Tel);
			txtField_Tel.setColumns(10);
		}
		{
			JLabel lbl_Dogovor = new JLabel("Договор №:");
			GridBagConstraints gbc_lbl_Dogovor = new GridBagConstraints();
			gbc_lbl_Dogovor.gridwidth = 2;
			gbc_lbl_Dogovor.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lbl_Dogovor.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_Dogovor.gridx = 0;
			gbc_lbl_Dogovor.gridy = 6;
			contentPanel.add(lbl_Dogovor, gbc_lbl_Dogovor);
		}
		{
			txtArea_Dogovor = new JTextArea(strDogovor);
			txtArea_Dogovor.setBorder(new LineBorder(Color.LIGHT_GRAY));
			txtArea_Dogovor.setFont(font);
			GridBagConstraints gbc_txtArea_Dogovor = new GridBagConstraints();
			gbc_txtArea_Dogovor.gridwidth = 3;
			gbc_txtArea_Dogovor.fill = GridBagConstraints.BOTH;
			gbc_txtArea_Dogovor.gridx = 0;
			gbc_txtArea_Dogovor.gridy = 7;
			contentPanel.add(txtArea_Dogovor, gbc_txtArea_Dogovor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						external_Aplic = new External_applicant(txtArea_Organiztion.getText().trim(), txtArea_Adress.getText().trim(),
								txtField_Tel.getText().trim(), txtArea_Dogovor.getText().trim());
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						external_Aplic = tamplateExternalAplic;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		round.StopWindow();
		setVisible(true);

	}

	public External_applicant getExternal_AplicFromExtraModuleView() {
		if (txtArea_Adress.getText().trim().isEmpty() && txtArea_Organiztion.getText().trim().isEmpty()
				&& txtField_Tel.getText().trim().isEmpty() && txtArea_Dogovor.getText().trim().isEmpty()) {
			external_Aplic = null;
		}

		return external_Aplic;
	}
}
