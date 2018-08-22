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

public class Obekt_na_izpitvane_requestDAO {

static String name_DBase = "JPA_RH_DBase";
	
public static void setBasicValueObekt_na_izpitvane(){
	setValueObekt_na_izpitvane("Вентилационна тръба-1 и Вентилационна тръба-2");
	setValueObekt_na_izpitvane("Спец корпус-1 и Спец корпус-2");
	setValueObekt_na_izpitvane("БАК 1и2");
	setValueObekt_na_izpitvane("БНС-1, СК-1");
	setValueObekt_na_izpitvane("БНС-2, СК-1");
	setValueObekt_na_izpitvane("БВС-1");
	setValueObekt_na_izpitvane("БВС-2");

}

//	Obekt_na_izpitvane
	public static void setValueObekt_na_izpitvane(String value) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Obekt_na_izpitvane_request valueEnt = new Obekt_na_izpitvane_request();
		valueEnt.setName_obekt_na_izpitvane(value);
		;
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Obekt_na_izpitvane_request> getInListAllValueObekt_na_izpitvane() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Obekt_na_izpitvane_request e");
		List<Obekt_na_izpitvane_request> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (Obekt_na_izpitvane_request e : list) {
			System.out.println("Num:" + ((Obekt_na_izpitvane_request) e).getId_obekt_na_izpitvane() + "  NAME :"
					+ ((Obekt_na_izpitvane_request) e).getName_obekt_na_izpitvane());
		}
		return list;
	}
	
	public static String [] getMasiveStringAllValueObekt_na_izpitvane() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Obekt_na_izpitvane_request e");
		List<Obekt_na_izpitvane_request> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		String[] values = new String[list.size()];
		int i = 0;
		for (Obekt_na_izpitvane_request e : list) { 
			values[i] = e.getName_obekt_na_izpitvane();
			i++;
			}
		return values;
	}

	
	@GET
	@QueryParam("{id}")
public static Obekt_na_izpitvane_request getValueObekt_na_izpitvaneById(@QueryParam("id") int id) {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
	EntityManager entitymanager = emfactory.createEntityManager();
	entitymanager.getTransaction().begin();
	Obekt_na_izpitvane_request  obekt_na_izpitvane = (Obekt_na_izpitvane_request) entitymanager.find(Obekt_na_izpitvane_request.class, id);
	
	entitymanager.close();
	emfactory.close();

	return obekt_na_izpitvane;
}
	
	@GET
	public static Obekt_na_izpitvane_request getValueObekt_na_izpitvane_requestByName(String name) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Obekt_na_izpitvane_request e WHERE e.name = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		if (query.getResultList().isEmpty()){
			setValueObekt_na_izpitvane(name);	
		}
		Obekt_na_izpitvane_request list = (Obekt_na_izpitvane_request) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
}
