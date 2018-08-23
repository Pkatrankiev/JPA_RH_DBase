package WindowView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import Aplication.RequestDAO;
import WindowViewAplication.RequestViewAplication;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.print.CancelablePrintJob;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SampleViewAdd extends JDialog {

	private final JScrollPane scrollPane;
	private JPanel panel_Label;
	private JPanel[] panel;
	private static JLabel[] lbl_sample_code;
	private static JComboBox[] comboBox_OI;
	private static JPanel[] edit_comboBox_OI;
	private static JTextArea[] txtArea_Sample_Descr;
	private static JTextField[] txtFld_Ref_date;
	private static JPanel[] edit_Ref_date;
	private static JComboBox[] comboBox_Period;
	private static JTextField[] txtFld_Year;
	private static Boolean[] corectYear;
	private static Boolean[] corect_Ref_date;
	private static ArrayList<String> comBox_O_I_S_dinam;
	private Boolean notEmptryString;
	private static Boolean cancelEntered = false;
	private Font font = new  Font("Tahoma", Font.PLAIN, 12);

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")
	public SampleViewAdd(Frame parent, final int countSample, int requestCode,  ArrayList<String> comBox_O_I_S, String ref_Date_Time, String period,
			String[][] stringVol) {
		super(parent, "Информация за пробите", true);

		setBounds(100, 100, 910, (countSample * 29) + 120);
		getContentPane().setLayout(new BorderLayout());
		{

			scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			JPanel panel_1 = new JPanel();
			scrollPane.setViewportView(panel_1);
			panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

			panel_Label = new JPanel();
			panel_Label.setAlignmentY(Component.TOP_ALIGNMENT);
			panel_Label.setMaximumSize(new Dimension(900, 30));
			panel_Label.setAutoscrolls(true);

			JLabel lbl_sam_code = new JLabel("Код");
			lbl_sam_code.setPreferredSize(new Dimension(50, 20));
			lbl_sam_code.setHorizontalAlignment(JLabel.CENTER);
			lbl_sam_code.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label.add(lbl_sam_code);
			JLabel lbl_OI = new JLabel("Обект на изпитване");
			lbl_OI.setPreferredSize(new Dimension(225, 20));
			lbl_OI.setHorizontalAlignment(JLabel.CENTER);
			lbl_OI.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label.add(lbl_OI);
			JLabel lbl_Samp_Descr = new JLabel("Описание на пробата");
			lbl_Samp_Descr.setPreferredSize(new Dimension(300, 20));
			lbl_Samp_Descr.setHorizontalAlignment(JLabel.CENTER);
			lbl_Samp_Descr.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label.add(lbl_Samp_Descr);
			JLabel lbl_Ref_date = new JLabel("Референтна дата");
			lbl_Ref_date.setPreferredSize(new Dimension(130, 20));
			lbl_Ref_date.setHorizontalAlignment(JLabel.CENTER);
			lbl_Ref_date.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label.add(lbl_Ref_date);
			JLabel lbl_Preiod = new JLabel("Периодичност");
			lbl_Preiod.setPreferredSize(new Dimension(142, 20));
			lbl_Preiod.setHorizontalAlignment(JLabel.CENTER);
			lbl_Preiod.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label.add(lbl_Preiod);
			panel_1.add(panel_Label);

			
			String[] comBox_Period = RequestViewAplication.getStringMassivePeriod();
			String year = ref_Date_Time.substring(6, 10);

			panel = new JPanel[countSample];
			lbl_sample_code = new JLabel[countSample];
			comboBox_OI = new JComboBox[countSample];
			edit_comboBox_OI = new JPanel[countSample];
			txtArea_Sample_Descr = new JTextArea[countSample];
			txtFld_Ref_date = new JTextField[countSample];
			edit_Ref_date = new JPanel[countSample];
			comboBox_Period = new JComboBox[countSample];
			txtFld_Year = new JTextField[countSample];
			corectYear = new Boolean[countSample];
			corect_Ref_date = new Boolean[countSample];

			notEmptryString = false;
			try {
				int ss = stringVol.length;
				if (ss == countSample)
					notEmptryString = true;
			} catch (NullPointerException e) {
				notEmptryString = false;
			}
			for (int i = 0; i < countSample; i++) {

				final int selection = i;
				{
					panel[i] = new JPanel();
					panel[i].setAlignmentY(Component.TOP_ALIGNMENT);
					panel[i].setMaximumSize(new Dimension(900, 25));
					panel[i].setAutoscrolls(true);

					{
						lbl_sample_code[i] = new JLabel(requestCode + "-" + (i + 1));
						lbl_sample_code[i] .setFont(font);
						lbl_sample_code[i].setPreferredSize(new Dimension(50, 20));

						lbl_sample_code[i].setBorder(new LineBorder(new Color(0, 0, 0)));
						panel[i].add(lbl_sample_code[i]);
					}
					{
						comboBox_OI[i] = new JComboBox();
						comboBox_OI[i] .setFont(font);
						comboBox_OI[i].setPreferredSize(new Dimension(200, 20));

						for (String string : comBox_O_I_S) {
							comboBox_OI[i].addItem(string);
						}
						if (notEmptryString)
							comboBox_OI[i].setSelectedItem(stringVol[i][1]);
						panel[i].add(comboBox_OI[i]);

						edit_comboBox_OI[i] = new JPanel();
						edit_comboBox_OI[i] .setFont(font);
						edit_comboBox_OI[i].setPreferredSize(new Dimension(21, 20));
						ImageIcon pic = new ImageIcon("add-icon.gif");
						edit_comboBox_OI[i].add(new JLabel(pic), BorderLayout.CENTER);
						edit_comboBox_OI[i].setBackground(Color.WHITE);
						panel[i].add(edit_comboBox_OI[i]);

						int l = i;
						edit_comboBox_OI[l].addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								edit_comboBox_OI[l].setBackground(Color.LIGHT_GRAY);
							}

							@Override
							public void mouseExited(MouseEvent e) {
								edit_comboBox_OI[l].setBackground(Color.WHITE);
							}

							public void mousePressed(MouseEvent e) {

								Boolean fl = false;
								final JFrame f = new JFrame();

								AddInChoice choiceO_I_R = new AddInChoice(f, comBox_O_I_S,
										(String) comboBox_OI[l].getSelectedItem());

								String str = AddInChoice.getChoiceO_I_R();

								for (String string : comBox_O_I_S) {
									if (str.equals(string))
										fl = true;
								}
								if (!fl) {
									comBox_O_I_S.add(str);
									// comboBox_OI[l].addItem(str);
									for (int j = 0; j < countSample; j++) {
										for (String string : comBox_O_I_S) {
											comboBox_OI[j].addItem(string);
										}

									}

								}
								comboBox_OI[l].setSelectedItem(str);

								for (int j = 0; j < countSample; j++) {
									String string = comBox_Period[j];

								}
							}

						});

					}
					{
						txtArea_Sample_Descr[i] = new JTextArea();
						txtArea_Sample_Descr[i] .setFont(font);
						if (notEmptryString)
							txtArea_Sample_Descr[i].setText(stringVol[i][2]);
						txtArea_Sample_Descr[i].setPreferredSize(new Dimension(300, 20));
						// txtArea_Sample_Descr[i].setMinimumSize(new
						// Dimension(400, 20));
						txtArea_Sample_Descr[i].setBorder(new LineBorder(new Color(0, 0, 0)));
						txtArea_Sample_Descr[i].addKeyListener(new KeyListener() {

							@Override
							public void keyTyped(KeyEvent event) {

								Dimension d = txtArea_Sample_Descr[selection].getMinimumSize();
								txtArea_Sample_Descr[selection].setPreferredSize(new Dimension(300, (d.height + 2)));
								panel[selection].setMaximumSize(new Dimension(900, (d.height + 8)));
							}

							@Override
							public void keyReleased(KeyEvent event) {

								Dimension d = txtArea_Sample_Descr[selection].getMinimumSize();
								txtArea_Sample_Descr[selection].setPreferredSize(new Dimension(300, (d.height + 2)));
								panel[selection].setMaximumSize(new Dimension(900, (d.height + 8)));
							}

							@Override
							public void keyPressed(KeyEvent event) {

								Dimension d = txtArea_Sample_Descr[selection].getMinimumSize();
								txtArea_Sample_Descr[selection].setPreferredSize(new Dimension(300, (d.height + 2)));
								panel[selection].setMaximumSize(new Dimension(900, (d.height + 8)));
							}
						});

						panel[i].add(txtArea_Sample_Descr[i]);
					}

					{
						txtFld_Ref_date[i] = new JTextField(ref_Date_Time);
						txtFld_Ref_date[i] .setFont(font);
						txtFld_Ref_date[i].setPreferredSize(new Dimension(120, 20));
						if (notEmptryString)
							txtFld_Ref_date[i].setText(stringVol[i][3]);
						txtFld_Ref_date[i].addKeyListener(new KeyListener() {

							@Override
							public void keyTyped(KeyEvent event) {

								if (DatePicker.incorrectDate(txtFld_Ref_date[selection].getText(), true))
									txtFld_Ref_date[selection].setForeground(Color.RED);
								else
									txtFld_Ref_date[selection].setForeground(Color.BLACK);
							}

							@Override
							public void keyReleased(KeyEvent event) {

								if (DatePicker.incorrectDate(txtFld_Ref_date[selection].getText(), true))
									txtFld_Ref_date[selection].setForeground(Color.RED);
								else
									txtFld_Ref_date[selection].setForeground(Color.BLACK);
							}

							@Override
							public void keyPressed(KeyEvent event) {

								if (DatePicker.incorrectDate(txtFld_Ref_date[selection].getText(), true))
									txtFld_Ref_date[selection].setForeground(Color.RED);
								else
									txtFld_Ref_date[selection].setForeground(Color.BLACK);
							}
						});
						panel[i].add(txtFld_Ref_date[i]);
						txtFld_Ref_date[i].setColumns(10);
					}

					edit_Ref_date[i] = new JPanel();
					edit_Ref_date[i] .setFont(font);
					edit_Ref_date[i].setPreferredSize(new Dimension(21, 20));
					ImageIcon pic = new ImageIcon("Modify.gif");
					edit_Ref_date[i].add(new JLabel(pic), BorderLayout.CENTER);
					edit_Ref_date[i].setBackground(Color.WHITE);
					panel[i].add(edit_Ref_date[i]);

					int l = i;
					corect_Ref_date[l] = true;
					edit_Ref_date[l].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							edit_Ref_date[l].setBackground(Color.LIGHT_GRAY);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							edit_Ref_date[l].setBackground(Color.WHITE);
						}

						public void mousePressed(MouseEvent e) {

							try {

								final JFrame f = new JFrame();
								DateChoice date_time_reception = new DateChoice(f, txtFld_Ref_date[l].getText());
								date_time_reception.setVisible(true);

								String textRefDate = "";
								textRefDate = DateChoice.get_date_time_reception();

								if (DatePicker.incorrectDate(textRefDate, true)) {
									txtFld_Ref_date[l].setForeground(Color.RED);
									corect_Ref_date[l] = false;
								} else {
									txtFld_Ref_date[l].setForeground(Color.BLACK);
									corect_Ref_date[l] = true;
								}

								txtFld_Ref_date[l].setText(textRefDate);

							} catch (NumberFormatException e1) {

							}
						}
					});

					{
						comboBox_Period[i] = new JComboBox(comBox_Period);
						comboBox_Period[i] .setFont(font);
						comboBox_Period[i].setPreferredSize(new Dimension(100, 20));

//						for (String string : comBox_Period) {
//							comboBox_Period[i].addItem(string);
//						}
						comboBox_Period[i].setSelectedItem(period);
						if (notEmptryString)
							comboBox_Period[i].setSelectedItem(stringVol[i][4]);
						panel[i].add(comboBox_Period[i]);
					}

					{
						txtFld_Year[i] = new JTextField(year);
						txtFld_Year[i] .setFont(font);
						txtFld_Year[i].setPreferredSize(new Dimension(37, 20));
						if (notEmptryString)
							txtFld_Year[i].setText(stringVol[i][5]);
						panel[i].add(txtFld_Year[i]);
						txtFld_Year[i].setColumns(3);

						int k = i;
						corectYear[k] = true;
						txtFld_Year[i].addKeyListener(new KeyListener() {

							@Override
							public void keyTyped(KeyEvent event) {

							}

							@Override
							public void keyReleased(KeyEvent event) {

								txtFld_Year[k]
										.setText(RequestViewAplication.checkFormatString(txtFld_Year[k].getText()));
								if (RequestDAO.checkRequestCode(txtFld_Year[k].getText())) {
									txtFld_Year[k].setForeground(Color.red);
									corectYear[k] = false;
								} else {
									if (RequestViewAplication.checkMaxVolume(txtFld_Year[k].getText(), 2000, 2050)) {
										txtFld_Year[k].setForeground(Color.red);
										corectYear[k] = false;
									} else {
										txtFld_Year[k].setForeground(Color.BLACK);
										txtFld_Year[k].setBorder(new LineBorder(Color.BLACK));
										corectYear[k] = true;

									}
								}
							}

							@Override
							public void keyPressed(KeyEvent event) {

							}
						});

					}
				}
				panel_1.add(panel[i]);
			}
			System.out.println(notEmptryString);
			if (notEmptryString)
				for (int j = 0; j < stringVol.length; j++) {
					for (int j2 = 0; j2 < stringVol[0].length; j2++) {
						System.out.println(stringVol[j][j2] + " ");
					}
					System.out.println();
				}
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Boolean saveCheck = true;
						String str_Year = "";
						String str_Ref_date = "";
						for (int i = 0; i < countSample; i++) {

							if (!corectYear[i]) {
								str_Year = "некоректна година" + "\n";
								saveCheck = false;
							}

							if (!corect_Ref_date[i]) {
								str_Ref_date = "некоректна дата" + "\n";
								saveCheck = false;
							}
							// String str_Izpit_Prod = "";
							// if
							// (choice_izpitvan_produkt.getSelectedItem().equals(""))
							// {
							// choice_izpitvan_produkt.setBackground(Color.RED);
							// str_Izpit_Prod = "изпитван продукт" + "\n";
							// saveCheck = false;
							// }
							// String str_Obekt_Izpit = "";
							// if
							// (choice_obekt_na_izpitvane_request.getSelectedItem().equals(""))
							// {
							// choice_obekt_na_izpitvane_request.setBackground(Color.RED);
							// str_Obekt_Izpit = "обект на изпитване" + "\n";
							// saveCheck = false;
							// }
							// String str_L_I_P = "";
							// if
							// (txtArea_list_izpitvan_pokazatel.getText().equals(""))
							// {
							// txtArea_list_izpitvan_pokazatel.setBorder(new
							// LineBorder(Color.RED));
							// str_L_I_P = "изпитван показател" + "\n";
							// saveCheck = false;
							// }
							// String str_corectRefDate = "";
							// if (!corectRefDate ||
							// txt_fid_date_time_reception.getText().equals(""))
							// {
							// txt_fid_date_time_reception.setBorder(new
							// LineBorder(Color.RED));
							// str_corectRefDate = "референтна дата" + "\n";
							// saveCheck = false;
							// }
						}
						if (!saveCheck) {
							String str = str_Ref_date + str_Year;

							JOptionPane.showMessageDialog(SampleViewAdd.this, str, "Грешни данни за следните полета:",
									JOptionPane.ERROR_MESSAGE);
						} else {
							getVolumeSampleView(countSample);
							cancelEntered = false;
							comBox_O_I_S_dinam = comBox_O_I_S;
							removeAll();
							dispose();
						}

					}
				});

				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelEntered = true;
										
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}

	public static String[][] getVolumeSampleView(int countSample) {
		String[][] volSampleView = new String[countSample][6];
		
		for (int i = 0; i < countSample; i++) {
			volSampleView[i][0] = lbl_sample_code[i].getText();
			volSampleView[i][1] = comboBox_OI[i].getSelectedItem().toString();
			volSampleView[i][2] = txtArea_Sample_Descr[i].getText();
			volSampleView[i][3] = txtFld_Ref_date[i].getText();
			volSampleView[i][4] = comboBox_Period[i].getSelectedItem().toString();
			volSampleView[i][5] = txtFld_Year[i].getText();
		}
		return volSampleView;
	}
	
	public static Boolean cancelEntered(){
		return cancelEntered;
	}
		
	public static ArrayList<String> getArrayListComboBox_OI(){
		
		return comBox_O_I_S_dinam;
	}
}
