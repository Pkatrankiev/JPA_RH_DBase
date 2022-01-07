package OldClases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Aplication.Ind_num_docDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import Aplication.UsersDAO;
import Aplication.ZabelejkiDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.TableColumn;
import DBase_Class.Users;
import DefaultTableList.TableObject_Class;
import Table_Request.Table_RequestToObektNaIzp;
import WindowView.DateChoice_period;
import WindowView.DatePicker;
import WindowView.RequestViewAplication;
import WindowView.RequestViewFunction;
import WindowView.TranscluentWindow;
 
public class RequestTableList_Functions {

	public static void OverallVariablesForRequestTable(String frame_name, Users user, Boolean firstLoad) {
		if(firstLoad){
		RequestTableList_OverallVariables.setDataTable(getDataTable());
		RequestTableList_OverallVariables.setListRowForUpdate(new ArrayList<Integer>());
		RequestTableList_OverallVariables.setListAllUsers(UsersDAO.getInListAllValueUsers());
		RequestTableList_OverallVariables.setUser(user);
		RequestTableList_OverallVariables.setFrame_name(frame_name);
		RequestTableList_OverallVariables
				.setValues_O_I_R(Obekt_na_izpitvane_requestDAO.getListStringAllValueObekt_na_izpitvane());
	}
	}

	private static Object[][] getDataTable() {

		Map<String, TableObject_Class> mapListForChangedStrObektNaIzp = RequestTableList_OverallVariables
				.getMap_TableObject_Class();
		int tbl_Colum = mapListForChangedStrObektNaIzp.size();
		List<Request> listRequest = RequestDAO.getInListAllValueRequest();
		Object[][] tableRequest = new Object[listRequest.size()][tbl_Colum];
		int i = 0;
		List<IzpitvanPokazatel> list_All_I_P = IzpitvanPokazatelDAO.getInListAllValueIzpitvan_pokazatel();
		List<Sample> listSample = SampleDAO.getInListAllValueSample();

		for (Request request : listRequest) {

			Integer.parseInt(request.getRecuest_code());
			String[][] masiveSample = RequestViewAplication.getMasiveSampleFromListSampleFromRequest(request,
					listSample);
			tableRequest[i][mapListForChangedStrObektNaIzp.get("rqst_code").getNumberColum()] = request
					.getRecuest_code();

			if (request.getInd_num_doc() != null) {
				tableRequest[i][mapListForChangedStrObektNaIzp.get("id_ND").getNumberColum()] = request.getInd_num_doc()
						.getName();
			} else {
				if (request.getExtra_module() != null) {
					if (request.getExtra_module().getInternal_applicant() != null) {
						tableRequest[i][mapListForChangedStrObektNaIzp.get("id_ND").getNumberColum()] = request
								.getExtra_module().getInternal_applicant().getInternal_applicant_organization();
					} else {
						if (request.getExtra_module().getExternal_applicant() != null) {
							tableRequest[i][mapListForChangedStrObektNaIzp.get("id_ND").getNumberColum()] = request
									.getExtra_module().getExternal_applicant().getExternal_applicant_name();
						}
					}
				}
			}
			tableRequest[i][mapListForChangedStrObektNaIzp.get("rqst_Date").getNumberColum()] = DatePicker
					.formatToTabDate(request.getDate_request(), false);
			// tableRequest[i][rqst_Date_Colum ] =
			// request.getDate_request();
			tableRequest[i][mapListForChangedStrObektNaIzp.get("izp_Prod").getNumberColum()] = request
					.getIzpitvan_produkt().getName_zpitvan_produkt();
			// tableRequest[i][obk_Izp_Colum] =
			// request.getObekt_na_izpitvane_request().getName_obekt_na_izpitvane();

			tableRequest[i][mapListForChangedStrObektNaIzp.get("obk_Izp").getNumberColum()] = request
					.getText_obekt_na_izpitvane_request();

			tableRequest[i][mapListForChangedStrObektNaIzp.get("izp_Pok").getNumberColum()] = RequestViewFunction
					.CreateStringListIzpPokaz(request, list_All_I_P);
			tableRequest[i][mapListForChangedStrObektNaIzp.get("razmer").getNumberColum()] = request.getRazmernosti()
					.getName_razmernosti();
			tableRequest[i][mapListForChangedStrObektNaIzp.get("cunt_Smpl").getNumberColum()] = request
					.getCounts_samples();
			tableRequest[i][mapListForChangedStrObektNaIzp.get("dscr_Smpl").getNumberColum()] = request
					.getDescription_sample_group();
			tableRequest[i][mapListForChangedStrObektNaIzp.get("ref_Date").getNumberColum()] = RequestViewFunction
					.GenerateStringRefDateTimeFromMasiveSample(masiveSample);
			tableRequest[i][mapListForChangedStrObektNaIzp.get("exec_Date").getNumberColum()] = DatePicker
					.formatToTabDate(request.getDate_execution(), false);

			String[] strPeriodDate = DateChoice_period.getMasiveDateFromPeriodString(request.getDate_reception());

			tableRequest[i][mapListForChangedStrObektNaIzp.get("rcpt_Date").getNumberColum()] = DateChoice_period
					.generateStringPeriodDate(true, true, strPeriodDate[0], strPeriodDate[1]);
			;
			tableRequest[i][mapListForChangedStrObektNaIzp.get("user").getNumberColum()] = request.getUsers()
					.getName_users() + " " + request.getUsers().getFamily_users();
			String zab = "";
			if (request.getZabelejki() != null)
				zab = request.getZabelejki().getName_zabelejki();
			tableRequest[i][mapListForChangedStrObektNaIzp.get("zab").getNumberColum()] = zab;
			tableRequest[i][mapListForChangedStrObektNaIzp.get("in_Acredit").getNumberColum()] = request
					.getAccreditation();
			i++;

		}

		return tableRequest;
	}

