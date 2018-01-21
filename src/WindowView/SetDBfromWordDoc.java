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
					if (celsTranfer[tab][row][coll] != null)
						flag = true;
				}
			}
		}

		System.out.println("row :" + new_tab_row + " cell :" + celsTranfer[0][0].length);
		int new_row = 0;
		int new_cell = celsTranfer[0][0].length;
		
		String[][] newTab = new String[new_tab_row + 2][celsTranfer[0][0].length];
		for (int tab = 2; tab < celsTranfer.length; tab++) {
			for (int row = 0; row < celsTranfer[0].length; row++) {
				if (flag)
					new_tab_row++;
				flag = false;
				for (int coll = 0; coll < new_cell; coll++) {
					if (celsTranfer[tab][row][coll] != null) {
						flag = true;
						newTab[new_row][coll] = celsTranfer[tab][row][coll];
					}
				}

			}
		}

		List_Metody[][] metodi_sample = new List_Metody[number_samples][10];
		Izpitvan_pokazatel[][][] pokazatel_metodi_sample = new Izpitvan_pokazatel[number_samples][10][10];
		int sample_N = 0;
		int num_mtody = 0;
		Boolean flag_metody = false;
		int num_pokazatel_mtody = 0;
		int[] max_num_metody = new int[number_samples];
		int[][] max_num_pokazatel_metody = new int[number_samples][10];
		String qurent_sample = null;

	

		 for (int row = 0; row < new_row; row++) {
		 for (int coll = 0; coll < new_cell; coll++) {
		
		 cellVolume = newTab[row][coll];
		 if (cellVolume != null) {
		
		 /** LIST_METODY **/
		
		 if (coll == 0) {
		 cellVolume = cellVolume.replaceAll(" ", "").replaceAll("\r", "");
		 System.out.println("cellVolume " + cellVolume);
		 if (cellVolume.startsWith(recuest_code) &
		 !cellVolume.equals(qurent_sample)) {
		 qurent_sample = cellVolume;
		 num_mtody = 0;
		 sample_N = Integer
		 .parseInt(cellVolume.substring((cellVolume.indexOf("-") + 1),
		 cellVolume.length()));
		 System.out.println("sample_N " + sample_N);
		 }
		 }
		 /** LIST_METODY in METODY Class **/
		 cellVolume = cellVolume.replaceAll("\r", " ").trim();
		 if (flag_metody & cellVolume.startsWith("M.��-��")) {
		 flag_metody = false;
		 } else {
		 if (cellVolume.startsWith("M.��-��")) {
		 flag_metody = true;
		 row--;
		 metodi_sample[sample_N - 1][num_mtody] = List_MetodyDAO
		 .getValueList_MetodyByName(cellVolume);
		 System.out.println("-----------------------------------metodi_sample"
		 + (sample_N - 1)
		 + " / " + num_mtody + " - "
		 + metodi_sample[sample_N - 1][num_mtody].getCode_metody());
		 num_mtody++;
		 num_pokazatel_mtody = 0;
		 max_num_metody[sample_N - 1] = num_mtody;
		 }
		 }
		
		 /** POKAZATEL in METODY Class **/
		 cellVolume = cellVolume.trim();
		 System.out.println("A-----------pokazatel_metodi_sample--------metodi_sample-" 
		 + cellVolume + ".");
		 if (cellVolume.startsWith("���������� ��")) {
		
		 if (flag_metody) {
		 pokazatel_metodi_sample[sample_N
		 - 1][num_mtody][num_pokazatel_mtody] = Izpitvan_pokazatelDAO
		 .getValueIzpitvan_pokazatelByName(cellVolume);
		 System.out.println("------------------------------pokazatel_metodi_sample"
		 + (sample_N - 1)
		 + " / " + num_mtody + " / " + num_pokazatel_mtody);
		 num_pokazatel_mtody++;
		 max_num_pokazatel_metody[sample_N - 1][num_mtody] =
		 num_pokazatel_mtody;
		
		 }
		 }
		
		 /** ............................................... **/
		 }
		
		 }
		 }
		System.out.println("RECUEST_CODE " + recuest_code);
		System.out.println("ACCREDITATION " + accreditation);
		System.out.println("DATE_TIME_RECEPTION " + date_time_reception);
		System.out.println("DATE_REQUEST " + date_recuest);
		System.out.println("DATE_TIME_REFERENCE " + date_time_reference);
		System.out.println("IZPITVAN_PRODUKT " + izpitvan_produkt.getName_zpitvan_produkt() + " � "
				+ izpitvan_produkt.getId_zpitvan_produkt());
		System.out.println("DESCRIPTION_SAMPLE_GROUP " + description_sample_group);
		System.out.println("NUMBER_SAMPLES " + number_samples);

		for (int j = 0; j < number_samples; j++) {
			System.out.println();
			System.out.println("DESCRIPTION_SAMPLE " + (j + 1) + " - " + sample[j] + ".");
			System.out.println("OBEKT_NA_IZPITVANE " + (j + 1) + " - " + ob_na_izpit[j] + ".");

			for (int i = 0; i < max_num_metody[j]; i++) {
				System.out.println("LIST_METODY " + (j + 1) + " - " + metodi_sample[j][i].getCode_metody() + " / "
						+ metodi_sample[j][i].getName_metody());

				for (int k = 0; k < max_num_pokazatel_metody[j][i]; k++) {
					System.out.println(" POKAZATEL " + (j + 1) + " / " + (i + 1) + " - "
							+ pokazatel_metodi_sample[j][i][k].getName_pokazatel());

				}
			}
		}

		/** --------------------------------------------------------------- **/
	}

}
