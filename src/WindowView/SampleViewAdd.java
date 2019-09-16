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
import Aplication.SampleDAO;
import DBase_Class.Request;
import DBase_Class.Sample;
import GlobalVariable.GlobalPathForIcons;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SampleViewAdd extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JScrollPane scrollPane;
	
	private static JPanel panel_Label;
	private static JPanel[] panel;
	private static JPanel[] edit_comboBox_OI;
	private static JPanel[] edit_Ref_date;
	
	private static JLabel[] lbl_sample_code;
	@SuppressWarnings("rawtypes")
	private static JComboBox[] comboBox_OI;
	@SuppressWarnings("rawtypes")
	private static JComboBox[] comboBox_OIR;
	@SuppressWarnings("rawtypes")
	private static JComboBox[] comboBox_Period;
	
	private static JTextArea[] txtArea_Sample_Descr;
	private static JTextField[] txtFld_Ref_date;
	private static JTextField[] txtFld_Year;
	private static Boolean[] corectYear;
	private static Boolean[] corect_Ref_date;
	
	private static ArrayList<String> comBox_O_I_S_dinam;
	private static Boolean notEmptryString;
	private static Boolean cancelEntered = false;
	private static Font font = new  Font("Tahoma", Font.PLAIN, 12);
	private static int dimmen = 1130;
	 
	@SuppressWarnings("unchecked")
	public SampleViewAdd(Frame parent, final int countSample, int requestCode,  ArrayList<String> comBox_O_I_S, String ref_Date_Time, String period,
			String[][] incoming_masiveValueSample, Request tamplateRequest, List<String>  listStringOfRequest_To_ObektNaIzpitvaneRequest) {
		super(parent, "Информация за пробите", true);
		
		List<Sample> listSample = new ArrayList<Sample>();
		if(tamplateRequest != null){
			listSample = SampleDAO.getListSampleFromColumnByVolume("request", tamplateRequest);
				}
		
		setBounds(100, 100, dimmen, (countSample * 29) + 120);
		getContentPane().setLayout(new BorderLayout());
		{

			scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			JPanel panel_1 = new JPanel();
			scrollPane.setViewportView(panel_1);
			panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

			panel_Label = new JPanel();
			panel_Label.setAlignmentY(Component.TOP_ALIGNMENT);
			panel_Label.setMaximumSize(new Dimension(1120, 30));
			panel_Label.setAutoscrolls(true);

			JLabel lbl_sam_code = new JLabel("Код");
			lbl_sam_code.setPreferredSize(new Dimension(50, 20));
			lbl_sam_code.setHorizontalAlignment(JLabel.CENTER);
			lbl_sam_code.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label.add(lbl_sam_code);
			
			JLabel lbl_OI_Request = new JLabel("Обект от който са взети пробите");
			lbl_OI_Request.setPreferredSize(new Dimension(220, 20));
			lbl_OI_Request.setHorizontalAlignment(JLabel.CENTER);
			lbl_OI_Request.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label.add(lbl_OI_Request);
			
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

			
			String[] comBox_Period = RequestViewFunction.getStringMassivePeriod();
			String year = ref_Date_Time.substring(6, 10);

			panel = new JPanel[countSample];
			lbl_sample_code = new JLabel[countSample];
			comboBox_OIR = new JComboBox[countSample];
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
				int ss = incoming_masiveValueSample.length;
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
					panel[i].setMaximumSize(new Dimension(1120, 25));
					panel[i].setAutoscrolls(true);

					{
						lbl_sample_code[i] = new JLabel(requestCode + "-" + (i + 1));
						lbl_sample_code[i] .setFont(font);
						lbl_sample_code[i].setPreferredSize(new Dimension(50, 20));

						lbl_sample_code[i].setBorder(new LineBorder(new Color(0, 0, 0)));
						panel[i].add(lbl_sample_code[i]);
					}
					{
						comboBox_OIR[i] = new JComboBox<Object>();
						comboBox_OIR[i] .setFont(font);
						comboBox_OIR[i].setPreferredSize(new Dimension(220, 20));

						for (String string : listStringOfRequest_To_ObektNaIzpitvaneRequest) {
							comboBox_OIR[i].addItem(string);
						}
						if (notEmptryString){
							comboBox_OIR[i].setSelectedItem(incoming_masiveValueSample[i][1]);
						}else{
						if(tamplateRequest != null && i<listSample.size())
							comboBox_OIR[i].setSelectedItem(listSample.get(i).getRequest_to_obekt_na_izpitvane_request().getObektNaIzp().getName_obekt_na_izpitvane());
						}
						panel[i].add(comboBox_OIR[i]);
					}
					{
						comboBox_OI[i] = new JComboBox<Object>();
						comboBox_OI[i] .setFont(font);
						comboBox_OI[i].setPreferredSize(new Dimension(200, 20));

						for (String string : comBox_O_I_S) {
							comboBox_OI[i].addItem(string);
						}
						if (notEmptryString){
							comboBox_OI[i].setSelectedItem(incoming_masiveValueSample[i][2]);
						}else{
						if(tamplateRequest != null && i<listSample.size())
							comboBox_OI[i].setSelectedItem(listSample.get(i).getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane());
						}
						panel[i].add(comboBox_OI[i]);

						edit_comboBox_OI[i] = new JPanel();
						edit_comboBox_OI[i] .setFont(font);
						edit_comboBox_OI[i].setPreferredSize(new Dimension(21, 20));
						ImageIcon pic = new ImageIcon(GlobalPathForIcons.get_destination_addIcon());
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

								new AddInChoice(f, comBox_O_I_S,
										(String) comboBox_OI[l].getSelectedItem());

								String str = AddInChoice.getChoice();

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
							
							}

						});

					}
					{
						txtArea_Sample_Descr[i] = new JTextArea();
						txtArea_Sample_Descr[i] .setFont(font);
						if (notEmptryString){
							txtArea_Sample_Descr[i].setText(incoming_masiveValueSample[i][3]);
						}else{
						if(tamplateRequest != null && i<listSample.size())
							txtArea_Sample_Descr[i].setText(listSample.get(i).getDescription_sample());
						}
						txtArea_Sample_Descr[i].setPreferredSize(new Dimension(300, 20));
						// txtArea_Sample_Descr[i].setMinimumSize(new
						// Dimension(400, 20));
						txtArea_Sample_Descr[i].setBorder(new LineBorder(new Color(0, 0, 0)));
						txtArea_Sample_Descr[i].addKeyListener(new KeyListener() {

							@Override
							public void keyTyped(KeyEvent event) {

								Dimension d = txtArea_Sample_Descr[selection].getMinimumSize();
								txtArea_Sample_Descr[selection].setPreferredSize(new Dimension(300, (d.height + 2)));
								panel[selection].setMaximumSize(new Dimension(dimmen, (d.height + 8)));
							}

							@Override
							public void keyReleased(KeyEvent event) {

								Dimension d = txtArea_Sample_Descr[selection].getMinimumSize();
								txtArea_Sample_Descr[selection].setPreferredSize(new Dimension(300, (d.height + 2)));
								panel[selection].setMaximumSize(new Dimension(dimmen, (d.height + 8)));
							}

							@Override
							public void keyPressed(KeyEvent event) {

								Dimension d = txtArea_Sample_Descr[selection].getMinimumSize();
								txtArea_Sample_Descr[selection].setPreferredSize(new Dimension(300, (d.height + 2)));
								panel[selection].setMaximumSize(new Dimension(dimmen, (d.height + 8)));
							}
						});

						panel[i].add(txtArea_Sample_Descr[i]);
					}

					{
						txtFld_Ref_date[i] = new JTextField(ref_Date_Time);
						txtFld_Ref_date[i] .setFont(font);
						txtFld_Ref_date[i].setPreferredSize(new Dimension(120, 20));
						if (notEmptryString)
							txtFld_Ref_date[i].setText(incoming_masiveValueSample[i][4]);
												
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
					ImageIcon pic = new ImageIcon(GlobalPathForIcons.get_destination_ModifyIcon());
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
								textRefDate = DateChoice.get_date_time_reference();

								if (DatePicker.incorrectDate(textRefDate, true)) {
									txtFld_Ref_date[l].setForeground(Color.RED);
									corect_Ref_date[l] = false;
								} else {
									txtFld_Ref_date[l].setForeground(Color.BLACK);
									corect_Ref_date[l] = true;
								}

								txtFld_Ref_date[l].setText(textRefDate);
								String sstr = txtArea_Sample_Descr[l].getText();
								sstr = sstr + DateChoice.get_str_period_sample();
								txtArea_Sample_Descr[l].setText(sstr);

							} catch (NumberFormatException e1) {

							}
						}
					});

					{
						comboBox_Period[i] = new JComboBox<Object>(comBox_Period);
						comboBox_Period[i] .setFont(font);
						comboBox_Period[i].setPreferredSize(new Dimension(100, 20));

//						for (String string : comBox_Period) {
//							comboBox_Period[i].addItem(string);
//						}
						comboBox_Period[i].setSelectedItem(period);
						if (notEmptryString)
							comboBox_Period[i].setSelectedItem(incoming_masiveValueSample[i][5]);
												
						panel[i].add(comboBox_Period[i]);
					}

					{
						if(period==null){
							year="";
						}
						txtFld_Year[i] = new JTextField(year);
						txtFld_Year[i] .setFont(font);
						txtFld_Year[i].setPreferredSize(new Dimension(37, 20));
						if (notEmptryString)
							txtFld_Year[i].setText(incoming_masiveValueSample[i][6]);
						
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
										.setText(RequestViewFunction.checkFormatString(txtFld_Year[k].getText()));
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
				for (int j = 0; j < incoming_masiveValueSample.length; j++) {
					for (int j2 = 0; j2 < incoming_masiveValueSample[0].length; j2++) {
						System.out.println(incoming_masiveValueSample[j][j2] + " ");
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
				String[][] volSampleView = new String[countSample][7];
				
				for (int i = 0; i < countSample; i++) {
					volSampleView[i][0] = lbl_sample_code[i].getText();
					volSampleView[i][1] = comboBox_OIR[i].getSelectedItem().toString();
					volSampleView[i][2] = comboBox_OI[i].getSelectedItem().toString();
					volSampleView[i][3] = txtArea_Sample_Descr[i].getText();
					volSampleView[i][4] = txtFld_Ref_date[i].getText();
					volSampleView[i][5] = comboBox_Period[i].getSelectedItem().toString();
					volSampleView[i][6] = txtFld_Year[i].getText();
					
				
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
