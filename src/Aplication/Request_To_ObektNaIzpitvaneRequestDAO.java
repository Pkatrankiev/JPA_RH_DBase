package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Request;
import DBase_Class.Request_To_ObektNaIzpitvaneRequest;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class Request_To_ObektNaIzpitvaneRequestDAO {

	public static void setValueRequest_To_ObektNaIzpitvaneRequest(Request request, Obekt_na_izpitvane_request obektNaIzp){
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Request_To_ObektNaIzpitvaneRequest request_to_ObectNaIzp = new Request_To_ObektNaIzpitvaneRequest();

		request_to_ObectNaIzp.setRequest(request);
		request_to_ObectNaIzp.setObektNaIzp(obektNaIzp);

		entitymanager.persist(request_to_ObectNaIzp);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	public static void setValueRequest_To_ObektNaIzpitvaneRequest(Request_To_ObektNaIzpitvaneRequest request_to_ObectNaIzp) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(request_to_ObectNaIzp);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static void updateValueRequest_To_ObektNaIzpitvaneRequest(Request_To_ObektNaIzpitvaneRequest request_to_ObectNaIzp) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.find(Request_To_ObektNaIzpitvaneRequest.class, request_to_ObectNaIzp.getId_Request_To_ObektNaIzpitvaneRequest());
		entitymanager.merge(request_to_ObectNaIzp);

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null, "Прблем при обновяване на заявака-обект на изпитване: " + request_to_ObectNaIzp.getRequest().getRecuest_code(),
					"Проблем с база данни:", JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	@GET
	public static List<Request_To_ObektNaIzpitvaneRequest> getInListAllRequest_To_ObektNaIzpitvaneRequest() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createNamedQuery("ListAllRequest_To_ObektNaIzpitvaneRequest");
		List<Request_To_ObektNaIzpitvaneRequest> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Request_To_ObektNaIzpitvaneRequest getRequest_To_ObektNaIzpitvaneRequestById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Request_To_ObektNaIzpitvaneRequest requestToObectIzp = (Request_To_ObektNaIzpitvaneRequest) entitymanager.find(Request_To_ObektNaIzpitvaneRequest.class, id);

		entitymanager.close();
		emfactory.close();

		return requestToObectIzp;
	}

	@SuppressWarnings("unchecked")
	public static List<Request_To_ObektNaIzpitvaneRequest> getRequest_To_ObektNaIzpitvaneRequestByRequest(Request request) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createNamedQuery("findRequest_To_ObektNaIzpitvaneRequestByRequest");
		query.setParameter("text", request);

		List<Request_To_ObektNaIzpitvaneRequest> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	@SuppressWarnings("unchecked")
	public static Request_To_ObektNaIzpitvaneRequest getRequest_To_ObektNaIzpitvaneRequestByRequestAndObektNaIzp(Request request, Obekt_na_izpitvane_request obektNaIzp) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createNamedQuery("ListAllRequest_To_ObektNaIzpitvaneRequestByRequestAndObektNaIzp");
		query.setParameter("obIzp", obektNaIzp);
		query.setParameter("request", request);

		List<Request_To_ObektNaIzpitvaneRequest> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Request_To_ObektNaIzpitvaneRequest> getRequest_To_ObektNaIzpitvaneRequestByObektNaIzp(Obekt_na_izpitvane_request obektNaIzp) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createNamedQuery("findRequest_To_ObektNaIzpitvaneRequestByObektNaIzp");
		query.setParameter("text", obektNaIzp);

		List<Request_To_ObektNaIzpitvaneRequest> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static void deleteRequest_To_ObektNaIzpitvaneRequest(Request_To_ObektNaIzpitvaneRequest request_to_ObectNaIzp) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.find(Request_To_ObektNaIzpitvaneRequest.class, request_to_ObectNaIzp.getId_Request_To_ObektNaIzpitvaneRequest());
		entitymanager.remove(request_to_ObectNaIzp);

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null,
					"Прблем при изтриване на заявка-обект на изпитване: " + request_to_ObectNaIzp.getRequest().getRecuest_code(),
					"Проблем с база данни:", JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}

}
