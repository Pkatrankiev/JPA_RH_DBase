package Aplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.IzpitvanPokazatel;
import DBase_Class.List_izpitvan_pokazatel;
import DBase_Class.Metody;
import DBase_Class.Request;
import DBase_Class.Sample;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class IzpitvanPokazatelDAO {

//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	
	

	public static void setValueIzpitvanPokazatel(List_izpitvan_pokazatel list_izpitvan_pokazatel, Request request,
			Metody metody) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		IzpitvanPokazatel izpitvanPokazatel = new IzpitvanPokazatel();

		izpitvanPokazatel.setPokazatel(list_izpitvan_pokazatel);
		izpitvanPokazatel.setRequest(request);
		izpitvanPokazatel.setMetody(metody);

		entitymanager.persist(izpitvanPokazatel);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static void updateObjectIzpitvanPokazatel(IzpitvanPokazatel izpitvan_pokazatel) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.find(IzpitvanPokazatel.class, izpitvan_pokazatel.getId_pokazatel());
		entitymanager.merge(izpitvan_pokazatel);

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null, "Прблем при обновяване на показател: " + izpitvan_pokazatel.getPokazatel().getName_pokazatel(),
					"Проблем с база данни:", JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}

	public static void setValueIzpitvanPokazatel(IzpitvanPokazatel pokazatal) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(pokazatal);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	@SuppressWarnings("unchecked")
	@GET
	public static List<IzpitvanPokazatel> getInListAllValueIzpitvan_pokazatel() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM IzpitvanPokazatel e");
		List<IzpitvanPokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
	
		return list;
	}

	@GET
	@QueryParam("{id}")
	public static IzpitvanPokazatel getValueIzpitvan_pokazatelById(@QueryParam("id") int id) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		IzpitvanPokazatel izpitvan_pokazatel = (IzpitvanPokazatel) entitymanager.find(IzpitvanPokazatel.class, id);

		entitymanager.close();
		emfactory.close();

		return izpitvan_pokazatel;
	}

	@SuppressWarnings("unchecked")
	@GET
	public static List<IzpitvanPokazatel> getValueIzpitvan_pokazatelByRequest(Request request) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM IzpitvanPokazatel e WHERE e.request = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", request);
		List<IzpitvanPokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	@GET
	public static IzpitvanPokazatel getIzpitvan_pokazatelObjectByRequestAndListIzpitvanPokazatel(Request request, List_izpitvan_pokazatel pokazatel) {

		for (IzpitvanPokazatel izpitvanPokazatel : getValueIzpitvan_pokazatelByRequest(request)) {
			if(izpitvanPokazatel.getPokazatel().getName_pokazatel().equals(pokazatel.getName_pokazatel())){
				return izpitvanPokazatel;
			}
		}

		return null;
	}

	
	
	
	@SuppressWarnings("unchecked")
	@GET
	public static List<IzpitvanPokazatel> getValueIzpitvan_pokazatelByPokazatel(List_izpitvan_pokazatel pokazatel) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM IzpitvanPokazatel e WHERE e.List_izpitvan_pokazatel = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", pokazatel);
		List<IzpitvanPokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	@SuppressWarnings("unchecked")
	public static void setBasikValueIzpitvan_pokazatel() {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		int min = 1;
		int max = 1;
		int ran = 1;

		// Get Sample list
		List<Sample> listSample = entitymanager.createQuery("SELECT e FROM Request e").getResultList();
		System.out.println("Num Sample:" + listSample.size());

		for (int samNum = 1; samNum <= listSample.size(); samNum++) {

			int ranval = 1 + (int) (Math.random() * 2);
			System.out.println();
			System.out.println("***********************************************************");
			System.out.println("Num Results:" + ranval);
			for (int i = 1; i <= ranval; i++) {

				// Get random List_izpitvan_pokazatel object
				List<List_izpitvan_pokazatel> listIp = entitymanager
						.createQuery("SELECT e FROM List_izpitvan_pokazatel e").getResultList();
				System.out.println("Num Izpitvan_pokazatel:" + listIp.size());
				max = listIp.size();
				ran = min + (int) (Math.random() * ((max - min) + 1));
				List_izpitvan_pokazatel pokazatel = List_izpitvan_pokazatelDAO.getValueIzpitvan_pokazatelById(ran);
				System.out.println("Name IzpitvanPokazatel:" + pokazatel.getId_pokazatel());

				// Get random Metody object
				List<Metody> listMetody = entitymanager.createQuery("SELECT e FROM Metody e").getResultList();
				System.out.println("Num Metody:" + listMetody.size());
				max = listMetody.size();
				ran = min + (int) (Math.random() * ((max - min) + 1));
				Metody metody = MetodyDAO.getValueMetodyById(ran);
				System.out.println("Code Metody:" + metody.getId_metody());

				setValueIzpitvanPokazatel(pokazatel, null, metody);

			}
		}

		entitymanager.close();
		emfactory.close();
	}

	public static List<IzpitvanPokazatel> getListIzpitvan_pokazatelFromColumnByVolume(String column_name,
			Object volume_check) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM IzpitvanPokazatel e WHERE e." + column_name + " = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", volume_check);

		@SuppressWarnings("unchecked")
		List<IzpitvanPokazatel> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static void setObjectInIzpitvan_pokazatelById(int id, Object updateObject,
			String colum) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		EntityTransaction updateTranzaction = entitymanager.getTransaction();
		updateTranzaction.begin();
		Query query = entitymanager
				.createQuery("UPDATE IzpitvanPokazatel e SET e." + colum + "= :coll WHERE e.Id_pokazatel= :id");
		query.setParameter("coll", updateObject).setParameter("id", id);

		query.executeUpdate();
		updateTranzaction.commit();

		entitymanager.close();
		emfactory.close();
	}

	public static void updateIzpitvanPokazatel(IzpitvanPokazatel pokazatel) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.find(IzpitvanPokazatel.class, pokazatel.getId_pokazatel());
		entitymanager.merge(pokazatel);

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null,
					"Прблем при обновяване на показател: " + pokazatel.getPokazatel().getName_pokazatel(),
					"Проблем с база данни:", JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}

	public static void test() {
		List<IzpitvanPokazatel> list = getInListAllValueIzpitvan_pokazatel();
		List<List_izpitvan_pokazatel> ress = new ArrayList<List_izpitvan_pokazatel>();
		System.out.println(list.size());
		int i = 0;
		for (IzpitvanPokazatel izpitvanPokazatel : list) {
			System.out.println(izpitvanPokazatel.getPokazatel().getName_pokazatel());
			ress.add(izpitvanPokazatel.getPokazatel());
			System.out.println(" ---- " + i);
			i++;
		}
		Set<List_izpitvan_pokazatel> unique = new HashSet<List_izpitvan_pokazatel>(ress);
		System.out.println(unique.size() + " " + i);

	}

	public static void deleteIzpitvanPokazatel(IzpitvanPokazatel pokazatel) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.find(IzpitvanPokazatel.class, pokazatel.getId_pokazatel());
		entitymanager.remove(pokazatel);

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null,
					"Прблем при изтриване на показател: " + pokazatel.getPokazatel().getName_pokazatel(),
					"Проблем с база данни:", JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}

	public static void deleteIzpitvanPokazatelByRequest(Request request) {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		String strInfo = "";
		List<IzpitvanPokazatel> listIzpPokazatel = getValueIzpitvan_pokazatelByRequest(request);
		for (IzpitvanPokazatel izpitvanPokazatel : listIzpPokazatel) {
			strInfo = izpitvanPokazatel.getPokazatel().getName_pokazatel();
			IzpitvanPokazatel izp = entitymanager.find(IzpitvanPokazatel.class, izpitvanPokazatel.getId_pokazatel());
			entitymanager.remove(izp);
		}

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null, "Прблем при изтриване на показател: " + strInfo,
					"Проблем с база данни:", JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}

}
