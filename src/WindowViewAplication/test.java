package WindowViewAplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Aplication.RequestDAO;

import javax.swing.JTextField;

public class test extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

//				textField.setText(RequestViewAplication.checkFormatRequestCode(textField.getText()));
			
			}

			@Override
			public void keyReleased(KeyEvent event) {
//				textField.setText(RequestViewAplication.checkFormatRequestCode(textField.getText()));
				
			}

			@Override
			public void keyPressed(KeyEvent event) {

				textField.setText(RequestViewAplication.checkFormatRequestCode(textField.getText()));
				
			}
		});
		contentPane.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
	}

}
