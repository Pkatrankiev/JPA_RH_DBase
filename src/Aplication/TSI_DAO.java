package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Dimension;
import DBase_Class.Izpitvan_produkt;
import DBase_Class.TSI;

public class TSI_DAO {
static String name_DBase = "JPA_RH_DBase";

	
	public static void setValueTSI(String name, String descript) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		TSI valueEnt = new TSI();
		valueEnt.setName(name);
		valueEnt.setDescript(descript);
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<TSI> getListAllValueTSI() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM TSI e");
		@SuppressWarnings("unchecked")
		List<TSI> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		return list;
	}

	
	@GET
	@QueryParam("{id}")
	public static TSI getValueTSIById(@QueryParam("id_tsi") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		TSI ind_num_doc = entitymanager.find(TSI.class, id);

		
		entitymanager.close();
		emfactory.close();

		return ind_num_doc;
	}

	@GET
	public static TSI getValueTSIByName(String name) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM TSI e WHERE e.name = :text";
		TSI list;
		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		if (query.getResultList().isEmpty()) {
			list = (TSI) getValueTSIById(9);
		} else
			list = (TSI) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}
	
	@GET
	public static TSI getValueTSIByNumberFromName(String num) {
		List<TSI> listTSI = getListAllValueTSI();
		
		for (TSI tsi : listTSI) {
			if(tsi.getName().indexOf(num)>=0){
				return tsi;
			}
		}
		
		return listTSI.get(9);
	}
	
	public static String[] getMasiveStringAllValueTSI(){
		 List<TSI> list = getListAllValueTSI();
		String[] values = new String[list.size()];
		int i = 0;
		for (TSI tsi : list) {
			values[i] = tsi.getName();
			i++;
		}
		return values;
	}
}
