package WindowView;

import javax.swing.JTable;

import Aplication.Izpitvan_produktDAO;
import DBase_Class.Izpitvan_produkt;

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

		for (int tab = 0; tab < celsTranfer.length; tab++) {

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
						
						/** DATE_TIME_REFERENCE in SAMPLE Class -referentna data i chas- **/
						if (cellVolume.startsWith("Референтна дата")) {
							date_time_reference = celsTranfer[tab][row][coll + 1].replaceAll(" ", "").replaceAll("h", "");
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

		System.out.println("RECUEST_CODE " + recuest_code);
		System.out.println("ACCREDITATION " + accreditation);
		System.out.println("DATE_TIME_RECEPTION " + date_time_reception);
		System.out.println("DATE_REQUEST " + date_recuest);
		System.out.println("DATE_TIME_REFERENCE " + date_time_reference);
		System.out.println("IZPITVAN_PRODUKT " + izpitvan_produkt.getName_zpitvan_produkt() + " № "
				+ izpitvan_produkt.getId_zpitvan_produkt());
		System.out.println("DESCRIPTION_SAMPLE_GROUP " + description_sample_group);
		
		
		/** NUMBER_SAMPLES in RECUEST Class -broi na probite- **/
		/** DESCRIPTION_SAMPLE in SAMPLE Class -opisanie na probite- **/
		int num_start = 0;
		int num_end = 0;
		String str;
		int number_samples = description_sample_group.replaceAll("[^" + recuest_code + "]", "").length() / recuest_code.length();
		System.out.println("NUMBER_SAMPLES " + number_samples);
		
		String[] ob_na_izpit = new String[number_samples];
		for (int i = 0; i < ob_na_izpit.length; i++) {
			num_end = obekt_na_izpitvane.indexOf("\r", num_start+1);
			if(num_end<0){
				num_end = obekt_na_izpitvane.length();
						}
			str = obekt_na_izpitvane.substring(num_start, num_end);
			num_start = num_end;
			ob_na_izpit[i] = str.trim();
			System.out.println("OBEKT_NA_IZPITVANE " + (i+1) +" - " + ob_na_izpit[i]+".");
		}
		
		num_start = 0;
		num_end = 0;
		String[] sample = new String[number_samples];
		for (int i = 0; i < sample.length; i++) {
			num_end = description_sample_group.indexOf(recuest_code, num_start+1);
			if(num_end<0){
				num_end = description_sample_group.length();
						}
			str = description_sample_group.substring(num_start, num_end);
			num_start = num_end;
			sample[i] = str.substring((str.indexOf("/",0)+1), str.length()).trim();
			System.out.println("DESCRIPTION_SAMPLE " + (i+1) +" - " + sample[i]+".");
		}
		
		
	}

}
