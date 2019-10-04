package AddDobivViewFunction;

import java.util.ArrayList;
import java.util.List;

import DBase_Class.Dobiv;
import DBase_Class.Metody;
import DBase_Class.Nuclide;
import DBase_Class.Users;

public class OverallVariablesAddDobiv {

	private static List<Dobiv> listChoisedDobiv;
	private static List<Users> list_Users;
	private static List<String> list_UsersNameFamilyOIR;
	private static List<String> list_UsersNameFamilyORHO;
	private static List<Metody> listMetody;
	private static List<String> listStandartCodeAllDobiv ;
	private static List<String> listSimbolBasikNulide;
	private static List<Nuclide> listNuclideToMetod;
	
	private static String[] masuveSimbolBasikNuclide;
	private static String[] masive_NuclideToMetod;
	private static String[] masiveTSI;
	private static Object[][] dataTable;
	
	private static Metody selectedMetod = null;
	private static Users user_Redac = null;
	
	private static Boolean flagNotReadListPokazatel = true;
	private static Boolean flagNotReadListMetody = true;
	private static Boolean viewAddRowButton = false;
	private static Boolean corectStandartCode = true;

	private static Boolean flagIncertedFile = false;
	private static Boolean fromDBase = null;
	
	
	
	
	
	public static List<Dobiv> getListChoisedDobiv() {
		return listChoisedDobiv;
	}

	public static void setListChoisedDobiv(List<Dobiv> listChoisedDobiv) {
		OverallVariablesAddDobiv.listChoisedDobiv = listChoisedDobiv;
	}

	public static List<Users> getList_Users() {
		return list_Users;
	}

	public static void setList_Users(List<Users> list_Users) {
		OverallVariablesAddDobiv.list_Users = list_Users;
	}

	public static List<String> getList_UsersNameFamilyOIR() {
		return list_UsersNameFamilyOIR;
	}

	public static void setList_UsersNameFamilyOIR(List<String> list_UsersNameFamilyOIR) {
		OverallVariablesAddDobiv.list_UsersNameFamilyOIR = list_UsersNameFamilyOIR;
	}

	public static List<String> getList_UsersNameFamilyORHO() {
		return list_UsersNameFamilyORHO;
	}

	public static void setList_UsersNameFamilyORHO(List<String> list_UsersNameFamilyORHO) {
		OverallVariablesAddDobiv.list_UsersNameFamilyORHO = list_UsersNameFamilyORHO;
	}

	public static List<Metody> getListMetody() {
		return listMetody;
	}

	public static void setListMetody(List<Metody> listMetody) {
		OverallVariablesAddDobiv.listMetody = listMetody;
	}

	public static List<String> getListStandartCodeAllDobiv() {
		return listStandartCodeAllDobiv;
	}

	public static void setListStandartCodeAllDobiv(List<String> listStandartCodeAllDobiv) {
		OverallVariablesAddDobiv.listStandartCodeAllDobiv = listStandartCodeAllDobiv;
	}

	public static List<String> getListSimbolBasikNulide() {
		return listSimbolBasikNulide;
	}

	public static void setListSimbolBasikNulide(List<String> listSimbolBasikNulide) {
		OverallVariablesAddDobiv.listSimbolBasikNulide = listSimbolBasikNulide;
	}

	public static List<Nuclide> getListNuclideToMetod() {
		return listNuclideToMetod;
	}

	public static void setListNuclideToMetod(List<Nuclide> listNuclideToMetod) {
		OverallVariablesAddDobiv.listNuclideToMetod = listNuclideToMetod;
	}

	public static String[] getMasuveSimbolBasikNuclide() {
		return masuveSimbolBasikNuclide;
	}

	public static void setMasuveSimbolBasikNuclide(String[] masuveSimbolBasikNuclide) {
		OverallVariablesAddDobiv.masuveSimbolBasikNuclide = masuveSimbolBasikNuclide;
	}

	public static String[] getMasive_NuclideToMetod() {
		return masive_NuclideToMetod;
	}

	public static void setMasive_NuclideToMetod(String[] masive_NuclideToMetod) {
		OverallVariablesAddDobiv.masive_NuclideToMetod = masive_NuclideToMetod;
	}

	public static String[] getMasiveTSI() {
		return masiveTSI;
	}

	public static void setMasiveTSI(String[] masiveTSI) {
		OverallVariablesAddDobiv.masiveTSI = masiveTSI;
	}

	public static Object[][] getDataTable() {
		return dataTable;
	}

	public static void setDataTable(Object[][] dataTable) {
		OverallVariablesAddDobiv.dataTable = dataTable;
	}

	public static Metody getSelectedMetod() {
		return selectedMetod;
	}

	public static void setSelectedMetod(Metody selectedMetod) {
		OverallVariablesAddDobiv.selectedMetod = selectedMetod;
	}

	public static Users getUser_Redac() {
		return user_Redac;
	}

	public static void setUser_Redac(Users user_Redac) {
		OverallVariablesAddDobiv.user_Redac = user_Redac;
	}

	public static Boolean getFlagNotReadListPokazatel() {
		return flagNotReadListPokazatel;
	}

	public static void setFlagNotReadListPokazatel(Boolean flagNotReadListPokazatel) {
		OverallVariablesAddDobiv.flagNotReadListPokazatel = flagNotReadListPokazatel;
	}

	public static Boolean getFlagNotReadListMetody() {
		return flagNotReadListMetody;
	}

	public static void setFlagNotReadListMetody(Boolean flagNotReadListMetody) {
		OverallVariablesAddDobiv.flagNotReadListMetody = flagNotReadListMetody;
	}

	public static Boolean getViewAddRowButton() {
		return viewAddRowButton;
	}

	public static void setViewAddRowButton(Boolean viewAddRowButton) {
		OverallVariablesAddDobiv.viewAddRowButton = viewAddRowButton;
	}

	public static Boolean getCorectStandartCode() {
		return corectStandartCode;
	}

	public static void setCorectStandartCode(Boolean corectStandartCode) {
		OverallVariablesAddDobiv.corectStandartCode = corectStandartCode;
	}

	public static boolean getFlagIncertedFile() {
		return flagIncertedFile;
	}

	public static void setFlagIncertedFile(boolean flagIncertedFile) {
		OverallVariablesAddDobiv.flagIncertedFile = flagIncertedFile;
	}

	public static Boolean getFromDBase() {
		return fromDBase;
	}

	public static void setFromDBase(Boolean fromDBase) {
		OverallVariablesAddDobiv.fromDBase = fromDBase;
	}

	
}
