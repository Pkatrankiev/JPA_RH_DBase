package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Izpitvan_pokazatel;
import DBase_Class.List_Metody;
import DBase_Class.Metody;
import DBase_Class.Results;
import DBase_Class.Sample;

public class MetodyDAO {
	
	private static final String internal_applicant = null;
	static String name_DBase = "JPA_RH_DBase";

	public static void setValueMetody(
			Sample sample,
			List_Metody list_metody,
			Izpitvan_pokazatel pokazatel){
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Metody valueEnt = new Metody();

		valueEnt.setSample(sample);
		valueEnt.setList_metody(list_metody);
		valueEnt.setPokazatel(pokazatel);
		
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		
	}
	
	public static List<Metody> getInListAllValueResults() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Metody e");

		List<Metody> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (Metody e : list) {
			System.out.println("Num:" + ((Metody) e).getId_metody() 
					+ "  Sample :" + ((Metody) e).getSample()
					+ "  Metody :" + ((Metody) e).getList_metody());
					
		}
		return list;

}
	
	public static void setBasicValueMetody() {

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
				
		
		// Get random Metody object
		List<List_Metody> listI = entitymanager.createQuery("SELECT e FROM List_Metody e").getResultList();
		System.out.println("Num List_Metody:" + listI.size());
		max = listI.size();
		ran = min + (int) (Math.random() * ((max - min) + 1));
		List_Metody list_metody = List_MetodyDAO.getValueMetodyById(ran);
		System.out.println("Name Metody:" + list_metody.getName_metody());
		
		
		// Get random Izpitvan_pokazatel object
		List<Izpitvan_pokazatel> listIp = entitymanager.createQuery("SELECT e FROM Izpitvan_pokazatel e")
				.getResultList();
		System.out.println("Num Izpitvan_pokazatel:" + listIp.size());
		max = listIp.size();
		ran = min + (int) (Math.random() * ((max - min) + 1));
		Izpitvan_pokazatel izpitvan_pokazatel = Izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelById(ran);
		System.out.println("Name Izpitvan_pokazatel:" + izpitvan_pokazatel.getName_pokazatel());
		
		setValueMetody(sample,
				list_metody,
				izpitvan_pokazatel
				);
		
		
					}
				}
				
				entitymanager.close();
				emfactory.close();
	}
	
	
	@GET
	@QueryParam("{id}")
	public static Metody getValueMetodyById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Metody request = (Metody) entitymanager.find(Metody.class, id);

		entitymanager.close();
		emfactory.close();

		return request;
	}
}
