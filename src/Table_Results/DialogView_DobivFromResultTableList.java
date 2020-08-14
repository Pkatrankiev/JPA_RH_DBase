package Table_Results;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;

import DBase_Class.Dobiv;
import GlobalVariable.ReadFileWithGlobalTextVariable;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class DialogView_DobivFromResultTableList extends JDialog {

	private static final long serialVersionUID = 1L;

	static String window_Title = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
			.get("TableDobivFromResult_windowTitle_Text");
	static String baisicText = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
			.get("TableDobivFromResult_Text");
	static String nuclideText = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
			.get("TableDobivFromResult_Nuclide");
	static String dobivText = ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
			.get("TableDobivFromResult_Dobiv");

	private static List<Dobiv> listAllDobivs;
	private Dobiv selectDobiv;

	public  DialogView_DobivFromResultTableList(Frame parent, List<Dobiv> listNameDobivs, Dobiv incomingDobiv) {
		super(parent, window_Title, true);
		

		listAllDobivs = listNameDobivs;
		selectDobiv = incomingDobiv;

		setSize(new Dimension(200, 200));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 105, 77, 0 };
		gridBagLayout.rowHeights = new int[] { 31, 27, 25, 0, 43, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblbaisicText = new JLabel(baisicText);
		GridBagConstraints gbc_lblNameFrame = new GridBagConstraints();
		gbc_lblNameFrame.gridwidth = 2;
		gbc_lblNameFrame.insets = new Insets(0, 0, 5, 0);
		gbc_lblNameFrame.gridx = 0;
		gbc_lblNameFrame.gridy = 0;
		getContentPane().add(lblbaisicText, gbc_lblNameFrame);

		JLabel lblNuclide = new JLabel(nuclideText);
		GridBagConstraints gbc_lblNuclide = new GridBagConstraints();
		gbc_lblNuclide.insets = new Insets(0, 0, 5, 5);
		gbc_lblNuclide.gridx = 0;
		gbc_lblNuclide.gridy = 1;
		getContentPane().add(lblNuclide, gbc_lblNuclide);

		JLabel lblNukcideVolume = new JLabel();
		GridBagConstraints gbc_lblNukcideVolume = new GridBagConstraints();
		gbc_lblNukcideVolume.insets = new Insets(0, 0, 5, 0);
		gbc_lblNukcideVolume.gridx = 1;
		gbc_lblNukcideVolume.gridy = 1;
		getContentPane().add(lblNukcideVolume, gbc_lblNukcideVolume);

		JLabel lblDobiv = new JLabel(dobivText);
		GridBagConstraints gbc_lblDobiv = new GridBagConstraints();
		gbc_lblDobiv.insets = new Insets(0, 0, 5, 5);
		gbc_lblDobiv.gridx = 0;
		gbc_lblDobiv.gridy = 2;
		getContentPane().add(lblDobiv, gbc_lblDobiv);

		JLabel lblDobivVolume = new JLabel();
		GridBagConstraints gbc_lblDobivVolume = new GridBagConstraints();
		gbc_lblDobivVolume.insets = new Insets(0, 0, 5, 0);
		gbc_lblDobivVolume.gridx = 1;
		gbc_lblDobivVolume.gridy = 2;
		getContentPane().add(lblDobivVolume, gbc_lblDobivVolume);
		
				JComboBox<String> comboBoxNameDobivs = new JComboBox<>();
				GridBagConstraints gbc_comboBoxNameDobivs = new GridBagConstraints();
				gbc_comboBoxNameDobivs.gridwidth = 2;
				gbc_comboBoxNameDobivs.insets = new Insets(0, 0, 5, 0);
				gbc_comboBoxNameDobivs.fill = GridBagConstraints.BOTH;
				gbc_comboBoxNameDobivs.gridx = 0;
				gbc_comboBoxNameDobivs.gridy = 3;
				getContentPane().add(comboBoxNameDobivs, gbc_comboBoxNameDobivs);
				
				setDobivToComboBox(listAllDobivs , comboBoxNameDobivs);
				if(incomingDobiv!=null){
				setFirstDobivToComboBox(incomingDobiv,  comboBoxNameDobivs, 
						   lblNukcideVolume, lblDobivVolume);
				}
				comboBoxNameDobivs.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						selectDobiv = getDobivFromComboBox(comboBoxNameDobivs.getSelectedItem());
						lblDobivVolume.setText(selectDobiv.getValue_result().toString());
						lblNukcideVolume.setText(selectDobiv.getNuclide().getSymbol_nuclide());
						
					}

					
				});

		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 4;
		getContentPane().add(btnCancel, gbc_btnCancel);

		JButton btnOK = new JButton("OK");
		GridBagConstraints gbc_btnOK = new GridBagConstraints();
		gbc_btnOK.anchor = GridBagConstraints.SOUTHWEST;
		gbc_btnOK.gridx = 1;
		gbc_btnOK.gridy = 4;
		getContentPane().add(btnOK, gbc_btnOK);
		
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectDobiv = incomingDobiv;
				
				dispose();
			}
		});

		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				dispose();
			}
		});
		
		addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	            	selectDobiv = incomingDobiv; 
	            }
	        });

		setVisible(true);
		repaint();
		
	}

	@SuppressWarnings("unused")
	private String setDobivVolumeFromDobiv(Dobiv dobiv) {
		return dobiv.getValue_result().toString();
	}

	@SuppressWarnings("unused")
	private String setDobivNuclideFromDobiv(Dobiv dobiv) {
		return dobiv.getNuclide().getSymbol_nuclide();
	}

	public Dobiv getSelectDobiv (){
		return selectDobiv;
	}
	
	private void setDobivToComboBox(List<Dobiv> listname, JComboBox<String> comboBoxNameDobivs) {
		
		for (Dobiv dobiv : listname) {
			comboBoxNameDobivs.addItem(dobiv.getCode_Standart());
			}
		}
	
   private void setFirstDobivToComboBox(Dobiv dobiv, JComboBox<String> comboBoxNameDobivs, 
		   JLabel lblNukcideVolume,JLabel lblDobivVolume) {
	   comboBoxNameDobivs.setSelectedItem(dobiv.getCode_Standart());
	   lblNukcideVolume.setText(dobiv.getNuclide().getSymbol_nuclide());
	   lblDobivVolume.setText(dobiv.getValue_result().toString());
		}
	
	
	private Dobiv getDobivFromComboBox(Object object) {
	
		for (Dobiv dobiv : listAllDobivs) {
			if(dobiv.getCode_Standart().equals(object)){
				return dobiv;
			}
		}
		
		return null;
		
	}

	
	
}
