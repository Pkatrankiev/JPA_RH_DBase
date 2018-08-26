package Table;

import java.util.List;

import Aplication.IzpitvanPokazatelDAO;
import Aplication.RequestDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Request;
import OldClases.TableRequestListNew;
import WindowView.RequestViewAplication;

public class TableRequestTamplate {
	public static void DrawTableWithRequestTamplate() {
		List<IzpitvanPokazatel> list_All_I_P = IzpitvanPokazatelDAO.getInListAllValueIzpitvan_pokazatel();
		List<Request> listTamplateRequest = RequestDAO.getListRequestFromColumnByContainsString("recuest_code",
				"templ");

		String[] tableHeader = { "№ на Заявката", "Ид.№ на документа", "Изпитван продукт", "Обект на изпитване",
				"     Показател     ", "Размерност" };
		Class[] types = { String.class, Integer.class, String.class, String.class, String.class, String.class };
		String[][] tabletamplateRequest = new String[listTamplateRequest.size()][6];
		int i = 0;
		for (Request tamplateRequest : listTamplateRequest) {
			tabletamplateRequest[i][0] = tamplateRequest.getRecuest_code();
			tabletamplateRequest[i][1] = tamplateRequest.getInd_num_doc().getName();
			tabletamplateRequest[i][2] = tamplateRequest.getIzpitvan_produkt().getName_zpitvan_produkt();
			tabletamplateRequest[i][3] = tamplateRequest.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();
			tabletamplateRequest[i][4] = RequestViewAplication.CreateStringListIzpPokaz(tamplateRequest, list_All_I_P);
			tabletamplateRequest[i][5] = tamplateRequest.getRazmernosti().getName_razmernosti();

			i++;
		}

		TableRequestList.TableRequestList(tableHeader, tabletamplateRequest, types);
		
	}
}
