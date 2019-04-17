package WindowView;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Aplication.DobivDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.MetodyDAO;
import Aplication.Metody_to_NiclideForDobiveDAO;
//import Aplication.Metody_to_NiclideForDobiveDAO;
import Aplication.Metody_to_PokazatelDAO;
import Aplication.NuclideDAO;
import Aplication.Nuclide_to_PokazatelDAO;
import Aplication.PostDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import Aplication.TSI_DAO;
import Aplication.UsersDAO;
import DBase_Class.Dobiv;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Metody_to_NiclideForDobive;
//import DBase_Class.Metody_to_NiclideForDobive;
import DBase_Class.Metody_to_Pokazatel;
import DBase_Class.Nuclide;
import DBase_Class.Nuclide_to_Pokazatel;
import DBase_Class.Sample;
import DBase_Class.Users;
import WindowViewAplication.AutoSuggestor;

public class AddDobivViewWithTable extends JDialog {

	private static Users user_Redac = null;

	private final JPanel contentPanel = new JPanel();
	private static JTextField txtBasicValueResult;
	private static JTextField txtStandartCode;
	private static JLabel lblNameMetod;
	private static Choice choiceIzpitProd;
	private static Choice choiceOIR;
	private static Choice choiceORHO;
	private static Choice choiceMetody;
	private static Metody selectedMetod = null;

	private static List<Dobiv> listChoisedDobiv;
	private static List<Users> list_Users;
	private static List<String> list_UsersNameFamilyOIR;
	private static List<String> list_UsersNameFamilyORHO;
	private static List<Metody> listMetody;
	private static List<String> listStandartCodeAllDobiv = new ArrayList<String>();
	private static List<String> listSimbolBasikNulide;
	private static List<Nuclide> listNuclideToMetod;

	private static JButton btnAddRow;

	int newCountResults = 0;
	int countRowTabDobivs = 0;
	int addCount = 0;
	int rowWidth = 20;
	Boolean flagNotReadListPokazatel = true;
	Boolean flagNotReadListMetody = true;
	Boolean viewAddRowButton = false;
	Boolean corectStandartCode = true;

	boolean flagIncertedFile;

	private static JTable tabDobivs;
	private static String[] masuveSimbolBasikNuclide;
	private static String[] masive_NuclideToMetod;
	private static String[] masiveTSI;
	private static Object[][] dataTable;
	private static int tbl_Colum = 8;
	private static int nuclide_Colum = 0;
	private static int actv_value_Colum = 1;
	private static int uncrt_Colum = 2;
	private static int TSI_Colum = 3;
	private static int dateHimObr_Colum = 4;
	private static int dateAnaliz_Colum = 5;
	private static int check_Colum = 6;
	private static int dobiv_Id_Colum = 7;

	// private Font font = new Font("Tahoma", Font.PLAIN, 12);
	private JPanel basic_panel;
	private JScrollPane scrollTablePane;
	private static JTextField textFieldDobivDescrip;

