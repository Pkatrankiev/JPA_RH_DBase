package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Naredbi;
import DBase_Class.Normi;
import DBase_Class.Nuclide;


public class NormiDAO {
	static String name_DBase = "JPA_RH_DBase";

	public static void setValueNormi(Naredbi naredbi, Nuclide nuclide, Double value_norma) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Normi valueEnt = new Normi();
		valueEnt.setNaredbi(naredbi);
		valueEnt.setNuclide(nuclide);
		valueEnt.setValue_norma(value_norma);
		
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

		@SuppressWarnings("unchecked")
		public static List<Normi> getInListAllValueNormi() {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createNamedQuery("getListAllNormi");
		List<Normi> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

		@GET
		@QueryParam("{id}")
		public static Normi getValueNaredbiById(@QueryParam("id") int id) {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			Normi naredbi = entitymanager.find(Normi.class, id);

			entitymanager.close();
			emfactory.close();

			return naredbi;
		}
		
		@SuppressWarnings("unchecked")
		public static List<Normi> getList_NormiByNaredbi(Naredbi name) {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			Query query = entitymanager.createNamedQuery("findNormiByNaredbi");
			query.setParameter("text", name);
		
			List<Normi> list = query.getResultList();
			
			entitymanager.close();
			emfactory.close();

			return list;
		}
		
		@SuppressWarnings("unchecked")
		public static Normi getValueList_NormiByNuclide(Nuclide name) {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			Query query = entitymanager.createNamedQuery("findNormiByNuclide");
			query.setParameter("text", name);
		
			List<Normi> list = query.getResultList();
			
			entitymanager.close();
			emfactory.close();

			return list.get(0);
		}

		@SuppressWarnings("unchecked")
		public static Normi getValueList_NormiByValue_norna(String name) {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			Query query = entitymanager.createNamedQuery("findNormiByValue_norna");
			query.setParameter("text", name);
		
			List<Normi> list = query.getResultList();
			
			entitymanager.close();
			emfactory.close();

			return list.get(0);
		}
		
		


}
