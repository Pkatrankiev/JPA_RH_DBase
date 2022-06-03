package Reference;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.commons.lang3.StringUtils;

import Aplication.EjectionDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.PeriodDAO;
import DBase_Class.Ejection;
import DBase_Class.Period;
import GlobalVariable.ReadFileWithGlobalTextVariable;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JScrollPane;

public class MounthlyReferenceForMenuEjectionVolums extends JDialog {

	private static final long serialVersionUID = 7534173139838953837L;
//	private final JPanel basicPanel = new JPanel();
	JButton btnPlus;
	
	static JComboBox<?>[] comboBox_Produkt = new JComboBox[7];
	GridBagConstraints[] gbc_comboBox_Produkt = new GridBagConstraints[7];
	static JComboBox<?>[] comboBox_Obekt = new JComboBox[7];
	GridBagConstraints[] gbc_comboBox_Obekt = new GridBagConstraints[7];
	static JTextField[] textField_Volum = new JTextField[7];
	GridBagConstraints[] gbc_textField_Volum  = new GridBagConstraints[7];
	
	static int countCoice = 0;
	private JTextField txtFieldGodina;
	static String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	
	final String[] strMounth = MounthlyReferenceForCNRDWater.getStringMounth();
	final String[] strProdukt = Izpitvan_produktDAO.getMasiveStringAllValueIzpitvan_produkt();
	final String[] strObekt = Obekt_na_izpitvane_requestDAO.getMasiveStringAllValueObekt_na_izpitvane();
	
	int sizeV = 240;
	int sizeH = 1050;
	int newRowWith = 22;
	int lineWith = 15;
	public MounthlyReferenceForMenuEjectionVolums(JFrame parent, String nameFrame) {
		super(parent, nameFrame, true);
		setResizable(false);
		
		final JDialog dialog = new JDialog();
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
	
	
		setSize(sizeH, sizeV);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane();
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel basicPanel = new JPanel();

		scrollPane.setViewportView(basicPanel);
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 10, 91, 64, 99, 80, 48, 40, 10 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 16, 0, 0, 16 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0,0.0,0.0,0.0,0.0,0.0 };
		basicPanel.setLayout(gbl_panel);

		

		JLabel lblGodina = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_GodinaLabel"));
		GridBagConstraints gbc_lblGodina = new GridBagConstraints();
		gbc_lblGodina.anchor = GridBagConstraints.EAST;
		gbc_lblGodina.insets = new Insets(0, 0, 5, 5);
		gbc_lblGodina.gridx = 1;
		gbc_lblGodina.gridy = 0;
		basicPanel.add(lblGodina, gbc_lblGodina);

		JLabel lblMesec = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWater_MesecLabel"));
		GridBagConstraints gbc_lblMesec = new GridBagConstraints();
		gbc_lblMesec.insets = new Insets(0, 0, 5, 5);
		gbc_lblMesec.gridx = 2;
		gbc_lblMesec.gridy = 0;
		basicPanel.add(lblMesec, gbc_lblMesec);

