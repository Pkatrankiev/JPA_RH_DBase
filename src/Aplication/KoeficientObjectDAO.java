package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.KoeficientObject;
import DBase_Class.Obekt_na_izpitvane_request;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class KoeficientObjectDAO {

	
	
	public static void setValueMetody(Obekt_na_izpitvane_request obect, Double koeficient) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		KoeficientObject valueEnt = new KoeficientObject();
		
		valueEnt.setObect(obect);
		valueEnt.setKoeficient(koeficient);
		
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	public static void setBasicValueKoeficientObject() {
		setValueMetody(Obekt_na_izpitvane_requestDAO.getValueObekt_na_izpitvaneById(8), 1.26);
		setValueMetody(Obekt_na_izpitvane_requestDAO.getValueObekt_na_izpitvaneById(9), 1.25);
		setValueMetody(Obekt_na_izpitvane_requestDAO.getValueObekt_na_izpitvaneById(121), 1.21);

	}
	
	@SuppressWarnings("unchecked")
	@PersistenceContext
	public static List<KoeficientObject> getInListAllValueKoeficientObject() {
		
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createNamedQuery("getListAllKoeficientObject");
		List<KoeficientObject> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}
	
	@GET
	@QueryParam("{id}")
	public static KoeficientObject getValueKoeficientObjectById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		KoeficientObject metody = entitymanager.find(KoeficientObject.class, id);

		entitymanager.close();
		emfactory.close();

		return metody;
	}
	
	

	
}
