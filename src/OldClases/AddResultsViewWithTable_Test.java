package OldClases;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.RootPaneContainer;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Aplication.DimensionDAO;
import Aplication.DobivDAO;
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
import Aplication.TSI_DAO;
import Aplication.UsersDAO;
import DBase_Class.Dobiv;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Metody_to_Pokazatel;
import DBase_Class.Nuclide;
import DBase_Class.Nuclide_to_Pokazatel;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import GlobalVariable.GlobalPathForIcons;
import WindowView.AddDobivViewWithTable;
import WindowView.CheckResultClass;
import WindowView.CheckViewValueDialogFrame;
import WindowView.DatePicker;
import WindowView.MesejePanel;
import WindowView.ReadGamaFile;
import WindowView.RequestViewFunction;
import WindowView.TranscluentWindow;

public class AddResultsViewWithTable_Test extends JDialog {

	private static final long serialVersionUID = 1L;

	private static Users user_Redac = null;

	private static JTextField txtBasicValueResult;
	private JTextField txtRqstCode;
	private JLabel lblNameMetod;
	private JPanel top_panel;
	private JLabel lblGif_labell;
	private JLayeredPane layeredPane;
	
	private static Choice choicePokazatel;
	private static Choice choiceOIR;
	private static Choice choiceORHO;
	private static Choice choiceMetody;
	private static Choice choiceDobiv;
	private static JButton btnCreadTable;
	private Choice choiceSmplCode;
	private Metody selectedMetod = null;
	private static List<Sample> listSample;
	private static List<Users> list_Users;
	private static List<String> list_UsersNameFamilyOIR;
	private static List<String> list_UsersNameFamilyORHO;
	private static List<String> listSimbolBasikNulide;
	private static List<String> listSimbolBasikNulideToMetod;
	private static List<IzpitvanPokazatel> listPokazatel;
	private static List<Dobiv> listDobivFromMetod = new ArrayList<Dobiv>();
	private static List<Nuclide_to_Pokazatel> listNucToPok;
	private static List<Results> ListResultsFromDBase;
	private static List<Results> resultListForSave;
	private static List<Results> resultListForDelete;

	private static JButton btnAddRow;

	private JFileChooser f = new JFileChooser();

	private static Request choiseRequest;

	int newCountResults = 0;
	int countRowTabResults = 0;
	int addCount = 0;
	int rowWidth = 20;
	int meseje = 1;
	Boolean flagNotReadListPokazatel = true;
	Boolean flagNotReadListMetody = true;
	Boolean viewAddRowButton = false;
	Boolean flagThread = true;
	boolean flagIncertedFile;

	private static JTable tabResults;
	private static String[] masuveSimbolNuclide;
	private static String[] masive_NuclideToPokazatel;
	private static String[] values_Razmernosti;
	private static String[] values_Dimension;
	private static String[] masiveTSI;
	private static Object[][] dataTable;
	private static int tbl_Colum = 14;
	private static int nuclide_Colum = 0;
	private static int actv_value_Colum = 1;
	private static int uncrt_Colum = 2;
	private static int mda_Colum = 3;
	private static int razm_Colum = 4;
	private static int sigma_Colum = 5;
	private static int qunt_Colum = 6;
	private static int dimen_Colum = 7;
	private static int TSI_Colum = 8;
	private static int dateHimObr_Colum = 9;
	private static int dateAnaliz_Colum = 10;
	private static int in_Prot_Colum = 11;
	private static int check_Colum = 12;
	private static int rsult_Id_Colum = 13;

	private JScrollPane scrollTablePane;

