package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Otclonenie;


public class OtclonenieDAO {
	static String name_DBase = "JPA_RH_DBase";

	public static void setValueOtclon(String value) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Otclonenie valueEnt = new Otclonenie();
		valueEnt.setName_otclon(value);
		
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Otclonenie> getInListAllValueOtclon() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Otclonenie e");
		@SuppressWarnings("unchecked")
		List<Otclonenie> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Otclonenie getValueOtclonById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Otclonenie otclon = (Otclonenie) entitymanager.find(Otclonenie.class, id);

		entitymanager.close();
		emfactory.close();

		return otclon;
	}

	public static String[] getMasiveStringAllValueOtclon() {
		List<Otclonenie> list = getInListAllValueOtclon();
		String[] values = new String[list.size()];
		int i = 0;
		for (Otclonenie otclon : list) {
			values[i] = otclon.getName_otclon();
			i++;
		}
		return values;
	}

	@SuppressWarnings("unchecked")
	@GET
	public static Otclonenie getValueOtclonByName(String name) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		String hql = "SELECT e FROM Otclonenie e WHERE e.name = :text";
		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		
		List<Otclonenie> list = query.getResultList();
		
		if (list.isEmpty()){
			setValueOtclon(name);
		 list = query.getResultList();
		}
	
		entitymanager.close();
		emfactory.close();

		return list.get(0);
	}

	public static void saveOtclonWitchCheck(String value) {
		String[] masive_Otclon = getMasiveStringAllValueOtclon();
		Boolean fl_Otclon = false;
		for (String string : masive_Otclon) {
			if (string.equals(value)) {
				fl_Otclon = true;
			}
		}
		if(!fl_Otclon){
			setValueOtclon( value);
	}
	}
}