	public AddDobivViewWithTable(JFrame parent, TranscluentWindow round, Users user) {
		super(parent, "Въвеждане на Добив", true);
		list_Users = UsersDAO.getInListAllValueUsers();
		// list_UsersNameFamily =
		// UsersDAO.getListStringAllName_FamilyUsersByPost(null);

		user_Redac = user;

		list_UsersNameFamilyOIR = UsersDAO.getListStringAllName_FamilyUsersByPost(PostDAO.getValuePostByName("ОИР"));
		list_UsersNameFamilyORHO = UsersDAO.getListStringAllName_FamilyUsersByPost(PostDAO.getValuePostByName("ОРХО"));
		listMetody = MetodyDAO.getInListAllValueMetody();
		for (Dobiv dobiv : DobivDAO.getListAllDobiv()) {
			listStandartCodeAllDobiv.add(dobiv.getCode_Standart());
		}

		masiveTSI = TSI_DAO.getMasiveStringAllValueTSI();

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

		BasicValueFileSection(basic_panel);

		btnTabFromFile(basic_panel);

		btnAddRow(basic_panel);

		ButtonPanell();
		round.StopWindow();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);

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
		choiceOIRListener();

	}

	private void choiceOIRListener() {
		choiceOIR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceOIR.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				choiceOIR.setBackground(Color.WHITE);
			}

		});
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
		for (Metody metod : listMetody) {
			choiceMetody.add(metod.getCode_metody());
		}
		createAllListsForNuclide();
		choiceMetodyListener();

	}

	private void choiceMetodyListener() {
		choiceMetody.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				setTextInMetodLabel();
				createAllListsForNuclide();
			}

		});

		choiceMetody.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceMetody.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setTextInMetodLabel();
				selectedMetod = MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem());

				// masive_NuclideToPokazatel =
				// createMasiveStringSimbolNuklide(listNuclideToMetod);
			}

			public void mousePressed(MouseEvent e) {
				choiceMetody.setBackground(Color.WHITE);
			}

		});
	}

	protected String[] createMasiveStringSimbolNuklide(List<Nuclide> list) {
		String[] masiv = new String[list.size()];
		int i = 0;
		for (Nuclide nuclide : list) {
			masiv[i] = nuclide.getSymbol_nuclide();
			i++;
		}
		return masiv;
	}

	@SuppressWarnings("unchecked")
	protected List<Nuclide> getListNuclideToMetod(Metody metod) {
		List<Nuclide> listnuclide = new ArrayList<Nuclide>();
		List<Metody_to_Pokazatel> listMet_Pokaz = Metody_to_PokazatelDAO.getListMetody_to_PokazatelByMetody(metod);

		for (Metody_to_Pokazatel metody_to_Pokazatel : listMet_Pokaz) {
			List<Nuclide_to_Pokazatel> listPokazatel = Nuclide_to_PokazatelDAO
					.getListNuclide_to_PokazatelByPokazatel(metody_to_Pokazatel.getPokazatel());
			for (Nuclide_to_Pokazatel nuclide_to_Pokazatel : listPokazatel) {
				listnuclide.add(nuclide_to_Pokazatel.getNuclide());
			}

		}

		return listnuclide;

	}

	private void setTextInMetodLabel() {
		if (choiceMetody.getSelectedItem() != null) {
			selectedMetod = MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem());
			lblNameMetod.setText(selectedMetod.getName_metody());
		}
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
		choiceORHOListener();
	}

	private void choiceORHOListener() {
		choiceORHO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceORHO.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				choiceORHO.setBackground(Color.WHITE);
			}

		});

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

		RequestViewFunction.setDataIn_Choice_Izpitvan_Produkt(choiceIzpitProd, null);
		choiceIzpitProdListener();
		
	}

	private void choiceIzpitProdListener() {
		choiceIzpitProd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceIzpitProd.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				choiceIzpitProd.setBackground(Color.WHITE);
			}

		});
	}

	private void CodeDobivSection(JPanel panel) {

		JLabel lblError = new JLabel();
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

		ArrayList<String> words = new ArrayList<>();
		for (Dobiv dobiv : DobivDAO.getListAllDobiv()) {
			words.add(dobiv.getCode_Standart());
		}

		AutoSuggestor autoSuggestor = new AutoSuggestor(txtStandartCode, this, words, Color.WHITE.brighter(),
				Color.BLUE, Color.RED, 0.79f);

		txtDobivCodeListener(lblError);

	}

	public void setTextLabelFromDobiv() {

	}

	public void txtDobivCodeListener(JLabel lblError) {
		txtStandartCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtStandartCode.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				txtStandartCode.setBackground(Color.WHITE);
			}

		});
		txtStandartCode.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {
				txtStandartCode.setBackground(Color.WHITE);
			}

			@Override
			public void keyReleased(KeyEvent event) {
				txtStandartCode.setBackground(Color.WHITE);
				listChoisedDobiv = DobivDAO.getList_DobivByCode_Standart(txtStandartCode.getText());

				if (listChoisedDobiv.size() == 0) {
					txtStandartCode.setForeground(Color.red);
					lblError.setVisible(true);
					lblError.setText("Добиви с този код не съществуват");
					corectStandartCode = true;
					validate();
					repaint();

				} else {
					txtStandartCode.setForeground(Color.BLACK);
					txtStandartCode.setBorder(new LineBorder(Color.BLACK));
					// txtStandartCode.setEditable(false);
					lblError.setVisible(true);
					lblError.setText("Добиви с този код съществуват");
					textFieldDobivDescrip.setText(listChoisedDobiv.get(0).getDescription());
					choiceIzpitProd.select(listChoisedDobiv.get(0).getIzpitvan_produkt().getName_zpitvan_produkt());
					choiceOIR.select(UsersDAO.getStringName_FamilyUser(listChoisedDobiv.get(0).getUser_measur()));
					choiceORHO.select(UsersDAO.getStringName_FamilyUser(listChoisedDobiv.get(0).getUser_chim_oper()));
					choiceMetody.select(listChoisedDobiv.get(0).getMetody().getCode_metody());
					corectStandartCode = false;
					validate();
					repaint();

				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				txtStandartCode.setBackground(Color.WHITE);
			}
		});
	}

	private void btnDataFromDBase(JPanel panel) {

		JButton btnCreadTable = new JButton("Данни от базата");
		btnCreadTableListenerFromDBase(panel, btnCreadTable);
		GridBagConstraints gbc_btnCreadTable = new GridBagConstraints();
		gbc_btnCreadTable.gridwidth = 2;
		gbc_btnCreadTable.anchor = GridBagConstraints.EAST;
		gbc_btnCreadTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreadTable.gridx = 0;
		gbc_btnCreadTable.gridy = 6;
		panel.add(btnCreadTable, gbc_btnCreadTable);
	}

	public void btnCreadTableListenerFromDBase(JPanel panel, JButton btnCreadTable) {
		btnCreadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (choiceMetody.getSelectedItem() != null) {
					List<Dobiv> ListDobivsFromStandart_code = DobivDAO.getListResultsFromColumnByVolume("code_Standart",
							txtStandartCode.getText());
					Dobiv[] masiveDobivForMetod = creadMasiveFromDobivsObjects_StandartCode(selectedMetod,
							ListDobivsFromStandart_code);

					if (masiveDobivForMetod.length > 0) {
						if (masiveDobivForMetod[0].getUser_measur() != null) {
							String str = masiveDobivForMetod[0].getUser_measur().getName_users() + " "
									+ masiveDobivForMetod[0].getUser_measur().getFamily_users();
							choiceOIR.select(str);
						}
						if (masiveDobivForMetod[0].getUser_chim_oper() != null) {
							String str = masiveDobivForMetod[0].getUser_chim_oper().getName_users() + " "
									+ masiveDobivForMetod[0].getUser_chim_oper().getFamily_users();
							choiceORHO.select(str);
						}
					}
					TranscluentWindow round = new TranscluentWindow();
					final Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {
							Object[][] ss = getDataTable(masiveDobivForMetod, listSimbolBasikNulide);
							dataTable = new Object[ss.length][tbl_Colum];
							dataTable = ss;
							Boolean isNewRow = false;
							ViewTableInPanel(panel, round, isNewRow);
						}
					});
					thread.start();

				}
			}

		});
	}

	private String[] creatMasiveSimbolNuclideToMrtod(List<String> listSimbolBasikNulide) {
		String[] masiveSimbolNuclide = new String[listSimbolBasikNulide.size()];
		int i = 0;
		for (String nuclide_to_Pokazatel : listSimbolBasikNulide) {
			masiveSimbolNuclide[i] = nuclide_to_Pokazatel;
			i++;
		}
		return masiveSimbolNuclide;
	}

	static List<String> getListSimbolBasikNulideToMetod(Metody metod) {
		List<String> listSimbolBasikNulide = new ArrayList<String>();
		List<Metody_to_NiclideForDobive> listMetody_NuclideForDobive = Metody_to_NiclideForDobiveDAO
				.getListMetody_to_NiclideForDobiveByMetody(metod);
		for (Metody_to_NiclideForDobive nuclide_to_Metod : listMetody_NuclideForDobive) {
			listSimbolBasikNulide.add(nuclide_to_Metod.getNuclide().getSymbol_nuclide());
		}
		return listSimbolBasikNulide;
	}

	private Dobiv[] creadMasiveFromDobivsObjects_StandartCode(Metody metod, List<Dobiv> ListDobivsFromStandart_code) {

		Dobiv[] masiveDobiv = new Dobiv[ListDobivsFromStandart_code.size()];
		int i = 0;
		for (Dobiv dobiv : ListDobivsFromStandart_code) {
			if (dobiv.getMetody().getCode_metody().equals(metod.getCode_metody())) {
				masiveDobiv[i] = dobiv;
			}
			i++;
		}
		return masiveDobiv;
	}

	private static Object[][] getDataTable(Dobiv[] masiveDobivForMetod, List<String> listSimbolBasikNulide) {

		int countBigMasive = masiveDobivForMetod.length + listSimbolBasikNulide.size();
		Object[][] bigMasiveDobiv = new Object[countBigMasive][tbl_Colum];

		for (int i = 0; i < masiveDobivForMetod.length; i++) {
			Dobiv dobiv = masiveDobivForMetod[i];
			bigMasiveDobiv[i] = rowWithValueDobivs(dobiv);

			Iterator<String> itr = listSimbolBasikNulide.iterator();
			while (itr.hasNext()) {
				String basikNulide = itr.next();
				if (basikNulide.equals(dobiv.getNuclide().getSymbol_nuclide())) {
					itr.remove();
				}
			}
		}

		int k = masiveDobivForMetod.length;
		for (String basikNulide : listSimbolBasikNulide) {
			bigMasiveDobiv[k] = rowWithoutValueDobivs(basikNulide);
			k++;
		}
		int countMasiveTable = masiveDobivForMetod.length + listSimbolBasikNulide.size();
		Object[][] tableDobiv = new Object[countMasiveTable][tbl_Colum];
		for (int i = 0; i < tableDobiv.length; i++) {
			tableDobiv[i] = bigMasiveDobiv[i];
		}

		return tableDobiv;
	}

	private static Object[] rowWithValueDobivs(Dobiv dobiv) {
		Object[] rowFromTableDobiv = new Object[tbl_Colum];
		try {
			rowFromTableDobiv[nuclide_Colum] = dobiv.getNuclide().getSymbol_nuclide();
			rowFromTableDobiv[actv_value_Colum] = dobiv.getValue_result();
			rowFromTableDobiv[uncrt_Colum] = dobiv.getUncertainty();
			rowFromTableDobiv[TSI_Colum] = dobiv.getTsi().getName();
			rowFromTableDobiv[dateHimObr_Colum] = dobiv.getDate_chim_oper();
			rowFromTableDobiv[dateAnaliz_Colum] = dobiv.getDate_measur();
			rowFromTableDobiv[check_Colum] = "Провери";
			rowFromTableDobiv[dobiv_Id_Colum] = dobiv.getId_dobiv();
		} catch (NullPointerException e) {
			JOptionPane.showInputDialog("Грешни данни за резултат:" + dobiv.getId_dobiv(), JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showInputDialog("Грешни данни за резултат:", JOptionPane.ERROR_MESSAGE);
		}
		return rowFromTableDobiv;
	}

	private static Object[] rowWithoutValueDobivs(String BasicNuclide) {
		Object[] rowFromTableResult = new Object[tbl_Colum];
		rowFromTableResult[nuclide_Colum] = BasicNuclide;
		rowFromTableResult[actv_value_Colum] = 0.0;
		rowFromTableResult[uncrt_Colum] = 0.0;
		rowFromTableResult[TSI_Colum] = "";
		rowFromTableResult[dateHimObr_Colum] = "";
		rowFromTableResult[dateAnaliz_Colum] = "";
		rowFromTableResult[check_Colum] = "Провери";
		rowFromTableResult[dobiv_Id_Colum] = null;
		return rowFromTableResult;
	}

	@SuppressWarnings("serial")
	private void ViewTableInPanel(JPanel panel, TranscluentWindow round, Boolean isNewRow) {
		round.StopWindow();
		if (scrollTablePane != null) {
			scrollTablePane.removeNotify();
		}

		if (listSimbolBasikNulide.size() < listNuclideToMetod.size()) {
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

		tabDobivs = CreateTableDobivs(isNewRow);
		countRowTabDobivs = dataTable.length;
		tabDobivs.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int col) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				String s1 = table.getValueAt(row, actv_value_Colum).toString();
				String s2 = table.getValueAt(row, uncrt_Colum).toString();

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

		panelTable.validate();
		panelTable.repaint();

		scrollTablePane.validate();
		scrollTablePane.repaint();

		panel.validate();
		panel.repaint();

		setSize(1100, (countRowTabDobivs * rowWidth) + 340);
		setLocationRelativeTo(null);
		validate();
		repaint();

	}

	public JTable CreateTableDobivs(Boolean isNewRow) {

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
					double actv_value = Double
							.parseDouble((table.getValueAt(table.getSelectedRow(), actv_value_Colum)).toString());
					Nuclide nuclide = NuclideDAO.getValueNuclideBySymbol(
							table.getValueAt(table.getSelectedRow(), nuclide_Colum).toString());
					checkValueFrame(nuclide, selectedMetod, actv_value);
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

							if (col == actv_value_Colum || col == uncrt_Colum) {
								try {
									Double.parseDouble((String) value);
									dataTable[row][col] = value;
									fireTableCellUpdated(row, col);
								} catch (NumberFormatException e) {
								}
							}

							if (col == nuclide_Colum || col == TSI_Colum) {
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
				setUp_TSI(table.getColumnModel().getColumn(TSI_Colum));

				table.getColumnModel().getColumn(dobiv_Id_Colum).setWidth(0);
				table.getColumnModel().getColumn(dobiv_Id_Colum).setMinWidth(0);
				table.getColumnModel().getColumn(dobiv_Id_Colum).setMaxWidth(0);
				table.getColumnModel().getColumn(dobiv_Id_Colum).setPreferredWidth(0);

			}

		});
		return table;
	}

	private void ButtonPanell() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("Запис");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Dobiv> listDobivsForSave = creadListFromDobivObjectForSave();
				for (Dobiv dobiv : listDobivsForSave) {
					saveDobivsObjectInDBase(dobiv);
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
				setVisible(false);
			}
		});
		// cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	private Boolean checkDobiv() {
		Boolean saveCheck = true;
		String str_Error = "";

		if (txtStandartCode.getText().trim().isEmpty()) {
			txtStandartCode.setBackground(Color.RED);
		str_Error = str_Error + "код на стандарта" + "\n";
		 saveCheck = false;
		 }

		if (choiceIzpitProd.getSelectedItem().trim().isEmpty()) {
			choiceIzpitProd.setBackground(Color.RED);
			str_Error = str_Error + "изпитван продукт" + "\n";
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

		if (!strCurrentDataInDataTable(dataTable).trim().isEmpty()) {
			str_Error = str_Error + strCurrentDataInDataTable(dataTable);
			saveCheck = false;
		}

		if (!saveCheck) {
			JOptionPane.showMessageDialog(AddDobivViewWithTable.this, str_Error, "Грешни данни за следните полета:",
					JOptionPane.ERROR_MESSAGE);
		}

		return saveCheck;
	}

	private static String strCurrentDataInDataTable(Object[][] dataTable) {
		String errDuplic ="";
		String errTSI ="";
		String errDateHim ="";
		String errDateAnaliz ="";
		List<String> listCodeNuclide = new ArrayList<String>();
		if (dataTable != null) {
			for (int i = 0; i < dataTable.length; i++) {
				String s1 = dataTable[i][uncrt_Colum].toString().toString();
				String s2 = dataTable[i][actv_value_Colum].toString();
				if ((Double.parseDouble((String) s1) + (Double.parseDouble((String) s2)) != 0)) {
					listCodeNuclide.add(dataTable[i][nuclide_Colum].toString());
					
					if (dataTable[i][TSI_Colum].toString().trim().isEmpty()) {
						errTSI = "Т С И " + "\n";
					}
					
					if (DatePicker.incorrectDate(dataTable[i][dateHimObr_Colum].toString().trim(), false)) {
						errDateHim =  "дата на хим. обраб." + "\n";
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
			errDuplic =  "невъведени данни"+ "\n";
		}
		return (errTSI + errDateHim  + errDateAnaliz + errDuplic);
	}

	private List<Dobiv> creadListFromDobivObjectForSave() {
		Boolean fl;
		List<Dobiv> listDobivsForSave = new ArrayList<Dobiv>();
		List<Dobiv> listDobivsForDelete = new ArrayList<Dobiv>();
		List<Dobiv> listDobivsFromTable = new ArrayList<Dobiv>();
		if (checkDobiv()) {
			for (int i = 0; i < dataTable.length; i++) {
				String s1 = dataTable[i][uncrt_Colum].toString().toString();
				String s2 = dataTable[i][actv_value_Colum].toString();
				if ((Double.parseDouble((String) s1) != 0 || (Double.parseDouble((String) s2)) != 0)) {
					listDobivsFromTable.add(creadDobivObject(i));
				} else {
					if (dataTable[i][dobiv_Id_Colum] != null) {
						listDobivsForDelete.add(DobivDAO.getDobivById((int) dataTable[i][dobiv_Id_Colum]));
					}
				}
			}
		}

		List<Dobiv> ListDobivsFromDBase = DobivDAO.getList_DobivByCode_Standart(txtStandartCode.getText());
		Iterator<Dobiv> itr = null;
		for (Dobiv dobiv : listDobivsFromTable) {

			itr = ListDobivsFromDBase.iterator();
			fl = false;
			while (itr.hasNext()) {
				String codeNulide = itr.next().getNuclide().getSymbol_nuclide();
				if (codeNulide.equals(dobiv.getNuclide().getSymbol_nuclide())) {
					itr.remove();
					listDobivsForSave.add(dobiv);
					fl = true;
				}
			}
			if (!fl) {
				listDobivsForSave.add(dobiv);
			}
		}

		for (Dobiv dobiv : ListDobivsFromDBase) {

			listDobivsForSave.add(dobiv);
		}

		for (Dobiv dobiv : listDobivsForDelete) {

			DobivDAO.deleteDobivById(dobiv.getId_dobiv());
		}
		;

		return listDobivsForSave;
	}

	private static Dobiv creadDobivObject(int i) {
		Dobiv dobiv;
		if (dataTable[i][dobiv_Id_Colum] == null) {
			dobiv = creadDobivsObject(i, new Dobiv());
		} else {
			dobiv = creadDobivsObject(i, DobivDAO.getDobivById((int) dataTable[i][dobiv_Id_Colum]));
		}
		return dobiv;
	}

	private static Dobiv creadDobivsObject(int i, Dobiv dobiv) {

		dobiv.setCode_Standart(txtStandartCode.getText());
		dobiv.setMetody((Metody) MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()));
		dobiv.setIzpitvan_produkt(
				Izpitvan_produktDAO.getValueIzpitvan_produktByName(choiceIzpitProd.getSelectedItem()));
		dobiv.setDescription(textFieldDobivDescrip.getText());
		dobiv.setNuclide(NuclideDAO.getValueNuclideBySymbol(dataTable[i][nuclide_Colum].toString()));
		dobiv.setValue_result(Double.parseDouble(dataTable[i][actv_value_Colum].toString()));
		dobiv.setUncertainty(Double.parseDouble(dataTable[i][uncrt_Colum].toString()));
		dobiv.setTsi(TSI_DAO.getValueTSIByName(dataTable[i][TSI_Colum].toString()));
		dobiv.setDate_chim_oper(dataTable[i][dateHimObr_Colum].toString());
		dobiv.setDate_measur(dataTable[i][dateAnaliz_Colum].toString());
		dobiv.setDate_redac(RequestViewFunction.DateNaw(false));
		String choiceUser = choiceORHO.getSelectedItem();
		for (Users user : list_Users) {
			if (choiceUser.substring(0, choiceUser.indexOf(" ")).equals(user.getName_users())
					&& choiceUser.substring(choiceUser.indexOf(" ") + 1).equals(user.getFamily_users())) {
				dobiv.setUser_chim_oper(user);
			}
		}
		choiceUser = choiceOIR.getSelectedItem();
		for (Users user : list_Users) {
			if (choiceUser.substring(0, choiceUser.indexOf(" ")).equals(user.getName_users())
					&& choiceUser.substring(choiceUser.indexOf(" ") + 1).equals(user.getFamily_users())) {
				dobiv.setUser_measur(user);
			}
		}
		dobiv.setUser_redac(user_Redac);
		dobiv.setBasic_value(txtBasicValueResult.getText());

		return dobiv;
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
		gbc_txtBasicValueResult.gridwidth = 3;
		gbc_txtBasicValueResult.gridx = 1;
		gbc_txtBasicValueResult.gridy = 5;
		panel.add(txtBasicValueResult, gbc_txtBasicValueResult);

		txtBasicValueResult.setColumns(10);

	}

	private static void saveDobivsObjectInDBase(Dobiv dobiv) {

		DobivDAO.updateDobiv(dobiv);
		;
	}

	public static void setUp_Nuclide(TableColumn Nuclide_Column, Boolean isNewRow) {
		JComboBox<?> comboBox = new JComboBox<Object>(masuveSimbolBasikNuclide);
		if (isNewRow) {
			comboBox = new JComboBox<Object>(masive_NuclideToMetod);
		}
		Nuclide_Column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		Nuclide_Column.setCellRenderer(renderer);
	}

	private void setUp_TSI(TableColumn tSI_column) {
		JComboBox<?> comboBox = new JComboBox<Object>(masiveTSI);
		tSI_column.setCellEditor(new DefaultCellEditor(comboBox));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Натисни за избор");
		tSI_column.setCellRenderer(renderer);

	}

	private void btnOpenFile(JPanel panel) {
		JButton btnOpenFile = new JButton("Отвори");
		btnOpenFileListener(btnOpenFile);
		GridBagConstraints gbc_btnBasicDataFile = new GridBagConstraints();
		gbc_btnBasicDataFile.anchor = GridBagConstraints.WEST;
		gbc_btnBasicDataFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnBasicDataFile.gridx = 4;
		gbc_btnBasicDataFile.gridy = 5;
		basic_panel.add(btnOpenFile, gbc_btnBasicDataFile);

	}

	public void btnOpenFileListener(JButton btnOpenFile) {
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				f.showOpenDialog(null);
				try {
					txtBasicValueResult.setText((f.getSelectedFile()).toString());
					ReadGamaFile.ReadGamaFile(f.getSelectedFile().toString());

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
						if (MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem()).getId_metody() == 9) {
							// TranscluentWindow round = new
							// TranscluentWindow();
							// final Thread thread = new Thread(new Runnable() {
							// @Override
							// public void run() {
							//
							// readFromGenie2kFile();
							// ViewTableInPanel(basic_panel, round);
							// }
							// });
							// thread.start();

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
		listNuclideToMetod = getListNuclideToMetod(selectedMetod);
		masive_NuclideToMetod = createMasiveStringSimbolNuklide(listNuclideToMetod);

		int countDataTable = dataTable.length;
		Object[][] newTable = new Object[countDataTable + 1][tbl_Colum];
		for (int i = 0; i < countDataTable; i++) {
			newTable[i] = dataTable[i];
		}
		for (Nuclide nuk : listNuclideToMetod) {
			System.out.println(nuk.getSymbol_nuclide());
		}
		System.out.println(masive_NuclideToMetod[0]);
		newTable[countDataTable] = rowWithoutValueDobivs(masive_NuclideToMetod[0]);
		dataTable = new Object[newTable.length][tbl_Colum];
		for (int i = 0; i < newTable.length; i++) {
			dataTable[i] = newTable[i];
		}
	}

	private void createAllListsForNuclide() {
		selectedMetod = MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem());
		listSimbolBasikNulide = getListSimbolBasikNulideToMetod(selectedMetod);
		masuveSimbolBasikNuclide = creatMasiveSimbolNuclideToMrtod(listSimbolBasikNulide);
		listNuclideToMetod = getListNuclideToMetod(selectedMetod);
		masive_NuclideToMetod = createMasiveStringSimbolNuklide(listNuclideToMetod);
	}

	public static void checkValueFrame(Nuclide nuclide, Metody curentMetod, Double actv_value) {
		List<Metody> listAllMetody = MetodyDAO.getInListAllValueMetody();
		List<CheckResultClass> listCheckResultObject = new ArrayList<CheckResultClass>();

		for (Metody metod : listAllMetody) {

			if (metod.getCode_metody().equals(curentMetod.getCode_metody())) {
				for (Dobiv dobiv : DobivDAO.getListDobivByMetody(metod)) {

					if (dobiv.getNuclide().getSymbol_nuclide().equals(nuclide.getSymbol_nuclide())) {
						int value = dobiv.getId_dobiv();
						CheckResultClass checkResultObject = new CheckResultClass(dobiv.getValue_result(), null, value);
						listCheckResultObject.add(checkResultObject);
					}
				}
			}

		}
		Collections.sort(listCheckResultObject, CheckResultClass.StuNameComparator);

		JFrame f = new JFrame();
		new CheckViewValueDialogFrame(f, listCheckResultObject, actv_value, null);
	}

	private static String[] getTabHeader() {
		String[] tableHeader = { "Нуклид", "Добив", "Неопределеност", "Т С И", "ДатаХимОбр", "ДатаАнализ", "Проверка",
				"Id_Result" };
		return tableHeader;
	}

	@SuppressWarnings("rawtypes")
	private static Class[] getTypes() {

		Class[] types = { String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, Integer.class };

		return types;
	}

}
