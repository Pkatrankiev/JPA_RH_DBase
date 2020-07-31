package DefaultTableList;

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
import Table_Request.Table_RequestToObektNaIzp;
import Table_Results.ResultsTableMouseListener;
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
	public static void SetDataInOverallVariablesForTable(TableList_OverallVariables objectTableList_OverallVariables, Users user, Boolean firstLoad, Request choisetRequest) {
		if (firstLoad) {
			
			switch (objectTableList_OverallVariables.getTipe_Table()) {
			
					case "request":
						objectTableList_OverallVariables.setDataTable(getDataTableRequest(objectTableList_OverallVariables));
						objectTableList_OverallVariables
						.setValues_O_I_R(Obekt_na_izpitvane_requestDAO.getListStringAllValueObekt_na_izpitvane());
						break;
			
					case "Results":
						objectTableList_OverallVariables.setDataTable(getDataTableResults(choisetRequest));
						break;
			
					}
			
			
		
			
			objectTableList_OverallVariables.setListRowForUpdate(new ArrayList<Integer>());
			objectTableList_OverallVariables.setListAllUsers(UsersDAO.getInListAllValueUsers());
			objectTableList_OverallVariables.setUser(user);
			

		}
	}
	
	@SuppressWarnings("static-access")
	private static Object[][] getDataTableRequest(TableList_OverallVariables objectTableList_OverallVariables) {

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
		Map<String, TableObject_Class> mapTableObject = TableList_OverallVariables.getMap_TableObject_Class();
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
								tableResult[i][mapTableObject.get("result_Dobiv").getNumberColum()] = 0.0;
								tableResult[i][mapTableObject.get("Id_Dobiv").getNumberColum()] =0;
								
								if (results.getDobiv() != null) {
									tableResult[i][mapTableObject.get("Nuclide_Dobiv").getNumberColum()] = results
											.getDobiv().getNuclide().getSymbol_nuclide();
									tableResult[i][mapTableObject.get("result_Dobiv").getNumberColum()] = results.getDobiv()
											.getValue_result();
									tableResult[i][mapTableObject.get("Id_Dobiv").getNumberColum()] = results.getDobiv().getId_dobiv();
								}

								tableResult[i][mapTableObject.get("InProtokol").getNumberColum()] = results
										.getInProtokol();
								tableResult[i][mapTableObject.get("Id_Results").getNumberColum()] = results
										.getId_results();
								
								i++;

							}
						} catch (NullPointerException e) {
							System.out.println("************************************");
							JOptionPane.showInputDialog(ReadFileWithGlobalTextVariable.getGlobalTextVariableMap()
									.get("errorDataForResult_Text"), JOptionPane.ERROR_MESSAGE);
						} 
						catch (NumberFormatException e) {
							System.out.println("/////////////////////////////////////");
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
		Collection<String> listOne = extractNameInColumn(TableList_OverallVariables.getList_TableColumn());
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
	
	public static  int[] getMasiveIndexColumnFromMasiveNameColumn(TableList_OverallVariables objectTableList_OverallVariables, String[] masiveNameColumn){
		int[] arr = new int[masiveNameColumn.length];
		
		for (int i = 0; i < masiveNameColumn.length; i++) {
			System.out.println("masiveNameColumn["+i+"] "+masiveNameColumn[i]);
			arr[i] = getModdelIndexColumnByColumnName(objectTableList_OverallVariables, masiveNameColumn[i]);
			System.out.println("indexColumn "+arr[i]);
		}
		
		return arr;
		
	}
	
	@SuppressWarnings("static-access")
	public static int getModdelIndexColumnByColumnName(TableList_OverallVariables objectTableList_OverallVariables, String columnName) {
		
		System.out.println("ssssssssssssss "+objectTableList_OverallVariables.getList_TableObject_Class());
		
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

	@SuppressWarnings("static-access")
	public static int getIndexColumnByKeyMap(TableList_OverallVariables objectTableList_OverallVariables,String keyMap) {
		Map<String, TableObject_Class> map_TableObject_Class = objectTableList_OverallVariables
				.getMap_TableObject_Class();
		return map_TableObject_Class.get(keyMap).getNumberColum();
	}
	
	public static int getColumnIndex(JTable table, String columnTitle) {
		int columnCount = table.getColumnCount();
		for (int column = 0; column < columnCount; column++) {
			if (table.getColumnName(column).equalsIgnoreCase(columnTitle)) {
				return column;
			}
		}

		return -1;
	}
		
	@SuppressWarnings({"static-access" })
	public static String getTipeColumnByNameColumn(TableList_OverallVariables objectTableList_OverallVariables, String nameColumn) {
		List<TableObject_Class> map_TableObject_Class = objectTableList_OverallVariables.getList_TableObject_Class();
		for (TableObject_Class tableObject_Class : map_TableObject_Class) {
			if (tableObject_Class.getColumName_Header().equals(nameColumn)) {
				return tableObject_Class.getTipeColumn();
			}
		}
		return null;
	}	

	

}
