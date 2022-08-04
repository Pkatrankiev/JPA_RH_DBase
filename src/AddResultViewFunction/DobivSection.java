package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import AddDobivViewFunction.OverallVariablesAddDobiv;
import Aplication.DobivDAO;
import DBase_Class.Dobiv;
import DBase_Class.Metody;
import WindowView.AddDobivView;
import WindowView.Login;
import WindowView.TranscluentWindow;

public class DobivSection {

	
	public static  void choiceDobivListener(Choice choiceDobiv, JLabel lbl_StoinostiFromDobiv, JButton btnAddDobiv) {
	
		btnAddDobiv.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				TranscluentWindow round = new TranscluentWindow();
				
				 final Thread thread = new Thread(new Runnable() {
				     @Override
				     public void run() {
				    	 
				    	 JFrame f = new JFrame();
				 		new AddDobivView(f,round, Login.getCurentUser());
				 			    	
				     }
				    });
				    thread.start();
				
			}
		
		
		 });
		
		
		choiceDobiv.addItemListener(new ItemListener() {
		
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (choiceDobiv.getSelectedItem() == null)
					setValueInChoiceDobiv(OverallVariablesAddResults.getSelectedMetod(), choiceDobiv,lbl_StoinostiFromDobiv, false);
				lbl_StoinostiFromDobiv
						.setText(generate_strStoinostiDobiv_Nuclide(choiceDobiv));

			}

		});

		choiceDobiv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				choiceDobiv.setBackground(Color.WHITE);
				if (choiceDobiv.getSelectedItem().trim().isEmpty()) {
					setValueInChoiceDobiv(OverallVariablesAddResults.getSelectedMetod(), choiceDobiv, lbl_StoinostiFromDobiv, false);
				lbl_StoinostiFromDobiv
						.setText(generate_strStoinostiDobiv_Nuclide(choiceDobiv));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
				if (choiceDobiv.getSelectedItem().trim().isEmpty()) {
					setValueInChoiceDobiv(OverallVariablesAddResults.getSelectedMetod(), choiceDobiv,lbl_StoinostiFromDobiv, false);
				lbl_StoinostiFromDobiv
						.setText(generate_strStoinostiDobiv_Nuclide(choiceDobiv));
				}  
			}

		});
	}
	
	static void setValueInChoiceDobiv(Metody selectedMetod, Choice choiceDobiv, JLabel lbl_StoinostiFromDobiv, Boolean clearItems) {
		if (clearItems || choiceDobiv.getSelectedItem().trim().isEmpty()) {
			
		choiceDobiv.removeAll();
		choiceDobiv.addItem("");
		
		OverallVariablesAddResults.setListDobivFromMetod ( DobivDAO.getListDobivByMetody(selectedMetod));
		for (Dobiv str : OverallVariablesAddResults.getListDobivFromMetod()) {
			choiceDobiv.addItem(str.getCode_Standart());
		}
		lbl_StoinostiFromDobiv
		.setText(generate_strStoinostiDobiv_Nuclide(choiceDobiv));	
		}
	}
	
	static void setValueInChoiceDobivFromORTECFile(Metody selectedMetod, Choice choiceDobiv, JLabel lbl_StoinostiFromDobiv) {
					
		choiceDobiv.removeAll();
//		choiceDobiv.addItem("");
		String selectItemStr = OverallVariablesAddDobiv.getListChoisedDobiv().get(0).getCode_Standart();
		choiceDobiv.addItem(selectItemStr);
		OverallVariablesAddResults.setListDobivFromMetod ( DobivDAO.getListDobivByMetody(selectedMetod));
		for (Dobiv str : OverallVariablesAddResults.getListDobivFromMetod()) {
			choiceDobiv.addItem(str.getCode_Standart());
		}
		choiceDobiv.select(selectItemStr);
		lbl_StoinostiFromDobiv.setText(generate_strStoinostiDobiv_Nuclide(choiceDobiv));	

	}
	
	static String generate_strStoinostiDobiv_Nuclide(Choice choiceDobiv) {
		String strStoinostiDobiv_Nuclide = "";
		String strrazmernostDobiv = "% , ";
		
		if (!choiceDobiv.getSelectedItem().trim().isEmpty()) {
			double valueDobiv = 0.0;
			List<Dobiv> listDobivi = DobivDAO.getList_DobivByCode_Standart(choiceDobiv.getSelectedItem());
			if(listDobivi.size()>0 && !listDobivi.get(0).getMetody().getCode().contains("Ì.ËÈ-ÐÕ-03")){
				strrazmernostDobiv ="÷.å. , ";
			}
			for (Dobiv dobiv : listDobivi) {
				valueDobiv = dobiv.getValue_result();
				if(!listDobivi.get(0).getMetody().getCode().contains("Ì.ËÈ-ÐÕ-03") && valueDobiv > 1 ){
					valueDobiv = valueDobiv/100;
				}
				strStoinostiDobiv_Nuclide += dobiv.getNuclide().getSymbol_nuclide() + " " + valueDobiv
						+ strrazmernostDobiv;
			}
			if(strStoinostiDobiv_Nuclide.trim().isEmpty()){
				 Dobiv dobiv = OverallVariablesAddDobiv.getListChoisedDobiv().get(0);
				 if(!dobiv.getMetody().getCode().contains("Ì.ËÈ-ÐÕ-03")){
						strrazmernostDobiv ="÷.å. , ";
					}
				 valueDobiv = dobiv.getValue_result();
					if(!dobiv.getMetody().getCode().contains("Ì.ËÈ-ÐÕ-03") && valueDobiv > 1 ){
						valueDobiv = valueDobiv/100;
					}
				 strStoinostiDobiv_Nuclide += dobiv.getNuclide().getSymbol_nuclide() + " " + valueDobiv
					+ strrazmernostDobiv;
			}
			
			if (!strStoinostiDobiv_Nuclide.isEmpty())
				strStoinostiDobiv_Nuclide = strStoinostiDobiv_Nuclide.substring(0,
						strStoinostiDobiv_Nuclide.length() - 2);
		}
		return strStoinostiDobiv_Nuclide;
	}
	
	

}
