package OldClases;

import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;
import DBase_Class.TableColumn;
import DBase_Class.Users;
import DefaultTableList.TableObject_Class;

public class RequestTableList_OverallVariables {
	
	private static List<Integer> listRowForUpdate;
	private static List<Users> listAllUsers;
	private static JCheckBox chckbxNewCheckBox;
	private static Users user;
	private static String frame_name;
	private static Object[][] dataTable;
	private static List<String> values_O_I_R;
	private static List<String> list_StringNewVizibleColumn;
	private static Map<String, TableObject_Class> map_TableObject_Class;
	private static List<TableObject_Class> list_TableObject_Class;	
	private static int[] masive_Invizible_Colum;
	private static Map<Integer, List<String>> mapListForChangedStrObektNaIzp;
	private static List<TableColumn> list_TableColumn;
	
	
	public static List<Integer> getListRowForUpdate() {
		return listRowForUpdate;
	}

	public static void setListRowForUpdate(List<Integer> listRowForUpdate) {
		RequestTableList_OverallVariables.listRowForUpdate = listRowForUpdate;
	}

	public static Object[][] getDataTable() {
		return dataTable;
	}

	public static void setDataTable(Object[][] dataTable) {
		RequestTableList_OverallVariables.dataTable = dataTable;
	}

	public static Map<String, TableObject_Class> getMap_TableObject_Class() {
		return map_TableObject_Class;
	}

	public static void setMap_TableObject_Class(Map<String, TableObject_Class> map_TableObject_Class) {
		RequestTableList_OverallVariables.map_TableObject_Class = map_TableObject_Class;
	}

	public static void setList_TableObject_Class(List<TableObject_Class> list_TableObject_Class) {
		RequestTableList_OverallVariables.list_TableObject_Class = list_TableObject_Class;
	}
	
	public static List<TableObject_Class> getList_TableObject_Class() {
		return list_TableObject_Class;
	}

	public static JCheckBox getChckbxNewCheckBox() {
		return chckbxNewCheckBox;
	}

	public static void setChckbxNewCheckBox(JCheckBox chckbxNewCheckBox) {
		RequestTableList_OverallVariables.chckbxNewCheckBox = chckbxNewCheckBox;
	}

	public static Users getUser() {
		return user;
	}

	public static void setUser(Users user) {
		RequestTableList_OverallVariables.user = user;
	}

	public static List<String> getValues_O_I_R() {
		return values_O_I_R;
	}

	public static void setValues_O_I_R(List<String> values_O_I_R) {
		RequestTableList_OverallVariables.values_O_I_R = values_O_I_R;
	}

	public static int[] getMasive_Invizible_Colum() {
		return masive_Invizible_Colum;
	}

	public static void setMasiveIndexInvizible_Colum(int[] masive_Invizible_Colum) {
		RequestTableList_OverallVariables.masive_Invizible_Colum = masive_Invizible_Colum;
	}

	public static List<Users> getListAllUsers() {
		return listAllUsers;
	}

	public static void setListAllUsers(List<Users> listAllUsers) {
		RequestTableList_OverallVariables.listAllUsers = listAllUsers;
	}
	
	public static boolean isEditableTable() {
		if(getUser() == null){
			return false;
		}
		return getChckbxNewCheckBox()!=null && getChckbxNewCheckBox().isSelected()	&& getUser().getIsAdmin();
	}

	public static Map<Integer, List<String>> getMapListForChangedStrObektNaIzp() {
		return mapListForChangedStrObektNaIzp;
	}

	public static void setMapListForChangedStrObektNaIzp(Map<Integer, List<String>> mapListForChangedStrObektNaIzp) {
		RequestTableList_OverallVariables.mapListForChangedStrObektNaIzp = mapListForChangedStrObektNaIzp;
	}


	public static String getFrame_name() {
		return frame_name;
	}

	public static void setFrame_name(String frame_name) {
		RequestTableList_OverallVariables.frame_name = frame_name;
	}

	
	public static List<TableColumn> getList_TableColumn() {
		return list_TableColumn;
	}

	public static void setList_TableColumn(List<TableColumn> list_TableColumn) {
		RequestTableList_OverallVariables.list_TableColumn = list_TableColumn;
	}

	public static List<String> getList_StringNewVizibleColumn() {
		return list_StringNewVizibleColumn;
	}

	public static void setList_StringNewVizibleColumn(List<String> list_StringNewVizibleColumn) {
		RequestTableList_OverallVariables.list_StringNewVizibleColumn = list_StringNewVizibleColumn;
	}
	
	}
