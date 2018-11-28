package ReadAndSaveDocFailInDBase;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import Aplication.Ind_num_docDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import Aplication.ZabelejkiDAO;
import CreateWordDocProtocol.Generate_Map_For_Request_Word_Document;
import DBase_Class.Extra_module;
import DBase_Class.Ind_num_doc;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Period;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Sample;
import DBase_Class.Users;
import DBase_Class.Zabelejki;
import WindowView.AddInChoice;
import WindowView.ChoiceL_I_P;
import WindowView.DateChoice;
import WindowView.DatePicker;
import WindowView.RequestViewAplication;
import WindowView.SampleViewAdd;
import WindowViewAplication.DocxMainpulator;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Choice;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class RequestViewForReadDoc extends JFrame {

	JScrollPane scrollpane;
	private JTextField txtFld_Count_Sample;
	private JTextField txtFld_date_execution;
	private JTextField txtFld_date_reception;
	private JTextField txtField_RequestCode;
	private JTextField txtFld_Date_Request;
	private String[][] masiveSampleValue = null;
	private String str_Descript_grup_Sample = "";
	private static ArrayList<String> comBox_O_I_S;
	private Boolean section = true;
	private Extra_module xtra_module = null;
	private Boolean corectRequestCode = true;
	private JLabel lblError;
	private Choice choice_izpitvan_produkt;
	private Choice choice_obekt_na_izpitvane_request;
	private JTextArea txtArea_list_izpitvan_pokazatel;
	private JTextField txt_fid_date_time_reference;
	private JTextArea txtArea_SampleDescription;
	private Choice choice_ind_num_doc;
	private Choice choice_Razmernost;
	private Choice choice_Zab;
	private JCheckBox chckbx_accreditation;
	private JTextArea txtArea_Descript_grup_Sample;
	private Users curent_user;
	private ArrayList<String> array_O_I_R;
	private Request request = null;;
	private Font font = new Font("Tahoma", Font.PLAIN, 11);

	private Border border;
//	private String FORMAT_DATE_TIME = GlobalVariable.getFORMAT_DATE_TIME();

	public RequestViewForReadDoc(Request tamplateRequest, String date_time_reference) {
		super("JScrollPane Demonstration");
		setSize(850, 980);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		init(tamplateRequest, date_time_reference);
		setVisible(true);
	}

	@SuppressWarnings("static-access")
	public void init(Request tamplateRequest, String date_time_reference) {

//		Boolean flTamplate = true;
		final JPanel p = new JPanel();
		p.setAlignmentY(0.0f);
		p.setAlignmentX(0.0f);
		p.setBackground(SystemColor.controlHighlight);
		p.setBorder(null);
		p.setSize(800, 900);

		scrollpane = new JScrollPane(p);
		scrollpane.setName("");
		scrollpane.setBorder(null);

		GridBagLayout gbl_p = new GridBagLayout();
		gbl_p.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_p.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_p.columnWidths = new int[] { 15, 190, 110, 100, 110, 160, 15 };
		gbl_p.rowHeights = new int[] { 181, 25, 27, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 24,
				0 };
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
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridwidth = 5;
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		p.add(lblNewLabel_2, gbc_lblNewLabel_2);

		String text2 = "<html>Попълва се от ЛИ-РХ за изпитвания, извършвани по програми и документи, вътрешни за<br>"
				+ "ДП „Радиоактивни отпадъци”</html>";

		lblError = new JLabel(" ");
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.gridwidth = 5;
		gbc_lblError.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 4;
		p.add(lblError, gbc_lblError);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblError.setForeground(Color.red);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 5;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 3;
		p.add(panel_1, gbc_panel_1);

		JLabel label = new JLabel("№");
		panel_1.add(label);
		// TODO txtField_RequestCode (код на заявката)
		txtField_RequestCode = new JTextField();
		txtField_RequestCode.setText(tamplateRequest.getRecuest_code());
		txtField_RequestCode.setForeground(Color.GRAY);
		txtField_RequestCode.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {

				txtField_RequestCode.setText(RequestViewAplication.checkFormatString(txtField_RequestCode.getText()));
				if (RequestDAO.checkRequestCode(txtField_RequestCode.getText())) {
					txtField_RequestCode.setForeground(Color.red);
					lblError.setText("Заявка с този номер вече съществува");
					corectRequestCode = false;
				} else {

					if (RequestViewAplication.checkMaxVolume(txtField_RequestCode.getText(), 3000, 6000)) {
						txtField_RequestCode.setForeground(Color.red);
						lblError.setText("Некоректен номер");
						corectRequestCode = false;
					} else {
						txtField_RequestCode.setForeground(Color.BLACK);

						txtField_RequestCode.setBorder(new LineBorder(Color.BLACK));
						lblError.setText(" ");
						corectRequestCode = true;
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent event) {

			}
		});
		txtField_RequestCode.setColumns(4);
		panel_1.add(txtField_RequestCode);

		JLabel label_2 = new JLabel("/");
		panel_1.add(label_2);

		// TODO txtFld_Date_Request (дата на заявката)
		txtFld_Date_Request = new JTextField();
		txtFld_Date_Request.setColumns(8);
		border = txtFld_Date_Request.getBorder();
		txtFld_Date_Request.setText(tamplateRequest.getDate_request());
		txtFld_Date_Request.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {
				//
				// if (DatePicker.incorrectDate(txtFld_Date_Request.getText(),
				// false))
				// txtFld_Date_Request.setForeground(Color.RED);
				// else
				// txtFld_Date_Request.setForeground(Color.BLACK);
			}

			@Override
			public void keyReleased(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_Date_Request.getText(), false)) {
					txtFld_Date_Request.setForeground(Color.RED);
				} else {
					txtFld_Date_Request.setForeground(Color.BLACK);
					txtFld_Date_Request.setBorder(border);
				}
			}

			@Override
			public void keyPressed(KeyEvent event) {

				// if (DatePicker.incorrectDate(txtFld_Date_Request.getText(),
				// false))
				// txtFld_Date_Request.setForeground(Color.RED);
				// else
				// txtFld_Date_Request.setForeground(Color.BLACK);
			}
		});
		panel_1.add(txtFld_Date_Request);

		JLabel lblNewLabel_4 = new JLabel(text2);
		lblNewLabel_4.setAlignmentY(0.0f);
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setBorder(
				new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(255, 255, 255), 6)));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.gridwidth = 5;
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 5;
		p.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lbl_ind_num_doc = new JLabel("Ид. номер на документа, изискващ изпитването: ");
		lbl_ind_num_doc.setBorder(null);
		lbl_ind_num_doc.setFont(font);
		GridBagConstraints gbc_lbl_ind_num_doc = new GridBagConstraints();
		gbc_lbl_ind_num_doc.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_ind_num_doc.anchor = GridBagConstraints.WEST;
		gbc_lbl_ind_num_doc.gridwidth = 2;
		gbc_lbl_ind_num_doc.gridx = 1;
		gbc_lbl_ind_num_doc.gridy = 6;
		p.add(lbl_ind_num_doc, gbc_lbl_ind_num_doc);

		// TODO choice_ind_num_doc (Ид. номер на документа)

		choice_ind_num_doc = new Choice();
		choice_ind_num_doc.setFont(font);
		choice_ind_num_doc.setPreferredSize(new Dimension(300, 20));
		String[] arr = RequestViewAplication.getStringMassiveI_N_D();
		for (String string : arr) {
			choice_ind_num_doc.add(string);
		}
		if (tamplateRequest != null) {
			if (tamplateRequest.getInd_num_doc() != null) {
				choice_ind_num_doc.select(tamplateRequest.getInd_num_doc().getName());
			} else {
				choice_ind_num_doc.select("");
			}
		}
		GridBagConstraints gbc_choice_ind_num_doc = new GridBagConstraints();
		gbc_choice_ind_num_doc.anchor = GridBagConstraints.WEST;
		gbc_choice_ind_num_doc.gridwidth = 3;
		gbc_choice_ind_num_doc.insets = new Insets(0, 0, 5, 5);
		gbc_choice_ind_num_doc.gridx = 3;
		gbc_choice_ind_num_doc.gridy = 6;
		p.add(choice_ind_num_doc, gbc_choice_ind_num_doc);

		JLabel lbl2_ind_num_doc = new JLabel(" ");
		lbl2_ind_num_doc.setFont(font);
		GridBagConstraints gbc_lbl2_ind_num_doc = new GridBagConstraints();
		gbc_lbl2_ind_num_doc.gridwidth = 4;
		gbc_lbl2_ind_num_doc.insets = new Insets(0, 0, 5, 5);
		gbc_lbl2_ind_num_doc.gridx = 2;
		gbc_lbl2_ind_num_doc.gridy = 7;
		p.add(lbl2_ind_num_doc, gbc_lbl2_ind_num_doc);

		choice_ind_num_doc.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				lbl2_ind_num_doc
						.setText(RequestViewAplication.getIND_DescriptByName(choice_ind_num_doc.getSelectedItem()));
			}

		});

		JLabel lbl_izpitvan_produkt = new JLabel("Изпитван продукт");
		GridBagConstraints gbc_lbl_izpitvan_produkt = new GridBagConstraints();
		gbc_lbl_izpitvan_produkt.anchor = GridBagConstraints.WEST;
		gbc_lbl_izpitvan_produkt.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_izpitvan_produkt.gridx = 1;
		gbc_lbl_izpitvan_produkt.gridy = 8;
		p.add(lbl_izpitvan_produkt, gbc_lbl_izpitvan_produkt);

		// TODO choice_izpitvan_produkt (изпитван продукт)
		choice_izpitvan_produkt = new Choice();
		choice_izpitvan_produkt.setFont(font);
		String[] arr1 = RequestViewAplication.getStringMassiveIzpitvanProdukt();
		for (String string : arr1) {
			choice_izpitvan_produkt.add(string);
		}
		if (tamplateRequest != null) {
			choice_izpitvan_produkt.select(tamplateRequest.getIzpitvan_produkt().getName_zpitvan_produkt());
		}
		GridBagConstraints gbc_izpitvan_produkt = new GridBagConstraints();
		gbc_izpitvan_produkt.gridwidth = 4;
		gbc_izpitvan_produkt.fill = GridBagConstraints.HORIZONTAL;
		gbc_izpitvan_produkt.insets = new Insets(0, 0, 5, 5);
		gbc_izpitvan_produkt.gridx = 2;
		gbc_izpitvan_produkt.gridy = 8;
		p.add(choice_izpitvan_produkt, gbc_izpitvan_produkt);

		choice_izpitvan_produkt.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				choice_izpitvan_produkt.setBackground(Color.WHITE);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				choice_izpitvan_produkt.setBackground(Color.WHITE);

			}
		});

		choice_izpitvan_produkt.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				choice_izpitvan_produkt.setBackground(Color.WHITE);
			}

		});

		JLabel lbl_obekt_na_izpitvane_request = new JLabel("Обект, от който са взети пробите:");
		GridBagConstraints gbc_lbl_obekt_na_izpitvane_request = new GridBagConstraints();
		gbc_lbl_obekt_na_izpitvane_request.anchor = GridBagConstraints.WEST;
		gbc_lbl_obekt_na_izpitvane_request.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_obekt_na_izpitvane_request.gridx = 1;
		gbc_lbl_obekt_na_izpitvane_request.gridy = 9;
		p.add(lbl_obekt_na_izpitvane_request, gbc_lbl_obekt_na_izpitvane_request);

		// TODO choice_obekt_na_izpitvane_request (обект на изпитване)
		choice_obekt_na_izpitvane_request = new Choice();
		choice_obekt_na_izpitvane_request.setFont(font);
		choice_obekt_na_izpitvane_request.setPreferredSize(new Dimension(205, 20));
		array_O_I_R = RequestViewAplication.getStringMassiveO_I_R();
		for (String string : array_O_I_R) {
			System.out.println(string);
			choice_obekt_na_izpitvane_request.add(string);
		}
		if (tamplateRequest != null) {
			choice_obekt_na_izpitvane_request
					.select(tamplateRequest.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane());
		}
		GridBagConstraints gbc_choice_obekt_na_izpitvane_request = new GridBagConstraints();
		gbc_choice_obekt_na_izpitvane_request.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_obekt_na_izpitvane_request.gridwidth = 3;
		gbc_choice_obekt_na_izpitvane_request.insets = new Insets(0, 0, 5, 5);
		gbc_choice_obekt_na_izpitvane_request.gridx = 2;
		gbc_choice_obekt_na_izpitvane_request.gridy = 9;
		p.add(choice_obekt_na_izpitvane_request, gbc_choice_obekt_na_izpitvane_request);

		choice_obekt_na_izpitvane_request.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				choice_obekt_na_izpitvane_request.setBackground(Color.WHITE);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				choice_obekt_na_izpitvane_request.setBackground(Color.WHITE);
			}
		});
		choice_obekt_na_izpitvane_request.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				choice_obekt_na_izpitvane_request.setBackground(Color.WHITE);
			}
		});

		JButton btn_add__obekt_na_izpitvane_request = new JButton("Добавяне");
		btn_add__obekt_na_izpitvane_request.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean fl = false;
