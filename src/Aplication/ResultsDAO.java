package Aplication;


import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.*;
import javax.ws.rs.QueryParam;

import AddResultViewFunction.SortListObjectByField;
import DBase_Class.Dobiv;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Nuclide;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import DBase_Class.Zabelejki;
import GlobalVariable.GlobalVariableForSQL_DBase;
import GlobalVariable.ResourceLoader;

public class ResultsDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	

	public static void setValueResults(
			Nuclide nuclide, 
			List_izpitvan_pokazatel pokazatel,
			Metody metody,
			Request request,
			Sample sample,
			Razmernosti rtazmernosti,
			String basic_value, 
			Double value_result, 
			int sigma, 
			Double uncertainty, 
			Double mda, 
			Zabelejki zabelejki,
			Users user_chim_oper, 
			String date_chim_oper, 
			Users user_measur, 
			String date_measur, 
			Users user_redac,
			String date_redac,
			Boolean inProtokol,
			Dobiv dobiv) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Results valueEnt = new Results();

		valueEnt.setNuclide(nuclide);
		valueEnt.setPokazatel(pokazatel); 
		valueEnt.setMetody(metody);
		valueEnt.setSample(sample);
		valueEnt.setRequest(request);
		valueEnt.setRazmernosti(rtazmernosti);

		valueEnt.setBasic_value(basic_value);
		valueEnt.setValue_result(value_result);
		valueEnt.setSigma(sigma);

		valueEnt.setUncertainty(uncertainty);
		valueEnt.setMda(mda);
		valueEnt.setZabelejki(zabelejki);
		valueEnt.setUser_chim_oper(user_chim_oper);

		valueEnt.setDate_chim_oper(date_chim_oper);
		valueEnt.setUser_measur(user_measur);
		valueEnt.setDate_measur(date_measur);

		valueEnt.setUser_redac(user_redac);
		valueEnt.setDate_redac(date_redac);
		valueEnt.setInProtokol(inProtokol);
		valueEnt.setDobiv(dobiv);

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static void setValueResults(Results valueEnt) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	public static void setVolumeInColumInResultsById(int id, Object ob_volume,String colum_name) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		EntityTransaction updateTranzaction = entitymanager.getTransaction();
		updateTranzaction.begin();
		Query query = entitymanager.createQuery("UPDATE Results e SET e."+colum_name+"= :coll WHERE e.Id_results= :id");
		query.setParameter("coll", ob_volume).setParameter("id", id);
		
        query.executeUpdate();
        updateTranzaction.commit();
				
		entitymanager.close();
		emfactory.close();
		}
	
	public static void deleteResultsById(int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		EntityTransaction updateTranzaction = entitymanager.getTransaction();
		updateTranzaction.begin();
		Query query = entitymanager.createQuery(" delete from Results where id =:id");
		query.setParameter("id", id);
		
        query.executeUpdate();
        updateTranzaction.commit();
				
		entitymanager.close();
		emfactory.close();
		}
	
	@SuppressWarnings("unchecked")
	public static List<Results> getInListAllValueResults() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Results e ORDER BY e.nuclide ASC");

		List<Results> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		
		return SortListObjectByField.sortListResultsByCodedNuclide(list);
	}
	
	@GET
	@QueryParam("{id}")
	public static Results getValueResultsById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Results request = (Results) entitymanager.find(Results.class, id);

		entitymanager.close();
		emfactory.close();

		return request;
	}

	@SuppressWarnings("unchecked")
	public static List<Results> getListResultsFromColumnByVolume(String column_name, Object volume_check) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Results e WHERE e."+column_name+" = :text ORDER BY e.nuclide ASC";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", volume_check);
		
		List<Results>  list =  query.getResultList();
		entitymanager.close();
		emfactory.close();
		
		

		return SortListObjectByField.sortListResultsByCodedNuclide(list);
	}
	

	
	
	
	@SuppressWarnings("unchecked")
	public static List<Results> getListResultsFromCurentSampleInProtokol(Sample samp) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Results e WHERE e.inProtokol = 1 AND e.sample = :text ORDER BY e.nuclide ASC";
		
		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", samp);
		
		List<Results>  list =  query.getResultList();
		entitymanager.close();
		emfactory.close();

		return SortListObjectByField.sortListResultsByCodedNuclide(list);
	}
	
	

	@SuppressWarnings("unchecked")
	public static List<Results> getListResultsByDataMeasurANDInProtokol(String dataMeasur) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		String hql = "SELECT e FROM Results e WHERE e.inProtokol = 1 AND e.date_measur REGEXP :text ORDER  BY e.date_measur ASC";
		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", dataMeasur);
		
		List<Results>  list =  query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Results> getListResults_LargerByIDANDInProtokol(int id) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		String hql = "SELECT e FROM Results e WHERE e.inProtokol = 1 AND e.Id_results >= :id ORDER BY e.Id_results ASC";
		Query query = entitymanager.createQuery(hql);
		query.setParameter("id", id);
		
		List<Results>  list =  query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}
	
	public static List<Results> getListResults_InProtokol() {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		
		Query query = entitymanager.createQuery("SELECT e FROM Results e WHERE e.inProtokol = 1 ORDER BY e.request ASC");
		
		
		@SuppressWarnings("unchecked")
		List<Results>  list =  query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}
	
	public static void updateResults(Results results) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
	
		entitymanager.find(Results.class, results.getId_results());
		entitymanager.merge(results);


		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			ResourceLoader.appendToFile(e);
			JOptionPane.showMessageDialog(null, "Прблем при обновяване на резултат: "+results.getSample().getRequest().getRecuest_code()+"-"+results.getSample().getSample_code()+" "+results.getNuclide().getSymbol_nuclide(), "Проблем с база данни:",
					JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}
	
	
}


