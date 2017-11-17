package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.External_applicant;
import DBase_Class.Obekt_na_izpitvane;

public class Obekt_na_izpitvaneDAO {

static String name_DBase = "JPA_RH_DBase";
	
public static void setBasicValueObekt_na_izpitvane(){
	setValueObekt_na_izpitvane("Вентилационна тръба-1");
	setValueObekt_na_izpitvane("Вентилационна тръба-2");
	setValueObekt_na_izpitvane("Спец корпус-1");
	setValueObekt_na_izpitvane("Спец корпус-2");
	setValueObekt_na_izpitvane("БАК 1и2");
	setValueObekt_na_izpitvane("БНС-1");
	setValueObekt_na_izpitvane("БНС-2");
	setValueObekt_na_izpitvane("БВС-1");
	setValueObekt_na_izpitvane("БВС-2");

}

//	Obekt_na_izpitvane
	public static void setValueObekt_na_izpitvane(String value) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Obekt_na_izpitvane valueEnt = new Obekt_na_izpitvane();
		valueEnt.setName_obekt_na_izpitvane(value);
		;
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Obekt_na_izpitvane> getInListAllValueObekt_na_izpitvane() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Obekt_na_izpitvane e");
		List<Obekt_na_izpitvane> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (Obekt_na_izpitvane e : list) {
			System.out.println("Num:" + ((Obekt_na_izpitvane) e).getId_obekt_na_izpitvane() + "  NAME :"
					+ ((Obekt_na_izpitvane) e).getName_obekt_na_izpitvane());
		}
		return list;
	}

	
	@GET
	@QueryParam("{id}")
public static Obekt_na_izpitvane getValueObekt_na_izpitvaneById(@QueryParam("id") int id) {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
	EntityManager entitymanager = emfactory.createEntityManager();
	entitymanager.getTransaction().begin();
	Obekt_na_izpitvane  obekt_na_izpitvane = (Obekt_na_izpitvane) entitymanager.find(Obekt_na_izpitvane.class, id);
	
	entitymanager.close();
	emfactory.close();

	return obekt_na_izpitvane;
}
	
	
}
