package Table;

import java.util.List;

public class OverallVariablesTableRequestList {

	private static String rqst_code_ColumName = "� �� ��������";
	private static String id_ND_ColumName = "��.� �� ���������";
	private static String rqst_Date_ColumName = "���� �� ��������";
	private static String izp_Prod_ColumName = "�������� �������";
	private static String obk_Izp_ColumName = "����� �� ���������";
	private static String izp_Pok_ColumName = "���������";
	private static String razmer_ColumName =  "����������";
	private static String cunt_Smpl_ColumName = "���� �����";
	private static String dscr_Smpl_ColumName = "�������� �� �������";
	private static String ref_Date_ColumName = "���������� ����";
	private static String exec_Date_ColumName = "���� �� ����������";
	private static String rcpt_Date_ColumName =  "����� �� ��������";
	private static String user_ColumName = "����� ��������";
	private static String zab_ColumName = "���������";
	private static String in_Acredit_ColumName = "� �����������";
	private static String user_Id_ColumName = "Id User";

	
	public static String getRqst_code_ColumName() {
		return rqst_code_ColumName;
	}

	public static String getId_ND_ColumName() {
		return id_ND_ColumName;
	}

	public static String getRqst_Date_ColumName() {
		return rqst_Date_ColumName;
	}

	public static String getIzp_Prod_ColumName() {
		return izp_Prod_ColumName;
	}

	public static String getObk_Izp_ColumName() {
		return obk_Izp_ColumName;
	}

	public static String getIzp_Pok_ColumName() {
		return izp_Pok_ColumName;
	}

	public static String getRazmer_ColumName() {
		return razmer_ColumName;
	}

	public static String getCunt_Smpl_ColumName() {
		return cunt_Smpl_ColumName;
	}

	public static String getDscr_Smpl_ColumName() {
		return dscr_Smpl_ColumName;
	}

	public static String getRef_Date_ColumName() {
		return ref_Date_ColumName;
	}

	public static String getExec_Date_ColumName() {
		return exec_Date_ColumName;
	}

	public static String getRcpt_Date_ColumName() {
		return rcpt_Date_ColumName;
	}

	public static String getUser_ColumName() {
		return user_ColumName;
	}

	public static String getZab_ColumName() {
		return zab_ColumName;
	}

	public static String getIn_Acredit_ColumName() {
		return in_Acredit_ColumName;
	}

	public static String getUser_Id_ColumName() {
		return user_Id_ColumName;
	}

	private static List<Integer> listRowForUpdate;
	private static Object[][] dataTable;
	
	
	
	
	public static List<Integer> getListRowForUpdate() {
		return listRowForUpdate;
	}

	public static void setListRowForUpdate(List<Integer> listRowForUpdate) {
		OverallVariablesTableRequestList.listRowForUpdate = listRowForUpdate;
	}

	public static Object[][] getDataTable() {
		return dataTable;
	}

	public static void setDataTable(Object[][] dataTable) {
		OverallVariablesTableRequestList.dataTable = dataTable;
	}
	
}
