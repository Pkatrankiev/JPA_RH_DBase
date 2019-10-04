package AddDobivViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Aplication.DobivDAO;
import DBase_Class.Dobiv;
import WindowView.AddDobivView_;

public class btnPaneAddDobivSection {
	
	public static void saveButtonListener(JButton okButton, AddDobivView_ addDobivView, Choice choiceOIR, Choice choiceORHO, JTextField txtBasicValueResult,
	 Choice choiceIzpitProd, JTextField txtStandartCode, Choice choiceMetody, JTextField textFieldDobivDescrip, JLabel lblNameMetod){
	okButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			List<Dobiv> listDobivsForSave = AddDobivViewMetods.creadListFromDobivObjectForSave( addDobivView,  choiceOIR,  choiceORHO,  txtBasicValueResult,
					  choiceIzpitProd,  txtStandartCode,  choiceMetody,  textFieldDobivDescrip,  lblNameMetod);
			for (Dobiv dobiv : listDobivsForSave) {
				saveDobivsObjectInDBase(dobiv);
			}

		}

	});
	}
	private static void saveDobivsObjectInDBase(Dobiv dobiv) {

		DobivDAO.updateDobiv(dobiv);
		;
	}
}
