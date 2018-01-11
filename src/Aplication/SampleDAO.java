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
import DBase_Class.Request;
import DBase_Class.Sample;

public class SampleDAO {

	static String name_DBase = "JPA_RH_DBase";

	public static void setValueSample(int sample_code, String description_sample, String date_time_reference,
			Request request, Obekt_na_izpitvane obekt_na_izpitvane, Izpitvan_pokazatel pokazatel) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Sample valueEnt = new Sample();
		valueEnt.setSample_code(sample_code);
		valueEnt.setDescription_sample(description_sample);
		valueEnt.setDate_time_reference(date_time_reference);
		valueEnt.setRequest(request);
		valueEnt.setObekt_na_izpitvane(obekt_na_izpitvane);
		valueEnt.setPokazatel(pokazatel);

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Sample> getInListAllValueSample() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Sample e");

		List<Sample> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		System.out.format("%4s%8s%4s%12s%20s%26s%30s",
			    "Num", "заявка", "код", "описание", "референтна дата", "обект на изпитване", "показател");
		System.out.println();
		for (Sample e : list) {
			System.out.format("%4s%8s%1s%-3s%12s%20s%26s%30s",
					((Sample) e).getId_sample(),
							((Sample) e).getRequest().getRecuest_code(),"-",
							((Sample) e).getSample_code(),
							((Sample) e).getDescription_sample(),
							((Sample) e).getDate_time_reference(),
							((Sample) e).getObekt_na_izpitvane().getName_obekt_na_izpitvane(),
							((Sample) e).getPokazatel().getName_pokazatel() );
			System.out.println();
			
			
//			System.out.println("Num:" + ((Sample) e).getId_sample() + "  request :"
//					+ ((Sample) e).getRequest().getRecuest_code() + "  samle_code :" + ((Sample) e).getSample_code()
//					+ "  description_sample :" + ((Sample) e).getDescription_sample() + "  date_time_reference :"
//					+ ((Sample) e).getDate_time_reference() + "  obekt_na_izpitvane :"
//					+ ((Sample) e).getObekt_na_izpitvane().getName_obekt_na_izpitvane() + "  pokazatel :"
//					+ ((Sample) e).getPokazatel().getName_pokazatel() );
		}
		return list;
	}

	public static void setBasicValueSample() {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		int min = 1;
		int max = 1;
		int ran = 1;
		// Get Request list
					List<Request> listtrequest = entitymanager.createQuery("SELECT e FROM Request e").getResultList();
					System.out.println("Num Request:" + listtrequest.size());
					
					for (int reqNum = 1; reqNum <= listtrequest.size(); reqNum++) {			
										
					Request request = RequestDAO.getValueRequestById(reqNum);
					
		
		for (int i = 1; i <= request.getNumber_samples(); i++) {
			

			
			// Get random Obekt_na_izpitvane object
			List<Obekt_na_izpitvane> listOi = entitymanager.createQuery("SELECT e FROM Obekt_na_izpitvane e")
					.getResultList();
			System.out.println("Num Obekt_na_izpitvane:" + listOi.size());
			max = listOi.size();
			ran = min + (int) (Math.random() * ((max - min) + 1));
			Obekt_na_izpitvane obekt_na_izpitvane = Obekt_na_izpitvaneDAO.getValueObekt_na_izpitvaneById(ran);
			System.out.println("Name Obekt_na_izpitvane:" + obekt_na_izpitvane.getName_obekt_na_izpitvane());

			// Get random Izpitvan_pokazatel object
			List<Izpitvan_pokazatel> listIp = entitymanager.createQuery("SELECT e FROM Izpitvan_pokazatel e")
					.getResultList();
			System.out.println("Num Izpitvan_pokazatel:" + listIp.size());
			max = listIp.size();
			ran = min + (int) (Math.random() * ((max - min) + 1));
			Izpitvan_pokazatel izpitvan_pokazatel = Izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelById(ran);
			System.out.println("Name Izpitvan_pokazatel:" + izpitvan_pokazatel.getName_pokazatel());

			setValueSample(i, // sample_code
					"проби1", // description_sample
					"22.12.2017 /12:00", // date_time_reference
					request, // request object
					obekt_na_izpitvane, // obekt_na_izpitvane
					izpitvan_pokazatel // izpitvan_pokazatel
			);
		}
					}
		entitymanager.close();
		emfactory.close();

	}

	@GET
	@QueryParam("{id}")
public static Sample getValueSampleById(@QueryParam("id") int id) {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
	EntityManager entitymanager = emfactory.createEntityManager();
	entitymanager.getTransaction().begin();
	Sample  sample = (Sample) entitymanager.find(Sample.class, id);
	
	entitymanager.close();
	emfactory.close();

	return sample;
}
	
}
