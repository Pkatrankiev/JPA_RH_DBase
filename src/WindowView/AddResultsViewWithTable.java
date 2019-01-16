
package WindowView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Aplication.AplicantDAO;
import Aplication.DimensionDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.Metody_to_PokazatelDAO;
import Aplication.NuclideDAO;
import Aplication.Nuclide_to_PokazatelDAO;
import Aplication.PostDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import Aplication.UsersDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Metody_to_Pokazatel;
import DBase_Class.Nuclide;
import DBase_Class.Nuclide_to_Pokazatel;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import Table.Table_Results_List;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import java.awt.Panel;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.Point;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class AddResultsViewWithTable extends JDialog {

	private static Users user = null;

	private final JPanel contentPanel = new JPanel();
	private static JTextField txtBasicValueResult;
	private JTextField txtRqstCode;
	private JLabel lblNameMetod;
	// private static Sample sample1 = null;
	// private List_izpitvan_pokazatel pokazatel = null;
	private static Choice choicePokazatel;
	private static Choice choiceOIR;
	private static Choice choiceORHO;
	private static Choice choiceMetody;
	private Choice choiceSmplCode;
	private Metody selectedMetod = null;
	private static List<Sample> listSample;
	private static List<Users> list_Users;
	private static List<String> list_UsersNameFamily;
	private static List<String> list_UsersNameFamilyOIR;
	private static List<String> list_UsersNameFamilyORHO;
	private static List<IzpitvanPokazatel> listPokazatel;

	private Request choiseRequest;

	int newCountResults = 0;
	int countRowTabResults = 0;
	int addCount = 0;
	int rowWidth = 20;
	Boolean flagNotReadListPokazatel = true;
	Boolean flagNotReadListMetody = true;
	boolean flagIncertedFile;

	private static JTable tabResults;
	private static String[] masuveSimbolNuclide;
	private static String[] values_Razmernosti;
	private static String[] values_Dimension;
	private static Object[][] dataTable;
	private static int tbl_Colum = 13;
	private static int nuclide_Colum = 0;
	private static int actv_value_Colum = 1;
	private static int uncrt_Colum = 2;
	private static int mda_Colum = 3;
	private static int razm_Colum = 4;
	private static int sigma_Colum = 5;
	private static int qunt_Colum = 6;
	private static int dimen_Colum = 7;
	private static int dateHimObr_Colum = 8;
	private static int dateAnaliz_Colum = 9;
	private static int in_Prot_Colum = 10;
	private static int check_Colum = 11;
	private static int rsult_Id_Colum = 12;

	private Font font = new Font("Tahoma", Font.PLAIN, 12);
	private JPanel basic_panel;
	private JScrollPane scrollTablePane;

	public AddResultsViewWithTable(JFrame parent, TranscluentWindow round, Users user) {
		super(parent, "Въвеждане на Резултати", true);
		// listDimension = DimensionDAO.getInListAllValueDimension();
		// listRazmernost = RazmernostiDAO.getInListAllValueRazmernosti();
		list_Users = UsersDAO.getInListAllValueUsers();
		list_UsersNameFamily = UsersDAO.getListStringAllName_FamilyUsers(null);
		list_UsersNameFamilyOIR = UsersDAO.getListStringAllName_FamilyUsers(PostDAO.getValuePostByName("ОИР"));
		list_UsersNameFamilyORHO = UsersDAO.getListStringAllName_FamilyUsers(PostDAO.getValuePostByName("ОРХО"));
		// listNuclide = NuclideDAO.getInListAllValueNuclide();
		listSample = new ArrayList<Sample>();

		values_Razmernosti = RazmernostiDAO.getMasiveStringAllValueRazmernosti();
		values_Dimension = DimensionDAO.getMasiveStringAllValueDimension();

		setSize(960, (countRowTabResults * rowWidth) + 340);
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
		gbl_basic_panel.columnWidths = new int[] { 91, 111, 147, 138, 182, 197, 0 };
		gbl_basic_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_basic_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_basic_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		basic_panel.setLayout(gbl_basic_panel);

		RequestCodeSection(basic_panel);

		SampleCodeSection(basic_panel);

		PokazatelSection(basic_panel);

		ChoiceORHO_Section(basic_panel);

		MetodSection(basic_panel);

		ChoiceOIR_Section(basic_panel);

		CreadTableButton(basic_panel);

		BasicValueFileSection(basic_panel);

		ButtonTableFromFile(basic_panel);

		addRowButtonSection(basic_panel);

		DobivSection(basic_panel);

		ButtonPanell();
		round.StopWindow();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	private  Results[] creadMasiveFromResultsObjects_ChoiseSample(Sample sample) {
		List<Results> ListResultsFromSample = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
		List<Results> choiceResults = new ArrayList<Results>();
		List_izpitvan_pokazatel pokazatel = getPokazatelObjectFromChoicePokazatel();
		for (Results result : ListResultsFromSample) {
			if (result.getPokazatel().getId_pokazatel() == pokazatel.getId_pokazatel()
					&& result.getMetody().getId_metody() == selectedMetod.getId_metody()) {
				choiceResults.add(result);
			}
		}
		Results[] masiveResults = new Results[choiceResults.size()];
		int i = 0;
		for (Results results : choiceResults) {
			masiveResults[i] = results;
			i++;
		}
		return masiveResults;
	}

	private  List<Results> creadListResultsObjects_ChoiseSample(Sample sample) {
		List<Results> ListResultsFromSample = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
		List<Results> choiceResults = new ArrayList<Results>();
		List_izpitvan_pokazatel pokazatel = getPokazatelObjectFromChoicePokazatel();
		for (Results result : ListResultsFromSample) {
			if (result.getPokazatel().getId_pokazatel() == pokazatel.getId_pokazatel()
					&& result.getMetody().getId_metody() == selectedMetod.getId_metody()) {
				choiceResults.add(result);
			}
		}
	
		return choiceResults;
	}
	
	private void ButtonPanell() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("Запис");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateIzpitvanPokazatelObjectInDBase();
				List<Results> listResultsForSave = creadListFromResultObjectForSave(getSampleObjectFromChoiceSampleCode());
				for (Results results : listResultsForSave) {
					saveResultsObjectInDBase(results);
				}
				
			}

			
		});
		// okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		// getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			}
		});
		// cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
	private void updateIzpitvanPokazatelObjectInDBase() {
		IzpitvanPokazatel izpivanPokazatel = IzpitvanPokazatelDAO.getIzpitvan_pokazatelObjectByRequestAndListIzpitvanPokazatel(choiseRequest, getPokazatelObjectFromChoicePokazatel());
		Metody mm = MetodyDAO.getValueList_MetodyByName(choiceMetody.getSelectedItem());
		izpivanPokazatel.getId_pokazatel();
		izpivanPokazatel.setMetody(mm);
		IzpitvanPokazatelDAO.updateIzpitvanPokazatel( izpivanPokazatel);
	}
	private static Boolean checkDuplicateCodeNuclide(Object[][] dataTable) {
		Boolean corectCheck = true;
		List<String> listCodeNuclide = new ArrayList<String>();
		for (int i = 0; i < dataTable.length; i++) {
			String s1 = dataTable[i][mda_Colum].toString().toString();
			String s2 = dataTable[i][actv_value_Colum].toString();
			if ((Double.parseDouble((String) s1) + (Double.parseDouble((String) s2)) > 0)) {

				listCodeNuclide.add(dataTable[i][nuclide_Colum].toString());

			}
		}
		System.out.println(listCodeNuclide.size());
		List<String> deDupStringList = new ArrayList<>(new HashSet<>(listCodeNuclide));
		System.out.println(deDupStringList.size() + "  " + listCodeNuclide.size());
		if (deDupStringList.size() != listCodeNuclide.size()) {
			corectCheck = false;
		}
		if (!corectCheck) {
			JOptionPane.showMessageDialog(null, "Налични са повтарящи се Нуклиди", "Проблем с база данни:",
					JOptionPane.ERROR_MESSAGE);
		}
		return corectCheck;
	}

	private  List<Results> creadListFromResultObjectForSave(Sample sample) {
		Boolean fl;
		List<Results> listResultsForSave = new ArrayList<Results>();
		List<Results> listResultsForDelete = new ArrayList<Results>();
		List<Results> listResultsFromTable = new ArrayList<Results>();
		if (checkDuplicateCodeNuclide(dataTable)) {
			for (int i = 0; i < dataTable.length; i++) {
				String s1 = dataTable[i][mda_Colum].toString().toString();
				String s2 = dataTable[i][actv_value_Colum].toString();
				if ((Double.parseDouble((String) s1) + (Double.parseDouble((String) s2)) > 0)) {
					listResultsFromTable.add(creadResultObject(sample, i));
				}else{
					if(dataTable[i][rsult_Id_Colum] != null){
						listResultsForDelete.add(ResultsDAO.getValueResultsById((int) dataTable[i][rsult_Id_Colum]));
					}
				}
			}
		}
		List<Results> ListResultsFromDBase = creadListResultsObjects_ChoiseSample(sample);
		Iterator<Results> itr = null;
		for (Results results:listResultsFromTable) {
			
			itr = ListResultsFromDBase.iterator();
			fl=false;
			while (itr.hasNext()) {
				String codeNulide = itr.next().getNuclide().getSymbol_nuclide();
				if (codeNulide.equals(results.getNuclide().getSymbol_nuclide())) {
					itr.remove();
					listResultsForSave.add(results);
					fl=true;
				}
			}
			if(!fl){
				listResultsForSave.add(results);
			}
		}
		System.out.println(ListResultsFromDBase.size());
		for (Results results : ListResultsFromDBase) {
		System.out.println(results.getNuclide().getSymbol_nuclide());
			listResultsForSave.add(results);
		}
		System.out.println("За изтриване:");
		for (Results results : listResultsForDelete) {
			System.out.println(results.getNuclide().getSymbol_nuclide()+" "+results.getId_results());
			JOptionPane.showMessageDialog(null, "Налични са повтарящи се Нуклиди", "Проблем с база данни:",
					JOptionPane.YES_NO_OPTION);
			ResultsDAO.deleteResultsById(results.getId_results());
		}
		System.out.println("За запис:");
		for (Results results : listResultsForSave) {
			System.out.println(results.getNuclide().getSymbol_nuclide());
		}
		
		return listResultsForSave;
	}

	private static void saveResultsObjectInDBase(Results result) {

		ResultsDAO.updateResults(result);
	}

	private static Results creadResultObject(Sample sample, int i) {
		Results result;
		if (dataTable[i][rsult_Id_Colum] == null) {
			result = creadResultsObject(i, new Results(), sample);
		} else {
			result = creadResultsObject(i, ResultsDAO.getValueResultsById((int) dataTable[i][rsult_Id_Colum]), sample);
		}
		return result;
	}

	private static Results creadResultsObject(int i, Results result, Sample sample) {

		result.setBasic_value(txtBasicValueResult.getText());
		if (dataTable[i][dateHimObr_Colum] == null) {
			result.setDate_chim_oper("");
		} else {
			result.setDate_chim_oper(dataTable[i][dateHimObr_Colum].toString());
		}
		result.setDate_measur(dataTable[i][dateAnaliz_Colum].toString());
		result.setDate_redac(RequestViewAplication.DateNaw(false));
		result.setInProtokol((Boolean) dataTable[i][in_Prot_Colum]);
		result.setMda(Double.parseDouble((String) dataTable[i][mda_Colum].toString()));
		result.setQuantity(Double.parseDouble((String) dataTable[i][qunt_Colum].toString()));
		result.setSigma((int) dataTable[i][sigma_Colum]);
		result.setUncertainty(Double.parseDouble((String) dataTable[i][uncrt_Colum].toString()));
		result.setValue_result(Double.parseDouble((String) dataTable[i][actv_value_Colum].toString()));

		if ((dataTable[i][dimen_Colum].equals(""))) {
			result.setDimension(null);
		} else {
			result.setDimension(DimensionDAO.getValueDimensionByName((String) dataTable[i][dimen_Colum]));
		}
		result.setMetody((Metody) MetodyDAO.getValueList_MetodyByName(choiceMetody.getSelectedItem()));
		result.setNuclide(NuclideDAO.getValueNuclideBySymbol((String) dataTable[i][nuclide_Colum]));
		result.setPokazatel(
				List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(choicePokazatel.getSelectedItem()));
		result.setRtazmernosti(RazmernostiDAO.getValueRazmernostiByName((String) dataTable[i][razm_Colum]));
		result.setSample(sample);
		String choiceUser = choiceORHO.getSelectedItem();
		for (Users user : list_Users) {
			if (choiceUser.substring(0, choiceUser.indexOf(" ")).equals(user.getName_users())
					&& choiceUser.substring(choiceUser.indexOf(" ") + 1).equals(user.getFamily_users())) {
				result.setUser_chim_oper(user);
			}
		}
		choiceUser = choiceOIR.getSelectedItem();
		for (Users user : list_Users) {
			if (choiceUser.substring(0, choiceUser.indexOf(" ")).equals(user.getName_users())
					&& choiceUser.substring(choiceUser.indexOf(" ") + 1).equals(user.getFamily_users())) {
				result.setUser_measur(user);
			}
		}
		result.setUser_redac(user);
		result.setZabelejki(null);
		return result;
	}

	private void DobivSection(JPanel panel) {

		JLabel lblDobiv = new JLabel("Добив");
		GridBagConstraints gbc_lblDobiv = new GridBagConstraints();
		gbc_lblDobiv.anchor = GridBagConstraints.EAST;
		gbc_lblDobiv.insets = new Insets(0, 0, 0, 5);
		gbc_lblDobiv.gridx = 0;
		gbc_lblDobiv.gridy = 9;
		panel.add(lblDobiv, gbc_lblDobiv);

		JComboBox<?> choiceDobiv = new JComboBox<Object>();
		choiceDobiv.setLightWeightPopupEnabled(false);
		choiceDobiv.setBackground(Color.WHITE);

		GridBagConstraints gbc_choiceDobiv = new GridBagConstraints();
		gbc_choiceDobiv.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceDobiv.gridwidth = 2;
		gbc_choiceDobiv.insets = new Insets(0, 0, 0, 5);
		gbc_choiceDobiv.gridx = 1;
		gbc_choiceDobiv.gridy = 9;
		panel.add(choiceDobiv, gbc_choiceDobiv);
	}

	private void BasicValueFileSection(JPanel panel) {
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
		gbc_txtBasicValueResult.gridwidth = 4;
		gbc_txtBasicValueResult.gridx = 1;
		gbc_txtBasicValueResult.gridy = 5;
		panel.add(txtBasicValueResult, gbc_txtBasicValueResult);

		txtBasicValueResult.setColumns(10);

		JButton btnBasicDataFile = new JButton("Отвори");
		btnBasicDataFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				f.showOpenDialog(null);
				try {
					txtBasicValueResult.setText((f.getSelectedFile()).toString());
					ReadGamaFile.ReadGamaFile(f.getSelectedFile().toString());
					System.out.println(ReadGamaFile.getListNuclideMDA());
					if (ReadGamaFile.getListNuclideMDA() > 0) {
						flagIncertedFile = true;
					} else {
						flagIncertedFile = false;
					}
				} catch (NullPointerException e2) {

				}

			}
		});
		GridBagConstraints gbc_btnBasicDataFile = new GridBagConstraints();
		gbc_btnBasicDataFile.anchor = GridBagConstraints.WEST;
		gbc_btnBasicDataFile.insets = new Insets(0, 0, 5, 0);
		gbc_btnBasicDataFile.gridx = 5;
		gbc_btnBasicDataFile.gridy = 5;
		basic_panel.add(btnBasicDataFile, gbc_btnBasicDataFile);

	}

	private void ButtonTableFromFile(JPanel panel) {

		JButton btnTabFromFile = new JButton("Данни от файл");
		btnTabFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flagIncertedFile) {
					if (choiceMetody.getSelectedItem() != null) {
						if (MetodyDAO.getValueList_MetodyByName(choiceMetody.getSelectedItem()).getId_metody() == 9) {
							TranscluentWindow round = new TranscluentWindow();
							final Thread thread = new Thread(new Runnable() {
								@Override
								public void run() {

									readFromGenie2kFile();
									ViewTableInPanel(panel, round);
								}
							});
							thread.start();

						}
					} else {
						JOptionPane.showInputDialog("Само за метод М.ЛИ-РХ-10", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showInputDialog("Не сте избрали коректен файл", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		GridBagConstraints gbc_btnTabFromFile = new GridBagConstraints();
		gbc_btnTabFromFile.anchor = GridBagConstraints.WEST;
		gbc_btnTabFromFile.insets = new Insets(0, 0, 5, 0);
		gbc_btnTabFromFile.gridx = 5;
		gbc_btnTabFromFile.gridy = 6;
		basic_panel.add(btnTabFromFile, gbc_btnTabFromFile);
	}

	private void readFromGenie2kFile() {
		Users user = ReadGamaFile.getUserFromFile();
		String str = user.getName_users() + " " + user.getFamily_users();
		choiceOIR.select(str);

		Object[][] ss = CreatedataTableFromFile();
		dataTable = new Object[ss.length][tbl_Colum];
		dataTable = ss;

	}

	private Object[][] CreatedataTableFromFile() {
		List<Nuclide_to_Pokazatel> listNucToPok = getListNuklideToPokazatel();
		List<String> listSimbolBasicNuclide = getListSimbolBasikNulideFNuclideToPokazatel(listNucToPok);
		masuveSimbolNuclide = getMasiveSimbolNuclideToPokazatel(listNucToPok);
		Results[] masiveResultsActivFromFile = ReadGamaFile.getMasivResultsWithAktiv();
		Results[] masiveResultsMDAFromFile = ReadGamaFile.getMasivResultsMDA(listSimbolBasicNuclide);

		int countBigMasive = masiveResultsActivFromFile.length + masiveResultsMDAFromFile.length;
		Object[][] tableResult = new Object[countBigMasive][tbl_Colum];

		int k = 0;
		for (int i = 0; i < masiveResultsActivFromFile.length; i++) {
			tableResult[i] = rowWithValueResultsFromFile(masiveResultsActivFromFile[i]);
			k = i;
		}
		if (k == 0)
			k = -1;
		for (int i = 0; i < masiveResultsMDAFromFile.length; i++) {
			k++;
			tableResult[k] = rowWithValueResultsFromFile(masiveResultsMDAFromFile[i]);

		}

		return tableResult;
	}

	private void ChoiceOIR_Section(JPanel panel) {
		JLabel lblNewLabel_2 = new JLabel("Извършил анализа");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		choiceOIR = new Choice();
		GridBagConstraints gbc_choiceOIR = new GridBagConstraints();
		gbc_choiceOIR.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceOIR.insets = new Insets(0, 0, 5, 0);
		gbc_choiceOIR.gridx = 5;
		gbc_choiceOIR.gridy = 3;
		panel.add(choiceOIR, gbc_choiceOIR);

		for (String str : list_UsersNameFamilyOIR) {
			choiceOIR.addItem(str);
		}

	}

	private void MetodSection(JPanel panel) {

		lblNameMetod = new JLabel();
		Dimension dim = new Dimension(550, 14);
		lblNameMetod.setPreferredSize(dim);
		lblNameMetod.setMinimumSize(dim);
		lblNameMetod.setMaximumSize(dim);
		lblNameMetod.setHorizontalAlignment(SwingConstants.LEFT);

		GridBagConstraints gbc_lblNameMetod = new GridBagConstraints();
		gbc_lblNameMetod.anchor = GridBagConstraints.WEST;
		gbc_lblNameMetod.gridwidth = 3;
		gbc_lblNameMetod.insets = new Insets(0, 0, 5, 0);
		gbc_lblNameMetod.gridx = 3;
		gbc_lblNameMetod.gridy = 4;
		basic_panel.add(lblNameMetod, gbc_lblNameMetod);

		JLabel lblMetody = new JLabel("Метод");
		GridBagConstraints gbc_lblMetody = new GridBagConstraints();
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

		choiceMetody.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				if (choicePokazatel.getSelectedItem() != null) {
					if (flagNotReadListMetody) {
						for (Metody metod : getListMetodyFormMetody_To_Pokaztel()) {
							choiceMetody.add(metod.getCode_metody());
							flagNotReadListMetody = false;
						}
					}

				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (choiceMetody.getSelectedItem() != null) {
					selectedMetod = MetodyDAO.getValueList_MetodyByName(choiceMetody.getSelectedItem());
					lblNameMetod.setText(selectedMetod.getName_metody());
				}
			}

			public void mousePressed(MouseEvent e) {

			}

		});

	}

	private void ChoiceORHO_Section(JPanel panel) {
		JLabel lblNewLabel_1 = new JLabel("Извършил Хим. обработ.");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		choiceORHO = new Choice();
		GridBagConstraints gbc_choiceORHO = new GridBagConstraints();
		gbc_choiceORHO.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceORHO.insets = new Insets(0, 0, 5, 0);
		gbc_choiceORHO.gridx = 5;
		gbc_choiceORHO.gridy = 1;
		panel.add(choiceORHO, gbc_choiceORHO);

		for (String str : list_UsersNameFamilyORHO) {
			choiceORHO.addItem(str);
		}

	}

	private List_izpitvan_pokazatel getPokazatelObjectFromChoicePokazatel() {
		return List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(choicePokazatel.getSelectedItem());
	}

	private void PokazatelSection(JPanel panel) {

		JLabel lblPokazatel = new JLabel("Показател");
		GridBagConstraints gbc_lblPokazatel = new GridBagConstraints();
		gbc_lblPokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPokazatel.gridx = 0;
		gbc_lblPokazatel.gridy = 3;
		panel.add(lblPokazatel, gbc_lblPokazatel);

		choicePokazatel = new Choice();
		choicePokazatel.setBackground(Color.WHITE);
		choicePokazatel.setPreferredSize(new Dimension(205, 20));
		GridBagConstraints gbc_choicePokazatel = new GridBagConstraints();
		gbc_choicePokazatel.fill = GridBagConstraints.HORIZONTAL;
		gbc_choicePokazatel.gridwidth = 2;
		gbc_choicePokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_choicePokazatel.gridx = 1;
		gbc_choicePokazatel.gridy = 3;
		panel.add(choicePokazatel, gbc_choicePokazatel);

		choicePokazatel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				if (choiseRequest != null) {
					if (getSampleObjectFromChoiceSampleCode() != null) {
						if (flagNotReadListPokazatel)
							for (IzpitvanPokazatel pokazat : listPokazatel) {
								choicePokazatel.add(pokazat.getPokazatel().getName_pokazatel());
								flagNotReadListPokazatel = false;
							}
					}
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

			}

		});
	}

	private void SampleCodeSection(JPanel panel) {
		JLabel lblSmplCode = new JLabel("№ на проба");
		GridBagConstraints gbc_lblSmplCode = new GridBagConstraints();
		gbc_lblSmplCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblSmplCode.gridx = 2;
		gbc_lblSmplCode.gridy = 0;
		panel.add(lblSmplCode, gbc_lblSmplCode);

		choiceSmplCode = new Choice();
		GridBagConstraints gbc_choiceSmplCode = new GridBagConstraints();
		gbc_choiceSmplCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceSmplCode.insets = new Insets(0, 0, 5, 5);
		gbc_choiceSmplCode.gridx = 2;
		gbc_choiceSmplCode.gridy = 1;
		panel.add(choiceSmplCode, gbc_choiceSmplCode);

		choiceSmplCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				String rquestCode = txtRqstCode.getText();

				if (choiseRequest != null) {
					if (listSample.isEmpty()) {
						listSample = SampleDAO.getListSampleFromColumnByVolume("request", choiseRequest);
						for (Sample samp : listSample) {
							choiceSmplCode.add(samp.getSample_code());
						}
					}

				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// for (Sample samp : listSample) {
				// if
				// (samp.getSample_code().equals(choiceSmplCode.getSelectedItem()))
				// {
				// sample = samp;
				// }
				// }
				// choiceSmplCode.getSelectedItem();
			}

			public void mousePressed(MouseEvent e) {

			}

		});

	}

	private void RequestCodeSection(JPanel panel) {

		JLabel lblError = new JLabel();
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.anchor = GridBagConstraints.WEST;
		gbc_lblError.gridwidth = 2;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 2;
		basic_panel.add(lblError, gbc_lblError);
		lblError.setVisible(false);

		JLabel lblRqstCode = new JLabel("Код на заявка");
		GridBagConstraints gbc_lblRqstCode = new GridBagConstraints();
		gbc_lblRqstCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRqstCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblRqstCode.gridx = 1;
		gbc_lblRqstCode.gridy = 0;
		panel.add(lblRqstCode, gbc_lblRqstCode);

		txtRqstCode = new JTextField();
		GridBagConstraints gbc_txtRqstCode = new GridBagConstraints();
		gbc_txtRqstCode.insets = new Insets(0, 0, 5, 5);
		gbc_txtRqstCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRqstCode.gridx = 1;
		gbc_txtRqstCode.gridy = 1;
		panel.add(txtRqstCode, gbc_txtRqstCode);
		txtRqstCode.setColumns(10);

		txtRqstCode.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {

				txtRqstCode.setText(RequestViewAplication.checkFormatString(txtRqstCode.getText()));
				if (!RequestDAO.checkRequestCode(txtRqstCode.getText())) {
					txtRqstCode.setForeground(Color.red);
					lblError.setVisible(true);
					lblError.setText("Заявка с този номер не съществува");
					validate();
					repaint();

				} else {
					txtRqstCode.setForeground(Color.BLACK);
					txtRqstCode.setBorder(new LineBorder(Color.BLACK));
					choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", txtRqstCode.getText());
					lblError.setVisible(false);
					listPokazatel = IzpitvanPokazatelDAO.getValueIzpitvan_pokazatelByRequest(choiseRequest);
					txtRqstCode.setEditable(false);
					validate();
					repaint();

				}
			}

			@Override
			public void keyPressed(KeyEvent event) {

			}
		});

	}

	private List<Metody> getListMetodyFormMetody_To_Pokaztel() {
		List<Metody_to_Pokazatel> list = Metody_to_PokazatelDAO
				.getListMetody_to_PokazatelFromColumnByVolume("pokazatel", getPokazatelObjectFromChoicePokazatel());
		List<Metody> listMetody = new ArrayList<Metody>();
		for (Metody_to_Pokazatel metody_to_Pokazatel : list) {
			listMetody.add(metody_to_Pokazatel.getMetody());
		}
		return listMetody;
	}

	public static void setUp_Nuclide(TableColumn Nuclide_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(masuveSimbolNuclide);
		Nuclide_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Nuclide_Column.setCellRenderer(renderer);
	}

	public static void setUp_Razmernosti(TableColumn Razmernosti_Column) {
		JComboBox<?> comboBox1 = new JComboBox<Object>(values_Razmernosti);
		Razmernosti_Column.setCellEditor(new DefaultCellEditor(comboBox1));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Razmernosti_Column.setCellRenderer(renderer);
	}

	public static void setUp_Dimension(TableColumn Dimension_Column) {
		JComboBox<?> comboBox = new JComboBox<Object>(values_Dimension);
		Dimension_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Dimension_Column.setCellRenderer(renderer);
	}

	private void CreadTableButton(JPanel panel) {
		JButton btnCreadTable = new JButton("Данни от базата");
		btnCreadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (choiceMetody.getSelectedItem() != null) {
					selectedMetod = MetodyDAO.getValueList_MetodyByName(choiceMetody.getSelectedItem());
					lblNameMetod.setText(selectedMetod.getName_metody());
					List<Nuclide_to_Pokazatel> listNucToPok = getListNuklideToPokazatel();
					List<String> listSimbolBasikNulide = getListSimbolBasikNulideFNuclideToPokazatel(listNucToPok);
					masuveSimbolNuclide = getMasiveSimbolNuclideToPokazatel(listNucToPok);

					Results[] masiveResultsForChoiceSample = creadMasiveFromResultsObjects_ChoiseSample(
							getSampleObjectFromChoiceSampleCode());
					if (masiveResultsForChoiceSample.length > 0) {
						if (masiveResultsForChoiceSample[0].getUser_measur() != null) {
							String str = masiveResultsForChoiceSample[0].getUser_measur().getName_users() + " "
									+ masiveResultsForChoiceSample[0].getUser_measur().getFamily_users();
							choiceOIR.select(str);
						}
						if (masiveResultsForChoiceSample[0].getUser_chim_oper() != null) {
							String str = masiveResultsForChoiceSample[0].getUser_chim_oper().getName_users() + " "
									+ masiveResultsForChoiceSample[0].getUser_chim_oper().getFamily_users();
							choiceORHO.select(str);
						}
					}
					TranscluentWindow round = new TranscluentWindow();
					final Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {
							Object[][] ss = getDataTable(masiveResultsForChoiceSample, listSimbolBasikNulide);
							dataTable = new Object[ss.length][tbl_Colum];
							dataTable = ss;

							ViewTableInPanel(panel, round);
						}
					});
					thread.start();

				}
			}
		});

		GridBagConstraints gbc_btnCreadTable = new GridBagConstraints();
		gbc_btnCreadTable.gridwidth = 2;
		gbc_btnCreadTable.anchor = GridBagConstraints.EAST;
		gbc_btnCreadTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreadTable.gridx = 0;
		gbc_btnCreadTable.gridy = 6;
		panel.add(btnCreadTable, gbc_btnCreadTable);

	}

	private String[] getMasiveSimbolNuclideToPokazatel(List<Nuclide_to_Pokazatel> listNucToPok) {
		String[] masiveSimbolNuclide = new String[listNucToPok.size()];
		int i = 0;
		for (Nuclide_to_Pokazatel nuclide_to_Pokazatel : listNucToPok) {
			masiveSimbolNuclide[i] = nuclide_to_Pokazatel.getNuclide().getSymbol_nuclide();
			i++;
		}
		return masiveSimbolNuclide;
	}

	private List<String> getListSimbolBasikNulideFNuclideToPokazatel(List<Nuclide_to_Pokazatel> listNucToPok) {
		List<String> listSimbolBasikNulide = new ArrayList<String>();
		int i = 0;
		for (Nuclide_to_Pokazatel nuclide_to_Pokazatel : listNucToPok) {
			if (nuclide_to_Pokazatel.getNuclide().getFavorite_nuclide()) {
				listSimbolBasikNulide.add(nuclide_to_Pokazatel.getNuclide().getSymbol_nuclide());
			}
			i++;
		}
		return listSimbolBasikNulide;
	}

	private List<Nuclide_to_Pokazatel> getListNuklideToPokazatel() {
		List<Nuclide_to_Pokazatel> listNucToPok = Nuclide_to_PokazatelDAO
				.getListNuclide_to_PokazatelFromColumnByVolume("pokazatel", getPokazatelObjectFromChoicePokazatel());
		return listNucToPok;
	}

	private Sample getSampleObjectFromChoiceSampleCode() {
		Sample smp = null;
		for (Sample samp : listSample) {
			if (samp.getSample_code().equals(choiceSmplCode.getSelectedItem())) {
				return samp;
			}
		}
		return smp;
	}

	private void ViewTableInPanel(JPanel panel, TranscluentWindow round) {
		round.StopWindow();
		if (scrollTablePane != null) {
			scrollTablePane.removeNotify();
		}
		scrollTablePane = new JScrollPane();
		GridBagConstraints gbc_scrollTablePane = new GridBagConstraints();
		gbc_scrollTablePane.gridwidth = 6;
		gbc_scrollTablePane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollTablePane.fill = GridBagConstraints.BOTH;
		gbc_scrollTablePane.gridx = 0;
		gbc_scrollTablePane.gridy = 7;
		panel.add(scrollTablePane, gbc_scrollTablePane);

		JPanel panelTable = new JPanel();
		panelTable.removeAll();
		scrollTablePane.setViewportView(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		tabResults = CreateTableResults();
		countRowTabResults = dataTable.length;
		tabResults.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

				String s1 = table.getValueAt(row, 1).toString();
				String s2 = table.getValueAt(row, 3).toString();

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

		tabResults.validate();
		tabResults.repaint();

		panelTable.add(tabResults.getTableHeader(), BorderLayout.NORTH);
		panelTable.add(tabResults, BorderLayout.CENTER);

		panelTable.validate();
		panelTable.repaint();

		scrollTablePane.validate();
		scrollTablePane.repaint();

		panel.validate();
		panel.repaint();

		setSize(960, (countRowTabResults * rowWidth) + 340);
		setLocationRelativeTo(null);
		validate();
		repaint();

	}

	private void addRowButtonSection(JPanel basic_panel) {

		JLabel lblNewLabel = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 8;
		basic_panel.add(lblNewLabel, gbc_lblNewLabel);

		JButton btnAddRow = new JButton("нов Нуклид");
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TranscluentWindow round = new TranscluentWindow();
				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {

						AddNewRowIn_dataTable();
						ViewTableInPanel(basic_panel, round);
					}
				});
				thread.start();

			}

		});
		GridBagConstraints gbc_btnAddRow = new GridBagConstraints();
		gbc_btnAddRow.anchor = GridBagConstraints.EAST;
		gbc_btnAddRow.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddRow.gridx = 5;
		gbc_btnAddRow.gridy = 8;
		basic_panel.add(btnAddRow, gbc_btnAddRow);
	}

	private void AddNewRowIn_dataTable() {
		List<String> listSimbolBasikNulide = getListSimbolBasikNulideFNuclideToPokazatel(getListNuklideToPokazatel());
		int countDataTable = dataTable.length;
		Object[][] newTable = new Object[countDataTable + 1][tbl_Colum];
		for (int i = 0; i < countDataTable; i++) {
			newTable[i] = dataTable[i];
		}
		newTable[countDataTable] = rowWithoutValueResults(listSimbolBasikNulide.get(0));
		dataTable = new Object[newTable.length][tbl_Colum];
		for (int i = 0; i < newTable.length; i++) {
			dataTable[i] = newTable[i];
		}
	}

	public JTable CreateTableResults() {
		String[] columnNames = getTabHeader();
		@SuppressWarnings("rawtypes")
		Class[] types = getTypes();

		JTable table = new JTable();// new DefaultTableModel(rowData,
									// columnNames));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				if (SwingUtilities.isRightMouseButton(e)) {
					String date = "";
					if (col == dateAnaliz_Colum) {
						date = table.getValueAt(table.getSelectedRow(), dateAnaliz_Colum).toString();
					}
					if (col == dateHimObr_Colum) {
						date = table.getValueAt(table.getSelectedRow(), dateHimObr_Colum).toString();
					}
					final JFrame f = new JFrame();
					DatePicker dPicer = new DatePicker(f, false, date);
					table.setValueAt(dPicer.setPickedDate(false), row, col);

				}
				if (table.getSelectedColumn() == check_Colum) {
					// **************************
				}
			}

		});

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel dtm = new DefaultTableModel(dataTable, columnNames) {

					private static final long serialVersionUID = 1L;
					@SuppressWarnings("rawtypes")
					private Class[] classTypes = types;

					@SuppressWarnings({})
					@Override
					public Class<?> getColumnClass(int columnIndex) {
						return this.classTypes[columnIndex];
					}

					public Object getValueAt(int row, int col) {
						return dataTable[row][col];
					}

					@Override
					public boolean isCellEditable(int row, int column) {
						if (column < check_Colum) {
							return true;
						} else {
							return false;
						}
					}

					public void setValueAt(Object value, int row, int col) {

						if (!dataTable[row][col].equals(value)) {
							if (col == dateHimObr_Colum || col == dateAnaliz_Colum) {
								if (!DatePicker.incorrectDate((String) value, false)) {
									dataTable[row][col] = value;
									fireTableCellUpdated(row, col);
								}
							}

							if (col == actv_value_Colum || col == uncrt_Colum || col == mda_Colum || col == sigma_Colum
									|| col == qunt_Colum) {
								try {
									Double.parseDouble((String) value);
									dataTable[row][col] = value;
									fireTableCellUpdated(row, col);
								} catch (NumberFormatException e) {
								}
							}

							if (col == nuclide_Colum || col == razm_Colum || col == dimen_Colum
									|| col == in_Prot_Colum) {
								dataTable[row][col] = value;
								fireTableCellUpdated(row, col);
							}
						}

					}

					public int getColumnCount() {
						return columnNames.length;
					}

					public int getRowCount() {
						for (int i = 0; i < dataTable.length; i++) {
							System.out.println(i);
						}
						return dataTable.length;
					}

				};

				table.setModel(dtm);

				setUp_Nuclide(table.getColumnModel().getColumn(nuclide_Colum));
				setUp_Razmernosti(table.getColumnModel().getColumn(razm_Colum));
				setUp_Dimension(table.getColumnModel().getColumn(dimen_Colum));

				table.getColumnModel().getColumn(rsult_Id_Colum).setWidth(0);
				table.getColumnModel().getColumn(rsult_Id_Colum).setMinWidth(0);
				table.getColumnModel().getColumn(rsult_Id_Colum).setMaxWidth(0);
				table.getColumnModel().getColumn(rsult_Id_Colum).setPreferredWidth(0);

			}
		});
		return table;
	}

	private static String[] getTabHeader() {
		String[] tableHeader = { "Нуклид", "Активност", "Неопределеност", "МДА", "Размерност", "Сигма", "Количество",
				"Мярка", "ДатаХимОбр", "ДатаАнализ", "В протокол", "Проверка", "Id_Result" };
		return tableHeader;
	}

	@SuppressWarnings("rawtypes")
	private static Class[] getTypes() {

		Class[] types = { String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, Boolean.class, String.class, Integer.class };

		return types;
	}

	private static Object[][] getDataTable(Results[] masiveResultsForChoiceSample, List<String> listSimbolBasikNulide) {

		int countBigMasive = masiveResultsForChoiceSample.length + listSimbolBasikNulide.size();
		Object[][] bigMasiveResult = new Object[countBigMasive][tbl_Colum];

		for (int i = 0; i < masiveResultsForChoiceSample.length; i++) {
			Results results = masiveResultsForChoiceSample[i];
			bigMasiveResult[i] = rowWithValueResults(results);

			Iterator<String> itr = listSimbolBasikNulide.iterator();
			while (itr.hasNext()) {
				String basikNulide = itr.next();
				if (basikNulide.equals(results.getNuclide().getSymbol_nuclide())) {
					itr.remove();
				}
			}
		}

		int k = masiveResultsForChoiceSample.length;
		for (String basikNulide : listSimbolBasikNulide) {
			bigMasiveResult[k] = rowWithoutValueResults(basikNulide);
			k++;
		}
		int countMasiveTable = masiveResultsForChoiceSample.length + listSimbolBasikNulide.size();
		Object[][] tableResult = new Object[countMasiveTable][tbl_Colum];
		for (int i = 0; i < tableResult.length; i++) {
			tableResult[i] = bigMasiveResult[i];
		}

		return tableResult;
	}

	private static Object[] rowWithValueResultsFromFile(Results result) {
		Object[] rowFromTableResult = new Object[tbl_Colum];
		rowFromTableResult[nuclide_Colum] = result.getNuclide().getSymbol_nuclide();
		rowFromTableResult[actv_value_Colum] = result.getValue_result();
		rowFromTableResult[uncrt_Colum] = result.getUncertainty();
		rowFromTableResult[mda_Colum] = result.getMda();
		rowFromTableResult[razm_Colum] = result.getRtazmernosti().getName_razmernosti();
		rowFromTableResult[sigma_Colum] = result.getSigma();
		rowFromTableResult[qunt_Colum] = result.getQuantity();
		rowFromTableResult[dimen_Colum] = result.getDimension().getName_dimension();
		rowFromTableResult[dateHimObr_Colum] = result.getDate_chim_oper();
		rowFromTableResult[dateAnaliz_Colum] = result.getDate_measur();
		rowFromTableResult[in_Prot_Colum] = true;
		rowFromTableResult[check_Colum] = "Провери";
		rowFromTableResult[rsult_Id_Colum] = null;
		return rowFromTableResult;
	}

	private static Object[] rowWithoutValueResults(String BasicNuclide) {
		Object[] rowFromTableResult = new Object[tbl_Colum];
		rowFromTableResult[nuclide_Colum] = BasicNuclide;
		rowFromTableResult[actv_value_Colum] = 0.0;
		rowFromTableResult[uncrt_Colum] = 0.0;
		rowFromTableResult[mda_Colum] = 0.0;
		rowFromTableResult[razm_Colum] = values_Razmernosti[0];
		rowFromTableResult[sigma_Colum] = 2;
		rowFromTableResult[qunt_Colum] = 0.0;
		rowFromTableResult[dimen_Colum] = "";
		rowFromTableResult[dimen_Colum] = values_Dimension[0];
		rowFromTableResult[dateHimObr_Colum] = "";
		rowFromTableResult[dateAnaliz_Colum] = "";
		rowFromTableResult[in_Prot_Colum] = false;
		rowFromTableResult[check_Colum] = "Провери";
		rowFromTableResult[rsult_Id_Colum] = null;
		return rowFromTableResult;
	}

	private static Object[] rowWithValueResults(Results results) {
		Object[] rowFromTableResult = new Object[tbl_Colum];
		try {
			rowFromTableResult[nuclide_Colum] = results.getNuclide().getSymbol_nuclide();
			rowFromTableResult[actv_value_Colum] = results.getValue_result();
			rowFromTableResult[uncrt_Colum] = results.getUncertainty();
			rowFromTableResult[mda_Colum] = results.getMda();
			rowFromTableResult[razm_Colum] = results.getRtazmernosti().getName_razmernosti();
			rowFromTableResult[sigma_Colum] = results.getSigma();
			rowFromTableResult[qunt_Colum] = results.getQuantity();
			rowFromTableResult[dimen_Colum] = "";
			if (results.getDimension() != null) {
				rowFromTableResult[dimen_Colum] = results.getDimension().getName_dimension();
			}
			rowFromTableResult[dateHimObr_Colum] = results.getDate_chim_oper();
			rowFromTableResult[dateAnaliz_Colum] = results.getDate_measur();
			rowFromTableResult[in_Prot_Colum] = results.getInProtokol();
			rowFromTableResult[check_Colum] = "Провери";
			rowFromTableResult[rsult_Id_Colum] = results.getId_results();
		} catch (NullPointerException e) {
			JOptionPane.showInputDialog("Грешни данни за резултат:" + results.getId_results(),
					JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showInputDialog("Грешни данни за резултат:", JOptionPane.ERROR_MESSAGE);
		}
		return rowFromTableResult;

	}

}
