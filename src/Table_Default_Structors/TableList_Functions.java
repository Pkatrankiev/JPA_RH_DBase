package Table_Default_Structors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
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
import GlobalVariable.ReadFileWithGlobalTextVariable;
import OldClases.Table_RequestToObektNaIzp;
import Table.RequestTableList_OverallVariables;
import Table.RequestTableMouseListener;
import Table_Results.DefauiltResultsTableMouseListener;
import Table_Results.ResultsTableList_Functions;
import Table_Results.ResultsTableList_OverallVariables;
import WindowView.DateChoice_period;
import WindowView.DatePicker;
import WindowView.RequestViewAplication;
import WindowView.RequestViewFunction;
import WindowView.TranscluentWindow;

public class TableList_Functions {

	
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
	
	public static String reformatString(String name_Column) {
		return name_Column.replace("<html>", "").replace("<br>", " ").replace("</html>", "").replace("_", "");
	}

	@SuppressWarnings("static-access")
	public static void OverallVariablesForResultstTable(ResultsTableList_OverallVariables objectTableList_OverallVariables, Users user, Boolean firstLoad) {
		if (firstLoad) {
			
			switch (objectTableList_OverallVariables.getTipe_Table()) {
			
					case "request":
						objectTableList_OverallVariables.setDataTable(getDataTableRequest(objectTableList_OverallVariables));
						objectTableList_OverallVariables
						.setValues_O_I_R(Obekt_na_izpitvane_requestDAO.getListStringAllValueObekt_na_izpitvane());
						break;
			
					case "Results":
						objectTableList_OverallVariables.setDataTable(getDataTableResults(null));
						break;
			
					}
			
			
		
			
			objectTableList_OverallVariables.setListRowForUpdate(new ArrayList<Integer>());
			objectTableList_OverallVariables.setListAllUsers(UsersDAO.getInListAllValueUsers());
			objectTableList_OverallVariables.setUser(user);
			

		}
	}
	
