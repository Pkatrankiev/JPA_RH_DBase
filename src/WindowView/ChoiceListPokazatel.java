package WindowView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import javax.swing.border.LineBorder;

import org.apache.poi.hdgf.streams.Stream;

import Aplication.List_izpitvan_pokazatelDAO;
import DBase_Class.List_izpitvan_pokazatel;
import WindowViewAplication.RequestViewAplication;

import javax.swing.SwingConstants;

public class ChoiceListPokazatel extends JFrame {

	private JScrollPane scrollPane;

	private JDialog d;
	private JButton btnPlus;
	private static Choice[] choice = new Choice[7];
	private static int countCoice = 0;

	public ChoiceListPokazatel(JFrame p) {
		
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		
	
	
		final JPanel panel = new JPanel();
		d = new JDialog();
		// set modal true
		d.setModal(true);
		

		scrollPane = new JScrollPane(panel);
		scrollPane.setName("");
		scrollPane.setBorder(null);
		panel.setBorder(null);
		panel.setSize(400, 280);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 12, 299, 20, 20 };
		gbl_panel.rowHeights = new int[] { 25, 25, 25, 25, 25, 25, 25, 25 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		panel.setLayout(gbl_panel);

		JLabel lblFirst = new JLabel("Изберете Показател");
		GridBagConstraints gbc_lblFirst = new GridBagConstraints();
		gbc_lblFirst.insets = new Insets(0, 0, 5, 0);
		gbc_lblFirst.gridwidth = 3;
		gbc_lblFirst.gridx = 1;
		gbc_lblFirst.gridy = 0;
		panel.add(lblFirst, gbc_lblFirst);

		choice[0] = new Choice();
		String[] arr = RequestViewAplication.getStringMassiveLIP();
		for (String string : arr) {
			choice[0].add(string);
		}

		GridBagConstraints gbc_choice = new GridBagConstraints();
		gbc_choice.fill = GridBagConstraints.HORIZONTAL;
		gbc_choice.insets = new Insets(0, 0, 5, 5);
		gbc_choice.gridx = 1;
		gbc_choice.gridy = 1;
		panel.add(choice[0], gbc_choice);

		final JButton btnMinus = new JButton("-");
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
				panel.revalidate();
				panel.repaint();
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

		btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				countCoice++;
				choice[countCoice] = new Choice();
				String[] arr = RequestViewAplication.getStringMassiveLIP();
				for (String string : arr) {
					choice[countCoice].add(string);
				}
				GridBagConstraints gbc_choice_1 = new GridBagConstraints();
				gbc_choice_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_choice_1.insets = new Insets(0, 0, 5, 5);
				gbc_choice_1.gridx = 1;
				gbc_choice_1.gridy = 1 + countCoice;
				panel.add(choice[countCoice], gbc_choice_1);
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

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getChoiceListPokazatel();
				dispose();

			}

		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 7;
		panel.add(btnNewButton, gbc_btnNewButton);

		
		
		
		
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setLocationRelativeTo(p);
		setVisible(true);
	}

	public static String[] getChoiceListPokazatel() {
		String[] arrListIzsledvanPokazatel = new String[countCoice];
		for (int i = 0; i < countCoice; i++) {
			arrListIzsledvanPokazatel[i] = choice[i].getSelectedItem();
		}
		return arrListIzsledvanPokazatel;
	}
}
