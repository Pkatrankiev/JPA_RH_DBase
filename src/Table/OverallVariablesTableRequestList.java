package Table;

import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;

import DBase_Class.Users;
import Table_Default_Structors.TableObject_Class;

public class OverallVariablesTableRequestList {
		
	

	private static List<Integer> listRowForUpdate;
	private static List<Users> listAllUsers;
	private static JCheckBox chckbxNewCheckBox;
	private static Users user;
	private static Object[][] dataTable;
	private static List<String> values_O_I_R;
	private static Map<String, TableObject_Class> map_TableObject_Class;
	private static List<TableObject_Class> list_TableObject_Class;	
	private static int[] masive_Invizible_Colum;
	private static Map<Integer, List<String>> mapListForChangedStrObektNaIzp;

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

	public static Map<String, TableObject_Class> getMap_TableObject_Class() {
		return map_TableObject_Class;
	}

	public static void setMap_TableObject_Class(Map<String, TableObject_Class> map_TableObject_Class) {
		OverallVariablesTableRequestList.map_TableObject_Class = map_TableObject_Class;
	}

	public static void setList_TableObject_Class(List<TableObject_Class> list_TableObject_Class) {
		OverallVariablesTableRequestList.list_TableObject_Class = list_TableObject_Class;
	}
	
	public static List<TableObject_Class> getList_TableObject_Class() {
		return list_TableObject_Class;
	}

	public static JCheckBox getChckbxNewCheckBox() {
		return chckbxNewCheckBox;
	}

	public static void setChckbxNewCheckBox(JCheckBox chckbxNewCheckBox) {
		OverallVariablesTableRequestList.chckbxNewCheckBox = chckbxNewCheckBox;
	}

	public static Users getUser() {
		return user;
	}

	public static void setUser(Users user) {
		OverallVariablesTableRequestList.user = user;
	}

	public static List<String> getValues_O_I_R() {
		return values_O_I_R;
	}

	public static void setValues_O_I_R(List<String> values_O_I_R) {
		OverallVariablesTableRequestList.values_O_I_R = values_O_I_R;
	}

	public static int[] getMasive_Invizible_Colum() {
		return masive_Invizible_Colum;
	}

	public static void setMasive_Invizible_Colum(int[] masive_Invizible_Colum) {
		OverallVariablesTableRequestList.masive_Invizible_Colum = masive_Invizible_Colum;
	}

	public static List<Users> getListAllUsers() {
		return listAllUsers;
	}

	public static void setListAllUsers(List<Users> listAllUsers) {
		OverallVariablesTableRequestList.listAllUsers = listAllUsers;
	}
	
	public static boolean isEditableTable() {
		if(getUser() == null){
			return false;
		}
		return getChckbxNewCheckBox().isSelected()	&& getUser().getIsAdmin();
	}

	public static Map<Integer, List<String>> getMapListForChangedStrObektNaIzp() {
		return mapListForChangedStrObektNaIzp;
	}

	public static void setMapListForChangedStrObektNaIzp(Map<Integer, List<String>> mapListForChangedStrObektNaIzp) {
		OverallVariablesTableRequestList.mapListForChangedStrObektNaIzp = mapListForChangedStrObektNaIzp;
	}
	
	}
