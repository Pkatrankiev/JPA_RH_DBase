package WindowView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Aplication.DimensionDAO;
import Aplication.Ind_num_docDAO;
import Aplication.Internal_applicantDAO;
import Aplication.Izpitvan_pokazatelDAO;
import Aplication.Izpitvan_produktDAO;
import Aplication.List_izpitvan_pokazatelDAO;
import Aplication.MetodyDAO;
import Aplication.NuclideDAO;
import Aplication.Obekt_na_izpitvane_requestDAO;
import Aplication.Obekt_na_izpitvane_sampleDAO;
import Aplication.PeriodDAO;
import Aplication.RazmernostiDAO;
import Aplication.RequestDAO;
import Aplication.ResultsDAO;
import Aplication.SampleDAO;
import Aplication.UsersDAO;
import Aplication.ZabelejkiDAO;
import DBase_Class.Dimension;
import DBase_Class.External_applicant;
import DBase_Class.Ind_num_doc;
import DBase_Class.Internal_applicant;
import DBase_Class.Izpitvan_pokazatel;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Nuclide;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Period;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import DBase_Class.Zabelejki;

public class SetDBfromWordDoc {

	public static void setVolume(String fileName) {

		String celsTranfer[][][] = ReaderWordDoc.readMyDocument(fileName);
		// System.out.println("broy tab " + celsTranfer.length);
		// System.out.println("Broy Raw " + celsTranfer[0].length);
		// System.out.println("Broy coll " + celsTranfer[0][0].length);
		String cellVolume;

		String[] columnNames = null;
		Object[][] data;
		NumberFormat frm = new DecimalFormat("#0.00000");
		String recuest_code = "";
		String date_time_reception = null;
		String obekt_na_izpitvane_request = null;
		String date_recuest = null;
		String date_time_reference = null;
		String date_redac = null;
		String date_measur = null;
		String aplicant_name = null;
		String aplicant_family = null;
		String date_execution = null;
		String date_chim = null;
		String zabelejka_recuest = null;
		Boolean accreditation = false;
		Boolean section = false;
		Boolean flag_note = false;
		Izpitvan_produkt izpitvan_produkt = null;
		String description_sample_group = null;

		External_applicant external_aplicant = null;
		Internal_applicant internal_aplicant = null;
		Ind_num_doc ind_num_doc = null;
		Zabelejki note = null;

		int z = 0;
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

						/** MDA in RESULT Class **/
						String[] mda = new String[20];

						if (cellVolume.contains("MDA") & cellVolume.contains(recuest_code)) {
							cellVolume = cellVolume.replaceAll("Е", "E");
							int start = cellVolume.indexOf("E") - 5;
							int end = cellVolume.indexOf("E") + 4;
							mda[z] = cellVolume.substring(start, end).trim();
							System.out.println("MDA [" + z + "] = " + mda[z]);
							z++;
						}

						/**
						 * ZABELEJKA in RECUEST Class -
						 **/
						if (clear_cells.startsWith("*")) {
							zabelejka_recuest = clear_cells.substring(1).trim();
						}

						/** OBEKT_NA_IZPITVANE in SAMPLE Class **/
						if (cellVolume.startsWith("Обект, от който")) {
							obekt_na_izpitvane_request = celsTranfer[tab][row][coll + 1];
						}

						/**
						 * is ACCREDITATION in RECUEST Class -v akreditaciq-
						 **/
						if (cellVolume.startsWith("Сертификат")) {
							accreditation = true;
						}

						/** DESCRIPTION_SAMPLE_GROUP in RECUEST Class **/
						if (cellVolume.contains("Описание на пробите")) {
							description_sample_group = celsTranfer[tab][row][coll + 1];
						}

						/** SECTION in SAMPLE Class **/
						if (cellVolume.startsWith("Заявител на изпитването")) {

							if (celsTranfer[tab][row][coll + 1]
									.replace("Държавно предприятие “Радиоактивни отпадъци”", "").trim().length() == 0) {

								section = true;
							}

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

						/**
						 * DATE_REDAC in RESULTS Class -data na
						 * protokola/redakciata-
						 **/
						if (cellVolume.startsWith("Протокол от изпитване")) {
							String str_tran = celsTranfer[tab][row][coll];
							date_redac = str_tran.substring(str_tran.indexOf("/") + 1).replace("г.", "")
									.replace("г", "").trim();
							System.out.println("дата редакция/протокол " + date_redac);

							/**
							 * DATE_EXECUTION in RESULTS Class -srok za
							 * izpalnenie -> data na protokola + 14 dni-
							 **/
							date_execution = addDate(date_redac, 14);

						}

						/**
						 * DATE_CHIM in RESULTS Class -data na chim-obrabotka-
						 **/
						if (cellVolume.contains("извършване на изпитването")) {
							String str_tran = celsTranfer[tab][row][coll + 1];
							if (str_tran.contains("÷")) {
								date_chim = str_tran.substring(0, str_tran.indexOf("÷")).trim();
								date_measur = str_tran.substring(str_tran.indexOf("÷") + 1).trim();
							} else {
								date_chim = str_tran.trim();
								date_measur = str_tran.trim();
							}

							System.out.println("date_chim -" + date_chim + "date_measur -" + date_measur);
						}

					}
				}

			}

		}

		/** NUMBER_SAMPLES in RECUEST Class -broi na probite- **/
		/** DESCRIPTION_SAMPLE in SAMPLE Class -opisanie na probite- **/
		int num_start = 0;
		int num_end = 0;
		String str;
		int counts_samples = description_sample_group.replaceAll("[^" + recuest_code + "]", "").length()
				/ recuest_code.length();

		System.out.println(counts_samples + "+------1- "
				+ description_sample_group.replaceAll("[^" + recuest_code + "]", "").length() + " --------2 "
				+ recuest_code.length());

		String[] ob_na_izpit = new String[counts_samples];
		for (int i = 0; i < ob_na_izpit.length; i++) {
			num_end = obekt_na_izpitvane_request.indexOf("\r", num_start + 1);
			if (num_end < 0) {
				num_end = obekt_na_izpitvane_request.length();
			}
			str = obekt_na_izpitvane_request.substring(num_start, num_end);
			num_start = num_end;
			ob_na_izpit[i] = str.trim();

		}

		num_start = 0;
		num_end = 0;
		String[] sample_description = new String[counts_samples];

		for (int i = 0; i < sample_description.length; i++) {
			num_end = description_sample_group.indexOf(recuest_code, num_start + 1);
			if (num_end < 0) {
				num_end = description_sample_group.length();
			}
			str = description_sample_group.substring(num_start, num_end);
			num_start = num_end;
			sample_description[i] = str.substring((str.indexOf("/", 0) + 1), str.length()).trim();
			System.out.println("sample[" + i + "]= " + sample_description[i]);

		}

		/** split tabs in one tab **/
		int new_tab_row = 0;
		int start_row = 0;
		Boolean flag = false;
		for (int tab = 2; tab < celsTranfer.length; tab++) {
			if (tab > 2) {
				start_row = 2;
			}
			for (int row = start_row; row < celsTranfer[0].length; row++) {
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
		start_row = 0;
		String[][] newTab = new String[new_tab_row][celsTranfer[0][0].length];
		for (int tab = 2; tab < celsTranfer.length; tab++) {
			if (tab > 2) {
				start_row = 2;
			}
			for (int row = start_row; row < celsTranfer[0].length; row++) {
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

//		TablePrintDemo.createAndShowGUI(columnNames2, newTab);
		System.out.println("counts_samples " + counts_samples);
		/** SET interval SAMPLES **/
		int sample_N = 0;
		String[] sample_code = new String[counts_samples];
		int[] row_sample_start = new int[counts_samples];
		String qurent_sample = null;
		for (int row = 0; row < newTab.length; row++) {
			cellVolume = newTab[row][0];
			if (cellVolume != null) {
				cellVolume = cellVolume.replaceAll(" ", "").replaceAll("\r", "");

				if (cellVolume.startsWith(recuest_code) & !cellVolume.equals(qurent_sample)) {
					qurent_sample = cellVolume;
					sample_code[sample_N] = (cellVolume.substring((cellVolume.indexOf("-") + 1), cellVolume.length()));

					row_sample_start[sample_N] = row;

					System.out.println("sample_N " + sample_N + " start " + row_sample_start[sample_N]);
					sample_N++;
				}
			}
		}
		counts_samples = sample_N;

		/** POKAZATEL in METODY Class **/
		int[][] row_pokazatel_start = new int[counts_samples][20];
		int end_num = 0;
		int num_pokazatel = 0;
		int[] max_num_pokazatel = new int[counts_samples];
		String[][] str_pokazatel_sample = new String[counts_samples][20];

		for (int num_samples = 0; num_samples < counts_samples; num_samples++) {

			if (num_samples == counts_samples - 1) {
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
					if (cellVolume.startsWith("Съдържание на ")) {
						flag_pokazatel = true;

					} else {
						i--;
					}

				} while (!flag_pokazatel & i >= 0);
				row_pokazatel_start[num_samples][num_pokazatel] = row_sample_start[num_samples];
				max_num_pokazatel[num_samples] = num_pokazatel;
				str_pokazatel_sample[num_samples][num_pokazatel] = cellVolume;
				if (i == row) {
					flag2 = true;
					row_pokazatel_start[num_samples][num_pokazatel] = row;
					num_pokazatel++;
				}
			}
		}
		System.out.println("**********************num_pokazatel: " + num_pokazatel);
		List_izpitvan_pokazatel[][] pokazatel_sample = new List_izpitvan_pokazatel[counts_samples][num_pokazatel + 1];
		for (int i = 0; i < counts_samples; i++) {
			for (int j = 0; j <= max_num_pokazatel[i]; j++) {
				System.out.println("str_pokazatel_sample[" + i + "][" + j + "]= " + str_pokazatel_sample[i][j]
						+ " Start [" + i + "][" + j + "]= " + row_pokazatel_start[i][j]);
				 pokazatel_sample[i][j] = List_izpitvan_pokazatelDAO
				 .getValueIzpitvan_pokazatelByName(str_pokazatel_sample[i][j]);

			}

		}

		/** METODY RESULYS Class **/
		if (num_pokazatel == 0) {
			num_pokazatel++;
		}
		System.out.println("*************** metody: ");
		String[][] metody = new String[counts_samples][num_pokazatel];

		for (int i = 0; i < counts_samples; i++) {
			for (int j = 0; j <= max_num_pokazatel[i]; j++) {
				boolean flag_pokazatel = false;
				if ((j < max_num_pokazatel[i])) {
					end_num = row_pokazatel_start[i][j + 1];
				} else {
					if ((i < counts_samples - 1)) {
						end_num = row_pokazatel_start[i + 1][0];
					} else
						end_num = newTab.length;
				}
				System.out.println(" Start [" + i + "][" + j + "]= " + row_pokazatel_start[i][j] + " end = " + end_num);
				for (int row = row_pokazatel_start[i][j]; row < end_num; row++) {
					flag_pokazatel = false;
					int k = row;
					do {
						cellVolume = newTab[k][1];
						cellVolume = cellVolume.replaceAll("\r", " ").trim();
						if (cellVolume.startsWith("М.ЛИ-РХ") || cellVolume.startsWith("M.ЛИ-РХ")
								|| cellVolume.startsWith("M.ЛИ-РХ")) {
							flag_pokazatel = true;

						} else {
							k--;
						}

					} while (!flag_pokazatel & k >= 0);
					metody[i][j] = cellVolume;

				}

				System.out.println(" metody: [" + i + "][" + j + "]= " + metody[i][j]);
			}

		}

		/** RESULYS Class **/

		if (num_pokazatel == 0) {
			num_pokazatel++;
		}
		System.out.println("*************** sample: " + counts_samples + " num_pokazatel: " + num_pokazatel);
		String[][][] results_nuklide = new String[counts_samples][num_pokazatel][50];
		String[][][] results_value = new String[counts_samples][num_pokazatel][50];
		Nuclide[][][] nuclide_sample = new Nuclide[counts_samples][num_pokazatel][50];
		double[][][] results_MDA = new double[counts_samples][num_pokazatel][50];
		double[][][] results_uncertainty = new double[counts_samples][num_pokazatel][50];
		double[][][] results_value_result = new double[counts_samples][num_pokazatel][50];
		int[][] max_num_results = new int[counts_samples][num_pokazatel];
		String[] razmernost = new String[counts_samples];
		String results_value_str = null;
		int num_results = 0;

		for (int i = 0; i < counts_samples; i++) {
			num_results = 0;
			for (int j = 0; j <= max_num_pokazatel[i]; j++) {
				if ((j < max_num_pokazatel[i])) {
					end_num = row_pokazatel_start[i][j + 1];
				} else {
					if ((i < counts_samples - 1)) {
						end_num = row_pokazatel_start[i + 1][0];
					} else
						end_num = newTab.length;
				}
				System.out.println("start " + row_pokazatel_start[i][j] + " end " + (end_num - 1));
				for (int row = row_pokazatel_start[i][j]; row < end_num; row++) {
					cellVolume = newTab[row][2];
					cellVolume = cellVolume.trim();
					System.out.println("000 razmernost " + row + "-" + newTab[row][3]);
					if (newTab[row][3].contains("Bq") || newTab[row][3].contains("Bq")) {
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
			System.out.println("razmernost " + i + "-" + razmernost[i]);
			if (razmernost[i] == null) {
				razmernost[i] = razmernost[i - 1];
			}
			System.out.println("razmernost " + i + "-" + razmernost[i]);
		}

		for (int i = 0; i < counts_samples; i++) {
			for (int j = 0; j <= max_num_pokazatel[i]; j++) {

				for (int k = 0; k <= max_num_results[i][j]; k++) {
					if (results_value[i][j][k] != null) {
						if (results_value[i][j][k].contains("*")) {
							flag_note = true;
						}
						results_value_str = results_value[i][j][k].replace("Е", "E");
						if (results_value_str.startsWith("<")) {

							results_MDA[i][j][k] = Double
									.valueOf(results_value_str.substring(results_value_str.indexOf("<") + 1));
							System.out.println("MDA " + results_MDA[i][j][k] + "  " + formatter(results_MDA[i][j][k]));

						} else {
							results_value_result[i][j][k] = Double
									.valueOf(results_value_str.substring(0, results_value_str.indexOf("±")).trim());
							System.out.println("Values " + results_value_result[i][j][k] + "  "
									+ formatter(results_value_result[i][j][k]));
							results_uncertainty[i][j][k] = Double.valueOf(results_value_str
									.substring(results_value_str.indexOf("±") + 1).replace("*", "").trim());
							System.out.println("Uncertainty " + results_uncertainty[i][j][k] + " "
									+ alignExpon(results_value_result[i][j][k], results_uncertainty[i][j][k]));
						}

						System.out.println("*sample " + i + " pokazatel-" + j + " results " + results_nuklide[i][j][k]
								+ " results " + results_value[i][j][k] + " niclude " + results_nuklide[i][j][k]);
						nuclide_sample[i][j][k] = NuclideDAO.getValueNuclideBySymbol(results_nuklide[i][j][k]);
					}
				}

			}

		}
		String basic_value = "";
		Boolean inProtokol = true;
		Period period = PeriodDAO.getPeriodById(1);
		int sigma = 2;
		if (section) {
			ind_num_doc = Ind_num_docDAO.getValueInternal_applicantById(3);
			internal_aplicant = Internal_applicantDAO.getValueInternal_applicantById(1);
		}
		Razmernosti razmernost_recuest = RazmernostiDAO.getValueRazmernostiByName(razmernost[0]);
		Dimension dimension = null;
		Double quantity = 1.0;
		if (razmernost[0].contains("/")) {
			String dmen = razmernost[0].substring(razmernost[0].indexOf("/") + 1);
			dimension = DimensionDAO.getValueDimensionByName(dmen);
			quantity = 0.0;

		}

		Users user_recues = UsersDAO.getValueUsersById(1);
		Users user_chim_oper = UsersDAO.getValueUsersById(1);
		Users user_measur = UsersDAO.getValueUsersById(1);
		Users user_redac = UsersDAO.getValueUsersById(1);
		if (zabelejka_recuest != null) {
			note = ZabelejkiDAO.getValueZabelejkiByName(zabelejka_recuest);
		}

		description_sample_group = "";
		for (int i = 0; i < counts_samples; i++) {
			description_sample_group = description_sample_group + recuest_code + "-" + (i + 1) + "/"
					+ sample_description[i] + ";\r";
		}
		Obekt_na_izpitvane_request ob_izpitvane_request = Obekt_na_izpitvane_requestDAO
				.getValueObekt_na_izpitvane_requestByName(obekt_na_izpitvane_request);

		System.out.println("ACCREDITATION " + accreditation);
		System.out.println("APLICANT_FAMILY " + aplicant_family);
		System.out.println("APLICANT_NAME " + aplicant_name);
		System.out.println("DATE_EXECUTION " + date_execution);
		System.out.println("DATE_REQUEST " + date_recuest);
		System.out.println("DATE_TIME_RECEPTION " + date_time_reception);
		System.out.println("DESCRIPTION_SAMPLE_GROUP " + description_sample_group);
		System.out.println("COUNTS_SAMPLES " + counts_samples);
		System.out.println("RECUEST_CODE " + recuest_code);
		System.out.println("SECTION " + section);

		System.out.println("EXTERNAL_APLICANT " + external_aplicant);
		System.out.println("IND_NUM_DOC " + ((ind_num_doc == null) ? ind_num_doc : ind_num_doc.getName()));
		System.out.println("INTERNAL_APLICANT " + ((internal_aplicant == null) ? internal_aplicant
				: internal_aplicant.getInternal_applicant_organization()));
		System.out.println("IZPITVAN_PRODUKT " + izpitvan_produkt.getName_zpitvan_produkt());
		System.out.println("RAZMERNOST " + razmernost_recuest.getName_razmernosti());
		System.out.println("USERS " + user_recues.getName_users());
		System.out.println("ZABELEJKI " + ((note == null) ? note : note.getName_zabelejki()));
		System.out.println("OBEKT_NA_IZPITVANE_REQUEST " + ob_izpitvane_request.getName_obekt_na_izpitvane());

		Request request = new Request(recuest_code, date_recuest, accreditation, // accreditation
				section, // section
				null, // xtra_module
				counts_samples, // counts_samples
				description_sample_group, // description_sample_group
				date_time_reception, // date_time_reception
				date_execution, // date_execution
				ind_num_doc, // ind_num_doc
				izpitvan_produkt, // izpitvan_produkt
				razmernost_recuest, // razmernosti
				note, // zabelejki
				user_recues, // users
				ob_izpitvane_request);
		RequestDAO.setValueRequest(request);

		/** --------------------------------------------------------------- **/

		for (int i = 0; i < counts_samples; i++) {
			Razmernosti razmernosti = RazmernostiDAO.getValueRazmernostiByName(razmernost[i]);
			System.out.println("REQUEST " + request.getRecuest_code());
			System.out.println("SAMPLE_CODE " + sample_code[i]);
			System.out.println("SAMPLE_DESCRIPTION " + sample_description[i]);
			System.out.println("DATE_TIME_REFERENCE " + date_time_reference);
			System.out.println("OBECT_NA_IZPITVANE_SAMPLE " + ob_na_izpit[i]);
			Sample samp = new Sample(sample_code[i], sample_description[i], date_time_reference, request,
					Obekt_na_izpitvane_sampleDAO.getValueObekt_na_izpitvane_sampleByName(ob_na_izpit[i]), period,2017);
			SampleDAO.setValueSample(samp);
			for (int j = 0; j <= max_num_pokazatel[i]; j++) {
				Metody metody_sample = MetodyDAO.getValueList_MetodyByName(metody[i][j]);
				System.out.println("IZPITVAN_POKAZATEL " + pokazatel_sample[i][j].getName_pokazatel());
				System.out.println("SAMPLE " + samp.getSample_code());
				System.out.println("METOD_NA_IZPITVANE " + metody_sample.getName_metody());
				Izpitvan_pokazatel izpitvan_pokazatel = new Izpitvan_pokazatel(pokazatel_sample[i][j], samp,
						metody_sample);
				Izpitvan_pokazatelDAO.setValueIzpitvan_pokazatel(izpitvan_pokazatel);
				for (int k = 0; k <= max_num_results[i][j]; k++) {

					System.out.println("BASIC_VALUE " + basic_value);
					System.out.println("DATE_CHIM_OPER " + date_chim);
					System.out.println("DATE_MEASUR " + date_measur);
					System.out.println("DATE_REDAC " + date_redac);
					System.out.println("IN_PROTOKOL " + inProtokol);
					System.out.println("MDA " + results_MDA[i][j][k]);
					System.out.println("SIGMA " + sigma);
					System.out.println("UNCERTAINTY " + results_uncertainty[i][j][k]);
					System.out.println("VALUE_RESULT " + results_value_result[i][j][k]);

					System.out.println("IZPITVAN_POKAZATEL " + izpitvan_pokazatel.getId_pokazatel());
					System.out.println("RAZMERNOSTY " + razmernost_recuest.getName_razmernosti());
					System.out.println("USER_CHIM " + user_chim_oper.getName_users());
					System.out.println("USER_MEASUR " + user_measur.getName_users());
					System.out.println("USER_REDAC " + user_redac.getName_users());
					System.out.println("ZABELEJKI " + ((note == null) ? note : note.getName_zabelejki()));
					System.out.println("QUANTITY " + quantity);
					System.out
							.println("DIMENSION " + ((dimension == null) ? dimension : dimension.getName_dimension()));
					Results resul = new Results(nuclide_sample[i][j][k], izpitvan_pokazatel, razmernost_recuest,
							basic_value, results_value_result[i][j][k], sigma, results_uncertainty[i][j][k],
							results_MDA[i][j][k], note, user_chim_oper, date_chim, user_measur, date_measur, user_redac,
							date_redac, inProtokol, quantity, dimension);
					ResultsDAO.setValueResults(resul);
				}

			}
		}

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
		if (!str_foll.contains("E-")) { // don't blast a negative sign
			str_foll = str_foll.replace("E", "E+");
		}
		str_foll = str_foll.replace(",", ".");
		return str_foll;
	}

	public static String addDate(String dt, int date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DATE, date); // number of days to add
		dt = sdf.format(c.getTime()); // dt is now the new date
		return dt;
	}
}