package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.DopalnIziskv;
import GlobalVariable.GlobalVariableForSQL_DBase;


public class DopalnIziskvDAO {
//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	

	public static void setValueDopalnIziskv(String value) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		DopalnIziskv valueEnt = new DopalnIziskv();
		valueEnt.setName_dopIzis(value);
		
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	
	public static List<DopalnIziskv> getInListAllValueDopalnIziskv() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM DopalnIziskv e  ORDER BY e.name ASC");
		@SuppressWarnings("unchecked")
		List<DopalnIziskv> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static DopalnIziskv getValueDopalnIziskvById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		DopalnIziskv otclon = (DopalnIziskv) entitymanager.find(DopalnIziskv.class, id);

		entitymanager.close();
		emfactory.close();

		return otclon;
	}

	public static String[] getMasiveStringAllValueDopalnIziskv() {
		List<DopalnIziskv> list = getInListAllValueDopalnIziskv();
		String[] values = new String[list.size()];
		int i = 0;
		for (DopalnIziskv otclon : list) {
			values[i] = otclon.getName_dopIzis();
			i++;
		}
		return values;
	}

	@SuppressWarnings("unchecked")
	@GET
	public static DopalnIziskv getValueDopalnIziskvByName(String name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		String hql = "SELECT e FROM DopalnIziskv e WHERE e.name = :text";
		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		
		List<DopalnIziskv> list = query.getResultList();
		
		if (list.isEmpty()){
			setValueDopalnIziskv(name);
		 list = query.getResultList();
		}
	
		entitymanager.close();
		emfactory.close();

		return list.get(0);
	}

	public static void saveDopalnIziskvWitchCheck(String value) {
		String[] masive_Otclon = getMasiveStringAllValueDopalnIziskv();
		Boolean fl_Otclon = false;
		for (String string : masive_Otclon) {
			if (string.equals(value)) {
				fl_Otclon = true;
			}
		}
		if(!fl_Otclon){
			setValueDopalnIziskv( value);
	}
	}
}
