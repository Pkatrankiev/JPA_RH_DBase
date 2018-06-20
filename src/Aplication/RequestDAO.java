package Aplication;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Dimension;
import DBase_Class.External_applicant;
import DBase_Class.Extra_module;
import DBase_Class.Ind_num_doc;
import DBase_Class.Internal_applicant;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.Izpitvan_pokazatel;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.Users;
import DBase_Class.Zabelejki;
import DBase_Class.Obekt_na_izpitvane_request;

public class RequestDAO {

	static String name_DBase = "JPA_RH_DBase";
	
	public static Request setValueRequest(String recuest_code, String date_request, Boolean accreditation, Boolean section,
			Extra_module xtra_module,  int counts_samples, String description_sample_group, String date_time_reception,
			String date_execution, Ind_num_doc ind_num_doc, Izpitvan_produkt izpitvan_produkt, Razmernosti razmernosti,
			Zabelejki zabelejki, Users users, Obekt_na_izpitvane_request obekt_na_izpitvane_request) {

		
		Request valueEnt = new Request();
		valueEnt.setRecuest_code(recuest_code);
		valueEnt.setDate_request(date_request);
		valueEnt.setAccreditation(accreditation);
		valueEnt.setSection(section);

		valueEnt.setExtra_module(xtra_module);
		
		valueEnt.setCounts_samples(counts_samples);
		valueEnt.setDescription_sample_group(description_sample_group);
		valueEnt.setDate_time_reception(date_time_reception);

		valueEnt.setDate_execution(date_execution);
		valueEnt.setInd_num_doc(ind_num_doc);
		valueEnt.setIzpitvan_produkt(izpitvan_produkt);

		valueEnt.setRazmernosti(razmernosti);

		valueEnt.setZabelejki(zabelejki);
		valueEnt.setUsers(users);
		valueEnt.setObekt_na_izpitvane_request(obekt_na_izpitvane_request);

		return valueEnt;
	}


	public static void saveRequestFromValue(String recuest_code, String date_request, Boolean accreditation, Boolean section,
			Extra_module xtra_module,  int counts_samples, String description_sample_group, String date_time_reception,
			String date_execution, Ind_num_doc ind_num_doc, Izpitvan_produkt izpitvan_produkt, Razmernosti razmernosti,
			Zabelejki zabelejki, Users users, Obekt_na_izpitvane_request obekt_na_izpitvane_request) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Request valueEnt = new Request();
		valueEnt.setRecuest_code(recuest_code);
		valueEnt.setDate_request(date_request);
		valueEnt.setAccreditation(accreditation);
		valueEnt.setSection(section);

		valueEnt.setExtra_module(xtra_module);
		
		valueEnt.setCounts_samples(counts_samples);
		valueEnt.setDescription_sample_group(description_sample_group);
		valueEnt.setDate_time_reception(date_time_reception);

		valueEnt.setDate_execution(date_execution);
		valueEnt.setInd_num_doc(ind_num_doc);
		valueEnt.setIzpitvan_produkt(izpitvan_produkt);

		valueEnt.setRazmernosti(razmernosti);

		valueEnt.setZabelejki(zabelejki);
		valueEnt.setUsers(users);
		valueEnt.setObekt_na_izpitvane_request(obekt_na_izpitvane_request);

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static void saveRequestFromRequest(Request valueEnt) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static Boolean checkRequestCode(String check_code) {
		Boolean available = false;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Request e WHERE e.recuest_code= :code");
		query.setParameter("code", check_code);
		List<Request> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		if (list.size() != 0)
			available = true;
		return available;
	}

	public static List<Request> getInListAllValueRequest() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Request e");

