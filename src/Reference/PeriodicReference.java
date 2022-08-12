package Reference;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DBase_Class.IzpitvanPokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Period;
import DBase_Class.Request_To_ObektNaIzpitvaneRequest;
import DBase_Class.Results;
import DBase_Class.Sample;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.TranscluentWindow;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.Request_To_ObektNaIzpitvaneRequestDAO;
import Aplication.SampleDAO;

public class PeriodicReference extends JDialog {

	private static final long serialVersionUID = 1L;

	int newCountResults = 0;
	static int countRowTabDobivs = 0;
	int addCount = 0;

	static Choice choiceIzpitProd;
	static Choice choicePokazatel;
	static JRadioButton rdbtnMesechni;
	static Choice choiceObectNaIzpit;
	static Choice choiceObectNaProbata;
	static Choice choiceGodina;
	static JRadioButton rdbtnMDA;
	static JRadioButton rdbtnAbsNeopred;
	static JRadioButton rdbtnBezNeopred;
	static String lbl_Text_MDA_Actyvnost = "";
	

	public PeriodicReference(JFrame parent, TranscluentWindow round) {
		super(parent, ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("PeriodicReference_TitleName"), true);
		setResizable(false);

		setSize(650, 250);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel basic_panel_1 = new JPanel();
		contentPanel.add(basic_panel_1, BorderLayout.NORTH);
		basic_panel_1.setBounds(new Rectangle(5, 0, 0, 0));
		GridBagLayout gbl_basic_panel = new GridBagLayout();
		gbl_basic_panel.columnWidths = new int[] { 112, 111, 0, 115, 122, 0 };
		gbl_basic_panel.rowHeights = new int[] { 30, 30, 30, 30, 30, 0, 0, 30, 0 };
		gbl_basic_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_basic_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		basic_panel_1.setLayout(gbl_basic_panel);
		JLabel lblIzpitProd = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_IzpitvanProdukt"));
		GridBagConstraints gbc_lblIzpitProd = new GridBagConstraints();
		gbc_lblIzpitProd.anchor = GridBagConstraints.EAST;
		gbc_lblIzpitProd.insets = new Insets(0, 0, 5, 5);
		gbc_lblIzpitProd.gridx = 0;
		gbc_lblIzpitProd.gridy = 1;
		basic_panel_1.add(lblIzpitProd, gbc_lblIzpitProd);

		choiceIzpitProd = new Choice();
		choiceIzpitProd.setBackground(Color.WHITE);
		choiceIzpitProd.setPreferredSize(new Dimension(205, 20));
		GridBagConstraints gbc_choiceIzpitProd = new GridBagConstraints();
		gbc_choiceIzpitProd.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceIzpitProd.gridwidth = 2;
		gbc_choiceIzpitProd.insets = new Insets(0, 0, 5, 5);
		gbc_choiceIzpitProd.gridx = 1;
		gbc_choiceIzpitProd.gridy = 1;
		basic_panel_1.add(choiceIzpitProd, gbc_choiceIzpitProd);
		getIntemsInChoice(choiceIzpitProd, Izpitvan_produktDAO.getMasiveStringAllValueIzpitvan_produkt());

		JLabel lblPokazatel = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_Pokazatel"));
		GridBagConstraints gbc_lblPokazatel = new GridBagConstraints();
		gbc_lblPokazatel.anchor = GridBagConstraints.EAST;
		gbc_lblPokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPokazatel.gridx = 0;
		gbc_lblPokazatel.gridy = 2;
		basic_panel_1.add(lblPokazatel, gbc_lblPokazatel);

		choicePokazatel = new Choice();
		GridBagConstraints gbc_choicePokazatel = new GridBagConstraints();
		gbc_choicePokazatel.fill = GridBagConstraints.HORIZONTAL;
		gbc_choicePokazatel.gridwidth = 2;
		gbc_choicePokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_choicePokazatel.gridx = 1;
		gbc_choicePokazatel.gridy = 2;
		basic_panel_1.add(choicePokazatel, gbc_choicePokazatel);
		getIntemsInChoice(choicePokazatel, List_izpitvan_pokazatelDAO.getMasiveStringAllValueList_Izpitvan_Pokazatel());

		rdbtnMDA = new JRadioButton(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelRadioButton_MDA"));
		GridBagConstraints gbc_rdbtnMDA = new GridBagConstraints();
		gbc_rdbtnMDA.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMDA.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMDA.gridx = 3;
		gbc_rdbtnMDA.gridy = 2;
		basic_panel_1.add(rdbtnMDA, gbc_rdbtnMDA);
		rdbtnMDA.setSelected(true);

		JRadioButton rdbtnActivnost = new JRadioButton(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelRadioButton_Activnost"));
		GridBagConstraints gbc_rdbtnActivnost = new GridBagConstraints();
		gbc_rdbtnActivnost.anchor = GridBagConstraints.WEST;
		gbc_rdbtnActivnost.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnActivnost.gridx = 4;
		gbc_rdbtnActivnost.gridy = 2;
		basic_panel_1.add(rdbtnActivnost, gbc_rdbtnActivnost);
		rdbtnActivnost.setSelected(false);

		JLabel lblObectNaIzpit = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Reference_LabelText_Obekt_Na_Izpitvane"));
		GridBagConstraints gbc_lblObectNaIzpit = new GridBagConstraints();
		gbc_lblObectNaIzpit.anchor = GridBagConstraints.EAST;
		gbc_lblObectNaIzpit.insets = new Insets(0, 0, 5, 5);
		gbc_lblObectNaIzpit.gridx = 0;
		gbc_lblObectNaIzpit.gridy = 3;
		basic_panel_1.add(lblObectNaIzpit, gbc_lblObectNaIzpit);

		choiceObectNaIzpit = new Choice();
		choiceObectNaIzpit.setPreferredSize(new Dimension(205, 20));
		choiceObectNaIzpit.setBackground(Color.WHITE);
		GridBagConstraints gbc_choiceObectNaIzpit = new GridBagConstraints();
		gbc_choiceObectNaIzpit.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceObectNaIzpit.gridwidth = 2;
		gbc_choiceObectNaIzpit.insets = new Insets(0, 0, 5, 5);
		gbc_choiceObectNaIzpit.gridx = 1;
		gbc_choiceObectNaIzpit.gridy = 3;
		basic_panel_1.add(choiceObectNaIzpit, gbc_choiceObectNaIzpit);

		JLabel lblNeopredelenost = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_Neopredelenost"));
		GridBagConstraints gbc_lblNeopredelenost = new GridBagConstraints();
		gbc_lblNeopredelenost.anchor = GridBagConstraints.EAST;
		gbc_lblNeopredelenost.insets = new Insets(0, 0, 5, 5);
		gbc_lblNeopredelenost.gridx = 3;
		gbc_lblNeopredelenost.gridy = 3;
		basic_panel_1.add(lblNeopredelenost, gbc_lblNeopredelenost);

		rdbtnAbsNeopred = new JRadioButton(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelRadioButton_AbsNeopred"));
		GridBagConstraints gbc_rdbtnAbsNeopred = new GridBagConstraints();
		gbc_rdbtnAbsNeopred.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAbsNeopred.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnAbsNeopred.gridx = 4;
		gbc_rdbtnAbsNeopred.gridy = 3;
		basic_panel_1.add(rdbtnAbsNeopred, gbc_rdbtnAbsNeopred);
		rdbtnAbsNeopred.setSelected(true);

		JRadioButton rdbtnOtnNeopred = new JRadioButton(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelRadioButton_OtnNeopred"));
		GridBagConstraints gbc_rdbtnOtnNeopred = new GridBagConstraints();
		gbc_rdbtnOtnNeopred.anchor = GridBagConstraints.WEST;
		gbc_rdbtnOtnNeopred.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnOtnNeopred.gridx = 4;
		gbc_rdbtnOtnNeopred.gridy = 4;
		basic_panel_1.add(rdbtnOtnNeopred, gbc_rdbtnOtnNeopred);
		rdbtnOtnNeopred.setSelected(false);
		
		rdbtnBezNeopred  = new JRadioButton(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelRadioButton_BezNeopred"));
		GridBagConstraints gbc_rdbtnBezNeopred = new GridBagConstraints();
		gbc_rdbtnBezNeopred.anchor = GridBagConstraints.WEST;
		gbc_rdbtnBezNeopred.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnBezNeopred.gridx = 4;
		gbc_rdbtnBezNeopred.gridy = 5;
		basic_panel_1.add(rdbtnBezNeopred, gbc_rdbtnBezNeopred);
		rdbtnBezNeopred.setSelected(false);

		JLabel lblObectNaProbata = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Reference_LabelText_Obekt_Na_Izpitvane_Na_Probata"));
		GridBagConstraints gbc_lblObectNaProbata = new GridBagConstraints();
		gbc_lblObectNaProbata.insets = new Insets(0, 0, 5, 5);
		gbc_lblObectNaProbata.anchor = GridBagConstraints.EAST;
		gbc_lblObectNaProbata.gridx = 0;
		gbc_lblObectNaProbata.gridy = 4;
		basic_panel_1.add(lblObectNaProbata, gbc_lblObectNaProbata);

		choiceObectNaProbata = new Choice();
		choiceObectNaProbata.setPreferredSize(new Dimension(205, 20));
		choiceObectNaProbata.setBackground(Color.WHITE);
		GridBagConstraints gbc_choiceObectNaProbata = new GridBagConstraints();
		gbc_choiceObectNaProbata.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceObectNaProbata.gridwidth = 2;
		gbc_choiceObectNaProbata.insets = new Insets(0, 0, 5, 5);
		gbc_choiceObectNaProbata.gridx = 1;
		gbc_choiceObectNaProbata.gridy = 4;
		basic_panel_1.add(choiceObectNaProbata, gbc_choiceObectNaProbata);

		JLabel lblGodina = new JLabel(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelText_Godina"));
		GridBagConstraints gbc_lblGodina = new GridBagConstraints();
		gbc_lblGodina.anchor = GridBagConstraints.EAST;
		gbc_lblGodina.insets = new Insets(0, 0, 5, 5);
		gbc_lblGodina.gridx = 0;
		gbc_lblGodina.gridy = 5;
		basic_panel_1.add(lblGodina, gbc_lblGodina);

		choiceGodina = new Choice();
		choiceGodina.setPreferredSize(new Dimension(205, 20));
		choiceGodina.setBackground(Color.WHITE);
		GridBagConstraints gbc_choiceGodina = new GridBagConstraints();
		gbc_choiceGodina.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceGodina.gridwidth = 2;
		gbc_choiceGodina.insets = new Insets(0, 0, 5, 5);
		gbc_choiceGodina.gridx = 1;
		gbc_choiceGodina.gridy = 5;
		basic_panel_1.add(choiceGodina, gbc_choiceGodina);
		getIntemsInChoice(choiceGodina, SampleDAO.getListNonRecurringObjectFromColumn("godina_period"));

		rdbtnMesechni = new JRadioButton(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelRadioButton_mesechni"));
		GridBagConstraints gbc_rdbtnMesechni = new GridBagConstraints();
		gbc_rdbtnMesechni.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMesechni.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMesechni.gridx = 3;
		gbc_rdbtnMesechni.gridy = 1;
		basic_panel_1.add(rdbtnMesechni, gbc_rdbtnMesechni);
		rdbtnMesechni.setSelected(true);

		JRadioButton rdbtnTrimesechni = new JRadioButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Reference_LabelRadioButton_TriMesechni"));
		GridBagConstraints gbc_rdbtnTrimesechni = new GridBagConstraints();
		gbc_rdbtnTrimesechni.anchor = GridBagConstraints.WEST;
		gbc_rdbtnTrimesechni.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnTrimesechni.gridx = 4;
		gbc_rdbtnTrimesechni.gridy = 1;
		basic_panel_1.add(rdbtnTrimesechni, gbc_rdbtnTrimesechni);
		rdbtnTrimesechni.setSelected(false);
		
		
		JButton btnStart = new JButton(
				ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("Reference_LabelButton_Start"));
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.anchor = GridBagConstraints.EAST;
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 2;
		gbc_btnStart.gridy = 6;
		basic_panel_1.add(btnStart, gbc_btnStart);
		btnStart.setVisible(true);

		listener_btnStart(choicePokazatel, choiceObectNaIzpit, choiceObectNaProbata, choiceIzpitProd, choiceGodina, btnStart, rdbtnMDA,
				rdbtnMesechni, rdbtnAbsNeopred);

		llistener_rdbtnActivnost(rdbtnMDA, rdbtnActivnost, rdbtnOtnNeopred, rdbtnAbsNeopred, lblNeopredelenost, rdbtnBezNeopred);

		llistener_rbtn1_VS_rdbtn2_VS_rdbtn3(rdbtnAbsNeopred, rdbtnOtnNeopred, rdbtnBezNeopred);
		
		listener_ChoiceIzpitProd(choiceIzpitProd, choicePokazatel, choiceObectNaIzpit, choiceObectNaProbata);
		listener_Pokazatel(choiceIzpitProd, choicePokazatel, choiceObectNaIzpit, choiceObectNaProbata);

		listener_rbtnTrimesechni(rdbtnMesechni, rdbtnTrimesechni);
		listener_rdbtnMesechni(rdbtnMesechni, rdbtnTrimesechni);
		
		JButton btnCancel = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 6;
		basic_panel_1.add(btnCancel, gbc_btnCancel);
		
		
		btnCancelListener(btnCancel);
		

		round.StopWindow();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	private void btnCancelListener(JButton btnCancel) {
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
	}
	
	private void llistener_rdbtnActivnost(JRadioButton rdbtnMDA, JRadioButton rdbtnActivnost,
			JRadioButton rdbtnOtnNeopred, JRadioButton rdbtnAbsNeopred, JLabel lblNeopredelenost, JRadioButton rdbtnBezNeopred) {
		VisibleNeopredBlok(rdbtnActivnost, rdbtnOtnNeopred, rdbtnAbsNeopred, lblNeopredelenost, rdbtnBezNeopred);
		rdbtnMDA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectReversiveRButtons(rdbtnMDA, rdbtnActivnost);
				VisibleNeopredBlok(rdbtnActivnost, rdbtnOtnNeopred, rdbtnAbsNeopred, lblNeopredelenost, rdbtnBezNeopred);
			}

		});

		rdbtnActivnost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectReversiveRButtons(rdbtnActivnost, rdbtnMDA);
				VisibleNeopredBlok(rdbtnActivnost, rdbtnOtnNeopred, rdbtnAbsNeopred, lblNeopredelenost, rdbtnBezNeopred);
			}

		});

	}

	@SuppressWarnings("unused")
	private void llistener_rbtn1_VS_rdbtn2(JRadioButton rdbtn1, JRadioButton rdbtn2) {
		rdbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectReversiveRButtons(rdbtn2, rdbtn1);
			}

		});

		rdbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectReversiveRButtons(rdbtn1, rdbtn2);
			}
		});

	}

	private void llistener_rbtn1_VS_rdbtn2_VS_rdbtn3(JRadioButton rdbtn1, JRadioButton rdbtn2, JRadioButton rdbtn3) {
		
		
		rdbtn2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtn2.isSelected()){
				rdbtn2.setSelected(true);
				rdbtn1.setSelected(false);
				rdbtn3.setSelected(false);
				}else{
					rdbtn2.setSelected(true);	
				}
			}
		});
		
		
		rdbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtn1.isSelected()){
				rdbtn2.setSelected(false);
				rdbtn1.setSelected(true);
				rdbtn3.setSelected(false);
				}else{
					rdbtn1.setSelected(true);	
				}
			}
		});
		
	
			rdbtn3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(rdbtn3.isSelected()){
					rdbtn2.setSelected(false);
					rdbtn1.setSelected(false);
					rdbtn3.setSelected(true);
					}else{
						rdbtn3.setSelected(true);	
					}
				}
			});
			
		
	}
	
	
	private void VisibleNeopredBlok(JRadioButton rdbtnActivnost, JRadioButton rdbtnOtnNeopred,
			JRadioButton rdbtnAbsNeopred, JLabel lblNeopredelenost, JRadioButton rdbtnBezNeopred) {
		if (rdbtnActivnost.isSelected()) {
			rdbtnOtnNeopred.setVisible(true);
			rdbtnAbsNeopred.setVisible(true);
			rdbtnBezNeopred.setVisible(true);
			lblNeopredelenost.setVisible(true);

		} else {
			rdbtnOtnNeopred.setVisible(false);
			rdbtnAbsNeopred.setVisible(false);
			rdbtnBezNeopred.setVisible(false);
			lblNeopredelenost.setVisible(false);
		}
	}

	private void listener_rdbtnMesechni(JRadioButton rdbtnMesechni, JRadioButton rdbtnTrimesechni) {
		rdbtnMesechni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectReversiveRButtons(rdbtnMesechni, rdbtnTrimesechni);
			}
		});
	}

	private void listener_rbtnTrimesechni(JRadioButton rdbtnMesechni, JRadioButton rdbtnTrimesechni) {
		rdbtnTrimesechni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectReversiveRButtons(rdbtnTrimesechni, rdbtnMesechni);
			}

		});
	}
	
	private void selectReversiveRButtons(JRadioButton rdbtn_1, JRadioButton rdbtn_2) {
		if (rdbtn_1.isSelected()) {
			rdbtn_2.setSelected(false);
		} else {
			rdbtn_2.setSelected(true);
		}
	}

	private void listener_btnStart(Choice choicePokazatel, Choice choiceObectNaIzpit, Choice choiceObectNaProbata, Choice choiceIzpitProd,
			Choice choiceGodina, JButton btnStart, JRadioButton rdbtnMDA, JRadioButton rdbtnMesechni,
			JRadioButton rdbtnAbsNeopred) {
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		
				TranscluentWindow round = new TranscluentWindow();

				final Thread thread = new Thread(new Runnable() {
				

					@Override
					public void run() {

						JFrame f = new JFrame();
						String titleName = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("PeriodicReference_TitleName");
						new ViewReferenceTable(f, round,titleName, choicePokazatel, choiceObectNaIzpit, choiceObectNaProbata,
								choiceGodina, choiceIzpitProd,   rdbtnMDA,  rdbtnMesechni,  rdbtnAbsNeopred, rdbtnBezNeopred);
						
					}
				});
				thread.start();

				
			}
		});
	}

	private void listener_Pokazatel(Choice choiceIzpitProd, Choice choicePokazatel, Choice choiceObectNaIzpit,
			Choice choiceObectNaProbata) {
		choicePokazatel.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				setItemsInChoiceObectNaIzpit(choiceIzpitProd, choicePokazatel, choiceObectNaIzpit,
						choiceObectNaProbata);
			}

		});
	}

	private void listener_ChoiceIzpitProd(Choice choiceIzpitProd, Choice choicePokazatel, Choice choiceObectNaIzpit,
			Choice choiceObectNaProbata) {
		choiceIzpitProd.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				setItemsInChoiceObectNaIzpit(choiceIzpitProd, choicePokazatel, choiceObectNaIzpit,
						choiceObectNaProbata);
			}

		});
	}

	private List<List<String>> getListRequestByIzpitvanProduktAndPokazatel(String izpitvanProdukt,
			String pokazatel_string) {

		List<String> listObectNaIzpitvane = new ArrayList<>();
		List<String> listObectNaIzpitvaneSample = new ArrayList<>();
		List_izpitvan_pokazatel pokazatel = List_izpitvan_pokazatelDAO
				.getValueIzpitvan_pokazatelByName(pokazatel_string);
		System.out.println(pokazatel.getName_pokazatel());
		List<IzpitvanPokazatel> listPokazatel = IzpitvanPokazatelDAO.getValueIzpitvan_pokazatelByPokazatel(pokazatel);

		for (IzpitvanPokazatel izpitvanPokazatel : listPokazatel) {
			if (izpitvanPokazatel.getRequest().getIzpitvan_produkt().getName_zpitvan_produkt()
					.equals(izpitvanProdukt)) {
				List<Sample> listSample = SampleDAO.getListSampleFromColumnByVolume("request",
						izpitvanPokazatel.getRequest());
				
				
				for (Sample samp : listSample) {
					if(samp.getRequest().getInd_num_doc().getId_ind_num_doc()>1){
					listObectNaIzpitvaneSample.add(samp.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane());
					}
				}

				List<Request_To_ObektNaIzpitvaneRequest> listObectNaIzpit = Request_To_ObektNaIzpitvaneRequestDAO
						.getRequest_To_ObektNaIzpitvaneRequestByRequest(izpitvanPokazatel.getRequest());
				for (Request_To_ObektNaIzpitvaneRequest request_To_ObektNaIzpitvaneRequest : listObectNaIzpit) {
					if(request_To_ObektNaIzpitvaneRequest.getRequest().getInd_num_doc().getId_ind_num_doc()>1){
					listObectNaIzpitvane
							.add(request_To_ObektNaIzpitvaneRequest.getObektNaIzp().getName_obekt_na_izpitvane());
					}
				}
			}
		}
		Set<String> set = new HashSet<>(listObectNaIzpitvane);
		listObectNaIzpitvane.clear();
		listObectNaIzpitvane.addAll(set);

		set = new HashSet<>(listObectNaIzpitvaneSample);
		listObectNaIzpitvaneSample.clear();
		listObectNaIzpitvaneSample.addAll(set);

		List<List<String>> globalListObektNaIzpitvane = new ArrayList<List<String>>();
		globalListObektNaIzpitvane.add(listObectNaIzpitvane);
		globalListObektNaIzpitvane.add(listObectNaIzpitvaneSample);

		return globalListObektNaIzpitvane;
	}

	

	

	private void getIntemsInChoice(Choice choice, Object[] masiveString) {
		choice.add("");
		for (Object element : masiveString) {
			choice.add(element.toString());
		}

	}

	
	private void setItemsInChoiceObectNaIzpit(Choice choiceIzpitProd, Choice choicePokazatel, Choice choiceObectNaIzpit,
			Choice choiceObectNaProbata) {

		choiceObectNaIzpit.removeAll();
		choiceObectNaProbata.removeAll();
		if (!choiceIzpitProd.getSelectedItem().equals("") && !choicePokazatel.getSelectedItem().equals("")) {
			TranscluentWindow round = new TranscluentWindow();

			final Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					List<List<String>> globalListObektNaIzpitvane = getListRequestByIzpitvanProduktAndPokazatel(
							choiceIzpitProd.getSelectedItem(), choicePokazatel.getSelectedItem());
					String[] masive1 = new String[globalListObektNaIzpitvane.get(0).size()];

					int i = 0;
					for (String string : globalListObektNaIzpitvane.get(0)) {
						masive1[i] = string;
						i++;
					}
					String[] masive2 = new String[globalListObektNaIzpitvane.get(1).size()];
					i = 0;
					for (String string : globalListObektNaIzpitvane.get(1)) {
						masive2[i] = string;
						i++;
					}

					getIntemsInChoice(choiceObectNaIzpit, masive1);
					getIntemsInChoice(choiceObectNaProbata, masive2);
					round.StopWindow();

				}
			});
			thread.start();
		}
	}

	
	public void IzpitvanProduktSection() {

	}

}

class Referens {

	
	private String nuclide;
	private Period period;
	private Results result;

	public Referens(String nuclide, Period period, Results result) {
		super();
		this.nuclide = nuclide;
		this.period = period;
		this.result = result;
	}

	public String getNuclide() {
		return nuclide;
	}

	public void setNuclide(String nuclide) {
		this.nuclide = nuclide;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Results getResult() {
		return result;
	}

	public void setResult(Results result) {
		this.result = result;
	}

}
