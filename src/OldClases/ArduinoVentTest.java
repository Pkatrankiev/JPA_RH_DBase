package OldClases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JRadioButton;

public class ArduinoVentTest extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSwetlina;
	private JTextField textFieldSwetlinaInput;
	private JTextField textFieldWlaga;
	private JTextField textFieldWlaga_Input;
	private JTextField textFieldZakasnenie;
	private JRadioButton rdbtnNewRadioButton;
	private JLabel lblCounts;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArduinoVentTest frame = new ArduinoVentTest();
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
	public ArduinoVentTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{83, 157, 0, 80, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblSwetlina = new JLabel("swetlina");
		GridBagConstraints gbc_lblSwetlina = new GridBagConstraints();
		gbc_lblSwetlina.insets = new Insets(0, 0, 5, 5);
		gbc_lblSwetlina.anchor = GridBagConstraints.EAST;
		gbc_lblSwetlina.gridx = 0;
		gbc_lblSwetlina.gridy = 0;
		contentPane.add(lblSwetlina, gbc_lblSwetlina);
		
		textFieldSwetlina = new JTextField();
		textFieldSwetlina.setText("100");
		GridBagConstraints gbc_textFieldSwetlina = new GridBagConstraints();
		gbc_textFieldSwetlina.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSwetlina.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSwetlina.gridx = 1;
		gbc_textFieldSwetlina.gridy = 0;
		contentPane.add(textFieldSwetlina, gbc_textFieldSwetlina);
		textFieldSwetlina.setColumns(10);
		
		textFieldSwetlinaInput = new JTextField("50");
		GridBagConstraints gbc_textFieldSwetlinaInput = new GridBagConstraints();
		gbc_textFieldSwetlinaInput.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldSwetlinaInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSwetlinaInput.gridx = 2;
		gbc_textFieldSwetlinaInput.gridy = 0;
		contentPane.add(textFieldSwetlinaInput, gbc_textFieldSwetlinaInput);
		textFieldSwetlinaInput.setColumns(10);
		
		JLabel lblWlaga = new JLabel("Wlaga");
		GridBagConstraints gbc_lblWlaga = new GridBagConstraints();
		gbc_lblWlaga.anchor = GridBagConstraints.EAST;
		gbc_lblWlaga.insets = new Insets(0, 0, 5, 5);
		gbc_lblWlaga.gridx = 0;
		gbc_lblWlaga.gridy = 1;
		contentPane.add(lblWlaga, gbc_lblWlaga);
		
		textFieldWlaga = new JTextField();
		textFieldWlaga.setText("50");
		GridBagConstraints gbc_textFieldWlaga = new GridBagConstraints();
		gbc_textFieldWlaga.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldWlaga.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWlaga.gridx = 1;
		gbc_textFieldWlaga.gridy = 1;
		contentPane.add(textFieldWlaga, gbc_textFieldWlaga);
		textFieldWlaga.setColumns(10);
		
		textFieldWlaga_Input = new JTextField("40");
		GridBagConstraints gbc_textFieldWlaga_Input = new GridBagConstraints();
		gbc_textFieldWlaga_Input.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldWlaga_Input.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWlaga_Input.gridx = 2;
		gbc_textFieldWlaga_Input.gridy = 1;
		contentPane.add(textFieldWlaga_Input, gbc_textFieldWlaga_Input);
		textFieldWlaga_Input.setColumns(10);
		
		JLabel lblWlaga_Tolerans = new JLabel("Wlaga_Tolerans");
		GridBagConstraints gbc_lblWlaga_Tolerans = new GridBagConstraints();
		gbc_lblWlaga_Tolerans.insets = new Insets(0, 0, 5, 0);
		gbc_lblWlaga_Tolerans.gridx = 3;
		gbc_lblWlaga_Tolerans.gridy = 1;
		contentPane.add(lblWlaga_Tolerans, gbc_lblWlaga_Tolerans);
		
		JLabel lblZakasnenie = new JLabel("Zakasnenie");
		GridBagConstraints gbc_lblZakasnenie = new GridBagConstraints();
		gbc_lblZakasnenie.anchor = GridBagConstraints.EAST;
		gbc_lblZakasnenie.insets = new Insets(0, 0, 5, 5);
		gbc_lblZakasnenie.gridx = 0;
		gbc_lblZakasnenie.gridy = 2;
		contentPane.add(lblZakasnenie, gbc_lblZakasnenie);
		
		textFieldZakasnenie = new JTextField(2);
		textFieldZakasnenie.setText("2");
		GridBagConstraints gbc_textFieldZakasnenie = new GridBagConstraints();
		gbc_textFieldZakasnenie.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldZakasnenie.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldZakasnenie.gridx = 1;
		gbc_textFieldZakasnenie.gridy = 2;
		contentPane.add(textFieldZakasnenie, gbc_textFieldZakasnenie);
		textFieldZakasnenie.setColumns(10);
		
		rdbtnNewRadioButton = new JRadioButton("Exit");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton.gridx = 3;
		gbc_rdbtnNewRadioButton.gridy = 2;
		contentPane.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(false);
		
		JLabel lblWentilator = new JLabel("Wentilator");
		GridBagConstraints gbc_lblWentilator = new GridBagConstraints();
		gbc_lblWentilator.insets = new Insets(0, 0, 0, 5);
		gbc_lblWentilator.gridwidth = 3;
		gbc_lblWentilator.gridx = 0;
		gbc_lblWentilator.gridy = 3;
		contentPane.add(lblWentilator, gbc_lblWentilator);
		
		lblCounts = new JLabel("Counts");
		GridBagConstraints gbc_lblCounts = new GridBagConstraints();
		gbc_lblCounts.gridx = 3;
		gbc_lblCounts.gridy = 3;
		contentPane.add(lblCounts, gbc_lblCounts);
	
	
	
	
	
	
	rdbtnNewRadioButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			int wlagaMIN = 0;
			int wlagaMAX = 0;
			int swetlo = 0;
			int zakasnenie = 0;
			int wlagaInput = 0;
			int swetloInput = 0;
			boolean fl= true;
			int count = 0;
			lblWlaga_Tolerans.setText(wlagaMIN+" - "+wlagaMAX);
			
			if(rdbtnNewRadioButton.isSelected()){
				lblWentilator.setBackground(Color.GREEN);
			}else{
				lblWentilator.setBackground(Color.RED);
			}
	
	while (!rdbtnNewRadioButton.isSelected()) {
		
		
		
		lblCounts.setText(count+"");
		
		 wlagaMIN = Integer. parseInt(textFieldWlaga.getText())-5;
		 wlagaMAX =  Integer. parseInt(textFieldWlaga.getText())+5;
		 swetlo = Integer. parseInt(textFieldSwetlina.getText());
		 zakasnenie = Integer. parseInt(textFieldZakasnenie.getText());
		wlagaInput = Integer. parseInt(textFieldWlaga_Input.getText());
		swetloInput = Integer. parseInt(textFieldSwetlinaInput.getText());
	
		if(wlagaInput>wlagaMAX){
			lblWentilator.setBackground(Color.GREEN);
		}
		if(wlagaInput<wlagaMIN){
			lblWentilator.setBackground(Color.RED);
		}
		
		if(swetloInput > swetlo){
			zakasnenie(zakasnenie);
			swetloInput = Integer. parseInt(textFieldSwetlinaInput.getText());
			if(swetloInput > swetlo){
			lblWentilator.setBackground(Color.GREEN);
			}
		}else{
			zakasnenie(zakasnenie);
			swetloInput = Integer. parseInt(textFieldSwetlinaInput.getText());
			if(swetloInput < swetlo){
			lblWentilator.setBackground(Color.RED);
			}
		}
		validate();
		repaint();
		count++;
		
	}
		}
	});
	
	}

	private void zakasnenie(int zakasnenie) {
		try {
			TimeUnit.SECONDS.sleep(zakasnenie);
		}
		catch (Exception e) {
			System.out.println("Oops! Something went wrong!");
		}
	}

}
