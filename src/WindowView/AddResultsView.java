package WindowView;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import DBase_Class.Users;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import Table.Add_DefaultTableModel;
import AddResultViewFunction.SampleCodeSection;
import AddResultViewFunction.OverallVariablesAddResults;
import AddResultViewFunction.AddResultViewMetods;
import AddResultViewFunction.RequestCodeSection;
import AddResultViewFunction.PokazatelSection;
import AddResultViewFunction.ChoiceORHO_Section;
import AddResultViewFunction.MetodSection;
import AddResultViewFunction.ChoiceOIR_Section;
import AddResultViewFunction.DobivSection;
import AddResultViewFunction.btnDataFromDBaseSection;
import AddResultViewFunction.btnOpenFileSection;
import AddResultViewFunction.btnTabFromFileSection;
import AddResultViewFunction.btnAddRowSection;
import AddResultViewFunction.LabelStoinostiFromDobivSection;
import AddResultViewFunction.ButtonPanellListener;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;

public class AddResultsView extends JDialog {

	private static final long serialVersionUID = 1L;

	private static JScrollPane scrollTablePane = new JScrollPane();
	private static JPanel basic_panel = null;
	private static JPanel panelTable = new JPanel();

	private static JTextField txtBasicValueResult = null;
	private static JTextField txtRqstCode = null;

	private static JLabel lblNameMetod = null;
	private static JLabel lbl_StoinostiFromDobiv = null;
	private static JLabel lblError = null;
	private static JLabel lbl_OI_Sample = null;
	private static JLabel lblSampleDescript = null;
	
	
	private static Choice choiceSmplCode = null;
	private static Choice choicePokazatel = null;
	private static Choice choiceOIR = null;
	private static Choice choiceORHO = null;
	private static Choice choiceMetody = null;
	private static Choice choiceDobiv = null;

	
	private static JButton btnAddRow = null;
	private static JButton btnDataFromDBase = null;
	private static JButton btnOpenFile = null;
	private static JButton btnTabFromFile = null;
	private static JButton saveButton = null;
	private static JButton btnAddDobiv =null;
	
	private static JFileChooser fileChooser = new JFileChooser();
	private static JTable tableResults = null;
	private static JTableHeader header = null;

	
	int newCountResults = 0;
	static int countRowTabResults = 0;
	int addCount = 0;
	static int rowWidth = 20, frameLight = 1090;
	
	

