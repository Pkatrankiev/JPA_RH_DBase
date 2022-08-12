package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Ejection;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.Obekt_na_izpitvane_request;
import DBase_Class.Period;

import GlobalVariable.GlobalVariableForSQL_DBase;
import GlobalVariable.ResourceLoader;

public class EjectionDAO {

	public static void setValueEjection(Izpitvan_produkt produkt, Obekt_na_izpitvane_request obect,
			Period mesec, int godina, Double volum) {

		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Ejection valueEnt = new Ejection();

		valueEnt.setProdukt(produkt);
		valueEnt.setObect(obect);
		valueEnt.setMesec(mesec);
		valueEnt.setGodina(godina);
		valueEnt.setVolum(volum);

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();

	}

	public static void setEjection(Ejection valueEnt) {

		// EntityManagerFactory emfactory =
		// Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();

	}

	
	public static List<Ejection> getInListAllEjection() {
		// EntityManagerFactory emfactory =
		// Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager
				.createQuery("SELECT e FROM Ejection e ORDER BY e.godina ASC");
		@SuppressWarnings("unchecked")
		List<Ejection> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

	
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Ejection getValueExternal_applicantById(@QueryParam("id") int id) {
		// EntityManagerFactory emfactory =
		// Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Ejection ejection = (Ejection) entitymanager.find(Ejection.class, id);

		entitymanager.close();
		emfactory.close();

		return ejection;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Ejection> getListEjectionFromMesecANDGodina(Period mesec, int godina) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Ejection e WHERE e.mesec = :textMesec AND e.godina = :textGodina  ORDER  BY e.produkt ASC";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("textMesec", mesec);
		query.setParameter("textGodina", godina);
		
		List<Ejection>  list =  query.getResultList();
		entitymanager.close();
		emfactory.close();
		
		

		return list;
	}
	
	public static void updateEjection(Ejection ejection) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
	
		entitymanager.find(Ejection.class, ejection.getId_ejection());
		entitymanager.merge(ejection);


		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			ResourceLoader.appendToFile(e);
			JOptionPane.showMessageDialog(null, "Прблем при обновяване на резултат: "+ejection.getObect().getName_obekt_na_izpitvane()+"-"+
			ejection.getMesec().getValue()+" "+ejection.getGodina(), "Проблем с база данни:",
					JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}	
	
}
