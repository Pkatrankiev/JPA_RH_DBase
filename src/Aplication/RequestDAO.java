package Aplication;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.External_applicant;
import DBase_Class.Extra_module;
import DBase_Class.Ind_num_doc;
import DBase_Class.Internal_applicant;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.Razmernosti;
import DBase_Class.Request;
import DBase_Class.Users;
import DBase_Class.Zabelejki;
import GlobalVariable.GlobalVariableForSQL_DBase;
import DBase_Class.Obekt_na_izpitvane_request;

public class RequestDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	

	public static Request setValueRequest(String recuest_code, String date_request, Boolean accreditation,
			Boolean section, Extra_module xtra_module, int counts_samples, String description_sample_group,
			String date_reception, String date_execution, Ind_num_doc ind_num_doc,
			Izpitvan_produkt izpitvan_produkt, Razmernosti razmernosti, Zabelejki zabelejki, Users users,
			String textObektNaIzpRequest) {

		Request valueEnt = new Request();
		valueEnt.setRecuest_code(recuest_code);
		valueEnt.setDate_request(date_request);
		valueEnt.setAccreditation(accreditation);
		valueEnt.setSection(section);

		valueEnt.setExtra_module(xtra_module);

		valueEnt.setCounts_samples(counts_samples);
		valueEnt.setDescription_sample_group(description_sample_group);
		valueEnt.setDate_reception(date_reception);

		valueEnt.setDate_execution(date_execution);
		valueEnt.setInd_num_doc(ind_num_doc);
		valueEnt.setIzpitvan_produkt(izpitvan_produkt);

		valueEnt.setRazmernosti(razmernosti);

		valueEnt.setZabelejki(zabelejki);
		valueEnt.setUsers(users);
		valueEnt.setText_obekt_na_izpitvane_request(textObektNaIzpRequest);

		return valueEnt;
	}

	public static void saveRequestFromValue(String recuest_code, String date_request, Boolean accreditation,
			Boolean section, Extra_module xtra_module, int counts_samples, String description_sample_group,
			String date_reception, String date_execution, Ind_num_doc ind_num_doc,
			Izpitvan_produkt izpitvan_produkt, Razmernosti razmernosti, Zabelejki zabelejki, Users users,
			Obekt_na_izpitvane_request obekt_na_izpitvane_request) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Request valueEnt = new Request();
		valueEnt.setRecuest_code(recuest_code);
		valueEnt.setDate_request(date_request);
		valueEnt.setAccreditation(accreditation);
		valueEnt.setSection(section);

		valueEnt.setExtra_module(xtra_module);

		valueEnt.setCounts_samples(counts_samples);
		valueEnt.setDescription_sample_group(description_sample_group);
		valueEnt.setDate_reception(date_reception);

		valueEnt.setDate_execution(date_execution);
		valueEnt.setInd_num_doc(ind_num_doc);
		valueEnt.setIzpitvan_produkt(izpitvan_produkt);

		valueEnt.setRazmernosti(razmernosti);

		valueEnt.setZabelejki(zabelejki);
		valueEnt.setUsers(users);
		valueEnt.setObekt_na_izpitvane_request(obekt_na_izpitvane_request);

		entitymanager.persist(valueEnt);
		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null, "Прблем при записа", "Проблем с база данни:",
					JOptionPane.ERROR_MESSAGE);
		}
		entitymanager.close();
		emfactory.close();
	}

	public static void updateObjectRequest(Request request) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		entitymanager.find(Request.class, request.getId_recuest());
		entitymanager.merge(request);

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null, "Прблем при обновяване на заявка: " + request.getRecuest_code(),
					"Проблем с база данни:", JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}

	public static void saveRequestFromRequest(Request valueEnt) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		entitymanager.persist(valueEnt);
		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			;
			JOptionPane.showMessageDialog(null,  "Прблем при запис на заявка: "+ valueEnt.getRecuest_code()+"kod na ExtraMod: "+valueEnt.getExtra_module().getId_extra_module() , "Проблем с база данни:",
					JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	public static Boolean checkRequestCode(String check_code) {
		Boolean available = false;
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
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

	@SuppressWarnings("unchecked")
	public static List<Request> getInListAllValueRequest() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Request e ORDER BY e.recuest_code ASC");

		List<Request> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	@SuppressWarnings("unchecked")
	public static void saveBasicValueRequest() {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
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
			Ind_num_doc ind_num_doc = Ind_num_docDAO.getValueInd_num_docById(ran);
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

	@GET
	@QueryParam("{id}")
	public static Request getValueRequestById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
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
			} catch (NumberFormatException e) {
				i--;
			}
		}
		Max_request_code = Arrays.stream(request_code).max().getAsInt();

		return Max_request_code;
	}

	public static void setIzpitvan_produktInRequestById(int id, Izpitvan_produkt izpitvan_produkt) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
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

	@SuppressWarnings("unchecked")
	public static List<Request> getListRequestFromColumnByVolume(String column_name, Object volume_check) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Request e WHERE e." + column_name + " = :text ORDER BY e.recuest_code ASC";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", volume_check);

		List<Request> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<Request> getListRequestFromColumnByContainsString(String column_name, String volume_check) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Request e WHERE e." + column_name + " REGEXP '" + volume_check + " ORDER BY e.recuest_code ASC";

		Query query = entitymanager.createQuery(hql);
		// query.setParameter("text", volume_check);

		List<Request> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static Request getRequestFromColumnByVolume(String column_name, Object volume_check) {
		Request list = new Request();
		for (Request element : getListRequestFromColumnByVolume(column_name, volume_check)) {
			list = element;
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<Request> getListRequestFromMonitoringProgramm(String izp_prod) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Request e WHERE e.ind_num_doc != :ind_num AND e.izpitvan_produkt = :izp_prod ORDER BY e.recuest_code ASC";
				

		Query query = entitymanager.createQuery(hql);
		query.setParameter("ind_num", Ind_num_docDAO.getValueInd_num_docById(1));
		query.setParameter("izp_prod", Izpitvan_produktDAO.getValueIzpitvan_produktByName(izp_prod));
		List<Request> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	public static void DeleteRequest(Request valueEnt) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Request request = entitymanager.find(Request.class, valueEnt.getId_recuest());
		entitymanager.remove(request);
		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null,  "Прблем при изтриване на заявка: "+request.getRecuest_code(), "Проблем с база данни:",
					JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}
}