package Aplication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Obekt_na_izpitvane_request;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class Obekt_na_izpitvane_requestDAO {

//static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	
	

//	Obekt_na_izpitvane
	public static void setValueObekt_na_izpitvane(String name,String simpleName) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Obekt_na_izpitvane_request valueEnt = new Obekt_na_izpitvane_request();
		valueEnt.setName_obekt_na_izpitvane(name);
		valueEnt.setSimple_Name(simpleName);
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Obekt_na_izpitvane_request> getInListAllValueObekt_na_izpitvane() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Obekt_na_izpitvane_request e ORDER BY e.name ASC");
		@SuppressWarnings("unchecked")
		List<Obekt_na_izpitvane_request> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	
	public static List<String> getListStringAllValueObekt_na_izpitvane() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
	List<Obekt_na_izpitvane_request> list = getInListAllValueObekt_na_izpitvane();
	List<String> values = new ArrayList<String>();
		
		for (Obekt_na_izpitvane_request e : list) { 
			values.add( e.getName_obekt_na_izpitvane());
			
			}
		return values;
	}

	public static List<String > getMasiveStringAllValueObekt_na_izpitvane() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
	List<Obekt_na_izpitvane_request> list = getInListAllValueObekt_na_izpitvane();
	List<String > values = new ArrayList<String >();
		int i = 0;
		for (Obekt_na_izpitvane_request e : list) { 
			values.add( e.getName_obekt_na_izpitvane());
			i++;
			}
		return values;
	}
	
	@GET
	@QueryParam("{id}")
	public static Obekt_na_izpitvane_request getValueObekt_na_izpitvaneById(@QueryParam("id") int id) {
//	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
	EntityManager entitymanager = emfactory.createEntityManager();
	entitymanager.getTransaction().begin();
	Obekt_na_izpitvane_request  obekt_na_izpitvane = (Obekt_na_izpitvane_request) entitymanager.find(Obekt_na_izpitvane_request.class, id);
	
	entitymanager.close();
	emfactory.close();

	return obekt_na_izpitvane;
}
	
	@SuppressWarnings("unchecked")
	@GET
	public static Obekt_na_izpitvane_request getValueObekt_na_izpitvane_requestByName(String name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Obekt_na_izpitvane_request e WHERE e.name = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		
		List<Obekt_na_izpitvane_request> list = query.getResultList();
		if (list.isEmpty()){
			setValueObekt_na_izpitvane(name, "");
		 list = query.getResultList();
		}

		entitymanager.close();
		emfactory.close();

		return list.get(0);
	}
	
	public static void saveValueObekt_na_izpitvaneWitchCheck(String value) {
		List<String > masive_Obekt_na_izpitvane =  getMasiveStringAllValueObekt_na_izpitvane();
		
		Boolean fl_Obekt_na_izpitvane = false;
		for (String string : masive_Obekt_na_izpitvane) {
			if (string.equals(value)) {
				fl_Obekt_na_izpitvane = true;
			}
		}
		if(!fl_Obekt_na_izpitvane){
			setValueObekt_na_izpitvane( value,"");	
		}
	}
}
