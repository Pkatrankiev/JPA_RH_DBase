package WindowView;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import AddDobivViewFunction.AddDobivViewMetods;
import AddDobivViewFunction.ChoiceORHODobivSection;
import AddDobivViewFunction.IzpitvanProduktAddDobivSection;
import AddDobivViewFunction.MetodDobivSection;
import AddDobivViewFunction.CodeDobivSection;
import AddDobivViewFunction.ChoiceOIRAddDobivSection;
import AddDobivViewFunction.OverallVariablesAddDobiv;
import AddDobivViewFunction.addNewRowInTableAddDobiv;
import AddDobivViewFunction.btnDataFromDBaseAddDobiv_Section;
import AddDobivViewFunction.btnOpenFileAddDobivSection;
import AddDobivViewFunction.btnPaneAddDobivSection;
import AddDobivViewFunction.btnTabFromFileAddDobivSection;
import DBase_Class.Users;

public class AddDobivView_ extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private static JFileChooser fileChooser = new JFileChooser();
	private JPanel basic_panel;
	private static JScrollPane scrollTablePane;
	private static JTextField textFieldDobivDescrip;

	private final JPanel contentPanel = new JPanel();
	private static JTextField txtBasicValueResult;
	private static JTextField txtStandartCode;
	private static JLabel lblNameMetod;
	private static Choice choiceIzpitProd;
	private static Choice choiceOIR;
	private static Choice choiceORHO;
	private static  Choice choiceMetody;
	private static  JLabel lblError;

	
	private static JButton okButton;
	private static JButton btnAddRow;
	private static JButton btnDataFromDBase;
	private static JButton btnOpenFile;
	private static JButton btnTabFromFile;

	int newCountResults = 0;
	static int countRowTabDobivs = 0;
	int addCount = 0;
	static int rowWidth = 20;
	

	private static JTable tabDobivs;
	


	// private Font font = new Font("Tahoma", Font.PLAIN, 12);
	

	public AddDobivView_(JFrame parent, TranscluentWindow round, Users user) {
		super(parent, "Въвеждане на Добив", true);
		
		AddDobivViewMetods.BasicDataInport(user);

		setSize(1100, (countRowTabDobivs * rowWidth) + 340);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);

		basic_panel = new JPanel();
		basic_panel.setBounds(new Rectangle(5, 0, 0, 0));
		scrollPane.setViewportView(basic_panel);
		GridBagLayout gbl_basic_panel = new GridBagLayout();
		gbl_basic_panel.columnWidths = new int[] { 112, 111, 147, 138, 166, 182, 197, 0 };
		gbl_basic_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_basic_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_basic_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		basic_panel.setLayout(gbl_basic_panel);

		CodeDobivSection(basic_panel);

		DobivDescriptionSection(basic_panel);

		IzpitvanProduktSection(basic_panel);

		ChoiceORHO_Section(basic_panel);

		MetodSection(basic_panel);

		ChoiceOIR_Section(basic_panel);

		btnDataFromDBase(basic_panel);

		btnOpenFile(basic_panel);

		btnTabFromFile(basic_panel);

		btnAddRow(basic_panel);

		ButtonPanell();
		
		TextFieldBasicValueFileSection(basic_panel);
		
		AllListenerSection(this);
		
		
		round.StopWindow();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	private void CodeDobivSection(JPanel panel) {

		lblError = new JLabel();
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.anchor = GridBagConstraints.WEST;
		gbc_lblError.gridwidth = 2;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 2;
		basic_panel.add(lblError, gbc_lblError);
		lblError.setVisible(false);

		JLabel lblStandartCode = new JLabel("Код на стандарта");
		GridBagConstraints gbc_lblStandartCode = new GridBagConstraints();
		gbc_lblStandartCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblStandartCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblStandartCode.gridx = 1;
		gbc_lblStandartCode.gridy = 0;
		panel.add(lblStandartCode, gbc_lblStandartCode);

		txtStandartCode = new JTextField();
		GridBagConstraints gbc_txtStandartCode = new GridBagConstraints();
		gbc_txtStandartCode.insets = new Insets(0, 0, 5, 5);
		gbc_txtStandartCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStandartCode.gridx = 1;
		gbc_txtStandartCode.gridy = 1;
		panel.add(txtStandartCode, gbc_txtStandartCode);
		txtStandartCode.setColumns(10);

	}

	private void DobivDescriptionSection(JPanel panel) {

		JLabel lblDobivDescrip = new JLabel("Описание");
		GridBagConstraints gbc_lblDobivDescrip = new GridBagConstraints();
		gbc_lblDobivDescrip.gridwidth = 2;
		gbc_lblDobivDescrip.insets = new Insets(0, 0, 5, 5);
		gbc_lblDobivDescrip.gridx = 2;
		gbc_lblDobivDescrip.gridy = 0;
		panel.add(lblDobivDescrip, gbc_lblDobivDescrip);

		textFieldDobivDescrip = new JTextField();
		GridBagConstraints gbc_textFieldDobivDescrip = new GridBagConstraints();
		gbc_textFieldDobivDescrip.gridwidth = 2;
		gbc_textFieldDobivDescrip.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDobivDescrip.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDobivDescrip.gridx = 2;
		gbc_textFieldDobivDescrip.gridy = 1;
		basic_panel.add(textFieldDobivDescrip, gbc_textFieldDobivDescrip);
		textFieldDobivDescrip.setColumns(10);

	}

	public void IzpitvanProduktSection(JPanel panel) {
		JLabel lblIzpitProd = new JLabel("Изпитван продукт");
		GridBagConstraints gbc_lblIzpitProd = new GridBagConstraints();
		gbc_lblIzpitProd.anchor = GridBagConstraints.EAST;
		gbc_lblIzpitProd.insets = new Insets(0, 0, 5, 5);
		gbc_lblIzpitProd.gridx = 0;
		gbc_lblIzpitProd.gridy = 3;
		panel.add(lblIzpitProd, gbc_lblIzpitProd);

		choiceIzpitProd = new Choice();
		choiceIzpitProd.setBackground(Color.WHITE);
		choiceIzpitProd.setPreferredSize(new Dimension(205, 20));
		GridBagConstraints gbc_choicePokazatel = new GridBagConstraints();
		gbc_choicePokazatel.fill = GridBagConstraints.HORIZONTAL;
		gbc_choicePokazatel.gridwidth = 2;
		gbc_choicePokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_choicePokazatel.gridx = 1;
		gbc_choicePokazatel.gridy = 3;
		panel.add(choiceIzpitProd, gbc_choicePokazatel);

	}

	private void ChoiceORHO_Section(JPanel panel) {
	JLabel lblNewLabel_1 = new JLabel("Извършил Хим. обработ.");
	GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
	gbc_lblNewLabel_1.gridx = 5;
	gbc_lblNewLabel_1.gridy = 1;
	panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

	choiceORHO = new Choice();
	GridBagConstraints gbc_choiceORHO = new GridBagConstraints();
	gbc_choiceORHO.fill = GridBagConstraints.HORIZONTAL;
	gbc_choiceORHO.insets = new Insets(0, 0, 5, 0);
	gbc_choiceORHO.gridx = 6;
	gbc_choiceORHO.gridy = 1;
	panel.add(choiceORHO, gbc_choiceORHO);
	
	
}
	
	private void MetodSection(JPanel panel) {
		Dimension dim = new Dimension(550, 14);
		JLabel lblMetody = new JLabel("Метод");
		GridBagConstraints gbc_lblMetody = new GridBagConstraints();
		gbc_lblMetody.anchor = GridBagConstraints.EAST;
		gbc_lblMetody.insets = new Insets(0, 0, 5, 5);
		gbc_lblMetody.gridx = 0;
		gbc_lblMetody.gridy = 4;
		panel.add(lblMetody, gbc_lblMetody);

		choiceMetody = new Choice();
		GridBagConstraints gbc_choiceMetody = new GridBagConstraints();
		gbc_choiceMetody.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceMetody.gridwidth = 2;
		gbc_choiceMetody.insets = new Insets(0, 0, 5, 5);
		gbc_choiceMetody.gridx = 1;
		gbc_choiceMetody.gridy = 4;
		panel.add(choiceMetody, gbc_choiceMetody);
		lblNameMetod = new JLabel();

		lblNameMetod.setPreferredSize(dim);
		lblNameMetod.setMinimumSize(dim);
		lblNameMetod.setMaximumSize(dim);
		lblNameMetod.setHorizontalAlignment(SwingConstants.LEFT);

		GridBagConstraints gbc_lblNameMetod = new GridBagConstraints();
		gbc_lblNameMetod.anchor = GridBagConstraints.WEST;
		gbc_lblNameMetod.gridwidth = 4;
		gbc_lblNameMetod.insets = new Insets(0, 0, 5, 0);
		gbc_lblNameMetod.gridx = 3;
		gbc_lblNameMetod.gridy = 4;
		basic_panel.add(lblNameMetod, gbc_lblNameMetod);
		
		
		

	}

	private void ChoiceOIR_Section(JPanel panel) {

		JLabel lblNewLabel_2 = new JLabel("Извършил анализа");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 5;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		choiceOIR = new Choice();
		GridBagConstraints gbc_choiceOIR = new GridBagConstraints();
		gbc_choiceOIR.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceOIR.insets = new Insets(0, 0, 5, 0);
		gbc_choiceOIR.gridx = 6;
		gbc_choiceOIR.gridy = 3;
		panel.add(choiceOIR, gbc_choiceOIR);

	}

	private void btnDataFromDBase(JPanel panel) {

		btnDataFromDBase = new JButton("Данни от базата");
		GridBagConstraints gbc_btnCreadTable = new GridBagConstraints();
		gbc_btnCreadTable.gridwidth = 2;
		gbc_btnCreadTable.anchor = GridBagConstraints.EAST;
		gbc_btnCreadTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreadTable.gridx = 0;
		gbc_btnCreadTable.gridy = 6;
		panel.add(btnDataFromDBase, gbc_btnCreadTable);
	}

	private void ButtonPanell() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		okButton = new JButton("Запис");
		buttonPane.add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				setVisible(false);
			}
		});
		
		buttonPane.add(cancelButton);
	}

	private void btnOpenFile(JPanel panel) {
		btnOpenFile = new JButton("Отвори");
		GridBagConstraints gbc_btnBasicDataFile = new GridBagConstraints();
		gbc_btnBasicDataFile.anchor = GridBagConstraints.WEST;
		gbc_btnBasicDataFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnBasicDataFile.gridx = 4;
		gbc_btnBasicDataFile.gridy = 5;
		basic_panel.add(btnOpenFile, gbc_btnBasicDataFile);

	}

	private void btnAddRow(JPanel basic_panel) {
		btnAddRow = new JButton("нов Нуклид");
		GridBagConstraints gbc_btnAddRow = new GridBagConstraints();
		gbc_btnAddRow.anchor = GridBagConstraints.EAST;
		gbc_btnAddRow.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddRow.gridx = 6;
		gbc_btnAddRow.gridy = 8;
		basic_panel.add(btnAddRow, gbc_btnAddRow);
		btnAddRow.setVisible(false);
	}
	
	private void btnTabFromFile(JPanel basic_panel) {
		btnTabFromFile = new JButton("Данни от файл");
		GridBagConstraints gbc_btnTabFromFile = new GridBagConstraints();
		gbc_btnTabFromFile.anchor = GridBagConstraints.WEST;
		gbc_btnTabFromFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnTabFromFile.gridx = 3;
		gbc_btnTabFromFile.gridy = 6;
		basic_panel.add(btnTabFromFile, gbc_btnTabFromFile);
	}
	
	private void TextFieldBasicValueFileSection(JPanel panel) {
		JLabel lblBasicValueRsltsFile = new JLabel("Път до файла");
		GridBagConstraints gbc_lblBasicValueRsltsFile = new GridBagConstraints();
		gbc_lblBasicValueRsltsFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblBasicValueRsltsFile.anchor = GridBagConstraints.EAST;
		gbc_lblBasicValueRsltsFile.gridx = 0;
		gbc_lblBasicValueRsltsFile.gridy = 5;
		panel.add(lblBasicValueRsltsFile, gbc_lblBasicValueRsltsFile);

		txtBasicValueResult = new JTextField();
		GridBagConstraints gbc_txtBasicValueResult = new GridBagConstraints();
		gbc_txtBasicValueResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBasicValueResult.insets = new Insets(0, 0, 5, 5);
		gbc_txtBasicValueResult.gridwidth = 3;
		gbc_txtBasicValueResult.gridx = 1;
		gbc_txtBasicValueResult.gridy = 5;
		panel.add(txtBasicValueResult, gbc_txtBasicValueResult);

		txtBasicValueResult.setColumns(10);

	}
	
		
	@SuppressWarnings("serial")
	public static void ViewTableInPanel(AddDobivView_ addDobivView, JPanel panel,  Boolean isNewRow) {
		
		if (scrollTablePane != null) {
			scrollTablePane.removeNotify();
		}

		if (OverallVariablesAddDobiv.getListSimbolBasikNulide().size() < OverallVariablesAddDobiv.getListNuclideToMetod().size()) {
			btnAddRow.setVisible(true);
		} else {
			btnAddRow.setVisible(false);
		}
		scrollTablePane = new JScrollPane();
		GridBagConstraints gbc_scrollTablePane = new GridBagConstraints();
		gbc_scrollTablePane.gridwidth = 7;
		gbc_scrollTablePane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollTablePane.fill = GridBagConstraints.BOTH;
		gbc_scrollTablePane.gridx = 0;
		gbc_scrollTablePane.gridy = 7;
		panel.add(scrollTablePane, gbc_scrollTablePane);

		JPanel panelTable = new JPanel();
		panelTable.removeAll();
		scrollTablePane.setViewportView(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		tabDobivs = AddDobivViewMetods.CreateTableDobivs(isNewRow);
		countRowTabDobivs = OverallVariablesAddDobiv.getDataTable().length;
		tabDobivs.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				String s1 = table.getValueAt(row, AddDobivViewMetods.getActv_value_Colum()).toString();
				String s2 = table.getValueAt(row, AddDobivViewMetods.getUncrt_Colum()).toString();

				if ((Double.parseDouble((String) s1) + (Double.parseDouble((String) s2))) == 0) {
					// setBackground(Color.BLACK);
					setForeground(Color.LIGHT_GRAY);
				} else {
					// setBackground(table.getBackground());
					setForeground(table.getForeground());
				}
				return this;
			}
		});

		tabDobivs.validate();
		tabDobivs.repaint();

		panelTable.add(tabDobivs.getTableHeader(), BorderLayout.NORTH);
		panelTable.add(tabDobivs, BorderLayout.CENTER);

