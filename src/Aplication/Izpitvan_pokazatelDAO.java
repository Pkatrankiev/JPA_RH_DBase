package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Izpitvan_pokazatel;
import DBase_Class.Obekt_na_izpitvane;

public class Izpitvan_pokazatelDAO {
	
static String name_DBase = "JPA_RH_DBase";
	
public static void setBasikValuePokazatel(){
	setValuePokazatel("Гама излъчващи радионуклиди");
	setValuePokazatel("Алфа радионуклиди");
	setValuePokazatel("Бета радионуклиди");
	
}


//	Pokazatel
	public static void setValuePokazatel(String value) {
		// Bq
		// kBq
		// Bq/g
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Izpitvan_pokazatel pokazatal = new Izpitvan_pokazatel();
		pokazatal.setName_pokazatel(value);
		entitymanager.persist(pokazatal);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Izpitvan_pokazatel> getInListAllValuePokazatel() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Pokazatel e");
		List<Izpitvan_pokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (Izpitvan_pokazatel e : list) {
			System.out.println(
					"Num:" + ((Izpitvan_pokazatel) e).getId_pokazatel() + "  NAME :" + ((Izpitvan_pokazatel) e).getName_pokazatel());
		}
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Izpitvan_pokazatel getValueIzpitvan_pokazatelById(@QueryParam("id") int id) {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
	EntityManager entitymanager = emfactory.createEntityManager();
	entitymanager.getTransaction().begin();
	
	Izpitvan_pokazatel  izpitvan_pokazatel = (Izpitvan_pokazatel) entitymanager.find(Izpitvan_pokazatel.class, id);
	
	entitymanager.close();
	emfactory.close();

	return izpitvan_pokazatel;
}

	
	
}
