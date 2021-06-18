package Reference;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ExcelFilesFunction.generateInformationToBAK45;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.TranscluentWindow;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class getExcelFileIzhvarlianiaBAK45 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	public getExcelFileIzhvarlianiaBAK45(JFrame parent, String frame_name, String mount_name, int godina,
			Object[][] DataValue) {
		super(parent, frame_name, true);

		String FILE_PATH = "D:/BAK-2021-NEW.xls";
		String OpenExcelFileLabel = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_OpenExcelFileLabel");
		String openBtn_Text = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("openBtn_Text");

		setSize(447, 133);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		contentPanel.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 123, 162, 126, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel(OpenExcelFileLabel);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField(FILE_PATH);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		JButton OpenButton = new JButton(openBtn_Text);
		GridBagConstraints gbc_OpenButton = new GridBagConstraints();
		gbc_OpenButton.anchor = GridBagConstraints.WEST;
		gbc_OpenButton.gridx = 2;
		gbc_OpenButton.gridy = 1;
		panel.add(OpenButton, gbc_OpenButton);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}

		});

		OpenBtnListener(OpenButton, textField);
		 okBtnListener(this, okButton, textField, mount_name, godina, DataValue);
		 
		setVisible(true);

	}

	public static void OpenBtnListener(JButton OpenBtn, JTextField textField) {
		OpenBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				final JFileChooser fileChooser = new JFileChooser();

				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showOpenDialog(null);

				String fileName = fileChooser.getSelectedFile().toString();
				File file = fileChooser.getSelectedFile();
				textField.setText(fileName);
				try (FileInputStream fis = new FileInputStream(file)) {
					// Do something here
				} catch (FileNotFoundException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				} catch (IOException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}

			}

		});
	}

	public static void okBtnListener(JDialog dialog,JButton btn, JTextField textField, String mount_name, int godina,
			Object[][] DataValue) {
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				new generateInformationToBAK45(textField.getText(), mount_name , godina,
						DataValue) 
				;
			}

		});
	}

}
