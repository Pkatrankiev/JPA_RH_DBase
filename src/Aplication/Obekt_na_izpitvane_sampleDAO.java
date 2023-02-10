package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Obekt_na_izpitvane_sample;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class Obekt_na_izpitvane_sampleDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
	

	public static void setBasicValueObekt_na_izpitvane_sample() {
		setValueObekt_na_izpitvane_sample("Вентилационна тръба-1");
		setValueObekt_na_izpitvane_sample("Вентилационна тръба-2");
		setValueObekt_na_izpitvane_sample("Спец корпус-1");
		setValueObekt_na_izpitvane_sample("Спец корпус-2");
		setValueObekt_na_izpitvane_sample("БАК 1и2");

	}

	// Obekt_na_izpitvane
	public static void setValueObekt_na_izpitvane_sample(String value) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Obekt_na_izpitvane_sample valueEnt = new Obekt_na_izpitvane_sample();
		valueEnt.setName_obekt_na_izpitvane(value);
		;
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Obekt_na_izpitvane_sample> getInListAllValueObekt_na_izpitvane_sample() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Obekt_na_izpitvane_sample e ORDER BY e.name ASC");
		@SuppressWarnings("unchecked")
		List<Obekt_na_izpitvane_sample> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
			return list;
	}

	public static String [] getMasiveStringAllValueObekt_na_izpitvane_sample() {
		List<Obekt_na_izpitvane_sample> list = getInListAllValueObekt_na_izpitvane_sample();
		String[] values = new String[list.size()];
		int i = 0;
		for (Obekt_na_izpitvane_sample e : list) { 
			values[i] = e.getName_obekt_na_izpitvane();
			i++;
			}
		return values;
	}
	
	@GET
	@QueryParam("{id}")
	public static Obekt_na_izpitvane_sample getValueObekt_na_izpitvane_sampleById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Obekt_na_izpitvane_sample obekt_na_izpitvane = (Obekt_na_izpitvane_sample) entitymanager
				.find(Obekt_na_izpitvane_sample.class, id);

		entitymanager.close();
		emfactory.close();

		return obekt_na_izpitvane;
	}

	@SuppressWarnings("unchecked")
	@GET
	public static Obekt_na_izpitvane_sample getValueObekt_na_izpitvane_sampleByName(String name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Obekt_na_izpitvane_sample e WHERE e.name = :text";
		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		List<Obekt_na_izpitvane_sample> list = query.getResultList();
		
		entitymanager.close();
		emfactory.close();

		return list.get(0);
	}
	
	public static List<Obekt_na_izpitvane_sample> getValueObekt_na_izpitvane_sampleByMounthlyReferenceForCNRDWater_Table() {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		
		String hql = "SELECT e FROM Obekt_na_izpitvane_sample e WHERE e.name LIKE '%03D-SS02T%'";
		Query query = entitymanager.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Obekt_na_izpitvane_sample> list = query.getResultList();
		
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@GET
	public static Obekt_na_izpitvane_sample getValueObekt_na_izpitvane_sampleOrSaveByName(String name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Obekt_na_izpitvane_sample e WHERE e.name = :text";
		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		List<Obekt_na_izpitvane_sample> list = query.getResultList();
		
		if (list.isEmpty()){
			setValueObekt_na_izpitvane_sample(name);
		 list = query.getResultList();
		}
		
		entitymanager.close();
		emfactory.close();

		return list.get(0);
	}

	
}
