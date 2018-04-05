package WindowView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
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

import WindowViewAplication.RequestViewAplication;

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
import javax.swing.text.MaskFormatter;

import org.apache.poi.util.SystemOutLogger;
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
import java.awt.SystemColor;

public class RequestView extends JFrame {

	JScrollPane scrollpane;
	private JTextField txtFld_Count_Sample;
	private JTextField txtStartDate;
	private JTextField txtEndDate;
	private GridBagConstraints gbc_txtEndDate;
	private JTextField txtFld_date_execution;
	private JTextField txtFld_date_time_request;
	String s = "";
	private JFormattedTextField txtField_RequestCode;
	private JTextField txtFld_Date_Request;

	public RequestView() {
		super("JScrollPane Demonstration");
		setSize(850, 980);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		init();
		setVisible(true);
	}

	public void init() {

		final JPanel p = new JPanel();
		p.setBackground(SystemColor.controlHighlight);
		p.setBorder(null);
		p.setSize(750, 900);

		scrollpane = new JScrollPane(p);
		scrollpane.setName("");
		scrollpane.setBorder(null);
		GridBagLayout gbl_p = new GridBagLayout();
		gbl_p.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_p.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_p.columnWidths = new int[] { 15, 160, 110, 110, 110, 160, 15 };
		gbl_p.rowHeights = new int[] { 181, 33, 27, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
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
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		p.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ФК 508-1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 5;
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
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		p.add(lblNewLabel_2, gbc_lblNewLabel_2);

		String text2 = "<html>Попълва се от ЛИ-РХ за изпитвания, извършвани по програми и документи, вътрешни за<br>"
				+ "ДП „Радиоактивни отпадъци”</html>";

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 3;
		p.add(panel_1, gbc_panel_1);

		JLabel label = new JLabel("№");
		panel_1.add(label);

		txtField_RequestCode = new JFormattedTextField(RequestViewAplication.createFormatter("####"));
		txtField_RequestCode.setColumns(4);
		panel_1.add(txtField_RequestCode);

		JLabel label_2 = new JLabel("/");
		panel_1.add(label_2);

		txtFld_Date_Request = new JTextField();
		txtFld_Date_Request.setColumns(8);
		txtFld_Date_Request.setText(RequestViewAplication.DateNaw(false));
		txtFld_Date_Request.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_Date_Request.getText(), false))
					txtFld_Date_Request.setForeground(Color.RED);
				else
					txtFld_Date_Request.setForeground(Color.BLACK);
			}

			@Override
			public void keyReleased(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_Date_Request.getText(), false))
					txtFld_Date_Request.setForeground(Color.RED);
				else
					txtFld_Date_Request.setForeground(Color.BLACK);
			}

			@Override
			public void keyPressed(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_Date_Request.getText(), false))
					txtFld_Date_Request.setForeground(Color.RED);
				else
					txtFld_Date_Request.setForeground(Color.BLACK);
			}
		});
		panel_1.add(txtFld_Date_Request);

		JLabel lblNewLabel_4 = new JLabel(text2);
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setBorder(
				new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(255, 255, 255), 6)));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.gridwidth = 5;
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 4;
		p.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lbl_ind_num_doc = new JLabel("Ид. номер на документа, изискващ изпитването: ");
		lbl_ind_num_doc.setBorder(null);
		lbl_ind_num_doc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lbl_ind_num_doc = new GridBagConstraints();
		gbc_lbl_ind_num_doc.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_ind_num_doc.anchor = GridBagConstraints.WEST;
		gbc_lbl_ind_num_doc.gridwidth = 2;
		gbc_lbl_ind_num_doc.gridx = 1;
		gbc_lbl_ind_num_doc.gridy = 5;
		p.add(lbl_ind_num_doc, gbc_lbl_ind_num_doc);

		Choice choice_ind_num_doc = new Choice();
		choice_ind_num_doc.setPreferredSize(new Dimension(300, 20));
		String[] arr = RequestViewAplication.getStringMassiveI_N_D();
		for (String string : arr) {
			choice_ind_num_doc.add(string);
		}
		GridBagConstraints gbc_choice_ind_num_doc = new GridBagConstraints();
		gbc_choice_ind_num_doc.anchor = GridBagConstraints.WEST;
		gbc_choice_ind_num_doc.gridwidth = 3;
		gbc_choice_ind_num_doc.insets = new Insets(0, 0, 5, 5);
		gbc_choice_ind_num_doc.gridx = 3;
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
		String[] arr1 = RequestViewAplication.getStringMassiveIzpitvanProdukt();
		for (String string : arr1) {
			choice_izpitvan_produkt.add(string);
		}
		GridBagConstraints gbc_izpitvan_produkt = new GridBagConstraints();
		gbc_izpitvan_produkt.gridwidth = 4;
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
		choice_obekt_na_izpitvane_request.setPreferredSize(new Dimension(405, 20));
		String[] arr2 = RequestViewAplication.getStringMassiveO_I_R();
		for (String string : arr2) {
			choice_obekt_na_izpitvane_request.add(string);
		}
		GridBagConstraints gbc_choice_obekt_na_izpitvane_request = new GridBagConstraints();
		gbc_choice_obekt_na_izpitvane_request.anchor = GridBagConstraints.WEST;
		gbc_choice_obekt_na_izpitvane_request.gridwidth = 3;
		gbc_choice_obekt_na_izpitvane_request.insets = new Insets(0, 0, 5, 5);
		gbc_choice_obekt_na_izpitvane_request.gridx = 3;
		gbc_choice_obekt_na_izpitvane_request.gridy = 7;
		p.add(choice_obekt_na_izpitvane_request, gbc_choice_obekt_na_izpitvane_request);

		JLabel lbl_Razmernost = new JLabel("Размерност");
		GridBagConstraints gbc_lbl_Razmernost = new GridBagConstraints();
		gbc_lbl_Razmernost.anchor = GridBagConstraints.WEST;
		gbc_lbl_Razmernost.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Razmernost.gridx = 1;
		gbc_lbl_Razmernost.gridy = 8;
		p.add(lbl_Razmernost, gbc_lbl_Razmernost);

		Choice choice_Razmernost = new Choice();
		choice_Razmernost.setPreferredSize(new Dimension(60, 20));
		String[] arr3 = RequestViewAplication.getStringMassiveRazmernost();
		for (String string : arr3) {
			choice_Razmernost.add(string);
		}
		GridBagConstraints gbc_choice_Razmernost = new GridBagConstraints();
		gbc_choice_Razmernost.anchor = GridBagConstraints.WEST;
		gbc_choice_Razmernost.insets = new Insets(0, 0, 5, 5);
		gbc_choice_Razmernost.gridx = 2;
		gbc_choice_Razmernost.gridy = 8;
		p.add(choice_Razmernost, gbc_choice_Razmernost);

		JLabel lbl_list_izpitvan_pokazatel = new JLabel("Изпитван показател:");
		GridBagConstraints gbc_lbl_list_izpitvan_pokazatel = new GridBagConstraints();
		gbc_lbl_list_izpitvan_pokazatel.anchor = GridBagConstraints.WEST;
		gbc_lbl_list_izpitvan_pokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_list_izpitvan_pokazatel.gridx = 1;
		gbc_lbl_list_izpitvan_pokazatel.gridy = 9;
		p.add(lbl_list_izpitvan_pokazatel, gbc_lbl_list_izpitvan_pokazatel);

		final JTextArea textArea = new JTextArea();
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

				final JFrame f = new JFrame();
				ChoiceListIzpPokazatel choiceLP = new ChoiceListIzpPokazatel(f);

				String str = "";
				String[] sss = new String[choiceLP.getChoiceListPokazatel1().length];
				sss = choiceLP.getChoiceListPokazatel1();
				for (String string : choiceLP.getChoiceListPokazatel1()) {
					str = str + string + "\n";
				}
				textArea.setText(str);

			}
		});
		GridBagConstraints gbc_btn_list_izpitvan_pokazatel = new GridBagConstraints();
		gbc_btn_list_izpitvan_pokazatel.gridwidth = 2;
		gbc_btn_list_izpitvan_pokazatel.anchor = GridBagConstraints.WEST;
		gbc_btn_list_izpitvan_pokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_btn_list_izpitvan_pokazatel.gridx = 4;
		gbc_btn_list_izpitvan_pokazatel.gridy = 9;
		p.add(btn_list_izpitvan_pokazatel, gbc_btn_list_izpitvan_pokazatel);

		JLabel lbl_Count_Sample = new JLabel("Брой на пробите ");
		GridBagConstraints gbc_lbl_Count_Sample = new GridBagConstraints();
		gbc_lbl_Count_Sample.anchor = GridBagConstraints.EAST;
		gbc_lbl_Count_Sample.gridwidth = 2;
		gbc_lbl_Count_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Count_Sample.gridx = 2;
		gbc_lbl_Count_Sample.gridy = 13;
		p.add(lbl_Count_Sample, gbc_lbl_Count_Sample);

		txtFld_Count_Sample = new JTextField();
		txtFld_Count_Sample.setText("1");
		txtFld_Count_Sample.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {
				System.out.println("Data = " + txtFld_Count_Sample.getText());
				String str = txtFld_Count_Sample.getText();
				try {
					int k = Integer.valueOf(str);
					if (k > 20) {
						txtFld_Count_Sample.setForeground(Color.red);
					} else {
						txtFld_Count_Sample.setForeground(Color.BLACK);
					}
					txtFld_Count_Sample.setText(str);
				} catch (NumberFormatException e) {
					txtFld_Count_Sample.setText("");
				}
			}

			@Override
			public void keyReleased(KeyEvent event) {
				System.out.println("Data = " + txtFld_Count_Sample.getText());
				String str = txtFld_Count_Sample.getText();
				try {
					int k = Integer.valueOf(str);
					if (k > 20) {
						txtFld_Count_Sample.setForeground(Color.red);
					} else {
						txtFld_Count_Sample.setForeground(Color.BLACK);
					}
					txtFld_Count_Sample.setText(str);
				} catch (NumberFormatException e) {
					txtFld_Count_Sample.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent event) {
				System.out.println("Data = " + txtFld_Count_Sample.getText());
				String str = txtFld_Count_Sample.getText();
				try {
					int k = Integer.valueOf(str);
					if (k > 20) {
						txtFld_Count_Sample.setForeground(Color.red);
					} else {
						txtFld_Count_Sample.setForeground(Color.BLACK);
					}
					txtFld_Count_Sample.setText(str);
				} catch (NumberFormatException e) {
					txtFld_Count_Sample.setText("");
				}
			}
		});
		txtFld_Count_Sample.setPreferredSize(new Dimension(5, 20));
		GridBagConstraints gbc_txtFld_Count_Sample = new GridBagConstraints();
		gbc_txtFld_Count_Sample.anchor = GridBagConstraints.WEST;
		gbc_txtFld_Count_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_txtFld_Count_Sample.gridx = 4;
		gbc_txtFld_Count_Sample.gridy = 13;
		p.add(txtFld_Count_Sample, gbc_txtFld_Count_Sample);
		txtFld_Count_Sample.setColumns(3);

		JButton btn_SampleDescription = new JButton("Описание на пробите");
		btn_SampleDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						try {
							System.out.println(" countSimple "+txtFld_Count_Sample.getText());
					int count_Sample = Integer.valueOf(txtFld_Count_Sample.getText());
					final JFrame f = new JFrame();
					SampleAddView sampleDescript = new SampleAddView(f,count_Sample);
					sampleDescript.setVisible(true);
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(RequestView.this,
		                    "Не сте въвели брой на пробите!",
		                    "Грешни данни",
		                    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btn_SampleDescription = new GridBagConstraints();
		gbc_btn_SampleDescription.anchor = GridBagConstraints.WEST;
		gbc_btn_SampleDescription.insets = new Insets(0, 0, 5, 5);
		gbc_btn_SampleDescription.gridx = 5;
		gbc_btn_SampleDescription.gridy = 13;
		p.add(btn_SampleDescription, gbc_btn_SampleDescription);

		JLabel lbl_SampleDescription = new JLabel("Описание на пробите ");
		GridBagConstraints gbc_lbl_SampleDescription = new GridBagConstraints();
		gbc_lbl_SampleDescription.anchor = GridBagConstraints.WEST;
		gbc_lbl_SampleDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_SampleDescription.gridx = 1;
		gbc_lbl_SampleDescription.gridy = 14;
		p.add(lbl_SampleDescription, gbc_lbl_SampleDescription);

		JTextArea txtArea_SampleDescription = new JTextArea();
		GridBagConstraints gbc_txtArea_SampleDescription = new GridBagConstraints();
		gbc_txtArea_SampleDescription.gridwidth = 4;
		gbc_txtArea_SampleDescription.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_SampleDescription.fill = GridBagConstraints.BOTH;
		gbc_txtArea_SampleDescription.gridx = 2;
		gbc_txtArea_SampleDescription.gridy = 14;
		p.add(txtArea_SampleDescription, gbc_txtArea_SampleDescription);

		JLabel lbl_Period = new JLabel("Периодичност");
		GridBagConstraints gbc_lbl_Period = new GridBagConstraints();
		gbc_lbl_Period.anchor = GridBagConstraints.EAST;
		gbc_lbl_Period.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Period.gridx = 1;
		gbc_lbl_Period.gridy = 16;
		p.add(lbl_Period, gbc_lbl_Period);

		Choice choice_Period = new Choice();
		GridBagConstraints gbc_choice_Period = new GridBagConstraints();

		String[] arr4 = RequestViewAplication.getStringMassivePeriod();
		for (String string : arr4) {
			choice_Period.add(string);
		}
		gbc_choice_Period.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_Period.insets = new Insets(0, 0, 5, 5);
		gbc_choice_Period.gridx = 2;
		gbc_choice_Period.gridy = 16;
		p.add(choice_Period, gbc_choice_Period);

		// date_time_reception
		JLabel lbl_date_time_reception = new JLabel("Референтна дата (средата на периода)");
		GridBagConstraints gbc_lbl_date_time_reception = new GridBagConstraints();
		gbc_lbl_date_time_reception.gridwidth = 2;
		gbc_lbl_date_time_reception.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_time_reception.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_time_reception.gridx = 1;
		gbc_lbl_date_time_reception.gridy = 12;
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
		gbc_date_time_reception.insets = new Insets(0, 0, 5, 5);
		gbc_date_time_reception.gridx = 3;
		gbc_date_time_reception.gridy = 12;
		p.add(txt_fid_date_time_reception, gbc_date_time_reception);

		// StartDate of date_time_reception
		txtStartDate = new JTextField();
		GridBagConstraints gbc_txtStartDate = new GridBagConstraints();
		gbc_txtStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtStartDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStartDate.gridx = 2;
		gbc_txtStartDate.gridy = 11;
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
		gbc_btnStartData.anchor = GridBagConstraints.WEST;
		gbc_btnStartData.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartData.gridx = 3;
		gbc_btnStartData.gridy = 11;
		p.add(btnStartData, gbc_btnStartData);

		// EndDate of date_time_reception
		txtEndDate = new JTextField();
		txtEndDate.setColumns(10);
		GridBagConstraints gbc_textField_a;
		gbc_txtEndDate = new GridBagConstraints();
		gbc_txtEndDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtEndDate.gridx = 4;
		gbc_txtEndDate.gridy = 11;
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
		gbc_btnEndData.gridx = 5;
		gbc_btnEndData.gridy = 11;
		p.add(btnEndData, gbc_btnEndData);

		// date_execution
		// ************************************************************************
		JLabel lbl_date_execution = new JLabel("Срок за изпълнение:");
		GridBagConstraints gbc_lbl_date_execution = new GridBagConstraints();
		gbc_lbl_date_execution.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_execution.gridwidth = 2;
		gbc_lbl_date_execution.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_execution.gridx = 1;
		gbc_lbl_date_execution.gridy = 17;
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
		gbc_txtFld_date_execution.insets = new Insets(0, 0, 5, 5);
		gbc_txtFld_date_execution.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld_date_execution.gridx = 3;
		gbc_txtFld_date_execution.gridy = 17;
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
		gbc_btn_date_execution.gridx = 4;
		gbc_btn_date_execution.gridy = 17;
		p.add(btn_date_execution, gbc_btn_date_execution);

		// date_time_request
		// *****************************************************************
		JLabel lbl_date_time_request = new JLabel("Дата и час на приемане:");
		GridBagConstraints gbc_lbl_date_time_request = new GridBagConstraints();
		gbc_lbl_date_time_request.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_time_request.gridwidth = 2;
		gbc_lbl_date_time_request.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_time_request.gridx = 1;
		gbc_lbl_date_time_request.gridy = 18;
		p.add(lbl_date_time_request, gbc_lbl_date_time_request);

		txtFld_date_time_request = new JTextField(RequestViewAplication.DateNaw(true));
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
		gbc_txtFld_date_time_request.insets = new Insets(0, 0, 5, 5);
		gbc_txtFld_date_time_request.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld_date_time_request.gridx = 3;
		gbc_txtFld_date_time_request.gridy = 18;
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
				System.out.println("Data11 = " + txtField_RequestCode.getText().trim());
			}
		});
		GridBagConstraints gbc_btn_date_time_request = new GridBagConstraints();
		gbc_btn_date_time_request.anchor = GridBagConstraints.WEST;
		gbc_btn_date_time_request.gridwidth = 2;
		gbc_btn_date_time_request.insets = new Insets(0, 0, 5, 5);
		gbc_btn_date_time_request.gridx = 4;
		gbc_btn_date_time_request.gridy = 18;
		p.add(btn_date_time_request, gbc_btn_date_time_request);

		getContentPane().add(scrollpane, BorderLayout.CENTER);

	}

}
