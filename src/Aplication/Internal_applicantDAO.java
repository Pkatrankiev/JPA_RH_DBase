package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Internal_applicant;
import DBase_Class.Request;
import GlobalVariable.GlobalVariableForSQL_DBase;

public class Internal_applicantDAO {
	
//	static String name_DBase = "JPA_RH_DBase";
//	static 	EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getLokalDBase();
	

	public static void setBasikValueInternal_applicant(){
		setValueInternal_applicant("няма информация", "няма информация", "няма информация");
		setValueInternal_applicant("Държавно предприятие “Радиоактивни отпадъци", "гр.Колодуй", "67-90");
		setValueInternal_applicant("ДП РАО, с-р ОРДК", "гр.Колодуй", "67-90");
		setValueInternal_applicant("ДП РАО, с-р РХ1", "гр.Колодуй", "20-24");
		
	}

	public static void setValueInternal_applicant(
			String internal_applicant_organization,
			String internal_applicant_address, 
			String internal_applicant_telephone)
			{
		
		Internal_applicant valueEnt = new Internal_applicant();
		
		valueEnt.setInternal_applicant_organization(internal_applicant_organization);
		valueEnt.setInternal_applicant_address(internal_applicant_address);
		valueEnt.setInternal_applicant_telephone(internal_applicant_telephone);
		
		setValueInternal_applicant( valueEnt);
				
	}
	
	public static void setValueInternal_applicant(Internal_applicant valueEnt){
	
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
				
		entitymanager.persist(valueEnt);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		
	}
	
	public static List<Internal_applicant> getInListAllInternal_applicant() {
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Internal_applicant e");
		@SuppressWarnings("unchecked")
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
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
	
		Internal_applicant  internal_applicant = (Internal_applicant) entitymanager.find(Internal_applicant.class, id);
				

		entitymanager.close();
		emfactory.close();

		return internal_applicant;
	}

	public static Internal_applicant getValueInternal_applicantByName(String internal_applicant_organization) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Internal_applicant e WHERE e.internal_applicant_organization = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", internal_applicant_organization);
		if (query.getResultList().isEmpty()){
			
			/**    value not in Base        **/
			
			}
		Internal_applicant list = (Internal_applicant) query.getSingleResult();
		entitymanager.close();
		emfactory.close();

		return list;
	}

	public static int setValueInternal_applicantWhithCheck(Internal_applicant valueEnt){
		int id=-1;
		List<Internal_applicant> list = getInListAllInternal_applicant();
		
	for (Internal_applicant internal_applicant : list) {
		if(internal_applicant.getInternal_applicant_address().contains(valueEnt.getInternal_applicant_address())
		&& internal_applicant.getInternal_applicant_organization().contains(valueEnt.getInternal_applicant_organization())
		&& internal_applicant.getInternal_applicant_telephone().contains(valueEnt.getInternal_applicant_telephone())
		){
			id = internal_applicant.getId_internal_applicant();
		}
	}
	
	return id;
		
	}
	
	public static void updateObjectInternal_applicant(Internal_applicant valueEnt) {

//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManagerFactory emfactory = GlobalVariableForSQL_DBase.getDBase();
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.find(Request.class, valueEnt.getId_internal_applicant());
		entitymanager.merge(valueEnt);

		try {
			entitymanager.getTransaction().commit();
		} catch (javax.persistence.RollbackException e) {
			JOptionPane.showMessageDialog(null, "Прблем при обновяване на данните на: " + valueEnt.getInternal_applicant_organization(),
					"Проблем с база данни:", JOptionPane.ERROR_MESSAGE);
		}

		entitymanager.close();
		emfactory.close();
	}
}
