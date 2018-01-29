package WindowView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JTable;

import Aplication.Izpitvan_pokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import DBase_Class.Izpitvan_pokazatel;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;

public class SetDBfromWordDoc {

	public static void setVolume(String fileName) {

		String celsTranfer[][][] = ReaderWordDoc.readMyDocument(fileName);
		System.out.println("broy tab " + celsTranfer.length);
		System.out.println("Broy Raw " + celsTranfer[0].length);
		System.out.println("Broy coll " + celsTranfer[0][0].length);
		String cellVolume;

		String[] columnNames = null;
		Object[][] data;
		NumberFormat frm = new DecimalFormat("#0.00000");
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
						if (clear_cells.startsWith("№")) {
							recuest_code = clear_cells.substring(1, 5);
						}

						/** OBEKT_NA_IZPITVANE in SAMPLE Class **/
						if (cellVolume.startsWith("Обект, от който")) {
							obekt_na_izpitvane = celsTranfer[tab][row][coll + 1];
						}

						/**
						 * is ACCREDITATION in RECUEST Class -v akreditaciq-
						 **/
						if (cellVolume.startsWith("Сертификат")) {
							accreditation = true;
						}

						/**
						 * DATE_TIME_RECEPTION in RECUEST Class -poluchavane na
						 * probite-
						 **/
						/** DATE_REQUEST in RECUEST Class -data na zaqvkata- **/
						if (cellVolume.startsWith("Дата на получаване")) {
							date_time_reception = celsTranfer[tab][row][coll + 1].replaceAll(" ", "").substring(0, 10);
							date_recuest = date_time_reception;
						}

						/**
						 * DATE_TIME_REFERENCE in SAMPLE Class -referentna data
						 * i chas-
						 **/
						if (cellVolume.startsWith("Референтна дата")) {
							date_time_reference = celsTranfer[tab][row][coll + 1].replaceAll(" ", "").replaceAll("h",
									"");
						}

						/** IZPITVAN_PRODUKT in RECUEST Class **/
						if (cellVolume.startsWith("Изпитван продукт")) {
							String produkt = celsTranfer[tab][row][coll + 1];
							izpitvan_produkt = Izpitvan_produktDAO.getValueIzpitvan_produktByName(produkt);
						}

						/** DESCRIPTION_SAMPLE_GROUP in RECUEST Class **/
						if (cellVolume.contains("Описание на пробите")) {
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
		number_samples = sample_N + 1;

		/** POKAZATEL in METODY Class **/

		int[][] row_pokazatel_start = new int[number_samples][20];
		int end_num = 0;
		int num_pokazatel = 0;
		int[] max_num_pokazatel = new int[number_samples];
		String[][] str_pokazatel_sample = new String[number_samples][20];

		for (int num_samples = 0; num_samples < number_samples; num_samples++) {

			if (num_samples == number_samples - 1) {
				end_num = newTab.length;
			} else {
				end_num = row_sample_start[num_samples + 1];
			}

			num_pokazatel = 0;
			Boolean flag2 = false;
			for (int row = row_sample_start[num_samples]; row < end_num; row++) {
				cellVolume = newTab[row][2];

				cellVolume = cellVolume.replaceAll("\r", " ").trim();
				boolean flag_pokazatel = false;
				int i = row;
				do {
					cellVolume = newTab[i][2];
					if (cellVolume.startsWith("Съдържание на")) {
						flag_pokazatel = true;
					} else {
						i--;
					}
				} while (!flag_pokazatel & i >= 0);
				str_pokazatel_sample[num_samples][num_pokazatel] = cellVolume;
				
				row_pokazatel_start[num_samples][num_pokazatel] = row_sample_start[num_samples];
				max_num_pokazatel[num_samples] = num_pokazatel;
				
				if (i == row) {
					System.out.println("i "+i+" num_pokazatel "+num_pokazatel);
					flag2 = true;
					String str_cell_p = cellVolume.substring((cellVolume.indexOf("на ") + 3), cellVolume.length());
					if ((str_cell_p.substring(0, 2)).equals("3H")
							|| Integer.parseInt(str_cell_p.substring(0, 2)) >= 10) {
						row_pokazatel_start[num_samples][num_pokazatel] = row;
					}
//					
					num_pokazatel++;
				}
			}
			System.out.println("2 - sample_N " + num_samples + " start " + row_pokazatel_start[num_samples][num_pokazatel]);
		}
		System.out.println("**********************num_pokazatel: " + num_pokazatel);
		List_izpitvan_pokazatel[][] pokazatel_sample = new List_izpitvan_pokazatel[number_samples][max_num_pokazatel[number_samples
				- 1]];
		for (int i = 0; i < number_samples; i++) {
			for (int j = 0; j <= max_num_pokazatel[i]; j++) {
				System.out.println("str_pokazatel_sample[" + i + "][" + j + "]= " + str_pokazatel_sample[i][j]
						+ " Start [" + i + "][" + j + "]= " + row_pokazatel_start[i][j]);
				// pokazatel_sample[i][j] =
				// List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelByName(str_pokazatel_sample[i][j]);

			}

		}

		/** RESULYS Class **/

		if (num_pokazatel == 0) {
			num_pokazatel++;
		}
		System.out.println("*************** sample: " + number_samples + " num_pokazatel: " + num_pokazatel);
		String[][][] results_nuklide = new String[number_samples][num_pokazatel][50];
		String[][][] results_value = new String[number_samples][num_pokazatel][50];
		double[][][] results_MDA = new double[number_samples][num_pokazatel][50];
		double[][][] results_uncertainty = new double[number_samples][num_pokazatel][50];
		double[][][] results_value_result = new double[number_samples][num_pokazatel][50];
		int[][] max_num_results = new int[number_samples][num_pokazatel];
		String[] razmernost = new String[number_samples];
		String results_value_str = null;
		int num_results = 0;
		int number_sample = 0;
		for (int i = 0; i < number_samples; i++) {
			num_results = 0;
			for (int j = 0; j <= max_num_pokazatel[i]; j++) {
				if ((j < max_num_pokazatel[i])) {
					end_num = row_pokazatel_start[i][j + 1];
				} else {
					if ((i < number_samples - 1)) {
						end_num = row_pokazatel_start[i + 1][0];
					} else
						end_num = newTab.length;
				}
				System.out.println("start " + row_pokazatel_start[i][j] + " end " + (end_num - 1));
				for (int row = row_pokazatel_start[i][j]; row < end_num; row++) {
					cellVolume = newTab[row][2];
					cellVolume = cellVolume.trim();

					if (newTab[row][3].contains("Bq")) {
						razmernost[i] = newTab[row][3];
					}

					// System.out.println("1-sample_N " + number_sample + "
					// start " + row_sample_start[number_sample]);
					String str_cell = null;
					if (cellVolume.startsWith("Съдържание на ")) {
						num_results = 0;
						str_cell = cellVolume.substring((cellVolume.indexOf("на ") + 3), cellVolume.length());
					} else
						str_cell = cellVolume;
					try {
						System.out.println("nuklid " + str_cell.substring(0, 2));

						if ((str_cell.substring(0, 2)).equals("3H")
								|| Integer.parseInt(str_cell.substring(0, 2)) >= 10) {

							System.out.println("sample " + i + " pokazatel-" + j + " results " + num_results + " - "
									+ str_cell + " row " + row + "  " + str_cell);

							results_value_str = newTab[row][4].trim();

							results_nuklide[i][j][num_results] = str_cell;
							results_value[i][j][num_results] = newTab[row][4].trim();
							max_num_results[i][j] = num_results;
							num_results++;

						}
					} catch (NumberFormatException | StringIndexOutOfBoundsException e) {

					}
				}
			}
			if (razmernost[i] == null) {
				razmernost[i] = razmernost[i - 1];
			}
			System.out.println("razmernost " + i + "-" + razmernost[i]);
		}

		for (int i = 0; i < number_samples; i++) {
			for (int j = 0; j <= max_num_pokazatel[i]; j++) {

				for (int k = 0; k <= max_num_results[i][j]; k++) {
					if(results_value[i][j][k]!=null){
					results_value_str = results_value[i][j][k].replace("Е", "E");
					if (results_value_str.startsWith("<")) {

						results_MDA[i][j][num_results] = Double
								.valueOf(results_value_str.substring(results_value_str.indexOf("<") + 1));
						System.out.println("MDA " + results_MDA[i][j][num_results] + "  "
								+ formatter(results_MDA[i][j][num_results]));

					} else {
						results_value_result[i][j][num_results] = Double
								.valueOf(results_value_str.substring(0, results_value_str.indexOf("±")).trim());
						System.out.println("Values " + results_value_result[i][j][num_results] + "  "
								+ formatter(results_value_result[i][j][num_results]));
						results_uncertainty[i][j][num_results] = Double
								.valueOf(results_value_str.substring(results_value_str.indexOf("±") + 1).replace("*", "").trim());
						System.out.println("Uncertainty " + results_uncertainty[i][j][num_results] + " "
								+ alignExpon(results_value_result[i][j][num_results], results_uncertainty[i][j][num_results]));
					}

					System.out.println("*sample " + i + " pokazatel-" + j + " results " + results_nuklide[i][j][k]
							+ " results " + results_value[i][j][k]);
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
		// izpitvan_produkt.getName_zpitvan_produkt() + " ¹ "
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

	private static String formatter(double number) {
		DecimalFormat formatter = new DecimalFormat("0.00E00");
		String fnumber = formatter.format(number);
		if (!fnumber.contains("E-")) { // don't blast a negative sign
			fnumber = fnumber.replace("E", "E+");
		}
		fnumber = fnumber.replace(",", ".");
		return fnumber;
	}


	public static String alignExpon(double basic, double foll) {
		NumberFormat frm = new DecimalFormat("0.00E00");
		NumberFormat frm_foll = new DecimalFormat("0.00");
		String str_bas = frm.format(basic);
		double expon = Double.valueOf("1.0" + str_bas.substring(str_bas.indexOf("E")));
		foll = foll / expon;
		String str_foll = frm_foll.format(foll) + str_bas.substring(str_bas.indexOf("E"));
		if (!str_foll.contains("E-")) { //don't blast a negative sign
			str_foll = str_foll.replace("E", "E+");
	    }
		str_foll = str_foll.replace(",", ".");
		return str_foll;
	}
	
}