	public static void updateData(JTable table, List<Integer> listStrRequestCodeForUpdate, TranscluentWindow round) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		int rqst_code_Colum = DefauiltRequestTableMouseListener.getIndexColumnByKeyMap("rqst_code");
		Map<Integer, List<String>> mapListForChangedStrObektNaIzp = RequestTableList_OverallVariables
				.getMapListForChangedStrObektNaIzp();
		for (int rowForUpdate : listStrRequestCodeForUpdate) {
			Request request = RequestDAO.getRequestFromColumnByVolume("recuest_code",
					model.getValueAt(rowForUpdate, rqst_code_Colum));

			List_izpitvan_pokazatel[][] list_Masive_L_I_P = updateIzpitvanPokazatelObject(table, rowForUpdate, request);
			updateIzpitvanPokazatelInResultObject(list_Masive_L_I_P, request);
			if (mapListForChangedStrObektNaIzp != null && !mapListForChangedStrObektNaIzp.isEmpty()) {
				List<Obekt_na_izpitvane_request> listObektIzpit_request = Table_RequestToObektNaIzp
						.creadListStrFromMap(mapListForChangedStrObektNaIzp, rowForUpdate);
				Table_RequestToObektNaIzp.updateRequestToObIzpObject(request, listObektIzpit_request);
				List<String> listStringObektIzpit_request = Table_RequestToObektNaIzp
						.creatListStringfromListObekt_na_izpitvane_request(listObektIzpit_request);
				request.setText_obekt_na_izpitvane_request(
						Table_RequestToObektNaIzp.createStringListObektNaIzp(listStringObektIzpit_request, false));
			}
			updateRequestObject(table, rowForUpdate, request);

		}
		round.StopWindow();
	}

	private static void updateRequestObject(JTable table, int row, Request request) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String str_rqst_Date = model.getValueAt(row, DefauiltRequestTableMouseListener.getIndexColumnByKeyMap("rqst_Date"))
				.toString();
		str_rqst_Date = reformatDate(str_rqst_Date);

		request.setDate_request(str_rqst_Date);
		request.setInd_num_doc(Ind_num_docDAO.getValueIByName(
				model.getValueAt(row, DefauiltRequestTableMouseListener.getIndexColumnByKeyMap("id_ND")).toString()));
		request.setIzpitvan_produkt(Izpitvan_produktDAO.getValueIzpitvan_produktByName(
				model.getValueAt(row, DefauiltRequestTableMouseListener.getIndexColumnByKeyMap("izp_Prod")).toString()));

		request.setRazmernosti(RazmernostiDAO.getValueRazmernostiByName(
				model.getValueAt(row, DefauiltRequestTableMouseListener.getIndexColumnByKeyMap("razmer")).toString()));
		request.setCounts_samples(
				(int) model.getValueAt(row, DefauiltRequestTableMouseListener.getIndexColumnByKeyMap("cunt_Smpl")));
		request.setDescription_sample_group(
				model.getValueAt(row, DefauiltRequestTableMouseListener.getIndexColumnByKeyMap("dscr_Smpl")).toString());

		String str_exec_Date = model.getValueAt(row, DefauiltRequestTableMouseListener.getIndexColumnByKeyMap("exec_Date"))
				.toString();
		str_exec_Date = reformatDate(str_exec_Date);
		request.setDate_execution(str_exec_Date);

		String str_recept_Date = model.getValueAt(row, DefauiltRequestTableMouseListener.getIndexColumnByKeyMap("rcpt_Date"))
				.toString();
		str_recept_Date = DateChoice_period.reformatPeriodDateFromTable(str_recept_Date);
		request.setDate_reception(str_recept_Date);
		request.setAccreditation(
				(Boolean) model.getValueAt(row, DefauiltRequestTableMouseListener.getIndexColumnByKeyMap("in_Acredit")));

		request.setUsers(getUserByName(
				model.getValueAt(row, DefauiltRequestTableMouseListener.getIndexColumnByKeyMap("user")).toString()));

		request.setZabelejki(ZabelejkiDAO.getValueZabelejkiByName(
				model.getValueAt(row, DefauiltRequestTableMouseListener.getIndexColumnByKeyMap("zab")).toString()));

		RequestDAO.updateObjectRequest(request);
	}

	private static Users getUserByName(String nameUser) {

		for (Users user : RequestTableList_OverallVariables.getListAllUsers()) {
			if (nameUser.substring(0, nameUser.indexOf(" ")).equals(user.getName_users())
					&& nameUser.substring(nameUser.indexOf(" ") + 1).equals(user.getFamily_users())) {
				return user;
			}
		}
		return null;
	}

	private static String reformatDate(String strDate) {
		strDate = DatePicker.reformatFromTabDate(strDate, false);
		return strDate;
	}

	private static List_izpitvan_pokazatel[][] updateIzpitvanPokazatelObject(JTable table, int row, Request request) {
		List<IzpitvanPokazatel> listIzpitvanPokazatelBase = IzpitvanPokazatelDAO
				.getValueIzpitvan_pokazatelByRequest(request);
		List_izpitvan_pokazatel[][] list_Masive_L_I_P = new List_izpitvan_pokazatel[listIzpitvanPokazatelBase
				.size()][2];
		int m = 0;
		for (String l_I_P : ReadListPokazatelInCell(table, row)) {
			list_Masive_L_I_P[m][0] = listIzpitvanPokazatelBase.get(m).getPokazatel();
			list_Masive_L_I_P[m][1] = List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(l_I_P);
			listIzpitvanPokazatelBase.get(m).setPokazatel(list_Masive_L_I_P[m][1]);
			IzpitvanPokazatelDAO.updateObjectIzpitvanPokazatel(listIzpitvanPokazatelBase.get(m));
			m++;
		}
		return list_Masive_L_I_P;
	}

	private static List<String> ReadListPokazatelInCell(JTable table, int row) {
		List<String> list = new ArrayList<String>();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String strPokazatel = model.getValueAt(row, DefauiltRequestTableMouseListener.getIndexColumnByKeyMap("izp_Pok"))
				.toString().trim();
		String str = "";
		while (!strPokazatel.isEmpty()) {
			str = strPokazatel.substring(0, strPokazatel.indexOf(";") + 1);
			list.add(str.replaceAll(";", "").trim());
			strPokazatel = strPokazatel.replaceFirst(str, "");
		}
		return list;
	}

	private static void updateIzpitvanPokazatelInResultObject(List_izpitvan_pokazatel[][] list_Masive_L_I_P,
			Request request) {
		List<Sample> listSampleDBase = SampleDAO.getListSampleFromColumnByVolume("request", request);
		for (Sample sampleDBase : listSampleDBase) {
			List<Results> listResults = ResultsDAO.getListResultsFromColumnByVolume("sample", sampleDBase);
			for (Results resultsDBase : listResults) {

				for (int i = 0; i < list_Masive_L_I_P.length; i++) {
					if (resultsDBase.getPokazatel().getName_pokazatel()
							.equals(list_Masive_L_I_P[i][0].getName_pokazatel()))
						resultsDBase.setPokazatel(list_Masive_L_I_P[i][1]);
					ResultsDAO.updateResults(resultsDBase);
				}

			}
		}
	}

	public static List<TableColumn> ghangeInvisibleInTableColunmObject(List<String> list_StringChoisedVisibleColumn, List<TableColumn> list_TableColumn) {
		for (TableColumn tableColumn : list_TableColumn) {
			tableColumn.setInVisible(false);
			for (String nameInVisibleColumn : list_StringChoisedVisibleColumn) {
				if(nameInVisibleColumn.equals(reformatString(tableColumn.getName_Column()))){
					tableColumn.setInVisible(true);
				}
			}
		}
		return list_TableColumn;
	}
	
	public static String[] getMasiveFromNameInvisbleColumn(List<TableColumn> list_TableColumn) {
		List<String> list_InvisibleTableColumn = new ArrayList<>();
		for (TableColumn tableColumn : list_TableColumn) {
			if (!tableColumn.getInVisible()) {
				list_InvisibleTableColumn.add(tableColumn.getName_Column());
			}
		}
		String[] masive = new String[list_InvisibleTableColumn.size()];
		int i = 0;
		for (String string : list_InvisibleTableColumn) {
			masive[i] = reformatString(string);
			i++;
		}
		return masive;
	}

	public static List<String> getMasiveFromNameVISIBLEColumn(List<TableColumn> list_TableColumn) {
		List<String> list_InvisibleTableColumn = new ArrayList<>();
		for (TableColumn tableColumn : list_TableColumn) {
			if (tableColumn.getInVisible()) {
				list_InvisibleTableColumn.add(reformatString(tableColumn.getName_Column()));
			}
		}
		
		return list_InvisibleTableColumn;

	}

	public static boolean check_ChangedVisibleColumn(List<String> list_StringChoisedVisibleColumn) {
		List<TableColumn> list_TableColumn = RequestTableList_OverallVariables.getList_TableColumn();
		boolean fl = true;
		for (TableColumn tableColumn : list_TableColumn) {
			fl = false;
			if (tableColumn.getInVisible()) {
			for (String nameVisibleColumn : list_StringChoisedVisibleColumn) {
			System.out.println(reformatString(tableColumn.getName_Column())+" + "+nameVisibleColumn);
			if (reformatString(tableColumn.getName_Column()).contains(nameVisibleColumn)) {
					fl= true;
				}
				
			}
			if(!fl){
				return true;
			}
			}
			
		}

		return false;
	}

	public static boolean check_ChangedVisibleColumn2(List<String> list_StringChoisedVisibleColumn) {
			if(list_StringChoisedVisibleColumn==null){
				return false;
			}
		Collection<String> listOne = extractNameInVisibleColumn();
		Collection<String> listTwo = list_StringChoisedVisibleColumn;


		    List<String> sourceList = new ArrayList<String>(listOne);
		    List<String> destinationList = new ArrayList<String>(listTwo);

		    sourceList.removeAll( listTwo );
		    destinationList.removeAll( listOne );


    System.out.println( sourceList );
    System.out.println( destinationList );
    if(sourceList.size()+destinationList.size()>0){
    	return true;
    }
	
    return false;
	}

	private static List<String> extractNameInVisibleColumn() {
		List<String> list = new ArrayList<>();
for (TableColumn tableColumn : RequestTableList_OverallVariables.getList_TableColumn()) {
		if (tableColumn.getInVisible()) {
			list.add(reformatString(tableColumn.getName_Column()));
		}
}
		return list;
	}
	
	public static String reformatString(String name_Column) {
		return name_Column.replace("<html>", "").replace("<br>", " ").replace("</html>", "").replace("_", "");
	}

	public static List<String> getListString_VizibleColumn(List<TableColumn> list_TableColumn) {
		ArrayList<String> listStringName = new ArrayList<>();
		for (TableColumn tableColumn : list_TableColumn) {
			if (tableColumn.getInVisible()) {
				listStringName.add(reformatString(tableColumn.getName_Column()));
			}
		}
		return listStringName;
	}

	public static ArrayList<String> getListString_NameColumn(List<TableColumn> list_TableColumn) {
		ArrayList<String> listStringName = new ArrayList<>();
		for (TableColumn tableColumn : list_TableColumn) {
			listStringName.add(reformatString(tableColumn.getName_Column()));

		}
		return listStringName;
	}

}
