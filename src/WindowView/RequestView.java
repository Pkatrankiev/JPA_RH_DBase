package WindowView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.util.Calendar;

import javax.swing.border.CompoundBorder;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Choice;
import javax.swing.JButton;

public class RequestView extends JFrame {

	JScrollPane scrollpane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField txtStartDate;
	private JTextField txtEndDate;
	private GridBagConstraints gbc_txtEndDate;
	private JTextField txtFld_date_execution;
	private JTextField txtFld_date_request;

	public RequestView() {
		super("JScrollPane Demonstration");
		setSize(700, 980);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		init();
		setVisible(true);
	}

	public void init() {

		JPanel p = new JPanel();
		p.setSize(700, 900);

		scrollpane = new JScrollPane(p);
		GridBagLayout gbl_p = new GridBagLayout();
		gbl_p.columnWidths = new int[] { 24, 108, 100, 92, 10, 100, 187, 34, 0 };
		gbl_p.rowHeights = new int[] { 104, 33, 0, 23, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23, 21, 21, 0 };
		gbl_p.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_p.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };

		p.setLayout(gbl_p);
		// "<html>First line<br>Second line</html>"

		String text1 = "<html>ДЪРЖАВНО ПРЕДПРИЯТИЕ “РАДИОАКТИВНИ ОТПАДЪЦИ“<br> ЛАБОРАТОРИЯ ЗА ИЗПИТВАНЕ<br><br><br>"
				+ "CЕКТОР РАДИОХИМИЯ<br>" + "ЛИ – РХ <br>" + "гр. Козлодуй<br>"
				+ "тел.: (0973) 7 24 01  e-mail: LI-RH_DPRAO@mail.bg</html>";
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>" + text1 + "</div></html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 6;
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		p.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ФК 508-1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 6;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		p.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ЗАЯВКА ЗА ИЗПИТВАНЕ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 7;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		p.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("№");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 3;
		p.add(lblNewLabel_3, gbc_lblNewLabel_3);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;
		p.add(textField, gbc_textField);
		textField.setColumns(4);

