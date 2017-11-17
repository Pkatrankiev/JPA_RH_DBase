package WindowView;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;

public class BasicWindow {

	private JFrame frame;
	protected Login log = new Login();;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasicWindow window = new BasicWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BasicWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);


		JButton loginButton = new JButton("Log in");
		menuBar.add(loginButton);

		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 20));

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			if (!log.isVisible()){
				System.out.println("data ");
				log.setVisible(true);
			}
			}
		});

	}

}