	public AddResultsView(JFrame parent, TranscluentWindow round, Users user) {
		super(parent, ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("AddResultsView_TitleName"), true);
//		setResizable(false);
		
		GetVisibleLAF(parent);
		OverallVariablesAddResults.clearAllVariables();
		AddResultViewMetods.BasicDataInport(user);
		
		setSize(frameLight, (countRowTabResults * rowWidth) + 340);
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
		scrollPane.setColumnHeaderView(basic_panel);
		GridBagLayout gbl_basic_panel = new GridBagLayout();
		gbl_basic_panel.columnWidths = new int[] { 10, 91, 111, 147, 138, 166, 182, 197, 0 };
		gbl_basic_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_basic_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_basic_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		basic_panel.setLayout(gbl_basic_panel);
	
		SampleCodeSection();
		
		RequestCodeSection();

		BasicValueFileSection();
		
		LabelStoinostiFromDobiv();
		
		DobivSection();

		PokazatelSection();

		ChoiceORHO_Section();

		MetodSection();

		ChoiceOIR_Section();
				
		btnDataFromDBaseSection();

		btnOpenFileSection();

		btnTabFromFile();

		btnAddRow();

		ButtonPanell();
		
		AllListenerSection(this);
		
		round.StopWindow();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);

	}


	private void RequestCodeSection() {

		lblError = new JLabel();
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.anchor = GridBagConstraints.WEST;
		gbc_lblError.gridwidth = 2;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 2;
		gbc_lblError.gridy = 2;
		basic_panel.add(lblError, gbc_lblError);
		lblError.setVisible(false);

		JLabel lblRqstCode = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("AddResultsView_LabelText_CodeForRequest"));
		GridBagConstraints gbc_lblRqstCode = new GridBagConstraints();
		gbc_lblRqstCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRqstCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblRqstCode.gridx = 2;
		gbc_lblRqstCode.gridy = 0;
		basic_panel.add(lblRqstCode, gbc_lblRqstCode);

		txtRqstCode = new JTextField();
		GridBagConstraints gbc_txtRqstCode = new GridBagConstraints();
		gbc_txtRqstCode.insets = new Insets(0, 0, 5, 5);
		gbc_txtRqstCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRqstCode.gridx = 2;
		gbc_txtRqstCode.gridy = 1;
		basic_panel.add(txtRqstCode, gbc_txtRqstCode);
		txtRqstCode.setColumns(10);
	

	}
	
	private void SampleCodeSection() {

		JLabel lblSmplCode = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("AddResultsView_LabelText_NumberForSample"));
		GridBagConstraints gbc_lblSmplCode = new GridBagConstraints();
		gbc_lblSmplCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblSmplCode.gridx = 3;
		gbc_lblSmplCode.gridy = 0;
		basic_panel.add(lblSmplCode, gbc_lblSmplCode);

		lbl_OI_Sample = new JLabel();
		GridBagConstraints gbc_lbl_OI_Sample = new GridBagConstraints();
		gbc_lbl_OI_Sample.anchor = GridBagConstraints.WEST;
		gbc_lbl_OI_Sample.gridwidth = 2;
		gbc_lbl_OI_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_OI_Sample.gridx = 4;
		gbc_lbl_OI_Sample.gridy = 1;
		basic_panel.add(lbl_OI_Sample, gbc_lbl_OI_Sample);
		lbl_OI_Sample.setVisible(false);

		lblSampleDescript = new JLabel();
		GridBagConstraints gbc_lblSampleDescript = new GridBagConstraints();
		gbc_lblSampleDescript.anchor = GridBagConstraints.WEST;
		gbc_lblSampleDescript.gridwidth = 2;
		gbc_lblSampleDescript.insets = new Insets(0, 0, 5, 5);
		gbc_lblSampleDescript.gridx = 4;
		gbc_lblSampleDescript.gridy = 3;
		basic_panel.add(lblSampleDescript, gbc_lblSampleDescript);
		lblSampleDescript.setVisible(false);

		choiceSmplCode = new Choice();
		GridBagConstraints gbc_choiceSmplCode = new GridBagConstraints();
		gbc_choiceSmplCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceSmplCode.insets = new Insets(0, 0, 5, 5);
		gbc_choiceSmplCode.gridx = 3;
		gbc_choiceSmplCode.gridy = 1;
		basic_panel.add(choiceSmplCode, gbc_choiceSmplCode);
		choiceSmplCode.setEnabled(false);
		

	}

	private void PokazatelSection() {

		JLabel lblPokazatel = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("AddResultsView_LabelText_Pokazatel"));
		GridBagConstraints gbc_lblPokazatel = new GridBagConstraints();
		gbc_lblPokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPokazatel.gridx = 1;
		gbc_lblPokazatel.gridy = 3;
		basic_panel.add(lblPokazatel, gbc_lblPokazatel);

		choicePokazatel = new Choice();
		choicePokazatel.setBackground(Color.WHITE);
		choicePokazatel.setPreferredSize(new Dimension(205, 20));
		GridBagConstraints gbc_choicePokazatel = new GridBagConstraints();
		gbc_choicePokazatel.fill = GridBagConstraints.HORIZONTAL;
		gbc_choicePokazatel.gridwidth = 2;
		gbc_choicePokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_choicePokazatel.gridx = 2;
		gbc_choicePokazatel.gridy = 3;
		basic_panel.add(choicePokazatel, gbc_choicePokazatel);
		choicePokazatel.add("");
		
	}

	private void ChoiceORHO_Section() {
		JLabel lblNewLabel_1 = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("LabelText_IzvarshilHimObrabotka"));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 6;
		gbc_lblNewLabel_1.gridy = 1;
		basic_panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		choiceORHO = new Choice();
		GridBagConstraints gbc_choiceORHO = new GridBagConstraints();
		gbc_choiceORHO.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceORHO.insets = new Insets(0, 0, 5, 0);
		gbc_choiceORHO.gridx = 7;
		gbc_choiceORHO.gridy = 1;
		basic_panel.add(choiceORHO, gbc_choiceORHO);
		
		

	}

	private void MetodSection() {

		lblNameMetod = new JLabel();
		Dimension dim = new Dimension(550, 14);

		lblNameMetod.setPreferredSize(dim);
		lblNameMetod.setMinimumSize(dim);
		lblNameMetod.setMaximumSize(dim);
		lblNameMetod.setHorizontalAlignment(SwingConstants.LEFT);

		GridBagConstraints gbc_lblNameMetod = new GridBagConstraints();
		gbc_lblNameMetod.anchor = GridBagConstraints.WEST;
		gbc_lblNameMetod.gridwidth = 4;
		gbc_lblNameMetod.insets = new Insets(0, 0, 5, 0);
		gbc_lblNameMetod.gridx = 4;
		gbc_lblNameMetod.gridy = 4;
		basic_panel.add(lblNameMetod, gbc_lblNameMetod);

		JLabel lblMetody = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("LabelText_Metod"));
		GridBagConstraints gbc_lblMetody = new GridBagConstraints();
		gbc_lblMetody.insets = new Insets(0, 0, 5, 5);
		gbc_lblMetody.gridx = 1;
		gbc_lblMetody.gridy = 4;
		basic_panel.add(lblMetody, gbc_lblMetody);

		choiceMetody = new Choice();
		GridBagConstraints gbc_choiceMetody = new GridBagConstraints();
		gbc_choiceMetody.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceMetody.gridwidth = 2;
		gbc_choiceMetody.insets = new Insets(0, 0, 5, 5);
		gbc_choiceMetody.gridx = 2;
		gbc_choiceMetody.gridy = 4;
		basic_panel.add(choiceMetody, gbc_choiceMetody);
		choiceMetody.add(" ");
		choiceMetody.select(" ");

	}

	private void ChoiceOIR_Section() {
		JLabel lblNewLabel_2 = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("LabelText_IzvarshilAnaliza"));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 6;
		gbc_lblNewLabel_2.gridy = 3;
		basic_panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		choiceOIR = new Choice();
		GridBagConstraints gbc_choiceOIR = new GridBagConstraints();
		gbc_choiceOIR.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceOIR.insets = new Insets(0, 0, 5, 0);
		gbc_choiceOIR.gridx = 7;
		gbc_choiceOIR.gridy = 3;
		basic_panel.add(choiceOIR, gbc_choiceOIR);
		ChoiceOIR_Section.setItemInChoiceOIR(choiceOIR);
		
	
	
	}

	private void btnDataFromDBaseSection() {

		btnDataFromDBase = new JButton(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("BtnText_DataOfDBase"));
		btnDataFromDBase.setForeground(OverallVariablesAddResults.getColorFromDBase());
		GridBagConstraints gbc_btnDataFromDBase = new GridBagConstraints();
		gbc_btnDataFromDBase.gridwidth = 2;
		gbc_btnDataFromDBase.anchor = GridBagConstraints.EAST;
		gbc_btnDataFromDBase.insets = new Insets(0, 0, 5, 5);
		gbc_btnDataFromDBase.gridx = 1;
		gbc_btnDataFromDBase.gridy = 6;
		basic_panel.add(btnDataFromDBase, gbc_btnDataFromDBase);
				
	}
		
	private void btnOpenFileSection() {
		btnOpenFile = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("openBtn_Text"));
		GridBagConstraints gbc_btnBasicDataFile = new GridBagConstraints();
		gbc_btnBasicDataFile.anchor = GridBagConstraints.WEST;
		gbc_btnBasicDataFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnBasicDataFile.gridx = 4;
		gbc_btnBasicDataFile.gridy = 5;
		basic_panel.add(btnOpenFile, gbc_btnBasicDataFile);
		
		
	}
	
	private void BasicValueFileSection() {
		JLabel lblBasicValueRsltsFile = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("LabelText_PatToFile"));
		GridBagConstraints gbc_lblBasicValueRsltsFile = new GridBagConstraints();
		gbc_lblBasicValueRsltsFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblBasicValueRsltsFile.anchor = GridBagConstraints.EAST;
		gbc_lblBasicValueRsltsFile.gridx = 1;
		gbc_lblBasicValueRsltsFile.gridy = 5;
		basic_panel.add(lblBasicValueRsltsFile, gbc_lblBasicValueRsltsFile);

		txtBasicValueResult = new JTextField();
		GridBagConstraints gbc_txtBasicValueResult = new GridBagConstraints();
		gbc_txtBasicValueResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBasicValueResult.insets = new Insets(0, 0, 5, 5);
		gbc_txtBasicValueResult.gridwidth = 2;
		gbc_txtBasicValueResult.gridx = 2;
		gbc_txtBasicValueResult.gridy = 5;
		basic_panel.add(txtBasicValueResult, gbc_txtBasicValueResult);

		txtBasicValueResult.setColumns(10);

	}
	
	private void btnTabFromFile() {
//		Icon icon = new ColorIcon(Color.RED, 50, 50);
		btnTabFromFile = new JButton(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("BtnText_DataOfFile"));
		btnTabFromFile.setForeground(OverallVariablesAddResults.getColorFromFile());
		btnTabFromFile.setOpaque(true);
		GridBagConstraints gbc_btnTabFromFile = new GridBagConstraints();
		gbc_btnTabFromFile.anchor = GridBagConstraints.WEST;
		gbc_btnTabFromFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnTabFromFile.gridx = 4;
		gbc_btnTabFromFile.gridy = 6;
		basic_panel.add(btnTabFromFile, gbc_btnTabFromFile);
		
		
	}
	
	private void btnAddRow() {

		btnAddRow = new JButton(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("newNuclideBtn_Text"));
		GridBagConstraints gbc_btnAddRow = new GridBagConstraints();
		gbc_btnAddRow.anchor = GridBagConstraints.EAST;
		gbc_btnAddRow.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddRow.gridx = 7;
		gbc_btnAddRow.gridy = 8;
		basic_panel.add(btnAddRow, gbc_btnAddRow);
		btnAddRow.setVisible(false);
		
		
	}
	
	private void DobivSection() {
		JLabel lblDobiv = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("LabelText_Dobiv"));
		GridBagConstraints gbc_lblDobiv = new GridBagConstraints();
		gbc_lblDobiv.anchor = GridBagConstraints.EAST;
		gbc_lblDobiv.insets = new Insets(0, 0, 5, 5);
		gbc_lblDobiv.gridx = 6;
		gbc_lblDobiv.gridy = 5;
		basic_panel.add(lblDobiv, gbc_lblDobiv);

		choiceDobiv = new Choice();
		GridBagConstraints gbc_choiceDobiv = new GridBagConstraints();
		gbc_choiceDobiv.insets = new Insets(0, 0, 5, 0);
		gbc_choiceDobiv.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceDobiv.gridx = 7;
		gbc_choiceDobiv.gridy = 5;
		basic_panel.add(choiceDobiv, gbc_choiceDobiv);
		choiceDobiv.addItem("");
		
		
			btnAddDobiv = new JButton(
					ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("AddDobiveFrame_TitleName"));
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.anchor = GridBagConstraints.EAST;
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton.gridx = 7;
			gbc_btnNewButton.gridy = 4;
			basic_panel.add(btnAddDobiv, gbc_btnNewButton);
		
		
	}
	
	private void LabelStoinostiFromDobiv() {
		lbl_StoinostiFromDobiv = new JLabel();
		GridBagConstraints gbc_lbl_StoinostiFromDobiv = new GridBagConstraints();
		gbc_lbl_StoinostiFromDobiv.anchor = GridBagConstraints.EAST;
		gbc_lbl_StoinostiFromDobiv.gridwidth = 2;
		gbc_lbl_StoinostiFromDobiv.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_StoinostiFromDobiv.gridx = 6;
		gbc_lbl_StoinostiFromDobiv.gridy = 6;
		basic_panel.add(lbl_StoinostiFromDobiv, gbc_lbl_StoinostiFromDobiv);

		
	}
	
	public static void ViewTableInPanel(AddResultsView addResultsViewWithTable, Boolean isNewRow) {

		if (scrollTablePane != null) {
			scrollTablePane.removeNotify();
		}
		
//		scrollTablePane = new JScrollPane();
		
		GridBagConstraints gbc_scrollTablePane = new GridBagConstraints();
		gbc_scrollTablePane.gridwidth = 7;
		gbc_scrollTablePane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollTablePane.fill = GridBagConstraints.BOTH;
		gbc_scrollTablePane.gridx = 1;
		gbc_scrollTablePane.gridy = 7;
		basic_panel.add(scrollTablePane, gbc_scrollTablePane);

		
		panelTable.removeAll();
		scrollTablePane.setViewportView(panelTable);
		panelTable.setBorder(new MatteBorder(1, 1, 0, 0, Color.GRAY));
		panelTable.setLayout(new BorderLayout(0, 0));

	
			
		tableResults = AddResultViewMetods.CreateTableResults(isNewRow,  btnAddRow,  header, 
				choiceSmplCode);
		countRowTabResults = OverallVariablesAddResults.getDataTable().length;
//		tableResults.setDefaultRenderer(Object.class, new Add_DefaultTableCellRenderer());

		header = tableResults.getTableHeader();
	
		header.setForeground(OverallVariablesAddResults.getColorFromFile());
		if(Add_DefaultTableModel.getFromDBase()){
			header.setForeground(OverallVariablesAddResults.getColorFromDBase());
		}
		SwingUtilities.updateComponentTreeUI(addResultsViewWithTable);
		panelTable.add(header, BorderLayout.NORTH);
		panelTable.add(tableResults, BorderLayout.CENTER);
		
//		panelTable.validate();
//		panelTable.repaint();
//
//		basic_panel.validate();
//		basic_panel.repaint();
			
		addResultsViewWithTable.setSize(frameLight, (countRowTabResults * rowWidth) + 340);
		addResultsViewWithTable.setLocationRelativeTo(null);
		addResultsViewWithTable.validate();
		addResultsViewWithTable.repaint();
		
		}
	
	
	private void ButtonPanell() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		saveButton = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("saveBtn_Text"));
		buttonPane.add(saveButton);
		
		JButton cancelButton = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				setVisible(false);
			}
		});
		// cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
	
	
	
	private void AllListenerSection(AddResultsView addResultsViewWithTable){
		RequestCodeSection.txtRqstCodeListener(addResultsViewWithTable,lblError,txtRqstCode,choiceSmplCode);
		SampleCodeSection.choiceSmplCodeListener(lbl_OI_Sample, lblSampleDescript,choiceSmplCode);
		PokazatelSection.PokazatelSectionListener(choicePokazatel, choiceSmplCode);
		ChoiceORHO_Section.ChoiceORHOListener(choiceORHO);
		MetodSection.choiceMetodyListener(choiceMetody, lblNameMetod, choicePokazatel, choiceDobiv, lbl_StoinostiFromDobiv);
		ChoiceOIR_Section.choiceOIRListener(choiceOIR);
		btnDataFromDBaseSection.btnDataFromDBaseListener(addResultsViewWithTable, basic_panel, btnDataFromDBase,  choiceMetody, choiceDobiv, 
				 choiceSmplCode,  choicePokazatel, choiceOIR,  choiceORHO, lbl_StoinostiFromDobiv);
		btnOpenFileSection.btnOpenFileListener(btnOpenFile,  fileChooser, txtRqstCode,
				choiceSmplCode, txtBasicValueResult, choiceMetody, choicePokazatel);
		btnTabFromFileSection.btnTabFromFileListener(addResultsViewWithTable, basic_panel,  btnTabFromFile,  choiceMetody, txtRqstCode,
				choiceSmplCode,  choiceOIR,  choicePokazatel, choiceDobiv, lbl_StoinostiFromDobiv);
		btnAddRowSection.btmAddRowListener(addResultsViewWithTable, btnAddRow, choicePokazatel);
		LabelStoinostiFromDobivSection.LabelStoinFromDobivListener(lbl_StoinostiFromDobiv, choiceDobiv);
		ButtonPanellListener.saveButtonListener(addResultsViewWithTable,  basic_panel,  saveButton, txtRqstCode,  choicePokazatel,  choiceMetody,
				 choiceOIR,  choiceORHO, choiceDobiv,  choiceSmplCode, txtBasicValueResult);
		DobivSection.choiceDobivListener(choiceDobiv, lbl_StoinostiFromDobiv,btnAddDobiv);
		
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

