package WindowView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Point;

public class AddResultsView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtBasicValueResult;
	private JTextField txtRqstCode;
	
	
	private JPanel[] panelRsltsValues;
	private JLabel[] lblNcldName;
	private JTextField[] txtValueResult;
	private JTextField[] txtUncertainty;
	private JTextField[] txtMDA;
	private JTextField[] txtSigma;
	private JTextField[] txtQuantity;
	private JTextField[] txtDateChimOper;
	private JTextField[] txtDateMeasur;
	private JCheckBox[] chckbxInProtokol;
	private JButton[] btnCheckResult;
	private Choice[] choiceDimention;
	private Choice[] choicеRazmernosti;
	private Font font = new  Font("Tahoma", Font.PLAIN, 12);

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

	/**
	 * Create the dialog.
	 */
	public AddResultsView() {
		int countSample = 4;
		setBounds(100, 100, 840, (countSample * 39) +320);
				getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		
		
		
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				JPanel panel = new JPanel();
				panel.setBounds(new Rectangle(5, 0, 0, 0));
				scrollPane.setViewportView(panel);
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[]{61, 111, 122, 48, 200, 178, 0};
				gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
				gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				panel.setLayout(gbl_panel);
				{
					JLabel lblRqstCode = new JLabel("Код на заявка");
					GridBagConstraints gbc_lblRqstCode = new GridBagConstraints();
					gbc_lblRqstCode.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblRqstCode.insets = new Insets(0, 0, 5, 5);
					gbc_lblRqstCode.gridx = 1;
					gbc_lblRqstCode.gridy = 0;
					panel.add(lblRqstCode, gbc_lblRqstCode);
				}
				{
					JLabel lblSmplCode = new JLabel("№ на проба");
					GridBagConstraints gbc_lblSmplCode = new GridBagConstraints();
					gbc_lblSmplCode.insets = new Insets(0, 0, 5, 5);
					gbc_lblSmplCode.gridx = 2;
					gbc_lblSmplCode.gridy = 0;
					panel.add(lblSmplCode, gbc_lblSmplCode);
				}
				{
					txtRqstCode = new JTextField();
					GridBagConstraints gbc_txtRqstCode = new GridBagConstraints();
					gbc_txtRqstCode.insets = new Insets(0, 0, 5, 5);
					gbc_txtRqstCode.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtRqstCode.gridx = 1;
					gbc_txtRqstCode.gridy = 1;
					panel.add(txtRqstCode, gbc_txtRqstCode);
					txtRqstCode.setColumns(10);
				}
				{
					Choice choiceSmplCode = new Choice();
					GridBagConstraints gbc_choiceSmplCode = new GridBagConstraints();
					gbc_choiceSmplCode.fill = GridBagConstraints.HORIZONTAL;
					gbc_choiceSmplCode.insets = new Insets(0, 0, 5, 5);
					gbc_choiceSmplCode.gridx = 2;
					gbc_choiceSmplCode.gridy = 1;
					panel.add(choiceSmplCode, gbc_choiceSmplCode);
				}
				{
					JLabel lblPokazatel = new JLabel("Показател");
					GridBagConstraints gbc_lblPokazatel = new GridBagConstraints();
					gbc_lblPokazatel.insets = new Insets(0, 0, 5, 5);
					gbc_lblPokazatel.gridx = 0;
					gbc_lblPokazatel.gridy = 2;
					panel.add(lblPokazatel, gbc_lblPokazatel);
				}
				{
					Choice choicePokazatel = new Choice();
					GridBagConstraints gbc_choicePokazatel = new GridBagConstraints();
					gbc_choicePokazatel.fill = GridBagConstraints.HORIZONTAL;
					gbc_choicePokazatel.gridwidth = 2;
					gbc_choicePokazatel.insets = new Insets(0, 0, 5, 5);
					gbc_choicePokazatel.gridx = 1;
					gbc_choicePokazatel.gridy = 2;
					panel.add(choicePokazatel, gbc_choicePokazatel);
				}
				{
					JLabel lblNewLabel_1 = new JLabel("Извършил Хим. обработ.");
					GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
					gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
					gbc_lblNewLabel_1.gridx = 4;
					gbc_lblNewLabel_1.gridy = 2;
					panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
				}
				{
					Choice choiceOHR = new Choice();
					GridBagConstraints gbc_choiceOHR = new GridBagConstraints();
					gbc_choiceOHR.fill = GridBagConstraints.HORIZONTAL;
					gbc_choiceOHR.insets = new Insets(0, 0, 5, 0);
					gbc_choiceOHR.gridx = 5;
					gbc_choiceOHR.gridy = 2;
					panel.add(choiceOHR, gbc_choiceOHR);
				}
				{
					JLabel lblMetody = new JLabel("Метод");
					GridBagConstraints gbc_lblMetody = new GridBagConstraints();
					gbc_lblMetody.insets = new Insets(0, 0, 5, 5);
					gbc_lblMetody.gridx = 0;
					gbc_lblMetody.gridy = 3;
					panel.add(lblMetody, gbc_lblMetody);
				}
				{
					Choice choiceMetody = new Choice();
					GridBagConstraints gbc_choiceMetody = new GridBagConstraints();
					gbc_choiceMetody.fill = GridBagConstraints.HORIZONTAL;
					gbc_choiceMetody.gridwidth = 2;
					gbc_choiceMetody.insets = new Insets(0, 0, 5, 5);
					gbc_choiceMetody.gridx = 1;
					gbc_choiceMetody.gridy = 3;
					panel.add(choiceMetody, gbc_choiceMetody);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Извършил анализа");
					GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
					gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
					gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_2.gridx = 4;
					gbc_lblNewLabel_2.gridy = 3;
					panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
				}
				{
					Choice choiceOIR = new Choice();
					GridBagConstraints gbc_choiceOIR = new GridBagConstraints();
					gbc_choiceOIR.fill = GridBagConstraints.HORIZONTAL;
					gbc_choiceOIR.insets = new Insets(0, 0, 5, 0);
					gbc_choiceOIR.gridx = 5;
					gbc_choiceOIR.gridy = 3;
					panel.add(choiceOIR, gbc_choiceOIR);
				}
				{
					JPanel panelRsltsList = new JPanel();
					GridBagConstraints gbc_panelRsltsList = new GridBagConstraints();
					gbc_panelRsltsList.gridwidth = 6;
					gbc_panelRsltsList.insets = new Insets(0, 0, 5, 0);
					gbc_panelRsltsList.fill = GridBagConstraints.BOTH;
					gbc_panelRsltsList.gridx = 0;
					gbc_panelRsltsList.gridy = 4;
					panel.add(panelRsltsList, gbc_panelRsltsList);
					panelRsltsList.setLayout(new BoxLayout(panelRsltsList, BoxLayout.Y_AXIS));
					{
						Panel panelLabellResults = new Panel();
						panelRsltsList.add(panelLabellResults);
						panelLabellResults.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
						
							PanelLabelResultsList(panelLabellResults);
					}
					
					panelRsltsValues = new JPanel[countSample];
					lblNcldName = new JLabel[countSample];
					txtValueResult = new JTextField[countSample];
					txtUncertainty = new JTextField[countSample];
					txtMDA = new JTextField[countSample];
					choicеRazmernosti = new Choice[countSample];
					txtSigma = new JTextField[countSample];
					txtQuantity = new JTextField[countSample];
					choiceDimention = new Choice[countSample];
					txtDateChimOper = new JTextField[countSample];
					txtDateMeasur = new JTextField[countSample];
					chckbxInProtokol = new JCheckBox[countSample];
					btnCheckResult = new JButton[countSample];
					
					
					{
						for (int i = 0; i < countSample; i++) {

							final int selection = i;
						panelRsltsValues[i]  = new JPanel();
						panelRsltsList.add(panelRsltsValues[i]);
						panelRsltsValues[i].setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
						{
							lblNcldName[i] = new JLabel("60Co");
							lblNcldName[i].setFont(font);
							lblNcldName[i] .setPreferredSize(new Dimension(42, 15));
							lblNcldName[i] .setHorizontalAlignment(JLabel.CENTER);
							lblNcldName[i] .setBorder(new LineBorder(new Color(0, 0, 0)));
							panelRsltsValues[i].add(lblNcldName[i] );
						}
						{
							txtValueResult[i] = new JTextField();
							txtValueResult[i].setFont(font);
							txtValueResult[i].setSize(new Dimension(5, 15));
							txtValueResult[i].setColumns(6);
							txtValueResult[i].setHorizontalAlignment(JLabel.CENTER);
							txtValueResult[i].setBorder(new LineBorder(new Color(0, 0, 0)));
							panelRsltsValues[i].add(txtValueResult[i]);
							
						}
						{
							txtUncertainty[i] = new JTextField();
							txtUncertainty[i].setFont(font);
							txtUncertainty[i].setSize(new Dimension(5, 15));
							txtUncertainty[i].setColumns(6);
							txtUncertainty[i].setHorizontalAlignment(JLabel.CENTER);
							txtUncertainty[i].setBorder(new LineBorder(new Color(0, 0, 0)));
							panelRsltsValues[i].add(txtUncertainty[i]);
							
						}
						{
							txtMDA[i] = new JTextField();
							txtMDA[i].setFont(font);
							txtMDA[i].setSize(new Dimension(6, 15));
							txtMDA[i].setColumns(6);
							txtMDA[i].setHorizontalAlignment(JLabel.CENTER);
							txtMDA[i].setBorder(new LineBorder(new Color(0, 0, 0)));
							panelRsltsValues[i].add(txtMDA[i]);
							
						}
						{
							choicеRazmernosti[i] = new Choice();
							lblNcldName[i].setFont(font);
							choicеRazmernosti[i].setPreferredSize(new Dimension(50, 20));
							panelRsltsValues[i].add(choicеRazmernosti[i]);
						}
						{
							txtSigma[i] = new JTextField();
							txtSigma[i].setFont(font);
							txtSigma[i].setSize(new Dimension(6, 15));
							txtSigma[i].setColumns(4);
							txtSigma[i].setHorizontalAlignment(JLabel.CENTER);
							txtSigma[i].setBorder(new LineBorder(new Color(0, 0, 0)));
							panelRsltsValues[i].add(txtSigma[i]);
							
						}
						{
							txtQuantity[i] = new JTextField();
							txtQuantity[i].setFont(font);
							txtQuantity[i].setSize(new Dimension(6, 15));
							txtQuantity[i].setColumns(6);
							txtQuantity[i].setHorizontalAlignment(JLabel.CENTER);
							txtQuantity[i].setBorder(new LineBorder(new Color(0, 0, 0)));
							panelRsltsValues[i].add(txtQuantity[i]);
						
						}
						{
							choiceDimention[i] = new Choice();
							choiceDimention[i].setFont(font);
							choiceDimention[i].setPreferredSize(new Dimension(50, 20));
							panelRsltsValues[i].add(choiceDimention[i]);
						}
						{
							txtDateChimOper[i] = new JTextField();
							txtDateChimOper[i].setFont(font);
							txtDateChimOper[i].setSize(new Dimension(6, 15));
							txtDateChimOper[i].setColumns(8);
							txtDateChimOper[i].setHorizontalAlignment(JLabel.CENTER);
							txtDateChimOper[i].setBorder(new LineBorder(new Color(0, 0, 0)));
							panelRsltsValues[i].add(txtDateChimOper[i]);
							
						}
						{
							txtDateMeasur[i] = new JTextField();
							txtDateMeasur[i].setFont(font);
							txtDateMeasur[i].setSize(new Dimension(6, 15));
							txtDateMeasur[i].setColumns(8);
							txtDateMeasur[i].setHorizontalAlignment(JLabel.CENTER);
							txtDateMeasur[i].setBorder(new LineBorder(new Color(0, 0, 0)));
							panelRsltsValues[i].add(txtDateMeasur[i]);
							
						}
						{
							chckbxInProtokol[i] = new JCheckBox("");
							chckbxInProtokol[i].setHorizontalAlignment(SwingConstants.CENTER);
							chckbxInProtokol[i].setAlignmentX(Component.CENTER_ALIGNMENT);
							chckbxInProtokol[i].setPreferredSize(new Dimension(75, 20));
							panelRsltsValues[i].add(chckbxInProtokol[i]);
						}
						{
							btnCheckResult[i] = new JButton("Проверка");
							btnCheckResult[i].setMargin(new Insets(2, 5, 2, 5));
							btnCheckResult[i].setFont(font);
							btnCheckResult[i].setPreferredSize(new Dimension(75, 20));
							btnCheckResult[i].addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
								}
							});
							panelRsltsValues[i].add(btnCheckResult[i]);
						}
					}
				}
				}
				{
					JButton btnAddNuclide = new JButton("Добавяне нуклид");
					btnAddNuclide.setMargin(new Insets(2, 5, 2, 5));
					btnAddNuclide.setPreferredSize(new Dimension(123, 20));
					GridBagConstraints gbc_btnAddNuclide = new GridBagConstraints();
					gbc_btnAddNuclide.anchor = GridBagConstraints.EAST;
					gbc_btnAddNuclide.insets = new Insets(0, 0, 5, 0);
					gbc_btnAddNuclide.gridx = 5;
					gbc_btnAddNuclide.gridy = 5;
					panel.add(btnAddNuclide, gbc_btnAddNuclide);
				}
				{
					JLabel lblBasicValueRsltsFile = new JLabel("New label");
					GridBagConstraints gbc_lblBasicValueRsltsFile = new GridBagConstraints();
					gbc_lblBasicValueRsltsFile.insets = new Insets(0, 0, 5, 5);
					gbc_lblBasicValueRsltsFile.anchor = GridBagConstraints.EAST;
					gbc_lblBasicValueRsltsFile.gridx = 0;
					gbc_lblBasicValueRsltsFile.gridy = 6;
					panel.add(lblBasicValueRsltsFile, gbc_lblBasicValueRsltsFile);
				}
				{
					txtBasicValueResult = new JTextField();
					GridBagConstraints gbc_txtBasicValueResult = new GridBagConstraints();
					gbc_txtBasicValueResult.anchor = GridBagConstraints.WEST;
					gbc_txtBasicValueResult.insets = new Insets(0, 0, 5, 0);
					gbc_txtBasicValueResult.gridwidth = 5;
					gbc_txtBasicValueResult.gridx = 1;
					gbc_txtBasicValueResult.gridy = 6;
					panel.add(txtBasicValueResult, gbc_txtBasicValueResult);
					txtBasicValueResult.setColumns(10);
				}
				{
					JLabel lblDobiv = new JLabel("New label");
					GridBagConstraints gbc_lblDobiv = new GridBagConstraints();
					gbc_lblDobiv.anchor = GridBagConstraints.EAST;
					gbc_lblDobiv.insets = new Insets(0, 0, 0, 5);
					gbc_lblDobiv.gridx = 0;
					gbc_lblDobiv.gridy = 7;
					panel.add(lblDobiv, gbc_lblDobiv);
				}
				{
					Choice choiceDobiv = new Choice();
					GridBagConstraints gbc_choiceDobiv = new GridBagConstraints();
					gbc_choiceDobiv.fill = GridBagConstraints.HORIZONTAL;
					gbc_choiceDobiv.gridwidth = 2;
					gbc_choiceDobiv.insets = new Insets(0, 0, 0, 5);
					gbc_choiceDobiv.gridx = 1;
					gbc_choiceDobiv.gridy = 7;
					panel.add(choiceDobiv, gbc_choiceDobiv);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void PanelLabelResultsList(Panel panel_1) {
		JLabel lblNameNuclide = new JLabel("Нуклид");
		lblNameNuclide.setPreferredSize(new Dimension(42, 20));
		lblNameNuclide.setHorizontalAlignment(JLabel.CENTER);
		lblNameNuclide.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblNameNuclide);

		JLabel lblValue = new JLabel("Стойност");
		lblValue.setPreferredSize(new Dimension(62, 20));
		lblValue.setHorizontalAlignment(JLabel.CENTER);
		lblValue.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblValue);

