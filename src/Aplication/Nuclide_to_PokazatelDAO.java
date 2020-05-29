package Aplication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import AddResultViewFunction.SortListObjectByField;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Nuclide;
import DBase_Class.Nuclide_to_Pokazatel;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class Nuclide_to_PokazatelDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	
	

	public static void setValueNuclide_to_Pokazatel(List_izpitvan_pokazatel izpitvanPokazatel, Nuclide nuclide) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Nuclide_to_Pokazatel nuclide_to_Pokazatel = new Nuclide_to_Pokazatel();

		nuclide_to_Pokazatel.setPokazatel(izpitvanPokazatel);
		nuclide_to_Pokazatel.setNuclide(nuclide);

		entitymanager.persist(nuclide_to_Pokazatel);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	public static void setValueNuclide_to_Pokazatel(Nuclide_to_Pokazatel pokazatal) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		entitymanager.persist(pokazatal);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	public static void updateObjectNuclide_to_Pokazatel(Nuclide_to_Pokazatel nuclide_to_Pokazatel) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		entitymanager.find(Nuclide_to_Pokazatel.class, nuclide_to_Pokazatel.getId_Nuclide_to_Pokazatel());
		entitymanager.merge(nuclide_to_Pokazatel);

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null, "Прблем при обновяване на нуклид_показател: " + nuclide_to_Pokazatel.getPokazatel().getName_pokazatel(),
					"Проблем с база данни:", JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	public static List<Nuclide_to_Pokazatel> getInListAllNuclide_to_Pokazatel() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createNamedQuery("getInListAllNuclide_to_Pokazatel");
		List<Nuclide_to_Pokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return SortListObjectByField.sortListNuclide_to_PokazatelByCodedNuclide(list);
	}

	@GET
	@QueryParam("{id}")
	public static Nuclide_to_Pokazatel getNuclide_to_PokazatelById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		Nuclide_to_Pokazatel мetody_to_Pokazatel = (Nuclide_to_Pokazatel) entitymanager.find(Nuclide_to_Pokazatel.class, id);

		entitymanager.close();
		emfactory.close();

		return мetody_to_Pokazatel;
	}

	@SuppressWarnings("unchecked")
	public static List<Nuclide_to_Pokazatel> getListNuclide_to_PokazatelByPokazatel(List_izpitvan_pokazatel volume_check) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		
		Query query = entitymanager.createNamedQuery("findNuclideToPokazatelByPokazatel");
		query.setParameter("text", volume_check);

		List<Nuclide_to_Pokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return SortListObjectByField.sortListNuclide_to_PokazatelByCodedNuclide(list);
	}
	
	
	public static List<Nuclide> getListNuclideByPokazatel(List_izpitvan_pokazatel volume_check) {
		List<Nuclide> list = new ArrayList<>();
		for (Nuclide_to_Pokazatel nuclideToPok : getListNuclide_to_PokazatelByPokazatel(volume_check)) {
			list.add(nuclideToPok.getNuclide());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static List<Nuclide_to_Pokazatel> getListNuclide_to_PokazatelByNuclide(Nuclide volume_check) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();
		
		Query query = entitymanager.createNamedQuery("findNuclide_to_PokazatelByNuclide");
		query.setParameter("text", volume_check);

		List<Nuclide_to_Pokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return SortListObjectByField.sortListNuclide_to_PokazatelByCodedNuclide(list);
	}
	
	public static void deleteNuclide_to_Pokazatel(Nuclide_to_Pokazatel pokazatel) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = GlobalVariableForSQL_DBase.getEntityManagerDBase(emfactory);
		entitymanager.getTransaction().begin();

		entitymanager.find(Nuclide_to_Pokazatel.class, pokazatel.getId_Nuclide_to_Pokazatel());
		entitymanager.remove(pokazatel);

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null,
					"Прблем при изтриване на нуклид_показател: " + pokazatel.getPokazatel().getName_pokazatel(),
					"Проблем с база данни:", JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}

}