	public AddResultsViewWithTable_Test(JFrame parent, TranscluentWindow round, Users user) {
		super(parent, "Въвеждане на Резултати", true);
		list_Users = UsersDAO.getInListAllValueUsers();
		list_UsersNameFamilyOIR = UsersDAO.getListStringAllName_FamilyUsersByPost(PostDAO.getValuePostByName("ОИР"));
		list_UsersNameFamilyORHO = UsersDAO.getListStringAllName_FamilyUsersByPost(PostDAO.getValuePostByName("ОРХО"));
		listSample = new ArrayList<Sample>();

		user_Redac = user;

		values_Razmernosti = RazmernostiDAO.getMasiveStringAllValueRazmernosti();
		values_Dimension = DimensionDAO.getMasiveStringAllValueDimension();
		masiveTSI = TSI_DAO.getMasiveStringAllValueTSI();

		setSize(1100, (countRowTabResults * rowWidth) + 360);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		layeredPane = new JLayeredPane();
		panel_1.add(layeredPane);
		
		ImageIcon pic = new ImageIcon(GlobalPathForIcons.get_destination_ajaxLoader());
		lblGif_labell = new JLabel(pic);
		layeredPane.setLayer(lblGif_labell, 4);
		lblGif_labell.setBounds(0, 0, 1082, 278);
		layeredPane.add(lblGif_labell);
		lblGif_labell.setVisible(false);

		top_panel = new JPanel();
		layeredPane.setLayer(top_panel, 7);
		top_panel.setBounds(0, 0, 1082, 278);
		layeredPane.add(top_panel);
		top_panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		top_panel.add(scrollPane);

		JPanel basic_panel = new JPanel();
		basic_panel.setBounds(new Rectangle(5, 0, 0, 0));
		scrollPane.setViewportView(basic_panel);
		GridBagLayout gbl_basic_panel = new GridBagLayout();
		gbl_basic_panel.columnWidths = new int[] { 91, 111, 147, 138, 166, 182, 197, 0 };
		gbl_basic_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_basic_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_basic_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		basic_panel.setLayout(gbl_basic_panel);

		RequestCodeSection(basic_panel);

		SampleCodeSection(basic_panel);

		PokazatelSection(basic_panel);

		ChoiceORHO_Section(basic_panel);

		MetodSection(basic_panel);

		ChoiceOIR_Section(basic_panel);

		btnDataFromDBase(basic_panel);

		btnOpenFile(basic_panel);

		BasicValueFileSection(basic_panel);

		btnTabFromFile(basic_panel);

		btnAddRow(basic_panel);

		DobivSection(basic_panel);

		ButtonPanell(basic_panel);
		round.StopWindow();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	private Results[] creadMasiveFromResultsObjects_ChoiseSample(Sample sample) {
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

	private List<Results> creadListResultsObjects_ChoiseSample(Sample sample) {
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

	public static void test2() {
		TranscluentWindow round = new TranscluentWindow();
		Callable<Integer> worker = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				int m = metodTest();

				return m;
			}

		};

		Future<Integer> result = Executors.newSingleThreadExecutor().submit(worker);
		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		round.StopWindow();
	}

	private static int metodTest() throws InterruptedException {
		int m = 0;

		for (int k = 0; k < 900; k++) {
			Thread.sleep(10);
			m++;
		}
		return m;
	}

	 public static void setWaitCursor(JDialog frame) {
	        if (frame != null) {
	            RootPaneContainer root = (RootPaneContainer) frame.getRootPane().getTopLevelAncestor();
	            root.getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	            root.getGlassPane().setVisible(true);
	        }
	    }

	    public static void setDefaultCursor(JDialog frame) {
	        if (frame != null) {
	            RootPaneContainer root = (RootPaneContainer) frame.getRootPane().getTopLevelAncestor();
	            root.getGlassPane().setCursor(Cursor.getDefaultCursor());
	        }
	    }

	
	
	
	private void ButtonPanell(JPanel panel) {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Запис");
		okButton.addActionListener(new ActionListener() {
//			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
			
			
				if (checkDataResult()) {
					updateIzpitvanPokazatelObjectInDBase();
					AddResultsViewWithTable_Test.setWaitCursor(AddResultsViewWithTable_Test.this);
	            
					choiceORHO.setBackground(Color.RED);
//					testSwingWorker;
					Sample samp = getSampleObjectFromChoiceSampleCode();
					ListResultsFromDBase = creadListResultsObjects_ChoiseSample(samp);
					resultListForDelete = AddResultsViewWithTable_Test.creadResultListForDelete(samp);
					resultListForSave = AddResultsViewWithTable_Test.creadResultListForSave(samp);
					
					
//					AddResultsViewWithTable.setDefaultCursor(AddResultsViewWithTable_Test.this);
					choiceORHO.setBackground(Color.WHITE);
					new MesejePanel(resultListForSave, resultListForDelete);
					int k = MesejePanel.getResultMeseje();

					if (k == 0) {
						for (Results results : resultListForSave) {
							int idresultInBase = existsNuclideInResultTOResultBase(ListResultsFromDBase, results);
							if (idresultInBase != 0) {
								results.setId_results(idresultInBase);
								ResultsDAO.updateResults(results);
							} else {
								ResultsDAO.setValueResults(results);
							}
						}
						for (Results results : resultListForDelete) {
							ResultsDAO.deleteResultsById(results.getId_results());
						}

//						listSimbolBasikNulide = getListSimbolBasikNulideFNuclideToPokazatel(listNucToPok);
						
//						Results[] masiveResultsForChoiceSample = creadMasiveFromResultsObjects_ChoiseSample(
//								getSampleObjectFromChoiceSampleCode());
//						startViewtablePanel(panel, masiveResultsForChoiceSample);
					}

				}
//				layeredPane.setLayer(lblGif_labell, 1);
//				lblGif_labell.setVisible(false);
			}

		});
		// okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		// getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				setVisible(false);
			}
		});
		// cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	public static class Cadre extends JFrame implements Runnable {

		// private PanneauHaut panneauHaut;
		// private PanneauBas panneauBas;

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void run() {
			System.out.println("Thread started");
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//	    setExtendedState(JFrame.MAXIMIZED_BOTH);
//	    this.panneauHaut = new PanneauHaut();
//	    this.panneauBas = new PanneauBas();
			JPanel mainPanel = new JPanel();
			JTextField kooltxt = new JTextField("hehehe");
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(kooltxt);
//	    mainPanel.add(panneauHaut, BorderLayout.NORTH);
//	    mainPanel.add(panneauBas.getPanel(), BorderLayout.SOUTH);
			this.setContentPane(mainPanel);
			pack();
			setLocationRelativeTo(null);
			this.setVisible(true);
		}
	}

	public static class TranscluentWindowNew extends JFrame implements Runnable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JWindow frame;

		@Override
		public void run() {
			frame = new JWindow();
			frame.setVisible(true);
			frame.setAlwaysOnTop(true);
			frame.setBackground(new Color(0, 0, 0, 0));
			JPanel pan = new JPanel();
			pan.setOpaque(false);
			frame.setContentPane(pan);
			ImageIcon pic = new ImageIcon(GlobalPathForIcons.get_destination_ajaxLoader());
			frame.getContentPane().add(new JLabel(pic));
			frame.pack();
			frame.setLocationRelativeTo(null);

		}

		public void StopWindow() {
			SwingUtilities.getWindowAncestor(frame).dispose();

		}

	}

	private void updateIzpitvanPokazatelObjectInDBase() {
		IzpitvanPokazatel izpivanPokazatel = IzpitvanPokazatelDAO
				.getIzpitvan_pokazatelObjectByRequestAndListIzpitvanPokazatel(choiseRequest,
						getPokazatelObjectFromChoicePokazatel());
		Metody mm = MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem());
		izpivanPokazatel.getId_pokazatel();
		izpivanPokazatel.setMetody(mm);
		IzpitvanPokazatelDAO.updateIzpitvanPokazatel(izpivanPokazatel);
	}

	public static List<Results> creadResultListForSave(Sample sample) {
		List<Results> listResultsForSave = new ArrayList<Results>();
		for (int i = 0; i < dataTable.length; i++) {
			String s1 = dataTable[i][mda_Colum].toString();
			String s2 = dataTable[i][actv_value_Colum].toString();
			if ((Double.parseDouble((String) s1) + (Double.parseDouble((String) s2)) > 0)) {
				listResultsForSave.add(creadResultObject(sample, i));

			}
		}

		return listResultsForSave;
	}

	public static List<Results> creadResultListForDelete(Sample sample) {
		List<Results> listResultsForDelete = new ArrayList<Results>();
		for (int i = 0; i < dataTable.length; i++) {
			String s1 = dataTable[i][mda_Colum].toString();
			String s2 = dataTable[i][actv_value_Colum].toString();
			if ((Double.parseDouble((String) s1) + (Double.parseDouble((String) s2)) == 0)) {
				if (dataTable[i][rsult_Id_Colum] != null) {
					listResultsForDelete.add(ResultsDAO.getValueResultsById((int) dataTable[i][rsult_Id_Colum]));
				}
			}
		}

		return listResultsForDelete;
	}

	private int existsNuclideInResultTOResultBase(List<Results> ListResultsFromDBase, Results results) {
		int fl = 0;
		for (Results res : ListResultsFromDBase) {
			String codeNulide = res.getNuclide().getSymbol_nuclide();
			if (codeNulide.equals(results.getNuclide().getSymbol_nuclide())) {
				return fl = res.getId_results();
			}
		}
		return fl;
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
		result.setDate_redac(RequestViewFunction.DateNaw(false));
		result.setInProtokol((Boolean) dataTable[i][in_Prot_Colum]);
		result.setMda(Double.parseDouble(dataTable[i][mda_Colum].toString()));
		result.setQuantity(Double.parseDouble(dataTable[i][qunt_Colum].toString()));
		result.setSigma(Integer.parseUnsignedInt(dataTable[i][sigma_Colum].toString()));
		result.setUncertainty(Double.parseDouble(dataTable[i][uncrt_Colum].toString()));
		result.setValue_result(Double.parseDouble(dataTable[i][actv_value_Colum].toString()));
		result.setTsi(TSI_DAO.getValueTSIByName(dataTable[i][TSI_Colum].toString()));
		if ((dataTable[i][dimen_Colum].equals(""))) {
			result.setDimension(null);
		} else {
			result.setDimension(DimensionDAO.getValueDimensionByName(dataTable[i][dimen_Colum].toString()));
		}
		result.setMetody((Metody) MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));
		result.setNuclide(NuclideDAO.getValueNuclideBySymbol(dataTable[i][nuclide_Colum].toString()));
		result.setPokazatel(
				List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(choicePokazatel.getSelectedItem()));
		result.setRtazmernosti(RazmernostiDAO.getValueRazmernostiByName(dataTable[i][razm_Colum].toString()));
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
		result.setUser_redac(user_Redac);
		result.setZabelejki(null);
		if (!choiceDobiv.getSelectedItem().toString().isEmpty()) {
			result.setDobiv(DobivDAO.getList_DobivByCode_Standart(choiceDobiv.getSelectedItem().toString()).get(0));
		}
		return result;
	}

	private void DobivSection(JPanel panel) {
		JLabel lblDobiv = new JLabel("Добив");
		GridBagConstraints gbc_lblDobiv = new GridBagConstraints();
		gbc_lblDobiv.anchor = GridBagConstraints.EAST;
		gbc_lblDobiv.insets = new Insets(0, 0, 5, 5);
		gbc_lblDobiv.gridx = 5;
		gbc_lblDobiv.gridy = 5;
		panel.add(lblDobiv, gbc_lblDobiv);

		choiceDobiv = new Choice();
		// choiceDobiv.setLightWeightPopupEnabled(false);
		// choiceDobiv.setBackground(Color.WHITE);

		GridBagConstraints gbc_choiceDobiv = new GridBagConstraints();
		gbc_choiceDobiv.insets = new Insets(0, 0, 5, 0);
		gbc_choiceDobiv.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceDobiv.gridx = 6;
		gbc_choiceDobiv.gridy = 5;
		panel.add(choiceDobiv, gbc_choiceDobiv);
	}

	private void setValueInChoiceDobiv() {
		choiceDobiv.removeAll();
		choiceDobiv.addItem("");
		listDobivFromMetod = DobivDAO.getListDobivByMetody(selectedMetod);
		for (Dobiv str : listDobivFromMetod) {
			choiceDobiv.addItem(str.getCode_Standart());
		}
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
		gbc_txtBasicValueResult.gridwidth = 2;
		gbc_txtBasicValueResult.gridx = 1;
		gbc_txtBasicValueResult.gridy = 5;
		panel.add(txtBasicValueResult, gbc_txtBasicValueResult);

		txtBasicValueResult.setColumns(10);

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
		System.out.print(masiveResultsActivFromFile.length);
		int countBigMasive = masiveResultsActivFromFile.length + masiveResultsMDAFromFile.length;
		Object[][] tableResult = new Object[countBigMasive][tbl_Colum];

		int k = 0;
		for (int i = 0; i < masiveResultsActivFromFile.length; i++) {
			tableResult[i] = rowWithValueResultsFromFile(masiveResultsActivFromFile[i]);
			// k = i;
			k++;
		}
		// if (k == 0)
		// k = -1;
		for (int i = 0; i < masiveResultsMDAFromFile.length; i++) {
			// k++;
			tableResult[k] = rowWithValueResultsFromFile(masiveResultsMDAFromFile[i]);
			k++;

		}

		return tableResult;
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

		for (String str : list_UsersNameFamilyOIR) {
			choiceOIR.addItem(str);
		}
		choiceOIR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				choiceOIR.setBackground(Color.WHITE);

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

			}

		});
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
		gbc_lblNameMetod.gridwidth = 4;
		gbc_lblNameMetod.insets = new Insets(0, 0, 5, 0);
		gbc_lblNameMetod.gridx = 3;
		gbc_lblNameMetod.gridy = 4;
		panel.add(lblNameMetod, gbc_lblNameMetod);

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
		choiceMetody.add(" ");
		choiceMetody.select(" ");
		choiceMetody.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				setValueInChoiceDobiv();
				listSimbolBasikNulideToMetod = AddDobivViewWithTable.getListSimbolBasikNulideToMetod(selectedMetod);
			}

		});

		choiceMetody.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceMetody.setBackground(Color.WHITE);
				if (choicePokazatel.getSelectedItem() != null) {
					if (flagNotReadListMetody) {
						choiceMetody.removeAll();
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
					selectedMetod = MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem());
					lblNameMetod.setText(selectedMetod.getName_metody());
					if (listDobivFromMetod.isEmpty()) {
						setValueInChoiceDobiv();
						listSimbolBasikNulideToMetod = AddDobivViewWithTable
								.getListSimbolBasikNulideToMetod(selectedMetod);
					}
					selectedMetod = MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem());
					lblNameMetod.setText(selectedMetod.getName_metody());
					listNucToPok = getListNuklideToPokazatel();

					listSimbolBasikNulide = getListSimbolBasikNulideFNuclideToPokazatel(listNucToPok);
					masuveSimbolNuclide = getMasiveSimbolNuclideToPokazatel(listNucToPok);
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

		for (String str : list_UsersNameFamilyORHO) {
			choiceORHO.addItem(str);
		}

		choiceORHO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				choiceORHO.setBackground(Color.WHITE);

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

			}

		});

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
		choicePokazatel.add("");
		choicePokazatel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choicePokazatel.setBackground(Color.WHITE);
				if (choiseRequest != null) {
					if (getSampleObjectFromChoiceSampleCode() != null) {
						if (flagNotReadListPokazatel)
							choicePokazatel.removeAll();
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

		JLabel lbl_OI_Sample = new JLabel();
		GridBagConstraints gbc_lbl_OI_Sample = new GridBagConstraints();
		gbc_lbl_OI_Sample.anchor = GridBagConstraints.WEST;
		gbc_lbl_OI_Sample.gridwidth = 2;
		gbc_lbl_OI_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_OI_Sample.gridx = 3;
		gbc_lbl_OI_Sample.gridy = 1;
		panel.add(lbl_OI_Sample, gbc_lbl_OI_Sample);
		lbl_OI_Sample.setVisible(false);

		JLabel lblSampleDescript = new JLabel();
		GridBagConstraints gbc_lblSampleDescript = new GridBagConstraints();
		gbc_lblSampleDescript.anchor = GridBagConstraints.WEST;
		gbc_lblSampleDescript.gridwidth = 2;
		gbc_lblSampleDescript.insets = new Insets(0, 0, 5, 5);
		gbc_lblSampleDescript.gridx = 3;
		gbc_lblSampleDescript.gridy = 3;
		panel.add(lblSampleDescript, gbc_lblSampleDescript);
		lblSampleDescript.setVisible(false);

		choiceSmplCode = new Choice();
		GridBagConstraints gbc_choiceSmplCode = new GridBagConstraints();
		gbc_choiceSmplCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceSmplCode.insets = new Insets(0, 0, 5, 5);
		gbc_choiceSmplCode.gridx = 2;
		gbc_choiceSmplCode.gridy = 1;
		panel.add(choiceSmplCode, gbc_choiceSmplCode);
		choiceSmplCode.setEnabled(false);
		choiceSmplCodeListener(lbl_OI_Sample, lblSampleDescript);

	}

	public void choiceSmplCodeListener(JLabel lbl_OI_Sample, JLabel lblSampleDescript) {

		// Add item listener
		choiceSmplCode.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				visibleSampleInfoLbl(lbl_OI_Sample, lblSampleDescript);

			}

		});

		choiceSmplCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				visibleSampleInfoLbl(lbl_OI_Sample, lblSampleDescript);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				visibleSampleInfoLbl(lbl_OI_Sample, lblSampleDescript);

			}

			public void mousePressed(MouseEvent e) {

			}

		});
	}

	public void visibleSampleInfoLbl(JLabel lbl_OI_Sample, JLabel lblSampleDescript) {
		if (choiceSmplCode.getSelectedItem() != "") {
			if (getName_IO_Sample(choiceSmplCode.getSelectedItem()).length() > 50) {
				lbl_OI_Sample.setText(getName_IO_Sample(choiceSmplCode.getSelectedItem()).substring(0, 50));
			} else {
				lbl_OI_Sample.setText(getName_IO_Sample(choiceSmplCode.getSelectedItem()));
			}
			if (get_Sample_Descrip(choiceSmplCode.getSelectedItem()).length() > 50) {
				lblSampleDescript.setText(get_Sample_Descrip(choiceSmplCode.getSelectedItem()).substring(0, 50));
			} else {
				lblSampleDescript.setText(get_Sample_Descrip(choiceSmplCode.getSelectedItem()));
			}
			lbl_OI_Sample.setVisible(true);
			lblSampleDescript.setVisible(true);
		}
	}

	public String get_Sample_Descrip(String name_IO_Sample) {
		{

			String strChoiseSamp = choiceSmplCode.getSelectedItem();
			for (Sample samp : listSample) {
				if (samp.getSample_code().equals(strChoiseSamp)) {
					name_IO_Sample = samp.getDescription_sample();

				}
			}
		}
		return name_IO_Sample;
	}

	public String getName_IO_Sample(String name_IO_Sample) {
		{

			String strChoiseSamp = choiceSmplCode.getSelectedItem();
			for (Sample samp : listSample) {
				if (samp.getSample_code().equals(strChoiseSamp)) {
					name_IO_Sample = samp.getObekt_na_izpitvane().getName_obekt_na_izpitvane();

				}
			}
		}
		return name_IO_Sample;
	}

	private void RequestCodeSection(JPanel panel) {

		JLabel lblError = new JLabel();
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.anchor = GridBagConstraints.WEST;
		gbc_lblError.gridwidth = 2;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 2;
		panel.add(lblError, gbc_lblError);
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

		txtRqstCodeListener(lblError);

	}

	public void txtRqstCodeListener(JLabel lblError) {

		txtRqstCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				txtRqstCode.setBackground(Color.WHITE);

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

			}

		});

		txtRqstCode.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {

				txtRqstCode.setText(RequestViewFunction.checkFormatString(txtRqstCode.getText()));
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
					if (choiseRequest != null) {
						if (listSample.isEmpty()) {
							listSample = SampleDAO.getListSampleFromColumnByVolume("request", choiseRequest);
							for (Sample samp : listSample) {
								choiceSmplCode.add(samp.getSample_code());
							}
						}

					}
					choiceSmplCode.setEnabled(true);
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
				.getListMetody_to_PokazatelByPokazatel(getPokazatelObjectFromChoicePokazatel());
		List<Metody> listMetody = new ArrayList<Metody>();
		for (Metody_to_Pokazatel metody_to_Pokazatel : list) {
			listMetody.add(metody_to_Pokazatel.getMetody());
		}
		return listMetody;
	}

	public static void setUp_Nuclide(TableColumn nuclide_Column, Boolean isNewRow) {
		JComboBox<?> comboBox = new JComboBox<Object>(masuveSimbolNuclide);
		if (isNewRow) {
			comboBox = new JComboBox<Object>(masive_NuclideToPokazatel);
		}
		nuclide_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		nuclide_Column.setCellRenderer(renderer);
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

	private void setUp_TSI(TableColumn tSI_column) {
		JComboBox<?> comboBox = new JComboBox<Object>(masiveTSI);
		tSI_column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		tSI_column.setCellRenderer(renderer);

	}

	private void btnDataFromDBase(JPanel panel) {

		btnCreadTable = new JButton("Данни от базата");
		btnCreadTableListener(panel, btnCreadTable);
		GridBagConstraints gbc_btnCreadTable = new GridBagConstraints();
		gbc_btnCreadTable.gridwidth = 2;
		gbc_btnCreadTable.anchor = GridBagConstraints.EAST;
		gbc_btnCreadTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreadTable.gridx = 0;
		gbc_btnCreadTable.gridy = 6;
		panel.add(btnCreadTable, gbc_btnCreadTable);

	}

	private void btnOpenFile(JPanel panel) {
		JButton btnOpenFile = new JButton("Отвори");
		btnOpenFileListener(btnOpenFile);
		GridBagConstraints gbc_btnBasicDataFile = new GridBagConstraints();
		gbc_btnBasicDataFile.anchor = GridBagConstraints.WEST;
		gbc_btnBasicDataFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnBasicDataFile.gridx = 3;
		gbc_btnBasicDataFile.gridy = 5;
		panel.add(btnOpenFile, gbc_btnBasicDataFile);
	}

	public void btnOpenFileListener(JButton btnOpenFile) {
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				f.showOpenDialog(null);
				try {
					txtBasicValueResult.setText((f.getSelectedFile()).toString());
					ReadGamaFile.getReadGamaFile(f.getSelectedFile().toString());

					if (ReadGamaFile.getListNuclideMDA() > 0) {
						flagIncertedFile = true;
					} else {
						flagIncertedFile = false;
					}
				} catch (NullPointerException e2) {

				}

			}

		});
	}

	public void btnCreadTableListener(JPanel panel, JButton btnCreadTable) {
		btnCreadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (choiceMetody.getSelectedItem() != null) {

					setValueInChoiceDobiv();

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

						if (masiveResultsForChoiceSample[0].getDobiv() != null) {
							String str = masiveResultsForChoiceSample[0].getDobiv().getCode_Standart();
							choiceDobiv.select(str);
						}

					}

					startViewtablePanel(panel, masiveResultsForChoiceSample);

				}
			}

		});

	}

	private void startViewtablePanel(JPanel panel, Results[] masiveResultsForChoiceSample) {
		TranscluentWindow round = new TranscluentWindow();
		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Object[][] ss = getDataTable(masiveResultsForChoiceSample, listSimbolBasikNulide);
				dataTable = new Object[ss.length][tbl_Colum];
				dataTable = ss;
				Boolean isNewRow = false;
				ViewTableInPanel(panel, round, isNewRow);
			}
		});
		thread.start();
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
		for (Nuclide_to_Pokazatel nuclide_to_Pokazatel : listNucToPok) {
			if (nuclide_to_Pokazatel.getNuclide().getFavorite_nuclide()) {
				listSimbolBasikNulide.add(nuclide_to_Pokazatel.getNuclide().getSymbol_nuclide());
			}

		}
		return listSimbolBasikNulide;
	}

	private String[] getListSimbolNuclideToPokazatel(List<Nuclide_to_Pokazatel> listNucToPok) {
		String[] listSimbolBasikNulide = new String[listNucToPok.size()];
		int i = 0;
		for (Nuclide_to_Pokazatel nuclide_to_Pokazatel : listNucToPok) {
			listSimbolBasikNulide[i] = nuclide_to_Pokazatel.getNuclide().getSymbol_nuclide();
			i++;
		}
		return listSimbolBasikNulide;
	}

	private List<Nuclide_to_Pokazatel> getListNuklideToPokazatel() {
		List<Nuclide_to_Pokazatel> listNucToPok = Nuclide_to_PokazatelDAO
				.getListNuclide_to_PokazatelByPokazatel(getPokazatelObjectFromChoicePokazatel());
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

	@SuppressWarnings("serial")
	private void ViewTableInPanel(JPanel panel, TranscluentWindow round, Boolean isNewRow) {

		if (scrollTablePane != null) {
			scrollTablePane.removeNotify();
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

		tabResults = CreateTableResults(isNewRow);
		countRowTabResults = dataTable.length;
		tabResults.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				String s1 = table.getValueAt(row, actv_value_Colum).toString();
				String s2 = table.getValueAt(row, mda_Colum).toString();

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

		round.StopWindow();
		top_panel.setBounds(0, 0,  1082,(countRowTabResults * rowWidth) + 278);
		setSize(1100, (countRowTabResults * rowWidth) + 340);
		setLocationRelativeTo(null);
		validate();
		repaint();

	}

	private void btnTabFromFile(JPanel basic_panel) {

		JButton btnTabFromFile = new JButton("Данни от файл");
		btnTabFromFileListener(basic_panel, btnTabFromFile);
		GridBagConstraints gbc_btnTabFromFile = new GridBagConstraints();
		gbc_btnTabFromFile.anchor = GridBagConstraints.WEST;
		gbc_btnTabFromFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnTabFromFile.gridx = 3;
		gbc_btnTabFromFile.gridy = 6;
		basic_panel.add(btnTabFromFile, gbc_btnTabFromFile);
	}

	private void btnAddRow(JPanel basic_panel) {
		btnAddRow = new JButton("нов Нуклид");
		btmAddRowListener(basic_panel, btnAddRow);
		GridBagConstraints gbc_btnAddRow = new GridBagConstraints();
		gbc_btnAddRow.anchor = GridBagConstraints.EAST;
		gbc_btnAddRow.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddRow.gridx = 6;
		gbc_btnAddRow.gridy = 8;
		basic_panel.add(btnAddRow, gbc_btnAddRow);
		btnAddRow.setVisible(false);
	}

	public void btnTabFromFileListener(JPanel basic_panel, JButton btnTabFromFile) {
		btnTabFromFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (flagIncertedFile) {
					if (choiceMetody.getSelectedItem() != null) {
						setValueInChoiceDobiv();
						if (MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()).getId_metody() == 9) {
							TranscluentWindow round = new TranscluentWindow();
							final Thread thread = new Thread(new Runnable() {
								@Override
								public void run() {

									readFromGenie2kFile();
									Boolean isNewRow = false;
									ViewTableInPanel(basic_panel, round, isNewRow);
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

	}

	public void btmAddRowListener(JPanel basic_panel, JButton btnAddRow) {
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TranscluentWindow round = new TranscluentWindow();
				final Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {

						AddNewRowIn_dataTable();
						Boolean isNewRow = true;
						ViewTableInPanel(basic_panel, round, isNewRow);
					}
				});
				thread.start();

			}

		});
	}

	private void AddNewRowIn_dataTable() {
		List<Nuclide_to_Pokazatel> listNucToPok = getListNuklideToPokazatel();
		masive_NuclideToPokazatel = getListSimbolNuclideToPokazatel(listNucToPok);
		int countDataTable = dataTable.length;
		Object[][] newTable = new Object[countDataTable + 1][tbl_Colum];
		for (int i = 0; i < countDataTable; i++) {
			newTable[i] = dataTable[i];
		}
		newTable[countDataTable] = rowWithoutValueResults(masive_NuclideToPokazatel[0]);
		dataTable = new Object[newTable.length][tbl_Colum];
		for (int i = 0; i < newTable.length; i++) {
			dataTable[i] = newTable[i];
		}
	}

	public JTable CreateTableResults(Boolean isNewRow) {

		if (1 < listNucToPok.size()) {
			btnAddRow.setVisible(true);
		} else {
			btnAddRow.setVisible(false);
		}

		String[] columnNames = getTabHeader();
		@SuppressWarnings("rawtypes")
		Class[] types = getTypes();

		JTable table = new JTable();// new DefaultTableModel(rowData,
									// columnNames));

		JTableHeader header = table.getTableHeader();
		header.addMouseListener(new TableHeaderMouseListener(table));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				if (SwingUtilities.isRightMouseButton(e)) {
					if (col == dateAnaliz_Colum || col == dateHimObr_Colum) {
						String date_choice = getDateFromDatePicker(table, col);
						table.setValueAt(date_choice, row, col);
					}

				}
				if (table.getSelectedColumn() == check_Colum) {
					double actv_value = Double
							.parseDouble((table.getValueAt(table.getSelectedRow(), actv_value_Colum)).toString());
					double mda = Double.parseDouble((table.getValueAt(table.getSelectedRow(), mda_Colum)).toString());
					Nuclide nuclide = NuclideDAO.getValueNuclideBySymbol(
							table.getValueAt(table.getSelectedRow(), nuclide_Colum).toString());
					Sample samp = getSampleObjectFromChoiceSampleCode();
					checkValueFrame(nuclide, samp, actv_value, mda);
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
								System.out.println(col);
								try {
									Double.parseDouble((String) value);
									dataTable[row][col] = value;
									fireTableCellUpdated(row, col);
								} catch (NumberFormatException e) {
								}
							}

							if (col == nuclide_Colum || col == razm_Colum || col == dimen_Colum || col == in_Prot_Colum
									|| col == TSI_Colum) {
								dataTable[row][col] = value;
								fireTableCellUpdated(row, col);
							}
						}

					}

					public int getColumnCount() {
						return columnNames.length;
					}

					public int getRowCount() {

						return dataTable.length;
					}

				};

				table.setModel(dtm);

				setUp_Nuclide(table.getColumnModel().getColumn(nuclide_Colum), isNewRow);

				setUp_Razmernosti(table.getColumnModel().getColumn(razm_Colum));
				setUp_Dimension(table.getColumnModel().getColumn(dimen_Colum));
				setUp_TSI(table.getColumnModel().getColumn(TSI_Colum));

				table.getColumnModel().getColumn(rsult_Id_Colum).setWidth(0);
				table.getColumnModel().getColumn(rsult_Id_Colum).setMinWidth(0);
				table.getColumnModel().getColumn(rsult_Id_Colum).setMaxWidth(0);
				table.getColumnModel().getColumn(rsult_Id_Colum).setPreferredWidth(0);

			}

		});
		return table;
	}

	private static String getDateFromDatePicker(JTable table, int col) {
		String date = "";
		if (col == dateAnaliz_Colum) {
			date = table.getValueAt(table.getSelectedRow(), dateAnaliz_Colum).toString();
		}
		if (col == dateHimObr_Colum) {
			date = table.getValueAt(table.getSelectedRow(), dateHimObr_Colum).toString();
		}
		final JFrame f = new JFrame();
		DatePicker dPicer = new DatePicker(f, false, date);
		String date_choice = dPicer.setPickedDate(false);
		return date_choice;
	}

	public static void checkValueFrame(Nuclide nuclide, Sample samp, Double actv_value, Double mda) {
		List<Sample> listAllSamp = SampleDAO.getInListAllValueSample();
		List<CheckResultClass> listCheckResultObject = new ArrayList<CheckResultClass>();

		for (Sample sample : listAllSamp) {
			if (sample.getObekt_na_izpitvane().getName_obekt_na_izpitvane()
					.equals(samp.getObekt_na_izpitvane().getName_obekt_na_izpitvane())
					&& sample.getDescription_sample().equals(samp.getDescription_sample())) {
				for (Results result : ResultsDAO.getListResultsFromColumnByVolume("sample", sample)) {
					if (result.getNuclide().getSymbol_nuclide().equals(nuclide.getSymbol_nuclide())) {
						int value = Integer.parseInt(sample.getRequest().getRecuest_code());
						CheckResultClass checkResultObject = new CheckResultClass(result.getValue_result(),
								result.getMda(), value);
						listCheckResultObject.add(checkResultObject);
					}
				}
			}

		}
		Collections.sort(listCheckResultObject, CheckResultClass.StuNameComparator);

		// TranscluentWindow round = new TranscluentWindow();
		JFrame f = new JFrame();
		new CheckViewValueDialogFrame(f, listCheckResultObject, actv_value, mda);
		// final Thread thread = new Thread(new Runnable() {
		// @Override
		// public void run() {
		//
		//
		//
		//
		// }
		// });
		// thread.start();
	}

	private static String[] getTabHeader() {
		String[] tableHeader = { "Нуклид", "Активност", "Неопределеност", "МДА", "Размерност", "Сигма", "Количество",
				"Мярка", "Т С И", "ДатаХимОбр", "ДатаАнализ", "В протокол", "Проверка", "Id_Result" };
		return tableHeader;
	}

	@SuppressWarnings("rawtypes")
	private static Class[] getTypes() {

		Class[] types = { String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, String.class, Boolean.class, String.class,
				Integer.class };

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
		rowFromTableResult[TSI_Colum] = result.getTsi().getName();
		rowFromTableResult[dateHimObr_Colum] = result.getDate_chim_oper();
		rowFromTableResult[dateAnaliz_Colum] = result.getDate_measur();
		rowFromTableResult[in_Prot_Colum] = false;
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
		rowFromTableResult[razm_Colum] = choiseRequest.getRazmernosti().getName_razmernosti();
		rowFromTableResult[sigma_Colum] = 2;
		rowFromTableResult[qunt_Colum] = 0.0;
		rowFromTableResult[dimen_Colum] = "";
		rowFromTableResult[dimen_Colum] = values_Dimension[0];
		rowFromTableResult[TSI_Colum] = "";
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
			rowFromTableResult[TSI_Colum] = results.getTsi().getName();
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

	private Boolean checkDataResult() {
		Boolean saveCheck = true;
		String str_Error = "";

		if (txtRqstCode.getText().trim().isEmpty()) {
			txtRqstCode.setBackground(Color.RED);
			str_Error = str_Error + "код на заявката" + "\n";
			saveCheck = false;
		}

		if (choicePokazatel.getSelectedItem().trim().isEmpty()) {
			choicePokazatel.setBackground(Color.RED);
			str_Error = str_Error + "изпитван показател" + "\n";
			saveCheck = false;
		}

		if (choiceMetody.getSelectedItem().trim().isEmpty()) {
			choiceMetody.setBackground(Color.RED);
			str_Error = str_Error + "метод" + "\n";

			saveCheck = false;
		}

		if (choiceOIR.getSelectedItem().trim().isEmpty()) {
			choiceOIR.setBackground(Color.RED);
			str_Error = str_Error + "извършил анализа" + "\n";
			saveCheck = false;
		}

		if (choiceORHO.getSelectedItem().trim().isEmpty()) {
			choiceORHO.setBackground(Color.RED);
			str_Error = str_Error + "изв. хим. обработка" + "\n";
			saveCheck = false;
		}

		if (!listSimbolBasikNulideToMetod.isEmpty() && choiceDobiv.getSelectedItem().trim().isEmpty()) {
			choiceDobiv.setBackground(Color.RED);
			str_Error = str_Error + "добив" + "\n";
			saveCheck = false;
		}

		if (!strCurrentDataInDataTable(dataTable).trim().isEmpty()) {
			str_Error = str_Error + strCurrentDataInDataTable(dataTable);
			System.out.println(str_Error);
			saveCheck = false;
		}

		if (!saveCheck) {
			JOptionPane.showMessageDialog(AddResultsViewWithTable_Test.this, str_Error, "Грешни данни за следните полета:",
					JOptionPane.ERROR_MESSAGE);
		}

		return saveCheck;
	}

	private static String strCurrentDataInDataTable(Object[][] dataTable) {
		String errDuplic = "";
		String errTSI = "";

		String errDateAnaliz = "";
		String errRazm = "";
		String errQunt = "";
		String errDim = "";
		List<String> listCodeNuclide = new ArrayList<String>();
		if (dataTable != null) {
			for (int i = 0; i < dataTable.length; i++) {
				String s1 = dataTable[i][mda_Colum].toString().toString();
				String s2 = dataTable[i][actv_value_Colum].toString();
				if ((Double.parseDouble((String) s1) + (Double.parseDouble((String) s2)) != 0)) {
					listCodeNuclide.add(dataTable[i][nuclide_Colum].toString());
					if (dataTable[i][razm_Colum].toString().trim().isEmpty()) {
						errRazm = "размерност " + "\n";
					}
					String razm = dataTable[i][razm_Colum].toString();
					String qunt = dataTable[i][qunt_Colum].toString();
					if (!razm.trim().isEmpty() && !razm.replace("Bq", "").isEmpty()) {
						if (qunt.trim().isEmpty() || Double.parseDouble(qunt) <= 0) {
							errQunt = "количество " + "\n";
						}

						if (dataTable[i][dimen_Colum].toString().trim().isEmpty()) {
							errDim = "мярка " + "\n";
						}

					}

					if (dataTable[i][TSI_Colum].toString().trim().isEmpty()) {
						errTSI = "Т С И " + "\n";
					}

					if (DatePicker.incorrectDate(dataTable[i][dateAnaliz_Colum].toString().trim(), false)) {
						errDateAnaliz = "дата на анализ" + "\n";
					}

				}
			}

			List<String> deDupStringList = new ArrayList<>(new HashSet<>(listCodeNuclide));

			if (deDupStringList.size() != listCodeNuclide.size()) {
				errDuplic = "повтарящи се нуклиди" + "\n";
			}
		} else {
			errDuplic = "невъведени данни" + "\n";
		}
		return (errTSI + errDateAnaliz + errDuplic + errRazm + errQunt + errDim);
	}

	public class testSwingWorker extends SwingWorker<ArrayList<List<Results>>, Integer> {
		ArrayList<List<Results>> masiveList = new ArrayList<List<Results>>();
		
		@Override
		protected ArrayList<List<Results>> doInBackground() throws Exception {
			TranscluentWindow round = new TranscluentWindow();
			
			Sample samp = getSampleObjectFromChoiceSampleCode();
			masiveList.add(creadListResultsObjects_ChoiseSample(samp));
			masiveList.add(AddResultsViewWithTable_Test.creadResultListForDelete(samp));
			masiveList.add(AddResultsViewWithTable_Test.creadResultListForSave(samp));
			
			round.StopWindow();
			return masiveList;
		}
		
	}
	
	
	public class TableHeaderMouseListener extends MouseAdapter {

		private JTable table;

		public TableHeaderMouseListener(JTable table) {
			this.table = table;
		}

		public void mouseClicked(MouseEvent event) {
			Point point = event.getPoint();
			int column = table.columnAtPoint(point);
			if (column == dateAnaliz_Colum || column == dateHimObr_Colum) {
				String date_choice = getDateFromDatePicker(table, column);
				for (int i = 0; i < dataTable.length; i++) {
					table.setValueAt(date_choice, i, column);
				}
			}

		}
	}

}