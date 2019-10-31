package AddDobivViewFunction;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import AddResultViewFunction.AddResultViewMetods;
import AddResultViewFunction.MesejePanelInAddResultsFuncion;

import Aplication.DobivDAO;
import DBase_Class.Dobiv;
import WindowView.AddDobivView;

public class btnPaneAddDobivSection {
	
	public static void saveButtonListener(JPanel basic_panel, JButton okButton, AddDobivView addDobivView, Choice choiceOIR, Choice choiceORHO, JTextField txtBasicValueResult,
	 Choice choiceIzpitProd, JTextField txtStandartCode, Choice choiceMetody, JTextField textFieldDobivDescrip, JLabel lblNameMetod){
	okButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			if (AddDobivViewMetods.checkDobiv(addDobivView, txtStandartCode, lblNameMetod, choiceIzpitProd, choiceOIR, choiceORHO, choiceMetody)) {
				AddResultViewMetods.setWaitCursor(basic_panel);
				List<Dobiv> ListFromDobivObjectForSave = AddDobivViewMetods.creadListDobivObjectForSave( addDobivView,  choiceOIR,  choiceORHO,  txtBasicValueResult,
						  choiceIzpitProd,  txtStandartCode,  choiceMetody,  textFieldDobivDescrip,  lblNameMetod);
				List<Dobiv> ListDobivObjectForDelete = AddDobivViewMetods.creadListDobivObjectForDelete();
				 
				AddResultViewMetods.setDefaultCursor(basic_panel);
				
				MesejePanelInAddResultsFuncion.MesejePanelInAddDobivFuncion(ListFromDobivObjectForSave, ListDobivObjectForDelete);
				int k = MesejePanelInAddResultsFuncion.getResultMeseje();

				if (k == 0) {
					AddResultViewMetods.setWaitCursor(basic_panel);
					for (Dobiv dobiv : ListFromDobivObjectForSave) {
						int idDobivWithExixtStandartCodeInBase = ExixtStandartCodeInBase(OverallVariablesAddDobiv.getListStandartCodeAllDobiv(), dobiv);
						if (idDobivWithExixtStandartCodeInBase != 0) {
							dobiv.setId_dobiv(idDobivWithExixtStandartCodeInBase);
							DobivDAO.updateDobiv(dobiv);
						} else {
							DobivDAO.setValueDobiv(dobiv);;
						}
					}
					for (Dobiv dob :ListDobivObjectForDelete ) {
						DobivDAO.deleteDobivById(dob.getId_dobiv());
					}

					btnDataFromDBaseAddDobiv_Section.viewTableDobivFromDBaseByStandartCode( addDobivView,  basic_panel,
							 choiceOIR,  choiceORHO,  txtStandartCode);
					AddResultViewMetods.setDefaultCursor(basic_panel);
				}
			}
			
		

		}

	});
	}
		
	public static int ExixtStandartCodeInBase(List<String> listStandartCodeAllDobiv, Dobiv results) {
		int fl = 0;
		for (String str : listStandartCodeAllDobiv) {
			if (str.equals(results.getCode_Standart())) {
				return fl = DobivDAO.getList_DobivByCode_Standart(str).get(0).getId_dobiv();
			}
		}
		return fl;
	}
	
}
