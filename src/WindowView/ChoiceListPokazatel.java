package WindowView;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class ChoiceListPokazatel extends JFrame {

	private JScrollPane scrollPane;

	private JButton btnPlus;
	private Choice[] choice = new Choice[7];
	private int countCoice = 0;

	public ChoiceListPokazatel() {
//		super("Избор на показател");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		init();
		setVisible(true);
	}

	public void init() {
		final JPanel panel = new JPanel();

		panel.setBorder(null);
		panel.setSize(400, 280);

		scrollPane = new JScrollPane(panel);
		scrollPane.setName("");
		scrollPane.setBorder(null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 12, 299, 20, 20 };
		gbl_panel.rowHeights = new int[] { 25, 25, 25, 25, 25, 25, 25, 25};
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
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 7;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

}
