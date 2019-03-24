package Aplication;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.*;
import javax.ws.rs.QueryParam;

import DBase_Class.IzpitvanPokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Nuclide;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import DBase_Class.Nuclide;
import DBase_Class.Zabelejki;
import DBase_Class.Obekt_na_izpitvane_request;

public class ResultsDAO {

	private static final String internal_applicant = null;
	static String name_DBase = "JPA_RH_DBase";

	public static void setValueResults(
			Nuclide nuclide, 
			List_izpitvan_pokazatel pokazatel,
			Metody metody,
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
			Boolean inProtokol) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Results valueEnt = new Results();

		valueEnt.setNuclide(nuclide);
		valueEnt.setPokazatel(pokazatel); 
		valueEnt.setMetody(metody);
		valueEnt.setSample(sample);
		valueEnt.setRtazmernosti(rtazmernosti);

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

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static void setValueResults(Results valueEnt) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	public static void setVolumeInColumInResultsById(int id, Object ob_volume,String colum_name) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
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
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		EntityTransaction updateTranzaction = entitymanager.getTransaction();
		updateTranzaction.begin();
		Query query = entitymanager.createQuery(" delete from Results where id =:id");
		query.setParameter("id", id);
		
        query.executeUpdate();
        updateTranzaction.commit();
				
		entitymanager.close();
		emfactory.close();
		}
	
	public static List<Results> getInListAllValueResults() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Results e ORDER BY e.nuclide ASC");

		List<Results> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		
		return list;
	}
	
	public static void setBasicValueResults() {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		int min = 1;
		int max = 1;
		int ran = 1;

		// Get pokazatel list
		List<IzpitvanPokazatel> list_pokazatel = entitymanager.createQuery("SELECT e FROM Izpitvan_pokazatel e").getResultList();
		System.out.println("Num pokazatel:" + list_pokazatel.size());
		
		Metody metody = null;
		for (int samNum = 1; samNum <= list_pokazatel.size(); samNum++) {
			
			int ranval = 1 + (int) (Math.random() * ((5 - 1) + 1));
			List_izpitvan_pokazatel pokazatel = null;
			System.out.println();
			System.out.println("***********************************************************");
			System.out.println("Num pokazatel:" + ranval);
			for (int i = 1; i <= ranval; i++) {

				
				// Get random Nuclide object
				Query query = entitymanager.createQuery("SELECT e FROM Nuclide e");
				List<Nuclide> listNuc = query.getResultList();
				System.out.println("Num Nuclide:" + listNuc.size());
				min = 1;
				max = listNuc.size();
				ran = min + (int) (Math.random() * ((max - min) + 1));
				Nuclide nuclide = NuclideDAO.getValueSNuclideById(ran);
				System.out.println("Name nuclide:" + nuclide.getSymbol_nuclide());

				// Get random Razmernosti object
				List<Razmernosti> listR = entitymanager.createQuery("SELECT e FROM Razmernosti e").getResultList();
				System.out.println("Num Razmernosti:" + listR.size());
				max = listR.size();
				ran = min + (int) (Math.random() * ((max - min) + 1));
				Razmernosti razmernosti = RazmernostiDAO.getValueRazmernostiById(ran);
				System.out.println("Name Razmernosti:" + razmernosti.getName_razmernosti());

				// Get random Zabelejki object
				List<Zabelejki> listZ = entitymanager.createQuery("SELECT e FROM Zabelejki e").getResultList();
				System.out.println("Num Zabelejki:" + listZ.size());
				max = listZ.size();
				ran = min + (int) (Math.random() * ((max - min) + 1));
				Zabelejki zabelejki = ZabelejkiDAO.getValueZabelejkiById(ran);
				System.out.println("Name Zabelejki:" + zabelejki.getName_zabelejki());

				// Get random User_chim_oper object
				List<Users> listU_C_O = entitymanager.createQuery("SELECT e FROM Users e").getResultList();
				System.out.println("Num Users:" + listU_C_O.size());
				max = listU_C_O.size();
				ran = min + (int) (Math.random() * ((max - min) + 1));
				Users users_chim_oper = UsersDAO.getValueUsersById(ran);
				System.out.println("Name Users:" + users_chim_oper.getName_users());

				// Get random User_measur object
				List<Users> listU_M = entitymanager.createQuery("SELECT e FROM Users e").getResultList();
				System.out.println("Num Users:" + listU_M.size());
				max = listU_M.size();
				ran = min + (int) (Math.random() * ((max - min) + 1));
				Users users_measur = UsersDAO.getValueUsersById(ran);
				System.out.println("Name Users:" + users_measur.getName_users());

				// Get random User_redac object
				List<Users> listU_R = entitymanager.createQuery("SELECT e FROM Users e").getResultList();
				System.out.println("Num Users:" + listU_R.size());
				max = listU_R.size();
				ran = min + (int) (Math.random() * ((max - min) + 1));
				Users users_redac = UsersDAO.getValueUsersById(ran);
				System.out.println("Name Users:" + users_redac.getName_users());

				// Get random value_result
				max = 99999;
				Double value_result = 10 + (Math.random() * ((max - min) + 1));
				System.out.println("value_result:" + value_result);

				// Get random uncertainty
				max = 9999;
				Double uncertainty = 10 + (Math.random() * ((max - min) + 1));
				System.out.println("uncertainty :" + uncertainty);

				// Get random mda
				max = 59999;
				Double mda = 10 + (Math.random() * ((max - min) + 1));
				System.out.println("mda :" + mda);

				// Get random sigma
				max = 2;
				int sigma = 1 + (int) (Math.random() * ((max - 1) + 1));
				System.out.println("sigma :" + sigma);

				// Get random date
				int dat[][] = new int [3][2];
				for (int n = 0; n < 3; n++) {
				int m = 1 + (int) (Math.random() * ((12 - 1) + 1));	
				int d = 1 + (int) (Math.random() * ((30 - 1) + 1));
				dat[n][0]=d;
				dat[n][1]=m;
				
				}
				setValueResults(
						nuclide, 
						pokazatel, 
						metody,
						null,
						razmernosti, 
						"undefinition", 
						value_result, 
						sigma, 
						uncertainty,
						mda, 
						zabelejki, 
						users_chim_oper, 
						dat[0][0]+"."+dat[0][1]+".2017", 
						users_measur, 
						dat[1][0]+"."+dat[1][1]+".2017", 
						users_redac,
						dat[2][0]+"."+dat[2][1]+".2017",
						true); 
			}
		}
		entitymanager.close();
		emfactory.close();

	}

	@GET
	@QueryParam("{id}")
	public static Results getValueResultsById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Results request = (Results) entitymanager.find(Results.class, id);

		entitymanager.close();
		emfactory.close();

		return request;
	}

	public static List<Results> getListResultsFromColumnByVolume(String column_name, Object volume_check) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Results e WHERE e."+column_name+" = :text ORDER BY e.nuclide ASC";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", volume_check);
		
		List<Results>  list =  query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static void updateResults(Results results) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
	
		entitymanager.find(Results.class, results.getId_results());
		entitymanager.merge(results);


		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null, "Прблем при обновяване на резултат: "+results.getSample().getRequest().getRecuest_code()+"-"+results.getSample().getSample_code()+" "+results.getNuclide().getSymbol_nuclide(), "Проблем с база данни:",
					JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}
	
	
}
