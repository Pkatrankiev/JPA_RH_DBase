package ReadAndSaveDocFailInDBase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import WindowViewAplication.DocxMainpulator;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class ChoiceDocFileFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField textField;
	private JPanel panel_1;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoiceDocFileFrame frame = new ChoiceDocFileFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChoiceDocFileFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		panel.setMinimumSize(new Dimension(10, 50));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("Път към Файла:");
		panel.add(lblNewLabel_1);
		
		textField = new JTextField("c:\\Soft\\Protokoli 2017\\");
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setMinimumSize(new Dimension(56, 100));
		panel.add(textField);
		textField.setColumns(35);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 String FILENAME = textField.getText();
//			String filePath = 	FILENAME.replaceAll("\\", "\")	;		 
				 try {
					DocxMainpulator.openWordDoc(FILENAME);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 SetDBfromWordDoc.setVolume(FILENAME);
			}
			
			
			
			
			
		});
		panel_1.add(btnNewButton);
	}

}
