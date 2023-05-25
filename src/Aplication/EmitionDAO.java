package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import DBase_Class.Emition;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class EmitionDAO {

	@SuppressWarnings("unchecked")
	public static List<Emition> getListAllEmition() {
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createNamedQuery("getListAllEmition");
		List<Emition> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Emition> getListEmitionByEmition_name(Emition emition) {
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createNamedQuery("getListEmitionByEmition_name");
		query.setParameter("text", emition);

		List<Emition> list = query.getResultList();

		entitymanager.close();
		emfactory.close();

		return list;
	}
	

	public static Emition getEmitionByEmition_name(String emition) {
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		
		String hql = "SELECT e FROM Emition e WHERE e.emition_name = :text";
		
		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", emition);

		Emition list = (Emition) query.getSingleResult();

		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	
	
	
	
	
}