		txtFieldGodina = new JTextField(year);
		txtFieldGodina.setPreferredSize(new Dimension(16, 20));
		txtFieldGodina.setMinimumSize(new Dimension(16, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.SOUTHEAST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		basicPanel.add(txtFieldGodina, gbc_textField);
		txtFieldGodina.setColumns(5);
		txtFieldGodina.setText(year);

		JComboBox<?> comboBox = new JComboBox<Object>(strMounth);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.anchor = GridBagConstraints.SOUTH;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		basicPanel.add(comboBox, gbc_comboBox);
		
		
		JLabel lblErrorGodina = new JLabel();
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridwidth = 3;
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 2;
		basicPanel.add(lblErrorGodina, gbc_lblNewLabel_3);
		
		JLabel lblReferenceEjection = new JLabel();
		GridBagConstraints gbc_lblReferenceEjection = new GridBagConstraints();
		gbc_lblReferenceEjection.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblReferenceEjection.gridwidth = 5;
		gbc_lblReferenceEjection.insets = new Insets(0, 0, 5, 5);
		gbc_lblReferenceEjection.gridx = 1;
		gbc_lblReferenceEjection.gridy = 3;
		basicPanel.add(lblReferenceEjection, gbc_lblReferenceEjection);
		
		JButton btnReference = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceEjectionVolums_Btn_Reference"));
		GridBagConstraints gbc_btnReference = new GridBagConstraints();
		gbc_btnReference.gridwidth = 2;
		gbc_btnReference.anchor = GridBagConstraints.SOUTHWEST;
		gbc_btnReference.insets = new Insets(0, 0, 5, 5);
		gbc_btnReference.gridx = 3;
		gbc_btnReference.gridy = 1;
		basicPanel.add(btnReference, gbc_btnReference);

		btnReference.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int countRow = StringUtils.countMatches(lblReferenceEjection.getText(), "<br>");
				sizeV = sizeV - countRow*lineWith;
				String mesec = (String) comboBox.getSelectedItem();
				int godina = Integer.parseInt(txtFieldGodina.getText());
				String textReference = createLblReference( mesec, godina);
				countRow = StringUtils.countMatches(textReference, "<br>");
				lblReferenceEjection.setText(textReference);
				sizeV = sizeV+ countRow*lineWith;
				setSize(sizeH, sizeV);
				basicPanel.repaint();
				basicPanel.revalidate();
		System.out.println(countRow+" - "+ textReference);		
			}

		

		});
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.GRAY);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridwidth = 8;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 4;
		basicPanel.add(separator, gbc_separator);
		
		JLabel lbl_Produkt = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Reference_LabelText_IzpitvanProdukt"));
		GridBagConstraints gbc_lbl_Produkt = new GridBagConstraints();
		gbc_lbl_Produkt.gridwidth = 2;
		gbc_lbl_Produkt.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Produkt.gridx = 1;
		gbc_lbl_Produkt.gridy = 5;
		basicPanel.add(lbl_Produkt, gbc_lbl_Produkt);
	
		
		
		JLabel lbl_Obect = new JLabel(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("Reference_LabelText_Obekt_Na_Izpitvane"));
		GridBagConstraints gbc_lbl_Obect = new GridBagConstraints();
		gbc_lbl_Obect.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Obect.gridx = 3;
		gbc_lbl_Obect.gridy = 5;
		basicPanel.add(lbl_Obect, gbc_lbl_Obect);
	
		JLabel lbl_Volum = new JLabel("<html>"+ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("MounthlyReferenceForCNRDWaterTable_Obem")+"<br>[m3]</center></html>");
		GridBagConstraints gbc_lbl_Volum = new GridBagConstraints();
		gbc_lbl_Volum.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Volum.gridx = 4;
		gbc_lbl_Volum.gridy = 5;
		basicPanel.add(lbl_Volum, gbc_lbl_Volum);
		
		generateRow(countCoice, basicPanel, gbl_panel);
		
		
		// btnMinus (премахване на елемент)
		final JButton btnMinus = new JButton("-");
		btnMinus.setPreferredSize(new Dimension(37, 23));
		btnMinus.setMargin(new Insets(0, 1, 1, 1));
				
		btnMinus.setVisible(false);
		
		
	
		btnMinus.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMinus.setHorizontalAlignment(SwingConstants.CENTER);
		btnMinus.setForeground(Color.RED);
		btnMinus.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_btnMinus = new GridBagConstraints();
		gbc_btnMinus.insets = new Insets(0, 0, 5, 5);
		gbc_btnMinus.anchor = GridBagConstraints.EAST;
		gbc_btnMinus.gridx = 6;
		gbc_btnMinus.gridy = 5;
		basicPanel.add(btnMinus, gbc_btnMinus);
		
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteRow(countCoice);
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
				sizeV = sizeV - newRowWith;
				setSize(sizeH, sizeV);
				basicPanel.repaint();
				basicPanel.revalidate();
			}
		});
		
		
		// btnPlus (добавяне на елемент)
		countCoice = 0;
		btnPlus = new JButton("+");
		btnPlus.setMargin(new Insets(0, 1, 1, 1));
		btnPlus.setPreferredSize(new Dimension(37, 23));

		btnPlus.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPlus.setHorizontalAlignment(SwingConstants.CENTER);
		btnPlus.setForeground(Color.BLUE);
		btnPlus.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_btnPlus = new GridBagConstraints();
		gbc_btnPlus.anchor = GridBagConstraints.WEST;
		gbc_btnPlus.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlus.gridx = 5;
		gbc_btnPlus.gridy = 5;
		basicPanel.add(btnPlus, gbc_btnPlus);
		
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				countCoice++;
				
				generateRow(countCoice, basicPanel, gbl_panel);
				
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
				sizeV = sizeV+ newRowWith;
				setSize(sizeH, sizeV);
				basicPanel.repaint();
				basicPanel.revalidate();
			}
		});
		
		
		
		
		
		
		
		
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("saveBtn_Text"));

