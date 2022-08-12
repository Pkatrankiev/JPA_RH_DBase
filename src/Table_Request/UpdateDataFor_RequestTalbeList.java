package Table_Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Aplication.Ind_num_docDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import Aplication.ZabelejkiDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import DefaultTableList.TableList_Functions;
import DefaultTableList.TableList_OverallVariables;
import WindowView.DateChoice_period;
import WindowView.DatePicker;
import WindowView.TranscluentWindow;


public class UpdateDataFor_RequestTalbeList {
	
	@SuppressWarnings("static-access")
	public static void updateDataFor_RequestTalbeList(TableList_OverallVariables objectTableList_OverallVariables, JTable table, TranscluentWindow round) {
		List<Integer> listStrRequestCodeForUpdate = objectTableList_OverallVariables.getListRowForUpdate();
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		int rqst_code_Colum = TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "rqst_code");
		Map<Integer, List<String>> mapListForChangedStrObektNaIzp = objectTableList_OverallVariables
				.getMapListForChangedStrObektNaIzp();
		for (int rowForUpdate : listStrRequestCodeForUpdate) {
			Request request = RequestDAO.getRequestFromColumnByVolume("recuest_code",
					model.getValueAt(rowForUpdate, rqst_code_Colum));

			List_izpitvan_pokazatel[][] list_Masive_L_I_P = updateIzpitvanPokazatelObject(objectTableList_OverallVariables, table, rowForUpdate, request);
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
			updateRequestObject(objectTableList_OverallVariables, table, rowForUpdate, request);

		}
//		round.StopWindow();
	}
	
	private static List_izpitvan_pokazatel[][] updateIzpitvanPokazatelObject(TableList_OverallVariables objectTableList_OverallVariables, JTable table, int row, Request request) {
		List<IzpitvanPokazatel> listIzpitvanPokazatelBase = IzpitvanPokazatelDAO
				.getValueIzpitvan_pokazatelByRequest(request);
		List_izpitvan_pokazatel[][] list_Masive_L_I_P = new List_izpitvan_pokazatel[listIzpitvanPokazatelBase
				.size()][2];
		int m = 0;
		for (String l_I_P : ReadListPokazatelInCell(objectTableList_OverallVariables, table, row)) {
			list_Masive_L_I_P[m][0] = listIzpitvanPokazatelBase.get(m).getPokazatel();
			list_Masive_L_I_P[m][1] = List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(l_I_P);
			listIzpitvanPokazatelBase.get(m).setPokazatel(list_Masive_L_I_P[m][1]);
			IzpitvanPokazatelDAO.updateObjectIzpitvanPokazatel(listIzpitvanPokazatelBase.get(m));
			m++;
		}
		return list_Masive_L_I_P;
	}

	private static void updateRequestObject(TableList_OverallVariables objectTableList_OverallVariables,  JTable table, int row, Request request) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String str_rqst_Date = model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "rqst_Date"))
				.toString();
		str_rqst_Date = reformatDate(str_rqst_Date);

		request.setDate_request(str_rqst_Date);
		request.setInd_num_doc(Ind_num_docDAO.getValueIByName(
				model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "id_ND")).toString()));
		request.setIzpitvan_produkt(Izpitvan_produktDAO.getValueIzpitvan_produktByName(
				model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,"izp_Prod")).toString()));

		request.setRazmernosti(RazmernostiDAO.getValueRazmernostiByName(
				model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,"razmer")).toString()));
		request.setCounts_samples(
				(int) model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,"cunt_Smpl")));
		request.setDescription_sample_group(
				model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,"dscr_Smpl")).toString());

		String str_exec_Date = model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,"exec_Date"))
				.toString();
		str_exec_Date = reformatDate(str_exec_Date);
		request.setDate_execution(str_exec_Date);

		String str_recept_Date = model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,"rcpt_Date"))
				.toString();
		str_recept_Date = DateChoice_period.reformatPeriodDateFromTable(str_recept_Date);
		request.setDate_reception(str_recept_Date);
		request.setAccreditation(
				(Boolean) model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,"in_Acredit")));

		request.setUsers(getUserByName(objectTableList_OverallVariables, 
				model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,"user")).toString()));

		request.setZabelejki(ZabelejkiDAO.getValueZabelejkiByName(
				model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,"zab")).toString()));

		RequestDAO.updateObjectRequest(request);
	}

	private static String reformatDate(String strDate) {
		strDate = DatePicker.reformatFromTabDate(strDate, false);
		return strDate;
	}

	@SuppressWarnings("static-access")
	private static Users getUserByName(TableList_OverallVariables objectTableList_OverallVariables,String nameUser) {

		for (Users user : objectTableList_OverallVariables.getListAllUsers()) {
			if (nameUser.substring(0, nameUser.indexOf(" ")).equals(user.getName_users())
					&& nameUser.substring(nameUser.indexOf(" ") + 1).equals(user.getFamily_users())) {
				return user;
			}
		}
		return null;
	}
	
	private static List<String> ReadListPokazatelInCell(TableList_OverallVariables objectTableList_OverallVariables, JTable table, int row) {
		List<String> list = new ArrayList<String>();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String strPokazatel = model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables,"izp_Pok"))
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
	
	

}
