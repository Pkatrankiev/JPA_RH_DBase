package Table;

import java.util.ArrayList;
import java.util.List;

import Aplication.Ind_num_docDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.RazmernostiDAO;
import Aplication.UsersDAO;
import Aplication.ZabelejkiDAO;

public class CreateColumnTapeForReuqestTable {
	
	
	private static String rqst_code_ColumName = OverallVariablesTableRequestList.getRqst_code_ColumName();
	private static String id_ND_ColumName = OverallVariablesTableRequestList.getId_ND_ColumName();
	private static String rqst_Date_ColumName = OverallVariablesTableRequestList.getRqst_Date_ColumName();
	private static String izp_Prod_ColumName = OverallVariablesTableRequestList.getIzp_Prod_ColumName();
	private static String obk_Izp_ColumName = OverallVariablesTableRequestList.getObk_Izp_ColumName();
	private static String izp_Pok_ColumName = OverallVariablesTableRequestList.getIzp_Pok_ColumName();
	private static String razmer_ColumName =  OverallVariablesTableRequestList.getRazmer_ColumName();
	private static String cunt_Smpl_ColumName = OverallVariablesTableRequestList.getCunt_Smpl_ColumName();
	private static String dscr_Smpl_ColumName = OverallVariablesTableRequestList.getDscr_Smpl_ColumName();
	private static String ref_Date_ColumName = OverallVariablesTableRequestList.getRef_Date_ColumName();
	private static String exec_Date_ColumName = OverallVariablesTableRequestList.getExec_Date_ColumName();
	private static String rcpt_Date_ColumName =  OverallVariablesTableRequestList.getRcpt_Date_ColumName();
	private static String user_ColumName = OverallVariablesTableRequestList.getUser_ColumName();
	private static String zab_ColumName = OverallVariablesTableRequestList.getZab_ColumName();
	private static String in_Acredit_ColumName = OverallVariablesTableRequestList.getIn_Acredit_ColumName();
	private static String user_Id_ColumName = OverallVariablesTableRequestList.getUser_Id_ColumName();
	
	public static List<TableObject_Class> CreateListColumnTapeForReuqestTable(){ 
		
		List<TableObject_Class> list = new ArrayList<>();
		
	TableObject_Class columObject = new TableObject_Class();
	
	columObject.setNumberColum(0);
	columObject.setColumName_Header(rqst_code_ColumName);
	columObject.setClassColumn(Integer.class);
	columObject.setTipeColumn("Int");
	columObject.setMasiveValueForChoice(null);
	list.add(columObject);
	
	columObject.setNumberColum(1);
	columObject.setColumName_Header(id_ND_ColumName);
	columObject.setClassColumn(String.class);
	columObject.setTipeColumn( "Choice");
	columObject.setMasiveValueForChoice(Ind_num_docDAO.getMasiveStringAllValueValueInd_num_doc());
	list.add(columObject);
	
	columObject.setNumberColum(2);
	columObject.setColumName_Header(rqst_Date_ColumName);
	columObject.setClassColumn(String.class);
	columObject.setTipeColumn("DatePicker");
	columObject.setMasiveValueForChoice(null);
	list.add(columObject);
	
	columObject.setNumberColum(3);
	columObject.setColumName_Header(izp_Prod_ColumName);
	columObject.setClassColumn(String.class);
	columObject.setTipeColumn("Choice");
	columObject.setMasiveValueForChoice(Izpitvan_produktDAO.getMasiveStringAllValueIzpitvan_produkt());
	list.add(columObject);
	
	columObject.setNumberColum(4);
	columObject.setColumName_Header(obk_Izp_ColumName);
	columObject.setClassColumn(String.class);
	columObject.setTipeColumn("Choice");
	columObject.setMasiveValueForChoice(Obekt_na_izpitvane_requestDAO.getMasiveStringAllValueObekt_na_izpitvane());
	list.add(columObject);
	
	columObject.setNumberColum(5);
	columObject.setColumName_Header(izp_Pok_ColumName );
	columObject.setClassColumn(String.class);
	columObject.setTipeColumn("Pokazatel");
	columObject.setMasiveValueForChoice(null);
	list.add(columObject);
	
	columObject.setNumberColum(6);
	columObject.setColumName_Header(razmer_ColumName);
	columObject.setClassColumn(String.class);
	columObject.setTipeColumn("Choice");
	columObject.setMasiveValueForChoice(RazmernostiDAO.getMasiveStringAllValueRazmernosti());
	list.add(columObject);
	
	columObject.setNumberColum(7);
	columObject.setColumName_Header(cunt_Smpl_ColumName);
	columObject.setClassColumn(Integer.class);
	columObject.setTipeColumn("Int");
	columObject.setMasiveValueForChoice(null);
	list.add(columObject);
			
	columObject.setNumberColum(8);
	columObject.setColumName_Header(dscr_Smpl_ColumName);
	columObject.setClassColumn( String.class);
	columObject.setTipeColumn("Text");
	columObject.setMasiveValueForChoice(null);
	list.add(columObject);
		
	columObject.setNumberColum(9);
	columObject.setColumName_Header(ref_Date_ColumName);
	columObject.setClassColumn( String.class);
	columObject.setTipeColumn("Date-TimePicer");
	columObject.setMasiveValueForChoice(null);
	list.add(columObject);
	
	columObject.setNumberColum(10);
	columObject.setColumName_Header(exec_Date_ColumName);
	columObject.setClassColumn(String.class);
	columObject.setTipeColumn("DatePicker");
	columObject.setMasiveValueForChoice(null);
	list.add(columObject);
	
	columObject.setNumberColum(11);
	columObject.setColumName_Header(rcpt_Date_ColumName);
	columObject.setClassColumn(String.class);
	columObject.setTipeColumn("DatePicker");
	columObject.setMasiveValueForChoice(null);
	list.add(columObject);
	
	columObject.setNumberColum(12);
	columObject.setColumName_Header(user_ColumName);
	columObject.setClassColumn(String.class);
	columObject.setTipeColumn("Choice");
	columObject.setMasiveValueForChoice( UsersDAO.getMasiveStringAllName_FamilyUsers());
	list.add(columObject);
	
	columObject.setNumberColum(13);
	columObject.setColumName_Header(zab_ColumName);
	columObject.setClassColumn(String.class);
	columObject.setTipeColumn("Choice");
	columObject.setMasiveValueForChoice( ZabelejkiDAO.getMasiveStringAllValueZabelejki());
	list.add(columObject);
	
	columObject.setNumberColum(14);
	columObject.setColumName_Header(in_Acredit_ColumName);
	columObject.setClassColumn(Boolean.class);
	columObject.setTipeColumn("Boolean_Check");
	columObject.setMasiveValueForChoice(null);
	list.add(columObject);
	
	columObject.setNumberColum(15);
	columObject.setColumName_Header(user_Id_ColumName);
	columObject.setClassColumn(Integer.class);
	columObject.setTipeColumn("Int");
	columObject.setMasiveValueForChoice(null);
	list.add(columObject);
	return list;
	
	}
}