//				final JFrame f = new JFrame();

//				AddInChoice choiceO_I_R = new AddInChoice(f, array_O_I_R,
//						choice_obekt_na_izpitvane_request.getSelectedItem());

				String str = AddInChoice.getChoice();
				for (String string : array_O_I_R) {
					if (str.equals(string))
						fl = true;
				}
				if (!fl) {
					array_O_I_R.add(str);
					choice_obekt_na_izpitvane_request.add(str);

				}
				choice_obekt_na_izpitvane_request.select(str);
			}
		});
		GridBagConstraints gbc_btn_add__obekt_na_izpitvane_request = new GridBagConstraints();
		gbc_btn_add__obekt_na_izpitvane_request.anchor = GridBagConstraints.NORTHWEST;
		gbc_btn_add__obekt_na_izpitvane_request.insets = new Insets(0, 0, 5, 5);
		gbc_btn_add__obekt_na_izpitvane_request.gridx = 5;
		gbc_btn_add__obekt_na_izpitvane_request.gridy = 9;
		p.add(btn_add__obekt_na_izpitvane_request, gbc_btn_add__obekt_na_izpitvane_request);

		JLabel lbl_Razmernost = new JLabel("Размерност");
		GridBagConstraints gbc_lbl_Razmernost = new GridBagConstraints();
		gbc_lbl_Razmernost.anchor = GridBagConstraints.WEST;
		gbc_lbl_Razmernost.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Razmernost.gridx = 1;
		gbc_lbl_Razmernost.gridy = 10;
		p.add(lbl_Razmernost, gbc_lbl_Razmernost);

		// TODO choice_Razmernost (размерност)
		choice_Razmernost = new Choice();
		choice_Razmernost.setFont(font);
		choice_Razmernost.setPreferredSize(new Dimension(60, 20));
		String[] arr3 = RequestViewAplication.getStringMassiveRazmernost();
		for (String string : arr3) {
			choice_Razmernost.add(string);
		}
		if (tamplateRequest != null) {
			choice_Razmernost.select(tamplateRequest.getRazmernosti().getName_razmernosti());
		}
		GridBagConstraints gbc_choice_Razmernost = new GridBagConstraints();
		gbc_choice_Razmernost.anchor = GridBagConstraints.WEST;
		gbc_choice_Razmernost.insets = new Insets(0, 0, 5, 5);
		gbc_choice_Razmernost.gridx = 2;
		gbc_choice_Razmernost.gridy = 10;
		p.add(choice_Razmernost, gbc_choice_Razmernost);

		chckbx_accreditation = new JCheckBox("Извън обхват", !tamplateRequest.getAccreditation());
		GridBagConstraints gbc_chckbx_accreditation = new GridBagConstraints();
		gbc_chckbx_accreditation.anchor = GridBagConstraints.EAST;
		gbc_chckbx_accreditation.insets = new Insets(0, 0, 5, 5);
		gbc_chckbx_accreditation.gridx = 5;
		gbc_chckbx_accreditation.gridy = 10;
		p.add(chckbx_accreditation, gbc_chckbx_accreditation);

		JLabel lbl_list_izpitvan_pokazatel = new JLabel("Изпитван показател:");
		GridBagConstraints gbc_lbl_list_izpitvan_pokazatel = new GridBagConstraints();
		gbc_lbl_list_izpitvan_pokazatel.anchor = GridBagConstraints.WEST;
		gbc_lbl_list_izpitvan_pokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_list_izpitvan_pokazatel.gridx = 1;
		gbc_lbl_list_izpitvan_pokazatel.gridy = 11;
		p.add(lbl_list_izpitvan_pokazatel, gbc_lbl_list_izpitvan_pokazatel);

		// TODO txtArea_list_izpitvan_pokazatel (изпитван показарел)
		txtArea_list_izpitvan_pokazatel = new JTextArea();
		txtArea_list_izpitvan_pokazatel.setFont(font);
		// txtArea_list_izpitvan_pokazatel.setFont(arg0);
		GridBagConstraints gbc_txtArea_list_izpitvan_pokazatel = new GridBagConstraints();
		gbc_txtArea_list_izpitvan_pokazatel.gridwidth = 3;
		gbc_txtArea_list_izpitvan_pokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_list_izpitvan_pokazatel.fill = GridBagConstraints.BOTH;
		gbc_txtArea_list_izpitvan_pokazatel.gridx = 2;
		gbc_txtArea_list_izpitvan_pokazatel.gridy = 11;
		txtArea_list_izpitvan_pokazatel.setEditable(false);
		p.add(txtArea_list_izpitvan_pokazatel, gbc_txtArea_list_izpitvan_pokazatel);

		if (tamplateRequest != null) {
			List<String> list_String_I_P_Tamplate = new ArrayList<String>();
			List<IzpitvanPokazatel> listIzpitvanPokazatel = RequestViewAplication.get_List_Izpitvan_pokazatel_From_Request(tamplateRequest);
			String[][] masiveStrIzpitPokazatel = new String [listIzpitvanPokazatel.size()][2];
			
			int s = 0;
			for (IzpitvanPokazatel izpitPokazatelFormDBase : listIzpitvanPokazatel) {
				masiveStrIzpitPokazatel[s][0] = izpitPokazatelFormDBase.getId_pokazatel()+"";
				masiveStrIzpitPokazatel[s][1] = izpitPokazatelFormDBase.getPokazatel().getName_pokazatel();
				list_String_I_P_Tamplate.add(izpitPokazatelFormDBase.getPokazatel().getName_pokazatel());
			s++;
			}
			JFrame f = new JFrame();
			ChoiceL_I_P choiceLP = new ChoiceL_I_P(f, list_String_I_P_Tamplate, true);
			String strTamplate = "";
			txtArea_list_izpitvan_pokazatel.setText("");
			for (String string : choiceLP.getChoiceL_P()) {
				strTamplate = strTamplate + string + "\n";
			}
			int cout_str = strTamplate.length();
			txtArea_list_izpitvan_pokazatel.setText(strTamplate.substring(0, cout_str - 1));
		}

		JButton btn_list_izpitvan_pokazatel = new JButton("Избор на показател");
		btn_list_izpitvan_pokazatel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// String [] list_I_P = null;
				ArrayList<String> list_I_P = new ArrayList<String>();

				if (!txtArea_list_izpitvan_pokazatel.getText().equals("")) {
					list_I_P = ChoiceL_I_P.getChoiceL_P();
					System.out.println(list_I_P.size());
				}
				final JFrame f = new JFrame();
				ChoiceL_I_P choiceLP = new ChoiceL_I_P(f, list_I_P, false);

				String str = "";
				txtArea_list_izpitvan_pokazatel.setText("");
				for (String string : choiceLP.getChoiceL_P()) {
					str = str + string + "\n";
				}

				txtArea_list_izpitvan_pokazatel.setBorder(border);
				int cout_str = str.length();
				txtArea_list_izpitvan_pokazatel.setText(str.substring(0, cout_str - 1));

			}
		});
		GridBagConstraints gbc_btn_list_izpitvan_pokazatel = new GridBagConstraints();
		gbc_btn_list_izpitvan_pokazatel.anchor = GridBagConstraints.WEST;
		gbc_btn_list_izpitvan_pokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_btn_list_izpitvan_pokazatel.gridx = 5;
		gbc_btn_list_izpitvan_pokazatel.gridy = 11;
		p.add(btn_list_izpitvan_pokazatel, gbc_btn_list_izpitvan_pokazatel);

		// date_time_reception
		JLabel lbl_date_time_reception = new JLabel("Референтна дата (средата на периода)");
		GridBagConstraints gbc_lbl_date_time_reception = new GridBagConstraints();
		gbc_lbl_date_time_reception.gridwidth = 2;
		gbc_lbl_date_time_reception.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_time_reception.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_time_reception.gridx = 1;
		gbc_lbl_date_time_reception.gridy = 12;
		p.add(lbl_date_time_reception, gbc_lbl_date_time_reception);

		// TODO txtArea_Descript_grup_Sample (описание на групата проби)
		txtArea_Descript_grup_Sample = new JTextArea();
		txtArea_Descript_grup_Sample.setFont(font);

		GridBagConstraints gbc_txtArea_Descript_grup_Sample = new GridBagConstraints();
		gbc_txtArea_Descript_grup_Sample.gridwidth = 5;
		gbc_txtArea_Descript_grup_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_Descript_grup_Sample.fill = GridBagConstraints.BOTH;
		gbc_txtArea_Descript_grup_Sample.gridx = 1;
		gbc_txtArea_Descript_grup_Sample.gridy = 14;
		p.add(txtArea_Descript_grup_Sample, gbc_txtArea_Descript_grup_Sample);

		// TODO choice_Period (период)

		final Choice choice_Period = new Choice();
		choice_Period.setFont(font);
		choice_Period.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints gbc_choice_Period = new GridBagConstraints();
		gbc_choice_Period.anchor = GridBagConstraints.WEST;
		gbc_choice_Period.insets = new Insets(0, 0, 5, 5);
		gbc_choice_Period.gridx = 2;
		gbc_choice_Period.gridy = 13;
		p.add(choice_Period, gbc_choice_Period);

		String[] arr4 = RequestViewAplication.getStringMassivePeriod();
		for (String string : arr4) {
			choice_Period.add(string);
		}

		// TODO txt_fid_date_time_reference (референтна дата час)
		date_time_reference = date_time_reference.replaceAll("/", " ");
		txt_fid_date_time_reference = new JTextField(date_time_reference);
		txt_fid_date_time_reference.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {

				if (DatePicker.incorrectDate(txt_fid_date_time_reference.getText(), true)) {
					txt_fid_date_time_reference.setForeground(Color.RED);
				} else {
					txt_fid_date_time_reference.setForeground(Color.BLACK);
					txt_fid_date_time_reference.setBorder(border);
				}
			}

			@Override
			public void keyPressed(KeyEvent event) {

			}
		});

		GridBagConstraints gbc_date_time_reference = new GridBagConstraints();
		gbc_date_time_reference.fill = GridBagConstraints.HORIZONTAL;
		gbc_date_time_reference.insets = new Insets(0, 0, 5, 5);
		gbc_date_time_reference.gridx = 3;
		gbc_date_time_reference.gridy = 12;
		p.add(txt_fid_date_time_reference, gbc_date_time_reference);

		JButton btn_date_time_reception = new JButton("Избор");
		btn_date_time_reception.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					final JFrame f = new JFrame();
					DateChoice date_time_reception = new DateChoice(f, txt_fid_date_time_reference.getText());
					date_time_reception.setVisible(true);
					if (choice_Period.getSelectedItem().equals("")) {
						str_Descript_grup_Sample = "";
						str_Descript_grup_Sample = DateChoice.get_str_period();
					} else {
						if (DateChoice.get_str_period().equals("")) {
							str_Descript_grup_Sample = "";
							str_Descript_grup_Sample = "за " + choice_Period.getSelectedItem();
						} else {
							str_Descript_grup_Sample = "";
							str_Descript_grup_Sample = DateChoice.get_str_period() + "\nза "
									+ choice_Period.getSelectedItem();

						}
					}
					txtArea_Descript_grup_Sample.setText(str_Descript_grup_Sample);
					String textRefDate = "";
					textRefDate = DateChoice.get_date_time_reference();

					if (DatePicker.incorrectDate(textRefDate, true))
						txt_fid_date_time_reference.setForeground(Color.RED);
					else {
						txt_fid_date_time_reference.setForeground(Color.BLACK);
						txt_fid_date_time_reference.setBorder(border);
					}

					txt_fid_date_time_reference.setText(textRefDate);

				} catch (NumberFormatException e) {

				}
			}
		});
		GridBagConstraints gbc_btn_date_time_reception = new GridBagConstraints();
		gbc_btn_date_time_reception.anchor = GridBagConstraints.WEST;
		gbc_btn_date_time_reception.insets = new Insets(0, 0, 5, 5);
		gbc_btn_date_time_reception.gridx = 4;
		gbc_btn_date_time_reception.gridy = 12;
		p.add(btn_date_time_reception, gbc_btn_date_time_reception);
		// GridBagConstraints gbc_textField_a;

		JLabel lbl_Period = new JLabel("Периодичност");
		GridBagConstraints gbc_lbl_Period = new GridBagConstraints();
		gbc_lbl_Period.anchor = GridBagConstraints.EAST;
		gbc_lbl_Period.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Period.gridx = 1;
		gbc_lbl_Period.gridy = 13;
		p.add(lbl_Period, gbc_lbl_Period);

		// Add item listener choice_Period
		choice_Period.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				if (choice_Period.getSelectedItem().equals("")) {
					str_Descript_grup_Sample = "";
					str_Descript_grup_Sample = DateChoice.get_str_period();
				} else {
					if (DateChoice.get_str_period().equals("")) {
						str_Descript_grup_Sample = "";
						str_Descript_grup_Sample = "за м." + choice_Period.getSelectedItem();
					} else {
						str_Descript_grup_Sample = "";
						str_Descript_grup_Sample = DateChoice.get_str_period() + "\nза м."
								+ choice_Period.getSelectedItem();

					}
				}
				txtArea_Descript_grup_Sample.setText(str_Descript_grup_Sample);

			}
		});

		JLabel lbl_Count_Sample = new JLabel("Брой на пробите ");
		GridBagConstraints gbc_lbl_Count_Sample = new GridBagConstraints();
		gbc_lbl_Count_Sample.anchor = GridBagConstraints.EAST;
		gbc_lbl_Count_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Count_Sample.gridx = 3;
		gbc_lbl_Count_Sample.gridy = 15;
		p.add(lbl_Count_Sample, gbc_lbl_Count_Sample);

		JLabel lblError_Count_Sample = new JLabel(" ");
		GridBagConstraints gbc_lblError_Count_Sample = new GridBagConstraints();
		gbc_lblError_Count_Sample.anchor = GridBagConstraints.WEST;
		gbc_lblError_Count_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_lblError_Count_Sample.gridx = 4;
		gbc_lblError_Count_Sample.gridy = 16;
		p.add(lblError_Count_Sample, gbc_lblError_Count_Sample);

		// TODO txtFld_Count_Sample (брой на пробите)
		txtFld_Count_Sample = new JTextField();
		txtFld_Count_Sample.setText(tamplateRequest.getCounts_samples() + "");
		txtFld_Count_Sample.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {
				System.out.println("Data = " + txtFld_Count_Sample.getText());
				txtFld_Count_Sample.setText(RequestViewAplication.checkFormatString(txtFld_Count_Sample.getText()));
				// String str = txtFld_Count_Sample.getText();

				if (RequestViewAplication.checkMaxVolume(txtFld_Count_Sample.getText(), 0, 20)) {
					txtFld_Count_Sample.setForeground(Color.red);
					lblError_Count_Sample.setText("Некоректен брой");

				} else {
					txtFld_Count_Sample.setForeground(Color.BLACK);

					txtFld_Count_Sample.setBorder(border);
					lblError_Count_Sample.setText(" ");

				}

			}

			@Override
			public void keyPressed(KeyEvent event) {

			}
		});
		txtFld_Count_Sample.setPreferredSize(new Dimension(5, 20));
		GridBagConstraints gbc_txtFld_Count_Sample = new GridBagConstraints();
		gbc_txtFld_Count_Sample.anchor = GridBagConstraints.WEST;
		gbc_txtFld_Count_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_txtFld_Count_Sample.gridx = 4;
		gbc_txtFld_Count_Sample.gridy = 15;
		p.add(txtFld_Count_Sample, gbc_txtFld_Count_Sample);
		txtFld_Count_Sample.setColumns(3);

		// TODO txtArea_SampleDescription (описание на пробите)
		txtArea_SampleDescription = new JTextArea();
		txtArea_SampleDescription.setFont(font);
		GridBagConstraints gbc_txtArea_SampleDescription = new GridBagConstraints();
		gbc_txtArea_SampleDescription.gridwidth = 5;
		gbc_txtArea_SampleDescription.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_SampleDescription.fill = GridBagConstraints.BOTH;
		gbc_txtArea_SampleDescription.gridx = 1;
		gbc_txtArea_SampleDescription.gridy = 17;
		p.add(txtArea_SampleDescription, gbc_txtArea_SampleDescription);
		txtArea_SampleDescription.setEditable(false);

		comBox_O_I_S = RequestViewAplication.getStringMassiveO_I_S();
		JButton btn_SampleDescription = new JButton("Описание на пробите");
		btn_SampleDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				sampleView(tamplateRequest);
			}
		});
		GridBagConstraints gbc_btn_SampleDescription = new GridBagConstraints();
		gbc_btn_SampleDescription.anchor = GridBagConstraints.WEST;
		gbc_btn_SampleDescription.insets = new Insets(0, 0, 5, 5);
		gbc_btn_SampleDescription.gridx = 5;
		gbc_btn_SampleDescription.gridy = 15;
		p.add(btn_SampleDescription, gbc_btn_SampleDescription);

		JLabel lbl_SampleDescription = new JLabel("Описание на пробите ");
		GridBagConstraints gbc_lbl_SampleDescription = new GridBagConstraints();
		gbc_lbl_SampleDescription.anchor = GridBagConstraints.WEST;
		gbc_lbl_SampleDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_SampleDescription.gridx = 1;
		gbc_lbl_SampleDescription.gridy = 16;
		p.add(lbl_SampleDescription, gbc_lbl_SampleDescription);

		// date_execution
		// ************************************************************************
		JLabel lbl_date_execution = new JLabel("Срок за изпълнение:");
		GridBagConstraints gbc_lbl_date_execution = new GridBagConstraints();
		gbc_lbl_date_execution.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_execution.gridwidth = 2;
		gbc_lbl_date_execution.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_execution.gridx = 1;
		gbc_lbl_date_execution.gridy = 19;
		p.add(lbl_date_execution, gbc_lbl_date_execution);

		// TODO txtFld_date_execution (срок за изпълнение)
		txtFld_date_execution = new JTextField(tamplateRequest.getDate_execution());
		txtFld_date_execution.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_date_execution.getText(), false)) {
					txtFld_date_execution.setForeground(Color.RED);
				} else {
					txtFld_date_execution.setForeground(Color.BLACK);
					txtFld_date_execution.setBorder(border);
				}
			}

			@Override
			public void keyPressed(KeyEvent event) {

			}
		});

		GridBagConstraints gbc_txtFld_date_execution = new GridBagConstraints();
		gbc_txtFld_date_execution.insets = new Insets(0, 0, 5, 5);
		gbc_txtFld_date_execution.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld_date_execution.gridx = 3;
		gbc_txtFld_date_execution.gridy = 19;
		p.add(txtFld_date_execution, gbc_txtFld_date_execution);

		JButton btn_date_execution = new JButton("Избор на дата");
		btn_date_execution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFrame f = new JFrame();
				DatePicker dPicer = new DatePicker(f, false, txtFld_date_execution.getText());
				txtFld_date_execution.setText(dPicer.setPickedDate(false));
				String text_date_execution = "";

				text_date_execution = txtFld_date_execution.getText();
				if (DatePicker.incorrectDate(text_date_execution, false))
					txtFld_date_execution.setForeground(Color.RED);
				else {
					txtFld_date_execution.setForeground(Color.BLACK);
					txtFld_date_execution.setBorder(border);
				}
				txtFld_date_execution.setText(text_date_execution);

			}
		});
		GridBagConstraints gbc_btn_date_execution = new GridBagConstraints();
		gbc_btn_date_execution.anchor = GridBagConstraints.WEST;
		gbc_btn_date_execution.gridwidth = 2;
		gbc_btn_date_execution.insets = new Insets(0, 0, 5, 5);
		gbc_btn_date_execution.gridx = 4;
		gbc_btn_date_execution.gridy = 19;
		p.add(btn_date_execution, gbc_btn_date_execution);

		// date_time_request
		// *****************************************************************
		JLabel lbl_date_time_request = new JLabel("Дата и час на приемане:");
		GridBagConstraints gbc_lbl_date_time_request = new GridBagConstraints();
		gbc_lbl_date_time_request.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_time_request.gridwidth = 2;
		gbc_lbl_date_time_request.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_time_request.gridx = 1;
		gbc_lbl_date_time_request.gridy = 20;
		p.add(lbl_date_time_request, gbc_lbl_date_time_request);

		// TODO txtFld_date_time_request (дата на приемане)
		txtFld_date_reception = new JTextField(tamplateRequest.getDate_reception());
		txtFld_date_reception.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_date_reception.getText(), true)) {
					txtFld_date_reception.setForeground(Color.RED);

				} else {
					txtFld_date_reception.setForeground(Color.BLACK);
					txtFld_date_reception.setBorder(border);
				}
			}

			@Override
			public void keyPressed(KeyEvent event) {

			}
		});
		GridBagConstraints gbc_txtFld_date_time_request = new GridBagConstraints();
		gbc_txtFld_date_time_request.insets = new Insets(0, 0, 5, 5);
		gbc_txtFld_date_time_request.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld_date_time_request.gridx = 3;
		gbc_txtFld_date_time_request.gridy = 20;
		p.add(txtFld_date_reception, gbc_txtFld_date_time_request);

		JButton btn_date_time_request = new JButton("Избор на дата");
		btn_date_time_request.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFrame f = new JFrame();
				DatePicker dPicer = new DatePicker(f, true, txtFld_date_reception.getText());
				txtFld_date_reception.setText(dPicer.setPickedDate(true));
				String textRefDate = "";
				textRefDate = txtFld_date_reception.getText();
				if (DatePicker.incorrectDate(textRefDate, true))
					txtFld_date_reception.setForeground(Color.RED);
				else {
					txtFld_date_reception.setForeground(Color.BLACK);
					txtFld_date_reception.setBorder(border);
				}

				txtFld_date_reception.setText(textRefDate);
				System.out.println("Data in requestViewFild = " + txtFld_date_reception.getText().trim());
			}
		});
		GridBagConstraints gbc_btn_date_time_request = new GridBagConstraints();
		gbc_btn_date_time_request.anchor = GridBagConstraints.WEST;
		gbc_btn_date_time_request.gridwidth = 2;
		gbc_btn_date_time_request.insets = new Insets(0, 0, 5, 5);
		gbc_btn_date_time_request.gridx = 4;
		gbc_btn_date_time_request.gridy = 20;
		p.add(btn_date_time_request, gbc_btn_date_time_request);

		JLabel lblNewLabel_3 = new JLabel("Приел:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 21;
		p.add(lblNewLabel_3, gbc_lblNewLabel_3);

		curent_user = tamplateRequest.getUsers();
		JLabel lbl_User = new JLabel(
				curent_user.getName_users() + " " + curent_user.getFamily_users());
		GridBagConstraints gbc_lbl_User = new GridBagConstraints();
		gbc_lbl_User.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_User.gridx = 2;
		gbc_lbl_User.gridy = 21;
		p.add(lbl_User, gbc_lbl_User);

		JLabel lbl_note = new JLabel("Забележка:");
		GridBagConstraints gbc_lbl_note = new GridBagConstraints();
		gbc_lbl_note.anchor = GridBagConstraints.WEST;
		gbc_lbl_note.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_note.gridx = 1;
		gbc_lbl_note.gridy = 22;
		p.add(lbl_note, gbc_lbl_note);

		// TODO choice_Zab (забележка)

		ArrayList<String> arrayZab = RequestViewAplication.getStringZabelejki();
		choice_Zab = new Choice();
		choice_Zab.setFont(font);
		for (String string : arrayZab) {
			choice_Zab.add(string);
		}
		choice_Zab.setPreferredSize(new Dimension(4, 18));

		if (tamplateRequest.getZabelejki() != null) {
			choice_Zab.select(tamplateRequest.getZabelejki().getName_zabelejki());
		}

		GridBagConstraints gbc_choice_Zab = new GridBagConstraints();
		gbc_choice_Zab.fill = GridBagConstraints.BOTH;
		gbc_choice_Zab.gridwidth = 4;
		gbc_choice_Zab.insets = new Insets(0, 0, 5, 5);
		gbc_choice_Zab.gridx = 1;
		gbc_choice_Zab.gridy = 23;
		p.add(choice_Zab, gbc_choice_Zab);

		JButton btn_add_Zab = new JButton("Добавяне");
		btn_add_Zab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// -------------------------------------------------------
				Boolean fl = false;
				final JFrame f = new JFrame();

				new AddInChoice(f, arrayZab, choice_Zab.getSelectedItem());

				String str = AddInChoice.getChoice();

				// ---------------------------------------------------------

				for (String string : arrayZab) {
					if (str.equals(string))
						fl = true;
				}
				if (!fl) {
					arrayZab.add(str);
					choice_Zab.add(str);

				}
				choice_Zab.select(str);
			}
		});
		GridBagConstraints gbc_btn_add_Zab = new GridBagConstraints();
		gbc_btn_add_Zab.anchor = GridBagConstraints.NORTHWEST;
		gbc_btn_add_Zab.gridheight = 2;
		gbc_btn_add_Zab.insets = new Insets(0, 0, 5, 5);
		gbc_btn_add_Zab.gridx = 5;
		gbc_btn_add_Zab.gridy = 23;
		p.add(btn_add_Zab, gbc_btn_add_Zab);

		// TODO btn_save ( Запис )
		JButton btn_save = new JButton("Запис");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkRequest()) {
					saveRequestSamplePokazatelTable(tamplateRequest);
					// setVisible(false);
				}
			}
		});
		btn_save.setPreferredSize(new Dimension(100, 23));
		GridBagConstraints gbc_btn_save = new GridBagConstraints();
		gbc_btn_save.anchor = GridBagConstraints.WEST;
		gbc_btn_save.insets = new Insets(0, 0, 0, 5);
		gbc_btn_save.gridx = 5;
		gbc_btn_save.gridy = 25;
		p.add(btn_save, gbc_btn_save);

		// TODO btn_Preview ( Превю )
		JButton btn_Preview = new JButton("Превю");
		btn_Preview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkRequest()) {
					createPreviewRequestWordDoc(tamplateRequest);
				}

			}

		});
		GridBagConstraints gbc_btn_Preview = new GridBagConstraints();
		gbc_btn_Preview.insets = new Insets(0, 0, 0, 5);
		gbc_btn_Preview.gridx = 4;
		gbc_btn_Preview.gridy = 25;
		p.add(btn_Preview, gbc_btn_Preview);

		// TODO btn_Template ( Резултати )
		JButton btn_Template = new JButton("Резултати");
		btn_Template.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultsListInTableForReadDoc.DrawTableWithEnableResultsList(tamplateRequest);
			}
		});
		GridBagConstraints gbc_btn_Template = new GridBagConstraints();
		gbc_btn_Template.insets = new Insets(0, 0, 0, 5);
		gbc_btn_Template.gridx = 3;
		gbc_btn_Template.gridy = 25;
		p.add(btn_Template, gbc_btn_Template);

		getContentPane().add(scrollpane, BorderLayout.CENTER);

	}

	private Boolean checkRequest() {

		Boolean saveCheck = true;
		String str_RequestCode = "";

		corectRequestCode = true;

		if (!corectRequestCode) {
			txtField_RequestCode.setBorder(new LineBorder(Color.RED));
			str_RequestCode = "код на заявката" + "\n";
			saveCheck = false;
		}
		String str_RequestDate = "";
		if (DatePicker.incorrectDate(txtFld_Date_Request.getText(), false)) {
			txtFld_Date_Request.setBorder(new LineBorder(Color.RED));
			str_RequestDate = "дата на заявката" + "\n";
			saveCheck = false;
		}
		String str_Izpit_Prod = "";
		if (choice_izpitvan_produkt.getSelectedItem().equals("")) {
			choice_izpitvan_produkt.setBackground(Color.RED);
			str_Izpit_Prod = "изпитван продукт" + "\n";
			saveCheck = false;
		}
		String str_Obekt_Izpit = "";
		if (choice_obekt_na_izpitvane_request.getSelectedItem().equals("")) {
			choice_obekt_na_izpitvane_request.setBackground(Color.RED);
			str_Obekt_Izpit = "обект на изпитване" + "\n";
			saveCheck = false;
		}
		String str_L_I_P = "";
		if (txtArea_list_izpitvan_pokazatel.getText().equals("")) {
			txtArea_list_izpitvan_pokazatel.setBorder(new LineBorder(Color.RED));
			str_L_I_P = "изпитван показател" + "\n";
			saveCheck = false;
		}
		String str_corectRefDate = "";
		if (DatePicker.incorrectDate(txt_fid_date_time_reference.getText(), true)) {
			txt_fid_date_time_reference.setBorder(new LineBorder(Color.RED));
			str_corectRefDate = "референтна дата" + "\n";
			saveCheck = false;
		}
		String str_SampleDescription = "";
		if (txtArea_SampleDescription.getText().equals("")) {
			txtArea_SampleDescription.setBorder(new LineBorder(Color.RED));
			str_SampleDescription = "описание на пробите" + "\n";
			saveCheck = false;
		}
		String str_DateExecution = "";
		if (DatePicker.incorrectDate(txtFld_date_execution.getText(), false)) {
			txtFld_date_execution.setBorder(new LineBorder(Color.RED));
			str_DateExecution = "срок за изпълнение" + "\n";
			saveCheck = false;
		}

		String str_DateTimeRequest = "";
		if (DatePicker.incorrectDate(txtFld_date_reception.getText(), false)) {
			txtFld_date_reception.setBorder(new LineBorder(Color.RED));
			str_DateTimeRequest = "дата на приемане" + "\n";
			saveCheck = false;
		}
		if (!saveCheck) {
			String str = str_RequestCode + str_RequestDate + str_Izpit_Prod + str_Obekt_Izpit + str_L_I_P
					+ str_corectRefDate + str_SampleDescription + str_DateExecution + str_DateTimeRequest;
			System.out.println(str);
			JOptionPane.showMessageDialog(RequestViewForReadDoc.this, str, "Грешни данни за следните полета:",
					JOptionPane.ERROR_MESSAGE);
		}

		return saveCheck;
	}

	private void saveRequestSamplePokazatelTable(Request request) {

		// TODO Update Request ( презапис на заявка )

		RequestDAO.updateObjectRequest(createRequestObject(request));

		// TODO Update IzpitvanPokaztel ( презапис на Изпитван показател )

		ArrayList<List_izpitvan_pokazatel> list_izpitvan_pokazatel = ChoiceL_I_P.getListI_PFormChoiceL_P();
		for (List_izpitvan_pokazatel l_I_P : list_izpitvan_pokazatel) {
			
			IzpitvanPokazatelDAO.setIzpitvanPokazatel(l_I_P, request);
		}

		// TODO Update Sample ( презапис на проби )

		int count_Sample = Integer.valueOf(txtFld_Count_Sample.getText());
		masiveSampleValue = SampleViewFromReadDocFile.getVolumeSampleView(count_Sample);
		updateSample(masiveSampleValue, request);
	}

	private Request createRequestObject(Request tamplateRequest) {
		
		Ind_num_doc ind_num_doc = null;
		if (!choice_ind_num_doc.getSelectedItem().equals(" "))
			ind_num_doc = Ind_num_docDAO.getValueIByName(choice_ind_num_doc.getSelectedItem());

		Izpitvan_produkt izpitvan_produkt = Izpitvan_produktDAO
				.getValueIzpitvan_produktByName(choice_izpitvan_produkt.getSelectedItem());
		Razmernosti razmernosti = RazmernostiDAO.getValueRazmernostiByName(choice_Razmernost.getSelectedItem());
		Zabelejki zabelejki = ZabelejkiDAO.getValueZabelejkiByName(choice_Zab.getSelectedItem());
		Obekt_na_izpitvane_request obekt_na_izpitvane_request = Obekt_na_izpitvane_requestDAO
				.getValueObekt_na_izpitvane_requestByName(choice_obekt_na_izpitvane_request.getSelectedItem());
		int count_Sample = Integer.valueOf(txtFld_Count_Sample.getText());

		int countOld, coundNew;
		coundNew = array_O_I_R.size();
		countOld = RequestViewAplication.getStringMassiveO_I_R().size();
		System.out.println(coundNew + " " + countOld);
		if (countOld != coundNew) {
			for (int i = countOld; i < coundNew; i++) {
				Obekt_na_izpitvane_requestDAO.setValueObekt_na_izpitvane(array_O_I_R.get(i));
			}
		}
		tamplateRequest.setDate_request(txtFld_Date_Request.getText());
		tamplateRequest.setAccreditation(chckbx_accreditation.isSelected());
		tamplateRequest.setSection(section);
		tamplateRequest.setExtra_module(xtra_module);
		tamplateRequest.setCounts_samples(Integer.valueOf(count_Sample));
		tamplateRequest.setDescription_sample_group(txtArea_Descript_grup_Sample.getText());
		tamplateRequest.setDate_reception(txtFld_date_reception.getText());
		tamplateRequest.setDate_execution(txtFld_date_execution.getText());
		tamplateRequest.setInd_num_doc(ind_num_doc);
		tamplateRequest.setIzpitvan_produkt( izpitvan_produkt);
		tamplateRequest.setRazmernosti(razmernosti);
		tamplateRequest.setZabelejki(zabelejki);
		tamplateRequest.setUsers(curent_user);
		tamplateRequest.setObekt_na_izpitvane_request(obekt_na_izpitvane_request);
		return tamplateRequest;

	}

	private void createPreviewRequestWordDoc(Request basicRequest) {
		request = createRequestObject(basicRequest);
		int count_Sample = Integer.valueOf(txtFld_Count_Sample.getText());
		masiveSampleValue = SampleViewFromReadDocFile.getVolumeSampleView(count_Sample);
		String date_time_reference = RequestViewAplication.GenerateStringRefDateTime(masiveSampleValue);

		Map<String, String> substitutionData = Generate_Map_For_Request_Word_Document.GenerateMapForRequestWordDocument(
				request, txtArea_list_izpitvan_pokazatel.getText(), masiveSampleValue, date_time_reference);

		DocxMainpulator.generateAndSend_Request_Docx("temp.docx",
				"Z-" + request.getRecuest_code() + "_" + request.getDate_request(), substitutionData);
	}

	private Request createRequestTamplate() {
		Request recuest = null;
		Ind_num_doc ind_num_doc = null;
		if (!choice_ind_num_doc.getSelectedItem().equals(" "))
			ind_num_doc = Ind_num_docDAO.getValueIByName(choice_ind_num_doc.getSelectedItem());

		Izpitvan_produkt izpitvan_produkt = Izpitvan_produktDAO
				.getValueIzpitvan_produktByName(choice_izpitvan_produkt.getSelectedItem());
		Razmernosti razmernosti = RazmernostiDAO.getValueRazmernostiByName(choice_Razmernost.getSelectedItem());
		Zabelejki zabelejki = ZabelejkiDAO.getValueZabelejkiByName(choice_Zab.getSelectedItem());
		Obekt_na_izpitvane_request obekt_na_izpitvane_request = Obekt_na_izpitvane_requestDAO
				.getValueObekt_na_izpitvane_requestByName(choice_obekt_na_izpitvane_request.getSelectedItem());
		int count_Sample = Integer.valueOf(txtFld_Count_Sample.getText());

		int countOld, coundNew;
		coundNew = array_O_I_R.size();
		countOld = RequestViewAplication.getStringMassiveO_I_R().size();
		System.out.println(coundNew + " " + countOld);
		if (countOld != coundNew) {
			for (int i = countOld; i < coundNew; i++) {
				Obekt_na_izpitvane_requestDAO.setValueObekt_na_izpitvane(array_O_I_R.get(i));
			}
		}
		String str_templ = RequestViewAplication.DateNaw(true);
		recuest = RequestDAO.setValueRequest("templ " + str_templ, "", chckbx_accreditation.isSelected(), section,
				xtra_module, count_Sample, txtArea_Descript_grup_Sample.getText(), "", "", ind_num_doc,
				izpitvan_produkt, razmernosti, zabelejki, curent_user, obekt_na_izpitvane_request);
		return recuest;

	}

	private void updateSample(String[][] masiveSampleValue, Request requestNew) {
		Request request = RequestDAO.getRequestFromColumnByVolume("recuest_code", requestNew.getRecuest_code());
		List<Sample> listSampelFromDBase = SampleDAO.getListSampleFromColumnByVolume("request", request);
		for (int i = 0; i < masiveSampleValue.length; i++) {
			for (Sample sampleDBAse : listSampelFromDBase) {
						
				System.out.println(sampleDBAse.getSample_code()+" //////////// "+masiveSampleValue[i][0].substring(5,masiveSampleValue[i][0].length()));
				if (sampleDBAse.getSample_code().equals(masiveSampleValue[i][0].substring(5,masiveSampleValue[i][0].length()))) {
					
					Period period = null;
					if (!masiveSampleValue[i][4].equals("")){
						period = PeriodDAO.getValuePeriodByPeriod(masiveSampleValue[i][4]);
					Obekt_na_izpitvane_sample obectNaIzpitvaneSample = Obekt_na_izpitvane_sampleDAO.getValueObekt_na_izpitvane_sampleByName(masiveSampleValue[i][1]);
					
					sampleDBAse.setObekt_na_izpitvane(obectNaIzpitvaneSample);
					sampleDBAse.setDescription_sample(masiveSampleValue[i][2]);
					sampleDBAse.setDate_time_reference(masiveSampleValue[i][3]);
					sampleDBAse.setPeriod(period);
					sampleDBAse.setGodina_period(Integer.valueOf(masiveSampleValue[i][5]));;

					SampleDAO.updateSample( sampleDBAse);
				}
			}
			}
		}
	}

	private void sampleView(Request tamplateRequest) {
//		List<Sample> listSample = SampleDAO.getInListAllValueSample();
		try {
//			DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FORMAT_DATE_TIME);
			String ref_Date_Time = txt_fid_date_time_reference.getText();
//			LocalDate data_time = LocalDate.parse(ref_Date_Time, sdf); // ref

			try {
				int count_Sample = Integer.valueOf(txtFld_Count_Sample.getText()); // broi
				// String ref_Date = (txtField_RequestCode.getText());
				final JFrame f = new JFrame();

				SampleViewFromReadDocFile sampleDescript = new SampleViewFromReadDocFile(f, tamplateRequest,
						comBox_O_I_S, ref_Date_Time, null, masiveSampleValue);

				sampleDescript.setVisible(true);
				if (!SampleViewAdd.cancelEntered()) {
					masiveSampleValue = SampleViewFromReadDocFile.getVolumeSampleView(count_Sample);
					txtArea_SampleDescription.setFont(new Font("monospaced", Font.PLAIN, 12));
					txtArea_SampleDescription.setText(RequestViewAplication.writeSampleDescript(masiveSampleValue));
					txtArea_SampleDescription.setBorder(border);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(RequestViewForReadDoc.this, "Не сте въвели брой на пробите!",
						"Грешни данни", JOptionPane.ERROR_MESSAGE);
			}
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(RequestViewForReadDoc.this, "Не сте въвели референтна дата и време!",
					"Грешни данни", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static String GenerateStringRefDateTime(Request tamplateRequest) {
		List<Sample> Sample = SampleDAO.getListSampleFromColumnByVolume("request", tamplateRequest);
		String[] masiveRefDateTime = new String[tamplateRequest.getCounts_samples()];
		int i = 0;
		for (Sample samp : Sample) {
			masiveRefDateTime[i] = samp.getDate_time_reference();
			i++;
		}
		String date_time_reference = masiveRefDateTime[0];
		if (compaRefDateTime(masiveRefDateTime)) {
			date_time_reference = "";

		}

		return date_time_reference;

	}

	private static Boolean compaRefDateTime(String[] masiveRefDateTime) {
		int count_Sample = masiveRefDateTime.length;
		Boolean comparedFlag = false;
		for (int i = 0; i < count_Sample; i++) {
			String compared = masiveRefDateTime[i];
			for (int j = i; j < count_Sample; j++) {
				if (!compared.equals(masiveRefDateTime[j])) {
					comparedFlag = true;
					return comparedFlag;
				}
			}
		}
		return comparedFlag;
	}

}
