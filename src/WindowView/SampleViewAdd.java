package WindowView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import WindowView.SampleAddView.MyTableModel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

public class SampleViewAdd extends JDialog {

	private final JScrollPane scrollPane;
	private JPanel[] panel;
	private JLabel [] lbl_sample_code;
	private JComboBox [] comboBox_OI;
	private JTextArea[] txtArea_Sample_Descr; 
	private JTextField[] txtFld_Ref_date;
	private JComboBox[] comboBox_Period;
	private JTextField[] txtFld_Year;
	

	/**
	 * Create the dialog.
	 */
	public SampleViewAdd(Frame parent, int countSample, int requestCode, String ref_Date_Time) {
		super(parent, "Информация за пробите", true);
		setBounds(100, 100, 850, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			
			scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
							JPanel panel_1 = new JPanel();
				scrollPane.setViewportView(panel_1);
				panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
				
			
			String year = ref_Date_Time.substring(6, 10);
			
			panel = new JPanel [countSample];
			
			
			lbl_sample_code = new JLabel [countSample];
			comboBox_OI = new JComboBox [countSample];
			txtArea_Sample_Descr = new JTextArea[countSample];
			txtFld_Ref_date = new JTextField[countSample];
			comboBox_Period = new JComboBox [countSample];
			txtFld_Year = new JTextField[countSample];
			
			for (int i = 0; i < countSample; i++) {
//			int i=1;
			{
				panel[i] = new JPanel();
				panel[i].setAlignmentY(Component.TOP_ALIGNMENT);
				panel[i].setMaximumSize(new Dimension(850, 25));
				panel[i].setAutoscrolls(true);
				
				{
					lbl_sample_code[i] = new JLabel(requestCode + "-" + (i+1));
					lbl_sample_code[i].setPreferredSize(new Dimension(50, 14));
//					lbl_sample_code[i].setMinimumSize(new Dimension(50, 14));
//					lbl_sample_code[i].setMaximumSize(new Dimension(50, 14));
					lbl_sample_code[i].setBorder(new LineBorder(new Color(0, 0, 0)));
					panel[i].add(lbl_sample_code[i]);
				}
				{
					comboBox_OI[i] = new JComboBox();
					comboBox_OI[i].setPreferredSize(new Dimension(200, 20));
//					comboBox_OI[i].setMinimumSize(new Dimension(200, 20));
					panel[i].add(comboBox_OI[i]);
				}
				{
					txtArea_Sample_Descr[i] = new JTextArea("123456789");
					txtArea_Sample_Descr[i].setPreferredSize(new Dimension(300, 20));
//					txtArea_Sample_Descr[i].setMinimumSize(new Dimension(400, 20));
					txtArea_Sample_Descr[i].setBorder(new LineBorder(new Color(0, 0, 0)));
					panel[i].add(txtArea_Sample_Descr[i]);
				}
				{
					txtFld_Ref_date[i] = new JTextField(ref_Date_Time);
//					txtFld_Ref_date[i].setMinimumSize(new Dimension(60, 20));
//					txtFld_Ref_date[i].setPreferredSize(new Dimension(60, 20));
					panel[i].add(txtFld_Ref_date[i]);
					txtFld_Ref_date[i].setColumns(9);
				}
				{
					comboBox_Period[i] = new JComboBox();
					comboBox_Period[i].setPreferredSize(new Dimension(100, 20));
					comboBox_Period[i].setMinimumSize(new Dimension(100, 20));
					panel[i].add(comboBox_Period[i]);
				}
				{
					txtFld_Year[i] = new JTextField(year);
//					txtFld_Year[i].setMinimumSize(new Dimension(50, 20));
//					txtFld_Year[i].setPreferredSize(new Dimension(50, 20));
					panel[i].add(txtFld_Year[i]);
					txtFld_Year[i].setColumns(3);
				}
			}
			panel_1.add(panel[i]);
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

}
