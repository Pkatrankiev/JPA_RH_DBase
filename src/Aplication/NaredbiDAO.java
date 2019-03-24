package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Metody;
import DBase_Class.Naredbi;
import DBase_Class.Razmernosti;

public class NaredbiDAO {
	static String name_DBase = "JPA_RH_DBase";

	public static void setValueNaredbi(Razmernosti razmernost, String name_request, String name_protokol) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Naredbi valueEnt = new Naredbi();
		valueEnt.setRazmernost(razmernost);
		valueEnt.setName_request(name_request);
		valueEnt.setName_protokol(name_protokol);
		
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

		@SuppressWarnings("unchecked")
		public static List<Naredbi> getInListAllValueNaredbi() {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createNamedQuery("getListAllNaredbi");
		List<Naredbi> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

		@GET
		@QueryParam("{id}")
		public static Naredbi getValueNaredbiById(@QueryParam("id") int id) {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			Naredbi naredbi = entitymanager.find(Naredbi.class, id);

			entitymanager.close();
			emfactory.close();

			return naredbi;
		}
		
		@SuppressWarnings("unchecked")
		public static Naredbi getValueList_NaredbiByName_request(String name) {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			Query query = entitymanager.createNamedQuery("findNaredbiByName_request");
			query.setParameter("text", name);
		
			List<Naredbi> list = query.getResultList();
			
			entitymanager.close();
			emfactory.close();

			return list.get(0);
		}
		
		@SuppressWarnings("unchecked")
		public static Naredbi getValueList_NaredbiByName_protokol(String name) {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			Query query = entitymanager.createNamedQuery("findNaredbiByName_protokol");
			query.setParameter("text", name);
		
			List<Naredbi> list = query.getResultList();
			
			entitymanager.close();
			emfactory.close();

			return list.get(0);
		}

		@SuppressWarnings("unchecked")
		public static Naredbi getValueList_NaredbiByRazmernost(Razmernosti name) {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			Query query = entitymanager.createNamedQuery("findNaredbiByRazmernost");
			query.setParameter("text", name);
		
			List<Naredbi> list = query.getResultList();
			
			entitymanager.close();
			emfactory.close();

			return list.get(0);
		}
		
		public static String[] getMasiveStringAllValueNaredbiName_request(){
			 List<Naredbi> list = getInListAllValueNaredbi();
			String[] values = new String[list.size()];
			int i = 0;
			for (Naredbi naredbi : list) {
				values[i] = naredbi.getName_request();
				i++;
			}
			return values;
		}



}
