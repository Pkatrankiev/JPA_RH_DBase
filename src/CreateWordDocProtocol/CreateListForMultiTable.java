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
//			for (IzpitvanPokazatel pokazatel : pokazatel_list) {
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
//			}
		}
		numberMergeCells[count_numberMergeCells] = coutRow;

		return numberMergeCells;

	}

	@SuppressWarnings("null")
	public static List<int[]> addRowPokazatelIfGamaOrAlpha(int[] numberMergeCells) {
		List<int[]> listNumberSampleCount = new ArrayList<int[]>();
		System.out.println("numberMergeCells.length= " + numberMergeCells.length);
		for (int i = numberMergeCells.length - 1; i >= 0; i--) {
			if (numberMergeCells[i] < maxRowInOneTableOnePage) {
				listNumberSampleCount.add(numberMergeCells);
				return listNumberSampleCount;
			}

			if (numberMergeCells[i] < maxRowInFullTableOnePage) {
				System.out.println("numberMergeCells[" + i + "]= " + numberMergeCells[i]);
				int countRowInLastTable = 0;
				int k = 0, m = 0;
				while (countRowInLastTable < 4 && i - k > 0) {
					k++;
					countRowInLastTable = numberMergeCells[i] - numberMergeCells[i - k];
					System.out.println("i= " + i + "; k= " + k + "; countRowInLastTable= " + countRowInLastTable);

				}
				System.out.println("i= " + i + "; k= " + k);
				int[] masive1 = new int[i - k + 1];
				int[] masive2 = new int[k];
				for (int j = 0; j < numberMergeCells.length; j++) {
					if (j <= i - k) {
						masive1[j] = numberMergeCells[j];
					} else {
						masive2[m] = numberMergeCells[j];
						m++;
					}
				}
				listNumberSampleCount.add(masive1);
				listNumberSampleCount.add(masive2);
				System.out.println("masive1= " + masive1.length + " " + "masive2= " + masive2.length);
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

		return null;

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
