package AddDobivViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import Aplication.DobivDAO;
import Aplication.UsersDAO;
import WindowView.AddDobivView_;


public class CodeDobivSection {
	
	public static void txtDobivCodeListener(AddDobivView_ addDobivView, JLabel lblError, JTextField txtStandartCode, JTextField textFieldDobivDescrip, 
			Choice choiceIzpitProd, Choice choiceOIR, Choice choiceORHO, Choice choiceMetody) {
		txtStandartCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtStandartCode.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				txtStandartCode.setBackground(Color.WHITE);
			}

		});
		txtStandartCode.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent event) {
				txtStandartCode.setBackground(Color.WHITE);
			}

			@Override
			public void keyReleased(KeyEvent event) {
				txtStandartCode.setBackground(Color.WHITE);
				OverallVariablesAddDobiv.setListChoisedDobiv ( DobivDAO.getList_DobivByCode_Standart(txtStandartCode.getText()));

				if (OverallVariablesAddDobiv.getListChoisedDobiv().size() == 0) {
					txtStandartCode.setForeground(Color.red);
					lblError.setVisible(true);
					lblError.setText("Добиви с този код не съществуват");
					OverallVariablesAddDobiv.setCorectStandartCode(true);
					

				} else {
					txtStandartCode.setForeground(Color.BLACK);
					txtStandartCode.setBorder(new LineBorder(Color.BLACK));
					// txtStandartCode.setEditable(false);
					lblError.setVisible(true);
					lblError.setText("Добиви с този код съществуват");
					textFieldDobivDescrip.setText(OverallVariablesAddDobiv.getListChoisedDobiv().get(0).getDescription());
					choiceIzpitProd.select(OverallVariablesAddDobiv.getListChoisedDobiv().get(0).getIzpitvan_produkt().getName_zpitvan_produkt());
					choiceOIR.select(UsersDAO.getStringName_FamilyUser(OverallVariablesAddDobiv.getListChoisedDobiv().get(0).getUser_measur()));
					choiceORHO.select(UsersDAO.getStringName_FamilyUser(OverallVariablesAddDobiv.getListChoisedDobiv().get(0).getUser_chim_oper()));
					choiceMetody.select(OverallVariablesAddDobiv.getListChoisedDobiv().get(0).getMetody().getCode_metody());
					OverallVariablesAddDobiv.setCorectStandartCode(false);
					

				}
				addDobivView.validate();
				addDobivView.repaint();

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
	
		});
	}

}
