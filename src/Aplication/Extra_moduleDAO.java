package Aplication;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

import DBase_Class.Aplicant;
import DBase_Class.External_applicant;
import DBase_Class.Extra_module;
import DBase_Class.Internal_applicant;
import DBase_Class.DopalnIziskv;
import DBase_Class.Sample;

public class Extra_moduleDAO {

	static String name_DBase = "JPA_RH_DBase";
	
	
	public static void setValueExtra_module(External_applicant external_applicant, 
			Internal_applicant internal_applicant, 
			DopalnIziskv otclonenie,
			Aplicant aplicant,
			Boolean return_samples,
			String additional_requirements,
			String additional_arrangements){
		
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Extra_module xtra_module = new Extra_module();
		
		xtra_module.setExternal_applicant(external_applicant);
		xtra_module.setInternal_applicant(internal_applicant);
		xtra_module.setReturn_samples(return_samples);
		xtra_module.setAplicant(aplicant);
		xtra_module.setDoplIzisk(otclonenie);
		xtra_module.setAdditional_requirements(additional_requirements);
		xtra_module.setAdditional_arrangements(additional_arrangements);
		
		entitymanager.persist(xtra_module);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	public static void setValueExtra_module(Extra_module xtra_module){
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		entitymanager.persist(xtra_module);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	public static Extra_module saveAndGetExtra_module(Extra_module extra_module){
		int id = CheckExtra_module(extra_module);
		if(id<0){
			setValueExtra_module(extra_module);
			return extra_module;
		}else{
			return getValueExtra_moduleById(id);
		}
	}
	
	public static int CheckExtra_module(Extra_module extra_module){
		int id_extraMod = -1;
		List<Extra_module>  list = getInListAllValueExtra_module();
		Boolean fl = true;
		for (Extra_module extraModule : list) {
			extra_module.getAdditional_arrangements();
			extraModule.getAdditional_arrangements();
			fl=true;
			if(!extra_module.getAdditional_arrangements().equals(extraModule.getAdditional_arrangements())){
				fl=false;
				System.out.println("Additional_arrangements "+fl);
			}
			if( !extra_module.getAdditional_requirements().equals(extraModule.getAdditional_requirements())){
				fl=false;	
				System.out.println("Additional_requirements "+fl);
					}
			if( extra_module.getReturn_samples()!=extraModule.getReturn_samples()){
				fl=false;	
				System.out.println("Return_samples "+fl);
					}
			if( extra_module.getAplicant().getId_aplicant() != extraModule.getAplicant().getId_aplicant()){
				fl=false;
				System.out.println("Aplicant "+fl);
					}
			
			
			if(extra_module.getExternal_applicant()!=null && extraModule.getExternal_applicant()!=null){
			
			if(extra_module.getExternal_applicant().getId_external_applicant() != extraModule.getExternal_applicant().getId_external_applicant()){
				fl=false;
				System.out.println("External_applicant "+fl);
					}
			}else{
				if(extra_module.getExternal_applicant()==null && extraModule.getExternal_applicant()==null){
				
			}else{
				fl=false;
				System.out.println("External_applicant "+fl);
			}
			}
			
				if(extra_module.getInternal_applicant()!=null && extraModule.getInternal_applicant()!=null){
					
					if(extra_module.getInternal_applicant().getId_internal_applicant() != extraModule.getInternal_applicant().getId_internal_applicant()){
						fl=false;
						System.out.println("getInternal_applicant "+fl);
							}
					}else{
						if(extra_module.getInternal_applicant()==null && extraModule.getInternal_applicant()==null){
							
					}else{
						fl=false;
						System.out.println("getInternal_applicant "+fl);
					}
					}
			if( extra_module.getDoplIzisk().getId_dopIzis() != extraModule.getDoplIzisk().getId_dopIzis()){
				fl=false;
				System.out.println("getOtclonenie "+fl);
					}
			
			if(fl){
								
				id_extraMod = extraModule.getId_extra_module();
				return id_extraMod;
			}
			}
		return id_extraMod;
		}
	
	
		
	
	
	@SuppressWarnings("unchecked")
	public static List<Extra_module> getInListAllValueExtra_module(){
	
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT e FROM Extra_module e");
		List<Extra_module> list = query.getResultList();
		entitymanager.close();
		emfactory.close();
		
		return list;
	}
	
	@GET
	@QueryParam("{id}")
	public static Extra_module getValueExtra_moduleById(@QueryParam("id") int id) {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
	EntityManager entitymanager = emfactory.createEntityManager();
	entitymanager.getTransaction().begin();
	
	Extra_module  extra_module = (Extra_module) entitymanager.find(Extra_module.class, id);
	
	entitymanager.close();
	emfactory.close();

	return extra_module;
}
	
	@SuppressWarnings("unchecked")
	public static List<Extra_module> getExtra_moduleFromColumnByVolume(String column_name, Object volume_check) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(name_DBase);
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		String hql = "SELECT e FROM Extra_module e WHERE e."+column_name+" = :text";

		Query query = entitymanager.createQuery(hql);
		query.setParameter("text", volume_check);
		
		List<Extra_module>  list =  query.getResultList();
		entitymanager.close();
		emfactory.close();

		return list;
	}


}
