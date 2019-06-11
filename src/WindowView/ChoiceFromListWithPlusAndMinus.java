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
import javax.swing.SwingUtilities;

import java.awt.Component;
import java.awt.Dimension;

public class ChoiceFromListWithPlusAndMinus extends JDialog {

	private static final long serialVersionUID = 1L;
	JButton btnPlus;
	static Choice[] choice = new Choice[7];
	GridBagConstraints[] gbc_choice = new GridBagConstraints[7];
	static int countCoice = 0;
	static List<String> masiveStringFromChoice = null;

	public ChoiceFromListWithPlusAndMinus(JFrame parent, List<String> incomingValueStringList, List<String> bsic_list,
			String labelString) {
		super(parent, "", true);
		final JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
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
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
		gbl_panel.columnWidths = new int[] { 10, 250, 37, 37 };
		gbl_panel.rowHeights = new int[] { 25, 25, 25, 25, 25, 25, 25, 25 };
		panel.setLayout(gbl_panel);

		JLabel lblFirst = new JLabel(labelString);
		GridBagConstraints gbc_lblFirst = new GridBagConstraints();
		gbc_lblFirst.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirst.gridx = 1;
		gbc_lblFirst.gridy = 0;
		panel.add(lblFirst, gbc_lblFirst);
		
		
		JButton add_Button = new JButton("Добавяне");
		add_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				RequestViewFunction.addNewObjectIn_Choice_Obekt_na_Izpitvane_Request(parent, incomingValueStringList,  bsic_list, labelString);
			
				
			}
		});
		add_Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		add_Button.setPreferredSize(new Dimension(75, 23));
		add_Button.setMargin(new Insets(0, 1, 1, 1));
		GridBagConstraints gbc_add_Button = new GridBagConstraints();
		gbc_add_Button.gridwidth = 2;
		gbc_add_Button.insets = new Insets(0, 0, 5, 0);
		gbc_add_Button.gridx = 2;
		gbc_add_Button.gridy = 0;
		panel.add(add_Button, gbc_add_Button);
		
		
		
		

		choice[0] = new Choice();
		choice[0].setPreferredSize(new Dimension(120, 23));
		
		if (incomingValueStringList == null) {
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

		// TODO btnPlus (добавяне на елемент)
		countCoice = 0;
		btnPlus = new JButton("+");
		btnPlus.setMargin(new Insets(0, 1, 1, 1));
		btnPlus.setPreferredSize(new Dimension(37, 23));
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				countCoice++;
				choice[countCoice] = new Choice();
				choice[countCoice].setPreferredSize(new Dimension(120, 23));
				
				for (String string : bsic_list) {
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

		// TODO paint old elements (изобразяване на привнесени елементи)
		if (incomingValueStringList != null) {
			countCoice = 0;
			for (String incomingString : incomingValueStringList) {
				choice[countCoice] = new Choice();
				choice[countCoice].setPreferredSize(new Dimension(120, 23));
				for (String str : bsic_list) {
					choice[countCoice].add(str);
				}
				choice[countCoice].select(incomingString);

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
	
				panel.revalidate();
				panel.repaint();
				countCoice++;
			}
			countCoice--;

		}

		// TODO buttonPane (панела с бутони ОК Cancel)
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				masiveStringFromChoice = createMasiveStringFromChoice();
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
					if (incomingValueStringList != null) {
					int k = 0;
					for (String string : incomingValueStringList) {
						choice[k].select(string);
						k++;
					}
				}
				dispose();

			}
		});
		// cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				countCoice = 0;
				for (String string : incomingValueStringList) {
					choice[countCoice].select(string);
					countCoice++;
				}
				dispose();
			}
		});

		setVisible(true);
	}

	public static List<String> createMasiveStringFromChoice() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i <= countCoice; i++) {
			list.add( choice[i].getSelectedItem());
		}
		return list;
	}

	public static List<String> getMasiveStringFromChoice() {
		return masiveStringFromChoice;
	}
}
