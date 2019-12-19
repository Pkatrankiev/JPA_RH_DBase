package Table;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Aplication.Ind_num_docDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.RazmernostiDAO;
import Aplication.UsersDAO;
import Aplication.ZabelejkiDAO;
import Table_Default_Structors.TableObject_Class;

public class CreateColumnTapeForReuqestTable {

	private static String rqst_code_ColumName = "№ на Заявката";
	private static String rqst_code = "rqst_code";
			
	private static String id_ND_ColumName =  "Ид.№ на документа";
	private static String id_ND = "id_ND";
	
	private static String rqst_Date_ColumName = "Дата на заявката";
	private static String rqst_Date = "rqst_Date";
	
	private static String izp_Prod_ColumName = "Изпитван продукт";
	private static String izp_Prod = "izp_Prod";
	
	private static String obk_Izp_ColumName = "Обект на изпитване";
	private static String obk_Izp = "obk_Izp";
	
	private static String izp_Pok_ColumName = "Показател";
	private static String izp_Pok = "izp_Pok";
	
	private static String razmer_ColumName =  "Размерност";
	private static String razmer = "razmer";
	
	private static String cunt_Smpl_ColumName ="Брой проби";
	private static String cunt_Smpl = "cunt_Smpl";
	
	private static String dscr_Smpl_ColumName = "Описание на пробите";
	private static String dscr_Smpl = "dscr_Smpl";
	
	private static String ref_Date_ColumName ="Референтна дата";
	private static String ref_Date = "ref_Date";
	
	private static String exec_Date_ColumName =  "Срок на изпълнение";
	private static String exec_Date = "exec_Date";
	
	private static String rcpt_Date_ColumName =  "Време на приемане";
	private static String rcpt_Date = "rcpt_Date";
	
	private static String user_ColumName =  "Приел заявката";
	private static String user = "user";
	
	private static String zab_ColumName =  "Забележка";
	private static String zab = "zab";
	
	private static String in_Acredit_ColumName =  "Извън обхват";
	private static String in_Acredit = "in_Acredit";
	
//	private static String user_Id_ColumName ="Id User";
//	private static String user_Id = "user_Id";
	
