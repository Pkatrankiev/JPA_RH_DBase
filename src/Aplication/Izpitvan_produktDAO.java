package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import DBase_Class.Izpitvan_produkt;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class Izpitvan_produktDAO {

	// @PersistenceContext(name = "JPA_RH_DBase")
	// private static EntityManager eManager;

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	

	public static void setBasikValueIzpitvan_produkt() {
		setValueIzpitvan_produkt("Строителен материал");
		setValueIzpitvan_produkt("Технологичен отпадък");
		setValueIzpitvan_produkt("Течни изхвърляния");
		setValueIzpitvan_produkt("Води");
		setValueIzpitvan_produkt("Газообразни изхвърляния");
		setValueIzpitvan_produkt("Въздух");

	}

	// Izpitvan_produkt
	@POST
	public static void setValueIzpitvan_produkt(String value) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		// EntityManager entitymanager = DbConnection.DbConnection();

		Izpitvan_produkt valueEnt = new Izpitvan_produkt();
		valueEnt.setName_zpitvan_produkt(value);

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	@GET
	public Response getAll() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createNamedQuery("getAllValueIzpitvan_produkt");
		List<Izpitvan_produkt> nominees = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return Response.status(200).entity(nominees).build();
	}

	public static String[] getMasiveStringAllValueIzpitvan_produkt() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Izpitvan_produkt e");
		@SuppressWarnings("unchecked")
		List<Izpitvan_produkt> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		String[] values = new String[list.size()];
		int i = 0;
		for (Izpitvan_produkt izpitvan_produkt : list) {
			values[i] = izpitvan_produkt.getName_zpitvan_produkt();
			i++;
		}
		return values;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Izpitvan_produkt> getInListAllValueIzpitvan_produkt() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createQuery("SELECT e FROM Izpitvan_produkt e ORDER BY e.name ASC");
		List<Izpitvan_produkt> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (Izpitvan_produkt e : list) {
			System.out.println("Num:" + ((Izpitvan_produkt) e).getId_zpitvan_produkt() + "  NAME :"
					+ ((Izpitvan_produkt) e).getName_zpitvan_produkt());
		}
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Izpitvan_produkt getValueIzpitvan_produktById(@QueryParam("Id_izpitvan_produkt") int id) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();

		entitymanager.getTransaction().begin();

		Izpitvan_produkt izpitvan_produkt = entitymanager.find(Izpitvan_produkt.class, id);

		entitymanager.close();
		emfactory.close();

		return izpitvan_produkt;
	}

	@GET
	public static Izpitvan_produkt getValueIzpitvan_produktByName(String name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Izpitvan_produkt e WHERE e.name = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		if (query.getResultList().isEmpty()){
			setValueIzpitvan_produkt(name);	
		}
		Izpitvan_produkt list = (Izpitvan_produkt) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}

}
