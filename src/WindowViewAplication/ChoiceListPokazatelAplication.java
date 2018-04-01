package WindowViewAplication;

import java.util.List;

import Aplication.List_izpitvan_pokazatelDAO;
import DBase_Class.List_izpitvan_pokazatel;

public class ChoiceListPokazatelAplication {

	public static String[] getStringMassiveLIP() {
		int i = 0;
		List<List_izpitvan_pokazatel> list = List_izpitvan_pokazatelDAO.getInListAllValuePokazatel();
		String[] arr = new String[list.size()];
		for (List_izpitvan_pokazatel e : list) {
			arr[i] = ((List_izpitvan_pokazatel) e).getName_pokazatel();
			i++;
		}
		return arr;
	}
}
