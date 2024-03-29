package WindowView;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Aplication.Ind_num_docDAO;
import Aplication.Internal_applicantDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.DopalnIziskvDAO;
import Aplication.PeriodDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.Request_To_ObektNaIzpitvaneRequestDAO;
import Aplication.SampleDAO;
import Aplication.ZabelejkiDAO;
import CreateWordDocProtocol.GenerateDocProtokol;
import Aplication.AplicantDAO;
import Aplication.External_applicantDAO;
import Aplication.Extra_moduleDAO;
import DBase_Class.Aplicant;
import DBase_Class.DopalnIziskv;
import DBase_Class.External_applicant;
import DBase_Class.Extra_module;
import DBase_Class.Ind_num_doc;
import DBase_Class.Internal_applicant;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Period;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Request_To_ObektNaIzpitvaneRequest;
import DBase_Class.Users;
import DBase_Class.Zabelejki;
import GlobalVariable.GlobalFormatDate;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import GlobalVariable.ResourceLoader;
import Table_Request.Table_RequestToObektNaIzp;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.LineBorder;

import AddResultViewFunction.AddResultViewMetods;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;

import javax.swing.border.Border;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Choice;
import javax.swing.JButton;
import java.awt.Toolkit;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class RequestView extends JDialog {

	private JTextField txtFld_Count_Sample;
	private JTextField txtFld_date_execution;
	private JTextField txtFld_date_period_reception;
	private JTextField txtField_RequestCode;
	private JTextField txtFld_Date_Request;
	private JTextField txt_fid_date_time_reference;

	private JLabel lblRequest_Title;
	private JLabel lblText_Formuliar;
	private JLabel lblError;

	private JTextArea txtArea_list_izpitvan_pokazatel;
	private JTextArea txtArea_SampleDescription;
	private JTextArea txtArea_Descript_grup_Sample;
	private JTextArea txtArea_Aplicant;

	private JTextArea txtArea_DopalnDogovorenosti;
	private Choice choice_izpitvan_produkt;
	private JTextArea txtArea_obekt_na_izpitvane_request;
	private Choice choice_ind_num_doc;
	private Choice choice_Razmernost;
	private Choice choice_Period;
	private Choice choice_Zab;
	private Choice choice_dopIzis;
	private JRadioButton rdbtn_Yes;

	private JCheckBox chckbx_accreditation;

	private String strAplicant = "";
	private String str_Descript_grup_Sample = "";
	private String original_Description_sample_group = "";
	private ArrayList<String> comBox_O_I_S;
	private List<String> listStringOfRequest_To_ObektNaIzpitvaneRequest = null;
	private ArrayList<String> bsic_listObektNaIzpit = null;
	// private ;
	private String[][] masiveSampleValue = null;
	private Boolean corectRequestCode = true;
	private Boolean section = true;
	private Boolean incorrect_date_period_Reception = false;
	private Boolean forEdit = false;
	
	private Users user;
	private Request request = null;
	private Request tamplateRequest = null;
	private String fondHeatText = "Tahoma";
	private Font font = new Font("Tahoma", Font.PLAIN, 11);
	private String FORMAT_DATE_TIME = GlobalFormatDate.getFORMAT_DATE_TIME();
	private External_applicant externalAplic = null;
	private Internal_applicant internalAplic = null;
	private String[] masive_AplicantNameFamily;
	private Choice choice_AplicantNameFamily;
	private JPanel basicPanel;
	private Border border;

	public RequestView(JFrame parent, Users curent_user, Request inkoming_TamplateRequest, TranscluentWindow round, Boolean curentForEdit) {
		super(parent, ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("RequestView_TitleFrame"), true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height - 80;
		// int screenWidth = screenSize.width;
		setSize(870, screenHeight);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JScrollPane scrollpane = new JScrollPane();

		getContentPane().add(scrollpane, BorderLayout.NORTH);
		user = curent_user ;
		tamplateRequest = inkoming_TamplateRequest;
		forEdit = curentForEdit;
		
		basicPanel = new JPanel();
		scrollpane.setViewportView(basicPanel);
		basicPanel.setAlignmentY(JPanel.TOP_ALIGNMENT);
		GridBagLayout gbl_p_1 = new GridBagLayout();
		gbl_p_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_p_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_p_1.columnWidths = new int[] { 11, 175, 110, 108, 110, 175, 11 };
		gbl_p_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		basicPanel.setLayout(gbl_p_1);
		
		border = UIManager.getBorder("TextField.border");

		//  Section_Header_Request (Титул на заявката)
		Section_Header_Request();

		

		//  Section_Code_Date_Request (Код и Дата на Заявката)
		Section_Code_Date_Request();

		//  Text_Area_Aplicant (Заявител)
		Text_Area_Aplicant();

		//  Button_Internal_Aplicant (Звено на ДП РАО)
		Button_Internal_Aplicant( );

		//  Buttom_External_Aplicant (Външни клиенти)
		Button_External_Aplicant();

		//  Section_Ind_Num_Doc (Ид. номер на документа)
		Section_Ind_Num_Doc();

		//  choice_izpitvan_produkt (изпитван продукт)
		Section_Izpitvan_Produkt();

		//  Section_Choice_O_I_R (обект на изпитване)
		Section_Choice_O_I_R();

		//  Sestion_Razmernost (размерност)
		Section_Razmernost();

		// CheckBox_InProtokol (извън протокол)
		CheckBox_InAccreditation();

		//  txtArea_list_izpitvan_pokazatel (изпитван показарел)
		Section_Text_Pokazatel( );

		//  Text_Area_Description_Sample_Goup (описание на групата проби)
		Text_Area_Description_Sample_Group();

		//  Sestion_Date_Time_Reference (референтна дата час)
		Sestion_Date_Time_Reference( );

		//  Section_Choice_Period (Период)
		Section_Choice_Period();

		//  txtArea_SampleDescription (описание на пробите)
		Section_Sample_Description( );

		//  txtFld_date_execution (срок за изпълнение)
		Section_Date_Execution( );

		//  Section_RadioButton_Return_Sample (Връшане на пробите)
		Section_Return_Sample();

		//  Section_Date_Reception (дата на приемане)
		Section_Date_Reception( );

		//  Section_Label_User (Приел Заявката)
		Section_Label_User();

		// Section_choice_Aplicant (Съгласувано с клиента)
		Section_Choise_AplicantNameFamily();

		//  Section_Choice_Zab (забележка)
		Section_Choice_Zab();

		//  Section_Text_Aria_DopIzisk (Допълнителни Изисквания)
		Section_DopalnIziskv( );

		//  Section_Text_Aria_DopDogovor (Допълнителни Договорености)
		Section_Text_Aria_DopalnDogovorenosti( );

		//  Button_Save ( Запис )
		Button_Save();

		//  Button_Preview ( Превю )
//		Button_Preview();

		//  Button_Cancel ( Отказ )
		Button_Cancel();

	
		getContentPane().add(scrollpane, BorderLayout.NORTH);
		round.StopWindow();
		setVisible(true);
		
	}

	private void Section_Header_Request() {

		lblRequest_Title = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Request_Title"));
		lblRequest_Title.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblRequest_Title = new GridBagConstraints();
		gbc_lblRequest_Title.anchor = GridBagConstraints.NORTH;
		gbc_lblRequest_Title.insets = new Insets(0, 0, 5, 5);
		gbc_lblRequest_Title.gridwidth = 3;
		gbc_lblRequest_Title.gridx = 2;
		gbc_lblRequest_Title.gridy = 0;
		basicPanel.add(lblRequest_Title, gbc_lblRequest_Title);
	}

	private void Section_Code_Date_Request() {

		lblText_Formuliar = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Text_Formuliar"));
		lblText_Formuliar.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblText_Formuliar = new GridBagConstraints();
		gbc_lblText_Formuliar.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblText_Formuliar.insets = new Insets(0, 0, 5, 5);
		gbc_lblText_Formuliar.gridx = 5;
		gbc_lblText_Formuliar.gridy = 0;
		basicPanel.add(lblText_Formuliar, gbc_lblText_Formuliar);
		lblError = new JLabel(" ");
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.gridwidth = 5;
		gbc_lblError.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 2;
		basicPanel.add(lblError, gbc_lblError);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblError.setForeground(Color.red);

		JPanel panel_1 = new JPanel();
		// panel_1.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 5;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		basicPanel.add(panel_1, gbc_panel_1);

		JLabel label = new JLabel("№");
		panel_1.add(label);
		//  txtField_RequestCode (код на заявката)
		Text_Code_Request(panel_1);

		JLabel label_2 = new JLabel("/");
		panel_1.add(label_2);

		//  txtFld_Date_Request (дата на заявката)
		Texet_Date_Request(panel_1);
	}

	private void Text_Code_Request(JPanel panel) {
		txtField_RequestCode = new JTextField();
		txtField_RequestCode.setText((RequestDAO.getMaxRequestCode() + 1) + "");
		txtField_RequestCode.setForeground(Color.GRAY);
		if (tamplateRequest != null) {
			if(forEdit){
				txtField_RequestCode.setText(tamplateRequest.getRecuest_code());
				txtField_RequestCode.setEditable(false);
			}
			}
		txtField_RequestCode.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {
				if(!forEdit){
				RequestViewFunction.enterRequestCode(txtField_RequestCode, lblError, corectRequestCode);
				}
			}

			@Override
			public void keyPressed(KeyEvent event) {

			}
		});
		txtField_RequestCode.setColumns(4);
		panel.add(txtField_RequestCode);
	}

	private void Texet_Date_Request(JPanel panel) {
		txtFld_Date_Request = new JTextField();
		txtFld_Date_Request.setColumns(8);
		String dateRequest =RequestViewFunction.DateNaw(false);
		if (tamplateRequest != null) {
		if(forEdit){
			dateRequest = tamplateRequest.getDate_request();
		}
		}

		txtFld_Date_Request.setText(dateRequest);
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
		panel.add(txtFld_Date_Request);
	}

	private void Text_Area_Aplicant() {

		if (tamplateRequest != null) {
			if (tamplateRequest.getExtra_module() != null) {
				internalAplic = tamplateRequest.getExtra_module().getInternal_applicant();
				if (internalAplic != null) {
					strAplicant = getStringFromIntraAplicant(internalAplic);
				} else {
					externalAplic = tamplateRequest.getExtra_module().getExternal_applicant();
					if (externalAplic != null) {
						strAplicant = getStringFromExtAplicant(externalAplic);
					}
				}
			}
		}
		JLabel lblAplicant = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_InternalAplikant_Aplikant"));

		GridBagConstraints gbc_lblAplicant = new GridBagConstraints();
		gbc_lblAplicant.anchor = GridBagConstraints.SOUTH;
		gbc_lblAplicant.insets = new Insets(0, 0, 5, 5);
		gbc_lblAplicant.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAplicant.gridx = 1;
		gbc_lblAplicant.gridy = 4;
		basicPanel.add(lblAplicant, gbc_lblAplicant);

		String textStr = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("RequestView_Text_InternalText");
		JLabel lblNewLabel_6 = new JLabel("<html><div style='text-align: center;'>" + textStr + "</div></html>");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 10));

		lblNewLabel_6.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.gridwidth = 2;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 4;
		basicPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		JLabel label = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("RequestView_Text_ExternalText"));
		label.setFont(new Font("Tahoma", Font.PLAIN, 9));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 4;
		gbc_label.gridy = 4;
		basicPanel.add(label, gbc_label);

		txtArea_Aplicant = new JTextArea(strAplicant);
		txtArea_Aplicant.setFont(new Font(fondHeatText, Font.PLAIN, 11));
		txtArea_Aplicant.setEditable(false);
		txtArea_Aplicant.setBorder(UIManager.getBorder("ComboBox.border"));
		GridBagConstraints gbc_txtArea_Aplicant = new GridBagConstraints();
		gbc_txtArea_Aplicant.gridwidth = 5;
		gbc_txtArea_Aplicant.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_Aplicant.fill = GridBagConstraints.BOTH;
		gbc_txtArea_Aplicant.gridx = 1;
		gbc_txtArea_Aplicant.gridy = 5;
		basicPanel.add(txtArea_Aplicant, gbc_txtArea_Aplicant);
		
		changeTiteleAndFormuliarRequest(strAplicant);
	}

	private void Button_Internal_Aplicant() {
		JButton btn_Internal_Aplicant = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Btn_InternetAplikant"));
		btn_Internal_Aplicant.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_Internal_Aplicant.setMargin(new Insets(2, 3, 2, 3));
		btn_Internal_Aplicant.setPreferredSize(new Dimension(115, 23));
		btn_Internal_Aplicant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TranscluentWindow round = new TranscluentWindow();
				Internal_applicant tamplateInternalAplic = null;
				externalAplic = null;
				if (tamplateRequest != null && tamplateRequest.getExtra_module() != null) {
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
				changeTiteleAndFormuliarRequest(strAplicant);
				
			}

	

		});
		GridBagConstraints gbc_btn_Internal_Aplicant = new GridBagConstraints();
		gbc_btn_Internal_Aplicant.gridwidth = 2;
		gbc_btn_Internal_Aplicant.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Internal_Aplicant.gridx = 2;
		gbc_btn_Internal_Aplicant.gridy = 3;
		basicPanel.add(btn_Internal_Aplicant, gbc_btn_Internal_Aplicant);
	}

	
	private void changeTiteleAndFormuliarRequest(String strAplicant) {
			if(strAplicant.trim().isEmpty()){
			lblRequest_Title.setText(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("RequestView_Request_Title"));
			lblText_Formuliar.setText(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("RequestView_Text_Formuliar"));
				}else{
			lblRequest_Title .setText(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("RequestView_Extra_Request_Title"));
			lblText_Formuliar .setText(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("RequestView_Text_Extra_Formuliar"));	
				}
		
	}
	
	private void Button_External_Aplicant() {
		JButton btnExternal_Aplicant = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Btn_External_Aplicant"));
		btnExternal_Aplicant.setMargin(new Insets(2, 3, 2, 3));
		btnExternal_Aplicant.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExternal_Aplicant.setPreferredSize(new Dimension(115, 23));
		
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
				changeTiteleAndFormuliarRequest(strAplicant);
			}

		});
		GridBagConstraints gbc_btnExternal_Aplicant = new GridBagConstraints();
		gbc_btnExternal_Aplicant.gridwidth = 2;
		gbc_btnExternal_Aplicant.insets = new Insets(0, 0, 5, 5);
		gbc_btnExternal_Aplicant.gridx = 4;
		gbc_btnExternal_Aplicant.gridy = 3;
		basicPanel.add(btnExternal_Aplicant, gbc_btnExternal_Aplicant);
	}

	private void Section_Ind_Num_Doc() {
		JLabel lbl_ind_num_doc = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Ind_Num_Doc_Text"));
		lbl_ind_num_doc.setBorder(new EmptyBorder(0, 1, 1, 0));
		lbl_ind_num_doc.setFont(font);
		GridBagConstraints gbc_lbl_ind_num_doc = new GridBagConstraints();
		gbc_lbl_ind_num_doc.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_ind_num_doc.anchor = GridBagConstraints.WEST;
		gbc_lbl_ind_num_doc.gridwidth = 2;
		gbc_lbl_ind_num_doc.gridx = 1;
		gbc_lbl_ind_num_doc.gridy = 6;
		basicPanel.add(lbl_ind_num_doc, gbc_lbl_ind_num_doc);

		choice_ind_num_doc = new Choice();
		choice_ind_num_doc.setFont(font);
		choice_ind_num_doc.setPreferredSize(new Dimension(300, 20));

		RequestViewFunction.setDataIn_Choice_Ind_Num_Doc(choice_ind_num_doc, tamplateRequest);

		GridBagConstraints gbc_choice_ind_num_doc = new GridBagConstraints();
		gbc_choice_ind_num_doc.anchor = GridBagConstraints.WEST;
		gbc_choice_ind_num_doc.gridwidth = 3;
		gbc_choice_ind_num_doc.insets = new Insets(0, 0, 5, 5);
		gbc_choice_ind_num_doc.gridx = 3;
		gbc_choice_ind_num_doc.gridy = 6;
		basicPanel.add(choice_ind_num_doc, gbc_choice_ind_num_doc);

		JLabel lbl2_ind_num_doc = new JLabel(" ");
		lbl2_ind_num_doc.setFont(font);
		GridBagConstraints gbc_lbl2_ind_num_doc = new GridBagConstraints();
		gbc_lbl2_ind_num_doc.gridwidth = 4;
		gbc_lbl2_ind_num_doc.insets = new Insets(0, 0, 5, 5);
		gbc_lbl2_ind_num_doc.gridx = 2;
		gbc_lbl2_ind_num_doc.gridy = 7;
		basicPanel.add(lbl2_ind_num_doc, gbc_lbl2_ind_num_doc);

		choice_ind_num_doc.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				lbl2_ind_num_doc
						.setText(RequestViewAplication.getIND_DescriptByName(choice_ind_num_doc.getSelectedItem()));
			}

		});
	}

	private void Section_Izpitvan_Produkt() {
		JLabel lbl_izpitvan_produkt = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Izpitvan_Produkt_Text"));
		GridBagConstraints gbc_lbl_izpitvan_produkt = new GridBagConstraints();
		gbc_lbl_izpitvan_produkt.anchor = GridBagConstraints.WEST;
		gbc_lbl_izpitvan_produkt.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_izpitvan_produkt.gridx = 1;
		gbc_lbl_izpitvan_produkt.gridy = 8;
		basicPanel.add(lbl_izpitvan_produkt, gbc_lbl_izpitvan_produkt);

		choice_izpitvan_produkt = new Choice();
		choice_izpitvan_produkt.setFont(font);

		RequestViewFunction.setDataIn_Choice_Izpitvan_Produkt(choice_izpitvan_produkt, tamplateRequest);

		GridBagConstraints gbc_izpitvan_produkt = new GridBagConstraints();
		gbc_izpitvan_produkt.gridwidth = 4;
		gbc_izpitvan_produkt.fill = GridBagConstraints.HORIZONTAL;
		gbc_izpitvan_produkt.insets = new Insets(0, 0, 5, 5);
		gbc_izpitvan_produkt.gridx = 2;
		gbc_izpitvan_produkt.gridy = 8;
		basicPanel.add(choice_izpitvan_produkt, gbc_izpitvan_produkt);

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

	private void Section_Choice_O_I_R() {
		JLabel lbl_obekt_na_izpitvane_request = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_obekt_na_izpitvane_Text"));
		GridBagConstraints gbc_lbl_obekt_na_izpitvane_request = new GridBagConstraints();
		gbc_lbl_obekt_na_izpitvane_request.anchor = GridBagConstraints.WEST;
		gbc_lbl_obekt_na_izpitvane_request.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_obekt_na_izpitvane_request.gridx = 1;
		gbc_lbl_obekt_na_izpitvane_request.gridy = 9;
		basicPanel.add(lbl_obekt_na_izpitvane_request, gbc_lbl_obekt_na_izpitvane_request);

		// choice_obekt_na_izpitvane_request = new Choice();
		// choice_obekt_na_izpitvane_request.setFont(font);
		// choice_obekt_na_izpitvane_request.setPreferredSize(new Dimension(205,
		// 20));
		txtArea_obekt_na_izpitvane_request = new JTextArea();
		txtArea_obekt_na_izpitvane_request.setBorder(UIManager.getBorder("ComboBox.border"));
		txtArea_obekt_na_izpitvane_request.setFont(font);
		txtArea_obekt_na_izpitvane_request.setEditable(false);

		GridBagConstraints gbc_choice_obekt_na_izpitvane_request = new GridBagConstraints();
		gbc_choice_obekt_na_izpitvane_request.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_obekt_na_izpitvane_request.gridwidth = 3;
		gbc_choice_obekt_na_izpitvane_request.insets = new Insets(0, 0, 5, 5);
		gbc_choice_obekt_na_izpitvane_request.gridx = 2;
		gbc_choice_obekt_na_izpitvane_request.gridy = 9;
		basicPanel.add(txtArea_obekt_na_izpitvane_request, gbc_choice_obekt_na_izpitvane_request);

		txtArea_obekt_na_izpitvane_request.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				txtArea_obekt_na_izpitvane_request.setBackground(Color.WHITE);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				txtArea_obekt_na_izpitvane_request.setBackground(Color.WHITE);
			}
		});

		listStringOfRequest_To_ObektNaIzpitvaneRequest = Table_RequestToObektNaIzp
				.getListStringOfRequest_To_ObektNaIzpitvaneRequest(tamplateRequest);
		txtArea_obekt_na_izpitvane_request.setText(Table_RequestToObektNaIzp
				.createStringListObektNaIzp(listStringOfRequest_To_ObektNaIzpitvaneRequest, true));
		bsic_listObektNaIzpit = RequestViewAplication.getStringMassiveO_I_R();

		//  Button_Add_O_I_R(Добавяне на Обект на Изпитване )
		Button_Add_O_I_R();
	}

	private void Button_Add_O_I_R() {
		JButton btn_add__obekt_na_izpitvane_request = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Btn_Izbor"));
		btn_add__obekt_na_izpitvane_request.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JFrame f = new JFrame();
				new ChoiceFromListWithPlusAndMinus(f, listStringOfRequest_To_ObektNaIzpitvaneRequest,
						bsic_listObektNaIzpit, ReadFileWithGlobalTextVariable.
						getGlobalTextVariableMap().get("ChoiceFromListWithPlusAndMinus_choiceText"));

				listStringOfRequest_To_ObektNaIzpitvaneRequest = ChoiceFromListWithPlusAndMinus
						.getMasiveStringFromChoice();
				txtArea_obekt_na_izpitvane_request.setText(Table_RequestToObektNaIzp
						.createStringListObektNaIzp(listStringOfRequest_To_ObektNaIzpitvaneRequest, true));

			}

		});
		GridBagConstraints gbc_btn_add__obekt_na_izpitvane_request = new GridBagConstraints();
		gbc_btn_add__obekt_na_izpitvane_request.anchor = GridBagConstraints.NORTHWEST;
		gbc_btn_add__obekt_na_izpitvane_request.insets = new Insets(0, 0, 5, 5);
		gbc_btn_add__obekt_na_izpitvane_request.gridx = 5;
		gbc_btn_add__obekt_na_izpitvane_request.gridy = 9;
		basicPanel.add(btn_add__obekt_na_izpitvane_request, gbc_btn_add__obekt_na_izpitvane_request);
	}

	private void CheckBox_InAccreditation() {
		chckbx_accreditation = new JCheckBox(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_CheckBox_InAccreditation_Text"));
		GridBagConstraints gbc_chckbx_accreditation = new GridBagConstraints();
		gbc_chckbx_accreditation.anchor = GridBagConstraints.EAST;
		gbc_chckbx_accreditation.insets = new Insets(0, 0, 5, 5);
		gbc_chckbx_accreditation.gridx = 5;
		gbc_chckbx_accreditation.gridy = 10;
		basicPanel.add(chckbx_accreditation, gbc_chckbx_accreditation);
		
		if (tamplateRequest != null) {
			chckbx_accreditation.setSelected(tamplateRequest.getAccreditation());
		}
	}

	private void Section_Razmernost() {
		JLabel lbl_Razmernost = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Razmernost_Text"));
		GridBagConstraints gbc_lbl_Razmernost = new GridBagConstraints();
		gbc_lbl_Razmernost.anchor = GridBagConstraints.WEST;
		gbc_lbl_Razmernost.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Razmernost.gridx = 1;
		gbc_lbl_Razmernost.gridy = 10;
		basicPanel.add(lbl_Razmernost, gbc_lbl_Razmernost);

		// choice_Razmernost (размерност)
		choice_Razmernost = new Choice();
		choice_Razmernost.setFont(font);
		choice_Razmernost.setPreferredSize(new Dimension(60, 20));

		RequestViewFunction.setDataIn_Choice_Razmernost(choice_Razmernost, tamplateRequest);

		GridBagConstraints gbc_choice_Razmernost = new GridBagConstraints();
		gbc_choice_Razmernost.anchor = GridBagConstraints.WEST;
		gbc_choice_Razmernost.insets = new Insets(0, 0, 5, 5);
		gbc_choice_Razmernost.gridx = 2;
		gbc_choice_Razmernost.gridy = 10;
		basicPanel.add(choice_Razmernost, gbc_choice_Razmernost);
	}

	private void Section_Text_Pokazatel() {
		JLabel lbl_list_izpitvan_pokazatel = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Pokazatel_Text"));
		GridBagConstraints gbc_lbl_list_izpitvan_pokazatel = new GridBagConstraints();
		gbc_lbl_list_izpitvan_pokazatel.anchor = GridBagConstraints.WEST;
		gbc_lbl_list_izpitvan_pokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_list_izpitvan_pokazatel.gridx = 1;
		gbc_lbl_list_izpitvan_pokazatel.gridy = 11;
		basicPanel.add(lbl_list_izpitvan_pokazatel, gbc_lbl_list_izpitvan_pokazatel);
		txtArea_list_izpitvan_pokazatel = new JTextArea();
		txtArea_list_izpitvan_pokazatel.setBorder(UIManager.getBorder("ComboBox.border"));
		txtArea_list_izpitvan_pokazatel.setFont(font);

		GridBagConstraints gbc_txtArea_list_izpitvan_pokazatel = new GridBagConstraints();
		gbc_txtArea_list_izpitvan_pokazatel.gridwidth = 3;
		gbc_txtArea_list_izpitvan_pokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_list_izpitvan_pokazatel.fill = GridBagConstraints.BOTH;
		gbc_txtArea_list_izpitvan_pokazatel.gridx = 2;
		gbc_txtArea_list_izpitvan_pokazatel.gridy = 11;
		txtArea_list_izpitvan_pokazatel.setEditable(false);
		basicPanel.add(txtArea_list_izpitvan_pokazatel, gbc_txtArea_list_izpitvan_pokazatel);

		RequestViewFunction.setTextIn_Text_Area_List_Izpitvan_Pokazatel(txtArea_list_izpitvan_pokazatel,
				tamplateRequest);

		Button_Pokazatel();
	}

	private void Button_Pokazatel() {
		JButton btn_list_izpitvan_pokazatel = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Btn_Pokazatel_Text"));
		btn_list_izpitvan_pokazatel.addActionListener(new ActionListener() {
			// @SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {

				RequestViewFunction.generateTextIn_Text_Area_List_Izpitvan_Pokazatel(txtArea_list_izpitvan_pokazatel,
						border);

			}

		});
		GridBagConstraints gbc_btn_list_izpitvan_pokazatel = new GridBagConstraints();
		gbc_btn_list_izpitvan_pokazatel.anchor = GridBagConstraints.WEST;
		gbc_btn_list_izpitvan_pokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_btn_list_izpitvan_pokazatel.gridx = 5;
		gbc_btn_list_izpitvan_pokazatel.gridy = 11;
		basicPanel.add(btn_list_izpitvan_pokazatel, gbc_btn_list_izpitvan_pokazatel);
	}

	private void Text_Area_Description_Sample_Group() {
		String strSampGroup = "";
		if (tamplateRequest != null) {
			strSampGroup = tamplateRequest.getDescription_sample_group();
			if (strSampGroup.indexOf("˲") > 0)
				original_Description_sample_group = strSampGroup.substring(0, strSampGroup.indexOf("˲")+1);
		}
		txtArea_Descript_grup_Sample = new JTextArea(original_Description_sample_group);
		txtArea_Descript_grup_Sample.setBorder(UIManager.getBorder("ComboBox.border"));
		txtArea_Descript_grup_Sample.setFont(font);

		GridBagConstraints gbc_txtArea_Descript_grup_Sample = new GridBagConstraints();
		gbc_txtArea_Descript_grup_Sample.gridwidth = 5;
		gbc_txtArea_Descript_grup_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_Descript_grup_Sample.fill = GridBagConstraints.BOTH;
		gbc_txtArea_Descript_grup_Sample.gridx = 1;
		gbc_txtArea_Descript_grup_Sample.gridy = 14;
		basicPanel.add(txtArea_Descript_grup_Sample, gbc_txtArea_Descript_grup_Sample);
	}

	private void Sestion_Date_Time_Reference() {

		JLabel lbl_date_time_reception = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Sestion_Date_Time_Reference_Text"));
		GridBagConstraints gbc_lbl_date_time_reception = new GridBagConstraints();
		gbc_lbl_date_time_reception.gridwidth = 2;
		gbc_lbl_date_time_reception.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_time_reception.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_time_reception.gridx = 1;
		gbc_lbl_date_time_reception.gridy = 12;
		basicPanel.add(lbl_date_time_reception, gbc_lbl_date_time_reception);
		
		String dateRefRequest ="";
		if (tamplateRequest != null) {
		if(forEdit){
			dateRefRequest = SampleDAO.getListSampleFromColumnByVolume("request",tamplateRequest).get(0).getDate_time_reference();
//			listSample = SampleDAO.getListSampleFromColumnByVolume("request", tamplateRequest);
		}
		}
		txt_fid_date_time_reference = new JTextField(dateRefRequest);
		txt_fid_date_time_reference.setBorder(UIManager.getBorder("ComboBox.border"));
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
		basicPanel.add(txt_fid_date_time_reference, gbc_date_time_reference);

		Button_Date_Time_Reference();
	}

	private void Button_Date_Time_Reference() {
		JButton btn_date_time_reference = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Btn_Izbor"));
		btn_date_time_reference.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					final JFrame f = new JFrame();
					Boolean forDateReception = false;
					Boolean withTime = true;
					Boolean fromTable = false;
					String str_date_period_reception = txt_fid_date_time_reference.getText();

					DateChoice_period date_time_reference = new DateChoice_period(f, "", str_date_period_reception,
							withTime, forDateReception,	fromTable);

					date_time_reference.setVisible(true);
					str_Descript_grup_Sample = RequestViewFunction.generateTxtInDescriptGrupSampleNew(choice_Period,
							txtFld_Count_Sample.getText(), original_Description_sample_group);

					txtArea_Descript_grup_Sample.setText(str_Descript_grup_Sample);

					String textRefDate = "";
					textRefDate = DateChoice_period.get_date_time_reference();

					if (DatePicker.incorrectDate(textRefDate, true))
						txt_fid_date_time_reference.setForeground(Color.RED);
					else {
						txt_fid_date_time_reference.setForeground(Color.BLACK);
						txt_fid_date_time_reference.setBorder(border);
					}

					txt_fid_date_time_reference.setText(textRefDate);

				} catch (NumberFormatException e) {
					ResourceLoader.appendToFile(e);
				}
			}
		});
		GridBagConstraints gbc_btn_date_time_reference = new GridBagConstraints();
		gbc_btn_date_time_reference.anchor = GridBagConstraints.WEST;
		gbc_btn_date_time_reference.insets = new Insets(0, 0, 5, 5);
		gbc_btn_date_time_reference.gridx = 4;
		gbc_btn_date_time_reference.gridy = 12;
		basicPanel.add(btn_date_time_reference, gbc_btn_date_time_reference);
	}

	private void Section_Choice_Period() {

		JLabel lbl_Period = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Choice_Period_Text"));
		GridBagConstraints gbc_lbl_Period = new GridBagConstraints();
		gbc_lbl_Period.anchor = GridBagConstraints.EAST;
		gbc_lbl_Period.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Period.gridx = 1;
		gbc_lbl_Period.gridy = 13;
		basicPanel.add(lbl_Period, gbc_lbl_Period);

		choice_Period = new Choice();
		choice_Period.setFont(font);
		choice_Period.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints gbc_choice_Period = new GridBagConstraints();
		gbc_choice_Period.anchor = GridBagConstraints.WEST;
		gbc_choice_Period.insets = new Insets(0, 0, 5, 5);
		gbc_choice_Period.gridx = 2;
		gbc_choice_Period.gridy = 13;
		basicPanel.add(choice_Period, gbc_choice_Period);

		RequestViewFunction.setDataIn_Choice_Period(choice_Period);
		if (tamplateRequest != null) {
			if(forEdit){
				Period period = SampleDAO.getListSampleFromColumnByVolume("request", tamplateRequest).get(0).getPeriod();
				String strPer = "";
				if(period!=null){
					strPer = period.getValue();
				}
				choice_Period.select(strPer);
			}
			}
		// Add item listener choice_Period
		choice_Period.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				str_Descript_grup_Sample = RequestViewFunction.generateTxtInDescriptGrupSampleNew(choice_Period,
						txtFld_Count_Sample.getText(), original_Description_sample_group);
				txtArea_Descript_grup_Sample.setText(str_Descript_grup_Sample);

			}
		});
	}

	private void Section_Sample_Description() {

		JLabel lbl_SampleDescription = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Sample_Description_Text"));
		GridBagConstraints gbc_lbl_SampleDescription = new GridBagConstraints();
		gbc_lbl_SampleDescription.anchor = GridBagConstraints.WEST;
		gbc_lbl_SampleDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_SampleDescription.gridx = 1;
		gbc_lbl_SampleDescription.gridy = 16;
		basicPanel.add(lbl_SampleDescription, gbc_lbl_SampleDescription);

		// txtFld_Count_Sample (брой на пробите)
		Text_Count_Sample();
		
		
		JButton btn_SampleDescription = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Sample_Description_Text"));
		
		btn_SampleDescription.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				createPanelSample_Description();

			}
		});

		GridBagConstraints gbc_btn_SampleDescription = new GridBagConstraints();
		gbc_btn_SampleDescription.anchor = GridBagConstraints.WEST;
		gbc_btn_SampleDescription.insets = new Insets(0, 0, 5, 5);
		gbc_btn_SampleDescription.gridx = 5;
		gbc_btn_SampleDescription.gridy = 16;
		basicPanel.add(btn_SampleDescription, gbc_btn_SampleDescription);

		txtArea_SampleDescription = new JTextArea();
		txtArea_SampleDescription.setBorder(UIManager.getBorder("ComboBox.border"));
		txtArea_SampleDescription.setFont(font);
		GridBagConstraints gbc_txtArea_SampleDescription = new GridBagConstraints();
		gbc_txtArea_SampleDescription.gridwidth = 5;
		gbc_txtArea_SampleDescription.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_SampleDescription.fill = GridBagConstraints.BOTH;
		gbc_txtArea_SampleDescription.gridx = 1;
		gbc_txtArea_SampleDescription.gridy = 17;
		basicPanel.add(txtArea_SampleDescription, gbc_txtArea_SampleDescription);
		txtArea_SampleDescription.setEditable(false);

		if (tamplateRequest != null) {
			if(forEdit){
				final JFrame f = new JFrame();
				comBox_O_I_S = RequestViewAplication.getStringMassiveO_I_S();
				int count_Sample = tamplateRequest.getCounts_samples();
				String period = choice_Period.getSelectedItem();
				String[][] masiveSampleFromTampleteRequest = SampleViewAdd.getVolumeSampleFromTampleteRequest(tamplateRequest);
					new SampleViewAdd(f, count_Sample, Integer.valueOf(tamplateRequest.getRecuest_code()), comBox_O_I_S,
						txt_fid_date_time_reference.getText(), period, masiveSampleFromTampleteRequest, tamplateRequest,
						listStringOfRequest_To_ObektNaIzpitvaneRequest);
				
				previewPanelSampleDescrip(count_Sample);
			}
		}

	}
		

	private void createPanelSample_Description() {
		String showMessageDialog_Error = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_showMessageDialog_Error");
		comBox_O_I_S = RequestViewAplication.getStringMassiveO_I_S();
		try {
			int requestCode = Integer.valueOf(txtField_RequestCode.getText()); // kod
			try {

				String ref_Date_Time = txt_fid_date_time_reference.getText(); // refDate
				LocalDate.parse(ref_Date_Time, DateTimeFormatter.ofPattern(FORMAT_DATE_TIME)); // check refDate
				String period = choice_Period.getSelectedItem();
				int choice = 0;
				
				if (period.equals("") && !choice_ind_num_doc.getSelectedItem().equals("")){ 
					String str = ReadFileWithGlobalTextVariable.
							getGlobalTextVariableMap().get("RequestView_showMessageDialog_ErrorPeriodeRequest");
					
					Object[] options = { "Да", "Не" };
					choice = JOptionPane.showOptionDialog(null,str+ " \nЩе продължите ли?",
							"Грешни данни", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					
				}
					if (choice == JOptionPane.YES_OPTION) {
														
				try {

					int count_Sample = Integer.valueOf(txtFld_Count_Sample.getText()); // broi
					if (count_Sample <= 0 || count_Sample > 20) {
						txtFld_Count_Sample.setText("");
						count_Sample = Integer.valueOf(txtFld_Count_Sample.getText());
					}

					final JFrame f = new JFrame();
					SampleViewAdd sampleDescript = null;
					sampleDescript = new SampleViewAdd(f, count_Sample, requestCode, comBox_O_I_S,
							ref_Date_Time, period, masiveSampleValue, tamplateRequest,
							listStringOfRequest_To_ObektNaIzpitvaneRequest);
					sampleDescript.setVisible(true);
				
					

					if (!SampleViewAdd.cancelEntered()) {
						previewPanelSampleDescrip(count_Sample);
						
					}
				} catch (NumberFormatException e) {
					ResourceLoader.appendToFile(e);
					JOptionPane.showMessageDialog(null, ReadFileWithGlobalTextVariable.
							getGlobalTextVariableMap().get("RequestView_DialogError_CountSamples"), showMessageDialog_Error,
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
			} catch (DateTimeParseException e) {
				ResourceLoader.appendToFile(e);
				JOptionPane.showMessageDialog(RequestView.this, ReadFileWithGlobalTextVariable.
						getGlobalTextVariableMap().get("RequestView_showMessageDialog_ErrorRefDataTime"),showMessageDialog_Error, 
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException e) {
			ResourceLoader.appendToFile(e);
			JOptionPane.showMessageDialog(RequestView.this, ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("RequestView_showMessageDialog_ErrorCodeRequest"), showMessageDialog_Error,
					JOptionPane.ERROR_MESSAGE);
		}
	
	}

	private void previewPanelSampleDescrip(int count_Sample) {
		masiveSampleValue = SampleViewAdd.getVolumeSampleView(count_Sample);
		txtArea_SampleDescription.setFont(new Font("monospaced", Font.PLAIN, 12));
		txtArea_SampleDescription.setText(
				RequestViewAplication.writeSampleDescript(getSimpleTextForRequestToO_I_R(
						listStringOfRequest_To_ObektNaIzpitvaneRequest, masiveSampleValue)));
		txtArea_SampleDescription.setBorder(border);
	}
	
	private String[][] getSimpleTextForRequestToO_I_R(List<String> listStringOfRequest_To_ObektNaIzpitvaneRequest, 
			String[][] masiveSampleValue) {
		for (int i = 0; i < masiveSampleValue.length; i++) {
			for (String strings : listStringOfRequest_To_ObektNaIzpitvaneRequest) {
				if (strings.equals(masiveSampleValue[i][1])) {
					String str = Obekt_na_izpitvane_requestDAO.getValueObekt_na_izpitvane_requestByName(strings)
							.getSimple_Name();
					if (!str.trim().equals("")) {
						masiveSampleValue[i][1] = str;
					}
				}
			}

		}
		return masiveSampleValue;
	}

	private void Section_Return_Sample() {
		JLabel lblNewLabel_5 = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Return_Sample"));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 3;
		gbc_lblNewLabel_5.gridy = 15;
		basicPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		rdbtn_Yes = new JRadioButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("Yes_Btn_Text"));
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.anchor = GridBagConstraints.EAST;
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 4;
		gbc_rdbtnNewRadioButton.gridy = 15;
		basicPanel.add(rdbtn_Yes, gbc_rdbtnNewRadioButton);
		rdbtn_Yes.setSelected(true);

		JRadioButton rdbtn_No = new JRadioButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("No_Btn_Text"));
		GridBagConstraints gbc_radioButton = new GridBagConstraints();
		gbc_radioButton.anchor = GridBagConstraints.WEST;
		gbc_radioButton.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton.gridx = 5;
		gbc_radioButton.gridy = 15;
		basicPanel.add(rdbtn_No, gbc_radioButton);

		if (tamplateRequest != null) {
			if(forEdit){
				rdbtn_Yes.setSelected(tamplateRequest.getExtra_module().getReturn_samples());
				selectValueInRButtonNO(rdbtn_No);
			}
		}
		
		rdbtn_Yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectValueInRButtonNO(rdbtn_No);
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

	private void selectValueInRButtonNO(JRadioButton rdbtn_No) {
		if (rdbtn_Yes.isSelected()) {
			rdbtn_No.setSelected(false);
		} else {
			rdbtn_No.setSelected(true);
		}
	}
	
	private void Text_Count_Sample() {

		JLabel lbl_Count_Sample = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Count_Sample"));
		GridBagConstraints gbc_lbl_Count_Sample = new GridBagConstraints();
		gbc_lbl_Count_Sample.anchor = GridBagConstraints.EAST;
		gbc_lbl_Count_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Count_Sample.gridx = 1;
		gbc_lbl_Count_Sample.gridy = 15;
		basicPanel.add(lbl_Count_Sample, gbc_lbl_Count_Sample);

		JLabel lblError_Count_Sample = new JLabel(" ");
		GridBagConstraints gbc_lblError_Count_Sample = new GridBagConstraints();
		gbc_lblError_Count_Sample.anchor = GridBagConstraints.WEST;
		gbc_lblError_Count_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_lblError_Count_Sample.gridx = 2;
		gbc_lblError_Count_Sample.gridy = 16;
		basicPanel.add(lblError_Count_Sample, gbc_lblError_Count_Sample);

		String countSamp = "1";
		if (tamplateRequest != null) {
			countSamp = tamplateRequest.getCounts_samples() + "";

		}
		txtFld_Count_Sample = new JTextField();
		txtFld_Count_Sample.setText(countSamp);
		txtFld_Count_Sample.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {
				txtFld_Count_Sample.setText(RequestViewFunction.checkFormatString(txtFld_Count_Sample.getText()));

				if (RequestViewAplication.checkMaxVolume(txtFld_Count_Sample.getText(), 1, 20)) {
					txtFld_Count_Sample.setForeground(Color.red);
					lblError_Count_Sample.setText(ReadFileWithGlobalTextVariable.
							getGlobalTextVariableMap().get("RequestView_Error_Count_Sample"));

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
		gbc_txtFld_Count_Sample.gridx = 2;
		gbc_txtFld_Count_Sample.gridy = 15;
		basicPanel.add(txtFld_Count_Sample, gbc_txtFld_Count_Sample);
		txtFld_Count_Sample.setColumns(3);

	}

	private void Section_Date_Execution() {
		JLabel lbl_date_execution = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Date_Execution"));
		GridBagConstraints gbc_lbl_date_execution = new GridBagConstraints();
		gbc_lbl_date_execution.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_execution.gridwidth = 2;
		gbc_lbl_date_execution.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_execution.gridx = 1;
		gbc_lbl_date_execution.gridy = 18;
		basicPanel.add(lbl_date_execution, gbc_lbl_date_execution);

		String dateExecution = "";
		if (tamplateRequest != null) {
			if(forEdit){
				dateExecution = tamplateRequest.getDate_execution();	
			}
			}
		txtFld_date_execution = new JTextField(dateExecution);
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
		basicPanel.add(txtFld_date_execution, gbc_txtFld_date_execution);

		Button_Date_Execution();
	}

	private void Button_Date_Execution() {
		JButton btn_date_execution = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Button_Date_Selection"));
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
		gbc_btn_date_execution.gridy = 18;
		basicPanel.add(btn_date_execution, gbc_btn_date_execution);
	}

	private void Section_Date_Reception() {
		JLabel lbl_date_reception = new JLabel(
				ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Date_Reception"));
		GridBagConstraints gbc_lbl_date_reception = new GridBagConstraints();
		gbc_lbl_date_reception.anchor = GridBagConstraints.EAST;
		gbc_lbl_date_reception.gridwidth = 2;
		gbc_lbl_date_reception.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_date_reception.gridx = 1;
		gbc_lbl_date_reception.gridy = 19;
		basicPanel.add(lbl_date_reception, gbc_lbl_date_reception);

		
		String dateReception =RequestViewFunction.DateNaw(false);
		if (tamplateRequest != null) {
		if(forEdit){
			dateReception = tamplateRequest.getDate_reception();
		}
		}
		txtFld_date_period_reception = new JTextField(dateReception);
		txtFld_date_period_reception.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {
				incorrect_date_period_Reception = RequestViewFunction
						.incorrectReception_Date_Period(txtFld_date_period_reception);
				if (incorrect_date_period_Reception) {
					txtFld_date_period_reception.setForeground(Color.RED);
				} else {
					txtFld_date_period_reception.setForeground(Color.BLACK);
					txtFld_date_period_reception.setBorder(border);
				}
			}

			@Override
			public void keyPressed(KeyEvent event) {

			}
		});
		GridBagConstraints gbc_txtFld_date_reception = new GridBagConstraints();
		gbc_txtFld_date_reception.gridwidth = 2;
		gbc_txtFld_date_reception.insets = new Insets(0, 0, 5, 5);
		gbc_txtFld_date_reception.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFld_date_reception.gridx = 3;
		gbc_txtFld_date_reception.gridy = 19;
		basicPanel.add(txtFld_date_period_reception, gbc_txtFld_date_reception);

		Button_Date_Rception();
	}

	private void Button_Date_Rception() {
		JButton btn_date_reception = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Button_Date_Selection"));
		btn_date_reception.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					
					Boolean forDateReception = true;
					Boolean withTime = false;
					Boolean fromTable = false;
					String str_date_period_reception = txtFld_date_period_reception.getText();
					if (incorrect_date_period_Reception) {
						str_date_period_reception = RequestViewFunction.DateNaw(false);
					}
					final JFrame f = new JFrame();
					String label = ReadFileWithGlobalTextVariable.
							getGlobalTextVariableMap().get("RequestView_Section_Date_Reception_DateChoice_period");
					DateChoice_period date_period_reception = new DateChoice_period(f, label, str_date_period_reception,
							withTime, forDateReception,fromTable);
					date_period_reception.setVisible(true);
					Boolean forTable = false;
					String textRefDate = "";
					textRefDate = DateChoice_period.get_str_period_sample(forDateReception, forTable);

					
					
					incorrect_date_period_Reception = RequestViewFunction
							.incorrectReception_Date_Period(txtFld_date_period_reception);
					if (incorrect_date_period_Reception) {
						txtFld_date_period_reception.setForeground(Color.RED);
					} else {
						txtFld_date_period_reception.setForeground(Color.BLACK);
						txtFld_date_period_reception.setBorder(border);
					}

					txtFld_date_period_reception.setText(textRefDate);

				} catch (NumberFormatException e) {
					ResourceLoader.appendToFile(e);
				}

			}
		});
		GridBagConstraints gbc_btn_date_reception = new GridBagConstraints();
		gbc_btn_date_reception.anchor = GridBagConstraints.WEST;
		gbc_btn_date_reception.insets = new Insets(0, 0, 5, 5);
		gbc_btn_date_reception.gridx = 5;
		gbc_btn_date_reception.gridy = 19;
		basicPanel.add(btn_date_reception, gbc_btn_date_reception);
	}

	private void Section_Label_User() {

		JLabel lblNewLabel_3 = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Accepted"));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 20;
		basicPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		String curentUser =user.getName_users() + " " + user.getFamily_users();
		if (tamplateRequest != null) {
		if(forEdit){
			curentUser = tamplateRequest.getUsers().getName_users() + " " + tamplateRequest.getUsers().getFamily_users();
		}
		}
		
		JLabel lbl_User = new JLabel(curentUser);
		GridBagConstraints gbc_lbl_User = new GridBagConstraints();
		gbc_lbl_User.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_User.gridx = 2;
		gbc_lbl_User.gridy = 20;
		basicPanel.add(lbl_User, gbc_lbl_User);
	}

	private void Section_Choise_AplicantNameFamily() {
		JLabel lbl_AplicantNameFamily = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Choise_AplicantNameFamily")+" :");
		GridBagConstraints gbc_lbl_AplicantNameFamily = new GridBagConstraints();
		gbc_lbl_AplicantNameFamily.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_AplicantNameFamily.gridx = 2;
		gbc_lbl_AplicantNameFamily.gridy = 21;
		basicPanel.add(lbl_AplicantNameFamily, gbc_lbl_AplicantNameFamily);

		choice_AplicantNameFamily = new Choice();
		choice_AplicantNameFamily.setPreferredSize(new Dimension(60, 20));
		choice_AplicantNameFamily.setFont(new Font("Tahoma", Font.PLAIN, 11));

		choice_AplicantNameFamily.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				choice_AplicantNameFamily.setBackground(Color.WHITE);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				choice_AplicantNameFamily.setBackground(Color.WHITE);

			}
		});

		choice_AplicantNameFamily.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				choice_AplicantNameFamily.setBackground(Color.WHITE);
			}

		});
		
		masive_AplicantNameFamily = AplicantDAO.getMasiveStringAllName_FamilyAplicant();
		ArrayList<String> array_AplicantNameFamily = new ArrayList<String>();
		array_AplicantNameFamily.add("");
		for (String string : masive_AplicantNameFamily) {
			choice_AplicantNameFamily.add(string);
			array_AplicantNameFamily.add(string);
		}
		String str = "";
		if (tamplateRequest != null) {
			if (tamplateRequest.getExtra_module() != null && tamplateRequest.getExtra_module().getAplicant() != null) {
				str = tamplateRequest.getExtra_module().getAplicant().getName_aplicant() + " "
						+ tamplateRequest.getExtra_module().getAplicant().getFamily_aplicant();
				choice_AplicantNameFamily.select(str);
			}
		}

		GridBagConstraints gbc_choice_AplicantNameFamily = new GridBagConstraints();
		gbc_choice_AplicantNameFamily.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_AplicantNameFamily.gridwidth = 2;
		gbc_choice_AplicantNameFamily.insets = new Insets(0, 0, 5, 5);
		gbc_choice_AplicantNameFamily.gridx = 3;
		gbc_choice_AplicantNameFamily.gridy = 21;
		basicPanel.add(choice_AplicantNameFamily, gbc_choice_AplicantNameFamily);

		Button_Add_AplicantNameFamily(array_AplicantNameFamily);

	}

	private void Button_Add_AplicantNameFamily( ArrayList<String> array_AplicantNameFamily) {
		JButton button = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Add"));

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChoiceFrameNameFamily(array_AplicantNameFamily, choice_AplicantNameFamily);

			}
		});

		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.WEST;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 5;
		gbc_button.gridy = 21;
		basicPanel.add(button, gbc_button);
	}

	private void Section_Choice_Zab() {

		ArrayList<String> arrayZab = RequestViewAplication.getStringZabelejki();
		choice_Zab = new Choice();
		choice_Zab.setFont(font);
		for (String string : arrayZab) {
			choice_Zab.add(string);
		}
		choice_Zab.setPreferredSize(new Dimension(4, 18));

		if (tamplateRequest != null) {
			if (tamplateRequest.getZabelejki() != null) {
				choice_Zab.select(tamplateRequest.getZabelejki().getName_zabelejki());
			} else {
				choice_Zab.select("");
			}
		}
		JLabel lbl_note = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Zab"));
		GridBagConstraints gbc_lbl_note = new GridBagConstraints();
		gbc_lbl_note.anchor = GridBagConstraints.WEST;
		gbc_lbl_note.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_note.gridx = 1;
		gbc_lbl_note.gridy = 22;
		basicPanel.add(lbl_note, gbc_lbl_note);

		GridBagConstraints gbc_choice_Zab = new GridBagConstraints();
		gbc_choice_Zab.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_Zab.gridwidth = 4;
		gbc_choice_Zab.insets = new Insets(0, 0, 5, 5);
		gbc_choice_Zab.gridx = 1;
		gbc_choice_Zab.gridy = 23;
		basicPanel.add(choice_Zab, gbc_choice_Zab);

		Button_Add_Zab( arrayZab);
	}

	private void Button_Add_Zab( ArrayList<String> arrayZab) {
		JButton btn_add_Zab = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Add"));
		btn_add_Zab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean fl = false;
				final JFrame f = new JFrame();
				String nameFrame = ReadFileWithGlobalTextVariable.
						getGlobalTextVariableMap().get("ChoiceNew_Zabelejka_choiceText");
				new AddInChoice(f, nameFrame, arrayZab, choice_Zab.getSelectedItem());

				String str = AddInChoice.getChoice();
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
		gbc_btn_add_Zab.anchor = GridBagConstraints.WEST;
		gbc_btn_add_Zab.insets = new Insets(0, 0, 5, 5);
		gbc_btn_add_Zab.gridx = 5;
		gbc_btn_add_Zab.gridy = 23;
		basicPanel.add(btn_add_Zab, gbc_btn_add_Zab);
	}

	private void Section_DopalnIziskv() {
		JLabel lbl_note = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_DopalnIziskv"));
		GridBagConstraints gbc_lbl_note = new GridBagConstraints();
		gbc_lbl_note.anchor = GridBagConstraints.WEST;
		gbc_lbl_note.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_note.gridx = 1;
		gbc_lbl_note.gridy = 24;
		basicPanel.add(lbl_note, gbc_lbl_note);

		choice_dopIzis = new Choice();
		choice_dopIzis.setPreferredSize(new Dimension(4, 18));
		choice_dopIzis.setFont(new Font("Tahoma", Font.PLAIN, 11));

		GridBagConstraints gbc_choice_dopIzis = new GridBagConstraints();
		gbc_choice_dopIzis.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice_dopIzis.gridwidth = 4;
		gbc_choice_dopIzis.insets = new Insets(0, 0, 5, 5);
		gbc_choice_dopIzis.gridx = 1;
		gbc_choice_dopIzis.gridy = 25;
		basicPanel.add(choice_dopIzis, gbc_choice_dopIzis);

		ArrayList<String> arrayDopIzis = RequestViewAplication.getStringDopIzis();
		for (String string : arrayDopIzis) {
			choice_dopIzis.add(string);
		}

		if (tamplateRequest != null) {
			if (tamplateRequest.getExtra_module() != null && tamplateRequest.getExtra_module().getDoplIzisk() != null) {
				choice_dopIzis.select(tamplateRequest.getExtra_module().getDoplIzisk().getName_dopIzis());
			}
		}

		JButton button_dopIzin = Button_Add_dopIzis(arrayDopIzis);

		GridBagConstraints gbc_button_dopIzis = new GridBagConstraints();
		gbc_button_dopIzis.anchor = GridBagConstraints.WEST;
		gbc_button_dopIzis.insets = new Insets(0, 0, 5, 5);
		gbc_button_dopIzis.gridx = 5;
		gbc_button_dopIzis.gridy = 25;
		basicPanel.add(button_dopIzin, gbc_button_dopIzis);

	}

	private JButton Button_Add_dopIzis(ArrayList<String> arrayDopIzis) {
		JButton button_dopIzis = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Add"));
		button_dopIzis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChoiceFrame(arrayDopIzis, choice_dopIzis);
			}
		});
		return button_dopIzis;
	}

	private void ChoiceFrame(ArrayList<String> array_list, Choice choice_obekt) {

		Boolean fl = false;
		final JFrame f = new JFrame();
		String nameFrame = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("ChoiceNew_dopIzis_choiceText");
		new AddInChoice(f, nameFrame, array_list, choice_obekt.getSelectedItem());
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

	private void Section_Text_Aria_DopalnDogovorenosti() {

		JLabel lbl_DopalnDogovorenosti = new JLabel(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_DopalnDogovorenosti"));
		GridBagConstraints gbc_lbl_DopalnDogovorenosti = new GridBagConstraints();
		gbc_lbl_DopalnDogovorenosti.anchor = GridBagConstraints.WEST;
		gbc_lbl_DopalnDogovorenosti.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_DopalnDogovorenosti.gridx = 1;
		gbc_lbl_DopalnDogovorenosti.gridy = 26;
		basicPanel.add(lbl_DopalnDogovorenosti, gbc_lbl_DopalnDogovorenosti);

		String strDopalnDogovorenosti = "";
		if (tamplateRequest != null) {
			if (tamplateRequest.getExtra_module() != null
					&& tamplateRequest.getExtra_module().getAdditional_requirements() != null) {
				strDopalnDogovorenosti = tamplateRequest.getExtra_module().getAdditional_requirements();
			}
		}

		txtArea_DopalnDogovorenosti = new JTextArea(strDopalnDogovorenosti);
		txtArea_DopalnDogovorenosti.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtArea_DopalnDogovorenosti.setBorder(border);
		GridBagConstraints gbc_txtArea_DopalnDogovorenosti = new GridBagConstraints();
		gbc_txtArea_DopalnDogovorenosti.gridwidth = 4;
		gbc_txtArea_DopalnDogovorenosti.insets = new Insets(0, 0, 5, 5);
		gbc_txtArea_DopalnDogovorenosti.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtArea_DopalnDogovorenosti.gridx = 2;
		gbc_txtArea_DopalnDogovorenosti.gridy = 26;
		basicPanel.add(txtArea_DopalnDogovorenosti, gbc_txtArea_DopalnDogovorenosti);

	}

	private void Button_Save() {
		JButton btn_save = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("saveBtn_Text"));
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(SetNewSimbolInDescriptionSampleGrupe_Request(txtArea_Descript_grup_Sample.getText()));
				if (checkExternalRequest() && checkRequest()) {

					AddResultViewMetods.setWaitCursor(basicPanel);
					AplicantDAO.saveValueAplicantWitchCheck(choice_AplicantNameFamily.getSelectedItem());
				
					if(forEdit){
						DeleteRequestFromDBase.DeleteAllObjectsConnectedByRequest(tamplateRequest, true);
						GenerateDocProtokol.deleteProtokol(tamplateRequest.getRecuest_code());
						
						JOptionPane.showMessageDialog(null, 
								ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("RequestView_showMessageDialog_EditRequest_Result"),
								"Внимание",
								JOptionPane.INFORMATION_MESSAGE);
						}
					
					request = createAndSaveRequest();
					saveRequest_To_ObektNaIzpitvaneRequest(listStringOfRequest_To_ObektNaIzpitvaneRequest);
					saveSample();
					SaveIzpitvanPokazatel();
					
					
					AddResultViewMetods.setDefaultCursor(basicPanel);
					setVisible(false);
				}
			}

		});

		btn_save.setPreferredSize(new Dimension(80, 23));
		GridBagConstraints gbc_btn_save = new GridBagConstraints();
		gbc_btn_save.insets = new Insets(0, 0, 0, 5);
		gbc_btn_save.gridx = 4;
		gbc_btn_save.gridy = 27;
		basicPanel.add(btn_save, gbc_btn_save);
	}

	private void Button_Cancel() {
		JButton btn_cancel = new JButton(ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("cancelBtn_Text"));
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btn_cancel.setPreferredSize(new Dimension(80, 23));
		GridBagConstraints gbc_btn_cancel = new GridBagConstraints();
		gbc_btn_cancel.anchor = GridBagConstraints.WEST;
		gbc_btn_cancel.insets = new Insets(0, 0, 0, 5);
		gbc_btn_cancel.gridx = 5;
		gbc_btn_cancel.gridy = 27;
		basicPanel.add(btn_cancel, gbc_btn_cancel);

	}

	private Request createAndSaveRequest() {
		Extra_module extra_mod = Extra_moduleDAO.saveAndGetExtra_module(createExtraModule());
		Request request = createRequestObject(extra_mod);
		if(forEdit){
			request.setId_recuest(tamplateRequest.getId_recuest());
			RequestDAO.updateObjectRequest(request);
		}else{
		RequestDAO.saveRequestFromRequest(request);
		}

		return request;

	}

	private Boolean checkExternalRequest() {
		
		String aplicantTxt = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_InternalAplikant_Aplikant");
		String selektTxt = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("LabelText_Select");
		String ind_num_docTxt = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Ind_Num_Doc_Text");
		String OR_Txt = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Text_OR");
		String JustONE = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_JustONE");
		String aplicantNameFamilyTxt = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Choise_AplicantNameFamily");
		Boolean saveCheck = true;
		
		String str_AplicantOrId_Number = "";
		if (txtArea_Aplicant.getText().equals("") && choice_ind_num_doc.getSelectedItem().equals("")){ 
			
			str_AplicantOrId_Number = selektTxt + " " + "\n"+ aplicantTxt + "\n"+ OR_Txt + "\n"+ ind_num_docTxt.substring(0, ind_num_docTxt.length()-1);
			saveCheck = false;
		}
			
		if(!txtArea_Aplicant.getText().equals("") && !choice_ind_num_doc.getSelectedItem().equals("")) {
			str_AplicantOrId_Number = selektTxt.substring(0, selektTxt.length()-1) + " " + JustONE +"\n"+ aplicantTxt + "\n"+ OR_Txt + "\n"+ ind_num_docTxt.substring(0, ind_num_docTxt.length()-1);
			saveCheck = false;
		}
		
		
		String str_AplicantName = "";
		if (!txtArea_Aplicant.getText().equals("") &&
			choice_AplicantNameFamily.getSelectedItem().trim().equals("")) {
			choice_AplicantNameFamily.setBackground(Color.RED);
			str_AplicantName = selektTxt + " "+  aplicantNameFamilyTxt + "\n";
			saveCheck = false;
		}
		
		
		if (!saveCheck) {
			String str = str_AplicantOrId_Number + str_AplicantName;
			System.out.println("1- "+str);
			JOptionPane.showMessageDialog(RequestView.this, str, ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("RequestView_showMessageDialog_Error"),
					JOptionPane.ERROR_MESSAGE);
		}
		
		return saveCheck;

	}
	
	
	private Boolean checkRequest() {

		Boolean saveCheck = true;
		String str_RequestCode = "";
		if(!forEdit){
		if (RequestDAO.checkRequestCode(txtField_RequestCode.getText())) {
			txtField_RequestCode.setForeground(Color.red);
			lblError.setText(ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("RequestView_showMessageDialog_Error_NumberRequest"));
			corectRequestCode = false;
		} else {
			txtField_RequestCode.setForeground(Color.BLACK);

			txtField_RequestCode.setBorder(new LineBorder(Color.BLACK));
			lblError.setText(" ");
			corectRequestCode = true;

		}
	}
		String code = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_CodeForRequest");
		String requestDate = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_DateForRequest");
		String izpit_Prod = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Izpitvan_Produkt_Text");
		String obekt_Izpit = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_obekt_na_izpitvane_Text");
		String L_I_P = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Pokazatel_Text");
		String refDate = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Sestion_Date_Time_Reference_Text");
		String sampleDescription = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Sample_Description_Text");
		String dateExecution = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Date_Execution");
		String dateTimeRequest = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_Section_Date_Reception");
		String checkZabToInObhvat = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestView_CheckBox_InAccreditation_Text");
		
		if (!corectRequestCode) {
			txtField_RequestCode.setBorder(new LineBorder(Color.RED));
			str_RequestCode = code + "\n";
			saveCheck = false;
		}
		String str_RequestDate = "";
		if (DatePicker.incorrectDate(txtFld_Date_Request.getText(), false)) {
			txtFld_Date_Request.setBorder(new LineBorder(Color.RED));
			str_RequestDate = requestDate + "\n";
			saveCheck = false;
		}
		String str_Izpit_Prod = "";
		if (choice_izpitvan_produkt.getSelectedItem().equals("")) {
			choice_izpitvan_produkt.setBackground(Color.RED);
			str_Izpit_Prod = izpit_Prod + "\n";
			saveCheck = false;
		}
		String str_Obekt_Izpit = "";
		if (txtArea_obekt_na_izpitvane_request.getText().equals("")) {
			txtArea_obekt_na_izpitvane_request.setBackground(Color.RED);
			str_Obekt_Izpit = obekt_Izpit + "\n";
			saveCheck = false;
		}
		String str_L_I_P = "";
		if (txtArea_list_izpitvan_pokazatel.getText().equals("")) {
			txtArea_list_izpitvan_pokazatel.setBorder(new LineBorder(Color.RED));
			str_L_I_P = L_I_P + "\n";
			saveCheck = false;
		}
		String str_corectRefDate = "";
		if (DatePicker.incorrectDate(txt_fid_date_time_reference.getText(), true)) {
			txt_fid_date_time_reference.setBorder(new LineBorder(Color.RED));
			str_corectRefDate = refDate + "\n";
			saveCheck = false;
		}
		String str_SampleDescription = "";
		if (txtArea_SampleDescription.getText().equals("")) {
			txtArea_SampleDescription.setBorder(new LineBorder(Color.RED));
			str_SampleDescription = sampleDescription + "\n";
			saveCheck = false;
		}
		String str_DateExecution = "";
		if (DatePicker.incorrectDate(txtFld_date_execution.getText(), false)) {
			txtFld_date_execution.setBorder(new LineBorder(Color.RED));
			str_DateExecution = dateExecution + "\n";
			saveCheck = false;
		}

		String str_DateTimeRequest = "";
		if (incorrect_date_period_Reception) {
			txtFld_date_period_reception.setBorder(new LineBorder(Color.RED));
			str_DateTimeRequest =dateTimeRequest + "\n";
			saveCheck = false;
		}

		String str_checkZabToInObhvat = "";
		if (choice_Zab.getSelectedItem().indexOf("10 %") > 0 && !chckbx_accreditation.isSelected()) {
			chckbx_accreditation.setBorder(new LineBorder(Color.RED));
			str_checkZabToInObhvat = checkZabToInObhvat + "\n";
			saveCheck = false;
		}

		if (!saveCheck) {
			String str = str_RequestCode + str_RequestDate + str_Izpit_Prod + str_Obekt_Izpit + str_L_I_P
					+ str_corectRefDate + str_SampleDescription + str_DateExecution + str_DateTimeRequest
					+ str_checkZabToInObhvat;
			System.out.println(str);
			JOptionPane.showMessageDialog(RequestView.this, str, ReadFileWithGlobalTextVariable.
					getGlobalTextVariableMap().get("RequestView_showMessageDialog_Error"),
					JOptionPane.ERROR_MESSAGE);
		}

		return saveCheck;
	}

	private Request createRequestObject(Extra_module extra_mod) {
		Request recuest = null;
		Ind_num_doc ind_num_doc = null;
		if (!choice_ind_num_doc.getSelectedItem().equals(" "))
			ind_num_doc = Ind_num_docDAO.getValueIByName(choice_ind_num_doc.getSelectedItem());

		Izpitvan_produkt izpitvan_produkt = Izpitvan_produktDAO
				.getValueIzpitvan_produktByName(choice_izpitvan_produkt.getSelectedItem());
		Razmernosti razmernosti = RazmernostiDAO.getValueRazmernostiByName(choice_Razmernost.getSelectedItem());
		Zabelejki zabelejki = ZabelejkiDAO.getValueZabelejkiByName(choice_Zab.getSelectedItem());

		// Obekt_na_izpitvane_request obekt_na_izpitvane_request =
		// Obekt_na_izpitvane_requestDAO
		// .getValueObekt_na_izpitvane_requestByName(txtArea_obekt_na_izpitvane_request.getText());

		int count_Sample = Integer.valueOf(txtFld_Count_Sample.getText());

		String textObektNaIzpRequest = Table_RequestToObektNaIzp
				.createStringListObektNaIzp(listStringOfRequest_To_ObektNaIzpitvaneRequest, false);

		recuest = RequestDAO.setValueRequest(txtField_RequestCode.getText(), txtFld_Date_Request.getText(),
				chckbx_accreditation.isSelected(), section, extra_mod, count_Sample,
				SetNewSimbolInDescriptionSampleGrupe_Request(txtArea_Descript_grup_Sample.getText()), txtFld_date_period_reception.getText(),
				txtFld_date_execution.getText(), ind_num_doc, izpitvan_produkt, razmernosti, zabelejki, user,
				textObektNaIzpRequest);
		return recuest;

	}

	private void saveSample() {
		int count_Sample = Integer.valueOf(txtFld_Count_Sample.getText());
		masiveSampleValue = SampleViewAdd.getVolumeSampleView(count_Sample);
		for (int i = 0; i < masiveSampleValue.length; i++) {
			Period period = null;
			if (!masiveSampleValue[i][5].equals(""))
				period = PeriodDAO.getValuePeriodByPeriod(masiveSampleValue[i][5]);
			Obekt_na_izpitvane_sample obectNaIzpitvaneSample = Obekt_na_izpitvane_sampleDAO
					.getValueObekt_na_izpitvane_sampleOrSaveByName(masiveSampleValue[i][2]);

			Request_To_ObektNaIzpitvaneRequest request_To_ObektNaIzpitvaneRequest = Table_RequestToObektNaIzp
					.getRequest_To_ObektNaIzpitvaneRequest(request, masiveSampleValue[i][1]);

			SampleDAO.setValueSample(masiveSampleValue[i][0].substring(5, masiveSampleValue[i][0].length()),
					masiveSampleValue[i][3], masiveSampleValue[i][4], request, obectNaIzpitvaneSample, period,
					Integer.valueOf(masiveSampleValue[i][6]), request_To_ObektNaIzpitvaneRequest);

		}
	}
	


	private void SaveIzpitvanPokazatel() {
		if(forEdit){
				IzpitvanPokazatelDAO.deleteIzpitvanPokazatelByRequest(request);
				}
		
		ArrayList<List_izpitvan_pokazatel> list_izpitvan_pokazatel = ChoiceL_I_P.getListI_PFormChoiceL_P();
		for (List_izpitvan_pokazatel l_I_P : list_izpitvan_pokazatel) {
			IzpitvanPokazatelDAO.setValueIzpitvanPokazatel(l_I_P, request, null);
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

	private Boolean ChoiceFrameNameFamily(ArrayList<String> array_list, Choice choice_obekt) {
		Boolean fl = false;
		final JFrame f = new JFrame();
		String nameFrame = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("ChoiceNew_New_Client_choiceText");
		new AddInChoiceNameFamily(f, nameFrame, array_list, choice_obekt.getSelectedItem());
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

	private Extra_module createExtraModule() {
		String dopDogov = txtArea_DopalnDogovorenosti.getText();
		externalAplic = GetAndSaveExternalAplicant();
		internalAplic = GetAndSaveInternalAplicant();
		Extra_module extra_module = new Extra_module();
		Aplicant aplic = AplicantDAO.getAplicantByNameFamily(choice_AplicantNameFamily.getSelectedItem());
		DopalnIziskv dopIzis = DopalnIziskvDAO.getValueDopalnIziskvByName(choice_dopIzis.getSelectedItem());
		if (!dopDogov.equals("") || aplic != null || dopIzis.getId_dopIzis() != 1 || externalAplic != null
				|| internalAplic != null) {
			extra_module.setAdditional_requirements(txtArea_DopalnDogovorenosti.getText());
			extra_module.setAdditional_arrangements("");
			extra_module.setReturn_samples(rdbtn_Yes.isSelected());
			extra_module.setAplicant(aplic);
			extra_module.setDoplIzisk(dopIzis);
			extra_module.setExternal_applicant(externalAplic);
			extra_module.setInternal_applicant(internalAplic);
		}
		return extra_module;
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
			System.out.println(internalAplic.getInternal_applicant_address());
			System.out.println(internalAplic.getInternal_applicant_organization());
			System.out.println(internalAplic.getInternal_applicant_telephone());
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

	private void saveRequest_To_ObektNaIzpitvaneRequest(
			List<String> listStringOfRequest_To_ObektNaIzpitvaneRequest) {
			for (String stringName : listStringOfRequest_To_ObektNaIzpitvaneRequest) {
			
			Request_To_ObektNaIzpitvaneRequestDAO.setValueRequest_To_ObektNaIzpitvaneRequest(request,
					Obekt_na_izpitvane_requestDAO.getValueObekt_na_izpitvane_requestByName(stringName));
		}

	}
	
	public static String SetNewSimbolInDescriptionSampleGrupe_Request(String str) {
		String za = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestViewFunction_za")+" ";
		String mes = ReadFileWithGlobalTextVariable.
				getGlobalTextVariableMap().get("RequestViewFunction_mesec")+" ";
				
		
		String one = "";
		String tue = "";
		String new_str = "";
		boolean fl = true;
		if(!str.isEmpty()){
			if(str.indexOf("˲")<0){
		int i = str.indexOf(za);
		if(i>0){
			one = str.substring(0, i).trim();
			tue = str.substring(i).trim();
			new_str = one+"˲ "+tue;
			fl=false;
		}
		i = str.indexOf(mes);
		if(fl && i>0){
			one = str.substring(0, i).trim();
			tue = str.substring(i).trim();
			new_str = one+"˲ "+tue;
			fl=false;
		}

		if(fl){
			new_str = str.trim()+"˲";
		}
		}else{
			new_str = str;
		}
		}
		System.out.println("- "+new_str);
		return new_str;
		
	
		
	}	

}
