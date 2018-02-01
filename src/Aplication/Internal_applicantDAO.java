package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.External_applicant;
import DBase_Class.Internal_applicant;

public class Internal_applicantDAO {
	
	static String name_DBase = "JPA_RH_DBase";

	public static void setBasikValueInternal_applicant(){
		
		setValueInternal_applicant("�������� ����������� ������������� ��������", "��.�������", "67-90");
		setValueInternal_applicant("�� ���, �-� ����", "��.�������", "67-90");
		setValueInternal_applicant("�� ���, �-� ��1", "��.�������", "20-24");
		
	}

	public static void setValueInternal_applicant(
			String internal_applicant_organization,
			String internal_applicant_address, 
			String internal_applicant_telephone)
			{
	
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
	
	
		Internal_applicant valueEnt = new Internal_applicant();
		
		valueEnt.setInternal_applicant_organization(internal_applicant_organization);
		valueEnt.setInternal_applicant_address(internal_applicant_address);
		valueEnt.setInternal_applicant_telephone(internal_applicant_telephone);
		
		
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		
	}
	
	public static List<Internal_applicant> getInListAllInternal_applicant() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Internal_applicant e");
		List<Internal_applicant> list = query.getResultList();
		entitymanager.close();
		emfactory.close();

		for (Internal_applicant e : list) {
			System.out.println("Num:" + ((Internal_applicant) e).getId_internal_applicant()+ 
					"  external_applicant_name :"+ ((Internal_applicant) e).getInternal_applicant_organization()+
					"  external_applicant_address :" + ((Internal_applicant) e).getInternal_applicant_address()+
					"  external_applicant_telephone :" + ((Internal_applicant) e).getInternal_applicant_telephone());
		}
		return list;
	}
	
	@GET
	@QueryParam("{id}")
	public static Internal_applicant getValueInternal_applicantById(@QueryParam("id") int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
	
		Internal_applicant  internal_applicant = (Internal_applicant) entitymanager.find(Internal_applicant.class, id);
				

		entitymanager.close();
		emfactory.close();

		return internal_applicant;
	}
}
