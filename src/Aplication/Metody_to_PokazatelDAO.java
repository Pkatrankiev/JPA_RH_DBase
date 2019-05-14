package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Metody_to_Pokazatel;
import GlobalVariable.GlobalVariableForSQL_DBase;


public class Metody_to_PokazatelDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	

	public static void setValueMetody_to_Pokazatel(List_izpitvan_pokazatel izpitvanPokazatel, Metody metody) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Metody_to_Pokazatel metody_to_Pokazatel = new Metody_to_Pokazatel();

		metody_to_Pokazatel.setPokazatel(izpitvanPokazatel);
		metody_to_Pokazatel.setMetody(metody);

		entitymanager.persist(metody_to_Pokazatel);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	public static void setValueMetody_to_Pokazatel(Metody_to_Pokazatel pokazatal) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(pokazatal);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	public static void updateObjectMetody_to_Pokazatel(Metody_to_Pokazatel metody_to_Pokazatel) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.find(Metody_to_Pokazatel.class, metody_to_Pokazatel.getId_Metody_to_Pokazatel());
		entitymanager.merge(metody_to_Pokazatel);

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null, "Прблем при обновяване на метод_показател: " + metody_to_Pokazatel.getPokazatel().getName_pokazatel(),
					"Проблем с база данни:", JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	@GET
	public static List<Metody_to_Pokazatel> getInListAllMetody_to_Pokazatel() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Metody_to_Pokazatel e");
		List<Metody_to_Pokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static Metody_to_Pokazatel getMetody_to_PokazatelById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Metody_to_Pokazatel мetody_to_Pokazatel = (Metody_to_Pokazatel) entitymanager.find(Metody_to_Pokazatel.class, id);

		entitymanager.close();
		emfactory.close();

		return мetody_to_Pokazatel;
	}

	@SuppressWarnings("unchecked")
	public static List<Metody_to_Pokazatel> getListMetody_to_PokazatelByPokazatel(List_izpitvan_pokazatel volume_check) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createNamedQuery("findMetody_to_PokazatelByPokazatel");
		query.setParameter("text", volume_check);

		List<Metody_to_Pokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Metody_to_Pokazatel> getListMetody_to_PokazatelByMetody(Metody volume_check) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Query query = entitymanager.createNamedQuery("findMetody_to_PokazatelByMetody");
		query.setParameter("text", volume_check);

		List<Metody_to_Pokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static void deleteMetody_to_Pokazatel(Metody_to_Pokazatel pokazatel) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.find(Metody_to_Pokazatel.class, pokazatel.getId_Metody_to_Pokazatel());
		entitymanager.remove(pokazatel);

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null,
					"Прблем при изтриване на метод_показател: " + pokazatel.getPokazatel().getName_pokazatel(),
					"Проблем с база данни:", JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}
}
