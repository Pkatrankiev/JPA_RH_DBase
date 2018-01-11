package Aplication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import com.mysql.fabric.xmlrpc.base.Data;

import DBase_Class.External_applicant;
import DBase_Class.Ind_num_doc;
import DBase_Class.Internal_applicant;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.Metody;
import DBase_Class.Nuclide;
import DBase_Class.Izpitvan_pokazatel;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Results;
import DBase_Class.Sample;
import DBase_Class.Users;
import DBase_Class.Nuclide;
import DBase_Class.Zabelejki;
import DBase_Class.Obekt_na_izpitvane;

public class ResultsDAO {

	static String name_DBase = "JPA_RH_DBase";


	public static void setValueResults( 
			Nuclide nuclide, 
			Sample sample, 
			Metody metody,
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
			String date_redac
			) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Results valueEnt = new Results();
		
		valueEnt.setNuclide(nuclide);
		valueEnt.setSample(sample);
		valueEnt.setMetody(metody);
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
		
	
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Results> getInListAllValueResults() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Results e");

		List<Results> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (Results e : list) {
			System.out.println("Num:" + ((Results) e).getId_results() 
					+ "  Nuclide :"+ ((Results) e).getNuclide() 
					+ "  Sample :" + ((Results) e).getSample()
					+ "  Metody :" + ((Results) e).getMetody()
					+"  Rtazmernosti :" + ((Results) e).getRtazmernosti()
					+ "  Basic_value :" + ((Results) e).getBasic_value()
					+ "  Value_result :" + ((Results) e).getValue_result()
					+ "  Sigma :" 	+ ((Results) e).getSigma() 
					+"  Uncertainty :" + ((Results) e).getUncertainty()
					+ "  Mda :" + ((Results) e).getMda()
					+ "  Zabelejki :" + ((Results) e).getZabelejki()
					+ "  chim_oper_family :" + ((Results) e).getUser_chim_oper().getFamily_users() 
					+ "  Date_chim_oper :" + ((Results) e).getDate_chim_oper() 
					+ "  User_measur_family :"+ ((Results) e).getUser_measur().getFamily_users() 
					+ "  Date_measur :" + ((Results) e).getDate_measur()
					+ "  User_redac_family :" + ((Results) e).getUser_redac().getFamily_users()
					+ "  Date_redac :" + ((Results) e).getDate_redac());
		}
		return list;
	}

	public static void setBasicValueRequest() {

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
		int ranval = 1 + (int) (Math.random() * 3);
for (int i = 1; i < ranval; i++) {
	

		// Get random Nuclide object
		Query query = entitymanager.createQuery("SELECT e FROM Nuclide e");
		List<Nuclide> listNuc = query.getResultList();
		System.out.println("Num Nuclide:" + listNuc.size());
		min = 1;
		max = listNuc.size();
		ran = min + (int) (Math.random() * ((max - min) + 1));
		Nuclide nuclide = NuclideDAO.getValueSNuclideById(ran);
		System.out.println("Name nuclide:" + nuclide.getSymbol_nuclide());

		// Get random Metody object
		List<Metody> listI = entitymanager.createQuery("SELECT e FROM Metody e")
				.getResultList();
		System.out.println("Num Metody:" + listI.size());
		max = listI.size();
		ran = min + (int) (Math.random() * ((max - min) + 1));
		Metody metody = MetodyDAO.getValueMetodyById(ran);
		System.out
				.println("Name Metody:" + metody.getName_metody());
		
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
		
		// Get random value_result
		max = 99999;
		Double value_result = 10 + (Math.random() * ((max - min) + 1));
		System.out.println("value_result:" + value_result);
		
		
		// Get random value_result
		max = 99999;
		Double value_result = 10 + (Math.random() * ((max - min) + 1));
		System.out.println("value_result:" + value_result);
		
		 setValueRequest(
		 nuclide,
		 sample,
		 metody,
		 razmernosti,
		 "undefinition",
		 
		 internal_applicant, //intrnal_applicant
		 "Иван", //applicant_name
		 "Иванов", //applicant_family
		 1+i, //number_samples
		 "проби1", //description_sample_group
		 "22.12.2017 /12:00", //date_time_reception
		 "25.12.2017", //date_execution
		 ind_num_doc, //ind_num_doc
		 izpitvan_produkt, //izpitvan_produkt
		 obekt_na_izpitvane, //obekt_na_izpitvane
		 izpitvan_pokazatel, //izpitvan_pokazatel
		 razmernosti, //razmernosti
		 zabelejki, //zabelejki
		 users); //users
}
		}
		 entitymanager.close();
			emfactory.close();

		// setValueRequest(
		// 26855,
		// "55.12.2007",
		// true,
		// true,
		// null,
		// null,
		// "Petar",
		// "Иванов",
		// 3,
		// "про221",
		// "26.12.2017 /18:00",
		// " 25.12.2007g",
		// null,
		// null,
		// null,
		// null,
		// null,
		// null,
		// null);

	}

	@GET
	@QueryParam("{id}")
public static Results getValueRequestById(@QueryParam("id") int id) {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
	EntityManager entitymanager = emfactory.createEntityManager();
	entitymanager.getTransaction().begin();
	Results  request = (Results) entitymanager.find(Results.class, id);
	
	entitymanager.close();
	emfactory.close();

	return request;
}

	
}

