package Table_Results;

import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Aplication.DimensionDAO;
import Aplication.DobivDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.NuclideDAO;
import Aplication.RazmernostiDAO;
import Aplication.ResultsDAO;
import DBase_Class.Results;
import DefaultTableList.TableList_Functions;
import DefaultTableList.TableList_OverallVariables;
import DefaultTableList.TableObject_Class;
import WindowView.TranscluentWindow;

public class UpdateDataFor_ResultsTalbeList {


	@SuppressWarnings("static-access")
	public	static void updateData(TableList_OverallVariables objectTableList_OverallVariables, JTable table,  TranscluentWindow round) {
	
		List<Integer> listWhithChangeRow = objectTableList_OverallVariables.getListRowForUpdate();
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Map<String, TableObject_Class> mapTableObject = TableList_OverallVariables.getMap_TableObject_Class();
		for (int changeRow : listWhithChangeRow) {
			Results result = ResultsDAO.getValueResultsById(
					(int) model.getValueAt(changeRow, mapTableObject.get("Id_Results").getNumberColum()));
			udateResultObject(objectTableList_OverallVariables, table, changeRow, result);
		}
		round.StopWindow();
	}

	private static void udateResultObject(TableList_OverallVariables objectTableList_OverallVariables, JTable table, int row, Results result) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		result.setMetody(MetodyDAO.getValueList_MetodyByCode(
				model.getValueAt(row,  TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "MetodNaIzpitvane")).toString()));
		 result.setPokazatel(List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName((String) ( model.getValueAt
				 (row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "IzpitvanPokazatel")))));
		result.setNuclide(NuclideDAO.getValueNuclideBySymbol(
				(String) model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "Nuclide")).toString()));
		 result.setValue_result((Double) model.getValueAt(row,TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "Ativity")));
		 result.setUncertainty((Double) model.getValueAt(row,TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "Uncertainty")));
		 result.setSigma((Integer) model.getValueAt(row,  TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "Sigma")));
		 result.setMda((Double) model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "MDA")));
		 result.setRazmernosti(RazmernostiDAO.getValueRazmernostiByName((String) model.getValueAt(row, 
				 TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "razmer"))));
		 result.setQuantity((Double) model.getValueAt(row,TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "Quantity")));
		
		 if ((model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "Dimension")).equals(""))) {
		 result.setDimension(null);
		 } else {
		 result.setDimension(DimensionDAO.getValueDimensionByName((String) model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "Dimension"))));
		 }
		 result.setInProtokol((Boolean) model.getValueAt(row, TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "InProtokol")));
		 
		 result.setDobiv(DobivDAO.getDobivById((int) model.getValueAt(row,TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "Id_Dobiv"))));
		 
		 
		 
		 ResultsDAO.updateResults(result);
		
		
	}

	
}