	@SuppressWarnings("static-access")
	private static Object[][] getDataTableRequest(ResultsTableList_OverallVariables objectTableList_OverallVariables) {

		Map<String, TableObject_Class> mapListForChangedStrObektNaIzp = objectTableList_OverallVariables
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

	
	private static Object[][] getDataTableResults(Request BasicRequest) {
		Map<String, TableObject_Class> mapTableObject = ResultsTableList_OverallVariables.getMap_TableObject_Class();
		int tbl_Colum = mapTableObject.size();
		List<Request> listAllRequest = new ArrayList<Request>();
		if (BasicRequest != null) {
			listAllRequest.add(BasicRequest);
		} else {
			listAllRequest = RequestDAO.getInListAllValueRequest();
		}
		List<Sample> listAllSample = SampleDAO.getInListAllValueSample();
		List<Results> listAllResults = ResultsDAO.getInListAllValueResults();
		Object[][] tableResult = new Object[listAllResults.size()][tbl_Colum];
		int i = 0;

		for (Request request : listAllRequest) {

			for (Sample sample : listAllSample) {
				if (sample.getRequest().getId_recuest() == request.getId_recuest()) {

					for (Results results : listAllResults) {
						try {
							if (results.getSample().getId_sample() == sample.getId_sample()) {

								int request_code = Integer.parseInt(results.getSample().getRequest().getRecuest_code());
								tableResult[i][mapTableObject.get("NumberRequest").getNumberColum()] = request_code;
								tableResult[i][mapTableObject.get("CodeSample").getNumberColum()] = sample
										.getSample_code();
								tableResult[i][mapTableObject.get("Object_Sample").getNumberColum()] = sample
										.getObekt_na_izpitvane_sample().getName_obekt_na_izpitvane();
								tableResult[i][mapTableObject.get("MetodNaIzpitvane").getNumberColum()] = results
										.getMetody().getCode_metody();
								tableResult[i][mapTableObject.get("IzpitvanPokazatel").getNumberColum()] = results
										.getPokazatel().getName_pokazatel();
								tableResult[i][mapTableObject.get("Nuclide").getNumberColum()] = results.getNuclide()
										.getSymbol_nuclide();

								tableResult[i][mapTableObject.get("Ativity").getNumberColum()] = results
										.getValue_result();
								tableResult[i][mapTableObject.get("Uncertainty").getNumberColum()] = results
										.getUncertainty();
								tableResult[i][mapTableObject.get("Sigma").getNumberColum()] = results.getSigma();
								tableResult[i][mapTableObject.get("MDA").getNumberColum()] = results.getMda();
								tableResult[i][mapTableObject.get("razmer").getNumberColum()] = results.getRazmernosti()
										.getName_razmernosti();
								tableResult[i][mapTableObject.get("Quantity").getNumberColum()] = results.getQuantity();
								tableResult[i][mapTableObject.get("Dimension").getNumberColum()] = "";
								if (results.getDimension() != null) {
									tableResult[i][mapTableObject.get("Dimension").getNumberColum()] = results
											.getDimension().getName_dimension();
								}

								tableResult[i][mapTableObject.get("Nuclide_Dobiv").getNumberColum()] = "";
								tableResult[i][mapTableObject.get("Dobiv").getNumberColum()] = 0.0;
								if (results.getDobiv() != null) {
									tableResult[i][mapTableObject.get("Nuclide_Dobiv").getNumberColum()] = results
											.getDobiv().getNuclide().getSymbol_nuclide();
									tableResult[i][mapTableObject.get("Dobiv").getNumberColum()] = results.getDobiv()
											.getValue_result();
								}

								tableResult[i][mapTableObject.get("InProtokol").getNumberColum()] = results
										.getInProtokol();
								tableResult[i][mapTableObject.get("Id_Results").getNumberColum()] = results
										.getId_results();

								i++;

							}
						} catch (NullPointerException e) {
							JOptionPane.showInputDialog(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
									.get("errorDataForResult_Text"), JOptionPane.ERROR_MESSAGE);
						} catch (NumberFormatException e) {
							JOptionPane.showInputDialog(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
									.get("errorDataForResult_Text"), JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		}

		Object[][] tableSampleNew = new Object[i][tbl_Colum];
		for (int j = 0; j < tableSampleNew.length; j++) {
			for (int k = 0; k < tbl_Colum; k++) {
				tableSampleNew[j][k] = tableResult[j][k];
			}
		}

		return tableSampleNew;
	}

	public static boolean check_ChangedVisibleColumn(List<String> list_StringChoisedVisibleColumn) {
		if (list_StringChoisedVisibleColumn == null) {
			return false;
		}
		Collection<String> listOne = extractNameInColumn(ResultsTableList_OverallVariables.getList_TableColumn());
		Collection<String> listTwo = list_StringChoisedVisibleColumn;

		List<String> sourceList = new ArrayList<String>(listOne);
		List<String> destinationList = new ArrayList<String>(listTwo);

		sourceList.removeAll(listTwo);
		destinationList.removeAll(listOne);

		System.out.println(sourceList);
		System.out.println(destinationList);
		if (sourceList.size() + destinationList.size() > 0) {
			return true;
		}

		return false;
	}
	
	private static List<String> extractNameInColumn(List<TableColumn> list_TableColumn) {
		List<String> list = new ArrayList<>();
		for (TableColumn tableColumn : list_TableColumn) {
			if (tableColumn.getInVisible()) {
				list.add(reformatString(tableColumn.getName_Column()));
			}
		}
		return list;
	}

	public static boolean check_ChangedVisibleColumn(List<TableColumn> list_StringChoisedVisibleColumn, List<TableColumn> list_TableColumnFromDBase) {
		if (list_StringChoisedVisibleColumn == null) {
			return false;
		}
		Collection<String> listOne = extractNameInColumn(list_TableColumnFromDBase);
		Collection<String> listTwo = extractNameInColumn(list_StringChoisedVisibleColumn);

		List<String> sourceList = new ArrayList<String>(listOne);
		List<String> destinationList = new ArrayList<String>(listTwo);

		sourceList.removeAll(listTwo);
		destinationList.removeAll(listOne);

		System.out.println(sourceList);
		System.out.println(destinationList);
		if (sourceList.size() + destinationList.size() > 0) {
			return true;
		}

		return false;
	}
	
	public static void updateDataFor_RequestTalbeList(ResultsTableList_OverallVariables objectTableList_OverallVariables, JTable table, List<Integer> listStrRequestCodeForUpdate, TranscluentWindow round) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		int rqst_code_Colum = DefauiltResultsTableMouseListener.getIndexColumnByKeyMap(objectTableList_OverallVariables, "rqst_code");
		Map<Integer, List<String>> mapListForChangedStrObektNaIzp = RequestTableList_OverallVariables
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
		round.StopWindow();
	}
	
	private static List_izpitvan_pokazatel[][] updateIzpitvanPokazatelObject(ResultsTableList_OverallVariables objectTableList_OverallVariables, JTable table, int row, Request request) {
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

	private static void updateRequestObject(ResultsTableList_OverallVariables objectTableList_OverallVariables,  JTable table, int row, Request request) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String str_rqst_Date = model.getValueAt(row, DefauiltResultsTableMouseListener.getIndexColumnByKeyMap(objectTableList_OverallVariables, "rqst_Date"))
				.toString();
		str_rqst_Date = reformatDate(str_rqst_Date);

		request.setDate_request(str_rqst_Date);
		request.setInd_num_doc(Ind_num_docDAO.getValueIByName(
				model.getValueAt(row, DefauiltResultsTableMouseListener.getIndexColumnByKeyMap(objectTableList_OverallVariables, "id_ND")).toString()));
		request.setIzpitvan_produkt(Izpitvan_produktDAO.getValueIzpitvan_produktByName(
				model.getValueAt(row, DefauiltResultsTableMouseListener.getIndexColumnByKeyMap(objectTableList_OverallVariables,"izp_Prod")).toString()));

		request.setRazmernosti(RazmernostiDAO.getValueRazmernostiByName(
				model.getValueAt(row, DefauiltResultsTableMouseListener.getIndexColumnByKeyMap(objectTableList_OverallVariables,"razmer")).toString()));
		request.setCounts_samples(
				(int) model.getValueAt(row, DefauiltResultsTableMouseListener.getIndexColumnByKeyMap(objectTableList_OverallVariables,"cunt_Smpl")));
		request.setDescription_sample_group(
				model.getValueAt(row, DefauiltResultsTableMouseListener.getIndexColumnByKeyMap(objectTableList_OverallVariables,"dscr_Smpl")).toString());

		String str_exec_Date = model.getValueAt(row, DefauiltResultsTableMouseListener.getIndexColumnByKeyMap(objectTableList_OverallVariables,"exec_Date"))
				.toString();
		str_exec_Date = reformatDate(str_exec_Date);
		request.setDate_execution(str_exec_Date);

		String str_recept_Date = model.getValueAt(row, DefauiltResultsTableMouseListener.getIndexColumnByKeyMap(objectTableList_OverallVariables,"rcpt_Date"))
				.toString();
		str_recept_Date = DateChoice_period.reformatPeriodDateFromTable(str_recept_Date);
		request.setDate_reception(str_recept_Date);
		request.setAccreditation(
				(Boolean) model.getValueAt(row, DefauiltResultsTableMouseListener.getIndexColumnByKeyMap(objectTableList_OverallVariables,"in_Acredit")));

		request.setUsers(getUserByName(objectTableList_OverallVariables, 
				model.getValueAt(row, DefauiltResultsTableMouseListener.getIndexColumnByKeyMap(objectTableList_OverallVariables,"user")).toString()));

		request.setZabelejki(ZabelejkiDAO.getValueZabelejkiByName(
				model.getValueAt(row, DefauiltResultsTableMouseListener.getIndexColumnByKeyMap(objectTableList_OverallVariables,"zab")).toString()));

		RequestDAO.updateObjectRequest(request);
	}

	private static String reformatDate(String strDate) {
		strDate = DatePicker.reformatFromTabDate(strDate, false);
		return strDate;
	}

	@SuppressWarnings("static-access")
	private static Users getUserByName(ResultsTableList_OverallVariables objectTableList_OverallVariables,String nameUser) {

		for (Users user : objectTableList_OverallVariables.getListAllUsers()) {
			if (nameUser.substring(0, nameUser.indexOf(" ")).equals(user.getName_users())
					&& nameUser.substring(nameUser.indexOf(" ") + 1).equals(user.getFamily_users())) {
				return user;
			}
		}
		return null;
	}
	
	private static List<String> ReadListPokazatelInCell(ResultsTableList_OverallVariables objectTableList_OverallVariables, JTable table, int row) {
		List<String> list = new ArrayList<String>();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String strPokazatel = model.getValueAt(row, DefauiltResultsTableMouseListener.getIndexColumnByKeyMap(objectTableList_OverallVariables,"izp_Pok"))
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
	
	public static List<String> getMasiveFromNameVISIBLEColumn(List<TableColumn> list_TableColumn) {
		List<String> list_InvisibleTableColumn = new ArrayList<>();
		for (TableColumn tableColumn : list_TableColumn) {
			if (tableColumn.getInVisible()) {
				list_InvisibleTableColumn.add(reformatString(tableColumn.getName_Column()));
			}
		}
		
		return list_InvisibleTableColumn;

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
	
	public static  int[] getMasiveIndexColumnFromMasiveNameColumn(ResultsTableList_OverallVariables objectTableList_OverallVariables, String[] masiveNameColumn){
		int[] arr = new int[masiveNameColumn.length];
		
		for (int i = 0; i < masiveNameColumn.length; i++) {
			System.out.println("masiveNameColumn["+i+"] "+masiveNameColumn[i]);
			arr[i] = getModdelIndexColumnByColumnName(objectTableList_OverallVariables, masiveNameColumn[i]);
			System.out.println("indexColumn "+arr[i]);
		}
		
		return arr;
		
	}
	
	@SuppressWarnings("static-access")
	public static int getModdelIndexColumnByColumnName(ResultsTableList_OverallVariables objectTableList_OverallVariables, String columnName) {
		for (TableObject_Class object : objectTableList_OverallVariables.getList_TableObject_Class()) {
			if (TableList_Functions.reformatString(object.getColumName_Header()).equalsIgnoreCase(columnName)) {
				return object.getNumberColum();
			}
		}
		return -1;
	}
	
	public static ArrayList<String> getListString_NameColumn(List<TableColumn> list_TableColumn) {
		ArrayList<String> listStringName = new ArrayList<>();
		for (TableColumn tableColumn : list_TableColumn) {
			listStringName.add(reformatString(tableColumn.getName_Column()));

		}
		return listStringName;
	}
}
