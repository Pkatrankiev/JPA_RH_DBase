package AddResultViewFunction;

import java.util.ArrayList;
import java.util.List;

import DBase_Class.Dobiv;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Metody;
import DBase_Class.Nuclide_to_Pokazatel;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import ExcelFilesFunction.Destruct_Result;

public class OverallVariables {

	private static List<Sample> listSample;
	private static List<Users> list_Users;
	private static List<String> list_UsersNameFamilyOIR;
	private static List<String> list_UsersNameFamilyORHO;
	private static List<String> listSimbolBasikNulide;
	private static List<String> listSimbolBasikNulideToMetod;
	private static List<IzpitvanPokazatel> listPokazatel;
	private static List<Dobiv> listDobivFromMetod = new ArrayList<Dobiv>();
	private static List<Nuclide_to_Pokazatel> listNucToPok;
	private static List<Results> ListResultsFromDBase;
	private static List<Results> resultListForSave;
	private static List<Results> resultListForDelete;
	private static List<Destruct_Result> destruct_Result_List;

	private static String[] masuveSimbolNuclide;
	private static String[] masive_NuclideToPokazatel;
	private static String[] values_Razmernosti;
	private static String[] values_Dimension;
	private static String[] masiveTSI;
	private static Object[][] dataTable;

	private static Metody selectedMetod = null;
	private static Request choiseRequest = null;
	private static Users user_Redac = null;

	private static Boolean flagNotReadListPokazatel = true;
	private static Boolean flagNotReadListMetody = true;
	private static Boolean viewAddRowButton = false;
	private static Boolean flagIncertedFile = false;

	public static void clearAllVariables() {
		listSample = null;
		list_Users = null;
		list_UsersNameFamilyOIR = null;
		list_UsersNameFamilyORHO = null;
		listSimbolBasikNulide = null;
		listSimbolBasikNulideToMetod = null;
		listPokazatel = null;
		listDobivFromMetod = null;
		listNucToPok = null;
		ListResultsFromDBase = null;
		resultListForSave = null;
		resultListForDelete = null;
		destruct_Result_List = null;

		masuveSimbolNuclide = null;
		masive_NuclideToPokazatel = null;
		values_Razmernosti = null;
		values_Dimension = null;
		masiveTSI = null;
		dataTable = null;

		selectedMetod = null;
		choiseRequest = null;
		user_Redac = null;

		flagNotReadListPokazatel = true;
		flagNotReadListMetody = true;
		viewAddRowButton = false;
		flagIncertedFile = false;
	}

	public static List<Sample> getListSample() {
		return listSample;
	}

	public static void setListSample(List<Sample> listSample) {
		OverallVariables.listSample = listSample;
	}

	public static List<Users> getList_Users() {
		return list_Users;
	}

	public static void setList_Users(List<Users> list_Users) {
		OverallVariables.list_Users = list_Users;
	}

	public static List<String> getList_UsersNameFamilyOIR() {
		return list_UsersNameFamilyOIR;
	}

	public static void setList_UsersNameFamilyOIR(List<String> list_UsersNameFamilyOIR) {
		OverallVariables.list_UsersNameFamilyOIR = list_UsersNameFamilyOIR;
	}

	public static List<String> getList_UsersNameFamilyORHO() {
		return list_UsersNameFamilyORHO;
	}

	public static void setList_UsersNameFamilyORHO(List<String> list_UsersNameFamilyORHO) {
		OverallVariables.list_UsersNameFamilyORHO = list_UsersNameFamilyORHO;
	}

	public static List<String> getListSimbolBasikNulide() {
		return listSimbolBasikNulide;
	}

	public static void setListSimbolBasikNulide(List<String> listSimbolBasikNulide) {
		OverallVariables.listSimbolBasikNulide = listSimbolBasikNulide;
	}

	public static List<String> getListSimbolBasikNulideToMetod() {
		return listSimbolBasikNulideToMetod;
	}

	public static void setListSimbolBasikNulideToMetod(List<String> listSimbolBasikNulideToMetod) {
		OverallVariables.listSimbolBasikNulideToMetod = listSimbolBasikNulideToMetod;
	}

	public static List<IzpitvanPokazatel> getListPokazatel() {
		return listPokazatel;
	}

	public static void setListPokazatel(List<IzpitvanPokazatel> listPokazatel) {
		OverallVariables.listPokazatel = listPokazatel;
	}

	public static List<Dobiv> getListDobivFromMetod() {
		return listDobivFromMetod;
	}

