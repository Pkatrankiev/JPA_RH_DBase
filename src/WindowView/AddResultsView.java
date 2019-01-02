package WindowView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Aplication.AplicantDAO;
import Aplication.DimensionDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.Metody_to_PokazatelDAO;
import Aplication.NuclideDAO;
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
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;

import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import java.awt.Panel;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Point;

public class AddResultsView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panelRsltsList;
	private JTextField txtBasicValueResult;
	private JTextField txtRqstCode;
	private Sample sample = null;
	private List_izpitvan_pokazatel pokazatel = null;
	private Choice choicePokazatel;
	private Choice choiceOIR;
	private Choice choiceORHO;
	private Metody selectedMetod = null;
	private Boolean corectRequestCode = false;
	private List<DBase_Class.Dimension> listDimension;
	private List<Razmernosti> listRazmernost;
	private List<Sample> listSample;
	private List<String> list_UsersNameFamily;
	private List<String> list_UsersNameFamilyOIR;
	private List<String> list_UsersNameFamilyORHO;
	private List<IzpitvanPokazatel> listPokazatel;
	private List<Metody> listMetody;
	private Results[] choiceResults;
	private List<Nuclide> listNuclide;
	private Request choiseRequest;

	int newCountResults = 0;
	int countResults = 0;
	int addCount = 0;
	int rowWidth = 40;
	Boolean flagNotReadListPokazatel = true;
	Boolean flagNotReadListMetody = true; 	
	
	private JPanel[] panelRsltsValues;
	private JTextField[] txtValueResult;
	private JTextField[] txtUncertainty;
	private JTextField[] txtMDA;
	private JTextField[] txtSigma;
	private JTextField[] txtQuantity;
	private JTextField[] txtDateChimOper;
	private JTextField[] txtDateMeasur;
	private JCheckBox[] chckbxInProtokol;
	private JButton[] btnCheckResult;
	private Choice[] choiceNcldName;
	private Choice[] choiceDimention;
	private Choice[] choicÂRazmernosti;
	private Font font = new Font("Tahoma", Font.PLAIN, 12);
	private JPanel panel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddResultsView dialog = new AddResultsView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public AddResultsView() {

		
		listDimension = DimensionDAO.getInListAllValueDimension();
		listRazmernost = RazmernostiDAO.getInListAllValueRazmernosti();
		list_UsersNameFamily = UsersDAO.getListStringAllName_FamilyUsers(null);
		list_UsersNameFamilyOIR = UsersDAO.getListStringAllName_FamilyUsers(PostDAO.getValuePostByName("Œ»–"));
		list_UsersNameFamilyORHO = UsersDAO.getListStringAllName_FamilyUsers(PostDAO.getValuePostByName("Œ–’Œ"));
		listNuclide = NuclideDAO.getInListAllValueNuclide();
		listSample = new ArrayList<Sample>();
		listMetody = new ArrayList<Metody>();
		

		setBounds(100, 100, 910, (countResults * rowWidth) + 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);

		panel_2 = new JPanel();
		panel_2.setBounds(new Rectangle(5, 0, 0, 0));
		scrollPane.setViewportView(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 61, 111, 122, 48, 200, 178, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		RequestCodeSection(panel_2);

		SampleCodeSection(panel_2);

		PokazatelSection(panel_2);

		ChoiceORHO_Section(panel_2);

		MetodSection(panel_2);

		ChoiceOIR_Section(panel_2);

//		PanelResultListSection(panel_2);

		BasicValueFileSection(panel_2);

		DobivSection(panel_2);

		ButtonPanell();

	}


	private Results[] creadListChoiseResults(Sample sample) {
		List<Results> ListResultsFromSample = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
			List<Results> choiceResults = new ArrayList<Results>();
			for (Results result : ListResultsFromSample) {
				if (result.getPokazatel().getId_pokazatel()==pokazatel.getId_pokazatel()&& result.getMetody().getId_metody()==selectedMetod.getId_metody()) {
					choiceResults.add(result);
				}
			}
			Results[] masiveResults = new Results[choiceResults.size()];
			int i=0;
			for (Results results : choiceResults) {
				masiveResults[i] = results;
				i++;
			} 
		return masiveResults;
	}

	
	
	private void ButtonPanell() {
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
	}

	private void DobivSection(JPanel panel) {
		JLabel lblDobiv = new JLabel("ƒÓ·Ë‚");
		GridBagConstraints gbc_lblDobiv = new GridBagConstraints();
		gbc_lblDobiv.anchor = GridBagConstraints.EAST;
		gbc_lblDobiv.insets = new Insets(0, 0, 0, 5);
		gbc_lblDobiv.gridx = 0;
		gbc_lblDobiv.gridy = 8;
		panel.add(lblDobiv, gbc_lblDobiv);

		Choice choiceDobiv = new Choice();
		GridBagConstraints gbc_choiceDobiv = new GridBagConstraints();
		gbc_choiceDobiv.fill = GridBagConstraints.HORIZONTAL;
		gbc_choiceDobiv.gridwidth = 2;
		gbc_choiceDobiv.insets = new Insets(0, 0, 0, 5);
		gbc_choiceDobiv.gridx = 1;
		gbc_choiceDobiv.gridy = 8;
		panel.add(choiceDobiv, gbc_choiceDobiv);
	}

	private void BasicValueFileSection(JPanel panel) {
		JLabel lblBasicValueRsltsFile = new JLabel("œ˙Ú ‰Ó Ù‡ÈÎ‡");
		GridBagConstraints gbc_lblBasicValueRsltsFile = new GridBagConstraints();
		gbc_lblBasicValueRsltsFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblBasicValueRsltsFile.anchor = GridBagConstraints.EAST;
		gbc_lblBasicValueRsltsFile.gridx = 0;
		gbc_lblBasicValueRsltsFile.gridy = 7;
		panel.add(lblBasicValueRsltsFile, gbc_lblBasicValueRsltsFile);

		txtBasicValueResult = new JTextField();
		GridBagConstraints gbc_txtBasicValueResult = new GridBagConstraints();
		gbc_txtBasicValueResult.anchor = GridBagConstraints.WEST;
		gbc_txtBasicValueResult.insets = new Insets(0, 0, 5, 0);
		gbc_txtBasicValueResult.gridwidth = 5;
		gbc_txtBasicValueResult.gridx = 1;
		gbc_txtBasicValueResult.gridy = 7;
		panel.add(txtBasicValueResult, gbc_txtBasicValueResult);
		txtBasicValueResult.setColumns(10);
	}

	private void PanelResultListSection(JPanel panel) {
				
		JPanel  panelRsltsList = new JPanel();
		panelRsltsList.removeAll();
		GridBagConstraints gbc_panelRsltsList = new GridBagConstraints();
		gbc_panelRsltsList.gridwidth = 6;
		gbc_panelRsltsList.insets = new Insets(0, 0, 5, 0);
		gbc_panelRsltsList.fill = GridBagConstraints.BOTH;
		gbc_panelRsltsList.gridx = 0;
		gbc_panelRsltsList.gridy = 5;
		panel.add(panelRsltsList, gbc_panelRsltsList);
		panelRsltsList.setLayout(new BoxLayout(panelRsltsList, BoxLayout.Y_AXIS));

		Panel panelLabellResults = new Panel();
		panelRsltsList.add(panelLabellResults);
		panelLabellResults.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		PanelLabelResultsList(panelLabellResults);

		panelRsltsValues = new JPanel[countResults + 6];
		choiceNcldName = new Choice[countResults + 6];
		txtValueResult = new JTextField[countResults + 6];
		txtUncertainty = new JTextField[countResults + 6];
		txtMDA = new JTextField[countResults + 6];
		choicÂRazmernosti = new Choice[countResults + 6];
		txtSigma = new JTextField[countResults + 6];
		txtQuantity = new JTextField[countResults + 6];
		choiceDimention = new Choice[countResults + 6];
		txtDateChimOper = new JTextField[countResults + 6];
		txtDateMeasur = new JTextField[countResults + 6];
		chckbxInProtokol = new JCheckBox[countResults + 6];
		btnCheckResult = new JButton[countResults + 6];

		for (int i = 0; i < countResults; i++) {
			
			RowInListResults(panelRsltsList, i);
		}

		JButton btnAddNuclide = new JButton("ƒÓ·‡‚ˇÌÂ ÌÛÍÎË‰");
		btnAddNuclide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCount = addCount + 1;
				newCountResults = countResults + addCount;
				RowInListResults(panelRsltsList, newCountResults);
				if (newCountResults == countResults + 5) {
					btnAddNuclide.setVisible(false);
				}
				System.out.println(newCountResults);
				setBounds(100, 100, 910, (newCountResults * rowWidth) + 320);
				validate();
				repaint();
			}
		});
		btnAddNuclide.setMargin(new Insets(2, 5, 2, 5));
		btnAddNuclide.setPreferredSize(new Dimension(123, 20));
		GridBagConstraints gbc_btnAddNuclide = new GridBagConstraints();
		gbc_btnAddNuclide.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddNuclide.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddNuclide.gridx = 5;
		gbc_btnAddNuclide.gridy = 6;
		panel.add(btnAddNuclide, gbc_btnAddNuclide);
	}

	private void ChoiceOIR_Section(JPanel panel) {
		JLabel lblNewLabel_2 = new JLabel("»Á‚˙¯ËÎ ‡Ì‡ÎËÁ‡");
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
		
		JLabel lblNameMetod = new JLabel();
		Dimension dim = new Dimension(480, 14);
		lblNameMetod.setPreferredSize(dim);
		lblNameMetod.setMinimumSize(dim);
		lblNameMetod.setMaximumSize(dim);
		lblNameMetod.setHorizontalAlignment(SwingConstants.LEFT);
		
		GridBagConstraints gbc_lblNameMetod = new GridBagConstraints();
		gbc_lblNameMetod.anchor = GridBagConstraints.WEST;
		gbc_lblNameMetod.gridwidth = 3;
		gbc_lblNameMetod.insets = new Insets(0, 0, 5, 5);
		gbc_lblNameMetod.gridx = 3;
		gbc_lblNameMetod.gridy = 4;
		panel_2.add(lblNameMetod, gbc_lblNameMetod);
				
		JLabel lblMetody = new JLabel("ÃÂÚÓ‰");
		GridBagConstraints gbc_lblMetody = new GridBagConstraints();
		gbc_lblMetody.insets = new Insets(0, 0, 5, 5);
		gbc_lblMetody.gridx = 0;
		gbc_lblMetody.gridy = 4;
		panel.add(lblMetody, gbc_lblMetody);

		Choice choiceMetody = new Choice();
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

					if (choicePokazatel.getSelectedItem()!= null) {
						if(flagNotReadListMetody){
						
						pokazatel = List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(choicePokazatel.getSelectedItem());
						List<Metody_to_Pokazatel> list = Metody_to_PokazatelDAO.getListMetody_to_PokazatelFromColumnByVolume("pokazatel", pokazatel);			
						for (Metody_to_Pokazatel metody_to_Pokazatel : list) {
							listMetody.add(metody_to_Pokazatel.getMetody());
						}
						for (Metody metod : listMetody) {
							choiceMetody.add(metod.getCode_metody());
							flagNotReadListMetody = false;
						}
						}
						selectedMetod = MetodyDAO.getValueList_MetodyByName(choiceMetody.getSelectedItem());
						lblNameMetod.setText(selectedMetod.getName_metody());
						choiceResults = creadListChoiseResults(sample);
						countResults = choiceResults.length;
//						newCountResults = countResults;
						
						PanelResultListSection(panel_2);
//						setBounds(100, 100, 910, (countResults * rowWidth) + 320);
//						validate();
//						repaint();
					}
				}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

			}

		});
		
	}

	private void ChoiceORHO_Section(JPanel panel) {
		JLabel lblNewLabel_1 = new JLabel("»Á‚˙¯ËÎ ’ËÏ. Ó·‡·ÓÚ.");
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

	private void PokazatelSection(JPanel panel) {

		JLabel lblPokazatel = new JLabel("œÓÍ‡Á‡ÚÂÎ");
		GridBagConstraints gbc_lblPokazatel = new GridBagConstraints();
		gbc_lblPokazatel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPokazatel.gridx = 0;
		gbc_lblPokazatel.gridy = 3;
		panel.add(lblPokazatel, gbc_lblPokazatel);

		choicePokazatel = new Choice();
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
					if (sample != null) {
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
		JLabel lblSmplCode = new JLabel("π Ì‡ ÔÓ·‡");
		GridBagConstraints gbc_lblSmplCode = new GridBagConstraints();
		gbc_lblSmplCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblSmplCode.gridx = 2;
		gbc_lblSmplCode.gridy = 0;
		panel.add(lblSmplCode, gbc_lblSmplCode);

		Choice choiceSmplCode = new Choice();
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
				for (Sample samp : listSample) {
					if (samp.getSample_code().equals(choiceSmplCode.getSelectedItem())) {
						sample = samp;
					}
				}
				choiceSmplCode.getSelectedItem();
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
		panel_2.add(lblError, gbc_lblError);
		lblError.setVisible(false);

		JLabel lblRqstCode = new JLabel(" Ó‰ Ì‡ Á‡ˇ‚Í‡");
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
					lblError.setText("«‡ˇ‚Í‡ Ò ÚÓÁË ÌÓÏÂ ÌÂ Ò˙˘ÂÒÚ‚Û‚‡");
					validate();
					repaint();
					corectRequestCode = false;
				} else {
					txtRqstCode.setForeground(Color.BLACK);
					txtRqstCode.setBorder(new LineBorder(Color.BLACK));
					choiseRequest = RequestDAO.getRequestFromColumnByVolume("recuest_code", txtRqstCode.getText());
					lblError.setVisible(false);
					listPokazatel = IzpitvanPokazatelDAO.getValueIzpitvan_pokazatelByRequest(choiseRequest);
					txtRqstCode.setEditable(false);
					validate();
					repaint();
					corectRequestCode = true;

				}
			}

			@Override
			public void keyPressed(KeyEvent event) {

			}
		});

	}

	
	private void RowInListResults(JPanel panelRsltsList, int i) {
		panelRsltsValues[i] = new JPanel();
		panelRsltsList.add(panelRsltsValues[i]);
		panelRsltsValues[i].setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		{
			choiceNcldName[i] = new Choice();
			choiceNcldName[i].setFont(font);
			choiceNcldName[i].setPreferredSize(new Dimension(85, 22));
			for (Nuclide nuclid : listNuclide) {
				choiceNcldName[i].addItem(nuclid.getSymbol_nuclide());
			}
			panelRsltsValues[i].add(choiceNcldName[i]);
			if(countResults>0&&countResults>i){
				choiceNcldName[i].select(choiceResults[i].getNuclide().getSymbol_nuclide());
			}
			
		}
		{
			txtValueResult[i] = new JTextField();
			txtValueResult[i].setFont(font);
			txtValueResult[i].setPreferredSize(new Dimension(5, 22));
			txtValueResult[i].setColumns(6);
			txtValueResult[i].setHorizontalAlignment(JLabel.CENTER);
			txtValueResult[i].setBorder(new LineBorder(new Color(0, 0, 0)));
			panelRsltsValues[i].add(txtValueResult[i]);
			if(countResults>0&&countResults>i){
				txtValueResult[i].setText(choiceResults[i].getValue_result()+"");
			}else{txtValueResult[i].setText("");}
		}
		{
			txtUncertainty[i] = new JTextField();
			txtUncertainty[i].setFont(font);
			txtUncertainty[i].setPreferredSize(new Dimension(5, 22));
			txtUncertainty[i].setColumns(6);
			txtUncertainty[i].setHorizontalAlignment(JLabel.CENTER);
			txtUncertainty[i].setBorder(new LineBorder(new Color(0, 0, 0)));
			panelRsltsValues[i].add(txtUncertainty[i]);
			if(countResults>0&&countResults>i){
				txtUncertainty[i].setText(choiceResults[i].getUncertainty()+"");
			}else{txtUncertainty[i].setText("");}
		}
		{
			txtMDA[i] = new JTextField();
			txtMDA[i].setFont(font);
			txtMDA[i].setPreferredSize(new Dimension(5,22));
			txtMDA[i].setColumns(6);
			txtMDA[i].setHorizontalAlignment(JLabel.CENTER);
			txtMDA[i].setBorder(new LineBorder(new Color(0, 0, 0)));
			panelRsltsValues[i].add(txtMDA[i]);
			if(countResults>0&&countResults>i){
				txtMDA[i].setText(choiceResults[i].getMda()+"");
			}else{txtMDA[i].setText("");}
		}
		{
			choicÂRazmernosti[i] = new Choice();
			choicÂRazmernosti[i].setFont(font);
			choicÂRazmernosti[i].setPreferredSize(new Dimension(65, 22));
			panelRsltsValues[i].add(choicÂRazmernosti[i]);
			for (Razmernosti razm : listRazmernost) {
				choicÂRazmernosti[i].addItem(razm.getName_razmernosti());
			}
			if(countResults>0&&countResults>i){
				choicÂRazmernosti[i].select(choiceResults[i].getRtazmernosti().getName_razmernosti());
			}
		}
		{
			txtSigma[i] = new JTextField();
			txtSigma[i].setFont(font);
			txtSigma[i].setPreferredSize(new Dimension(5, 22));
			txtSigma[i].setColumns(4);
			txtSigma[i].setHorizontalAlignment(JLabel.CENTER);
			txtSigma[i].setBorder(new LineBorder(new Color(0, 0, 0)));
			panelRsltsValues[i].add(txtSigma[i]);
			if(countResults>0&&countResults>i){
				txtSigma[i].setText(choiceResults[i].getSigma()+"");
			}else{txtSigma[i].setText("");}
		}
		{
			txtQuantity[i] = new JTextField();
			txtQuantity[i].setFont(font);
			txtQuantity[i].setPreferredSize(new Dimension(5, 22));
			txtQuantity[i].setColumns(6);
			txtQuantity[i].setHorizontalAlignment(JLabel.CENTER);
			txtQuantity[i].setBorder(new LineBorder(new Color(0, 0, 0)));
			panelRsltsValues[i].add(txtQuantity[i]);
			if(countResults>0&&countResults>i){
				txtQuantity[i].setText(choiceResults[i].getQuantity()+"");
			}else{txtQuantity[i].setText("");}
		}
		{
			choiceDimention[i] = new Choice();
			choiceDimention[i].setFont(font);
			for (DBase_Class.Dimension dim : listDimension) {
				choiceDimention[i].addItem(dim.getName_dimension());
			}
			panelRsltsValues[i].add(choiceDimention[i]);
			if(countResults>0&&countResults>i){
				String str="";
				if(choiceResults[i].getDimension()!=null){
					str = choiceResults[i].getDimension().getName_dimension();
				}
				choiceDimention[i].select(str);
			}
		}
		{
			txtDateChimOper[i] = new JTextField();
			txtDateChimOper[i].setFont(font);
			txtDateChimOper[i].setPreferredSize(new Dimension(5, 22));
			txtDateChimOper[i].setColumns(8);
			txtDateChimOper[i].setHorizontalAlignment(JLabel.CENTER);
			txtDateChimOper[i].setBorder(new LineBorder(new Color(0, 0, 0)));
			panelRsltsValues[i].add(txtDateChimOper[i]);
			if(countResults>0&&countResults>i){
				txtDateChimOper[i].setText(choiceResults[i].getDate_chim_oper()+"");
			}else{txtDateChimOper[i].setText("");}
		}
		{
			txtDateMeasur[i] = new JTextField();
			txtDateMeasur[i].setFont(font);
			txtDateMeasur[i].setPreferredSize(new Dimension(5, 22));
			txtDateMeasur[i].setColumns(8);
			txtDateMeasur[i].setHorizontalAlignment(JLabel.CENTER);
			txtDateMeasur[i].setBorder(new LineBorder(new Color(0, 0, 0)));
			panelRsltsValues[i].add(txtDateMeasur[i]);
			if(countResults>0&&countResults>i){
				txtDateMeasur[i].setText(choiceResults[i].getDate_measur()+"");
			}else{txtDateMeasur[i].setText("");}
		}
		{
			chckbxInProtokol[i] = new JCheckBox("");
			chckbxInProtokol[i].setHorizontalAlignment(SwingConstants.CENTER);
			chckbxInProtokol[i].setAlignmentX(Component.CENTER_ALIGNMENT);
			chckbxInProtokol[i].setPreferredSize(new Dimension(75, 20));
			panelRsltsValues[i].add(chckbxInProtokol[i]);
			if(countResults>0&&countResults>i){
				chckbxInProtokol[i].setSelected(choiceResults[i].getInProtokol());
			}
		}
		{
			btnCheckResult[i] = new JButton("œÓ‚ÂÍ‡");
			btnCheckResult[i].setMargin(new Insets(2, 5, 2, 5));
			btnCheckResult[i].setFont(font);
			btnCheckResult[i].setPreferredSize(new Dimension(75, 22));
			btnCheckResult[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			panelRsltsValues[i].add(btnCheckResult[i]);
		}
	}

	
	private void PanelLabelResultsList(Panel panel_1) {
		JLabel lblNameNuclide = new JLabel("ÕÛÍÎË‰");
		lblNameNuclide.setPreferredSize(new Dimension(85, 20));
		lblNameNuclide.setHorizontalAlignment(JLabel.CENTER);
		lblNameNuclide.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblNameNuclide);

		JLabel lblValue = new JLabel("—ÚÓÈÌÓÒÚ");
		lblValue.setPreferredSize(new Dimension(62, 20));
		lblValue.setHorizontalAlignment(JLabel.CENTER);
		lblValue.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblValue);

		{
			JLabel lblUncertainty = new JLabel("ÕÂÓÔÂ‰.");
			lblUncertainty.setPreferredSize(new Dimension(62, 20));
			lblUncertainty.setHorizontalAlignment(JLabel.CENTER);
			lblUncertainty.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.add(lblUncertainty);
		}
		{
			JLabel lblMDA = new JLabel("Ãƒ¿");
			lblMDA.setPreferredSize(new Dimension(62, 20));
			lblMDA.setHorizontalAlignment(JLabel.CENTER);
			lblMDA.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.add(lblMDA);
		}
		{
			JLabel lblRazmernosti = new JLabel("–‡ÁÏ.");
			lblRazmernosti.setPreferredSize(new Dimension(65, 20));
			lblRazmernosti.setHorizontalAlignment(JLabel.CENTER);
			lblRazmernosti.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.add(lblRazmernosti);
		}
		{
			JLabel lblSigma = new JLabel("—Ë„Ï‡");
			lblSigma.setPreferredSize(new Dimension(42, 20));
			lblSigma.setHorizontalAlignment(JLabel.CENTER);
			lblSigma.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.add(lblSigma);
		}
		{
			JLabel lblQuantity = new JLabel(" ÓÎË˜.");
			lblQuantity.setPreferredSize(new Dimension(62, 20));
			lblQuantity.setHorizontalAlignment(JLabel.CENTER);
			lblQuantity.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.add(lblQuantity);
		}
		{
			JLabel lblDimention = new JLabel("–‡ÁÏ.");
			lblDimention.setPreferredSize(new Dimension(50, 20));
			lblDimention.setHorizontalAlignment(JLabel.CENTER);
			lblDimention.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.add(lblDimention);
		}
		{
			JLabel lblDateChimOper = new JLabel("ƒ‡Ú‡’ËÏŒ·");
			lblDateChimOper.setPreferredSize(new Dimension(82, 20));
			lblDateChimOper.setHorizontalAlignment(JLabel.CENTER);
			lblDateChimOper.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.add(lblDateChimOper);
		}
		{
			JLabel lblDateMesur = new JLabel("ƒ‡Ú‡¿Ì‡ÎËÁ");
			lblDateMesur.setPreferredSize(new Dimension(82, 20));
			lblDateMesur.setHorizontalAlignment(JLabel.CENTER);
			lblDateMesur.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.add(lblDateMesur);
		}
		{
			JLabel lblInProtokol = new JLabel("¬ œÓÚÓÍÓÎ");
			lblInProtokol.setPreferredSize(new Dimension(75, 20));
			lblInProtokol.setHorizontalAlignment(JLabel.CENTER);
			lblInProtokol.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.add(lblInProtokol);
		}
		{
			JLabel lblCheckResult = new JLabel("œÓ‚ÂÍ‡");
			lblCheckResult.setPreferredSize(new Dimension(75, 20));
			lblCheckResult.setHorizontalAlignment(JLabel.CENTER);
			lblCheckResult.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.add(lblCheckResult);
		}
	}

}
