package WindowView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DateChoice_period extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JTextField txtStartDate;
	private static JTextField txtEndDate;
	private static JTextField txt_fid_date_time_reference;
	private static JLabel lbl_errorPeriod;
	String startIncomingData = "";
	String endIncomingData = "";
	Boolean correctDate = true;

	public DateChoice_period(Frame parent, String incoming_date_time, Boolean withTime, Boolean forDateReception, Boolean fromTable) {
		super(parent, "Избор на референтна дата и време", true);

		if (forDateReception) {
			setTitle("Избор на дата / период на вземане");
			String[] masiveStartAndEndDate = getMasiveDateFromPeriodString(incoming_date_time);
			if(fromTable){
				if(incoming_date_time.length()<12){
				startIncomingData = DatePicker.reformatFromTabDate(masiveStartAndEndDate[0], false);
				endIncomingData = "";
				}else{
				startIncomingData = DatePicker.reformatFromTabDate(masiveStartAndEndDate[1], false);
				endIncomingData = DatePicker.reformatFromTabDate(masiveStartAndEndDate[0], false);
				}
			}else{
			startIncomingData = masiveStartAndEndDate[0];
			endIncomingData = masiveStartAndEndDate[1];
			}
		}

		setBounds(100, 100, 550, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 87, 143, 110, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		if (!forDateReception) {
			JLabel lbl_date_time_reception = new JLabel("Референтна дата (средата на периода)");
			GridBagConstraints gbc_lbl_date_time_reception = new GridBagConstraints();
			gbc_lbl_date_time_reception.gridwidth = 2;
			gbc_lbl_date_time_reception.insets = new Insets(0, 0, 0, 5);
			gbc_lbl_date_time_reception.anchor = GridBagConstraints.EAST;
			gbc_lbl_date_time_reception.gridx = 0;
			gbc_lbl_date_time_reception.gridy = 2;
			contentPanel.add(lbl_date_time_reception, gbc_lbl_date_time_reception);

			txt_fid_date_time_reference = new JTextField(incoming_date_time);
			txt_fid_date_time_reference.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent event) {

					// if
					// (DatePicker.incorrectDate(txt_fid_date_time_reference.getText(),
					// withTime))
					// txt_fid_date_time_reference.setForeground(Color.RED);
					// else
					// txt_fid_date_time_reference.setForeground(Color.BLACK);
				}

				@Override
				public void keyReleased(KeyEvent event) {

					if (DatePicker.incorrectDate(txt_fid_date_time_reference.getText(), withTime)) {
						txt_fid_date_time_reference.setForeground(Color.RED);
						correctDate = false;
					} else {
						txt_fid_date_time_reference.setForeground(Color.BLACK);
						correctDate = true;
					}
				}

				@Override
				public void keyPressed(KeyEvent event) {

					// if
					// (DatePicker.incorrectDate(txt_fid_date_time_reference.getText(),
					// withTime))
					// txt_fid_date_time_reference.setForeground(Color.RED);
					// else
					// txt_fid_date_time_reference.setForeground(Color.BLACK);
				}
			});

			GridBagConstraints gbc_date_time_reference = new GridBagConstraints();
			gbc_date_time_reference.insets = new Insets(0, 0, 0, 5);
			gbc_date_time_reference.fill = GridBagConstraints.HORIZONTAL;
			gbc_date_time_reference.gridx = 2;
			gbc_date_time_reference.gridy = 2;
			contentPanel.add(txt_fid_date_time_reference, gbc_date_time_reference);
			txt_fid_date_time_reference.setColumns(10);
		} else {
			lbl_errorPeriod = new JLabel(incoming_date_time);

			GridBagConstraints gbc_lbl_errorPeriod = new GridBagConstraints();
			gbc_lbl_errorPeriod.insets = new Insets(0, 0, 0, 5);
			gbc_lbl_errorPeriod.fill = GridBagConstraints.HORIZONTAL;
			gbc_lbl_errorPeriod.gridx = 2;
			gbc_lbl_errorPeriod.gridy = 2;
			contentPanel.add(lbl_errorPeriod, gbc_lbl_errorPeriod);
			lbl_errorPeriod.setVisible(false);
		}

		// StartDate of date_time_reception
		// ----------------------------------------------------------------------
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

			txtStartDate = new JTextField(startIncomingData);
			txtStartDate.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent event) {

					// check_and_collor_incorrect_DateAndReferenceTxt(withTime,
					// forDateReception,txtStartDate,
					// txt_fid_date_time_reference);
				}

				@Override
				public void keyReleased(KeyEvent event) {
					correctDate = check_and_collor_incorrect_DateAndReferenceTxt(withTime, forDateReception,
							txtStartDate);
				}

				@Override
				public void keyPressed(KeyEvent event) {

					// check_and_collor_incorrect_DateAndReferenceTxt(withTime,
					// forDateReception,txtStartDate,
					// txt_fid_date_time_reference);
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
			btnStartData.setMinimumSize(new Dimension(105, 23));
			btnStartData.setPreferredSize(new Dimension(105, 23));
			btnStartData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					final JFrame d = new JFrame();
					DatePicker dPicer = new DatePicker(d, withTime, txtStartDate.getText());
					txtStartDate.setText(dPicer.setPickedDate(withTime));
					correctDate = check_and_collor_incorrect_DateAndReferenceTxt(withTime, forDateReception,
							txtStartDate);

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
			txtEndDate = new JTextField(endIncomingData);
			txtEndDate.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent event) {

					// check_and_collor_incorrect_DateAndReferenceTxt(withTime,
					// forDateReception,txtEndDate,
					// txt_fid_date_time_reference);

				}

				@Override
				public void keyReleased(KeyEvent event) {
					correctDate = check_and_collor_incorrect_DateAndReferenceTxt(withTime, forDateReception,
							txtEndDate);

				}

				@Override
				public void keyPressed(KeyEvent event) {
					// check_and_collor_incorrect_DateAndReferenceTxt(withTime,
					// forDateReception,txtEndDate,
					// txt_fid_date_time_reference);

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
					DatePicker dPicer = new DatePicker(k, withTime, txtEndDate.getText());
					txtEndDate.setText(dPicer.setPickedDate(withTime));
					correctDate = check_and_collor_incorrect_DateAndReferenceTxt(withTime, forDateReception,
							txtEndDate);

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
						if (!correctDate) {
							// display the showOptionDialog
							int choice = 0;
							Object[] options = { "Не", "Да" };
							choice = JOptionPane.showOptionDialog(null,
									"Има некоректни дати.\nЩе нанесете ли поправки?", "Грешни данни",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

							if (choice == JOptionPane.YES_OPTION) {
								returnToIncomingDate(incoming_date_time, forDateReception);
								dispose();
							}
						} else {
							removeAll();
							dispose();
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
						returnToIncomingDate(incoming_date_time, forDateReception);
						dispose();
					}

				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public static String[] getMasiveDateFromPeriodString(String incoming_date_time) {
		String[] masiveStartAndEndDate = new String[2];
		if (incoming_date_time.length() > 10) {
			masiveStartAndEndDate[0] = incoming_date_time.substring(0, incoming_date_time.indexOf("÷")).trim();
			masiveStartAndEndDate[1] = incoming_date_time
					.substring(incoming_date_time.indexOf("÷") + 1, incoming_date_time.length()).trim();
		} else {
			masiveStartAndEndDate[0] = incoming_date_time;
			masiveStartAndEndDate[1] = "";
		}
		return masiveStartAndEndDate;
	}

	public static String get_str_period_sample(Boolean forDateReception, Boolean forTable) {

		String str = "";
		if (txtStartDate != null || txtEndDate != null) {

			str = generateStringPeriodDate(forDateReception, forTable, txtStartDate.getText(), txtEndDate.getText());
		}
		return str;
	}

	public static String generateStringPeriodDate(Boolean forDateReception, Boolean forTable, String txtStartDate,
			String txtEndDate) {

		String str = "", txtStartDate_Table = "", txtEndDate_Table = "";
		txtStartDate_Table = DatePicker.formatToTabDate(txtStartDate, false);
		if (forDateReception) {
			str = txtStartDate;
		}
		if (forTable) {
			str = txtStartDate_Table;
		}
		if (!txtEndDate.equals("")) {
			txtEndDate_Table = DatePicker.formatToTabDate(txtEndDate, false);
			if (forDateReception) {
				str = txtStartDate + " ÷ " + txtEndDate;
				if (forTable) {
					str = txtEndDate_Table + " ÷ " + txtStartDate_Table;
				}
			} else {
				str = "за периода: " + txtStartDate.substring(0, 10) + " ÷ " + txtEndDate.substring(0, 10);
			}
		}
		return str;
	}

	public static String reformatPeriodDateFromTable(String incoming_date_time) {
		String[] masiveStartAndEndDate = getMasiveDateFromPeriodString(incoming_date_time);
		String str = "";
			if(incoming_date_time.length()<12){
				str = DatePicker.reformatFromTabDate(masiveStartAndEndDate[0], false);
			}else{
				str = DatePicker.reformatFromTabDate(masiveStartAndEndDate[1], false)+
						" ÷ " + DatePicker.reformatFromTabDate(masiveStartAndEndDate[0], false);
			}
		return str;
	}
	
	
	private void returnToIncomingDate(String incoming_date_time, Boolean forDateReception) {
		if (forDateReception) {
			txtStartDate.setText(startIncomingData);
			txtEndDate.setText(endIncomingData);
		} else {
			txt_fid_date_time_reference.setText(incoming_date_time);
		}
	}

	public static String get_date_time_reference() {

		String str = "";
		str = txt_fid_date_time_reference.getText();
		return str;
	}

	private Boolean check_and_collor_incorrect_DateAndReferenceTxt(Boolean withTime, Boolean forDateReception,
			JTextField txtDate) {
		Boolean check = true;
		if (DatePicker.incorrectDate(txtDate.getText(), withTime)) {
			txtDate.setForeground(Color.RED);
			check = false;
		} else {
			txtDate.setForeground(Color.BLACK);
			check = true;
		}
		String textRefDate = "";
		if (!forDateReception) {
			textRefDate = DatePicker.getReferenceDate(txtStartDate.getText(), txtEndDate.getText());
			if (DatePicker.incorrectDate(textRefDate, withTime)) {
				txt_fid_date_time_reference.setForeground(Color.RED);
				txt_fid_date_time_reference.setText(textRefDate);
				check = false;
			} else {
				txt_fid_date_time_reference.setForeground(Color.BLACK);
				check = true;
				txt_fid_date_time_reference.setText(textRefDate);
			}

		} else {
			textRefDate = DatePicker.chec_current_period(txtStartDate.getText(), txtEndDate.getText());
			if (DatePicker.incorrectDate(textRefDate, withTime)) {
				lbl_errorPeriod.setForeground(Color.RED);
				lbl_errorPeriod.setText(textRefDate);
				lbl_errorPeriod.setVisible(true);
				check = false;
			} else {

				lbl_errorPeriod.setForeground(Color.BLACK);
				lbl_errorPeriod.setText(textRefDate);
				lbl_errorPeriod.setVisible(false);
				check = true;
			}
		}
		return check;
	}

}