//		okButton.setActionCommand(
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mesec = (String) comboBox.getSelectedItem();
				int godina = Integer.parseInt(txtFieldGodina.getText());
				saveEjectionObject(mesec, godina);
			}

			

		});
		
		
		JButton cancelButton = new JButton(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
				.get("exitBtn_Text"));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}

		});

		txtFieldGodina.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				MounthlyReferenceForCNRDWater.enterGodina(txtFieldGodina, lblErrorGodina, btnReference, 2022);

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		
		
		
		
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void generateRow(int row, JPanel basicPanel, GridBagLayout gbl_panel) {

		comboBox_Produkt[row] = new JComboBox<Object>(strProdukt);
		gbc_comboBox_Produkt[row] = new GridBagConstraints();
		gbc_comboBox_Produkt[row].gridwidth = 2;
		gbc_comboBox_Produkt[row].insets = new Insets(0, 0, 0, 5);
		gbc_comboBox_Produkt[row].fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Produkt[row].gridx = 1;
		gbc_comboBox_Produkt[row].gridy = 6+ row;
		basicPanel.add(comboBox_Produkt[row], gbc_comboBox_Produkt[row]);
		
		comboBox_Obekt[row] = new JComboBox<Object>(strObekt);
		gbc_comboBox_Obekt[row] = new GridBagConstraints();
		gbc_comboBox_Obekt[row].insets = new Insets(0, 0, 0, 5);
		gbc_comboBox_Obekt[row].fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Obekt[row].gridx = 3;
		gbc_comboBox_Obekt[row].gridy = 6+ row;
		basicPanel.add(comboBox_Obekt[row], gbc_comboBox_Obekt[row]);
		
		textField_Volum[row] = new JTextField();
		gbc_textField_Volum[row] = new GridBagConstraints();
		gbc_textField_Volum[row].insets = new Insets(0, 0, 0, 5);
		gbc_textField_Volum[row].fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Volum[row].gridx = 4;
		gbc_textField_Volum[row].gridy = 6+ row;
		basicPanel.add(textField_Volum[row], gbc_textField_Volum[row]);
		textField_Volum[row].setColumns(5);
		
		textField_Volum[row].addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					double k = Double.parseDouble(textField_Volum[row].getText());
					textField_Volum[row].setForeground(Color.BLACK);
					System.out.println(k);
				} catch (NumberFormatException e1) {
					textField_Volum[row].setForeground(Color.RED);
				}
				
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		
		
		}

	private void deleteRow(int row) {
		comboBox_Produkt[row].setVisible(false);
		comboBox_Obekt[row].setVisible(false);
		textField_Volum[row].setVisible(false);
				
	}
	
	private String createLblReference(String mesec, int godina) {
		
		Period per = PeriodDAO.getValuePeriodByPeriod(mesec);
		String str = "";
		List<Ejection> listReferenceEjection = EjectionDAO.getListEjectionFromMesecANDGodina(per, godina);
		
		if(!listReferenceEjection.isEmpty()){
		String prod = listReferenceEjection.get(0).getProdukt().getName_zpitvan_produkt();
		str = str + prod+"<br>";
		for (Ejection ejection : listReferenceEjection) {
			if(prod.equals(ejection.getProdukt().getName_zpitvan_produkt())){
				str = str + ejection.getObect().getName_obekt_na_izpitvane()+" - "+
						FormatDoubleNumber.formatDoubleToString(ejection.getVolum(),2, true)+" m3 <br>";
			}else{
				prod = ejection.getProdukt().getName_zpitvan_produkt();
				str = str +prod+"<br>";
				str = str + ejection.getObect().getName_obekt_na_izpitvane()+" - "+
						FormatDoubleNumber.formatDoubleToString(ejection.getVolum(),2, true)+" m3 <br>";
			}
		}
		if(!str.isEmpty()){
			str = "<html>" + str +"</html>";
		}
		}
		return str;
	}
	
	private void saveEjectionObject(String mesec, int godina) {
		Period per = PeriodDAO.getValuePeriodByPeriod(mesec);
		
		List<Ejection> listEjection = new ArrayList<Ejection>();
		boolean fl = false;
		try {
		for (int i = 0; i < countCoice+1 ; i++) {
			Double.parseDouble(textField_Volum[i].getText());
			Ejection ejection = new Ejection() ;
			Obekt_na_izpitvane_requestDAO.getValueObekt_na_izpitvane_requestByName(comboBox_Obekt[i].getSelectedItem().toString());
			ejection.setGodina(godina);
			ejection.setMesec(per);
			ejection.setObect(Obekt_na_izpitvane_requestDAO.
					getValueObekt_na_izpitvane_requestByName(comboBox_Obekt[i].getSelectedItem().toString()));
			ejection.setProdukt(Izpitvan_produktDAO.
					getValueIzpitvan_produktByName((comboBox_Produkt[i].getSelectedItem().toString())));
			ejection.setVolum(FormatDoubleNumber.formatStringToDouble(textField_Volum[i].getText(),2));
	
			listEjection.add(ejection);
		}
		
		List<Ejection> listReferenceEjection = EjectionDAO.getListEjectionFromMesecANDGodina(per, godina);	
		
		for (Ejection newEjection : listEjection ) {
			fl = false;
			
			for (Ejection dataBaseEjection : listReferenceEjection ) {	
				if( newEjection.getProdukt().getName_zpitvan_produkt().equals(dataBaseEjection.getProdukt().getName_zpitvan_produkt()) &&
					newEjection.getObect().getName_obekt_na_izpitvane().equals(dataBaseEjection.getObect().getName_obekt_na_izpitvane())){
					fl = true;
					
					if(!FormatDoubleNumber.formatDoubleToString(newEjection.getVolum(),2, true).
							equals(FormatDoubleNumber.formatDoubleToString(dataBaseEjection.getVolum(),2, true))){
						dataBaseEjection.setVolum(newEjection.getVolum());
						EjectionDAO.updateEjection(dataBaseEjection);
						
					}
				}
			}
			

			if(!fl){
				EjectionDAO.setEjection(newEjection);
				
			}
			
		}
} catch (NumberFormatException e1) {
	JOptionPane.showMessageDialog(null, 
			ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("MounthlyReferenceForCNRDWater_ErrorObemBAK"), 
			ReadFileWithGlobalTextVariable.getGlobalTextVariableMap().get("errorOfData"),
			JOptionPane.ERROR_MESSAGE);	
		}
	}
	
	
	
	
}