		List<Request> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		// for (Request e : list) {
		// System.out.println("Num:" + ((Request) e).getId_recuest()
		// + " recuest_code :"+ ((Request) e).getRecuest_code()
		// + " date_request :" + ((Request) e).getDate_request()
		// + " accreditation :" + ((Request) e).getAccreditation()
		// +" external_applicant_name :" + ((Request)
		// e).getExternal_applicant().getExternal_applicant_name()
		// + " external_applicant_address :"+ ((Request)
		// e).getExternal_applicant().getExternal_applicant_address()
		// + " external_applicant_telephone :"+ ((Request)
		// e).getExternal_applicant().getExternal_applicant_telephone()
		// + " external_applicant_contract_number :"+ ((Request)
		// e).getExternal_applicant().getExternal_applicant_contract_number()
		// +" internal_applicant_organization :"+ ((Request)
		// e).getInternal_applicant().getInternal_applicant_organization()
		// + " internal_applicant_address :"+ ((Request)
		// e).getInternal_applicant().getInternal_applicant_address()
		// + " internal_applicant_telephone :"+ ((Request)
		// e).getInternal_applicant().getInternal_applicant_telephone()
		// +" applicant_name :" + ((Request) e).getApplicant_name()
		// + " number_samples :"+ ((Request) e).getCounts_samples()
		// + " description_sample_group :"+ ((Request)
		// e).getDescription_sample_group()
		// + " date_time_reception :"+ ((Request) e).getDate_time_reception()
		// + " date_execution :" + ((Request) e).getDate_execution()
		// + " ind_num_doc :" + ((Request) e).getInd_num_doc().getName()
		// + " izpitvan_produkt :"+ ((Request)
		// e).getIzpitvan_produkt().getName_zpitvan_produkt()
		// + " razmernosti :"+ ((Request)
		// e).getRazmernosti().getName_razmernosti()
		// + " zabelejki :"+ ((Request) e).getZabelejki().getName_zabelejki()
		// + " users :"+ ((Request) e).getUsers().getName_users() + " " +
		// ((Request) e).getUsers().getFamily_users());
		// }
		return list;
	}

	public static void saveBasicValueRequest() {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		for (int i = 1; i < 9; i++) {

			// Get random External_applicant object
			Query query = entitymanager.createQuery("SELECT e FROM External_applicant e");
			List<External_applicant> listE = query.getResultList();
			System.out.println("Num External_applicant:" + listE.size());
			int min = 1;
			int max = listE.size();
			int ran = min + (int) (Math.random() * ((max - min) + 1));
			External_applicant external_applicant = External_applicantDAO.getValueExternal_applicantById(ran);
			System.out.println("Name External_applicant:" + external_applicant.getExternal_applicant_name());

			// Get random Internal_applicant object
			List<Internal_applicant> listI = entitymanager.createQuery("SELECT e FROM Internal_applicant e")
					.getResultList();
			System.out.println("Num Internal_applicant:" + listI.size());
			max = listI.size();
			ran = min + (int) (Math.random() * ((max - min) + 1));
			Internal_applicant internal_applicant = Internal_applicantDAO.getValueInternal_applicantById(ran);
			System.out.println(
					"Organization Internal_applicant:" + internal_applicant.getInternal_applicant_organization());

			// Get random Ind_num_doc object
			List<Ind_num_doc> listId = entitymanager.createQuery("SELECT e FROM Ind_num_doc e").getResultList();
			System.out.println("Num Ind_num_doc:" + listId.size());
			max = listId.size();
			ran = min + (int) (Math.random() * ((max - min) + 1));
			Ind_num_doc ind_num_doc = Ind_num_docDAO.getValueInternal_applicantById(ran);
			System.out.println("Name Ind_num_doc:" + ind_num_doc.getName());

			// Get random Izpitvan_produkt object
			List<Izpitvan_produkt> listIprod = entitymanager.createQuery("SELECT e FROM Izpitvan_produkt e")
					.getResultList();
			System.out.println("Num Ind_num_doc:" + listIprod.size());
			max = listIprod.size();
			ran = min + (int) (Math.random() * ((max - min) + 1));
			Izpitvan_produkt izpitvan_produkt = Izpitvan_produktDAO.getValueIzpitvan_produktById(ran);
			System.out.println("Name Izpitvan_produkt:" + izpitvan_produkt.getName_zpitvan_produkt());

			// Get random Obekt_na_izpitvane object
			List<Obekt_na_izpitvane_request> listOi = entitymanager.createQuery("SELECT e FROM Obekt_na_izpitvane e")
					.getResultList();
			System.out.println("Num Obekt_na_izpitvane:" + listOi.size());
			max = listOi.size();
			ran = min + (int) (Math.random() * ((max - min) + 1));
			Obekt_na_izpitvane_request obekt_na_izpitvane = Obekt_na_izpitvane_requestDAO
					.getValueObekt_na_izpitvaneById(ran);
			System.out.println("Name Obekt_na_izpitvane:" + obekt_na_izpitvane.getName_obekt_na_izpitvane());

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

			// Get random Users object
			List<Users> listU = entitymanager.createQuery("SELECT e FROM Users e").getResultList();
			System.out.println("Num Users:" + listU.size());
			max = listU.size();
			ran = min + (int) (Math.random() * ((max - min) + 1));
			Users users = UsersDAO.getValueUsersById(ran);
			System.out.println("Name Users:" + users.getName_users());

			saveRequestFromValue((2255 + i) + "", "12.12.2017", true, // accreditation
					true, // section
					null, // xtra_module
					1 + i, // number_samples
					"ïðîáè1", // description_sample_group
					"22.12.2017 /12:00", // date_time_reception
					"25.12.2017", // date_execution
					ind_num_doc, // ind_num_doc
					izpitvan_produkt, // izpitvan_produkt
					razmernosti, // razmernosti
					zabelejki, // zabelejki
					users, Obekt_na_izpitvane_requestDAO.getValueObekt_na_izpitvaneById(1)); // users
		}
		entitymanager.close();
		emfactory.close();

	}

	
	
	public static Request settBasicValueRequest() {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

	int i=1;

			// Get random External_applicant object
			Query query = entitymanager.createQuery("SELECT e FROM External_applicant e");
			List<External_applicant> listE = query.getResultList();
			System.out.println("Num External_applicant:" + listE.size());
			int min = 1;
			int max = listE.size();
			int ran = min + (int) (Math.random() * ((max - min) + 1));
			External_applicant external_applicant = External_applicantDAO.getValueExternal_applicantById(ran);
			System.out.println("Name External_applicant:" + external_applicant.getExternal_applicant_name());

			// Get random Internal_applicant object
			List<Internal_applicant> listI = entitymanager.createQuery("SELECT e FROM Internal_applicant e")
					.getResultList();
			System.out.println("Num Internal_applicant:" + listI.size());
			max = listI.size();
			ran = min + (int) (Math.random() * ((max - min) + 1));
			Internal_applicant internal_applicant = Internal_applicantDAO.getValueInternal_applicantById(ran);
			System.out.println(
					"Organization Internal_applicant:" + internal_applicant.getInternal_applicant_organization());

			// Get random Ind_num_doc object
			List<Ind_num_doc> listId = entitymanager.createQuery("SELECT e FROM Ind_num_doc e").getResultList();
			System.out.println("Num Ind_num_doc:" + listId.size());
			max = listId.size();
			ran = min + (int) (Math.random() * ((max - min) + 1));
			Ind_num_doc ind_num_doc = Ind_num_docDAO.getValueInternal_applicantById(ran);
			System.out.println("Name Ind_num_doc:" + ind_num_doc.getName());

			// Get random Izpitvan_produkt object
			List<Izpitvan_produkt> listIprod = entitymanager.createQuery("SELECT e FROM Izpitvan_produkt e")
					.getResultList();
			System.out.println("Num Ind_num_doc:" + listIprod.size());
			max = listIprod.size();
			ran = min + (int) (Math.random() * ((max - min) + 1));
			Izpitvan_produkt izpitvan_produkt = Izpitvan_produktDAO.getValueIzpitvan_produktById(ran);
			System.out.println("Name Izpitvan_produkt:" + izpitvan_produkt.getName_zpitvan_produkt());

			// Get random Obekt_na_izpitvane object
			List<Obekt_na_izpitvane_request> listOi = entitymanager.createQuery("SELECT e FROM Obekt_na_izpitvane_request e")
					.getResultList();
			System.out.println("Num Obekt_na_izpitvane:" + listOi.size());
			max = listOi.size();
			ran = min + (int) (Math.random() * ((max - min) + 1));
			Obekt_na_izpitvane_request obekt_na_izpitvane = Obekt_na_izpitvane_requestDAO
					.getValueObekt_na_izpitvaneById(ran);
			System.out.println("Name Obekt_na_izpitvane:" + obekt_na_izpitvane.getName_obekt_na_izpitvane());

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

			// Get random Users object
			List<Users> listU = entitymanager.createQuery("SELECT e FROM Users e").getResultList();
			System.out.println("Num Users:" + listU.size());
			max = listU.size();
			ran = min + (int) (Math.random() * ((max - min) + 1));
			Users users = UsersDAO.getValueUsersById(ran);
			System.out.println("Name Users:" + users.getName_users());
			
			Request req = setValueRequest((2255 + i) + "", "12.12.2017", true, // accreditation
					true, // section
					null, // xtra_module
					1 + i, // number_samples
					"ïðîáè1", // description_sample_group
					"22.12.2017 /12:00", // date_time_reception
					"25.12.2017", // date_execution
					ind_num_doc, // ind_num_doc
					izpitvan_produkt, // izpitvan_produkt
					razmernosti, // razmernosti
					zabelejki, // zabelejki
					users, Obekt_na_izpitvane_requestDAO.getValueObekt_na_izpitvaneById(1)); // users 

			
		entitymanager.close();
		emfactory.close();
		return req;

	}

	@GET
	@QueryParam("{id}")
	public static Request getValueRequestById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Request request = (Request) entitymanager.find(Request.class, id);

		entitymanager.close();
		emfactory.close();

		return request;
	}

	public static int getMaxRequestCode() {
		int[] request_code = new int[getInListAllValueRequest().size()];
		int i = 0;
		int Max_request_code;
		for (Request request : getInListAllValueRequest()) {
			try {
				request_code[i] = Integer.parseInt(request.getRecuest_code());
				i++;
			} catch (StringIndexOutOfBoundsException e) {

			}
		}
		Max_request_code = Arrays.stream(request_code).max().getAsInt();

		return Max_request_code;
	}

	public static void setIzpitvan_produktInRequestById(int id, Izpitvan_produkt izpitvan_produkt) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		EntityTransaction updateTranzaction = entitymanager.getTransaction();
		updateTranzaction.begin();
		Query query = entitymanager
				.createQuery("UPDATE Request e SET e.izpitvan_produkt= :izpProd WHERE e.Id_recuest= :id");
		query.setParameter("izpProd", izpitvan_produkt).setParameter("id", id);

		query.executeUpdate();
		updateTranzaction.commit();

		entitymanager.close();
		emfactory.close();
	}

	public static List<Request> getListRequestFromColumnByVolume(String column_name, Object volume_check) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Request e WHERE e." + column_name + " = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", volume_check);

		List<Request> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static Request getRequestFromColumnByVolume(String column_name, Object volume_check) {
		Request list = new Request();
		for (Request element : getListRequestFromColumnByVolume( column_name, volume_check)) {
			list = element;
			System.out.println("5555555555555");
		}
		
			return list;
	}
	
}