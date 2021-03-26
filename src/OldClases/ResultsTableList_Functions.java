package OldClases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Aplication.DimensionDAO;
import Aplication.IzpitvanPokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.NuclideDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import Aplication.UsersDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.TableColumn;
import DBase_Class.Users;
import DefaultTableList.TableList_OverallVariables;
import DefaultTableList.TableObject_Class;
import GlobalVariable.ReadFileWithGlobalTextVariable;
import WindowView.DateChoice_period;
import WindowView.DatePicker;
import WindowView.RequestViewAplication;
import WindowView.RequestViewFunction;
import WindowView.TranscluentWindow;

public class ResultsTableList_Functions {

	@SuppressWarnings("static-access")
	public static void OverallVariablesForResultstTable(TableList_OverallVariables objectTableList_OverallVariables, String frame_name, Users user, Boolean firstLoad) {
		if (firstLoad) {
			objectTableList_OverallVariables.setDataTable(getDataTable(null));
			objectTableList_OverallVariables.setListRowForUpdate(new ArrayList<Integer>());
			objectTableList_OverallVariables.setListAllUsers(UsersDAO.getInListAllValueUsers());
			objectTableList_OverallVariables.setUser(user);
			objectTableList_OverallVariables.setFrame_name(frame_name);

		}
	}

	private static Object[][] getDataTable(Request BasicRequest) {
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
	
	public static ArrayList<String> getListString_NameColumn(List<TableColumn> list_TableColumn) {
		ArrayList<String> listStringName = new ArrayList<>();
		for (TableColumn tableColumn : list_TableColumn) {
			listStringName.add(reformatString(tableColumn.getName_Column()));

		}
		return listStringName;
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

	public static List<TableColumn> ghangeInvisibleInTableColunmObject(List<String> list_StringChoisedVisibleColumn,
			List<TableColumn> list_TableColumn) {
		for (TableColumn tableColumn : list_TableColumn) {
			tableColumn.setInVisible(false);
			for (String nameInVisibleColumn : list_StringChoisedVisibleColumn) {
				if (nameInVisibleColumn.equals(reformatString(tableColumn.getName_Column()))) {
					tableColumn.setInVisible(true);
				}
			}
		}
		return list_TableColumn;
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

	public static boolean check_ChangedVisibleColumn2(List<String> list_StringChoisedVisibleColumn) {
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

	public static List<String> getMasiveFromNameVISIBLEColumn(List<TableColumn> list_TableColumn) {
		List<String> list_InvisibleTableColumn = new ArrayList<>();
		for (TableColumn tableColumn : list_TableColumn) {
			if (tableColumn.getInVisible()) {
				list_InvisibleTableColumn.add(reformatString(tableColumn.getName_Column()));
			}
		}

		return list_InvisibleTableColumn;

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

	public static String reformatString(String name_Column) {
		return name_Column.replace("<html>", "").replace("<br>", " ").replace("</html>", "").replace("_", "");
	}
}
