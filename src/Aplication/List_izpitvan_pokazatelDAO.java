package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.List_izpitvan_pokazatel;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class List_izpitvan_pokazatelDAO {
	
//static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	
	
public static void setBasikValuePokazatel(){
	setValueList_pokazatel("Съдържание на гама-излъчващи радионуклиди:");
	setValueList_pokazatel("Съдържание на алфа-излъчващи радионуклиди:");
	setValueList_pokazatel("Съдържание на 55Fe");
	setValueList_pokazatel("Съдържание на 63Ni");
	setValueList_pokazatel("Съдържание на 90Sr");
	setValueList_pokazatel("Съдържание на 241Pu");
	
	
}


//	Pokazatel
	public static void setValueList_pokazatel(String name) {
		
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		List_izpitvan_pokazatel pokazatal = new List_izpitvan_pokazatel();
		
		pokazatal.setName_pokazatel(name);
		
		entitymanager.persist(pokazatal);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	public static List<List_izpitvan_pokazatel> getInListAllValuePokazatel() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM List_izpitvan_pokazatel e");
		List<List_izpitvan_pokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (List_izpitvan_pokazatel e : list) {
			System.out.println(
					"Num:" + ((List_izpitvan_pokazatel) e).getId_pokazatel() + "  NAME :" + ((List_izpitvan_pokazatel) e).getName_pokazatel()
					);
		}
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static List_izpitvan_pokazatel getValueIzpitvan_pokazatelById(@QueryParam("id") int id) {
//	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
	
	List_izpitvan_pokazatel  izpitvan_pokazatel = (List_izpitvan_pokazatel) entitymanager.find(List_izpitvan_pokazatel.class, id);
	
	entitymanager.close();
	emfactory.close();

	return izpitvan_pokazatel;
}

	@GET
	public static List_izpitvan_pokazatel getValueIzpitvan_pokazatelByName(String name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM List_izpitvan_pokazatel e WHERE e.name = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		if (query.getResultList().isEmpty()){
			setValueList_pokazatel(name);	
		}
		List_izpitvan_pokazatel list = (List_izpitvan_pokazatel) query.getSingleResult();
		entitymanager.close();
		emfactory.close();	
				
		

		return list;
	}	
	
	public static String[] getMasiveStringAllValueList_Izpitvan_Pokazatel(){
		 List<List_izpitvan_pokazatel> list = getInListAllValuePokazatel();
		String[] values = new String[list.size()];
		int i = 0;
		for (List_izpitvan_pokazatel pokazatel : list) {
			values[i] = pokazatel.getName_pokazatel();
			i++;
		}
		return values;
	}
		
}
