package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Emition;
import DBase_Class.Metody;
import GlobalVariable.GlobalVariableForSQL_DBase;


public class MetodyDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	
	

	
	// Metody

	public static void setValueMetody(String name, String code, Boolean inAcredit) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Metody valueEnt = new Metody();
		valueEnt.setName_metody(name);
		valueEnt.setCode_metody(code);
		valueEnt.setInAcredit(inAcredit);
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	@PersistenceContext
	public static List<Metody> getInListAllValueMetody() {
		
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createNamedQuery("getListAllMetody");
		List<Metody> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Metody getValueMetodyById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Metody metody = entitymanager.find(Metody.class, id);

		entitymanager.close();
		emfactory.close();

		return metody;
	}

	@SuppressWarnings("unchecked")
	@GET
	@PersistenceContext
	public static Metody getValueList_MetodyByCode(String name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		
		Query query = entitymanager.createNamedQuery("findMetodyByCode");
		query.setParameter("text", name);
	
		List<Metody> list = query.getResultList();
		
		entitymanager.close();
		emfactory.close();

		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@GET
	@PersistenceContext
	public static List<Metody> getList_MetodyByInAcredit(Boolean inAcredit) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createNamedQuery("getList_MetodyByInAcredit");
		query.setParameter("text", inAcredit);
		
		List<Metody> list =  query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@PersistenceContext
	public static List<Metody> getList_MetodyByActing(Boolean acting) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createNamedQuery("getList_MetodyByActing");
		query.setParameter("text", acting);
		
		List<Metody> list = query.getResultList();;
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@PersistenceContext
	public static List<Metody> getList_MetodyByActingAndArrangement(Boolean acting) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createNamedQuery("getList_MetodyByActingAndArrangement");
		query.setParameter("text", acting);
		
		List<Metody> list = query.getResultList();;
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Metody> getList_MetodyByEmitionAndArrangement(Object emition) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createNamedQuery("getList_MetodyByEmitionAndArrangement");
		query.setParameter("text", emition);
		
		List<Metody> list = query.getResultList();;
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	
	public static String[] getMasiveStringAllValueMetody(){
		 List<Metody> list = getInListAllValueMetody();
		String[] values = new String[list.size()];
		int i = 0;
		for (Metody izpitvan_produkt : list) {
			values[i] = izpitvan_produkt.getCode_metody();
			i++;
		}
		return values;
	}

}
