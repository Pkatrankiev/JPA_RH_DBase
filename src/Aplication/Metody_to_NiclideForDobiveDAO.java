package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Metody;
import DBase_Class.Metody_to_NiclideForDobive;

import DBase_Class.Nuclide;
import GlobalVariable.GlobalVariableForSQL_DBase;
import GlobalVariable.ResourceLoader;

public class Metody_to_NiclideForDobiveDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	

	public static void setValueMetody_to_NiclideForDobive(Nuclide nuclide, Metody metody) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Metody_to_NiclideForDobive metody_to_NiclideForDobive = new Metody_to_NiclideForDobive();

		metody_to_NiclideForDobive.setNuclide(nuclide);
		metody_to_NiclideForDobive.setMetody(metody);

		entitymanager.persist(metody_to_NiclideForDobive);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Metody_to_NiclideForDobive> getListAllMetody_to_NiclideForDobive() {
	
//	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
	
	Query query = entitymanager.createNamedQuery("getListAllMetody_to_NiclideForDobive");
	List<Metody_to_NiclideForDobive> list = query.getResultList();
	entitymanager.close();
	emfactory.close();
	return list;
}
	
	@GET
	@QueryParam("{id}")
	public static Metody_to_NiclideForDobive getMetody_to_NiclideForDobiveById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		
		Metody_to_NiclideForDobive metody_to_NiclideForDobive = entitymanager.find(Metody_to_NiclideForDobive.class, id);

		entitymanager.close();
		emfactory.close();

		return metody_to_NiclideForDobive;
	}

	@SuppressWarnings("unchecked")
	public static List<Metody_to_NiclideForDobive> getListMetody_to_NiclideForDobiveByMetody(Metody name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		
		Query query = entitymanager.createNamedQuery("findMetody_to_NiclideForDobiveByMetody");
		query.setParameter("text", name);
	
		List<Metody_to_NiclideForDobive> list = query.getResultList();
		
		entitymanager.close();
		emfactory.close();

		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<Metody_to_NiclideForDobive> getListDobivByNuclide(Nuclide name) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		
		Query query = entitymanager.createNamedQuery("findMetody_to_NiclideForDobiveByNuclide");
		query.setParameter("text", name);
	
		List<Metody_to_NiclideForDobive> list = query.getResultList();
		
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public  void updateMetody_to_NiclideForDobive(Metody_to_NiclideForDobive metody_to_NiclideForDobive) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
	
		entitymanager.find(Metody_to_NiclideForDobive.class, metody_to_NiclideForDobive.getId_Metody_to_NiclideForDobive());
		entitymanager.merge(metody_to_NiclideForDobive);


		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			ResourceLoader.appendToFile(e);
			JOptionPane.showMessageDialog(null, "Прблем при обновяване на Мотод-Нуклид: "+metody_to_NiclideForDobive.getMetody()+" "+metody_to_NiclideForDobive.getNuclide().getSymbol_nuclide(), "Проблем с база данни:",
					JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}
	
	public static void deleteMetody_to_NiclideForDobiveById(int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		EntityTransaction updateTranzaction = entitymanager.getTransaction();
		updateTranzaction.begin();
		Query query = entitymanager.createQuery(" delete from Metody_to_NiclideForDobive where id =:id");
		query.setParameter("id", id);
		
        query.executeUpdate();
        updateTranzaction.commit();
				
		entitymanager.close();
		emfactory.close();
		}
}
