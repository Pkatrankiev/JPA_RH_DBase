package WindowView;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Aplication.IzpitvanPokazatelDAO;
import Aplication.RequestDAO;
import Aplication.Request_To_ObektNaIzpitvaneRequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import DBase_Class.Request;
import DBase_Class.Request_To_ObektNaIzpitvaneRequest;
import DBase_Class.Results;
import DBase_Class.Sample;

public class DeleteRequestFromDBase {

	public static void Delete_RequestFromDBase(Request request, TranscluentWindow round) {
		round.StopWindow();
		if (listResultsByCodeRequest(request).size() > 0) {
			JOptionPane.showMessageDialog(null, "Не може да се изрие заявката.\nИма въведени резултати за нея.");
		} else {
			int result = JOptionPane.showConfirmDialog(null,
					"Ще изтриете ли заявка с код: " + request.getRecuest_code() + "? ", null,
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				DeleteAllObjectsConnectedByRequest(request, false);
			}
		}
	}

	static void DeleteAllObjectsConnectedByRequest(Request request, boolean biRequestView) {
		if(biRequestView){
		for (Results resultDBase : ResultsDAO.getListResultsFromColumnByVolume("request", request)) {
			ResultsDAO.deleteResultsById(resultDBase.getId_results());
		}
		}
		IzpitvanPokazatelDAO.deleteIzpitvanPokazatelByRequest(request);
		for (Sample sampleDBase : SampleDAO.getListSampleFromColumnByVolume("request", request)) {
			SampleDAO.deleteSample(sampleDBase);
		}
		for (Request_To_ObektNaIzpitvaneRequest requestToObektNaIzpReqst : Request_To_ObektNaIzpitvaneRequestDAO
				.getRequest_To_ObektNaIzpitvaneRequestByRequest(request)) {
			Request_To_ObektNaIzpitvaneRequestDAO.deleteRequest_To_ObektNaIzpitvaneRequest(requestToObektNaIzpReqst);
		}
		if(!biRequestView){
		RequestDAO.DeleteRequest(request);
		}
	}

	public static List<Results> listResultsByCodeRequest(Request request) {
		List<Results> listResults = new ArrayList<>();
		for (Sample sampleDBase : SampleDAO.getListSampleFromColumnByVolume("request", request)) {
			for (Results result : ResultsDAO.getListResultsFromColumnByVolume("sample", sampleDBase)) {
				listResults.add(result);
			}

		}
		return listResults;
	}
}
