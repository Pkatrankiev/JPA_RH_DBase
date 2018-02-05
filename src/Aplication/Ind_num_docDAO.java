package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Ind_num_doc;
import DBase_Class.Internal_applicant;
import DBase_Class.Izpitvan_produkt;

public class Ind_num_docDAO {
	
	static String name_DBase = "JPA_RH_DBase";
	

	public static void setBasikValueInd_num_doc(){
		setValueInd_num_doc("няма информация","няма информация");
		setValueInd_num_doc("ПП12-ИЕ.РНК.ПН.007/00","Охарактеризиране на шламове и утайки в СП,,ИЕ 1÷4 блок");
		setValueInd_num_doc("ОП08-ИЕ.ФХК.ПР.002/00", "Проект 5f. Осигуряване проследимост на пробите от БНС и БВС на СК-1 и СК-2");
		setValueInd_num_doc("ОП01-ИЕ.РНК.УН.001/01", "Обем и организация на радиохимичния и технологичен контрол");
		
	}
	
	public static void setValueInd_num_doc(String value, String content) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Ind_num_doc valueEnt = new Ind_num_doc();
		valueEnt.setName(value);
		valueEnt.setContent(content);
		;
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public static List<Ind_num_doc> getInListAllValueInd_num_doc() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Ind_num_doc e");
		List<Ind_num_doc> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		
		for (Ind_num_doc e : list) {
			System.out
					.println("Num:" + ((Ind_num_doc) e).getId_ind_num_doc() + "  NAME :" + ((Ind_num_doc) e).getName());
		}
		return list;
	}

	
	@GET
	@QueryParam("{id}")
	public static Ind_num_doc getValueInternal_applicantById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
	
		Ind_num_doc  ind_num_doc = (Ind_num_doc) entitymanager.find(Ind_num_doc.class, id);
		
		entitymanager.close();
		emfactory.close();

		return ind_num_doc;
	}


	@GET
	public static Ind_num_doc getValueIzpitvan_produktByName(String name) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Ind_num_doc e WHERE e.name = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", name);
		if (query.getResultList().isEmpty()){
			setValueInd_num_doc(name, " ");	
		}
		Ind_num_doc list = (Ind_num_doc) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}

}

