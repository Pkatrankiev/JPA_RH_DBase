package WindowView;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;

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
import AddResultViewFunction.OverallVariablesAddResults;
import DBase_Class.Users;
import Table.Add_DefaultTableModel;
import javax.swing.ScrollPaneConstants;

public class AddDobivView extends JDialog {

	
	private static final long serialVersionUID = 1L;
		
	private static JScrollPane scrollTablePane = new JScrollPane();
	private static JPanel basic_panel = null;
	private static JPanel panelTable = new JPanel();
		
	private static JTextField txtBasicValueResult= null;
	private static JTextField txtStandartCode= null;
	private static JTextField textFieldDobivDescrip= null;
	
	private static JLabel lblNameMetod= null;
	private static JLabel lblError= null;
	
	private static Choice choiceIzpitProd= null;
	private static Choice choiceOIR= null;
	private static Choice choiceORHO= null;
	private static Choice choiceMetody= null;
		
	private static JButton okButton= null;
	private static JButton btnAddRow= null;
	private static JButton btnDataFromDBase= null;
	private static JButton btnOpenFile= null;
	private static JButton btnTabFromFile= null;

	private static JTable tabDobivs= null;
	private static JTableHeader header = null;
	private static JFileChooser fileChooser = new JFileChooser();
	
	int newCountResults = 0;
	static int countRowTabDobivs = 0;
	int addCount = 0;
	static int rowWidth = 20, frameLight = 1100;
	

	
	


	// private Font font = new Font("Tahoma", Font.PLAIN, 12);
	

