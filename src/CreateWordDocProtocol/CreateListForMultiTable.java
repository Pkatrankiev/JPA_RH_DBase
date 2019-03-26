package CreateWordDocProtocol;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tr;

import Aplication.ResultsDAO;
import DBase_Class.IzpitvanPokazatel;
import DBase_Class.Results;
import DBase_Class.Sample;

public class CreateListForMultiTable {

	public static int maxRowInOneTableOnePage = 15;
	public static int maxRowInFullTableOnePage = 25;

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

		return numberMergeCells;

	}

	@SuppressWarnings({ "null", "unused" })
	public static List<int[]> addRowPokazatelIfGamaOrAlpha(int[] numberMergeCells) {
		List<int[]> listNumberSampleCount = new ArrayList<int[]>();
		int countMergeCells = numberMergeCells.length - 1;
			if (numberMergeCells[countMergeCells] < maxRowInOneTableOnePage) {
				listNumberSampleCount.add(numberMergeCells);
				
				System.out.println("numberMergeCells.length= " + numberMergeCells.length);
				for (int i = 0; i < numberMergeCells.length; i++) {
					System.out.println("masive0["+i+"]= " + numberMergeCells[i]);
				}
				
				
				return listNumberSampleCount;
			}

			if (numberMergeCells[countMergeCells] < maxRowInFullTableOnePage) {
				int countRowInLastTable = 0;
				int k = 0, m = 0;
				do  {
					k++;
					countRowInLastTable = numberMergeCells[countMergeCells] - numberMergeCells[countMergeCells - k];
				}	
				while(countRowInLastTable <= 4 && countMergeCells - k >= 0);
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
				
				
				System.out.println("masive1= " + masive1.length + " masive2= " + masive2.length);
				for (int i = 0; i < masive1.length; i++) {
					System.out.println("masive1["+i+"]= " + masive1[i]);
				}
				for (int i = 0; i < masive2.length; i++) {
					System.out.println("masive2["+i+"]= " + masive2[i]);
				}
				
				
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
