package AddDobivViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Aplication.DobivDAO;
import DBase_Class.Dobiv;
import WindowView.AddDobivView_;
import WindowView.TranscluentWindow;

	public class btnDataFromDBaseAddDobiv_Section {

	public static void btnDataFromDBaseListener(AddDobivView_ addDobivView, JPanel panel, JButton btnDataFromDBase,  Choice choiceMetody, Choice choiceOIR, 
			Choice choiceORHO, JTextField txtStandartCode) {
		
		btnDataFromDBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!choiceMetody.getSelectedItem().trim().isEmpty()) {
					
					List<Dobiv> ListDobivsFromStandart_code = DobivDAO.getListResultsFromColumnByVolume("code_Standart",
							txtStandartCode.getText());
					Dobiv[] masiveDobivForMetod = AddDobivViewMetods.creadMasiveFromDobivsObjects_StandartCode(OverallVariablesAddDobiv.getSelectedMetod(),
							ListDobivsFromStandart_code);

					if (masiveDobivForMetod.length > 0) {
						if (masiveDobivForMetod[0] != null) {
						if (masiveDobivForMetod[0].getUser_measur() != null) {
							String str = masiveDobivForMetod[0].getUser_measur().getName_users() + " "
									+ masiveDobivForMetod[0].getUser_measur().getFamily_users();
							choiceOIR.select(str);
						}
						if (masiveDobivForMetod[0].getUser_chim_oper() != null) {
							String str = masiveDobivForMetod[0].getUser_chim_oper().getName_users() + " "
									+ masiveDobivForMetod[0].getUser_chim_oper().getFamily_users();
							choiceORHO.select(str);
						}
					}
					}
					TranscluentWindow round = new TranscluentWindow();
					final Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {
							OverallVariablesAddDobiv.setFromDBase(true);
							AddDobivViewMetods.startViewtablePanel( addDobivView, masiveDobivForMetod, panel, round);
						}
					});
					thread.start();
					
					
					

					

				}
			}

		});
	}

	

	
	

}
