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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
import javax.swing.ScrollPaneConstants;

public class RequestView extends JFrame {

	JScrollPane scrollpane;
	private JTextField txtFld_recuest_code;
	private JTextField txtFld_date_request;
	private JTextField textField_7;
	private JTextField txtStartDate;
	private JTextField txtEndDate;
	private GridBagConstraints gbc_txtEndDate;
	private JTextField txtFld_date_execution;
	private JTextField txtFld_date_time_request;

	public RequestView() {
		super("JScrollPane Demonstration");
		setSize(850, 980);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		init();
		setVisible(true);
	}

	public void init() {

		JPanel p = new JPanel();
		p.setBorder(null);
		p.setSize(750, 900);

		scrollpane = new JScrollPane(p);
		scrollpane.setName("");
		scrollpane.setBorder(null);
		GridBagLayout gbl_p = new GridBagLayout();
		gbl_p.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_p.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_p.columnWidths = new int[] { 15, 155, 120, 120, 4, 120, 155, 15 };
		gbl_p.rowHeights = new int[] { 181, 33, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		p.setLayout(gbl_p);

		String text1 = "<html>ДЪРЖАВНО ПРЕДПРИЯТИЕ “РАДИОАКТИВНИ ОТПАДЪЦИ“<br> ЛАБОРАТОРИЯ ЗА ИЗПИТВАНЕ<br><br><br>"
				+ "CЕКТОР РАДИОХИМИЯ<br>" + "ЛИ – РХ <br>" + "гр. Козлодуй<br>"
				+ "тел.: (0973) 7 24 01  e-mail: LI-RH_DPRAO@mail.bg</html>";
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>" + text1 + "</div></html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.gridwidth = 6;
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

		txtFld_recuest_code = new JTextField();
		GridBagConstraints gbc_txtFld_recuest_code = new GridBagConstraints();
		gbc_txtFld_recuest_code.insets = new Insets(0, 0, 5, 5);
		gbc_txtFld_recuest_code.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld_recuest_code.gridx = 3;
		gbc_txtFld_recuest_code.gridy = 3;
		p.add(txtFld_recuest_code, gbc_txtFld_recuest_code);
		txtFld_recuest_code.setColumns(4);

		JLabel label = new JLabel("/");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.gridx = 4;
		gbc_label.gridy = 3;
		p.add(label, gbc_label);

		txtFld_date_request = new JTextField();
		GridBagConstraints gbc_txtFld_date_request = new GridBagConstraints();
		gbc_txtFld_date_request.anchor = GridBagConstraints.WEST;
		gbc_txtFld_date_request.insets = new Insets(0, 0, 5, 5);
		gbc_txtFld_date_request.gridx = 5;
		gbc_txtFld_date_request.gridy = 3;
		p.add(txtFld_date_request, gbc_txtFld_date_request);
		txtFld_date_request.setColumns(10);

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

		JLabel lbl_ind_num_doc = new JLabel("Ид. номер на документа, изискващ изпитването: ");
		lbl_ind_num_doc.setBorder(null);
		lbl_ind_num_doc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lbl_ind_num_doc = new GridBagConstraints();
		gbc_lbl_ind_num_doc.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_ind_num_doc.anchor = GridBagConstraints.WEST;
		gbc_lbl_ind_num_doc.gridwidth = 3;
		gbc_lbl_ind_num_doc.gridx = 1;
		gbc_lbl_ind_num_doc.gridy = 5;
		p.add(lbl_ind_num_doc, gbc_lbl_ind_num_doc);

		Choice choice_ind_num_doc = new Choice();
		GridBagConstraints gbc_choice_ind_num_doc = new GridBagConstraints();
		gbc_choice_ind_num_doc.anchor = GridBagConstraints.WEST;
		gbc_choice_ind_num_doc.gridwidth = 3;
		gbc_choice_ind_num_doc.insets = new Insets(0, 0, 5, 5);
		gbc_choice_ind_num_doc.gridx = 4;
		gbc_choice_ind_num_doc.gridy = 5;
		p.add(choice_ind_num_doc, gbc_choice_ind_num_doc);

		JLabel lbl_izpitvan_produkt = new JLabel("Изпитван продукт");
		GridBagConstraints gbc_lbl_izpitvan_produkt = new GridBagConstraints();
		gbc_lbl_izpitvan_produkt.anchor = GridBagConstraints.WEST;
		gbc_lbl_izpitvan_produkt.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_izpitvan_produkt.gridx = 1;
		gbc_lbl_izpitvan_produkt.gridy = 6;
		p.add(lbl_izpitvan_produkt, gbc_lbl_izpitvan_produkt);

		Choice choice_izpitvan_produkt = new Choice();
		GridBagConstraints gbc_izpitvan_produkt = new GridBagConstraints();
		gbc_izpitvan_produkt.gridwidth = 5;
		gbc_izpitvan_produkt.fill = GridBagConstraints.HORIZONTAL;
		gbc_izpitvan_produkt.insets = new Insets(0, 0, 5, 5);
		gbc_izpitvan_produkt.gridx = 2;
		gbc_izpitvan_produkt.gridy = 6;
		p.add(choice_izpitvan_produkt, gbc_izpitvan_produkt);

		JLabel lbl_obekt_na_izpitvane_request = new JLabel("Обект, от който са взети пробите:");
		GridBagConstraints gbc_lbl_obekt_na_izpitvane_request = new GridBagConstraints();
		gbc_lbl_obekt_na_izpitvane_request.anchor = GridBagConstraints.WEST;
		gbc_lbl_obekt_na_izpitvane_request.gridwidth = 2;
		gbc_lbl_obekt_na_izpitvane_request.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_obekt_na_izpitvane_request.gridx = 1;
		gbc_lbl_obekt_na_izpitvane_request.gridy = 7;
		p.add(lbl_obekt_na_izpitvane_request, gbc_lbl_obekt_na_izpitvane_request);

		Choice choice_obekt_na_izpitvane_request = new Choice();
		GridBagConstraints gbc_choice_obekt_na_izpitvane_request = new GridBagConstraints();
		gbc_choice_obekt_na_izpitvane_request.gridwidth = 4;
		gbc_choice_obekt_na_izpitvane_request.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_obekt_na_izpitvane_request.insets = new Insets(0, 0, 5, 5);
		gbc_choice_obekt_na_izpitvane_request.gridx = 3;
		gbc_choice_obekt_na_izpitvane_request.gridy = 7;
		p.add(choice_obekt_na_izpitvane_request, gbc_choice_obekt_na_izpitvane_request);

		JLabel lblNewLabel_12 = new JLabel("Размерност");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 1;
		gbc_lblNewLabel_12.gridy = 8;
		p.add(lblNewLabel_12, gbc_lblNewLabel_12);

		Choice choice_3 = new Choice();
		GridBagConstraints gbc_choice_3 = new GridBagConstraints();
		gbc_choice_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_3.insets = new Insets(0, 0, 5, 5);
		gbc_choice_3.gridx = 2;
		gbc_choice_3.gridy = 8;
		p.add(choice_3, gbc_choice_3);

		JLabel lbl_list_izpitvan_pokazatel = new JLabel("Изпитван показател:");
		GridBagConstraints gbc_lbl_list_izpitvan_pokazatel = new GridBagConstraints();
		gbc_lbl_list_izpitvan_pokazatel.anchor = GridBagConstraints.WEST;
		gbc_lbl_list_izpitvan_pokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_list_izpitvan_pokazatel.gridx = 1;
		gbc_lbl_list_izpitvan_pokazatel.gridy = 9;
		p.add(lbl_list_izpitvan_pokazatel, gbc_lbl_list_izpitvan_pokazatel);

		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 9;
		p.add(textArea, gbc_textArea);
		
		JButton btn_list_izpitvan_pokazatel = new JButton("Избор на показател");
		btn_list_izpitvan_pokazatel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChoiceListPokazatel choiceLP = new ChoiceListPokazatel();
				
				choiceLP.setVisible(true);
			}
		});
		GridBagConstraints gbc_btn_list_izpitvan_pokazatel = new GridBagConstraints();
		gbc_btn_list_izpitvan_pokazatel.anchor = GridBagConstraints.WEST;
		gbc_btn_list_izpitvan_pokazatel.gridwidth = 2;
		gbc_btn_list_izpitvan_pokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_btn_list_izpitvan_pokazatel.gridx = 4;
		gbc_btn_list_izpitvan_pokazatel.gridy = 9;
		p.add(btn_list_izpitvan_pokazatel, gbc_btn_list_izpitvan_pokazatel);

		JLabel lblNewLabel_8 = new JLabel("Брой на пробите ");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.gridwidth = 2;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 2;
		gbc_lblNewLabel_8.gridy = 10;
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
		gbc_textField_7.gridy = 10;
		p.add(textField_7, gbc_textField_7);
		textField_7.setColumns(3);

		JLabel lblNewLabel_9 = new JLabel("Описание на пробите ");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 11;
		p.add(lblNewLabel_9, gbc_lblNewLabel_9);

		String text_i = "333-1";

		JTextArea textArea_1 = new JTextArea();
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.gridwidth = 5;
		gbc_textArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 2;
		gbc_textArea_1.gridy = 11;
		p.add(textArea_1, gbc_textArea_1);

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
		gbc_btnStartData.gridwidth = 2;
		gbc_btnStartData.anchor = GridBagConstraints.WEST;
		gbc_btnStartData.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartData.gridx = 3;
		gbc_btnStartData.gridy = 14;
		p.add(btnStartData, gbc_btnStartData);

		// EndDate of date_time_reception
		txtEndDate = new JTextField();
		txtEndDate.setColumns(10);
		GridBagConstraints gbc_textField_a;
		gbc_txtEndDate = new GridBagConstraints();
		gbc_txtEndDate.anchor = GridBagConstraints.WEST;
		gbc_txtEndDate.insets = new Insets(0, 0, 5, 5);
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
		gbc_btn_date_execution.anchor = GridBagConstraints.WEST;
		gbc_btn_date_execution.gridwidth = 2;
		gbc_btn_date_execution.insets = new Insets(0, 0, 5, 5);
		gbc_btn_date_execution.gridx = 5;
		gbc_btn_date_execution.gridy = 16;
		p.add(btn_date_execution, gbc_btn_date_execution);

		// date_time_request
		// *****************************************************************
		JLabel lbl_date_time_request = new JLabel("Дата и час на приемане:");
		GridBagConstraints gbc_lbl_date_time_request = new GridBagConstraints();
		gbc_lbl_date_time_request.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_time_request.gridwidth = 2;
		gbc_lbl_date_time_request.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_time_request.gridx = 1;
		gbc_lbl_date_time_request.gridy = 17;
		p.add(lbl_date_time_request, gbc_lbl_date_time_request);

		txtFld_date_time_request = new JTextField("");
		txtFld_date_time_request.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_date_time_request.getText(), true))
					txtFld_date_time_request.setForeground(Color.RED);
				else
					txtFld_date_time_request.setForeground(Color.BLACK);
			}

			@Override
			public void keyReleased(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_date_time_request.getText(), true))
					txtFld_date_time_request.setForeground(Color.RED);
				else
					txtFld_date_time_request.setForeground(Color.BLACK);
			}

			@Override
			public void keyPressed(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_date_time_request.getText(), true))
					txtFld_date_time_request.setForeground(Color.RED);
				else
					txtFld_date_time_request.setForeground(Color.BLACK);
			}
		});
		GridBagConstraints gbc_txtFld_date_time_request = new GridBagConstraints();
		gbc_txtFld_date_time_request.gridwidth = 2;
		gbc_txtFld_date_time_request.insets = new Insets(0, 0, 5, 5);
		gbc_txtFld_date_time_request.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld_date_time_request.gridx = 3;
		gbc_txtFld_date_time_request.gridy = 17;
		p.add(txtFld_date_time_request, gbc_txtFld_date_time_request);

		JButton btn_date_time_request = new JButton("Избор на дата");
		btn_date_time_request.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFrame f = new JFrame();
				DatePicker dPicer = new DatePicker(f, true);
				txtFld_date_time_request.setText(dPicer.setPickedDate(true));

				String textRefDate = "";
				textRefDate = txtFld_date_time_request.getText();
				if (DatePicker.incorrectDate(textRefDate, true))
					txtFld_date_time_request.setForeground(Color.RED);
				else
					txtFld_date_time_request.setForeground(Color.BLACK);

				txtFld_date_time_request.setText(textRefDate);
			}
		});
		GridBagConstraints gbc_btn_date_time_request = new GridBagConstraints();
		gbc_btn_date_time_request.anchor = GridBagConstraints.WEST;
		gbc_btn_date_time_request.gridwidth = 2;
		gbc_btn_date_time_request.insets = new Insets(0, 0, 5, 5);
		gbc_btn_date_time_request.gridx = 5;
		gbc_btn_date_time_request.gridy = 17;
		p.add(btn_date_time_request, gbc_btn_date_time_request);

		getContentPane().add(scrollpane, BorderLayout.CENTER);

	}

}
