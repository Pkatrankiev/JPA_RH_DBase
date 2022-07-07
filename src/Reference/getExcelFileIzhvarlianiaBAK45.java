package Reference;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.poi.hssf.OldExcelFormatException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NotOLE2FileException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.PeriodDAO;
import DBase_Class.Ejection;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Period;
import ExcelFilesFunction.generateInformationToBAK45;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import GlobalVariable.ResourceLoader;



import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;


public class getExcelFileIzhvarlianiaBAK45 extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel textField_ObemBAK;
	static String ExcelFileBAK_Path = "";
	static String errorOfData = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("errorOfData");
	static String errorOfExcellFileFormat = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("errorOfExcellFileFormat");
	static String nameSheet = "";
	
	public getExcelFileIzhvarlianiaBAK45(JFrame parent, String frame_name, String mount_name, int godina,
			Object[][] DataValue) {
		super(parent, frame_name, true);

		ExcelFileBAK_Path = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_ExcelFileBAK_Path");
		String OpenExcelFileLabel = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_OpenExcelFileLabel");
		String IzhvarlenObemBAKLabel = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_IzhvarlenObemBAKLabel") + " " + mount_name;

		String openBtn_Text = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("openBtn_Text");
		int numMouth = PeriodDAO.getValuePeriodByPeriod(mount_name).getId_period();
		double obemBAK = getObemBAK(mount_name,  godina);
		if(numMouth<10){
		nameSheet = "0"+ numMouth;
		}else{
		nameSheet = numMouth+"";
		}
		System.out.println(nameSheet);
		setSize(450, 180);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		contentPanel.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 202, 115, 126, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel(OpenExcelFileLabel);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField(ExcelFileBAK_Path);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblObemBAK = new JLabel(IzhvarlenObemBAKLabel);
		GridBagConstraints gbc_lblObemBAK = new GridBagConstraints();
		gbc_lblObemBAK.anchor = GridBagConstraints.WEST;
		gbc_lblObemBAK.gridwidth = 3;
		gbc_lblObemBAK.insets = new Insets(0, 0, 5, 0);
		gbc_lblObemBAK.gridx = 0;
		gbc_lblObemBAK.gridy = 2;
		panel.add(lblObemBAK, gbc_lblObemBAK);

		textField_ObemBAK = new JLabel(FormatDoubleNumber.formatDoubleToString(obemBAK, 2, true));
		textField_ObemBAK.setMinimumSize(new Dimension(80, 20));
		GridBagConstraints gbc_textField_ObemBAK = new GridBagConstraints();
		gbc_textField_ObemBAK.anchor = GridBagConstraints.EAST;
		gbc_textField_ObemBAK.insets = new Insets(0, 0, 0, 5);
		gbc_textField_ObemBAK.gridx = 0;
		gbc_textField_ObemBAK.gridy = 3;
		panel.add(textField_ObemBAK, gbc_textField_ObemBAK);
		

		JLabel lblKubik = new JLabel("m3");
		GridBagConstraints gbc_lblKubik = new GridBagConstraints();
		gbc_lblKubik.anchor = GridBagConstraints.WEST;
		gbc_lblKubik.insets = new Insets(0, 0, 0, 5);
		gbc_lblKubik.gridx = 1;
		gbc_lblKubik.gridy = 3;
		panel.add(lblKubik, gbc_lblKubik);

		JButton OpenButton = new JButton(openBtn_Text);
		GridBagConstraints gbc_OpenButton = new GridBagConstraints();
		gbc_OpenButton.insets = new Insets(0, 0, 5, 0);
		gbc_OpenButton.anchor = GridBagConstraints.WEST;
		gbc_OpenButton.gridx = 2;
		gbc_OpenButton.gridy = 1;
		panel.add(OpenButton, gbc_OpenButton);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}

		});

		double obem_Bak4i5 = Double.parseDouble(textField_ObemBAK.getText());
		if(obem_Bak4i5>0){
		OpenBtnListener(OpenButton, textField);
		okBtnListener(this, okButton, textField, mount_name, godina, DataValue, obem_Bak4i5	);
		setVisible(true);
		}else{
			String noResults = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWater_OpenExcelFile_NotBAKObem") +" "+ mount_name + " " + godina;
			JOptionPane.showMessageDialog(null, noResults, errorOfData, JOptionPane.ERROR_MESSAGE);
			setVisible(false);
		}

	}

