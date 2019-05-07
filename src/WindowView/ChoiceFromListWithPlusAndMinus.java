package WindowView;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.Dimension;

public class ChoiceFromListWithPlusAndMinus extends JDialog {

	
	private static final long serialVersionUID = 1L;
	JButton btnPlus;
	static Choice[] choice = new Choice[7];
	GridBagConstraints[] gbc_choice = new GridBagConstraints[7];
	static int countCoice = 0;

	
	public ChoiceFromListWithPlusAndMinus(JFrame parent, String[] incomingValueStringMasive, List<String> bsic_list, String labelString) {
		super(parent, "", true);
		final JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		
		setSize(430, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setName("");
		scrollPane.setBorder(null);

		final JPanel panel = new JPanel();
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setBorder(null);
		panel.setSize(400, 280);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0};
		gbl_panel.columnWidths = new int[] { 10, 299, 37, 37 };
		gbl_panel.rowHeights = new int[] { 25, 25, 25, 25, 25, 25, 25, 25 };
//		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
//		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		panel.setLayout(gbl_panel);

		JLabel lblFirst = new JLabel(labelString);
		GridBagConstraints gbc_lblFirst = new GridBagConstraints();
		gbc_lblFirst.insets = new Insets(0, 0, 5, 0);
		gbc_lblFirst.gridx = 1;
		gbc_lblFirst.gridy = 0;
		panel.add(lblFirst, gbc_lblFirst);

		choice[0] = new Choice();
		choice[0].setPreferredSize(new Dimension(100, 23));
//		final  = RequestViewAplication.getStringListLIP();
		// String[] arr2 = RequestViewAplication.setMasiveFromList(list);
		
		
		if (incomingValueStringMasive == null) {
		for (String string : bsic_list) {
			choice[0].add(string);
		}

		 gbc_choice[0] = new GridBagConstraints();
		gbc_choice[0].fill = GridBagConstraints.HORIZONTAL;
		gbc_choice[0].insets = new Insets(0, 0, 5, 5);
		gbc_choice[0].gridx = 1;
		gbc_choice[0].gridy = 1;
		panel.add(choice[0], gbc_choice[0]);
		}
		// TODO btnMinus (премахване на елемент)
		final JButton btnMinus = new JButton("-");
		btnMinus.setPreferredSize(new Dimension(37, 23));
		btnMinus.setMargin(new Insets(0, 1, 1, 1));
//		btnMinus.setMinimumSize(new Dimension(37, 24));
//		btnMinus.setMaximumSize(new Dimension(37, 24));
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				choice[countCoice].setVisible(false);
				countCoice--;
				System.out.println("Data - = " + countCoice);
				if (countCoice > 0) {
					btnMinus.setVisible(true);
				} else {
					btnMinus.setVisible(false);
				}
				if (countCoice < 6) {
					btnPlus.setVisible(true);
				} else {
					btnPlus.setVisible(false);
				}
				panel.repaint();
				panel.revalidate();
			}
		});
		btnMinus.setVisible(false);
		btnMinus.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMinus.setHorizontalAlignment(SwingConstants.CENTER);
		btnMinus.setForeground(Color.RED);
		btnMinus.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_btnMinus = new GridBagConstraints();
		gbc_btnMinus.anchor = GridBagConstraints.EAST;
		gbc_btnMinus.insets = new Insets(0, 0, 5, 0);
		gbc_btnMinus.gridx = 3;
		gbc_btnMinus.gridy = 1;
		panel.add(btnMinus, gbc_btnMinus);

		// TODO btnPlus (добав€не на елемент)
		countCoice = 0;
		btnPlus = new JButton("+");
		btnPlus.setMargin(new Insets(0, 1, 1, 1));
		btnPlus.setPreferredSize(new Dimension(37, 23));