	public static void CreateListColumnTapeForReuqestTable(){ 
		Map<String, TableObject_Class> mapListTableRequest = new HashMap<String, TableObject_Class>();
		List<TableObject_Class> list_TableObject_Class = new ArrayList<>();
		
	TableObject_Class columObject1 = new TableObject_Class();
	
	columObject1.setNumberColum(0);
	columObject1.setColumName_Header(rqst_code_ColumName);
	columObject1.setClassColumn(Integer.class);
	columObject1.setTipeColumn("code_Request");
	columObject1.setMasiveValueForChoice(null);
	list_TableObject_Class.add(columObject1);
	mapListTableRequest.put(rqst_code,columObject1);
	
	TableObject_Class columObject2 = new TableObject_Class();
	columObject2.setNumberColum(1);
	columObject2.setColumName_Header(id_ND_ColumName);
	columObject2.setClassColumn(String.class);
	columObject2.setTipeColumn( "Choice");
	columObject2.setMasiveValueForChoice(Ind_num_docDAO.getMasiveStringAllValueValueInd_num_doc());
	list_TableObject_Class.add(columObject2);
	mapListTableRequest.put(id_ND,columObject2);
	
	TableObject_Class columObject3 = new TableObject_Class();
	columObject3.setNumberColum(2);
	columObject3.setColumName_Header(rqst_Date_ColumName);
	columObject3.setClassColumn(String.class);
	columObject3.setTipeColumn("DatePicker");
	columObject3.setMasiveValueForChoice(null);
	list_TableObject_Class.add(columObject3);
	mapListTableRequest.put(rqst_Date,columObject3);
	
	TableObject_Class columObject4 = new TableObject_Class();
	columObject4.setNumberColum(3);
	columObject4.setColumName_Header(izp_Prod_ColumName);
	columObject4.setClassColumn(String.class);
	columObject4.setTipeColumn("Choice");
	columObject4.setMasiveValueForChoice(Izpitvan_produktDAO.getMasiveStringAllValueIzpitvan_produkt());
	list_TableObject_Class.add(columObject4);
	mapListTableRequest.put(izp_Prod,columObject4);
	
	TableObject_Class columObject5 = new TableObject_Class();
	columObject5.setNumberColum(4);
	columObject5.setColumName_Header(obk_Izp_ColumName);
	columObject5.setClassColumn(String.class);
	columObject5.setTipeColumn("Table_RequestToObektNaIzp");
	columObject5.setMasiveValueForChoice(Obekt_na_izpitvane_requestDAO.getMasiveStringAllValueObekt_na_izpitvane());
	list_TableObject_Class.add(columObject5);
	mapListTableRequest.put(obk_Izp,columObject5);
	
	TableObject_Class columObject6 = new TableObject_Class();
	columObject6.setNumberColum(5);
	columObject6.setColumName_Header(izp_Pok_ColumName );
	columObject6.setClassColumn(String.class);
	columObject6.setTipeColumn("Pokazatel");
	columObject6.setMasiveValueForChoice(null);
	list_TableObject_Class.add(columObject6);
	mapListTableRequest.put(izp_Pok,columObject6);
	
	TableObject_Class columObject7 = new TableObject_Class();
	columObject7.setNumberColum(6);
	columObject7.setColumName_Header(razmer_ColumName);
	columObject7.setClassColumn(String.class);
	columObject7.setTipeColumn("Choice");
	columObject7.setMasiveValueForChoice(RazmernostiDAO.getMasiveStringAllValueRazmernosti());
	list_TableObject_Class.add(columObject7);
	mapListTableRequest.put(razmer,columObject7);
	
	TableObject_Class columObject8 = new TableObject_Class();
	columObject8.setNumberColum(7);
	columObject8.setColumName_Header(cunt_Smpl_ColumName);
	columObject8.setClassColumn(Integer.class);
	columObject8.setTipeColumn("count_Simple");
	columObject8.setMasiveValueForChoice(null);
	list_TableObject_Class.add(columObject8);
	mapListTableRequest.put(cunt_Smpl,columObject8);
		
	TableObject_Class columObject9 = new TableObject_Class();
	columObject9.setNumberColum(8);
	columObject9.setColumName_Header(dscr_Smpl_ColumName);
	columObject9.setClassColumn( String.class);
	columObject9.setTipeColumn("Text");
	columObject9.setMasiveValueForChoice(null);
	list_TableObject_Class.add(columObject9);
	mapListTableRequest.put(dscr_Smpl,columObject9);
		
	TableObject_Class columObject10 = new TableObject_Class();
	columObject10.setNumberColum(9);
	columObject10.setColumName_Header(ref_Date_ColumName);
	columObject10.setClassColumn( String.class);
	columObject10.setTipeColumn("Date-TimePicer");
	columObject10.setMasiveValueForChoice(null);
	list_TableObject_Class.add(columObject10);
	mapListTableRequest.put(ref_Date,columObject10);
	
	TableObject_Class columObject11 = new TableObject_Class();
	columObject11.setNumberColum(10);
	columObject11.setColumName_Header(exec_Date_ColumName);
	columObject11.setClassColumn(String.class);
	columObject11.setTipeColumn("DatePicker");
	columObject11.setMasiveValueForChoice(null);
	list_TableObject_Class.add(columObject11);
	mapListTableRequest.put(exec_Date,columObject11);
	
	TableObject_Class columObject12 = new TableObject_Class();
	columObject12.setNumberColum(11);
	columObject12.setColumName_Header(rcpt_Date_ColumName);
	columObject12.setClassColumn(String.class);
	columObject12.setTipeColumn("DatePicker_Dual");
	columObject12.setMasiveValueForChoice(null);
	list_TableObject_Class.add(columObject12);
	mapListTableRequest.put(rcpt_Date,columObject12);
	
	TableObject_Class columObject13 = new TableObject_Class();
	columObject13.setNumberColum(12);
	columObject13.setColumName_Header(user_ColumName);
	columObject13.setClassColumn(String.class);
	columObject13.setTipeColumn("Choice");
	columObject13.setMasiveValueForChoice( UsersDAO.getMasiveStringAllName_FamilyUsers());
	list_TableObject_Class.add(columObject13);
	mapListTableRequest.put(user,columObject13);
	
	TableObject_Class columObject14 = new TableObject_Class();
	columObject14.setNumberColum(13);
	columObject14.setColumName_Header(zab_ColumName);
	columObject14.setClassColumn(String.class);
	columObject14.setTipeColumn("Choice");
	columObject14.setMasiveValueForChoice( ZabelejkiDAO.getMasiveStringAllValueZabelejki());
	list_TableObject_Class.add(columObject14);
	mapListTableRequest.put(zab,columObject14);
	
	TableObject_Class columObject15 = new TableObject_Class();
	columObject15.setNumberColum(14);
	columObject15.setColumName_Header(in_Acredit_ColumName);
	columObject15.setClassColumn(Boolean.class);
	columObject15.setTipeColumn("Boolean_Check");
	columObject15.setMasiveValueForChoice(null);
	list_TableObject_Class.add(columObject15);
	mapListTableRequest.put(in_Acredit,columObject15);
	
//	columObject.setNumberColum(15);
//	columObject.setColumName_Header(user_Id_ColumName);
//	columObject.setClassColumn(Integer.class);
//	columObject.setTipeColumn("Int");
//	columObject.setMasiveValueForChoice(null);
//	list_TableObject_Class.add(columObject);
//	mapListTableRequest.put(user_Id,columObject);
	
	for (int i = 0; i < list_TableObject_Class.size(); i++) {
		list_TableObject_Class.get(i).setNumberColum(i);
		
	}
	for (TableObject_Class tableObject_Class : list_TableObject_Class) {
		System.out.println(tableObject_Class.getColumName_Header()+"  --   "+tableObject_Class.getNumberColum());
	}
	OverallVariablesTableRequestList.setList_TableObject_Class(list_TableObject_Class);
	OverallVariablesTableRequestList.setMap_TableObject_Class( mapListTableRequest);
	
	}
	
	
}
