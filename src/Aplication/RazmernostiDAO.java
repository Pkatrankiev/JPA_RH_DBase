package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.External_applicant;
import DBase_Class.Razmernosti;

public class RazmernostiDAO {

	static String name_DBase = "JPA_RH_DBase";

	public static void setBasicValueRazmernosti(){
		setValueRazmernosti("Bq");
		setValueRazmernosti("Bq/g");
		setValueRazmernosti("Bq/mL");
		setValueRazmernosti("Bq/L");
		setValueRazmernosti("Bq/m3");
		
	}
	

	public static void setValueRazmernosti(String value) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Razmernosti valueEnt = new Razmernosti();
		valueEnt.setName_razmernosti(value);
		;
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Razmernosti> getInListAllValueRazmernosti() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Razmernosti e");
		List<Razmernosti> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (Razmernosti e : list) {
			System.out.println("Num:" + ((Razmernosti) e).getId_razmernosti() + "  NAME :"
					+ ((Razmernosti) e).getName_razmernosti());
		}
		return list;
	}

	
	@GET
	@QueryParam("{id}")
public static Razmernosti getValueRazmernostiById(@QueryParam("id") int id) {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
	EntityManager entitymanager = emfactory.createEntityManager();
	entitymanager.getTransaction().begin();
	
	Razmernosti  razmernosti = (Razmernosti) entitymanager.find(Razmernosti.class, id);
	
	entitymanager.close();
	emfactory.close();

	return razmernosti;
}
}