private double getObemBAK(String mount_name, int godina) {
	int idBAK4i5 = 14;
	Period per = PeriodDAO.getValuePeriodByPeriod(mount_name);
	List<Ejection> listEjection = MounthlyReferenceForMenuEjectionCalculate.generateListEjection( per,  godina);
	Obekt_na_izpitvane_request obect = Obekt_na_izpitvane_requestDAO.getValueObekt_na_izpitvaneById(idBAK4i5);
	for (Ejection ejection : listEjection) {
		if(ejection.getObect().getName_obekt_na_izpitvane().equals(obect.getName_obekt_na_izpitvane())){
			return ejection.getVolum();
		}
	}
		return 0;
	}



	public static void OpenBtnListener(JButton OpenBtn, JTextField textField) {
		OpenBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				final JFileChooser fileChooser = new JFileChooser(ExcelFileBAK_Path);

				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showOpenDialog(null);

				String fileName = (fileChooser.getSelectedFile() == null ? "" : fileChooser.getSelectedFile().toString());
				// File file = fileChooser.getSelectedFile();
				textField.setText(fileName);
				if(!fileName.isEmpty()){
				checkCorectExcellFile(fileName);
				}
			}

		});
	}

	@SuppressWarnings("resource")
	private static boolean checkCorectExcellFile(String fileName) {
		boolean fl = false;
		String checkKorektExcelFile = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_CheckKorektExcelFile");
		String notSelectCorectExcelFile = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_NotSelectCorectExcelFile");
		String fileNotFound = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_OpenExcelFileNotFound")  +" "+  fileName;
		String NotCorectNuclideList = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_OpenExcelFileNotCorectNuclideList") +" "+ nameSheet;
		
		try {
			System.out.println(fileName);
			FileInputStream fis =new FileInputStream(fileName);
			Workbook workbook;
			workbook = new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet(nameSheet);
			Cell cell = null;
			cell = sheet.getRow(5).getCell(1);
			
			fl = true;
			if(!cell.getStringCellValue().equals(checkKorektExcelFile)){
				fl = false;
				JOptionPane.showMessageDialog(null, notSelectCorectExcelFile, errorOfData, JOptionPane.ERROR_MESSAGE);
			}
			

		} catch (OldExcelFormatException | OfficeXmlFileException e) {
			ResourceLoader.appendToFile(e);
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, errorOfExcellFileFormat, errorOfData, JOptionPane.ERROR_MESSAGE);
		
		} catch (NotOLE2FileException | NullPointerException e) {
			ResourceLoader.appendToFile(e);
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, notSelectCorectExcelFile, errorOfData, JOptionPane.ERROR_MESSAGE);
		}
		catch (StringIndexOutOfBoundsException e) {
			ResourceLoader.appendToFile(e);
			JOptionPane.showMessageDialog(null, NotCorectNuclideList, errorOfData, JOptionPane.ERROR_MESSAGE);

		} catch (FileNotFoundException e) {
			ResourceLoader.appendToFile(e);
			JOptionPane.showMessageDialog(null, fileNotFound, errorOfData, JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();

		} catch (IOException e) {
			ResourceLoader.appendToFile(e);
			e.printStackTrace();
		}

		return fl;
	}

	public static void okBtnListener(JDialog dialog, JButton btn, JTextField textField, String mount_name, int godina,
			Object[][] DataValue, double obemBAK4i5 ) {
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
							
				if (checkCorectExcellFile(textField.getText())) {
						try {
							
							dialog.setVisible(false);
							new generateInformationToBAK45(textField.getText(), mount_name, godina, DataValue, obemBAK4i5);

						} catch (NumberFormatException e2) {
							ResourceLoader.appendToFile(e2);
							
						}
		
				}
			}
		});
	}

}