{
		JLabel lblUncertainty = new JLabel("Неопред.");
		lblUncertainty.setPreferredSize(new Dimension(62, 20));
		lblUncertainty.setHorizontalAlignment(JLabel.CENTER);
		lblUncertainty.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblUncertainty);
}
{
		JLabel lblMDA = new JLabel("МДА");
		lblMDA.setPreferredSize(new Dimension(62, 20));
		lblMDA.setHorizontalAlignment(JLabel.CENTER);
		lblMDA.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblMDA);
}
{
		JLabel lblRazmernosti = new JLabel("Разм.");
		lblRazmernosti.setPreferredSize(new Dimension(50, 20));
		lblRazmernosti.setHorizontalAlignment(JLabel.CENTER);
		lblRazmernosti.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblRazmernosti);
}
{
		JLabel lblSigma = new JLabel("Сигма");
		lblSigma.setPreferredSize(new Dimension(42, 20));
		lblSigma.setHorizontalAlignment(JLabel.CENTER);
		lblSigma.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblSigma);
}
{
		JLabel lblQuantity = new JLabel("Колич.");
		lblQuantity.setPreferredSize(new Dimension(62, 20));
		lblQuantity.setHorizontalAlignment(JLabel.CENTER);
		lblQuantity.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblQuantity);
}
{
		JLabel lblDimention = new JLabel("Разм.");
		lblDimention.setPreferredSize(new Dimension(50, 20));
		lblDimention.setHorizontalAlignment(JLabel.CENTER);
		lblDimention.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblDimention);
}
{
		JLabel lblDateChimOper = new JLabel("ДатаХимОбр");
		lblDateChimOper.setPreferredSize(new Dimension(82, 20));
		lblDateChimOper.setHorizontalAlignment(JLabel.CENTER);
		lblDateChimOper.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblDateChimOper);
}
{
		JLabel lblDateMesur = new JLabel("ДатаАнализ");
		lblDateMesur.setPreferredSize(new Dimension(82, 20));
		lblDateMesur.setHorizontalAlignment(JLabel.CENTER);
		lblDateMesur.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblDateMesur);
}
{
		JLabel lblInProtokol = new JLabel("В Протокол");
		lblInProtokol.setPreferredSize(new Dimension(75, 20));
		lblInProtokol.setHorizontalAlignment(JLabel.CENTER);
		lblInProtokol.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblInProtokol);
}
{
		JLabel lblCheckResult = new JLabel("Проверка");
		lblCheckResult.setPreferredSize(new Dimension(75, 20));
		lblCheckResult.setHorizontalAlignment(JLabel.CENTER);
		lblCheckResult.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(lblCheckResult);
}
	}

}
