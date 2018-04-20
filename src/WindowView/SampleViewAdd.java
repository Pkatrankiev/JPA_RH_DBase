package WindowView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import WindowView.SampleAddView.MyTableModel;
import WindowViewAplication.RequestViewAplication;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SampleViewAdd extends JDialog {

	private final JScrollPane scrollPane;
	private JPanel panel_Label;
	private JPanel[] panel;
	private static JLabel[] lbl_sample_code;
	private static JComboBox[] comboBox_OI;
	private static JTextArea[] txtArea_Sample_Descr;
	private static JTextField[] txtFld_Ref_date;
	private static JComboBox[] comboBox_Period;
	private static JTextField[] txtFld_Year;

	/**
	 * Create the dialog.
	 */
	public SampleViewAdd(Frame parent, final int countSample, int requestCode, String ref_Date_Time, String period,
			String[][] stringVol) {
		super(parent, "Информация за пробите", true);

		setBounds(100, 100, 850, (countSample * 29) + 120);
		getContentPane().setLayout(new BorderLayout());
		{

			scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			JPanel panel_1 = new JPanel();
			scrollPane.setViewportView(panel_1);
			panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

			panel_Label = new JPanel();
			panel_Label.setAlignmentY(Component.TOP_ALIGNMENT);
			panel_Label.setMaximumSize(new Dimension(850, 30));
			panel_Label.setAutoscrolls(true);

			JLabel lbl_sam_code = new JLabel("Код");
			lbl_sam_code.setPreferredSize(new Dimension(50, 20));
			lbl_sam_code.setHorizontalAlignment(JLabel.CENTER);
			lbl_sam_code.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label.add(lbl_sam_code);
			JLabel lbl_OI = new JLabel("Обект на изпитване");
			lbl_OI.setPreferredSize(new Dimension(200, 20));
			lbl_OI.setHorizontalAlignment(JLabel.CENTER);
			lbl_OI.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label.add(lbl_OI);
			JLabel lbl_Samp_Descr = new JLabel("Описание на пробата");
			lbl_Samp_Descr.setPreferredSize(new Dimension(300, 20));
			lbl_Samp_Descr.setHorizontalAlignment(JLabel.CENTER);
			lbl_Samp_Descr.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label.add(lbl_Samp_Descr);
			JLabel lbl_Ref_date = new JLabel("Референтна дата");
			lbl_Ref_date.setPreferredSize(new Dimension(100, 20));
			lbl_Ref_date.setHorizontalAlignment(JLabel.CENTER);
			lbl_Ref_date.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label.add(lbl_Ref_date);
			JLabel lbl_Preiod = new JLabel("Периодичност");
			lbl_Preiod.setPreferredSize(new Dimension(142, 20));
			lbl_Preiod.setHorizontalAlignment(JLabel.CENTER);
			lbl_Preiod.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_Label.add(lbl_Preiod);
			panel_1.add(panel_Label);

			String[] comBox_O_I_S = RequestViewAplication.getStringMassiveO_I_S();
			String[] comBox_Period = RequestViewAplication.getStringMassivePeriod();
			String year = ref_Date_Time.substring(6, 10);

			panel = new JPanel[countSample];
			lbl_sample_code = new JLabel[countSample];
			comboBox_OI = new JComboBox[countSample];
			txtArea_Sample_Descr = new JTextArea[countSample];
			txtFld_Ref_date = new JTextField[countSample];
			comboBox_Period = new JComboBox[countSample];
			txtFld_Year = new JTextField[countSample];

			Boolean notEmptryString = false;
			try {
				int ss = stringVol.length;
				if (ss == countSample)
					notEmptryString = true;
			} catch (NullPointerException e) {
				notEmptryString = false;
			}
			for (int i = 0; i < countSample; i++) {
				// int i=1;
				final int selection = i;
				{
					panel[i] = new JPanel();
					panel[i].setAlignmentY(Component.TOP_ALIGNMENT);
					panel[i].setMaximumSize(new Dimension(850, 25));
					panel[i].setAutoscrolls(true);

					{
						lbl_sample_code[i] = new JLabel(requestCode + "-" + (i + 1));
						lbl_sample_code[i].setPreferredSize(new Dimension(50, 20));

						lbl_sample_code[i].setBorder(new LineBorder(new Color(0, 0, 0)));
						panel[i].add(lbl_sample_code[i]);
					}
					{
						comboBox_OI[i] = new JComboBox();
						comboBox_OI[i].setPreferredSize(new Dimension(200, 20));
						for (String string : comBox_O_I_S) {
							comboBox_OI[i].addItem(string);
						}
						if (notEmptryString)
							comboBox_OI[i].setSelectedItem(stringVol[i][1]);
						panel[i].add(comboBox_OI[i]);
					}
					{
						txtArea_Sample_Descr[i] = new JTextArea();
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
								panel[selection].setMaximumSize(new Dimension(850, (d.height + 8)));
							}

							@Override
							public void keyReleased(KeyEvent event) {

								Dimension d = txtArea_Sample_Descr[selection].getMinimumSize();
								txtArea_Sample_Descr[selection].setPreferredSize(new Dimension(300, (d.height + 2)));
								panel[selection].setMaximumSize(new Dimension(850, (d.height + 8)));
							}

							@Override
							public void keyPressed(KeyEvent event) {

								Dimension d = txtArea_Sample_Descr[selection].getMinimumSize();
								txtArea_Sample_Descr[selection].setPreferredSize(new Dimension(300, (d.height + 2)));
								panel[selection].setMaximumSize(new Dimension(850, (d.height + 8)));
							}
						});

						panel[i].add(txtArea_Sample_Descr[i]);
					}

					{
						txtFld_Ref_date[i] = new JTextField(ref_Date_Time);
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
						txtFld_Ref_date[i].setColumns(9);
					}

					{
						comboBox_Period[i] = new JComboBox();

						comboBox_Period[i].setPreferredSize(new Dimension(100, 20));
						for (String string : comBox_Period) {
							comboBox_Period[i].addItem(string);
						}
						comboBox_Period[i].setSelectedItem(period);
						if (notEmptryString)
							comboBox_Period[i].setSelectedItem(stringVol[i][4]);
						panel[i].add(comboBox_Period[i]);
					}

					{
						txtFld_Year[i] = new JTextField(year);
						if (notEmptryString)
							txtFld_Year[i].setText(stringVol[i][5]);
						panel[i].add(txtFld_Year[i]);
						txtFld_Year[i].setColumns(3);
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
						getVolumeSampleView(countSample);
						removeAll();
						dispose();
					}
				});
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
}
