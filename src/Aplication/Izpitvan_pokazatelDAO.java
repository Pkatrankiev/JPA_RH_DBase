package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Izpitvan_pokazatel;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Results;
import DBase_Class.Sample;

public class Izpitvan_pokazatelDAO {
	
static String name_DBase = "JPA_RH_DBase";
	



//	Pokazatel
	public static void setValueIzpitvan_pokazatel(List_izpitvan_pokazatel pokazatel, Sample sample, Metody metody) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Izpitvan_pokazatel pokazatal = new Izpitvan_pokazatel();
		
		pokazatal.setPokazatel(pokazatel);
		pokazatal.setSample(sample);
		pokazatal.setMetody(metody);
		
		entitymanager.persist(pokazatal);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static void setValueIzpitvan_pokazatel(Izpitvan_pokazatel pokazatal) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
			
		entitymanager.persist(pokazatal);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}


	
	public static List<Izpitvan_pokazatel> getInListAllValueIzpitvan_pokazatel() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Izpitvan_pokazatel e");
		List<Izpitvan_pokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (Izpitvan_pokazatel e : list) {
			System.out.println(
					"Num:" + ((Izpitvan_pokazatel) e).getId_pokazatel() + "  NAME :" + ((Izpitvan_pokazatel) e).getId_pokazatel());
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

	@GET
	public static List <Izpitvan_pokazatel> getValueIzpitvan_pokazatelBySample(Sample sample) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Izpitvan_pokazatel e WHERE e.sample = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", sample);
				List<Izpitvan_pokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}	
	
	@GET
	public static List <Izpitvan_pokazatel> getValueIzpitvan_pokazatelByPokazatel(List_izpitvan_pokazatel pokazatel) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Izpitvan_pokazatel e WHERE e.List_izpitvan_pokazatel = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", pokazatel);
				List<Izpitvan_pokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}	
	
	public static void setBasikValueIzpitvan_pokazatel() {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		int min = 1;
		int max = 1;
		int ran = 1;
		
		// Get Sample list
				List<Sample> listSample = entitymanager.createQuery("SELECT e FROM Sample e").getResultList();
				System.out.println("Num Sample:" + listSample.size());

				for (int samNum = 1; samNum <= listSample.size(); samNum++) {

		Sample sample = SampleDAO.getValueSampleById(samNum);
		int ranval = 1 + (int) (Math.random() * 2);
		System.out.println();
		System.out.println("***********************************************************");
		System.out.println("Num Results:" + ranval);
		for (int i = 1; i <= ranval; i++) {
				
		
	
		
		// Get random List_izpitvan_pokazatel object
		List<List_izpitvan_pokazatel> listIp = entitymanager.createQuery("SELECT e FROM List_izpitvan_pokazatel e")
				.getResultList();
		System.out.println("Num Izpitvan_pokazatel:" + listIp.size());
		max = listIp.size();
		ran = min + (int) (Math.random() * ((max - min) + 1));
		List_izpitvan_pokazatel izpitvan_pokazatel = List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelById(ran);
		System.out.println("Name Izpitvan_pokazatel:" + izpitvan_pokazatel.getId_pokazatel());
		
		// Get random Metody object
				List<Metody> listMetody = entitymanager.createQuery("SELECT e FROM Metody e")
						.getResultList();
				System.out.println("Num Metody:" + listMetody.size());
				max = listMetody.size();
				ran = min + (int) (Math.random() * ((max - min) + 1));
				Metody metody = MetodyDAO.getValueMetodyById(ran);
				System.out.println("Code Metody:" + metody.getId_metody());
		
		setValueIzpitvan_pokazatel(izpitvan_pokazatel, sample, metody);
		
		
					}
				}
				
				entitymanager.close();
				emfactory.close();
	}
	
	public static List<Izpitvan_pokazatel> getListIzpitvan_pokazatelFromColumnByVolume(String column_name, Object volume_check) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Izpitvan_pokazatel e WHERE e."+column_name+" = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", volume_check);
		
		List<Izpitvan_pokazatel>  list =  query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static void setList_Izpitvan_pokazatelInIzpitvan_pokazatelById(int id, Object izpitvan_pokazatel, String colum) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		EntityTransaction updateTranzaction = entitymanager.getTransaction();
		updateTranzaction.begin();
		Query query = entitymanager.createQuery("UPDATE Izpitvan_pokazatel e SET e."+colum+"= :coll WHERE e.Id_pokazatel= :id");
		query.setParameter("coll", izpitvan_pokazatel).setParameter("id", id);
		
        query.executeUpdate();
        updateTranzaction.commit();
				
		entitymanager.close();
		emfactory.close();
		}
	

	
}
