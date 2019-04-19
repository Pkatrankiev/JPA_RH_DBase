package Aplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Aplicant;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class AplicantDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	
	public static void setValueAplicant(String name, String family) {
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Aplicant valueEnt = new Aplicant();
		valueEnt.setName_aplicant(name);
		valueEnt.setFamily_aplicant(family);

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static void saveValueAplicantWitchCheck(String nameFamily) {
		String[] masive_AplicantNameFamily = getMasiveStringAllName_FamilyAplicant();
		Boolean fl_Aplicant = false;
		for (String string : masive_AplicantNameFamily) {
			if (string.equals(nameFamily)) {
				fl_Aplicant = true;
			}
		}
		if(!fl_Aplicant){
		setValueAplicant(nameFamily.substring(0, nameFamily.indexOf(" ")), nameFamily.substring(nameFamily.indexOf(" ")+1));
		}
	}
	
	@SuppressWarnings("unchecked")
	@GET
	public static List<Aplicant> getInListAllValueAplicant() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Aplicant e ORDER BY e.name ASC");
		List<Aplicant> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static String[] getMasiveStringAllName_FamilyAplicant() {
		List<Aplicant> list = getInListAllValueAplicant();
		String[] values = new String[list.size()];
		int i = 0;
		for (Aplicant user : list) {
			values[i] = user.getName_aplicant() + " " + user.getFamily_aplicant();
			i++;
		}
		return values;
	}

	@GET
	@QueryParam("{id}")
	public static Aplicant getValueAplicantById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Aplicant aplicant = (Aplicant) entitymanager.find(Aplicant.class, id);

		entitymanager.close();
		emfactory.close();

		return aplicant;
	}

	@SuppressWarnings("unchecked")
	@GET
	public static List<Aplicant> getValueAplicantByName(String name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Aplicant e WHERE e.name = :text ORDER BY e.name ASC";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);

		List<Aplicant> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	@SuppressWarnings("unchecked")
	@GET
	public static List<Aplicant> getValueAplicantByFamily(String family) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Aplicant e WHERE e.family = :text ORDER BY e.family ASC";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", family);

		List<Aplicant> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static Aplicant getAplicantByNameFamily(String nameFamily) {
		for (Aplicant aplicant : getInListAllValueAplicant()) {
			if ((aplicant.getName_aplicant() + " " + aplicant.getFamily_aplicant()).equals(nameFamily)) {
				return aplicant;
			}
		}
		return null;
	}
}
