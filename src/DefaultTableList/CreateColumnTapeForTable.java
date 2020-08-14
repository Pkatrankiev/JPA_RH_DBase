package DefaultTableList;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Aplication.DimensionDAO;
import Aplication.Ind_num_docDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.NuclideDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.RazmernostiDAO;
import Aplication.TableColumnDAO;
import Aplication.UsersDAO;
import Aplication.ZabelejkiDAO;
import DBase_Class.TableColumn;


public class CreateColumnTapeForTable {
	
	
	@SuppressWarnings("static-access")
	public static void CreateListColumnTapeForTable(TableList_OverallVariables objectTableList_OverallVariables){ 
		Map<String, TableObject_Class> mapListTable = new HashMap<String, TableObject_Class>();
		List<TableObject_Class> list_TableObject_Class = new ArrayList<>();
		
		List<TableColumn> list_TableColumn = TableColumnDAO.getListTableColumnByTipe_Table(objectTableList_OverallVariables.getTipe_Table());
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
				
			case "Object_Sample":
				colum_Object.setMasiveValueForChoice(Obekt_na_izpitvane_sampleDAO.getMasiveStringAllValueObekt_na_izpitvane_sample());
				break;
				
			case "MetodNaIzpitvane":
				colum_Object.setMasiveValueForChoice(MetodyDAO.getMasiveStringAllValueMetody());
				break;
				
			case "IzpitvanPokazatel":
				colum_Object.setMasiveValueForChoice(List_izpitvan_pokazatelDAO.getMasiveStringAllValueList_Izpitvan_Pokazatel());
				break;
				
			case "Nuclide":
				colum_Object.setMasiveValueForChoice(NuclideDAO.getMasiveStringAllValueNuclide());
				break;
				
			case "Nuclide_Dobiv":
				colum_Object.setMasiveValueForChoice(NuclideDAO.getMasiveStringAllValueNuclide());
				break;
				
			case "Dimension":
				colum_Object.setMasiveValueForChoice(DimensionDAO.getMasiveStringAllValueDimension());
				break;
			}
			
			list_TableObject_Class.add(colum_Object);
			mapListTable.put(tableColumn.getKeyMap(),colum_Object);
			k++;
		}
		
		objectTableList_OverallVariables.setList_TableObject_Class(list_TableObject_Class);
		objectTableList_OverallVariables.setMap_TableObject_Class( mapListTable);
		objectTableList_OverallVariables.setList_TableColumn(list_TableColumn);
		
//		switch (tipeTable) {
//
//		case "request":
//			RequestTableList_OverallVariables.setList_TableObject_Class(list_TableObject_Class);
//			RequestTableList_OverallVariables.setMap_TableObject_Class( mapListTable);
//			RequestTableList_OverallVariables.setList_TableColumn(list_TableColumn);
//			break;
//
//		case "Results":
//			TableList_OverallVariables.setList_TableObject_Class(list_TableObject_Class);
//			TableList_OverallVariables.setMap_TableObject_Class( mapListTable);
//			TableList_OverallVariables.setList_TableColumn(list_TableColumn);
//			break;
//
//		}
	
	}

	@SuppressWarnings("static-access")
	public static void CreateListColumnTapeForTable_Test(TableList_OverallVariables objectTableList_OverallVariables){ 
		Map<String, TableObject_Class> mapListTable = new HashMap<String, TableObject_Class>();
		List<TableObject_Class> list_TableObject_Class = new ArrayList<>();
		
		List<TableColumn> list_TableColumn = TableColumnDAO.getListTableColumnByTipe_Table(objectTableList_OverallVariables.getTipe_Table());
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
				
			case "Object_Sample":
				colum_Object.setMasiveValueForChoice(Obekt_na_izpitvane_sampleDAO.getMasiveStringAllValueObekt_na_izpitvane_sample());
				break;
				
			case "MetodNaIzpitvane":
				colum_Object.setMasiveValueForChoice(MetodyDAO.getMasiveStringAllValueMetody());
				break;
				
			case "IzpitvanPokazatel":
				colum_Object.setMasiveValueForChoice(List_izpitvan_pokazatelDAO.getMasiveStringAllValueList_Izpitvan_Pokazatel());
				break;
				
			case "Nuclide":
				colum_Object.setMasiveValueForChoice(NuclideDAO.getMasiveStringAllValueNuclide());
				break;
				
			case "Nuclide_Dobiv":
				colum_Object.setMasiveValueForChoice(NuclideDAO.getMasiveStringAllValueNuclide());
				break;
				
			case "Dimension":
				colum_Object.setMasiveValueForChoice(DimensionDAO.getMasiveStringAllValueDimension());
				break;
			}
			
			list_TableObject_Class.add(colum_Object);
			mapListTable.put(tableColumn.getKeyMap(),colum_Object);
			k++;
		}
		
		objectTableList_OverallVariables.setList_TableObject_Class(list_TableObject_Class);
		objectTableList_OverallVariables.setMap_TableObject_Class( mapListTable);
		objectTableList_OverallVariables.setList_TableColumn(list_TableColumn);	
		
//		switch (tipeTable) {
//
//		case "request":
//			RequestTableList_OverallVariables.setList_TableObject_Class(list_TableObject_Class);
//			RequestTableList_OverallVariables.setMap_TableObject_Class( mapListTable);
//			RequestTableList_OverallVariables.setList_TableColumn(list_TableColumn);
//			break;
//
//		case "Results":
//			ResultsTableList_OverallVariables.setList_TableObject_Class(list_TableObject_Class);
//			ResultsTableList_OverallVariables.setMap_TableObject_Class( mapListTable);
//			ResultsTableList_OverallVariables.setList_TableColumn(list_TableColumn);
//			break;
//
//		}
	
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
