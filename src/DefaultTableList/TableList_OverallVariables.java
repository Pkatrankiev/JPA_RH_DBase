package DefaultTableList;

import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;
import DBase_Class.TableColumn;
import DBase_Class.Users;

public class TableList_OverallVariables {

	private static List<Integer> listRowForUpdate;
	private static List<Users> listAllUsers;
	private static JCheckBox chckbxNewCheckBox;
	private static Users user;
	private static String frame_name;
	private static String tipe_Table;
	private static Object[][] dataTable;
	private static List<String> values_O_I_R;
	private static List<String> list_StringNewVizibleColumn;
	private static Map<String, TableObject_Class> map_TableObject_Class;
	private static List<TableObject_Class> list_TableObject_Class;
	private static int[] masive_Invizible_Colum;
	private static Map<Integer, List<String>> mapListForChangedStrObektNaIzp;
	private static List<TableColumn> list_TableColumn;
	
	
	public static JCheckBox getChckbxNewCheckBox() {
		return chckbxNewCheckBox;
	}

	public static void setChckbxNewCheckBox(JCheckBox chckbxNewCheckBox) {
		TableList_OverallVariables.chckbxNewCheckBox = chckbxNewCheckBox;
	}

	public static Users getUser() {
		return user;
	}

	public static void setUser(Users user) {
		TableList_OverallVariables.user = user;
	}

	public static String getFrame_name() {
		return frame_name;
	}

	public static void setFrame_name(String frame_name) {
		TableList_OverallVariables.frame_name = frame_name;
	}

	public static Object[][] getDataTable() {
		return dataTable;
	}

	public static void setDataTable(Object[][] dataTable) {
		TableList_OverallVariables.dataTable = dataTable;
	}

	public static List<String> getValues_O_I_R() {
		return values_O_I_R;
	}

	public static void setValues_O_I_R(List<String> values_O_I_R) {
		TableList_OverallVariables.values_O_I_R = values_O_I_R;
	}

	public static List<String> getList_StringNewVizibleColumn() {
		return list_StringNewVizibleColumn;
	}

	public static void setList_StringNewVizibleColumn(List<String> list_StringNewVizibleColumn) {
		TableList_OverallVariables.list_StringNewVizibleColumn = list_StringNewVizibleColumn;
	}

	public static Map<String, TableObject_Class> getMap_TableObject_Class() {
		return map_TableObject_Class;
	}

	public static void setMap_TableObject_Class(Map<String, TableObject_Class> map_TableObject_Class) {
		TableList_OverallVariables.map_TableObject_Class = map_TableObject_Class;
	}

	public static List<TableObject_Class> getList_TableObject_Class() {
		return list_TableObject_Class;
	}

	public static void setList_TableObject_Class(List<TableObject_Class> list_TableObject_Class) {
		TableList_OverallVariables.list_TableObject_Class = list_TableObject_Class;
	}

	public static int[] getMasive_Invizible_Colum() {
		return masive_Invizible_Colum;
	}

	public static void setMasive_Invizible_Colum(int[] masive_Invizible_Colum) {
		TableList_OverallVariables.masive_Invizible_Colum = masive_Invizible_Colum;
	}

	public static Map<Integer, List<String>> getMapListForChangedStrObektNaIzp() {
		return mapListForChangedStrObektNaIzp;
	}

	public static void setMapListForChangedStrObektNaIzp(Map<Integer, List<String>> mapListForChangedStrObektNaIzp) {
		TableList_OverallVariables.mapListForChangedStrObektNaIzp = mapListForChangedStrObektNaIzp;
	}

	public static List<TableColumn> getList_TableColumn() {
		return list_TableColumn;
	}

	public static void setList_TableColumn(List<TableColumn> list_TableColumn) {
		TableList_OverallVariables.list_TableColumn = list_TableColumn;
	}

	public static List<Integer> getListRowForUpdate() {
		return listRowForUpdate;
	}

	public static void setListRowForUpdate(List<Integer> listRowForUpdate) {
		TableList_OverallVariables.listRowForUpdate = listRowForUpdate;
	}

	public static List<Users> getListAllUsers() {
		return listAllUsers;
	}

	public static void setListAllUsers(List<Users> listAllUsers) {
		TableList_OverallVariables.listAllUsers = listAllUsers;
	}

	public static boolean isEditableTable() {
		if (getUser() == null) {
			return false;
		}
		if( getChckbxNewCheckBox()==null){
			return false;	
		}
		System.out.println("+- "+getChckbxNewCheckBox().isSelected());
		System.out.println(getChckbxNewCheckBox().isSelected()	&& getUser().getIsAdmin());
		return  getChckbxNewCheckBox().isSelected() && getUser().getIsAdmin();
	}

	public static boolean isSelectedCheckBox() {
		
		if( getChckbxNewCheckBox()==null){
			return false;	
		}
		return  getChckbxNewCheckBox().isSelected();
	}
	
	public static String getTipe_Table() {
		return tipe_Table;
	}

	public static void setTipe_Table(String tipe_Table) {
		TableList_OverallVariables.tipe_Table = tipe_Table;
	}

}