		JLabel label = new JLabel("/");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.gridx = 4;
		gbc_label.gridy = 3;
		p.add(label, gbc_label);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 3;
		p.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		String text2 = "<html>Попълва се от ЛИ-РХ за изпитвания, извършвани по програми и документи, вътрешни за<br>"
				+ "ДП „Радиоактивни отпадъци”</html>";
		JLabel lblNewLabel_4 = new JLabel(text2);
		lblNewLabel_4.setBorder(
				new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(255, 255, 255), 6)));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.gridwidth = 6;
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 4;
		p.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Ид. номер на документа, изискващ изпитването: ");
		lblNewLabel_5.setBorder(null);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.gridwidth = 3;
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 5;
		p.add(lblNewLabel_5, gbc_lblNewLabel_5);

		Choice choice_4 = new Choice();
		GridBagConstraints gbc_choice_4 = new GridBagConstraints();
		gbc_choice_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_4.gridwidth = 3;
		gbc_choice_4.insets = new Insets(0, 0, 5, 5);
		gbc_choice_4.gridx = 4;
		gbc_choice_4.gridy = 5;
		p.add(choice_4, gbc_choice_4);

		JLabel lblNewLabel_6 = new JLabel("Изпитван продукт");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 6;
		p.add(lblNewLabel_6, gbc_lblNewLabel_6);

		Choice choice = new Choice();
		GridBagConstraints gbc_choice = new GridBagConstraints();
		gbc_choice.gridwidth = 5;
		gbc_choice.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice.insets = new Insets(0, 0, 5, 5);
		gbc_choice.gridx = 2;
		gbc_choice.gridy = 6;
		p.add(choice, gbc_choice);

		JLabel lblNewLabel_11 = new JLabel("Обект, от който са взети пробите:");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_11.gridwidth = 2;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 1;
		gbc_lblNewLabel_11.gridy = 7;
		p.add(lblNewLabel_11, gbc_lblNewLabel_11);

		Choice choice_1 = new Choice();
		GridBagConstraints gbc_choice_1 = new GridBagConstraints();
		gbc_choice_1.gridwidth = 4;
		gbc_choice_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_1.insets = new Insets(0, 0, 5, 5);
		gbc_choice_1.gridx = 3;
		gbc_choice_1.gridy = 7;
		p.add(choice_1, gbc_choice_1);

		JLabel lblNewLabel_7 = new JLabel("Изпитван показател:");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 8;
		p.add(lblNewLabel_7, gbc_lblNewLabel_7);

		Choice choice_2 = new Choice();
		GridBagConstraints gbc_choice_2 = new GridBagConstraints();
		gbc_choice_2.gridwidth = 2;
		gbc_choice_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_2.insets = new Insets(0, 0, 5, 5);
		gbc_choice_2.gridx = 2;
		gbc_choice_2.gridy = 8;
		p.add(choice_2, gbc_choice_2);

		JLabel lblNewLabel_12 = new JLabel("Размерност");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.gridwidth = 2;
		gbc_lblNewLabel_12.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 4;
		gbc_lblNewLabel_12.gridy = 8;
		p.add(lblNewLabel_12, gbc_lblNewLabel_12);

		Choice choice_3 = new Choice();
		GridBagConstraints gbc_choice_3 = new GridBagConstraints();
		gbc_choice_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_3.insets = new Insets(0, 0, 5, 5);
		gbc_choice_3.gridx = 6;
		gbc_choice_3.gridy = 8;
		p.add(choice_3, gbc_choice_3);

		JLabel lblNewLabel_8 = new JLabel("Брой на пробите ");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.gridwidth = 2;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 2;
		gbc_lblNewLabel_8.gridy = 9;
		p.add(lblNewLabel_8, gbc_lblNewLabel_8);

		textField_7 = new JTextField();
		textField_7.setToolTipText("");
		textField_7.setMaximumSize(new Dimension(2147483640, 2147483646));
		textField_7.setPreferredSize(new Dimension(5, 20));
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.anchor = GridBagConstraints.WEST;
		gbc_textField_7.gridwidth = 2;
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.gridx = 4;
		gbc_textField_7.gridy = 9;
		p.add(textField_7, gbc_textField_7);
		textField_7.setColumns(3);

		JLabel lblNewLabel_9 = new JLabel("Описание на пробите ");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.gridwidth = 2;
		gbc_lblNewLabel_9.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 10;
		p.add(lblNewLabel_9, gbc_lblNewLabel_9);

		String text_i = "333-1";
		JLabel lblNewLabel_10 = new JLabel("3233-1");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 1;
		gbc_lblNewLabel_10.gridy = 11;
		p.add(lblNewLabel_10, gbc_lblNewLabel_10);

		textField_8 = new JTextField();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridwidth = 5;
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.gridx = 2;
		gbc_textField_8.gridy = 11;
		p.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);

		JLabel label_1 = new JLabel("Периодичност");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 13;
		p.add(label_1, gbc_label_1);

		Choice choice_5 = new Choice();
		GridBagConstraints gbc_choice_5 = new GridBagConstraints();
		gbc_choice_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_5.insets = new Insets(0, 0, 5, 5);
		gbc_choice_5.gridx = 2;
		gbc_choice_5.gridy = 13;
		p.add(choice_5, gbc_choice_5);

		// date_time_reception
		JLabel lbl_date_time_reception = new JLabel("Референтна дата (средата на периода)");
		GridBagConstraints gbc_lbl_date_time_reception = new GridBagConstraints();
		gbc_lbl_date_time_reception.gridwidth = 2;
		gbc_lbl_date_time_reception.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_time_reception.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_time_reception.gridx = 1;
		gbc_lbl_date_time_reception.gridy = 15;
		p.add(lbl_date_time_reception, gbc_lbl_date_time_reception);

		final JTextField txt_fid_date_time_reception = new JTextField("");
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
		gbc_date_time_reception.fill = GridBagConstraints.HORIZONTAL;
		gbc_date_time_reception.gridwidth = 2;
		gbc_date_time_reception.insets = new Insets(0, 0, 5, 5);
		gbc_date_time_reception.gridx = 3;
		gbc_date_time_reception.gridy = 15;
		p.add(txt_fid_date_time_reception, gbc_date_time_reception);

		// StartDate of date_time_reception
		txtStartDate = new JTextField();
		GridBagConstraints gbc_txtStartDate = new GridBagConstraints();
		gbc_txtStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtStartDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStartDate.gridx = 2;
		gbc_txtStartDate.gridy = 14;
		p.add(txtStartDate, gbc_txtStartDate);
		txtStartDate.setColumns(10);

		JButton btnStartData = new JButton("Начална дата");
		btnStartData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFrame f = new JFrame();
				DatePicker dPicer = new DatePicker(f, true);
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
		gbc_btnStartData.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartData.gridx = 3;
		gbc_btnStartData.gridy = 14;
		p.add(btnStartData, gbc_btnStartData);

		// EndDate of date_time_reception
		txtEndDate = new JTextField();
		txtEndDate.setColumns(10);
		GridBagConstraints gbc_textField_a;
		gbc_txtEndDate = new GridBagConstraints();
		gbc_txtEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtEndDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEndDate.gridx = 5;
		gbc_txtEndDate.gridy = 14;
		p.add(txtEndDate, gbc_txtEndDate);

		JButton btnEndData = new JButton("Крайна дата");
		btnEndData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFrame f = new JFrame();
				DatePicker dPicer = new DatePicker(f, true);
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
		gbc_btnEndData.insets = new Insets(0, 0, 5, 5);
		gbc_btnEndData.gridx = 6;
		gbc_btnEndData.gridy = 14;
		p.add(btnEndData, gbc_btnEndData);

		// date_execution
		// ************************************************************************
		JLabel lbl_date_execution = new JLabel("Срок за изпълнение:");
		GridBagConstraints gbc_lbl_date_execution = new GridBagConstraints();
		gbc_lbl_date_execution.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_execution.gridwidth = 2;
		gbc_lbl_date_execution.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_execution.gridx = 1;
		gbc_lbl_date_execution.gridy = 16;
		p.add(lbl_date_execution, gbc_lbl_date_execution);

		txtFld_date_execution = new JTextField("");
		txtFld_date_execution.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_date_execution.getText(), false))
					txtFld_date_execution.setForeground(Color.RED);
				else
					txtFld_date_execution.setForeground(Color.BLACK);
			}

			@Override
			public void keyReleased(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_date_execution.getText(), false))
					txtFld_date_execution.setForeground(Color.RED);
				else
					txtFld_date_execution.setForeground(Color.BLACK);
			}

			@Override
			public void keyPressed(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_date_execution.getText(), false))
					txtFld_date_execution.setForeground(Color.RED);
				else
					txtFld_date_execution.setForeground(Color.BLACK);
			}
		});

		GridBagConstraints gbc_txtFld_date_execution = new GridBagConstraints();
		gbc_txtFld_date_execution.gridwidth = 2;
		gbc_txtFld_date_execution.insets = new Insets(0, 0, 5, 5);
		gbc_txtFld_date_execution.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld_date_execution.gridx = 3;
		gbc_txtFld_date_execution.gridy = 16;
		p.add(txtFld_date_execution, gbc_txtFld_date_execution);

		JButton btn_date_execution = new JButton("Избор на дата");
		btn_date_execution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFrame f = new JFrame();
				DatePicker dPicer = new DatePicker(f, false);
				txtFld_date_execution.setText(dPicer.setPickedDate(false));
				String text_date_execution = "";

				text_date_execution = txtFld_date_execution.getText();
				if (DatePicker.incorrectDate(text_date_execution, false))
					txtFld_date_execution.setForeground(Color.RED);
				else
					txtFld_date_execution.setForeground(Color.BLACK);
				txtFld_date_execution.setText(text_date_execution);

			}
		});
		GridBagConstraints gbc_btn_date_execution = new GridBagConstraints();
		gbc_btn_date_execution.insets = new Insets(0, 0, 5, 5);
		gbc_btn_date_execution.gridx = 5;
		gbc_btn_date_execution.gridy = 16;
		p.add(btn_date_execution, gbc_btn_date_execution);

		// date_request
		// *****************************************************************
		JLabel lbl_date_request = new JLabel("Дата и час на приемане:");
		GridBagConstraints gbc_lbl_date_request = new GridBagConstraints();
		gbc_lbl_date_request.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_request.gridwidth = 2;
		gbc_lbl_date_request.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_date_request.gridx = 1;
		gbc_lbl_date_request.gridy = 17;
		p.add(lbl_date_request, gbc_lbl_date_request);

		txtFld_date_request = new JTextField("");
		txtFld_date_request.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_date_request.getText(), true))
					txtFld_date_request.setForeground(Color.RED);
				else
					txtFld_date_request.setForeground(Color.BLACK);
			}

			@Override
			public void keyReleased(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_date_request.getText(), true))
					txtFld_date_request.setForeground(Color.RED);
				else
					txtFld_date_request.setForeground(Color.BLACK);
			}

			@Override
			public void keyPressed(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_date_request.getText(), true))
					txtFld_date_request.setForeground(Color.RED);
				else
					txtFld_date_request.setForeground(Color.BLACK);
			}
		});
		GridBagConstraints gbc_txtFld_date_request = new GridBagConstraints();
		gbc_txtFld_date_request.gridwidth = 2;
		gbc_txtFld_date_request.insets = new Insets(0, 0, 0, 5);
		gbc_txtFld_date_request.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld_date_request.gridx = 3;
		gbc_txtFld_date_request.gridy = 17;
		p.add(txtFld_date_request, gbc_txtFld_date_request);

		JButton btn_date_request = new JButton("Избор на дата");
		btn_date_request.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFrame f = new JFrame();
				DatePicker dPicer = new DatePicker(f, true);
				txtFld_date_request.setText(dPicer.setPickedDate(true));

				String textRefDate = "";
				textRefDate =txtFld_date_request.getText();
				if (DatePicker.incorrectDate(textRefDate, true))
					txtFld_date_request.setForeground(Color.RED);
				else
					txtFld_date_request.setForeground(Color.BLACK);

				txtFld_date_request.setText(textRefDate);
			}
		});
		GridBagConstraints gbc_btn_date_request = new GridBagConstraints();
		gbc_btn_date_request.insets = new Insets(0, 0, 0, 5);
		gbc_btn_date_request.gridx = 5;
		gbc_btn_date_request.gridy = 17;
		p.add(btn_date_request, gbc_btn_date_request);

		getContentPane().add(scrollpane, BorderLayout.CENTER);

	}

}