//		panelTable.validate();
//		panelTable.repaint();

		scrollTablePane.validate();
		scrollTablePane.repaint();

//		panel.validate();
//		panel.repaint();
		
		
		
		addDobivView.setSize(1100, (countRowTabDobivs * rowWidth) + 340);
		addDobivView.setLocationRelativeTo(null);
		addDobivView.validate();
		addDobivView.repaint();

	}

	
	
	private void AllListenerSection(AddDobivView_ addDobivView){
		CodeDobivSection.txtDobivCodeListener(addDobivView, lblError,  txtStandartCode,  textFieldDobivDescrip, 
				 choiceIzpitProd,  choiceOIR,  choiceORHO,  choiceMetody);
		IzpitvanProduktAddDobivSection.choiceIzpitProdListener(choiceIzpitProd);	
		ChoiceORHODobivSection.choiceORHOListener(choiceORHO);
		MetodDobivSection.choiceMetodyListener(choiceMetody, lblNameMetod);
		ChoiceOIRAddDobivSection.choiceOIRListener( choiceOIR);
		btnDataFromDBaseAddDobiv_Section.btnDataFromDBaseListener(addDobivView, basic_panel, btnDataFromDBase,  choiceMetody,  choiceOIR, 
				 choiceORHO,  txtStandartCode);
		btnPaneAddDobivSection.saveButtonListener(okButton, addDobivView, choiceOIR, choiceORHO, txtBasicValueResult, choiceIzpitProd, txtStandartCode, choiceMetody, textFieldDobivDescrip, lblNameMetod);
		btnOpenFileAddDobivSection.btnOpenFileListener(  btnOpenFile,  fileChooser, txtBasicValueResult,  txtStandartCode,  choiceMetody);
		addNewRowInTableAddDobiv.btmAddRowInTableAddDobivListener(addDobivView, basic_panel,  btnAddRow);
		btnTabFromFileAddDobivSection.btnTabFromFileListener(  addDobivView,  basic_panel,  btnTabFromFile,  
				 choiceMetody,  txtStandartCode);
	}
}
