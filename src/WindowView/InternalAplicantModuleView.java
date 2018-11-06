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
import DBase_Class.Extra_module;
import DBase_Class.Internal_applicant;
import DBase_Class.Request;
import DBase_Class.Users;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import Aplication.External_applicantDAO;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Dimension;

public class InternalAplicantModuleView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String fondHeatText = "Tahoma";
	private Font font = new  Font(fondHeatText, Font.PLAIN, 11);
	private JTextField txtField_Tel;
	private JTextArea txtArea_Organiztion;
	private JTextArea txtArea_Adress;
	
	private Internal_applicant internal_Aplic;
	private String strOrganiz = "";
	private String strAdress = "";

	private String strTel = "";
	private int id_InternalAplicBDate;
	
	public InternalAplicantModuleView(JFrame parent,Internal_applicant  tamplateInternalAplic,TranscluentWindow round) {
				super(parent, "", true);
				setRootPaneCheckingEnabled(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 77, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		if(tamplateInternalAplic!=null){
			strOrganiz = tamplateInternalAplic.getInternal_applicant_organization();
			strAdress = tamplateInternalAplic.getInternal_applicant_address();
			strTel = tamplateInternalAplic.getInternal_applicant_telephone();
			internal_Aplic = tamplateInternalAplic;
			id_InternalAplicBDate = tamplateInternalAplic.getId_internal_applicant();
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
			JLabel lbl_Organiztion = new JLabel("Звено на ДП РАО:");
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
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						internal_Aplic = new Internal_applicant(
								txtArea_Organiztion.getText(), 
								txtArea_Adress.getText(), 
								txtField_Tel.getText());
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
						
						internal_Aplic = tamplateInternalAplic;
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

	public Internal_applicant getInternal_AplicFromInternalModuleView(){
		Boolean fl = true;
		if(!txtArea_Adress.getText().equals(internal_Aplic.getInternal_applicant_address())){
			internal_Aplic.setInternal_applicant_address(txtArea_Adress.getText());
			fl=false;
		}
		if(!txtArea_Organiztion.getText().equals(internal_Aplic.getInternal_applicant_organization())){
			internal_Aplic.setInternal_applicant_organization(txtArea_Organiztion.getText());
			fl=false;
		}
		if(!txtField_Tel.getText().equals(internal_Aplic.getInternal_applicant_telephone())){
			internal_Aplic.setInternal_applicant_telephone(txtField_Tel.getText());
			fl=false;
		}
		if(fl){
			internal_Aplic.setId_internal_applicant(id_InternalAplicBDate);
		}
		return internal_Aplic;
		
			}
}
