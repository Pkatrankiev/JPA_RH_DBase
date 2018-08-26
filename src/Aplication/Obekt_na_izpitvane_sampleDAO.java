package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.External_applicant;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Obekt_na_izpitvane_sample;

public class Obekt_na_izpitvane_sampleDAO {

	static String name_DBase = "JPA_RH_DBase";

	public static void setBasicValueObekt_na_izpitvane_sample() {
		setValueObekt_na_izpitvane_sample("Вентилационна тръба-1");
		setValueObekt_na_izpitvane_sample("Вентилационна тръба-2");
		setValueObekt_na_izpitvane_sample("Спец корпус-1");
		setValueObekt_na_izpitvane_sample("Спец корпус-2");
		setValueObekt_na_izpitvane_sample("БАК 1и2");

	}

	// Obekt_na_izpitvane
	public static void setValueObekt_na_izpitvane_sample(String value) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
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
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Obekt_na_izpitvane_sample e");
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
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Obekt_na_izpitvane_sample obekt_na_izpitvane = (Obekt_na_izpitvane_sample) entitymanager
				.find(Obekt_na_izpitvane_sample.class, id);

		entitymanager.close();
		emfactory.close();

		return obekt_na_izpitvane;
	}

	@GET
	public static Obekt_na_izpitvane_sample getValueObekt_na_izpitvane_sampleByName(String name) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Obekt_na_izpitvane_sample e WHERE e.name = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		if (query.getResultList().isEmpty()) {
			setValueObekt_na_izpitvane_sample(name);
		}
		Obekt_na_izpitvane_sample list = (Obekt_na_izpitvane_sample) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}
}
