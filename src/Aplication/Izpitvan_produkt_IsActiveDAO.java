package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Izpitvan_produkt;
import DBase_Class.Izpitvan_produkt_IsActive;

import GlobalVariable.GlobalVariableForSQL_DBase;

public class Izpitvan_produkt_IsActiveDAO {

	
	
	public static void setValueIzpitvan_produkt_IsActive(Izpitvan_produkt izpitvanProdukt, boolean koeficient) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Izpitvan_produkt_IsActive valueEnt = new Izpitvan_produkt_IsActive();
		
		valueEnt.setIzpitvanProdukt(izpitvanProdukt);
		valueEnt.setActive(koeficient);
		
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}	
	
	@SuppressWarnings("unchecked")
	@PersistenceContext
	public static List<Izpitvan_produkt_IsActive> getInListActive_Izpitvan_produkt_IsActive() {
		
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Izpitvan_produkt_IsActive e WHERE e.active = 1");
		
		List<Izpitvan_produkt_IsActive> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}
	
	@GET
	@QueryParam("{id}")
	public static Izpitvan_produkt_IsActive getValueIzpitvan_produkt_IsActiveById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Izpitvan_produkt_IsActive metody = entitymanager.find(Izpitvan_produkt_IsActive.class, id);

		entitymanager.close();
		emfactory.close();

		return metody;
	}
	
	public static void updateIzpitvan_produkt_IsActive(Izpitvan_produkt_IsActive izpitvanProduktActive) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		entitymanager.find(Izpitvan_produkt_IsActive.class, izpitvanProduktActive.getIzpitvan_produkt_IsActive_ID());
		entitymanager.merge(izpitvanProduktActive);
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
}
