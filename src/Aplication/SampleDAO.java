package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Izpitvan_pokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Obekt_na_izpitvane_sample;
import DBase_Class.Period;
import DBase_Class.Request;
import DBase_Class.Sample;

public class SampleDAO {

	static String name_DBase = "JPA_RH_DBase";

	public static void setValueSample(String sample_code, String description_sample, String date_time_reference,
			Request request, Obekt_na_izpitvane_sample obekt_na_izpitvane, Period period, int godina_period) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
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

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
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

//		System.out.format("55", "Num", "������", "���", "��������", "���������� ����",
//				"����� �� ���������", "���������");
//		System.out.println();
//		for (Sample e : list) {
//			System.out.format("22", ((Sample) e).getId_sample(),
//					((Sample) e).getRequest().getRecuest_code(), "-", ((Sample) e).getSample_code(),
//					((Sample) e).getDescription_sample(), ((Sample) e).getDate_time_reference(),
//					((Sample) e).getObekt_na_izpitvane().getName_obekt_na_izpitvane());
//			System.out.println();
//		}
		return list;
	}

	public static void setBasicValueSample() {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
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
//						"�����1", // description_sample
//						"22.12.2017 /12:00", // date_time_reference
//						request, // request object
//						obekt_na_izpitvane, period, 2017);
//			}
//		}
		entitymanager.close();
		emfactory.close();

	}

	public static List<Sample> getListSampleFromColumnByVolume(String column_name, Object volume_check) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
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
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Sample sample = (Sample) entitymanager.find(Sample.class, id);

		entitymanager.close();
		emfactory.close();

		return sample;
	}

	public static void updateSample(Sample sample, int id) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		Query query = entitymanager.createQuery("UPDATE Sample e SET" 
				+ " e.sample_code  = :sample_code,"
				+ " e.description_sample = :description_sample," 
				+ " e.date_time_reference = :date_time_reference,"
				+ " e.request = :request," 
				+ " e.obekt_na_izpitvane = :obekt_na_izpitvane,"
				+ " e.period = :period,"
				+ " e.godina_period = :godina_period," 
				
				+ " WHERE e.Id_sample = :id");

		query.setParameter("sample_code", sample.getSample_code())
				.setParameter("description_sample", sample.getDescription_sample())
				.setParameter("date_time_reference", sample.getDate_time_reference())
				.setParameter("request", sample.getRequest())
				.setParameter("obekt_na_izpitvane", sample.getObekt_na_izpitvane())
				.setParameter("period", sample.getPeriod())
				.setParameter("godina_period", sample.getGodina_period())
				.setParameter("id", id).executeUpdate();

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null, "������ ��� ���������� �� �����: "+sample.getRequest().getRecuest_code()+"-"+sample.getSample_code(), "������� � ���� �����:",
					JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}
	
}
