package WindowView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Aplication.List_izpitvan_pokazatelDAO;
import DBase_Class.List_izpitvan_pokazatel;

import javax.swing.JScrollPane;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.ComponentOrientation;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ChoiceL_I_P extends JDialog {

	static ArrayList<String> bsic_list = RequestViewAplication.getStringListLIP();
	static int countL_I_P = bsic_list.size();
	
	Panel[] check_panel =new Panel[countL_I_P];
	static JLabel[] label = new JLabel[countL_I_P];
	static JCheckBox[] checkBox = new JCheckBox[countL_I_P];
	
	
	public ChoiceL_I_P(JFrame parent, List<String> list_izpitvan_pokazatel, Boolean fromTamplate) {
		super(parent, "", true);
		final JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		final JPanel contentPanel = new JPanel();
		
//		Panel[] check_panel = new Panel[countL_I_P];
//		JLabel[] label = new JLabel[countL_I_P];
//		JCheckBox[] checkBox = new JCheckBox[countL_I_P];

		setBounds(100, 100, 400, 100 + (countL_I_P * 25));
//		setBounds(100, 100, 400, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		contentPanel.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("Изберете Изпитван Показател");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel_1);

		int i = 0;
		for (String string : bsic_list) {

			check_panel[i] = new Panel();
			panel.add(check_panel[i]);
			check_panel[i].setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			check_panel[i].setLayout(new BoxLayout(check_panel[i], BoxLayout.X_AXIS));

			checkBox[i] = new JCheckBox();
			check_panel[i].add(checkBox[i]);
			
			if(!list_izpitvan_pokazatel.isEmpty()){
			
				for (String str : list_izpitvan_pokazatel) {
				if(str.equals(string))
					checkBox[i].setSelected(true);
				}
			}
			

			label[i] = new JLabel(string);
			check_panel[i].add(label[i]);
			i++;
		}

		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						getChoiceL_P();
						removeAll();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("  +++ "+list_izpitvan_pokazatel.size());
						for (int j = 0; j < bsic_list.size(); j++) 
							checkBox[j].setSelected(false);
						for (String string : list_izpitvan_pokazatel) {
							for (int j = 0; j < bsic_list.size(); j++) {
								if(label[j].getText().equals(string)){
									
									checkBox[j].setSelected(true);
								} 
							}
						
						}
						for (int j = 0; j < bsic_list.size(); j++) {
						
						}
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				dialog.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent evt) {
						for (int j = 0; j < bsic_list.size(); j++){ 
						checkBox[j].setSelected(false);
						}
//						dispose();
					}
				});
					
		if(fromTamplate){
		setVisible(false);
		}else setVisible(true);
	}

	public static  ArrayList<String> getChoiceL_P() {
		ArrayList<String> arr = new ArrayList<String>();
		for (int i = 0; i < countL_I_P; i++) {
			if (checkBox[i].isSelected())
				arr.add(label[i].getText());
		}
		return arr;
	}
	
	public static  ArrayList<List_izpitvan_pokazatel> getListI_PFormChoiceL_P() {
		ArrayList<List_izpitvan_pokazatel> arr = new ArrayList<List_izpitvan_pokazatel>();
		List_izpitvan_pokazatel l_I_P = null;
		for (int i = 0; i < countL_I_P; i++) {
			if (checkBox[i].isSelected()){
				l_I_P =	List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(label[i].getText());
					arr.add(l_I_P);
			}
		}
		return arr;
	}
}
