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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.ArrayUtils;

import Aplication.Izpitvan_produktDAO;
import DBase_Class.Izpitvan_produkt;
import WindowViewAplication.RequestViewAplication;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.Component;

public class ChoiceListIzpPokazatel extends JDialog {

	
	
	JButton btnPlus;
	static Choice[] choice = new Choice[7];
	static int countCoice = 0;

	/**
	 * Create the dialog.
	 */
	public ChoiceListIzpPokazatel(JFrame parent) {
		 super(parent, "", true);
		final JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 500, 350);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
			JScrollPane scrollPane = new JScrollPane((Component) null);
			scrollPane.setName("");
			scrollPane.setBorder(null);

			
			
				final JPanel panel = new JPanel();
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
				final List<String> list = RequestViewAplication.getStringListLIP();
				String[] arr = RequestViewAplication.setMasiveFromList(list);
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
				countCoice = 0;
				btnPlus = new JButton("+");
				btnPlus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						countCoice++;
						choice[countCoice] = new Choice();

						list.remove(choice[countCoice-1].getSelectedItem());
					
						String[] arr2 = RequestViewAplication.setMasiveFromList(list);
						for (String string : arr2) {
						
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

				scrollPane.setViewportView(panel);
				getContentPane().add(scrollPane, BorderLayout.CENTER);
				dialog.setLocationRelativeTo(parent);
				dialog.setLocationRelativeTo(parent);
				
			

		
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
//						panel.removeAll();
						panel.revalidate();
						panel.repaint();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			
				setVisible(true);
	}

	public static String[] getChoiceListPokazatel1() {
	 String[] arr = new String[countCoice+1];
		for (int i = 0; i <= countCoice; i++) {
			arr[i] = choice[i].getSelectedItem();
		}
		return arr;
	}

	
}
