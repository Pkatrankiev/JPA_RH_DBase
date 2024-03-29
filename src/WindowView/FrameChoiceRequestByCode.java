package WindowView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Aplication.RequestDAO;
import CreateWordDocProtocol.GenerateRequestWordDoc;
import CreateWordDocProtocol.GenerateDocRazpredFormul;
import CreateWordDocProtocol.Generate_Map_For_Request_Word_Document;
import CreateWordDocProtocol.GenerateDocProtokol;
import DBase_Class.Request;
import DBase_Class.Users;
import GlobalVariable.GlobalPathForDocFile;
import GlobalVariable.ResourceLoader;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.Map;
import java.awt.event.ActionEvent;

public class FrameChoiceRequestByCode extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private static Boolean corectRequestCode = true;

	private String fileRequestDoc = GlobalPathForDocFile.get_NameTamplate_Request();
	private String fileRazpredDoc = GlobalPathForDocFile.get_NameTamplate_RazpredForm();
	private String fileProtDoc = GlobalPathForDocFile.get_NameTamplate_Protokol();
	
	public FrameChoiceRequestByCode(JFrame parent, String nameFrame, Users user) {
		super(parent, nameFrame, true);
		setSize(280, 160);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 45, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Въведете № на заявката:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblError = new JLabel();
			GridBagConstraints gbc_lblError = new GridBagConstraints();
			gbc_lblError.insets = new Insets(0, 0, 0, 5);
			gbc_lblError.anchor = GridBagConstraints.EAST;
			gbc_lblError.gridwidth = 3;
			gbc_lblError.gridx = 0;
			gbc_lblError.gridy = 1;
			contentPanel.add(lblError, gbc_lblError);

			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(3);

			textField.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent event) {

				}

				@Override
				public void keyReleased(KeyEvent event) {
					enterRequestCode(textField, lblError);

				}

				@Override
				public void keyPressed(KeyEvent event) {

				}
			});
		}
		{

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						if (checkRequest()) {
							dispose();
							TranscluentWindow round = new TranscluentWindow();
							 final Thread thread = new Thread(new Runnable() {
							     @Override
							     public void run() {
							      		try {
											greateWordDocChoiseWithTextFrame(nameFrame, textField.getText(), round, user);
										} catch (ParseException e) {
											ResourceLoader.appendToFile(e);
											e.printStackTrace();
										}
									
							    	     	
							     }
							    });
							    thread.start();
						
							
						}

					}

				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}

	private void greateWordDocChoiseWithTextFrame(String nameFrame, String codeRequest, TranscluentWindow round, Users user) throws ParseException {
		Request choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", codeRequest);
		String date_time_reference = RequestViewFunction.GenerateStringRefDateTimeFromRequest(choiseRequest);

		Map<String, String> substitutionData = Generate_Map_For_Request_Word_Document.GenerateMapForRequestWordDocument(
				choiseRequest, RequestViewFunction.generateStringListIzpitvanPokazatelFromrequest(choiseRequest),
				RequestViewFunction.generateMasiveSampleDescriptionFromRequest(choiseRequest), date_time_reference);
		switch (nameFrame) {
		case "Генериране на Протокол":
			
			GenerateDocProtokol.GenerateProtokolWordDoc(fileProtDoc, choiseRequest, substitutionData, round);
					
			break;
		case "Генериране на Заявка":
			GenerateRequestWordDoc.generateAndSend_Request_Docx(fileRequestDoc,
					"Z-" + choiseRequest.getRecuest_code() + "_" + choiseRequest.getDate_request(), substitutionData, round);
			break;
		case "Генериране на Разпределителен формуляр":
			GenerateDocRazpredFormul.GenerateRazpFormWordDoc(fileRazpredDoc, choiseRequest, substitutionData, round);
			break;
			
		case "Изтриване на Заявка":
			DeleteRequestFromDBase.Delete_RequestFromDBase(choiseRequest, round);
		break;
		
		case "Редактиране на Заявка":
			JFrame f = new JFrame();
			new RequestView(f, user, choiseRequest, round, true);
		break;
		
		default:
			break;
		}

	}
	
	
	private Boolean checkRequest() {

		Boolean saveCheck = true;
		String str_RequestCode = "";

		if (!corectRequestCode) {
			textField.setBorder(new LineBorder(Color.RED));
			str_RequestCode = "код на заявката";
			saveCheck = false;
			JOptionPane.showMessageDialog(FrameChoiceRequestByCode.this, str_RequestCode, "Грешни данни",
					JOptionPane.ERROR_MESSAGE);
		}

		return saveCheck;
	}

	public static void enterRequestCode(JTextField txtField_RequestCode, JLabel lblError) {
		txtField_RequestCode.setText(RequestViewFunction.checkFormatString(txtField_RequestCode.getText()));
		if (!RequestDAO.checkRequestCode(txtField_RequestCode.getText())) {
			txtField_RequestCode.setForeground(Color.red);
			lblError.setText("Заявка с този номер не съществува");
			corectRequestCode = false;
		} else {

			if (RequestViewFunction.checkMaxVolume(txtField_RequestCode.getText(), 3000, 6000)) {
				txtField_RequestCode.setForeground(Color.red);
				lblError.setText("Некоректен номер");
				corectRequestCode = false;
			} else {
				txtField_RequestCode.setForeground(Color.BLACK);

				txtField_RequestCode.setBorder(new LineBorder(Color.BLACK));
				lblError.setText("");
				corectRequestCode = true;
			}
		}
	}

}
