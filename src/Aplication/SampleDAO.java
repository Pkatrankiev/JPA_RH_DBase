package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Period;
import DBase_Class.Request;
import DBase_Class.Sample;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class SampleDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	

	public static void setValueSample(String sample_code, String description_sample, String date_time_reference,
			Request request, Obekt_na_izpitvane_sample obekt_na_izpitvane, Period period, int godina_period) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Sample valueEnt = new Sample();
		valueEnt.setSample_code(sample_code);
		valueEnt.setDescription_sample(description_sample);
		valueEnt.setDate_time_reference(date_time_reference);
		valueEnt.setRequest(request);
		valueEnt.setObekt_na_izpitvane(obekt_na_izpitvane);
		valueEnt.setPeriod(period);
		valueEnt.setGodina_period(godina_period);

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static Sample creatSampleFromValue(String sample_code, String description_sample, String date_time_reference,
			Request request, Obekt_na_izpitvane_sample obekt_na_izpitvane, Period period, int godina_period) {

		Sample valueEnt = new Sample();
		valueEnt.setSample_code(sample_code);
		valueEnt.setDescription_sample(description_sample);
		valueEnt.setDate_time_reference(date_time_reference);
		valueEnt.setRequest(request);
		valueEnt.setObekt_na_izpitvane(obekt_na_izpitvane);
		valueEnt.setPeriod(period);
		valueEnt.setGodina_period(godina_period);
		
		return valueEnt;
	}
	
	public static void setValueSample(Sample valueEnt) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	public static List<Sample> getInListAllValueSample() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Sample e");

		List<Sample> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static void setBasicValueSample() {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
//
//		int min = 1;
//		int max = 1;
//		int ran = 1;
//		// Get Request list
//		List<Request> listtrequest = entitymanager.createQuery("SELECT e FROM Request e").getResultList();
//		System.out.println("Num Request:" + listtrequest.size());
//
//		for (int reqNum = 1; reqNum <= listtrequest.size(); reqNum++) {
//
//			Request request = RequestDAO.getValueRequestById(reqNum);
//			Period period = null;
//
//			for (int i = 1; i <= request.getCounts_samples(); i++) {
//
//				// Get random Obekt_na_izpitvane object
//				List<Obekt_na_izpitvane_request> listOi = entitymanager
//						.createQuery("SELECT e FROM Obekt_na_izpitvane_request e").getResultList();
//				System.out.println("Num Obekt_na_izpitvane:" + listOi.size());
//				max = listOi.size();
//				ran = min + (int) (Math.random() * ((max - min) + 1));
//				Obekt_na_izpitvane_sample obekt_na_izpitvane = Obekt_na_izpitvane_sampleDAO
//						.getValueObekt_na_izpitvane_sampleById(ran);
////				System.out.println("Name Obekt_na_izpitvane:" + obekt_na_izpitvane.getName_obekt_na_izpitvane());
//
//				setValueSample(i + "", // sample_code
//						"проби1", // description_sample
//						"22.12.2017 /12:00", // date_time_reference
//						request, // request object
//						obekt_na_izpitvane, period, 2017);
//			}
//		}
		entitymanager.close();
		emfactory.close();

	}

	@SuppressWarnings("unchecked")
	public static List<Sample> getListSampleFromColumnByVolume(String column_name, Object volume_check) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Sample e WHERE e." + column_name + " = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", volume_check);

		List<Sample> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Sample getValueSampleById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Sample sample = (Sample) entitymanager.find(Sample.class, id);

		entitymanager.close();
		emfactory.close();

		return sample;
	}

	public static void updateSample(Sample sample) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		

		entitymanager.find(Sample.class, sample.getId_sample());
		entitymanager.merge(sample);
		
		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null, "Прблем при обновяване на проба: "+sample.getRequest().getRecuest_code()+"-"+sample.getSample_code(), "Проблем с база данни:",
					JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}
	
}