	public AddDobivView(JFrame parent, TranscluentWindow round, Users user) {
		super(parent, "Въвеждане на Добив", true);
	
		GetVisibleLAF(parent);
		OverallVariablesAddDobiv.clearAllVariables() ;
		AddDobivViewMetods.BasicDataInport(user);

		setSize(frameLight, (countRowTabDobivs * rowWidth) + 340);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel contentPanel = new JPanel();
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

		CodeDobivSection();

		DobivDescriptionSection();

		IzpitvanProduktSection();

		ChoiceORHO_Section();

		MetodSection();

		ChoiceOIR_Section();

		btnDataFromDBase();

		btnOpenFile();

		btnTabFromFile();

		btnAddRow();

		ButtonPanell();
		
		TextFieldBasicValueFileSection();
		
		AllListenerSection(this);
		
		
		round.StopWindow();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	private void CodeDobivSection() {

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
		basic_panel.add(lblStandartCode, gbc_lblStandartCode);

		txtStandartCode = new JTextField();
		GridBagConstraints gbc_txtStandartCode = new GridBagConstraints();
		gbc_txtStandartCode.insets = new Insets(0, 0, 5, 5);
		gbc_txtStandartCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStandartCode.gridx = 1;
		gbc_txtStandartCode.gridy = 1;
		basic_panel.add(txtStandartCode, gbc_txtStandartCode);
		txtStandartCode.setColumns(10);

	}

	private void DobivDescriptionSection() {

		JLabel lblDobivDescrip = new JLabel("Описание");
		GridBagConstraints gbc_lblDobivDescrip = new GridBagConstraints();
		gbc_lblDobivDescrip.gridwidth = 2;
		gbc_lblDobivDescrip.insets = new Insets(0, 0, 5, 5);
		gbc_lblDobivDescrip.gridx = 2;
		gbc_lblDobivDescrip.gridy = 0;
		basic_panel.add(lblDobivDescrip, gbc_lblDobivDescrip);

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

	public void IzpitvanProduktSection() {
		JLabel lblIzpitProd = new JLabel("Изпитван продукт");
		GridBagConstraints gbc_lblIzpitProd = new GridBagConstraints();
		gbc_lblIzpitProd.anchor = GridBagConstraints.EAST;
		gbc_lblIzpitProd.insets = new Insets(0, 0, 5, 5);
		gbc_lblIzpitProd.gridx = 0;
		gbc_lblIzpitProd.gridy = 3;
		basic_panel.add(lblIzpitProd, gbc_lblIzpitProd);

		choiceIzpitProd = new Choice();
		choiceIzpitProd.setBackground(Color.WHITE);
		choiceIzpitProd.setPreferredSize(new Dimension(205, 20));
		GridBagConstraints gbc_choicePokazatel = new GridBagConstraints();
		gbc_choicePokazatel.fill = GridBagConstraints.HORIZONTAL;
		gbc_choicePokazatel.gridwidth = 2;
		gbc_choicePokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_choicePokazatel.gridx = 1;
		gbc_choicePokazatel.gridy = 3;
		basic_panel.add(choiceIzpitProd, gbc_choicePokazatel);

	}

	private void ChoiceORHO_Section() {
	JLabel lblNewLabel_1 = new JLabel("Извършил Хим. обработ.");
	GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
	gbc_lblNewLabel_1.gridx = 5;
	gbc_lblNewLabel_1.gridy = 1;
	basic_panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

	choiceORHO = new Choice();
	GridBagConstraints gbc_choiceORHO = new GridBagConstraints();
	gbc_choiceORHO.fill = GridBagConstraints.HORIZONTAL;
	gbc_choiceORHO.insets = new Insets(0, 0, 5, 0);
	gbc_choiceORHO.gridx = 6;
	gbc_choiceORHO.gridy = 1;
	basic_panel.add(choiceORHO, gbc_choiceORHO);
	
	
}
	
	private void MetodSection() {
		Dimension dim = new Dimension(550, 14);
		JLabel lblMetody = new JLabel("Метод");
		GridBagConstraints gbc_lblMetody = new GridBagConstraints();
		gbc_lblMetody.anchor = GridBagConstraints.EAST;
		gbc_lblMetody.insets = new Insets(0, 0, 5, 5);
		gbc_lblMetody.gridx = 0;
		gbc_lblMetody.gridy = 4;
		basic_panel.add(lblMetody, gbc_lblMetody);

		choiceMetody = new Choice();
		GridBagConstraints gbc_choiceMetody = new GridBagConstraints();
		gbc_choiceMetody.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceMetody.gridwidth = 2;
		gbc_choiceMetody.insets = new Insets(0, 0, 5, 5);
		gbc_choiceMetody.gridx = 1;
		gbc_choiceMetody.gridy = 4;
		basic_panel.add(choiceMetody, gbc_choiceMetody);
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

	private void ChoiceOIR_Section() {

		JLabel lblNewLabel_2 = new JLabel("Извършил анализа");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 5;
		gbc_lblNewLabel_2.gridy = 3;
		basic_panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		choiceOIR = new Choice();
		GridBagConstraints gbc_choiceOIR = new GridBagConstraints();
		gbc_choiceOIR.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceOIR.insets = new Insets(0, 0, 5, 0);
		gbc_choiceOIR.gridx = 6;
		gbc_choiceOIR.gridy = 3;
		basic_panel.add(choiceOIR, gbc_choiceOIR);

	}

	private void btnDataFromDBase() {

		btnDataFromDBase = new JButton("Данни от базата");
		btnDataFromDBase.setForeground(OverallVariablesAddResults.getColorFromDBase());
		GridBagConstraints gbc_btnCreadTable = new GridBagConstraints();
		gbc_btnCreadTable.gridwidth = 2;
		gbc_btnCreadTable.anchor = GridBagConstraints.EAST;
		gbc_btnCreadTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreadTable.gridx = 0;
		gbc_btnCreadTable.gridy = 6;
		basic_panel.add(btnDataFromDBase, gbc_btnCreadTable);
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

	private void btnOpenFile() {
		btnOpenFile = new JButton("Отвори");
		GridBagConstraints gbc_btnBasicDataFile = new GridBagConstraints();
		gbc_btnBasicDataFile.anchor = GridBagConstraints.WEST;
		gbc_btnBasicDataFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnBasicDataFile.gridx = 4;
		gbc_btnBasicDataFile.gridy = 5;
		basic_panel.add(btnOpenFile, gbc_btnBasicDataFile);

	}

	private void btnAddRow() {
		btnAddRow = new JButton("нов Нуклид");
		GridBagConstraints gbc_btnAddRow = new GridBagConstraints();
		gbc_btnAddRow.anchor = GridBagConstraints.EAST;
		gbc_btnAddRow.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddRow.gridx = 6;
		gbc_btnAddRow.gridy = 8;
		basic_panel.add(btnAddRow, gbc_btnAddRow);
		btnAddRow.setVisible(false);
	}
	
	private void btnTabFromFile() {
		btnTabFromFile = new JButton("Данни от файл");
		btnTabFromFile.setForeground(OverallVariablesAddResults.getColorFromFile());
		GridBagConstraints gbc_btnTabFromFile = new GridBagConstraints();
		gbc_btnTabFromFile.anchor = GridBagConstraints.WEST;
		gbc_btnTabFromFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnTabFromFile.gridx = 3;
		gbc_btnTabFromFile.gridy = 6;
		basic_panel.add(btnTabFromFile, gbc_btnTabFromFile);
	}
	
	private void TextFieldBasicValueFileSection() {
		JLabel lblBasicValueRsltsFile = new JLabel("Път до файла");
		GridBagConstraints gbc_lblBasicValueRsltsFile = new GridBagConstraints();
		gbc_lblBasicValueRsltsFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblBasicValueRsltsFile.anchor = GridBagConstraints.EAST;
		gbc_lblBasicValueRsltsFile.gridx = 0;
		gbc_lblBasicValueRsltsFile.gridy = 5;
		basic_panel.add(lblBasicValueRsltsFile, gbc_lblBasicValueRsltsFile);

		txtBasicValueResult = new JTextField();
		GridBagConstraints gbc_txtBasicValueResult = new GridBagConstraints();
		gbc_txtBasicValueResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBasicValueResult.insets = new Insets(0, 0, 5, 5);
		gbc_txtBasicValueResult.gridwidth = 3;
		gbc_txtBasicValueResult.gridx = 1;
		gbc_txtBasicValueResult.gridy = 5;
		basic_panel.add(txtBasicValueResult, gbc_txtBasicValueResult);

		txtBasicValueResult.setColumns(10);

	}
	
		
	
	public static void ViewTableInPanel(AddDobivView addDobivView,  Boolean isNewRow) {
		
		if (scrollTablePane != null) {
			scrollTablePane.removeNotify();
		}

		
//		scrollTablePane = new JScrollPane();
		GridBagConstraints gbc_scrollTablePane = new GridBagConstraints();
		gbc_scrollTablePane.gridwidth = 7;
		gbc_scrollTablePane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollTablePane.fill = GridBagConstraints.BOTH;
		gbc_scrollTablePane.gridx = 0;
		gbc_scrollTablePane.gridy = 7;
		basic_panel.add(scrollTablePane, gbc_scrollTablePane);

		
		panelTable.removeAll();
		scrollTablePane.setViewportView(panelTable);
		panelTable.setBorder(new MatteBorder(1, 1, 0, 0, Color.GRAY));
		panelTable.setLayout(new BorderLayout(0, 0));

		tabDobivs = AddDobivViewMetods.CreateTableDobivs(isNewRow, btnAddRow);
		countRowTabDobivs = OverallVariablesAddDobiv.getDataTable().length;
		
		header = tabDobivs.getTableHeader();
		header.setForeground(OverallVariablesAddResults.getColorFromFile());
		if(Add_DefaultTableModel.getFromDBase()){
			header.setForeground(OverallVariablesAddResults.getColorFromDBase());
		}
		
		SwingUtilities.updateComponentTreeUI(addDobivView);
		

//		tabDobivs.validate();
//		tabDobivs.repaint();

		panelTable.add(header, BorderLayout.NORTH);
		panelTable.add(tabDobivs, BorderLayout.CENTER);

//		panelTable.validate();
//		panelTable.repaint();
//
//		scrollTablePane.validate();
//		scrollTablePane.repaint();
//
//		panel.validate();
//		panel.repaint();
//		
		
		
		addDobivView.setSize(frameLight, (countRowTabDobivs * rowWidth) + 340);
		addDobivView.setLocationRelativeTo(null);
		addDobivView.validate();
		addDobivView.repaint();

	}

	
	
	private void AllListenerSection(AddDobivView addDobivView){
		CodeDobivSection.txtDobivCodeListener(addDobivView, lblError,  txtStandartCode,  textFieldDobivDescrip, 
				 choiceIzpitProd,  choiceOIR,  choiceORHO,  choiceMetody);
		IzpitvanProduktAddDobivSection.choiceIzpitProdListener(choiceIzpitProd);	
		ChoiceORHODobivSection.choiceORHOListener(choiceORHO);
		MetodDobivSection.choiceMetodyListener(choiceMetody, lblNameMetod);
		ChoiceOIRAddDobivSection.choiceOIRListener( choiceOIR);
		btnDataFromDBaseAddDobiv_Section.btnDataFromDBaseListener(addDobivView, basic_panel, btnDataFromDBase,  choiceMetody,  choiceOIR, 
				 choiceORHO,  txtStandartCode);
		btnPaneAddDobivSection.saveButtonListener(basic_panel,okButton, addDobivView, choiceOIR, choiceORHO, txtBasicValueResult, choiceIzpitProd, txtStandartCode, choiceMetody, textFieldDobivDescrip, lblNameMetod);
		btnOpenFileAddDobivSection.btnOpenFileListener(  btnOpenFile,  fileChooser, txtBasicValueResult,  txtStandartCode,  choiceMetody);
		addNewRowInTableAddDobiv.btmAddRowInTableAddDobivListener(addDobivView, btnAddRow);
		btnTabFromFileAddDobivSection.btnTabFromFileListener(  addDobivView,  basic_panel,  btnTabFromFile,  
				 choiceMetody, choiceOIR,   txtStandartCode);
	}
	
	private void GetVisibleLAF(final JFrame win) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(win);
			this.pack();
		} catch (Exception ex) {
			Logger.getLogger(JFileChooser.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
