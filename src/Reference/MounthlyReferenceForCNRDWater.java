package Reference;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Period;
import DBase_Class.Results;
import DBase_Class.Sample;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import GlobalVariable.ResourceLoader;
import InfoPanelInMainWindow.CreateListLeftPanelStartWindowClass;
import WindowView.RequestViewFunction;
import WindowView.TranscluentWindow;

import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Component;

public class MounthlyReferenceForCNRDWater extends JDialog {

	private static final long serialVersionUID = 7534173139838953837L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtFieldGodina;
	static String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));

	public MounthlyReferenceForCNRDWater(JFrame parent, String nameFrame) {
		super(parent, nameFrame, true);
		String[] strMounth = getStringMounth();
	
		setSize(410, 189);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 90, 154, 0 };
		gbl_contentPanel.rowHeights = new int[] { 20, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		String str_BasicLabel = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_BasicLabel");
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>" + str_BasicLabel + "</div></html>");

		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_GodinaLabel"));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_MesecLabel"));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 1;
		contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		txtFieldGodina = new JTextField(year);
		txtFieldGodina.setPreferredSize(new Dimension(20, 20));
		txtFieldGodina.setMinimumSize(new Dimension(20, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.VERTICAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 2;
		contentPanel.add(txtFieldGodina, gbc_textField);
		txtFieldGodina.setColumns(10);
		txtFieldGodina.setText(year);

		JComboBox<?> comboBox = new JComboBox<Object>(strMounth);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		contentPanel.add(comboBox, gbc_comboBox);

		JLabel lblErrorGodina = new JLabel();
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		contentPanel.add(lblErrorGodina, gbc_lblNewLabel_3);
		// lblErrorGodina.setVisible(false);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");

		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createVolumeAndViewTable(comboBox.getSelectedItem().toString(),
						Integer.parseInt(txtFieldGodina.getText()));
			}

		});
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}

		});

		txtFieldGodina.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				enterGodina(txtFieldGodina, lblErrorGodina, okButton, 2020);

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		
		
		
		
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void createVolumeAndViewTable(String mesec, int godina) {

		
		
		

		
		TranscluentWindow round = new TranscluentWindow();

		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				String[] columnNames = createColumnNameTableValue();
				
				Object[][] DataTableValue = createDataTableValue(mesec, godina);

				List<String> listNuclideSimbol = createListSimbolNuclideWithVolume(DataTableValue);
				JFrame f = new JFrame();
				String titleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MounthlyReferenceForCNRDWater_TableLabel")+mesec+" "+godina;
							
				new MounthlyReferenceForCNRDWater_Table(f, round, titleName,  mesec, godina, DataTableValue, columnNames, listNuclideSimbol);
			}
		});
		thread.start();
				
		
	}

	
	private String[] createColumnNameTableValue() {
		String[] columnNames = new String[] {
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_ProtokolN"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_ObektSample"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_Obem"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_Nuclide"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_SpActivnost"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_NeopredOtnosit"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MounthlyReferenceForCNRDWaterTable_MDA"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_IzhvActivnost"),
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
						.get("MounthlyReferenceForCNRDWaterTable_NeopredAbsol") };
		return columnNames;
	}

	private Object[][] createDataTableValue(String mesec, int godina) {
		Period mounth = PeriodDAO.getValuePeriodByPeriod(mesec);
		List<String> listAllProtokolFile = CreateListLeftPanelStartWindowClass.getListAllProtokols();
		List<Sample> listSample = SampleDAO.getListSampleByMounthlyReferenceForCNRDWater_Table(mounth, godina);
		String[] listStrNameRezervoareCNRD = { "03D-SS02T01", "03D-SS02T02", "03D-SS02T03" };
		String[] listStrNameSimbolNuclideWithMDA = { "60Co", "137Cs" };
		Double izhvarlenObem = 3.5;
		List<Obekt_na_izpitvane_sample> listObektSamp = new ArrayList<>();
		List<MounthlyReferenceForCNRDWater_ObjectClass> listTableObject = new ArrayList<>();
		for (String string : listStrNameRezervoareCNRD) {
			listObektSamp.add(Obekt_na_izpitvane_sampleDAO.getValueObekt_na_izpitvane_sampleByName(string));
		}

		for (Sample sample : listSample) {
			for (Obekt_na_izpitvane_sample sampleObekt : listObektSamp) {
				if (sample.getObekt_na_izpitvane_sample().getId_obekt_na_izpitvane() == sampleObekt
						.getId_obekt_na_izpitvane()) {
					List<Results> listResults = ResultsDAO.getListResultsFromCurentSampleInProtokol(sample);
					for (Results results : listResults) {
						MounthlyReferenceForCNRDWater_ObjectClass objectClass = new MounthlyReferenceForCNRDWater_ObjectClass();
						Double specActiv = results.getValue_result() * 1000;
						Double specActivUTC = results.getUncertainty() * 1000;
						Double MDA = results.getMda() * 1000;
						Double specActivNeopred = specActiv > 0 ? specActivUTC * 100 / specActiv : 0.0;
						Double izhvarlenaActiv = specActiv > 0 ? specActiv * izhvarlenObem : 0.0;
						Double izhvarlanaActivUTC = specActiv > 0 ? izhvarlenaActiv * specActivNeopred / 100 : 0.0;
						String nuclideSimbol = results.getNuclide().getSymbol_nuclide();

						objectClass.setProtokolN(CreateListLeftPanelStartWindowClass
								.getLabelProtokol(sample.getRequest().getRecuest_code(), listAllProtokolFile));
						objectClass.setSampleObjectName(
								sample.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane());
						objectClass.setIzhvarlenObem(izhvarlenObem);
						objectClass.setNuclideName(nuclideSimbol);
						objectClass.setSpecificActyvity(specActiv);
						objectClass.setSpecificActyvityUTC(specActivNeopred);
						objectClass.setMDA(
								Arrays.asList(listStrNameSimbolNuclideWithMDA).contains(nuclideSimbol) ? MDA : 0.0);
						objectClass.setIzhvarlanaActyvity(izhvarlenaActiv);
						objectClass.setIzhvarlanaActyvityUTC(izhvarlanaActivUTC);
						System.out.println(sample.getRequest().getRecuest_code());
						listTableObject.add(objectClass);
					}
				}
			}
		}

		Object[][] DataTableValue = new Object[listTableObject.size()][9];
		int i = 0;
		String protokolOld = "a";
		String protokolNext = "b";

		for (MounthlyReferenceForCNRDWater_ObjectClass dataOjectClass : listTableObject) {
			if (i > 0) {
				protokolOld = DataTableValue[i - 1][0].toString();
				protokolNext = dataOjectClass.getProtokolN().toString();
			}
			DataTableValue[i][0] = "";
			DataTableValue[i][1] = "";
			DataTableValue[i][2] = "";

			if (!protokolOld.contains(protokolNext)) {
				DataTableValue[i][0] = dataOjectClass.getProtokolN();
				DataTableValue[i][1] = dataOjectClass.getSampleObjectName();
				DataTableValue[i][2] = dataOjectClass.getIzhvarlenObem();
			}
			DataTableValue[i][3] = dataOjectClass.getNuclideName();
			DataTableValue[i][4] = dataOjectClass.getSpecificActyvity();
			DataTableValue[i][5] = dataOjectClass.getSpecificActyvityUTC();
			DataTableValue[i][6] = dataOjectClass.getMDA();
			DataTableValue[i][7] = dataOjectClass.getIzhvarlanaActyvity();
			DataTableValue[i][8] = dataOjectClass.getIzhvarlanaActyvityUTC();
			i++;
		}
		return DataTableValue;
	}

	

	@SuppressWarnings("unused")
	private List<String> createListSimbolNuclideWithVolume(Object[][] DataTableValue) {
		List<String> listNuclide = new ArrayList<>();
		List<String> listAllNuclide = new ArrayList<>();
		for (int i = 0; i < DataTableValue.length; i++) {
			listAllNuclide.add(DataTableValue[i][3].toString());
		}
		return removeDuplicates(listAllNuclide);

	}

	public static List<String> removeDuplicates(List<String> listAllNuclide) {

		Set<String> set = new LinkedHashSet<>();
		// Add the elements to set
		set.addAll(listAllNuclide);
		// Clear the list
		listAllNuclide.clear();
		// add the elements of set
		// with no duplicates to the list
		listAllNuclide.addAll(set);
		// return the list
		return listAllNuclide;
	}

	public static boolean enterGodina(JTextField txtFieldGodina, JLabel lblErrorGodina, JButton okButton, int refGodina) {

		boolean corectRequestCode = false;
		txtFieldGodina.setText(RequestViewFunction.checkFormatString(txtFieldGodina.getText()));
		int godina = getGodinafromTextFildGodina(txtFieldGodina);
		if (godina < refGodina) {
			txtFieldGodina.setForeground(Color.red);
			lblErrorGodina.setText(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
					.get("MounthlyReferenceForCNRDWater_GodinaLabel_Error")+ " "+refGodina);
			okButton.setEnabled(false);
			corectRequestCode = false;
		} else {

			txtFieldGodina.setForeground(Color.BLACK);
			okButton.setEnabled(true);
			// txtFieldGodina.setBorder(new LineBorder(Color.BLACK));
			lblErrorGodina.setText(" ");
			corectRequestCode = true;
		}
		return corectRequestCode;
	}

	private static int getGodinafromTextFildGodina(JTextField txtFieldGodina) {
		int godina = 0;
		try {
			godina = Integer.parseInt(txtFieldGodina.getText());

		} catch (NumberFormatException e) {
			ResourceLoader.appendToFile(e);
		}
		return godina;
	}

	public static String[] getStringMounth() {
		List<Period> listMounth = PeriodDAO.getInListPeriod_Mesechni();
		String[] strMounth = new String[listMounth.size()];
		int i = 0;
		for (Period period : listMounth) {
			strMounth[i] = period.getValue();
			i++;
		}
		return strMounth;
	}

	
	
	
	
}
