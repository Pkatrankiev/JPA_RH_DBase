package WindowViewAplication;

import java.util.List;

import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_pokazatelDAO;
import DBase_Class.Izpitvan_pokazatel;
import DBase_Class.Request;
import DBase_Class.Sample;

class CreateNewTableIzpitvanPokazatel {

	public static void CreateNewTableIzpitvanPokazatel() {
		List<Izpitvan_pokazatel> list_izpit_pokazat = Izpitvan_pokazatelDAO.getInListAllValueIzpitvan_pokazatel();

		Request request0 = null;
		for (Izpitvan_pokazatel izpitvan_pokazatel : list_izpit_pokazat) {
			Sample sample = izpitvan_pokazatel.getSample();
			List<Izpitvan_pokazatel> listIzpPokaz = Izpitvan_pokazatelDAO
					.getListIzpitvan_pokazatelFromColumnByVolume("sample", sample);

			Request request = sample.getRequest();

			if (request0 != request) {
				request0 = request;
				if (listIzpPokaz.size() > 1){
					for (Izpitvan_pokazatel izp_pokazatel : listIzpPokaz) {
						IzpitvanPokazatelDAO.setValueIzpitvanPokazatel(izp_pokazatel.getPokazatel(), request,
								izp_pokazatel.getMetody());
					}
				}else
				IzpitvanPokazatelDAO.setValueIzpitvanPokazatel(izpitvan_pokazatel.getPokazatel(), request,
						izpitvan_pokazatel.getMetody());
			}

		}
	}

}
