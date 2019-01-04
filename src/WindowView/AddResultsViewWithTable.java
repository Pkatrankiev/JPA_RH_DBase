
	package WindowView;

	import java.awt.BorderLayout;
	import java.awt.FlowLayout;
	import java.awt.Font;

	import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
	import javax.swing.JDialog;
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
	import java.util.List;
	import java.awt.event.ActionEvent;
	import java.awt.Rectangle;
import java.awt.Window;
import java.awt.Point;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

	public class AddResultsViewWithTable extends JDialog {

		private final JPanel contentPanel = new JPanel();
		private JPanel panelRsltsList;
		private JTextField txtBasicValueResult;
		private JTextField txtRqstCode;
		private JLabel lblNameMetod;
		private Sample sample = null;
		private List_izpitvan_pokazatel pokazatel = null;
		private Choice choicePokazatel;
		private Choice choiceOIR;
		private Choice choiceORHO;
		private Choice choiceMetody;
		private Metody selectedMetod = null;
		private Boolean corectRequestCode = false;
		private static List<DBase_Class.Dimension> listDimension;
		private static List<Razmernosti> listRazmernost;
		private static List<Sample> listSample;
		private static List<String> list_UsersNameFamily;
		private static List<String> list_UsersNameFamilyOIR;
		private static List<String> list_UsersNameFamilyORHO;
		private static List<IzpitvanPokazatel> listPokazatel;
		private static List<Metody> listMetody;
		private Results[] masiveResultsForChoiceSample;
		private static List<Nuclide> listNuclide;
		private Request choiseRequest;

		int newCountResults = 0;
		int countResults = 0;
		int addCount = 0;
		int rowWidth = 20;
		Boolean flagNotReadListPokazatel = true;
		Boolean flagNotReadListMetody = true; 	
		
		private static String[] values_Nuclide;
		private static String[] values_Razmernosti;
		private static String[] values_Dimension;
		private static Object[][] dataTable;
		private static int tbl_Colum = 12;
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
	
		private Font font = new Font("Tahoma", Font.PLAIN, 12);
		private JPanel basic_panel;
		private JScrollPane scrollTablePane;

		public static void main(String[] args) {
			try {
				listDimension = DimensionDAO.getInListAllValueDimension();
				listRazmernost = RazmernostiDAO.getInListAllValueRazmernosti();
				list_UsersNameFamily = UsersDAO.getListStringAllName_FamilyUsers(null);
				list_UsersNameFamilyOIR = UsersDAO.getListStringAllName_FamilyUsers(PostDAO.getValuePostByName("ОИР"));
				list_UsersNameFamilyORHO = UsersDAO.getListStringAllName_FamilyUsers(PostDAO.getValuePostByName("ОРХО"));
//				listNuclide = NuclideDAO.getInListAllValueNuclide();
				listSample = new ArrayList<Sample>();
				listMetody = new ArrayList<Metody>();
				
				values_Nuclide = NuclideDAO.getMasiveStringAllValueNuclide();
				values_Razmernosti = RazmernostiDAO.getMasiveStringAllValueRazmernosti();
				values_Dimension = DimensionDAO.getMasiveStringAllValueDimension();
				
				
				AddResultsViewWithTable dialog = new AddResultsViewWithTable();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		public AddResultsViewWithTable() {

			
			

			setBounds(100, 100, 960, (countResults * rowWidth) + 340);
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
			gbl_basic_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			basic_panel.setLayout(gbl_basic_panel);

			RequestCodeSection(basic_panel);

			SampleCodeSection(basic_panel);

			PokazatelSection(basic_panel);

			ChoiceORHO_Section(basic_panel);

			MetodSection(basic_panel);

			ChoiceOIR_Section(basic_panel);

			CreadTableButton(basic_panel);
			
			BasicValueFileSection(basic_panel);

			DobivSection(basic_panel);

			
			
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
			
			JLabel lblNewLabel = new JLabel(" ");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 8;
			basic_panel.add(lblNewLabel, gbc_lblNewLabel);
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
			Dimension dim = new Dimension(480, 14);
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
			JLabel lblSmplCode = new JLabel("№ на проба");
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

		


		public static void setUp_Nuclide(JTable table, TableColumn Nuclide_Column) {
			JComboBox<?> comboBox = new JComboBox<Object>(values_Nuclide);
			Nuclide_Column.setCellEditor(new DefaultCellEditor(comboBox));
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setToolTipText("Натисни за избор");
			Nuclide_Column.setCellRenderer(renderer);
		}

		public static void setUp_Razmernosti(JTable table, TableColumn Razmernosti_Column) {
			JComboBox<?> comboBox1 = new JComboBox<Object>(values_Razmernosti);
			Razmernosti_Column.setCellEditor(new DefaultCellEditor(comboBox1));
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setToolTipText("Натисни за избор");
			Razmernosti_Column.setCellRenderer(renderer);
		}

		public static void setUp_Dimension(JTable table, TableColumn Dimension_Column) {
			JComboBox<?> comboBox = new JComboBox<Object>(values_Dimension);
			Dimension_Column.setCellEditor(new DefaultCellEditor(comboBox));
			DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
			renderer.setToolTipText("Натисни за избор");
			Dimension_Column.setCellRenderer(renderer);
		}

		private  void CreadTableButton(JPanel panel) {
			JButton btnCreadTable = new JButton("Генериране на Таблица");
			btnCreadTable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(choiceMetody.getSelectedItem()!=null){
									
					List<Nuclide_to_Pokazatel> listNucPok = Nuclide_to_PokazatelDAO.getListNuclide_to_PokazatelFromColumnByVolume("pokazatel", pokazatel);
					
					values_Nuclide = new String[listNucPok.size()];
					List<String> listBasikNulide = new ArrayList<String>();
					int i =0;
					for (Nuclide_to_Pokazatel nuclide_to_Pokazatel : listNucPok) {
						values_Nuclide[i]= nuclide_to_Pokazatel.getNuclide().getSymbol_nuclide();
						if(nuclide_to_Pokazatel.getNuclide().getFavorite_nuclide()){
						listBasikNulide.add(nuclide_to_Pokazatel.getNuclide().getSymbol_nuclide());
						}
						i++;
					}
					selectedMetod = MetodyDAO.getValueList_MetodyByName(choiceMetody.getSelectedItem());
					lblNameMetod.setText(selectedMetod.getName_metody());
					masiveResultsForChoiceSample = creadListChoiseResults(sample);
					countResults = 	masiveResultsForChoiceSample.length;	
					
					if (scrollTablePane!=null){
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
					
					JTable tab = CreateTableResults(masiveResultsForChoiceSample,listBasikNulide);
					
					tab.validate();
					tab.repaint();
					
					
					panelTable.add(tab.getTableHeader(), BorderLayout.NORTH);
					panelTable.add(tab, BorderLayout.CENTER);
					
					panelTable.validate();
					panelTable.repaint();
					
					
					scrollTablePane.validate();
					scrollTablePane.repaint();
						
					panel.validate();
					panel.repaint();
						
				
					setBounds(100, 100, 960, (countResults * rowWidth) + 340);
					validate();
					repaint();
					}
			}
			});
			
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
						gbc_txtBasicValueResult.insets = new Insets(0, 0, 5, 0);
						gbc_txtBasicValueResult.gridwidth = 5;
						gbc_txtBasicValueResult.gridx = 1;
						gbc_txtBasicValueResult.gridy = 5;
						panel.add(txtBasicValueResult, gbc_txtBasicValueResult);
						txtBasicValueResult.setColumns(10);
			GridBagConstraints gbc_btnCreadTable = new GridBagConstraints();
			gbc_btnCreadTable.gridwidth = 2;
			gbc_btnCreadTable.anchor = GridBagConstraints.EAST;
			gbc_btnCreadTable.insets = new Insets(0, 0, 5, 5);
			gbc_btnCreadTable.gridx = 0;
			gbc_btnCreadTable.gridy = 6;
			panel.add(btnCreadTable, gbc_btnCreadTable);
			
			
		}
		
		public JTable CreateTableResults( Results[] masiveResultsForChoiceSample, List<String> listBasikNulide){
			String[] columnNames = getTabHeader();
			@SuppressWarnings("rawtypes")
			Class[] types = getTypes();
			dataTable = getDataTable(masiveResultsForChoiceSample, listBasikNulide);	
			
			
			JTable table = new JTable();// new DefaultTableModel(rowData,
												// columnNames));

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {}

				public void mousePressed(MouseEvent e) {
					System.out.println(table.getSelectedColumn()+"  "+table.getSelectedRow()+"   "+ dataTable.length);
					System.out.println(countResults+"   " +masiveResultsForChoiceSample.length);	
					if (table.getSelectedColumn() == check_Colum ) {
//						**************************
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
							if(column<check_Colum){
							return true;
							}else{
								return false;	
							}
							}
						
						
						
						public void setValueAt(Object value, int row, int col) {
							if (!dataTable[row][col].equals(value)) {
								dataTable[row][col] = value;
								fireTableCellUpdated(row, col);
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
			
					setUp_Nuclide(table, table.getColumnModel().getColumn(nuclide_Colum));
					setUp_Razmernosti(table, table.getColumnModel().getColumn(razm_Colum));
					setUp_Dimension(table, table.getColumnModel().getColumn(dimen_Colum));

					
				}	
			});
			return table;
		}
			
		private static String[] getTabHeader() {
			String[] tableHeader = { "Нуклид", "Активност", "Неопределеност", "МДА",  "Размерност", "Сигма", 
					"Количество", "Мярка", "ДатаХимОбр", "ДатаАнализ", "В протокол", "Проверка" };
			return tableHeader;
		}

		@SuppressWarnings("rawtypes")
		private static Class[] getTypes() {

			Class[] types = { String.class, String.class, String.class, String.class, String.class, Integer.class, String.class, String.class, String.class,
					String.class, Boolean.class, String.class };

			return types;
		}
		
		private static Object[][] getDataTable(Results[] masiveResultsForChoiceSample,  List<String> listBasikNulide) {
			Object[][] tableResult = new Object[masiveResultsForChoiceSample.length+1][tbl_Colum];
			Object[] rowFromTableResult = new Object[tbl_Colum];
			int k=0;
			Boolean flag;
			for (String basikNulide : listBasikNulide) {
				flag = false;
			for (int i = 0; i < masiveResultsForChoiceSample.length; i++) {
				Results results =	masiveResultsForChoiceSample[i];
				if(basikNulide.equals(results.getNuclide().getSymbol_nuclide())){
					flag = true;
			rowWithValueResults(results, tableResult, i);
				}
			k++;
			}
			if(flag){
				tableResult[k] = rowWithoutValueResults(k);	
			}
			k++;
			}
			

			return tableResult;
		}


		private static Object[] rowWithoutValueResults( int k) {
			Object[] rowFromTableResult = new Object[tbl_Colum];
			rowFromTableResult[nuclide_Colum] = values_Nuclide[0];
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
			rowFromTableResult[in_Prot_Colum] = true;
			rowFromTableResult[check_Colum] = "Провери";
			return rowFromTableResult;
		}


		private static void rowWithValueResults(Results results, Object[][] tableResult, int i) {
			try {
			tableResult[i][nuclide_Colum] = results.getNuclide().getSymbol_nuclide();
			tableResult[i][actv_value_Colum] = results.getValue_result();
			tableResult[i][uncrt_Colum] = results.getUncertainty();
			tableResult[i][mda_Colum] = results.getMda();
			tableResult[i][razm_Colum] = results.getRtazmernosti().getName_razmernosti();
			tableResult[i][sigma_Colum] = results.getSigma();
			tableResult[i][qunt_Colum] = results.getQuantity();
			tableResult[i][dimen_Colum] = "";
			if (results.getDimension() != null) {
				tableResult[i][dimen_Colum] = results.getDimension().getName_dimension();
			}
			tableResult[i][dateHimObr_Colum] = results.getDate_chim_oper();
			tableResult[i][dateAnaliz_Colum] = results.getDate_measur();
			tableResult[i][in_Prot_Colum] = results.getInProtokol();
			tableResult[i][check_Colum] = "Провери";
		} catch (NullPointerException e) {
			JOptionPane.showInputDialog("Грешни данни за резултат:"+results.getId_results(), JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showInputDialog("Грешни данни за резултат:", JOptionPane.ERROR_MESSAGE);
		}
		
		}
		
	}

