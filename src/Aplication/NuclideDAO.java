package Aplication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import DBase_Class.Nuclide;

public class NuclideDAO {


	static String name_DBase = "JPA_RH_DBase";

//	Nuclide
	public static void setValueNuclide(String name_bg, String name_en, String symbol, Double half_life,
			Character genesis, Boolean favorite) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Nuclide valueEnt = new Nuclide();
		valueEnt.setName_bg_nuclide(name_bg);
		valueEnt.setName_en_nuclide(name_en);
		valueEnt.setSymbol_nuclide(symbol);
		valueEnt.setHalf_life_nuclide(half_life);
		valueEnt.setGenesis_nuclide(genesis);
		valueEnt.setFavorite_nuclide(favorite);

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Nuclide> getInListAllValueNuclide() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Nuclide e");
		List<Nuclide> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (Nuclide e : list) {
			System.out.println("Num:" + ((Nuclide) e).getId_nuclide() + "  NAME_BG :"
					+ ((Nuclide) e).getName_bg_nuclide() + "  NAME_EN :" + ((Nuclide) e).getName_en_nuclide()
					+ "  Symbol :" + ((Nuclide) e).getSymbol_nuclide() + "  Half-Life :"
					+ ((Nuclide) e).getHalf_life_nuclide() + "  Genesis :" + ((Nuclide) e).getGenesis_nuclide()
					+ "  Favorite :" + ((Nuclide) e).getFavorite_nuclide());
		}
		return list;
	}

	public static void setBasicValueNuclide() {
		
		setValueNuclide("�������", "Beryllium", "BE-7", 4.598E+06, 't', true);
		setValueNuclide("������", "Natrium", "NA-22", 8.214E+07, 't', true);
		setValueNuclide("�����", "Kalium", "K-40", 3.946E+16, 't', false);
		setValueNuclide("����", "Chromium", "CR-51", 2.394E+06, 't', true);
		setValueNuclide("������", "Manganum", "MN-54", 2.697E+07, 't', true);
		setValueNuclide("������", "Cobaltum", "CO-57", 2.348E+07, 't', true);
		setValueNuclide("������", "Cobaltum", "CO-58", 6.120E+06, 't', true);
		setValueNuclide("������", "Ferrum", "FE-59", 3.844E+06, 't', true);
		setValueNuclide("������", "Cobaltum", "CO-60", 1.663E+08, 't', true);
		setValueNuclide("����", "Zincum", "ZN-65", 2.108E+07, 't', true);
		setValueNuclide("�����", "Selenium", "SE-75", 1.035E+07, 't', true);
		setValueNuclide("�������", "Krypton", "KR-85", 3.393E+08, 't', true);
		setValueNuclide("��������", "Strontium", "SR-85", 5.603E+06, 't', true);
		setValueNuclide("�����", "Yttrium", "Y-88", 9.213E+06, 't', true);
		setValueNuclide("������", "Niobium", "Nb-92", 1.104E+15, 't', true);
		setValueNuclide("������", "Niobium", "Nb-92m", 8.770E+05, 't', true);
		setValueNuclide("������", "Niobium", "Nb-94", 6.311E+11, 't', true);
		setValueNuclide("������", "Niobium", "NB-95", 3.023E+06, 't', true);
		setValueNuclide("��������", "Zirconium", "ZR-95", 5.532E+06, 't', false);
		setValueNuclide("�������", "Ruthenium", "RU-103", 3.392E+06, 't', true);
		setValueNuclide("�������", "Ruthenium", "RU-106", 3.219E+07, 't', true);
		setValueNuclide("������", "Argentum", "AG-108M", 1.382E+10, 't', true);
		setValueNuclide("������", "Cadmium", "CD-109", 3.991E+07, 't', true);
		setValueNuclide("������", "Argentum", "AG-110M", 2.158E+07, 't', true);
		setValueNuclide("�����", "Stannum", "SN-113", 9.944E+06, 't', true);
		setValueNuclide("�������", "Stibium", "SB-124", 5.202E+06, 't', true);
		setValueNuclide("�������", "Stibium", "SB-125", 8.705E+07, 't', true);
		setValueNuclide("���", "Iodum", "I-131", 6.932E+05, 't', true);
		setValueNuclide("�����", "Barium", "BA-133", 3.326E+08, 't', true);
		setValueNuclide("�����", "Caesium", "CS-134", 6.517E+07, 't', true);
		setValueNuclide("�����", "Caesium", "CS-137", 9.483E+08, 't', true);
		setValueNuclide("�����", "Cerium", "CE-139", 1.189E+07, 't', true);
		setValueNuclide("�����", "Barium", "BA-140", 1.102E+06, 't', true);
		setValueNuclide("�����", "Cerium", "CE-141", 2.808E+06, 't', true);
		setValueNuclide("�����", "Cerium", "CE-144", 2.461E+07, 't', true);
		setValueNuclide("�������", "Europium", "EU-152", 4.267E+08, 't', true);
		setValueNuclide("�������", "Europium", "EU-152", 4.267E+08, 't', true);
		setValueNuclide("�������", "Europium", "EU-154", 2.714E+08, 't', true);
		setValueNuclide("�������", "Europium", "EU-155", 1.500E+08, 't', true);
		setValueNuclide("������", "Holmium", "Ho-166m", 3.787E+10, 't', true);
		setValueNuclide("������", "Tantalum", "Ta-182", 9.902E+06, 't', true);
		setValueNuclide("������", "Iridium", "IR-192", 6.379E+06, 't', true);
		setValueNuclide("������", "Iridium", "IR-192", 6.379E+06, 't', true);
		setValueNuclide("�����", "Hydrargyrum", "HG-203", 4.026E+06, 't', true);
		setValueNuclide("�����", "Plumbum", "Pb-210", 7.015E+08, 't', true);
		setValueNuclide("��������", "Neptunium", "NP-239", 2.036E+05, 't', true);
		setValueNuclide("��������", "Americium", "AM-241", 1.365E+10, 't', true);
	
		}
	}

	

