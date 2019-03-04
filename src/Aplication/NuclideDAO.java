package Aplication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Izpitvan_produkt;
import DBase_Class.Metody;
import DBase_Class.Nuclide;
import DBase_Class.Request;
import DBase_Class.Sample;

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

	public static void setValueNuclide(Nuclide nuclide) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(nuclide);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static void updateNuclide(Nuclide nuclide) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.find(Nuclide.class, nuclide.getId_nuclide());
		entitymanager.merge(nuclide);
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	
	public static List<Nuclide> getInListAllValueNuclide() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Nuclide e ORDER BY e.symbol ASC");
		@SuppressWarnings("unchecked")
		List<Nuclide> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		
		return list;
	}

	public static void setBasicValueNuclide() {
		
		setValueNuclide("�������", "Beryllium", "7Be", 4.598E+06, 't', true);
		setValueNuclide("������", "Natrium", "22Na", 8.214E+07, 't', true);
		setValueNuclide("�����", "Kalium", "40K", 3.946E+16, 't', false);
		setValueNuclide("����", "Chromium", "51Cr", 2.394E+06, 't', true);
		setValueNuclide("������", "Manganum", "54Mn", 2.697E+07, 't', true);
		setValueNuclide("������", "Cobaltum", "57Co", 2.348E+07, 't', true);
		setValueNuclide("������", "Cobaltum", "58Co", 6.120E+06, 't', true);
		setValueNuclide("������", "Ferrum", "59Fe", 3.844E+06, 't', true);
		setValueNuclide("������", "Cobaltum", "60Co", 1.663E+08, 't', true);
		setValueNuclide("����", "Zincum", "65Zn", 2.108E+07, 't', true);
		setValueNuclide("�����", "Selenium", "75Se", 1.035E+07, 't', true);
		setValueNuclide("�������", "Krypton", "85Kr", 3.393E+08, 't', true);
		setValueNuclide("��������", "Strontium", "85Sr", 5.603E+06, 't', true);
		setValueNuclide("�����", "Yttrium", "88Y", 9.213E+06, 't', true);
		setValueNuclide("������", "Niobium", "92Nb", 1.104E+15, 't', true);
		setValueNuclide("������", "Niobium", "92mNb", 8.770E+05, 't', true);
		setValueNuclide("������", "Niobium", "94Nb", 6.311E+11, 't', true);
		setValueNuclide("������", "Niobium", "95Nb", 3.023E+06, 't', true);
		setValueNuclide("��������", "Zirconium", "95Zr", 5.532E+06, 't', false);
		setValueNuclide("�������", "Ruthenium", "103Ru", 3.392E+06, 't', true);
		setValueNuclide("�������", "Ruthenium", "106Ru", 3.219E+07, 't', true);
		setValueNuclide("������", "Argentum", "108mAg", 1.382E+10, 't', true);
		setValueNuclide("������", "Cadmium", "109Cd", 3.991E+07, 't', true);
		setValueNuclide("������", "Argentum", "110mAg", 2.158E+07, 't', true);
		setValueNuclide("�����", "Stannum", "113Sn", 9.944E+06, 't', true);
		setValueNuclide("�������", "Stibium", "124Sb", 5.202E+06, 't', true);
		setValueNuclide("�������", "Stibium", "125Sb", 8.705E+07, 't', true);
		setValueNuclide("���", "Iodum", "131I", 6.932E+05, 't', true);
		setValueNuclide("�����", "Barium", "133Ba", 3.326E+08, 't', true);
		setValueNuclide("�����", "Caesium", "134Cs", 6.517E+07, 't', true);
		setValueNuclide("�����", "Caesium", "137Cs", 9.483E+08, 't', true);
		setValueNuclide("�����", "Cerium", "139Ce", 1.189E+07, 't', true);
		setValueNuclide("�����", "Barium", "140Ba", 1.102E+06, 't', true);
		setValueNuclide("�����", "Cerium", "141Ce", 2.808E+06, 't', true);
		setValueNuclide("�����", "Cerium", "144Ce", 2.461E+07, 't', true);
		setValueNuclide("�������", "Europium", "152Eu", 4.267E+08, 't', true);
		setValueNuclide("�������", "Europium", "154Eu", 2.714E+08, 't', true);
		setValueNuclide("�������", "Europium", "155Eu", 1.500E+08, 't', true);
		setValueNuclide("������", "Holmium", "166mHo", 3.787E+10, 't', true);
		setValueNuclide("������", "Tantalum", "182Ta182", 9.902E+06, 't', true);
		setValueNuclide("������", "Iridium", "192Ir", 6.379E+06, 't', true);
		setValueNuclide("�����", "Hydrargyrum", "203Hg", 4.026E+06, 't', true);
		setValueNuclide("�����", "Plumbum", "210Pb", 7.015E+08, 't', true);
		setValueNuclide("��������", "Neptunium", "239Np", 2.036E+05, 't', true);
		setValueNuclide("��������", "Americium", "241Am", 1.365E+10, 't', true);
	
		}
	
	@GET
	@QueryParam("{id}")
	public static Nuclide getValueSNuclideById(@QueryParam("id") int id) {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
	EntityManager entitymanager = emfactory.createEntityManager();
	entitymanager.getTransaction().begin();
	Nuclide  nuclide = (Nuclide) entitymanager.find(Nuclide.class, id);
	
	entitymanager.close();
	emfactory.close();

	return nuclide;
}
	
	@GET
	public static Nuclide getValueNuclideBySymbol(String symbol) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Nuclide e WHERE e.symbol = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", symbol);
		if (query.getResultList().isEmpty()){
			setValueNuclide("-", "-", symbol, 0.0, '-', true);	
		}
		Nuclide list = (Nuclide) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static String[] getMasiveStringAllValueNuclide(){
		 List<Nuclide> list = getInListAllValueNuclide();
		String[] values = new String[list.size()];
		int i = 0;
		for (Nuclide izpitvan_produkt : list) {
			values[i] = izpitvan_produkt.getSymbol_nuclide();
			i++;
		}
		return values;
	}
}

	

