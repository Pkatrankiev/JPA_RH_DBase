package Table;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Aplication.Ind_num_docDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.RazmernostiDAO;
import Aplication.TableColumnDAO;
import Aplication.UsersDAO;
import Aplication.ZabelejkiDAO;
import DBase_Class.TableColumn;
import Table_Default_Structors.TableObject_Class;

public class CreateColumnTapeForTable {

	
	public static void CreateListColumnTapeForTable(String tipeTable){ 
		Map<String, TableObject_Class> mapListTableRequest = new HashMap<String, TableObject_Class>();
		List<TableObject_Class> list_TableObject_Class = new ArrayList<>();
		
		List<TableColumn> list_TableColumn = TableColumnDAO.getListTableColumnByTipe_Table(tipeTable);
		int k=0;
		for (TableColumn tableColumn : list_TableColumn) {
			TableObject_Class colum_Object = new TableObject_Class();
			
			colum_Object.setColumName_Header(tableColumn.getName_Column());
			colum_Object.setClassColumn(getClassByStringClass(tableColumn.getClass_Column()));
			colum_Object.setTipeColumn(tableColumn.getTipe_Column());
			colum_Object.setMasiveValueForChoice(null);
			colum_Object.setNumberColum(k);
			
			switch (tableColumn.getKeyMap()) {
			
			case "zab":
				colum_Object.setMasiveValueForChoice(ZabelejkiDAO.getMasiveStringAllValueZabelejki());
				break;
			case "user":
				colum_Object.setMasiveValueForChoice( UsersDAO.getMasiveStringAllName_FamilyUsers());
				break;
			case "razmer":
				colum_Object.setMasiveValueForChoice( RazmernostiDAO.getMasiveStringAllValueRazmernosti());
				break;
			case "obk_Izp":
				colum_Object.setMasiveValueForChoice(Obekt_na_izpitvane_requestDAO.getMasiveStringAllValueObekt_na_izpitvane());
				break;
			case "izp_Prod":
				colum_Object.setMasiveValueForChoice(Izpitvan_produktDAO.getMasiveStringAllValueIzpitvan_produkt());
				break;
			case "id_ND":
				colum_Object.setMasiveValueForChoice(Ind_num_docDAO.getMasiveStringAllValueValueInd_num_doc());
				break;
			
			}
			
			list_TableObject_Class.add(colum_Object);
			mapListTableRequest.put(tableColumn.getKeyMap(),colum_Object);
			k++;
		}

	RequestTableList_OverallVariables.setList_TableObject_Class(list_TableObject_Class);
	RequestTableList_OverallVariables.setMap_TableObject_Class( mapListTableRequest);
	RequestTableList_OverallVariables.setList_TableColumn(list_TableColumn);
	}

	private static Class<?> getClassByStringClass(String class_Column) {
		switch (class_Column) {
		
		case "String":
			return String.class;
		case "Integer":
			return Integer.class;
		case "Boolean":
			return Boolean.class;
		case "Double":
			return Double.class;
			
		}
		return null;
	}
	
	
}
