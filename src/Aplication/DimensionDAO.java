package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Dimension;


public class DimensionDAO {

	static String name_DBase = "JPA_RH_DBase";

	public static void setBasicValueDimension() {

		setValueDimension("g");
		setValueDimension("mL");
		setValueDimension("L");
		setValueDimension("m3");

	}

	public static void setValueDimension(String value) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Dimension valueEnt = new Dimension();
		valueEnt.setName_dimension(value);
		;
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Dimension> getInListAllValueDimension() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Dimension e");
		List<Dimension> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (Dimension e : list) {
			System.out.println(
					"Num:" + ((Dimension) e).getId_dimension() + "  NAME :" + ((Dimension) e).getName_dimension());
		}
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Dimension getValueDimensionById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Dimension razmernosti = (Dimension) entitymanager.find(Dimension.class, id);

		entitymanager.close();
		emfactory.close();

		return razmernosti;
	}

	@GET
	public static Dimension getValueDimensionByName(String name) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Dimension e WHERE e.name = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		if (query.getResultList().isEmpty()) {
			setValueDimension(name);
		}
		
		Dimension list = (Dimension) query.getResultList().get(0);
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static String[] getMasiveStringAllValueDimension(){
		 List<Dimension> list = getInListAllValueDimension();
		String[] values = new String[list.size()];
		int i = 0;
		for (Dimension izpitvan_produkt : list) {
			values[i] = izpitvan_produkt.getName_dimension();
			i++;
		}
		return values;
	}
}