//		btnPlus.setMinimumSize(new Dimension(37, 25));
//		btnPlus.setMaximumSize(new Dimension(37, 25));
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				countCoice++;
				choice[countCoice] = new Choice();
				choice[countCoice].setPreferredSize(new Dimension(100, 23));
				List<String> new_list1 = bsic_list;

				for (String string : new_list1) {
					choice[countCoice].add(string);
				}
				gbc_choice[countCoice] = new GridBagConstraints();
				gbc_choice[countCoice].fill = GridBagConstraints.HORIZONTAL;
				gbc_choice[countCoice].insets = new Insets(0, 0, 5, 5);
				gbc_choice[countCoice].gridx = 1;
				gbc_choice[countCoice].gridy = 1 + countCoice;
				panel.add(choice[countCoice], gbc_choice[countCoice]);
				System.out.println("Data + = " + countCoice);
				if (countCoice > 0) {
					btnMinus.setVisible(true);
				} else {
					btnMinus.setVisible(false);
				}
				if (countCoice < 6) {
					btnPlus.setVisible(true);
				} else {
					btnPlus.setVisible(false);
				}

				panel.repaint();
				panel.revalidate();
			}
		});
		btnPlus.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPlus.setHorizontalAlignment(SwingConstants.CENTER);
		btnPlus.setForeground(Color.BLUE);
		btnPlus.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_btnPlus = new GridBagConstraints();
		gbc_btnPlus.anchor = GridBagConstraints.WEST;
		gbc_btnPlus.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlus.gridx = 2;
		gbc_btnPlus.gridy = 1;
		panel.add(btnPlus, gbc_btnPlus);

		scrollPane.setViewportView(panel);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		dialog.setLocationRelativeTo(parent);
		dialog.setLocationRelativeTo(parent);

		// TODO paint old elements (изобраз€ване на привнесени елементи)
		if (incomingValueStringMasive != null) {
			countCoice = 0;
			for (String string : incomingValueStringMasive) {
				choice[countCoice] = new Choice();
				choice[countCoice].add(string);
				choice[countCoice].select(string);

				gbc_choice[countCoice] = new GridBagConstraints();
				gbc_choice[countCoice].fill = GridBagConstraints.HORIZONTAL;
				gbc_choice[countCoice].insets = new Insets(0, 0, 5, 5);
				gbc_choice[countCoice].gridx = 1;
				gbc_choice[countCoice].gridy = 1 + countCoice;
				panel.add(choice[countCoice], gbc_choice[countCoice]);
				System.out.println("Data + = " + countCoice);
				if (countCoice > 0) {
					btnMinus.setVisible(true);
				} else {
					btnMinus.setVisible(false);
				}
				if (countCoice < 6) {
					btnPlus.setVisible(true);
				} else {
					btnPlus.setVisible(false);
				}
//				// 'i' obhojda wsichki nalichni elementi
//				for (int i = 0; i < countCoice; i++) {
//					ArrayList<String> new_list3 = bsic_list;
//					
//					// 'k' obhojda izbranite stoinosti v nalichnite elementi
//					for (int k = 0; k < countCoice; k++) {
//						if (k != i)
//							new_list3.remove(choice[k].getSelectedItem());
//					}
//					choice[i].removeAll();
//					for (String str1 : new_list3) {
//
//						choice[i].add(str1);
//					}
//				}
				
				panel.revalidate();
				panel.repaint();
				countCoice++;
			}
			countCoice--;

		}

		// TODO buttonPane (панела с бутони ќ  Cancel)
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getChoiceListPokazatel1();
				removeAll();
				dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// panel.removeAll();
				// panel.revalidate();
				// panel.repaint();
				if (incomingValueStringMasive != null) {
				int k = 0;
				for (String string : incomingValueStringMasive) {
					choice[k].select(string);
					k++;
				}
				}
				dispose();
				
			}
		});
//		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				countCoice = 0;
				for (String string : incomingValueStringMasive) {
					choice[countCoice].select(string);
					countCoice++;
				}
				dispose();
			}
		});

		setVisible(true);
	}

	public static String[] getChoiceListPokazatel1() {
		String[] arr = new String[countCoice + 1];
		for (int i = 0; i <= countCoice; i++) {
			arr[i] = choice[i].getSelectedItem();
		}
		return arr;
	}

	
}
