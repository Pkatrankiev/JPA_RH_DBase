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
import WindowView.AddResultsView;


	public class ButtonPanellListener {


	public static void saveButtonListener(AddResultsView addResultsViewWithTable, JPanel basic_panel, JButton saveButton,JTextField txtRqstCode, Choice choicePokazatel, Choice choiceMetody,
			Choice choiceOIR, Choice choiceORHO, Choice choiceDobiv, Choice choiceSmplCode, JTextField txtBasicValueResult) {
		saveButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (checkDataResult(txtRqstCode, choicePokazatel, choiceMetody,
						choiceOIR,choiceORHO,  choiceDobiv)) {
					updateIzpitvanPokazatelObjectInDBase(choicePokazatel, choiceMetody);
					AddresultViewMetods.setWaitCursor(basic_panel);

					Sample samp = SampleCodeSection.getSampleObjectFromChoiceSampleCode(choiceSmplCode);
					OverallVariables.setListResultsFromDBase ( creadListResultsObjects_ChoiseSample(samp,  choicePokazatel));
					OverallVariables.setResultListForDelete ( AddresultViewMetods.creadResultListForDelete(samp));
					OverallVariables.setResultListForSave ( AddresultViewMetods.creadResultListForSave( samp, txtBasicValueResult,
							 choiceMetody,  choicePokazatel,  choiceORHO,  choiceOIR,  choiceDobiv));

					AddresultViewMetods.setDefaultCursor(basic_panel);

					new MesejePanelInAddResultsFuncion(OverallVariables.getResultListForSave(), OverallVariables.getResultListForDelete());
					int k = MesejePanelInAddResultsFuncion.getResultMeseje();

					if (k == 0) {
						AddresultViewMetods.setWaitCursor(basic_panel);
						for (Results results : OverallVariables.getResultListForSave()) {
							int idresultInBase = existsNuclideInResultTOResultBase(OverallVariables.getListResultsFromDBase(), results);
							if (idresultInBase != 0) {
								results.setId_results(idresultInBase);
								ResultsDAO.updateResults(results);
							} else {
								ResultsDAO.setValueResults(results);
							}
						}
						for (Results results : OverallVariables.getResultListForDelete()) {
							ResultsDAO.deleteResultsById(results.getId_results());
						}

						OverallVariables.setListSimbolBasikNulide ( AddresultViewMetods.getListSimbolBasikNulideFNuclideToPokazatel(OverallVariables.getListNucToPok()));
						Results[] masiveResultsForChoiceSample = AddresultViewMetods.creadMasiveFromResultsObjects_ChoiseSample(
								SampleCodeSection.getSampleObjectFromChoiceSampleCode(choiceSmplCode),  choicePokazatel);
						startViewtablePanel(addResultsViewWithTable,basic_panel,  masiveResultsForChoiceSample);
						AddresultViewMetods.setDefaultCursor(basic_panel);
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

	static void startViewtablePanel(AddResultsView addResultsViewWithTable, JPanel basic_panel, Results[] masiveResultsForChoiceSample) {
		Object[][] ss = AddresultViewMetods.getDataTable(masiveResultsForChoiceSample, OverallVariables.getListSimbolBasikNulide());
		OverallVariables.setFromDBase(true);
		AddresultViewMetods.createDataTableAndViewTableInPanel( addResultsViewWithTable ,basic_panel, ss);
	}
	
	static List<Results> creadListResultsObjects_ChoiseSample(Sample sample,Choice choicePokazatel) {
		List<Results> ListResultsFromSample = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
		List<Results> choiceResults = new ArrayList<Results>();
		List_izpitvan_pokazatel pokazatel = AddresultViewMetods.getPokazatelObjectFromChoicePokazatel(choicePokazatel);
		for (Results result : ListResultsFromSample) {
			if (result.getPokazatel().getId_pokazatel() == pokazatel.getId_pokazatel()
					&& result.getMetody().getId_metody() == OverallVariables.getSelectedMetod().getId_metody()) {
				choiceResults.add(result);
			}
		}

		return choiceResults;
	}

	
	static void updateIzpitvanPokazatelObjectInDBase(Choice choicePokazatel, Choice choiceMetody) {
		IzpitvanPokazatel izpivanPokazatel = IzpitvanPokazatelDAO
				.getIzpitvan_pokazatelObjectByRequestAndListIzpitvanPokazatel(OverallVariables.getChoiseRequest(),
						AddresultViewMetods.getPokazatelObjectFromChoicePokazatel(choicePokazatel));
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
			str_Error = str_Error + "код на заявката" + "\n";
			saveCheck = false;
		}

		if (choicePokazatel.getSelectedItem().trim().isEmpty()) {
			choicePokazatel.setBackground(Color.RED);
			str_Error = str_Error + "изпитван показател" + "\n";
			saveCheck = false;
		}

		if (choiceMetody.getSelectedItem().trim().isEmpty()) {
			choiceMetody.setBackground(Color.RED);
			str_Error = str_Error + "метод" + "\n";

			saveCheck = false;
		}

		if (choiceOIR.getSelectedItem().trim().isEmpty()) {
			choiceOIR.setBackground(Color.RED);
			str_Error = str_Error + "извършил анализа" + "\n";
			saveCheck = false;
		}

		if (!OverallVariables.getListSimbolBasikNulideToMetod().isEmpty() && choiceORHO.getSelectedItem().trim().isEmpty()) {
			choiceORHO.setBackground(Color.RED);
			str_Error = str_Error + "изв. хим. обработка" + "\n";
			saveCheck = false;
		}
		System.out.println(choiceDobiv.getSelectedItem().trim().isEmpty());
		System.out.println( OverallVariables.getListSimbolBasikNulideToMetod().isEmpty());

		if (!OverallVariables.getListSimbolBasikNulideToMetod().isEmpty() && choiceDobiv.getSelectedItem().trim().isEmpty()) {
			choiceDobiv.setBackground(Color.RED);
			str_Error = str_Error + "добив" + "\n";
			saveCheck = false;
		}

		if (!AddresultViewMetods.strCurrentDataInDataTable(OverallVariables.getDataTable()).trim().isEmpty()) {
			str_Error = str_Error + AddresultViewMetods.strCurrentDataInDataTable(OverallVariables.getDataTable());
			System.out.println(str_Error);
			saveCheck = false;
		}

		if (!saveCheck) {
			JOptionPane.showMessageDialog(null, str_Error, "Грешни данни за следните полета:",
					JOptionPane.ERROR_MESSAGE);
		}

		return saveCheck;
	}

	
}
