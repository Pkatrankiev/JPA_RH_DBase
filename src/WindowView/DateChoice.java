package WindowView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;

public class DateChoice extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTextField txtStartDate;
	private static JTextField txtEndDate;
	private static JTextField txt_fid_date_time_reception;


	public DateChoice(Frame parent, String date_time_reception) {
		super(parent, "Избор на референтна дата и време", true);
		setBounds(100, 100, 550, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{87, 143, 110, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		
		{
			JLabel lbl_date_time_reception = new JLabel("Референтна дата (средата на периода)");
			GridBagConstraints gbc_lbl_date_time_reception = new GridBagConstraints();
			gbc_lbl_date_time_reception.gridwidth = 2;
			gbc_lbl_date_time_reception.insets = new Insets(0, 0, 0, 5);
			gbc_lbl_date_time_reception.anchor = GridBagConstraints.EAST;
			gbc_lbl_date_time_reception.gridx = 0;
			gbc_lbl_date_time_reception.gridy = 2;
			contentPanel.add(lbl_date_time_reception, gbc_lbl_date_time_reception);
		}
		{
			txt_fid_date_time_reception = new JTextField(date_time_reception);
			txt_fid_date_time_reception.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent event) {

					if (DatePicker.incorrectDate(txt_fid_date_time_reception.getText(), true))
						txt_fid_date_time_reception.setForeground(Color.RED);
					else
						txt_fid_date_time_reception.setForeground(Color.BLACK);
				}

				@Override
				public void keyReleased(KeyEvent event) {

					if (DatePicker.incorrectDate(txt_fid_date_time_reception.getText(), true))
						txt_fid_date_time_reception.setForeground(Color.RED);
					else
						txt_fid_date_time_reception.setForeground(Color.BLACK);
				}

				@Override
				public void keyPressed(KeyEvent event) {

					if (DatePicker.incorrectDate(txt_fid_date_time_reception.getText(), true))
						txt_fid_date_time_reception.setForeground(Color.RED);
					else
						txt_fid_date_time_reception.setForeground(Color.BLACK);
				}
			});

			GridBagConstraints gbc_date_time_reception = new GridBagConstraints();
			gbc_date_time_reception.insets = new Insets(0, 0, 0, 5);
			gbc_date_time_reception.fill = GridBagConstraints.HORIZONTAL;
			gbc_date_time_reception.gridx = 2;
			gbc_date_time_reception.gridy = 2;
			contentPanel.add(txt_fid_date_time_reception, gbc_date_time_reception);
			txt_fid_date_time_reception.setColumns(10);
		}
		
		
		// StartDate of date_time_reception
		{
			JLabel lblNewLabel = new JLabel("Начална дата");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			
			txtStartDate = new JTextField();
			txtStartDate.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent event) {

					if (DatePicker.incorrectDate(txtStartDate.getText(), true))
						txtStartDate.setForeground(Color.RED);
					else{
						txtStartDate.setForeground(Color.BLACK);
						String textRefDate = "";
						textRefDate = DatePicker.getReferenceDate(txtStartDate.getText(), txtEndDate.getText());
						if (DatePicker.incorrectDate(textRefDate, true))
							txt_fid_date_time_reception.setForeground(Color.RED);
						else
							txt_fid_date_time_reception.setForeground(Color.BLACK);

						txt_fid_date_time_reception.setText(textRefDate);
					}
				}

				@Override
				public void keyReleased(KeyEvent event) {

					if (DatePicker.incorrectDate(txtStartDate.getText(), true))
						txtStartDate.setForeground(Color.RED);
					else{
						txtStartDate.setForeground(Color.BLACK);
						String textRefDate = "";
						textRefDate = DatePicker.getReferenceDate(txtStartDate.getText(), txtEndDate.getText());
						if (DatePicker.incorrectDate(textRefDate, true))
							txt_fid_date_time_reception.setForeground(Color.RED);
						else
							txt_fid_date_time_reception.setForeground(Color.BLACK);

						txt_fid_date_time_reception.setText(textRefDate);
					}
				}

				@Override
				public void keyPressed(KeyEvent event) {

					if (DatePicker.incorrectDate(txtStartDate.getText(), true))
						txtStartDate.setForeground(Color.RED);
					else{
						txtStartDate.setForeground(Color.BLACK);
						String textRefDate = "";
						textRefDate = DatePicker.getReferenceDate(txtStartDate.getText(), txtEndDate.getText());
						if (DatePicker.incorrectDate(textRefDate, true))
							txt_fid_date_time_reception.setForeground(Color.RED);
						else
							txt_fid_date_time_reception.setForeground(Color.BLACK);

						txt_fid_date_time_reception.setText(textRefDate);
					}
				}
			});
			
			GridBagConstraints gbc_txtStartDate = new GridBagConstraints();
			gbc_txtStartDate.insets = new Insets(0, 0, 5, 5);
			gbc_txtStartDate.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtStartDate.gridx = 2;
			gbc_txtStartDate.gridy = 0;
			contentPanel.add(txtStartDate, gbc_txtStartDate);
			txtStartDate.setColumns(10);
		}
		{
			JButton btnStartData = new JButton("Начална дата");
			btnStartData.setMaximumSize(new Dimension(105, 23));
			btnStartData.setMinimumSize(new Dimension (105, 23));
			btnStartData.setPreferredSize(new Dimension (105, 23));
			btnStartData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					final JFrame d = new JFrame();
					DatePicker dPicer = new DatePicker(d, true, txtStartDate.getText());
					txtStartDate.setText(dPicer.setPickedDate(true));
					String textRefDate = "";
					textRefDate = DatePicker.getReferenceDate(txtStartDate.getText(), txtEndDate.getText());
					if (DatePicker.incorrectDate(textRefDate, true))
						txt_fid_date_time_reception.setForeground(Color.RED);
					else
						txt_fid_date_time_reception.setForeground(Color.BLACK);

					txt_fid_date_time_reception.setText(textRefDate);
				}
			});

			GridBagConstraints gbc_btnStartData = new GridBagConstraints();
			gbc_btnStartData.anchor = GridBagConstraints.WEST;
			gbc_btnStartData.insets = new Insets(0, 0, 5, 0);
			gbc_btnStartData.gridx = 3;
			gbc_btnStartData.gridy = 0;
			contentPanel.add(btnStartData, gbc_btnStartData);
		}
		
		// EndDate of date_time_reception
		{
			JLabel lblNewLabel_1 = new JLabel("Крайна дата");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			

			txtEndDate = new JTextField();
			txtEndDate.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent event) {

					if (DatePicker.incorrectDate(txtEndDate.getText(), true))
						txtEndDate.setForeground(Color.RED);
					else{
						txtEndDate.setForeground(Color.BLACK);
						String textRefDate = "";
						textRefDate = DatePicker.getReferenceDate(txtStartDate.getText(), txtEndDate.getText());
						if (DatePicker.incorrectDate(textRefDate, true))
							txt_fid_date_time_reception.setForeground(Color.RED);
						else
							txt_fid_date_time_reception.setForeground(Color.BLACK);

						txt_fid_date_time_reception.setText(textRefDate);
					}
				}

				@Override
				public void keyReleased(KeyEvent event) {

					if (DatePicker.incorrectDate(txtEndDate.getText(), true))
						txtEndDate.setForeground(Color.RED);
					else{
						txtEndDate.setForeground(Color.BLACK);
						String textRefDate = "";
						textRefDate = DatePicker.getReferenceDate(txtStartDate.getText(), txtEndDate.getText());
						if (DatePicker.incorrectDate(textRefDate, true))
							txt_fid_date_time_reception.setForeground(Color.RED);
						else
							txt_fid_date_time_reception.setForeground(Color.BLACK);

						txt_fid_date_time_reception.setText(textRefDate);
					}
				}

				@Override
				public void keyPressed(KeyEvent event) {

					if (DatePicker.incorrectDate(txtEndDate.getText(), true))
						txtEndDate.setForeground(Color.RED);
					else{
						txtEndDate.setForeground(Color.BLACK);
						String textRefDate = "";
						textRefDate = DatePicker.getReferenceDate(txtStartDate.getText(), txtEndDate.getText());
						if (DatePicker.incorrectDate(textRefDate, true))
							txt_fid_date_time_reception.setForeground(Color.RED);
						else
							txt_fid_date_time_reception.setForeground(Color.BLACK);

						txt_fid_date_time_reception.setText(textRefDate);
					}
				}
			});
			
			GridBagConstraints gbc_txtEndDate = new GridBagConstraints();
			gbc_txtEndDate.insets = new Insets(0, 0, 5, 5);
			gbc_txtEndDate.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEndDate.gridx = 2;
			gbc_txtEndDate.gridy = 1;
			contentPanel.add(txtEndDate, gbc_txtEndDate);
			txtEndDate.setColumns(10);
		}
		{
			JButton btnEndData = new JButton("Крайна дата");
			btnEndData.setPreferredSize(new Dimension(105, 23));
			btnEndData.setMinimumSize(new Dimension(105, 23));
			btnEndData.setMaximumSize(new Dimension(105, 23));
			btnEndData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					final JFrame k = new JFrame();
					DatePicker dPicer = new DatePicker(k, true, txtEndDate.getText());
					txtEndDate.setText(dPicer.setPickedDate(true));
					String textRefDate = "";
					textRefDate = DatePicker.getReferenceDate(txtStartDate.getText(), txtEndDate.getText());
					if (DatePicker.incorrectDate(textRefDate, true))
						txt_fid_date_time_reception.setForeground(Color.RED);
					else
						txt_fid_date_time_reception.setForeground(Color.BLACK);
					txt_fid_date_time_reception.setText(textRefDate);

				}
			});
			GridBagConstraints gbc_btnEndData = new GridBagConstraints();
			gbc_btnEndData.anchor = GridBagConstraints.WEST;
			gbc_btnEndData.insets = new Insets(0, 0, 5, 0);
			gbc_btnEndData.gridx = 3;
			gbc_btnEndData.gridy = 1;
			contentPanel.add(btnEndData, gbc_btnEndData);
		}
		
		
		
		
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removeAll();
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
					public void actionPerformed(ActionEvent e) {
					txt_fid_date_time_reception.setText(date_time_reception);
						dispose();	
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public static String get_str_period_sample() {
		// TODO Auto-generated method stub
		String str="";
		if(!txtEndDate.getText().equals(""))
		str = "за периода: "+txtStartDate.getText().substring(0, 10)+" ÷ "+ txtEndDate.getText().substring(0, 10) ;
		return str;
	}
	

	public static String get_date_time_reference() {
		// TODO Auto-generated method stub
		String str="";
		str = txt_fid_date_time_reception.getText();
		return str;
	}
}
