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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Aplication.Internal_applicantDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.OtclonenieDAO;
import Aplication.PeriodDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import Aplication.ZabelejkiDAO;
import Aplication.AplicantDAO;
import Aplication.External_applicantDAO;
import Aplication.Extra_moduleDAO;
import Aplication.GlobalVariable;
import CreateWordDocProtocol.Generate_Map_For_Request_Word_Document;
import DBase_Class.External_applicant;
import DBase_Class.Extra_module;
import DBase_Class.Ind_num_doc;
import DBase_Class.Internal_applicant;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Period;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Users;
import DBase_Class.Zabelejki;
import ReadAndSaveDocFailInDBase.ResultsListInTableForReadDoc;
import WindowView.AddInChoice;
import WindowView.AddInChoiceNameFamily;
import WindowView.ChoiceL_I_P;
import WindowView.DateChoice;
import WindowView.DatePicker;
import WindowView.ExternalAplicantModuleView;
import WindowView.InternalAplicantModuleView;
import WindowView.RequestViewAplication;
import WindowView.RequestViewFunction;
import WindowView.SampleViewAdd;
import WindowView.TranscluentWindow;
import WindowViewAplication.DocxMainpulator;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.border.Border;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Choice;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class ExtraRequestViewForReadDoc extends JFrame {

	JScrollPane scrollpane;
	private JTextField txtFld_Count_Sample;
	private JTextField txtFld_date_execution;
	private JTextField txtFld_date_reception;
	private JTextField txtField_RequestCode;
	private JTextField txtFld_Date_Request;
	private String[][] masiveSampleValue = null;
	private String str_Descript_grup_Sample = "";
	private ArrayList<String> comBox_O_I_S;
	private Boolean section = true;

	private Boolean corectRequestCode = true;
	private String strAplicant = "";
	private Choice choice_Period;

	private JLabel lblError;
	private Choice choice_izpitvan_produkt;
	private Choice choice_obekt_na_izpitvane_request;
	private JTextArea txtArea_list_izpitvan_pokazatel;
	private JTextArea txtArea_dopIzis;
	private JTextArea txtArea_DopalnDogovorenosti;
	private JTextField txt_fid_date_time_reference;
	private JTextArea txtArea_SampleDescription;
	private JRadioButton rdbtn_Yes;
	private Choice choice_Razmernost;
	private Choice choice_otclon;
	private JCheckBox chckbx_accreditation;
	private JTextArea txtArea_Descript_grup_Sample;
	private JTextArea txtArea_Aplicant;
	private Users curent_user;
	private ArrayList<String> array_O_I_R;
	private Request request = null;;
	private String fondHeatText = "Tahoma";
	private Font font = new Font(fondHeatText, Font.PLAIN, 11);
	private Zabelejki zabelejki = ZabelejkiDAO.getValueZabelejkiById(5);
	private String FORMAT_DATE_TIME = GlobalVariable.getFORMAT_DATE_TIME();
	private External_applicant externalAplic = null;
	private Internal_applicant internalAplic = null;
	private String[] masive_AplicantNameFamily;
	private Choice choice_AplicantNameFamily;

	private JPanel p_1;

	public ExtraRequestViewForReadDoc(Users user, Request tamplateRequest, TranscluentWindow round, String date_time_reference) {
		super("Заявка за Извънредно Изпитване");
		setSize(850, 980);
		setLocationRelativeTo(null);
		curent_user = user;
		p_1 = new JPanel();
		p_1.setAlignmentY(0.0f);
		p_1.setAlignmentX(0.0f);
		// p.setBackground(SystemColor.controlHighlight);
		p_1.setBorder(null);
		p_1.setSize(800, 900);

		scrollpane = new JScrollPane(p_1);
		scrollpane.setName("");
		scrollpane.setBorder(null);

		GridBagLayout gbl_p_1 = new GridBagLayout();
		gbl_p_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_p_1.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_p_1.columnWidths = new int[] { 15, 190, 110, 100, 110, 160, 15 };
		gbl_p_1.rowHeights = new int[] { 181, 25, 27, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 11, 0, 0, 17, 0 };
		p_1.setLayout(gbl_p_1);

		// TODO Section_Header_Request (Титул на заявката)
		Section_Header_Request(p_1);

		Border border = UIManager.getBorder("TextField.border");

		// TODO Section_Code_Date_Request (Код и Дата на Заявката)
		Section_Code_Date_Request(p_1, border);

		// TODO Text_Area_Aplicant (Заявител)
		Text_Area_Aplicant(p_1, border);

		// TODO Button_Internal_Aplicant (Звено на ДП РАО)
		Button_Internal_Aplicant(tamplateRequest, p_1);

		// TODO Buttom_External_Aplicant (Външни клиенти)
		Button_External_Aplicant(tamplateRequest, p_1);

		// TODO choice_izpitvan_produkt (изпитван продукт)
		Section_Izpitvan_Produkt(tamplateRequest, p_1);

		// TODO Section_obekt_na_izpitvane_request (обект на изпитване)
		Section_O_I_R(tamplateRequest, p_1);

		// TODO Section_Razmernost (размерност)
		Section_Razmernost(tamplateRequest, p_1);

		// TODO CheckBox_InProtokol (извън протокол)
		CheckBox_InProtokol(tamplateRequest, p_1);

		// TODO txtArea_list_izpitvan_pokazatel (изпитван показарел)
		Section_Pokazatel(tamplateRequest, p_1, border);

		// TODO txtArea_Descript_grup_Sample (описание на групата проби)
		Text_Area_Description_Sample_Grup(tamplateRequest,p_1, border);

		// TODO Section_Date_Time_Reference (референтна дата час)
		Section_Date_Time_Reference(p_1, border, date_time_reference);

		// TODO Section_choice_Period (Периодичност)
		Section_Choice_Period(p_1);

		// TODO Section_Text_Area_Sample_Description (описание на пробите)
		Section_Text_Area_Sample_Description(p_1, border, tamplateRequest);

		// TODO Section_date_execution (срок за изпълнение)
		Section_Date_Execution(p_1, border);

		// TODO Section_RadioButton_Return_Sample (Връшане на пробите)
		Section_Return_Sample(p_1);

		// TODO Section_Text_Aria_DopDogovor (Допълнителни Договорености)
		Section_Text_Aria_DopalnDogovorenosti(p_1, border);

		// TODO Section_choice_Aplicant (Съгласувано с клиента)
		Section_Choise_AplicantNameFamily(tamplateRequest, p_1);

		// TODO Section_date_reception (дата на приемане)
		Section_Date_Reception(p_1, border);

		// TODO Section_Text_User (Приел заявката)
		Section_User(user, p_1);

		// TODO Section_choice_otklon (Отклонение)
		Section_Otclon(p_1);

		// TODO Section_Text_Aria_DopIzisk (Допълнителни Изисквания)
		Section_DopalnIziskv(p_1, border);

		// TODO Button_Save ( Запис )
		Button_Save(p_1);

		// TODO Button_Preview ( Превю )
		Button_Preview(p_1);

		// TODO btn_Results ( Резултати )
		Button_Results(tamplateRequest, p_1);

		// TODO Button_Template ( Шаблон )
		if (user.getIsAdmin()) {
			Button_Template(p_1);
		}

		// TODO Button_Cancel ( Отказ )
		Button_Cancel(p_1);

		getContentPane().add(scrollpane, BorderLayout.SOUTH);
		setVisible(true);
		round.StopWindow();

	}

	private void Section_Header_Request(final JPanel p) {
		String text1 = "<html>ДЪРЖАВНО ПРЕДПРИЯТИЕ “РАДИОАКТИВНИ ОТПАДЪЦИ“<br><br><br> ЛАБОРАТОРИЯ ЗА ИЗПИТВАНЕ<br>"
				+ "CЕКТОР РАДИОХИМИЯ<br>" + "ЛИ – РХ <br>" + "гр. Козлодуй<br>"
				+ "тел.: (0973) 7 24 01  e-mail: LI-RH_DPRAO@mail.bg</html>";
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>" + text1 + "</div></html>");
		lblNewLabel.setFont(new Font(fondHeatText, Font.BOLD, 18));
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

		JLabel lblNewLabel_1 = new JLabel("Ф 704-3");
		lblNewLabel_1.setFont(new Font(fondHeatText, Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 5;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		p.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ЗАЯВКА ЗА ИЗВЪНРЕДНО ИЗПИТВАНЕ");
		lblNewLabel_2.setFont(new Font(fondHeatText, Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridwidth = 5;
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		p.add(lblNewLabel_2, gbc_lblNewLabel_2);
	}

	private void Section_Code_Date_Request(final JPanel p, Border border) {
		lblError = new JLabel(" ");
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.gridwidth = 5;
		gbc_lblError.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 4;
		p.add(lblError, gbc_lblError);
		lblError.setFont(new Font(fondHeatText, Font.PLAIN, 9));
		lblError.setForeground(Color.red);

		JPanel panel_1 = new JPanel();
		// panel_1.setBackground(SystemColor.controlHighlight);
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
		Text_Code_Request(panel_1);

		JLabel label_2 = new JLabel("/");
		panel_1.add(label_2);

		// TODO txtFld_Date_Request (дата на заявката)
		Text_Date_Request(panel_1, border);
	}

	private void Text_Code_Request(JPanel panel_1) {
		txtField_RequestCode = new JTextField();
		txtField_RequestCode.setText((RequestDAO.getMaxRequestCode() + 1) + "");
		txtField_RequestCode.setForeground(Color.GRAY);
		txtField_RequestCode.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {

				txtField_RequestCode.setText(RequestViewFunction.checkFormatString(txtField_RequestCode.getText()));
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
	}

	private void Text_Date_Request(JPanel panel_1, Border border) {
		txtFld_Date_Request = new JTextField();
		txtFld_Date_Request.setColumns(8);

		txtFld_Date_Request.setText(RequestViewFunction.DateNaw(false));
		txtFld_Date_Request.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

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

			}
		});
		panel_1.add(txtFld_Date_Request);
	}

	private void Text_Area_Aplicant(final JPanel p, Border border) {
		JLabel lblNewLabel_4 = new JLabel("Заявител: ");

		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 6;
		p.add(lblNewLabel_4, gbc_lblNewLabel_4);

		String textStr = "<html>За изпитвания извън обема,<br>предвиден във вътрешните документи</html>";
		JLabel lblNewLabel_6 = new JLabel("<html><div style='text-align: center;'>" + textStr + "</div></html>");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 10));

		lblNewLabel_6.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.gridwidth = 2;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 6;
		p_1.add(lblNewLabel_6, gbc_lblNewLabel_6);

		JLabel label = new JLabel(
				"<html>\u0417\u0430 \u0438\u0437\u043F\u0438\u0442\u0432\u0430\u043D\u0438\u044F \u0438\u0437\u0432\u044A\u043D \u043E\u0431\u0435\u043C\u0430,<br>\u043F\u0440\u0435\u0434\u0432\u0438\u0434\u0435\u043D \u0432\u044A\u0432 \u0432\u044A\u0442\u0440\u0435\u0448\u043D\u0438\u0442\u0435 \u0434\u043E\u043A\u0443\u043C\u0435\u043D\u0442\u0438</html>");
		label.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 4;
		gbc_label.gridy = 6;
		p_1.add(label, gbc_label);

		txtArea_Aplicant = new JTextArea(strAplicant);
		txtArea_Aplicant.setFont(new Font(fondHeatText, Font.PLAIN, 11));
		txtArea_Aplicant.setEditable(false);
		txtArea_Aplicant.setBorder(border);
		GridBagConstraints gbc_txtArea_Aplicant = new GridBagConstraints();
		gbc_txtArea_Aplicant.gridwidth = 5;
		gbc_txtArea_Aplicant.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_Aplicant.fill = GridBagConstraints.BOTH;
		gbc_txtArea_Aplicant.gridx = 1;
		gbc_txtArea_Aplicant.gridy = 7;
		p.add(txtArea_Aplicant, gbc_txtArea_Aplicant);
	}

	private void Button_Internal_Aplicant(Request tamplateRequest, final JPanel p) {
		JButton btn_Internal_Aplicant = new JButton("Звено на ДП РАО");
		btn_Internal_Aplicant.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_Internal_Aplicant.setMargin(new Insets(2, 3, 2, 3));
		btn_Internal_Aplicant.setPreferredSize(new Dimension(105, 23));
		btn_Internal_Aplicant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TranscluentWindow round = new TranscluentWindow();
				Internal_applicant tamplateInternalAplic = null;
				externalAplic = null;
				if (tamplateRequest != null) {
					tamplateInternalAplic = tamplateRequest.getExtra_module().getInternal_applicant();
				}
				if (internalAplic != null) {
					tamplateInternalAplic = internalAplic;
				}
				JFrame parent = new JFrame();
				InternalAplicantModuleView intraModView = new InternalAplicantModuleView(parent, tamplateInternalAplic,
						round);

				internalAplic = intraModView.getInternal_AplicFromInternalModuleView();
				strAplicant = getStringFromIntraAplicant(internalAplic);
				txtArea_Aplicant.setText(strAplicant);
				System.out.println("--------------" + strAplicant);
			}

		});
		GridBagConstraints gbc_btn_Internal_Aplicant = new GridBagConstraints();
		gbc_btn_Internal_Aplicant.gridwidth = 2;
		gbc_btn_Internal_Aplicant.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Internal_Aplicant.gridx = 2;
		gbc_btn_Internal_Aplicant.gridy = 5;
		p.add(btn_Internal_Aplicant, gbc_btn_Internal_Aplicant);
	}

	private void Button_External_Aplicant(Request tamplateRequest, final JPanel p) {
		JButton btnExternal_Aplicant = new JButton("Външни клиенти");
		btnExternal_Aplicant.setMargin(new Insets(2, 3, 2, 3));
		btnExternal_Aplicant.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExternal_Aplicant.setPreferredSize(new Dimension(105, 23));
		btnExternal_Aplicant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TranscluentWindow round = new TranscluentWindow();
				External_applicant tamplateExternalAplic = null;
				internalAplic = null;
				if (tamplateRequest != null) {
					tamplateExternalAplic = tamplateRequest.getExtra_module().getExternal_applicant();
				}
				if (externalAplic != null) {
					tamplateExternalAplic = externalAplic;
				}
				JFrame parent = new JFrame();
				ExternalAplicantModuleView extraModView = new ExternalAplicantModuleView(parent, tamplateExternalAplic,
						round);

				externalAplic = extraModView.getExternal_AplicFromExtraModuleView();
				strAplicant = getStringFromExtAplicant(externalAplic);
				txtArea_Aplicant.setText(strAplicant);
			}

		});
		GridBagConstraints gbc_btnExternal_Aplicant = new GridBagConstraints();
		gbc_btnExternal_Aplicant.gridwidth = 2;
		gbc_btnExternal_Aplicant.insets = new Insets(0, 0, 5, 5);
		gbc_btnExternal_Aplicant.gridx = 4;
		gbc_btnExternal_Aplicant.gridy = 5;
		p.add(btnExternal_Aplicant, gbc_btnExternal_Aplicant);
	}

	private void Section_Izpitvan_Produkt(Request tamplateRequest, final JPanel p) {
		JLabel lbl_izpitvan_produkt = new JLabel("Изпитван продукт");
		GridBagConstraints gbc_lbl_izpitvan_produkt = new GridBagConstraints();
		gbc_lbl_izpitvan_produkt.anchor = GridBagConstraints.WEST;
		gbc_lbl_izpitvan_produkt.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_izpitvan_produkt.gridx = 1;
		gbc_lbl_izpitvan_produkt.gridy = 8;
		p.add(lbl_izpitvan_produkt, gbc_lbl_izpitvan_produkt);

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
	}

	private void Section_O_I_R(Request tamplateRequest, final JPanel p) {
		JLabel lbl_obekt_na_izpitvane_request = new JLabel("Обект, от който са взети пробите:");
		GridBagConstraints gbc_lbl_obekt_na_izpitvane_request = new GridBagConstraints();
		gbc_lbl_obekt_na_izpitvane_request.anchor = GridBagConstraints.WEST;
		gbc_lbl_obekt_na_izpitvane_request.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_obekt_na_izpitvane_request.gridx = 1;
		gbc_lbl_obekt_na_izpitvane_request.gridy = 9;
		p.add(lbl_obekt_na_izpitvane_request, gbc_lbl_obekt_na_izpitvane_request);

		choice_obekt_na_izpitvane_request = new Choice();
		choice_obekt_na_izpitvane_request.setFont(font);
		choice_obekt_na_izpitvane_request.setPreferredSize(new Dimension(205, 20));
		array_O_I_R = RequestViewAplication.getStringMassiveO_I_R();
		for (String string : array_O_I_R) {
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

		Button_Add_O_I_R(p);

	}

	private void Button_Add_O_I_R(final JPanel p) {
		JButton btn_add__obekt_na_izpitvane_request = new JButton("Добавяне");
		btn_add__obekt_na_izpitvane_request.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ChoiceFrame(array_O_I_R, choice_obekt_na_izpitvane_request);

			}

		});
		GridBagConstraints gbc_btn_add__obekt_na_izpitvane_request = new GridBagConstraints();
		gbc_btn_add__obekt_na_izpitvane_request.anchor = GridBagConstraints.NORTHWEST;
		gbc_btn_add__obekt_na_izpitvane_request.insets = new Insets(0, 0, 5, 5);
		gbc_btn_add__obekt_na_izpitvane_request.gridx = 5;
		gbc_btn_add__obekt_na_izpitvane_request.gridy = 9;
		p.add(btn_add__obekt_na_izpitvane_request, gbc_btn_add__obekt_na_izpitvane_request);
	}

	private void Section_Razmernost(Request tamplateRequest, final JPanel p) {
		JLabel lbl_Razmernost = new JLabel("Размерност");
		GridBagConstraints gbc_lbl_Razmernost = new GridBagConstraints();
		gbc_lbl_Razmernost.anchor = GridBagConstraints.WEST;
		gbc_lbl_Razmernost.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Razmernost.gridx = 1;
		gbc_lbl_Razmernost.gridy = 10;
		p.add(lbl_Razmernost, gbc_lbl_Razmernost);

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
	}

	private void CheckBox_InProtokol(Request tamplateRequest,final JPanel p) {
		chckbx_accreditation = new JCheckBox("Извън обхват");
		if (tamplateRequest != null) {
			chckbx_accreditation.setSelected(tamplateRequest.getAccreditation());
		}
		GridBagConstraints gbc_chckbx_accreditation = new GridBagConstraints();
		gbc_chckbx_accreditation.anchor = GridBagConstraints.EAST;
		gbc_chckbx_accreditation.insets = new Insets(0, 0, 5, 5);
		gbc_chckbx_accreditation.gridx = 5;
		gbc_chckbx_accreditation.gridy = 10;
		p.add(chckbx_accreditation, gbc_chckbx_accreditation);
	}

	private void Section_Pokazatel(Request tamplateRequest, final JPanel p, Border border) {
		JLabel lbl_list_izpitvan_pokazatel = new JLabel("Изпитван показател:");
		GridBagConstraints gbc_lbl_list_izpitvan_pokazatel = new GridBagConstraints();
		gbc_lbl_list_izpitvan_pokazatel.anchor = GridBagConstraints.WEST;
		gbc_lbl_list_izpitvan_pokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_list_izpitvan_pokazatel.gridx = 1;
		gbc_lbl_list_izpitvan_pokazatel.gridy = 11;
		p.add(lbl_list_izpitvan_pokazatel, gbc_lbl_list_izpitvan_pokazatel);

		txtArea_list_izpitvan_pokazatel = new JTextArea();
		txtArea_list_izpitvan_pokazatel.setFont(font);
		txtArea_list_izpitvan_pokazatel.setBorder(border);
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
			for (IzpitvanPokazatel izpitPokazatelFormTamplate : RequestViewFunction
					.get_List_Izpitvan_pokazatel_From_Request(tamplateRequest)) {
				list_String_I_P_Tamplate.add(izpitPokazatelFormTamplate.getPokazatel().getName_pokazatel());
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

		Button_Pokazatel(p, border);
	}

	private void Button_Pokazatel(final JPanel p, Border border) {
		JButton btn_list_izpitvan_pokazatel = new JButton("Избор на показател");
		btn_list_izpitvan_pokazatel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ChoicePokazatel(border);
			}

		});
		GridBagConstraints gbc_btn_list_izpitvan_pokazatel = new GridBagConstraints();
		gbc_btn_list_izpitvan_pokazatel.gridheight = 2;
		gbc_btn_list_izpitvan_pokazatel.anchor = GridBagConstraints.NORTHWEST;
		gbc_btn_list_izpitvan_pokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_btn_list_izpitvan_pokazatel.gridx = 5;
		gbc_btn_list_izpitvan_pokazatel.gridy = 11;
		p.add(btn_list_izpitvan_pokazatel, gbc_btn_list_izpitvan_pokazatel);
	}

	private void Text_Area_Description_Sample_Grup(Request tamplateRequest, final JPanel p, Border border) {
		
		String str ="";
		if (tamplateRequest != null) {
			str = tamplateRequest.getDescription_sample_group();
		}
		txtArea_Descript_grup_Sample = new JTextArea(str);
		txtArea_Descript_grup_Sample.setFont(font);
		txtArea_Descript_grup_Sample.setBorder(border);
		GridBagConstraints gbc_txtArea_Descript_grup_Sample = new GridBagConstraints();
		gbc_txtArea_Descript_grup_Sample.gridwidth = 5;
		gbc_txtArea_Descript_grup_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_Descript_grup_Sample.fill = GridBagConstraints.BOTH;
		gbc_txtArea_Descript_grup_Sample.gridx = 1;
		gbc_txtArea_Descript_grup_Sample.gridy = 14;
		p.add(txtArea_Descript_grup_Sample, gbc_txtArea_Descript_grup_Sample);
	}

	private void Section_Date_Time_Reference(final JPanel p, Border border,String date_time_reference) {

		JLabel lbl_date_time_reference = new JLabel("Референтна дата (средата на периода)");
		GridBagConstraints gbc_lbl_date_time_reference = new GridBagConstraints();
		gbc_lbl_date_time_reference.gridwidth = 2;
		gbc_lbl_date_time_reference.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_time_reference.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_time_reference.gridx = 1;
		gbc_lbl_date_time_reference.gridy = 12;
		p.add(lbl_date_time_reference, gbc_lbl_date_time_reference);

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

		Button_Date_Time_Reference(p, border);
	}

	private void Button_Date_Time_Reference(final JPanel p, Border border) {
		JButton btn_date_time_reference = new JButton("Избор на дата");
		btn_date_time_reference.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					final JFrame f = new JFrame();
					DateChoice date_time_reference = new DateChoice(f, txt_fid_date_time_reference.getText());
					date_time_reference.setVisible(true);
					if (choice_Period.getSelectedItem().equals("")) {
						str_Descript_grup_Sample = "";
						str_Descript_grup_Sample = DateChoice.get_str_period_sample();
					} else {
						if (DateChoice.get_str_period_sample().equals("")) {
							str_Descript_grup_Sample = "";
							str_Descript_grup_Sample = "за " + choice_Period.getSelectedItem();
						} else {
							str_Descript_grup_Sample = "";
							str_Descript_grup_Sample = DateChoice.get_str_period_sample() + "\nза "
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
		GridBagConstraints gbc_btn_date_time_reference = new GridBagConstraints();
		gbc_btn_date_time_reference.anchor = GridBagConstraints.WEST;
		gbc_btn_date_time_reference.insets = new Insets(0, 0, 5, 5);
		gbc_btn_date_time_reference.gridx = 4;
		gbc_btn_date_time_reference.gridy = 12;
		p.add(btn_date_time_reference, gbc_btn_date_time_reference);
	}

	private void Section_Choice_Period(final JPanel p) {

		JLabel lbl_Period = new JLabel("Периодичност");
		GridBagConstraints gbc_lbl_Period = new GridBagConstraints();
		gbc_lbl_Period.anchor = GridBagConstraints.EAST;
		gbc_lbl_Period.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Period.gridx = 1;
		gbc_lbl_Period.gridy = 13;
		p.add(lbl_Period, gbc_lbl_Period);
		
		choice_Period = new Choice();
		choice_Period.setFont(font);
		choice_Period.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints gbc_choice_Period = new GridBagConstraints();
		gbc_choice_Period.anchor = GridBagConstraints.WEST;
		gbc_choice_Period.insets = new Insets(0, 0, 5, 5);
		gbc_choice_Period.gridx = 2;
		gbc_choice_Period.gridy = 13;
		p.add(choice_Period, gbc_choice_Period);
		
		RequestViewFunction.setDataIn_Choice_Period(choice_Period);

		

		// Add item listener choice_Period
		choice_Period.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				str_Descript_grup_Sample =   RequestViewFunction.generateTxtInDescriptGrupSample( choice_Period,txtFld_Count_Sample.getText());
				txtArea_Descript_grup_Sample.setText(str_Descript_grup_Sample);


			}
		});
	}

	private void Section_Text_Count_Sample(final JPanel p, Border border, Request tamplateRequest) {

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

		txtFld_Count_Sample = new JTextField();
		txtFld_Count_Sample.setText(tamplateRequest.getCounts_samples() + "");
		txtFld_Count_Sample.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {
				System.out.println("Data = " + txtFld_Count_Sample.getText());
				txtFld_Count_Sample.setText(RequestViewFunction.checkFormatString(txtFld_Count_Sample.getText()));
				// String str = txtFld_Count_Sample.getText();

				if (RequestViewAplication.checkMaxVolume(txtFld_Count_Sample.getText(), 1, 20)) {
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
	}

	private void Section_Text_Area_Sample_Description(final JPanel p, Border border, Request tamplateRequest) {

		JLabel lbl_SampleDescription = new JLabel("Описание на пробите ");
		GridBagConstraints gbc_lbl_SampleDescription = new GridBagConstraints();
		gbc_lbl_SampleDescription.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lbl_SampleDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_SampleDescription.gridx = 1;
		gbc_lbl_SampleDescription.gridy = 16;
		p.add(lbl_SampleDescription, gbc_lbl_SampleDescription);

		// TODO txtFld_Count_Sample (брой на пробите)
		Section_Text_Count_Sample(p, border, tamplateRequest);

		Button_Sample_Description(p, border,tamplateRequest);

		txtArea_SampleDescription = new JTextArea();
		txtArea_SampleDescription.setFont(font);
		txtArea_SampleDescription.setBorder(border);
		GridBagConstraints gbc_txtArea_SampleDescription = new GridBagConstraints();
		gbc_txtArea_SampleDescription.gridwidth = 5;
		gbc_txtArea_SampleDescription.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_SampleDescription.fill = GridBagConstraints.BOTH;
		gbc_txtArea_SampleDescription.gridx = 1;
		gbc_txtArea_SampleDescription.gridy = 17;
		p.add(txtArea_SampleDescription, gbc_txtArea_SampleDescription);
		txtArea_SampleDescription.setEditable(false);

	}

	private void Button_Sample_Description(final JPanel p, Border border, Request tamplateRequest) {
		JButton btn_SampleDescription = new JButton("Описание на пробите");
		btn_SampleDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comBox_O_I_S = RequestViewAplication.getStringMassiveO_I_S();
				try {
					int requestCode = Integer.valueOf(txtField_RequestCode.getText()); // kod
					try {
						DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FORMAT_DATE_TIME);
						String ref_Date_Time = txt_fid_date_time_reference.getText();
						LocalDate data_time = LocalDate.parse(ref_Date_Time, sdf); // ref
						String period = choice_Period.getSelectedItem();
						try {
							int count_Sample = Integer.valueOf(txtFld_Count_Sample.getText()); // broi
							// String ref_Date =
							// (txtField_RequestCode.getText());
							final JFrame f = new JFrame();
							SampleViewFromReadDocFile sampleDescript =  new SampleViewFromReadDocFile(f, tamplateRequest,
									comBox_O_I_S, ref_Date_Time, null, masiveSampleValue);
//							sampleDescript = new SampleViewAdd(f, count_Sample, requestCode, comBox_O_I_S,
//									ref_Date_Time, period, masiveSampleValue);

							sampleDescript.setVisible(true);
							if (!SampleViewAdd.cancelEntered()) {
								masiveSampleValue = SampleViewFromReadDocFile.getVolumeSampleView(count_Sample);
								txtArea_SampleDescription.setFont(new Font("monospaced", Font.PLAIN, 12));
								txtArea_SampleDescription.setText(RequestViewAplication.writeSampleDescript(masiveSampleValue));
								txtArea_SampleDescription.setBorder(border);
							}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(ExtraRequestViewForReadDoc.this, "Не сте въвели брой на пробите!",
									"Грешни данни", JOptionPane.ERROR_MESSAGE);
						}
					} catch (DateTimeParseException e) {
						JOptionPane.showMessageDialog(ExtraRequestViewForReadDoc.this, "Не сте въвели референтна дата и време!",
								"Грешни данни", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(ExtraRequestViewForReadDoc.this, "Не сте въвели код на пробата!",
							"Грешни данни", JOptionPane.ERROR_MESSAGE);
				}

			}

		});
		GridBagConstraints gbc_btn_SampleDescription = new GridBagConstraints();
		gbc_btn_SampleDescription.gridheight = 2;
		gbc_btn_SampleDescription.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btn_SampleDescription.insets = new Insets(0, 0, 5, 5);
		gbc_btn_SampleDescription.gridx = 5;
		gbc_btn_SampleDescription.gridy = 15;
		p.add(btn_SampleDescription, gbc_btn_SampleDescription);
	}

	private void Section_Date_Execution(final JPanel p, Border border) {
		JLabel lbl_date_execution = new JLabel("Срок за изпълнение:");
		GridBagConstraints gbc_lbl_date_execution = new GridBagConstraints();
		gbc_lbl_date_execution.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_execution.gridwidth = 2;
		gbc_lbl_date_execution.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_execution.gridx = 1;
		gbc_lbl_date_execution.gridy = 18;
		p.add(lbl_date_execution, gbc_lbl_date_execution);

		txtFld_date_execution = new JTextField("");
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
		gbc_txtFld_date_execution.gridy = 18;
		p.add(txtFld_date_execution, gbc_txtFld_date_execution);

		JButton btn_date_execution = Button_Date_Execution(border);
		GridBagConstraints gbc_btn_date_execution = new GridBagConstraints();
		gbc_btn_date_execution.anchor = GridBagConstraints.WEST;
		gbc_btn_date_execution.gridwidth = 2;
		gbc_btn_date_execution.insets = new Insets(0, 0, 5, 5);
		gbc_btn_date_execution.gridx = 4;
		gbc_btn_date_execution.gridy = 18;
		p.add(btn_date_execution, gbc_btn_date_execution);
	}

	private JButton Button_Date_Execution(Border border) {
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
		return btn_date_execution;
	}

	private void Section_Return_Sample(final JPanel p) {
		JLabel lblNewLabel_5 = new JLabel("Връщане на пробите");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 19;
		p.add(lblNewLabel_5, gbc_lblNewLabel_5);

		rdbtn_Yes = new JRadioButton("Да");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.anchor = GridBagConstraints.EAST;
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 3;
		gbc_rdbtnNewRadioButton.gridy = 19;
		p.add(rdbtn_Yes, gbc_rdbtnNewRadioButton);
		rdbtn_Yes.setSelected(true);

		JRadioButton rdbtn_No = new JRadioButton("Не");
		GridBagConstraints gbc_radioButton = new GridBagConstraints();
		gbc_radioButton.anchor = GridBagConstraints.EAST;
		gbc_radioButton.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton.gridx = 4;
		gbc_radioButton.gridy = 19;
		p.add(rdbtn_No, gbc_radioButton);

		rdbtn_Yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtn_Yes.isSelected()) {
					rdbtn_No.setSelected(false);
				} else {
					rdbtn_No.setSelected(true);
				}
			}

		});

		rdbtn_No.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtn_No.isSelected()) {
					rdbtn_Yes.setSelected(false);
				} else {
					rdbtn_Yes.setSelected(true);
				}
			}

		});
	}

	private void Section_Text_Aria_DopalnDogovorenosti(final JPanel p, Border border) {
		JLabel lbl_DopalnDogovorenosti = new JLabel("Допълнителни договорености:");
		GridBagConstraints gbc_lbl_DopalnDogovorenosti = new GridBagConstraints();
		gbc_lbl_DopalnDogovorenosti.anchor = GridBagConstraints.WEST;
		gbc_lbl_DopalnDogovorenosti.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_DopalnDogovorenosti.gridx = 1;
		gbc_lbl_DopalnDogovorenosti.gridy = 24;
		p.add(lbl_DopalnDogovorenosti, gbc_lbl_DopalnDogovorenosti);

		txtArea_DopalnDogovorenosti = new JTextArea("");
		txtArea_DopalnDogovorenosti.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtArea_DopalnDogovorenosti.setBorder(border);
		GridBagConstraints gbc_txtArea_DopalnDogovorenosti = new GridBagConstraints();
		gbc_txtArea_DopalnDogovorenosti.gridwidth = 5;
		gbc_txtArea_DopalnDogovorenosti.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_DopalnDogovorenosti.fill = GridBagConstraints.BOTH;
		gbc_txtArea_DopalnDogovorenosti.gridx = 1;
		gbc_txtArea_DopalnDogovorenosti.gridy = 25;
		p.add(txtArea_DopalnDogovorenosti, gbc_txtArea_DopalnDogovorenosti);
	}

	private void Section_Choise_AplicantNameFamily(Request tamplateRequest, final JPanel p) {
		JLabel lbl_AplicantNameFamily = new JLabel("Съгласувано с клиента:");
		GridBagConstraints gbc_lbl_AplicantNameFamily = new GridBagConstraints();
		gbc_lbl_AplicantNameFamily.anchor = GridBagConstraints.EAST;
		gbc_lbl_AplicantNameFamily.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_AplicantNameFamily.gridx = 1;
		gbc_lbl_AplicantNameFamily.gridy = 26;
		p.add(lbl_AplicantNameFamily, gbc_lbl_AplicantNameFamily);

		choice_AplicantNameFamily = new Choice();
		choice_AplicantNameFamily.setPreferredSize(new Dimension(60, 20));
		choice_AplicantNameFamily.setFont(new Font("Tahoma", Font.PLAIN, 11));

		masive_AplicantNameFamily = AplicantDAO.getMasiveStringAllName_FamilyAplicant();
		ArrayList<String> array_AplicantNameFamily = new ArrayList<String>();
		for (String string : masive_AplicantNameFamily) {
			choice_AplicantNameFamily.add(string);
			array_AplicantNameFamily.add(string);
		}
		if (tamplateRequest != null) {
			
			String str = "";
			choice_AplicantNameFamily.select(str);
		}

		GridBagConstraints gbc_choice_AplicantNameFamily = new GridBagConstraints();
		gbc_choice_AplicantNameFamily.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_AplicantNameFamily.gridwidth = 2;
		gbc_choice_AplicantNameFamily.insets = new Insets(0, 0, 5, 5);
		gbc_choice_AplicantNameFamily.gridx = 2;
		gbc_choice_AplicantNameFamily.gridy = 26;
		p.add(choice_AplicantNameFamily, gbc_choice_AplicantNameFamily);

		Button_Add_AplicantNameFamily(p, array_AplicantNameFamily);

	}

	private void Button_Add_AplicantNameFamily(final JPanel p, ArrayList<String> array_AplicantNameFamily) {
		JButton button = new JButton("Добавяне");

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChoiceFrameNameFamily(array_AplicantNameFamily, choice_AplicantNameFamily);

			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTHWEST;
		gbc_button.gridheight = 2;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 4;
		gbc_button.gridy = 26;
		p.add(button, gbc_button);
	}

	private void Section_Date_Reception(final JPanel p, Border border) {
		JLabel lbl_date_reception = new JLabel("Дата на приемане:");
		GridBagConstraints gbc_lbl_date_reception = new GridBagConstraints();
		gbc_lbl_date_reception.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_reception.gridwidth = 2;
		gbc_lbl_date_reception.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_reception.gridx = 1;
		gbc_lbl_date_reception.gridy = 28;
		p.add(lbl_date_reception, gbc_lbl_date_reception);

		// TODO txtFld_date_reception (дата на приемане)
		txtFld_date_reception = new JTextField(RequestViewFunction.DateNaw(false));
		txtFld_date_reception.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {

				if (DatePicker.incorrectDate(txtFld_date_reception.getText(), false)) {
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
		GridBagConstraints gbc_txtFld_date_reception = new GridBagConstraints();
		gbc_txtFld_date_reception.insets = new Insets(0, 0, 5, 5);
		gbc_txtFld_date_reception.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld_date_reception.gridx = 3;
		gbc_txtFld_date_reception.gridy = 28;
		p.add(txtFld_date_reception, gbc_txtFld_date_reception);

		JButton btn_date_reception = Button_Date_Rception(border);

		GridBagConstraints gbc_btn_date_reception = new GridBagConstraints();
		gbc_btn_date_reception.anchor = GridBagConstraints.WEST;
		gbc_btn_date_reception.gridwidth = 2;
		gbc_btn_date_reception.insets = new Insets(0, 0, 5, 5);
		gbc_btn_date_reception.gridx = 4;
		gbc_btn_date_reception.gridy = 28;
		p.add(btn_date_reception, gbc_btn_date_reception);
	}

	private JButton Button_Date_Rception(Border border) {
		JButton btn_date_reception = new JButton("Избор на дата");
		btn_date_reception.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFrame f = new JFrame();
				DatePicker dPicer = new DatePicker(f, false, txtFld_date_reception.getText());
				txtFld_date_reception.setText(dPicer.setPickedDate(false));
				String textRefDate = "";
				textRefDate = txtFld_date_reception.getText();
				if (DatePicker.incorrectDate(textRefDate, false))
					txtFld_date_reception.setForeground(Color.RED);
				else {
					txtFld_date_reception.setForeground(Color.BLACK);
					txtFld_date_reception.setBorder(border);
				}

				txtFld_date_reception.setText(textRefDate);
				System.out.println("Data in requestViewFild = " + txtFld_date_reception.getText().trim());
			}
		});
		return btn_date_reception;
	}

	private void Section_User(Users user, final JPanel p) {
		JLabel lblNewLabel_3 = new JLabel("Приел:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 29;
		p.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel lbl_User = new JLabel(user.getName_users() + " " + user.getFamily_users());
		GridBagConstraints gbc_lbl_User = new GridBagConstraints();
		gbc_lbl_User.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_User.gridx = 2;
		gbc_lbl_User.gridy = 29;
		p.add(lbl_User, gbc_lbl_User);
	}

	private void Section_Otclon(final JPanel p) {
		JLabel lbl_otclon = new JLabel("Отклонение от условията на метода:");
		GridBagConstraints gbc_lbl_otclon = new GridBagConstraints();
		gbc_lbl_otclon.anchor = GridBagConstraints.WEST;
		gbc_lbl_otclon.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_otclon.gridx = 1;
		gbc_lbl_otclon.gridy = 20;
		p.add(lbl_otclon, gbc_lbl_otclon);

		choice_otclon = new Choice();
		choice_otclon.setFont(font);
		choice_otclon.setPreferredSize(new Dimension(4, 18));

		GridBagConstraints gbc_choice_otclon = new GridBagConstraints();
		gbc_choice_otclon.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_otclon.gridwidth = 4;
		gbc_choice_otclon.insets = new Insets(0, 0, 5, 5);
		gbc_choice_otclon.gridx = 1;
		gbc_choice_otclon.gridy = 21;
		p.add(choice_otclon, gbc_choice_otclon);

		ArrayList<String> arrayOtclon = RequestViewAplication.getStringOtclon();
		for (String string : arrayOtclon) {
			choice_otclon.add(string);
		}
		JButton btn_add_otclon = Button_Add_Otclon(arrayOtclon);

		GridBagConstraints gbc_btn_add_otclon = new GridBagConstraints();
		gbc_btn_add_otclon.anchor = GridBagConstraints.NORTHWEST;
		gbc_btn_add_otclon.gridheight = 2;
		gbc_btn_add_otclon.insets = new Insets(0, 0, 5, 5);
		gbc_btn_add_otclon.gridx = 5;
		gbc_btn_add_otclon.gridy = 21;
		p.add(btn_add_otclon, gbc_btn_add_otclon);
	}

	private JButton Button_Add_Otclon(ArrayList<String> arrayOtclon) {
		JButton btn_add_otclon = new JButton("Добавяне");
		btn_add_otclon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChoiceFrame(arrayOtclon, choice_otclon);
			}
		});
		return btn_add_otclon;
	}

	private void Section_DopalnIziskv(final JPanel p, Border border) {
		JLabel lbl_note = new JLabel("Допълнителни изисквания на клиента:");
		GridBagConstraints gbc_lbl_note = new GridBagConstraints();
		gbc_lbl_note.anchor = GridBagConstraints.WEST;
		gbc_lbl_note.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_note.gridx = 1;
		gbc_lbl_note.gridy = 22;
		p.add(lbl_note, gbc_lbl_note);

		txtArea_dopIzis = new JTextArea(" ");
		txtArea_dopIzis.setFont(font);
		txtArea_dopIzis.setBorder(border);
		GridBagConstraints gbc_txtArea_dopIzis = new GridBagConstraints();
		gbc_txtArea_dopIzis.gridwidth = 5;
		gbc_txtArea_dopIzis.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_dopIzis.fill = GridBagConstraints.BOTH;
		gbc_txtArea_dopIzis.gridx = 1;
		gbc_txtArea_dopIzis.gridy = 23;
		p.add(txtArea_dopIzis, gbc_txtArea_dopIzis);
	}

	private void Button_Cancel(final JPanel p) {
		JButton btn_save = new JButton("Отказ");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btn_save.setPreferredSize(new Dimension(80, 23));
		GridBagConstraints gbc_btn_save = new GridBagConstraints();
		gbc_btn_save.anchor = GridBagConstraints.WEST;
		gbc_btn_save.insets = new Insets(0, 0, 0, 5);
		gbc_btn_save.gridx = 5;
		gbc_btn_save.gridy = 31;
		p.add(btn_save, gbc_btn_save);
	}

	private void Button_Save(final JPanel p) {
		JButton btn_save = new JButton("Запис");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (checkRequest()) {
					AplicantDAO.saveValueAplicantWitchCheck(choice_AplicantNameFamily.getSelectedItem());
					request = createAndSaveRequest();
					saveSample();
					SaveIzpitvanPokazatel();
					setVisible(false);
				}
			}
		});
		btn_save.setPreferredSize(new Dimension(80, 23));
		GridBagConstraints gbc_btn_save = new GridBagConstraints();
		gbc_btn_save.anchor = GridBagConstraints.EAST;
		gbc_btn_save.insets = new Insets(0, 0, 0, 5);
		gbc_btn_save.gridx = 4;
		gbc_btn_save.gridy = 31;
		p.add(btn_save, gbc_btn_save);
	}

	private void Button_Preview(final JPanel p) {
		JButton btn_Preview = new JButton("Превю");
		btn_Preview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkRequest()) {
					createPreviewRequestWordDoc();
				}

			}

		});
		btn_Preview.setPreferredSize(new Dimension(80, 23));
		GridBagConstraints gbc_btn_Preview = new GridBagConstraints();
		gbc_btn_Preview.anchor = GridBagConstraints.WEST;
		gbc_btn_Preview.insets = new Insets(0, 0, 0, 5);
		gbc_btn_Preview.gridx = 3;
		gbc_btn_Preview.gridy = 31;
		p.add(btn_Preview, gbc_btn_Preview);
	}

	private void Button_Results(Request tamplateRequest, final JPanel p) {
		JButton btnNewButton = new JButton("Резултати");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultsListInTableForReadDoc.DrawTableWithEnableResultsList(tamplateRequest);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 31;
		p.add(btnNewButton, gbc_btnNewButton);
	}

	private void Button_Template(final JPanel p) {
		JButton btn_Template = new JButton("Шаблон");
		btn_Template.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkRequest()) {

					AplicantDAO.saveValueAplicantWitchCheck(choice_AplicantNameFamily.getSelectedItem());

					request = createAndSaveRequestTamplate();
					saveSample();
					SaveIzpitvanPokazatel();
					setVisible(false);
				}

			}
		});
		btn_Template.setPreferredSize(new Dimension(80, 23));
		GridBagConstraints gbc_btn_Template = new GridBagConstraints();
		gbc_btn_Template.anchor = GridBagConstraints.EAST;
		gbc_btn_Template.insets = new Insets(0, 0, 0, 5);
		gbc_btn_Template.gridx = 2;
		gbc_btn_Template.gridy = 31;
		p.add(btn_Template, gbc_btn_Template);
	}

	private Boolean checkRequest() {

		Boolean saveCheck = true;
		String str_RequestCode = "";
		if (RequestDAO.checkRequestCode(txtField_RequestCode.getText())) {
			txtField_RequestCode.setForeground(Color.red);
			lblError.setText("Заявка с този номер вече съществува");
			corectRequestCode = false;
		} else {
			txtField_RequestCode.setForeground(Color.BLACK);

			txtField_RequestCode.setBorder(new LineBorder(Color.BLACK));
			lblError.setText(" ");
			corectRequestCode = true;

		}
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
			JOptionPane.showMessageDialog(ExtraRequestViewForReadDoc.this, str, "Грешни данни за следните полета:",
					JOptionPane.ERROR_MESSAGE);
		}

		return saveCheck;
	}

	private void SaveIzpitvanPokazatel() {
		ArrayList<List_izpitvan_pokazatel> list_izpitvan_pokazatel = ChoiceL_I_P.getListI_PFormChoiceL_P();
		for (List_izpitvan_pokazatel l_I_P : list_izpitvan_pokazatel) {
			IzpitvanPokazatelDAO.setValueIzpitvanPokazatel(l_I_P, request, null);
		}
	}

	private Request createRequestObject() {

		Ind_num_doc ind_num_doc = null;

		Izpitvan_produkt izpitvan_produkt = Izpitvan_produktDAO
				.getValueIzpitvan_produktByName(choice_izpitvan_produkt.getSelectedItem());

		Razmernosti razmernosti = RazmernostiDAO.getValueRazmernostiByName(choice_Razmernost.getSelectedItem());

		Obekt_na_izpitvane_request obekt_na_izpitvane_request = Obekt_na_izpitvane_requestDAO
				.getValueObekt_na_izpitvane_requestByName(choice_obekt_na_izpitvane_request.getSelectedItem());

		int count_Sample = Integer.valueOf(txtFld_Count_Sample.getText());

		Extra_module extra_mod = Extra_moduleDAO.saveAndGetExtra_module(createExtraModule());

		Request recuest = RequestDAO.setValueRequest(txtField_RequestCode.getText(), txtFld_Date_Request.getText(),
				chckbx_accreditation.isSelected(), section, extra_mod, count_Sample,
				txtArea_Descript_grup_Sample.getText(), txtFld_date_reception.getText(),
				txtFld_date_execution.getText(), ind_num_doc, izpitvan_produkt, razmernosti, zabelejki, curent_user,
				obekt_na_izpitvane_request);

		return recuest;

	}

	private Request createAndSaveRequest() {
		Request recuest = createRequestObject();

		RequestDAO.saveRequestFromRequest(recuest);
		return recuest;

	}

	private Extra_module createExtraModule() {
		externalAplic = GetAndSaveExternalAplicant();
		internalAplic = GetAndSaveInternalAplicant();
		Extra_module extra_module = new Extra_module();
		extra_module.setAdditional_arrangements(txtArea_dopIzis.getText());
		extra_module.setAdditional_requirements(txtArea_DopalnDogovorenosti.getText());
		extra_module.setReturn_samples(rdbtn_Yes.isSelected());
		extra_module.setAplicant(AplicantDAO.getAplicantByNameFamily(choice_AplicantNameFamily.getSelectedItem()));
		extra_module.setOtclonenie(OtclonenieDAO.getValueOtclonByName(choice_otclon.getSelectedItem()));
		extra_module.setExternal_applicant(externalAplic);
		extra_module.setInternal_applicant(internalAplic);
		return extra_module;
	}

	private void createPreviewRequestWordDoc() {
		request = createRequestObject();
		int count_Sample = Integer.valueOf(txtFld_Count_Sample.getText());
		masiveSampleValue = SampleViewAdd.getVolumeSampleView(count_Sample);
		String date_time_reference = RequestViewFunction.GenerateStringRefDateTimeFromMasiveSample(masiveSampleValue);

		Map<String, String> substitutionData = Generate_Map_For_Request_Word_Document.GenerateMapForRequestWordDocument(
				request, txtArea_list_izpitvan_pokazatel.getText(), masiveSampleValue, date_time_reference);

		DocxMainpulator.generateAndSend_Request_Docx("temp.docx",
				"Z-" + request.getRecuest_code() + "_" + request.getDate_request(), substitutionData);
	}

	private Request createAndSaveRequestTamplate() {
		Request recuest = createRequestObject();
		String str_templ = RequestViewFunction.DateNaw(true);

		recuest.setRecuest_code("templ" + str_templ);
		recuest.setDate_request("");
		recuest.setDate_reception("");
		recuest.setDate_execution("");
		recuest.setUsers(null);
		RequestDAO.saveRequestFromRequest(recuest);
		return recuest;

	}

	private void saveSample() {
		int count_Sample = Integer.valueOf(txtFld_Count_Sample.getText());
		masiveSampleValue = SampleViewAdd.getVolumeSampleView(count_Sample);
		for (int i = 0; i < masiveSampleValue.length; i++) {
			Period period = null;
			if (!masiveSampleValue[i][4].equals(""))
				period = PeriodDAO.getValuePeriodByPeriod(masiveSampleValue[i][4]);
			Obekt_na_izpitvane_sample obectNaIzpitvaneSample = Obekt_na_izpitvane_sampleDAO
					.getValueObekt_na_izpitvane_sampleByName(masiveSampleValue[i][1]);

			SampleDAO.setValueSample(masiveSampleValue[i][0].substring(5, masiveSampleValue[i][0].length()),
					masiveSampleValue[i][2], masiveSampleValue[i][3], request, obectNaIzpitvaneSample, period,
					Integer.valueOf(masiveSampleValue[i][5]));

		}
	}

	private String getStringFromExtAplicant(External_applicant externalAplic) {
		String str = "";
		if (externalAplic != null) {
			str = "Заявител:\n Организация / Име:" + externalAplic.getExternal_applicant_name() + " \n Адрес: "
					+ externalAplic.getExternal_applicant_address() + "\n Тел.: "
					+ externalAplic.getExternal_applicant_telephone() + " \n Договор №: "
					+ externalAplic.getExternal_applicant_contract_number();
		}
		return str;

	}

	private String getStringFromIntraAplicant(Internal_applicant internallAplic) {
		String str = "";
		if (internallAplic != null) {
			str = "Заявител:\n Звено от ДП РАО:" + internallAplic.getInternal_applicant_organization() + " \n Адрес: "
					+ internallAplic.getInternal_applicant_address() + "\n Тел.: "
					+ internallAplic.getInternal_applicant_telephone();
		}
		return str;

	}

	private void ChoiceFrame(ArrayList<String> array_list, Choice choice_obekt) {

		Boolean fl = false;
		final JFrame f = new JFrame();
		new AddInChoice(f, array_list, choice_obekt.getSelectedItem());
		String str = AddInChoice.getChoice();
		for (String string : array_list) {
			if (str.equals(string))
				fl = true;
		}
		if (!fl) {
			array_list.add(str);
			choice_obekt.add(str);
		}
		choice_obekt.select(str);

	}

	private Boolean ChoiceFrameNameFamily(ArrayList<String> array_list, Choice choice_obekt) {
		Boolean fl = false;
		final JFrame f = new JFrame();
		new AddInChoiceNameFamily(f, array_list, choice_obekt.getSelectedItem());
		String str = AddInChoiceNameFamily.getChoice();
		for (String string : array_list) {
			if (str.equals(string))
				fl = true;
		}
		if (!fl) {
			array_list.add(str);
			choice_obekt.add(str);
		}
		choice_obekt.select(str);
		return fl;

	}

	private void ChoicePokazatel(Border border) {
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
		if (cout_str > 0) {
			txtArea_list_izpitvan_pokazatel.setText(str.substring(0, cout_str - 1));
		}
	}

	private External_applicant GetAndSaveExternalAplicant() {
		if (externalAplic != null) {
			int id_externalAplic = External_applicantDAO.setValueExternal_applicantWhithCheck(externalAplic);
			if (id_externalAplic < 0) {
				External_applicantDAO.setValueExternal_applicant(externalAplic);
				System.out.println("externalAplic.getId_external  " + externalAplic.getId_external_applicant());
			} else {
				System.out.println("id_externalAplic  " + id_externalAplic);
			}
		}
		return externalAplic;
	}

	private Internal_applicant GetAndSaveInternalAplicant() {
		if (internalAplic != null) {
			int id_internalAplic = Internal_applicantDAO.setValueInternal_applicantWhithCheck(internalAplic);
			if (id_internalAplic < 0) {
				Internal_applicantDAO.setValueInternal_applicant(internalAplic);
				System.out.println("internalAplic.getId_internal  " + internalAplic.getId_internal_applicant());
			} else {
				internalAplic.setId_internal_applicant(id_internalAplic);
				System.out.println("id_internalAplic  " + id_internalAplic);
			}
		}
		return internalAplic;
	}

}
