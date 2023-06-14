package OldClases;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Aplication.Metody_to_NiclideForDobiveDAO;
import Aplication.NuclideDAO;
import DBase_Class.Metody;
import DBase_Class.Metody_to_NiclideForDobive;
import DBase_Class.Nuclide;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.RequestViewFunction;

public class ChoiceNuclideForDobiveWithPlusAndMinus extends JDialog {

	private static final long serialVersionUID = 1L;
	JButton btnPlus;
	static Choice[] choice = new Choice[7];
	GridBagConstraints[] gbc_choice = new GridBagConstraints[7];
	static int countCoice = 0;
	static List<String> masiveStringFromChoice = null;
	static List<String> old_incomingValueStringList;
	static List<String> old_bsic_list;
	static Boolean cancelBtnTurn=false;

	public ChoiceNuclideForDobiveWithPlusAndMinus(JFrame parent,
			String labelString, Metody metod) {
		
				
		super(parent, labelString, true);
		setResizable(false);
		final JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		List<Nuclide> listNuclide = NuclideDAO.getInListAllValueNuclide();
		List<String>  listStringAllNuklide = new ArrayList<>();
		for (Nuclide nuclide : listNuclide) {
			listStringAllNuklide.add(nuclide.getSymbol_nuclide());
		}
		
		List<String> basic_list = listStringAllNuklide;
		
		List<Metody_to_NiclideForDobive> listMetofordobiv = Metody_to_NiclideForDobiveDAO.getListMetody_to_NiclideForDobiveByMetody(metod);
		List<String>  listStringNuklideFromMetod = new ArrayList<>();
		for (Metody_to_NiclideForDobive metody_to_NiclideForDobive : listMetofordobiv) {
			listStringNuklideFromMetod.add(metody_to_NiclideForDobive.getNuclide().getSymbol_nuclide());
		}
		
		List<String> incomingValueStringList = listStringNuklideFromMetod;

		
		old_incomingValueStringList = incomingValueStringList;
		old_bsic_list = basic_list;
		
		
		
		
		setSize(250, 310);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setName("");
		scrollPane.setBorder(null);

		
		final JPanel panel = new JPanel();
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setBorder(null);
		panel.setSize(230, 290);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0 };
		gbl_panel.columnWidths = new int[] { 10, 40, 37, 37, 5 };
		gbl_panel.rowHeights = new int[] { 25, 25, 25, 25, 25, 25, 25, 25 };
		panel.setLayout(gbl_panel);

		
		JLabel lblFirst = new JLabel(labelString);
		GridBagConstraints gbc_lblFirst = new GridBagConstraints();
		gbc_lblFirst.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirst.gridx = 1;
		gbc_lblFirst.gridy = 0;
		panel.add(lblFirst, gbc_lblFirst);
		
		
		
		

		choice[0] = new Choice();
		choice[0].setPreferredSize(new Dimension(120, 23));
		
//		if (incomingValueStringList == null) {
//			for (String string : basic_list) {
//				choice[0].add(string);
//			}
//
//			gbc_choice[0] = new GridBagConstraints();
//			gbc_choice[0].fill = GridBagConstraints.HORIZONTAL;
//			gbc_choice[0].insets = new Insets(0, 0, 5, 5);
//			gbc_choice[0].gridx = 1;
//			gbc_choice[0].gridy = 1;
//			panel.add(choice[0], gbc_choice[0]);
//		}
		// btnMinus (премахване на елемент)
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
		gbc_btnMinus.insets = new Insets(0, 0, 5, 5);
		gbc_btnMinus.gridx = 3;
		gbc_btnMinus.gridy = 1;
		panel.add(btnMinus, gbc_btnMinus);

		// btnPlus (добавяне на елемент)
		countCoice = 0;
		btnPlus = new JButton("+");
		btnPlus.setMargin(new Insets(0, 1, 1, 1));
		btnPlus.setPreferredSize(new Dimension(37, 23));
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				countCoice++;
				choice[countCoice] = new Choice();
				choice[countCoice].setPreferredSize(new Dimension(120, 23));
				
				for (String string : basic_list) {
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

		// paint old elements (изобразяване на привнесени елементи)
		if (incomingValueStringList != null) {
			countCoice = 0;
			for (String incomingString : incomingValueStringList) {
				choice[countCoice] = new Choice();
				choice[countCoice].setPreferredSize(new Dimension(120, 23));
				for (String str : basic_list) {
					choice[countCoice].add(str);
				}
				choice[countCoice].select(incomingString);

				gbc_choice[countCoice] = new GridBagConstraints();
				gbc_choice[countCoice].fill = GridBagConstraints.HORIZONTAL;
				gbc_choice[countCoice].insets = new Insets(0, 0, 5, 5);
				gbc_choice[countCoice].gridx = 1;
				gbc_choice[countCoice].gridy = 1 + countCoice;
				panel.add(choice[countCoice], gbc_choice[countCoice]);
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

		//  buttonPane (панела с бутони ОК Cancel)
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("OK_Btn_Text"));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkDuplicates(createMasiveStringFromChoice())){
					JOptionPane.showMessageDialog(null, ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("ThereAreDublikataElements"));
						
				}else{
					cancelBtnTurn = false; 
				masiveStringFromChoice = createMasiveStringFromChoice();
				removeAll();
				dispose();
				}
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		
		
		
		JButton cancelButton = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("exitBtn_Text"));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if (old_incomingValueStringList != null) {
						cancelBtnTurn = true; 
//						masiveStringFromChoice.clear();
					masiveStringFromChoice = old_incomingValueStringList;
				
					
					
				}
				removeAll();
				dispose();

			}
		});
		// cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

//		dialog.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent evt) {
//				countCoice = 0;
//				for (String string : incomingValueStringList) {
//					choice[countCoice].select(string);
//					countCoice++;
//				}
//				dispose();
//			}
//		});

		setVisible(true);
	}

	
	public static <T extends Comparable<T>> Boolean checkDuplicates(List<T> array) {
        Set<T> dupes = new HashSet<T>();
        for (T i : array) {
            if (!dupes.add(i)) {
                return true;
            }
        }
		return false;

    }

	
	public static List<String> createMasiveStringFromChoice() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i <= countCoice; i++) {
			if(!choice[i].getSelectedItem().equals(""))
			list.add( choice[i].getSelectedItem());
		}
		return list;
	}

	public static Boolean cancelBtnTurn() {
		return cancelBtnTurn;
	}
	
	public static List<String> getMasiveStringFromChoice() {
		if(masiveStringFromChoice==null){
			return old_incomingValueStringList;
		}
		return masiveStringFromChoice;
	}
}