	public static void setListDobivFromMetod(List<Dobiv> listDobivFromMetod) {
		OverallVariables.listDobivFromMetod = listDobivFromMetod;
	}

	public static List<Nuclide_to_Pokazatel> getListNucToPok() {
		return listNucToPok;
	}

	public static void setListNucToPok(List<Nuclide_to_Pokazatel> listNucToPok) {
		OverallVariables.listNucToPok = listNucToPok;
	}

	public static List<Results> getListResultsFromDBase() {
		return ListResultsFromDBase;
	}

	public static void setListResultsFromDBase(List<Results> listResultsFromDBase) {
		ListResultsFromDBase = listResultsFromDBase;
	}

	public static List<Results> getResultListForSave() {
		return resultListForSave;
	}

	public static void setResultListForSave(List<Results> resultListForSave) {
		OverallVariables.resultListForSave = resultListForSave;
	}

	public static List<Results> getResultListForDelete() {
		return resultListForDelete;
	}

	public static void setResultListForDelete(List<Results> resultListForDelete) {
		OverallVariables.resultListForDelete = resultListForDelete;
	}

	public static List<Destruct_Result> getDestruct_Result_List() {
		return destruct_Result_List;
	}

	public static void setDestruct_Result_List(List<Destruct_Result> destruct_Result_List) {
		OverallVariables.destruct_Result_List = destruct_Result_List;
	}

	public static String[] getMasuveSimbolNuclide() {
		return masuveSimbolNuclide;
	}

	public static void setMasuveSimbolNuclide(String[] masuveSimbolNuclide) {
		OverallVariables.masuveSimbolNuclide = masuveSimbolNuclide;
	}

	public static String[] getMasive_NuclideToPokazatel() {
		return masive_NuclideToPokazatel;
	}

	public static void setMasive_NuclideToPokazatel(String[] masive_NuclideToPokazatel) {
		OverallVariables.masive_NuclideToPokazatel = masive_NuclideToPokazatel;
	}

	public static String[] getValues_Razmernosti() {
		return values_Razmernosti;
	}

	public static void setValues_Razmernosti(String[] values_Razmernosti) {
		OverallVariables.values_Razmernosti = values_Razmernosti;
	}

	public static String[] getValues_Dimension() {
		return values_Dimension;
	}

	public static void setValues_Dimension(String[] values_Dimension) {
		OverallVariables.values_Dimension = values_Dimension;
	}

	public static String[] getMasiveTSI() {
		return masiveTSI;
	}

	public static void setMasiveTSI(String[] masiveTSI) {
		OverallVariables.masiveTSI = masiveTSI;
	}

	public static Object[][] getDataTable() {
		return dataTable;
	}

	public static void setDataTable(Object[][] dataTable) {
		OverallVariables.dataTable = dataTable;
	}

	public static Metody getSelectedMetod() {
		return selectedMetod;
	}

	public static void setSelectedMetod(Metody selectedMetod) {
		OverallVariables.selectedMetod = selectedMetod;
	}

	public static Request getChoiseRequest() {
		return choiseRequest;
	}

	public static void setChoiseRequest(Request choiseRequest) {
		OverallVariables.choiseRequest = choiseRequest;
	}

	public static Users getUser_Redac() {
		return user_Redac;
	}

	public static void setUser_Redac(Users user_Redac) {
		OverallVariables.user_Redac = user_Redac;
	}

	public static Boolean getFlagNotReadListPokazatel() {
		return flagNotReadListPokazatel;
	}

	public static void setFlagNotReadListPokazatel(Boolean flagNotReadListPokazatel) {
		OverallVariables.flagNotReadListPokazatel = flagNotReadListPokazatel;
	}

	public static Boolean getFlagNotReadListMetody() {
		return flagNotReadListMetody;
	}

	public static void setFlagNotReadListMetody(Boolean flagNotReadListMetody) {
		OverallVariables.flagNotReadListMetody = flagNotReadListMetody;
	}

	public static Boolean getViewAddRowButton() {
		return viewAddRowButton;
	}

	public static void setViewAddRowButton(Boolean viewAddRowButton) {
		OverallVariables.viewAddRowButton = viewAddRowButton;
	}

	public static Boolean getFlagIncertedFile() {
		return flagIncertedFile;
	}

	public static void setFlagIncertedFile(Boolean flagIncertedFile) {
		OverallVariables.flagIncertedFile = flagIncertedFile;
	}

}
