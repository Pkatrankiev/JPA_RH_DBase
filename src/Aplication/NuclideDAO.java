package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import AddResultViewFunction.SortListObjectByField;
import DBase_Class.Nuclide;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class NuclideDAO {


//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	

//	Nuclide
	public static void setValueNuclide(String name_bg, String name_en, String symbol, Double half_life,
			Character genesis, Boolean favorite, int ejection_key ) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
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

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		entitymanager.persist(nuclide);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static void updateNuclide(Nuclide nuclide) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		entitymanager.find(Nuclide.class, nuclide.getId_nuclide());
		entitymanager.merge(nuclide);
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	
	public static List<Nuclide> getInListAllValueNuclide() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Nuclide e ORDER BY e.symbol ASC");
		@SuppressWarnings("unchecked")
		List<Nuclide> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		
		return SortListObjectByField.sortListNuclideByCodedNuclide(list);
	}


	@GET
	@QueryParam("{id}")
	public static Nuclide getValueNuclideById(@QueryParam("id") int id) {
//	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
	Nuclide  nuclide = (Nuclide) entitymanager.find(Nuclide.class, id);
	
	entitymanager.close();
	emfactory.close();

	return nuclide;
}
	
	@GET
	public static Nuclide getValueNuclideBySymbol(String symbol) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Nuclide e WHERE e.symbol = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", symbol);
		Nuclide list;
		if (query.getResultList().isEmpty()) {
			list = null;
		} else
			list = (Nuclide) query.getSingleResult();
		
		entitymanager.close();
		emfactory.close();

		return list;
	}

	@GET
	public static List<Nuclide> getListNuclideEjection() {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Nuclide e WHERE e.ejection_key IS NOT NULL ORDER BY e.symbol ASC";
		
		Query query = entitymanager.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Nuclide> list = query.getResultList();
		
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

	

