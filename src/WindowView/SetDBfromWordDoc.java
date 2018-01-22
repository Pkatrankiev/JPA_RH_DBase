package WindowView;

import javax.swing.JTable;

import Aplication.Izpitvan_pokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.List_MetodyDAO;
import DBase_Class.Izpitvan_pokazatel;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_Metody;

public class SetDBfromWordDoc {

	public static void setVolume(String fileName) {

		String celsTranfer[][][] = ReaderWordDoc.readMyDocument(fileName);
		System.out.println("broy tab " + celsTranfer.length);
		System.out.println("Broy Raw " + celsTranfer[0].length);
		System.out.println("Broy coll " + celsTranfer[0][0].length);
		String cellVolume;

		String[] columnNames = null;
		Object[][] data;

		String recuest_code = "";
		String date_time_reception = null;
		String obekt_na_izpitvane = null;
		String date_recuest = null;
		String date_time_reference = null;
		Boolean accreditation = false;
		Izpitvan_produkt izpitvan_produkt = null;
		String description_sample_group = null;
		int num_sample = 0;

		for (int tab = 0; tab < 2; tab++) {

			for (int row = 0; row < celsTranfer[0].length; row++) {

				for (int coll = 0; coll < celsTranfer[0][0].length; coll++) {

					cellVolume = celsTranfer[tab][row][coll];
					if (cellVolume != null) {
						System.out.println("cellVolume " + cellVolume);

						/**
						 * is RECUEST_CODE in RECUEST Class -kod na zaqvkata-
						 **/
						String clear_cells = cellVolume.replaceAll(" ", "");
						if (clear_cells.startsWith("�")) {
							recuest_code = clear_cells.substring(1, 5);
						}

						/** OBEKT_NA_IZPITVANE in SAMPLE Class **/
						if (cellVolume.startsWith("�����, �� �����")) {
							obekt_na_izpitvane = celsTranfer[tab][row][coll + 1];
						}

						/**
						 * is ACCREDITATION in RECUEST Class -v akreditaciq-
						 **/
						if (cellVolume.startsWith("����������")) {
							accreditation = true;
						}

						/**
						 * DATE_TIME_RECEPTION in RECUEST Class -poluchavane na
						 * probite-
						 **/
						/** DATE_REQUEST in RECUEST Class -data na zaqvkata- **/
						if (cellVolume.startsWith("���� �� ����������")) {
							date_time_reception = celsTranfer[tab][row][coll + 1].replaceAll(" ", "").substring(0, 10);
							date_recuest = date_time_reception;
						}

						/**
						 * DATE_TIME_REFERENCE in SAMPLE Class -referentna data
						 * i chas-
						 **/
						if (cellVolume.startsWith("���������� ����")) {
							date_time_reference = celsTranfer[tab][row][coll + 1].replaceAll(" ", "").replaceAll("h",
									"");
						}

						/** IZPITVAN_PRODUKT in RECUEST Class **/
						if (cellVolume.startsWith("�������� �������")) {
							String produkt = celsTranfer[tab][row][coll + 1];
							izpitvan_produkt = Izpitvan_produktDAO.getValueIzpitvan_produktByName(produkt);
						}

						/** DESCRIPTION_SAMPLE_GROUP in RECUEST Class **/
						if (cellVolume.contains("�������� �� �������")) {
							description_sample_group = celsTranfer[tab][row][coll + 1];
						}

						/**
						 * DATE_EXECUTION in RECUEST Class -srok za izpalnenie-
						 **/

					}
				}

			}

		}

		/** NUMBER_SAMPLES in RECUEST Class -broi na probite- **/
		/** DESCRIPTION_SAMPLE in SAMPLE Class -opisanie na probite- **/
		int num_start = 0;
		int num_end = 0;
		String str;
		int number_samples = description_sample_group.replaceAll("[^" + recuest_code + "]", "").length()
				/ recuest_code.length();

		String[] ob_na_izpit = new String[number_samples];
		for (int i = 0; i < ob_na_izpit.length; i++) {
			num_end = obekt_na_izpitvane.indexOf("\r", num_start + 1);
			if (num_end < 0) {
				num_end = obekt_na_izpitvane.length();
			}
			str = obekt_na_izpitvane.substring(num_start, num_end);
			num_start = num_end;
			ob_na_izpit[i] = str.trim();

		}

		num_start = 0;
		num_end = 0;
		String[] sample = new String[number_samples];

		for (int i = 0; i < sample.length; i++) {
			num_end = description_sample_group.indexOf(recuest_code, num_start + 1);
			if (num_end < 0) {
				num_end = description_sample_group.length();
			}
			str = description_sample_group.substring(num_start, num_end);
			num_start = num_end;
			sample[i] = str.substring((str.indexOf("/", 0) + 1), str.length()).trim();

		}

		/** split tabs in one tab **/
		int new_tab_row = 0;
		Boolean flag = false;
		for (int tab = 2; tab < celsTranfer.length; tab++) {
			for (int row = 0; row < celsTranfer[0].length; row++) {
				if (flag)
					new_tab_row++;
				flag = false;
				for (int coll = 0; coll < celsTranfer[0][0].length; coll++) {
					if (celsTranfer[tab][row][coll] != null) {
						flag = true;
					}
				}
			}
		}

		System.out.println("row :" + new_tab_row + " cell :" + celsTranfer[0][0].length);
		int new_row = 0;
		int new_cell = celsTranfer[0][0].length;

		String[][] newTab = new String[new_tab_row][celsTranfer[0][0].length];
		for (int tab = 2; tab < celsTranfer.length; tab++) {
			for (int row = 0; row < celsTranfer[0].length; row++) {
				if (flag)
					new_row++;
				flag = false;
				for (int coll = 0; coll < new_cell; coll++) {
					if (celsTranfer[tab][row][coll] != null) {
						flag = true;
					}
					if (new_row < new_tab_row) {

						newTab[new_row][coll] = celsTranfer[tab][row][coll];

					}
				}
			}
		}
		String[] columnNames2 = new String[newTab[0].length];

		for (int j = 0; j < newTab[0].length; j++) {

			columnNames2[j] = j + "";
		}

		TablePrintDemo.createAndShowGUI(columnNames2, newTab);

		/** SET interval SAMPLES **/
		int sample_N = 0;

		int[] row_sample_start = new int[number_samples];
		String qurent_sample = null;
		for (int row = 0; row < newTab.length; row++) {
			cellVolume = newTab[row][0];
			cellVolume = cellVolume.replaceAll(" ", "").replaceAll("\r", "");

			if (cellVolume.startsWith(recuest_code) & !cellVolume.equals(qurent_sample)) {
				qurent_sample = cellVolume;
				sample_N = (Integer.parseInt(cellVolume.substring((cellVolume.indexOf("-") + 1), cellVolume.length())))
						- 1;
				row_sample_start[sample_N] = row;
				System.out.println("sample_N " + sample_N + " start " + row_sample_start[sample_N]);
			}
		}

		/** LIST_METODY in METODY Class **/
		int[][] row_metody_start = new int[number_samples][10];
		int end_num = 0;
		int[] max_num_metody = new int[number_samples];
		      

		List_Metody[][] metodi_sample = new List_Metody[number_samples][10];
		for (int num_samples = 0; num_samples < number_samples; num_samples++) {
			int num_metody = 0;
			if (num_samples == number_samples - 1) {
				end_num = newTab.length;
			} else {
				end_num = row_sample_start[num_samples + 1];
			}

			for (int row = row_sample_start[num_samples]; row < end_num; row++) {
				cellVolume = newTab[row][1];

				cellVolume = cellVolume.replaceAll("\r", " ").trim();

				if (cellVolume.startsWith("M.��-��")) {

//					metodi_sample[num_samples][num_metody] = List_MetodyDAO.getValueList_MetodyByName(cellVolume);

					row_metody_start[num_samples][num_metody] = row - 1;
					num_metody++;
					max_num_metody[num_samples] = num_metody;
					
					}
				
			}
			System.out.println("max_num_metody ["+num_samples+"] "+max_num_metody[num_samples]);
		}
		/**
		 * List_Metody-metodi_sample[number_samples][max_num_metody[num_samples]
		 * ] int-row_metody_start[number_samples][num_metody]
		 * int-max_num_metody[number_samples]
		 **/

		/** POKAZATEL in METODY Class **/
		int num = max_num_metody[number_samples-1];
		Izpitvan_pokazatel[][][] pokazatel_metodi_sample = new Izpitvan_pokazatel[number_samples][num][10];
		int[][] max_num_pokazatel_metody = new int[number_samples][num];
		int num_metody_pokazatel = 0;
		for (int num_samples = 0; num_samples < number_samples; num_samples++) {
			
			for (int num_metody_samples = 0; num_metody_samples < max_num_metody[num_samples]; num_metody_samples++) {
				
				
				if (row_metody_start[num_samples][num_metody_samples] == max_num_metody[num_samples]) {
					end_num = newTab.length;
				} else {
					end_num = row_metody_start[num_samples][num_metody_pokazatel+1];
				}
	
				for (int row = row_metody_start[num_samples][num_metody_pokazatel]; row < end_num; row++) {
					cellVolume = newTab[row][2];
					cellVolume = cellVolume.trim();
					System.out.println("cellVolume "+cellVolume+" row "+row);
					if (cellVolume.startsWith("���������� ��")) {
						
//						pokazatel_metodi_sample[num_samples][num_metody_samples][num_metody_pokazatel] = Izpitvan_pokazatelDAO
//								.getValueIzpitvan_pokazatelByName(cellVolume);
						System.out.println("--pokazatel_metodi_sample" + num_samples + " / "
								+ num_metody_samples + " / " + num_metody_pokazatel);
						
						max_num_pokazatel_metody[num_samples][num_metody_pokazatel] = num_metody_pokazatel;
						num_metody_pokazatel++;
					}
				}
			}
		}
		//
		//
		// System.out.println("RECUEST_CODE " + recuest_code);
		// System.out.println("ACCREDITATION " + accreditation);
		// System.out.println("DATE_TIME_RECEPTION " + date_time_reception);
		// System.out.println("DATE_REQUEST " + date_recuest);
		// System.out.println("DATE_TIME_REFERENCE " + date_time_reference);
		// System.out.println("IZPITVAN_PRODUKT " +
		// izpitvan_produkt.getName_zpitvan_produkt() + " � "
		// + izpitvan_produkt.getId_zpitvan_produkt());
		// System.out.println("DESCRIPTION_SAMPLE_GROUP " +
		// description_sample_group);
		// System.out.println("NUMBER_SAMPLES " + number_samples);
		//
		// for (int j = 0; j < number_samples; j++) {
		// System.out.println();
		// System.out.println("DESCRIPTION_SAMPLE " + (j + 1) + " - " +
		// sample[j] + ".");
		// System.out.println("OBEKT_NA_IZPITVANE " + (j + 1) + " - " +
		// ob_na_izpit[j] + ".");
		//
		// for (int i = 0; i < max_num_metody[j]; i++) {
		// System.out.println("LIST_METODY " + (j + 1) + " - " +
		// metodi_sample[j][i].getCode_metody() + " / "
		// + metodi_sample[j][i].getName_metody());
		//
		// for (int k = 0; k < max_num_pokazatel_metody[j][i]; k++) {
		// System.out.println(" POKAZATEL " + (j + 1) + " / " + (i + 1) + " - "
		// + pokazatel_metodi_sample[j][i][k].getName_pokazatel());
		//
		// }
		// }
		// }

		/** --------------------------------------------------------------- **/

	}

}
