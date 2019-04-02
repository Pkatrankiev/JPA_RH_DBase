package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Dobiv;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.Metody;


import DBase_Class.Nuclide;
import DBase_Class.Request;
import DBase_Class.Results;
import DBase_Class.TSI;
import DBase_Class.Users;

public class DobivDAO {
	static String name_DBase = "JPA_RH_DBase";

	public static void setValueDobiv(
			String code_Standart,
			Metody metody,
			Izpitvan_produkt izpitvan_produkt,
			String description,
			Nuclide nuclide,
			Double value_result,
			Double uncertainty,
			TSI tsi,
			Users user_chim_oper,
			String date_chim_oper,
			Users user_measur,
			String date_measur,
			Users user_redac,
			String date_redac,
			String basic_value) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Dobiv valueEnt = new Dobiv();
		
		valueEnt.setCode_Standart(code_Standart);
		valueEnt.setMetody(metody);
		valueEnt.setIzpitvan_produkt(izpitvan_produkt);
		valueEnt.setDescription(description);
		valueEnt.setNuclide(nuclide);
		valueEnt.setValue_result(value_result);
		valueEnt.setUncertainty(uncertainty);
		valueEnt.setTsi(tsi);
		valueEnt.setUser_chim_oper(user_chim_oper);
		valueEnt.setDate_chim_oper(date_chim_oper);
		valueEnt.setUser_measur(user_measur);
		valueEnt.setDate_measur(date_measur);
		valueEnt.setUser_redac(user_redac);
		valueEnt.setDate_redac(date_redac);
		valueEnt.setBasic_value(basic_value);
		
		
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

		@SuppressWarnings("unchecked")
		public static List<Dobiv> getListAllDobiv() {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createNamedQuery("getListAllDobiv");
		List<Dobiv> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

		@GET
		@QueryParam("{id}")
		public static Dobiv getDobivById(@QueryParam("id") int id) {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			Dobiv naredbi = entitymanager.find(Dobiv.class, id);

			entitymanager.close();
			emfactory.close();

			return naredbi;
		}
		
		@SuppressWarnings("unchecked")
		public static List<Dobiv> getList_DobivByCode_Standart(String name) {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			Query query = entitymanager.createNamedQuery("findNDobivByCode_Standart");
			query.setParameter("text", name);
		
			List<Dobiv> list = query.getResultList();
			
			entitymanager.close();
			emfactory.close();
			
			
			return list;
			
		}
		
		@SuppressWarnings("unchecked")
		public static List<Dobiv> getListDobivByMetody(Metody name) {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			Query query = entitymanager.createNamedQuery("findDobivByMetody");
			query.setParameter("text", name);
		
			List<Dobiv> list = query.getResultList();
			
			entitymanager.close();
			emfactory.close();

			return list;
		}

		@SuppressWarnings("unchecked")
		public static List<Dobiv> getListDobivByIzpitvan_produkt(Izpitvan_produkt name) {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			Query query = entitymanager.createNamedQuery("findDobivByIzpitvan_produkt");
			query.setParameter("text", name);
		
			List<Dobiv> list = query.getResultList();
			
			entitymanager.close();
			emfactory.close();

			return list;
		}
		
		@SuppressWarnings("unchecked")
		public static List<Dobiv> getListDobivByNuclide(Nuclide name) {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
			
			Query query = entitymanager.createNamedQuery("findDobivByNuclide");
			query.setParameter("text", name);
		
			List<Dobiv> list = query.getResultList();
			
			entitymanager.close();
			emfactory.close();

			return list;
		}

		@SuppressWarnings("unchecked")
		public static List<Dobiv> getListResultsFromColumnByVolume(String column_name, Object volume_check) {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();

			String hql = "SELECT e FROM Dobiv e WHERE e."+column_name+" = :text ORDER BY e.code_Standart ASC";

			Query query = entitymanager.createQuery(hql);
			query.setParameter("text", volume_check);
			
			List<Dobiv>  list =  query.getResultList();
			entitymanager.close();
			emfactory.close();

			return list;
		}
		
		public static void updateDobiv(Dobiv dobiv) {

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();
		
			entitymanager.find(Dobiv.class, dobiv.getId_dobiv());
			entitymanager.merge(dobiv);


			try {
				entitymanager.getTransaction().commit();
			} catch (javax.persistence.RollbackException e) {
				JOptionPane.showMessageDialog(null, "Прблем при обновяване на добив: "+dobiv.getCode_Standart()+" "+dobiv.getNuclide().getSymbol_nuclide(), "Проблем с база данни:",
						JOptionPane.ERROR_MESSAGE);
			}

			entitymanager.close();
			emfactory.close();
		}
		
		public static void deleteDobivById(int id) {
			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
			EntityManager entitymanager = emfactory.createEntityManager();
			EntityTransaction updateTranzaction = entitymanager.getTransaction();
			updateTranzaction.begin();
			Query query = entitymanager.createQuery(" delete from Dobiv where id =:id");
			query.setParameter("id", id);
			
	        query.executeUpdate();
	        updateTranzaction.commit();
					
			entitymanager.close();
			emfactory.close();
			}
			
		public static Boolean checkDobivCode(String check_code) {
			Boolean available = false;
		
			if (getList_DobivByCode_Standart(check_code).size() != 0)
				System.out.println(getList_DobivByCode_Standart(check_code).size());
				available = true;
			return available;
		}


}
