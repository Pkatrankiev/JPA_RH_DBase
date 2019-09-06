package AddResultViewFunction;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.ResultsDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Results;
import DBase_Class.Sample;
import WindowView.AddResultsViewWithTable;
import WindowView.MesejePanel;


public class ButtonPanellListener {

	@SuppressWarnings("unused")
	public static void saveButtonListener(JPanel basic_panel, JPanel panel, JButton saveButton,JTextField txtRqstCode, Choice choicePokazatel, Choice choiceMetody,
			Choice choiceOIR, Choice choiceORHO, Choice choiceDobiv, Choice choiceSmplCode, JTextField txtBasicValueResult) {
		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (checkDataResult(txtRqstCode, choicePokazatel, choiceMetody,
						choiceOIR,choiceORHO,  choiceDobiv)) {
					updateIzpitvanPokazatelObjectInDBase(choicePokazatel, choiceMetody);
					AddresultViewMwetods.setWaitCursor(panel);

					Sample samp = SampleCodeSection.getSampleObjectFromChoiceSampleCode(choiceSmplCode);
					ŒverallVariables.setListResultsFromDBase ( creadListResultsObjects_ChoiseSample(samp,  choicePokazatel));
					ŒverallVariables.setResultListForDelete ( AddresultViewMwetods.creadResultListForDelete(samp));
					ŒverallVariables.setResultListForSave ( AddresultViewMwetods.creadResultListForSave( samp, txtBasicValueResult,
							 choiceMetody,  choicePokazatel,  choiceORHO,  choiceOIR,  choiceDobiv));

					AddresultViewMwetods.setDefaultCursor(panel);

					new MesejePanel(ŒverallVariables.getResultListForSave(), ŒverallVariables.getResultListForDelete());
					int k = MesejePanel.getResultMeseje();

					if (k == 0) {
						AddresultViewMwetods.setWaitCursor(panel);
						for (Results results : ŒverallVariables.getResultListForSave()) {
							int idresultInBase = existsNuclideInResultTOResultBase(ŒverallVariables.getListResultsFromDBase(), results);
							if (idresultInBase != 0) {
								results.setId_results(idresultInBase);
								ResultsDAO.updateResults(results);
							} else {
								ResultsDAO.setValueResults(results);
							}
						}
						for (Results results : ŒverallVariables.getResultListForDelete()) {
							ResultsDAO.deleteResultsById(results.getId_results());
						}

						ŒverallVariables.setListSimbolBasikNulide ( AddresultViewMwetods.getListSimbolBasikNulideFNuclideToPokazatel(ŒverallVariables.getListNucToPok()));
						Results[] masiveResultsForChoiceSample = AddresultViewMwetods.creadMasiveFromResultsObjects_ChoiseSample(
								SampleCodeSection.getSampleObjectFromChoiceSampleCode(choiceSmplCode),  choicePokazatel);
						startViewtablePanel(basic_panel, panel, masiveResultsForChoiceSample);
						AddresultViewMwetods.setDefaultCursor(panel);
					}

				}

			}

		});
	}
	
	static int existsNuclideInResultTOResultBase(List<Results> ListResultsFromDBase, Results results) {
		int fl = 0;
		for (Results res : ListResultsFromDBase) {
			String codeNulide = res.getNuclide().getSymbol_nuclide();
			if (codeNulide.equals(results.getNuclide().getSymbol_nuclide())) {
				return fl = res.getId_results();
			}
		}
		return fl;
	}

	static void startViewtablePanel(JPanel basic_panel, JPanel panel, Results[] masiveResultsForChoiceSample) {
		Object[][] ss = AddresultViewMwetods.getDataTable(masiveResultsForChoiceSample, ŒverallVariables.getListSimbolBasikNulide());
		AddresultViewMwetods.createDataTableAndViewTableInPanel(basic_panel, ss);
	}

	
	
	static List<Results> creadListResultsObjects_ChoiseSample(Sample sample,Choice choicePokazatel) {
		List<Results> ListResultsFromSample = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
		List<Results> choiceResults = new ArrayList<Results>();
		List_izpitvan_pokazatel pokazatel = AddresultViewMwetods.getPokazatelObjectFromChoicePokazatel(choicePokazatel);
		for (Results result : ListResultsFromSample) {
			if (result.getPokazatel().getId_pokazatel() == pokazatel.getId_pokazatel()
					&& result.getMetody().getId_metody() == ŒverallVariables.getSelectedMetod().getId_metody()) {
				choiceResults.add(result);
			}
		}

		return choiceResults;
	}

	
	static void updateIzpitvanPokazatelObjectInDBase(Choice choicePokazatel, Choice choiceMetody) {
		IzpitvanPokazatel izpivanPokazatel = IzpitvanPokazatelDAO
				.getIzpitvan_pokazatelObjectByRequestAndListIzpitvanPokazatel(ŒverallVariables.getChoiseRequest(),
						AddresultViewMwetods.getPokazatelObjectFromChoicePokazatel(choicePokazatel));
		Metody mm = MetodyDAO.getValueList_MetodyByCode(choiceMetody.getSelectedItem());
		izpivanPokazatel.getId_pokazatel();
		izpivanPokazatel.setMetody(mm);
		IzpitvanPokazatelDAO.updateIzpitvanPokazatel(izpivanPokazatel);
	}

	
	static Boolean checkDataResult( JTextField txtRqstCode, Choice choicePokazatel, Choice choiceMetody,
			Choice choiceOIR, Choice choiceORHO, Choice choiceDobiv) {
		Boolean saveCheck = true;
		String str_Error = "";

		if (txtRqstCode.getText().trim().isEmpty()) {
			txtRqstCode.setBackground(Color.RED);
			str_Error = str_Error + "ÍÓ‰ Ì‡ Á‡ˇ‚Í‡Ú‡" + "\n";
			saveCheck = false;
		}

		if (choicePokazatel.getSelectedItem().trim().isEmpty()) {
			choicePokazatel.setBackground(Color.RED);
			str_Error = str_Error + "ËÁÔËÚ‚‡Ì ÔÓÍ‡Á‡ÚÂÎ" + "\n";
			saveCheck = false;
		}

		if (choiceMetody.getSelectedItem().trim().isEmpty()) {
			choiceMetody.setBackground(Color.RED);
			str_Error = str_Error + "ÏÂÚÓ‰" + "\n";

			saveCheck = false;
		}

		if (choiceOIR.getSelectedItem().trim().isEmpty()) {
			choiceOIR.setBackground(Color.RED);
			str_Error = str_Error + "ËÁ‚˙¯ËÎ ‡Ì‡ÎËÁ‡" + "\n";
			saveCheck = false;
		}

		if (!ŒverallVariables.getListSimbolBasikNulideToMetod().isEmpty() && choiceORHO.getSelectedItem().trim().isEmpty()) {
			choiceORHO.setBackground(Color.RED);
			str_Error = str_Error + "ËÁ‚. ıËÏ. Ó·‡·ÓÚÍ‡" + "\n";
			saveCheck = false;
		}

		if (!ŒverallVariables.getListSimbolBasikNulideToMetod().isEmpty() && choiceDobiv.getSelectedItem().trim().isEmpty()) {
			choiceDobiv.setBackground(Color.RED);
			str_Error = str_Error + "‰Ó·Ë‚" + "\n";
			saveCheck = false;
		}

		if (!AddresultViewMwetods.strCurrentDataInDataTable(ŒverallVariables.getDataTable()).trim().isEmpty()) {
			str_Error = str_Error + AddresultViewMwetods.strCurrentDataInDataTable(ŒverallVariables.getDataTable());
			System.out.println(str_Error);
			saveCheck = false;
		}

		if (!saveCheck) {
			JOptionPane.showMessageDialog(null, str_Error, "√Â¯ÌË ‰‡ÌÌË Á‡ ÒÎÂ‰ÌËÚÂ ÔÓÎÂÚ‡:",
					JOptionPane.ERROR_MESSAGE);
		}

		return saveCheck;
	}

	
}
