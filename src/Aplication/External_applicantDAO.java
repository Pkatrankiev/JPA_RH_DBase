package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


import DBase_Class.External_applicant;
import DBase_Class.Izpitvan_produkt;

public class External_applicantDAO {
	
	static String name_DBase = "JPA_RH_DBase";

	
	public static void setBasikValueExternal_applicant(){
		setValueExternal_applicant("няма информация", "няма информация","" , "няма информация");
		setValueExternal_applicant("ДП РАО", "гр.Колодуй","" , "2345");
		setValueExternal_applicant("ДП РАО1", "гр.Колодуй2", "0888123456", "ном:2345");
		
	}
			
			
	public static void setValueExternal_applicant(
			String external_applicant_name, 
			String external_applicant_address, 
			String external_applicant_telephone,
			String external_applicant_contract_number){
	
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
	
	
		External_applicant valueEnt = new External_applicant();
		
		valueEnt.setExternal_applicant_name(external_applicant_name);
		valueEnt.setExternal_applicant_address(external_applicant_address);
		valueEnt.setExternal_applicant_telephone(external_applicant_telephone);
		valueEnt.setExternal_applicant_contract_number(external_applicant_contract_number);
		
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		
	}
	
	public static void setValueExternal_applicant(External_applicant valueEnt){
	
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
			
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		
	}
	
	public static int setValueExternal_applicantWhithCheck(External_applicant valueEnt){
		int id=-1;
		List<External_applicant> list = getInListAllExternalApplicant();
	for (External_applicant external_applicant : list) {
		if(external_applicant.getExternal_applicant_address().contains(valueEnt.getExternal_applicant_address())
		&& external_applicant.getExternal_applicant_contract_number().contains(valueEnt.getExternal_applicant_contract_number())
		&& external_applicant.getExternal_applicant_name().contains(valueEnt.getExternal_applicant_name())
		&& external_applicant.getExternal_applicant_telephone().contains(valueEnt.getExternal_applicant_telephone())
		){
			id = external_applicant.getId_external_applicant();
		}
	}
			
	return id;
		
	}
	
	public static List<External_applicant> getInListAllExternalApplicant() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM External_applicant e ORDER BY e.external_applicant_name ASC");
		List<External_applicant> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (External_applicant e : list) {
			System.out.println("Num:" + ((External_applicant) e).getId_external_applicant()+ 
					"  external_applicant_name :"+ ((External_applicant) e).getExternal_applicant_name()+
					"  external_applicant_address :" + ((External_applicant) e).getExternal_applicant_address()+
					"  external_applicant_telephone :" + ((External_applicant) e).getExternal_applicant_telephone()+
					"  external_applicant_contract_number :" + ((External_applicant) e).getExternal_applicant_contract_number());
		}
		
		
		return list;
	}

		@GET
		@QueryParam("{id}")
	public static External_applicant getValueExternal_applicantById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		External_applicant  external_applicant = (External_applicant) entitymanager.find(External_applicant.class, id);
		
		
		entitymanager.close();
		emfactory.close();

		return external_applicant;
	}


}
