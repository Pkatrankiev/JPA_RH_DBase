package Table_Sample;

import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.RequestDAO;
import Aplication.SampleDAO;
import DBase_Class.Request_To_ObektNaIzpitvaneRequest;
import DBase_Class.Sample;
import DefaultTableList.TableList_Functions;
import DefaultTableList.TableList_OverallVariables;
import DefaultTableList.TableObject_Class;
import Table_Request.Table_RequestToObektNaIzp;
import WindowView.TranscluentWindow;

public class UpdateDataFor_SampleTalbeList {
	@SuppressWarnings("static-access")
	public	static void updateData(TableList_OverallVariables objectTableList_OverallVariables, JTable table,  
			TranscluentWindow round) {
		
		List<Integer> listWhithChangeRow = objectTableList_OverallVariables.getListRowForUpdate();
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Map<String, TableObject_Class> mapTableObject = TableList_OverallVariables.getMap_TableObject_Class();
		for (int changeRow : listWhithChangeRow) {
			Sample sample = SampleDAO.getValueSampleById(
					(int) model.getValueAt(changeRow, mapTableObject.get("Id_Sample").getNumberColum()));
			udateSampleObject(objectTableList_OverallVariables, table, changeRow, sample);
		}
		round.StopWindow();
	}
	
		private static void udateSampleObject(TableList_OverallVariables objectTableList_OverallVariables, JTable table, 
				int row, Sample sample) {
			
			DefaultTableModel model = (DefaultTableModel) table.getModel();
//			

				sample.setDate_time_reference(model.getValueAt(row,  TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "ref_Date")).toString());
				sample.setDescription_sample(model.getValueAt(row,  TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "dscr_Sam_Smpl")).toString());
				sample.setGodina_period((int) model.getValueAt(row,  TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "Godina_Period")));
				sample.setSample_code(model.getValueAt(row,  TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "CodeSample")).toString());
				Request_To_ObektNaIzpitvaneRequest requestTo_O_I_R = Table_RequestToObektNaIzp
						.getRequest_To_ObektNaIzpitvaneRequest(sample.getRequest(),
								model.getValueAt(row,  TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "obk_Izp")).toString());
				sample.setRequest_to_obekt_na_izpitvane_request(requestTo_O_I_R);
				sample.setObekt_na_izpitvane_sample(Obekt_na_izpitvane_sampleDAO.getValueObekt_na_izpitvane_sampleByName(
						model.getValueAt(row,  TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "Object_Sample")).toString()));
				String strPeriod = model.getValueAt(row,  TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "Period")).toString();
				if (strPeriod.equals("")) {
					sample.setPeriod(null);
				} else {
					sample.setPeriod(PeriodDAO.getValuePeriodByPeriod(strPeriod));
				}
				sample.setRequest(RequestDAO.getRequestFromColumnByVolume("recuest_code",
						model.getValueAt(row,  TableList_Functions.getIndexColumnByKeyMap(objectTableList_OverallVariables, "NumberRequest")).toString()));

				SampleDAO.updateSample(sample);
			}
		
		
		

		
		
	
		
	
}
