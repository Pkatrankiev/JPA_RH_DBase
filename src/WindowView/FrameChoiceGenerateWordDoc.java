package WindowView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class FrameChoiceGenerateWordDoc extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameChoiceGenerateWordDoc frame = new FrameChoiceGenerateWordDoc();
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
	public FrameChoiceGenerateWordDoc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 140);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(panel);
		
		JLabel lblRequest = new JLabel("Въведете № на заявката:");
		panel.add(lblRequest);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setMaximumSize(new Dimension(50, 16));
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		lblNewLabel.setMaximumSize(new Dimension(10, 0));
		lblNewLabel.setSize(new Dimension(50, 0));
		lblNewLabel.setPreferredSize(new Dimension(50, 0));
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		btnNewButton.setMaximumSize(new Dimension(89, 20));
	}

}
