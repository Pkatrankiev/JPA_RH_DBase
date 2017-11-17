package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Ind_num_doc;
import DBase_Class.Internal_applicant;

public class Ind_num_docDAO {
	
	static String name_DBase = "JPA_RH_DBase";
	

	public static void setBasikValueInd_num_doc(){
		setValueInd_num_doc("œœ12-»≈.–Õ .œÕ.007/00");
		setValueInd_num_doc("Œœ08-»≈.‘’ .œ–.002/00");
		setValueInd_num_doc("Œœ01-»≈.–Õ .”Õ.001/01");
		
	}
	
	public static void setValueInd_num_doc(String value) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Ind_num_doc valueEnt = new Ind_num_doc();
		valueEnt.setName(value);
		;
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Ind_num_doc> getInListAllValueInd_num_doc() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Ind_num_doc e");
		List<Ind_num_doc> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		
		for (Ind_num_doc e : list) {
			System.out
					.println("Num:" + ((Ind_num_doc) e).getId_ind_num_doc() + "  NAME :" + ((Ind_num_doc) e).getName());
		}
		return list;
	}

	
	@GET
	@QueryParam("{id}")
	public static Ind_num_doc getValueInternal_applicantById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
	
		Ind_num_doc  ind_num_doc = (Ind_num_doc) entitymanager.find(Ind_num_doc.class, id);
		
		entitymanager.close();
		emfactory.close();

		return ind_num_doc;
	}
}

