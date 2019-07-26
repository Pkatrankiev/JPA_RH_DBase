package CreateWordDocProtocol;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tr;

import Aplication.ResultsDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Results;
import DBase_Class.Sample;
import GlobalVariable.GlobalPathForDocFile;

public class CreateListForMultiTable {

	public static int maxRowInOneTableOnePage = GlobalPathForDocFile.getMaxRowInOneTableOnePage();
	public static int maxRowInFullTableOnePage = GlobalPathForDocFile.getMaxRowInFullTableOnePage();

	public CreateListForMultiTable() {
	}

	public static int[] CreateMasiveForMultiTable(List<Sample> smple_list, List<IzpitvanPokazatel> pokazatel_list,
			int coutRow) {

		String pokaz = pokazatel_list.get(0).getPokazatel().getName_pokazatel();
		Boolean sendOnePokazatel = false;
		int[] numberMergeCells = new int[smple_list.size() + 1];
		int count_numberMergeCells = 0;
		
		if (pokazatel_list.size() == 1) {
			Boolean fl = PokazatelIfGamaOrAlpha(pokaz);
			if (fl) {
				sendOnePokazatel = true;
				coutRow++;
			}
		}
		for (Sample sample : smple_list) {
			numberMergeCells[count_numberMergeCells] = coutRow;
			count_numberMergeCells++;
			List<Results> result_list = ResultsDAO.getListResultsFromColumnByVolume("sample", sample);
				if (!sendOnePokazatel) {
					coutRow++;
				}
				if(result_list.size()==1){
					coutRow--;
				}
				for (Results result : result_list) {

					if (result.getInProtokol()) {

						coutRow++;
					}

				}
				
		}
		numberMergeCells[count_numberMergeCells] = coutRow;
		
		for (int i = 0; i < numberMergeCells.length; i++) {
			System.out.println("numberMergeCells["+i+"]= " + numberMergeCells[i]);
		}
		
	
		return numberMergeCells;

	}

	
		
	public static int lastIndexSampleForFirstTab(List<Sample> listAllSamp) {
		List<Sample> listSampl = new ArrayList<Sample>();
		int[] countResultsInSample = new int[listAllSamp.size()];
		int countAllResults=0;
		for (int i = 0; i < countResultsInSample.length; i++) {
			countResultsInSample[i] = ResultsDAO.getListResultsFromCurentSampleInProtokol(listAllSamp.get(i)).size();
			countAllResults=countAllResults+countResultsInSample[i];
			}
		for (int i = 0; i < countResultsInSample.length; i++) {
			System.out.println("countResultsInSample["+i+"] "+countResultsInSample[i]);
		}
		System.out.println("countAllResults "+countAllResults);
		if (countAllResults < maxRowInOneTableOnePage) {
			return listAllSamp.size()-1;
			}

			if (countAllResults < maxRowInFullTableOnePage) {
				
				int countRowInLastTable = 0;	
				int index_countResultsInSample  = countResultsInSample.length;
				
				System.out.println("index_countResultsInSample "+index_countResultsInSample);
				
				do  {
					index_countResultsInSample--;
					countRowInLastTable = countRowInLastTable + countResultsInSample[index_countResultsInSample];
				}while(countRowInLastTable < 4 && index_countResultsInSample >= 0);
				
				System.out.println("countRowInLastTable "+countRowInLastTable);
				System.out.println("index_countResultsInSample "+index_countResultsInSample);
				for (int j = 0; j < countResultsInSample.length; j++) {
					if (j < index_countResultsInSample) {
						listSampl.add(listAllSamp.get(j));
					} 
				}
				return listSampl.size()-1;
			} else {
				
				int countRowInFirstTable = 0;
				for (int j = 0; j < countResultsInSample.length; j++) {
					countRowInFirstTable = countRowInFirstTable + countResultsInSample[j];
					if (countRowInFirstTable <= maxRowInFullTableOnePage) {
						listSampl.add(listAllSamp.get(j));
					}
				}
			return listSampl.size()-1;

			}
		
	

	}
	
	public static List<int[]> addRowPokazatelIfGamaOrAlpha(int[] numberMergeCells) {
		List<int[]> listNumberSampleCount = new ArrayList<int[]>();
		int countMergeCells = numberMergeCells.length - 1;
			if (numberMergeCells[countMergeCells] < maxRowInOneTableOnePage) {
				listNumberSampleCount.add(numberMergeCells);
				return listNumberSampleCount;
			}

			if (numberMergeCells[countMergeCells] < maxRowInFullTableOnePage) {
				int countRowInLastTable = 0;
				int k = 0, m = 0;
				
				do  {
					k++;
					countRowInLastTable = numberMergeCells[countMergeCells] - numberMergeCells[countMergeCells - k];
				}while(countRowInLastTable <= 4 && countMergeCells - k >= 0);
				
				int[] masive1 = new int[countMergeCells - k + 1];
				int[] masive2 = new int[k];
				for (int j = 0; j < numberMergeCells.length; j++) {
					if (j <= countMergeCells - k) {
						masive1[j] = numberMergeCells[j];
					} else {
						masive2[m] = numberMergeCells[j];
						m++;
					}
				}
				listNumberSampleCount.add(masive1);
				listNumberSampleCount.add(masive2);
		
				return listNumberSampleCount;
			} else {
				List<Integer> list1 = new ArrayList<Integer>();
				List<Integer> list2 = new ArrayList<Integer>();
				for (int j = 0; j < numberMergeCells.length; j++) {
					if (numberMergeCells[j] <= maxRowInFullTableOnePage) {
						list1.add(numberMergeCells[j]);
					} else {
						list2.add(numberMergeCells[j]);
					}
				}
				int[] masive1 = new int[list1.size()];
				int[] masive2 = new int[list2.size()];
				int k = 0;
				for (int num : list1) {
					masive1[k] = num;
					k++;
				}
				k = 0;
				for (int num : list2) {
					masive2[k] = num;
					k++;
				}
				listNumberSampleCount.add(masive1);
				listNumberSampleCount.add(masive2);
				System.out.println("masive1= " + masive1.length + " " + "masive2= " + masive2.length);
				return listNumberSampleCount;

			}
		
	

	}

	public static Boolean addRowPokazatelIfGamaOrAlpha(Tbl tempTable, Tr templateRow_pokazatel,
			Map<String, String> repl_request_pokazarel, String pokaz) {
		Boolean fl = false;
		if (PokazatelIfGamaOrAlpha(pokaz)) {
			repl_request_pokazarel.put("$$request_pokazarel$$", pokaz);
			AplicationDocTemplate.addRowToTable(tempTable, templateRow_pokazatel, repl_request_pokazarel);
			fl = true;
		}
		return fl;
	}

	public static Boolean PokazatelIfGamaOrAlpha(String pokaz) {
		Boolean fl = false;
		if (pokaz.indexOf("דאלא") > 0 || pokaz.indexOf("אכפא") > 0) {

			fl = true;
		}
		return fl;
	}
}
